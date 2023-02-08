package com.example.inquery;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class f_requests extends AppCompatActivity  {
    public EditText from, against, reason;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseReference reference;
    Button approve, reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequests);
        from= findViewById(R.id.rfrom);
        against= findViewById(R.id.ragainst);
        reason= findViewById(R.id.rreason);
        approve= findViewById(R.id.rapprove);
        reject= findViewById(R.id.rreject);
        from.setEnabled(false);
        against.setEnabled(false);
        reason.setEnabled(false);
        reference= FirebaseDatabase.getInstance().getReference("Data");
        reference.child("Faculty").child("complaints").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot= task.getResult();
                    String frm= String.valueOf(snapshot.child("from").getValue());
                    String agst= String.valueOf(snapshot.child("against").getValue());
                    String rsn= String.valueOf(snapshot.child("reason").getValue());
                    from.setText(frm);
                    against.setText(agst);
                    reason.setText(rsn);

                }
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Faculty").child("complaints").child("status").setValue("Approved!");

                Toast.makeText(f_requests.this, "Approved successfully!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(f_requests.this, f_profile.class);
                startActivity(intent);
                finish();
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Faculty").child("complaints").child("status").setValue("Rejected!");
                Toast.makeText(f_requests.this, "Rejected successfully!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(f_requests.this, f_profile.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(f_requests.this, f_profile.class);
        startActivity(intent);
        finish();
    }
}
