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

public class RegistAct extends AppCompatActivity {
    private EditText userName, userPass, userEmail;
    private Button btnRegist;
    private TextView userLogin;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        setupUIview();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        mDialog = new ProgressDialog(this);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistAct.this, SignIn.class));
            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setMessage("Tunggu Sebentar");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //mengecek apakah data sudah ada dalam database
                        if (dataSnapshot.child(userName.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(RegistAct.this, "Username sudah ada", Toast.LENGTH_SHORT).show();
                        }else {
                            mDialog.dismiss();
                            user User = new user(userEmail.getText().toString(),userPass.getText().toString());
                            table_user.child(userName.getText().toString()).setValue(User);
                            Toast.makeText(RegistAct.this, "Berhasil daftar", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    private void setupUIview(){
        userName = (EditText) findViewById(R.id.etUser);
        userPass = (EditText) findViewById(R.id.etPass);
        userEmail= (EditText) findViewById(R.id.etEmail);
        btnRegist = (Button) findViewById(R.id.btnSignup);
        userLogin = (TextView) findViewById(R.id.tvSignin);
    }
    /*private boolean validasi(){
        Boolean hasil = false;

        String nama = userName.getText().toString();
        String email = userEmail.getText().toString();
        String pass = userPass.getText().toString();


        if (nama.isEmpty()&& pass.isEmpty() && email.isEmpty()){
            Toast.makeText(this,"Harap di isi semua", Toast.LENGTH_SHORT).show();
        }else {
            hasil = true;
        }
        return hasil;
    }*/
}

