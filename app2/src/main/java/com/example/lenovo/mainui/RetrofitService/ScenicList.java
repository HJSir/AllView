package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/27.
 */

public class ScenicList {
    /**
     * address : 张家界火车站
     * coverURL :  static/img/banner-index-1.jpgeURL/1.html
     * fans : 0
     * id : 1
     * scenicLevel : 0
     * scenicName : 天门山
     * scenicPrice : 258
     */

    private String address;
    private String coverURL;
    private int fans;
    private int id;
    private int scenicLevel;
    private String scenicName;
    private int scenicPrice;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScenicLevel() {
        return scenicLevel;
    }

    public void setScenicLevel(int scenicLevel) {
        this.scenicLevel = scenicLevel;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public int getScenicPrice() {
        return scenicPrice;
    }

    public void setScenicPrice(int scenicPrice) {
        this.scenicPrice = scenicPrice;
    }
}
