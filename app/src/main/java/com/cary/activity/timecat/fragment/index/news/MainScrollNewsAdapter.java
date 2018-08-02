package com.cary.activity.timecat.fragment.index.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.view.LimitScrollerView;

import java.util.List;

/**
 * Created by Cary on 2018/4/9.
 */

public class MainScrollNewsAdapter implements LimitScrollerView.LimitScrollAdapter {
    private List<NewsCommitResult.Data> datas;
    private Context mContext;
    public void setDatas(List<NewsCommitResult.Data> datas, Context context,LimitScrollerView limitScroll) {
        this.datas = datas;
        this.mContext = context;
        //API:2、开始滚动
        limitScroll.startScroll();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public View getView(int index) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.limit_scroller_item, null, false);
        TextView tv_text = (TextView) itemView.findViewById(R.id.tv_text);

        //绑定数据
        itemView.setTag(datas.get(index));
        //iv_icon.setImageResource(data.getIcon());
        tv_text.setText(datas.get(index).getTitle());
        return itemView;
    }
}