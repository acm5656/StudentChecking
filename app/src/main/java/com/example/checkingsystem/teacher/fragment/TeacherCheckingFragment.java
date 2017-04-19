package com.example.checkingsystem.teacher.fragment;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.TeacherCourseTimeTable;
import com.example.checkingsystem.net.OpenCheckingNet;
import com.example.checkingsystem.teacher.activity.TeacherCheckingActivity;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.PickerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeacherCheckingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TeacherCheckingFragment extends Fragment implements View.OnClickListener{

    private BluetoothAdapter bluetoothAdapter;
    private OnFragmentInteractionListener mListener;
    private Button checkingButton;
    private View view;

    private PickerView pickerView;
    private int attentanceTime = 3;
    private String macAddress;
    private TextView weekTextView;
    private TextView dayTextView;
    private TextView courseNameTextView;
    private String courseID;

    public TeacherCheckingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        courseID = ((TeacherCheckingActivity)getActivity()).courseID;
        view = inflater.inflate(R.layout.fragment_teacher_checking, container, false);
        initSource();

        return view;
    }

    private void initSource() {
        checkingButton = (Button)view.findViewById(R.id.fragment_teacher_checking_start_checking);
        weekTextView = (TextView)view.findViewById(R.id.fragment_teacher_checking_week);
        dayTextView = (TextView)view.findViewById(R.id.fragment_teacher_checking_course_class);
        courseNameTextView = (TextView)view.findViewById(R.id.fragment_teacher_checking_course_name);
        for(TeacherCourseTimeTable teacherCourseTimeTable:LoginActivity.teacherCourseTimeTableList)
        {
            if(teacherCourseTimeTable.getCourseTimeId().equals(courseID))
            {
                weekTextView.setText("第"+teacherCourseTimeTable.getCourseTimeWeek()+"周");
                courseNameTextView.setText(teacherCourseTimeTable.getCourseName());
                String classTime = TeacherScheduleFragment.getClassTime(teacherCourseTimeTable.getCourseTimeGmtBegin());
                dayTextView.setText("周"+teacherCourseTimeTable.getCourseTimeDay()+"第"+classTime+"节");
            }
        }
        checkingButton.setOnClickListener(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        pickerView = (PickerView)view.findViewById(R.id.minute_pv);
        List<String> data = new ArrayList<String>();
        for (int i = 1; i < 6; i++)
        {
            data.add("0" + i);
        }
        pickerView.setData(data);
        pickerView.setOnSelectListener(new PickerView.onSelectListener()
        {

            @Override
            public void onSelect(String text)
            {
                attentanceTime = new Integer(text);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fragment_teacher_checking_start_checking:
                if(!bluetoothAdapter.isEnabled()){
                    Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,0);//设置持续时间（最多300秒）
                    startActivity(discoveryIntent);

                }

                if(Build.VERSION.SDK_INT >= 23) {
                    macAddress = android.provider.Settings.Secure.getString(getActivity().getContentResolver(), "bluetooth_address");

                }else {
                    macAddress = bluetoothAdapter.getAddress();
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis()+attentanceTime*1000*60);

                String teacherID = LoginActivity.teacherStatic.getTeacherId();
                OpenCheckingNet openCheckingNet = new OpenCheckingNet();
                openCheckingNet.OpenTeacherChecking(getActivity(),macAddress,courseID,teacherID,new Long(timestamp.getTime()).toString());
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void setDiscoverableTimeout(int timeout) {
        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode =BluetoothAdapter.class.getMethod("setScanMode", int.class,int.class);
            setScanMode.setAccessible(true);

            setDiscoverableTimeout.invoke(adapter, timeout);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE,timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
