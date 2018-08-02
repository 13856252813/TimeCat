package com.cary.activity.timecat.manager.teacher.fragment.self;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfomationActivity extends AppCompatActivity {

    @BindView(R.id.tv_select_store)
    TextView tvSelectStore;
    @BindView(R.id.ll_select_store)
    LinearLayout llSelectStore;
    @BindView(R.id.tv_select_teacher_type)
    TextView tvSelectTeacherType;
    @BindView(R.id.ll_select_teacher_type)
    LinearLayout llSelectTeacherType;
    @BindView(R.id.tv_select_acceptance_type)
    TextView tvSelectAcceptanceType;
    @BindView(R.id.ll_select_acceptance_type)
    LinearLayout llSelectAcceptanceType;
    @BindView(R.id.tv_select_city_type)
    TextView tvSelectCityType;
    @BindView(R.id.ll_select_city)
    LinearLayout llSelectCity;
    @BindView(R.id.tv_select_address)
    EditText tvSelectAddress;
    @BindView(R.id.ll_select_address)
    LinearLayout llSelectAddress;
    @BindView(R.id.tv_select_sos_people)
    EditText tvSelectSosPeople;
    @BindView(R.id.ll_select_sos_people)
    LinearLayout llSelectSosPeople;
    @BindView(R.id.tv_select_sos_telphone)
    EditText tvSelectSosTelphone;
    @BindView(R.id.ll_select_sos_telphone)
    LinearLayout llSelectSosTelphone;
    @BindView(R.id.tv_select_myfacility)
    EditText tvSelectMyfacility;
    @BindView(R.id.ll_select_myfacility)
    LinearLayout llSelectMyfacility;
    @BindView(R.id.tv_select_selfintroduction)
    EditText tvSelectSelfintroduction;
    @BindView(R.id.ll_select_selfintroduction)
    LinearLayout llSelectSelfintroduction;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_infomation);
        ButterKnife.bind(this);

        titleText.setText("我的资料");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }
}
