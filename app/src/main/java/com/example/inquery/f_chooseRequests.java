package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class f_chooseRequests extends AppCompatActivity {
    ImageView leave, custom, misconduct, complaints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fchoose_requests);
//        leave= findViewById(R.id.leave);
//        misconduct= findViewById(R.id.miscon);
//        complaints= findViewById(R.id.complaint);
//        custom= findViewById(R.id.custom);

//        leave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(f_chooseRequests.this, f_requests.class);
//                intent.putExtra("type", "leave");
//                startActivity(intent);
//                finish();
//            }
//        });
//        custom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(f_chooseRequests.this, f_requests.class);
//                intent.putExtra("type", "custom");
//                startActivity(intent);
//                finish();
//            }
//        });
//        misconduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(f_chooseRequests.this, f_requests.class);
//                intent.putExtra("type", "misconduct");
//                startActivity(intent);
//                finish();
//            }
//        });
//        complaints.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(f_chooseRequests.this, f_requests.class);
//                intent.putExtra("type", "complaints");
//                startActivity(intent);
//                finish();
//            }
//        });


    }
}