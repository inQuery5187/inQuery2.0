package com.example.inquery;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class f_chooseRequests extends AppCompatActivity {

    String ID;
    TextView showCount, profileBtn;
    RecyclerView listView;
    ArrayList<Integer> images= new ArrayList<Integer>();
    ArrayList<String> requests= new ArrayList<>();
    private int noOfRequests;
    int count=0;
    DatabaseReference reference;
    private static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fchoose_requests);
        getSupportActionBar().hide();
        listView= findViewById(R.id.recyclerView2);
        showCount= findViewById(R.id.showCount);
        profileBtn= findViewById(R.id.profileBtn);
        noOfRequests=0;
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("fID", "");
        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID.trim());
        //find count of requests
        ArrayList<String> req= new ArrayList<String>(Arrays.asList("leave", "misconduct", "complaints", "custom"));
        Log.d("SHOWCT", ""+req);
        for (int i=0; i<req.size();i++) {
            String str= req.get(i);
            findCount(str);

        }
        images.add(R.drawable.logo_leave);
        images.add(R.drawable.logo_complaint);
        images.add(R.drawable.logo_misconduct);
        images.add(R.drawable.logo_custom);
        requests.add("Leave");
        requests.add("Complaint");
        requests.add("Misconduct");
        requests.add("Custom");

        HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter(this, images, requests, "F");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(horizontalListAdapter);

        reference.child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot= task.getResult();
                String name= String.valueOf(dataSnapshot.getValue());
                profileBtn.setText(name);
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(f_chooseRequests.this, f_profile.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void findCount(String request){
        try {
            reference.child(request).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    noOfRequests+= (int)snapshot.getChildrenCount();
                    String s= ""+noOfRequests;
                    showCount.setText(s);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch (Exception e){

        }

    }

}