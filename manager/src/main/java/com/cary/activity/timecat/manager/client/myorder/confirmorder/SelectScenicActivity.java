package com.cary.activity.timecat.manager.client.myorder.confirmorder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.adapter.RecyclerViewListAdapter;
import com.cary.activity.timecat.manager.teacher.fragment.hotscenic.HotScenicApi;
import com.cary.activity.timecat.manager.teacher.fragment.hotscenic.HotScenicCommitResult;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 选择景点
 */
@SuppressLint("Registered")
public class SelectScenicActivity extends Activity {
    private static final String TAG = SelectScenicActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_select_scenic)
    RecyclerView recyclerSelectScenic;
    private HotScenicApi hotScenicApi;
    private HotScenicCommitResult hotScenicComRes;
    private List<HotScenicCommitResult.Data> mHotScenicData;
    private String  token = "";
    private SharedPreferencesHelper shph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scenic);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("选择景点");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        shph = new SharedPreferencesHelper(this);
        token = (String) shph.getSharedPreference("toen","");

        //获取热门景点数据
        hotScenicApi = hotScenicApi.getApi();
        createSingleHotScenic();
    }

    //热门景点
    private Callback<HotScenicCommitResult> callbackHotScenic = new Callback<HotScenicCommitResult>() {
        @Override
        public void onResponse(Call<HotScenicCommitResult> call, Response<HotScenicCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                hotScenicComRes = response.body();
                if ("00".equals(hotScenicComRes.getCode())) {
                    mHotScenicData = hotScenicComRes.getData();
                    //热门景点
                    loadListDate(false, true, recyclerSelectScenic, R.layout.activity_home_list_item_layout, mHotScenicData);
                    recyclerSelectScenic.setNestedScrollingEnabled(false);
                } else {
                    ToastUtil.showShort(SelectScenicActivity.this, hotScenicComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<HotScenicCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleHotScenic() {
        Call<HotScenicCommitResult> call = hotScenicApi.getService().createCommit(token);
        call.enqueue(callbackHotScenic);
    }

    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, List<HotScenicCommitResult.Data> mHotScenicData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewListAdapter recyclerViewGridAdapter = new RecyclerViewListAdapter(this);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(inversion);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ToastUtil.showShort(SelectScenicActivity.this, "Grid 1 postion:" + postion);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });
    }
}
