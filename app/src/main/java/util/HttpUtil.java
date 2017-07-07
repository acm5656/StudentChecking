package util;

import android.util.Log;

import com.tencent.cos.utils.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 那年.盛夏 on 2017/3/7.
 */

public class HttpUtil {

    public static String urlIp="http://123.206.175.47:80/";
//    public static String urlIp="http://localhost:80/";
//    public static String urlIp="http://192.168.43.158/";
//    public static String urlIp="http://172.20.10.2/";
//    public static String urlIp="http://10.19.207.36/";
    public static final int CONTENT_TYPE_IS_APPLICATION_JSON = 1;
    public static final int NO_STATUS = 0;

    public static void sendHttpGetRequest(final String address,final HttpCallbackListener listener)
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
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line=reader.readLine())!=null)
                    {
                        response.append(line);
                    }
                    Log.e("get",response.toString());
                    if(listener!=null&&(response.toString()!=null)&&(!response.toString().equals("")))
                    {
                        listener.onFinish(response.toString());
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

    public static void sendHttpPostRequest(final String address, final HttpCallbackListener listener, final String data, final int status)
    {
        Log.e("httpUtil_data",data);
        Log.e("httpUtil_url",address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    if(status==CONTENT_TYPE_IS_APPLICATION_JSON)
                        connection.setRequestProperty("Content-Type","application/json");

                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.write(data.getBytes());
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line=reader.readLine())!=null)
                    {
                        response.append(line);
                    }
                    Log.e("result",response.toString());

                    if(listener!=null&&(response.toString()!=null)&&(!response.toString().equals("")))
                    {
                        listener.onFinish(response.toString());
                    }

                } catch (Exception e) {
                    if(listener!=null)
                    {
                        Log.e("test",e.toString());
                        Log.e("test",e.getMessage());

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

    public static void sendHttpPutRequest(final String address, final HttpCallbackListener listener, final String data, final int status)
    {
        Log.e("httpUtil_data",data);
        Log.e("httpUtil_url",address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    if(status==CONTENT_TYPE_IS_APPLICATION_JSON)
                        connection.setRequestProperty("Content-Type","application/json");
                    connection.setRequestMethod("PUT");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.write(data.getBytes());
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line=reader.readLine())!=null)
                    {
                        response.append(line);
                    }
                    Log.e("result",response.toString());

                    if(listener!=null&&(response.toString()!=null)&&(!response.toString().equals("")))
                    {
                        listener.onFinish(response.toString());
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
