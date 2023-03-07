package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class f_profile extends AppCompatActivity {
    DatabaseReference reference;
    TextView userId, name;
    Button querybox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fprofile);
        userId= findViewById(R.id.name);
        name= findViewById(R.id.epf_background);
        querybox=findViewById(R.id.requests);
        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty");
        reference.child("2021b1541083").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        Toast.makeText(f_profile.this, "Data Exists!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String user = String.valueOf(dataSnapshot.child("username").getValue());
                        String nam = String.valueOf(dataSnapshot.child("name").getValue());
                        userId.setText(user);
                        name.setText(nam);
                    }
                }
            }

        });
        querybox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(f_profile.this, f_requests.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(f_profile.this, f_login.class);
        startActivity(intent);
        finish();
    }
}