package com.example.jian.allview.app;

import android.content.Intent;
import android.view.View;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.lenovo.mainui.activity.*;

/**
 * Created by jian on 2017/9/17.
 */

public class HomeFragment extends BaseFragment {
    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {

    }
}

