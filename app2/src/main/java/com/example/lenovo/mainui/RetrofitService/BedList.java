package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/26.
 */

public class BedList {
    /**
     * bedType : 双人床
     * coverURL : static/img/banner-index-1.jpg
     * hotelID : 1
     * id : 1
     * price : 300
     * roomID : 1
     * roomName : 游客房
     * stock : 41
     */

    private String bedType;
    private String coverURL;
    private int hotelID;
    private int id;
    private int price;
    private int roomID;
    private String roomName;
    private int stock;

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
