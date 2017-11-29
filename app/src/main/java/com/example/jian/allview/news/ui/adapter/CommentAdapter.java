package com.example.jian.allview.news.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jian.allview.R;

import com.example.jian.allview.news.bean.CommentData;
import com.example.jian.allview.utils.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChayChan
 * @description: 新闻详情页评论的适配器
 * @date 2017/6/28  16:01
 */

public class CommentAdapter extends BaseQuickAdapter<CommentData, BaseViewHolder> {

    private Context mContext;

    public CommentAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<CommentData> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentData commentData) {
        SimpleDateFormat sdf= new SimpleDateFormat("MM-dd");
//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(commentData.comment.create_time  * 1000);
        String sDateTime = sdf.format(dt);
//        Log.i("creatTime",""+commentData.comment.create_time);
        GlideUtils.loadRound(mContext, commentData.comment.user_profile_image_url, helper.getView(R.id.iv_avatar));
        helper.setText(R.id.tv_name, commentData.comment.user_name)
                .setText(R.id.tv_like_count, String.valueOf(commentData.comment.digg_count))
                .setText(R.id.tv_content, commentData.comment.text)
                .setText(R.id.tv_time,sDateTime);
//        Log.i("creatTime",""+TimeUtils.getShortTime(commentData.comment.create_time * 1000));
    }
}
