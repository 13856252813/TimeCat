package com.cary.activity.timecat.fragment.index.fulldress.selection;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialResult;

/*
 * 项目名:    shopping-selection
 * 包名       com.zsy.shoppingselect
 * 文件名:    ShoppingSelectView
 * 创建者:
 * 创建时间:  2017/5/5 on 15:58
 * 描述:     TODO 商品规格选择View
 */
public class ShoppingSelectView extends LinearLayout {
    /**
     * 数据源
     */
//    private List<FullDressColothNorm.Data> list;
    private FullDressDetialResult list;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 规格标题栏的文本间距
     */
    private int titleMargin = 8;
    /**
     * 整个商品属性的左右间距
     */
    private int flowLayoutMargin = 16;
    /**
     * 属性按钮的高度
     */
    private int buttonHeight = 25;
    /**
     * 属性按钮之间的左边距
     */
    private int buttonLeftMargin = 10;
    /**
     * 属性按钮之间的上边距
     */
    private int buttonTopMargin = 8;
    /**
     * 文字与按钮的边距
     */
    private int textPadding = 10;
    /**
     * 选择后的回调监听
     */
    private OnSelectedListener listener;


    public ShoppingSelectView(Context context) {
        super(context);
        initView(context);
    }

    public ShoppingSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        this.context = context;
    }

    @SuppressLint("ResourceAsColor")
    public void getView() {
        if (list.getData().getNorms().size() < 0) {
            return;
        }
//        for (FullDressColothNorm.Data attr : list) {
        for (FullDressDetialResult.Norms attr : list.getData().getNorms()) {
            //设置规格分类的标题
            TextView textView = new TextView(context);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = dip2px(context, titleMargin);
//            textView.setText(attr.getSku_name());
            textView.setText(attr.getNorm());
            params.setMargins(margin, margin, margin, margin);
            textView.setLayoutParams(params);
            addView(textView);
            //设置一个大规格下的所有小规格
            FlowLayout layout = new FlowLayout(context);
//            layout.setTitle(attr.getSku_name());
            layout.setTitle(attr.getNorm());
            layout.setPadding(dip2px(context, flowLayoutMargin), 0, dip2px(context, flowLayoutMargin), 0);
            //设置选择监听
            if (listener != null) {
                layout.setListener(listener);
            }
//            for (int i = 0; i < attr.getValue_list().size(); i++) {
            for (int i = 0; i < attr.getItems().size(); i++) {
//                GoodsBean.SkuInfoBean.ValueListBean smallAttr = attr.getValue_list().get(i);
                FullDressDetialResult.Items smallAttr = attr.getItems().get(i);
                //属性按钮
                RadioButton button = new RadioButton(context);
                //默认选中第一个
                /*if (i == 0) {
                    button.setChecked(true);
                }*/
                //设置按钮的参数
                LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                        dip2px(context, buttonHeight));
                //设置文字的边距
                int padding = dip2px(context, textPadding);
                button.setPadding(padding, 0, padding, 0);
                //设置margin属性，需传入LayoutParams否则会丢失原有的布局参数
                MarginLayoutParams marginParams = new MarginLayoutParams(buttonParams);
                marginParams.leftMargin = dip2px(context, buttonLeftMargin);
                marginParams.topMargin = dip2px(context, buttonTopMargin);

                button.setLayoutParams(marginParams);
                button.setGravity(Gravity.CENTER);
                button.setBackgroundResource(R.drawable.tv_sel);
                button.setButtonDrawable(android.R.color.transparent);
                button.setTextColor(getResources().getColor(R.color.radiobutton_textcolor));
//                button.setText(smallAttr.getValue_name());//设置规格内容
//                button.setId(smallAttr.getValue_id());//设置规格ID
                button.setText(smallAttr.getItem());//设置规格内容
                button.setId(smallAttr.getNormId());//设置规格ID
                layout.addView(button);
            }
            addView(layout);
        }
    }
    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setData(FullDressDetialResult data) {
        list = data;
        getView();
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }
}