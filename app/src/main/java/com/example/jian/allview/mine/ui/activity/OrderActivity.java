package com.example.jian.allview.mine.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.ui.fragment.OrderAfterFragment;
import com.example.jian.allview.mine.ui.fragment.OrderDoFragment;
import com.example.jian.allview.mine.ui.fragment.OrderFinishFragment;
import com.example.jian.allview.mine.ui.fragment.OrderWaitFragment;


public class OrderActivity extends BaseActivity implements View.OnClickListener {
    Button bt_wait;
    Button bt_finish;
    Button bt_after;
    Button bt_do;
    ImageView back_icon;

    @Override
    public void initView() {
        super.initView();
        bt_wait= (Button) findViewById(R.id.order_wait);
        bt_after= (Button) findViewById(R.id.order_after);
        bt_finish= (Button) findViewById(R.id.order_finish);
        bt_do= (Button) findViewById(R.id.order_do);
        back_icon = (ImageView) findViewById(R.id.back_icon);
        Intent intent = getIntent();
        int num = intent.getIntExtra("num",1);
        switch (num){
            case 1:
                changecolor(1);

                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderWaitFragment()).commit();
                break;
            case 2:
                changecolor(2);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderDoFragment()).commit();
                break;
            case 3:
                changecolor(3);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderFinishFragment()).commit();
                break;
            case 4:
                changecolor(4);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderAfterFragment()).commit();
                break;

        }
    }

    @Override
    public void initListener() {
        super.initListener();
        bt_wait.setOnClickListener(this);
        bt_after.setOnClickListener(this);
        bt_finish.setOnClickListener(this);
        bt_do.setOnClickListener(this);
        back_icon.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();



    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            // 后期优化
            case R.id.back_icon:
                finish();
                break;

            case R.id.order_after:
                changecolor(4);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderAfterFragment()).commit();
                break;
            case R.id.order_do:
                changecolor(2);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderDoFragment()).commit();
                break;
            case R.id.order_finish:
                changecolor(3);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderFinishFragment()).commit();
                break;
            case R.id.order_wait:
                changecolor(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_order, new OrderWaitFragment()).commit();
                break;
            default:
                break;
        }

    }
    void changecolor(int num){

        if(num==1){
            bt_wait.setTextColor(getResources().getColor(R.color.qianlan));
            bt_do.setTextColor(getResources().getColor(R.color.black));
            bt_finish.setTextColor(getResources().getColor(R.color.black));
            bt_after.setTextColor(getResources().getColor(R.color.black));
        }
        if(num==2)
        {
            bt_wait.setTextColor(getResources().getColor(R.color.black));
            bt_do.setTextColor(getResources().getColor(R.color.qianlan));
            bt_finish.setTextColor(getResources().getColor(R.color.black));
            bt_after.setTextColor(getResources().getColor(R.color.black));
        }
        if(num==3)
        {
            bt_wait.setTextColor(getResources().getColor(R.color.black));
            bt_do.setTextColor(getResources().getColor(R.color.black));
            bt_finish.setTextColor(getResources().getColor(R.color.qianlan));
            bt_after.setTextColor(getResources().getColor(R.color.black));
        }
        if(num==4)
        {
            bt_wait.setTextColor(getResources().getColor(R.color.black));
            bt_do.setTextColor(getResources().getColor(R.color.black));
            bt_finish.setTextColor(getResources().getColor(R.color.black));
            bt_after.setTextColor(getResources().getColor(R.color.qianlan));
        }


    }
}
