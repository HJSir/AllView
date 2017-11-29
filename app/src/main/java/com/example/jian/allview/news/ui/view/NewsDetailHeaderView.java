package com.example.jian.allview.news.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jian.allview.R;

import com.example.jian.allview.news.bean.NewsDetail;
import com.example.jian.allview.utils.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ChayChan
 * @description: 新闻详情页头部
 * @date 2017/6/28  15:25
 */

public class NewsDetailHeaderView extends FrameLayout {



    TextView mTvTitle;


    public LinearLayout mLlInfo;


    ImageView mIvAvatar;


    TextView mTvAuthor;


    TextView mTvTime;


    WebView mWvContent;

    private Context mContext;

    public NewsDetailHeaderView(Context context) {
        this(context, null);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.header_news_detail, this);
        mTvTitle= (TextView) findViewById(R.id.tvTitle);
        mLlInfo= (LinearLayout) findViewById(R.id.ll_info);
        mIvAvatar= (ImageView) findViewById(R.id.iv_avatar);
        mTvAuthor= (TextView) findViewById(R.id.tv_author);
        mTvTime= (TextView) findViewById(R.id.tv_time);
        mWvContent= (WebView) findViewById(R.id.wv_content);

//        ButterKnife.bind(this, this);
    }

    public void setDetail(NewsDetail detail) {
        mTvTitle.setText(detail.title);
        SimpleDateFormat sdf= new SimpleDateFormat("MM-dd");
//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(detail.publish_time * 1000L);
        String sDateTime = sdf.format(dt);
        if (detail.media_user == null) {
            //如果没有用户信息
            mLlInfo.setVisibility(GONE);
        } else {
            if (!TextUtils.isEmpty(detail.media_user.avatar_url)){
                GlideUtils.loadRound(mContext, detail.media_user.avatar_url, mIvAvatar);
            }
            mTvAuthor.setText(detail.media_user.screen_name);
            mTvTime.setText(sDateTime);
        }

        if (TextUtils.isEmpty(detail.content))
            mWvContent.setVisibility(GONE);


        String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                "<head><meta charset=\"utf-8\"/>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<style> \n" +
                "img{max-width:100%!important;height:auto!important}\n" +
                " </style>";
        String htmlPart2 = "</body></html>";

        String html = htmlPart1 + detail.content + htmlPart2;

        mWvContent.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

}
