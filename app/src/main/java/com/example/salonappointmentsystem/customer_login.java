package com.example.salonappointmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class customer_login extends AppCompatActivity {

    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;

    FirebaseAuth cusAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        loginEmail = findViewById(R.id.salonLoginEmail);
        loginPassword = findViewById(R.id.salonLoginPassword);
        loginButton = findViewById(R.id.loginButtonForSalon);

        cusAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(view -> {
            loginUser();
        });

        TextView loginNav = findViewById(R.id.register);
        loginNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Register", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(customer_login.this, customerRegistration.class));
            }
        });
    }

    private void loginUser(){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            loginEmail.setError("Email cannot be empty");
            loginEmail.requestFocus();
        } else if(TextUtils.isEmpty(password)){
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
        }else {
            cusAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), show_saloon_list.class));
//                        startActivity(new Intent(getApplicationContext(), customerProfilePage.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Login Failed. Incorrect details!.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}