package com.example.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.converter.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {
    EditText username,password;
    Button btn_login;
    TextView sign;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        firebaseAuth = FirebaseAuth.getInstance();


        username = findViewById(R.id.ab);
        password = findViewById(R.id.ab1);
        btn_login = findViewById(R.id.login);
        sign = findViewById(R.id.sign);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("plz enter email");
                    username.requestFocus();
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("plz enter password");
                    password.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    password.setError("Length of password maore than 6");
                    password.requestFocus();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(activity_login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }
}