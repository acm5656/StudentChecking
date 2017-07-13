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
import android.widget.Toast;

import com.example.checkingsystem.ChooseWeekFragment;
import com.example.checkingsystem.R;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.fragment.TeacherCourseIndexFragment;
import com.example.checkingsystem.teacher.fragment.TeacherInquireFragment;
import com.example.checkingsystem.teacher.fragment.TeacherMineFragment;
import com.example.checkingsystem.teacher.fragment.TeacherScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

/**
 * Created by 那年.盛夏 on 2017/3/11.
 */



public class TeacherIndexActivity extends AppCompatActivity implements View.OnClickListener{
    final int TEACHER_ADD_COURSE = 3;
    private ViewPager viewPager;

    private TextView teacherIndexStudyTextView;
    private TextView teacherIndexInquireTextView;
    private TextView teacherIndexMineTextView;

    private LinearLayout teacherIndexStudyLayout;
    private LinearLayout teacherIndexInquireLayout;
    private LinearLayout teacherIndexMineLayout;

    private ImageView teacherIndexStudyImageView;
    private ImageView teacherIndexInquireImageView;
    private ImageView teacherIndexMineImageView;
    private ImageView teacherIndexAddCoourse;




    private TextView teacherIndexChooseWeek;
    private List<Fragment> list;
    TeacherCourseIndexFragment teacherCourseIndexFragment;
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
        teacherCourseIndexFragment = new TeacherCourseIndexFragment();
        list.add(teacherCourseIndexFragment);
        list.add(teacherInquireFragment);
        list.add(teacherMineFragment);

        teacherIndexChooseWeek.setOnClickListener(this);
        teacherIndexAddCoourse.setOnClickListener(TeacherIndexActivity.this);
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
                            teacherIndexMineTextView.setTextAppearance(R.style.unClickText);
                            teacherIndexStudyTextView.setTextAppearance(R.style.OnClickText);
                            teacherIndexInquireTextView.setTextAppearance(R.style.unClickText);

                        }else {
                            teacherIndexMineTextView.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            teacherIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                            teacherIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }
                        teacherIndexChooseWeek.setText("课程");
                        teacherIndexAddCoourse.setImageResource(R.drawable.add);
                        teacherIndexAddCoourse.setOnClickListener(TeacherIndexActivity.this);

                        teacherIndexMineImageView.setImageResource(R.drawable.un_mine);
                        teacherIndexStudyImageView.setImageResource(R.drawable.schedule);
                        teacherIndexInquireImageView.setImageResource(R.drawable.un_query);

                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherIndexMineTextView.setTextAppearance(R.style.unClickText);
                            teacherIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            teacherIndexInquireTextView.setTextAppearance(R.style.OnClickText);

                        }else {
                            teacherIndexMineTextView.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            teacherIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            teacherIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                        }

                        teacherIndexChooseWeek.setText("查询");
                        teacherIndexChooseWeek.setOnClickListener(null);
                        teacherIndexAddCoourse.setImageBitmap(null);
                        teacherIndexAddCoourse.setOnClickListener(null);

                        teacherIndexMineImageView.setImageResource(R.drawable.un_mine);
                        teacherIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        teacherIndexInquireImageView.setImageResource(R.drawable.query);

                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            teacherIndexMineTextView.setTextAppearance(R.style.OnClickText);
                            teacherIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            teacherIndexInquireTextView.setTextAppearance(R.style.unClickText);

                        }
                        else {
                            teacherIndexMineTextView.setTextAppearance(getApplicationContext(), R.style.OnClickText);
                            teacherIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            teacherIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }
                        teacherIndexChooseWeek.setText("我");
                        teacherIndexChooseWeek.setOnClickListener(null);
                        teacherIndexAddCoourse.setImageBitmap(null);
                        teacherIndexAddCoourse.setOnClickListener(null);
                        teacherIndexMineImageView.setImageResource(R.drawable.mine);
                        teacherIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        teacherIndexInquireImageView.setImageResource(R.drawable.un_query);

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
        teacherIndexStudyLayout = (LinearLayout) findViewById(R.id.teacher_index_study);
        teacherIndexInquireLayout = (LinearLayout) findViewById(R.id.teacher_index_inquire);
        teacherIndexMineLayout = (LinearLayout) findViewById(R.id.teacher_index_mine);

        teacherIndexStudyImageView = (ImageView) findViewById(R.id.teacher_index_study_image_view);
        teacherIndexInquireImageView = (ImageView) findViewById(R.id.teacher_index_inquire_image_view);
        teacherIndexMineImageView = (ImageView) findViewById(R.id.teacher_index_mine_image_view);

        teacherIndexStudyTextView = (TextView)findViewById(R.id.teacher_index_study_text_view);
        teacherIndexInquireTextView = (TextView)findViewById(R.id.teacher_index_inquire_text_view);
        teacherIndexMineTextView = (TextView)findViewById(R.id.teacher_index_mine_text_view);


        teacherIndexChooseWeek = (TextView)findViewById(R.id.teacher_index_choose_week);
        teacherIndexMineLayout.setOnClickListener(this);
        teacherIndexInquireLayout.setOnClickListener(this);
        teacherIndexStudyLayout.setOnClickListener(this);
        teacherIndexAddCoourse = (ImageView)findViewById(R.id.activity_teacher_index_add_course);
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
            case R.id.activity_teacher_index_add_course:
                Intent intent = new Intent(TeacherIndexActivity.this,TeacherAddCourseActivity.class);
                startActivityForResult(intent,TEACHER_ADD_COURSE);
                break;
        }
    }
    //原显示周数的函数
//    @Override
//    public void SendMessageValue(String strValue) {
//        weekTime = strValue;
//        if("0".equals(strValue))
//            teacherIndexChooseWeek.setText("放假中");
//        else {
//            teacherIndexChooseWeek.setText("第" + strValue + "周");
//        }
//        teacherScheduleFragment.updateUI(new Integer(weekTime));
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.finishAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("test","-------------------ac1");
        Log.e("test",requestCode+"");
        if(requestCode==TeacherCourseIndexFragment.TEACHER_ADD_COURSE) {
            Log.e("test","-------------------ac3");
            teacherCourseIndexFragment.onActivityResult(requestCode, resultCode, data);
        }
        if(requestCode==TeacherMineFragment.CHANGE_PASSWORD_CODE||requestCode==TeacherMineFragment.CHANGE_INFO_REQUEST_CODE) {
            Log.e("test","-------------------ac2");
            teacherMineFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
