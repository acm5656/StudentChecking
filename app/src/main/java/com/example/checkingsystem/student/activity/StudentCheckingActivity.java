package com.example.checkingsystem.student.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.student.fragment.StudentAskForLeaveFragment;
import com.example.checkingsystem.student.fragment.StudentCheckingFragment;

import java.util.ArrayList;
import java.util.List;

public class StudentCheckingActivity extends FragmentActivity {

    private ViewPager viewPager;
    private TextView askForLeave;
    private TextView checking;
    private FragmentManager fragmentManager;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_checking);
        initUI();
        StudentAskForLeaveFragment studentAskForLeaveFragment = new StudentAskForLeaveFragment();
        StudentCheckingFragment studentCheckingFragment = new StudentCheckingFragment();
        fragmentManager = getSupportFragmentManager();
        list = new ArrayList<Fragment>();
        list.add(studentAskForLeaveFragment);
        list.add(studentCheckingFragment);
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
                if(position==1)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        checking.setTextAppearance(R.style.OnClickText);
                        askForLeave.setTextAppearance(R.style.unClickText);
                    }
                }
                if(position==0)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        checking.setTextAppearance(R.style.unClickText);
                        askForLeave.setTextAppearance(R.style.OnClickText);
                    }

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
        askForLeave = (TextView)findViewById(R.id.ask_for_leave);
        checking = (TextView)findViewById(R.id.checking);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.e("toast","----------"+requestCode+"   "+resultCode+"  "+RESULT_OK);
//
//        switch (requestCode)
//        {
//            case StudentCheckingFragment.FACE_VERIFY_RESULT:
//                if(resultCode == RESULT_OK) {
//                    String dataStr = data.getStringExtra("data_return");
//                    Log.e("toast","---------------2");
//                    Toast.makeText(this,dataStr,Toast.LENGTH_SHORT).show();
//                }
//
//                break;
//        }
//    }
}
