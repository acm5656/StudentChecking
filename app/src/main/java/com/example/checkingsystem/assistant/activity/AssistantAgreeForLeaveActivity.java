package com.example.checkingsystem.assistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.assistant.fragments.AssistantAgreeForLeaveFragment;
import com.example.checkingsystem.entity.ClassLeaveShow;
import com.example.checkingsystem.entity.VirtualCourseLeave;
import com.example.checkingsystem.net.AssistantHandlerLeaveNet;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import util.ActivityColectorUtil;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantAgreeForLeaveActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private CircleImageView circleImageView;
    private TextView textViewMineId;
    private TextView textViewMineName;
    private TextView textViewLeaveTime;
    private TextView textViewLeaveReason;
    private EditText editTextLeaveNote;
    private Button buttonAgree;
    private Button buttonDisagree;
    private ClassLeaveShow classLeaveShow;
    private VirtualCourseLeave virtualCourseLeave = new VirtualCourseLeave();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_agree_for_leave);
        ActivityColectorUtil.addActivity(this);
        classLeaveShow = AssistantAgreeForLeaveFragment.classLeaveShow;
        initUI();
        initListener();
    }

    private void initUI(){
        iv_back = (ImageView) findViewById(R.id.iv_activity_assistant_agree_for_leave_back);
        circleImageView = (CircleImageView) findViewById(R.id.clv_activity_assistant_agree_for_leave);
        textViewMineId = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_leave_mine_id);
        textViewMineName = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_leave_mine_name);
        textViewLeaveTime = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_leave_time);
        textViewLeaveReason = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_leave_reason);
        editTextLeaveNote = (EditText) findViewById(R.id.et_activity_assistant_agree_for_leave_note);
        buttonAgree = (Button) findViewById(R.id.btn_activity_assistant_agree_for_leave_agree);
        buttonDisagree = (Button) findViewById(R.id.btn_activity_assistant_agree_for_leave_disagree);
        if(classLeaveShow.getStudentBitmap()!=null) {
            circleImageView.setImageBitmap(classLeaveShow.getStudentBitmap());
        }
        textViewMineId.setText(classLeaveShow.getStudentSchoolName());
        textViewMineName.setText(classLeaveShow.getStudentName());
        Date beginDate = new Date(classLeaveShow.getVirtualCourseLeaveBegin().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String beginDateStr = sdf.format(beginDate);

        Date endDate = new Date(classLeaveShow.getVirtualCourseLeaveEnd().getTime());
        String endDateStr = sdf.format(endDate);
        textViewLeaveTime.setText(beginDateStr+"-"+endDateStr);
        textViewLeaveReason.setText(classLeaveShow.getVirtualCourseLeaveReason());

    }

    private void initListener(){
        buttonAgree.setOnClickListener(this);
        buttonDisagree.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        virtualCourseLeave.setVirtualCourseLeaveId(classLeaveShow.getVirtualCourseLeaveId());
        virtualCourseLeave.setVirtualCourseLeaveFeedback(editTextLeaveNote.getText().toString());
        virtualCourseLeave.setVirtualCourseLeaveAssisId(LoginActivity.assistantStatic.getAssistantId());
        switch (view.getId()){
            case R.id.btn_activity_assistant_agree_for_leave_agree:
                virtualCourseLeave.setVirtualCourseLeaveStatus(VirtualCourseLeave.STATUS_PERMITTED);
                break;
            case R.id.btn_activity_assistant_agree_for_leave_disagree:
                virtualCourseLeave.setVirtualCourseLeaveStatus(VirtualCourseLeave.STATUS_REFUSE);
                break;
            case R.id.iv_activity_assistant_agree_for_leave_back:
                onBackPressed();
                break;
        }

        AssistantHandlerLeaveNet assistantHandlerLeaveNet = new AssistantHandlerLeaveNet();
        assistantHandlerLeaveNet.assistantHnadlerLeave(this,virtualCourseLeave);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.removeActivity(this);
    }
}
