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
import com.example.lenovo.mainui.RetrofitService.HotelList;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.adapter.HotelAdapter;
import com.example.lenovo.mainui.adapter.HotelItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelActivity extends Activity implements View.OnClickListener{
    private List<HotelItem> scenicDate=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private HotelAdapter adapter;
    int lastVisibleItem;
    int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        Button buttonback=(Button) findViewById(R.id.back_button_hotel);
        buttonback.setOnClickListener(this);
        //RecyclerView组件
        //刷新
        swipeRefresh=(SwipeRefreshLayout) findViewById(R.id.refresh_hotel);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                refreshdate();
            }
        });

        recyclerView=(RecyclerView) findViewById(R.id.recycler_hotel);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new HotelAdapter(this,scenicDate);
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
                                request_Hotel();
                            }
                        }, 500);
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
        request_Hotel();
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button_hotel) {
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
                        request_Hotel();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    public void request_Hotel() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);

        //对 发送请求 进行封装
        Call<List<HotelList>> call = request.getHotel();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<HotelList>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<HotelList>> call, Response<List<HotelList>> response) {
                // 步骤7：处理返回的数据结果
                // Log.e("yanzhen",response.body().get(0).getCoverURL()+"");
                if(page==0){
                    for(int i=0;i<2;i++) {
                        HotelItem date1 = new HotelItem("http://123.207.56.152/vrzjj/"+response.body().get(0).getCoverURL(), response.body().get(0).getHotelName(), response.body().get(0).getHotelPrice(), response.body().get(0).getFans(), response.body().get(0).getAddress(), 4.9,response.body().get(0).getId());
                        scenicDate.add(date1);
                        HotelItem date2 = new HotelItem("http://123.207.56.152/vrzjj/"+response.body().get(1).getCoverURL(), response.body().get(1).getHotelName(), response.body().get(1).getHotelPrice(), response.body().get(1).getFans(), response.body().get(1).getAddress(), 4.9,response.body().get(1).getId());
                        scenicDate.add(date2);
                        HotelItem date3 = new HotelItem("http://123.207.56.152/vrzjj/"+response.body().get(2).getCoverURL(), response.body().get(2).getHotelName(), response.body().get(2).getHotelPrice(), response.body().get(2).getFans(), response.body().get(2).getAddress(), 4.9,response.body().get(2).getId());
                        scenicDate.add(date3);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    HotelItem date1 = new HotelItem("http://123.207.56.152/vrzjj/"+response.body().get(0).getCoverURL(), response.body().get(0).getHotelName(), response.body().get(0).getHotelPrice(), response.body().get(0).getFans(), response.body().get(0).getAddress(), 4.9,response.body().get(0).getId());
                    scenicDate.add(date1);
                    adapter.notifyDataSetChanged();
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<List<HotelList>> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen","连接失败");
            }
        });
    }

}
