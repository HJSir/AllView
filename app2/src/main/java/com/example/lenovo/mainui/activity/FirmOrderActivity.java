package com.example.lenovo.mainui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.RetrofitService.BedList;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.RetrofitService.HotelDetails;
import com.example.lenovo.mainui.RetrofitService.HotelId;
import com.example.lenovo.mainui.RetrofitService.Id;
import com.example.lenovo.mainui.RetrofitService.UploadOrder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirmOrderActivity extends Activity implements View.OnClickListener{
    private int number=1;
    SimpleDraweeView simpleDraweeView;
    private  Button button_back;
    private Button button_add;
    private Button button_sub;
    private  Button button_sum;
    private TextView text_storename;
    private TextView text_activityname;
    private TextView text_activityprice;
    private TextView text_sumprice;
    private TextView text_sum;
    private String bedid;
    private int price;
   private int hotelID;
    private int roomID;
    private int userID;
    private String orderName;
    private String coverURL;
    private int orderNum;
    private int orderStatus;
    private int ordertype;
    private int total;
    private long payTime;
    private int judgehotel=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_order);
        Intent intnnt=getIntent();
        bedid= getIntent().getStringExtra("bedfirm");
        EditText hint= (EditText) findViewById(R.id.login);
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("可告诉商家你的特殊要求(选填)");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15,true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        hint.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        simpleDraweeView=(SimpleDraweeView)findViewById(R.id.order_image);
        button_back=(Button)findViewById(R.id.back_button_order);
        button_add=(Button)findViewById(R.id.button_add);
        button_sub=(Button)findViewById(R.id.button_sub);
        button_sum=(Button)findViewById(R.id.button_firm);
        button_back.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_sum.setOnClickListener(this);
        text_storename=(TextView)findViewById(R.id.order_name);
        text_activityname=(TextView)findViewById(R.id.bed_name);;
        text_activityprice=(TextView)findViewById(R.id.bed_price);
        text_sumprice=(TextView)findViewById(R.id.sum_price);
        text_sum=(TextView)findViewById(R.id.bed_num);
        request_HotelDetails1();
        request_HotelDetails2();
    }
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button_order) {
            finish();

        } else if (i == R.id.button_sub) {
            number++;
            text_sum.setText(number + "");
            text_sumprice.setText("¥" + price * number + "");

        } else if (i == R.id.button_add) {
            if (number > 1) {
                number--;
                text_sum.setText(number + "");
                text_sumprice.setText("¥" + price * number + "");
            }

        } else if (i == R.id.button_firm) {
            request_HotelDetails3();


        } else {
        }
    }

    public void request_HotelDetails1() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        Id id_nu=new Id(Integer.parseInt(String.valueOf(bedid.charAt(0))));
        Call<HotelDetails> call = request.getHotelDetails(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<HotelDetails>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<HotelDetails> call, Response<HotelDetails> response) {
                // 步骤7：处理返回的数据结果
                text_storename.setText(response.body().getHotelName());

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<HotelDetails> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }

    public void request_HotelDetails2() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        HotelId id_nu=new HotelId(Integer.parseInt(String.valueOf(bedid.charAt(0))));
        Call<List<BedList>> call = request.getBedDetails(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<BedList>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<BedList>> call, Response<List<BedList>> response) {
                // 步骤7：处理返回的数据结果
                text_activityname.setText(response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getRoomName());
                text_activityprice.setText("¥"+response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getPrice()+"");
                simpleDraweeView.setImageURI(Uri.parse(response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getCoverURL()));
                text_sumprice.setText("¥"+response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getPrice()+"");
                price=response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getPrice();
                orderName=response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getRoomName();
                coverURL=response.body().get(Integer.parseInt(String.valueOf(bedid.charAt(1)))-1).getCoverURL();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<BedList>> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }

    public void request_HotelDetails3() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        UploadOrder id_nu=new UploadOrder(Integer.parseInt(String.valueOf(bedid.charAt(0))),Integer.parseInt(String.valueOf(bedid.charAt(1))),1,orderName,coverURL,number,0,1,350,20171128);
        Call<String> call = request.setOrders(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<String>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // 步骤7：处理返回的数据结果
                if(response.body().toString().equals("1")){
                    judgehotel=1;
                }else{
                    judgehotel=0;
                }
                if(judgehotel==1) {
                    Toast.makeText(FirmOrderActivity.this, "预定成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FirmOrderActivity.this,"预定失败", Toast.LENGTH_SHORT).show();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }
}
