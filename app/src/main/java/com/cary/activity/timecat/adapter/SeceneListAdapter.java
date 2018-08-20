package com.cary.activity.timecat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.model.AttractionBean;

import java.util.List;

public class SeceneListAdapter extends RecyclerView.Adapter<SeceneListAdapter.MyHolder> {

    private Context mContext;
    private List<AttractionBean.DataBean> mList;

    public SeceneListAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void setDatas(List<AttractionBean.DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_home_list_item_layout, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return  mList==null?0:mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewName;
        private TextView mTextMoney;
        private TextView mTextMoneyTwo;

        public MyHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.activity_home_list_item_hot_img);
            mTextViewName = itemView.findViewById(R.id.activity_home_list_item_hot_title);
            mTextMoney = itemView.findViewById(R.id.activity_home_list_item_hot_money);
            mTextMoneyTwo = itemView.findViewById(R.id.activity_home_list_item_hot_money_two);
        }


        public void bindView(int position) {
            final AttractionBean.DataBean data=mList.get(position);
            Log.e("fl","----url:"+data.getImgurl());
            Glide.with(mContext).load(HttpUrlClient.ALIYUNPHOTOBASEURL+data.getImgurl()).into(mImageView);
            mTextViewName.setText(data.getTitle());
            mTextMoney.setText("¥"+data.getAmount());
            mTextMoneyTwo.setText("¥"+data.getMarkerPrice());
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick(data);
                    }

                }
            });
        }
    }


    public interface ItemClickListener{
        void onItemClick(AttractionBean.DataBean data);
    }


    private ItemClickListener mListener;
    public void setOnItemClickListener(ItemClickListener listener){
        mListener=listener;
    }
}
