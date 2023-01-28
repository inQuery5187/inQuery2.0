package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class qleavesingle extends AppCompatActivity {
    TextView teachersel;
    DatabaseReference reference;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems= new ArrayList<Integer>();
    ArrayList<String> nameArr= new ArrayList<String>();
    ArrayList<String> userArr= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qleavesingle);
        teachersel= findViewById(R.id.spinner);
        reference= FirebaseDatabase.getInstance().getReference("Data");
        reference.child("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    nameArr.add(String.valueOf(dataSnapshot.child("name").getValue()));
                    userArr.add(String.valueOf(dataSnapshot.child("username").getValue()));
                }
                listItems=getValue();
                checkedItems= new boolean[listItems.length];
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        teachersel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(qleavesingle.this);
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
                        teachersel.setText("Your preferred subcategories are...\n");

                        //for loop
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item+ listItems[mUserItems.get(i)];

                            if (i!=mUserItems.size() -1) {
                                //add comma
                                item = item+ ", ";
                            }
                        }
                        teachersel.setText("Choose Reciever: "+item);
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
                            teachersel.setText(" ");
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