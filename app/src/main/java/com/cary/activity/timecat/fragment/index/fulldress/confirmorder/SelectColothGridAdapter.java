package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SelectColothGridAdapter extends RecyclerView.Adapter<SelectColothGridAdapter.FullDessGridViewHodler> {
    private List<String> mList=new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mClickListener;
    public SelectColothGridAdapter(Context mContext, List<String> mList ) {
        this.mContext = mContext;
        this.mList = mList;
    }
    public void setListData(List<String> mLists){
        this.mList = mLists;
        notifyDataSetChanged();
    }
    @Override
    public FullDessGridViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.activity_select_coloth_item, parent, false);
        FullDessGridViewHodler viewHolder = new FullDessGridViewHodler(childView, mClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FullDessGridViewHodler holder, final int position) {
        holder.showName.setText(mList.get(position));
//        holder.showPrice.setText("¥"+mList.get(position));
        RequestOptions options2 = new RequestOptions()
                .override(960, 480)
                .placeholder(R.mipmap.image_default)
                .error(R.mipmap.image_default)
                .priority(Priority.HIGH);
        holder.itemView.setTag(position);

    }
    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size();
        }
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    class FullDessGridViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView showImage;
        TextView showName;
        CheckBox showPrice;
        private OnItemClickListener mListener;// 声明自定义的接口

        public FullDessGridViewHodler(View itemView,OnItemClickListener mListener) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.imgeview_item_show_image);
            showName = (TextView) itemView.findViewById(R.id.imgeview_item_name);
            showPrice = (CheckBox) itemView.findViewById(R.id.cbProtol);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mListener.onItemClick(v, getPosition());
        }
    }
}
