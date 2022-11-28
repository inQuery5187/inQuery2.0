package com.example.inquery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class s_query extends AppCompatActivity {
    TextView query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squery);
        query= findViewById(R.id.textView2);
        query.setText("Subject: Application for Casual Leave\n" +
                "\n" +
                "Dear Mr./Mrs. _______________,\n" +
                "\n" +
                "I am writing to you to let you know that I have an important personal matter to attend at my hometown due to which I will not be able to come to the office from 25-11-2022 to ______.\n" +
                "\n" +
                "I have discussed and delegated my tasks to ________ and have instructed them to call me for any help during my absence.\n" +
                "\n" +
                "I will be obliged if you consider my application for approval.\n" +
                "\n" +
                "\n" +
                "Yours Sincerely,\n" +
                "\n" +
                "\n" +
                " {Your Name}");


    }
}