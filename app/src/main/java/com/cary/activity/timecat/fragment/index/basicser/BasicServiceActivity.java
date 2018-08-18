package com.cary.activity.timecat.fragment.index.basicser;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SetMealApi;
import com.cary.activity.timecat.model.BasicService;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicServiceActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recyclerViewBasicService)
    RecyclerView recyclerViewBasicService;

    private SetMealApi mApi;

    RecyclerViewBServiceAdapter recyclerViewGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_service);
        ButterKnife.bind(this);
        mApi = SetMealApi.getApi();

        titleText.setText("基础服务");
        titleText.setTextColor(getResources().getColor(R.color.color_333333));
        titleBack.setImageResource(R.mipmap.basic_back);


        recyclerViewGridAdapter = new RecyclerViewBServiceAdapter(this);
        recyclerViewBasicService.setAdapter(recyclerViewGridAdapter);
        recyclerViewBasicService.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        getBasicDate();
    }

    @Override
    public int getLayout() {
        return 0;
    }

    public void getBasicDate(){
        Call<BasicService> call = mApi.getService().getBasicList();
        call.enqueue(new Callback<BasicService>() {
            @Override
            public void onResponse(Call<BasicService> call, Response<BasicService> response) {
                if (response.isSuccessful()) {
                    BasicService service=response.body();
                    if ("00".equals(service.getCode())) {
                        recyclerViewGridAdapter.setDatas(service.getData());
                        recyclerViewGridAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.showShort(BasicServiceActivity.this, service.getMsg());
                    }

                }
            }

            @Override
            public void onFailure(Call<BasicService> call, Throwable t) {
            }
        });
    }

}
