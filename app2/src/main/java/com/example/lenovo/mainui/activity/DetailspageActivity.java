package com.example.lenovo.mainui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.RetrofitService.HotelDetails;
import com.example.lenovo.mainui.RetrofitService.Id;
import com.example.lenovo.mainui.RetrofitService.ScenicDetails;
import com.example.lenovo.mainui.adapter.FixedPopupWindow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailspageActivity extends Activity implements View.OnClickListener {
    FixedPopupWindow mPopWindow;
    WebView webview;
    private View button1;
    private View button2;
    private View button3;
    private View button4;
    private Intent intent;
    private String id;
    private String phoneNumber = "12345";
    TextView text_name2;
    TextView text_introduce;
    TextView text_name1;
    private int dingstinguish=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspage);
        Button buttonback = (Button) findViewById(R.id.back_button_detailspage);
        buttonback.setOnClickListener(this);
        button1 = findViewById(R.id.layout_store);
        button2 = findViewById(R.id.layout_product);
        button3 = findViewById(R.id.layout_map);
        button4 = findViewById(R.id.layout_custom);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        text_name1 = (TextView) findViewById(R.id.detailspage_name);
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setSupportZoom(false);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        intent = getIntent();
        if(intent.getStringExtra("hotelname")!=null){
            id = intent.getStringExtra("hotelname");
            request_HotelDetails();
            dingstinguish=1;
        }
        if(intent.getStringExtra("scenicname")!=null){
            id= intent.getStringExtra("scenicname");
            request_ScenicDetails();
            dingstinguish=2;
        }


    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button_detailspage) {
            finish();

        } else if (i == R.id.layout_store) {
            showPopupWindow();

        } else if (i == R.id.layout_product) {
            if (dingstinguish == 1 && Integer.parseInt(id) <= 1) {
                Intent intent_product = new Intent(DetailspageActivity.this, ProductActivity.class);
                intent_product.putExtra("hotelId", id + "");
                startActivity(intent_product);
            } else if (Integer.parseInt(id) > 1) {
                Toast.makeText(this, "无产品", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "无产品", Toast.LENGTH_SHORT).show();
            }

        } else if (i == R.id.layout_map) {
            Log.e("yanzhen", "hello");
            Intent intent_map = new Intent(DetailspageActivity.this, BaiduActivity.class);
            startActivity(intent_map);

        } else if (i == R.id.layout_custom) {
            Intent intent_call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            intent_call.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent_call);

        } else if (i == R.id.phone) {
            Intent intent_phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            intent_phone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent_phone);

        } else if (i == R.id.close) {
            mPopWindow.dismiss();

        } else if (i == R.id.view_folk) {
            mPopWindow.dismiss();

        } else if (i == R.id.back_button_store) {
            finish();

        } else {
        }
    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(DetailspageActivity.this).inflate(R.layout.store_layout, null);
        mPopWindow = new FixedPopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        Button tv1 = (Button) contentView.findViewById(R.id.view_folk);
        Button tv2 = (Button) contentView.findViewById(R.id.phone);
        Button tv3 = (Button) contentView.findViewById(R.id.close);
        Button tv4 = (Button) contentView.findViewById(R.id.back_button_store);
        text_name2 = (TextView) contentView.findViewById(R.id.store_name);
        text_introduce = (TextView) contentView.findViewById(R.id.introduce);
        if(dingstinguish==1) {
            request_HotelDetails();
        }else {
            request_ScenicDetails();
        }
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(DetailspageActivity.this).inflate(R.layout.activity_detailspage, null).findViewById(R.id.layout_detail);
        View rootview2 = LayoutInflater.from(DetailspageActivity.this).inflate(R.layout.activity_detailspage, null);
        //mPopWindow.showAsDropDown(rootview);
        mPopWindow.showAtLocation(rootview2, Gravity.BOTTOM, 0, 0);

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
        Id id_nu=new Id(Integer.parseInt(id));
        Call<HotelDetails> call = request.getHotelDetails(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<HotelDetails>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<HotelDetails> call, Response<HotelDetails> response) {
                // 步骤7：处理返回的数据结果
                phoneNumber=response.body().getPhoneNum();
                text_name1.setText(response.body().getHotelName());
                webview.loadUrl(response.body().getVRURL());
                if(text_name2!=null&&text_introduce!=null){
                    text_name2.setText(response.body().getHotelName());
                    text_introduce.setText(response.body().getHotelIntroduce());
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<HotelDetails> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }

    public void request_ScenicDetails() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        Id id_nu=new Id(Integer.parseInt(id));
        Call<ScenicDetails> call = request.getScenicDetails(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<ScenicDetails>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ScenicDetails> call, Response<ScenicDetails> response) {
                // 步骤7：处理返回的数据结果
                Log.e("yanzhen","我进来了");

                phoneNumber=response.body().getPhoneNum();
                Log.e("yanzhen",response.body().getPhoneNum());
                text_name1.setText(response.body().getScenicName());
                webview.loadUrl(response.body().getVRURL());
                if(text_name2!=null&&text_introduce!=null){
                    text_name2.setText(response.body().getScenicName());
                    text_introduce.setText(response.body().getScenicIntroduce());
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<ScenicDetails> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }
}
