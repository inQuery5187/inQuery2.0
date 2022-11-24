package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class s_login extends AppCompatActivity {
    EditText userId, userPwd;
    TextView signUp;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);
        userId= findViewById(R.id.userId);
        userPwd= findViewById(R.id.userPwd);
        signUp= findViewById(R.id.studentBtn);
        login= findViewById(R.id.facultyBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID= userId.getText().toString();
                String pwd= userPwd.getText().toString();
                //if id and pwd exists in database login
                Intent intent= new Intent(s_login.this, s_profile.class);
                startActivity(intent);
                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(s_login.this, s_signup.class);
                startActivity(intent);
            }
        });
    }
}