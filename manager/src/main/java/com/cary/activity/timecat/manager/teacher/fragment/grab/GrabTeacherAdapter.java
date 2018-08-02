package com.cary.activity.timecat.manager.teacher.fragment.grab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cary on 2018/4/18.
 */

public class GrabTeacherAdapter extends RecyclerView.Adapter<GrabTeacherAdapter.MyViewHolder> {
    //这个是checkbox的Hashmap集合
    private HashMap<TaskDetialResult.Grabs, Boolean> map;
    //这个是数据集合
    private List<TaskDetialResult.Grabs> list;
    private Context mContext;
    private boolean isSelect;

    public GrabTeacherAdapter(Context context) {
        this.mContext = context;

    }

    public void setisSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public void reSetData(List<TaskDetialResult.Grabs> mList) {
        this.list = mList;
        map = new HashMap<>();
        for (int i = 0; i < mList.size(); i++) {
            map.put(mList.get(i), false);
        }
    }

    /**
     * 单选
     */
    public void singlesel(TaskDetialResult.Grabs data) {
        Set<Map.Entry<TaskDetialResult.Grabs, Boolean>> entries = map.entrySet();
        for (Map.Entry<TaskDetialResult.Grabs, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(data, true);
        notifyDataSetChanged();
    }

    //这里主要初始化布局控件
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        //初始化布局文件
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_grab_teacher_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //放入集合中的值
        final TaskDetialResult.Grabs grabs = list.get(position);
        holder.txt.setText(grabs.getNickname());
        holder.txtScore.setText(grabs.getCredit());
        if (isSelect) {//只有选择了的时候 才去隐藏，不然不能隐藏
            if (grabs.getSelected()) {
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setVisibility(View.GONE);
            }
        }
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .override(60, 60)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));

        String headImage = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3685210707,481049533&fm=11&gp=0.jpg";
//                HttpUrlClient.ALIYUNPHOTOBASEURL+grabs.getImgurl();
        Glide.with(mContext).load(headImage).apply(options2).into(holder.image);
        holder.checkBox.setChecked(map.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                map.put(grabs, !map.get(position));
                //刷新适配器
                notifyDataSetChanged();
                //单选
                singlesel(grabs);
                onCheckListener.onCheckListener(grabs.getId());

            }
        });

    }

    private OnCheckListener onCheckListener;

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public interface OnCheckListener {
        void onCheckListener(int grabsId);
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size() + 1;
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        private TextView txt, txtScore;
        private ImageView image;
        private CheckBox checkBox;

        //初始化控件
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = (ImageView) itemView.findViewById(R.id.iv_grab_teacher_img);
            txt = (TextView) itemView.findViewById(R.id.tv_grab_teacher_name);
            txtScore = itemView.findViewById(R.id.tv_grab_teacher_score);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_grab_teacher_isselect);
        }
    }
}
