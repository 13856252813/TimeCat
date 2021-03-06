package com.cary.activity.timecat.fragment.message.myfriend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.view.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cary on 2018/4/18.
 */

public class MyFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //这个是数据集合
    private List<FriendListResult.Data> mList = new ArrayList<>();

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

    public MyFriendAdapter(Context context) {
        this.mContext = context;
    }

    public void setClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
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
        return new MyViewHolder(view);
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

    public FriendListResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<FriendListResult.Data> list) {
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

    public void reSetData(List<FriendListResult.Data> list) {
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView txt;
        ImageView image;
        CheckBox checkBox;

        //初始化控件
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = (ImageView) itemView.findViewById(R.id.select_friend_pay_img);
            txt = (TextView) itemView.findViewById(R.id.select_friend_pay_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.isselect_friend_pay);
        }

        public void setData(final FriendListResult.Data data) {
            txt.setText(data.getNickname());
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(60, 60)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));

            String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v,getPosition());
                }
            });
        }
    }
}
