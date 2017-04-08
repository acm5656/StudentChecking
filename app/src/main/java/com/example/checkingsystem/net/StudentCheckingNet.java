package com.example.checkingsystem.net;

import android.app.Activity;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/4/6.
 */

public class StudentCheckingNet {
    private Activity activity;
    public void studentOpenChecking(String path,Activity activity,String courseID, List<String> arrayList,String macAdrress)
    {
        this.activity = activity;

    }
}
