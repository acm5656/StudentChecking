package com.example.checkingsystem.teacher.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.teacher.fragment.TeacherAskForLeaveInfoFragment;
import com.example.checkingsystem.teacher.fragment.TeacherCheckingFragment;
import com.example.checkingsystem.teacher.fragment.TeacherScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

/**
 * Created by 那年.盛夏 on 2017/3/11.
 */

public class TeacherCheckingActivity extends AppCompatActivity{
    private TextView teacherAskForLeaveInfoTextView;
    private TextView teacherCheckingTextView;

    private LinearLayout teacherAskForLeaveInfoLayout;
    private LinearLayout teacherCheckingLayout;

    private ImageView teacherAskForLeaveInfoImageView;
    private ImageView teacherCheckingImageView;

    private ViewPager teacherCheckingFragmentContainer;
    private List<Fragment> list;
    TeacherCheckingFragment teacherCheckingFragment;
    public String courseID;
    public CourseShow courseShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_checking);
        ActivityColectorUtil.addActivity(this);
        initUI();
        list = new ArrayList<>();
        teacherCheckingFragment = new TeacherCheckingFragment();
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
    }

    private void initUI() {
        teacherCheckingFragmentContainer = (ViewPager)findViewById(R.id.teacher_checking_fragment_container);
        Intent intent = getIntent();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityColectorUtil.removeActivity(this);
    }

}
