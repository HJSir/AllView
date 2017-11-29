package com.example.lenovo.mainui.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 2017/11/23.
 */

public interface GetService {
    @POST("HotImage/get.action")
    Call<HotViewpage> getHotViewpage();

    @POST("HotArticle/get.action")
    Call<List<HotArticle>> getHotArticle();

    @POST("Hotel/getSummary.action")
    Call<List<HotelList>> getHotel();

    @POST("Hotel/get.action")
    Call<HotelDetails> getHotelDetails(@Body Id id);

    @POST("HotelRoomDetail/getByHotelID.action")
    Call<List<BedList>> getBedDetails(@Body HotelId hotelId);

    @POST("HotelRoomDetail/getByByHotelAndRoom.action")
    Call<BedDetaials> getBed(@Body BedId bedId);

    @POST("Scenic/getSummary.action")
    Call<List<ScenicList>> getScenicList();

    @POST("Scenic/get.action")
    Call<ScenicDetails> getScenicDetails(@Body Id id);


    @POST("UserOrder/add.action")
    Call<String> setOrders(@Body UploadOrder id);
}
