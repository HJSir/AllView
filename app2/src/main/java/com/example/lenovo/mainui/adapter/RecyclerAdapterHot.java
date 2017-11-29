package com.example.lenovo.mainui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.mainui.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/2.
 */

public class RecyclerAdapterHot extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<RecyclerViewHot> msecondList;
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
        SimpleDraweeView viewimage;
        TextView viewtext_label1;
        TextView viewtext_label2;
        TextView viewtext_label3;
        TextView viewtext_label4;
        TextView viewtext_name;

        public MyViewHolder(View view) {
            super(view);
            viewimage=(SimpleDraweeView) view.findViewById(R.id.view_hot);

            viewtext_name=(TextView) view.findViewById(R.id.view_name);
            viewtext_label1=(TextView) view.findViewById(R.id.view_label1);
            viewtext_label2=(TextView) view.findViewById(R.id.view_label2);
            viewtext_label3=(TextView) view.findViewById(R.id.view_label3);
            viewtext_label4=(TextView) view.findViewById(R.id.view_label4);
            if(viewimage==null){
                Log.e("error","3");
            }
        }
    }

    /**
     * 脚布局的ViewHolder
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private TextView tv_state;
        private TextView tv_line1;
        private TextView tv_line2;


        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
            tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
            tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);

        }
    }

    public RecyclerAdapterHot(Context context, List<RecyclerViewHot> secondList,int width) {
        mContext = context;
        msecondList= secondList;
        this.width=width;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = View.inflate(mContext, R.layout.recyclerhot, null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        } else if (viewType == TYPE_FOOTER) {
            View view = View.inflate(mContext,R.layout.recycler_load_more_layout, null);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }
int width;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
           MyViewHolder myholder= (MyViewHolder) holder;
            //Log.d("msecondList",msecondList.get(position)+"");
            if(myholder!=null){
                Log.e("msecondList","1");
            }
            //ImageSecond imagesecond=msecondList.get(position);
            RecyclerViewHot recyclerViewHot =msecondList.get(position);
          /*  if(imagesecond!=null){
                Log.e("msecondList","2");
            }
            if(myholder.viewimage!=null){
                Log.e("msecondList","3");
            }*/

            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
//            heigth = dm.heightPixels;
//            width = dm.widthPixels;
//            DisplayMetrics dm = new DisplayMetrics();
            ViewGroup.LayoutParams lp;

            lp=myholder.viewimage.getLayoutParams();
            lp.width=width;


            myholder.viewimage.setLayoutParams(lp);
            myholder.viewimage.setImageURI(Uri.parse(recyclerViewHot.getImageName()));
           // myholder.viewimage.setBackgroundResource(R.mipmap.image_test1);
          // Log.e("yanzhen",Uri.parse(recyclerViewHot.getImageName())+"");
            myholder.viewtext_name.setText(recyclerViewHot.getName());
            myholder.viewtext_label1.setText(recyclerViewHot.getLabel1());
            myholder.viewtext_label2.setText(recyclerViewHot.getLabel2());
            myholder.viewtext_label3.setText(recyclerViewHot.getLabel3());
            myholder.viewtext_label4.setText(recyclerViewHot.getLabel4());
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
