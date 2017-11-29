package com.example.jian.allview.mine.presenter;

import android.util.Log;

import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.Hotel;
import com.example.jian.allview.mine.bean.HotelAndRoomID;
import com.example.jian.allview.mine.bean.HotelInfo;
import com.example.jian.allview.mine.bean.ID;
import com.example.jian.allview.mine.bean.IDInt;
import com.example.jian.allview.mine.bean.OrderDetail;
import com.example.jian.allview.mine.bean.ScenicSpot;
import com.example.jian.allview.mine.ui.view.IOrderDetailView;
import com.example.jian.allview.mine.ui.view.IOrderView;

import rx.Subscriber;

/**
 * Created by jian on 2017/11/26.
 */

public class OrderDetailPresenter extends BasePresenter<IOrderDetailView> {
    public OrderDetailPresenter(IOrderDetailView view) {
        super(view);
    }

    public void getDetail(IDInt id)
    {
        addSubscription(mApiService.getOrderDetail(id), new Subscriber<OrderDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("erro getDetail",e.toString());
            }

            @Override
            public void onNext(OrderDetail o) {

                               mView.getDetail(o);

            }
        });



    }

    public void getHotelRoomInfo(HotelAndRoomID id){

        addSubscription(mApiService.getOrderHotelRoomInfo(id), new Subscriber<HotelInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
  Log.e("erro",e.toString());
            }

            @Override
            public void onNext(HotelInfo o) {
                        mView.getHotelRoomInfo(o);
            }
        });



    }
    public void getHotelInfo(IDInt id){

        addSubscription(mApiService.getOrderHotelInfo(id), new Subscriber<Hotel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("erro2",e.toString());
            }

            @Override
            public void onNext(Hotel o) {
                mView.getHotelInfo(o);
            }
        });



    }
    public void getScenicSpot(IDInt id){

        addSubscription(mApiService.getScenicSpotInfo(id), new Subscriber<ScenicSpot>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("erro4",e.toString());
            }

            @Override
            public void onNext(ScenicSpot o) {
                mView.getScenicSpotInfo(o);
            }
        });



    }
}
