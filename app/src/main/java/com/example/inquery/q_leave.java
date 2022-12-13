package com.example.inquery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class q_leave extends AppCompatActivity {
    TextView teachersel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qleave);
        teachersel= findViewById(R.id.spinner);
        String[] listItems={"life","was","a","willow"};
        boolean[] checkedItems;
        checkedItems= new boolean[listItems.length];
        ArrayList<Integer> mUserItems= new ArrayList<Integer>();
        teachersel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(q_leave.this);
                mbuilder.setTitle("select the receiver");



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


                        teachersel.setText(item);
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
}