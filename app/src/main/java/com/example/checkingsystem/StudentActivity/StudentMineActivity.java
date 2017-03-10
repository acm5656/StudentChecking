package com.example.checkingsystem.StudentActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.checkingsystem.R;

public class StudentMineActivity extends AppCompatActivity {

    private Button mineStudy;
    private Button mineInquire;
    private Button miniMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mine);
        initUI();
        mineStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentMineActivity.this,StudentScheduleActivity.class);
                startActivity(intent);
            }
        });

        mineInquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentMineActivity.this,StudentInquireActivity.class);
                startActivity(intent);
            }
        });




    }

    void initUI()
    {
        mineStudy = (Button)findViewById(R.id.mine_study);
        mineInquire = (Button)findViewById(R.id.mine_query);
        miniMine = (Button)findViewById(R.id.mine_mine);
    }

}
