package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
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

public class s_profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DatabaseReference reference;
    TextView userId, name;
    Button query;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprofile);
        setNavigationViewListener();
        navigationView = (NavigationView) findViewById(R.id.nav_view);

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

        reference= FirebaseDatabase.getInstance().getReference("Data").child("Student");
        reference.child("2021B1541083").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        Toast.makeText(s_profile.this, "Data Exists!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String user = String.valueOf(dataSnapshot.child("username").getValue());
                        String nam = String.valueOf(dataSnapshot.child("name").getValue());
                        userId.setText(user);
                        name.setText(nam);
                    }
                }
            }
        });
//        query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(s_profile.this, queries.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(s_profile.this, sprofileedit.class);
//                startActivity(intent);
//                finish();
//            }
//        });
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
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(s_profile.this, s_home.class);
        startActivity(intent);
        finish();
    }
}