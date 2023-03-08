package com.example.inquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class qleavemulti extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView teachersel, fromDate, toDate;
    DatabaseReference reference;
    ImageView btFromDate, btToDate;
    String[] listItems;
    boolean[] checkedItems;
    int count;
    ArrayList<Integer> mUserItems= new ArrayList<Integer>();
    ArrayList<String> nameArr= new ArrayList<String>();
    ArrayList<String> userArr= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qleavemulti);
        teachersel = findViewById(R.id.spinnerReciever);
        btFromDate = findViewById(R.id.btFromDate);
        btToDate = findViewById(R.id.btToDate);
        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);

        btFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.inquery.DatePicker mDatePickerDialogFragmentFrom;
                mDatePickerDialogFragmentFrom = new com.example.inquery.DatePicker();
                count= 1;
                mDatePickerDialogFragmentFrom.show(getSupportFragmentManager(), "DATE PICK");


            }
        });
        btToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.inquery.DatePicker mDatePickerDialogFragmentTo;
                mDatePickerDialogFragmentTo = new com.example.inquery.DatePicker();
                count= 2;
                mDatePickerDialogFragmentTo.show(getSupportFragmentManager(), "DATE PICK");

            }
        });
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
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(qleavemulti.this);
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        if(count==1){
            fromDate.setText(selectedDate);
        }
        if(count==2){
            toDate.setText(selectedDate);
        }

    }
}