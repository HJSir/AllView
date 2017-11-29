package com.example.jian.allview.mine.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TableRow;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.ui.activity.LoginActivity;


public class MineSetFragment extends BaseFragment implements View.OnClickListener {
private TableRow bt_logout;





    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        bt_logout.setOnClickListener(this);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        bt_logout= (TableRow) rootView.findViewById(R.id.mineSet_logout);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine_set;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.mineSet_logout:
                SharedPreferences preference=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preference.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
