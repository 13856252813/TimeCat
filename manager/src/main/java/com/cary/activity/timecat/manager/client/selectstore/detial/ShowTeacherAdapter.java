package com.cary.activity.timecat.manager.client.selectstore.detial;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * 时光老师 adapter
 */
public class ShowTeacherAdapter extends RecyclerView.Adapter<ShowTeacherAdapter.ViewHolder> implements View.OnClickListener {
    private List<ShowTeacherComResult.Data> mLists = new ArrayList<>();
    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView showImage;
        TextView showName;
        TextView teacherType;
        TextView teacherCriedit;

        public ViewHolder(View itemView) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.imgeview_item_show_image);
            showName = (TextView) itemView.findViewById(R.id.tv_item_name);
            teacherType = (TextView) itemView.findViewById(R.id.tv_item_teachertype);
            teacherCriedit = itemView.findViewById(R.id.tv_item_credit);
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public ShowTeacherAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<ShowTeacherComResult.Data> mLists) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_store_detial_show_teacher_item, parent, false);
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
        ShowTeacherComResult.Data data = mLists.get(position);
        holder.showName.setText("Name");//data.getName());
        holder.teacherType.setText( data.getTeacherType());
        holder.teacherCriedit.setText("信誉");
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(50, 50)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
        String imageUrl = "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6b6f7d97e9dde711f8d245f697eecef4/71cf3bc79f3df8dc6f0d4203c111728b47102874.jpg";
// HttpUrlClient.YUNPHOTOBASEURL + data.getImgurl();
        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
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