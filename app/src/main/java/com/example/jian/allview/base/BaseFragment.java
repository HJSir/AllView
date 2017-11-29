package com.example.jian.allview.base;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jian.allview.R;


public abstract class BaseFragment <T extends BasePresenter>extends Fragment {
protected T mPresenter;
    private View rootView;
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(rootView==null){

            rootView=inflater.inflate(provideContentViewId(),container,false);
            //执行初始化操作
            initView(rootView);
            initData();
            loadData();
            initListener();


        }else{
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;

    }
    /**
     * 初始化一些view
     * @param rootView
     */
    public void initView(View rootView) {
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 设置listener的操作
     */
    public void initListener() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        rootView = null;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    //加载数据
    protected abstract void loadData();

}
