package com.cary.activity.timecat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.adapter.SeceneListAdapter;
import com.cary.activity.timecat.http.base.ApiClient;
import com.cary.activity.timecat.model.AttractionBean;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SceneListActivity extends BaseActivity {


    @BindView(R.id.title_back)
    ImageView mImageBack;
    @BindView(R.id.title_text)
    TextView mTextTitle;
    @BindView(R.id.scene_list)
    RecyclerView mRecyclerScene;

    private ApiClient mApiClient;
    private String mToken;
    private String mMealId;

    private SeceneListAdapter mAdapter;
    public static final int RESULT_CODE=1;


    @Override
    public int getLayout() {
        return R.layout.activity_scene;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextTitle.setText("选择景点");
        if (getCurrentUser() != null) {
            mToken = getCurrentUser().getToken();
        }
        mMealId = getIntent().getStringExtra("id");
        initRecyclerView();
        intSeceneData();
    }



    public void intSeceneData() {
        Call<AttractionBean> call = ApiClient.getApi().getService().getAttractionsById(mToken, mMealId);
        call.enqueue(new Callback<AttractionBean>() {
            @Override
            public void onResponse(Call<AttractionBean> call, Response<AttractionBean> response) {
                AttractionBean bean = response.body();
                if ("00".equals(bean.getCode())) {
                    mAdapter.setDatas(bean.getData());
                    mRecyclerScene.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<AttractionBean> call, Throwable t) {

            }
        });
    }

    public void initRecyclerView() {
        mAdapter=new SeceneListAdapter(this);
        mRecyclerScene.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerScene.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SeceneListAdapter.ItemClickListener(){

            @Override
            public void onItemClick(AttractionBean.DataBean data) {
                Intent intent=new Intent();
                intent.putExtra("data",data);
                setResult(RESULT_CODE,intent);
                finish();
            }
        });
    }

    @OnClick({R.id.title_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }

    }
}
