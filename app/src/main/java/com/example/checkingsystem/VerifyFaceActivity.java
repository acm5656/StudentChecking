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
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.student.fragment.StudentCheckingFragment;
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
import java.util.concurrent.atomic.AtomicBoolean;

import util.ActivityColectorUtil;
import util.BitmapUtil;
import util.FaceRect;
import util.ParseResult;

public class VerifyFaceActivity extends AppCompatActivity {
    private SurfaceView mPreviewSurface;
    private SurfaceView mFaceSurface;
    private android.hardware.Camera mcamera;

    private int mCameraID = android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

    private int extendSize = 1;
    private int PREVIEW_WIDTH = 640 * extendSize;
    private int PREVIEW_HEIGHT = 480 * extendSize;

    private String authid;

//    private int PREVIEW_WIDTH ;
//    private int PREVIEW_HEIGHT ;

    private byte[] nv21 = null;
    private byte[] buffer;

    private Matrix mScaleMatrix = new Matrix();

    private Accelerometer mAcc;

    private FaceDetector mFaceDetector;

    private FaceRequest mFaceRequest;

    private boolean mStopTrack;

    private int isAlign = 0;

    private AtomicBoolean isGetImage = new AtomicBoolean(false);

    private int i = 0;

    private AtomicBoolean isVerfiFace = new AtomicBoolean(false);

    private boolean runBool = false;
    private Thread thread;

    private long starTime;
    private long endTime;

    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void finish() {
        runBool = true;
        endTime = System.currentTimeMillis();
        while (((endTime-starTime)/1000)<1)
        {
            endTime = System.currentTimeMillis();
        }
        closeCamera();
        super.finish();
    }

    private RequestListener mRequestListener = new RequestListener() {

        @Override
        public void onEvent(int eventType, Bundle params) {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {

            Log.d("VerifyFaceActivity---","------------onBufferReceived1");
            String result = null;
            try {
                result = new String(buffer, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.d("VerifyFaceActivity---","------------onBufferReceived2");

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String type = object.optString("sst");
            if("verify".equals(type))
            {
                try {
                    verify(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onCompleted(SpeechError error) {

            if(error != null)
            {
                isVerfiFace.set(false);
                isGetImage.set(false);
                Log.e("boolen","-4--isGetImage-"+isGetImage.get()+"---isVerfiFace-"+isVerfiFace.get());
                Log.e("VerifyFaceActivity---",error.getMessage());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_face);
        ActivityColectorUtil.addActivity(this);
        SpeechUtility.createUtility(VerifyFaceActivity.this, SpeechConstant.APPID +"=587f2efc");
        mFaceDetector = FaceDetector.createDetector(this, null);
        mFaceRequest = new FaceRequest(this);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!runBool)
                {
                    starTime = System.currentTimeMillis();
                    if(isGetImage.get()&&(!isVerfiFace.get()))
                    {

                        Camera.Size size = mcamera.getParameters().getPreviewSize();
                        YuvImage image = new YuvImage(nv21, ImageFormat.NV21, size.width,
                                size.height, null);
                        if (image != null) {

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            image.compressToJpeg(new Rect(0, 0, size.width, size.height),
                                    80, stream);
                            Bitmap bmp = BitmapFactory.decodeByteArray(
                                    stream.toByteArray(), 0, stream.size());

                            bmp = BitmapUtil.zoomBitmap(bmp,size.width/2,size.height/2);

                            bmp = BitmapUtil.rotateBitmapByDegree(bmp, -90);

                            byte[] mface = BitmapUtil.Bitmap2Bytes(bmp);

                            String result = mFaceDetector.detectARGB(bmp);
                            Log.d("VerifyFaceActivity---", "result:"+result);
                            FaceRect[] faceRect = ParseResult.parseResult(result);

                            if(faceRect.length!=0)
                            {
                                Log.e("boolen","-2--isGetImage-"+isGetImage.get()+"---isVerfiFace-"+isVerfiFace.get());
                                Log.d("VerifyFaceActivity---","------------verfiFace--1");
                                isVerfiFace.set(true);
                                mFaceRequest.setParameter(SpeechConstant.AUTH_ID, authid);
                                mFaceRequest.setParameter(SpeechConstant.WFR_SST, "verify");
                                mFaceRequest.sendRequest(mface, mRequestListener);
                                Log.e("VerifyFaceActivity---","------------verfiFace--2");
                            }
                            else {
                                isGetImage.set(false);
                            }
                            Log.e("VerifyFaceActivity---","------------verfiFace--3");

                            bmp.recycle();
                        }
                    }
                }
            }
        });
        thread.start();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        authid = getIntent().getStringExtra("authid");

        authid = MainActivity.studentStatic.getStudentNo();
        Log.e("test-----",authid);

        PREVIEW_WIDTH = metrics.widthPixels;

        PREVIEW_HEIGHT = metrics.heightPixels;

        initUI();
        buffer = new byte[PREVIEW_WIDTH * PREVIEW_HEIGHT];

        mAcc = new Accelerometer(VerifyFaceActivity.this);


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
        mPreviewSurface = (SurfaceView) findViewById(R.id.ver_sfv_preview);
        mPreviewSurface.getHolder().addCallback(mPriviewCallback);

    }



    private void closeCamera() {
        if (null != mcamera) {
            mcamera.setPreviewCallback(null);
            mcamera.stopPreview();
            mcamera.release();
            mcamera = null;
            Log.e("test","-----------camera finish");
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


        mcamera = android.hardware.Camera.open(mCameraID);

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
                if(!isGetImage.get())
                {
                    System.arraycopy(data, 0, nv21, 0, data.length);
                    Log.e("VerifyFaceActivity---","---------------获取图像");
                    isGetImage.set(true);
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

    private void verify(JSONObject obj) throws JSONException {
        int ret = obj.getInt("ret");
        if (ret != 0) {
            showTip("验证失败");
            isVerfiFace.set(false);
            isGetImage.set(false);
            return;
        }
        Log.e("boolen","-3--isGetImage-"+isGetImage.get()+"---isVerfiFace-"+isVerfiFace.get());
        if ("success".equals(obj.get("rst"))) {
            if (obj.getBoolean("verf")) {
                runBool = true;
                Intent intent = new Intent(VerifyFaceActivity.this, StudentIndexActivity.class);
                intent.putExtra("data_return","考勤成功，请好好听课");
                setResult(RESULT_OK,intent);
                this.finish();
            } else {
                showTip("验证不通过");
                isGetImage.set(false);
                isVerfiFace.set(false);
            }
        } else {
            showTip("验证失败");
            isGetImage.set(false);
            isVerfiFace.set(false);
        }
    }

    private void showTip(final String str) {
        Toast mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setText(str);
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityColectorUtil.removeActivity(this);
    }


}
