package com.example.checkingsystem.assistant.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.net.AssistandAddClassNet;

import java.io.File;
import java.util.UUID;

import util.BitmapUtil;
import util.CosUtil;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantAddClassActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;

    private ImageView imageView;
    private EditText editTextDepartment;
    private EditText editTextMajor;
    private EditText editTextYear;
    private EditText editTextNo;
    private Button buttonSubmit;
    private String imgUrl;
    private String imgName;
    public static final int CHOOSE_PHOTO = 3;
    private static final int REQUEST_CODE_CLIP_PHOTO = 2;
    String imagePath;
    Uri uri = null;
    public Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_add_class);
        initUI();
        initListener();
    }

    private void initUI(){
        iv_back = (ImageView) findViewById(R.id.iv_activity_assistant_add_class);
        imageView = (ImageView) findViewById(R.id.iv_activity_assistant_add_class);
        editTextDepartment = (EditText) findViewById(R.id.et_activity_assistant_add_class_department);
        editTextMajor = (EditText) findViewById(R.id.et_activity_assistant_add_class_major);
        editTextYear = (EditText) findViewById(R.id.et_activity_assistant_add_class_year);
        editTextNo = (EditText) findViewById(R.id.et_activity_assistant_add_class_no);
        buttonSubmit = (Button) findViewById(R.id.btn_activity_assistant_add_class_submit);
    }

    private void initListener(){
        iv_back.setOnClickListener(this);
        imageView.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_activity_assistant_add_class:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_PHOTO);
                break;
            case R.id.btn_activity_assistant_add_class_submit:
                String stringDepartment = editTextDepartment.getText().toString();
                String stringMajor = editTextMajor.getText().toString();
                String stringYear = editTextYear.getText().toString();
                String stringNo = editTextNo.getText().toString();
                if(stringDepartment == null || stringDepartment.trim().equals(""))
                {
                    Toast.makeText(this,"请输入学院名称",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(stringMajor == null || stringMajor.trim().equals(""))
                {
                    Toast.makeText(this,"请输入专业名称",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(stringYear == null || stringYear.trim().equals(""))
                {
                    Toast.makeText(this,"请输入所属年级",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(stringNo == null || stringNo.trim().equals(""))
                {
                    Toast.makeText(this,"请输入班级编号",Toast.LENGTH_SHORT).show();
                    break;
                }
                imgUrl = CosUtil.urlHeaderImage+imgName;
                Class assistantClass = new Class();
                assistantClass.setClassAssistantId(LoginActivity.assistantStatic.getAssistantId());
                assistantClass.setClassDepartment(stringDepartment);
                assistantClass.setClassMajor(stringMajor);
                assistantClass.setClassYear(stringYear);
                assistantClass.setClassNo(stringNo);
                assistantClass.setClassHeadImgUrl(imgUrl);
                AssistandAddClassNet assistandAddClassNet = new AssistandAddClassNet();
                assistandAddClassNet.assistantAddClass(this,assistantClass);
                break;
            case R.id.iv_activity_assistant_add_class_back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_PHOTO:
                    if(Build.VERSION.SDK_INT>=19) {
                        handleImageOnKitKat(data);
                    }
                    else {
                        handleImageBeforeKitKat(data);
                    }
                    starCropPicture();
//                    displayImage(imagePath);
                    break;
                case REQUEST_CODE_CLIP_PHOTO:
                    bitmap = data.getParcelableExtra("data");
                    imgName = UUID.randomUUID().toString()+".jpg";
                    BitmapUtil.saveMyBitmap(bitmap,imgName);
                    CosUtil.upLoad(CosUtil.headerImageCosPath, Environment.getExternalStorageDirectory().getPath()+ LoginActivity.path,imgName,this);
                    imageView.setImageBitmap(bitmap);
                    break;
            }
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data)
    {

        uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri))
        {
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority()))
            {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);

            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority()))
            {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId)
                );
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme()))
        {
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme()))
        {
            imagePath = uri.getPath();
        }
//        displayImage(imagePath);

    }

    private void handleImageBeforeKitKat(Intent data)
    {
        uri = data.getData();
        String imagePath = getImagePath(uri,null);
//        Log.e("test",imagePath);
//        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection)
    {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath)
    {
        if(imagePath != null)
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }
    }
    private void starCropPicture() {
        Intent intent = new Intent("com.android.camera.action.CROP");

        File file = new File(imagePath);

        uri = Uri.parse(imagePath);
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        intent.putExtra("scale", "true"); // 开启剪裁
        intent.putExtra("aspectX", 1); // 宽高比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150); // 宽高
        intent.putExtra("outputY", 150);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CODE_CLIP_PHOTO);

    }



}
