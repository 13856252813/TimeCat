package com.cary.activity.timecat.manager.message.myfriend.detial;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;

import java.util.ArrayList;
import java.util.List;

public class FriendNewsAdapter extends RecyclerView.Adapter<FriendNewsAdapter.ViewHolder> implements View.OnClickListener {


    private List<ShowImageCommitResult.Data> mLists = new ArrayList<>();
    private Context mContext;
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFriendNewsItemHead;
        TextView tvFriendNewsNameNick;
        TextView tvFriendNewsDescription;
        RecyclerView recyclerFriendNewsList;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFriendNewsItemHead = itemView.findViewById(R.id.iv_friend_news_item_head);
            tvFriendNewsNameNick = itemView.findViewById(R.id.tv_friend_news_name_nick);
            tvFriendNewsDescription = itemView.findViewById(R.id.tv_friend_news_description);
            recyclerFriendNewsList = itemView.findViewById(R.id.recycler_friend_news_list);
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public FriendNewsAdapter(Context mContext) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_friend_news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        viewHolder.recyclerTeacherWorks.setLayoutManager(linearLayoutManager);
//        selectworkAdapter = new SelectTeacherWorkAdapter(mContext);
//        //设置item间距，30dp
//        viewHolder.recyclerTeacherWorks.addItemDecoration(new SpaceItemDecoration(20));
//        viewHolder.recyclerTeacherWorks.setAdapter(selectworkAdapter);

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
//        holder.showName.setText(data.getTitle());
//        holder.showPrice.setText("¥" + data.getPrice());
        holder.tvFriendNewsNameNick.setText("咚咚锵老师");
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
        List<String> ImageList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            ImageList.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=bdac238024381f3081198ba999004c67/6159252dd42a2834171827b357b5c9ea14cebfcf.jpg");
        }

        loadListDate(false, true, holder.recyclerFriendNewsList, ImageList);
        holder.recyclerFriendNewsList.setNestedScrollingEnabled(false);
        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return 6;//mLists.size();
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

    //此处是底部的gridview 的列表
    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, List<String> mGridList) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        FriendNewsImgGridAdapter recyclerViewGridAdapter = new FriendNewsImgGridAdapter(mContext, mGridList);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(inversion);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
//        recyclerViewGridAdapter.setOnItemClickListener(new  OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(mContext, FriendDetialActivity.class);
//                intent.putExtra("id", "123");
//                mContext.startActivity(intent);
////                ((Activity)mContext).finish();
//            }
//        });
    }
}