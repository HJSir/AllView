package com.example.jian.allview.find.presenter;

import android.util.Log;

import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.find.bean.FindArticle;
import com.example.jian.allview.find.ui.view.IFindArticleView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by jian on 2017/11/24.
 */

public class FindArticlePresenter extends BasePresenter<IFindArticleView> {
    public FindArticlePresenter(IFindArticleView view) {
        super(view);
    }


    public void getArticleList(){
        addSubscription(mApiService.getFindArticleList(), new Subscriber<List<FindArticle>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Tag",e.toString());
            }

            @Override
            public void onNext(List<FindArticle> o) {

                   mView.getArticleList(o);
            }
        });




    }
}
