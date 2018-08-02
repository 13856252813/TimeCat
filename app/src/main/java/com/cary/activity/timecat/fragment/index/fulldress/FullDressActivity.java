package com.cary.activity.timecat.fragment.index.fulldress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 礼服馆
 */
public class FullDressActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_fulldress_sellarea)
    ImageView ivFulldressSellarea;
    @BindView(R.id.iv_fulldress_sharearea)
    ImageView ivFulldressSharearea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_dress);
        ButterKnife.bind(this);
        titleText.setText("服装馆");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

    }

    @OnClick({R.id.title_back, R.id.iv_fulldress_sellarea, R.id.iv_fulldress_sharearea})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this,FullDressTabActivity.class);
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.iv_fulldress_sellarea://售卖区
                intent.putExtra("flagtag","0");
                startActivity(intent);
                break;
            case R.id.iv_fulldress_sharearea://共享区
                intent.putExtra("flagtag","1");
                startActivity(intent);
                break;
        }

    }
}
