package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class s_home extends AppCompatActivity {

    String ID;
    int no;
    RecyclerView recyclerView;
    DatabaseReference reference;
    RecyclerView listView;
    TextView profileBtn;
    ArrayList<Integer> images= new ArrayList<Integer>();
    ArrayList<String> requests= new ArrayList<>();
    private static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shome);
        getSupportActionBar().hide();
        recyclerView= (RecyclerView) findViewById(R.id.requestHistoryView);
        recyclerView.setVisibility(View.INVISIBLE);
        profileBtn = findViewById(R.id.profileBtn);
        listView= findViewById(R.id.horizontal_list);

        images.add(R.drawable.logo_leave);
        images.add(R.drawable.logo_complaint);
        images.add(R.drawable.logo_misconduct);
        images.add(R.drawable.logo_custom);
        requests.add("Leave");
        requests.add("Complaint");
        requests.add("Misconduct");
        requests.add("Custom");

        HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter(this, images, requests);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(horizontalListAdapter);

        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("sID", "");

        reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(ID);

        reference.child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot= task.getResult();
                String name= String.valueOf(dataSnapshot.getValue());
                profileBtn.setText(name);
            }
        });




        try {
            reference.child("requestHistory").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    no= (int)snapshot.getChildrenCount();
                    if(no>0){
                        recyclerView.setVisibility(View.VISIBLE);
                        getData();
                    }
                    else{
                        recyclerView.setVisibility(View.INVISIBLE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch(Exception e){
            recyclerView.setVisibility(View.INVISIBLE);
            Log.d("EXCP", e.toString());
        }

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(s_home.this, s_profile.class);
                startActivity(intent);
            }
        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(s_home.this, s_login.class);
//                startActivity(intent);
//                finish();
//                SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("sID", "");
//                editor.putString("flag", "");
//                editor.apply();
//                Toast.makeText(s_home.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void getData() {

        List<Requests> requ= new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(ID);
        reference.child("requestHistory").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Requests req= new Requests("", "", "", "");
                    req.type=childSnapshot.child("type").getValue(String.class);
                    req.reason= childSnapshot.child("reason").getValue(String.class);
                    req.status= childSnapshot.child("status").getValue(String.class);
                    requ.add(req);
                }
                setRecyclerView(requ);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setRecyclerView(List<Requests> requ) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RequestsAdapter requestsAdapter= new RequestsAdapter(requ, "Status: ", ID, "student");
        recyclerView.setAdapter(requestsAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(s_home.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
