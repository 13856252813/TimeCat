package com.cary.activity.timecat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;

import butterknife.BindView;

public class SetRelateUserActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView mTextTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextTitle.setText("添加关联用户");

    }

    @Override
    public int getLayout() {
        return R.layout.activity_relate_user;
    }
}
