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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button buttonSignIn;
    EditText emailId, password;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance ();
        emailId = findViewById (R.id.loginEmailText);
        password = findViewById (R.id.loginPasswordText);
        buttonSignIn = findViewById (R.id.loginButtonLogin);
        signUpBtn = findViewById(R.id.signUp);

        mAuthStateListener = new FirebaseAuth.AuthStateListener () {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mFirebaseAuth.getCurrentUser ();
                if(mFirebaseUser != null){
                    Toast.makeText (MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent loggedIn = new Intent(MainActivity.this, Dashboard.class);
                    startActivity (loggedIn);
                }
                else{
                    Toast.makeText (MainActivity.this, "You are not logged in", Toast.LENGTH_SHORT).show ();
                }
            }
        };

        buttonSignIn.setOnClickListener (new View.OnClickListener () {
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
                }
                else if (email.isEmpty () && pwd.isEmpty ()) {
                    Toast.makeText (MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show ();
                }
                else if (!(email.isEmpty () && pwd.isEmpty ())) {
                    mFirebaseAuth.signInWithEmailAndPassword (email, pwd).addOnCompleteListener (MainActivity.this, new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText (MainActivity.this, "Login Error"+ task.getException (), Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity (new Intent (MainActivity.this, Dashboard.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText (MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show ();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, Signup.class);
                        startActivity(intent);
                    }
                });

    }
    @Override
    protected void onStart() {
        super.onStart ();
        mFirebaseAuth.addAuthStateListener (mAuthStateListener);
    }
}