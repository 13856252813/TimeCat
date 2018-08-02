package com.cary.activity.timecat.fragment.person.receiveadd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;

import java.util.ArrayList;
import java.util.List;

public class ReceivedAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ReceivedAddressListResult.Data> mList = new ArrayList<>();
    private LayoutInflater inf;
    private RecyclerViewOnClickListener onClickListener;
    private boolean isShowBottom;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public ReceivedAddAdapter(Context mContext) {
        this.mContext = mContext;
        inf = LayoutInflater.from(mContext);

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_received_address_list_item, parent, false);
        return new ViewHolder(view, onClickListener, mContext);
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

    public ReceivedAddressListResult.Data getItem(int position) {
        return mList.get(position);
    }

    public void addAll(List<ReceivedAddressListResult.Data> list) {
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

    public void reSetData(List<ReceivedAddressListResult.Data> list) {
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

    /*
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_received_address_list_item, parent, false));
            return holder;
        }
        //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
    // 在这里对获取对象进行操作
        //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
        //position是点击位置
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //设置textView显示内容为list里的对应项
            if (list.get(position).getIsDefault()) {
                holder.tv_received_address_default_text.setVisibility(View.VISIBLE);
            } else {
                holder.tv_received_address_default_text.setVisibility(View.GONE);
            }
            holder.tv_received_address_name.setText(list.get(position).getName());
            holder.tv_received_address_phone.setText(list.get(position).getMobile());
            String address = list.get(position).getProvince() + list.get(position).getCity() +
                    list.get(position).getArea() + list.get(position).getDetail();
            holder.tv_received_address_address.setText(address);
            if (isShowBottom()) {
                holder.ll_received_address_bottom.setVisibility(View.VISIBLE);
            } else {
                holder.ll_received_address_bottom.setVisibility(View.GONE);
            }
            holder.checkbox_default_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (onClickListener != null) {
                            onClickListener.onItemDefaultListener(holder, position);
                        }
                    }
                }
            });
            holder.tv_received_address_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClickDeleteListener(holder, position);
                    }
                }
            });
            holder.tv_received_address_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClickEditListener(holder, position);
                    }
                }
            });
            //子项的点击事件监听
    //        holder.itemView.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View view) {
    //                Toast.makeText(mContext, "点击子项" + position, Toast.LENGTH_SHORT).show();
    //                Intent intent = new Intent(mContext, NewHelperDetialActivity.class);
    //                intent.putExtra("id", position + "");
    //                mContext.startActivity(intent);
    //            }
    //        });
        }

        //要显示的子项数量
        @Override
        public int getItemCount() {
            if (list != null && list.size() > 0) {
                return list.size();
            }
            return 0;
        }
    */
    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_received_address_name;
        TextView tv_received_address_phone;
        TextView tv_received_address_address;
        LinearLayout ll_received_address_bottom;
        CheckBox checkbox_default_address;
        TextView tv_received_address_delete;
        TextView tv_received_address_edit;
        TextView tv_received_address_default_text;
        RelativeLayout rl_received_address_content;
        private RecyclerViewOnClickListener onClickListener;
        private Context mContext;

        public ViewHolder(View itemView, RecyclerViewOnClickListener onClickListener, Context mContext) {
            super(itemView);
            this.onClickListener = onClickListener;
            this.mContext = mContext;
            tv_received_address_name = itemView.findViewById(R.id.tv_received_address_name);
            tv_received_address_phone = itemView.findViewById(R.id.tv_received_address_phone);
            tv_received_address_address = itemView.findViewById(R.id.tv_received_address_address);
            ll_received_address_bottom = itemView.findViewById(R.id.ll_received_address_bottom);
            checkbox_default_address = itemView.findViewById(R.id.checkbox_default_address);
            tv_received_address_delete = itemView.findViewById(R.id.tv_received_address_delete);
            tv_received_address_edit = itemView.findViewById(R.id.tv_received_address_edit);
            tv_received_address_default_text = itemView.findViewById(R.id.tv_received_address_default_text);
            rl_received_address_content = itemView.findViewById(R.id.rl_received_address_content);
        }

        public void setData(final ReceivedAddressListResult.Data data) {
            if (data.getIsDefault()) {
                tv_received_address_default_text.setVisibility(View.VISIBLE);
            } else {
                tv_received_address_default_text.setVisibility(View.GONE);
            }
            tv_received_address_name.setText(data.getName());
            tv_received_address_phone.setText(data.getMobile());
            String address = data.getProvince() + data.getCity() +
                    data.getArea() + data.getDetail();
            tv_received_address_address.setText(address);
            if (isShowBottom()) {
                ll_received_address_bottom.setVisibility(View.VISIBLE);
            } else {
                ll_received_address_bottom.setVisibility(View.GONE);
            }
            checkbox_default_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (onClickListener != null) {
                            onClickListener.onItemDefaultListener(data.getId());
                        }
                    }
                }
            });
            tv_received_address_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClickDeleteListener(data.getId());
                    }
                }
            });
            tv_received_address_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClickEditListener(data.getId());
                    }
                }
            });
            //子项的点击事件监听
            rl_received_address_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClickSetAddress(data.getId());
                }
            });
        }
    }

    //设置点击事件
    public void setRecyclerViewOnItemLongListener(RecyclerViewOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //接口回调设置点击事件
    public interface RecyclerViewOnClickListener {
        void onItemDefaultListener(int id);

        void onClickEditListener(int id);

        void onClickDeleteListener(int id);

        void onClickSetAddress(int id);

    }

    public boolean isShowBottom() {
        return isShowBottom;
    }

    public void setShowBottom(boolean showBottom) {
        isShowBottom = showBottom;
    }


    /*
    public void addData(List<ReceivedAddressListResult.Data> mData) {
        if (list.size() > 0) {
            for (int i = 0; i < mData.size(); i++) {
                list.add(mData.get(i));
            }
        } else {
            this.list = mData;
        }
        notifyDataSetChanged();
    }
    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, ReceivedAddressListResult.Data msg) {
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
*/
}
