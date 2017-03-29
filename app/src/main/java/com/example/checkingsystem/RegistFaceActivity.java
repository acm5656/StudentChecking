package com.example.checkingsystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;


import com.example.checkingsystem.entity.Picture;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentFacecode;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.iflytek.cloud.FaceDetector;
import com.iflytek.cloud.FaceRequest;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.util.Accelerometer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import util.ActivityColectorUtil;
import util.BitmapUtil;
import util.ChangeTypeUtil;
import util.CosUtil;
import util.FaceRect;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.ParseResult;
import util.PathUtil;
import util.SurfaceViewCircle;

public class RegistFaceActivity extends AppCompatActivity {
    private SurfaceViewCircle mPreviewSurface;
    private SurfaceView mFaceSurface;
    private Camera mcamera;

    private int mCameraID = Camera.CameraInfo.CAMERA_FACING_FRONT;

    private int extendSize = 1;
    private int PREVIEW_WIDTH = 640 * extendSize;
    private int PREVIEW_HEIGHT = 480 * extendSize;

    private String authid;

//    private int PREVIEW_WIDTH ;
//    private int PREVIEW_HEIGHT ;

    private byte[] nv21;
    private byte[] buffer;

    private Matrix mScaleMatrix = new Matrix();

    private Accelerometer mAcc;

    private FaceDetector mFaceDetector;

    private FaceRequest mFaceRequest;

    private boolean mStopTrack;

    private int isAlign = 0;

    private boolean isGetImage = false;

    private boolean isVerfiFace = false;

    private Bitmap bmp = null;
    private String name = null;
    private String uuid = null;

    HttpCallbackListener httpCallbackListenerPicture = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            String data = (String) resultObj.getData();
            Log.e("test","response:   "+response);
            if(resultObj.getMeta().getResult()) {
                Log.e("test---","face----do");
                StudentFacecode facecode = new StudentFacecode();
                facecode.setStudentFacecodeStuId(LoginActivity.studentStatic.getStudentId());
                facecode.setStudentFacecodePicId(data);
                facecode.setStudentFacecode(uuid);
                String path = HttpUtil.urlIp+ PathUtil.STUDENT_FACE_CODE;
                Log.e("test---","data：  "+ChangeTypeUtil.getJSONString(facecode));
                HttpUtil.sendHttpPostRequest(
                        path,
                        httpCallbackListenerFaceCode,
                        ChangeTypeUtil.getJSONString(facecode),
                        HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON
                );
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    HttpCallbackListener httpCallbackListenerFaceCode = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            Log.e("test---",response);
            if(resultObj.getMeta().getResult()) {
                LoginActivity.studentStatic.setStudentFacecode(resultObj.getData().toString());
                Intent intent = new Intent(RegistFaceActivity.this, StudentIndexActivity.class);
                intent.putExtra("data_return", "恭喜你注册成功");
                setResult(RESULT_OK, intent);
                finish();
            }
            else {
                Toast.makeText(RegistFaceActivity.this,"注册失败，请稍后重试",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(Exception e) {
            Log.e("test---",e.getMessage());
            Log.e("test---",e.toString());
        }
    };

    private RequestListener mRequestListener = new RequestListener() {

        @Override
        public void onEvent(int eventType, Bundle params) {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            String result = null;
            try {
                result = new String(buffer, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String type = object.optString("sst");
            if("reg".equals(type))
            {
                try {
                    regist(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onCompleted(SpeechError error) {

            Log.e("RegistFaceActivity---","------------onCompleted");
            if(error == null)
            {
                Log.e("RegistFaceActivity---","------------error null");
            }
            else {
                isVerfiFace = false;
                Log.e("RegistFaceActivity---",new Integer(error.getErrorCode()).toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_face);
        ActivityColectorUtil.addActivity(this);
        uuid = "a"+UUID.randomUUID().toString().substring(0,15);
        uuid = uuid.replaceAll("-","_");
        Log.e("test","uuid-----"+uuid);
        SpeechUtility.createUtility(RegistFaceActivity.this, SpeechConstant.APPID +"=587f2efc");
        mFaceDetector = FaceDetector.createDetector(this, null);
        mFaceRequest = new FaceRequest(this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        PREVIEW_WIDTH = metrics.widthPixels;
        authid = uuid;

        PREVIEW_HEIGHT = metrics.heightPixels;

        initUI();

        buffer = new byte[PREVIEW_WIDTH * PREVIEW_HEIGHT];

        mAcc = new Accelerometer(RegistFaceActivity.this);


    }

    private SurfaceHolder.Callback mPriviewCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            openCamera();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mScaleMatrix.setScale(width/(float)PREVIEW_HEIGHT,height/(float)PREVIEW_WIDTH);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            closeCamera();
        }
    };

    private void initUI() {
        mPreviewSurface = (SurfaceViewCircle) findViewById(R.id.reg_sfv_preview);

        mPreviewSurface.getHolder().addCallback(mPriviewCallback);

    }



    private void closeCamera() {
        if (null != mcamera) {
            mcamera.setPreviewCallback(null);
            mcamera.stopPreview();
            mcamera.release();
            mcamera = null;
        }

    }

    private void openCamera() {
        if(mcamera!=null)
        {
            return;
        }

        if(!checkCameraPermission())
        {
            mStopTrack = true;
            return;
        }


        mcamera = Camera.open(mCameraID);

        Camera.Parameters params = mcamera.getParameters();

        params.setPreviewFormat(ImageFormat.NV21);
//        params.setPictureSize(PREVIEW_WIDTH,PREVIEW_HEIGHT);

        mcamera.setParameters(params);

        mcamera.setDisplayOrientation(90);
        mcamera.setPreviewCallback(new Camera.PreviewCallback() {

            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                if(nv21==null)
                {
                    nv21 = new byte[data.length];
                }
                if(!isGetImage&&(!isVerfiFace))
                {
                    System.arraycopy(data, 0, nv21, 0, data.length);
                    Log.e("RegistFaceActivity---","---------------获取图像");
                    isGetImage = true;
                }

                if(isGetImage&&(!isVerfiFace))
                {

                    Log.e("RegistFaceActivity---","---------------获取图像成功");
                    Camera.Size size = mcamera.getParameters().getPreviewSize();
                        YuvImage image = new YuvImage(nv21, ImageFormat.NV21, size.width,
                                size.height, null);
                        if (image != null) {

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            image.compressToJpeg(new Rect(0, 0, size.width, size.height),
                                    80, stream);
                            bmp = BitmapFactory.decodeByteArray(
                                    stream.toByteArray(), 0, stream.size());

                            bmp = BitmapUtil.zoomBitmap(bmp,size.width/2,size.height/2);

                            bmp = BitmapUtil.rotateBitmapByDegree(bmp, -90);

                            byte[] mface = BitmapUtil.Bitmap2Bytes(bmp);

                            String result = mFaceDetector.detectARGB(bmp);
                            Log.e("RegistFaceActivity---", "result:"+result);
                            FaceRect[] faceRect = ParseResult.parseResult(result);
                            if(faceRect.length!=0) {
                                isVerfiFace = true;
                                mFaceRequest.setParameter(SpeechConstant.AUTH_ID, authid);
                                mFaceRequest.setParameter(SpeechConstant.WFR_SST, "reg");
                                mFaceRequest.sendRequest(mface, mRequestListener);
                            }

                            isGetImage = false;
                        }
                }

            }
        });

        try {
            mcamera.setPreviewDisplay(mPreviewSurface.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mcamera.startPreview();

    }

    private boolean checkCameraPermission()
    {
        int status = checkPermission(Manifest.permission.CAMERA, Process.myPid(), Process.myUid());
        if (PackageManager.PERMISSION_GRANTED == status) {
            return true;
        }
        return false;
    }

    private void regist(JSONObject obj) throws JSONException {
        int ret = obj.getInt("ret");
        if (ret != 0) {
            isVerfiFace = false;
            showTip("注册失败");
            return;
        }
        if ("success".equals(obj.get("rst"))) {
            name = uuid+".jpg";
            if(bmp!=null) {
                BitmapUtil.saveMyBitmap(bmp,name);
            }
            bmp.recycle();


            CosUtil.upLoad(Environment.getExternalStorageDirectory().getPath()+ LoginActivity.path,name ,getApplicationContext());
            String url = HttpUtil.urlIp + PathUtil.SAVE_PICTURE;
            Picture picture = new Picture();
            picture.setPictureName(name);
            picture.setPictureUrl(CosUtil.urlFace+name);
//            HttpUtil.sendHttpPostRequest(url,httpCallbackListenerPicture, "picture="+ChangeTypeUtil.getJSONString(picture));
            HttpUtil.sendHttpPostRequest(url,httpCallbackListenerPicture,ChangeTypeUtil.getJSONString(picture),HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
        } else {
            isVerfiFace = false;
            showTip("注册失败");
        }
    }

    private void showTip(final String str) {
        Toast mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setText(str);
        mToast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityColectorUtil.removeActivity(this);
    }

}
