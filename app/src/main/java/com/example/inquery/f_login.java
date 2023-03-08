package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class f_login extends AppCompatActivity {
    EditText userId, userPwd;
    TextView forgotPwd;
    ImageView signUp, login;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flogin);
        userId= findViewById(R.id.name);
        userPwd= findViewById(R.id.userPwd);
        signUp= findViewById(R.id.signupBtn);
        login= findViewById(R.id.loginBtn);
        forgotPwd= findViewById(R.id.forgotBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID= userId.getText().toString();
                String pwd= userPwd.getText().toString();
                //if id and pwd exists in database login
                reference= FirebaseDatabase.getInstance().getReference("Data").child("Faculty");
                reference.child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()){

                                DataSnapshot dataSnapshot= task.getResult();
                                String pwdChk= String.valueOf(dataSnapshot.child("pwd").getValue());
                                if(pwdChk.equals(pwd)){

                                    Toast.makeText(f_login.this, "Logged in using Password!", Toast.LENGTH_SHORT).show();
                                    Intent extraIntent = new Intent(f_login.this, f_profile.class);
                                    startActivity(extraIntent);
                                    finish();
                                }
                            }
                        }
                    }
                });
                Intent intent= new Intent(f_login.this, f_profile.class);
                startActivity(intent);
                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(f_login.this, f_signup.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(f_login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}