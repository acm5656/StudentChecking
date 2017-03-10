package com.example.checkingsystem.student.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.ChooseWeekFragment;
import com.example.checkingsystem.R;
import com.example.checkingsystem.student.fragment.StudentInquireFragment;
import com.example.checkingsystem.student.fragment.StudentMineFragment;
import com.example.checkingsystem.student.fragment.StudentScheduleFragment;

import java.util.ArrayList;
import java.util.List;

public class StudentIndexActivity extends AppCompatActivity implements View.OnClickListener,ChooseWeekFragment.CallBackValue {

    private ViewPager viewPager;
    private TextView studentIndexStudy;
    private TextView studentIndexInquire;
    private TextView studentIndexMine;
    private TextView studentIndexChooseWeek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_index);
        initUI();
        final List<Fragment> list = new ArrayList<>();
        StudentInquireFragment studentInquireFragment = new StudentInquireFragment();
        StudentMineFragment studentMineFragment = new StudentMineFragment();
        StudentScheduleFragment studentScheduleFragment = new StudentScheduleFragment();
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

        viewPager.setCurrentItem(0);
        studentIndexMine.setOnClickListener(this);
        studentIndexInquire.setOnClickListener(this);
        studentIndexStudy.setOnClickListener(this);

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
                            studentIndexMine.setTextAppearance(R.style.unClickText);
                            studentIndexStudy.setTextAppearance(R.style.OnClickText);
                            studentIndexInquire.setTextAppearance(R.style.unClickText);
                            studentIndexChooseWeek.setText("课表");
                            studentIndexChooseWeek.setOnClickListener(StudentIndexActivity.this);
                        }
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMine.setTextAppearance(R.style.unClickText);
                            studentIndexStudy.setTextAppearance(R.style.unClickText);
                            studentIndexInquire.setTextAppearance(R.style.OnClickText);
                            studentIndexChooseWeek.setText("查询");
                            studentIndexChooseWeek.setOnClickListener(null);
                        }
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMine.setTextAppearance(R.style.OnClickText);
                            studentIndexStudy.setTextAppearance(R.style.unClickText);
                            studentIndexInquire.setTextAppearance(R.style.unClickText);
                            studentIndexChooseWeek.setText("我");
                            studentIndexChooseWeek.setOnClickListener(null);
                        }
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
        studentIndexStudy = (TextView)findViewById(R.id.student_index_study);
        studentIndexMine = (TextView)findViewById(R.id.student_index_mine);
        studentIndexInquire = (TextView)findViewById(R.id.student_index_inquire);
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
        Toast.makeText(StudentIndexActivity.this,strValue,Toast.LENGTH_SHORT).show();
    }
}
