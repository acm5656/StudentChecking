package com.example.checkingsystem.student.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.RegistFaceActivity;
import com.example.checkingsystem.beans.Item;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.net.StudentAddClassNet;
import com.example.checkingsystem.net.StudentAddCourseNet;
import com.example.checkingsystem.student.activity.StudentChangeInfoActivity;
import com.example.checkingsystem.ChangePasswordActivity;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.student.activity.StudentQueryLeaveInfoListActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentMineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StudentMineFragment extends Fragment {
//    Handler setImageHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            headImageView.setImageBitmap(LoginActivity.headPictureBitmap);
//        }
//    };
    private OnFragmentInteractionListener mListener;
    private View view;
    private Button quitButton;
    public static final int FACE_REGIST_REQUEST_CODE = 1;
    public static final int CHANGE_INFO_REQUEST_CODE = 2;
    private RelativeLayout registRelativeLayout;
    private RelativeLayout changeIngoRelativeLayout;
    private RelativeLayout changePasswordRelativeLayout;
    private RelativeLayout stduentAddClassRelativeLayout;
    private RelativeLayout studentQueryAskLeaveInfoRelativeLayout;
    private TextView schoolIDTextView;
    private TextView studentName;
    private CircleImageView headImageView;
    EditText addClassEditText;
    private String classCode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_mine, container, false);
        quitButton = (Button) view.findViewById(R.id.fragment_student_mine_quit);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.studentDao.deleteStudent(LoginActivity.studentStatic);
                LoginActivity.clearStaticResourse();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        registRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout_regist_face);
        registRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!LoginActivity.studentStatic.getStudentStatus().equals(Student.STATUS_AUTH_PASS))
                {
                    Intent intent = new Intent(getActivity(), RegistFaceActivity.class);
                    startActivityForResult(intent,FACE_REGIST_REQUEST_CODE);
                } else {
                    Toast.makeText(getActivity(),"已经通过认证，不能再进行人脸注册",Toast.LENGTH_LONG).show();
                }

            }
        });
        changeIngoRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout_mineInfo);
        changeIngoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentChangeInfoActivity.class);
                startActivityForResult(intent,CHANGE_INFO_REQUEST_CODE);
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

        stduentAddClassRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout_student_add_class);
        stduentAddClassRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginActivity.studentStatic.getStudentFacecode()==null||LoginActivity.studentStatic.getStudentFacecode().trim().equals("")||LoginActivity.studentStatic.getStudentStatus().equals(Student.STATUS_AUTH_FAIL))
                {
                    Intent intent = new Intent(getActivity(), RegistFaceActivity.class);
                    startActivityForResult(intent,FACE_REGIST_REQUEST_CODE);
                }else {
                    showInputDialog();
                }
            }
        });

        studentQueryAskLeaveInfoRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout_student_query_ask_leave_info);
        studentQueryAskLeaveInfoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentQueryLeaveInfoListActivity.class);
                startActivity(intent);
            }
        });



        schoolIDTextView = (TextView)view.findViewById(R.id.fragment_student_mine_school_id);
        schoolIDTextView.setText("学号："+LoginActivity.studentStatic.getStudentNo());
        studentName = (TextView)view.findViewById(R.id.fragment_student_mine_name);
        studentName.setText("姓名："+LoginActivity.studentStatic.getStudentName());
        headImageView = (CircleImageView) view.findViewById(R.id.fragment_student_mine_head_picture);
        if(LoginActivity.headPictureBitmap!=null)
        {
            headImageView.setImageBitmap(LoginActivity.headPictureBitmap);
        }
        return view;
    }



    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(LoginActivity.headPictureBitmap!=null)
        {
            headImageView.setImageBitmap(LoginActivity.headPictureBitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String dataStr = null;
        if(resultCode== Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case FACE_REGIST_REQUEST_CODE:
                    dataStr = data.getStringExtra("data_return");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                    showInputDialog();
                    break;
                case CHANGE_INFO_REQUEST_CODE:
                    dataStr = data.getStringExtra("data");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    DialogInterface.OnClickListener getOnclickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            classCode = addClassEditText.getText().toString();
            StudentAddClassNet studentAddClassNet = new StudentAddClassNet();
            studentAddClassNet.studentAddClass(getActivity(),LoginActivity.studentStatic.getStudentId(),classCode);
        }
    };

    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        addClassEditText = new EditText(getActivity());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(getActivity());
        inputDialog.setTitle("请输入班级编号").setView(addClassEditText);
        inputDialog.setPositiveButton("确定",
                getOnclickListener).show();
    }

}
