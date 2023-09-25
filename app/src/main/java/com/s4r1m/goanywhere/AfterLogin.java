package com.s4r1m.goanywhere;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AfterLogin extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView userDetails;
    MaterialButton buttonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userDetails = findViewById(R.id.email_textView);
        buttonLogout = findViewById(R.id.button_logout);
        if (user == null) {
            startActivity(new Intent(AfterLogin.this, MainActivity.class));
            finish();
        } else {
            userDetails.setText(user.getEmail());
        }

        buttonLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AfterLogin.this, MainActivity.class));
            finish();
        });
    }
}