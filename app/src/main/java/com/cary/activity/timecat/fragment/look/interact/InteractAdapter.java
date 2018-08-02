package com.cary.activity.timecat.fragment.look.interact;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.fragment.message.myfriend.detial.FriendDetialActivity;
import com.cary.activity.timecat.fragment.message.myfriend.detial.FriendNewsImgGridAdapter;
import com.cary.activity.timecat.http.base.HttpUrlClient;

import java.util.ArrayList;
import java.util.List;

public class InteractAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<InteractListResult.Data> mList = new ArrayList<>();
    private Context mContext;
    String videoideoUrl3 = "http://117.131.17.227:40000/_uploads/videos/huanglesong.mp4";

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivInteractItemHead;
        TextView tvInteractNameNick;
        TextView tvInteractDescription;
        RecyclerView recyclerInteractList, recycler_interact_order_comment;
        LinearLayout ll_interact_order_comment;
        RatingBar ratingbar_interact_order_comment;
        ImageView iv_interact_order_image;
        TextView tv_interact_order_title, tv_interact_order_price,
                tv_interact_order_old_price, tv_interact_order_sell_number;
        TextView tv_interact_time, tv_interact_comment, tv_interact_praise;
        RelativeLayout rl_interact_video;
        VideoView videoView_interact;
        ImageView iv_interact_playvideo;
        ImageView iv_videoView_interact;

        public ViewHolder(View itemView) {
            super(itemView);
            ivInteractItemHead = itemView.findViewById(R.id.iv_interact_item_head);
            tvInteractNameNick = itemView.findViewById(R.id.tv_interact_name_nick);
            tvInteractDescription = itemView.findViewById(R.id.tv_interact_description);
            recyclerInteractList = itemView.findViewById(R.id.recycler_interact_list);
            recycler_interact_order_comment = itemView.findViewById(R.id.recycler_interact_order_comment);
            ll_interact_order_comment = itemView.findViewById(R.id.ll_interact_order_comment);
            ratingbar_interact_order_comment = itemView.findViewById(R.id.ratingbar_interact_order_comment);
            iv_interact_order_image = itemView.findViewById(R.id.iv_interact_order_image);
            tv_interact_order_title = itemView.findViewById(R.id.tv_interact_order_title);
            tv_interact_order_price = itemView.findViewById(R.id.tv_interact_order_price);
            tv_interact_order_old_price = itemView.findViewById(R.id.tv_interact_order_old_price);
            tv_interact_order_sell_number = itemView.findViewById(R.id.tv_interact_order_sell_number);

            tv_interact_time = itemView.findViewById(R.id.tv_interact_time);
            tv_interact_comment = itemView.findViewById(R.id.tv_interact_comment);
            tv_interact_praise = itemView.findViewById(R.id.tv_interact_praise);

            rl_interact_video = itemView.findViewById(R.id.rl_interact_video);
            videoView_interact = itemView.findViewById(R.id.videoView_interact);
            iv_interact_playvideo = itemView.findViewById(R.id.iv_interact_playvideo);
            iv_videoView_interact = itemView.findViewById(R.id.iv_videoView_interact);

            itemView.setOnClickListener(this);

        }

        public void setData(InteractListResult.Data data) {
            tvInteractNameNick.setText(data.getNickname());
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(960, 480)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(ivInteractItemHead);
            tvInteractDescription.setText(data.getContent());
            tv_interact_time.setText(data.getReleaseTime());
            tv_interact_praise.setText(data.getZanCount()+"点赞");
            tv_interact_comment.setText((data.getEvas()!=null ? data.getEvas().size() : 0 )+"评论");
//            List<String> ImageList = new ArrayList<>();
//            int listSize = 0;
            if (data.getInfos() != null && data.getInfos().size() > 0) {//订单
//                listSize = 3;
                ll_interact_order_comment.setVisibility(View.VISIBLE);
                recyclerInteractList.setVisibility(View.GONE);
                rl_interact_video.setVisibility(View.GONE);
                ratingbar_interact_order_comment.setRating(data.getInfos().get(0).getStarCount());
                tv_interact_order_title.setText(data.getInfos().get(0).getTitle());
                tv_interact_order_price.setText("¥" + data.getInfos().get(0).getPrice());
                tv_interact_order_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tv_interact_order_old_price.setText("¥" + data.getInfos().get(0).getMarketPrice());
                tv_interact_order_sell_number.setText("已售" + data.getInfos().get(0).getSellCount() + "次");

            } else if (data.getBarType() == 0) {//视频
//           iv_videoView_interact.setImageBitmap(BaseUtil.createVideoThumbnail(videoUrl, 294, 165));
                ll_interact_order_comment.setVisibility(View.GONE);
                recyclerInteractList.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(data.getVideoUrl())){
                    rl_interact_video.setVisibility(View.VISIBLE);
                }
            } else if (data.getBarType() == 1) {
                //图片
//                listSize = 11;
                ll_interact_order_comment.setVisibility(View.GONE);
                recyclerInteractList.setVisibility(View.VISIBLE);
                rl_interact_video.setVisibility(View.GONE);
            }
//            for (int i = 0; i < listSize; i++) {
//                ImageList.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=bdac238024381f3081198ba999004c67/6159252dd42a2834171827b357b5c9ea14cebfcf.jpg");
//            }

//            loadListDate(false, true, recyclerInteractList, ImageList);
            recyclerInteractList.setNestedScrollingEnabled(false);
            iv_interact_playvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(videoideoUrl3);
                    videoView_interact.setVideoURI(uri);
                    videoView_interact.requestFocus();
                    videoView_interact.start();
                    iv_interact_playvideo.setVisibility(View.GONE);
                    iv_videoView_interact.setVisibility(View.GONE);
                }
            });
            /**
             * 视频或者音频到结尾时触发的方法
             */
            videoView_interact.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i("通知", "完成");
                }
            });
            videoView_interact.setMediaController(new MediaController(mContext) {
                @Override
                public boolean onTouchEvent(MotionEvent event) {
                    videoView_interact.pause();
                    iv_interact_playvideo.setVisibility(View.VISIBLE);
                    return super.onTouchEvent(event);
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(getPosition());
            }
        }
    }

    public InteractAdapter(Context mContext) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_interact_item, parent, false);
        return new ViewHolder(view);
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

    public InteractListResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<InteractListResult.Data> list) {
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

    public void reSetData(List<InteractListResult.Data> list) {
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
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_interact_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        view.setOnClickListener(this);
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
////        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
////        viewHolder.recyclerTeacherWorks.setLayoutManager(linearLayoutManager);
////        selectworkAdapter = new SelectTeacherWorkAdapter(mContext);
////        //设置item间距，30dp
////        viewHolder.recyclerTeacherWorks.addItemDecoration(new SpaceItemDecoration(20));
////        viewHolder.recyclerTeacherWorks.setAdapter(selectworkAdapter);
//
//        return viewHolder;
//    }
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
////        ShowImageCommitResult.Data data = mLists.get(position);
////        holder.showName.setText(data.getTitle());
////        holder.showPrice.setText("¥" + data.getPrice());
//        holder.tvInteractNameNick.setText("咚咚锵老师");
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(960, 480)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH);
////                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
////        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
////        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
////        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
//        List<String> ImageList = new ArrayList<>();
//        int listSize = 0;
//        if (position % 2 == 0) {
//            listSize = 3;
//            holder.ll_interact_order_comment.setVisibility(View.VISIBLE);
//            holder.recyclerInteractList.setVisibility(View.GONE);
//            holder.rl_interact_video.setVisibility(View.GONE);
//        } else if (position % 3 == 0) {
//
////            holder.iv_videoView_interact.setImageBitmap(BaseUtil.createVideoThumbnail(videoUrl, 294, 165));
//            holder.ll_interact_order_comment.setVisibility(View.GONE);
//            holder.recyclerInteractList.setVisibility(View.GONE);
//            holder.rl_interact_video.setVisibility(View.VISIBLE);
//        } else {
//            listSize = 11;
//            holder.ll_interact_order_comment.setVisibility(View.GONE);
//            holder.recyclerInteractList.setVisibility(View.VISIBLE);
//            holder.rl_interact_video.setVisibility(View.GONE);
//        }
//        for (int i = 0; i < listSize; i++) {
//            ImageList.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=bdac238024381f3081198ba999004c67/6159252dd42a2834171827b357b5c9ea14cebfcf.jpg");
//        }
//
//        loadListDate(false, true, holder.recyclerInteractList, ImageList);
//        holder.recyclerInteractList.setNestedScrollingEnabled(false);
//        holder.itemView.setTag(position);
//        holder.iv_interact_playvideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse(videoideoUrl3);
//                holder.videoView_interact.setVideoURI(uri);
//                holder.videoView_interact.requestFocus();
//                holder.videoView_interact.start();
//                holder.iv_interact_playvideo.setVisibility(View.GONE);
//                holder.iv_videoView_interact.setVisibility(View.GONE);
//            }
//        });
///**
// * 视频或者音频到结尾时触发的方法
// */
//        holder.videoView_interact.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Log.i("通知", "完成");
//
//            }
//        });
//        holder.videoView_interact.setMediaController(new MediaController(mContext) {
//            @Override
//            public boolean onTouchEvent(MotionEvent event) {
//                holder.videoView_interact.pause();
//                holder.iv_interact_playvideo.setVisibility(View.VISIBLE);
//                return super.onTouchEvent(event);
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 6;//mLists.size();
//    }


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
        recyclerViewGrid.addItemDecoration(new SpaceItemDecoration(5));

        recyclerViewGridAdapter.setOnItemClickListener(new com.cary.activity.timecat.main.adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(mContext, FriendDetialActivity.class);
                intent.putExtra("id", "123");
                mContext.startActivity(intent);
//                ((Activity)mContext).finish();
            }
        });
    }
}