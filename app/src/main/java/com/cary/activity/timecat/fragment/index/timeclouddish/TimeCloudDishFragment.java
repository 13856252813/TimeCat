package com.cary.activity.timecat.fragment.index.timeclouddish;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ImageInfoObj;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ImageWidgetInfoObj;
import com.cary.activity.timecat.fragment.index.timeclouddish.adapter.TimeCloudDishRecyclerAdapter;
import com.cary.activity.timecat.fragment.index.timeclouddish.newfloder.NewFloderApi;
import com.cary.activity.timecat.fragment.index.timeclouddish.newfloder.NewFloderCommitResult;
import com.cary.activity.timecat.fragment.index.timeclouddish.photo.CloudDishPhotoActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.uploadpic.UploadPicApi;
import com.cary.activity.timecat.fragment.index.timeclouddish.uploadpic.UploadPicCommitResult;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.oss.OSSCredentialsApi;
import com.cary.activity.timecat.oss.OSSCredentialsCommitResult;
import com.cary.activity.timecat.oss.put.OSSPutClass;
import com.cary.activity.timecat.util.LogUtils;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;
import com.cary.activity.timecat.util.view.NewFloderUploaderPopu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 时光云盘 fragment
 */
public class TimeCloudDishFragment extends Fragment {

    private String TAG = TimeCloudDishFragment.class.getSimpleName();

    private List<CloudDishPhotoCommitResult.Data> mListDatas = new ArrayList<>();
    private int position = 0;
    private TimeCloudDishRecyclerAdapter mAdapter;
    private CloudDishPhotoCommitResult cpcr;
    private CloudDishApi cdApi;
    private SharedPreferencesHelper shareHelper;
    private int uid;//用户id
    private String token;
    private int SumPage = 1;
    private int currentpage = 1;//页数

    private ImageInfoObj imageInfoObj;
    private ImageWidgetInfoObj imageWidgetInfoObj;

    private RecyclerView recyclerView;
    private ImageView ivAddImage;
    private SwipeRefreshLayout mSwipeRefresh;
    private NewFloderUploaderPopu menuWindow;
    private final static int CAMERAPRESS = 3;

    private String bucketName = "";
    private boolean uploadimgFlag = true;
    private OSSCredentialsCommitResult osscredentialsComRes;
    private OSSCredentialsApi osscredentialsApi;
    private OSS oss;
    private OSSPutClass ossPutClass;
    private String uploadimage = "";//上传图片后返回的路径

    //新建文件夹
    private NewFloderCommitResult newFloderCR;
    private NewFloderApi newFloaderApi;
    //上传图片
    private UploadPicCommitResult uploadPicCR;
    private UploadPicApi uploadPicApi;
    int newFloderType = 0;//文件夹类型 私密 公开 默认公开1


    public static TimeCloudDishFragment newInstance(int position) {
        TimeCloudDishFragment newFragment = new TimeCloudDishFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        newFragment.setArguments(bundle);
        return newFragment;

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        position=bundle.getInt("position");

        osscredentialsApi = OSSCredentialsApi.getApi();
        ossPutClass = new OSSPutClass(getActivity());
        credentialProvider = new OSSCustomSignerCredentialProvider() {

            @Override
            public String signContent(String content) {
                // 您需要在这里依照OSS规定的签名算法，实现加签一串字符内容，并把得到的签名传拼接上AccessKeyId后返回
                // 一般实现是，将字符内容post到您的业务服务器，然后返回签名
                // 如果因为某种原因加签失败，描述error信息后，返回nil
                // 以下是用本地算法进行的演示
                Log.v(TAG,"content:"+content);
                createSingleOssAk(content);
                return mbd.getData();//"OSS " + AccessKeyId + ":" + OSSUtils.base64(hmac - sha1(SecretKeyId, content));
            }
        };

        createSingleOssCre();

        shareHelper = new SharedPreferencesHelper(getActivity());
        newFloaderApi = NewFloderApi.getApi();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_time_cloud_dish, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        mSwipeRefresh = rootView.findViewById(R.id.swiperefreshlayout_timedish);
        ivAddImage = rootView.findViewById(R.id.ivCloudAddImage);

        cdApi = CloudDishApi.getApi();
        uid = (int) shareHelper.getSharedPreference("id", 0);
        token = (String) shareHelper.getSharedPreference("token", "");

        ivAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow = new NewFloderUploaderPopu(getActivity(), clickListener);
                menuWindow.show();
            }
        });

        //列表刷新
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TimeCloudDishRecyclerAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ImageView imageView = view.findViewById(R.id.iv_time_cloud_dish_img);
                Intent intent = new Intent();
//                if(mListDatas.get(postion))
                //判断是否是文件夹 跳转 跳转不同的界面
                //跳转到列表
                intent.setClass(getActivity(), CloudDishPhotoActivity.class);
                intent.putExtra("id", mListDatas.get(postion).getId()+"");

//                //跳转到图片
//                String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL+mListDatas.get(postion).getFolder();
//                imageInfoObj = new ImageInfoObj();
//                imageInfoObj.imageUrl = imageUrl;
//                imageInfoObj.imageWidth = 1280;
//                imageInfoObj.imageHeight = 720;
//                imageWidgetInfoObj = new ImageWidgetInfoObj();
//                imageWidgetInfoObj.x = imageView.getLeft();
//                imageWidgetInfoObj.y = imageView.getTop();
//                imageWidgetInfoObj.width = imageView.getLayoutParams().width;
//                imageWidgetInfoObj.height = imageView.getLayoutParams().height;
//                intent.setClass(getActivity(), ShowPictureActivity.class);
//                intent.putExtra("imageInfoObj", imageInfoObj);
//                intent.putExtra("imageWidgetInfoObj", imageWidgetInfoObj);

                startActivity(intent);
            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override//滚动状态变化时回调
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                // mLastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                //滑动停止并且底部不可滚动（即滑动到底部） 加载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !(ViewCompat.canScrollVertically(recyclerView, 1))) {
                    if (currentpage < SumPage) {
                        loadMore();
                    }
                }
            }

            @Override//滚动时回调
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        setMethod();
        return rootView;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(TimeCloudDishRecyclerAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod();
    }

    private void setMethod() {
        if (position == 0) {
            newFloderType = 1;
            ivAddImage.setVisibility(View.VISIBLE);
            createSingle(newFloderType);
        } else if (position == 1) {
            newFloderType = 0;
            ivAddImage.setVisibility(View.VISIBLE);
            createSingle(newFloderType);
        } else if(position == 3){
            ivAddImage.setVisibility(View.GONE);
            createSingleRecycle();
        }

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_new_floder:
                    LayoutInflater factory = LayoutInflater.from(getActivity());//提示框
                    View dialogView = factory.inflate(R.layout.dialog_new_floder, null);//这里必须是final的
                    final EditText edit = (EditText) dialogView.findViewById(R.id.editTextDialogNewFloder);//获得输入框对象

                    AlertDialog mDialog = new AlertDialog.Builder(getActivity(),R.style.alert_dialog)
                            .setTitle("请输入新建文件夹名称")//提示框标题
                            .setView(dialogView)
                            .setPositiveButton("确定",//提示框的两个按钮
                                    new android.content.DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            //事件
                                            String newFloderName = edit.getText().toString().trim();
                                            if (!TextUtils.isEmpty(newFloderName)) {
                                                ToastUtil.showShort(getActivity(), newFloderName);
                                                createSingleNewFloder(newFloderType, newFloderName);
                                            } else {
                                                ToastUtil.showShort(getActivity(), "文件夹名称不能为空");
                                            }
                                        }
                                    })
                            .setNegativeButton("取消", null)
                            .create();
                    mDialog.show();
                    break;
                case R.id.btn_upload_photo:
                    if (Build.VERSION.SDK_INT >= 23) {
//                        Toast.makeText(getActivity(), "当前的版本号" + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                        //android 6.0权限问题
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(), "执行了权限请求", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERAPRESS);
                        } else {
                            startIcon();
                        }

                    } else {
                        startIcon();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void startIcon() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String imagePath = getPath(getActivity(), data.getData());
            Log.v(TAG, "要上传的本地图片路径：" + imagePath);
            if (TextUtils.isEmpty(bucketName) || TextUtils.isEmpty(imagePath)) {
                ToastUtil.showShort(getActivity(), "图片或获取参数 不全");
            } else if (uploadimgFlag) {
//                getUpLoadImgRes(imagePath);
                OssUploadImg(bucketName, new File(imagePath).getName(), imagePath);
            } else {
                ToastUtil.showShort(getActivity(), "正在上传，请稍等~");
            }
        }
    }
    private void createSingleOssCre() {
        Call<OSSCredentialsCommitResult> call = osscredentialsApi.getService().createCommit();
        call.enqueue(callbackOssCre);
    }

    /***********************************************************************************************************
     *                                              获取OSS配置                                                 *
     * *********************************************************************************************************/
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
//                    ToastUtil.showShort(getActivity(), osscredentialsComRes.getMsg());
                } else {
                    ToastUtil.showShort(getActivity(), osscredentialsComRes.getMsg());
                }
                bucketName = osscredentialsComRes.getData().getBucketName();
                LogUtils.v("buckname:" + osscredentialsComRes.getData().getBucketName());
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
    OSSCredentialProvider credentialProvider = null;
    /***********************************************************************************************************
     *                                                初始化OSS                                                 *
     * *********************************************************************************************************/
    private void initOss(String endpoint, String AccessKeyId, String SecretKeyId, String SecurityToken) {
        // 在移动端建议使用STS方式初始化OSSClient。
        // 更多信息可查看sample 中 sts 使用方式(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
//        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
//                AccessKeyId, SecretKeyId, SecurityToken);
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
        oss = new OSSClient(getActivity(), endpoint, credentialProvider);
    }

    /***********************************************************************************************************
     *                                                上传图片                                                  *
     * *********************************************************************************************************/
    public void OssUploadImg(String bucketName, final String objectKey, String uploadimage) {
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
                //上传文件名到服务器
                createSingleUploadPic("", objectKey, "备注：根目录");
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

    /***********************************************************************************************************
     *                                                  获取列表                                                *
     * *********************************************************************************************************/
    private Callback<CloudDishPhotoCommitResult> callbackPicList = new Callback<CloudDishPhotoCommitResult>() {
        @Override
        public void onResponse(Call<CloudDishPhotoCommitResult> call, Response<CloudDishPhotoCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                cpcr = response.body();
                if ("00".equals(cpcr.getCode())) {
                    mListDatas = cpcr.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mListDatas);
                        mSwipeRefresh.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mListDatas);
                    }
                    //总弄页数
                    SumPage = cpcr.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(TimeCloudDishRecyclerAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(TimeCloudDishRecyclerAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
//                    ToastUtil.showShort(getActivity(), cpcr.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<CloudDishPhotoCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(int flag) {
        Call<CloudDishPhotoCommitResult> call = cdApi.getService().createCommit(token, uid + "", flag, currentpage);
        call.enqueue(callbackPicList);
    }
    //回收站列表
    private void createSingleRecycle() {
        Call<CloudDishPhotoCommitResult> call = cdApi.getService().createCommitRecycle(token,  currentpage);
        call.enqueue(callbackPicList);
    }
    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, List<CloudDishPhotoCommitResult.Data> mList) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new TimeCloudDishRecyclerAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (position == 1) {//私密照片
                    ToastUtil.showShort(getActivity(), "Grid 1 postion:" + postion);
                } else if (position == 0) {//公开照片
                    ToastUtil.showShort(getActivity(), "Grid 0 postion:" + postion);
                }
                Intent intent = new Intent(getActivity(), CloudDishPhotoActivity.class);
                intent.putExtra("type", postion + "");
                startActivity(intent);
            }
        });

    }

    /***********************************************************************************************************
     *                                                新建文件夹                                                 *
     * *********************************************************************************************************/
    private Callback<NewFloderCommitResult> callbacknewFloder = new Callback<NewFloderCommitResult>() {
        @Override
        public void onResponse(Call<NewFloderCommitResult> call, Response<NewFloderCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                newFloderCR = response.body();
                if ("00".equals(newFloderCR.getCode())) {
                    ToastUtil.showShort(getActivity(), "文件夹创建成功");
                    currentpage = 1;
                    setMethod();
                } else {
                    ToastUtil.showShort(getActivity(), newFloderCR.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewFloderCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleNewFloder(int flag, String newFloderName) {
        String token = shareHelper.getSharedPreference("token", "").toString();
        int uid = (int) shareHelper.getSharedPreference("id", 0);
        Call<NewFloderCommitResult> call = newFloaderApi.getService().createCommit(token, uid + "", flag, newFloderName);
        call.enqueue(callbacknewFloder);
    }

    private String getUpLoadImgRes(String imagePath) {
        ossPutClass.OssPutMethod(imagePath);
        String imagePathurl = "";
        if (ossPutClass != null) {
            imagePathurl = ossPutClass.getOSSPutResult().getData();
        }
        return imagePathurl;
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

    /***********************************************************************************************************
     *                                         上传图片 给服务器                                                 *
     * *********************************************************************************************************/
    private Callback<UploadPicCommitResult> callbackUploadPic = new Callback<UploadPicCommitResult>() {
        @Override
        public void onResponse(Call<UploadPicCommitResult> call, Response<UploadPicCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                uploadPicCR = response.body();
                if ("00".equals(newFloderCR.getCode())) {
                    ToastUtil.showShort(getActivity(), uploadPicCR.getData().getFileName() + "Upload Success");
                } else {
                    ToastUtil.showShort(getActivity(), uploadPicCR.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }
        }

        @Override
        public void onFailure(Call<UploadPicCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleUploadPic(String FloderId, String newFloderName, String note) {
        Call<UploadPicCommitResult> call = uploadPicApi.getService().createCommit(
                token, FloderId, newFloderName, note, newFloderName);
        call.enqueue(callbackUploadPic);
    }

    private ModelBeanData mbd;
    private Callback<ModelBeanData> callbackOssak = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mbd = response.body();
                if ("00".equals(mbd.getCode())) {
                    Log.v(TAG, "mbd.getSign:" + mbd.getData());
                }


            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleOssAk(String content) {
        Call<ModelBeanData> call = osscredentialsApi.getService().createCommitSign(token,content);
        call.enqueue(callbackOssak);
    }
}
