package com.example.jian.allview.mine.presenter;

import android.util.Log;

import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.User;
import com.example.jian.allview.mine.bean.UserInfo;
import com.example.jian.allview.mine.bean.UserInfomation;
import com.example.jian.allview.mine.ui.view.ILoginView;

import rx.Subscriber;

/**
 * Created by jian on 2017/11/19.
 */

public class LoginPresenter extends BasePresenter <ILoginView>{


    public LoginPresenter(ILoginView view) {
        super(view);
    }
    public  void getStatue(User user){


        addSubscription(mApiService.getLoginStatues(user), new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag",e.toString());
            }

            @Override
            public void onNext(Object o) {
                Log.i("Tag",o.toString());
             mView.getStatues(o.toString());
            }
        });

    }
    public  void getInfo(UserInfo user){


        addSubscription(mApiService.getUserInfo(user), new Subscriber<UserInfomation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag",e.toString());
            }

            @Override
            public void onNext(UserInfomation o) {



                mView.getUserInfo(o);
            }
        });

    }

}
