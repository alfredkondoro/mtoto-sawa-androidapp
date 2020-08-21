package com.ab.mtotosawa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    TextView signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        loginBtn = (Button) findViewById(R.id.logInBtn);
        signUpBtn = findViewById(R.id.signUp);
        loginBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent dashboard = new Intent(getApplicationContext(),Dashboard.class);
                                            startActivity(dashboard);
                                        }
                                    });
                signUpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, signup.class);
                        startActivity(intent);
                    }
                });

    }
}