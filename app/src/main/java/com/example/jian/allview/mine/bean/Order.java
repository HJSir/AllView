package com.example.jian.allview.mine.bean;

/**
 * Created by jian on 2017/11/23.
 */

public class Order {


    /**
     * coverURL : static/img/banner-index-1.jpg
     * hotelID : 1
     * id : 1
     * orderName : 温馨旅社-游客房
     * orderNum : 1
     * orderStatus : 1
     * ordertype : 1
     * roomID : 1
     * scenicID : 0
     * total : 320
     * userID : 1
     */

    private String coverURL;
    private int hotelID;
    private int id;
    private String orderName;
    private int orderNum;
    private int orderStatus;
    private int ordertype;
    private int roomID;
    private int scenicID;
    private int total;
    private int userID;

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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getScenicID() {
        return scenicID;
    }

    public void setScenicID(int scenicID) {
        this.scenicID = scenicID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
