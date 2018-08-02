package com.cary.activity.timecat.fragment.index.news;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 资讯详情
 */
public class NewsDetialActivity extends BaseActivity {

    private static final String TAG = NewsDetialActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.news_detial_title)
    TextView newsDetialTitle;
    @BindView(R.id.news_detial_createtime)
    TextView newsDetialCreatetime;
    @BindView(R.id.news_detial_content)
    WebView newsDetialContent;

    private NewsApi mNewsApi;
    private NewsDetialResult mNewsRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int id;
    private String titleStr;// = getIntent().getStringExtra("title");
    private String contentStr;// = getIntent().getStringExtra("context");
    private String createTimeStr;// = getIntent().getStringExtra("createtime");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news_detial);
        ButterKnife.bind(this);
        titleText.setText("资讯详情");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebSettings settings = newsDetialContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        newsDetialContent.setWebViewClient(new MyWebViewClient(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }


        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        mNewsApi = NewsApi.getApi();
        id = getIntent().getIntExtra("id",0);

        if(id!=0){
            createSingleNews(id);
        }else{
            ToastUtil.showShort(this,"获取数据失败，文章在文库中错误");
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_news_detial;
    }

    //加载数据
    private Callback<NewsDetialResult> callbackNews = new Callback<NewsDetialResult>() {
        @Override
        public void onResponse(Call<NewsDetialResult> call, Response<NewsDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mNewsRes = response.body();
                if ("00".equals(mNewsRes.getCode())) {
                    if(mNewsRes!=null)
                    {
                        titleStr = mNewsRes.getData().getTitle();
                        createTimeStr = mNewsRes.getData().getCreateTime();
                        contentStr = mNewsRes.getData().getContent();
                        if (!TextUtils.isEmpty(titleStr)) {
                            newsDetialTitle.setText(titleStr);
                        }
                        if (!TextUtils.isEmpty(createTimeStr)) {
                            newsDetialCreatetime.setText(createTimeStr);
                        }
                        if (!TextUtils.isEmpty(contentStr)) {
                            newsDetialContent.loadData(contentStr, "text/html;charset=utf-8", "utf-8");
                        }
                    }
                } else {
                    ToastUtil.showShort(NewsDetialActivity.this, mNewsRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewsDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleNews(int id) {
        Call<NewsDetialResult> call = mNewsApi.getService().createCommitID(token, id);
        call.enqueue(callbackNews);
    }
    class MyWebViewClient extends WebViewClient {
        private Activity activity;

        public MyWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
