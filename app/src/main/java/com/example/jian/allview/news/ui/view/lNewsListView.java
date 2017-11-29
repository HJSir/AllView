package com.example.jian.allview.news.ui.view;





import com.example.jian.allview.news.bean.News;

import java.util.List;


public interface lNewsListView {

    void onGetNewsListSuccess(List<News> newList);

    void  onError();
}
