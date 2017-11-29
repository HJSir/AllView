package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/26.
 */

public class HotelDetails {
    /**
     * VRURL : hotFoundarticleURL/1.html
     * address : 张家界
     * coverURL : static/img/banner-index-1.jpg
     * fans : 123
     * hotelIntroduce : 位于张家界市
     * hotelLevel : 4.6
     * hotelName : 温馨旅社
     * hotelPrice : 120
     * id : 1
     * latitude : 115.5
     * longitude : 34.5
     */

    private String VRURL;
    private String address;
    private String coverURL;
    private int fans;
    private String hotelIntroduce;
    private double hotelLevel;
    private String hotelName;
    private int hotelPrice;
    private int id;
    private double latitude;
    private double longitude;
    private String phoneNum;
    public String getVRURL() {
        return VRURL;
    }

    public void setVRURL(String VRURL) {
        this.VRURL = VRURL;
    }

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

    public String getHotelIntroduce() {
        return hotelIntroduce;
    }

    public void setHotelIntroduce(String hotelIntroduce) {
        this.hotelIntroduce = hotelIntroduce;
    }

    public double getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(double hotelLevel) {
        this.hotelLevel = hotelLevel;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
