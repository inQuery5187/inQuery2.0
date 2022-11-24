package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class f_signup extends AppCompatActivity {
    EditText name, userID, userPwd, userDesignation, userClass;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsignup);
        name= findViewById(R.id.name);
        userID= findViewById(R.id.userId);
        userPwd= findViewById(R.id.userPwd);
        submit= findViewById(R.id.signUP);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(f_signup.this, "Signed Up successfully!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(f_signup.this, f_login.class);
                startActivity(intent);
                finish();
            }
        });




    }
}