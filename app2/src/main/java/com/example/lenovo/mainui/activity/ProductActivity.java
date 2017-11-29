package com.example.lenovo.mainui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.RetrofitService.BedList;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.RetrofitService.HotelId;
import com.example.lenovo.mainui.adapter.ProductAdapter;
import com.example.lenovo.mainui.adapter.ProductItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends Activity implements View.OnClickListener{
    private List<ProductItem> scenicDate=new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProductAdapter adapter;
    private String hotelID;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Log.e("yanzhen","1");
        intent=getIntent();
        hotelID = intent.getStringExtra("hotelId");
        Log.e("yanzhen",hotelID);
        Button buttonback=(Button) findViewById(R.id.back_button_product);
        buttonback.setOnClickListener(this);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_product);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ProductAdapter(this,scenicDate);
        recyclerView.setAdapter(adapter);
        Log.e("yanzhen","2");

       // loadMore();
        request_HotelDetails();
    }
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button_product) {
            finish();

        } else {
        }
    }

    public void request_HotelDetails() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        HotelId id_nu=new HotelId(Integer.parseInt(hotelID));
        Call<List<BedList>> call = request.getBedDetails(id_nu);
            //步骤6:发送网络请求(异步)
            call.enqueue(new Callback<List<BedList>>() {
                //请求成功时回调
                @Override
                public void onResponse(Call<List<BedList>> call, Response<List<BedList>> response) {
                    // 步骤7：处理返回的数据结果
                    if(response.body()==null){
                        Log.e("yanzhen","我进来了");
                    }
                    ProductItem date = new ProductItem("http://123.207.56.152/vrzjj/" + response.body().get(0).getCoverURL(), response.body().get(0).getRoomName(), response.body().get(0).getPrice(),response.body().get(0).getHotelID(),response.body().get(0).getRoomID());
                    scenicDate.add(date);
                    ProductItem date1 = new ProductItem("http://123.207.56.152/vrzjj/" + response.body().get(1).getCoverURL(), response.body().get(1).getRoomName(), response.body().get(1).getPrice(),response.body().get(1).getHotelID(),response.body().get(1).getRoomID());
                    scenicDate.add(date1);
                    adapter.notifyDataSetChanged();
                }

                //请求失败时回调
                @Override
                public void onFailure(Call<List<BedList>> call, Throwable throwable) {
                    //System.out.println("连接失败");
                    Log.e("yanzhen", "连接失败");
                }
            });
    }
}
