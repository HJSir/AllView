package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/25.
 */

public class HotArticle {
    /**
     * articleURL : hotFoundarticleURL/1.html
     * coverURL : static/img/banner-index-1.jpg
     * heat : 223
     * id : 2
     * introduction : 玻璃桥好玩吗？求介绍
     * title : 玻璃桥好玩吗？
     */
    private String articleURL;
    private String coverURL;
    private int heat;
    private int id;
    private String introduction;
    private String title;

    public String getArticleURL() {
        return articleURL;
    }

    public void setArticleURL(String articleURL) {
        this.articleURL = articleURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
