package com.cary.activity.timecat.manager.client.people;

import android.content.Intent;
import android.os.Bundle;
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
import com.cary.activity.timecat.manager.client.myorder.confirmorder.teacher.TeacherDetialActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 员工管理
 */
public class EmployeeManagerActivity extends AppCompatActivity {
    private static final String TAG = EmployeeManagerActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_employee_manager)
    RecyclerView recyclerEmployeeManager;
    @BindView(R.id.swiperefreshlayout_employee_manager)
    SwipeRefreshLayout swiperefreshlayoutEmployeeManager;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int storeId;
    private int currentpage = 1;
    private int type;

    private EmployeeManagerApi mApi;
    private EmployeeManagerResult mRes;
    private List<EmployeeManagerResult.Data> mData;
    private EmployeeManagerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_employee_manager);
        ButterKnife.bind(this);

        type = getIntent().getIntExtra("type", 0);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        if (type == 0) {
            titleText.setText("员工管理");
        } else if (type == 1) {
            titleText.setText("客户管理");
        }

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);
        storeId = (int) sharePh.getSharedPreference("storeId", 0);
        mApi = EmployeeManagerApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerEmployeeManager.setLayoutManager(mLayoutManager);
        recyclerEmployeeManager.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new EmployeeManagerAdapter(this);
        recyclerEmployeeManager.setAdapter(mAdapter);
        if (type == 0) {
            //员工管理
            createSingle();
        } else if (type == 1) {
            //客户管理
            createSingle();
        }

        mAdapter.setItemClickListener(new EmployeeManagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(EmployeeManagerActivity.this, TeacherDetialActivity.class);
                if (type == 0) {
                    //员工管理
                    intent.putExtra("id", mData.get(position).getId());
                } else if (type == 1) {
                    //客户管理
//                    createSingle();
                }
                startActivity(intent);
            }

            @Override
            public void onCheckClick(int position) {
                //获取选中后的数据
            }
        });
    }

    private Callback<EmployeeManagerResult> callbackPage = new Callback<EmployeeManagerResult>() {
        @Override
        public void onResponse(Call<EmployeeManagerResult> call, Response<EmployeeManagerResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }

                } else {
                    Log.e(TAG, "+++" + response.message());
                }
            }
        }

        @Override
        public void onFailure(Call<EmployeeManagerResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<EmployeeManagerResult> call = mApi.getService().createCommitPage(token, storeId);
        call.enqueue(callbackPage);
    }
}
