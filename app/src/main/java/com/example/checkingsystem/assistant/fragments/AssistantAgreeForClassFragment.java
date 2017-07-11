package com.example.checkingsystem.assistant.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.AssistantAgreeForClassItemAdapter;
import com.example.checkingsystem.assistant.activity.AssistantAgreeForClassActivity;
import com.example.checkingsystem.entity.ClassGrade;
import com.example.checkingsystem.entity.ClassGradeShow;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.net.AssistantGetClassGradeApplyNet;
import com.example.checkingsystem.net.GetPictureNet;
import com.example.checkingsystem.net.GetStudentInfoByID;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantAgreeForClassFragment extends Fragment{
    final static int ASSISTANT_AGREE_FOR_CLASS = 1;
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    private View view;
    private ListView listView;
    List<ClassGradeShow> classGradeShowList;
    public static ClassGradeShow classGradeShow;
    ObjectMapper objectMapper = new ObjectMapper();
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            updateUI();
        }
    };
    HttpCallbackListener getStudenInfoHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj<List<Student>> resultObj = null;
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<Student>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj!=null&&resultObj.getMeta().getResult())
            {

                for(int i = 0;i<resultObj.getData().size() ;i++)
                {
                    for(int j = 0;j<classGradeShowList.size();j++) {
                        if (resultObj.getData().get(i).getStudentId().equals(classGradeShowList.get(j).getClassGrade().getClassGradeStudentId()))
                        {
                            classGradeShowList.get(j).setStudent(resultObj.getData().get(i));
                        }
                    }
                }
                LoginActivity.classGradeShowList = classGradeShowList;
                for(int i = 0 ;i<classGradeShowList.size() ;i++)
                {
                    GetPictureNet getPictureNet = new GetPictureNet();
                    final int finalI = i;
                    getPictureNet.getPicture(classGradeShowList.get(i).getStudent().getStudentHeadimageUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                        @Override
                        public void onFinish(InputStream inputStream) {
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            LoginActivity.classGradeShowList.get(finalI).setBitmap(bitmap);
                            Message message = new Message();
                            message.what = SUCCESS;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }
                Message message = new Message();
                message.what = SUCCESS;
                handler.sendMessage(message);
            }else {
                Message message = new Message();
                message.what = FAIL;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FAIL;
            handler.sendMessage(message);
        }
    };
    HttpCallbackListener getClassGradehttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj<List<ClassGrade>> resultObj = null;
            try {
                 resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<ClassGrade>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj!=null&&resultObj.getMeta().getResult())
            {
                List<String> studentIDList = new ArrayList<>();
                classGradeShowList = new ArrayList<>();
                for(int i = 0 ;i<resultObj.getData().size() ;i++)
                {
                    ClassGradeShow classGradeShow = new ClassGradeShow();
                    classGradeShow.setClassGrade(resultObj.getData().get(i));
                    studentIDList.add(resultObj.getData().get(i).getClassGradeStudentId());
                    classGradeShowList.add(classGradeShow);
                }
                GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                getStudentInfoByID.teacherGetStudentInfoByID(getStudenInfoHttpCallbackListener,studentIDList);
            }else {
                Message message = new Message();
                message.what = FAIL;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FAIL;
            handler.sendMessage(message);
        }
    };
    /**
     * listView的适配器
     */
    private AssistantAgreeForClassItemAdapter assistantAgreeForClassItemAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_assistant_agree_for_course, container, false);
        initItem();
        AssistantGetClassGradeApplyNet assistantGetClassGradeApplyNet = new AssistantGetClassGradeApplyNet();
        assistantGetClassGradeApplyNet.assistantGetClassGradeApply(getClassGradehttpCallbackListener);

        return view;
    }

    private void initItem(){
        listView = (ListView) view.findViewById(R.id.lv_fragment_assistant_agree_for_course);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AssistantAgreeForClassActivity.class);
                classGradeShow = classGradeShowList.get(position);
                startActivityForResult(intent,ASSISTANT_AGREE_FOR_CLASS);
            }
        });
    }
    public void updateUI()
    {
        assistantAgreeForClassItemAdapter = new AssistantAgreeForClassItemAdapter(getActivity(),LoginActivity.classGradeShowList,R.layout.item_assistant_agree_for_class);
        listView.setAdapter(assistantAgreeForClassItemAdapter);
    }

}
