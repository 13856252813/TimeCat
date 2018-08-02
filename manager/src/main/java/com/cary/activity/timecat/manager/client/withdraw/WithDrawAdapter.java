package com.cary.activity.timecat.manager.client.withdraw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;

import java.util.List;

public class WithDrawAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<WithDrawResult.Data> mList;
    private LayoutInflater inf;
    private OnItemClickListener mClickListener;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public WithDrawAdapter(Context mContext) {
        this.mContext = mContext;
        inf = LayoutInflater.from(mContext);

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_with_draw_item, parent, false);
        return new ViewHolder(view, mClickListener, mContext);
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

    public WithDrawResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<WithDrawResult.Data> list) {
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

    public void reSetData(List<WithDrawResult.Data> list) {
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_with_draw_head;
        TextView tv_with_draw_name;
        TextView tv_with_draw_name_position;
        TextView tv_with_draw_name_create;
        TextView tv_with_draw_name_money;
        Button btn_with_draw_agree;
        private OnItemClickListener mClickListener;
        private Context mContext;

        public ViewHolder(View itemView, OnItemClickListener mClickListener, Context mContext) {
            super(itemView);
            this.mClickListener = mClickListener;
            this.mContext = mContext;
            tv_with_draw_name = itemView.findViewById(R.id.tv_with_draw_name);
            iv_with_draw_head = itemView.findViewById(R.id.iv_with_draw_head);
            tv_with_draw_name_position = itemView.findViewById(R.id.tv_with_draw_name_position);
            tv_with_draw_name_create = itemView.findViewById(R.id.tv_with_draw_name_create);
            tv_with_draw_name_money = itemView.findViewById(R.id.tv_with_draw_name_money);
            btn_with_draw_agree = itemView.findViewById(R.id.btn_with_draw_agree);
            itemView.setOnClickListener(this);
        }

        public void setData(WithDrawResult.Data data) {
            tv_with_draw_name.setText(data.getNickname());
//            String postionone = data.get
//            if(TextUtils.isEmpty())
            tv_with_draw_name_create.setText(data.getCreateTime());
            tv_with_draw_name_money.setText(data.getAmount() + "");
            if (data.getStatus() == 1) {
                btn_with_draw_agree.setText("已通过");
                btn_with_draw_agree.setClickable(false);
            } else if (data.getStatus() == 0) {
                btn_with_draw_agree.setText("申请中");
                btn_with_draw_agree.setClickable(true);
            } else if (data.getStatus() == 2) {
                btn_with_draw_agree.setText("已拒绝");
                btn_with_draw_agree.setClickable(false);
            }
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(960, 480)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1685935631,4222374157&fm=27&gp=0.jpg";
//                HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(iv_with_draw_head);
        }

        @Override
        public void onClick(View v) {
            if (v == btn_with_draw_agree) {
                mClickListener.onItemClick(getPosition());
            } else {
                mClickListener.onItemClick(v, getPosition());
            }
        }


    }


}
