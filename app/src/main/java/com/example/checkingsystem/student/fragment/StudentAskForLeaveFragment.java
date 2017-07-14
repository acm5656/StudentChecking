package com.example.checkingsystem.student.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.StudentCourseTimeTable;
import com.example.checkingsystem.net.StudentAskForLeaveNet;
import com.example.checkingsystem.student.activity.StudentCheckingActivity;
import com.example.checkingsystem.teacher.fragment.TeacherScheduleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PickerView;


public class StudentAskForLeaveFragment extends Fragment {

    private EditText reasonEditText;
    private Button submitButton;
    private View view;
    private PickerView startYearPV;
    private PickerView startMonthPV;
    private PickerView startDayPV;
    private PickerView endtYearPV;
    private PickerView endMonthPV;
    private PickerView endDayPV;

    private String startYearStr="2017";
    private String startMonthStr="1";
    private String startDayStr="1";

    private String endYearStr="2017";
    private String endMonthStr="1";
    private String endDayStr="1";

    int dayArr[] =new int[] {0,31,28,31,30,31,30,31,31,30,31,30,31};

    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_ask_for_leave, container, false);
        initSource();
        return view;
    }

    private void initSource() {

        startYearPV = (PickerView) view.findViewById(R.id.fragment_student_ask_for_leave_start_year_pv);
        startMonthPV = (PickerView) view.findViewById(R.id.fragment_student_ask_for_leave_start_month_pv);
        startDayPV = (PickerView) view.findViewById(R.id.fragment_student_ask_for_leave_start_day_pv);
        endtYearPV = (PickerView) view.findViewById(R.id.fragment_student_ask_for_leave_end_year_pv);
        endMonthPV = (PickerView) view.findViewById(R.id.fragment_student_ask_for_leave_end_month_pv);
        endDayPV= (PickerView) view.findViewById(R.id.fragment_student_ask_for_leave_end_day_pv);

        List<String> month = new ArrayList<String>();
        List<String> year = new ArrayList<String>();
        final List<String> day = new ArrayList<String>();
        for (int i = 1; i <= 12; i++)
        {
            if (i<10){
                month.add("0" + i);
            }else{
                month.add("" + i);
            }
        }
        for(int i = 1;i<=31;i++)
        {
            if (i<10){
                day.add("0" + i);
            }else{
                day.add("" + i);
            }
        }
        for(int i = 1 ;i<=20;i++)
        {
            year.add(2016 + i + "");
        }
        startMonthPV.setData(month);
        endMonthPV.setData(month);
        startMonthPV.setSelected(0);
        endMonthPV.setSelected(0);
        startDayPV.setData(day);
        endDayPV.setData(day);
        startDayPV.setSelected(0);
        endDayPV.setSelected(0);

        startYearPV.setData(year);
        endtYearPV.setData(year);
        startYearPV.setSelected(0);
        endtYearPV.setSelected(0);

        startMonthPV.setOnSelectListener(new PickerView.onSelectListener()
        {
            @Override
            public void onSelect(String text)
            {

                Integer integer = new Integer(text);
                startMonthStr = text;
                int count = dayArr[integer];
                day.clear();
                for(int i = 1;i<=count ;i++)
                {
                    if(i<10){
                        day.add("0" + i);
                    }else {
                        day.add("" + i);
                    }
                }
                startDayPV.setData(day);
                startDayPV.setSelected(0);
            }
        });
        endMonthPV.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                Integer integer = new Integer(text);
                int count = dayArr[integer];
                endMonthStr = text;
                day.clear();
                for(int i = 1;i<=count ;i++)
                {
                    if(i<10){
                        day.add("0" + i);
                    }else {
                        day.add("" + i);
                    }
                }
                endDayPV.setData(day);
                endDayPV.setSelected(0);
            }
        });
        startYearPV.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                startYearStr = text;
            }
        });
        endtYearPV.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                endYearStr = text;
            }
        });

        startDayPV.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                startDayStr = text;
            }
        });
        endDayPV.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                endDayStr = text;
            }
        });

        reasonEditText = (EditText)view.findViewById(R.id.fragement_student_ask_for_leave_reason);
        submitButton = (Button)view.findViewById(R.id.fragment_student_ask_for_leave_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentID = LoginActivity.studentStatic.getStudentId();
                String reason = reasonEditText.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                Date beginDate = null;
                Date endDate = null;
                try {
                    beginDate = sdf.parse(startYearStr+"-"+startMonthStr+"-"+startDayStr);
                    endDate = sdf.parse(endYearStr+"-"+endMonthStr+"-"+endDayStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(reason!=null&&!reason.trim().equals("")) {
                    StudentAskForLeaveNet studentAskForLeaveNet = new StudentAskForLeaveNet();
                    studentAskForLeaveNet.studentAskForLeave(getActivity(), studentID, reason, beginDate,endDate);
                }else {
                    Toast.makeText(getContext(),"请输入请假理由",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
