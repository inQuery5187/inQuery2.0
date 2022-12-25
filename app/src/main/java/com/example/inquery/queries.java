package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class queries extends AppCompatActivity {
    ImageView leave, doc, suggest, complaint, miscon, custom, qbox, profilebox;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

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
//        qbox=findViewById(R.id.qbox);
//        profilebox=findViewById(R.id.profilebox);

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
        drawerLayout = findViewById(R.id.my_drawer_layout1);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}