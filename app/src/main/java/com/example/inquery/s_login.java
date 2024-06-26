package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class s_login extends AppCompatActivity {
    EditText userId, userPwd;
    ImageView signUp, login, pwdsh;
    DatabaseReference reference;
    String ID;
    private static final String SHARED_PREFS= "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);
        getSupportActionBar().hide();
        checkLogin();
        userId= findViewById(R.id.name);
        userPwd= findViewById(R.id.userPwd);
        signUp= findViewById(R.id.signupBtn);
        login= findViewById(R.id.loginBtn);
        pwdsh= findViewById(R.id.icon_pwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.setImageResource(R.drawable.button_medium_dark);
                ID= userId.getText().toString();
                String pwd= userPwd.getText().toString();

                //if id and pwd exists in database login
                reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users");
                reference.child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()){
                                DataSnapshot dataSnapshot= task.getResult();
                                String pwdChk= String.valueOf(dataSnapshot.child("pwd").getValue());
                                if(pwdChk.equals(pwd)){
                                    Toast.makeText(s_login.this, "Logged in using Password!", Toast.LENGTH_SHORT).show();
                                    Intent extraIntent = new Intent(s_login.this, s_home.class);
                                    SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("sID", ID);
                                    editor.putString("flag", "true");
                                    editor.apply();
                                    startActivity(extraIntent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(s_login.this, "incorrect password", Toast.LENGTH_SHORT).show();
                                    login.setImageResource(R.drawable.button_medium);
                                }
                            }
                        }
                    }
                });
            }
        });
        pwdsh.setImageResource(R.drawable.icon_notvisible);
        pwdsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((userPwd.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))){
                    //if pwd is visible hide it
                    userPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwdsh.setImageResource(R.drawable.icon_notvisible);
                }else{
                    userPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwdsh.setImageResource(R.drawable.icon_visible);
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp.setImageResource(R.drawable.button_medium_dark);
                Intent intent= new Intent(s_login.this, s_signup.class);
                startActivity(intent);
            }
        });

    }

    private void checkLogin() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("flag", "");

        if(check.equals("true")){
            Intent extraIntent = new Intent(s_login.this, s_home.class);
            startActivity(extraIntent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(s_login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}