package com.example.jian.allview.mine.ui.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;

import com.example.jian.allview.mine.ui.fragment.EditUserInfoFragment;
import com.example.jian.allview.mine.ui.fragment.LoginFragment;
import com.example.jian.allview.mine.ui.fragment.MineFragment;
import com.example.jian.allview.mine.ui.fragment.RegistDtailFragment;
import com.example.jian.allview.mine.ui.fragment.UserDtailFragment;

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class LoginActivity extends BaseActivity implements LoginFragment.LoginClickListener ,LoginFragment.RegistClickListener,UserDtailFragment.setMineEditClick,RegistDtailFragment.RegistJump{
    EventHandler eventHandler;
    private UserDtailFragment userdetail;
    private LoginFragment mLoginFragment;
    private EditUserInfoFragment mEditUserInfoFragment;
    private RegistDtailFragment mRegistDtailFragment;


    @Override
    public void initView() {
        super.initView();
        InitLoginFragment();
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
        return R.layout.activity_login;
    }

    public void InitLoginFragment(){
        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(preferences.getBoolean("Stag",false)) {

            userdetail = new UserDtailFragment();
            transaction.replace(R.id.id_Login, userdetail);
            transaction.commit();
        }else{
            mLoginFragment = new LoginFragment();
            transaction.replace(R.id.id_Login, mLoginFragment);
            transaction.commit();
        }
     }





    @Override
    public void setMineEditOnClick(View v) {
        mEditUserInfoFragment = new EditUserInfoFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.id_Login, mEditUserInfoFragment);
        FragmentTransaction ft2 =  getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.id_Login, mEditUserInfoFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();
    }


    @Override
    public void onJump() {

        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mLoginFragment = new LoginFragment();
        transaction.replace(R.id.id_Login, mLoginFragment);
        transaction. commitAllowingStateLoss();
    }

    @Override
    public void onLogin() {
    finish();

    }

    @Override
    public void onRegist() {


        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mRegistDtailFragment = new RegistDtailFragment();
        transaction.replace(R.id.id_Login, mRegistDtailFragment);
        transaction. commitAllowingStateLoss();


    }




}
