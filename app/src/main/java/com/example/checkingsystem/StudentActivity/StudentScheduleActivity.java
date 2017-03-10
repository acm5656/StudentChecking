package com.example.checkingsystem.StudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.student.activity.StudentCheckingActivity;
import com.example.checkingsystem.ChooseWeekFragment;
import com.example.checkingsystem.R;

/**
 * Created by 那年.盛夏 on 2017/3/7.
 */

public class StudentScheduleActivity extends AppCompatActivity implements ChooseWeekFragment.CallBackValue,View.OnClickListener {

    private Button scheduleStudy;
    private Button scheduleQuery;
    private Button scheduleMine;
    private TextView scheduleStudyChooseWeek;
    private TextView weekOneClassOne;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_schedule);


        initUI();
        scheduleQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentScheduleActivity.this,StudentInquireActivity.class);
                startActivity(intent);
            }
        });
        scheduleMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentScheduleActivity.this,StudentMineActivity.class);
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

        weekOneClassOne.setOnClickListener(this);



    }

    private void initUI() {
        scheduleStudy = (Button)findViewById(R.id.schedule_study);
        scheduleQuery = (Button)findViewById(R.id.schedule_query);
        scheduleMine = (Button)findViewById(R.id.schedule_mine);
        scheduleStudyChooseWeek = (TextView) findViewById(R.id.schedule_study_choose_week);
        weekOneClassOne = (TextView)findViewById(R.id.WeekOneClassOne);

    }


    @Override
    public void SendMessageValue(String strValue) {
        Toast.makeText(StudentScheduleActivity.this,strValue,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.WeekOneClassOne:
                Intent intent = new Intent(StudentScheduleActivity.this,StudentCheckingActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void startCheckingActivity(int week,int course)
    {
        Intent intent = new Intent();
        
    }

}
