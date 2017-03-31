package com.example.checkingsystem.student.fragment;

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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.RegistFaceActivity;
import com.example.checkingsystem.beans.Item;
import com.example.checkingsystem.student.activity.StudentChangeInfoActivity;
import com.example.checkingsystem.ChangePasswordActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentMineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StudentMineFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button quitButton;
    public static final int FACE_REGIST_REQUEST_CODE = 1;
    private RelativeLayout registRelativeLayout;
    private RelativeLayout changeIngoRelativeLayout;
    private RelativeLayout changePasswordRelativeLayout;
    private TextView schoolIDTextView;
    private TextView studentName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_mine, container, false);
        quitButton = (Button) view.findViewById(R.id.fragment_student_mine_quit);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.studentDao.deleteStudent(LoginActivity.studentStatic);
                LoginActivity.studentStatic = null;
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        registRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout_regist_face);
        registRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistFaceActivity.class);
                startActivityForResult(intent,FACE_REGIST_REQUEST_CODE);
            }
        });
        changeIngoRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout_mineInfo);
        changeIngoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentChangeInfoActivity.class);
                startActivity(intent);
            }
        });
        changePasswordRelativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout_change_password);
        changePasswordRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        schoolIDTextView = (TextView)view.findViewById(R.id.fragment_student_mine_school_id);
        schoolIDTextView.setText(LoginActivity.studentStatic.getStudentTel());


        return view;
    }



    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case FACE_REGIST_REQUEST_CODE:
                    String dataStr = data.getStringExtra("data_return");
                    Log.e("toast","---------------2");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
