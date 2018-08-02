package com.cary.activity.timecat.main.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.news.CEOEmailResult;
import com.cary.activity.timecat.fragment.index.news.NewsApi;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CEOEmailActivity extends Activity {
    private static final String TAG = CEOEmailActivity.class.getSimpleName();

    @BindView(R.id.ivCEOEmailCancel)
    ImageView ivCEOEmailCancel;
    @BindView(R.id.tvClickLook)
    TextView tvClickLook;
    private NewsApi mNewsApi;
    private CEOEmailResult mNewsRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int id;
    private String titleStr;// = getIntent().getStringExtra("title");
    private String contentStr;// = getIntent().getStringExtra("context");
    private String createTimeStr;// = getIntent().getStringExtra("createtime");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceoemail);
        ButterKnife.bind(this);
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        mNewsApi = NewsApi.getApi();
        createSingle();
    }

    @OnClick({R.id.ivCEOEmailCancel, R.id.tvClickLook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCEOEmailCancel:
                finish();
                break;
            case R.id.tvClickLook:
//                ToastUtil.showShort(this,getString(R.string.click_look));
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("content", contentStr);
                startActivity(intent);

                break;
        }
    }

    //加载数据
    private Callback<CEOEmailResult> callbackNews = new Callback<CEOEmailResult>() {
        @Override
        public void onResponse(Call<CEOEmailResult> call, Response<CEOEmailResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mNewsRes = response.body();
                if ("00".equals(mNewsRes.getCode())) {
                    contentStr = mNewsRes.getData().getContent();
                } else {
                    ToastUtil.showShort(CEOEmailActivity.this, mNewsRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<CEOEmailResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<CEOEmailResult> call = mNewsApi.getService().createCommitCEOEmail(token);
        call.enqueue(callbackNews);
    }
}
