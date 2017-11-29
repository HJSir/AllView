package com.example.lenovo.mainui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.RetrofitService.ScenicList;
import com.example.lenovo.mainui.adapter.ScenicAdapter;
import com.example.lenovo.mainui.adapter.ScenicItem;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ScenicActivity extends Activity implements View.OnClickListener{
    private List<ScenicItem> scenicDate=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ScenicAdapter adapter;
    int lastVisibleItem;
    int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        Fresco.initialize(this);
        Button buttonback=(Button) findViewById(R.id.back_button_scenic);
        buttonback.setOnClickListener(this);
//RecyclerView组件
        //刷新
        swipeRefresh=(SwipeRefreshLayout) findViewById(R.id.refresh_scenic);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                refreshdate();
            }
        });

        recyclerView=(RecyclerView) findViewById(R.id.recycler_scenic);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ScenicAdapter(this,scenicDate);
        //adapter2=new ViewSecondAdapter(secondList);
        recyclerView.setAdapter(adapter);
        //recyclerViewHot.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //到达底部了
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    //到达底部之后如果footView的状态不是正在加载的状态,就将 他切换成正在加载的状态
                    if (page >1) {
                        adapter.changeState(2);
                        Log.e("duanlian", "page111:"+page );
                    } else {
                        Log.e("duanlian", "page:"+page );
                        adapter.changeState(1);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ++page;
                                //loadMore();
                                request_Scenic();
                            }
                        }, 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
        //loadMore();
        request_Scenic();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button_scenic) {
            finish();

        } else {
        }
    }


    private void refreshdate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scenicDate.clear();
                        page=0;
                        //loadMore();
                        request_Scenic();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    public void request_Scenic() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);

        //对 发送请求 进行封装
        Call<List<ScenicList>> call = request.getScenicList();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<ScenicList>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<ScenicList>> call, Response<List<ScenicList>> response) {
                // 步骤7：处理返回的数据结果
                Log.e("yanzhen",response.body().get(0).getCoverURL()+"");
                if(page==0){
                    for(int i=0;i<3;i++) {
                        ScenicItem date = new ScenicItem("http://123.207.56.152/vrzjj/"+response.body().get(0).getCoverURL(), response.body().get(0).getScenicName(),response.body().get(0).getScenicPrice(), response.body().get(0).getFans(), response.body().get(0).getAddress(), 4.9,response.body().get(0).getId());//名字，价格，月售额，地址，评价
                        scenicDate.add(date);
                        ScenicItem date1 = new ScenicItem("http://123.207.56.152/vrzjj/"+response.body().get(1).getCoverURL(), response.body().get(1).getScenicName(),response.body().get(1).getScenicPrice(), response.body().get(1).getFans(), response.body().get(1).getAddress(), 4.9,response.body().get(1).getId());//名字，价格，月售额，地址，评价
                        scenicDate.add(date1);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    ScenicItem date = new ScenicItem("http://123.207.56.152/vrzjj/"+response.body().get(0).getCoverURL(), response.body().get(0).getScenicName(),response.body().get(0).getScenicPrice(), response.body().get(0).getFans(), response.body().get(0).getAddress(), 4.9,response.body().get(0).getId());//名字，价格，月售额，地址，评价
                    scenicDate.add(date);
                    adapter.notifyDataSetChanged();
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<List<ScenicList>> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen","连接失败");
            }
        });
    }
}
