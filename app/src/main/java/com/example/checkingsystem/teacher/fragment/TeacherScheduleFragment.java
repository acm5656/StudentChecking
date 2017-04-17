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
import android.widget.TextView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.entity.TeacherCourseTimeTable;
import com.example.checkingsystem.teacher.activity.TeacherCheckingActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TeacherScheduleFragment extends Fragment implements View.OnClickListener{
    private TextView teacherWeekOneClassOne;
    private TextView teacherWeekOneClassTwo;
    private TextView teacherWeekOneClassThree;
    private TextView teacherWeekOneClassFour;

    private TextView teacherWeekTwoClassOne;
    private TextView teacherWeekTwoClassTwo;
    private TextView teacherWeekTwoClassThree;
    private TextView teacherWeekTwoClassFour;

    private TextView teacherWeekThreeClassOne;
    private TextView teacherWeekThreeClassTwo;
    private TextView teacherWeekThreeClassThree;
    private TextView teacherWeekThreeClassFour;

    private TextView teacherWeekFourClassOne;
    private TextView teacherWeekFourClassTwo;
    private TextView teacherWeekFourClassThree;
    private TextView teacherWeekFourClassFour;

    private TextView teacherWeekFiveClassOne;
    private TextView teacherWeekFiveClassTwo;
    private TextView teacherWeekFiveClassThree;
    private TextView teacherWeekFiveClassFour;

    private TextView teacherWeekSixClassOne;
    private TextView teacherWeekSixClassTwo;
    private TextView teacherWeekSixClassThree;
    private TextView teacherWeekSixClassFour;

    private TextView teacherWeekSevenClassOne;
    private TextView teacherWeekSevenClassTwo;
    private TextView teacherWeekSevenClassThree;
    private TextView teacherWeekSevenClassFour;
    private OnFragmentInteractionListener mListener;
    private View view;
    private TextView weekOneClassOne;
    private Map<String, TextView> textViewMap;

    public TeacherScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_schedule, container, false);
        initUI();

        return view;
    }

    private void initUI() {
        teacherWeekOneClassOne = (TextView) view.findViewById(R.id.teacherWeekOneClassOne);
        teacherWeekOneClassOne.setOnClickListener(this);
        teacherWeekOneClassOne    = (TextView)view.findViewById(R.id.teacherWeekOneClassOne);
        teacherWeekOneClassTwo    = (TextView)view.findViewById(R.id.teacherWeekOneClassTwo);
        teacherWeekOneClassThree  = (TextView)view.findViewById(R.id.teacherWeekOneClassThree);
        teacherWeekOneClassFour   = (TextView)view.findViewById(R.id.teacherWeekOneClassFour);
        teacherWeekTwoClassOne    = (TextView)view.findViewById(R.id.teacherWeekTwoClassOne);
        teacherWeekTwoClassTwo    = (TextView)view.findViewById(R.id.teacherWeekTwoClassTwo);
        teacherWeekTwoClassThree  = (TextView)view.findViewById(R.id.teacherWeekTwoClassThree);
        teacherWeekTwoClassFour   = (TextView)view.findViewById(R.id.teacherWeekTwoClassFour);
        teacherWeekThreeClassOne  = (TextView)view.findViewById(R.id.teacherWeekThreeClassOne);
        teacherWeekThreeClassTwo  = (TextView)view.findViewById(R.id.teacherWeekThreeClassTwo);
        teacherWeekThreeClassThree= (TextView)view.findViewById(R.id.teacherWeekThreeClassThree);
        teacherWeekThreeClassFour = (TextView)view.findViewById(R.id.teacherWeekThreeClassFour);
        teacherWeekFourClassOne   = (TextView)view.findViewById(R.id.teacherWeekFourClassOne);
        teacherWeekFourClassTwo   = (TextView)view.findViewById(R.id.teacherWeekFourClassTwo);
        teacherWeekFourClassThree = (TextView)view.findViewById(R.id.teacherWeekFourClassThree);
        teacherWeekFourClassFour  = (TextView)view.findViewById(R.id.teacherWeekFourClassFour);
        teacherWeekFiveClassOne   = (TextView)view.findViewById(R.id.teacherWeekFiveClassOne);
        teacherWeekFiveClassTwo   = (TextView)view.findViewById(R.id.teacherWeekFiveClassTwo);
        teacherWeekFiveClassThree = (TextView)view.findViewById(R.id.teacherWeekFiveClassThree);
        teacherWeekFiveClassFour  = (TextView)view.findViewById(R.id.teacherWeekFiveClassFour);
        teacherWeekSixClassOne    = (TextView)view.findViewById(R.id.teacherWeekSixClassOne);
        teacherWeekSixClassTwo    = (TextView)view.findViewById(R.id.teacherWeekSixClassTwo);
        teacherWeekSixClassThree  = (TextView)view.findViewById(R.id.teacherWeekSixClassThree);
        teacherWeekSixClassFour   = (TextView)view.findViewById(R.id.teacherWeekSixClassFour);
        teacherWeekSevenClassOne  = (TextView)view.findViewById(R.id.teacherWeekSevenClassOne);
        teacherWeekSevenClassTwo  = (TextView)view.findViewById(R.id.teacherWeekSevenClassTwo);
        teacherWeekSevenClassThree= (TextView)view.findViewById(R.id.teacherWeekSevenClassThree);
        teacherWeekSevenClassFour = (TextView)view.findViewById(R.id.teacherWeekSevenClassFour);

        teacherWeekOneClassOne.setOnClickListener(this);
        teacherWeekOneClassTwo.setOnClickListener(this);
        teacherWeekOneClassThree.setOnClickListener(this);
        teacherWeekOneClassFour.setOnClickListener(this);
        teacherWeekTwoClassOne.setOnClickListener(this);
        teacherWeekTwoClassTwo.setOnClickListener(this);
        teacherWeekTwoClassThree.setOnClickListener(this);
        teacherWeekTwoClassFour.setOnClickListener(this);
        teacherWeekThreeClassOne.setOnClickListener(this);
        teacherWeekThreeClassTwo.setOnClickListener(this);
        teacherWeekThreeClassThree.setOnClickListener(this);
        teacherWeekThreeClassFour.setOnClickListener(this);
        teacherWeekFourClassOne.setOnClickListener(this);
        teacherWeekFourClassTwo.setOnClickListener(this);
        teacherWeekFourClassThree.setOnClickListener(this);
        teacherWeekFourClassFour.setOnClickListener(this);
        teacherWeekFiveClassOne.setOnClickListener(this);
        teacherWeekFiveClassTwo.setOnClickListener(this);
        teacherWeekFiveClassThree.setOnClickListener(this);
        teacherWeekFiveClassFour.setOnClickListener(this);
        teacherWeekSixClassOne.setOnClickListener(this);
        teacherWeekSixClassTwo.setOnClickListener(this);
        teacherWeekSixClassThree.setOnClickListener(this);
        teacherWeekSixClassFour.setOnClickListener(this);
        teacherWeekSevenClassOne.setOnClickListener(this);
        teacherWeekSevenClassTwo.setOnClickListener(this);
        teacherWeekSevenClassThree.setOnClickListener(this);
        teacherWeekSevenClassFour.setOnClickListener(this);
        textViewMap = new HashMap<>();
        textViewMap.put("11",  teacherWeekOneClassOne  );
        textViewMap.put("12",  teacherWeekOneClassTwo  );
        textViewMap.put("13",teacherWeekOneClassThree  );
        textViewMap.put("14", teacherWeekOneClassFour  );
        textViewMap.put("21",  teacherWeekTwoClassOne  );
        textViewMap.put("22",  teacherWeekTwoClassTwo  );
        textViewMap.put("23",teacherWeekTwoClassThree  );
        textViewMap.put("24", teacherWeekTwoClassFour  );
        textViewMap.put("31",teacherWeekThreeClassOne  );
        textViewMap.put("32",teacherWeekThreeClassTwo  );
        textViewMap.put("33",teacherWeekThreeClassThree);
        textViewMap.put("34",teacherWeekThreeClassFour );
        textViewMap.put("41", teacherWeekFourClassOne  );
        textViewMap.put("42", teacherWeekFourClassTwo  );
        textViewMap.put("43",teacherWeekFourClassThree );
        textViewMap.put("44",teacherWeekFourClassFour  );
        textViewMap.put("51", teacherWeekFiveClassOne  );
        textViewMap.put("52", teacherWeekFiveClassTwo  );
        textViewMap.put("53",teacherWeekFiveClassThree );
        textViewMap.put("54",teacherWeekFiveClassFour  );
        textViewMap.put("61",  teacherWeekSixClassOne  );
        textViewMap.put("62",  teacherWeekSixClassTwo  );
        textViewMap.put("63",teacherWeekSixClassThree  );
        textViewMap.put("64", teacherWeekSixClassFour  );
        textViewMap.put("71",teacherWeekSevenClassOne  );
        textViewMap.put("72",teacherWeekSevenClassTwo  );
        textViewMap.put("73",teacherWeekSevenClassThree);
        textViewMap.put("74",teacherWeekSevenClassFour );
        updateUI(1);
    }

    @Override
    public void onClick(View v) {
        String teacherWeek = ((TeacherIndexActivity)getActivity()).weekTime;
        Intent intent = new Intent(getActivity(), TeacherCheckingActivity.class);
        String courseID = null;
        Log.e("test","-----"+v.getId());
        Log.e("test","-----"+teacherWeek);
        switch (v.getId())
        {
            case R.id.teacherWeekOneClassOne:
                courseID = getCouorseID(teacherWeek,"1","1");

                break;
            case R.id.teacherWeekOneClassTwo:
                courseID = getCouorseID(teacherWeek,"1","2");
                break;
            case R.id.teacherWeekOneClassThree:
                courseID = getCouorseID(teacherWeek,"1","3");
                break;
            case R.id.teacherWeekOneClassFour:
                courseID = getCouorseID(teacherWeek,"1","4");
                break;
            case R.id.teacherWeekTwoClassOne:
                courseID = getCouorseID(teacherWeek,"2","1");
                break;
            case R.id.teacherWeekTwoClassTwo:
                courseID = getCouorseID(teacherWeek,"2","2");
                break;
            case R.id.teacherWeekTwoClassThree:
                courseID = getCouorseID(teacherWeek,"2","3");
                break;
            case R.id.teacherWeekTwoClassFour:
                courseID = getCouorseID(teacherWeek,"2","4");
                break;
            case R.id.teacherWeekThreeClassOne:
                courseID = getCouorseID(teacherWeek,"3","1");
                break;
            case R.id.teacherWeekThreeClassTwo:
                courseID = getCouorseID(teacherWeek,"3","2");
                break;
            case R.id.teacherWeekThreeClassThree:
                courseID = getCouorseID(teacherWeek,"3","3");
                break;
            case R.id.teacherWeekThreeClassFour:
                courseID = getCouorseID(teacherWeek,"3","4");
                break;
            case R.id.teacherWeekFourClassOne:
                courseID = getCouorseID(teacherWeek,"4","1");
                break;
            case R.id.teacherWeekFourClassTwo:
                courseID = getCouorseID(teacherWeek,"4","2");
                break;
            case R.id.teacherWeekFourClassThree:
                courseID = getCouorseID(teacherWeek,"4","3");
                break;
            case R.id.teacherWeekFourClassFour:
                courseID = getCouorseID(teacherWeek,"4","4");
                break;
            case R.id.teacherWeekFiveClassOne:
                courseID = getCouorseID(teacherWeek,"5","1");
                break;
            case R.id.teacherWeekFiveClassTwo:
                courseID = getCouorseID(teacherWeek,"5","2");
                break;
            case R.id.teacherWeekFiveClassThree:
                courseID = getCouorseID(teacherWeek,"5","3");
                break;
            case R.id.teacherWeekFiveClassFour:
                courseID = getCouorseID(teacherWeek,"5","4");
                break;
            case R.id.teacherWeekSixClassOne:
                courseID = getCouorseID(teacherWeek,"6","1");
                break;
            case R.id.teacherWeekSixClassTwo:
                courseID = getCouorseID(teacherWeek,"6","2");
                break;
            case R.id.teacherWeekSixClassThree:
                courseID = getCouorseID(teacherWeek,"6","3");
                break;
            case R.id.teacherWeekSixClassFour:
                courseID = getCouorseID(teacherWeek,"6","4");
                break;
            case R.id.teacherWeekSevenClassOne:
                courseID = getCouorseID(teacherWeek,"7","1");
                break;
            case R.id.teacherWeekSevenClassTwo:
                courseID = getCouorseID(teacherWeek,"7","2");
                break;
            case R.id.teacherWeekSevenClassThree:
                courseID = getCouorseID(teacherWeek,"7","3");
                break;
            case R.id.teacherWeekSevenClassFour:
                courseID = getCouorseID(teacherWeek,"7","4");
                break;
        }

        if(courseID!=null) {
            intent.putExtra("courseID", courseID);
            startActivity(intent);
        }
    }
    private String getCouorseID(String weekTime, String day, String classTime) {
        for(TeacherCourseTimeTable teacherCourseTimeTable:LoginActivity.teacherCourseTimeTableList) {
            Log.e("test",weekTime+" "+day+" "+classTime);
            if(teacherCourseTimeTable.getCourseTimeWeek().equals(new Integer(weekTime)))
            {
                String classTimeTeacher = getClassTime(teacherCourseTimeTable.getCourseTimeGmtBegin());
                Log.e("test2",teacherCourseTimeTable.getCourseTimeWeek()+" "+teacherCourseTimeTable.getCourseTimeDay()+" "+classTimeTeacher);
                if(classTimeTeacher.equals(classTime))
                {
                    Log.e("test",teacherCourseTimeTable.getCourseTimeId()+"");
                    if(teacherCourseTimeTable.getCourseTimeDay().equals(new Integer(day)))
                    {

                        Log.e("test",teacherCourseTimeTable.getCourseTimeId()+"");
                        return teacherCourseTimeTable.getCourseTimeId();
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
        if(LoginActivity.teacherCourseTimeTableList!=null) {
            for (TeacherCourseTimeTable teacherCourseTimeTable : LoginActivity.teacherCourseTimeTableList) {
                if (teacherCourseTimeTable.getCourseTimeWeek() == week) {
                    String classTime = getClassTime(teacherCourseTimeTable.getCourseTimeGmtBegin());
                    TextView textView = textViewMap.get(teacherCourseTimeTable.getCourseTimeDay() + classTime);
                    if(textView!=null) {
                        textView.setText(teacherCourseTimeTable.getCourseName());
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
