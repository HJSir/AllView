package com.example.jian.allview.mine.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseFragment;
import com.example.jian.allview.base.BasePresenter;
import com.example.jian.allview.mine.bean.UserInfomation;
import com.example.jian.allview.mine.presenter.DealUserInfoPresenter;
import com.example.jian.allview.mine.ui.activity.PhotoChooseActivity;
import com.example.jian.allview.mine.ui.view.IUserDealView;
import com.facebook.drawee.view.SimpleDraweeView;


public class EditUserInfoFragment extends BaseFragment<DealUserInfoPresenter> implements IUserDealView,View.OnClickListener {
      private EditText ed_diqu;
    private SimpleDraweeView user_image_edit;
    private Button bt_save;
//    private EditText ed_sex;
    private EditText ed_name;

    @Override
    public void DealSuccess(String s) {
        if(s.equals("1")){
        Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();


            SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
              editor.putString("name",ed_name.getText().toString());
            editor.putString("area",ed_diqu.getText().toString());
            editor.commit();
            getFragmentManager().popBackStack();
        } else {
            Toast.makeText(getActivity(),"失败",Toast.LENGTH_SHORT);
        }
    }

    public interface setEditUserInfoFragmentClick{
        void setEditUserInfoFragmentOnClick(View v);

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        ed_diqu= (EditText) rootView.findViewById(R.id.mine_userimfo_editdiqu);
        bt_save= (Button) rootView.findViewById(R.id.mine_userinfo_save);
        ed_name= (EditText) rootView.findViewById(R.id.mine_userimfo_editname);
//        ed_sex= (EditText) rootView.findViewById(R.id.mine_userimfo_editsex);
        user_image_edit= (SimpleDraweeView) rootView.findViewById(R.id.user_image_edit);

    }

    @Override
    public void initData() {
        super.initData();
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        ed_name.setText(preferences.getString("name",null));
        ed_diqu.setText(preferences.getString("area",null));
//        ed_sex.setText(preferences.getString("sex",null));
        user_image_edit.setImageURI(preferences.getString("image",null));
    }

    @Override
    public void initListener() {
        super.initListener();
        bt_save.setOnClickListener(this);
        user_image_edit.setOnClickListener(this);
//        ed_sex.setOnClickListener(this);
    }

    @Override
    protected DealUserInfoPresenter createPresenter() {
        return new DealUserInfoPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_edit_user_info;
    }

    @Override
    protected void loadData() {

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.mine_userinfo_save:
                SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//                ed_name.setText(preferences.getString("name",null));
                UserInfomation userInfomation = new UserInfomation();
                userInfomation.setId(Integer.parseInt(preferences.getString("id",null)));
                userInfomation.setArea(ed_diqu.getText().toString());
                userInfomation.setUserName(ed_name.getText().toString());
                mPresenter.dealinfo(userInfomation);
                break;


//            case R.id.mine_userimfo_editsex:
//                final String[] type2 = {"��", "Ů"};
//                Alertdialog("ѡ����Ů",type2,0);
//
//
//                break;
            case R.id.user_image_edit:
                final String[] type1 = {"相册", "相机"};
                Alertdialog("选择",type1,1);
                break;


        }

    }


    public int Alertdialog(String title, final String[] type, final int tag)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle(title);
//        //    指定下拉列表的显示数�?
//        final String[] cities = {"广州", "上海", "北京", "香港", "澳门"};
        //    设置�?个下拉的列表选择�?
        builder.setItems(type, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
//                if(tag==0){
//
//                    if(which==0)
//                        ed_sex.setText("��");
//                    else
//                        ed_sex.setText("Ů");
//                }
                if(tag==1){
                    Intent intent = new Intent(getActivity(),PhotoChooseActivity.class);
                    intent.putExtra("mEmail", which);
                    startActivity(intent);

                }
                // Toast.makeText(getActivity(), "选择的为�?" +tag[0]+ type[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        //从Share中取出地�?并显示�??
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String url=preferences.getString("Surl",null);
        if (url!=null){
//            Toast.makeText(getActivity(), "图片地址�?"+preferences.getString("Surl",null), Toast.LENGTH_SHORT).show();
            user_image_edit.setImageURI(url);
//            Toast.makeText(getActivity(),"目前使用在线数据库无法存储图片，�?以无法修改头像！",Toast.LENGTH_SHORT).show();
        }

    }
}
