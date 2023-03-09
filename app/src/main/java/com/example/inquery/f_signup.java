package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class f_signup extends AppCompatActivity {
    EditText name, userID, userPwd, cpwd;
    ImageView submit;
    DatabaseReference reference;
    Data Data;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsignup);
        name= findViewById(R.id.epf_background);
        userID= findViewById(R.id.name);
        userPwd= findViewById(R.id.userPwd);
        cpwd= findViewById(R.id.cpwd);
        submit= findViewById(R.id.signUp);
        Data=new Data();
        int i=1;
        reference= FirebaseDatabase.getInstance().getReference().child("Data").child("Faculty").child("users");
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                submit.setImageResource(R.drawable.button_medium_dark);
                String nam = name.getText().toString().trim();
                String usernam = userID.getText().toString().trim();
                String p = userPwd.getText().toString();
                String cp = cpwd.getText().toString();
                if (p.equals(cp)) {
                    Data.setName(nam);
                    Data.setUsername(usernam);
                    Data.setPwd(p);
                    reference.child(userID.getText().toString().trim()).setValue(Data);
                    Toast.makeText(f_signup.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(f_signup.this, f_login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(f_signup.this, "password and repeat password don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}