package com.cary.activity.timecat.fragment.index.basicser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.model.BasicService;

import java.util.List;

/**
 * Created by Cary on 2018/4/7.
 */

public class RecyclerViewBServiceAdapter extends RecyclerView.Adapter<RecyclerViewBServiceAdapter.ListViewHolder> {
    private Context mContext;
    //泛型是RecyclerView所需的Bean类
    private List<BasicService.DataBean> mDateBeen;
    private OnItemClickListener mClickListener;

    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public RecyclerViewBServiceAdapter(Context context,List<BasicService.DataBean> dataBean) {
        this.mContext = context;
        this.mDateBeen = dataBean;
    }
    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public RecyclerViewBServiceAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerViewBServiceAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个ViewHolder对象，决定了item的样式，参数1.上下文 2.XML布局资源 3.null
        View itemView = View.inflate(mContext, R.layout.activity_basic_service_item, null);
        //创建一个ViewHodler对象
        ListViewHolder gridViewHolder = new ListViewHolder(itemView, mClickListener);
        //把ViewHolder传出去
        return gridViewHolder;
    }

    //当ViewHolder和数据绑定是回调
    @Override
    public void onBindViewHolder(RecyclerViewBServiceAdapter.ListViewHolder holder, int position) {
        final int pos = position;
        //从集合里拿对应的item的数据对象
        BasicService.DataBean dateBean = mDateBeen.get(position);
        //给Holder里面的控件对象设置数据
        holder.setData(dateBean);
    }

    public void setDatas(List<BasicService.DataBean> list){
        mDateBeen=list;
    }

    //决定RecyclerView有多少条item
    @Override
    public int getItemCount() {
        //数据不为null，有几条数据就显示几条数据
        if (mDateBeen != null && mDateBeen.size() > 0) {
            return mDateBeen.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    //自动帮我们写的ViewHolder，参数：View布局对象
    public class ListViewHolder extends RecyclerView.ViewHolder  {
        private TextView mTextViewTitle;
        private TextView mTvDesc;

        //        private final RelativeLayout home_grid_item_layout;
        private OnItemClickListener mListener;// 声明自定义的接口

        public ListViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mTextViewTitle = (TextView) itemView.findViewById(R.id.title);
            mTvDesc = (TextView) itemView.findViewById(R.id.dec);
        }


        public void setData(BasicService.DataBean data) {
            //给imageView设置图片数据
            //给TextView设置文本数据
            mTextViewTitle.setText(data.getServiceName());
            mTvDesc.setText("  "+data.getServiceDetail());
        }

    }
}
