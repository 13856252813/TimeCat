package com.cary.activity.timecat.fragment.person.attention.meal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AttentionMealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MealAttentionResult.Data> mList = new ArrayList<>();
    private OnItemClickListener mClickListener;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public AttentionMealAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_attention_meal_list_item, parent, false);
        return new ViewHolder(view, mClickListener);
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

    public MealAttentionResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<MealAttentionResult.Data> list) {
        if (list.size() > 0) {
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
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

    public void reSetData(List<MealAttentionResult.Data> list) {
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
//        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_attention_meal_list_item, parent, false));
//        return holder;
//    }
//
//    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
//// 在这里对获取对象进行操作
//    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
//    //position是点击位置
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        //设置textView显示内容为list里的对应项
//        holder.tv_attention_meal_item_name.setText("时光猫");
//        holder.tv_attention_meal_item_price.setText("¥1000-60000");
//        holder.tv_attention_meal_item_sellnum.setText("已售number次");
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(960, 480)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH);
////                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
////        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
////        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
////        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.iv_attention_meal_item_image);
//        holder.itemView.setTag(position);
//        //子项的点击事件监听
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "点击子项" + position, Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(mContext, NewHelperDetialActivity.class);
////                intent.putExtra("id", position + "");
////                mContext.startActivity(intent);
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

        TextView tv_attention_meal_item_sellnum;
        TextView tv_attention_meal_item_price;
        TextView tv_attention_meal_item_name;
        ImageView iv_attention_meal_item_image;
        private OnItemClickListener mListener;

        public ViewHolder(View itemView, OnItemClickListener mListener) {
            super(itemView);
            this.mListener = mListener;
            tv_attention_meal_item_sellnum = itemView.findViewById(R.id.tv_attention_meal_item_sellnum);
            tv_attention_meal_item_price = itemView.findViewById(R.id.tv_attention_meal_item_price);
            tv_attention_meal_item_name = itemView.findViewById(R.id.tv_attention_meal_item_name);
            iv_attention_meal_item_image = itemView.findViewById(R.id.iv_attention_meal_item_image);
            itemView.setOnClickListener(this);
        }

        public void setData(MealAttentionResult.Data data) {

            tv_attention_meal_item_sellnum.setText(  "已售"+data.getPhotoPackage().getSellCount()+"次");
            tv_attention_meal_item_price.setText("¥"+data.getPhotoPackage().getPrice());
            tv_attention_meal_item_name.setText(data.getPhotoPackage().getTitle());
            Glide.with(mContext).load(HttpUrlClient.ALIYUNPHOTOBASEURL + data.getPhotoPackage().getImgurl()).into(iv_attention_meal_item_image);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v, getPosition());
        }
    }


}
