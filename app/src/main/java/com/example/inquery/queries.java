package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class queries extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageView leavesingle, doc, leavemulti, complaint, miscon, custom, qbox, profilebox;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);
        setNavigationViewListener();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        leavesingle=findViewById(R.id.leave);
//        doc=findViewById(R.id.doc);
        //leavemulti=findViewById(R.id.leavemulti);
        complaint=findViewById(R.id.complaint);
        miscon=findViewById(R.id.miscon);
        custom=findViewById(R.id.custom);
//        qbox=findViewById(R.id.qbox);
//        profilebox=findViewById(R.id.profilebox);

        leavesingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leavesingle.setImageResource(R.drawable.button_queries_dark);
                Intent intent= new Intent(queries.this, qleave.class);
                startActivity(intent);
            }
        });
        /*leavemulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(queries.this, qleavemulti.class);
                startActivity(intent);
            }
        });*/
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaint.setImageResource(R.drawable.button_queries_dark);
                Intent intent= new Intent(queries.this, qcomplaint.class);
                startActivity(intent);
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom.setImageResource(R.drawable.button_queries_dark);
                Intent intent= new Intent(queries.this, qcustom.class);
                startActivity(intent);
            }
        });
        miscon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miscon.setImageResource(R.drawable.button_queries_dark);
                Intent intent= new Intent(queries.this, qmisconduct.class);
                startActivity(intent);
            }
        });
//        profilebox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(queries.this, s_profile.class);
//                startActivity(intent);
//            }
//        });
        drawerLayout = findViewById(R.id.my_drawer_layout1);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    }
    private void navProfile() {
        Intent intent= new Intent(this, s_profile.class);
        startActivity(intent);
    }
    private void navQueries() {
        Intent intents= new Intent(this, queries.class);
        startActivity(intents);
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
        Intent intent = new Intent(queries.this, s_home.class);
        startActivity(intent);
        finish();
    }
}