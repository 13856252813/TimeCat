package com.cary.activity.timecat.manager.teacher.fragment.invitaion;

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
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by Cary on 2018/4/18.
 */

public class MyInvitaionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InvitaionResult.Data> mDateBeen;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private Context mContext;
    private OnItemClickListener mClickListener;


    public MyInvitaionAdapter(Context context) {
        this.mContext = context;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_my_friend_item, parent, false);
        return new MyViewHolder(view, mClickListener);
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
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.setData(getItem(position));
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public InvitaionResult.Data getItem(int position) {
        return mDateBeen.get(position);
    }

    public void addAll(List<InvitaionResult.Data> list) {
        this.mDateBeen.addAll(list);
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
        if (mDateBeen != null && mDateBeen.size() > 0) {
            return mDateBeen.size() + 1;
        }
        return 0;
    }

    public void reSetData(List<InvitaionResult.Data> list) {
        this.mDateBeen = list;
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
//    //这里主要初始化布局控件
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
//        //初始化布局文件
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_friend_item, parent, false);
//        MyViewHolder viewHolder = new MyViewHolder(inflate, mClickListener);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        //放入集合中的值
//        holder.txt.setText(list.get(position));
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(60, 60)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH)
//                .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
//
//        Glide.with(mContext).load(list.get(position)).apply(options2).into(holder.image);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mClickListener = listener;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View itemView;
        private TextView txt;
        private ImageView image;
        private OnItemClickListener mListener;// 声明自定义的接口

        //初始化控件
        public MyViewHolder(View itemView, OnItemClickListener mListener) {
            super(itemView);
            this.itemView = itemView;
            this.mListener = mListener;
            image = (ImageView) itemView.findViewById(R.id.select_friend_pay_img);
            txt = (TextView) itemView.findViewById(R.id.select_friend_pay_name);
            itemView.setOnClickListener(this);
        }

        public void setData(InvitaionResult.Data data) {
            txt.setText(data.getNickname());
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(40, 30)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.avatarw)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            Glide.with(mContext).load(data.getImgurl()).apply(options2).into(image);

        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
