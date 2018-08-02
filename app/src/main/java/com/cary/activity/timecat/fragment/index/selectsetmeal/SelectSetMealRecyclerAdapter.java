package com.cary.activity.timecat.fragment.index.selectsetmeal;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by Cary on 2018/4/10.
 */

public class SelectSetMealRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<SetMealResult.Data> mList;
    private OnItemClickListener mClickListener;
    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public SelectSetMealRecyclerAdapter(Context mContext ) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else if (viewType == VIEW_TYPE_ITEM) {
            return onCreateItemViewHolder(parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_ITEM:
                onBindItemViewHolder(holder, position);
                break;
            case VIEW_TYPE_FOOTER:
                onBindFooterViewHolder(holder, position, mLoadStatus);
                break;
            default:
                break;
        }
    }

    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footer_layout, parent, false);
        return new FooterViewHolder(view);
    }

    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_set_meal_item, parent, false);
        return new AuthorViewHolder(view, mClickListener, mContext);
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int poition, LoadStatus loadStatus) {
        FooterViewHolder viewHolder = (FooterViewHolder) holder;
        switch (loadStatus) {
            case CLICK_LOAD_MORE:
                viewHolder.mLoadingLayout.setVisibility(View.GONE);
                viewHolder.mClickLoad.setVisibility(View.VISIBLE);
                break;
            case LOADING_MORE:
                viewHolder.mLoadingLayout.setVisibility(View.VISIBLE);
                viewHolder.mClickLoad.setVisibility(View.GONE);
                break;
            case LOADING_GONE:
                viewHolder.mLoadingLayout.setVisibility(View.GONE);
                viewHolder.mClickLoad.setVisibility(View.GONE);
                break;
        }
    }

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        AuthorViewHolder viewHolder = (AuthorViewHolder) holder;
        viewHolder.setData(getItem(position));
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public SetMealResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<SetMealResult.Data> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {//最后一条为FooterView
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size() + 1;
        }
        return 0;
    }

    public void reSetData(List<SetMealResult.Data> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setLoadStatus(LoadStatus loadStatus) {
        this.mLoadStatus = loadStatus;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLoadingLayout;
        public TextView mClickLoad;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mLoadingLayout = (LinearLayout) itemView.findViewById(R.id.loading);
            mClickLoad = (TextView) itemView.findViewById(R.id.click_load_txt);
/* mClickLoad.setOnClickListener(new View.OnClickListener() {
//添加点击加载更多监听
                @Override
                public void onClick(View view) {
                    loadMore();
                }
            });*/
        }
    }


//
//    @Override
//    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View childView = inflater.inflate(R.layout.select_set_meal_item, parent, false);
//        AuthorViewHolder viewHolder = new AuthorViewHolder(childView,mClickListener);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(AuthorViewHolder holder, int position) {
//
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView select_set_meal_sell_num;
        TextView select_set_meal_price;
        TextView tv_select_set_meal_title;
        ImageView iv_select_set_meal_img;
        TextView tv_select_setmeal_price_old;
        private OnItemClickListener mListener;// 声明自定义的接口
        private Context mContext;

        public AuthorViewHolder(View itemView,OnItemClickListener listener,Context mContext) {
            super(itemView);
            mListener = listener;
            this.mContext = mContext;
            select_set_meal_sell_num = (TextView) itemView.findViewById(R.id.select_set_meal_sell_num);
            select_set_meal_price = (TextView) itemView.findViewById(R.id.select_set_meal_price);
            tv_select_set_meal_title = (TextView) itemView.findViewById(R.id.tv_select_set_meal_title);
            iv_select_set_meal_img = (ImageView) itemView.findViewById(R.id.iv_select_set_meal_img);
            tv_select_setmeal_price_old = itemView.findViewById(R.id.select_set_meal_price_old);
            tv_select_setmeal_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            itemView.setOnClickListener(this);
        }
        public void setData(SetMealResult.Data data){
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(420,360)
//                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL+data.getImgurl();
        Glide.with(mContext).load(imageUrl).apply(options2).into(iv_select_set_meal_img);
        tv_select_set_meal_title.setText(data.getTitle());
        select_set_meal_price.setText("¥"+data.getPrice()+"-"+data.getMarketPrice());
        select_set_meal_sell_num.setText("已售出"+data.getSellCount()+"次");
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}