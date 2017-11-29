package com.example.jian.allview.news.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.jian.allview.base.BasePresenter;


import com.example.jian.allview.news.bean.News;
import com.example.jian.allview.news.bean.NewsData;
import com.example.jian.allview.news.bean.NewsResponse;
import com.example.jian.allview.news.ui.view.lNewsListView;
import com.example.jian.allview.utils.ListUtils;
import com.example.jian.allview.utils.PreUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static com.example.jian.allview.app.BaseApp.mContext;

/**
 * Created by jian on 2017/9/19.
 */

public class NewsListPresenter extends BasePresenter<lNewsListView> {

    private long lastTime;
    public NewsListPresenter(lNewsListView view) {
        super(view);
    }

    public void getNewsList(){

        lastTime = PreUtils.getLong("lastTime",0);//读取对应频道下最后一次刷新的时间戳

//        if (lastTime == 0){
            //如果为空，则是从来没有刷新过，使用当前时间戳
            lastTime = System.currentTimeMillis() / 1000;
//        }
        addSubscription(mApiService.getNewsList("http://is.snssdk.com/api/news/feed/v66/?category=news_local&concern_id=6226114249253456386&refer=1&count=20&loc_mode=6&loc_time=1505739453&latitude=29.136081&longitude=110.465326&city=%E5%BC%A0%E5%AE%B6%E7%95%8C%E5%B8%82&tt_from=pull&user_city=%E5%BC%A0%E5%AE%B6%E7%95%8C&lac=29518&cid=45723&cp=5e97bcfccf924q1&plugin_enable=3&iid=15115459457&device_id=35657034542&ac=wifi&channel=leshistore&aid=13&app_name=news_article&min_behot_time"+lastTime+"&last_refresh_sub_entrance_interval="+System.currentTimeMillis() / 1000+""), new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("jin","onError:"+e.toString());
            }

            @Override
            public void onNext( NewsResponse response) {
                Log.i("jin","onnext");
                lastTime = System.currentTimeMillis() / 1000;
                PreUtils.putLong("lastTime",lastTime);//保存刷新的时间戳
                List<NewsData> data = response.data;
                List<News> newsList = new ArrayList<>();
                if (!ListUtils.isEmpty(data)){
                    for (NewsData newsData : data) {
                        Log.i("jin","2");
                        News news = new Gson().fromJson(newsData.content, News.class);
                        newsList.add(news);
                    }
                }
                Log.i("jin","onnext"+response.toString());
                if(newsList==null){
                    Toast.makeText(mContext,"加载出错",Toast.LENGTH_SHORT);
                }
                mView.onGetNewsListSuccess(newsList);

            }
        });




    }

}
