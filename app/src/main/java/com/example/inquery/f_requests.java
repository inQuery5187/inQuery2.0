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

public class f_requests extends AppCompatActivity {
    String ID, reason;
    int no;
    DatabaseReference reference;
//    ArrayList<Requests> request = new ArrayList<>();
//    ArrayList<String> leaves = new ArrayList<>();
//    ArrayList<String> misconducts = new ArrayList<>();
//    ArrayList<String> custom = new ArrayList<>();
////    ArrayList<String> complaints = new ArrayList<>();
//    Requests r1 =new Requests("leave", "2021b1541083", "Something");
//    Requests r2 =new Requests("leave", "2021b1541083", "Something");
//    Requests r3 =new Requests("misconduct", "2021b1541083", "Something");
//    Requests r4 =new Requests("misconduct", "2021b1541083", "Something");
//    Requests r5 =new Requests("custom", "2021b1541083", "Something");
//    Requests r6 =new Requests("custom", "2021b1541083", "Something");
//    Requests r7 =new Requests("complaint", "2021b1541083", "Something");
//    Requests r8 =new Requests("complaint", "2021b1541083", "Something");
//    Requests r9 =new Requests("misconduct", "2021b1541083", "Something");
//    Requests r10 =new Requests("leave", "2021b1541083", "Something");
//    Requests[] req= {r1,r2,r3,r4,r5,r6,r7,r8,r9,r10, };
    RecyclerView recyclerView;
    Requests requests;
    int count=0;
    String type;
    TextView showType;
    private static final String SHARED_PREFS= "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequests);
        showType= findViewById(R.id.textView5);
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("fID", "");
        type= getIntent().getStringExtra("type");
        showType.setText(StringUtils.capitalize(type));



        reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID);
        reference.child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                no= (int)snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Requests[] requ = new Requests[no];
        reference.child(type).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Requests req= new Requests("", "", "");
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    req.type=type;
                    req.reason= childSnapshot.child("reason").getValue(String.class);
                    req.sender= childSnapshot.getKey();
                    requ[count]= req;
                    count++;
                }
                setRecyclerView(requ);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setRecyclerView(Requests[] requ) {
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Requests r1 =new Requests("leave", "2021b1541083", "Something");
//        Requests r2 =new Requests("leave", "2021b1541083", "Something");
//        Requests r3 =new Requests("misconduct", "2021b1541083", "Something");
//        Requests r4 =new Requests("misconduct", "2021b1541083", "Something");
//        Requests r5 =new Requests("custom", "2021b1541083", "Something");
//        Requests r6 =new Requests("custom", "2021b1541083", "Something");
//        Requests r7 =new Requests("complaint", "2021b1541083", "Something");
//        Requests r8 =new Requests("complaint", "2021b1541083", "Something");
//        Requests r9 =new Requests("misconduct", "2021b1541083", "Something");
//        Requests r10 =new Requests("leave", "2021b1541083", "Something");
//        Requests[] reques= {r1,r2,r3,r4,r5,r6,r7,r8,r9,r10, };
        CustomAdapter c= new CustomAdapter(requ);
        recyclerView.setAdapter(c);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(f_requests.this, f_profile.class);
//        startActivity(intent);
//        finish();
//    }


}

