package com.cary.activity.timecat.fragment.index.timeclouddish;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewFloderUploadActivity extends Activity {

    @BindView(R.id.btn_new_floder)
    Button btnNewFloder;
    @BindView(R.id.btn_upload_photo)
    Button btnUploadPhoto;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.pop_layout)
    LinearLayout popLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_floder_upload);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_new_floder, R.id.btn_upload_photo, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_new_floder:


                break;
            case R.id.btn_upload_photo:
                ToastUtil.showShort(this, "上传照片");
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
