package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.regex.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class s_signup extends AppCompatActivity {
    EditText name, userID, userPwd, cpwd;
    ImageView submit, pwdsh, cpwdsh;
    DatabaseReference reference;

    public static boolean isValidPassword(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssignup);
        getSupportActionBar().hide();
        name= findViewById(R.id.epf_background);
        userID= findViewById(R.id.name);
        userPwd= findViewById(R.id.userPwd);
        cpwd= findViewById(R.id.cpwd);
        submit= findViewById(R.id.signUp);
        pwdsh= findViewById(R.id.icon_pwd);
        cpwdsh= findViewById(R.id.cpwd_img);

        reference= FirebaseDatabase.getInstance().getReference().child("Data").child("Student").child("users");
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nam = name.getText().toString().trim();
                String usernam = userID.getText().toString().trim();
                String p= userPwd.getText().toString();
                String cp = cpwd.getText().toString();
                if (usernam.length()>=12){
                    if (nam!=null){
                        if (isValidPassword(p)){
                            if (p.equals(cp)) {
                                Bundle bundle= new Bundle();
                                bundle.putString("name", nam);
                                bundle.putString("username", usernam);
                                bundle.putString("pwd", p);
                                Intent intent = new Intent(s_signup.this, s_completesignup.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(s_signup.this, "password and repeat password don't match", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(s_signup.this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(s_signup.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(s_signup.this, "Please enter your correct ID no.", Toast.LENGTH_SHORT).show();
                }
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
        cpwdsh.setImageResource(R.drawable.icon_notvisible);
        cpwdsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((cpwd.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))){
                    //if pwd is visible hide it
                    cpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cpwdsh.setImageResource(R.drawable.icon_notvisible);
                }else{
                    cpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cpwdsh.setImageResource(R.drawable.icon_visible);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(s_signup.this, s_login.class);
        startActivity(intent);
        finish();
    }
}