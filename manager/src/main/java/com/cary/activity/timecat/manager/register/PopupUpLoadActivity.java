package com.cary.activity.timecat.manager.register;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.LogUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupUpLoadActivity extends Activity implements View.OnClickListener {
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private LinearLayout layout;
    private Intent intent;

    private Button showList;
    private Button uploadNow;
    private String mCurrentPhotoPath;
    private Bitmap sourcePic;
    private File dir = null;

    private String picName = null;
    private String uploadFile = null;

    static Uri capturedImageUri=null;
    private Bitmap bitmap = null;
    final  static  int  CAMERA =1;
    final  static  int  ICON =2;
    final  static  int  CAMERAPRESS =3;
    final  static  int  ICONPRESS=4;
    Uri imageUri; //图片路径
    File imageFile; //图片文件
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_up_load);

        intent = getIntent();
        btn_take_photo = (Button) this.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button) this.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button) this.findViewById(R.id.btn_cancel);

        layout = (LinearLayout) findViewById(R.id.pop_layout);

        layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "提示：点击空白地方可以关闭",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_cancel.setOnClickListener(this);
        btn_pick_photo.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }
        if (data != null) {
            if (data.getExtras() != null)
            {
                bitmap = (Bitmap) data.getExtras().get("data");
                intent.putExtras(data.getExtras());
                intent.putExtra("uri", capturedImageUri);
                intent.putExtra("requestCode", 1);
                intent.putExtra("image", bitmap);
            }

            if (data.getData() != null)
                intent.setData(data.getData());
        }
        setResult(requestCode, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                if( Build.VERSION.SDK_INT>=23){
                    Toast.makeText(this,"当前的版本号"+Build.VERSION.SDK_INT,Toast.LENGTH_LONG).show();
                    //android 6.0权限问题
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED||
                            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERAPRESS);
                        Toast.makeText(this,"执行了权限请求",Toast.LENGTH_LONG).show();
                    }else {
                        dispatchTakePictureIntent();
                    }
                }else {
                    dispatchTakePictureIntent();
                }

                break;
            case R.id.btn_pick_photo:
                if( Build.VERSION.SDK_INT>=23){
                    Toast.makeText(this,"当前的版本号"+Build.VERSION.SDK_INT,Toast.LENGTH_LONG).show();
                    //android 6.0权限问题
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED||
                            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED   ){
                        Toast.makeText(this,"执行了权限请求",Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERAPRESS);
                    }else {
                        startIcon();
                    }

                }else {
                    startIcon();
                }
                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void startIcon()
    {
        try {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 2);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // Create an image file name
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg",     /* suffix */
                storageDir   /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                LogUtils.d("photeoFile.size():"+photoFile.length());
            } catch (Exception ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            capturedImageUri = Uri.fromFile(photoFile);
            if (photoFile != null) {
                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }
}
