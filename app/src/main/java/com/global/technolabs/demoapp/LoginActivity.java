package com.global.technolabs.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText username, mobile;
    Button loginbtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        mobile = findViewById(R.id.mobile);
        loginbtn = findViewById(R.id.loginbtn);
        progressBar = findViewById(R.id.progressbar);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String str_username = username.getText().toString().trim();
        final String str_mobile = mobile.getText().toString().trim();
        if (str_username.isEmpty()) {
            Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_mobile.isEmpty()) {
            Toast.makeText(this, "Please Enter Mobile number", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        loginbtn.setVisibility(View.INVISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + str_mobile,
                60,
                TimeUnit.SECONDS,
                LoginActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        loginbtn.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressBar.setVisibility(View.GONE);
                        loginbtn.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        progressBar.setVisibility(View.GONE);
                        loginbtn.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
                        intent.putExtra("mobile", str_mobile);
                        intent.putExtra("verificationId", verificationId);
                        startActivity(intent);
                    }
                }
        );
    }

}