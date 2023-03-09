package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView facultyBtn, studentBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facultyBtn= findViewById(R.id.facultyBtn);
        studentBtn= findViewById(R.id.studentBtn);
        facultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facultyBtn.setImageResource(R.drawable.button_medium_dark);
                Intent intent= new Intent(MainActivity.this, f_login.class);
                startActivity(intent);
            }
        });
        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentBtn.setImageResource(R.drawable.button_medium_dark);
                Intent intent= new Intent(MainActivity.this, s_login.class);
                startActivity(intent);
            }
        });


    }
}