package com.example.jian.allview.mine.ui.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.ui.fragment.MineSetFragment;


public class MineSetActivity extends BaseActivity {
private MineSetFragment minsetFragment;

    @Override
    public void initView() {
        super.initView();
        setDefalutMineSetFragment();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_mine_set;
    }

    private void setDefalutMineSetFragment() {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        minsetFragment =new MineSetFragment();
        transaction.replace(R.id.activity_mine_set,minsetFragment);
        transaction.commit();
    }
}
