package com.cary.activity.timecat.manager.client.myorder.confirmorder;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("Registered")
public class BaseInfoMessageActivity extends AppCompatActivity {

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
    TextView etBaseinfomessageBridegroom;
    @BindView(R.id.ll_baseinfomessage_bridegroom)
    LinearLayout llBaseinfomessageBridegroom;
    @BindView(R.id.tv_baseinfomessage_brideg_text)
    TextView tvBaseinfomessageBridegText;
    @BindView(R.id.et_baseinfomessage_brideg)
    TextView etBaseinfomessageBrideg;
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
    TextView etBaseinfomessageStoreReception;
    @BindView(R.id.ll_baseinfomessage_store_reception)
    LinearLayout llBaseinfomessageStoreReception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info_message);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("基本信息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }

    private String bridegroom, brideg, storeReception, shootTime, storeReceptionStr;

    @OnClick({R.id.title_back, R.id.ll_baseinfomessage_bridegroom, R.id.ll_baseinfomessage_brideg, R.id.ll_baseinfomessage_shootingtime, R.id.ll_baseinfomessage_wedding_day, R.id.ll_baseinfomessage_store_reception})
    public void onViewClicked(View view) {
        Calendar c = Calendar.getInstance();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_baseinfomessage_bridegroom:
                showInputDialog("新郎姓名", etBaseinfomessageBridegroom);
                bridegroom = etBaseinfomessageBridegroom.getText().toString().trim();
                ToastUtil.showShort(this, "新郎姓名：" + bridegroom);
                break;
            case R.id.ll_baseinfomessage_brideg:
                showInputDialog("新娘姓名", etBaseinfomessageBrideg);
                brideg = etBaseinfomessageBrideg.getText().toString().trim();
                ToastUtil.showShort(this, "新娘姓名：" + brideg);
                break;
            case R.id.ll_baseinfomessage_shootingtime:
                showDatePickerDialog(2, etBaseinfomessageShootingtime, c);
                shootTime = etBaseinfomessageShootingtime.getText().toString().trim();
                ToastUtil.showShort(this, "拍摄时间" + shootTime);
                break;
            case R.id.ll_baseinfomessage_wedding_day:
                showDatePickerDialog(2, etBaseinfomessageWeddingDay, c);
                storeReceptionStr = etBaseinfomessageWeddingDay.getText().toString().trim();
                ToastUtil.showShort(this, "婚期时间" + storeReceptionStr);
                break;
            case R.id.ll_baseinfomessage_store_reception:
                showInputDialog("门店接待", etBaseinfomessageStoreReception);
                storeReception = etBaseinfomessageStoreReception.getText().toString().trim();
                ToastUtil.showShort(this, "门店接待：" + storeReception);
                break;
        }
    }

    private void showInputDialog(String title, final TextView tv) {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(BaseInfoMessageActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(BaseInfoMessageActivity.this);
        inputDialog.setTitle(title).setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(BaseInfoMessageActivity.this,
                        tv.setText(editText.getText().toString().trim());
//                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    /**
     * 日期选择
     *
     * @param themeResId
     * @param tv
     * @param calendar
     */
    private void showDatePickerDialog(int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
//                tv.setText("您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                tv.setTextColor(getResources().getColor(R.color.color_three));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}
