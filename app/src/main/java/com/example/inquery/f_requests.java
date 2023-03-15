package com.example.inquery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class f_requests extends AppCompatActivity {
    String ID, reason;
    DatabaseReference reference;
//    ArrayList<Requests> request = new ArrayList<>();
//    ArrayList<String> leaves = new ArrayList<>();
//    ArrayList<String> misconducts = new ArrayList<>();
//    ArrayList<String> custom = new ArrayList<>();
//    ArrayList<String> complaints = new ArrayList<>();
    Requests r1 =new Requests("leave", "2021b1541083", "Something");
    Requests r2 =new Requests("leave", "2021b1541083", "Something");
    Requests r3 =new Requests("misconduct", "2021b1541083", "Something");
    Requests r4 =new Requests("misconduct", "2021b1541083", "Something");
    Requests r5 =new Requests("custom", "2021b1541083", "Something");
    Requests r6 =new Requests("custom", "2021b1541083", "Something");
    Requests r7 =new Requests("complaint", "2021b1541083", "Something");
    Requests r8 =new Requests("complaint", "2021b1541083", "Something");
    Requests r9 =new Requests("misconduct", "2021b1541083", "Something");
    Requests r10 =new Requests("leave", "2021b1541083", "Something");
    Requests[] req= {r1,r2,r3,r4,r5,r6,r7,r8,r9,r10, };
    RecyclerView recyclerView;
    Requests requests;
    int count=0;

    private static final String SHARED_PREFS= "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequests);
        recyclerView= findViewById(R.id.recyclerView);
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("fID", "");

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter c= new CustomAdapter(req);
        recyclerView.setAdapter(c);

//        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID);
//        reference.child("leave").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot childSnapshot :
//                        snapshot.getChildren()) {
//                    leaves.add(childSnapshot.getKey());
////                    Log.d("REASONNNN", childSnapshot.getKey());
//                }
//                addToRequest();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        reference.child("misconduct").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot childSnapshot :
//                        snapshot.getChildren()) {
//                    misconducts.add(childSnapshot.getKey());
//                }
//                addToRequest();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        reference.child("complaints").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot childSnapshot :
//                        snapshot.getChildren()) {
//                    complaints.add(childSnapshot.getKey());
//                }
//                addToRequest();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        reference.child("custom").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot childSnapshot :
//                        snapshot.getChildren()) {
//                    custom.add(childSnapshot.getKey());
//                }
//                addToRequest();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        if(request!=null) {
//
//
//        }
    }
//    public void addToRequest(){
//
//        if(leaves!=null){
//
//            for(String str: leaves){
//                reference.child("leave").child(str).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                        DataSnapshot dataSnapshot = task.getResult();
//                        reason= String.valueOf(dataSnapshot.child("reason").getValue());
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//                    @Override
//                    public void onSuccess(DataSnapshot dataSnapshot) {
//                        leaves.clear();
//                        Requests requests= new Requests("leave", str, reason);
//                        request.add(requests);
//
//
////                        Log.d("REASONNNN",request.get(0).type);
//                    }
//                });
//            }
//            CustomAdapter c = new CustomAdapter(request);
//            c.setClickListener(f_requests.this);
//            recyclerView.setAdapter(c);
//
//        }
//        if(misconducts!=null){
//
//            for(String str: misconducts){
//                reference.child("misconduct").child(str).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                        DataSnapshot dataSnapshot = task.getResult();
//                        reason= String.valueOf(dataSnapshot.child("action").getValue());
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//                    @Override
//                    public void onSuccess(DataSnapshot dataSnapshot) {
//                        misconducts.clear();
//
//                        Requests requests= new Requests("misconduct", str, reason);
//                        request.add(requests);
//                    }
//                });
//            }
//
//        }
//        if(complaints!=null){
//
//            for(String str: complaints){
//                reference.child("complaints").child(str).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                        DataSnapshot dataSnapshot = task.getResult();
//                        reason= String.valueOf(dataSnapshot.child("reason").getValue());
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//                    @Override
//                    public void onSuccess(DataSnapshot dataSnapshot) {
//                        complaints.clear();
//                        Requests requests= new Requests("complaint", str, reason);
//                        request.add(requests);
//                    }
//                });
//            }
//
//        }
//        if(custom!=null) {
//            for (String str : custom) {
//                reference.child("custom").child(str).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                        DataSnapshot dataSnapshot = task.getResult();
//                        reason = String.valueOf(dataSnapshot.child("reason").getValue());
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//                    @Override
//                    public void onSuccess(DataSnapshot dataSnapshot) {
//                        custom.clear();
//                        Requests requests = new Requests("custom", str, reason);
//                        request.add(requests);
//                    }
//                });
//            }
//
//        }
//
//
//    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(f_requests.this, f_profile.class);
        startActivity(intent);
        finish();
    }


}

