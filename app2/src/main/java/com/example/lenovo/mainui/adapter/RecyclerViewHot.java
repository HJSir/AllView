package com.example.lenovo.mainui.adapter;

/**
 * Created by Lenovo on 2017/11/2.
 */

public class RecyclerViewHot {
    private String imagename;
    private String name;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    public RecyclerViewHot(String imagename, String name,String label1, String label2, String label3, String label4){
        this.imagename=imagename;
        this.name=name;
        this.label1=label1;
        this.label2=label2;
        this.label3=label3;
        this.label4=label4;
    }
       public String getImageName(){
           return imagename;
       }
       public String getLabel1(){
           return label1;
       }
        public String getLabel2(){
        return label2;
      }
      public String getLabel3(){
        return label3;
      }
      public String getLabel4(){
        return label4;
      }
      public String getName(){
          return name;
      }
}
