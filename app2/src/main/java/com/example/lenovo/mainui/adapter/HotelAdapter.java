package com.example.lenovo.mainui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.activity.DetailspageActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class HotelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mList;
    private List<HotelItem> msecondList;
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局
    static final int TYPE_FOOTER = 1;
    //    //上拉加载更多
//    static final int PULL_LOAD_MORE = 0;
    //正在加载更多
    static final int LOADING_MORE = 1;
    //没有更多
    static final int NO_MORE = 2;
    //脚布局当前的状态,默认为没有更多
    int footer_state = 1;
    static class MyViewHolder extends RecyclerView.ViewHolder {
        View hotelView;
        TextView mTextView;
        SimpleDraweeView viewimage;
        TextView viewtext_name;
        TextView viewtext_scores;
        TextView viewtext_label;
        TextView viewtext_price;
        TextView viewtext_adress;
        TextView viewtext_number;
        TextView viewtext_price2;
        TextView viewtext_price3;


        public MyViewHolder(View view) {
            super(view);
            hotelView=view;
            viewimage=(SimpleDraweeView) view.findViewById(R.id.hotel_image);
            viewtext_name=(TextView) view.findViewById(R.id.hotel_name);
            viewtext_scores=(TextView) view.findViewById(R.id.hotel_scores);
            viewtext_label=(TextView) view.findViewById(R.id.hotel_label);
            viewtext_price=(TextView) view.findViewById(R.id.hotel_price);
            viewtext_adress=(TextView) view.findViewById(R.id.hotel_adress);
            viewtext_number=(TextView) view.findViewById(R.id.hotel_number);
            viewtext_price2=(TextView) view.findViewById(R.id.hotel_price2);
            viewtext_price3=(TextView) view.findViewById(R.id.hotel_price3);
            if(viewimage==null){
                Log.e("error","3");
            }
        }
    }

    /**
     * 脚布局的ViewHolder
     */
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        TextView tv_state;
        TextView tv_line1;
        TextView tv_line2;


        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
            tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
            tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);

        }
    }

    public HotelAdapter(Context context, List<HotelItem> secondList) {
        mContext = context;
        msecondList= secondList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = View.inflate(mContext, R.layout.activity_hotelitem, null);
            final HotelAdapter.MyViewHolder holder = new HotelAdapter.MyViewHolder(view);
            holder.hotelView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    int position=holder.getAdapterPosition();
                    HotelItem hotel=msecondList.get(position);
                    Intent intent=new Intent(mContext,DetailspageActivity.class);
                    intent.putExtra("hotelname",hotel.getId()+"");
                    mContext.startActivity(intent);
                }
            });
            return holder;
        } else if (viewType == TYPE_FOOTER) {
            View view = View.inflate(mContext, R.layout.recycler_load_more_layout, null);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myholder= (MyViewHolder) holder;
            //Log.d("msecondList",msecondList.get(position)+"");
            if(myholder!=null){
                Log.e("msecondList","1");
            }
            HotelItem imagesecond=msecondList.get(position);
            if(imagesecond!=null){
                Log.e("msecondList","2");
            }
            if(myholder.viewimage!=null){
                Log.e("msecondList","3");
            }
            myholder.viewimage.setImageURI( imagesecond.getImageName());
            myholder.viewtext_name.setText(imagesecond.getName());
            myholder.viewtext_scores.setText(imagesecond.getScores()+"");
            //myholder.viewtext_label.setText(imagesecond.getTurnover()+"");
            myholder.viewtext_price.setText(imagesecond.getPrice()+"");
            myholder.viewtext_adress.setText(imagesecond.getAdress());
            myholder.viewtext_number.setText("   "+imagesecond.getTurnover()+"+人消费");
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (footer_state) {//根据状态来让脚布局发生改变
//                case PULL_LOAD_MORE://上拉加载
//                    footViewHolder.mProgressBar.setVisibility(View.GONE);
//                    footViewHolder.tv_state.setText("上拉加载更多");
//                    break;
                case LOADING_MORE:
                    footViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                    footViewHolder.tv_line1.setVisibility(View.GONE);
                    footViewHolder.tv_line2.setVisibility(View.GONE);
                    footViewHolder.tv_state.setText("正在加载...");
                    break;
                case NO_MORE:
                    footViewHolder.mProgressBar.setVisibility(View.GONE);
                    footViewHolder.tv_line1.setVisibility(View.VISIBLE);
                    footViewHolder.tv_line2.setVisibility(View.VISIBLE);
                    footViewHolder.tv_state.setText("我是有底线的");
                    footViewHolder.tv_state.setTextColor(Color.parseColor("#ff00ff"));
                    break;
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        //如果position加1正好等于所有item的总和,说明是最后一个item,将它设置为脚布局
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return msecondList != null ? msecondList.size()+1: 0;
    }

    /**
     * 正常布局的ViewHolder
     */

    /**
     * 改变脚布局的状态的方法,在activity根据请求数据的状态来改变这个状态
     *
     * @param state
     */
    public void changeState(int state) {
        this.footer_state = state;
        notifyDataSetChanged();
    }

}
