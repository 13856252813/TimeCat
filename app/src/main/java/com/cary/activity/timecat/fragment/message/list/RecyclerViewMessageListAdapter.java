package com.cary.activity.timecat.fragment.message.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Cary on 2018/4/7.
 */

public class RecyclerViewMessageListAdapter extends RecyclerView.Adapter<RecyclerViewMessageListAdapter.ListViewHolder> {
    private Context mContext;
    //泛型是RecyclerView所需的Bean类
    private List<MessageListCommitResult.Data> mDateBeen;
    private int mLayoutId;
    private OnItemClickListener mClickListener;

    //构造方法，一般需要接收两个参数 1.上下文 2.集合对象（包含了我们所需要的数据）
    public RecyclerViewMessageListAdapter(Context context, List<MessageListCommitResult.Data> dateBeen, int layoutId) {
        mContext = context;
        mDateBeen = dateBeen;
        mLayoutId = layoutId;
    }

    @Override
    public RecyclerViewMessageListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个ViewHolder对象，决定了item的样式，参数1.上下文 2.XML布局资源 3.null
        View itemView = View.inflate(mContext, mLayoutId, null);
        //创建一个ViewHodler对象
        ListViewHolder gridViewHolder = new ListViewHolder(itemView, mClickListener);
        //把ViewHolder传出去
        return gridViewHolder;
    }

    //当ViewHolder和数据绑定是回调
    @Override
    public void onBindViewHolder(RecyclerViewMessageListAdapter.ListViewHolder holder, int position) {
        if(mDateBeen!=null && mDateBeen.size()>0) {
            //从集合里拿对应的item的数据对象
            MessageListCommitResult.Data dateBean = mDateBeen.get(position);
            //给Holder里面的控件对象设置数据
            holder.setData(dateBean);
        }
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
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_message_list_item_head;
        private TextView tv_message_list_item_noread;//未读标识
        private ImageView iv_message_list_item_group_head;//是否是群组聊天消息
        private TextView tv_message_list_item_group_name;//群聊名称
        private TextView tv_message_list_item_group_message;//最后一条消息
        private TextView tv_message_list_item_group_message_time;//消息时间


        //        private final RelativeLayout home_grid_item_layout;
        private OnItemClickListener mListener;// 声明自定义的接口

        public ListViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            iv_message_list_item_head = (ImageView) itemView.findViewById(R.id.iv_message_list_item_head);
            iv_message_list_item_group_head = (ImageView) itemView.findViewById(R.id.iv_message_list_item_group_head);
            tv_message_list_item_noread = (TextView) itemView.findViewById(R.id.tv_message_list_item_noread);
            tv_message_list_item_group_name = (TextView) itemView.findViewById(R.id.tv_message_list_item_group_name);
            tv_message_list_item_group_message = (TextView) itemView.findViewById(R.id.tv_message_list_item_group_message);
            tv_message_list_item_group_message_time = (TextView) itemView.findViewById(R.id.tv_message_list_item_group_message_time);

            itemView.setOnClickListener(this);
        }

        public void setData(MessageListCommitResult.Data data) {
            //给imageView设置图片数据
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(40, 60)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.avatarw)
                    .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL+data.getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(iv_message_list_item_head);
            //给TextView设置文本数据
            tv_message_list_item_group_name.setText(data.getName());
            
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }
}
