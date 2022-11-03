package com.capstoneProject.grapebhumi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    TextInputLayout txtinputname;
    TextInputLayout txtinputpass;
    TextInputLayout txtinputphone;
    TextInputLayout txtinputotp;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^"+"(?=.*[0-9])"+"(?=.*[0-9])"+"(?=.*[a-zA-Z])"+"(?=\\S+S)"+".{6,}"+"$");

    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtinputname = findViewById(R.id.txt_name);
        txtinputpass = findViewById(R.id.txt_password);
        txtinputphone = findViewById(R.id.txt_phone);
        txtinputotp = findViewById(R.id.txt_otp);

        btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateName() | !validatePassword() | !validatePhone()){
                    return;
                }else{
                    startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                }
            }
        });
    }
    private boolean validateName(){
        String nameInput = txtinputname.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()){
            txtinputname.setError("Field can't be empty");
            return false;
        }else{
            txtinputname.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String passInput = txtinputpass.getEditText().getText().toString().trim();

        if (passInput.isEmpty()){
            txtinputpass.setError("Field can't be empty");
            return false;
        }else{
            txtinputpass.setError(null);
            return true;
        }
    }
    private boolean validatePhone(){
        String phoneInput = txtinputphone.getEditText().getText().toString().trim();

        if (phoneInput.isEmpty()){
            txtinputphone.setError("Field can't be empty");
            return false;
        }else{
            txtinputphone.setError(null);
            return true;
        }
    }

}