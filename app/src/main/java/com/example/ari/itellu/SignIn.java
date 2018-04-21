package com.example.ari.itellu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ari.itellu.model.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    private EditText Nama;
    private EditText Pass;
    private TextView SignUp;
    private Button Login;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Nama = (EditText) findViewById(R.id.etusername);
        Pass = (EditText) findViewById(R.id.etpass);
        Login = (Button) findViewById(R.id.btnSignIn);
        SignUp = (TextView) findViewById(R.id.tvregist);

        //inisialisasi database firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        mDialog = new ProgressDialog(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.setMessage("Tunggu Sebentar");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //mengecek apakah user ada dalam database
                        if (dataSnapshot.child(Nama.getText().toString()).exists()) {
                            //Mengambil informasi user
                            mDialog.dismiss();
                            user User = dataSnapshot.child(Nama.getText().toString()).getValue(user.class);
                            if (User != null) {
                                if (User.getPassword().equals(Pass.getText().toString())) {
                                    Toast.makeText(SignIn.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignIn.this, "Password salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Pengguna ini tidak terdaftar", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, RegistAct.class));
            }
        });
    }
    private void validasi (String username, String password){

    }
}
