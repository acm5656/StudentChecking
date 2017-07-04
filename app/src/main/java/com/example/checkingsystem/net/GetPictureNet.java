package com.example.checkingsystem.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.checkingsystem.LoginActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import util.BitmapUtil;

/**
 * Created by 那年.盛夏 on 2017/4/16.
 */

public class GetPictureNet {
    public Bitmap bitmap = null;
    public interface HttpPictureCallbackListener {
        void onFinish(InputStream inputStream);
        void onError(Exception e);

    }

    public void getPicture(final String address, final HttpPictureCallbackListener httpPictureCallbackListener)
    {
            new Thread(new Runnable() {
                @Override
                public void run() {
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
            }).start();

    }

}
