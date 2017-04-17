package com.example.checkingsystem.teacher.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.teacher.fragment.TeacherAskForLeaveInfoFragment;
import com.example.checkingsystem.teacher.fragment.TeacherCheckingFragment;
import com.example.checkingsystem.teacher.fragment.TeacherScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

/**
 * Created by 那年.盛夏 on 2017/3/11.
 */

public class TeacherCheckingActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView teacherAskForLeaveInfo;
    private TextView teacherChecking;
    private ViewPager teacherCheckingFragmentContainer;
    private List<Fragment> list;
    TeacherAskForLeaveInfoFragment teacherAskForLeaveInfoFragment;
    TeacherCheckingFragment teacherCheckingFragment;
    public String courseID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_checking);
        ActivityColectorUtil.addActivity(this);
        initUI();
        list = new ArrayList<>();

        teacherCheckingFragment = new TeacherCheckingFragment();
        teacherAskForLeaveInfoFragment = new TeacherAskForLeaveInfoFragment();
        list.add(teacherAskForLeaveInfoFragment);
        list.add(teacherCheckingFragment);


        FragmentManager fragmentManager = getSupportFragmentManager();

        teacherCheckingFragmentContainer.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        teacherCheckingFragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        teacherCheckingFragmentContainer.setCurrentItem(0);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherAskForLeaveInfo.setTextAppearance(R.style.OnClickText);
                            teacherChecking.setTextAppearance(R.style.unClickText);
                        }
                        break;
                    case 1:
                        teacherCheckingFragmentContainer.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherAskForLeaveInfo.setTextAppearance(R.style.unClickText);
                            teacherChecking.setTextAppearance(R.style.OnClickText);
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        teacherCheckingFragmentContainer.setCurrentItem(0);
    }

    private void initUI() {
        teacherAskForLeaveInfo = (TextView) findViewById(R.id.teacher_ask_for_leave_info);
        teacherChecking = (TextView)findViewById(R.id.teacher_checking);
        teacherCheckingFragmentContainer = (ViewPager)findViewById(R.id.teacher_checking_fragment_container);
        Intent intent = getIntent();
        courseID = intent.getStringExtra("courseID");
        teacherAskForLeaveInfo.setOnClickListener(this);
        teacherChecking.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityColectorUtil.removeActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.teacher_ask_for_leave_info:
                teacherCheckingFragmentContainer.setCurrentItem(0);
                break;
            case R.id.teacher_checking:
                teacherCheckingFragmentContainer.setCurrentItem(1);
                break;
        }
    }
}
