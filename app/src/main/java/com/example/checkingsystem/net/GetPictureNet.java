package com.example.checkingsystem.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.checkingsystem.LoginActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.BitmapUtil;

/**
 * Created by 那年.盛夏 on 2017/4/16.
 */

public class GetPictureNet {
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    public Bitmap bitmap = null;
    public interface HttpPictureCallbackListener {
        void onFinish(InputStream inputStream);
        void onError(Exception e);

    }

    public void getPicture(final String address, final HttpPictureCallbackListener httpPictureCallbackListener)
    {
        Log.e("test",address);

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e("test",Thread.currentThread()+"--");
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(address);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        InputStream in = connection.getInputStream();
                        if(httpPictureCallbackListener!=null)
                        {
                            httpPictureCallbackListener.onFinish(in);
                        }

                    } catch (Exception e) {
                        if(httpPictureCallbackListener!=null)
                        {
                            httpPictureCallbackListener.onError(e);
                        }

                    }finally {
                        if(connection!=null)
                        {
                            connection.disconnect();
                        }
                    }
                }
            });

    }

}
