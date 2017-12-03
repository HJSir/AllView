package com.example.jian.allview.mine.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;

import com.example.jian.allview.mine.bean.Order;
import com.example.jian.allview.mine.bean.ID;
import com.example.jian.allview.mine.bean.orderbean;
import com.example.jian.allview.mine.presenter.OrderPresenter;
import com.example.jian.allview.mine.ui.activity.OrderDetailActivity;
import com.example.jian.allview.mine.ui.adapter.OrderAfterAdapter;
import com.example.jian.allview.mine.ui.view.IOrderView;


import java.util.List;


public class OrderDoFragment extends BaseFragment<OrderPresenter> implements IOrderView {




    List<orderbean> mList;
    orderbean mOrderbean;
    ListView mListView;

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        mListView= (ListView) rootView.findViewById(R.id.lv_order_do);
    }

    @Override
    public void initData() {
        super.initData();

        ID s = new ID();
        s.setUserID("1");
        mPresenter.getOrderlist(s);
    }

    @Override
    public void initListener() {
        super.initListener();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), OrderDetailActivity.class));
            }
        });
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order_do;
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void getOrderlist(List<Order> list) {
        if(list.size()!=0)
            mListView.setAdapter(new OrderAfterAdapter(getActivity(),list));
    }
}


