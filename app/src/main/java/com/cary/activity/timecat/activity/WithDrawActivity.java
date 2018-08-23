package com.cary.activity.timecat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;

import butterknife.BindView;
import butterknife.OnClick;

public class WithDrawActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleText.setText("提现");
    }



    @OnClick({R.id.title_back})
    public  void onViewClick(View view){
        switch (view.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }


    @Override
    public int getLayout() {
        return R.layout.activity_withdraw;
    }
}
