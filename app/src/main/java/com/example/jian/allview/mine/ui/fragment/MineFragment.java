package com.example.jian.allview.mine.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.ui.activity.LoginActivity;
import com.example.jian.allview.mine.ui.activity.MineSetActivity;
import com.example.jian.allview.mine.ui.activity.OrderActivity;
import com.example.jian.allview.utils.PercentLinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;


public class MineFragment extends BaseFragment implements View.OnClickListener{

private TextView username;
private SimpleDraweeView user_image;
UserDtailFragment mUserDtailFragment;
    private PercentLinearLayout bt_wait;
    private PercentLinearLayout bt_do;
    private PercentLinearLayout bt_finish;
    private PercentLinearLayout bt_after;
    private PercentLinearLayout bt_setting;
    private LinearLayout bt_allorder;

    public void onResume() {
        super.onResume();
        Login();
    }


    @Override
    public void initListener() {
        super.initListener();
        bt_allorder.setOnClickListener(this);
        bt_setting.setOnClickListener(this);
        bt_wait.setOnClickListener(this);
        bt_do.setOnClickListener(this);
        bt_after.setOnClickListener(this);
        bt_finish.setOnClickListener(this);
        user_image.setOnClickListener(this);

    }

    @Override
    public void initData() {
        super.initData();
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(preferences.getString("image",null)==null){
            user_image.setImageURI("http://img1.imgtn.bdimg.com/it/u=2496618007,3190450108&fm=26&gp=0.jpg");
        }else{
            Log.i("Tag","url:"+preferences.getString("image",null));
            user_image.setImageURI(preferences.getString("image",null));
        }
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        bt_setting = (PercentLinearLayout) rootView.findViewById(R.id.mine_setting);
        bt_wait = (PercentLinearLayout) rootView.findViewById(R.id.mine_wait);
        bt_do = (PercentLinearLayout) rootView.findViewById(R.id.mine_do);
        bt_after = (PercentLinearLayout) rootView.findViewById(R.id.mine_after);
        bt_finish = (PercentLinearLayout) rootView.findViewById(R.id.mine_finish);
        user_image = (SimpleDraweeView) rootView.findViewById(R.id.user_image);
        username= (TextView) rootView.findViewById(R.id.user_name);
        bt_allorder= (LinearLayout) rootView.findViewById(R.id.ll_allorder);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void loadData() {

    }


    public void Login(){

        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(preferences.getString("name",null)==null&&preferences.getString("phoneNumber",null)!=null) {
            username.setText(preferences.getString("phoneNumber",null));
        }else
            username.setText(preferences.getString("name","请登录"));
//        String Sword=preferences.getString("WORD", "0");
//        username.setText(sname);
//        System.out.println("name"+sname+"Sword"+Sword+"ISLogin"+preferences.getBoolean("Stag",false));
        if(preferences.getString("image","http://123.207.56.152/vrzjj/").equals("http://123.207.56.152/vrzjj/")){
            user_image.setImageURI("http://img1.imgtn.bdimg.com/it/u=2496618007,3190450108&fm=26&gp=0.jpg");
        }else{
            Log.i("Tag","url:"+preferences.getString("image",null));
            user_image.setImageURI(preferences.getString("image",null));
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
           case R.id.ll_allorder:
               intent.setClass(getActivity(), OrderActivity.class);
               intent.putExtra("num",4);
               startActivity(intent);
               break;
            case R.id.user_image:
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_setting:
                intent.setClass(getActivity(), MineSetActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_after:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",4);
                startActivity(intent);
                break;
            case R.id.mine_do:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",2);
                startActivity(intent);
                break;
            case R.id.mine_finish:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",3);
                startActivity(intent);
                break;
            case R.id.mine_wait:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",1);
                startActivity(intent);
                break;
        }
    }
}
