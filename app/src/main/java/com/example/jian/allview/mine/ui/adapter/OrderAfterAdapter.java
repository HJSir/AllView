package com.example.jian.allview.mine.ui.adapter;

/**
 * Created by jian on 2017/11/3.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jian.allview.R;
import com.example.jian.allview.mine.bean.Order;
import com.example.jian.allview.mine.bean.orderbean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;


/**
 * Created by jian on 2017/7/13.
 */

public class OrderAfterAdapter extends BaseAdapter {
    private List<Order> mlist;
    private LayoutInflater mInflater;
    String statues;
    Context context;
    public OrderAfterAdapter(Context context, List<Order> list) {
        mlist=list;
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return  mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAfterAdapter.Viewholder viewholder;
        if(convertView==null){
            viewholder = new OrderAfterAdapter.Viewholder();
            convertView = mInflater.inflate(R.layout.order_after_listview,null);
            viewholder.shopname = (TextView) convertView.findViewById(R.id.lv_after_shopname);
            viewholder.content= (TextView) convertView.findViewById(R.id.lv_after_content);
            viewholder.orderstatues= (TextView) convertView.findViewById(R.id.lv_after_statues);
            viewholder.place= (TextView) convertView.findViewById(R.id.lv_after_place);
            viewholder.price= (TextView) convertView.findViewById(R.id.lv_after_price);
            viewholder.shopimage= (SimpleDraweeView) convertView.findViewById(R.id.lv_after_image);
//            viewholder.bt_checkplan = (Button) convertView.findViewById(R.id.lv_do_checkplan);
//            viewholder.bt_surefinish = (Button) convertView.findViewById(R.id.lv_do_surefinish);
            convertView.setTag(viewholder);

        }else{
            viewholder = (OrderAfterAdapter.Viewholder) convertView.getTag();
        }
        Order orderbean = mlist.get(position);
        viewholder.shopname.setText(orderbean.getOrderName());
        viewholder.content.setText("订单号："+orderbean.getOrderNum());
        if(orderbean.getOrderStatus()==0)
            statues="等待接单";
        else if(orderbean.getOrderStatus()==1)
            statues="商家接单";
        else if(orderbean.getOrderStatus()==2)
            statues="订单完成";
        viewholder.orderstatues.setText(statues);
        viewholder.place.setText("张家界");



        viewholder.price.setText(orderbean.getTotal()+"");
        viewholder.shopimage.setImageURI("http://123.207.56.152/vrzjj/"+orderbean.getCoverURL());
//        viewholder.bt_surefinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"确锟斤拷锟疥工",Toast.LENGTH_SHORT).show();
//            }
//        });
//        viewholder.bt_checkplan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(context,"锟斤拷锟斤拷",Toast.LENGTH_SHORT).show();
//
//            }
//        });

        return convertView;
    }

    class Viewholder{
        private TextView shopname;
        private TextView orderstatues;
        private TextView place;
        private TextView content;
        private TextView price;
        private SimpleDraweeView shopimage;
        private Button bt_checkplan;
        private Button bt_surefinish;

    }

}
