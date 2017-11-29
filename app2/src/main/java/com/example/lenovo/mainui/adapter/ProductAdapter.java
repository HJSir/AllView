package com.example.lenovo.mainui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.activity.BuyActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<ProductItem> msecondList;
    static class MyViewHolder extends RecyclerView.ViewHolder {
        View hotelView;
        SimpleDraweeView viewimage;
        TextView viewtext_name;
        TextView viewtext_price;
        public MyViewHolder(View view) {
            super(view);
            hotelView=view;
            viewimage=(SimpleDraweeView) view.findViewById(R.id.product_image);
            viewtext_name=(TextView) view.findViewById(R.id.product_name);
            viewtext_price=(TextView) view.findViewById(R.id.product_price);
        }
    }

    public ProductAdapter(Context context, List<ProductItem> secondList) {
        mContext = context;
        msecondList= secondList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = View.inflate(mContext, R.layout.activity_productitem, null);
            final MyViewHolder holder = new MyViewHolder(view);
            holder.hotelView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    int position=holder.getAdapterPosition();
                    ProductItem product=msecondList.get(position);
                    Intent intent=new Intent(mContext,BuyActivity.class);
                    intent.putExtra("bedid",product.getHotelId()+""+product.getRoomId());
                    mContext.startActivity(intent);
                }
            });
            return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          MyViewHolder myholder= (MyViewHolder) holder;
            ProductItem imagesecond=msecondList.get(position);
            myholder.viewimage.setImageURI(Uri.parse(imagesecond.getImageName()));
            myholder.viewtext_name.setText(imagesecond.getName());
            myholder.viewtext_price.setText(imagesecond.getPrice()+"");
    }


    @Override
    public int getItemCount() {
        return  msecondList.size();
    }

}
