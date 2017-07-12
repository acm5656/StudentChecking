package com.example.checkingsystem.assistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.checkingsystem.R;

public class AssistantChangeSensitiveInfo extends AppCompatActivity {
    private ImageView sensitiveBack;
    private EditText sensitiveName;
    private EditText sensitiveId;
    private RadioGroup sensitiveSex;
    private Button sensitiveSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_change_sensitive_info);
        initUI();
    }
    private void initUI(){
        sensitiveBack= (ImageView) findViewById(R.id.iv_activity_assistant_change_sensitive_info_back);
        sensitiveName = (EditText) findViewById(R.id.activity_assistant_change_sensitive_name);
        sensitiveId= (EditText) findViewById(R.id.activity_assistant_change_sensitive_school_id);
        sensitiveSex= (RadioGroup) findViewById(R.id.activity_assistant_change_sensitive_sex_radioGroup);
        sensitiveSubmit= (Button) findViewById(R.id.acticity_assistant_change_sensitive_info_submit);
    }
}

