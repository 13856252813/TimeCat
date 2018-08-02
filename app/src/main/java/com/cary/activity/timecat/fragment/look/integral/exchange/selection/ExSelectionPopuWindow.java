package com.cary.activity.timecat.fragment.look.integral.exchange.selection;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.selection.OnSelectedListener;
import com.cary.activity.timecat.fragment.look.integral.exchange.IntegralDetialResult;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.TreeMap;

/**
 *
 */

public class ExSelectionPopuWindow implements View.OnClickListener, View.OnTouchListener, OnSelectedListener {

    private static final String TAG = ExSelectionPopuWindow.class.getSimpleName();
    private ImageView ivSelectionImage;
    private TextView tvSelectionName;
    private TextView tvSelectionPrice;
    private TextView tvSelectionSpecification;
    private TextView tvSelectionRepertory;


    private PopupWindow popupWindow;
    private View mMenuView;
    private Activity mContext;
    private ExShoppingSelectView view;
    //    String jsonString;
//    GoodsBean mData;
    private IntegralDetialResult mData;
    private TextView confirm;
    String title = "";
    String smallTitle = "";
    int id;
    private TreeMap<String, String> map = new TreeMap<>();
    //    private View.OnClickListener clickListener;
    private OnClickConfrmListener clickListener;

    public interface OnClickConfrmListener {
        void onClick(View v, String smallTitle);
    }

    public ExSelectionPopuWindow(Activity context, OnClickConfrmListener clickListener, IntegralDetialResult mData) {
        this.mData = mData;
        LayoutInflater inflater = LayoutInflater.from(context);
        mContext = context;
        this.clickListener = clickListener;
        mMenuView = inflater.inflate(R.layout.activity_full_dress_exselection_layout, null);
        view = (ExShoppingSelectView) mMenuView.findViewById(R.id.v);
        //设置监听需要在设置数据源之前
        view.setOnSelectedListener(this);
        confirm = mMenuView.findViewById(R.id.tv_selection_confirm);
        confirm.setOnClickListener(this);
        ivSelectionImage = mMenuView.findViewById(R.id.iv_selection_image);
        tvSelectionName = mMenuView.findViewById(R.id.tv_selection_name);
        tvSelectionPrice = mMenuView.findViewById(R.id.tv_selection_price);
        tvSelectionSpecification = mMenuView.findViewById(R.id.tv_selection_specification);
        tvSelectionRepertory = mMenuView.findViewById(R.id.tv_selection_repertory);

//        initJsonData();
//        getData();
        initData();
        String ImageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL+mData.getData().getImgurl();
        Glide.with(context).load(ImageUrl).into(ivSelectionImage);
        tvSelectionName.setText(mData.getData().getTitle());
        tvSelectionPrice.setText("¥" + mData.getData().getScore());

        popupWindow = new PopupWindow(mMenuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.white));
        popupWindow.setBackgroundDrawable(dw);
//        mMenuView.setOnTouchListener(this);
    }

    /**
     * 显示菜单
     */
    public void show() {
        //得到当前activity的rootView
        View rootView = ((ViewGroup) mContext.findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public void cancel() {
        popupWindow.dismiss();
    }


    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        int height = mMenuView.findViewById(R.id.pop_layout).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (y < height) {
                popupWindow.dismiss();
            }
        }
        return true;
    }

    //本地数据测试专用
//    private void initJsonData() {
//        try {
//            InputStream is = mContext.getAssets().open("specs.json");//打开json数据
//            byte[] by = new byte[is.available()];//转字节
//            is.read(by);
//            jsonString = new String(by, "utf-8");
//            is.close();//关闭流
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //解析Json数据
//    private void getData() {
//        Gson gson = new Gson();
//        mData = gson.fromJson(jsonString, GoodsBean.class);
//    }

    private void initData() {
        //最终数据
        view.setData(mData);
        Log.d(TAG, mData + "");
    }

    @Override
    public void onSelected(String title, String smallTitle, int id) {
//        Toast.makeText(mContext, item.getNormId() + "--" + item.getItem(), Toast.LENGTH_SHORT).show();
        //将获取的数据传递给上一个界面
//        this.items = item;
        this.title = title;
        this.smallTitle = smallTitle;
        this.id = id;
        map.put(title, smallTitle);

//        tvSelectionRepertory.setText("库存："+mData.getData().getNorms().get()+"件");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
//                clickListener.onClick(v);
                String[] smallTitleArray = smallTitle.split(",");
                String tempStr = "";
                for (String value : map.values()) {
                    tempStr += value + ",";
//                for (int i = 0; i < smallTitleArray.length - 1; i++) {
//                    if (!TextUtils.isEmpty(smallTitleArray[i])) {
//                        tempStr += smallTitleArray[i] + ",";
//                    }
                }
                int tempLength = (tempStr.split(",")).length;
                if (!TextUtils.isEmpty(tempStr) && tempLength == mData.getData().getNorms().size()) {
                    clickListener.onClick(v, tempStr);
                } else {
                    ToastUtil.showShort(mContext, "规格未选择完善");
                }
                break;
        }
    }
}