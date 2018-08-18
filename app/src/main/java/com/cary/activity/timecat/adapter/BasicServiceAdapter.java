package com.cary.activity.timecat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.model.BasicService;

import java.util.List;

public class BasicServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<BasicService.DataBean> mList;


    public BasicServiceAdapter(Context context) {
        mContext = context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.basic_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder= (MyViewHolder) holder;
        myHolder.textView.setText(mList.get(position).getServiceName());

    }

    @Override
    public int getItemCount() {
        return mList == null || mList.size() == 0 ? 0 : mList.size();
    }


    public void setData(List<BasicService.DataBean> list) {
        mList = list;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_basic);
        }


    }
}
