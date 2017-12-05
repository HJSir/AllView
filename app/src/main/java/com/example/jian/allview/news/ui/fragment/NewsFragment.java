package com.example.jian.allview.news.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jian.allview.R;
import com.example.jian.allview.app.Constant;
import com.example.jian.allview.base.BaseFragment;

import com.example.jian.allview.news.bean.News;
import com.example.jian.allview.news.presenter.NewsListPresenter;
import com.example.jian.allview.news.ui.activity.NewsDetailBaseActivity;
import com.example.jian.allview.news.ui.activity.NewsDetailDetailActivity;
import com.example.jian.allview.news.ui.activity.WebViewActivity;
import com.example.jian.allview.news.ui.adapter.NewsAdapter;
import com.example.jian.allview.news.ui.view.lNewsListView;

import com.example.jian.allview.utils.ListUtils;
import com.example.jian.allview.utils.NetWorkUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class NewsFragment extends BaseFragment<NewsListPresenter> implements lNewsListView, BaseQuickAdapter.RequestLoadMoreListener ,BGARefreshLayout.BGARefreshLayoutDelegate {
   RecyclerView mRvNews;
    Context mContext;
    private BGARefreshLayout mRefreshLayout;
    protected BaseQuickAdapter mNewsAdapter;
    private boolean isVideoList;
    private List<News> mNewsList = new ArrayList<>();
    @Override
    public void initListener() {
        super.initListener();
//        isVideoList = getArguments().getBoolean(Constant.IS_VIDEO_LIST, false);
        isVideoList=false;
        mNewsAdapter = new NewsAdapter(mContext,isVideoList, mNewsList);
        mRvNews.setAdapter(mNewsAdapter);




        mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                News news = mNewsList.get(position);

                String itemId = news.item_id;
                StringBuffer urlSb = new StringBuffer("http://m.toutiao.com/i");
                urlSb.append(itemId).append("/info/");
                String url = urlSb.toString();//http://m.toutiao.com/i6412427713050575361/info/
                Intent intent = null;

                if (news.has_video) {
                    //如果article_type为1，则是使用WebViewActivity打开
                    intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra(WebViewActivity.URL, news.article_url);
                    startActivity(intent);
                    return;
                } else {
                    //非视频新闻
                    if (news.article_type == 1) {
                        //如果article_type为1，则是使用WebViewActivity打开
                        intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra(WebViewActivity.URL, news.article_url);
                        startActivity(intent);
                        return;
                    }
                    //其他新闻
                    intent = new Intent(mContext, NewsDetailDetailActivity.class);
                }

                intent.putExtra(NewsDetailBaseActivity.CHANNEL_CODE, 1);
                intent.putExtra(NewsDetailBaseActivity.POSITION, position);

                intent.putExtra(NewsDetailBaseActivity.DETAIL_URL, url);
                Log.i("tag",url);
                intent.putExtra(NewsDetailBaseActivity.GROUP_ID, news.group_id);
                intent.putExtra(NewsDetailBaseActivity.ITEM_ID, itemId);

                startActivity(intent);
            }
        });

        mNewsAdapter.setEnableLoadMore(true);
        mNewsAdapter.setOnLoadMoreListener(this, mRvNews);




    }
    /**
     * 处理置顶新闻和广告重复
     */
    private void dealRepeat(List<News> newList) {
        if (!ListUtils.isEmpty(newList)) {
            //如果是推荐频道并且数据列表已经有数据,处理置顶新闻或广告重复的问题
            newList.remove(0);//由于第一条新闻是重复的，移除原有的第一条
         //移除广告

            for(int i=0;i<newList.size();i++){
                News fourthNews = newList.get(i);
                //如果列表第4个和原有列表第4个新闻都是广告，并且id一致，移除 ,无标题 移除
                if (fourthNews.tag.equals(Constant.ARTICLE_GENRE_AD)|| TextUtils.isEmpty(fourthNews.abstractX)) {
                    newList.remove(i);
                }

            }

        }
    }
    @Override
    public void initData() {
        super.initData();




    }
    @Override
    protected void loadData() {

mPresenter.getNewsList();


    }
    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        mRefreshLayout = (BGARefreshLayout)rootView. findViewById(R.id.refresh_layout);
        mContext=getActivity();
        mRvNews= (RecyclerView) rootView.findViewById(R.id.rv_news);
        mRvNews.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        initRefreshLayout(mRefreshLayout);
    }

    @Override
    protected NewsListPresenter createPresenter() {
        return  new NewsListPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_news;
    }



    @Override
    public void onGetNewsListSuccess(List<News> newList) {
        dealRepeat(newList);//处理新闻重复问题
        mNewsList.addAll(0, newList);
        mNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoadMoreRequested() {
        // BaseRecyclerViewAdapterHelper的加载更多
//        if (mNewsRecord.getPage() == 0 || mNewsRecord.getPage() == 1) {
//            //如果记录的页数为0(即是创建的空记录)，或者页数为1(即已经是第一条记录了)
//            //mRefreshLayout.endLoadingMore();//结束加载更多
//            mNewsAdapter.loadMoreEnd();
//            return;
//        }
//
//        NewsRecord preNewsRecord = NewsRecordHelper.getPreNewsRecord(mChannelCode, mNewsRecord.getPage());
//        if (preNewsRecord == null) {
        mRefreshLayout.endRefreshing();
             mRefreshLayout.endLoadingMore();//结束加载更多
            mNewsAdapter.loadMoreEnd();
        mNewsAdapter.loadMoreEnd(true);
        Log.i("s","我进来了");

//            return;
//        }
//
//        mNewsRecord = preNewsRecord;

        long startTime = System.currentTimeMillis();

//        List<News> newsList = NewsRecordHelper.convertToNewsList(mNewsRecord.getJson());
//
//        if (isRecommendChannel) {
//            //如果是推荐频道
//            newsList.remove(0);//移除第一个，因为第一个是置顶新闻，重复
//        }

        long endTime = System.currentTimeMillis();

        //由于是读取数据库，如果耗时不足1秒，则1秒后才收起加载更多
//        if (endTime - startTime <= 1000) {
//            UIUtils.postTaskDelay(new Runnable() {
//                @Override
//                public void run() {
//                    mNewsAdapter.loadMoreComplete();
//                    mNewsList.addAll(newsList);//添加到集合下面
//                    mNewsAdapter.notifyDataSetChanged();//刷新adapter
//                }
//            }, (int) (1000 - (endTime - startTime)));
//        }
    }
    private void initRefreshLayout(BGARefreshLayout refreshLayout) {

        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder(mContext, true);
        // 设置下拉刷新和上拉加载更多的风格

        refreshViewHolder.setOriginalImage(R.mipmap.refresh);
        refreshViewHolder.setUltimateColor(R.color.blue_theme);

        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
         mRefreshLayout.setIsShowLoadingMoreView(false);
        // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("正在加载更多");
        // 设置整个加载更多控件的背景颜色资源 id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.blue_theme);
        // 设置整个加载更多控件的背景 drawable 资源 id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
//        // 设置下拉刷新控件的背景颜色资源 id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
//        // 设置下拉刷新控件的背景 drawable 资源 id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
//        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//        mRefreshLayout.setCustomHeaderView(mBanner, false);
        // 可选配置  -------------END
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        // 在这里加载最新数据

        if (!NetWorkUtils.isNetworkAvailable(mActivity)) {
            //网络不可用弹出提示
         Toast.makeText(mContext,"网络不可用",Toast.LENGTH_SHORT);
            if (mRefreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                mRefreshLayout.endRefreshing();
            }
            return;
        }
        mPresenter.getNewsList();
        Log.i("shuaxin","刷新完毕");
        mRefreshLayout.endRefreshing();
        mRefreshLayout.endLoadingMore();//结束加载更多
        mNewsAdapter.loadMoreEnd();
        mNewsAdapter.loadMoreEnd(true);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        // 在这里加载更多数据，或者更具产品需求实现上拉刷新也可以

       return false;
    }

    // 通过代码方式控制进入正在刷新状态。应用场景：某些应用在 activity 的 onStart 方法中调用，自动进入正在刷新状态获取最新数据
    public void beginRefreshing() {
        mRefreshLayout.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态
    public void beginLoadingMore() {
        mRefreshLayout.beginLoadingMore();
    }
}
