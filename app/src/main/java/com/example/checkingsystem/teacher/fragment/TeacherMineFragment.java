package com.example.checkingsystem.teacher.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.ChangePasswordActivity;
import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.teacher.activity.TeacherChangeInfoActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherMineFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button teacher_fragment_mine_exit;
    private RelativeLayout changePasswordRelative;
    private RelativeLayout changeInfoRelative;
    private CircleImageView headImageView;
    private TextView schoolNumber;
    private TextView name;
    public static final int CHANGE_INFO_REQUEST_CODE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_mine, container, false);
        intiUI();
        return view;
    }

    private void intiUI() {
        teacher_fragment_mine_exit = (Button) view.findViewById(R.id.teacher_fragment_mine_exit);
        teacher_fragment_mine_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.teacherDao.deleteTeacher(LoginActivity.teacherStatic);
                LoginActivity.teacherStatic = null;
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        changePasswordRelative = (RelativeLayout)view.findViewById(R.id.fragment_teacher_mine_change_password);
        changeInfoRelative = (RelativeLayout)view.findViewById(R.id.fragment_teacher_mine_changeInfo);
        changeInfoRelative.setOnClickListener(this);
        changePasswordRelative.setOnClickListener(this);
        headImageView = (CircleImageView) view.findViewById(R.id.activity_teacher_change_info_head_picture);
        if(LoginActivity.headPictureBitmap!=null)
        {
            headImageView.setImageBitmap(LoginActivity.headPictureBitmap);
        }
        schoolNumber = (TextView) view.findViewById(R.id.fragment_teacher_mine_number);
        name = (TextView)view.findViewById(R.id.fragment_teacher_mine_name);
        schoolNumber.setText(LoginActivity.teacherStatic.getTeacherSchoolUsername());
        if(LoginActivity.teacherStatic.getTeacherName()!=null&&name!=null) {
            name.setText(LoginActivity.teacherStatic.getTeacherName());
        }else
        {
            Log.e("test","is null");
        }



    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.fragment_teacher_mine_change_password:
                intent.setClass(getContext(),ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_teacher_mine_changeInfo:
                intent.setClass(getContext(), TeacherChangeInfoActivity.class);
                startActivityForResult(intent,CHANGE_INFO_REQUEST_CODE);
                break;
        }

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String dataStr = null;
        if(resultCode== Activity.RESULT_OK)
        {
            switch (requestCode)
            {

                case CHANGE_INFO_REQUEST_CODE:
                    dataStr = data.getStringExtra("data");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                    if(LoginActivity.headPictureBitmap!=null)
                    {
                        headImageView.setImageBitmap(LoginActivity.headPictureBitmap);
                    }
                    break;
            }
        }
    }
}
