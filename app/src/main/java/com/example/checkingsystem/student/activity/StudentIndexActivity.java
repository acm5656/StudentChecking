package com.example.checkingsystem.student.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.ChooseWeekFragment;
import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.VirtualCourse;
import com.example.checkingsystem.net.StudentAddCourseNet;
import com.example.checkingsystem.student.fragment.StudentAskForLeaveFragment;
import com.example.checkingsystem.student.fragment.StudentCourseIndexFragment;
import com.example.checkingsystem.student.fragment.StudentInquireFragment;
import com.example.checkingsystem.student.fragment.StudentMineFragment;
import com.example.checkingsystem.student.fragment.StudentScheduleFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

import static com.tencent.cos.COSClient.getContext;

public class StudentIndexActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;

    private TextView studentIndexStudyTextView;
    private TextView studentIndexInquireTextView;
    private TextView studentIndexMineTextView;
    private TextView studentIndexAskForLeaveTextView;

    private ImageView studentIndexStudyImageView;
    private ImageView studentIndexInquireImageView;
    private ImageView studentIndexMineImageView;
    private ImageView studentIndexAskForLeaveImageView;

    private LinearLayout studentIndexStudyLayout;
    private LinearLayout studentIndexInquireLayout;
    private LinearLayout studentIndexMineLayout;
    private LinearLayout studentIndexAskForLeaveLayout;
    private ImageView studentAddCourseimage;

    private TextView studentIndexChooseWeek;
    public String week = "1";
    public StudentMineFragment studentMineFragment;
    public StudentCourseIndexFragment studentCourseIndexFragment;
    public StudentInquireFragment studentInquireFragment;
    public StudentAskForLeaveFragment studentAskForLeaveFragment;
    EditText courseCodeEditText;
    String courseCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_index);
        ActivityColectorUtil.addActivity(this);
        initUI();
        final List<Fragment> list = new ArrayList<>();
        studentInquireFragment = new StudentInquireFragment();
        studentMineFragment = new StudentMineFragment();
        studentCourseIndexFragment = new StudentCourseIndexFragment();
        studentAskForLeaveFragment = new StudentAskForLeaveFragment();
        list.add(studentCourseIndexFragment);
        list.add(studentAskForLeaveFragment);
        list.add(studentInquireFragment);
        list.add(studentMineFragment);
        studentAddCourseimage.setOnClickListener(this);
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
                            studentIndexAskForLeaveTextView.setTextAppearance(R.style.unClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }

                        studentIndexMineImageView.setImageResource(R.drawable.un_mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.un_query);
                        studentIndexAskForLeaveImageView.setImageResource(R.drawable.un_ask_for_leave);
                        studentIndexChooseWeek.setText("课程");
                        studentIndexChooseWeek.setOnClickListener(StudentIndexActivity.this);
                        studentAddCourseimage.setImageResource(R.drawable.add);

                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMineTextView.setTextAppearance(R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(R.style.unClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(R.style.OnClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                        }

                        studentAddCourseimage.setImageBitmap(null);
                        studentIndexMineImageView.setImageResource(R.drawable.un_mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.un_query);
                        studentIndexAskForLeaveImageView.setImageResource(R.drawable.ask_for_leave);
                        studentIndexChooseWeek.setText("请假");
                        studentIndexChooseWeek.setOnClickListener(null);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMineTextView.setTextAppearance(R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(R.style.OnClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(R.style.unClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }

                        studentAddCourseimage.setImageBitmap(null);
                        studentIndexMineImageView.setImageResource(R.drawable.un_mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.query);
                        studentIndexAskForLeaveImageView.setImageResource(R.drawable.un_ask_for_leave);
                        studentIndexChooseWeek.setText("查询");
                        studentIndexChooseWeek.setOnClickListener(null);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            studentIndexMineTextView.setTextAppearance(R.style.OnClickText);
                            studentIndexStudyTextView.setTextAppearance(R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(R.style.unClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(R.style.unClickText);
                        }else {
                            studentIndexMineTextView.setTextAppearance(getApplicationContext(),R.style.OnClickText);
                            studentIndexStudyTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexInquireTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                            studentIndexAskForLeaveTextView.setTextAppearance(getApplicationContext(),R.style.unClickText);
                        }
                        studentAddCourseimage.setImageBitmap(null);
                        studentIndexMineImageView.setImageResource(R.drawable.mine);
                        studentIndexStudyImageView.setImageResource(R.drawable.un_schedule);
                        studentIndexInquireImageView.setImageResource(R.drawable.un_query);
                        studentIndexAskForLeaveImageView.setImageResource(R.drawable.un_ask_for_leave);
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
        studentIndexAskForLeaveLayout = (LinearLayout)findViewById(R.id.student_index_ask_for_leave);

        studentIndexStudyTextView = (TextView)findViewById(R.id.student_index_study_text_view);
        studentIndexMineTextView = (TextView)findViewById(R.id.student_index_mine_text_view);
        studentIndexInquireTextView = (TextView)findViewById(R.id.student_index_inquire_text_view);
        studentIndexAskForLeaveTextView = (TextView)findViewById(R.id.student_index_ask_for_leave_text_view);

        studentIndexInquireImageView = (ImageView)findViewById(R.id.student_index_inquire_image_view);
        studentIndexMineImageView = (ImageView)findViewById(R.id.student_index_mine_image_view);
        studentIndexStudyImageView = (ImageView)findViewById(R.id.student_index_study_image_view);
        studentIndexAskForLeaveImageView = (ImageView)findViewById(R.id.student_index_ask_for_leave_img);

        studentIndexChooseWeek = (TextView)findViewById(R.id.student_index_choose_week);
        studentAddCourseimage = (ImageView)findViewById(R.id.acivity_student_index_add);
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
                viewPager.setCurrentItem(3);

                break;
            case R.id.student_index_inquire:
                viewPager.setCurrentItem(2);

                break;
            case R.id.student_index_study:
                viewPager.setCurrentItem(0);

                break;
            case R.id.student_index_ask_for_leave:
                viewPager.setCurrentItem(1);
                break;
            case R.id.acivity_student_index_add:
                showInputDialog();

                break;
        }
    }

//    @Override
//    public void SendMessageValue(String strValue) {
//        week = strValue;
//        if("0".equals(strValue))
//            studentIndexChooseWeek.setText("放假中");
//        else {
//            studentIndexChooseWeek.setText("第" + strValue + "周");
//        }
//        studentScheduleFragment.updateUI(new Integer(strValue));
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.finishAll();
    }
    DialogInterface.OnClickListener getOnclickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            courseCode = courseCodeEditText.getText().toString();
            StudentAddCourseNet studentAddCourseNet = new StudentAddCourseNet();
            studentAddCourseNet.studentAddCourse(StudentIndexActivity.this,courseCode, LoginActivity.studentStatic.getStudentId());

        }
    };
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        courseCodeEditText = new EditText(this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setTitle("请输入课程编号").setView(courseCodeEditText);
        inputDialog.setPositiveButton("确定",
                getOnclickListener).show();
    }




}
