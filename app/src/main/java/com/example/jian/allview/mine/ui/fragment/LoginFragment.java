package com.example.jian.allview.mine.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.User;
import com.example.jian.allview.mine.bean.UserInfo;
import com.example.jian.allview.mine.bean.UserInfomation;
import com.example.jian.allview.mine.presenter.LoginPresenter;
import com.example.jian.allview.mine.ui.view.ILoginView;


public class LoginFragment extends BaseFragment<LoginPresenter> implements ILoginView,View.OnClickListener {
    private Button login_bt;
    private TextView login_zc;
    private EditText password;
    private EditText username;
    private RegistDtailFragment RGfragment;
    String phoneNumber,Sword;


    @Override
    public void initView(View rootView) {
        super.initView(rootView);

            username = (EditText) rootView.findViewById(R.id.username);
            password = (EditText) rootView.findViewById(R.id.password);
            login_bt = (Button) rootView.findViewById(R.id.login_bt);
            login_zc = (TextView)rootView.findViewById(R.id.login_zc);

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void initListener() {
        super.initListener();
        login_bt.setOnClickListener(this);
        login_zc.setOnClickListener(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void getStatues(String s) {
        if(!s.equals("0")){
            UserInfo user=new UserInfo();
            user.setUserPass(password.getText().toString());
            user.setId(s);
              mPresenter.getInfo(user);

        }
        else
        {
            Toast.makeText(getActivity(),"账号密码错误",Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void getUserInfo(UserInfomation userInfomation) {

//        tv_name.setText(preferences.getString("name",null));
//        tv_num.setText(preferences.getString("phoneNumber",null));
//        tv_id.setText(preferences.getString("ID",null));
//        tv_sex.setText(preferences.getString("sex",null));
//        user_image.setImageURI(preferences.getString("image",null));
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        boolean Stag = true; //标记登录状态
        editor.putBoolean("Stag", Stag);
        editor.putString("id", String.valueOf(userInfomation.getId()));
        editor.putString("area", userInfomation.getArea());
        editor.putString("phoneNumber", userInfomation.getUserTel());
        editor.putString("image","http://123.207.56.152/vrzjj/"+ userInfomation.getImageURL());
        editor.putString("name", userInfomation.getUserName());
        editor.putString("WORD", userInfomation.getUserPass());
        editor.commit();
        if (getActivity() instanceof LoginClickListener )
        {
            ((LoginClickListener) getActivity()).onLogin();
        }


    }

    public interface LoginClickListener{
        void onLogin();
    }
    public interface RegistClickListener{

        void onRegist();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_bt:


                phoneNumber=username.getText().toString();
                Sword=password.getText().toString();
                User user1 = new User();
                user1.setUserPass(Sword);
                user1.setUserTel(phoneNumber);
                mPresenter.getStatue(user1);


                break;
            case R.id.login_zc:
                if(getActivity() instanceof RegistClickListener )
                {
                    ((RegistClickListener) getActivity()).onRegist();
                }
                break;
        }
    }
}
