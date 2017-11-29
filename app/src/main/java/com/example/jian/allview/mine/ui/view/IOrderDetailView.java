package com.example.jian.allview.mine.ui.view;

import com.example.jian.allview.mine.bean.Hotel;
import com.example.jian.allview.mine.bean.HotelAndRoomID;
import com.example.jian.allview.mine.bean.HotelInfo;
import com.example.jian.allview.mine.bean.OrderDetail;
import com.example.jian.allview.mine.bean.ScenicSpot;

/**
 * Created by jian on 2017/11/27.
 */

public interface IOrderDetailView {

     void getDetail(OrderDetail orderDetail);
     void getHotelRoomInfo(HotelInfo hotelInfo);
   void getHotelInfo(Hotel hotel);
    void getScenicSpotInfo(ScenicSpot spot);

}
