package com.cary.activity.timecat.fragment.index.selectstore.detial;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.http.base.HttpUrlClient;

import java.util.ArrayList;
import java.util.List;

/***
 * 最新时光留言
 */
public class StoreDetialCommentAdapter extends RecyclerView.Adapter<StoreDetialCommentAdapter.ViewHolder> implements View.OnClickListener {

    private List<StoreDetialCommentResult.Data> mLists = new ArrayList<>();
    private Context mContext;
    private StoreDetialCommentImageAdapter mImageAdapter;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeacherDetialLeaveMessageUserHead;
        TextView tvTeacherDetialLeaveMessageUserName;
        TextView tvTeacherDetialLeaveMessageUserTime;
        RatingBar teacherDetialLeaveMessageUserTimeRatingbar;
        RecyclerView recyclerTeacherDetialUserImg;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTeacherDetialLeaveMessageUserHead = (ImageView) itemView.findViewById(R.id.iv_teacher_detial_leave_message_user_head);
            tvTeacherDetialLeaveMessageUserName = (TextView) itemView.findViewById(R.id.tv_teacher_detial_leave_message_user_name);
            tvTeacherDetialLeaveMessageUserTime = (TextView) itemView.findViewById(R.id.tv_teacher_detial_leave_message_user_time);
            teacherDetialLeaveMessageUserTimeRatingbar = (RatingBar) itemView.findViewById(R.id.teacher_detial_leave_message_user_time_ratingbar);
            recyclerTeacherDetialUserImg = (RecyclerView) itemView.findViewById(R.id.recycler_teacher_detial_user_img);
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public StoreDetialCommentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<StoreDetialCommentResult.Data> mLists) {
        this.mLists = mLists;
        notifyDataSetChanged();
    }

    /**
     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_store_detial_comment_item, parent, false);
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
        StoreDetialCommentResult.Data data = mLists.get(position);
        holder.tvTeacherDetialLeaveMessageUserName.setText(data.getWebUser().getNickname());//data.getTitle());
        holder.tvTeacherDetialLeaveMessageUserTime.setText(data.getEvaTime());
        holder.teacherDetialLeaveMessageUserTimeRatingbar.setRating(data.getStarCount());
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(40, 40)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.ivTeacherDetialLeaveMessageUserHead);

        String imgList[] = data.getImgurl().split(",");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerTeacherDetialUserImg.setLayoutManager(linearLayoutManager);
        //设置item间距，30dp
        holder.recyclerTeacherDetialUserImg.addItemDecoration(new SpaceItemDecoration(20));
        mImageAdapter = new StoreDetialCommentImageAdapter(mContext,imgList);
        holder.recyclerTeacherDetialUserImg.setAdapter(mImageAdapter);
//        mImageAdapter.setDatas(imgList);

        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return mLists.size();
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