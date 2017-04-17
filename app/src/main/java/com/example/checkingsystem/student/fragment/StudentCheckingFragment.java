package com.example.checkingsystem.student.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.VerifyFaceActivity;
import com.example.checkingsystem.entity.AuthorityInfo;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.StudentCourseTimeTable;
import com.example.checkingsystem.student.activity.StudentCheckingActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class StudentCheckingFragment extends Fragment implements View.OnClickListener {

    public static final int TRUE = 1;
    public static final int FALSE = 0;
    private OnFragmentInteractionListener mListener;
    private View view;
    private Button verfiButton;
    private Button checkingButton;
    public final static int FACE_VERIFY_RESULT = 1;
    private BluetoothReceiver bluetoothReceiver;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice device;
    private ArrayList<String> pairedMaclist;
    private boolean doQuery = true;
    private ProgressDialog progressDialog;
    private boolean sendMac = false;
    private String macAddress;
    private int i = 0;
    private String studentID;
    EditText editText ;
    ObjectMapper objectMapper = new ObjectMapper();
    ResultObj<Student> resultObj;
    private String studentFaceID;
    IntentFilter intentFileter;
    private TextView weekTextView;
    private TextView dayTextView;
    private TextView courseNameTextView;
    private String courseID;

    Handler handlerStuID = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case TRUE:
                    doQuery = true;
                    sendMac = false;
                    getCheckingAuthority();
                    break;
                case FALSE:
                    Toast.makeText(getContext(), "操作失败请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {

            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<Student>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                message.what = TRUE;
                studentID = resultObj.getData().getStudentId();
                studentFaceID = resultObj.getData().getStudentFacecode();
                handlerStuID.sendMessage(message);
            }else
            {
                Message message = new Message();
                message.what = FALSE;
                handlerStuID.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {

            Message message = new Message();
            message.what = FALSE;
            handlerStuID.sendMessage(message);
        }
    };
    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            studentID = editText.getText().toString();
            String path = HttpUtil.urlIp+PathUtil.STUDENT_GET_STUDENT_ID;
            String data = "studentNo="+studentID;
            HttpUtil.sendHttpGetRequest(path+"?"+data,httpCallbackListener);

        }
    };
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case TRUE:
                    doQuery = false;
                    progressDialog.dismiss();
                    startVerifyFace();
                    break;
                case FALSE:
                    Toast.makeText(getContext(),"未获得考勤权限，请确保周围有已授权设备",Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    };

    HttpCallbackListener httpCallbackListenerGetAuthority = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            if(resultObj.getMeta()==null)
                Log.e("test","meta = null");
            if(resultObj.getMeta().getResult()==null)
                Log.e("test","result = null");

            if(resultObj.getMeta().getResult()) {
                Log.e("test","进入考勤");
                Message message = new Message();
                handler.sendMessage(message);

            }else {
                sendMac = false;
            }
        }

        @Override
        public void onError(Exception e) {
            sendMac = false;
            Log.e("test","error");
        }
    };

    class BluetoothReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                //得到intent里面的信息
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //System.out.println(device.getAddress());
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress()+"\n"+intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI));
                if((intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI)+100)>=25)
                {
                    pairedMaclist.add(device.getAddress());
                }

                Log.e("test",device.getAddress()+"/"+intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI));
//                list.setAdapter(mArrayAdapter);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_checking, container, false);
        verfiButton = (Button)view.findViewById(R.id.fragment_student_checking_button);
        verfiButton.setOnClickListener(this);
        checkingButton = (Button)view.findViewById(R.id.fragment_student_help_student_checking_button);
        checkingButton.setOnClickListener(this);
        courseID = ((StudentCheckingActivity)getActivity()).courseID;
        intentFileter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        bluetoothReceiver = new BluetoothReceiver();
        weekTextView = (TextView) view.findViewById(R.id.fragment_student_checking_week);
        dayTextView = (TextView)view.findViewById(R.id.fragment_student_checking_day);
        courseNameTextView = (TextView)view.findViewById(R.id.fragment_student_checking_name);

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

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fragment_student_checking_button:
                doQuery =true;
                sendMac = false;
                getCheckingAuthority();
                studentID = LoginActivity.studentStatic.getStudentId();
                studentFaceID = LoginActivity.studentStatic.getStudentFacecode();
                break;
            case R.id.fragment_student_help_student_checking_button:
                showInputDialog();

                break;
        }
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case FACE_VERIFY_RESULT:
                if(resultCode == getActivity().RESULT_OK) {
                    String dataStr = data.getStringExtra("data_return");
                    Log.e("toast","---------------2");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    public void startVerifyFace()
    {
        Intent intent = new Intent(getActivity(), VerifyFaceActivity.class);
        intent.putExtra("studentID",studentID);
        intent.putExtra("mac",macAddress);
        intent.putExtra("studentFaceID",studentFaceID);
        Log.e("test",studentID+" "+studentFaceID);
        startActivityForResult(intent,FACE_VERIFY_RESULT);
    }
    private void openBluetooth() {
        //bluetoothAdapter.enable();
        if(bluetoothAdapter != null){
            if(!bluetoothAdapter.isEnabled()){
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(intent);
            }
//            Log.e("test_local",MacUtil.getBtAddressViaReflection());
            String macAddress = android.provider.Settings.Secure.getString(getActivity().getContentResolver(), "bluetooth_address");
            Log.e("test_local",macAddress);
            Log.e("test_local",bluetoothAdapter.getName());
            //得到所有已经被对的蓝牙适配器对象
            Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();

            if(devices.size() > 0){
                for(Iterator<BluetoothDevice> iterator = devices.iterator(); iterator.hasNext();){
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
                    //得到远程蓝牙设备的地址
                    System.out.println(bluetoothDevice.getAddress());
                }
            }

        }
        else {
            //System.out.println("没有蓝牙设备");
            Toast.makeText(getContext(), "没有蓝牙设备",Toast.LENGTH_SHORT).show();
        }
    }

    public void getCheckingAuthority()
    {
        getActivity().registerReceiver(bluetoothReceiver, intentFileter);
        openBluetooth();
        progressDialog = ProgressDialog.show(getActivity(),"考勤获权", "请稍等，确保一米内有已经获权的设备", true, false);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(doQuery) {
                    if(!sendMac) {
                        long beginTime = System.currentTimeMillis();
                        bluetoothAdapter.startDiscovery();
                        pairedMaclist = new ArrayList<String>();
                        long endTime = System.currentTimeMillis();
                        while (endTime - beginTime < 3000) {

                            endTime = System.currentTimeMillis();
                        }
                        i++;
                        bluetoothAdapter.cancelDiscovery();
                        if(Build.VERSION.SDK_INT >= 23) {
                            macAddress = android.provider.Settings.Secure.getString(getActivity().getContentResolver(), "bluetooth_address");

                        }else {
                            macAddress = bluetoothAdapter.getAddress();
                        }
                        String path = HttpUtil.urlIp+ PathUtil.STUDENT_GET_CHECKING_AUTHORITY;
                        AuthorityInfo authorityInfo = new AuthorityInfo();
                        authorityInfo.setMacList(pairedMaclist);
                        authorityInfo.setStuMac(macAddress);
                        authorityInfo.setCourseAttendanceCourseTimeId(courseID);

                        String data = ChangeTypeUtil.getJSONString(authorityInfo);
                        HttpUtil.sendHttpPostRequest(path,httpCallbackListenerGetAuthority,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
                        sendMac = true;
                        if(i>20)
                        {
                            doQuery = false;
                            progressDialog.dismiss();
                            Message mesage = new Message();
                            mesage.what = FALSE;
                            handler.sendMessage(mesage);
                        }
                    }
                }
            }
        }).start();
    }
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        editText = new EditText(getContext());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(getContext());
        inputDialog.setTitle("请输入学号").setView(editText);
        inputDialog.setPositiveButton("确定",
                onClickListener).show();
    }



}
