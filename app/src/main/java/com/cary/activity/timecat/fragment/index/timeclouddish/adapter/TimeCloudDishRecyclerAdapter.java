package com.cary.activity.timecat.fragment.index.timeclouddish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.CloudDishPhotoCommitResult;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cary on 2018/4/10.
 */

public class TimeCloudDishRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<CloudDishPhotoCommitResult.Data> mList=new ArrayList<>();
    private OnItemClickListener mClickListener;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public TimeCloudDishRecyclerAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_time_cloud_dish_flod_item, parent, false);
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

    public CloudDishPhotoCommitResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<CloudDishPhotoCommitResult.Data> list) {
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

    public void reSetData(List<CloudDishPhotoCommitResult.Data> list) {
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
//        View childView = inflater.inflate(R.layout.fragment_time_cloud_dish_item, parent, false);
//        AuthorViewHolder viewHolder = new AuthorViewHolder(childView, mClickListener);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(AuthorViewHolder holder, int position) {
////        mList.get(position).getFolder()+
//        holder.tv_time_cloud_dish_title.setText(mList.get(position).getFolder());
//        Glide.with(mContext).load(mContext.getResources().getDrawable(R.mipmap.folder)).into(holder.iv_time_cloud_dish_img);
//
//    }
//
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

    class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_time_cloud_dish_title;
        ImageView iv_time_cloud_dish_img;
        private OnItemClickListener mListener;// 声明自定义的接口
        private Context mContext;

        public AuthorViewHolder(View itemView, OnItemClickListener listener, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            mListener = listener;
            tv_time_cloud_dish_title = (TextView) itemView.findViewById(R.id.tv_time_cloud_dish_title);
            iv_time_cloud_dish_img = (ImageView) itemView.findViewById(R.id.iv_time_cloud_dish_img);
            itemView.setOnClickListener(this);
        }

        public void setData(CloudDishPhotoCommitResult.Data data) {
//            String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL+data.getFolder();
//            Glide.with(mContext).load(imageUrl).into(iv_time_cloud_dish_img);
            tv_time_cloud_dish_title.setText(data.getFolder());
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}