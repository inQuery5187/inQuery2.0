package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class s_signup extends AppCompatActivity {
    EditText name, userID, userPwd, cpwd;
    Button submit;
    DatabaseReference reference;
    Data Data;
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
        name= findViewById(R.id.userName);
        userID= findViewById(R.id.userId);
        userPwd= findViewById(R.id.userPwd);
        cpwd= findViewById(R.id.cpwd);
        submit= findViewById(R.id.signUP);
        Data=new Data();
        reference= FirebaseDatabase.getInstance().getReference().child("Data").child("Student");
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
                                Data.setName(nam);
                                Data.setUsername(usernam);
                                Data.setPwd(p);
                                reference.child(userID.getText().toString().trim()).setValue(Data);
                                Toast.makeText(s_signup.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(s_signup.this, s_login.class);
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
    }
}