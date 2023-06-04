package com.example.inquery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class f_requests extends AppCompatActivity {
    String ID, reason;
    int no;
    DatabaseReference reference;
    RecyclerView recyclerView;
    Requests requests;
    int count=0;
    String type;
    TextView showType;
    ImageView showReq;
    private static final String SHARED_PREFS= "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequests);
        getSupportActionBar().hide();
        showType= findViewById(R.id.textView5);
        showReq= findViewById(R.id.noReq);
        showReq.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("fID", "");
        type= getIntent().getStringExtra("type");
        showType.setText(StringUtils.capitalize(type));
        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID);
        try {
            reference.child(type).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    no= (int)snapshot.getChildrenCount();
                    if(no>0){
                        getData();
                    }
                    else{
                        showReq.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch(Exception e){
            showReq.setVisibility(View.VISIBLE);
            Log.d("EXCP", e.toString());
        }



    }

    private void getData() {

        List<Requests> requ= new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID);
        reference.child(type).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Requests req= new Requests("", "", "", "");
                    req.type=type;
                    req.reason= childSnapshot.child("reason").getValue(String.class);
                    req.sender= childSnapshot.getKey();
                    req.UID= childSnapshot.child("UID").getValue(String.class);
                    requ.add(req);
                }
                setRecyclerView(requ, type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setRecyclerView(List<Requests> requ, String type) {
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RequestsAdapter requestsAdapter= new RequestsAdapter(requ, type, "Sender: ", ID, "faculty");
        recyclerView.setAdapter(requestsAdapter);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(f_requests.this, f_profile.class);
//        startActivity(intent);
//        finish();
//    }


}

