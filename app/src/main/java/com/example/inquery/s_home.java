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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class s_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;
    String ID;
    int no;
    RecyclerView recyclerView;
    DatabaseReference reference;
    TextView logout;
    private static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shome);
        setNavigationViewListener();
//        logout= findViewById(R.id.button);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        recyclerView= (RecyclerView) findViewById(R.id.requestHistoryView);
        recyclerView.setVisibility(View.INVISIBLE);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout1);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("sID", "");

        reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(ID);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_home:{
                navHome();
                return true;
            }

            case R.id.nav_profile:
            {
                navProfile();
                return true;
            }

            case R.id.nav_queries:
            {
                navQueries();
                return true;
            }

            case R.id.nav_settings:{
                navSettings();
                return true;
            }

            }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navHome() {
        Intent intent= new Intent(this, s_home.class);
        startActivity(intent);
        finish();
    }
    private void navProfile() {
        Intent intent= new Intent(this, s_profile.class);
        startActivity(intent);
        finish();
    }
    private void navQueries() {
        Intent intents= new Intent(this, queries.class);
        startActivity(intents);
        finish();
    }
    private void navSettings() {
        Intent intents= new Intent(this, settings.class);
        startActivity(intents);
        finish();
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(s_home.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
