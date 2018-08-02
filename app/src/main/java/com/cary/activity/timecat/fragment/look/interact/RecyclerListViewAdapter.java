package com.cary.activity.timecat.fragment.look.interact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.cary.activity.timecat.util.view.GlideCircleTransform;

import java.util.List;

public class RecyclerListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<InteractDetialResult.Evas> mList;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public RecyclerListViewAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_interact_detial_list_item, parent, false);
        return new ViewHolder(view, mItemListener,mContext);
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
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(getItem(position));
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public InteractDetialResult.Evas getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<InteractDetialResult.Evas> list) {
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

    public void reSetData(List<InteractDetialResult.Evas> list) {
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
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_interact_detial_list_item, parent, false));
//        return holder;
//    }
//
//    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
//// 在这里对获取对象进行操作
//    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
//    //position是点击位置
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        InteractDetialResult.Evas  evas = list.get(position);
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(30, 30)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH)
//                .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
//        String imageUrl = "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a818a24c9525bc31345d07986edd8de7/8694a4c27d1ed21b8b195967a16eddc450da3f5b.jpg";
//// HttpUrlClient.ALIYUNPHOTOBASEURL + data.get(i).getImgurl();
//        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.imageView);
//        //设置textView显示内容为list里的对应项
//        holder.textViewComment.setText(evas.getContent());
//        holder.textView.setText(evas.getEvaNickname());
//        if(!TextUtils.isEmpty(evas.getEvaNickname())){
//            holder.llReply.setVisibility(View.VISIBLE);
//            holder.textViewReply.setText(evas.getEvaNickname());
//        }
//        //子项的点击事件监听
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "点击子项" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    //要显示的子项数量
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
    private OnItemClickListener mItemListener;

    public void setItemListener(OnItemClickListener mItemListener) {
        this.mItemListener = mItemListener;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        TextView textViewComment;
        ImageView imageView;
        LinearLayout llReply;
        TextView textViewReply;
        OnItemClickListener mItemListener;
        Context mContext;

        public ViewHolder(View itemView, OnItemClickListener itemClickListener,Context context) {
            super(itemView);
            this.mContext = context;
            this.mItemListener = itemClickListener;
            textView = itemView.findViewById(R.id.tv_interact_comment_list_name);
            imageView = itemView.findViewById(R.id.iv_interact_comment_list_item);
            llReply = itemView.findViewById(R.id.ll_interact_comment_reply);
            textViewComment = itemView.findViewById(R.id.tv_interact_comment_list_context);
            textViewReply = itemView.findViewById(R.id.tv_interact_comment_reply_name);
            textViewReply.setOnClickListener(this);
        }

        public void setData(InteractDetialResult.Evas evas) {
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(30, 30)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL + evas.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(imageView);
            //设置textView显示内容为list里的对应项
            textViewComment.setText(evas.getContent());
            textView.setText(evas.getEvaNickname());
            if (!TextUtils.isEmpty(evas.getEvaNickname())) {
                llReply.setVisibility(View.VISIBLE);
                textViewReply.setText(evas.getEvaNickname());
            }

        }

        @Override
        public void onClick(View v) {
            if (v == textViewReply) {
                mItemListener.onItemClick(getPosition());
            }
        }
    }


}
