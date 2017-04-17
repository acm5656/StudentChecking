package com.example.checkingsystem.student.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.StudentCourseTimeTable;

import com.example.checkingsystem.student.activity.StudentCheckingActivity;
import com.example.checkingsystem.student.activity.StudentIndexActivity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StudentScheduleFragment extends Fragment implements View.OnClickListener{
    private TextView studentWeekOneClassOne;
    private TextView studentWeekOneClassTwo;
    private TextView studentWeekOneClassThree;
    private TextView studentWeekOneClassFour;

    private TextView studentWeekTwoClassOne;
    private TextView studentWeekTwoClassTwo;
    private TextView studentWeekTwoClassThree;
    private TextView studentWeekTwoClassFour;

    private TextView studentWeekThreeClassOne;
    private TextView studentWeekThreeClassTwo;
    private TextView studentWeekThreeClassThree;
    private TextView studentWeekThreeClassFour;

    private TextView studentWeekFourClassOne;
    private TextView studentWeekFourClassTwo;
    private TextView studentWeekFourClassThree;
    private TextView studentWeekFourClassFour;

    private TextView studentWeekFiveClassOne;
    private TextView studentWeekFiveClassTwo;
    private TextView studentWeekFiveClassThree;
    private TextView studentWeekFiveClassFour;

    private TextView studentWeekSixClassOne;
    private TextView studentWeekSixClassTwo;
    private TextView studentWeekSixClassThree;
    private TextView studentWeekSixClassFour;

    private TextView studentWeekSevenClassOne;
    private TextView studentWeekSevenClassTwo;
    private TextView studentWeekSevenClassThree;
    private TextView studentWeekSevenClassFour;

    private View view;

    private OnFragmentInteractionListener mListener;
    private Map<String,TextView> textViewMap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_schedule, container, false);
        initUI();

        return view;
    }

    private void initUI() {
        textViewMap = new HashMap<>();
        studentWeekOneClassOne = (TextView) view.findViewById(R.id.studentWeekOneClassOne);
        studentWeekOneClassOne.setOnClickListener(this);
        studentWeekOneClassOne    = (TextView)view.findViewById(R.id.studentWeekOneClassOne);
        studentWeekOneClassTwo    = (TextView)view.findViewById(R.id.studentWeekOneClassTwo);
        studentWeekOneClassThree  = (TextView)view.findViewById(R.id.studentWeekOneClassThree);
        studentWeekOneClassFour   = (TextView)view.findViewById(R.id.studentWeekOneClassFour);
        studentWeekTwoClassOne    = (TextView)view.findViewById(R.id.studentWeekTwoClassOne);
        studentWeekTwoClassTwo    = (TextView)view.findViewById(R.id.studentWeekTwoClassTwo);
        studentWeekTwoClassThree  = (TextView)view.findViewById(R.id.studentWeekTwoClassThree);
        studentWeekTwoClassFour   = (TextView)view.findViewById(R.id.studentWeekTwoClassFour);
        studentWeekThreeClassOne  = (TextView)view.findViewById(R.id.studentWeekThreeClassOne);
        studentWeekThreeClassTwo  = (TextView)view.findViewById(R.id.studentWeekThreeClassTwo);
        studentWeekThreeClassThree= (TextView)view.findViewById(R.id.studentWeekThreeClassThree);
        studentWeekThreeClassFour = (TextView)view.findViewById(R.id.studentWeekThreeClassFour);
        studentWeekFourClassOne   = (TextView)view.findViewById(R.id.studentWeekFourClassOne);
        studentWeekFourClassTwo   = (TextView)view.findViewById(R.id.studentWeekFourClassTwo);
        studentWeekFourClassThree = (TextView)view.findViewById(R.id.studentWeekFourClassThree);
        studentWeekFourClassFour  = (TextView)view.findViewById(R.id.studentWeekFourClassFour);
        studentWeekFiveClassOne   = (TextView)view.findViewById(R.id.studentWeekFiveClassOne);
        studentWeekFiveClassTwo   = (TextView)view.findViewById(R.id.studentWeekFiveClassTwo);
        studentWeekFiveClassThree = (TextView)view.findViewById(R.id.studentWeekFiveClassThree);
        studentWeekFiveClassFour  = (TextView)view.findViewById(R.id.studentWeekFiveClassFour);
        studentWeekSixClassOne    = (TextView)view.findViewById(R.id.studentWeekSixClassOne);
        studentWeekSixClassTwo    = (TextView)view.findViewById(R.id.studentWeekSixClassTwo);
        studentWeekSixClassThree  = (TextView)view.findViewById(R.id.studentWeekSixClassThree);
        studentWeekSixClassFour   = (TextView)view.findViewById(R.id.studentWeekSixClassFour);
        studentWeekSevenClassOne  = (TextView)view.findViewById(R.id.studentWeekSevenClassOne);
        studentWeekSevenClassTwo  = (TextView)view.findViewById(R.id.studentWeekSevenClassTwo);
        studentWeekSevenClassThree= (TextView)view.findViewById(R.id.studentWeekSevenClassThree);
        studentWeekSevenClassFour = (TextView)view.findViewById(R.id.studentWeekSevenClassFour);

        studentWeekOneClassOne.setOnClickListener(this);
        studentWeekOneClassTwo.setOnClickListener(this);
        studentWeekOneClassThree.setOnClickListener(this);
        studentWeekOneClassFour.setOnClickListener(this);
        studentWeekTwoClassOne.setOnClickListener(this);
        studentWeekTwoClassTwo.setOnClickListener(this);
        studentWeekTwoClassThree.setOnClickListener(this);
        studentWeekTwoClassFour.setOnClickListener(this);
        studentWeekThreeClassOne.setOnClickListener(this);
        studentWeekThreeClassTwo.setOnClickListener(this);
        studentWeekThreeClassThree.setOnClickListener(this);
        studentWeekThreeClassFour.setOnClickListener(this);
        studentWeekFourClassOne.setOnClickListener(this);
        studentWeekFourClassTwo.setOnClickListener(this);
        studentWeekFourClassThree.setOnClickListener(this);
        studentWeekFourClassFour.setOnClickListener(this);
        studentWeekFiveClassOne.setOnClickListener(this);
        studentWeekFiveClassTwo.setOnClickListener(this);
        studentWeekFiveClassThree.setOnClickListener(this);
        studentWeekFiveClassFour.setOnClickListener(this);
        studentWeekSixClassOne.setOnClickListener(this);
        studentWeekSixClassTwo.setOnClickListener(this);
        studentWeekSixClassThree.setOnClickListener(this);
        studentWeekSixClassFour.setOnClickListener(this);
        studentWeekSevenClassOne.setOnClickListener(this);
        studentWeekSevenClassTwo.setOnClickListener(this);
        studentWeekSevenClassThree.setOnClickListener(this);
        studentWeekSevenClassFour.setOnClickListener(this);

        textViewMap.put("11",studentWeekOneClassOne);
        textViewMap.put("12",studentWeekOneClassTwo);
        textViewMap.put("13",studentWeekOneClassThree);
        textViewMap.put("14",studentWeekOneClassFour);
        textViewMap.put("21",studentWeekTwoClassOne);
        textViewMap.put("22",studentWeekTwoClassTwo);
        textViewMap.put("23",studentWeekTwoClassThree);
        textViewMap.put("24",studentWeekTwoClassFour);
        textViewMap.put("31",studentWeekThreeClassOne);
        textViewMap.put("32",studentWeekThreeClassTwo);
        textViewMap.put("33",studentWeekThreeClassThree);
        textViewMap.put("34",studentWeekThreeClassFour);
        textViewMap.put("41",studentWeekFourClassOne);
        textViewMap.put("42",studentWeekFourClassTwo);
        textViewMap.put("43",studentWeekFourClassThree);
        textViewMap.put("44",studentWeekFourClassFour);
        textViewMap.put("51",studentWeekFiveClassOne);
        textViewMap.put("52",studentWeekFiveClassTwo);
        textViewMap.put("53",studentWeekFiveClassThree);
        textViewMap.put("54",studentWeekFiveClassFour);
        textViewMap.put("61",studentWeekSixClassOne);
        textViewMap.put("62",studentWeekSixClassTwo);
        textViewMap.put("63",studentWeekSixClassThree);
        textViewMap.put("64",studentWeekSixClassFour);
        textViewMap.put("71",studentWeekSevenClassOne);
        textViewMap.put("72",studentWeekSevenClassTwo);
        textViewMap.put("73",studentWeekSevenClassThree);
        textViewMap.put("74",studentWeekSevenClassFour);
        updateUI(1);
    }

    @Override
    public void onClick(View v) {
        String studentWeek = ((StudentIndexActivity)getActivity()).week;
        Intent intent = new Intent(getActivity(), StudentCheckingActivity.class);
        String courseID = null;
        switch (v.getId())
        {
            case R.id.studentWeekOneClassOne:
                courseID = getCouorseID(studentWeek,"1","1");

                break;
            case R.id.studentWeekOneClassTwo:
                courseID = getCouorseID(studentWeek,"1","2");
                break;
            case R.id.studentWeekOneClassThree:
                courseID = getCouorseID(studentWeek,"1","3");
                break;
            case R.id.studentWeekOneClassFour:
                courseID = getCouorseID(studentWeek,"1","4");
                break;
            case R.id.studentWeekTwoClassOne:
                courseID = getCouorseID(studentWeek,"2","1");
                break;
            case R.id.studentWeekTwoClassTwo:
                courseID = getCouorseID(studentWeek,"2","2");
                break;
            case R.id.studentWeekTwoClassThree:
                courseID = getCouorseID(studentWeek,"2","3");
                break;
            case R.id.studentWeekTwoClassFour:
                courseID = getCouorseID(studentWeek,"2","4");
                break;
            case R.id.studentWeekThreeClassOne:
                courseID = getCouorseID(studentWeek,"3","1");
                break;
            case R.id.studentWeekThreeClassTwo:
                courseID = getCouorseID(studentWeek,"3","2");
                break;
            case R.id.studentWeekThreeClassThree:
                courseID = getCouorseID(studentWeek,"3","3");
                break;
            case R.id.studentWeekThreeClassFour:
                courseID = getCouorseID(studentWeek,"3","4");
                break;
            case R.id.studentWeekFourClassOne:
                courseID = getCouorseID(studentWeek,"4","1");
                break;
            case R.id.studentWeekFourClassTwo:
                courseID = getCouorseID(studentWeek,"4","2");
                break;
            case R.id.studentWeekFourClassThree:
                courseID = getCouorseID(studentWeek,"4","3");
                break;
            case R.id.studentWeekFourClassFour:
                courseID = getCouorseID(studentWeek,"4","4");
                break;
            case R.id.studentWeekFiveClassOne:
                courseID = getCouorseID(studentWeek,"5","1");
                break;
            case R.id.studentWeekFiveClassTwo:
                courseID = getCouorseID(studentWeek,"5","2");
                break;
            case R.id.studentWeekFiveClassThree:
                courseID = getCouorseID(studentWeek,"5","3");
                break;
            case R.id.studentWeekFiveClassFour:
                courseID = getCouorseID(studentWeek,"5","4");
                break;
            case R.id.studentWeekSixClassOne:
                courseID = getCouorseID(studentWeek,"6","1");
                break;
            case R.id.studentWeekSixClassTwo:
                courseID = getCouorseID(studentWeek,"6","2");
                break;
            case R.id.studentWeekSixClassThree:
                courseID = getCouorseID(studentWeek,"6","3");
                break;
            case R.id.studentWeekSixClassFour:
                courseID = getCouorseID(studentWeek,"6","4");
                break;
            case R.id.studentWeekSevenClassOne:
                courseID = getCouorseID(studentWeek,"7","1");
                break;
            case R.id.studentWeekSevenClassTwo:
                courseID = getCouorseID(studentWeek,"7","2");
                break;
            case R.id.studentWeekSevenClassThree:
                courseID = getCouorseID(studentWeek,"7","3");
                break;
            case R.id.studentWeekSevenClassFour:
                courseID = getCouorseID(studentWeek,"7","4");
                break;
        }

        if(courseID!=null) {
            intent.putExtra("courseID", courseID);
            startActivity(intent);
        }
    }
    private String getCouorseID(String weekTime, String day, String classTime) {
        for(StudentCourseTimeTable studentCourseTimeTable:LoginActivity.studentCourseTimeTableList) {
            Log.e("test",weekTime+" "+day+" "+classTime);
            if(studentCourseTimeTable.getCourseTimeWeek().equals(new Integer(weekTime)))
            {
                String classTimestudent = getClassTime(studentCourseTimeTable.getCourseTimeGmtBegin());
                Log.e("test2",studentCourseTimeTable.getCourseTimeWeek()+" "+studentCourseTimeTable.getCourseTimeDay()+" "+classTimestudent);
                if(classTimestudent.equals(classTime))
                {
                    Log.e("test",studentCourseTimeTable.getCourseTimeId()+"");
                    if(studentCourseTimeTable.getCourseTimeDay().equals(new Integer(day)))
                    {

                        Log.e("test",studentCourseTimeTable.getCourseTimeId()+"");
                        return studentCourseTimeTable.getCourseTimeId();
                    }
                }
            }
        }

        return null;
    }
    public void updateUI(int week)
    {
        for(int i = 1 ;i<=7 ;i++)
        {
            for(int j = 1 ; j<=4 ; j++)
            {
                TextView textView = textViewMap.get(i+""+j);
                if(textView!=null) {
                    textView.setText("");

                }
            }
        }
        if(LoginActivity.studentCourseTimeTableList!=null) {
            for (StudentCourseTimeTable studentCourseTimeTable : LoginActivity.studentCourseTimeTableList) {
                if (studentCourseTimeTable.getCourseTimeWeek() == week) {
                    String classTime = getClassTime(studentCourseTimeTable.getCourseTimeGmtBegin());
                    TextView textView = textViewMap.get(studentCourseTimeTable.getCourseTimeDay() + classTime);
                    if(textView!=null) {
                        textView.setText(studentCourseTimeTable.getCourseName());
                    }
                }
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
    
    public static String getClassTime(Timestamp timestamp)
    {
        String time = null;
        Date date = new Date(timestamp.getTime());
        switch (date.getHours())
        {
            case 8:
                time = "1";
                break;
            case 10:
                time = "2";
                break;
            case 14:
                time = "3";
                break;
            case 16:
                time = "4";
                break;
            default:
                time = "0";

        }
        return time;
    }
}
