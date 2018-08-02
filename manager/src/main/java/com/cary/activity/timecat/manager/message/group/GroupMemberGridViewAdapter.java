package com.cary.activity.timecat.manager.message.group;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import java.util.ArrayList;

public class GroupMemberGridViewAdapter extends ArrayAdapter<GroupMemberItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GroupMemberItem> mGridData = new ArrayList<GroupMemberItem>();


    public GroupMemberGridViewAdapter(Context context, int resource, ArrayList<GroupMemberItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.mGridData = objects;
    }

    public void setGridData(ArrayList<GroupMemberItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_group_member_name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_group_member_head);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GroupMemberItem item = mGridData.get(position);
        holder.textView.setText(item.getTitle());
        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));

        Glide.with(mContext).load(item.getImage()).into(holder.imageView);

        return convertView;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
