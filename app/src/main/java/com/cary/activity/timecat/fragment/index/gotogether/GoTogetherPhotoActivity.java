package com.cary.activity.timecat.fragment.index.gotogether;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.person.myorder.MyOrderActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 随行拍
 */
public class GoTogetherPhotoActivity extends BaseActivity {

    private static final String TAG = GoTogetherPhotoActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_together)
    TextView tvTogether;
    @BindView(R.id.iv_together_line)
    TextView ivTogetherLine;
    @BindView(R.id.ll_together_photo)
    LinearLayout llTogetherPhoto;
    @BindView(R.id.tv_take_photo)
    TextView tvTakePhoto;
    @BindView(R.id.iv_take_photo_line)
    TextView ivTakePhotoLine;
    @BindView(R.id.ll_take_photo)
    LinearLayout llTakePhoto;
    @BindView(R.id.tv_make_dress)
    TextView tvMakeDress;
    @BindView(R.id.iv_make_dress_line)
    TextView ivMakeDressLine;
    @BindView(R.id.ll_make_dress)
    LinearLayout llMakeDress;
    @BindView(R.id.tv_service_time_text)
    TextView tvServiceTimeText;
    @BindView(R.id.tv_service_time)
    TextView tvServiceTime;
    @BindView(R.id.rl_service_time)
    RelativeLayout rlServiceTime;
    @BindView(R.id.tv_service_city_text)
    TextView tvServiceCityText;
    @BindView(R.id.tv_service_city)
    TextView tvServiceCity;
    @BindView(R.id.rl_service_city)
    RelativeLayout rlServiceCity;
    @BindView(R.id.tv_explain_remark_text)
    TextView tvExplainRemarkText;
    @BindView(R.id.tv_explain_remark)
    EditText tvExplainRemark;
    @BindView(R.id.rl_explain_remark)
    LinearLayout rlExplainRemark;
    @BindView(R.id.tv_gotogether_subtraction)
    TextView tvGotogetherSubtraction;
    @BindView(R.id.tv_gotogether_add)
    TextView tvGotogetherAdd;
    @BindView(R.id.btn_go_together_order)
    Button btnGoTogetherOrder;
    @BindView(R.id.tv_gotogether_price)
    TextView tvGotogetherPrice;

    private String type = "gp";//gp跟拍，sx,gz     gp,sx,gz
    //地区选择器
    private CityPickerView mCityPickerView = new CityPickerView();
    private String provinceStr, cityStr, areaStr;

    private TaskApi taskApi;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token;
    private int uid;
    private TaskAddResult taskRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_go_together_photo);
        ButterKnife.bind(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("随行拍");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mCityPickerView.init(this);
        taskApi = TaskApi.getApi();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        uid = (int) sharedPreferencesHelper.getSharedPreference("id", 0);


    }

    @Override
    public int getLayout() {
        return R.layout.activity_go_together_photo;
    }

    @OnClick({R.id.title_back, R.id.ll_together_photo, R.id.ll_take_photo,
            R.id.ll_make_dress, R.id.rl_service_time, R.id.rl_service_city,
            R.id.rl_explain_remark, R.id.tv_gotogether_subtraction,
            R.id.tv_gotogether_add, R.id.btn_go_together_order, R.id.tv_gotogether_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_together_photo:
//                ToastUtil.showShort(this, "跟拍");
                type = "gp";
                setTextColor(tvTogether, ivTogetherLine, true);
                setTextColor(tvTakePhoto, ivTakePhotoLine, false);
                setTextColor(tvMakeDress, ivMakeDressLine, false);

                break;
            case R.id.ll_take_photo:
//                ToastUtil.showShort(this, "摄像");
                type = "sx";
                setTextColor(tvTogether, ivTogetherLine, false);
                setTextColor(tvTakePhoto, ivTakePhotoLine, true);
                setTextColor(tvMakeDress, ivMakeDressLine, false);
                break;
            case R.id.ll_make_dress:
//                ToastUtil.showShort(this, "跟妆");
                type = "gz";
                setTextColor(tvTogether, ivTogetherLine, false);
                setTextColor(tvTakePhoto, ivTakePhotoLine, false);
                setTextColor(tvMakeDress, ivMakeDressLine, true);
                break;
            case R.id.rl_service_time:
//                ToastUtil.showShort(this, "服务时间");
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        tvServiceTime.setText(DateFormat.format("yyyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();

                break;
            case R.id.rl_service_city:
//                ToastUtil.showShort(this, "服务城市");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                wheel();
                break;
//            case R.id.rl_explain_remark:
//                final EditText inputServer = new EditText(this);
//                inputServer.setFocusable(true);
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("请输入备注").setIcon(
//                        R.mipmap.ic_launcher).setView(inputServer).setNegativeButton(
//                        "取消", null);
//                builder.setPositiveButton("确定",
//                        new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int which) {
//                                tvExplainRemark.setText(inputServer.getText().toString());
//                            }
//                        });
//                builder.show();
//                break;
            case R.id.tv_gotogether_price:
                final EditText inputPrice = new EditText(this);
                inputPrice.setFocusable(true);
                inputPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builderprice = new AlertDialog.Builder(this);
                builderprice.setTitle("请输入价格").setIcon(
                        R.mipmap.ic_launcher).setView(inputPrice).setNegativeButton(
                        "取消", null);
                builderprice.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                tvGotogetherPrice.setText(inputPrice.getText().toString());
                            }
                        });
                builderprice.show();
                break;
            case R.id.tv_gotogether_subtraction:
                String textStrSub = tvGotogetherPrice.getText().toString().trim();
                int textIntSub = Integer.parseInt(textStrSub);
                if (textIntSub > 0) {
                    textIntSub -= 1;
                } else {
                    ToastUtil.showShort(this, "价格不能为0");
                }
                tvGotogetherPrice.setText(textIntSub + "");
                break;
            case R.id.tv_gotogether_add:
                String textStrAdd = tvGotogetherPrice.getText().toString().trim();
                int textIntAdd = Integer.parseInt(textStrAdd);
                textIntAdd += 1;
                tvGotogetherPrice.setText(textIntAdd + "");
                break;
            case R.id.btn_go_together_order:
//                ToastUtil.showShort(this, "发布订单");
//                Intent intent = new Intent(this, TaskDetialActivity.class);
//                startActivity(intent);
                String serviceTime = tvServiceTime.getText().toString().trim();
                String serviceCity = tvServiceCity.getText().toString().trim();
                String serviceContent = tvExplainRemark.getText().toString().trim();
                String amount = tvGotogetherPrice.getText().toString().trim();

                if (TextUtils.isEmpty(serviceCity)) {
                    ToastUtil.showShort(this, "请选择服务城市");
                    break;
                } else if (TextUtils.isEmpty(serviceTime)) {
                    ToastUtil.showShort(this, "请选择服务时间");
                    break;
                } else {
                    createSingle(serviceTime, serviceCity, serviceContent, amount, type);
                }


                break;
        }
    }

    /****************************
     * 显示二级标题的选择
     * @param tv
     * @param iv
     * @param flag
     ****************************/
    private void setTextColor(TextView tv, TextView iv, boolean flag) {
        if (flag) {
            tv.setTextColor(getResources().getColor(R.color.login_color_btn));
            iv.setVisibility(View.VISIBLE);

        } else {
            tv.setTextColor(getResources().getColor(R.color.color_three));
            iv.setVisibility(View.GONE);

        }
    }

    /******************************************************
     *                  弹出选择器
     ******************************************************/
    private void wheel() {
        CityConfig cityConfig = new CityConfig.Builder().title("选择城市").build();//标题
        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
//                sb.append("选择的结果：\n");
                if (province != null) {
                    sb.append(province.getName() + " ");//+ province.getId() + "\n");
                    provinceStr = province.getName() + "";
                }

                if (city != null) {
                    sb.append(city.getName() + " ");// + city.getId() + ("\n"));
                    cityStr = city.getName() + "";
                }

                if (district != null) {
                    sb.append(district.getName() + " ");// + district.getId() + ("\n"));
                    areaStr = district.getName() + "";
                }
                tvServiceCity.setText("" + sb.toString());
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(GoTogetherPhotoActivity.this, "已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }

    /*************发布订单******************/
    private Callback<TaskAddResult> callback = new Callback<TaskAddResult>() {
        @Override
        public void onResponse(Call<TaskAddResult> call, Response<TaskAddResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                taskRes = response.body();
                if ("00".equals(taskRes.getCode())) {
                    if (taskRes.getData() != null) {
                        ToastUtil.showShort(GoTogetherPhotoActivity.this, "下单成功");
                        Intent intent = new Intent (GoTogetherPhotoActivity.this, MyOrderActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                    }
                } else {
                    ToastUtil.showShort(GoTogetherPhotoActivity.this, taskRes.getMsg());
                }

            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TaskAddResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String serviceTime, String serviceCity, String content, String amount, String type) {
        Call<TaskAddResult> call = taskApi.getService().createCommitAdd(token, uid, serviceTime,
                serviceCity, content, amount, type,"Alipay");
        call.enqueue(callback);
    }
}
