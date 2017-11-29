package com.example.jian.allview.mine.presenter;

import android.util.Log;

import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.Order;
import com.example.jian.allview.mine.bean.ID;
import com.example.jian.allview.mine.ui.view.IOrderView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by jian on 2017/11/23.
 */

public class OrderPresenter extends BasePresenter<IOrderView> {
    public OrderPresenter(IOrderView view) {
        super(view);
    }

    public void getOrderlist(ID arg){

        addSubscription(mApiService.getUserOrderList(arg), new Subscriber<List<Order>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag",e.toString());
            }

            @Override
            public void onNext(List<Order> o) {
            mView.getOrderlist(o);

            }
        });






    }

}
