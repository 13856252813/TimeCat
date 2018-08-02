package com.cary.activity.timecat.manager.register;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.login.LoginActivity;
import com.cary.activity.timecat.manager.login.LoginCommitResult;
import com.cary.activity.timecat.manager.oss.OSSCredentialsApi;
import com.cary.activity.timecat.manager.oss.OSSCredentialsCommitResult;
import com.cary.activity.timecat.manager.oss.put.OSSPutClass;
import com.cary.activity.timecat.manager.teacherregister.TeacherRegisterActivity;
import com.cary.activity.timecat.manager.util.LogUtils;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfectInformationActivity extends AppCompatActivity {

    private static final String TAG = PerfectInformationActivity.class.getSimpleName();

//    private RegisterCommitParam regcommitParam;
    private RegisterApi regApi;
    private OSSCredentialsCommitResult osscredentialsComRes;
    private OSSCredentialsApi osscredentialsApi;

    private OSS oss;

    private OSSPutClass ossPutClass;
    private String uploadimage = "";//上传图片后返回的路径

    //    String myUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522321760966&di=c237251b288d0f00bd74b721153d1651&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F09fa513d269759eee5b61ac2befb43166c22dfd1.jpg";
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.ivPerfectInfomationHead)
    ImageView ivPerfectInfomationHead;
    @BindView(R.id.tvPerfectInfomationHead)
    TextView tvPerfectInfomationHead;
    @BindView(R.id.etPerfectInformationNick)
    EditText etPerfectInformationNick;
    @BindView(R.id.btUploadOk)
    Button btUploadOk;

    private Uri imageUri; //图片路径
    private File imageFile; //图片文件
    private String imagePath;
    private Bitmap photo;
    final static int CAMERA = 1;
    final static int ICON = 2;
    final static int CAMERAPRESS = 3;
    final static int ICONPRESS = 4;
    RequestOptions options2;
    private String bucketName = "";
    private boolean uploadimgFlag = true;
    private LoginCommitResult logincomes;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_perfect_information);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        osscredentialsApi = OSSCredentialsApi.getApi();
        ossPutClass = new OSSPutClass(this);

        createSingleOssCre();
        regApi = RegisterApi.getApi();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        logincomes = (LoginCommitResult) getIntent().getSerializableExtra("logincomes");

        titleTextRight.setVisibility(View.VISIBLE);
        titleText.setText(getString(R.string.perfectInfomationTitle));
        titleTextRight.setText(getString(R.string.skip));
        tvPerfectInfomationHead.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        titleTextRight.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        options2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.avatarw)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform(this, 2, this.getResources().getColor(R.color.black)));
        Glide.with(this).load(R.mipmap.avatarw).apply(options2).into(ivPerfectInfomationHead);

    }


    @OnClick({R.id.title_back, R.id.title_text_right, R.id.ivPerfectInfomationHead, R.id.tvPerfectInfomationHead, R.id.btUploadOk})
    public void onViewClicked(View view) {
        String nickStr = etPerfectInformationNick.getText().toString().trim();
       switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
//                regcommitParam.setNickname("");
//                regcommitParam.setImgurl("");
//                createSingle();
                //直接跳过
                Intent mIntent = new Intent (this,LoginActivity.class);
                setResult(1001, mIntent);
                finish();
                break;
            case R.id.ivPerfectInfomationHead:
                Intent popupIntent = new Intent(this, PopupUpLoadActivity.class);
                startActivityForResult(popupIntent, 1);
                break;
            case R.id.tvPerfectInfomationHead:
//                上传图片
//                uploadimage = getUpLoadImgRes(imagePath);
                if (TextUtils.isEmpty(bucketName) || TextUtils.isEmpty(imagePath)) {
                    ToastUtil.showShort(this, "图片或获取参数 不全");
                } else if (uploadimgFlag) {
                    OssUploadImg(bucketName, new File(imagePath).getName(), imagePath);
                } else {
                    ToastUtil.showShort(this, "正在上传，请稍等~");
                }
                break;
            case R.id.btUploadOk:
                if (TextUtils.isEmpty(nickStr) && TextUtils.isEmpty(uploadimage)) {
                    ToastUtil.showShort(this, "请输入昵称或上传头像");
                } else {
                    if(!TextUtils.isEmpty(nickStr)){
                        //更新信息
                        createSingle(nickStr,"");
                    }else if(!TextUtils.isEmpty(bucketName)){
                        createSingle("",bucketName);
                    }else{
                        createSingle(nickStr,bucketName);
                    }
//                    regcommitParam.setNickname(nickStr);
//                    if (!TextUtils.isEmpty(bucketName)) {
//                        regcommitParam.setImgurl(new File(imagePath).getName());
//                        regcommitParam.setImgurl(getUpLoadImgRes(imagePath));
//                    }

                }

                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                imageUri = (Uri) data.getExtras().get("uri");
                photo = (Bitmap) data.getExtras().get("image");
                Uri uri = data.getData();
                if (uri != null) {
                    this.photo = BitmapFactory.decodeFile(uri.getPath());
                }
                if (this.photo == null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        this.photo = (Bitmap) bundle.get("data");
                    } else {
                        Toast.makeText(PerfectInformationActivity.this, "拍照失败", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                FileOutputStream fileOutputStream = null;
                try {
                    // 获取 SD 卡根目录
                    String saveDir = Environment.getExternalStorageDirectory() + "/timecat_photos";
                    // 新建目录
                    File dir = new File(saveDir);
                    if (! dir.exists()) dir.mkdir();
                    // 生成文件名
                    SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
                    String filename = "MT" + (t.format(new Date())) + ".jpg";
                    // 新建文件
                    File file = new File(saveDir, filename);
                    // 打开文件输出流
                    fileOutputStream = new FileOutputStream(file);
                    // 生成图片文件
                    this.photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    // 相片的完整路径
                    this.imagePath = file.getPath();
                    LogUtils.v("imagePath" + imagePath);
                    Glide.with(this).load(imagePath).apply(options2).into(ivPerfectInfomationHead);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            case 2:
                DisplayMetrics metric = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metric);
                String dst = getPath(this, data.getData());
                imageFile = new File(dst);
                imagePath = dst;
                Bitmap bitmap = ThumbnailUtils.extractThumbnail(getBitmapFromFile(imageFile), 50, 50);
                getimage(imagePath);
//                ivPerfectInfomationHead.setImageBitmap(bitmapdown);
                LogUtils.v("imagePath" + imagePath);
                Glide.with(this).load(imagePath).apply(options2).into(ivPerfectInfomationHead);
                break;
            default:
                break;

        }
    }

    private String getUpLoadImgRes(String imagePath) {
        ossPutClass.OssPutMethod(imagePath);
        String imagePathurl = "";
        if (ossPutClass != null) {
            imagePathurl = ossPutClass.getOSSPutResult().getData();
        }
        return imagePathurl;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERAPRESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //获取到了权限
//                    startCamera();
                } else {
                    Toast.makeText(this, "对不起你没有同意该权限", Toast.LENGTH_LONG).show();
                }
                break;

            case ICONPRESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //获取到了权限
                    startIcon();
                } else {
                    Toast.makeText(this, "对不起你没有同意该权限", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private Callback<RegisterCommitResult> callback = new Callback<RegisterCommitResult>() {
        @Override
        public void onResponse(Call<RegisterCommitResult> call, Response<RegisterCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                RegisterCommitResult regComRes = response.body();
                if ("00".equals(regComRes.getCode())) {
                    startActivity(new Intent(PerfectInformationActivity.this, TeacherRegisterActivity.class));
                    finish();
                } else {
                    ToastUtil.showShort(PerfectInformationActivity.this, regComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<RegisterCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            ToastUtil.showShort(PerfectInformationActivity.this, "注册失败");
        }
    };

    private void createSingle(String nickStr,String bucketName) {
        String token = (String) sharedPreferencesHelper.getSharedPreference("token","");
        int uid = (int) sharedPreferencesHelper.getSharedPreference("id",0);
        Call<RegisterCommitResult> call ;
        if(!TextUtils.isEmpty(nickStr)){
            call=regApi.getService().createCommitPutNick(token,uid,nickStr);
        }else  if(!TextUtils.isEmpty(bucketName)){
            call=regApi.getService().createCommitPutBucketName(token,uid,bucketName);
        }else{
            call=regApi.getService().createCommitPutId(token,uid,nickStr,bucketName);
        }
        call.enqueue(callback);
    }

//    public void  startCamera(){
//        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//        imageFile = new File(path, "heard.png");
//        try {
//            if (imageFile.exists()) {
//                imageFile.delete();
//            }
//            imageFile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //将File对象转换为Uri并启动照相程序
//        imageUri = Uri.fromFile(imageFile);
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
//        startActivityForResult(intent, CAMERA); //启动照相
//    }

    public void startIcon() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, ICON);
    }

    public Bitmap getBitmapFromFile(File dst) {
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            //opts.inJustDecodeBounds = false;
            opts.inSampleSize = 2;

            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 60, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    //上传图片
    private Callback<OSSCredentialsCommitResult> callbackOssCre = new Callback<OSSCredentialsCommitResult>() {
        @Override
        public void onResponse(Call<OSSCredentialsCommitResult> call, Response<OSSCredentialsCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                osscredentialsComRes = response.body();
                if ("00".equals(osscredentialsComRes.getCode())) {
//                    OSSCredentialsClass.this.osscredentialsComRes = osscredentialsComRes;
                    //获取OSS配置正确
                    ToastUtil.showShort(PerfectInformationActivity.this, osscredentialsComRes.getMsg());
                } else {
                    ToastUtil.showShort(PerfectInformationActivity.this, osscredentialsComRes.getMsg());
                }
                bucketName = osscredentialsComRes.getData().getBucketName();
                Log.v(TAG,"buckname:" + osscredentialsComRes.getData().getBucketName());
                initOss(osscredentialsComRes.getData().getEndpoint(), osscredentialsComRes.getData().getAccessKeyId(),
                        osscredentialsComRes.getData().getAccessKeySecret(), osscredentialsComRes.getData().getSecurityToken());
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<OSSCredentialsCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleOssCre() {
        Call<OSSCredentialsCommitResult> call = osscredentialsApi.getService().createCommit();
        call.enqueue(callbackOssCre);
    }

    private void createMapOssCre() {
        Map map = new HashMap();
        Call<OSSCredentialsCommitResult> call = osscredentialsApi.getService().createCommit(map);
        call.enqueue(callbackOssCre);
    }

    private void initOss(String endpoint, String AccessKeyId, String SecretKeyId, String SecurityToken) {
// 在移动端建议使用STS方式初始化OSSClient。
// 更多信息可查看sample 中 sts 使用方式(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
                AccessKeyId, SecretKeyId, SecurityToken);
//该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
//开启可以在控制台看到日志，并且会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv  默认不开启
//日志会记录oss操作行为中的请求数据，返回数据，异常信息
//例如requestId,response header等
//android_version：5.1  android版本
//mobile_model：XT1085  android手机型号
//network_state：connected  网络状况
//network_type：WIFI 网络连接类型
//具体的操作行为信息:
//[2017-09-05 16:54:52] - Encounter local execpiton: //java.lang.IllegalArgumentException: The bucket name is invalid.
//A bucket name must:
//1) be comprised of lower-case characters, numbers or dash(-);
//2) start with lower case or numbers;
//3) be between 3-63 characters long.
//------>end of log
        OSSLog.enableLog();
        oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
    }

    public void OssUploadImg(String bucketName, String objectKey, String uploadimage) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, uploadimage);
// 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                uploadimgFlag = false;
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                LogUtils.v("PutObject==result:" + result.toString());
                uploadimgFlag = true;
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
                uploadimgFlag = true;
            }
        });
// task.cancel(); // 可以取消任务

    }

    private String getFile(Bitmap bitmap,Uri uri) {
        String pictureDir = uri.toString();
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream baos = null;
        File file = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
//            String saveDir = Environment.getExternalStorageDirectory()
//                    + "/dreamtownImage";
//            File dir = new File(saveDir);
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
//            file = new File(saveDir, userId + DTCalTime.getMTime() + DTCalTime.generateShortUuid() + ".jpg");
//            file.delete();
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            file = new File(pictureDir);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);
            pictureDir = file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pictureDir;
    }
}
