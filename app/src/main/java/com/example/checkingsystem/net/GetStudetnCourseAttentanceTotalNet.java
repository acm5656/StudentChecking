package com.example.checkingsystem.net;

import android.content.Context;
import android.util.Log;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ItemCountGroupByCidAndIStatus;
import com.example.checkingsystem.entity.ResultObj;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/1.
 */

public class GetStudetnCourseAttentanceTotalNet {
    static HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultObj<List<ItemCountGroupByCidAndIStatus>> itemCountGroupByCidAndIStatusResultObjList = null;
            try {
                itemCountGroupByCidAndIStatusResultObjList = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<ItemCountGroupByCidAndIStatus>>>() {});
                List<ItemCountGroupByCidAndIStatus> list = itemCountGroupByCidAndIStatusResultObjList.getData();
                for(int i =0 ; i<list.size() ;i++)
                {
                    for(int j = 0 ;j<LoginActivity.studentCourseShow.size();j++)
                    {
                        if(list.get(i).getCourseId().equals(LoginActivity.studentCourseShow.get(j).getDbID()))
                        {
                            if(list.get(i).getItemStatus().equals("attendance"))
                            {
                                LoginActivity.studentCourseShow.get(j).setCourseAttentanceCount(new Integer(list.get(i).getCount()).toString());
                            }
                            if(list.get(i).getItemStatus().equals("leave"))
                            {
                                LoginActivity.studentCourseShow.get(j).setCourseAskForLeaveCount(new Integer(list.get(i).getCount()).toString());
                            }
                            if(list.get(i).getItemStatus().equals("absence"))
                            {
                                LoginActivity.studentCourseShow.get(j).setCourseAbsentCount(new Integer(list.get(i).getCount()).toString());
                            }
                            if(list.get(i).getItemStatus().equals("late"))
                            {
                                LoginActivity.studentCourseShow.get(j).setCourseLateCount(new Integer(list.get(i).getCount()).toString());
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
    public static void getStudentCourseAttentanceTotal(){
        String url = HttpUtil.urlIp+ PathUtil.STUDENT_GET_COURSE_ATTENTANCE_TOTAL;
        String date = LoginActivity.studentStatic.getStudentId();
        HttpUtil.sendHttpGetRequest(url+"/"+date, httpCallbackListener);
    }
}
