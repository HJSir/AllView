package com.example.jian.allview.news.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jian.allview.R;
import com.example.jian.allview.app.Constant;
import com.example.jian.allview.base.BaseActivity;

import com.example.jian.allview.news.bean.CommentData;
import com.example.jian.allview.news.bean.CommentResponse;
import com.example.jian.allview.news.presenter.NewsDetailPresenter;
import com.example.jian.allview.news.ui.adapter.CommentAdapter;
import com.example.jian.allview.news.ui.view.INewsDetailView;
import com.example.jian.allview.news.ui.view.NewsDetailHeaderView;

import com.example.jian.allview.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;


public abstract class NewsDetailBaseActivity extends BaseActivity<NewsDetailPresenter> implements INewsDetailView, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {

    public static final String CHANNEL_CODE = "channelCode";
    public static final String PROGRESS = "progress";
    public static final String POSITION = "position";
    public static final String DETAIL_URL = "detailUrl";
    public static final String GROUP_ID = "groupId";
    public static final String ITEM_ID = "itemId";


    FrameLayout mFlContent;


    RecyclerView mRvComment;


    TextView mTvCommentCount;

    private List<CommentData> mCommentList = new ArrayList<>();
//    private StateView mStateView;
    private CommentAdapter mCommentAdapter;
    protected NewsDetailHeaderView mHeaderView;
    private String mDetalUrl;
    private String mGroupId;
    private String mItemId;
    protected CommentResponse mCommentResponse;

    protected String mChannelCode;
    protected int mPosition;

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return getViewContentViewId();
    }

    protected abstract int getViewContentViewId();

    @Override
    public void initView() {
        mRvComment= (RecyclerView) findViewById(R.id.rv_comment);
        mTvCommentCount= (TextView) findViewById(R.id.tv_comment_count);
        mFlContent= (FrameLayout) findViewById(R.id.fl_comment_icon);

//        mStateView = StateView.inject(mFlContent);
//        mStateView.setLoadingResource(R.layout.page_loading);
//        mStateView.setRetryResource(R.layout.page_net_error);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();

//        mChannelCode = intent.getStringExtra(CHANNEL_CODE);
        mPosition = intent.getIntExtra(POSITION, 0);

        mDetalUrl = intent.getStringExtra(DETAIL_URL);
        mGroupId = intent.getStringExtra(GROUP_ID);
        mItemId = intent.getStringExtra(ITEM_ID);

        mItemId = mItemId.replace("i", "");

        mPresenter.getNewsDetail(mDetalUrl);
        loadCommentData();
    }

    private void loadCommentData() {
//        mStateView.showLoading();
        mPresenter.getComment(mGroupId, mItemId, 1);
    }

    @Override
    public void initListener() {
        mFlContent.setOnClickListener(this);
        mRvComment.setLayoutManager(new LinearLayoutManager(this));
        mCommentAdapter = new CommentAdapter(this, R.layout.item_comment, mCommentList);
        mRvComment.setAdapter(mCommentAdapter);

        mHeaderView = new NewsDetailHeaderView(this);
        mCommentAdapter.addHeaderView(mHeaderView);

        mCommentAdapter.setEnableLoadMore(true);
        mCommentAdapter.setOnLoadMoreListener(this, mRvComment);

//        mCommentAdapter.setEmptyView(R.layout.pager_no_comment);
        mCommentAdapter.setHeaderAndEmpty(true);

//        mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
//            @Override
//            public void onRetryClick() {
//                loadCommentData();
//            }
//        });
    }


    @Override
    public void onGetCommentSuccess(CommentResponse response) {

        mCommentResponse = response;

        if (ListUtils.isEmpty(mCommentList)){
            //第一次访问,展示内容布局
//            mStateView.showContent();
        }

        if (ListUtils.isEmpty(response.data)){
            //没有评论了
            mCommentAdapter.loadMoreEnd();
            return;
        }

        if (response.total_number > 0) {
            //如果评论数大于0，显示红点
            mTvCommentCount.setVisibility(View.VISIBLE);
            mTvCommentCount.setText(String.valueOf(response.total_number));
        }

        mCommentList.addAll(response.data);
        mCommentAdapter.notifyDataSetChanged();

        if (!response.has_more) {
            mCommentAdapter.loadMoreEnd();
        }else{
            mCommentAdapter.loadMoreComplete();
        }
    }


    @Override
    public void onError() {
//        mStateView.showRetry();
    }



    @Override
    public void onLoadMoreRequested() {
        mPresenter.getComment(mGroupId, mItemId, mCommentList.size() / Constant.COMMENT_PAGE_SIZE + 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_comment_icon:
                //底部评论的图标
                RecyclerView.LayoutManager layoutManager = mRvComment.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int firstPosition = linearManager.findFirstVisibleItemPosition();
                    int last = linearManager.findLastVisibleItemPosition();
                    if (firstPosition == 0 && last == 0) {
                        //处于头部，滚动到第一个条目
                        mRvComment.scrollToPosition(1);
                    } else {
                        //不是头部，滚动到头部
                        mRvComment.scrollToPosition(0);
                    }
                }
                break;
        }
    }


    /**
     *  发送事件，用于更新上个页面的播放进度以及评论数
     */
//    protected void postVideoEvent(boolean isVideoDetail) {
//        DetailCloseEvent event = new DetailCloseEvent();
//        event.setChannelCode(mChannelCode);
//        event.setPosition(mPosition);
//
//        if (mCommentResponse != null){
//            event.setCommentCount(mCommentResponse.total_number);
//        }
//
//        if (isVideoDetail && JCMediaManager.instance().mediaPlayer != null && JCVideoPlayerManager.getCurrentJcvd() != null){
//            //如果是视频详情
//            int progress = JCMediaManager.instance().mediaPlayer.getCurrentPosition();
//            event.setProgress(progress);
//        }
//
//        EventBus.getDefault().postSticky(event);
//        finish();
//    }
}
