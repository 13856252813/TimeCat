package com.cary.activity.timecat.fragment.look.integral.exchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.hotscenic.HotScenicCommitResult;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by Cary on 2018/4/7.
 */

public class IntegralGralImgAdapter extends RecyclerView.Adapter<IntegralGralImgAdapter.ListViewHolder> {
    private Context mContext;
    //泛型是RecyclerView所需的Bean类
    private List<String> mDateBeen;
    private int mLayoutId;
    private OnItemClickListener mClickListener;

    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public IntegralGralImgAdapter(Context context, List<String> dateBeen, int layoutId) {
        mContext = context;
        mDateBeen = dateBeen;
        mLayoutId = layoutId;
    }

    @Override
    public IntegralGralImgAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个ViewHolder对象，决定了item的样式，参数1.上下文 2.XML布局资源 3.null
        View itemView = View.inflate(mContext, mLayoutId, null);
        //创建一个ViewHodler对象
        ListViewHolder gridViewHolder = new ListViewHolder(itemView, mClickListener);
        //把ViewHolder传出去
        return gridViewHolder;
    }

    //当ViewHolder和数据绑定是回调
    @Override
    public void onBindViewHolder(IntegralGralImgAdapter.ListViewHolder holder, int position) {
        final int pos = position;
        //从集合里拿对应的item的数据对象
//        HotScenicCommitResult.Data dateBean = mDateBeen.get(position);
//        //给Holder里面的控件对象设置数据
//        holder.setData(dateBean);
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
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private OnItemClickListener mListener;// 声明自定义的接口

        public ListViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mImageView = (ImageView) itemView.findViewById(R.id.iv_integral_exchange_list_item);
//            itemView.setOnClickListener(this);
        }

        public void setData(HotScenicCommitResult.Data data) {
            //给imageView设置图片数据
//            mImageView.setImageResource(data.icon);
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(370, 320)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.avatarw)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));

            Glide.with(mContext).load(data.getImgurl()).apply(options2).into(mImageView);
            //给TextView设置文本数据
//            mTextView.setText(data.getTitle());
//            mTextViewDesc.setText(data.getTitle());
//            mTextViewOldGral.setText("¥" + data.getAmount());
//            mTextViewGral.setText(data.getTitle());
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
