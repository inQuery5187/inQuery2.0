package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class f_completesignup extends AppCompatActivity {

    String name, username, pwd;
    EditText phoneNo, email, department, designation;
    ImageView submit;
    DatabaseReference reference;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcompletesignup);
        getSupportActionBar().hide();

        phoneNo= findViewById(R.id.name);
        email= findViewById(R.id.epf_background);
        department= findViewById(R.id.userPwd);
        designation= findViewById(R.id.cpwd);
        submit= findViewById(R.id.signUp);

        Bundle bundle= getIntent().getExtras();
        name= bundle.getString("name");
        username= bundle.getString("username");
        pwd= bundle.getString("pwd");
        data=new Data();
        reference= FirebaseDatabase.getInstance().getReference().child("Data").child("Faculty").child("users");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String no= phoneNo.getText().toString().trim();
                String mail= email.getText().toString().trim();
                String dep= department.getText().toString().trim();
                String des= designation.getText().toString().trim();
                if(phoneNo.getText()!=null && email.getText()!=null && department.getText()!=null && designation.getText()!=null){
                    data.setName(name);
                    data.setUsername(username);
                    data.setPwd(pwd);
                    data.setPhoneno(no);
                    data.setEmail(mail);
                    data.setDepartment(dep);
                    data.setDesignation(des);
                    reference.child(username).setValue(data);
                    Toast.makeText(f_completesignup.this, "Data Registered successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(f_completesignup.this, f_login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(f_completesignup.this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}