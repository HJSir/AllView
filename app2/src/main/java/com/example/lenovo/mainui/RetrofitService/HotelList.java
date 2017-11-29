package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/26.
 */

public class HotelList {
    /**
     * address : 张家界
     * coverURL : static/img/banner-index-1.jpg
     * fans : 123
     * hotelName : 温馨旅社
     * hotelPrice : 120
     * id : 1
     */

    private String address;
    private String coverURL;
    private int fans;
    private String hotelName;
    private int hotelPrice;
    private int id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
