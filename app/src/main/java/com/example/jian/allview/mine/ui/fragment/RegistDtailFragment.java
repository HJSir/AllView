package com.example.jian.allview.mine.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.User;
import com.example.jian.allview.mine.bean.userBean;
import com.example.jian.allview.mine.presenter.RegistPresetner;
import com.example.jian.allview.mine.ui.activity.LoginActivity;
import com.example.jian.allview.mine.ui.view.IRegistView;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static cn.smssdk.SMSSDK.getSupportedCountries;
import static cn.smssdk.SMSSDK.getVerificationCode;
import static cn.smssdk.SMSSDK.submitVerificationCode;


public class RegistDtailFragment extends BaseFragment<RegistPresetner> implements IRegistView, View.OnClickListener{
    private Button zc;
    private EditText regID;
    private EditText regPASSWORD;
  private EditText regSEX;
    private EditText regYZM;
    private Button regBTHQYZM;
    private EditText regNAME;
    private View currentView;
    String id,pw;
    EventHandler eventHandler;
    boolean isRegMessage=false;
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {


            if(msg.what>0)
                     regBTHQYZM.setText("Çë"+msg.what+"ÃëºóÖØÊÔ");
            else
                    regBTHQYZM.setText("»ñÈ¡ÑéÖ¤Âë");

            super.handleMessage(msg);
        }
    };
    int tag=0;
    @Override
    protected void loadData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_regist_dtail;
    }

    @Override
    protected RegistPresetner createPresenter() {
        return new RegistPresetner(this);
    }

    @Override
    public void initListener() {
        super.initListener();
//        regSEX.setOnClickListener(this);
        zc.setOnClickListener(this);
        regBTHQYZM.setOnClickListener(this);
        doMessage();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        if(zc==null){
            regID = (EditText) rootView.findViewById(R.id.reg_id);
            regPASSWORD= (EditText) rootView.findViewById(R.id.reg_password);
//            regNAME= (EditText) rootView.findViewById(R.id.reg_name);
//            regSEX= (EditText) rootView.findViewById(R.id.reg_sex);
            regYZM= (EditText) rootView.findViewById(R.id.reg_yzm);
            zc= (Button) rootView.findViewById(R.id.login_detail_zc);
            regBTHQYZM= (Button) rootView.findViewById(R.id.reg_bthqyzm);
           }
        // é€šè¿‡ä»£ç æ³¨å†Œä½ çš„AppKeyå’ŒAppSecret
        MobSDK.init(getActivity(), "1a02cfb2afca0", "64677de12ae65903516875a5e8a7c506");
        // åˆ›å»ºEventHandlerå¯¹è±¡

    }

    @Override
    public void getRegistStatues(String o) {
        if(o.equals("1")){
            Toast.makeText(getActivity(),"×¢²á³É¹¦",Toast.LENGTH_SHORT);
            if (getActivity() instanceof LoginFragment.LoginClickListener)
            {
                ((RegistDtailFragment.RegistJump) getActivity()).onJump();
            }
        }else
            Toast.makeText(getActivity(),"×¢²áÊ§°Ü",Toast.LENGTH_SHORT);
    }

    public interface RegistJump{
    void onJump();
}

    public int Alertdialog(String title, final String[] type)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title);

        builder.setItems(type, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                    //ï¿??
                    if(which==0)
                        regSEX.setText("ÄÐ");
                    else
                        regSEX.setText("Å®");


            }
        });
        builder.show();
        return 0;
    }

public void saveinfo(String id,String pw,String phnum,String name,String sex){
    userBean user = new userBean();
    user.setUser_id(id);
    user.setUser_password(pw);
    user.setUser_phonenumber(phnum);
    user.setUser_sex(sex);
    user.setUser_name(name);



}
    public void doMessage(){
        isRegMessage=false;
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    String msg = throwable.getMessage();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //ï¿½Øµï¿½ï¿½ï¿½ï¿?
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                           // ·µ»Ø³É¹¦¡£ÔÚ´ËÌá½»ÐÅÏ¢µ½·þÎñÆ÷Íê³É×¢²á
                            User user=new User();
                            user.setUserTel(id);
                            user.setUserPass(pw);
                            mPresenter.getRegesit(user);


                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){

                            isRegMessage=true;

                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //ï¿½ï¿½ï¿½ï¿½Ö§ï¿½Ö·ï¿½ï¿½ï¿½ï¿½ï¿½Ö¤ï¿½ï¿½Ä¹ï¿½ï¿½ï¿½ï¿½Ð±ï¿?


                        }
                    }else{
                        ((Throwable)data).printStackTrace();
                    }
                }
            }
        };

        // æ³¨å†Œç›‘å¬ï¿??
        SMSSDK.registerEventHandler(eventHandler);


    }




    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login_detail_zc){

            if(regPASSWORD.getText()!=null&&regYZM.getText()!=null&&isRegMessage){
                id=regID.getText().toString();
                pw=regPASSWORD.getText().toString();
                submitVerificationCode("86", regID.getText().toString(),regYZM.getText().toString());

            }

        }
//        if(v.getId()==R.id.reg_sex){
//            final String[] type2 = {"ÄÐ", "Å®"};
//            Alertdialog("Ñ¡ÔñÄÐÅ®",type2);
//        }
        if(v.getId()==R.id.reg_bthqyzm){
            if(tag==0){

                getVerificationCode("86", regID.getText().toString());
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                        tag=1;

                     for(int i=60;i>=0;i--){
                         Message message =Message.obtain();
                     message.what = i;

                 myHandler.sendMessage(message);
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         Thread.currentThread().interrupt();
                     }
                 }
                     tag=0;

                 }
             }).start();
        }}
    }
}
