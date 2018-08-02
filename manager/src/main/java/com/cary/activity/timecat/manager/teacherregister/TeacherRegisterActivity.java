package com.cary.activity.timecat.manager.teacherregister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 老师入驻
 */
public class TeacherRegisterActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
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
    @BindView(R.id.tv_select_work1_city)
    TextView tvSelectWork1City;
    @BindView(R.id.ll_select_work1)
    LinearLayout llSelectWork1;
    @BindView(R.id.recycler_work1)
    RecyclerView recyclerWork1;
    @BindView(R.id.tv_add_work1)
    TextView tvAddWork1;
    @BindView(R.id.tv_select_work2_city)
    TextView tvSelectWork2City;
    @BindView(R.id.ll_select_work2)
    LinearLayout llSelectWork2;
    @BindView(R.id.recycler_work2)
    RecyclerView recyclerWork2;
    @BindView(R.id.tv_add_work2)
    TextView tvAddWork2;
    @BindView(R.id.tv_select_work3_city)
    TextView tvSelectWork3City;
    @BindView(R.id.ll_select_work3)
    LinearLayout llSelectWork3;
    @BindView(R.id.recycler_work3)
    RecyclerView recyclerWork3;
    @BindView(R.id.tv_add_work3)
    TextView tvAddWork3;
    @BindView(R.id.btn_commit_apply)
    Button btnCommitApply;

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_teacher_register);
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        titleText.setText("入驻资料");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }

    @OnClick({R.id.title_back, R.id.ll_select_store, R.id.ll_select_teacher_type, R.id.ll_select_acceptance_type, R.id.ll_select_city, R.id.ll_select_work1, R.id.ll_select_work2, R.id.ll_select_work3, R.id.btn_commit_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_select_store:
                ToastUtil.showShort(this, "选择店铺");
                break;
            case R.id.ll_select_teacher_type:
                ToastUtil.showShort(this, "选择老师类型");
                break;
            case R.id.ll_select_acceptance_type:
                ToastUtil.showShort(this, "选择应聘类型");
                break;
            case R.id.ll_select_city:
                ToastUtil.showShort(this, "选择城市");
                break;
            case R.id.ll_select_work1:
                ToastUtil.showShort(this, "选择work1");
                break;
            case R.id.ll_select_work2:
                ToastUtil.showShort(this, "选择work2");
                break;
            case R.id.ll_select_work3:
                ToastUtil.showShort(this, "选择work3");
                break;
            case R.id.btn_commit_apply:
                ToastUtil.showShort(this, "提交入驻申请");
                break;
        }
    }
}
