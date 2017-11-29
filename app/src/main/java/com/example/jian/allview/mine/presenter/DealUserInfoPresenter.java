package com.example.jian.allview.mine.presenter;

import android.util.Log;

import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.UserInfomation;
import com.example.jian.allview.mine.ui.view.IUserDealView;

import rx.Subscriber;

/**
 * Created by jian on 2017/11/19.
 */

public class DealUserInfoPresenter extends BasePresenter<IUserDealView> {
    public DealUserInfoPresenter(IUserDealView view) {
        super(view);
    }

    public void dealinfo(UserInfomation userInfomation){

        addSubscription(mApiService.deaUserInfo(userInfomation), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag",e.toString());
            }

            @Override
            public void onNext(String o) {
                Log.i("Tag",o);
              mView.DealSuccess(o);
            }
        });



    }
}
