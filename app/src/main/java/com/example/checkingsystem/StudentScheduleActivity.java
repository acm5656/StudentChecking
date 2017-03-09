package com.example.checkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 那年.盛夏 on 2017/3/7.
 */

public class StudentScheduleActivity extends AppCompatActivity implements ChooseWeekFragment.CallBackValue {

    private Button scheduleStudy;
    private Button scheduleQuery;
    private Button scheduleMine;
    private TextView scheduleStudyChooseWeek;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_schedule);
        Intent intent = getIntent();


        initUI();
        scheduleQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentScheduleActivity.this,StudentInquireActivity.class);
                startActivity(intent);
            }
        });

        scheduleStudyChooseWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ChooseWeekFragment dialog = new ChooseWeekFragment();

                dialog.show(fragmentManager,"dialog");
            }
        });



    }

    private void initUI() {
        scheduleStudy = (Button)findViewById(R.id.schedule_study);
        scheduleQuery = (Button)findViewById(R.id.schedule_query);
        scheduleMine = (Button)findViewById(R.id.schedule_mine);
        scheduleStudyChooseWeek = (TextView) findViewById(R.id.schedule_study_choose_week);

    }


    @Override
    public void SendMessageValue(String strValue) {
        Toast.makeText(StudentScheduleActivity.this,strValue,Toast.LENGTH_SHORT).show();
    }
}
