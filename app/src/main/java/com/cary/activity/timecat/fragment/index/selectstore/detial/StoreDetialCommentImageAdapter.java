package com.cary.activity.timecat.fragment.index.selectstore.detial;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ImageInfoObj;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ImageWidgetInfoObj;
import com.cary.activity.timecat.fragment.index.selectstore.detial.imageInfo.ShowPictureActivity;
import com.cary.activity.timecat.http.base.HttpUrlClient;

/***
 * 最新时光留言
 */
public class StoreDetialCommentImageAdapter extends RecyclerView.Adapter<StoreDetialCommentImageAdapter.ViewHolder> implements View.OnClickListener {

    private String[] mLists;
    private Context mContext;

    private ImageInfoObj imageInfoObj;
    private ImageWidgetInfoObj imageWidgetInfoObj;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeacherDetialLeaveMessageUserHead;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTeacherDetialLeaveMessageUserHead = (ImageView) itemView.findViewById(R.id.iv_teacher_detial_leave_message_user_img);
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public StoreDetialCommentImageAdapter(Context mContext,String[] mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

//    public void setDatas(String[] mLists) {
//        notifyDataSetChanged();
//    }

    /**
     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_store_detial_comment_img_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    /**
     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过
     * position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的Imageview和textview当中即可，
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(113, 113)
                .placeholder(R.mipmap.image_default)
                .error(R.mipmap.image_default)
                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + mLists[position];
        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.ivTeacherDetialLeaveMessageUserHead);

        imageInfoObj = new ImageInfoObj();
        imageInfoObj.imageUrl = imageUrl;
        imageInfoObj.imageWidth = 1280;
        imageInfoObj.imageHeight = 720;

        imageWidgetInfoObj = new ImageWidgetInfoObj();
        imageWidgetInfoObj.x = holder.ivTeacherDetialLeaveMessageUserHead.getLeft();
        imageWidgetInfoObj.y = holder.ivTeacherDetialLeaveMessageUserHead.getTop();
        imageWidgetInfoObj.width = holder.ivTeacherDetialLeaveMessageUserHead.getLayoutParams().width;
        imageWidgetInfoObj.height = holder.ivTeacherDetialLeaveMessageUserHead.getLayoutParams().height;

        holder.ivTeacherDetialLeaveMessageUserHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowPictureActivity.class);
                intent.putExtra("imageInfoObj", imageInfoObj);
                intent.putExtra("imageWidgetInfoObj", imageWidgetInfoObj);
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return mLists.length;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}