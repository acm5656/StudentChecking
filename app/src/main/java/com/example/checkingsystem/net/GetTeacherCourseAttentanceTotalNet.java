package com.example.checkingsystem.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ItemCountGroupByCidAndIStatus;
import com.example.checkingsystem.entity.ResultObj;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/3.
 */

public class GetTeacherCourseAttentanceTotalNet {
    static final int ERROR = 0;
    static final int SUCCESS = 1;

    static HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        Message message = new Message();
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ResultObj<List<ItemCountGroupByCidAndIStatus>> resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<ItemCountGroupByCidAndIStatus>>>() {});
                if(resultObj.getMeta().getResult())
                {
                    List<ItemCountGroupByCidAndIStatus> list = resultObj.getData();
                    for(int i =0 ; i<list.size() ;i++)
                    {
                        for(int j = 0 ;j<LoginActivity.teacherCourseShow.size();j++)
                        {
                            if(list.get(i).getCourseId().equals(LoginActivity.teacherCourseShow.get(j).getDbID()))
                            {
                                if(list.get(i).getItemStatus().equals("attendance"))
                                {
                                    LoginActivity.teacherCourseShow.get(j).setCourseAttentanceCount(new Integer(list.get(i).getCount()).toString());
                                }
                                if(list.get(i).getItemStatus().equals("leave"))
                                {
                                    LoginActivity.teacherCourseShow.get(j).setCourseAskForLeaveCount(new Integer(list.get(i).getCount()).toString());
                                }
                                if(list.get(i).getItemStatus().equals("absence"))
                                {
                                    LoginActivity.teacherCourseShow.get(j).setCourseAbsentCount(new Integer(list.get(i).getCount()).toString());
                                }
                                if(list.get(i).getItemStatus().equals("late"))
                                {
                                    LoginActivity.teacherCourseShow.get(j).setCourseLateCount(new Integer(list.get(i).getCount()).toString());
                                }

                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
        }
    };
    public static void getTeacherCourseAttentanceTotal() {
        String url = HttpUtil.urlIp+ PathUtil.TEACHER_GET_EVERT_COURSE_ATTENTANCE_TOTAL;
        List<String> courseIDList = new ArrayList<>();
        String data = "";
        for(int i = 0; i< LoginActivity.teacherCourseShow.size(); i++)
        {
            data+=LoginActivity.teacherCourseShow.get(i).getDbID();
            if(i!=LoginActivity.teacherCourseShow.size()-1)
                data+=",";
        }
        HttpUtil.sendHttpGetRequest(url+"?"+"courseIds="+data,httpCallbackListener);

    }
}
