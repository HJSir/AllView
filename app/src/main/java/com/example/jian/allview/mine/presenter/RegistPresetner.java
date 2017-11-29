package com.example.jian.allview.mine.presenter;

import android.util.Log;

import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.User;
import com.example.jian.allview.mine.ui.view.IRegistView;

import rx.Subscriber;

/**
 * Created by jian on 2017/11/19.
 */

public class RegistPresetner extends BasePresenter<IRegistView>{
    public RegistPresetner(IRegistView view) {
        super(view);
    }
    public void getRegesit(User user){

        addSubscription(mApiService.getRegsitStatues(user), new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag",e.toString());
            }

            @Override
            public void onNext(Object o) {
                Log.i("tag","ssss"+ o.toString());
                mView.getRegistStatues(o.toString());

            }
        });




    }
}
