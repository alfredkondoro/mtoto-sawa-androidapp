package com.ab.mtotosawa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    Button buttonSignUp;
    EditText emailId, password;
    private FirebaseAuth mFirebaseAuth;
    TextView loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_signup);
        mFirebaseAuth = FirebaseAuth.getInstance ();
        emailId = (EditText)findViewById (R.id.registerEmailText);
        password =(EditText)findViewById (R.id.registerPasswordText);
        buttonSignUp = (Button)findViewById (R.id.registerButton);

        loginBtn = findViewById(R.id.loginBtn);

        buttonSignUp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String email = emailId.getText ().toString ();
                String pwd = password.getText ().toString ();
                if (email.isEmpty ()) {
                    emailId.setError ("Please enter the Email");
                    emailId.requestFocus ();
                } else if (pwd.isEmpty ()) {
                    password.setError ("Please enter the password");
                    password.requestFocus ();

                } else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(Signup.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Signup.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent (Signup.this, MainActivity.class));
                            }
                        }
                    });
                } else{
                    Toast.makeText(Signup.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(signUp);
            }
        });


    }
}