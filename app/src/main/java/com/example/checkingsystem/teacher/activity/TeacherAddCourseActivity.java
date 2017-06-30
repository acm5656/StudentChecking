package com.example.checkingsystem.teacher.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.VirtualCourse;
import com.example.checkingsystem.net.TeacherAddCourseNet;

import java.io.File;
import java.util.UUID;

import util.BitmapUtil;
import util.CosUtil;

public class TeacherAddCourseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private EditText courseAskEditText;
    private EditText courseNameEditText;
    private EditText courseScheduleEditText;
    private EditText courseExamEditText;
    private Button submitButton;
    private String imgUrl;
    private String imgName;
    public static final int CHOOSE_PHOTO = 3;
    private static final int REQUEST_CODE_CLIP_PHOTO = 2;
    String imagePath;
    Uri uri = null;
    public Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_course);
        initUI();
    }
    private void initUI()
    {
        imageView = (ImageView)findViewById(R.id.activity_teacher_add_course_img);
        courseAskEditText = (EditText)findViewById(R.id.activity_teacher_add_course_ask);
        courseNameEditText = (EditText)findViewById(R.id.activity_teacher_add_course_name);
        courseScheduleEditText = (EditText)findViewById(R.id.activity_teacher_add_course_schedule);
        courseExamEditText = (EditText)findViewById(R.id.activity_teacher_add_course_exam);
        submitButton = (Button)findViewById(R.id.activity_teacher_add_course_submit_button);
        imageView.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_teacher_add_course_img:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_PHOTO);
                break;
            case R.id.activity_teacher_add_course_submit_button:
                if(imgName!=null) {
                    imgUrl = CosUtil.urlHeaderImage + imgName;
                }
                else {
                    imgUrl = null;
                }
                String courseAskStr = courseAskEditText.getText().toString();
                String courseNameStr = courseNameEditText.getText().toString();
                String courseScedule = courseScheduleEditText.getText().toString();
                String couseExamStr = courseExamEditText.getText().toString();
                if(courseNameStr==null||courseNameStr.trim().equals(""))
                {
                    Toast.makeText(this,"请输入课程名称",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(imgUrl==null||imgUrl.trim().equals(""))
                {
                    Toast.makeText(this,"请上传课程图片",Toast.LENGTH_SHORT).show();
                    break;
                }
                VirtualCourse virtualCourse = new VirtualCourse();
                virtualCourse.setVirtualCourseAsk(courseAskStr);
                virtualCourse.setVirtualCourseExam(couseExamStr);
                virtualCourse.setVirtualCourseTeacherId(LoginActivity.teacherStatic.getTeacherId());
                virtualCourse.setVirtualCourseSchedule(courseScedule);
                virtualCourse.setVirtualCourseName(courseNameStr);
                virtualCourse.setVirtualCourseImgUrl(imgUrl);
                TeacherAddCourseNet teacherAddCourseNet = new TeacherAddCourseNet();
                teacherAddCourseNet.addCourseNet(this,virtualCourse);
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
