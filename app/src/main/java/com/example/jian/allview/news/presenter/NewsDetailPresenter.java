package com.example.jian.allview.news.presenter;


import com.example.jian.allview.api.SubscriberCallBack;
import com.example.jian.allview.app.Constant;
import com.example.jian.allview.base.BasePresenter;

import com.example.jian.allview.news.bean.CommentResponse;
import com.example.jian.allview.news.bean.NewsDetail;
import com.example.jian.allview.news.ui.view.INewsDetailView;


import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 新闻详情获取数据的presenter
 * @date 2017/6/28  15:42
 */

public class NewsDetailPresenter extends BasePresenter<INewsDetailView> {

    public NewsDetailPresenter(INewsDetailView view) {
        super(view);
    }

    public void getComment(String groupId, String itemId, int pageNow) {
        int offset = (pageNow - 1) * Constant.COMMENT_PAGE_SIZE;
        addSubscription(mApiService.getComment("http://is.snssdk.com/article/v2/tab_comments/group_id="+groupId+"&item_id="+itemId+"&offset="+offset+"&count="+ String.valueOf(Constant.COMMENT_PAGE_SIZE)), new Subscriber<CommentResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               e.printStackTrace();

                mView.onError();
            }

            @Override
            public void onNext(CommentResponse response) {
                mView.onGetCommentSuccess(response);
            }

        });
    }

    public void getNewsDetail(String url) {
        addSubscription(mApiService.getNewsDetail(url), new SubscriberCallBack<NewsDetail>() {

            @Override
            protected void onSuccess(NewsDetail response) {
                mView.onGetNewsDetailSuccess(response);
            }

            @Override
            protected void onError() {
                mView.onError();
            }
        });
    }
}
