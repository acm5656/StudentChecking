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
import com.example.checkingsystem.entity.Student;
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
    //view声明，分别是主页的底部框的textview，imageview，layout
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
    //旧版本的选择周的功能，已经舍弃
    private TextView studentIndexChooseWeek;
    public String week = "1";
    //学生功能主页面的四个fragment，每一个fragment代表一个滑动的页面
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
        //初始化fragment
        studentInquireFragment = new StudentInquireFragment();
        studentMineFragment = new StudentMineFragment();
        studentCourseIndexFragment = new StudentCourseIndexFragment();
        studentAskForLeaveFragment = new StudentAskForLeaveFragment();
        //向集合中添加
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
        //以舍弃，老版本的选课
        studentIndexChooseWeek.setText("课表");
        studentIndexChooseWeek.setOnClickListener(this);

        //设置当前显示的页面
        viewPager.setCurrentItem(0);
        studentIndexMineLayout.setOnClickListener(this);
        studentIndexInquireLayout.setOnClickListener(this);
        studentIndexStudyLayout.setOnClickListener(this);
        studentIndexAskForLeaveLayout.setOnClickListener(this);
        //页面变化时执行的事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    //主要是修改底部框的图标，文字的颜色和上方的点击事件和图片
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
    //初始化资源文件
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
        //当学生初次注册时，没有通过认证后，登录进来显示的提示信息
        if(LoginActivity.studentStatic.getStudentStatus().equals(Student.STATUS_AUTH_FAIL))
        {
            Toast.makeText(getApplicationContext(),"申请被拒绝，请再次进行人脸识别，再次申请加入班级",Toast.LENGTH_LONG).show();
        }
        if(LoginActivity.studentStatic.getStudentStatus().equals(Student.STATUS_WAIT_AUTH))
        {
            Toast.makeText(getApplicationContext(),"由于您当前信息还不完善，请在我的页面下，进行人脸识别，并加入班级，成功加入后，可以进行考勤等一系列操作",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //以舍弃的点击事件
            case R.id.student_index_choose_week:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ChooseWeekFragment dialog = new ChooseWeekFragment();

                dialog.show(fragmentManager,"dialog");
                break;
            //四个页面来回切换的设置当前显示页
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
            //进入班级的点击事件，判断学生的状态，如果状态没通过审核，则会显示提示信息
            case R.id.acivity_student_index_add:
                if(LoginActivity.studentStatic.getStudentStatus().equals(Student.STATUS_AUTH_PASS))
                {
                    showInputDialog();
                }
                else if(LoginActivity.studentStatic.getStudentStatus().equals(Student.STATUS_AUTH_FAIL))
                {
                    Toast.makeText(getApplicationContext(),"申请被拒绝，请再次进行人脸识别，再次申请加入班级",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getApplicationContext(),"还未通过审核，不能够加入班级，请在我的页面下，进行人脸识别，并加入班级，",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    //摁返回键执行的函数
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //回收所有的activity,退出
        ActivityColectorUtil.finishAll();
    }
    //输入班级后执行的事件
    DialogInterface.OnClickListener getOnclickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            courseCode = courseCodeEditText.getText().toString();
            StudentAddCourseNet studentAddCourseNet = new StudentAddCourseNet();
            studentAddCourseNet.studentAddCourse(StudentIndexActivity.this,courseCode, LoginActivity.studentStatic.getStudentId());

        }
    };
    //用来显示加入班级的dialog
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
