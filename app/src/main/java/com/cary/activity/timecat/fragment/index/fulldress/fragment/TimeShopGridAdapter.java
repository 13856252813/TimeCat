package com.cary.activity.timecat.fragment.index.fulldress.fragment;

import android.content.Context;
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
import com.cary.activity.timecat.fragment.index.timeshop.fragment.TimeShopProductResult;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.List;

public class TimeShopGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = TimeShopGridAdapter.class.getSimpleName();

    private List<TimeShopProductResult.DataBean> mList;
    private Context mContext;
    private OnItemClickListener mClickListener;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;


    public TimeShopGridAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_show_image_item, parent, false);
        return new FullDessGridViewHodler(view, mClickListener, mContext);
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
        FullDessGridViewHodler viewHolder = (FullDessGridViewHodler) holder;
        viewHolder.setData(getItem(position));
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public TimeShopProductResult.DataBean getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<TimeShopProductResult.DataBean> list) {
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

    public void reSetData(List<TimeShopProductResult.DataBean> list) {
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


//    @Override
//    public FullDessGridViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View childView = inflater.inflate(R.layout.activity_show_image_item, parent, false);
//        FullDessGridViewHodler viewHolder = new FullDessGridViewHodler(childView, mClickListener);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final FullDessGridViewHodler holder, final int position) {
//        holder.showName.setText(mList.get(position));
//        holder.showPrice.setText("¥"+mList.get(position));
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(960, 480)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH);
////                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
////        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
////        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
////        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
//        holder.itemView.setTag(position);
//
//    }
//    @Override
//    public int getItemCount() {
//        if (mList != null && mList.size() > 0) {
//            return mList.size();
//        }
//        return mList.size();
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    class FullDessGridViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView showImage;
        TextView showName;
        TextView showPrice;
        private OnItemClickListener mListener;// 声明自定义的接口
        private Context mContext;

        public FullDessGridViewHodler(View itemView, OnItemClickListener mListener, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            showImage = (ImageView) itemView.findViewById(R.id.imgeview_item_show_image);
            showName = (TextView) itemView.findViewById(R.id.imgeview_item_name);
            showPrice = (TextView) itemView.findViewById(R.id.imgeview_item_price);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        public void setData(TimeShopProductResult.DataBean data) {
            showName.setText(data.getTitle()+"");
            showPrice.setText("¥" + data.getAmount());
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(174, 174)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(showImage);
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
