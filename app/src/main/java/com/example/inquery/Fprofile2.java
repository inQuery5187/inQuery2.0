package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Fprofile2 extends AppCompatActivity {
    EditText name,rollno,branch,gender,phoneno,email,address;
    ImageView submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fprofile2);
        name=findViewById(R.id.name);
        rollno=findViewById(R.id.roll_no);
        branch=findViewById(R.id.branch);
        gender=findViewById(R.id.gender);
        phoneno=findViewById(R.id.phone_no);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                int r=Integer.parseInt(rollno.getText().toString());
                String b=branch.getText().toString();
                String g=gender.getText().toString();
                String p=phoneno.getText().toString();
                String e=email.getText().toString();
                String a=address.getText().toString();
                Toast.makeText(Fprofile2.this, "Profile Edited Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}