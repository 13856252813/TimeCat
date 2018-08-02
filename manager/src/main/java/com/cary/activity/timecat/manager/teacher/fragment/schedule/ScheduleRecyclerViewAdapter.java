package com.cary.activity.timecat.manager.teacher.fragment.schedule;

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
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.http.base.HttpUrlClient;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Cary on 2018/4/7.
 */

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
     private List<ScheduleResult.Data> mDateBeen;


    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public ScheduleRecyclerViewAdapter(Context context ) {
        mContext = context;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_content_item_schedule, parent, false);
        return new ListViewHolder(view, mItemClickListener);
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
        ListViewHolder viewHolder = (ListViewHolder) holder;
        viewHolder.setData(getItem(position));
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public ScheduleResult.Data getItem(int position) {
        return mDateBeen.get(position);
    }

    public void addAll(List<ScheduleResult.Data> list) {
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

    public void reSetData(List<ScheduleResult.Data> list) {
        this.mDateBeen = list;
        //如果没有数据，则全部设置为未选中
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

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
//
//    @Override
//    public ScheduleRecyclerViewAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //转换一个ViewHolder对象，决定了item的样式，参数1.上下文 2.XML布局资源 3.null
//        View itemView = View.inflate(mContext, mLayoutId, null);
//        //创建一个ViewHodler对象
//        ListViewHolder gridViewHolder = new ListViewHolder(itemView, mClickListener);
//        //把ViewHolder传出去
//        return gridViewHolder;
//    }
//
//    //当ViewHolder和数据绑定是回调
//    @Override
//    public void onBindViewHolder(ScheduleRecyclerViewAdapter.ListViewHolder holder, int position) {
//        final int pos = position;
//        //从集合里拿对应的item的数据对象
////        HotScenicCommitResult.Data dateBean = mDateBeen.get(position);
////        //给Holder里面的控件对象设置数据
////        holder.setData(dateBean);
//    }
//
//    //决定RecyclerView有多少条item
//    @Override
//    public int getItemCount() {
//        //数据不为null，有几条数据就显示几条数据
//        if (mDateBeen != null && mDateBeen.size() > 0) {
//            return mDateBeen.size();
//        }
//        return 0;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mClickListener = listener;
//    }

    //自动帮我们写的ViewHolder，参数：View布局对象
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_schedule_typeimg;
        private TextView tv_schedule_title;
        private TextView tv_schedule_money;
        private TextView tv_schedule_content;
        private TextView tv_schedule_state;
        private TextView tv_schedule_time;
        private ImageView iv_schedule_headimg;
        private TextView tv_schedule_name;
        //        private final RelativeLayout home_grid_item_layout;
        private OnItemClickListener mListener;// 声明自定义的接口

        public ListViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            iv_schedule_typeimg = (ImageView) itemView.findViewById(R.id.iv_schedule_typeimg);
            tv_schedule_title = itemView.findViewById(R.id.tv_schedule_title);
            tv_schedule_money = itemView.findViewById(R.id.tv_schedule_money);
            tv_schedule_content = itemView.findViewById(R.id.tv_schedule_content);
            tv_schedule_state = itemView.findViewById(R.id.tv_schedule_state);
            tv_schedule_time = itemView.findViewById(R.id.tv_schedule_time);
            iv_schedule_headimg = (ImageView) itemView.findViewById(R.id.iv_schedule_headimg);
            tv_schedule_name = itemView.findViewById(R.id.tv_schedule_name);
            itemView.setOnClickListener(this);
            iv_schedule_headimg.setOnClickListener(this);
        }

        public void setData(ScheduleResult.Data data) {
            //给imageView设置图片数据
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(40, 40)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.avatarw)
                    .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrlHead =  HttpUrlClient.ALIYUNPHOTOBASEURL + data.getTeacherId();
            Glide.with(mContext).load(imageUrlHead).apply(options2).into(iv_schedule_headimg);
            RequestOptions options = new RequestOptions()
                    .override(40, 40)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.avatarw)
                    .priority(Priority.HIGH);
            String imageUrl =    HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options).into(iv_schedule_typeimg);
            //给TextView设置文本数据
            String title = "";
            if(!TextUtils.isEmpty(data.getType())&&"gp".equals(data.getItemType())){
                title = "跟拍";
            }else if(!TextUtils.isEmpty(data.getType())&&"sy".equals(data.getItemType())){
                title = "摄影";
            }else if(!TextUtils.isEmpty(data.getType())&&"gz".equals(data.getItemType())){
                title = "化妆";
            }else if(!TextUtils.isEmpty(data.getType())&&"wedding".equals(data.getItemType())){
                title = "婚纱摄影";
            }else if(!TextUtils.isEmpty(data.getType())&&"photo".equals(data.getItemType())){
                title = "写真摄影";
            }else if(!TextUtils.isEmpty(data.getType())&&"baby".equals(data.getItemType())){
                title = "宝宝摄影";
            }
            tv_schedule_title.setText(title);
            tv_schedule_money.setText("¥"+data.getAmount());
            tv_schedule_content.setText(data.getTaskId()+"任务ID");

            String status = "";
            if(data.getSuccess()){
                status = "已完成";
            }else  {
                status = "待完成";
            }
            tv_schedule_state.setText(status);
            tv_schedule_time.setText(data.getCreateTime());
            tv_schedule_name.setText(data.getNickname());

        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            if (v == iv_schedule_headimg) {
                mListener.onItemClick(getPosition());
            } else {
                mListener.onItemClick(getPosition());
            }
        }
    }
}
