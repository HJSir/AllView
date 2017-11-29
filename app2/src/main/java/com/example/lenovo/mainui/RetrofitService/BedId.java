package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/27.
 */

public class BedId {

    /**
     * hotelID : 1
     * roomID : 1
     */

    private int hotelID;
    private int roomID;
public BedId(int hotelID,int roomID){
    this.hotelID=hotelID;
    this.roomID=roomID;
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
}
