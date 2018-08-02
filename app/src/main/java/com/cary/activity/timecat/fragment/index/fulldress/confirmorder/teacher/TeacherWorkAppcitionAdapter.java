package com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher;


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
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;

import java.util.ArrayList;
import java.util.List;

public class TeacherWorkAppcitionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TeacherWorkResult.Data> mList = new ArrayList<>();
    private Context mContext;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView showImage;
        TextView showName;
        OnItemClickListener mListener;
        Context mContext;

        public ViewHolder(View itemView, OnItemClickListener mListener, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            this.mListener = mListener;
            showImage = (ImageView) itemView.findViewById(R.id.imgeview_item_show_image);
            showName = (TextView) itemView.findViewById(R.id.imgeview_item_name);
            itemView.setOnClickListener(this);
        }

        public void setData(TeacherWorkResult.Data data) {
            showName.setText(data.getAddress());
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(90, 90)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
        String imageUrl =   HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurls();
        Glide.with(mContext).load(imageUrl).apply(options2).into(showImage);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(getPosition());
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public TeacherWorkAppcitionAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_teacher_detial_work_appction_item, parent, false);
        return new ViewHolder(view, mItemClickListener, mContext);
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

    public TeacherWorkResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<TeacherWorkResult.Data> list) {
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

    public void reSetData(List<TeacherWorkResult.Data> list) {
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
//    public void setDatas(List<ShowImageCommitResult.Data> mLists) {
//        this.mLists = mLists;
//        notifyDataSetChanged();
//    }
//
//    /**
//     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
//     *
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_teacher_detial_work_appction_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        view.setOnClickListener(this);
//        return viewHolder;
//    }
//
//    /**
//     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过
//     * position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的Imageview和textview当中即可，
//     *
//     * @param holder
//     * @param position
//     */
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
////        ShowImageCommitResult.Data data = mLists.get(position);
//        holder.showName.setText("三亚  彭浪屿");//data.getTitle());
////        RequestOptions options2 = new RequestOptions()
//////                    .centerCrop()
////                .override(960, 480)
////                .placeholder(R.mipmap.ic_launcher)
////                .error(R.mipmap.ic_launcher)
////                .priority(Priority.HIGH);
//////                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
////        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
////        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
////        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
//        holder.itemView.setTag(position);
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 8;//mLists.size();
//    }
//    @Override
//    public void onClick(View v) {
//        if (mItemClickListener!=null){
//            mItemClickListener.onItemClick((Integer) v.getTag());
//        }
//    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}