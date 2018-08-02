package com.cary.activity.timecat.manager.teacher.fragment.attention.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.crashpledge.PayPhotoGraphyOrderActivity;
import com.cary.activity.timecat.manager.http.base.HttpUrlClient;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cary on 2018/4/18.
 */

public class SelectFriendPayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //这个是checkbox的Hashmap集合
    private final HashMap<FriendListResult.Data, Boolean> map;
    //这个是数据集合
//    private final ArrayList<String> list;
    private List<FriendListResult.Data> mList;

    private Context mContext;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;


    public SelectFriendPayAdapter(Context context) {
        this.mContext = context;
        map = new HashMap<>();
    }

    /**
     * 全选
     */
    public void All() {
        Set<Map.Entry<FriendListResult.Data, Boolean>> entries = map.entrySet();
        boolean shouldall = false;
        for (Map.Entry<FriendListResult.Data, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            if (!value) {
                shouldall = true;
                break;
            }
        }
        for (Map.Entry<FriendListResult.Data, Boolean> entry : entries) {
            entry.setValue(shouldall);
        }
        notifyDataSetChanged();
    }

    /**
     * 反选
     */
    public void neverall() {
        Set<Map.Entry<FriendListResult.Data, Boolean>> entries = map.entrySet();
        for (Map.Entry<FriendListResult.Data, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_select_friend_pay_item, parent, false);
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
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), false);
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

    public void reSetData(List<FriendListResult.Data> list) {
        this.mList = list;
        //如果没有数据，则全部设置为未选中
        if (map.size() <= 0) {
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i), false);
            }
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


    /**
     * 单选
     *
     * @param data
     */
    public void singlesel(FriendListResult.Data data) {
        Set<Map.Entry<FriendListResult.Data, Boolean>> entries = map.entrySet();
        for (Map.Entry<FriendListResult.Data, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(data, true);
        notifyDataSetChanged();
    }


//
//    //这里主要初始化布局控件
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
//        //初始化布局文件
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_friend_pay_item, parent, false);
//        return new MyViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        //放入集合中的值
//        holder.txt.setText(list.get(position));
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(60,60)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH)
//                .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
//
//        Glide.with(mContext).load(list.get(position)).apply(options2).into(holder.image);
//
//
//        holder.checkBox.setChecked(map.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                map.put(position, !map.get(position));
//                //刷新适配器
//                notifyDataSetChanged();
//                //单选
//                singlesel(position);
//                //将数据传递给前面的界面 并销毁当前自己的界面
//                Intent intent  = new Intent(mContext, PayPhotoGraphyOrderActivity.class);
//                intent.putExtra("id",position);
//                ((Activity)mContext).setResult(1001,intent);
//                ((Activity) mContext).finish();
//
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }

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

            String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL+data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(image);

            if (map != null && map.size() > 0) {
                Log.v("SelectFriendPayAdapter", "313  map.getData:" + map.get(data));
                checkBox.setChecked(map.get(data));
                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        map.put(data, !map.get(data));
                        //刷新适配器
                        notifyDataSetChanged();
                        //单选
                        singlesel(data);
                        //将数据传递给前面的界面 并销毁当前自己的界面
                        Intent intent = new Intent(mContext, PayPhotoGraphyOrderActivity.class);
                        intent.putExtra("id", data.getId());
                        ((Activity) mContext).setResult(1001, intent);
                        ((Activity) mContext).finish();

                    }
                });
            }
        }
    }
}
