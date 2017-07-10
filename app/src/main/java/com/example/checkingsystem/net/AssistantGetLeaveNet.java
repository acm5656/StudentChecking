package com.example.checkingsystem.net;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/9.
 */

public class AssistantGetLeaveNet {
    public void assistantGetLeave(String assistantID, HttpCallbackListener httpCallbackListener)
    {
        String url = HttpUtil.urlIp + PathUtil.ASSISTANT_GET_ALL_COURSE_LEAVE;
        String data = assistantID;
        HttpUtil.sendHttpGetRequest(url+"?assistantId="+data,httpCallbackListener);
    }
}
