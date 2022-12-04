package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class s_signup extends AppCompatActivity {
    EditText name, userID, userPwd, cpwd;
    Button submit;
    DatabaseReference reference;
    Data Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssignup);
        name= findViewById(R.id.backgroundtop);
        userID= findViewById(R.id.userId);
        userPwd= findViewById(R.id.userPwd);
        cpwd= findViewById(R.id.cpwd);
        submit= findViewById(R.id.signUP);
        Data=new Data();
        reference= FirebaseDatabase.getInstance().getReference().child("Data").child("Student");
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nam = name.getText().toString().trim();
                String usernam = userID.getText().toString().trim();
                String p= userPwd.getText().toString();
                String cp = cpwd.getText().toString();
                if (p.equals(cp)&&p.length()>=8&&nam.length()>=6&&usernam.length()>=6) {
                Data.setName(nam);
                Data.setUsername(usernam);
                Data.setPwd(p);
                    reference.child(userID.getText().toString().trim()).setValue(Data);
                    Toast.makeText(s_signup.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(s_signup.this, s_login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(s_signup.this, "please fill correct details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}