package com.example.jian.allview.mine.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.Hotel;
import com.example.jian.allview.mine.bean.HotelAndRoomID;
import com.example.jian.allview.mine.bean.HotelInfo;
import com.example.jian.allview.mine.bean.ID;
import com.example.jian.allview.mine.bean.IDInt;
import com.example.jian.allview.mine.bean.OrderDetail;
import com.example.jian.allview.mine.bean.ScenicSpot;
import com.example.jian.allview.mine.presenter.OrderDetailPresenter;
import com.example.jian.allview.mine.presenter.OrderPresenter;
import com.example.jian.allview.mine.ui.view.IOrderDetailView;
import com.example.jian.allview.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements View.OnClickListener,IOrderDetailView {
TextView tv_typename;
    TextView order_time;
    TextView order_price;
    TextView order_num;
    TextView order_shopname;
    TextView order_adress;
    TextView order_tel;
    ImageView bt_back;
    SimpleDraweeView logo;
    @Override
    protected OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        super.initView();
        bt_back = (ImageView) findViewById(R.id.back_icon2);
        tv_typename= (TextView) findViewById(R.id.tv_order_typename);
        order_time= (TextView) findViewById(R.id.tv_order_time);
        order_price= (TextView) findViewById(R.id.tv_order_typeprice);
        order_num= (TextView) findViewById(R.id.tv_order_num);
        order_shopname= (TextView) findViewById(R.id.tv_order_shopname);
         order_adress=(TextView) findViewById(R.id.tv_order_shopadres);
         order_tel=(TextView) findViewById(R.id.tv_order_shoptel);
        logo=(SimpleDraweeView)findViewById(R.id.img_type);
    }

    @Override
    public void initData() {
        super.initData();
        IDInt id= new IDInt();
        id.setId(1);
        mPresenter.getDetail(id);
    }

    @Override
    public void initListener() {
        super.initListener();
        bt_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_icon2)
            finish();
    }

    @Override
    public void getDetail(OrderDetail orderDetail) {


        tv_typename.setText(orderDetail.getOrderName());
        order_time.setText("时间："+DateUtils.getShortTime(orderDetail.getConsumeTime().getTime()));
        order_num.setText("数量："+orderDetail.getTotal());
        if(orderDetail.getHotelID()==0){
            IDInt id2 = new IDInt();
            id2.setId(1);
mPresenter.getScenicSpot(id2);

        }else{

            HotelAndRoomID id=new HotelAndRoomID();
            id.setHotelID(1);
            id.setRoomID(1);
            mPresenter.getHotelRoomInfo(id);
            IDInt id2 = new IDInt();
             id2.setId(1);
  mPresenter.getHotelInfo(id2);
        }


    }

    @Override
    public void getHotelRoomInfo(HotelInfo info) {
        order_price.setText("价格："+info.getPrice());
        logo.setImageURI("http://123.207.56.152/vrzjj/"+info.getCoverURL());
        order_tel.setText("电话："+info.getPhoneNum());

    }

    @Override
    public void getHotelInfo(Hotel hotel) {
                order_shopname.setText("店称："+hotel.getHotelName());
        order_adress.setText("地址："+hotel.getAddress());


    }

    @Override
    public void getScenicSpotInfo(ScenicSpot spot) {

        order_price.setText("价格："+spot.getScenicPrice());
        logo.setImageURI("http://123.207.56.152/vrzjj/"+spot.getCoverURL());
        order_tel.setText("电话："+spot.getPhoneNum());
        order_shopname.setText("景区名："+spot.getScenicName());
        order_adress.setText("地址："+spot.getAddress());
    }


}
