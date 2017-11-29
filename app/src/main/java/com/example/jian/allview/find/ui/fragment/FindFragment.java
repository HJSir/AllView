package com.example.jian.allview.find.ui.fragment;

/**
 * Created by jian on 2017/9/17.
 */

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.find.bean.FindArticle;
import com.example.jian.allview.find.presenter.FindArticlePresenter;
import com.example.jian.allview.find.ui.activity.FindDetailActivity;
import com.example.jian.allview.find.ui.adapter.GridViewAdapter;
import com.example.jian.allview.find.ui.view.IFindArticleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jian on 2017/9/17.
 */

public class FindFragment extends BaseFragment<FindArticlePresenter> implements IFindArticleView{

    private GridView mGridView = null;
    private GridViewAdapter mGridViewAdapter = null;
    private ArrayList<FindArticle> mGridData = null;
    private GridView gview2;
    private GridView gview3;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组


    @Override
    public void initListener() {
        super.initListener();

    }

    @Override
    public void initData() {
        super.initData();
  mPresenter.getArticleList();
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        mGridView = (GridView)rootView.findViewById(R.id.gview);
        gview2 = (GridView) rootView.findViewById(R.id.gview2);
        gview3 = (GridView) rootView.findViewById(R.id.gview3);
        data_list = new ArrayList<Map<String, Object>>(); //新建List





    }

    @Override
    protected FindArticlePresenter createPresenter() {
        return new FindArticlePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void getArticleList(List<FindArticle> s) {




        mGridViewAdapter = new GridViewAdapter(getActivity(), R.layout.gridview_item, (ArrayList<FindArticle>) s);
        mGridView.setAdapter(mGridViewAdapter);
        gview2.setAdapter(mGridViewAdapter);
        gview3.setAdapter(mGridViewAdapter);

    }
}