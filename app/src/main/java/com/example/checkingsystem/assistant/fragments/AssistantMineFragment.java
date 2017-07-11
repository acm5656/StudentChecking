package com.example.checkingsystem.assistant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.checkingsystem.ChangePasswordActivity;
import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.assistant.activity.AssistantChangeInfoActivity;
import com.example.checkingsystem.dao.AssistantDao;

import de.hdodenhof.circleimageview.CircleImageView;
import util.ActivityColectorUtil;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantMineFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button buttonExit;
    private RelativeLayout layoutChangePwd;
    private RelativeLayout layoutChangeInfo;
    private CircleImageView headImageView;
    private TextView textViewId;
    private TextView textViewName;
    public static final int CHANGE_PASSWORD_CODE = 1;
    public static final int CHANGE_INFO_REQUEST_CODE = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_assistant_mine, container, false);
        initUI();
        initListener();
        return view;
    }

    private void initUI(){
        buttonExit = (Button) view.findViewById(R.id.btn_fragment_assistant_mine_exit);
        layoutChangePwd = (RelativeLayout) view.findViewById(R.id.fragment_assistant_mine_change_password);
        layoutChangeInfo = (RelativeLayout) view.findViewById(R.id.fragment_assistant_mine_changeInfo);
        headImageView = (CircleImageView) view.findViewById(R.id.fragment_assistant_mine_head_picture);
        textViewId = (TextView) view.findViewById(R.id.tv_fragment_assistant_mine_id);
        textViewName = (TextView) view.findViewById(R.id.tv_fragment_assistant_mine_name);
    }

    private void initListener(){
        buttonExit.setOnClickListener(this);
        layoutChangePwd.setOnClickListener(this);
        layoutChangeInfo.setOnClickListener(this);
        headImageView.setOnClickListener(this);
        textViewId.setOnClickListener(this);
        textViewName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.fragment_assistant_mine_change_password:
                intent.setClass(getContext(), ChangePasswordActivity.class);
                startActivityForResult(intent, CHANGE_PASSWORD_CODE);
                break;
            case R.id.fragment_assistant_mine_changeInfo:
                intent.setClass(getContext(), AssistantChangeInfoActivity.class);
                startActivityForResult(intent,CHANGE_INFO_REQUEST_CODE);
                break;
            case R.id.btn_fragment_assistant_mine_exit:
                AssistantDao assistantDao = new AssistantDao(getActivity());
                assistantDao.deleteAssistant(LoginActivity.assistantStatic);
                intent.setClass(getContext(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
