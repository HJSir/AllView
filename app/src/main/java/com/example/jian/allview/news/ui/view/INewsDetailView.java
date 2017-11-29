package com.example.jian.allview.news.ui.view;


import com.example.jian.allview.news.bean.CommentResponse;
import com.example.jian.allview.news.bean.NewsDetail;

public interface INewsDetailView {

    void onGetNewsDetailSuccess(NewsDetail newsDetail);

    void onGetCommentSuccess(CommentResponse response);

    void onError();
}
