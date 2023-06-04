package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class s_profile extends AppCompatActivity{
    TextView name, username, course, branch, yearAndSec, rollNo, phoneNo, email;
    Button logout;
    String ID;
    DatabaseReference reference;
    private static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprofile);
        getSupportActionBar().hide();
        name= findViewById(R.id.studentName);
        username= findViewById(R.id.studentUsername);
        course= findViewById(R.id.courseEntry);
        branch= findViewById(R.id.branchEntry);
        yearAndSec= findViewById(R.id.yearnSecEntry);
        rollNo= findViewById(R.id.rollNoEntry);
        phoneNo= findViewById(R.id.phoneNoEntry);
        email= findViewById(R.id.emailEntry);
        logout = findViewById(R.id.logoutBtn);

        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("sID", "");

        reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(ID);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot= task.getResult();
                name.setText(String.valueOf(snapshot.child("name").getValue()));
                username.setText(String.valueOf(snapshot.child("username").getValue()));
                course.setText(String.valueOf(snapshot.child("course").getValue()));
                branch.setText(String.valueOf(snapshot.child("branch").getValue()));
                yearAndSec.setText(String.valueOf(snapshot.child("yearAndSec").getValue()));
                rollNo.setText(String.valueOf(snapshot.child("rollno").getValue()));
                phoneNo.setText(String.valueOf(snapshot.child("phoneno").getValue()));
                email.setText(String.valueOf(snapshot.child("email").getValue()));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(s_profile.this, s_login.class);
                startActivity(intent);
                finish();
                SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sID", "");
                editor.putString("flag", "");
                editor.apply();
                Toast.makeText(s_profile.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
