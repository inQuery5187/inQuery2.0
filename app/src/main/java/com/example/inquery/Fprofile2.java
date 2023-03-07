package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Fprofile2 extends AppCompatActivity {
    EditText name,facultyid,dept,phoneno,email;
    ImageView submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fprofile2);
        name=findViewById(R.id.userName);
        facultyid=findViewById(R.id.faculty_id);
        dept=findViewById(R.id.department);
        phoneno=findViewById(R.id.phone_no);
        email=findViewById(R.id.email);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String r=facultyid.getText().toString();
                String b=dept.getText().toString();
                String p=phoneno.getText().toString();
                String e=email.getText().toString();
                Toast.makeText(Fprofile2.this, "Profile Edited Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}