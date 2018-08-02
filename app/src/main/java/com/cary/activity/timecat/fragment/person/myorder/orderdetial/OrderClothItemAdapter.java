package com.cary.activity.timecat.fragment.person.myorder.orderdetial;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderClothItemAdapter extends RecyclerView.Adapter<OrderClothItemAdapter.ViewHolder> {
    private Context mContext;
    private List<String> list = new ArrayList<>();
    private LayoutInflater inf;

    public OrderClothItemAdapter(Context mContext) {
        this.mContext = mContext;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_order_clothmould_list_item, parent, false));
        return holder;
    }

    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
// 在这里对获取对象进行操作
    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
    //position是点击位置
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //设置textView显示内容为list里的对应项

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recycler.setLayoutManager(linearLayoutManager);
        OrderClothItemImgAdapter myAdapter = new OrderClothItemImgAdapter(mContext);
        //设置item间距，30dp
        holder.recycler.addItemDecoration(new SpaceItemDecoration(20));
        holder.recycler.setAdapter(myAdapter);
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(76, 76)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
//        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
//        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
//        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
        holder.itemView.setTag(position);
        //子项的点击事件监听
        holder.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext,"position:button:"+position);
            }
        });
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 8;
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class ViewHolder extends RecyclerView.ViewHolder {

        Button confirmBtn;
        RecyclerView recycler;

        public ViewHolder(View itemView) {
            super(itemView);
            confirmBtn = itemView.findViewById(R.id.btn_order_clothmould_list_item);
            recycler = itemView.findViewById(R.id.order_clothmould_recycler_img);
        }
    }


    public void addData(List<String> mData) {
        if (list.size() > 0) {
            for (int i = 0; i < mData.size(); i++) {
                list.add(mData.get(i));
            }
        } else {
            this.list = mData;
        }
        notifyDataSetChanged();
    }
    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, String msg) {
        if (position < list.size() && position >= 0) {
            list.add(position, msg);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }

    //去除指定位置的子项
    public boolean removeItem(int position) {
        if (position < list.size() && position >= 0) {
            list.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    //清空显示数据
    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

}
