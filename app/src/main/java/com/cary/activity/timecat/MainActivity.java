package com.cary.activity.timecat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.download.DownloadActivity;
import com.cary.activity.timecat.guide.PrefManager;
import com.cary.activity.timecat.main.dialog.CEOEmailActivity;
import com.cary.activity.timecat.phone.PhoneApi;
import com.cary.activity.timecat.phone.PhoneResult;
import com.cary.activity.timecat.phone.PhoneService;
import com.cary.activity.timecat.reglogin.LoginActivity;
import com.cary.activity.timecat.util.view.GlideCircleTransform;
import com.cary.activity.timecat.util.view.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 进入后的页面
 */

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private List<String> mData;
    @BindView(R.id.phone_view)
    EditText phoneView;
    @BindView(R.id.result_view)
    TextView resultView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;

    private long exitTime = 0;//标识时间退出

    private PhoneApi phoneApi;
    private PhoneService phoneService;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        保存的初始化
        prefManager = new PrefManager(this);
        //这句话，可以再次显示引导页
//        prefManager.setFirstTimeLaunch(true);
        if(prefManager.isFirstTimeLaunch())
        {
            startActivity(new Intent(this,CEOEmailActivity.class));
            prefManager.setFirstTimeLaunch(false);
        }

        (findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        ButterKnife.bind(this);

        phoneApi = PhoneApi.getApi();
        phoneService = phoneApi.getService();

        String myUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522321760966&di=c237251b288d0f00bd74b721153d1651&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F09fa513d269759eee5b61ac2befb43166c22dfd1.jpg";
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
        Glide.with(this).load(myUrl).apply(options).into(imageView);
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform());
        Glide.with(this).load(myUrl).apply(options2).into(imageView2);
        RequestOptions options3 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransform());
        Glide.with(this).load(myUrl).apply(options3).into(imageView3);

        initData(1);
        initView();
    }

    @OnClick({R.id.query_view, R.id.query_rxjava_view, R.id.duo_shuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query_view:
                query();
                break;
            case R.id.query_rxjava_view:
                queryByRxJava();
                break;
            case R.id.duo_shuo:
//                startActivity(new Intent(MainActivity.this, DuoShuoActivity.class));
                break;
        }
    }

    private void queryByRxJava() {
        resultView.setText("");
        String number = phoneView.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        phoneService.getPhoneResult(PhoneApi.API_KEY, number)
                .subscribeOn(Schedulers.newThread())    //子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())  //回调到主线程
                .subscribe(new Observer<PhoneResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PhoneResult result) {

                        if (result != null && result.getErrNum() == 0) {
                            PhoneResult.RetDataEntity entity = result.getRetData();
                            resultView.append("地址：" + entity.getCity());
                        }else{
                            resultView.append("地址：Faiuled" );
                        }
                    }
                });
    }

    private void query() {
        resultView.setText("");
        String number = phoneView.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please input phone number!", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<PhoneResult> call = phoneService.getResult(PhoneApi.API_KEY, number);
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()) {
                    PhoneResult result = response.body();
                    if (result != null && result.getErrNum() == 0) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        resultView.append("地址：" + entity.getCity());
                    }
                }else{
                    resultView.append("地址：Faiuled" );
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });
    }
    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    private void initView() {
        //设置ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.leftarrow);
        setSupportActionBar(toolbar);//替换系统的actionBar

        //设置TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 1; i < 20; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
        }
        //TabLayout的切换监听
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initData(tab.getPosition() + 1);
                setScrollViewContent();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setScrollViewContent();
    }

    /**
     * 刷新ScrollView的内容
     */
    private void setScrollViewContent() {
        //NestedScrollView下的LinearLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_sc_content);
        layout.removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            View view = View.inflate(this, R.layout.activity_main_index_item_layout, null);
            ((TextView) view.findViewById(R.id.tv_info)).setText(mData.get(i));
            //动态添加 子View
            layout.addView(view, i);
        }
    }

    @OnClick(R.id.download)
    public void onClick() {
        startActivity(new Intent(this, DownloadActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //退出时保存
            prefManager.setSaveTime();
            finish();
            System.exit(0);
        }
    }
}
