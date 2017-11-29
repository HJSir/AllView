package com.example.jian.allview.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T mPresenter;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        mPresenter = createPresenter();
        initView();
        initData();
        initListener();

    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    protected abstract int provideContentViewId();

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }


}
