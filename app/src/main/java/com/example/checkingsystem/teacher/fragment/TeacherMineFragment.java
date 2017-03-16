package com.example.checkingsystem.teacher.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.checkingsystem.MainActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Teacher;

import java.util.List;

public class TeacherMineFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button teacher_fragment_mine_exit;

    public TeacherMineFragment() {
    }


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
                MainActivity.teacherDao.deleteTeacher(MainActivity.teacherStatic);
                MainActivity.teacherStatic = null;
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
