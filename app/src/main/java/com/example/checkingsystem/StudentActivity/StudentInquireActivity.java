package com.example.checkingsystem.StudentActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.checkingsystem.R;

public class StudentInquireActivity extends AppCompatActivity {

    private Button inquireStudy;
    private Button inquireMine;
    private Button inquireQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_inquire);
        initUI();
        inquireStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentInquireActivity.this,StudentScheduleActivity.class);
                startActivity(intent);
            }
        });

        inquireMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentInquireActivity.this,StudentMineActivity.class);
                startActivity(intent);
            }
        });

    }

    void initUI()
    {
        inquireQuery = (Button)findViewById(R.id.inquire_query);
        inquireStudy = (Button)findViewById(R.id.inquire_study);
        inquireMine = (Button)findViewById(R.id.inquire_mine);
    }
}
