package com.cary.activity.timecat.fragment.look.interact;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

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

/**
 * 互动吧详情
 */
public class InteractDetialActivity extends BaseActivity {

    private static final String TAG = InteractDetialActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_interact_item_head)
    ImageView ivInteractItemHead;
    @BindView(R.id.tv_interact_name_nick)
    TextView tvInteractNameNick;
    @BindView(R.id.tv_interact_description)
    TextView tvInteractDescription;
    @BindView(R.id.recycler_interact_list)
    RecyclerView recyclerInteractList;
    @BindView(R.id.videoView_interact)
    VideoView videoViewInteract;
    @BindView(R.id.iv_videoView_interact)
    ImageView ivVideoViewInteract;
    @BindView(R.id.iv_interact_playvideo)
    ImageView ivInteractPlayvideo;
    @BindView(R.id.rl_interact_video)
    RelativeLayout rlInteractVideo;
    @BindView(R.id.recycler_interact_order_comment)
    RecyclerView recyclerInteractOrderComment;
    @BindView(R.id.ratingbar_interact_order_comment)
    RatingBar ratingbarInteractOrderComment;
    @BindView(R.id.iv_interact_order_image)
    ImageView ivInteractOrderImage;
    @BindView(R.id.tv_interact_order_title)
    TextView tvInteractOrderTitle;
    @BindView(R.id.tv_interact_order_price)
    TextView tvInteractOrderPrice;
    @BindView(R.id.tv_interact_order_old_price)
    TextView tvInteractOrderOldPrice;
    @BindView(R.id.tv_interact_order_sell_number)
    TextView tvInteractOrderSellNumber;
    @BindView(R.id.ll_interact_order_comment)
    LinearLayout llInteractOrderComment;
    @BindView(R.id.tv_interact_time)
    TextView tvInteractTime;
    @BindView(R.id.tv_interact_comment)
    TextView tvInteractComment;
    @BindView(R.id.tv_interact_praise)
    TextView tvInteractPraise;
    @BindView(R.id.iv_interact_parise_grid)
    ImageView ivInteractPariseGrid;
    @BindView(R.id.interact_praise_recyclerview)
    RecyclerView interactPraiseRecyclerview;
    @BindView(R.id.rl_interact_parise_grid)
    RelativeLayout rlInteractPariseGrid;
    @BindView(R.id.iv_interact_comment_list)
    ImageView ivInteractCommentList;
    @BindView(R.id.interact_comment_recyclerview)
    RecyclerView interactCommentRecyclerview;
    @BindView(R.id.rl_interact_comment_list)
    RelativeLayout rlInteractCommentList;
    @BindView(R.id.et_interact_detial_add_comment)
    EditText etInteractDetialAddComment;
    @BindView(R.id.btn_interact_add_comment)
    Button btnInteractAddComment;
    @BindView(R.id.ll_interact_detial_head)
    LinearLayout llInteractDetialHead;
    @BindView(R.id.swiperefreshlayout_interact_comment)
    SwipeRefreshLayout swiperefreshlayoutInteractComment;
    @BindView(R.id.ll_interact_add_comment)
    LinearLayout llInteractAddComment;

    private RecyclerGridViewAdapter recyclerGridViewAdapter;
    private List<InteractDetialResult.Zans> priaseData;
    private RecyclerListViewAdapter recyclerListViewAdapter;
    private List<InteractDetialResult.Evas> commentData;
    private InteractApi mApi;
    private InteractDetialResult mRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int id;
    private InteractCommentAddResult commentRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        titleText.setText("详情");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        id = getIntent().getIntExtra("id", 0);
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = InteractApi.getApi();

        GridLayoutManager mgr = new GridLayoutManager(this, 9);
//        mgr.setOrientation(GridLayout.HORIZONTAL);
        interactPraiseRecyclerview.setLayoutManager(mgr);
//        int spanCount = 9;//跟布局里面的spanCount属性是一致的
//        int spacing = 2;//每一个矩形的间距
//        boolean includeEdge = false;//如果设置成false那边缘地带就没有间距s
//        //设置每个item间距
        interactPraiseRecyclerview.addItemDecoration(new SpaceItemDecoration(10));


        interactCommentRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        //评论内容
        recyclerListViewAdapter = new RecyclerListViewAdapter(this);//添加适配器，这里适配器刚刚装入了数据
        interactCommentRecyclerview.setAdapter(recyclerListViewAdapter);
        recyclerListViewAdapter.setItemListener(new RecyclerListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        interactCommentRecyclerview.setNestedScrollingEnabled(false);
        createSingleInteract();

//        recyclerGridViewAdapter.setOnRecyclerViewItemListener(new RecyclerGridViewAdapter.OnRecyclerViewItemListener() {
//            @Override
//            public void onItemClickListener(View view, int position) {
//                Toast.makeText(InteractDetialActivity.this, "onClick:" + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClickListener(View view, int position) {
//                Toast.makeText(InteractDetialActivity.this, "onLongClick:" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_interact_detial;
    }

    @OnClick({R.id.title_back, R.id.btn_interact_add_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_interact_add_comment:
                String commentStr = etInteractDetialAddComment.getText().toString().trim();
                if (!TextUtils.isEmpty(commentStr)) {
                    createSingleInteractAddComment(commentStr);
                }
                break;
        }
    }

    //加载数据
    private Callback<InteractDetialResult> callbackInteract = new Callback<InteractDetialResult>() {
        @Override
        public void onResponse(Call<InteractDetialResult> call, Response<InteractDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    tvInteractNameNick.setText(mRes.getData().getNickname());
                    tvInteractDescription.setText(mRes.getData().getContent());
                    tvInteractPraise.setText(mRes.getData().getZanCount() + "点赞");
                    tvInteractTime.setText(mRes.getData().getReleaseTime());
                    tvInteractComment.setText(mRes.getData().getEvaCount() + "评论");
                    priaseData = mRes.getData().getZans();
                    commentData = mRes.getData().getEvas();
                    //点赞头像
                    recyclerGridViewAdapter = new RecyclerGridViewAdapter(InteractDetialActivity.this, priaseData);
                    interactPraiseRecyclerview.setAdapter(recyclerGridViewAdapter);

                    recyclerListViewAdapter.reSetData(mRes.getData().getEvas());

                } else {
                    ToastUtil.showShort(InteractDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<InteractDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleInteract() {
        Call<InteractDetialResult> call = mApi.getService().createCommitID(token, id);
        call.enqueue(callbackInteract);
    }

    /*********************增加评论********************/
    private Callback<InteractCommentAddResult> callbackInteractaddcomment = new Callback<InteractCommentAddResult>() {
        @Override
        public void onResponse(Call<InteractCommentAddResult> call, Response<InteractCommentAddResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                commentRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    etInteractDetialAddComment.setText("");
                    etInteractDetialAddComment.clearFocus();
                    createSingleInteract();
//                    List<InteractDetialResult.Evas> evasList = new InteractDetialResult().getData().getEvas();
//                    InteractDetialResult.Evas evas = (InteractDetialResult.Evas) new InteractDetialResult().getData().getEvas();
//                    evas.setEvaId(commentRes.getData().getEvaId());
//                    evas.setContent(commentRes.getData().getContent());
//                    evas.setEvaImgurl(commentRes.getData().getEvaImgurl());
//                    evas.setEvaNickname(commentRes.getData().getEvaNickname());
//                    evas.setEvaTime(commentRes.getData().getEvaTime());
//                    evas.setId(commentRes.getData().getId());
//                    evas.setImgurl(commentRes.getData().getImgurl());
//                    evas.setInteractiveId(commentRes.getData().getInteractiveId());
//                    evas.setUid(commentRes.getData().getUid());
//                    evas.setEvaUid(commentRes.getData().getEvaUid());
//                    evasList.add(evas);
//                    recyclerListViewAdapter.addAll(evasList);
                } else {
                    ToastUtil.showShort(InteractDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<InteractCommentAddResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleInteractAddComment(String commentContent) {
        Call<InteractCommentAddResult> call = mApi.getService().createCommitAddComment(token, id, uid, commentContent);
        call.enqueue(callbackInteractaddcomment);
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;
            //由于每行都只有9个，所以第一个都是9的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 9 == 0) {
                outRect.left = 0;
            }
        }

    }

}
