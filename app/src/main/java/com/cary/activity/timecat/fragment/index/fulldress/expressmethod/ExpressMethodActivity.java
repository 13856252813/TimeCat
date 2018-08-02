package com.cary.activity.timecat.fragment.index.fulldress.expressmethod;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 快递公司
 */
public class ExpressMethodActivity extends BaseActivity {

    private static final String TAG = ExpressMethodActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_express_method)
    RecyclerView recyclerExpressMethod;
//    @BindView(R.id.swiperefreshlayout_express_method)
//    SwipeRefreshLayout swiperefreshlayoutExpressMethod;

    private LinearLayoutManager linearLayoutManager;

    private ExpressAdapter mAdapter;
    private ExpressApi mApi;
    private ExpressResult mRes;
    private List<ExpressResult.Data> mData;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleText.setText("选择快递");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));


        titleText.setText("我的好友");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        mApi = ExpressApi.getApi();
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid =  ((int)sharePh.getSharedPreference("id",0)+"");


        //在加载数据之前配置
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerExpressMethod.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        mAdapter = new ExpressAdapter(this);
        recyclerExpressMethod.setAdapter(mAdapter);
        createSingleExpress();
//        ralApi = ReceivedAddressListApi.getApi();
//        sharePH = new SharedPreferencesHelper(this);
//        token = (String) sharePH.getSharedPreference("token", "");
//        uid = (int) sharePH.getSharedPreference("id", 0);
//        recyclerReceivedAddress.addItemDecoration(new SpaceItemDecoration(20));
//        //在加载数据之前配置
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        recyclerReceivedAddress.setLayoutManager(mLayoutManager);
//        recyclerReceivedAddress.setItemAnimator(new DefaultItemAnimator());
//        //创建一个适配器
//        myAdapter = new ReceivedAddAdapter(this);
//        //获取默认地址
//        createSingleAddress();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_express_method;
    }
    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<ExpressResult> callbackExpress = new Callback<ExpressResult>() {
        @Override
        public void onResponse(Call<ExpressResult> call, Response<ExpressResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    mAdapter.reSetData(mData);
                } else {
                    ToastUtil.showShort(ExpressMethodActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ExpressResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleExpress( ) {
        Call<ExpressResult> call = mApi.getService().createCommitList(token);
        call.enqueue(callbackExpress);
    }
}
