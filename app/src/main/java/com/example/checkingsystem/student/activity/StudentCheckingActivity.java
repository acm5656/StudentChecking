package com.example.checkingsystem.student.activity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.student.fragment.StudentAskForLeaveFragment;
import com.example.checkingsystem.student.fragment.StudentCheckingFragment;
import com.example.checkingsystem.student.fragment.StudentCourseIndexFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

public class StudentCheckingActivity extends FragmentActivity {
    private ImageView iv_back;
    private ViewPager viewPager;

    private TextView askForLeaveTextView;
    private TextView checkingTextView;

    private LinearLayout askForLeaveLayout;
    private LinearLayout checkingLayout;

    private ImageView askForLeaveImageView;
    private ImageView checkingImageView;

    private FragmentManager fragmentManager;
    private List<Fragment> list;
    public String courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_checking);
        ActivityColectorUtil.addActivity(this);
        initUI();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        StudentCheckingFragment studentCheckingFragment = new StudentCheckingFragment();
        fragmentManager = getSupportFragmentManager();
        list = new ArrayList<Fragment>();
        list.add(studentCheckingFragment);
        Intent intent = getIntent();
        courseID = StudentCourseIndexFragment.courseShow.getDbID();
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {

                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setCurrentItem(0);

    }

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_activity_student_checking_back);
        viewPager = (ViewPager)findViewById(R.id.checking_fragment_container);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityColectorUtil.removeActivity(this);
    }


}
