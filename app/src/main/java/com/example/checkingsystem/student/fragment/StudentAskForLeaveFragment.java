package com.example.checkingsystem.student.fragment;

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


public class StudentAskForLeaveFragment extends Fragment {

    private EditText reasonEditText;
    private Button submitButton;
    private View view;

    private OnFragmentInteractionListener mListener;

    private TextView weekTextView;
    private TextView dayTextView;
    private TextView courseNameTextView;
    private String courseID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_ask_for_leave, container, false);
        courseID = ((StudentCheckingActivity)getActivity()).courseID;
        initSource();
        return view;
    }

    private void initSource() {
        weekTextView = (TextView) view.findViewById(R.id.fragment_student_ask_for_leave_week);
        dayTextView = (TextView)view.findViewById(R.id.fragment_student_ask_for_leave_day);
        courseNameTextView = (TextView)view.findViewById(R.id.fragment_student_ask_for_leave_name);

        for(StudentCourseTimeTable studentCourseTimeTable:LoginActivity.studentCourseTimeTableList)
        {
            if(studentCourseTimeTable.getCourseTimeId().equals(courseID))
            {
                weekTextView.setText("第"+studentCourseTimeTable.getCourseTimeWeek()+"周");
                courseNameTextView.setText(studentCourseTimeTable.getCourseName());
                String classTime = StudentScheduleFragment.getClassTime(studentCourseTimeTable.getCourseTimeGmtBegin());
                dayTextView.setText("周"+studentCourseTimeTable.getCourseTimeDay()+"第"+classTime+"节");
            }
        }
        reasonEditText = (EditText)view.findViewById(R.id.fragement_student_ask_for_leave_reason);
        submitButton = (Button)view.findViewById(R.id.fragment_student_ask_for_leave_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentID = LoginActivity.studentStatic.getStudentId();

                String reason = reasonEditText.getText().toString();
                if(reason!=null&&!reason.trim().equals("")) {
                    StudentAskForLeaveNet studentAskForLeaveNet = new StudentAskForLeaveNet();
                    studentAskForLeaveNet.studentAskForLeave(getActivity(), studentID, reason, courseID);
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
