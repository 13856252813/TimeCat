package com.cary.activity.timecat.manager.client.selectstore.detial;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.message.myfriend.detial.ShowImageCommitResult;

import java.util.ArrayList;
import java.util.List;

/***
 * 客片欣赏
 */
public class LookCustomAdapter extends RecyclerView.Adapter<LookCustomAdapter.ViewHolder> implements View.OnClickListener {
    private List<ShowImageCommitResult.Data> mLists = new ArrayList<>();
    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView showImage;
        TextView showName;

        public ViewHolder(View itemView) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.imgeview_item_show_image);
            showName = (TextView) itemView.findViewById(R.id.imgeview_item_name);
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public LookCustomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<ShowImageCommitResult.Data> mLists) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_teacher_detial_work_appction_item, parent, false);
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
//        ShowImageCommitResult.Data data = mLists.get(position);
        holder.showName.setText("三亚 彭浪屿");//data.getTitle());
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(960, 480)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH);
////                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
//        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
//        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
//        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return mLists.size();
    }
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }
    private OnItemClickListener mItemClickListener;
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}