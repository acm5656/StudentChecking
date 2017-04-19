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

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

public class StudentCheckingActivity extends FragmentActivity implements View.OnClickListener {

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
        StudentAskForLeaveFragment studentAskForLeaveFragment = new StudentAskForLeaveFragment();
        StudentCheckingFragment studentCheckingFragment = new StudentCheckingFragment();
        fragmentManager = getSupportFragmentManager();
        list = new ArrayList<Fragment>();
        list.add(studentCheckingFragment);
        list.add(studentAskForLeaveFragment);
        Intent intent = getIntent();
        courseID = intent.getStringExtra("courseID");
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        checkingTextView.setTextAppearance(R.style.OnClickText);
                        askForLeaveTextView.setTextAppearance(R.style.unClickText);
                    }
                    else {
                        checkingTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                        askForLeaveTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                    }
                    checkingImageView.setImageResource(R.drawable.checking);
                    askForLeaveImageView.setImageResource(R.drawable.un_ask_for_leave);
                }
                if(position==1)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        checkingTextView.setTextAppearance(R.style.unClickText);
                        askForLeaveTextView.setTextAppearance(R.style.OnClickText);

                    }else {
                        checkingTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        askForLeaveTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                    }
                    checkingImageView.setImageResource(R.drawable.un_checking);
                    askForLeaveImageView.setImageResource(R.drawable.ask_for_leave);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);

    }

    private void initUI() {
        viewPager = (ViewPager)findViewById(R.id.checking_fragment_container);
        askForLeaveTextView = (TextView)findViewById(R.id.activity_student_checking_ask_for_leave_page_text_view);
        checkingTextView = (TextView)findViewById(R.id.activity_student_checking_page_text_view);

        askForLeaveLayout = (LinearLayout)findViewById(R.id.activity_student_checking_ask_for_leave_page);
        checkingLayout = (LinearLayout)findViewById(R.id.activity_student_checking_page);

        askForLeaveImageView = (ImageView) findViewById(R.id.activity_student_checking_ask_for_leave_page_image_view);
        checkingImageView = (ImageView) findViewById(R.id.activity_student_checking_page_image_view);

        askForLeaveLayout.setOnClickListener(this);
        checkingLayout.setOnClickListener(this);
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
            case R.id.activity_student_checking_page:
                viewPager.setCurrentItem(0);
                break;
            case R.id.activity_student_checking_ask_for_leave_page:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
