package com.cary.activity.timecat.manager.client.myorder;

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

import java.util.ArrayList;
import java.util.List;

public class ToBeCompletedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<TaskListResult.Data> mList = new ArrayList<>();
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

    public ToBeCompletedAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_my_order_list_item, parent, false);
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

    public TaskListResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<TaskListResult.Data> list) {
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

    public void reSetData(List<TaskListResult.Data> list) {
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
//        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_my_order_list_item, parent, false));
//        return holder;
//    }
//
//    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
//// 在这里对获取对象进行操作
//    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
//    //position是点击位置
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        //设置textView显示内容为list里的对应项
//        holder.tv_myorder_name.setText("时光猫");
//        holder.tv_myorder_price.setText("¥1000");
//        holder.tv_myorder_desc.setText("标题");
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
//        //子项的点击事件监听
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "点击子项" + position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                if (position == 1) {
//                    intent.setClass(mContext, OrderDetialActivity.class);
//                }
//                if (position == 2) {
//                    intent.setClass(mContext, ClothBuyDetialActivity.class);
//                }
//                intent.putExtra("id", position + "");
//                mContext.startActivity(intent);
//            }
//        });
//    }
//
//    //要显示的子项数量
//    @Override
//    public int getItemCount() {
//        if (list != null && list.size() > 0) {
//            return list.size();
//        }
//        return 8;
//    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_myorder_name;
        TextView tv_myorder_desc;
        TextView tv_myorder_stage;
        TextView tv_myorder_stage_time;
        TextView tv_myorder_price;
        ImageView iv_myorder_iamge;
        private OnItemClickListener mClickListener;
        private Context mContext;

        public ViewHolder(View itemView, OnItemClickListener mClickListener, Context mContext) {
            super(itemView);
            this.mClickListener = mClickListener;
            this.mContext = mContext;
            tv_myorder_name = itemView.findViewById(R.id.tv_myorder_name);
            tv_myorder_desc = itemView.findViewById(R.id.tv_myorder_desc);
            tv_myorder_stage = itemView.findViewById(R.id.tv_myorder_stage);
            tv_myorder_stage_time = itemView.findViewById(R.id.tv_myorder_stage_time);
            tv_myorder_price = itemView.findViewById(R.id.tv_myorder_price);
            iv_myorder_iamge = itemView.findViewById(R.id.iv_myorder_iamge);
            itemView.setOnClickListener(this);
        }

        public void setData(TaskListResult.Data data) {
            String type = data.getType();
            if ("gp".equals(type)) {
                tv_myorder_name.setText("跟拍");
            } else if ("sx".equals(type)) {
                tv_myorder_name.setText("摄影");
            } else if ("gz".equals(type)) {
                tv_myorder_name.setText("化妆");
            } else if ("wedding".equals(type)) {
                tv_myorder_name.setText("婚纱摄影");
            } else if ("photo".equals(type)) {
                tv_myorder_name.setText("写真摄影");
            } else if ("baby".equals(type)) {
                tv_myorder_name.setText("宝宝摄影");
            }
            tv_myorder_price.setText("¥"+data.getAmount());
            tv_myorder_desc.setText(data.getContent());
            tv_myorder_stage.setText("阶段");
            tv_myorder_stage_time.setText(data.getCreateTime());
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(960, 480)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1685935631,4222374157&fm=27&gp=0.jpg";
//                HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(iv_myorder_iamge);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v, getPosition());
        }


    }


}
