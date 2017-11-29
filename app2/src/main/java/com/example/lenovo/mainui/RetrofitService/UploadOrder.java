package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/28.
 */

public class UploadOrder {
    /**
     * hotelID : 1
     * roomID : 2
     * userID : 2
     * orderName : f3f23r2
     * coverURL : 42523
     * orderNum : 1
     * orderStatus : 1
     * ordertype : 1
     * total : 350
     * payTime : 1391141532000
     */

    private int hotelID;
    private int roomID;
    private int userID;
    private String orderName;
    private String coverURL;
    private int orderNum;
    private int orderStatus;
    private int ordertype;
    private int total;
    private long payTime;
    public UploadOrder(int hotelID,int roomID,int userID,String orderName,String coverURL,int orderNum,int orderStatus,int ordertype,int total,long payTime){
        this.hotelID=hotelID;
        this.roomID=roomID;
        this.userID=userID;
        this.orderName=orderName;
        this.coverURL=coverURL;
        this.orderNum=orderNum;
        this.orderStatus=orderStatus;
        this.ordertype=ordertype;
        this.total=total;
        this.payTime=payTime;
}
    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }
}
