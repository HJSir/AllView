package com.example.lenovo.mainui.RetrofitService;

/**
 * Created by Lenovo on 2017/11/23.
 */

public class HotViewpage {


    /**
     * id : 1
     * image1URL : static/img/banner-index-1.jpg
     * image2URL : static/img/banner-index-1.jpg
     * image3URL : static/img/banner-index-1.jpg
     */

    private int id;
    private String image1URL;
    private String image2URL;
    private String image3URL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage1URL() {
        return image1URL;
    }

    public void setImage1URL(String image1URL) {
        this.image1URL = image1URL;
    }

    public String getImage2URL() {
        return image2URL;
    }

    public void setImage2URL(String image2URL) {
        this.image2URL = image2URL;
    }

    public String getImage3URL() {
        return image3URL;
    }

    public void setImage3URL(String image3URL) {
        this.image3URL = image3URL;
    }

}
