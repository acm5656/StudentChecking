package com.example.checkingsystem.assistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;

import util.ActivityColectorUtil;

/**
 * Created by 竞豪 on 2017/7/9.
 */
public class AssistantAgreeForClassActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageViewHead;
    private TextView textViewNo;
    private TextView textViewName;
    private TextView textViewNickName;
    private TextView textViewTel;
    private TextView textViewEmail;
    private Button buttonSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_agree_for_class);
        ActivityColectorUtil.addActivity(this);
        initUI();
        initListener();
    }

    private void initUI(){
        imageViewHead = (ImageView) findViewById(R.id.iv_activity_assistant_agree_for_class_head);
        textViewNo = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_no);
        textViewName = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_name);
        textViewNickName = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_nick_name);
        textViewTel = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_tel);
        textViewEmail = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_email);
        buttonSubmit = (Button) findViewById(R.id.btn_activity_assistant_agree_for_class_submit);
    }

    private void initListener(){
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_assistant_agree_for_class_submit:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.finishAll();
    }
}
