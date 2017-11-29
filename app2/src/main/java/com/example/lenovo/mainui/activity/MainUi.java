package com.example.lenovo.mainui.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.mainui.R;
import com.example.lenovo.mainui.RetrofitService.GetService;
import com.example.lenovo.mainui.RetrofitService.HotArticle;
import com.example.lenovo.mainui.RetrofitService.HotViewpage;
import com.example.lenovo.mainui.adapter.RecyclerAdapterHot;
import com.example.lenovo.mainui.adapter.RecyclerViewHot;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainUi extends Fragment {
    private List<RecyclerViewHot> secondList=new ArrayList<>();
    private ViewPager viewPager;
    private LinearLayout point_group;
    private TextView image_desc;
    // 图片资源id
   // LinearLayoutManager linearLayoutManager;
    LinearLayoutManager layoutManager2;
    RecyclerView recyclerViewHot;
    int lastVisibleItem;
    int page = 0;
    RecyclerAdapterHot adapter2;
    SimpleDraweeView simpleDraweeView1;
    SimpleDraweeView simpleDraweeView2;
    SimpleDraweeView simpleDraweeView3;
    private ArrayList<SimpleDraweeView> imageList;
    // 上一个页面的位置
    protected int lastPosition = 0;
    private SwipeRefreshLayout swipeRefresh;
    // 判断是否自动滚动viewPager
    private boolean isRunning = true;
    View rootView;
    private String[] imageUri=new String[3];
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.arg1==1){
                simpleDraweeView1.setImageURI(Uri.parse("http://123.207.56.152/vrzjj/"+imageUri[0]));
                simpleDraweeView2.setImageURI(Uri.parse("http://123.207.56.152/vrzjj/"+imageUri[1]));
                simpleDraweeView3.setImageURI(Uri.parse("http://123.207.56.152/vrzjj/"+imageUri[2]));
            }else{
            // 执行滑动到下一个页面
//                Log.e("yanzhen",viewPager.getCurrentItem()+"w");
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                simpleDraweeView1.postInvalidate();
                simpleDraweeView2.postInvalidate();
                // 在发一个handler延时
                handler.sendEmptyMessageDelayed(0, 3000);
            }}
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(rootView==null){

            rootView=inflater.inflate(R.layout.activity_ui,container,false);




        }else{
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        Fresco.initialize(getActivity());
         simpleDraweeView1=new SimpleDraweeView(getActivity());
         simpleDraweeView2=new SimpleDraweeView(getActivity());
         simpleDraweeView3=new SimpleDraweeView(getActivity());
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        point_group = (LinearLayout) rootView.findViewById(R.id.point_group);
        // 初始化图片资源
        imageList = new ArrayList<SimpleDraweeView>();
        request_HotImageView();
        insertImage();
        playImage();
        Button button_scenic=(Button)rootView.findViewById(R.id.button_scenic);
        Button button_hotel=(Button)rootView.findViewById(R.id.button_hotel);
        Button button_custom=(Button) rootView.findViewById(R.id.button_custom);
        button_scenic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getActivity(),ScenicActivity.class);
                startActivity(intent);
            }
        });
        button_hotel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getActivity(),HotelActivity.class);
                startActivity(intent);
            }
        });
        button_custom.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getActivity(),CustomActivity.class);
                startActivity(intent);
            }
        });
        swipeRefresh=(SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                refreshdate();
            }
        });
        recyclerViewHot=(RecyclerView) rootView.findViewById(R.id.recycler_hot);//****


        layoutManager2=new LinearLayoutManager(getActivity());
        recyclerViewHot.setLayoutManager(layoutManager2);


        Display display = getActivity().getWindowManager().getDefaultDisplay();


        adapter2=new RecyclerAdapterHot(getActivity(),secondList,display.getWidth());
        recyclerViewHot.setAdapter(adapter2);
        recyclerViewHot.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //到达底部了
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter2.getItemCount()) {
                    //到达底部之后如果footView的状态不是正在加载的状态,就将 他切换成正在加载的状态
                    if (page >1) {
                        adapter2.changeState(2);
                    } else {
                        adapter2.changeState(1);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ++page;
                               // loadMore();
                                request_HotArticle();
                            }
                        }, 500);
                    }


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager2.findLastVisibleItemPosition();
            }
        });
        request_HotArticle();
        return rootView;
    }

    @Override
    public void onDestroy() {
// 停止滚动
        isRunning = false;
        super.onDestroy();
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
    private void refreshdate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        secondList.clear();
                        page=0;
                        request_HotArticle();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    public void request_HotImageView() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);

        //对 发送请求 进行封装
        Call<HotViewpage> call = request.getHotViewpage();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<HotViewpage>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<HotViewpage> call, Response<HotViewpage> response) {
                // 步骤7：处理返回的数据结果
                imageUri[0]=response.body().getImage1URL();
                imageUri[1]=response.body().getImage2URL();
                imageUri[2]=response.body().getImage3URL();
                Message msg= new Message();
                  msg.arg1=1;
                handler.sendMessage(msg);
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<HotViewpage> call, Throwable throwable) {
                Log.e("yanzhen","连接失败");
            }
        });
    }

    public void insertImage(){
        imageList.add(simpleDraweeView1);
        imageList.add(simpleDraweeView2);
        imageList.add(simpleDraweeView3);

    }

    public void playImage(){

        viewPager.setAdapter(new MyPageAdapter());
        // 设置当前viewPager的位置
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 页面切换后调用， position是新的页面位置

                // 实现无限制循环播放
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
        /**
         * 自动循环： 1.定时器：Timer 2.开子线程：while true循环 3.ClockManger
         * 4.用Handler发送延时信息，实现循环，最简单最方便
         *
         */
        handler.sendEmptyMessageDelayed(0, 0);
    }

    public void request_HotArticle() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.207.56.152/vrzjj/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetService request = retrofit.create(GetService.class);

        //对 发送请求 进行封装
        Call<List<HotArticle>> call = request.getHotArticle();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<HotArticle>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<HotArticle>> call, Response<List<HotArticle>> response) {
                // 步骤7：处理返回的数据结果
                if(page==0){
                    for(int i=0;i<2;i++) {
                        RecyclerViewHot date = new RecyclerViewHot("http://123.207.56.152/vrzjj/"+response.body().get(0).getCoverURL(), response.body().get(0).getTitle(), response.body().get(0).getIntroduction(), null, null, null);
                        secondList.add(date);
                    }
                    adapter2.notifyDataSetChanged();
                }else {
                    RecyclerViewHot date = new RecyclerViewHot("http://123.207.56.152/vrzjj/"+response.body().get(1).getCoverURL(), response.body().get(1).getTitle(), response.body().get(1).getIntroduction(), null, null, null);
                    secondList.add(date);
                    adapter2.notifyDataSetChanged();
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<List<HotArticle>> call, Throwable throwable) {
                //System.out.println("连接失败");
                Log.e("yanzhen","连接失败");
            }
        });
    }

}

