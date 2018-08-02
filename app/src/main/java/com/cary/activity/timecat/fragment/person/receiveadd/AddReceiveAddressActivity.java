package com.cary.activity.timecat.fragment.person.receiveadd;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReceiveAddressActivity extends AppCompatActivity {

    private static final String TAG = AddReceiveAddressActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_received_person_text)
    TextView tvReceivedPersonText;
    @BindView(R.id.et_received_person)
    EditText etReceivedPerson;
    @BindView(R.id.ll_received_person)
    LinearLayout llReceivedPerson;
    @BindView(R.id.tv_received_person_mobile)
    TextView tvReceivedPersonMobile;
    @BindView(R.id.et_received_person_mobile)
    EditText etReceivedPersonMobile;
    @BindView(R.id.ll_received_person_mobile)
    LinearLayout llReceivedPersonMobile;
    @BindView(R.id.tv_received_address)
    TextView tvReceivedAddress;
    @BindView(R.id.et_received_address)
    TextView etReceivedAddress;
    @BindView(R.id.ll_received_address)
    LinearLayout llReceivedAddress;
    @BindView(R.id.tv_received_detial_address)
    TextView tvReceivedDetialAddress;
    @BindView(R.id.et_received_detial_address)
    EditText etReceivedDetialAddress;
    @BindView(R.id.ll_received_detial_address)
    LinearLayout llReceivedDetialAddress;
    @BindView(R.id.btn_received_address_save)
    Button btnReceivedAddressSave;
    //地区选择器
    private CityPickerView mCityPickerView = new CityPickerView();

    private NewReceivedAddressApi nraddApi;
    private NewReceivedResult radlRes;
    private SharedPreferencesHelper shareHP;
    private String provinceStr, cityStr, areaStr;
    private int uid;
    private String token;

    private int id;
    private ReceivedAddressListApi ralApi;
    private NewReceivedResult nrres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receive_address);
        ButterKnife.bind(this);

        nraddApi = NewReceivedAddressApi.getApi();
        shareHP = new SharedPreferencesHelper(this);
        ralApi = ReceivedAddressListApi.getApi();
        token = (String) shareHP.getSharedPreference("token", "");
        uid = (int) shareHP.getSharedPreference("id", 0);

        id = getIntent().getIntExtra("id",0);
        if (id!=0) {
            titleText.setText("编辑收货地址");
            btnReceivedAddressSave.setText("保存");
            createSingleGet(id);
        } else {
            titleText.setText("添加收货地址");
        }
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mCityPickerView.init(this);

    }

    @OnClick({R.id.title_back, R.id.ll_received_address, R.id.btn_received_address_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_received_address:
//                ToastUtil.showShort(this, "select city");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                wheel();
                break;
            case R.id.btn_received_address_save:
//                ToastUtil.showShort(this, "save");
                String receivedName = etReceivedPerson.getText().toString().trim();
                String receivedMobile = etReceivedPersonMobile.getText().toString().trim();
                String receivedDetialAddress = etReceivedDetialAddress.getText().toString().trim();
                if (TextUtils.isEmpty(receivedName)) {
                    ToastUtil.showShort(this, "请输入收件人姓名");
                    break;
                } else if (TextUtils.isEmpty(receivedMobile)) {
                    ToastUtil.showShort(this, "请输入收件人电话");
                    break;
                } else if (TextUtils.isEmpty(provinceStr)) {
                    ToastUtil.showShort(this, "请选择省份");
                    break;
                } else if (TextUtils.isEmpty(cityStr)) {
                    ToastUtil.showShort(this, "请选择城市");
                    break;
                } else if (TextUtils.isEmpty(areaStr)) {
                    ToastUtil.showShort(this, "请选择区镇");
                    break;
                } else if (TextUtils.isEmpty(receivedDetialAddress)) {
                    ToastUtil.showShort(this, "请输入收件人详细地址");
                    break;
                } else {
                    //更新
                    if (id!=0) {
                        createSingleUpdate(id, receivedName, receivedMobile, provinceStr, cityStr, areaStr, receivedDetialAddress);
                    } else {
                        //增加
                        createSingle(receivedName, receivedMobile, provinceStr, cityStr, areaStr, receivedDetialAddress);
                    }
                }

                break;
        }
    }

    /**
     * 弹出选择器
     */
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
                etReceivedAddress.setText("" + sb.toString());
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(AddReceiveAddressActivity.this, "已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }

    /*************新增地址******************/
    private Callback<NewReceivedResult> callback = new Callback<NewReceivedResult>() {
        @Override
        public void onResponse(Call<NewReceivedResult> call, Response<NewReceivedResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                radlRes = response.body();
                if ("00".equals(radlRes.getCode())) {
                    if (radlRes.getData() != null) {
//                        NewReceivedResult.Data nrr = new NewReceivedResult().getData();
//                        nrr.setArea(radlRes.getData().getArea());
//                        nrr.setCity(radlRes.getData().getCity());
//                        nrr.setDetail(radlRes.getData().getDetail());
//                        nrr.setProvince(radlRes.getData().getProvince());
//                        nrr.setIsDefault(radlRes.getData().getIsDefault());
//                        nrr.setMobile(radlRes.getData().getMobile());
//                        nrr.setName(radlRes.getData().getName());
//                        nrr.setUid(radlRes.getData().getUid());
//                        nrr.setId(radlRes.getData().getId());
//
//                        Intent intent = new Intent(AddReceiveAddressActivity.this, ReceivedAddressActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("received", nrr);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
                        ToastUtil.showShort(AddReceiveAddressActivity.this, "新增成功");
                        finish();
                    } else {
                        ToastUtil.showShort(AddReceiveAddressActivity.this, "新增地址");
                    }
                } else {
                    ToastUtil.showShort(AddReceiveAddressActivity.this, radlRes.getMsg());
                }

            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewReceivedResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String name, String mobile, String province, String city, String area, String detial) {
        Call<NewReceivedResult> call = nraddApi.getService().createCommit(token, uid, name,
                mobile, province, city, area, detial, true);
        call.enqueue(callback);
    }

    /*****************更新地址*********************/
    private Callback<NewReceivedResult> callbackupdate = new Callback<NewReceivedResult>() {
        @Override
        public void onResponse(Call<NewReceivedResult> call, Response<NewReceivedResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                radlRes = response.body();
                if ("00".equals(radlRes.getCode())) {
                    if (radlRes.getData() != null) {
                        ToastUtil.showShort(AddReceiveAddressActivity.this, "更新成功");
                        finish();
                    } else {
                        ToastUtil.showShort(AddReceiveAddressActivity.this, "更新地址");
                    }
                } else {
                    ToastUtil.showShort(AddReceiveAddressActivity.this, radlRes.getMsg());
                }

            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewReceivedResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleUpdate(int id, String name, String mobile, String province, String city, String area, String detial) {
        Call<NewReceivedResult> call = nraddApi.getService().createCommitPutId(token, id, uid, name,
                mobile, province, city, area, detial, true);
        call.enqueue(callbackupdate);
    }


    /*********************获取单个地址信息******************/
    private Callback<NewReceivedResult> callbackdel = new Callback<NewReceivedResult>() {
        @Override
        public void onResponse(Call<NewReceivedResult> call, Response<NewReceivedResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                nrres = response.body();
                if ("00".equals(nrres.getCode())) {
                    etReceivedPerson.setText(nrres.getData().getName());
                    etReceivedPersonMobile.setText(nrres.getData().getMobile());
                    etReceivedDetialAddress.setText(nrres.getData().getDetail());
                    provinceStr = nrres.getData().getProvince();
                    cityStr = nrres.getData().getCity();
                    areaStr = nrres.getData().getArea();
                    etReceivedAddress.setText(provinceStr + cityStr + areaStr);
                } else {
                    ToastUtil.showShort(AddReceiveAddressActivity.this, nrres.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewReceivedResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleGet(int id) {
        Call<NewReceivedResult> calldel = ralApi.getService().createCommitGetId(token, id);
        calldel.enqueue(callbackdel);
    }
}
