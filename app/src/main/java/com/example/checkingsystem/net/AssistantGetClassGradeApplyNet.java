package com.example.checkingsystem.net;

import android.app.Activity;

import com.example.checkingsystem.LoginActivity;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/10.
 */

public class AssistantGetClassGradeApplyNet {
    public void assistantGetClassGradeApply(HttpCallbackListener httpCallbackListener)
    {
        String url = HttpUtil.urlIp+ PathUtil.ASSISTANT_GET_STUDENT_APPLY_CLASS;
        String data = "assistantId="+ LoginActivity.assistantStatic.getAssistantId();
        HttpUtil.sendHttpGetRequest(url+"?"+data,httpCallbackListener);
    }

}
