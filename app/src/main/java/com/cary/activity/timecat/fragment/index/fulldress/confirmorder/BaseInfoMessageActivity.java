package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.model.BasicMealInfo;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class BaseInfoMessageActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_baseinfomessage_bridegroom_text)
    TextView tvBaseinfomessageBridegroomText;
    @BindView(R.id.et_baseinfomessage_bridegroom)
    EditText etBaseinfomessageBridegroom;
    @BindView(R.id.ll_baseinfomessage_bridegroom)
    LinearLayout llBaseinfomessageBridegroom;
    @BindView(R.id.tv_baseinfomessage_brideg_text)
    TextView tvBaseinfomessageBridegText;
    @BindView(R.id.et_baseinfomessage_brideg)
    EditText etBaseinfomessageBrideg;
    @BindView(R.id.ll_baseinfomessage_brideg)
    LinearLayout llBaseinfomessageBrideg;
    @BindView(R.id.tv_baseinfomessage_shootingtime_text)
    TextView tvBaseinfomessageShootingtimeText;
    @BindView(R.id.et_baseinfomessage_shootingtime)
    TextView etBaseinfomessageShootingtime;
    @BindView(R.id.ll_baseinfomessage_shootingtime)
    LinearLayout llBaseinfomessageShootingtime;
    @BindView(R.id.tv_baseinfomessage_wedding_day_text)
    TextView tvBaseinfomessageWeddingDayText;
    @BindView(R.id.et_baseinfomessage_wedding_day)
    TextView etBaseinfomessageWeddingDay;
    @BindView(R.id.ll_baseinfomessage_wedding_day)
    LinearLayout llBaseinfomessageWeddingDay;
    @BindView(R.id.tv_baseinfomessage_store_reception_text)
    TextView tvBaseinfomessageStoreReceptionText;
    @BindView(R.id.et_baseinfomessage_store_reception)
    EditText etBaseinfomessageStoreReception;
    @BindView(R.id.ll_baseinfomessage_store_reception)
    LinearLayout llBaseinfomessageStoreReception;




    private String bridegroom, brideg, marryTime, shootTime, storeReceptionStr;

    private BasicMealInfo mBasicInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleText.setText("基本信息");
        titleTextRight.setText("完成");
        titleTextRight.setTextColor(getResources().getColor(R.color.login_color_btn));
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));


    }

    @Override
    public int getLayout() {
        return R.layout.activity_base_info_message;
    }


    @OnClick({R.id.title_back, R.id.title_text_right,R.id.ll_baseinfomessage_bridegroom, R.id.ll_baseinfomessage_brideg,
            R.id.ll_baseinfomessage_shootingtime, R.id.ll_baseinfomessage_wedding_day, R.id.ll_baseinfomessage_store_reception})
    public void onViewClicked(View view) {
        Calendar c = Calendar.getInstance();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                bridegroom =etBaseinfomessageBridegroom.getText().toString().trim();
                brideg= etBaseinfomessageBrideg.getText().toString().trim();
                storeReceptionStr= etBaseinfomessageStoreReception.getText().toString().trim();
                mBasicInfo=new BasicMealInfo( bridegroom, brideg, marryTime, shootTime, storeReceptionStr);
                Intent intent=new Intent();
                intent.putExtra("data",mBasicInfo);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.ll_baseinfomessage_shootingtime:
                showDatePickerDialog(2, etBaseinfomessageShootingtime, c);
                shootTime = etBaseinfomessageShootingtime.getText().toString().trim();
                ToastUtil.showShort(this, "拍摄时间" + shootTime);
                break;
            case R.id.ll_baseinfomessage_wedding_day:
                showDatePickerDialog(2, etBaseinfomessageWeddingDay, c);
                marryTime = etBaseinfomessageWeddingDay.getText().toString().trim();
                ToastUtil.showShort(this, "婚期时间" + storeReceptionStr);
                break;
        }
    }


    /**
     * 日期选择
     *
     * @param themeResId
     * @param tv
     * @param calendar
     */
    private void showDatePickerDialog(int themeResId, final TextView tv, Calendar calendar) {
        new DatePickerDialog(this, themeResId, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                tv.setTextColor(getResources().getColor(R.color.color_three));
            }
        }
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}
