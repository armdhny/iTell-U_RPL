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
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    //Inisiasi
    private EditText Email;
    private EditText Pass;
    private TextView SignUp;
    private Button Login;
    private ProgressDialog mDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //Inisiasi
        Email = (EditText) findViewById(R.id.etEmailSignIn);
        Pass = (EditText) findViewById(R.id.etpass);
        Login = (Button) findViewById(R.id.btnSignIn);
        SignUp = (TextView) findViewById(R.id.tvregist);

        //inisialisasi database firebase
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_user = database.getReference("User");
        mDialog = new ProgressDialog(this);
        //Method yang di klik login ini ada process login sama validasi.
        //Validasinya yang getText to String is empty
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Email.getText().toString().isEmpty()) {
                    Email.setError("Required");
                    return;
                }

                if (Pass.getText().toString().isEmpty()) {
                    Pass.setError("Required");
                    return;
                }

                mDialog.setMessage("Please Wait");
                mDialog.setIndeterminate(true);
                mDialog.show();
                loginProcess();
            }
        });



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, RegistAct.class));
            }
        });
    }

    //Method ini berfungsi untuk melakukan proses login
    private void loginProcess() {
        final String str_email = Email.getText().toString();
        final String str_password = Pass.getText().toString();

        FirebaseC.mAuth.signInWithEmailAndPassword(str_email, str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            Log.d("", "signInWithEmail: Success");
                            FirebaseUser curUser = FirebaseC.mAuth.getCurrentUser();
                            FirebaseC.currentUser = curUser;
                            startActivity(new Intent(SignIn.this, NavigationActivity.class));
                            Toast.makeText(SignIn.this, "Login Success", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.w("", "signInWithEmail: failure", task.getException());
                            Toast.makeText(SignIn.this, "Account Doesn't Exist",
                                    Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }
                });
    }
}
