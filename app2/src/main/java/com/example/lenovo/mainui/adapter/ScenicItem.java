package com.example.lenovo.mainui.adapter;

/**
 * Created by Lenovo on 2017/10/15.
 */

public class ScenicItem {
    private String imagename;
    private String name;
    private int price;
    private int turnover;
    private String adress;
    private double scores;
    private int id;
    public ScenicItem(String imagename, String name, int price, int turnover, String adress, double scores,int id){
        this.imagename=imagename;
        this.name=name;
        this.price=price;
        this.turnover=turnover;
        this.adress=adress;
        this.scores=scores;
        this.id=id;
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
    public int getTurnover(){
        return turnover;
    }
    public String  getAdress(){
        return adress;
    }
    public double getScores(){
        return scores;
    }
    public int getId(){return id;}
}
