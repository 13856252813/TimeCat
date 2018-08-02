package com.cary.activity.timecat.webview;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.LogUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cary on 2018/4/1.
 * 通用的webView
 */

public class WebViewActivity extends BaseActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();

    private final static int FINISH_ACTIVITY = 0;
    private final static int REQUEST_UPLOAD_FILE_CODE = 2;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    private ValueCallback<Uri> mUploadFile;
    private WebView webview;
    private String url;
    private Handler handler = new MyHandler(this);

    private String contentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_PROGRESS);
//        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

//        titleText.setText("支付订单");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));

        webview = (WebView) findViewById(R.id.webView);
        url = getIntent().getStringExtra("url");
//        url="https://shouyin.yeepay.com/nc-cashier-wap/wap/request/10020390900/gd32HxtZUowm-fleMvX9zg%3D%3D";
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        //设置webview的settings和client
        setActionbarTitle();
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        configWebview();
        contentStr = getIntent().getStringExtra("content");
        if(TextUtils.isEmpty(contentStr)){
            // 加载 页面
            loadURL();
        }else{
            Log.v(TAG,"contents"+contentStr);
            webView.loadData(contentStr, "text/html; charset=UTF-8", null);
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_webview;
    }

    private void setActionbarTitle() {
        String title = getIntent().getStringExtra("title");
        if (title != null)
            titleText.setText(title);
        else
            titleText.setText(R.string.app_name);
    }

    private void loadURL() {
        try {
            webview.loadUrl(url);
        } catch (Exception e) {
            LogUtils.e(e.getLocalizedMessage());
        }
    }

    @Override
    protected void onDestroy() {
        webview.destroy();
        super.onDestroy();
    }

    @SuppressWarnings("deprecation")
    private void configWebview() {
        // 允许javascript代码执行
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheMaxSize(8 * 1024 * 1024);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setDefaultTextEncodingName("utf-8");

        // 在当前页面打开链接，而不是启动用户手机上安装的浏览器打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                return true;
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {
            // 使webview可以更新进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                WebViewActivity.this.setTitle("加载中……");
                WebViewActivity.this.setProgress(newProgress * 100);
                if (newProgress == 100)
                    setActionbarTitle();
            }

            //使JS alert可以以Android的AlertDiaolog形式弹出
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this)
                        .setMessage(message).setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        result.confirm();
                                    }
                                });
                builder.setCancelable(true);
                builder.show();
                return true;
            }

            //html中上传点击input type为file的控件时会调用下列方法，在Android4.4中无效，待修复
            // Android 4.1+
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadFile,
                                        String acceptType, String capture) {
                openFileChooser(uploadFile);
            }

            // Android 3.0 +
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadFile,
                                        String acceptType) {
                openFileChooser(uploadFile);
            }

            // Android 3.0
            public void openFileChooser(ValueCallback<Uri> uploadFile) {
                mUploadFile = uploadFile;
                startActivityForResult(createCameraIntent(),
                        REQUEST_UPLOAD_FILE_CODE);

            }
        });
        // 在js中用window.injs.方法名来调用InJavaScript类中的方法
        webview.addJavascriptInterface(new InJavaScript(), "android");
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    private Intent createCameraIntent() {
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);// 选择图片文件
        imageIntent.setType("image/*");
        return imageIntent;
    }

    // 使后退键可以达到网页回退功能，而不是关闭activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //添加菜单栏的几个功能键
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.webview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 给javascript调用的代码
     */
    private final class InJavaScript {
        //可以用JS关闭本Activity
        @JavascriptInterface
        public void finish() {
            handler.sendEmptyMessage(FINISH_ACTIVITY);
        }

        //可以用JS触发一个分享文本信息的intent
        @JavascriptInterface
        public void sharelink(String link) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "分享");
            i.putExtra(Intent.EXTRA_TEXT, "share this:" + link);
            startActivity(Intent.createChooser(i, "请选择分享方式"));
        }
    }

    /*
     * (non-Javadoc)左上角回退可以结束本activity,另有前进、后退、刷新
     *
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://关闭页面
                finish();
                break;
//            case R.id.menu_webview_refresh://刷新
//                webview.reload();
//                break;
//            case R.id.menu_webview_back://后退
//                if (webview.canGoBack())
//                    webview.goBack();
//                break;
//            case R.id.menu_webview_forward://前进
//                if (webview.canGoForward())
//                    webview.goForward();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //选择文件后回调给JS一个URI
        if (requestCode == REQUEST_UPLOAD_FILE_CODE && resultCode == RESULT_OK) {
            if (null == mUploadFile)
                return;
            Uri result = (null == data) ? null : data.getData();//注，此处data.getData(),若为data则仅是contentProvider的地址将不能为JS识别
            if (null != result) {
                mUploadFile.onReceiveValue(result);
                mUploadFile = null;
            }
            //如果用户取消了选择文件操作，必须回调一个null的URI给JS，否则webview将会死掉
        } else if (requestCode == REQUEST_UPLOAD_FILE_CODE && resultCode == RESULT_CANCELED) {
            Uri result = null;
            mUploadFile.onReceiveValue(result);
            mUploadFile = null;
        }

    }

    //用来处理UI操作的handler，可扩展，暂无太大用处……
    private static class MyHandler extends Handler {
        WeakReference<WebViewActivity> weakReference;

        public MyHandler(WebViewActivity webViewActivity) {
            weakReference = new WeakReference<WebViewActivity>(webViewActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FINISH_ACTIVITY:
                    weakReference.get().finish();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}