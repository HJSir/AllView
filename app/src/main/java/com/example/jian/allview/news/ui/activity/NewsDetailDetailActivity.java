package com.example.jian.allview.news.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jian.allview.R;

import com.example.jian.allview.news.bean.NewsDetail;
import com.example.jian.allview.utils.GlideUtils;

/**
 * @author ChayChan
 * @description: 非视频新闻详情
 * @date 2017/6/24  19:3
 */

public class NewsDetailDetailActivity extends NewsDetailBaseActivity {


    ImageView mIvBack;
//    @Bind(R.id.ll_user)
    LinearLayout mLlUser;

//    @Bind(R.id.iv_avatar)
    ImageView mIvAvatar;

//    @Bind(R.id.tv_author)
    TextView mTvAuthor;


    @Override
    protected int getViewContentViewId() {
        return R.layout.activity_news_detail;
    }


    @Override
    public void initView() {
        super.initView();
//        Eyes.setStatusBarColor(this, UIUtils.getColor(R.color.status_color_grey));//设置状态栏的颜色为灰色
    }

    @Override
    public void initListener() {
        super.initListener();
        mLlUser= (LinearLayout) findViewById(R.id.ll_user);
        mIvBack= (ImageView) findViewById(R.id.iv_back);
        mIvAvatar= (ImageView) findViewById(R.id.iv_avatar);
        mTvAuthor= (TextView) findViewById(R.id.tv_author);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int llInfoBottom = mHeaderView.mLlInfo.getBottom();
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRvComment.getLayoutManager();
        mRvComment.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int position = layoutManager.findFirstVisibleItemPosition();
                View firstVisiableChildView = layoutManager.findViewByPosition(position);
                int itemHeight = firstVisiableChildView.getHeight();
                int scrollHeight = (position) * itemHeight - firstVisiableChildView.getTop();

//                KLog.i("scrollHeight: " + scrollHeight);
//                KLog.i("llInfoBottom: " + llInfoBottom);

                mLlUser.setVisibility(scrollHeight > llInfoBottom ? View.VISIBLE : View.GONE);//如果滚动超过用户信息一栏，显示标题栏中的用户头像和昵称
            }
        });
    }

    @Override
    public void onGetNewsDetailSuccess(NewsDetail newsDetail) {
        mHeaderView.setDetail(newsDetail);

        mLlUser.setVisibility(View.GONE);

        if (newsDetail.media_user != null){
            GlideUtils.loadRound(this,newsDetail.media_user.avatar_url, mIvAvatar);
            mTvAuthor.setText(newsDetail.media_user.screen_name);
        }
    }


}
