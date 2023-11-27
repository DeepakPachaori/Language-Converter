package com.example.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

EditText name,mail,password,confirm;
TextView login;
Button sign;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        confirm=findViewById(R.id.confirm);
        password=findViewById(R.id.password);
        sign=findViewById(R.id.sign);
        login=findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),activity_login.class));
            finish();
        }


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mail.getText().toString().trim();
                String Password=password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is reuired");
                    return;
                }
                if (password.length()<6){
                    password.setError("Password is to short");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),activity_login.class));
                        }
                        else{
                            Toast.makeText(SignUp.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),activity_login.class));
                    }
                });
            }
        });


    }
}