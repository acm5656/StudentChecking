package com.example.checkingsystem.student.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.student.activity.StudentCheckingActivity;

public class StudentScheduleFragment extends Fragment implements View.OnClickListener{
    private TextView weekOneClassOne;
    private View view;

    private OnFragmentInteractionListener mListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_schedule, container, false);
        initUI();
        weekOneClassOne.setOnClickListener(this);
        return view;
    }

    private void initUI() {
        weekOneClassOne = (TextView) view.findViewById(R.id.WeekOneClassOne);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.WeekOneClassOne:
                Intent intent = new Intent(getActivity(), StudentCheckingActivity.class);
                startActivity(intent);
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
