package com.cary.activity.timecat.manager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.manager.databean.DateBean;

import java.util.List;

/**
 * Created by Cary on 2018/4/7.
 *
 */

public class RecyclerViewGridAdapter extends RecyclerView.Adapter<RecyclerViewGridAdapter.GridViewHolder> {
    private Context mContext;
    //泛型是RecyclerView所需的Bean类
    private List<DateBean> mDateBeen;
    private int mLayoutId;
    private OnItemClickListener mClickListener;

    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public RecyclerViewGridAdapter(Context context, List<DateBean> dateBeen,int layoutId) {
        mContext = context;
        mDateBeen = dateBeen;
        mLayoutId = layoutId;
    }
    @Override
    public RecyclerViewGridAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个ViewHolder对象，决定了item的样式，参数1.上下文 2.XML布局资源 3.null
        View itemView = View.inflate(mContext,mLayoutId, null);
        //创建一个ViewHodler对象
        GridViewHolder gridViewHolder = new GridViewHolder(itemView,mClickListener);
        //把ViewHolder传出去
        return gridViewHolder;
    }
    //当ViewHolder和数据绑定是回调
    @Override
    public void onBindViewHolder(RecyclerViewGridAdapter.GridViewHolder holder, int position) {
        final int pos = position;
        //从集合里拿对应的item的数据对象
        DateBean dateBean = mDateBeen.get(position);
        //给Holder里面的控件对象设置数据
        holder.setData(dateBean);
    }
    //决定RecyclerView有多少条item
    @Override
    public int getItemCount() {
        //数据不为null，有几条数据就显示几条数据
        if (mDateBeen != null && mDateBeen.size() > 0) {
            return mDateBeen.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
    //自动帮我们写的ViewHolder，参数：View布局对象
    public class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private   ImageView mImageView;
        private   TextView mTextView;
//        private final RelativeLayout home_grid_item_layout;
        private OnItemClickListener mListener;// 声明自定义的接口

        public GridViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
//            mImageView = (ImageView) itemView.findViewById(R.id.main_home_item_list_icon);
//            mTextView = (TextView) itemView.findViewById(R.id.main_home_item_list_name);
            itemView.setOnClickListener(this);
        }
        public void setData(DateBean data) {
            //给imageView设置图片数据
            mImageView.setImageResource(data.icon);
            //给TextView设置文本数据
            mTextView.setText(data.name);
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
