package com.example.jian.allview.find.bean;

/**
 * Created by jian on 2017/11/24.
 */

public class FindArticle {


    /**
     * articleURL : hotFoundarticleURL/1.jpg
     * coverURL : hotFoundCoverURL/1.jpg
     * heat : 131
     * id : 1
     * introduction : 介绍一下：我是第一个页面啊
     * theme : 酒店
     * title : 我是第一个页面
     * type : 1
     */

    private String articleURL;
    private String coverURL;
    private int heat;
    private int id;
    private String introduction;
    private String theme;
    private String title;
    private int type;

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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
