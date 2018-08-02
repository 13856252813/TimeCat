package com.cary.activity.timecat.fragment.message.myfriend.detial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FriendNewsImgGridAdapter extends RecyclerView.Adapter<FriendNewsImgGridAdapter.ImageGridViewHodler> {
    private List<String> mList=new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mClickListener;
    public FriendNewsImgGridAdapter(Context mContext, List<String> mList ) {
        this.mContext = mContext;
        this.mList = mList;
    }
    public void setListData(List<String> mLists){
        this.mList = mLists;
        notifyDataSetChanged();
    }
    @Override
    public ImageGridViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.activity_friend_news_img_item, parent, false);
        ImageGridViewHodler viewHolder = new ImageGridViewHodler(childView, mClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ImageGridViewHodler holder, final int position) {
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(960, 480)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
//        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
//        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
//        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
        holder.itemView.setTag(position);

    }
    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0 && mList.size()<9) {
            return mList.size();
        }
        return 9;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    class ImageGridViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView showImage;
        private OnItemClickListener mListener;// 声明自定义的接口

        public ImageGridViewHodler(View itemView,OnItemClickListener mListener) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.iv_imageview_item);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
