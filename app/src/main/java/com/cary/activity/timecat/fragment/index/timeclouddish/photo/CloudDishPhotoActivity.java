package com.cary.activity.timecat.fragment.index.timeclouddish.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ImageInfoObj;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ImageWidgetInfoObj;
import com.cary.activity.timecat.fragment.index.timeclouddish.adapter.CloudDishPhotoRecyclerAdapter;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.ShowImageActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;
import com.cary.activity.timecat.util.view.PictureDoPopu;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件夹、文件列表
 */
public class CloudDishPhotoActivity extends BaseActivity {

    private static final String TAG = CloudDishPhotoActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.recyclerViewCloudPhoto)
    RecyclerView recycler;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.ivCloudDoImage)
    ImageView ivCloudDoImage;
    @BindView(R.id.swiperefreshlayout_clouddish)
    SwipeRefreshLayout swiperefreshlayoutClouddish;
    @BindView(R.id.tv_cloud_dish_cut)
    TextView tvCloudDishCut;
    @BindView(R.id.tv_cloud_dish_copy)
    TextView tvCloudDishCopy;
    @BindView(R.id.tv_cloud_dish_paste)
    TextView tvCloudDishPaste;
    @BindView(R.id.tv_cloud_dish_del)
    TextView tvCloudDishDel;
    @BindView(R.id.ll_cloud_dish_bottom_do)
    LinearLayout llCloudDishBottomDo;

    private TreeMap<CloudDishPhotoResult.Data, Boolean> mMap;//判断选中了那几条
    private CloudDishPhotoResult cpcr;
    private List<CloudDishPhotoResult.Data> mList;
    private CloudDishPhotoApi cdApi;
    private SharedPreferencesHelper shareHelper;
    private PictureDoPopu menuWindow;
    private CloudDishPhotoRecyclerAdapter mAdapter;

    private String token;
    private int userId;//Y用户id
    private String floderId;//文件夹id
    private int SumPage = 1;
    private int currentpage = 1;//页数

    private ImageInfoObj imageInfoObj;
    private ImageWidgetInfoObj imageWidgetInfoObj;
    private String idStr = "";//选中的id
    private String idPic;//文件id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cloud_dish_photo);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ButterKnife.bind(this);
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.setText("编辑");
        titleTextRight.setPadding(0, 0, 20, 0);
        titleText.setText("选择样片");

        if(!TextUtils.isEmpty(idStr)){
            idStr = getIntent().getStringExtra("idStr");
            createSingleList("");
        }

        ivCloudDoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        shareHelper = new SharedPreferencesHelper(this);
        cdApi = CloudDishPhotoApi.getApi();
        userId = (int) shareHelper.getSharedPreference("id", 0);
        token = (String) shareHelper.getSharedPreference("token", "");
        floderId = getIntent().getStringExtra("id");

        //列表刷新
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CloudDishPhotoRecyclerAdapter(this);
        recycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickCoustomListener(new CloudDishPhotoRecyclerAdapter.OnItemClickCoustomListener() {
            @Override
            public void onClickView(View v, int position) {
                //跳转新的详情界面
                Intent intent = new Intent();
                intent.setClass(CloudDishPhotoActivity.this, ShowImageActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onImageView(int position) {
                //弹出界面
                menuWindow = new PictureDoPopu(CloudDishPhotoActivity.this, clickListener);
                menuWindow.show();
                idPic = mList.get(position).getId() + "";
            }

            @Override
            public void onSelectView(TreeMap<CloudDishPhotoResult.Data, Boolean> mMap) {
                //判断 map的值 为true的
                CloudDishPhotoActivity.this.mMap = mMap;
            }
        });

        swiperefreshlayoutClouddish.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleList(floderId);
            }
        });

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
//        recycler.setNestedScrollingEnabled(false);
        createSingleList(floderId);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_cloud_dish_photo;
    }

    @OnClick({R.id.title_back, R.id.title_text_right, R.id.tv_cloud_dish_cut,
            R.id.tv_cloud_dish_copy, R.id.tv_cloud_dish_paste, R.id.tv_cloud_dish_del})
    public void onViewClicked(View view) {
        for (CloudDishPhotoResult.Data data : mMap.keySet()) {
            if (mMap.get(data)) {
                idStr += data.getId() + ",";
            }
        }
        if (!TextUtils.isEmpty(idStr)) {
            idStr = idStr.substring(0, idStr.length() - 1);
        }
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                String str = titleTextRight.getText().toString().trim();
                if ("编辑".equals(str)) {
                    mAdapter.setShowEdit(true);
                    titleTextRight.setText("完成");
                    llCloudDishBottomDo.setVisibility(View.VISIBLE);
                } else if ("完成".equals(str)) {
                    titleTextRight.setText("编辑");
                    //跳转到新的界面 进行粘贴
                    llCloudDishBottomDo.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_cloud_dish_cut:
                llCloudDishBottomDo.setVisibility(View.GONE);
                createSinglecopy(idStr);
                break;
            case R.id.tv_cloud_dish_copy:
                llCloudDishBottomDo.setVisibility(View.GONE);
                createSinglecopy(idStr);
                break;
            case R.id.tv_cloud_dish_paste:
                llCloudDishBottomDo.setVisibility(View.GONE);
                createSinglecopy(idStr);
                break;
            case R.id.tv_cloud_dish_del:
                llCloudDishBottomDo.setVisibility(View.GONE);
                createSingledel(idStr);
                break;
        }
    }

    public void loadMore() {
        mAdapter.setLoadStatus(CloudDishPhotoRecyclerAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleList(floderId);
    }

    /***********************************************************************************************
     * 自定义adapter的监听事件，将选中的id，用map的形式传递出来                                         *
     * 弹框 文件的操作监听   单个文件的操作                                                                         *
     ***********************************************************************************************/
    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_picture_do_copy:
//                    ToastUtil.showShort(CloudDishPhotoActivity.this, "复制");
                    createSinglecopy(idPic);
                    break;
                case R.id.btn_picture_do_cut:
//                    ToastUtil.showShort(CloudDishPhotoActivity.this, "剪切");
                    createSinglecut(idPic);
                    break;
                case R.id.btn_picture_do_delete:
//                    ToastUtil.showShort(CloudDishPhotoActivity.this, "删除");
                    createSingledel(idPic);
                    break;
                case R.id.btn_picture_do_note:
//                    ToastUtil.showShort(CloudDishPhotoActivity.this, "备注");
                    //跳转到详情界面

                    break;
                case R.id.btn_picture_do_cancel:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
    /***********************************************************************************************
     *                      获取文件列表                                                            *
     ***********************************************************************************************/
    private Callback<CloudDishPhotoResult> callbacklist = new Callback<CloudDishPhotoResult>() {
        @Override
        public void onResponse(Call<CloudDishPhotoResult> call, Response<CloudDishPhotoResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                cpcr = response.body();
                if ("00".equals(cpcr.getCode())) {
                    mList = cpcr.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mList);
                        swiperefreshlayoutClouddish.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mList);
                    }
                    //总弄页数
                    SumPage = cpcr.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(CloudDishPhotoRecyclerAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(CloudDishPhotoRecyclerAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
//                    loadListDate(false, true, recycler, mList);
//                    recycler.setNestedScrollingEnabled(false);
                } else {
                    ToastUtil.showShort(CloudDishPhotoActivity.this, cpcr.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<CloudDishPhotoResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleList(String floderId) {
        Call<CloudDishPhotoResult> call = cdApi.getService().createCommit(token, floderId, currentpage);
        call.enqueue(callbacklist);
    }

    /***********************************************************************************************
     *                      删除文件                                                           *
     ***********************************************************************************************/
    private Callback<ModelBeanData> callbackdel = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData datamodel = response.body();
                if ("00".equals(datamodel.getCode())) {
                    ToastUtil.showShort(CloudDishPhotoActivity.this, "删除成功");
                } else {
                    ToastUtil.showShort(CloudDishPhotoActivity.this, datamodel.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingledel(String picId) {
        Call<ModelBeanData> call = cdApi.getService().createCommitDel(token, picId);
        call.enqueue(callbackdel);
    }

    private void createSinglecopy(String picId) {
        Call<ModelBeanData> call = cdApi.getService().createCommitCopy(token, picId);
        call.enqueue(callbackdel);
    }

    private void createSinglecut(String picId) {
        Call<ModelBeanData> call = cdApi.getService().createCommitCut(token, picId);
        call.enqueue(callbackdel);
    }

}
