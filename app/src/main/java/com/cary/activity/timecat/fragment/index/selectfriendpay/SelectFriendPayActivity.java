package com.cary.activity.timecat.fragment.index.selectfriendpay;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectFriendPayActivity extends BaseActivity {

    private static final String TAG = SelectFriendPayActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.SelectFriendPayrecyclerview)
    RecyclerView SelectFriendPayrecyclerview;
    @BindView(R.id.swiperefreshlayout_friend)
    SwipeRefreshLayout swiperefreshlayoutFriend;

    private SelectFriendPayAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private FriendListApi mFriendApi;
    private FriendListResult mFriendRes;
    private List<FriendListResult.Data> mData;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("我的好友");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        mFriendApi = FriendListApi.getApi();
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid =  ((int)sharePh.getSharedPreference("id",0)+"");


        //在加载数据之前配置
        linearLayoutManager = new LinearLayoutManager(this);
        SelectFriendPayrecyclerview.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        mAdapter = new SelectFriendPayAdapter(this);
        SelectFriendPayrecyclerview.setAdapter(mAdapter);

        SelectFriendPayrecyclerview.setItemAnimator(new DefaultItemAnimator());
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(SelectFriendPayActivity.this, SetMealDetialActivity.class);
//                intent.putExtra("id", mData.get(postion).getId());
//                startActivity(intent);
//            }
//        });

        swiperefreshlayoutFriend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleFriend();
            }
        });

        SelectFriendPayrecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleFriend();
    }
    @Override
    public int getLayout() {
        return R.layout.activity_select_friend_pay;
    }

    @OnClick({R.id.title_back, R.id.title_text, R.id.rlTitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text:
                break;
            default:
                break;
        }
    }
    public void loadMore() {
        mAdapter.setLoadStatus(SelectFriendPayAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleFriend( );
    }
    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<FriendListResult> callbackFriend = new Callback<FriendListResult>() {
        @Override
        public void onResponse(Call<FriendListResult> call, Response<FriendListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mFriendRes = response.body();
                if ("00".equals(mFriendRes.getCode())) {
                    mData = mFriendRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutFriend.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mFriendRes.getPi().getTotalPage();
                    if (SumPage< 2) {
                        mAdapter.setLoadStatus(SelectFriendPayAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(SelectFriendPayAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(SelectFriendPayActivity.this, mFriendRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FriendListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleFriend( ) {
        Call<FriendListResult> call = mFriendApi.getService().createCommitPage(token,currentpage);
        call.enqueue(callbackFriend);
    }
}
