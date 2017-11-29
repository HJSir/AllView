package com.example.lenovo.mainui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.RetrofitService.BedDetaials;
import com.example.lenovo.mainui.RetrofitService.BedId;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.RetrofitService.HotelDetails;
import com.example.lenovo.mainui.RetrofitService.Id;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyActivity extends Activity implements View.OnClickListener {
    private ViewPager viewPager;
    private LinearLayout point_group;
    private TextView image_desc;
    private String phoneNumber = "123456";
    SimpleDraweeView simpleDraweeView1;
    SimpleDraweeView simpleDraweeView2;
    SimpleDraweeView simpleDraweeView3;
    TextView name ;
    TextView price;
    TextView number ;
    TextView storename ;
    TextView storeadress ;
    TextView storephone ;
    TextView storeintroduce;
    private String bedId;
    private ArrayList<SimpleDraweeView> imageList;
    // 上一个页面的位置
    protected int lastPosition = 0;
    private SwipeRefreshLayout swipeRefresh;
    // 判断是否自动滚动viewPager
    private boolean isRunning = true;
    private String[] imageUri=new String[3];
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Log.i("Tag", "我接收到信息了");
            if (msg.arg1 == 1) {
                Log.i("Tag", "我进来执行");
                simpleDraweeView1.setImageURI(Uri.parse("http://123.207.56.152/vrzjj/" + imageUri[0]));
                simpleDraweeView2.setImageURI(Uri.parse("http://123.207.56.152/vrzjj/" + imageUri[1]));
                simpleDraweeView3.setImageURI(Uri.parse("http://123.207.56.152/vrzjj/" + imageUri[2]));
                Log.i("Tag", "List size=" + imageList.size());
            } else {
                // 执行滑动到下一个页面
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                if (isRunning) {
                    simpleDraweeView1.postInvalidate();
                    simpleDraweeView2.postInvalidate();
                    Log.i("Tag", "4");
                    // 在发一个handler延时
                    handler.sendEmptyMessageDelayed(0, 5000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Fresco.initialize(this);
        Intent intent=getIntent();
        bedId=intent.getStringExtra("bedid");
        Log.e("yanzhen","1");
        Log.e("yanzhen",bedId);
        simpleDraweeView1 = new SimpleDraweeView(this);
        simpleDraweeView2 = new SimpleDraweeView(this);
        simpleDraweeView3 = new SimpleDraweeView(this);
        Button button_back = (Button) findViewById(R.id.back_button_buy);
        View button_service = findViewById(R.id.layout_service);
        View button_buy = findViewById(R.id.layout_buyproduct);
        button_back.setOnClickListener(this);
        button_service.setOnClickListener(this);
        button_buy.setOnClickListener(this);
        name = (TextView) findViewById(R.id.activity_name);
        price = (TextView) findViewById(R.id.activity_price);
        number = (TextView) findViewById(R.id.activity_number);
        storename = (TextView) findViewById(R.id.store_name1);
        storeadress = (TextView) findViewById(R.id.store_adress);
        storephone = (TextView) findViewById(R.id.store_phone);
        storeintroduce = (TextView) findViewById(R.id.store_introduce);

        viewPager = (ViewPager) findViewById(R.id.viewPager2);
        point_group = (LinearLayout) findViewById(R.id.point_group2);
        // 初始化图片资源
        imageList = new ArrayList<SimpleDraweeView>();
        insertImage();
        playImage();
        Log.e("yanzhen","2");
        request_BedDetails();
        request_HotelDetails();
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button_buy) {
            finish();

        } else if (i == R.id.layout_service) {
            Intent intent_call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            intent_call.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent_call);

        } else if (i == R.id.layout_buyproduct) {
            Intent intent = new Intent(this, FirmOrderActivity.class);
            intent.putExtra("bedfirm", bedId);
            startActivity(intent);

        } else {
        }
    }

    private class MyPageAdapter extends PagerAdapter {
        // 需要实现以下四个方法

        @Override
        public int getCount() {
            // 获得页面的总数
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // 判断view和Object对应是否有关联关系
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
            // position: viewpager上的位置
            // 给container添加内容
            container.addView(imageList.get(position % imageList.size()));

            return imageList.get(position % imageList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁对应位置上的Object
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
            object = null;
        }

    }

    private void insertImage() {
        imageList.add(simpleDraweeView1);
        imageList.add(simpleDraweeView2);
        imageList.add(simpleDraweeView3);
    }

    private void playImage() {
        viewPager.setAdapter(new MyPageAdapter());
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                position %= imageList.size();
                lastPosition = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 页面正在滑动时间回调
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 当pageView 状态发生改变的时候，回调
            }
        });
        handler.sendEmptyMessageDelayed(0, 0);
    }

    public void request_BedDetails() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        final GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        String hotelID=String.valueOf(bedId.charAt(0));
        String roomID=String.valueOf(bedId.charAt(1));
        BedId id_nu=new BedId(Integer.valueOf(hotelID),Integer.valueOf(roomID));
        Call<BedDetaials> call = request.getBed(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<BedDetaials>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BedDetaials> call, Response<BedDetaials> response) {
                // 步骤7：处理返回的数据结果
                imageUri[0]=response.body().getImage1URL();
                imageUri[1]=response.body().getImage2URL();
                imageUri[2]=response.body().getImage3URL();
                name.setText(response.body().getRoomName());
                storeintroduce.setText(response.body().getRoomIntroduce());
                price.setText("¥"+response.body().getPrice()+"");
                number.setText(response.body().getStock()+"") ;
                storephone.setText(response.body().getPhoneNum()) ;
                phoneNumber=response.body().getPhoneNum();
                Message msg= new Message();
                msg.arg1=1;
                handler.sendMessage(msg);

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BedDetaials> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }

    public void request_HotelDetails() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 步骤5:创建 网络请求接口 的实例
        final GetService request = retrofit.create(GetService.class);
        //对 发送请求 进行封装
        String hotelID=String.valueOf(bedId.charAt(0));
        Id id_nu=new Id(Integer.valueOf(hotelID));
        Call<HotelDetails> call = request.getHotelDetails(id_nu);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<HotelDetails>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<HotelDetails> call, Response<HotelDetails> response) {
                // 步骤7：处理返回的数据结果
                storename.setText(response.body().getHotelName()) ;
                storeadress.setText(response.body().getAddress()) ;


            }

            //请求失败时回调
            @Override
            public void onFailure(Call<HotelDetails> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen", "连接失败");
            }
        });
    }
}
