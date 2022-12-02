package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class queries extends AppCompatActivity {
    ImageView leave, doc, suggest, complaint, miscon, custom, qbox, profilebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);
        leave=findViewById(R.id.leave);
        doc=findViewById(R.id.doc);
        suggest=findViewById(R.id.suggest);
        complaint=findViewById(R.id.complaint);
        miscon=findViewById(R.id.miscon);
        custom=findViewById(R.id.custom);
        qbox=findViewById(R.id.qbox);
        profilebox=findViewById(R.id.profilebox);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(queries.this, q_leave.class);
                startActivity(intent);
            }
        });
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(queries.this, qcomplaint.class);
                startActivity(intent);
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(queries.this, qcustom.class);
                startActivity(intent);
            }
        });
        qbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(queries.this, queries.class);
                startActivity(intent);
            }
        });
        profilebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(queries.this, s_profile.class);
                startActivity(intent);
            }
        });
    }
}