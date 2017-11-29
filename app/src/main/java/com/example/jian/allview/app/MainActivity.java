package com.example.jian.allview.app;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;

import com.example.jian.allview.find.ui.fragment.FindFragment;
import com.example.jian.allview.mine.ui.fragment.MineFragment;
import com.example.jian.allview.news.ui.fragment.NewsFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_home;
    private TextView tv_find;
    private TextView tv_news;
    private TextView tv_mine;
    private FindFragment fg_find;
    private HomeFragment fg_home;
    private MineFragment fg_mine;
    private NewsFragment fg_news;

    @Override
    public void initView() {
        tv_find = (TextView) findViewById(R.id.tab_find);
        tv_home = (TextView) findViewById(R.id.tab_home);
        tv_news = (TextView) findViewById(R.id.tab_news);
        tv_mine = (TextView) findViewById(R.id.tab_mine);
        setDefualtView();
    }

    public void setDefualtView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        changeStatues(tv_home);
        //set默认fragment
        if (fg_home == null) {

            fg_home = new HomeFragment();
            transaction.add(R.id.fragment_container, fg_home);

        } else {
            transaction.show(fg_home);
        }
        transaction.commit();

    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (fg_find != null) {
            transaction.hide(fg_find);
        }
        if (fg_home != null) {
            transaction.hide(fg_home);
        }
        if (fg_mine != null) {
            transaction.hide(fg_mine);
        }
        if (fg_news != null) {
            transaction.hide(fg_news);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tv_find.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_news.setOnClickListener(this);
        tv_mine.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    public void selected() {
        tv_find.setSelected(false);
        tv_home.setSelected(false);
        tv_news.setSelected(false);
        tv_mine.setSelected(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    public void changeStatues(TextView tv) {

        selected();
        tv.setSelected(true);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()) {
            case R.id.tab_home:
                changeStatues(tv_home);
                if (fg_home == null) {
                    fg_home = new HomeFragment();
                    transaction.add(R.id.fragment_container, fg_home);
                } else {
                    transaction.show(fg_home);
                }

                break;
            case R.id.tab_news:
                changeStatues(tv_news);
                if (fg_news == null) {
                    fg_news = new NewsFragment();
                    transaction.add(R.id.fragment_container, fg_news);
                } else {
                    transaction.show(fg_news);
                }

                break;
            case R.id.tab_find:
                changeStatues(tv_find);
                if (fg_find == null) {
                    fg_find = new FindFragment();
                    transaction.add(R.id.fragment_container, fg_find);
                } else {
                    transaction.show(fg_find);
                }

                break;
            case R.id.tab_mine:
                changeStatues(tv_mine);
                if (fg_mine == null) {
                    fg_mine = new MineFragment();
                    transaction.add(R.id.fragment_container, fg_mine);
                } else {
                    transaction.show(fg_mine);
                }

                break;
        }
        transaction.commit();

    }
}
