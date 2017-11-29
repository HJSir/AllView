package com.example.jian.allview.mine.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;


public class UserDtailFragment extends BaseFragment implements View.OnClickListener {
View currentView;
    private Button bt_edit;
    private TextView tv_age; //暂时未作处理
//    private TextView tv_sex;
    private TextView tv_num;
    private TextView tv_name;
//    private TextView tv_id;
    TextView tv_area;
    private SimpleDraweeView user_image;
//    private userBean user=new userBean();
    public interface setMineEditClick{
        void setMineEditOnClick(View v);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_user_dtail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initListener() {
        super.initListener();
        bt_edit.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void initData() {
        super.initData();

        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        tv_area.setText(preferences.getString("area",null));
        tv_name.setText(preferences.getString("name",null));
        tv_num.setText(preferences.getString("phoneNumber",null));
//        tv_id.setText(preferences.getString("ID",null));
//        tv_sex.setText(preferences.getString("sex",null));
        user_image.setImageURI(preferences.getString("image",null));
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        if(bt_edit==null){
            tv_area= (TextView) rootView.findViewById(R.id.mine_userimfo_diqu);
            bt_edit= (Button) rootView.findViewById(R.id.mine_userinfo_edit);
//            tv_sex= (TextView) rootView.findViewById(R.id.mine_userimfo_readsex);
            tv_name= (TextView) rootView.findViewById(R.id.mine_userimfo_readname);
            tv_num= (TextView) rootView.findViewById(R.id.mine_userimfo_readtel);
//            tv_id= (TextView) rootView.findViewById(R.id.mine_userimfo_readid);
            user_image = (SimpleDraweeView) rootView.findViewById(R.id.user_image);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.mine_userinfo_edit){
            if (getActivity() instanceof UserDtailFragment.setMineEditClick)
            {

                (( UserDtailFragment.setMineEditClick) getActivity()).setMineEditOnClick(v);
            }

        }
    }
}
