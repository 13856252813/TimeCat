package com.cary.activity.timecat.fragment.person.myorder.orderdetial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.BaseInfoMessageActivity;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.SelectColothActivity;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.SelectScenicActivity;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.SelectSniecAdapter;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.SelectTeacherActivity;
import com.cary.activity.timecat.fragment.index.photography.PayPhotoGraphyOrderActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.fragment.person.myorder.ToBeCompletedAdapter;
import com.cary.activity.timecat.util.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 套餐信息
 */
public class OrderInfomationFragment extends Fragment {
    private static String TAG = OrderInfomationFragment.class.getSimpleName();
    @BindView(R.id.iv_confirm_order_commodity)
    ImageView ivConfirmOrderCommodity;
    @BindView(R.id.tv_confirm_order_commodity_title)
    TextView tvConfirmOrderCommodityTitle;
    @BindView(R.id.tv_confirm_order_commodity_price)
    TextView tvConfirmOrderCommodityPrice;
    @BindView(R.id.tv_confirm_order_commodity_desc)
    TextView tvConfirmOrderCommodityDesc;
    @BindView(R.id.tv_confirm_order_commodity_store)
    TextView tvConfirmOrderCommodityStore;
    @BindView(R.id.iv_confirm_order_user_arrow)
    ImageView ivConfirmOrderUserArrow;
    @BindView(R.id.rl_confirm_order_user)
    RelativeLayout rlConfirmOrderUser;
    @BindView(R.id.iv_confirm_order_user_head)
    ImageView ivConfirmOrderUserHead;
    @BindView(R.id.tv_confirm_order_user_name)
    TextView tvConfirmOrderUserName;
    @BindView(R.id.tv_confirm_order_user_bridegroom_text)
    TextView tvConfirmOrderUserBridegroomText;
    @BindView(R.id.tv_confirm_order_user_bridegroom)
    TextView tvConfirmOrderUserBridegroom;
    @BindView(R.id.tv_confirm_order_user_brideg_text)
    TextView tvConfirmOrderUserBridegText;
    @BindView(R.id.tv_confirm_order_user_bride)
    TextView tvConfirmOrderUserBride;
    @BindView(R.id.tv_confirm_order_user_weddingday_text)
    TextView tvConfirmOrderUserWeddingdayText;
    @BindView(R.id.tv_confirm_order_user_weddingday)
    TextView tvConfirmOrderUserWeddingday;
    @BindView(R.id.tv_confirm_order_user_weddingtime_text)
    TextView tvConfirmOrderUserWeddingtimeText;
    @BindView(R.id.tv_confirm_order_user_weddingtime)
    TextView tvConfirmOrderUserWeddingtime;
    @BindView(R.id.tv_confirm_order_user_scneic_text)
    TextView tvConfirmOrderUserScneicText;
    @BindView(R.id.tv_confirm_order_user_scneic)
    TextView tvConfirmOrderUserScneic;
    @BindView(R.id.tv_confirm_order_user_reception_text)
    TextView tvConfirmOrderUserReceptionText;
    @BindView(R.id.tv_confirm_order_user_reception)
    TextView tvConfirmOrderUserReception;
    @BindView(R.id.iv_confirm_order_camcerman)
    ImageView ivConfirmOrderCamcerman;
    @BindView(R.id.tv_confirm_order_camcerman)
    TextView tvConfirmOrderCamcerman;
    @BindView(R.id.tv_confirm_order_camcerman_text)
    TextView tvConfirmOrderCamcermanText;
    @BindView(R.id.tv_confirm_order_camcerman_price)
    TextView tvConfirmOrderCamcermanPrice;
    @BindView(R.id.tv_confirm_order_camcerman_setmeal)
    TextView tvConfirmOrderCamcermanSetmeal;
    @BindView(R.id.iv_confirm_order_dresser)
    ImageView ivConfirmOrderDresser;
    @BindView(R.id.tv_confirm_order_dresser)
    TextView tvConfirmOrderDresser;
    @BindView(R.id.tv_confirm_order_dresser_text)
    TextView tvConfirmOrderDresserText;
    @BindView(R.id.tv_confirm_order_dresser_price)
    TextView tvConfirmOrderDresserPrice;
    @BindView(R.id.tv_confirm_order_dresser_setmeal)
    TextView tvConfirmOrderDresserSetmeal;
    @BindView(R.id.iv_confirm_order_numerical)
    ImageView ivConfirmOrderNumerical;
    @BindView(R.id.tv_confirm_order_numerical)
    TextView tvConfirmOrderNumerical;
    @BindView(R.id.tv_confirm_order_numerical_text)
    TextView tvConfirmOrderNumericalText;
    @BindView(R.id.tv_confirm_order_numical_price)
    TextView tvConfirmOrderNumicalPrice;
    @BindView(R.id.tv_confirm_order_clothing_text)
    TextView tvConfirmOrderClothingText;
    @BindView(R.id.iv_confirm_order_clothing)
    ImageView ivConfirmOrderClothing;
    @BindView(R.id.tv_confirm_order_clothing_name)
    TextView tvConfirmOrderClothingName;
    @BindView(R.id.iv_confirm_order_clothing_add_boy)
    ImageView ivConfirmOrderClothingAddBoy;
    @BindView(R.id.ll_confirm_order_clothing_add_boy)
    LinearLayout llConfirmOrderClothingAddBoy;
    @BindView(R.id.tv_confirm_order_clothing_name_add_boy)
    TextView tvConfirmOrderClothingNameAddBoy;
    @BindView(R.id.tv_confirm_order_clothing_text_two)
    TextView tvConfirmOrderClothingTextTwo;
    @BindView(R.id.iv_confirm_order_clothing_two)
    ImageView ivConfirmOrderClothingTwo;
    @BindView(R.id.tv_confirm_order_clothing_name_two)
    TextView tvConfirmOrderClothingNameTwo;
    @BindView(R.id.iv_confirm_order_clothing_add_boy_two)
    ImageView ivConfirmOrderClothingAddBoyTwo;
    @BindView(R.id.ll_confirm_order_clothing_add_girl)
    LinearLayout llConfirmOrderClothingAddGirl;
    @BindView(R.id.tv_confirm_order_clothing_name_add_girl)
    TextView tvConfirmOrderClothingNameAddGirl;
    @BindView(R.id.recyclerview_confirm_order_secnic)
    RecyclerView recyclerviewConfirmOrderSecnic;
    @BindView(R.id.iv_confirm_order_secnic_add)
    ImageView ivConfirmOrderSecnicAdd;
    @BindView(R.id.tv_confirm_order_secnic_two)
    TextView tvConfirmOrderSecnicTwo;
    @BindView(R.id.tv_confirm_order_secnic_name_two)
    TextView tvConfirmOrderSecnicNameTwo;
    @BindView(R.id.rl_confirm_order_secnic_add)
    RelativeLayout rlConfirmOrderSecnicAdd;
    @BindView(R.id.tv_confirm_order_setmealmoney_text)
    TextView tvConfirmOrderSetmealmoneyText;
    @BindView(R.id.tv_confirm_order_setmealmoney_flag)
    TextView tvConfirmOrderSetmealmoneyFlag;
    @BindView(R.id.tv_confirm_order_setmealmoney)
    TextView tvConfirmOrderSetmealmoney;
    @BindView(R.id.tv_confirm_order_setmealmoney_two)
    TextView tvConfirmOrderSetmealmoneyTwo;
    @BindView(R.id.tv_confirm_order_commit_money)
    TextView tvConfirmOrderCommitMoney;
    @BindView(R.id.ll_confirm_order_dresser)
    LinearLayout llConfirmOrderDresser;
    @BindView(R.id.ll_confirm_order_camcerman)
    LinearLayout llConfirmOrderCamcerman;
    @BindView(R.id.tv_order_information_stage)
    TextView tvOrderInformationStage;
    @BindView(R.id.tv_order_information_surplus)
    TextView tvOrderInformationSurplus;
    @BindView(R.id.iv_order_information_image)
    ImageView ivOrderInformationImage;
    @BindView(R.id.tv_order_information_mark)
    TextView tvOrderInformationMark;
    @BindView(R.id.tv_order_information_order_number_text)
    TextView tvOrderInformationOrderNumberText;
    @BindView(R.id.tv_order_information_order_number)
    TextView tvOrderInformationOrderNumber;
    @BindView(R.id.tv_order_information_creattime_text)
    TextView tvOrderInformationCreattimeText;
    @BindView(R.id.tv_order_information_order_creattime)
    TextView tvOrderInformationOrderCreattime;
    @BindView(R.id.tv_order_information_money_creattime_text)
    TextView tvOrderInformationMoneyCreattimeText;
    @BindView(R.id.tv_order_information_money_creattime)
    TextView tvOrderInformationMoneyCreattime;
    @BindView(R.id.tv_order_information_ready_money_text)
    TextView tvOrderInformationReadyMoneyText;
    @BindView(R.id.tv_order_information_ready_money)
    TextView tvOrderInformationReadyMoney;
    @BindView(R.id.tv_order_information_money_overtime_text)
    TextView tvOrderInformationMoneyOvertimeText;
    @BindView(R.id.tv_order_information_money_overtime)
    TextView tvOrderInformationMoneyOvertime;
    @BindView(R.id.tv_confirm_order_alreadymoney)
    TextView tvConfirmOrderAlreadymoney;
    Unbinder unbinder;
    private SelectSniecAdapter adapter;

    private ToBeCompletedAdapter myAdapter;
    private SharedPreferencesHelper sharePH;

    public OrderInfomationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_information, container, false);
        unbinder = ButterKnife.bind(this, view);
        sharePH = new SharedPreferencesHelper(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewConfirmOrderSecnic.setLayoutManager(linearLayoutManager);
        adapter = new SelectSniecAdapter(getActivity());
        //设置item间距，30dp
        recyclerviewConfirmOrderSecnic.addItemDecoration(new SpaceItemDecoration(20));
        recyclerviewConfirmOrderSecnic.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({ R.id.iv_confirm_order_commodity, R.id.ll_confirm_order_camcerman,
            R.id.ll_confirm_order_dresser, R.id.rl_confirm_order_user,
            R.id.ll_confirm_order_clothing_add_boy, R.id.ll_confirm_order_clothing_add_girl,
            R.id.rl_confirm_order_secnic_add, R.id.tv_confirm_order_commit_money})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_confirm_order_commodity:

                break;
            case R.id.ll_confirm_order_camcerman:
                intent.setClass(getActivity(), SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "camcer");
                startActivity(intent);
                break;
            case R.id.ll_confirm_order_dresser:
                intent.setClass(getActivity(), SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "dresser");
                intent.setClass(getActivity(), SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "camcer");
                startActivity(intent);
                break;
            case R.id.rl_confirm_order_user:
                intent.setClass(getActivity(), BaseInfoMessageActivity.class);

                startActivity(intent);
                break;
            case R.id.ll_confirm_order_clothing_add_boy:
                intent.setClass(getActivity(), SelectColothActivity.class);
                intent.putExtra("sex", "2");
                startActivity(intent);
                break;
            case R.id.ll_confirm_order_clothing_add_girl:
                intent.setClass(getActivity(), SelectColothActivity.class);
                intent.putExtra("sex", "1");
                startActivity(intent);
                break;
            case R.id.rl_confirm_order_secnic_add:
                intent.setClass(getActivity(), SelectScenicActivity.class);

                startActivity(intent);
                break;
            case R.id.tv_confirm_order_commit_money:
                //跳转到评价
                intent.setClass(getActivity(), PayPhotoGraphyOrderActivity.class);

                startActivity(intent);
                break;
        }
    }
}
