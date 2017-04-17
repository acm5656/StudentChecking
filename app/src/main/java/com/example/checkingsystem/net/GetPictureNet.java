package com.example.checkingsystem.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.checkingsystem.LoginActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import util.BitmapUtil;
import util.HttpCallbackListener;
import util.HttpUtil;

/**
 * Created by 那年.盛夏 on 2017/4/16.
 */

public class GetPictureNet {
    public interface HttpPictureCallbackListener {
        void onFinish(InputStream inputStream);
        void onError(Exception e);

    }
    static HttpPictureCallbackListener listener = new HttpPictureCallbackListener() {
        @Override
        public void onFinish(InputStream inputStream) {
            LoginActivity.headPictureBitmap = BitmapFactory.decodeStream(inputStream);

            BitmapUtil.saveMyBitmap(LoginActivity.headPictureBitmap,"head1.jpg");
        }

        @Override
        public void onError(Exception e) {

        }
    };

    static void getPicture(final String address)
    {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(address);
                        Log.e("get",address);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        Log.e("get","---------1");
                        InputStream in = connection.getInputStream();
                        if(listener!=null)
                        {
                            listener.onFinish(in);
                        }

                    } catch (Exception e) {
                        if(listener!=null)
                        {
                            listener.onError(e);
                        }

                    }finally {
                        if(connection!=null)
                        {
                            connection.disconnect();
                        }
                    }
                }
            }).start();

    }

}
