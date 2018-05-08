package com.example.ari.itellu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

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
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_user = database.getReference("User");

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
                if (userEmail.getText().toString().isEmpty()) {
                    userEmail.setError("Required");
                    return;
                }

                if (userPass.getText().toString().isEmpty()) {
                    userPass.setError("Required");
                    return;
                }

                mDialog.setMessage("Proses Mendaftarkan");
                mDialog.setIndeterminate(true);
                mDialog.show();

                createUser();
            }
        });
    }

    private void createUser() {
        final String email = userEmail.getText().toString();
        String password = userPass.getText().toString();

        FirebaseC.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            Log.d("", "createUserWithEmail: Success");
                            FirebaseC.currentUser = FirebaseC.mAuth.getCurrentUser();
                            Toast.makeText(RegistAct.this, "Sign Up Complete", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistAct.this, SignIn.class));
                            finish();
                        } else {
                            mDialog.dismiss();
                            Log.w("", "createUserWithEmail: Failure", task.getException());
                            Toast.makeText(RegistAct.this, "Authentication Failure",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setupUIview(){
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

