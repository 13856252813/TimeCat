package com.cary.activity.timecat.fragment.look.integral;

import android.content.Context;
import android.graphics.Paint;
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
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by Cary on 2018/4/7.
 */

public class IntegralMallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    //泛型是RecyclerView所需的Bean类
    private List<IntegralListResult.Data> mList;
    private int mLayoutId;
    private OnItemClickListener mClickListener;
    private int pos;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public IntegralMallAdapter(Context context) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_integral_mall_list_item, parent, false);
        return new ListViewHolder(view, mClickListener, mContext);
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

    public IntegralListResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<IntegralListResult.Data> list) {
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

    public void reSetData(List<IntegralListResult.Data> list) {
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

//    @Override
//    public IntegralMallAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
//    public void onBindViewHolder(IntegralMallAdapter.ListViewHolder holder, int position) {
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    //自动帮我们写的ViewHolder，参数：View布局对象
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTextView;
        private TextView mTextViewDesc;
        private TextView mTextViewOldGral;
        private TextView mTextViewGral;
        //        private final RelativeLayout home_grid_item_layout;
        private OnItemClickListener mListener;// 声明自定义的接口
        private Context mContext;

        public ListViewHolder(View itemView, OnItemClickListener listener, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            mListener = listener;
            mImageView = (ImageView) itemView.findViewById(R.id.iv_integral_mall_list_item);
            mTextView = (TextView) itemView.findViewById(R.id.tv_integral_mall_list_item_title);
            mTextViewDesc = (TextView) itemView.findViewById(R.id.tv_integral_mall_list_item_desc);
            mTextViewOldGral = (TextView) itemView.findViewById(R.id.tv_integral_mall_list_item_old_gral);
            mTextViewGral = (TextView) itemView.findViewById(R.id.tv_integral_mall_list_item_gral);
            itemView.setOnClickListener(this);
        }

        public void setData(IntegralListResult.Data data) {
            mTextViewOldGral.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //给imageView设置图片数据
//            mImageView.setImageResource(data.icon);
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(88, 88)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.avatarw)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2595140314,823568551&fm=27&gp=0.jpg";
//                    HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(data.getImgurl()).apply(options2).into(mImageView);
//            给TextView设置文本数据
            mTextView.setText(data.getTitle());
            mTextViewDesc.setText(data.getIntroduction());
            mTextViewOldGral.setText("¥" + data.getMarkerScore());
            mTextViewGral.setText("¥" + data.getScore());
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
