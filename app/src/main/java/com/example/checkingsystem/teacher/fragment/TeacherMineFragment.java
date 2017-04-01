package com.example.checkingsystem.teacher.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.checkingsystem.ChangePasswordActivity;
import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;

public class TeacherMineFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button teacher_fragment_mine_exit;
    private RelativeLayout changePasswordRelative;
    private RelativeLayout changeInfoRelative;

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
        changePasswordRelative.setOnClickListener(this);


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

                break;
        }

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
