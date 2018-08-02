package com.cary.activity.timecat.fragment.index.star;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.SelectTeacherWorkAdapter;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;

import java.util.List;

public class StarTeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StarListResult.DataBean.TeachersBean> mDateBeen;
    private Context mContext;
    private SelectTeacherWorkAdapter selectworkAdapter;

    public enum LoadStatus {
        CLICK_LOAD_MORE,//上拉加载更多
        LOADING_MORE,//正在加载更多
        LOADING_GONE
    }

    private LoadStatus mLoadStatus = LoadStatus.CLICK_LOAD_MORE;//上拉加载的状态
    private static final int VIEW_TYPE_FOOTER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivSelectTeacherItemHead;
        TextView tvTimeCredit;
        TextView tvTimeCreditText;
        CheckBox cbProtol;
        TextView tvTeacherNameNick;
        TextView tvTeacherDescription;
        RecyclerView recyclerTeacherWorks;
        TextView tvSetmealChargeText;
        TextView tvSetmealCharge;
        TextView tvSetmealChargeCloth;
        TextView tvSeniorityMoney;
        TextView tvSeniorityMoneyText;
        private OnItemClickListener mListener;

        public ViewHolder(View itemView, OnItemClickListener mListener) {
            super(itemView);
            this.mListener = mListener;
            ivSelectTeacherItemHead = (ImageView) itemView.findViewById(R.id.iv_select_teacher_item_head);
            tvTimeCredit = (TextView) itemView.findViewById(R.id.tv_time_credit);
            tvTimeCreditText = (TextView) itemView.findViewById(R.id.tv_time_credit_text);
            cbProtol = (CheckBox) itemView.findViewById(R.id.cbProtol);
            tvTeacherNameNick = (TextView) itemView.findViewById(R.id.tv_teacher_name_nick);
            tvTeacherDescription = (TextView) itemView.findViewById(R.id.tv_teacher_description);
            recyclerTeacherWorks = (RecyclerView) itemView.findViewById(R.id.recycler_teacher_works);
            tvSetmealChargeText = (TextView) itemView.findViewById(R.id.tv_setmeal_charge_text);
            tvSetmealCharge = (TextView) itemView.findViewById(R.id.tv_setmeal_charge);
            tvSetmealChargeCloth = (TextView) itemView.findViewById(R.id.tv_setmeal_charge_cloth);
            tvSeniorityMoney = (TextView) itemView.findViewById(R.id.tv_seniority_money);
            tvSeniorityMoneyText = (TextView) itemView.findViewById(R.id.tv_seniority_money_text);
            itemView.setOnClickListener(this);
        }

        public void setData(final StarListResult.DataBean.TeachersBean data) {
//        holder.showPrice.setText("¥" + data.getPrice());
            tvTeacherNameNick.setText(data.getWebUser().getNickname() + " | " + data.getWebUser().getPosition());
            RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                    .override(960, 480)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
            String imageUrl = "http://pic.ytqmx.com:82/2013/0410/06/02.jpg!720.jpg";
// HttpUrlClient.ALIYUNPHOTOBASEURL + data.getWebUser().getImgurl();
            Glide.with(mContext).load(imageUrl).apply(options2).into(ivSelectTeacherItemHead);
            tvTeacherDescription.setText(data.getSelfIntroduction());
            tvSetmealCharge.setText("¥" + data.getWebUser().getPhotoAmount() + "/");
            tvSeniorityMoney.setText("¥" + data.getWebUser().getPhotoAmount());
            tvTimeCredit.setText(data.getWebUser().getCredit() + "");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerTeacherWorks.setLayoutManager(linearLayoutManager);
            selectworkAdapter = new SelectTeacherWorkAdapter(mContext);
            //设置item间距，30dp
            recyclerTeacherWorks.addItemDecoration(new SpaceItemDecoration(20));
            recyclerTeacherWorks.setAdapter(selectworkAdapter);


        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getPosition());
        }
    }


    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     * @param mContext
     */
    public StarTeacherAdapter(Context mContext) {
        this.mContext = mContext;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_select_teacher_item, parent, false);
        return new ViewHolder(view, mItemClickListener);
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

    public StarListResult.DataBean.TeachersBean getItem(int position) {
        return mDateBeen.get(position);
    }

    public void addAll(List<StarListResult.DataBean.TeachersBean> list) {
        this.mDateBeen.addAll(list);

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
        if (mDateBeen != null && mDateBeen.size() > 0) {
            return mDateBeen.size() + 1;
        }
        return 0;
    }

    public void reSetData(List<StarListResult.DataBean.TeachersBean> list) {
        this.mDateBeen = list;

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

//
//    public void setDatas(List<ShowImageCommitResult.Data> mLists) {
//        this.mLists = mLists;
//        notifyDataSetChanged();
//    }
//
//    /**
//     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
//     *
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_teacher_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        view.setOnClickListener(this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        viewHolder.recyclerTeacherWorks.setLayoutManager(linearLayoutManager);
//        selectworkAdapter = new SelectTeacherWorkAdapter(mContext);
//        //设置item间距，30dp
//        viewHolder.recyclerTeacherWorks.addItemDecoration(new SpaceItemDecoration(20));
//        viewHolder.recyclerTeacherWorks.setAdapter(selectworkAdapter);
//        return viewHolder;
//    }
//
//    /**
//     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过
//     * position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的Imageview和textview当中即可，
//     *
//     * @param holder
//     * @param position
//     */
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
////        ShowImageCommitResult.Data data = mLists.get(position);
////        holder.showName.setText(data.getTitle());
////        holder.showPrice.setText("¥" + data.getPrice());
//        holder.tvTeacherNameNick.setText("咚咚锵 | 主管");
//        RequestOptions options2 = new RequestOptions()
////                    .centerCrop()
//                .override(960, 480)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH);
////                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
////        String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
////        Log.v("ShowImagedapter", "imageUrl:" + imageUrl);
////        Glide.with(mContext).load(imageUrl).apply(options2).into(holder.showImage);
//        holder.itemView.setTag(position);
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 6;//mLists.size();
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (mItemClickListener != null) {
//            mItemClickListener.onItemClick((Integer) v.getTag());
//        }
//    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onCheckClick(int position);
    }
}