package com.cary.activity.timecat.fragment.person.newhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.R;

import java.util.List;

public class NewHelperListViewAdapter extends RecyclerView.Adapter<NewHelperListViewAdapter.ViewHolder> {
    private Context mContext;
    private List<NewHelperResult.Data> list;
    private LayoutInflater inf;

    public NewHelperListViewAdapter(Context mContext, List<NewHelperResult.Data> data) {
        this.mContext = mContext;
        this.list = data;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_new_helper_list_item, parent, false));
        return holder;
    }

    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
// 在这里对获取对象进行操作
    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
    //position是点击位置
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //设置textView显示内容为list里的对应项
        holder.textViewnumber.setText((position+1)+"、");
        holder.textViewtitle.setText(list.get(position).getTitle());
        //子项的点击事件监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击子项" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,NewHelperDetialActivity.class);
                intent.putExtra("id",position+"");
                mContext.startActivity(intent);
            }
        });
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        if(list!=null&&list.size()>0){
            return list.size();
        }
        return list.size();
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewnumber;
        TextView textViewtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewnumber = itemView.findViewById(R.id.tv_new_helper_list_item_number);
            textViewtitle = itemView.findViewById(R.id.tv_new_helper_list_item_title);
        }
    }

    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, NewHelperResult.Data msg) {
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
