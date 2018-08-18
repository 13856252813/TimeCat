package com.cary.activity.timecat.fragment.person.sharemoney;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.person.invitaion.InvitaionCodeActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareMoneyActivity extends BaseActivity {
    private static final String TAG = ShareMoneyActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_share_money_title)
    TextView tvShareMoneyTitle;
    @BindView(R.id.tv_share_money_context)
    TextView tvShareMoneyContext;
    @BindView(R.id.btn_share_money_code)
    Button btnShareMoneyCode;

    private SharedPreferencesHelper sharePH;
    private ShareMoneyApi smApi;
    private ShareMoneyResult mSM;
    private String ShareCode;
    private int codeFlag =0;
    private int invitaionPerson;//邀请人的id 根据这个id 再去获取
    private String myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        titleText.setText("分享赚钱");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        sharePH = new SharedPreferencesHelper(this);
        //获取 数据
        smApi = ShareMoneyApi.getApi();

        myself = getIntent().getStringExtra("myself");
        invitaionPerson = getIntent().getIntExtra("invitaionperson", 0);

        createSingle();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_share_money;
    }

    @OnClick({R.id.title_back, R.id.btn_share_money_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_share_money_code:
                Intent intent = new Intent();
                intent.setClass(this, InvitaionCodeActivity.class);
                intent.putExtra("myself",myself);
                intent.putExtra("invitaionperson", invitaionPerson);
                startActivity(intent);
                break;
        }
    }
    private Callback<ShareMoneyResult> callback  = new Callback<ShareMoneyResult>() {
        @Override
        public void onResponse(Call<ShareMoneyResult> call, Response<ShareMoneyResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mSM= response.body();
                if ("00".equals(mSM.getCode())) {
                    tvShareMoneyTitle.setText(mSM.getData().getTitle());
                    tvShareMoneyContext.setText(mSM.getData().getContent());
                    ShareCode = mSM.getData().getRequestCode();
                } else {
                    ToastUtil.showShort(ShareMoneyActivity.this, mSM.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ShareMoneyResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        String token = (String) sharePH.getSharedPreference("token","");
        String id = ((int) sharePH.getSharedPreference("id",0))+"";
        Call<ShareMoneyResult> call = smApi.getService().createCommit(token,id);
        call.enqueue(callback );
    }
}
