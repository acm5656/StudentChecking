package util;

import android.util.Log;

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

    public static String urlIp="http://192.168.191.1:80/";
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
                    Log.e("httpUtil","------------1");
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    if(status==CONTENT_TYPE_IS_APPLICATION_JSON)
                        connection.setRequestProperty("Content-Type","application/json");
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    Log.e("httpUtil","------------2");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(data);
                    InputStream in = connection.getInputStream();
                    Log.e("httpUtil","------------3");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder response = new StringBuilder();
                    Log.e("httpUtil","------------4");
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
                        Log.e("test",e.getMessage());
                        Log.e("test",e.toString());
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
                    Log.e("httpUtil","------------1");
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    if(status==CONTENT_TYPE_IS_APPLICATION_JSON)
                        connection.setRequestProperty("Content-Type","application/json");
                    connection.setRequestMethod("PUT");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    Log.e("httpUtil","------------2");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(data);
                    InputStream in = connection.getInputStream();
                    Log.e("httpUtil","------------3");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder response = new StringBuilder();
                    Log.e("httpUtil","------------4");
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
                        Log.e("error",e.getMessage());
                        Log.e("error",e.toString());
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
