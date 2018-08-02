package com.cary.activity.timecat.fragment.person.receiveadd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.detial.DefaultRecAddResult;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialOrderActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 收货地址
 */
public class ReceivedAddressActivity extends BaseActivity {

    private static final String TAG = ReceivedAddressActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_received_address)
    RecyclerView recyclerReceivedAddress;
    @BindView(R.id.btn_received_address_newadd)
    Button btnReceivedAddressNewadd;
    @BindView(R.id.ll_received_address_btn)
    LinearLayout llReceivedAddressBtn;
    @BindView(R.id.swiperefreshlayout_receivedadddress)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ReceivedAddressListApi ralApi;
    private SharedPreferencesHelper sharePH;
    private ReceivedAddressListResult readressRes;

    private ReceivedAddAdapter myAdapter;
    //    private List<String> mData = new ArrayList<>();
    private int managerflag = 0;
    private int currentPage = 1;
    private NewReceivedResult.Data newReceived;
    private NewReceivedResult nrres;
//    defaultaddress  传递地址

    private DefaultRecAddResult defaultAddRes;//默认收货地址
    private int uid;
    private String token;
    private int currentpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_received_address);
        ButterKnife.bind(this);
        titleText.setText("收货地址");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleTextRight.setText("管理");
        titleTextRight.setPadding(0, 0, 20, 0);
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.setTextColor(getResources().getColor(R.color.login_color_btn));

        ralApi = ReceivedAddressListApi.getApi();
        sharePH = new SharedPreferencesHelper(this);
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);

        recyclerReceivedAddress.addItemDecoration(new SpaceItemDecoration(20));
        //在加载数据之前配置
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerReceivedAddress.setLayoutManager(mLayoutManager);
        recyclerReceivedAddress.setItemAnimator(new DefaultItemAnimator());
        //创建一个适配器
        myAdapter = new ReceivedAddAdapter(this);
        recyclerReceivedAddress.setAdapter(myAdapter);
//添加新数据
//        newReceived = (NewReceivedResult.Data) getIntent().getSerializableExtra("received");
//        if (newReceived != null) {
//            ReceivedAddressListResult.Data msg = (ReceivedAddressListResult.Data) new ReceivedAddressListResult().getData();
//            msg.setArea(newReceived.getArea());
//            msg.setCity(newReceived.getCity());
//            msg.setDetail(newReceived.getDetail());
//            msg.setProvince(newReceived.getProvince());
//            msg.setIsDefault(newReceived.getIsDefault());
//            msg.setMobile(newReceived.getMobile());
//            msg.setName(newReceived.getName());
//            msg.setUid(newReceived.getUid());
//            msg.setId(newReceived.getId());
//            myAdapter.addItem(0, msg);
//        }
        currentpage = 1;
        myAdapter.setRecyclerViewOnItemLongListener(new ReceivedAddAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onItemDefaultListener(int id) {
//                ToastUtil.showShort(ReceivedAddressActivity.this, "设置默认");
                createSingleUpdate(id);
            }

            @Override
            public void onClickEditListener(int id) {
//                ToastUtil.showShort(ReceivedAddressActivity.this, "编辑地址");
                Intent intent = new Intent(ReceivedAddressActivity.this, AddReceiveAddressActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void onClickDeleteListener(int id) {
//                ToastUtil.showShort(ReceivedAddressActivity.this, "删除地址");
                createSingleDel(id);
            }

            @Override
            public void onClickSetAddress(int id) {
                //选择地址
                Intent intent = new Intent(ReceivedAddressActivity.this, FullDressDetialOrderActivity.class);
                intent.putExtra("id", id);
                setResult(1001, intent);
                finish();
            }


        });

        //获取默认地址
        createSingleAddress();

        //刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle(currentPage);
            }
        });

        recyclerReceivedAddress.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override//滚动状态变化时回调
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                // mLastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                //滑动停止并且底部不可滚动（即滑动到底部） 加载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !(ViewCompat.canScrollVertically(recyclerView, 1))) {
                    currentpage++;
                    loadMore(currentpage);
                }
            }

            @Override//滚动时回调
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_received_address;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取数据
        createSingle(currentPage);

    }

    @OnClick({R.id.title_back, R.id.title_text_right, R.id.btn_received_address_newadd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                if (managerflag == 0) {
                    titleTextRight.setText("完成");
                    llReceivedAddressBtn.setVisibility(View.VISIBLE);
                    managerflag = 1;
                    myAdapter.setShowBottom(true);
                    myAdapter.notifyDataSetChanged();
                } else if (managerflag == 1) {
                    titleTextRight.setText("管理");
                    llReceivedAddressBtn.setVisibility(View.GONE);
                    managerflag = 0;
                    myAdapter.setShowBottom(false);
                    myAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_received_address_newadd:
//                ToastUtil.showShort(this, "新增地址");
                Intent intent = new Intent(this, AddReceiveAddressActivity.class);

                startActivity(intent);
                break;
        }
    }

    public void loadMore(int currentpage) {
        myAdapter.setLoadStatus(ReceivedAddAdapter.LoadStatus.LOADING_MORE);
        myAdapter.refresh();
        Log.v(TAG, "currentpage:" + currentpage);
        createSingle(currentpage);
    }

    private Callback<ReceivedAddressListResult> callback = new Callback<ReceivedAddressListResult>() {
        @Override
        public void onResponse(Call<ReceivedAddressListResult> call, Response<ReceivedAddressListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                readressRes = response.body();
                if ("00".equals(readressRes.getCode())) {
                    if (currentpage == 1) {
                        myAdapter.reSetData(readressRes.getData());
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        myAdapter.addAll(readressRes.getData());
                    }
                    //总弄页数
                    if (readressRes.getPi().getTotalPage() < 2) {
                        myAdapter.setLoadStatus(ReceivedAddAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        myAdapter.setLoadStatus(ReceivedAddAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(ReceivedAddressActivity.this, readressRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ReceivedAddressListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(int current) {
        Call<ReceivedAddressListResult> call = ralApi.getService().createCommit(token, uid, current);
        call.enqueue(callback);
    }

    /************删除地址***************/
    private Callback<NewReceivedResult> callbackdel = new Callback<NewReceivedResult>() {
        @Override
        public void onResponse(Call<NewReceivedResult> call, Response<NewReceivedResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                nrres = response.body();
                if ("00".equals(nrres.getCode())) {
                    ToastUtil.showShort(ReceivedAddressActivity.this, "删除成功");
//                    createSingle(currentPage + "");
                    myAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(ReceivedAddressActivity.this, nrres.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewReceivedResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleDel(long id) {
        String token = (String) sharePH.getSharedPreference("token", "");
        Call<NewReceivedResult> calldel = ralApi.getService().createCommitId(token, id);
        calldel.enqueue(callbackdel);
    }

    /*****************更新地址*********************/
    private Callback<NewReceivedResult> callbackupdate = new Callback<NewReceivedResult>() {
        @Override
        public void onResponse(Call<NewReceivedResult> call, Response<NewReceivedResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                NewReceivedResult radlRes = response.body();
                if ("00".equals(radlRes.getCode())) {
                    if (radlRes.getData() != null) {
                        ToastUtil.showShort(ReceivedAddressActivity.this, "设为默认地址成功");
                        finish();
                    } else {
                        ToastUtil.showShort(ReceivedAddressActivity.this, "设为默认地址Failed");
                    }
                } else {
                    ToastUtil.showShort(ReceivedAddressActivity.this, radlRes.getMsg());
                }

            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewReceivedResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleUpdate(int id) {
        Call<NewReceivedResult> call = ralApi.getService().createCommitPutId(token, id, true);
        call.enqueue(callbackupdate);
    }

    /*****************获取地址*********************/
    private Callback<DefaultRecAddResult> callbackdefault = new Callback<DefaultRecAddResult>() {
        @Override
        public void onResponse(Call<DefaultRecAddResult> call, Response<DefaultRecAddResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                defaultAddRes = response.body();
                if ("00".equals(defaultAddRes.getCode())) {
                    if (defaultAddRes.getData() != null) {
                        Log.v(TAG, "有默认地址");
                    } else {
                        //没有默认地址  显示新增地址
                        llReceivedAddressBtn.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtil.showShort(ReceivedAddressActivity.this, defaultAddRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<DefaultRecAddResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleAddress() {
        Call<DefaultRecAddResult> call = ralApi.getService().createCommitGetDefault(token, uid);
        call.enqueue(callbackdefault);
    }

}
