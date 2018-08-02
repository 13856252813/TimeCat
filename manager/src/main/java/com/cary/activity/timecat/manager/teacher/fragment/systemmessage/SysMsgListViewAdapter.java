package com.cary.activity.timecat.manager.teacher.fragment.systemmessage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;

import java.util.List;

public class SysMsgListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<SysMsgResult.Data> mList;
    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private OnItemClickListener mClickListener;

    public SysMsgListViewAdapter(Context mContext ) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_system_message_list_item, parent, false);
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

    public SysMsgResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<SysMsgResult.Data> list) {
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

    public void reSetData(List<SysMsgResult.Data> list) {
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
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textViewtitle;
        TextView textViewdesc;
        TextView textViewtime;
        private OnItemClickListener mListener;// 声明自定义的接口
        private Context mContext;

        public ViewHolder(View itemView, OnItemClickListener listener, Context mContext) {
            super(itemView);
            this.mListener = listener;
            this.mContext = mContext;
            textViewtitle = itemView.findViewById(R.id.tv_system_message_list_item_title);
            textViewdesc = itemView.findViewById(R.id.tv_system_message_list_item_desc);
            textViewtime = itemView.findViewById(R.id.tv_system_message_list_item_time);
            itemView.setOnClickListener(this);
        }
        public void setData(SysMsgResult.Data data) {
            textViewtitle.setText(data.getTitle());
            textViewdesc.setText(data.getContent());
            textViewtime.setText(data.getCreateTime());

        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }

//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_system_message_list_item, parent, false));
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
//        holder.textViewtitle.setText(list.get(position));
//        //子项的点击事件监听
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "点击子项" + position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext,SysMsgDetialActivity.class);
//                intent.putExtra("id",position+"");
//                mContext.startActivity(intent);
//            }
//        });
//    }
//
//    //要显示的子项数量
//    @Override
//    public int getItemCount() {
//        if(list!=null&&list.size()>0){
//            return list.size();
//        }
//        return list.size();
//    }
//
//
//
//    /*之下的方法都是为了方便操作，并不是必须的*/
//
//    //在指定位置插入，原位置的向后移动一格
//    public boolean addItem(int position, String msg) {
//        if (position < list.size() && position >= 0) {
//            list.add(position, msg);
//            notifyItemInserted(position);
//            return true;
//        }
//        return false;
//    }
//
//    //去除指定位置的子项
//    public boolean removeItem(int position) {
//        if (position < list.size() && position >= 0) {
//            list.remove(position);
//            notifyItemRemoved(position);
//            return true;
//        }
//        return false;
//    }
//
//    //清空显示数据
//    public void clearAll() {
//        list.clear();
//        notifyDataSetChanged();
//    }

}
