package com.example.checkingsystem.student.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.ChooseWeekFragment;
import com.example.checkingsystem.R;
import com.example.checkingsystem.student.fragment.StudentInquireFragment;
import com.example.checkingsystem.student.fragment.StudentMineFragment;
import com.example.checkingsystem.student.fragment.StudentScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

public class StudentIndexActivity extends AppCompatActivity implements View.OnClickListener,ChooseWeekFragment.CallBackValue {

    private ViewPager viewPager;

    private TextView studentIndexStudyTextView;
    private TextView studentIndexInquireTextView;
    private TextView studentIndexMineTextView;

    private ImageView studentIndexStudyImageView;
    private ImageView studentIndexInquireImageView;
    private ImageView studentIndexMineImageView;

    private LinearLayout studentIndexStudyLayout;
    private LinearLayout studentIndexInquireLayout;
    private LinearLayout studentIndexMineLayout;

    private ImageView studentIndexPullImageView;


    private TextView studentIndexChooseWeek;
    public String week = "1";
    StudentMineFragment studentMineFragment;
    StudentScheduleFragment studentScheduleFragment;
    StudentInquireFragment studentInquireFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_index);
        ActivityColectorUtil.addActivity(this);
        initUI();
        final List<Fragment> list = new ArrayList<>();
        studentInquireFragment = new StudentInquireFragment();
        studentMineFragment = new StudentMineFragment();
        studentScheduleFragment = new StudentScheduleFragment();
        list.add(studentScheduleFragment);
        list.add(studentInquireFragment);
        list.add(studentMineFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
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
        studentIndexChooseWeek.setText("课表");
        studentIndexChooseWeek.setOnClickListener(this);
        viewPager.setCurrentItem(0);
        studentIndexMineLayout.setOnClickListener(this);
        studentIndexInquireLayout.setOnClickListener(this);
        studentIndexStudyLayout.setOnClickListener(this);

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
                            studentIndexMineTextView.setTextAppearance(R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(R.style.OnClickText);
                            studentIndexInquireTextView.setTextAppearance(R.style.unClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }

                        studentIndexMineImageView.setImageResource(R.drawable.un_mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.un_query);
                        studentIndexPullImageView.setImageResource(R.drawable.pull);
                        studentIndexChooseWeek.setText("课表");
                        studentIndexChooseWeek.setOnClickListener(StudentIndexActivity.this);

                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMineTextView.setTextAppearance(R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(R.style.OnClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                        }


                        studentIndexMineImageView.setImageResource(R.drawable.un_mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.query);
                        studentIndexPullImageView.setImageBitmap(null);

                        studentIndexChooseWeek.setText("查询");
                        studentIndexChooseWeek.setOnClickListener(null);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMineTextView.setTextAppearance(R.style.OnClickText);
                            studentIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(R.style.unClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }

                        studentIndexMineImageView.setImageResource(R.drawable.mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.un_query);
                        studentIndexPullImageView.setImageBitmap(null);

                        studentIndexChooseWeek.setText("我");
                        studentIndexChooseWeek.setOnClickListener(null);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void initUI()
    {
        viewPager = (ViewPager)findViewById(R.id.student_index_viewpager);
        studentIndexStudyLayout = (LinearLayout) findViewById(R.id.student_index_study);
        studentIndexMineLayout = (LinearLayout)findViewById(R.id.student_index_mine);
        studentIndexInquireLayout = (LinearLayout)findViewById(R.id.student_index_inquire);

        studentIndexStudyTextView = (TextView)findViewById(R.id.student_index_study_text_view);
        studentIndexMineTextView = (TextView)findViewById(R.id.student_index_mine_text_view);
        studentIndexInquireTextView = (TextView)findViewById(R.id.student_index_inquire_text_view);

        studentIndexInquireImageView = (ImageView)findViewById(R.id.student_index_inquire_image_view);
        studentIndexMineImageView = (ImageView)findViewById(R.id.student_index_mine_image_view);
        studentIndexStudyImageView = (ImageView)findViewById(R.id.student_index_study_image_view);

        studentIndexPullImageView = (ImageView)findViewById(R.id.activity_student_index_pull);

        studentIndexChooseWeek = (TextView)findViewById(R.id.student_index_choose_week);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.student_index_choose_week:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ChooseWeekFragment dialog = new ChooseWeekFragment();

                dialog.show(fragmentManager,"dialog");
                break;
            case  R.id.student_index_mine:
                viewPager.setCurrentItem(2);

                break;
            case R.id.student_index_inquire:
                viewPager.setCurrentItem(1);

                break;
            case R.id.student_index_study:
                viewPager.setCurrentItem(0);

                break;
        }
    }

    @Override
    public void SendMessageValue(String strValue) {
        week = strValue;
        if("0".equals(strValue))
            studentIndexChooseWeek.setText("放假中");
        else {
            studentIndexChooseWeek.setText("第" + strValue + "周");
        }
        studentScheduleFragment.updateUI(new Integer(strValue));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.finishAll();
    }


}
