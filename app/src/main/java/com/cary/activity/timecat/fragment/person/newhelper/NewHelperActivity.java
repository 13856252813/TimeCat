package com.cary.activity.timecat.fragment.person.newhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 新手帮助
 */
public class NewHelperActivity extends AppCompatActivity {

    private static final String TAG = NewHelperActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_newhelper_list)
    RecyclerView recyclerNewhelperList;

    private NewHelperApi newHelperApi;
    private SharedPreferencesHelper sharePH;
    private NewHelperListViewAdapter recyclerListViewAdapter;
    private NewHelperResult nhresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_helper);
        ButterKnife.bind(this);

        titleText.setText("新手帮助");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newHelperApi = NewHelperApi.getApi();
        sharePH = new SharedPreferencesHelper(this);
        //获取 数据
        createSingle();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerNewhelperList.setLayoutManager(layoutManager);
//下面这行代码就是添加分隔线的方法
        recyclerNewhelperList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
    private Callback<NewHelperResult> callback  = new Callback<NewHelperResult>() {
        @Override
        public void onResponse(Call<NewHelperResult> call, Response<NewHelperResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                nhresult= response.body();
                if ("00".equals(nhresult.getCode())) {
                    recyclerListViewAdapter = new NewHelperListViewAdapter(NewHelperActivity.this, nhresult.getData());//添加适配器，这里适配器刚刚装入了数据
                    recyclerNewhelperList.setAdapter(recyclerListViewAdapter);
                } else {
                    ToastUtil.showShort(NewHelperActivity.this, nhresult.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewHelperResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        String token = (String) sharePH.getSharedPreference("token","");
        Call<NewHelperResult> call = newHelperApi.getService().createCommit(token );
        call.enqueue(callback );
    }
}
