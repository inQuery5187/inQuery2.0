package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    TextView userId, name, department, design, number, email;
    Button logout;
    String ID;
    private static final String SHARED_PREFS= "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fprofile);
        getSupportActionBar().hide();
        userId= findViewById(R.id.name);
        name= findViewById(R.id.epf_background);
        logout= findViewById(R.id.button);
        department= findViewById(R.id.courseEntry);
        design= findViewById(R.id.branchEntry);
        number = findViewById(R.id.yearnSecEntry);
        email= findViewById(R.id.emailEntry);
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("fID", "");
        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot= task.getResult();
                name.setText(String.valueOf(snapshot.child("name").getValue()));
                userId.setText(String.valueOf(snapshot.child("username").getValue()));
                department.setText(String.valueOf(snapshot.child("department").getValue()));
                design.setText(String.valueOf(snapshot.child("designation").getValue()));
                number.setText(String.valueOf(snapshot.child("phoneno").getValue()));
                email.setText(String.valueOf(snapshot.child("email").getValue()));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(f_profile.this, f_login.class);
                startActivity(intent);
                finish();
                SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fID", "");
                editor.putString("Flag", "");
                editor.apply();
                Toast.makeText(f_profile.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(f_profile.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}