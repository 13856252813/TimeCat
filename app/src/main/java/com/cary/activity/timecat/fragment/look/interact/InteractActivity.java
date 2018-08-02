package com.cary.activity.timecat.fragment.look.interact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 互动吧
 */
public class InteractActivity extends BaseActivity {

    private static final String TAG = InteractActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_interact_all_text)
    TextView tvInteractAllText;
    @BindView(R.id.tv_interact_all_line)
    TextView tvInteractAllLine;
    @BindView(R.id.ll_interact_all_list)
    LinearLayout llInteractAllList;
    @BindView(R.id.tv_interact_dynamic_text)
    TextView tvInteractDynamicText;
    @BindView(R.id.tv_interact_dynamic_line)
    TextView tvInteractDynamicLine;
    @BindView(R.id.ll_interact_dynamic_list)
    LinearLayout llInteractDynamicList;
    @BindView(R.id.tv_interact_comment_text)
    TextView tvInteractCommentText;
    @BindView(R.id.tv_interact_comment_line)
    TextView tvInteractCommentLine;
    @BindView(R.id.ll_interact_comment_list)
    LinearLayout llInteractCommentList;
    @BindView(R.id.tv_interact_mydynamic_text)
    TextView tvInteractMydynamicText;
    @BindView(R.id.tv_interact_mydynamic_line)
    TextView tvInteractMydynamicLine;
    @BindView(R.id.ll_interact_mydynamic_list)
    LinearLayout llInteractMydynamicList;
    @BindView(R.id.recycler_interact_list)
    RecyclerView recyclerInteractList;
    @BindView(R.id.swiperefreshlayout_interact)
    SwipeRefreshLayout swiperefreshlayoutInteract;

    private InteractAdapter mAdapter;
    private InteractApi mApi;
    private InteractListResult mRes;
    private List<InteractListResult.Data> mData;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int currentpage = 1;
    private int uid;
    private int flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_interact);
        ButterKnife.bind(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("互动吧");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleTextRight.setText("发表动态");
        titleTextRight.setPadding(0, 0, 20, 0);
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.setTextColor(getResources().getColor(R.color.login_color_btn));

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = InteractApi.getApi();
        recyclerInteractList.addItemDecoration(new SpaceItemDecoration(20));
        //在加载数据之前配置
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerInteractList.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        mAdapter = new InteractAdapter(this);
        recyclerInteractList.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new InteractAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                Intent intent = new Intent(InteractActivity.this, InteractDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }
        });

        swiperefreshlayoutInteract.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod(flag);
            }
        });

        recyclerInteractList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        setMethod(flag);
    }

    public void loadMore() {
        mAdapter.setLoadStatus(InteractAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod(flag);
    }
    private void setMethod(int flag) {
        if (flag == -1) {
            createSingleIntegral();
        }else if(flag == 0 || flag == 1){
            createSingleIntegral(flag);
        }else if(flag == 2){
            createSingleIntegralMy();
        }
    }
    @Override
    public int getLayout() {
        return R.layout.activity_interact;
    }

    @OnClick({R.id.title_back, R.id.title_text_right, R.id.ll_interact_all_list, R.id.ll_interact_dynamic_list, R.id.ll_interact_comment_list, R.id.ll_interact_mydynamic_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                Intent intent = new Intent(this, SendDynamicActivity.class);

                startActivity(intent);
                break;
            case R.id.ll_interact_all_list:
                setTextImageView(tvInteractAllText, tvInteractAllLine, true);
                setTextImageView(tvInteractDynamicText, tvInteractDynamicLine, false);
                setTextImageView(tvInteractCommentText, tvInteractCommentLine, false);
                setTextImageView(tvInteractMydynamicText, tvInteractMydynamicLine, false);
                flag = -1;
                setMethod(flag);
                break;
            case R.id.ll_interact_dynamic_list:
                setTextImageView(tvInteractAllText, tvInteractAllLine, false);
                setTextImageView(tvInteractDynamicText, tvInteractDynamicLine, true);
                setTextImageView(tvInteractCommentText, tvInteractCommentLine, false);
                setTextImageView(tvInteractMydynamicText, tvInteractMydynamicLine, false);
                flag = 0;
                setMethod(flag);
                break;
            case R.id.ll_interact_comment_list:
                setTextImageView(tvInteractAllText, tvInteractAllLine, false);
                setTextImageView(tvInteractDynamicText, tvInteractDynamicLine, false);
                setTextImageView(tvInteractCommentText, tvInteractCommentLine, true);
                setTextImageView(tvInteractMydynamicText, tvInteractMydynamicLine, false);
                flag = 1;
                setMethod(flag);
                break;
            case R.id.ll_interact_mydynamic_list:
                setTextImageView(tvInteractAllText, tvInteractAllLine, false);
                setTextImageView(tvInteractDynamicText, tvInteractDynamicLine, false);
                setTextImageView(tvInteractCommentText, tvInteractCommentLine, false);
                setTextImageView(tvInteractMydynamicText, tvInteractMydynamicLine, true);
                flag = 2;
                setMethod(flag);
                break;
        }
    }


    private void setTextImageView(TextView tv, TextView iv, boolean tiflag) {
        if (tiflag) {
            tv.setTextColor(getResources().getColor(R.color.login_color_btn));
            iv.setBackgroundColor(getResources().getColor(R.color.login_color_btn));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_three));
            iv.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }
    private int SumPage = 1;
    //加载数据
    private Callback<InteractListResult> callbackInteract = new Callback<InteractListResult>() {
        @Override
        public void onResponse(Call<InteractListResult> call, Response<InteractListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutInteract.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    SumPage = mRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(InteractAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(InteractAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(InteractActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<InteractListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleIntegral(int type) {
        Call<InteractListResult> call = mApi.getService().createCommitPageType(token,type,currentpage);
        call.enqueue(callbackInteract);
    }

    private void createSingleIntegral() {
        Call<InteractListResult> call = mApi.getService().createCommitPageAll(token,currentpage);
        call.enqueue(callbackInteract);
    }
    private void createSingleIntegralMy() {
        Call<InteractListResult> call = mApi.getService().createCommitPageMy(token, uid,currentpage);
        call.enqueue(callbackInteract);
    }
}
