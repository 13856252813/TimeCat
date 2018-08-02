package com.cary.activity.timecat.manager.client.selectstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.client.selectstore.detial.StoreDetialActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 选择门店
 */
@SuppressLint("Registered")
public class SelectStoreActivity extends AppCompatActivity {
    private static final String TAG = SelectStoreActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.recyclerViewSelectStore)
    RecyclerView recyclerViewSelectStore;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.swipeRefreshlayout_store)
    SwipeRefreshLayout swipeRefreshlayoutStore;

    private SelectStoreApi mSStoreApi;
    private SelectStoreCommitResult mSStoreComRes;
    private List<SelectStoreCommitResult.Data> mSStoreData;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewSStoreAdapter mAdapter;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_store);
        ButterKnife.bind(this);
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleText.setText("选择门店");

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");

        mSStoreApi = SelectStoreApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectStore.setLayoutManager(mLayoutManager);
        recyclerViewSelectStore.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerViewSStoreAdapter(this);
        recyclerViewSelectStore.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(SelectStoreActivity.this, StoreDetialActivity.class);
                intent.putExtra("id", mSStoreData.get(postion).getId());
                startActivityForResult(intent,1001);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });
        //选择按钮点击
        mAdapter.setViewClickListener(new RecyclerViewSStoreAdapter.OnItemViewClickListener() {
            @Override
            public void OnClickView(View v,int position) {
                switch (v.getId()){
                    case R.id.tv_select_store:
                        int StoreId = mSStoreData.get(position).getId();
                        String storeName = mSStoreData.get(position).getCity();
//                        ToastUtil.showShort(SelectStoreActivity.this,position+":storeName="+storeName);
                        sharePh.put("storeId",StoreId);
                        sharePh.put("storeName",storeName);
                        finish();
                        break;
                }
            }
        });

        swipeRefreshlayoutStore.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerViewSelectStore.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingle();
    }


    public void loadMore() {
        mAdapter.setLoadStatus(RecyclerViewSStoreAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    private int SumPage = 1;
    private Callback<SelectStoreCommitResult> callbackStore = new Callback<SelectStoreCommitResult>() {
        @Override
        public void onResponse(Call<SelectStoreCommitResult> call, Response<SelectStoreCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mSStoreComRes = response.body();
                if ("00".equals(mSStoreComRes.getCode())) {
                    mSStoreData = mSStoreComRes.getData();
//                    loadListDate(false, true, recyclerViewSelectStore, R.layout.activity_select_store_item_layout, mSStoreData);
//                    recyclerViewSelectStore.setNestedScrollingEnabled(false);
                    if (currentpage == 1) {
                        mAdapter.reSetData(mSStoreData);
                        swipeRefreshlayoutStore.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mSStoreData);
                    }
                    SumPage = mSStoreComRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(RecyclerViewSStoreAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(RecyclerViewSStoreAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(SelectStoreActivity.this, mSStoreComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SelectStoreCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<SelectStoreCommitResult> call = mSStoreApi.getService().createCommitPage(token, currentpage);
        call.enqueue(callbackStore);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1001:
                finish();
                break;
        }
    }

    //    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
//    private void loadListDate(Boolean inversion, Boolean orientation,
//                              final RecyclerView recyclerViewGrid, int layoutId, List<SelectStoreCommitResult.Data> mSStoreData) {
////创建适配器adapter对象 参数1.上下文 2.数据加载集合
//       mAdapter= new RecyclerViewSStoreAdapter(this, mSStoreData, layoutId);
////设置适配器
//        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
////布局管理器对象 参数1.上下文 2.规定显示的行数
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
////通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
//        gridLayoutManager.setReverseLayout(inversion);
////设置RecycleView显示的方向是水平还是垂直
////GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
//// 三元运算符
//        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
////设置布局管理器， 参数linearLayoutManager对象
//        recyclerViewGrid.setLayoutManager(gridLayoutManager);
//        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                ToastUtil.showShort(SelectStoreActivity.this, "Grid 1 postion:" + postion);
//                Intent intent = new Intent(SelectStoreActivity.this, SelectSetMealActivity.class);
//                intent.putExtra("id", "123");
//                startActivity(intent);
//            }
//        });
//    }

}
