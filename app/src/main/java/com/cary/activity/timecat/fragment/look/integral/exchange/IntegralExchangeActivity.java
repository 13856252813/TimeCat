package com.cary.activity.timecat.fragment.look.integral.exchange;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.banner.BannerApi;
import com.cary.activity.timecat.fragment.index.banner.BannerCommitResult;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialOrderActivity;
import com.cary.activity.timecat.fragment.look.integral.IntegralApi;
import com.cary.activity.timecat.fragment.look.integral.exchange.selection.ExSelectionPopuWindow;
import com.cary.activity.timecat.fragment.look.interact.InteractDetialActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.BannerLayout;
import com.cary.activity.timecat.webview.WebViewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntegralExchangeActivity extends BaseActivity {

    private static final String TAG = InteractDetialActivity.class.getSimpleName();

    @BindView(R.id.iv_integral_exchange_back)
    ImageView ivIntegralExchangeBack;
    @BindView(R.id.banner_integral_exchanges_detial)
    BannerLayout bannerIntegralExchangesDetial;
    @BindView(R.id.tv_integral_exchanges_title)
    TextView tvIntegralExchangesTitle;
    @BindView(R.id.tv_integral_exchanges_gral_text)
    TextView tvIntegralExchangesGralText;
    @BindView(R.id.tv_integral_exchanges_gral)
    TextView tvIntegralExchangesGral;
    @BindView(R.id.tv_integral_exchanges_gral_old)
    TextView tvIntegralExchangesGralOld;
    @BindView(R.id.tv_integral_exchanges_gral_already)
    TextView tvIntegralExchangesGralAlready;
    @BindView(R.id.ll_integral_exchanges_title)
    LinearLayout llIntegralExchangesTitle;
    @BindView(R.id.integral_exchanges_detial_desc_webview)
    WebView integralExchangesDetialDescWebView;
    @BindView(R.id.ll_integral_exchanges_detial)
    LinearLayout llIntegralExchangesDetial;
    @BindView(R.id.tv_integral_exchanges_text)
    TextView tvIntegralExchangesText;
    @BindView(R.id.tv_integral_exchanges_number)
    TextView tvIntegralExchangesNumber;
    @BindView(R.id.tv_integral_exchanges_btn)
    TextView tvIntegralExchangesBtn;
    @BindView(R.id.tv_integral_exchanges_desc)
    TextView tvIntegralExchangesDesc;
    private BannerApi bannerApi;
    private List<String> bannerUrls = new ArrayList<>();
    private BannerCommitResult bannerComRes;

    private IntegralApi mApi;
    private IntegralDetialResult mRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int id;
    private int currentpage = 1;
    private int uid;

    private ExSelectionPopuWindow selectPopu;
    private int photoFlag = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_integral_exchange);
        ButterKnife.bind(this);
        tvIntegralExchangesGralOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //默认API 最低19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  //该参数指布局能延伸到navigationbar，我们场景中不应加这个参数
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT); //设置navigationbar颜色为透明
        }
        id = getIntent().getIntExtra("id", 0);
        bannerApi = BannerApi.getApi();
        createSingleBanner();
        bannerIntegralExchangesDetial.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                 if (0 == bannerComRes.getData().get(position).getType()) {
                    Toast.makeText(IntegralExchangeActivity.this, "跳转到商品:" + position, Toast.LENGTH_SHORT).show();
                } else if (1 == bannerComRes.getData().get(position).getType()) {
                    Intent intent = new Intent(IntegralExchangeActivity.this, WebViewActivity.class);
                    intent.putExtra("url", bannerComRes.getData().get(position).getUrl());
                    startActivity(intent);
                } else {
                    Toast.makeText(IntegralExchangeActivity.this, "点击的此处位置position:" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = IntegralApi.getApi();
        createSingleDetial();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_integral_exchange;
    }

    @OnClick({R.id.iv_integral_exchange_back, R.id.tv_integral_exchanges_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_integral_exchange_back:
                finish();
                break;
            case R.id.tv_integral_exchanges_btn:
                //此处弹窗 选择规格
                selectPopu = new ExSelectionPopuWindow(this, clickListener,mRes);
                selectPopu.show();
                break;
        }
    }
    //确认 所选的值
    private ExSelectionPopuWindow.OnClickConfrmListener clickListener = new ExSelectionPopuWindow.OnClickConfrmListener() {
        @Override
        public void onClick(View v,String itemStr) {
//            mListItem.add(title);
            switch (v.getId()) {
                case R.id.tv_selection_confirm:
                    selectPopu.cancel();
                    Intent intent = new Intent(IntegralExchangeActivity.this, FullDressDetialOrderActivity.class);
                    intent.putExtra("photoFlag", photoFlag);
                    intent.putExtra("itemNorm",itemStr.substring(0,itemStr.length()-1));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("exchangedetial", (Serializable) mRes.getData());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            }
        }
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.tv_selection_confirm:
//                    selectPopu.cancel();
//                    Intent intent = new Intent(FullDressDetialActivity.this, FullDressDetialOrderActivity.class);
//                    intent.putExtra("photoFlag", photoFlag);
//                    startActivity(intent);
//                    break;
//            }
//        }
    };

    /******************************banner 获取数据*************************/
    private Callback<BannerCommitResult> callbackbanner = new Callback<BannerCommitResult>() {
        @Override
        public void onResponse(Call<BannerCommitResult> call, Response<BannerCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                bannerComRes = response.body();
                if ("00".equals(bannerComRes.getCode())) {
                    for (int i = 0; i < bannerComRes.getData().size(); i++) {
                        bannerUrls.add(bannerComRes.getData().get(i).getImgurl());
                    }
                    if (bannerIntegralExchangesDetial != null) {
                        bannerIntegralExchangesDetial.setViewUrls(bannerUrls, null);
                    }
                } else {
                    ToastUtil.showShort(IntegralExchangeActivity.this, bannerComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<BannerCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleBanner() {
        Call<BannerCommitResult> call = bannerApi.getService().createCommitIntergral();
        call.enqueue(callbackbanner);
    }

    /****************获取积分兑换详情************************/
    private Callback<IntegralDetialResult> callback = new Callback<IntegralDetialResult>() {
        @Override
        public void onResponse(Call<IntegralDetialResult> call, Response<IntegralDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    tvIntegralExchangesDesc.setText(mRes.getData().getIntroduction());
                    tvIntegralExchangesTitle.setText(mRes.getData().getTitle());
                    tvIntegralExchangesGral.setText(mRes.getData().getScore() + "");
                    tvIntegralExchangesGralOld.setText("¥" + mRes.getData().getMarkerScore());
                    tvIntegralExchangesGralAlready.setText("已兑换" + mRes.getData().getExchangeCount() + "次");
                    tvIntegralExchangesNumber.setText(mRes.getData().getScore() + "");//兑换积分
                    integralExchangesDetialDescWebView.loadData(mRes.getData().getContent(), "text/html", "utf-8");

                } else {
                    ToastUtil.showShort(IntegralExchangeActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<IntegralDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleDetial() {
        Call<IntegralDetialResult> call = mApi.getService().createCommitID(token, id);
        call.enqueue(callback);
    }
}
