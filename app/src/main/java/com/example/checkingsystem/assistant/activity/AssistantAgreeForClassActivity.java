package com.example.checkingsystem.assistant.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.assistant.fragments.AssistantAgreeForClassFragment;
import com.example.checkingsystem.entity.ClassGrade;
import com.example.checkingsystem.entity.ClassGradeShow;
import com.example.checkingsystem.net.AssistantHandleApplyClassNet;
import com.example.checkingsystem.net.GetPictureNet;

import java.io.InputStream;

import util.ActivityColectorUtil;
import util.CosUtil;

/**
 * Created by 竞豪 on 2017/7/9.
 */
public class AssistantAgreeForClassActivity extends AppCompatActivity implements View.OnClickListener {
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    private ImageView imageViewHead;
    private TextView textViewNo;
    private TextView textViewName;
    private TextView textViewNickName;
    private TextView textViewTel;
    private TextView textViewEmail;
    private Button buttonSubmit;
    private Button buttonRefuse;
    private ClassGradeShow classGradeShow;
    ProgressDialog  progressDialog;
    AlertDialog dialog;
    Bitmap headBitmap;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    imageViewHead.setImageBitmap(headBitmap);
                    progressDialog.dismiss();
                    imageViewHead.setOnClickListener(new View.OnClickListener() { // 点击放大
                        public void onClick(View paramView) {
                            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                            View imgEntryView = inflater.inflate(R.layout.picture_dialog, null); // 加载自定义的布局文件

                            ImageView img = (ImageView)imgEntryView.findViewById(R.id.img_view);
                            img.setImageBitmap(headBitmap);
                            dialog.setView(imgEntryView,0,0,0,0); // 自定义dialog

                            dialog.show();

                            imgEntryView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View paramView) {
                                    dialog.cancel();
                                }
                            });
                        }
                    });

                    break;
                case FAIL:
                    Toast.makeText(AssistantAgreeForClassActivity.this,"人脸加载失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_agree_for_class);
        ActivityColectorUtil.addActivity(this);
        classGradeShow = AssistantAgreeForClassFragment.classGradeShow;
        initUI();
        initListener();
    }

    private void initUI(){
        dialog = new AlertDialog.Builder(this).create();
        imageViewHead = (ImageView) findViewById(R.id.iv_activity_assistant_agree_for_class_head);
        textViewNo = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_no);
        textViewName = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_name);
        textViewNickName = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_nick_name);
        textViewTel = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_tel);
        textViewEmail = (TextView) findViewById(R.id.tv_activity_assistant_agree_for_class_email);
        buttonSubmit = (Button) findViewById(R.id.btn_activity_assistant_agree_for_class_submit);
        buttonRefuse = (Button)findViewById(R.id.btn_activity_assistant_agree_for_class_refuse);
        progressDialog = ProgressDialog.show(this, "加载中", "请稍等", true, true);
        textViewNo.setText(classGradeShow.getStudent().getStudentNo());
        textViewName.setText(classGradeShow.getStudent().getStudentName());
        textViewNickName.setText(classGradeShow.getStudent().getStudentNickname());
        textViewTel.setText(classGradeShow.getStudent().getStudentTel());
        textViewEmail.setText(classGradeShow.getStudent().getStudentEmail());

        GetPictureNet getPictureNet = new GetPictureNet();
        getPictureNet.getPicture(CosUtil.urlFace+classGradeShow.getStudent().getStudentFacecode()+".jpg", new GetPictureNet.HttpPictureCallbackListener() {
            @Override
            public void onFinish(InputStream inputStream) {
                headBitmap = BitmapFactory.decodeStream(inputStream);
                Message message = new Message();
                message.what = SUCCESS;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                Message message = new Message();
                message.what = FAIL;
                handler.sendMessage(message);
            }
        });
    }

    private void initListener(){
        buttonSubmit.setOnClickListener(this);
        buttonRefuse.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ClassGrade classGrade = new ClassGrade();
        classGrade.setClassGradeStudentId(classGradeShow.getStudent().getStudentId());
        classGrade.setClassGradeId(classGradeShow.getClassGrade().getClassGradeId());

        switch (view.getId()){
            case R.id.btn_activity_assistant_agree_for_class_submit:
                classGrade.setClassGradeStatus(ClassGrade.STATUS_OK);
                break;
            case R.id.btn_activity_assistant_agree_for_class_refuse:
                classGrade.setClassGradeStatus(ClassGrade.STATUS_AUTO_REFUSE);
                break;
        }

        AssistantHandleApplyClassNet assistantHandleApplyClassNet = new AssistantHandleApplyClassNet();
        assistantHandleApplyClassNet.assistantHandleApplyClass(this,classGrade);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityColectorUtil.removeActivity(this);
    }
}
