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

public class s_completesignup extends AppCompatActivity {

    String name, username, pwd;
    EditText rollNo, course, branch, yearAndSec, ph, email;
    ImageView submit;
    DatabaseReference reference;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scompletesignup);
        getSupportActionBar().hide();

        rollNo= findViewById(R.id.name);
        course= findViewById(R.id.epf_background);
        branch= findViewById(R.id.userPwd);
        yearAndSec= findViewById(R.id.cpwd);
        ph= findViewById(R.id.cpwd2);
        email= findViewById(R.id.cpwd3);
        submit= findViewById(R.id.signUp);

        Bundle bundle= getIntent().getExtras();
        name= bundle.getString("name");
        username= bundle.getString("username");
        pwd= bundle.getString("pwd");
        data=new Data();
        reference= FirebaseDatabase.getInstance().getReference().child("Data").child("Student").child("users");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rn=  rollNo.getText().toString().trim();
                String crs= course.getText().toString().trim();
                String bch= branch.getText().toString().trim();
                String yrsc= yearAndSec.getText().toString().trim();
                String no= ph.getText().toString().trim();
                String mail= email.getText().toString().trim();
                if(rollNo.getText()!=null && course.getText()!=null && branch.getText()!=null && yearAndSec.getText()!=null && ph.getText()!=null && email.getText()!=null){
                    data.setName(name);
                    data.setUsername(username);
                    data.setPwd(pwd);
                    data.setRollno(rn);
                    data.setCourse(crs);
                    data.setBranch(bch);
                    data.setYearAndSec(yrsc);
                    data.setPhoneno(no);
                    data.setEmail(mail);
                    reference.child(username).setValue(data);
                    Toast.makeText(s_completesignup.this, "Data Registered successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(s_completesignup.this, s_login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(s_completesignup.this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}