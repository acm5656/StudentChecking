package com.example.checkingsystem.assistant.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.assistant.fragments.AssistantAgreeForClassFragment;
import com.example.checkingsystem.assistant.fragments.AssistantAgreeForLeaveFragment;
import com.example.checkingsystem.assistant.fragments.AssistantInquireFragment;
import com.example.checkingsystem.assistant.fragments.AssistantMineFragment;

import java.util.ArrayList;
import java.util.List;

import util.ActivityColectorUtil;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantIndexActivity extends AppCompatActivity implements View.OnClickListener {
    final int ASSISTANT_ADD_CLASS = 1;
    private ViewPager viewPager;
    private TextView title;

    private LinearLayout layoutAgreeForLeave;
    private LinearLayout layoutJoinClass;
    private LinearLayout layoutQuery;
    private LinearLayout layoutMine;

    private TextView textViewAgreeForLeave;
    private TextView textViewJoinClass;
    private TextView textViewQuery;
    private TextView textViewMine;

    private ImageView imageViewAgreeForLeave;
    private ImageView imageViewJoinClass;
    private ImageView imageViewQuery;
    private ImageView imageViewMine;
    private ImageView imageViewAddClass;

    public Fragment assistantAgreeForClassFragment;
    public Fragment assistantAgreeForLeaveFragment;
    public Fragment assistantInquireFragment;
    public Fragment assistantMineFragment;
    private  final List<Fragment> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_index);
        ActivityColectorUtil.addActivity(this);

        initUI();
        initViewPager();
        initListener();
    }

    private void initUI(){
        viewPager = (ViewPager) findViewById(R.id.vp_activity_assistant_index);
        title = (TextView) findViewById(R.id.tv_activity_assistant_index_title);

        layoutAgreeForLeave = (LinearLayout) findViewById(R.id.activity_assistant_index_agree_leave);
        layoutJoinClass = (LinearLayout) findViewById(R.id.activity_assistant_index_join_class);
        layoutQuery = (LinearLayout) findViewById(R.id.activity_assistant_index_query);
        layoutMine = (LinearLayout) findViewById(R.id.activity_assistant_index_mine);

        textViewAgreeForLeave = (TextView) findViewById(R.id.tv_activity_assistant_index_agree_leave);
        textViewJoinClass = (TextView) findViewById(R.id.tv_activity_assistant_index_join_class);
        textViewQuery = (TextView) findViewById(R.id.tv_activity_assistant_index_query);
        textViewMine = (TextView) findViewById(R.id.tv_activity_assistant_index_mine);

        imageViewAgreeForLeave = (ImageView) findViewById(R.id.iv_activity_assistant_index_agree_leave);
        imageViewJoinClass = (ImageView) findViewById(R.id.iv_activity_assistant_index_join_class);
        imageViewQuery = (ImageView) findViewById(R.id.iv_activity_assistant_index_query);
        imageViewMine = (ImageView) findViewById(R.id.iv_activity_assistant_index_mine);
        imageViewAddClass = (ImageView) findViewById(R.id.iv_activity_assistant_index_add);
        imageViewAddClass.setImageBitmap(null);
    }

    private void initViewPager(){
        assistantAgreeForClassFragment = new AssistantAgreeForClassFragment();
        assistantAgreeForLeaveFragment = new AssistantAgreeForLeaveFragment();
        assistantInquireFragment = new AssistantInquireFragment();
        assistantMineFragment = new AssistantMineFragment();
        list.add(assistantAgreeForLeaveFragment);
        list.add(assistantAgreeForClassFragment);
        list.add(assistantInquireFragment);
        list.add(assistantMineFragment);

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

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        viewPager.setCurrentItem(0);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textViewAgreeForLeave.setTextAppearance(R.style.OnClickText);
                            textViewJoinClass.setTextAppearance(R.style.unClickText);
                            textViewQuery.setTextAppearance(R.style.unClickText);
                            textViewMine.setTextAppearance(R.style.unClickText);
                        }else {
                            textViewAgreeForLeave.setTextAppearance(getApplicationContext(), R.style.OnClickText);
                            textViewJoinClass.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewQuery.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewMine.setTextAppearance(getApplicationContext(), R.style.unClickText);
                        }
                        imageViewAddClass.setImageBitmap(null);
                        imageViewAddClass.setOnClickListener(null);

                        imageViewAgreeForLeave.setImageResource(R.drawable.ask_for_leave);
                        imageViewJoinClass.setImageResource(R.drawable.un_schedule);
                        imageViewQuery.setImageResource(R.drawable.un_query);
                        imageViewMine.setImageResource(R.drawable.un_mine);
                        title.setText("申请批假");
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textViewAgreeForLeave.setTextAppearance(R.style.unClickText);
                            textViewJoinClass.setTextAppearance(R.style.OnClickText);
                            textViewQuery.setTextAppearance(R.style.unClickText);
                            textViewMine.setTextAppearance(R.style.unClickText);
                        }else {
                            textViewAgreeForLeave.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewJoinClass.setTextAppearance(getApplicationContext(), R.style.OnClickText);
                            textViewQuery.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewMine.setTextAppearance(getApplicationContext(), R.style.unClickText);
                        }
                        imageViewAddClass.setImageResource(R.drawable.add);
                        imageViewAddClass.setOnClickListener(AssistantIndexActivity.this);

                        imageViewAgreeForLeave.setImageResource(R.drawable.un_ask_for_leave);
                        imageViewJoinClass.setImageResource(R.drawable.schedule);
                        imageViewQuery.setImageResource(R.drawable.un_query);
                        imageViewMine.setImageResource(R.drawable.un_mine);
                        title.setText("申请入班");
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textViewAgreeForLeave.setTextAppearance(R.style.unClickText);
                            textViewJoinClass.setTextAppearance(R.style.unClickText);
                            textViewQuery.setTextAppearance(R.style.OnClickText);
                            textViewMine.setTextAppearance(R.style.unClickText);
                        }else {
                            textViewAgreeForLeave.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewJoinClass.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewQuery.setTextAppearance(getApplicationContext(), R.style.OnClickText);
                            textViewMine.setTextAppearance(getApplicationContext(), R.style.unClickText);
                        }
                        imageViewAddClass.setImageBitmap(null);
                        imageViewAddClass.setOnClickListener(null);

                        imageViewAgreeForLeave.setImageResource(R.drawable.un_ask_for_leave);
                        imageViewJoinClass.setImageResource(R.drawable.un_schedule);
                        imageViewQuery.setImageResource(R.drawable.query);
                        imageViewMine.setImageResource(R.drawable.un_mine);
                        title.setText("查询");
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textViewAgreeForLeave.setTextAppearance(R.style.unClickText);
                            textViewJoinClass.setTextAppearance(R.style.unClickText);
                            textViewQuery.setTextAppearance(R.style.unClickText);
                            textViewMine.setTextAppearance(R.style.OnClickText);
                        }else {
                            textViewAgreeForLeave.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewJoinClass.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewQuery.setTextAppearance(getApplicationContext(), R.style.unClickText);
                            textViewMine.setTextAppearance(getApplicationContext(), R.style.OnClickText);
                        }
                        imageViewAddClass.setImageBitmap(null);
                        imageViewAddClass.setOnClickListener(null);

                        imageViewAgreeForLeave.setImageResource(R.drawable.un_ask_for_leave);
                        imageViewJoinClass.setImageResource(R.drawable.un_schedule);
                        imageViewQuery.setImageResource(R.drawable.un_query);
                        imageViewMine.setImageResource(R.drawable.mine);
                        title.setText("我");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initListener(){
        layoutAgreeForLeave.setOnClickListener(this);
        layoutJoinClass.setOnClickListener(this);
        layoutQuery.setOnClickListener(this);
        layoutMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_assistant_index_agree_leave:
                viewPager.setCurrentItem(0);
                break;
            case R.id.activity_assistant_index_join_class:
                viewPager.setCurrentItem(1);
                break;
            case R.id.activity_assistant_index_query:
                viewPager.setCurrentItem(2);
                break;
            case R.id.activity_assistant_index_mine:
                viewPager.setCurrentItem(3);
                break;
            case R.id.iv_activity_assistant_index_add:
                Intent intent = new Intent(AssistantIndexActivity.this, AssistantAddClassActivity.class);
                startActivityForResult(intent, ASSISTANT_ADD_CLASS);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.finishAll();
    }
}
