package com.example.lenovo.mainui.adapter;

/**
 * Created by Lenovo on 2017/11/16.
 */

public class ProductItem {
    private String imagename;
    private String name;
    private int price;
    private int hotelId;
    private int roomId;
    public ProductItem(String imagename, String name, int price,int hotelId,int roomId){
        this.imagename=imagename;
        this.name=name;
        this.price=price;
        this.hotelId=hotelId;
        this.roomId=roomId;
    }
    public String  getImageName(){
        return imagename;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getHotelId(){
        return hotelId;
    }
    public int getRoomId(){
        return roomId;
    }
}
