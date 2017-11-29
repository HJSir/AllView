package com.example.jian.allview.find.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jian.allview.R;
import com.example.jian.allview.find.bean.FindArticle;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by jian on 2017/11/24.
 */

public class GridViewAdapter extends ArrayAdapter<FindArticle> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<FindArticle> mGridData = new ArrayList<FindArticle>();


    public GridViewAdapter(Context context, int resource, ArrayList<FindArticle> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.mGridData = objects;
    }

    public void setGridData(ArrayList<FindArticle> mGridData) {
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
            holder.textView = (TextView) convertView.findViewById(R.id.find_text);
            holder.imageView = (SimpleDraweeView) convertView.findViewById(R.id.find_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FindArticle item = mGridData.get(position);
        holder.textView.setText(item.getTitle());
//        holder.imageView.setAspectRatio(0.5F);
//        Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        holder.imageView.setImageURI("http://123.207.56.152/vrzjj/"+item.getCoverURL());
//        System.out.println(""+item.getCoverURL());
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
        SimpleDraweeView imageView;
    }




}
