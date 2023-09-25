package com.s4r1m.goanywhere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailLogin, passwordLogin;
    FirebaseAuth mAuth;
    MaterialButton buttonLogin;
    TextView clickToRegister;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v -> {
            String email, password;
            email = String.valueOf(emailLogin.getText());
            password = String.valueOf(passwordLogin.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(MainActivity.this, "Enter email ", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Enter password ", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });


        });
        clickToRegister = findViewById(R.id.clickToRegister);
        clickToRegister.setOnClickListener(v -> {
            Intent toRegister = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(toRegister);
            finish();
        });
    }

}