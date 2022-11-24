package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class s_signup extends AppCompatActivity {
    EditText name, userID, userPwd, userDesignation, userClass;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssignup);
        name= findViewById(R.id.name);
        userID= findViewById(R.id.userId);
        userPwd= findViewById(R.id.userPwd);
        userDesignation= findViewById(R.id.userDesignation);
        userClass= findViewById(R.id.userClass);
        submit= findViewById(R.id.signUP);
        userClass.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userDesignation.getText().toString().toLowerCase().equals("hod")){
                    userClass.setVisibility(View.VISIBLE);
                }
                Toast.makeText(s_signup.this, "Signed Up successfully!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(s_signup.this, s_login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}