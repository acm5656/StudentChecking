package com.example.checkingsystem.student.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.net.StudentAskForLeaveNet;
import com.example.checkingsystem.student.activity.StudentCheckingActivity;


public class StudentAskForLeaveFragment extends Fragment {

    private EditText reasonEditText;
    private Button submitButton;
    private View view;

    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_ask_for_leave, container, false);
        initSource();
        return view;
    }

    private void initSource() {
        reasonEditText = (EditText)view.findViewById(R.id.fragement_student_ask_for_leave_reason);
        submitButton = (Button)view.findViewById(R.id.fragment_student_ask_for_leave_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseID = ((StudentCheckingActivity)getActivity()).courseID;
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
