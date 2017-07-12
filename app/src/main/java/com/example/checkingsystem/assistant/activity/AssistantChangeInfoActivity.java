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
import android.widget.TextView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.net.ChangeInfoNet;

import java.io.File;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import util.ActivityColectorUtil;
import util.BitmapUtil;
import util.CosUtil;

import static com.example.checkingsystem.R.id.tv_activity_assistant_change_info_name;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantChangeInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_CLIP_PHOTO = 2;
    public static final int CHOOSE_PHOTO = 3;
    Uri uri = null;
    String imagePath = null;
    private ImageView iv_back;
    private CircleImageView circleImageView;
    private EditText nickName;
    private EditText email;
    private Button submitButton;
    private Bitmap headPictureBitmap;
    String imageName = null;
    private TextView tel;
    private TextView name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_change_info);
        ActivityColectorUtil.addActivity(this);

        //初始化资源
        initSourse();
        //设置点击事件
        circleImageView.setOnClickListener(this);
        circleImageView.setImageBitmap(LoginActivity.headPictureBitmap);
        submitButton.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    private void initSourse(){
        iv_back = (ImageView) findViewById(R.id.iv_activity_assistant_change_info_back);
        circleImageView = (CircleImageView) findViewById(R.id.activity_assistant_change_info_head_picture);
        nickName = (EditText) findViewById(R.id.et_activity_assistant_change_info_nick_name);
        email = (EditText) findViewById(R.id.et_activity_assistant_change_info_email);
        submitButton = (Button) findViewById(R.id.btn_activity_assistant_change_info_submit);
        tel = (TextView) findViewById(R.id.tv_activity_assistant_change_info_tel);
        name = (TextView) findViewById(tv_activity_assistant_change_info_name);

        nickName.setText(LoginActivity.assistantStatic.getAssistantName());
        email.setText(LoginActivity.assistantStatic.getAssistantEmail());
        tel.setText(LoginActivity.assistantStatic.getAssistantTel());
        name.setText("姓名：" + LoginActivity.assistantStatic.getAssistantName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_assistant_change_info_submit:
                String nickNameStr = nickName.getText().toString();
                String emailStr = email.getText().toString();
                String path = CosUtil.urlHeaderImage + imageName;
                ChangeInfoNet changeInfoNet = new ChangeInfoNet();
                changeInfoNet.assistantChangeInfo(nickNameStr,emailStr,path,this);
                break;
            case R.id.activity_assistant_change_info_head_picture:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,CHOOSE_PHOTO);
                break;
            case R.id.iv_activity_assistant_change_info_back:
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
                    break;
                case REQUEST_CODE_CLIP_PHOTO:
                    Bitmap bitmap = data.getParcelableExtra("data");
                    imageName = UUID.randomUUID().toString() + ".jpg";
                    BitmapUtil.saveMyBitmap(bitmap,imageName);
                    CosUtil.upLoad(CosUtil.headerImageCosPath, Environment.getExternalStorageDirectory().getPath() + LoginActivity.path,imageName,this);
                    circleImageView.setImageBitmap(bitmap);
                    LoginActivity.headPictureBitmap = bitmap;
                    break;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId)
                );
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
    }

    private void handleImageBeforeKitKat(Intent data)
    {
        uri = data.getData();
        String imagePath = getImagePath(uri,null);
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
            circleImageView.setImageBitmap(bitmap);
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
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CODE_CLIP_PHOTO);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityColectorUtil.removeActivity(this);
    }
}
