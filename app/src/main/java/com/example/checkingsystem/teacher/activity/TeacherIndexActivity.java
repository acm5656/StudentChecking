package com.example.checkingsystem.teacher.activity;

import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.ChooseWeekFragment;
import com.example.checkingsystem.R;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.fragment.TeacherInquireFragment;
import com.example.checkingsystem.teacher.fragment.TeacherMineFragment;
import com.example.checkingsystem.teacher.fragment.TeacherScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

/**
 * Created by 那年.盛夏 on 2017/3/11.
 */



public class TeacherIndexActivity extends AppCompatActivity implements View.OnClickListener,ChooseWeekFragment.CallBackValue{
    private ViewPager viewPager;
    private TextView teacherIndexStudy;
    private TextView teacherIndexInquire;
    private TextView teacherIndexMine;
    private TextView teacherIndexChooseWeek;
    private List<Fragment> list;
    TeacherScheduleFragment teacherScheduleFragment;
    TeacherMineFragment teacherMineFragment;
    TeacherInquireFragment teacherInquireFragment;
    public String weekTime = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_index);
        ActivityColectorUtil.addActivity(this);
        list = new ArrayList<>();
        initUI();
        FragmentManager fragmentManager = getSupportFragmentManager();
        teacherInquireFragment = new TeacherInquireFragment();
        teacherMineFragment = new TeacherMineFragment();
        teacherScheduleFragment = new TeacherScheduleFragment();
        list.add(teacherScheduleFragment);
        list.add(teacherInquireFragment);
        list.add(teacherMineFragment);

        teacherIndexChooseWeek.setOnClickListener(this);
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        viewPager.setCurrentItem(0);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherIndexMine.setTextAppearance(R.style.unClickText);
                            teacherIndexStudy.setTextAppearance(R.style.OnClickText);
                            teacherIndexInquire.setTextAppearance(R.style.unClickText);
                            teacherIndexChooseWeek.setText("课表");
                            teacherIndexChooseWeek.setOnClickListener(TeacherIndexActivity.this);
                        }
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherIndexMine.setTextAppearance(R.style.unClickText);
                            teacherIndexStudy.setTextAppearance(R.style.unClickText);
                            teacherIndexInquire.setTextAppearance(R.style.OnClickText);
                            teacherIndexChooseWeek.setText("查询");
                            teacherIndexChooseWeek.setOnClickListener(null);
                        }
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherIndexMine.setTextAppearance(R.style.OnClickText);
                            teacherIndexStudy.setTextAppearance(R.style.unClickText);
                            teacherIndexInquire.setTextAppearance(R.style.unClickText);
                            teacherIndexChooseWeek.setText("我");
                            teacherIndexChooseWeek.setOnClickListener(null);
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    private void initUI() {
        viewPager = (ViewPager)findViewById(R.id.teacher_index_viewpager);
        teacherIndexStudy = (TextView)findViewById(R.id.teacher_index_study);
        teacherIndexInquire = (TextView)findViewById(R.id.teacher_index_inquire);
        teacherIndexMine = (TextView)findViewById(R.id.teacher_index_mine);
        teacherIndexChooseWeek = (TextView)findViewById(R.id.teacher_index_choose_week);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.teacher_index_inquire:
                viewPager.setCurrentItem(1);
                break;
            case R.id.teacher_index_study:
                viewPager.setCurrentItem(0);
                break;
            case R.id.teacher_index_mine:
                viewPager.setCurrentItem(2);
                break;
            case R.id.teacher_index_choose_week:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ChooseWeekFragment dialog = new ChooseWeekFragment();

                dialog.show(fragmentManager,"dialog");
                break;

        }
    }
    @Override
    public void SendMessageValue(String strValue) {
        Toast.makeText(TeacherIndexActivity.this,strValue,Toast.LENGTH_SHORT).show();
        weekTime = strValue;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.finishAll();
    }
}
