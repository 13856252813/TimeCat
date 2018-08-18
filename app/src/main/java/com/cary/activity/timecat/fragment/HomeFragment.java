package com.cary.activity.timecat.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.banner.BannerApi;
import com.cary.activity.timecat.fragment.index.banner.BannerCommitResult;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressActivity;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressTabActivity;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.SelectTeacherActivity;
import com.cary.activity.timecat.fragment.index.gotogether.GoTogetherPhotoActivity;
import com.cary.activity.timecat.fragment.index.hotscenic.HotScenicActivity;
import com.cary.activity.timecat.fragment.index.hotscenic.HotScenicApi;
import com.cary.activity.timecat.fragment.index.hotscenic.HotScenicCommitResult;
import com.cary.activity.timecat.fragment.index.hotscenic.HotScenicDetialActivity;
import com.cary.activity.timecat.fragment.index.news.MainScrollNewsAdapter;
import com.cary.activity.timecat.fragment.index.news.NewsActivity;
import com.cary.activity.timecat.fragment.index.news.NewsApi;
import com.cary.activity.timecat.fragment.index.news.NewsCommitResult;
import com.cary.activity.timecat.fragment.index.news.NewsDetialActivity;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SelectSetMealActivity;
import com.cary.activity.timecat.fragment.index.selectstore.SelectStoreActivity;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialActivity;
import com.cary.activity.timecat.fragment.index.star.StarListActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.TimeCloudDishActivity;
import com.cary.activity.timecat.fragment.index.timeshop.TimeShopActivity;
import com.cary.activity.timecat.fragment.look.integral.IntegralMallActivity;
import com.cary.activity.timecat.fragment.person.systemmessage.SysMsgApi;
import com.cary.activity.timecat.fragment.person.systemmessage.SysMsgNumResult;
import com.cary.activity.timecat.fragment.person.systemmessage.SystemMessageActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.main.adapter.RecycleViewDivider;
import com.cary.activity.timecat.main.adapter.RecyclerViewGridAdapter;
import com.cary.activity.timecat.main.adapter.RecyclerViewListAdapter;
import com.cary.activity.timecat.main.bean.DateBean;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.BannerLayout;
import com.cary.activity.timecat.util.view.LimitScrollerView;
import com.cary.activity.timecat.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/8.
 * 用activity 管理所有的activity
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();

    ImageView titleBackNotiy;
    TextView titleHomeText;
    TextView homeTitleRight;
    RelativeLayout titleHomeLayout;
    RecyclerView recyclerViewGrid;
    RecyclerView recyclerViewGridTwo;
    RecyclerView recyclerViewThree;
    ImageView redCirclePoint;
    BannerLayout bannerLayout;
    TextView homeNewsPaterMore;

    protected boolean useThemestatusBarColor = false;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = true;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置


    protected int[] GridDrawables = {R.mipmap.mainicon1, R.mipmap.mainicon2, R.mipmap.mainicon3, R.mipmap.mainicon4,
            R.mipmap.mainicon5, R.mipmap.mainicon6, R.mipmap.mainicon7, R.mipmap.mainicon8};
    protected String[] GridNames = {"婚纱摄影", "写真摄影", "宝宝摄影", "礼服馆",
            "时光云盘", "时光超市", "随行拍", "积分商城"};
    protected int[] GridTwoDrawables = {R.mipmap.mainphoto1, R.mipmap.mainphoto2, R.mipmap.mainphoto3, R.mipmap.mainphoto4};
    protected String[] GridTwoNames = {"热门拍摄景点", "总监摄影师推荐", "明星榜", "千款礼服租借"};

    private BannerApi bannerApi;
    private List<String> bannerUrls = new ArrayList<>();
    ;
    private BannerCommitResult bannerComRes;

    private HotScenicApi hotScenicApi;
    private HotScenicCommitResult hotScenicComRes;
    private List<HotScenicCommitResult.Data> mHotScenicData;

    private NewsApi mNewsApi;
    private NewsCommitResult mNewsComRes;
    private List<NewsCommitResult.Data> mNewsData;

    private LimitScrollerView limitScroll;//自动滚动的今日头条
    private MainScrollNewsAdapter mAdapter;

    private String token = "";
    private SharedPreferencesHelper shph;
    private int uid;

    //未读消息
    private SysMsgApi sysMsgApi;
    private SysMsgNumResult mUnNumComRes;
    private String storeName;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_index, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        titleBackNotiy = (ImageView) root.findViewById(R.id.title_home_notify);
        titleHomeText = (TextView) root.findViewById(R.id.title_home_text);
        homeTitleRight = (TextView) root.findViewById(R.id.home_title_right);

        titleHomeLayout = (RelativeLayout) root.findViewById(R.id.title_home_layout);
        recyclerViewGrid = (RecyclerView) root.findViewById(R.id.recyclerViewGrid);
        recyclerViewGridTwo = (RecyclerView) root.findViewById(R.id.recyclerViewGridTwo);
        recyclerViewThree = (RecyclerView) root.findViewById(R.id.recyclerViewThree);
        redCirclePoint = (ImageView) root.findViewById(R.id.redCirclePoint);
        titleBackNotiy.setOnClickListener(this);
        homeTitleRight.setOnClickListener(this);

        titleBackNotiy.setVisibility(View.VISIBLE);
        homeTitleRight.setVisibility(View.VISIBLE);

        titleBackNotiy.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.bell));
        titleHomeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        titleHomeText.setText("首页");
        titleHomeText.setTextColor(getResources().getColor(R.color.color_three));

        bannerLayout = (BannerLayout) root.findViewById(R.id.banner1);

        shph = new SharedPreferencesHelper(getActivity());
        token = (String) shph.getSharedPreference("token", "");
        uid = (int) shph.getSharedPreference("id", 0);

        //是否有未读消息
        sysMsgApi = SysMsgApi.getApi();
        createSingleUnReadMsg();

        //二级标题
        loadGridDate(false, true, GridNames, GridDrawables,
                recyclerViewGrid, R.layout.activity_home_grid_item_layout, 4, 1);
        recyclerViewGrid.setNestedScrollingEnabled(false);

        //中间的四个框
        loadGridDate(false, true, GridTwoNames, GridTwoDrawables,
                recyclerViewGridTwo, R.layout.activity_home_grid_two_item_layout, 2, 2);
        recyclerViewGridTwo.setNestedScrollingEnabled(false);
        recyclerViewGridTwo.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.white)));

        //banner 数据
        bannerApi = BannerApi.getApi();
        createSingleBanner();
        if (bannerLayout != null && bannerUrls.size() > 0) {
            bannerLayout.setViewUrls(bannerUrls, null);
        }
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                if (0 == bannerComRes.getData().get(position).getType()) {
                    intent.setClass(getActivity(), SetMealDetialActivity.class);
                    intent.putExtra("id", bannerComRes.getData().get(position).getId());
                    startActivity(intent);
                 } else if (1 == bannerComRes.getData().get(position).getType()) {
                    intent.setClass(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", bannerComRes.getData().get(position).getUrl());
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "点击的此处位置position:" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //获取热门景点数据
        hotScenicApi = hotScenicApi.getApi();
        createSingleHotScenic();
        //获取热点咨询数据
        mNewsApi = mNewsApi.getApi();
        mAdapter = new MainScrollNewsAdapter(getActivity());
        createSingleNews();

        /********************************begin首页滚动begin****************************************/
        limitScroll = (LimitScrollerView) root.findViewById(R.id.limitScroll);
        //API：4、设置条目点击事件
        limitScroll.setOnItemClickListener(new LimitScrollerView.OnItemClickListener() {
            @Override
            public void onItemClick(Object obj) {
                NewsCommitResult.Data mData = (NewsCommitResult.Data) obj;
                Intent intent = new Intent(getActivity(), NewsDetialActivity.class);
                intent.putExtra("id", mData.getId());
                startActivity(intent);
            }
        });
        //API:1、设置数据适配器
        /********************************end首页滚动end****************************************/
        //滚动的更多
        homeNewsPaterMore = root.findViewById(R.id.home_news_pater_more);
        homeNewsPaterMore.setOnClickListener(this);

    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.title_home_notify:
                //通知
                intent.setClass(getActivity(), SystemMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.home_title_right:
                //选择门店
                intent.setClass(getActivity(), SelectStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.home_news_pater_more:
                //更多资讯
                intent.setClass(getActivity(), NewsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        storeName = (String) shph.getSharedPreference("storeName", "");
        if (!TextUtils.isEmpty(storeName)) {
            homeTitleRight.setText(storeName);
        } else {
            homeTitleRight.setText("");
        }
    }

    //RecyclerView实现GridView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadGridDate(Boolean inversion, Boolean orientation,
                              String[] GridNames, int[] GridDrawables,
                              final RecyclerView recyclerViewGrid, int layoutId,
                              int spanCount, final int GridFlag) {

//集合对象
        ArrayList<DateBean> dateBeanArrayList = new ArrayList<>();
//给Bean类放数据，把装好数据的Bean类放到集合里
        for (int i = 0; i < GridDrawables.length; i++) {
//创建Bean类对象
            DateBean dateBean = new DateBean();
//给benu类对象添加图片和信息
            dateBean.icon = GridDrawables[i];
            dateBean.name = GridNames[i];
//把Bean类放入集合
            dateBeanArrayList.add(dateBean);
        }
//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewGridAdapter recyclerViewGridAdapter = new RecyclerViewGridAdapter(getActivity(), dateBeanArrayList, layoutId);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
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
                Intent intent = new Intent();
                if (GridFlag == 1) {
                    switch (postion) {
                        case 0:
//                            ToastUtil.showShort(getActivity(), "婚纱摄影");
                            intent.setClass(getActivity(), SelectSetMealActivity.class);
                            intent.putExtra("type", "1");
                            startActivity(intent);
                            break;
                        case 1:
//                            ToastUtil.showShort(getActivity(), "写真摄影");
                            intent.setClass(getActivity(), SelectSetMealActivity.class);
                            intent.putExtra("type", "2");
                            startActivity(intent);
                            break;
                        case 2:
//                            ToastUtil.showShort(getActivity(), "宝宝摄影");
                            intent.setClass(getActivity(), SelectSetMealActivity.class);
                            intent.putExtra("type", "3");
                            startActivity(intent);
                            break;
                        case 3:
                            startActivity(new Intent(getActivity(), FullDressActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(getActivity(), TimeCloudDishActivity.class));
                            break;
                        case 5:
//                            ToastUtil.showShort(getActivity(), "时光超市");
                            startActivity(new Intent(getActivity(), TimeShopActivity.class));
                            break;
                        case 6:
//                            ToastUtil.showShort(getActivity(),"随行拍");
                            startActivity(new Intent(getActivity(), GoTogetherPhotoActivity.class));
                            break;
                        case 7:
//                            ToastUtil.showShort(getActivity(), "积分商城");
                            intent.setClass(getActivity(), IntegralMallActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                } else if (GridFlag == 2) {
                    //第二列
                    switch (postion) {
                        case 0:
//                            ToastUtil.showShort(getActivity(), "热门景点");
                            intent.setClass(getActivity(), HotScenicActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
//                            ToastUtil.showShort(getActivity(), "总监摄影师推荐");
                            intent.setClass(getActivity(), SelectTeacherActivity.class);
                            intent.putExtra("teacherflag", "camcer");
                            startActivity(intent);
                            break;
                        case 2:
//                            ToastUtil.showShort(getActivity(), "明星榜");
                            intent.setClass(getActivity(), StarListActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
//                            千款礼服租借
                            intent.setClass(getActivity(), FullDressTabActivity.class);
                            intent.putExtra("flagtag", "1");
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    //热门景点   RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, final List<HotScenicCommitResult.Data> mHotScenicData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewListAdapter recyclerViewGridAdapter = new RecyclerViewListAdapter(getActivity(), mHotScenicData, layoutId);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
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
//                ToastUtil.showShort(getActivity(), "Grid 1 postion:" + postion);
                Intent intent = new Intent(getActivity(), HotScenicDetialActivity.class);
                intent.putExtra("id", mHotScenicData.get(postion).getId());
                startActivity(intent);
            }
        });
    }


    @SuppressLint("HandlerLeak")
    private Handler bannerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                if (bannerLayout != null && bannerUrls.size() > 0) {
                    bannerLayout.setViewUrls(bannerUrls, null);
                }
            }
        }
    };
    //banner 获取数据
    private Callback<BannerCommitResult> callbackbanner = new Callback<BannerCommitResult>() {
        @Override
        public void onResponse(Call<BannerCommitResult> call, Response<BannerCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                bannerComRes = response.body();
                if ("00".equals(bannerComRes.getCode())) {
                    if (bannerUrls.size() == bannerComRes.getData().size()) {

                    } else {
                        bannerUrls.clear();
                        for (int i = 0; i < bannerComRes.getData().size(); i++) {
                            bannerUrls.add(bannerComRes.getData().get(i).getImgurl());
                        }
                        bannerHandler.sendEmptyMessage(1000);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), bannerComRes.getMsg());
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
        Call<BannerCommitResult> call = bannerApi.getService().createCommit();
        call.enqueue(callbackbanner);
    }

    private void createMapBanner() {
        Map map = new HashMap();
        Call<BannerCommitResult> call = bannerApi.getService().createCommit(map);
        call.enqueue(callbackbanner);
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
                    loadListDate(false, true, recyclerViewThree, R.layout.activity_home_list_item_layout, mHotScenicData);
                    recyclerViewThree.setNestedScrollingEnabled(false);
                } else {
                    ToastUtil.showShort(getActivity(), hotScenicComRes.getMsg());
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

    //    动态咨询
    private Callback<NewsCommitResult> callbackNews = new Callback<NewsCommitResult>() {
        @Override
        public void onResponse(Call<NewsCommitResult> call, Response<NewsCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mNewsComRes = response.body();
                if ("00".equals(mNewsComRes.getCode())) {
                    mNewsData = mNewsComRes.getData();
                    //滚动数据
                    mAdapter.setDatas(mNewsData, limitScroll);
                    limitScroll.setDataAdapter(mAdapter);
                } else {
                    ToastUtil.showShort(getActivity(), mNewsComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewsCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleNews() {
        Call<NewsCommitResult> call = mNewsApi.getService().createCommit(token);
        call.enqueue(callbackNews);
    }

    //    是否有未读消息
    private Callback<SysMsgNumResult> callbackUnReadNum = new Callback<SysMsgNumResult>() {
        @Override
        public void onResponse(Call<SysMsgNumResult> call, Response<SysMsgNumResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mUnNumComRes = response.body();
                if ("00".equals(mUnNumComRes.getCode())) {
                    int unReadNum = mUnNumComRes.getData();
                    //有消息则 显示红点 否则不显示
                    if (unReadNum > 0) {
                        redCirclePoint.setVisibility(View.VISIBLE);
                    } else {
                        redCirclePoint.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mUnNumComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SysMsgNumResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleUnReadMsg() {
        Call<SysMsgNumResult> call = sysMsgApi.getService().createCommitUnReadNum(token, uid);
        call.enqueue(callbackUnReadNum);
    }

}