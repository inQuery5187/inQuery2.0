package com.example.inquery;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class qcomplaint extends AppCompatActivity {
    ImageView submit;
    TextView dialog;
    EditText against, reason;
    DatabaseReference db, reference;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems= new ArrayList<Integer>();
    ArrayList<String> nameArr= new ArrayList<String>();
    ArrayList<String> userArr= new ArrayList<String>();
    ArrayList<String> toAdd= new ArrayList<String>();
    String valAgainst, valReason, ID;
    int no;
    private static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcomplaint);
        submit= findViewById(R.id.csubmit);
        dialog= findViewById(R.id.receiver);
        against= findViewById(R.id.rfrom);
        reason= findViewById(R.id.rreason);
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        ID= sharedPreferences.getString("sID", "");
        db= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(toAdd!=null){
                    Set<String> adding= new HashSet<String>(toAdd);
                    toAdd= new ArrayList<>(adding);
                    submit.setImageResource(R.drawable.button_medium_dark);
                    valAgainst= against.getText().toString().trim();
                    valReason= reason.getText().toString().trim();
                    reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(ID).child("requestHistory");
                    String uid= reference.push().getKey();
                    for(String str: toAdd){
                        db.child(str).child("complaints").child(ID).child("against").setValue(valAgainst);
                        db.child(str).child("complaints").child(ID).child("from").setValue(ID);
                        db.child(str).child("complaints").child(ID).child("reason").setValue(valReason);
                        db.child(str).child("complaints").child(ID).child("UID").setValue(uid);
                        db.child(str).child("complaints").child(ID).child("status").setValue("Pending c:");
                    }
                    HashMap<String, String> map= new HashMap<>();
                    map.put("type", "complaint");
                    map.put("status", "Pending c:");
                    map.put("reason", valReason);
                    reference.child(uid).setValue(map);
                    Toast.makeText(qcomplaint.this, "Data submitted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(qcomplaint.this, "Please select the faculty", Toast.LENGTH_SHORT).show();
                }
                }

        });
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    nameArr.add(valueOf(dataSnapshot.child("name").getValue()));
                    userArr.add(valueOf(dataSnapshot.child("username").getValue()));
                }
                listItems=getValue();
                checkedItems= new boolean[listItems.length];
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(qcomplaint.this);
                mbuilder.setTitle("select the receiver");
                String[] x= new String[nameArr.size()];
                mbuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean Checked) {
                        //check condition
                        if(Checked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });
                mbuilder.setCancelable(false);
                mbuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item ="";
                        dialog.setText("Your preferred subcategories are...\n");

                        //for loop
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item+ listItems[mUserItems.get(i)];
                            toAdd.add(userArr.get(mUserItems.get(i)));
                            if (i!=mUserItems.size() -1) {
                                //add comma
                                item = item+ ", ";
                            }
                        }
                        dialog.setText("Choose Reciever: "+item);
                    }
                });
                mbuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mbuilder.setNeutralButton("clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < checkedItems.length; j++) {
                            checkedItems[j] = false;
                            //clear list
                            mUserItems.clear();
                            dialog.setText(" ");
                        }
                    }
                });
                AlertDialog mdialog = mbuilder.create();
                mdialog.show();
            }
        });

    }
    private String[] getValue(){
        ArrayList<String> arr3= new ArrayList<>();
        for(int i =0; i<nameArr.size(); i++) {
            arr3.add(nameArr.get(i)+"("+userArr.get(i)+")");
            Log.d("VALUEEEE", arr3.get(i));
        }
        String[] x= {};
        return arr3.toArray(x);
    }
}