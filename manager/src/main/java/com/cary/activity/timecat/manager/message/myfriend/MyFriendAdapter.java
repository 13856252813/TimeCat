package com.cary.activity.timecat.manager.message.myfriend;

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
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cary on 2018/4/18.
 */

public class MyFriendAdapter extends RecyclerView.Adapter<MyFriendAdapter.MyViewHolder> {
    //这个是checkbox的Hashmap集合
    private final HashMap<Integer, Boolean> map;
    //这个是数据集合
    private final ArrayList<String> list;

    private Context mContext;
    private OnItemClickListener mClickListener;


    public MyFriendAdapter(Context context) {
        this.mContext = context;
        map = new HashMap<>();
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            //添加30条数据
            list.add("这是条目" + i);
            map.put(i, false);
        }

    }

    //这里主要初始化布局控件
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        //初始化布局文件
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_friend_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(inflate, mClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //放入集合中的值
        holder.txt.setText(list.get(position));
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(60,60)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));

        Glide.with(mContext).load(list.get(position)).apply(options2).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View itemView;
        private TextView txt;
        private ImageView image;
        private OnItemClickListener mListener;// 声明自定义的接口

        //初始化控件
        public MyViewHolder(View itemView,OnItemClickListener mListener) {
            super(itemView);
            this.itemView = itemView;
            this.mListener = mListener;
            image = (ImageView) itemView.findViewById(R.id.select_friend_pay_img);
            txt = (TextView) itemView.findViewById(R.id.select_friend_pay_name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
