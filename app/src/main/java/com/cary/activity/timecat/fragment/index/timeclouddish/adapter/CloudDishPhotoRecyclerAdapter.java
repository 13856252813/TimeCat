package com.cary.activity.timecat.fragment.index.timeclouddish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.photo.CloudDishPhotoResult;
import com.cary.activity.timecat.http.base.HttpUrlClient;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Cary on 2018/4/10.
 * 云盘 显示图片adapter
 */

public class CloudDishPhotoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<CloudDishPhotoResult.Data> mList;
    private TreeMap<CloudDishPhotoResult.Data, Boolean> mMap;
    private OnItemClickCoustomListener mClickListener;
    private boolean isShow;//是否显示可编辑

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public CloudDishPhotoRecyclerAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_time_cloud_dish_pic_item, parent, false);
        AuthorViewHolder holder = new AuthorViewHolder(view, mClickListener, mContext);
        return holder;
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

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AuthorViewHolder viewHolder = (AuthorViewHolder) holder;
        //选择
        if (isShow) {
            viewHolder.check_cloud_dish_isshow.setVisibility(View.VISIBLE);
        } else {
            viewHolder.check_cloud_dish_isshow.setVisibility(View.GONE);
        }
        //选中单个
        viewHolder.check_cloud_dish_isshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMap.put(mList.get(position), true);
                } else {
                    mMap.put(mList.get(position), false);
                }
                mClickListener.onSelectView(mMap);
            }
        });
        viewHolder.setData(getItem(position));
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public CloudDishPhotoResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<CloudDishPhotoResult.Data> list) {
        this.mList.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            mMap.put(list.get(i), false);
        }
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

    public void reSetData(List<CloudDishPhotoResult.Data> list) {
        this.mList = list;
        for (int i = 0; i < list.size(); i++) {
            mMap.put(list.get(i), false);
        }
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
//    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View childView = inflater.inflate(R.layout.fragment_time_cloud_dish_item, parent, false);
//        AuthorViewHolder viewHolder = new AuthorViewHolder(childView, mClickListener);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(AuthorViewHolder holder, int position) {
//        holder.tv_time_cloud_dish_title.setText("照片"+mList.get(position).getFileName());
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

    public void setOnItemClickCoustomListener(OnItemClickCoustomListener listener) {
        this.mClickListener = listener;
    }

    public void setShowEdit(boolean isShow) {
        this.isShow = isShow;
    }

    public interface OnItemClickCoustomListener {
        void onClickView(View v, int position);//图片点击

        void onImageView(int position);//后面的图片操作

        void onSelectView(TreeMap<CloudDishPhotoResult.Data, Boolean> mMap);//判断选中
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_time_cloud_dish_title;
        ImageView iv_time_cloud_dish_img;
        TextView tv_time_cloud_dish_mark;
        CheckBox check_cloud_dish_isshow;
        ImageView ivtime_cloud_dish_right;
        private OnItemClickCoustomListener mListener;// 声明自定义的接口
        private Context mContext;

        public AuthorViewHolder(View itemView, OnItemClickCoustomListener listener, Context mContext) {
            super(itemView);
            mListener = listener;
            this.mContext = mContext;
            tv_time_cloud_dish_title = (TextView) itemView.findViewById(R.id.tv_time_cloud_dish_title);
            iv_time_cloud_dish_img = (ImageView) itemView.findViewById(R.id.iv_time_cloud_dish_img);
            tv_time_cloud_dish_mark = itemView.findViewById(R.id.tv_time_cloud_dish_mark);
            check_cloud_dish_isshow = itemView.findViewById(R.id.check_cloud_dish_isshow);
            ivtime_cloud_dish_right = (ImageView) itemView.findViewById(R.id.iv_time_cloud_dish_right);
            ivtime_cloud_dish_right.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void setData(CloudDishPhotoResult.Data data) {
            //服务器+bucketname
            String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getBucketName();
            Glide.with(mContext).load(imageUrl).into(iv_time_cloud_dish_img);
            tv_time_cloud_dish_title.setText(data.getFileName());
            String mark = data.getNote();
            if (!TextUtils.isEmpty(mark)) {
                tv_time_cloud_dish_mark.setVisibility(View.VISIBLE);
                tv_time_cloud_dish_mark.setText(mark);
            } else {
                tv_time_cloud_dish_mark.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            if (v == ivtime_cloud_dish_right) {
                mListener.onImageView(getPosition());
            } else {
                mListener.onClickView(v, getPosition());
            }
        }
    }
}