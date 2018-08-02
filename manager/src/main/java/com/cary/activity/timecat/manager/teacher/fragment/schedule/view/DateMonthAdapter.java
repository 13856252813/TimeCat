package com.cary.activity.timecat.manager.teacher.fragment.schedule.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.teacher.fragment.schedule.ScheduleResult;

import java.util.ArrayList;
import java.util.List;

/**
 * author：Administrator on 2017/4/10 16:11
 * description:文件说明
 * version:版本
 */
public class DateMonthAdapter extends BaseAdapter<DateEntity> {
    private String dateString;
    private List<ScheduleResult.Data> mList;
    public DateMonthAdapter(Context c) {
        super(c);
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void setData(ArrayList<DateEntity> beanList) {
        super.setData(beanList);
    }

    public void setLists(List<ScheduleResult.Data> list){
        this.mList = list;
    }
    private int selectedPosition = -1;// 选中的位置

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DateEntity info = getData().get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.activity_teacher_date_item_month_data, null);
            holder.data = (TextView) convertView.findViewById(R.id.data);
            holder.luna = (TextView) convertView.findViewById(R.id.luna);
            holder.bg = convertView.findViewById(R.id.bg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (TextUtils.isEmpty(info.date)) {
            //填充空白的 日期 星期
            holder.data.setText("");
            holder.luna.setText("");
            holder.bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
        } else {

            holder.luna.setText(info.luna);
            holder.data.setText(info.day);
            if (dateString.equals(info.date)) {
                if (info.isToday) {
                    holder.bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                    holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.login_color_btn));
                    holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.login_color_btn));
                } else {
                    holder.bg.setBackgroundResource(R.drawable.select_bg);
                    holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                    holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                }

            } else {
                if (info.isToday) {
                    holder.bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                    holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.login_color_btn));
                    holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.login_color_btn));
                } else {
                    holder.bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                    holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.color_666666));
                    holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.color_999999));
                }
            }
        }
        if (selectedPosition == position) {
            if (!TextUtils.isEmpty(info.date)) {
                if (info.isToday) {
                    holder.bg.setBackgroundResource(R.drawable.select_green_bg);
                    holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                    holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                } else {
                    holder.bg.setBackgroundResource(R.drawable.select_bg);
                    holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                    holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                }
            }
        } else {
            if (info.isToday) {
                holder.bg.setBackgroundResource(R.drawable.select_green_bg);
                holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
            } else {
                holder.bg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
                holder.data.setTextColor(ContextCompat.getColor(mContext, R.color.color_666666));
                holder.luna.setTextColor(ContextCompat.getColor(mContext, R.color.color_999999));
            }

        }
        return convertView;
    }

    public class ViewHolder {
        TextView data;
        TextView luna ;
        View bg ;
    }
}
