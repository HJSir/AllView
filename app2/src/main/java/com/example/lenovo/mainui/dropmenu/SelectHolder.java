package com.example.lenovo.mainui.dropmenu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mainui.R;

import java.util.List;

/**
 *
 * 筛选
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class SelectHolder extends BaseWidgetHolder<List<String>> {

    private View mNoRuleView;
    private View mTeacherGenderView;
    private View mCourseTypeView;
    private TextView mSureBtn;

    private View mGenderView;
    private View mTypeView;

    private RadioItemView mGenderNoRuleRIView;
    private RadioItemView mGenderMaleRIView;
    private RadioItemView mGenderFemaleRIView;
    private RadioItemView mTypeNoRuleRIView;
    private RadioItemView mTypeTeacherToHomeRIView;
    private RadioItemView mTypeStudentToSchoolRIView;

    private RadioItemView mGenderRecorder = null;
    private RadioItemView mTypeRecorder = null;

    private TextView mTeacherGenderText;
    private TextView mTypeText;

    private boolean mIsFirstExtendGender = true;
    private boolean mIsFirstExtendType = true;

    private OnSelectedInfoListener mOnSelectedInfoListener = null;

    private String mRetGender = "";
    private String mRetType = "";
    private ImageView mTeacherGenderArrorImage;
    private ImageView mTypeArrorImage;

    public SelectHolder(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.layout_holder_select, null);

        mNoRuleView = view.findViewById(R.id.zhbx);
        mTeacherGenderView = view.findViewById(R.id.jsxb);
        mCourseTypeView = view.findViewById(R.id.skfs);

        //列表标题控件
        mGenderView = view.findViewById(R.id.ll_gender);
        mTypeView = view.findViewById(R.id.ll_type);

        mTeacherGenderText = (TextView) view.findViewById(R.id.tv_jsxb);
        mTypeText = (TextView) view.findViewById(R.id.tv_skfs);

        mTeacherGenderArrorImage = (ImageView) view.findViewById(R.id.img_jsxb);
        mTypeArrorImage = (ImageView) view.findViewById(R.id.img_skfs);

        mGenderNoRuleRIView = (RadioItemView) view.findViewById(R.id.riv_gender_norule);
        mGenderMaleRIView = (RadioItemView) view.findViewById(R.id.riv_gender_boy);
        mGenderFemaleRIView = (RadioItemView) view.findViewById(R.id.riv_gender_girl);

        mTypeNoRuleRIView = (RadioItemView) view.findViewById(R.id.riv_type_norule);
        mTypeTeacherToHomeRIView = (RadioItemView) view.findViewById(R.id.riv_type_teacherToHome);
        mTypeStudentToSchoolRIView = (RadioItemView) view.findViewById(R.id.riv_type_studentToSchool);

        mGenderNoRuleRIView.setTitleText("no rule");
        mGenderMaleRIView.setTitleText("male");
        mGenderFemaleRIView.setTitleText("female");
        mTypeNoRuleRIView.setTitleText("no rule");
        mTypeTeacherToHomeRIView.setTitleText("rule1");
        mTypeStudentToSchoolRIView.setTitleText("rule2");

        mSureBtn = (TextView) view.findViewById(R.id.tv_button);

        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnSelectedInfoListener != null){
                    mOnSelectedInfoListener.OnselectedInfo(mRetGender, mRetType);
                }
            }
        });

        initViewListners();
        initGenderListener();
        initTypeListener();

        //默认不限
        mGenderNoRuleRIView.setSelected(true);
        mTypeNoRuleRIView.setSelected(true);

        return view;
    }

    private void initViewListners(){

        mNoRuleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mGenderRecorder != null){
                    mGenderRecorder.setSelected(false);
                }

                if(mTypeRecorder != null){
                    mTypeRecorder.setSelected(false);
                }

                mGenderView.setVisibility(View.GONE);
                mTypeView.setVisibility(View.GONE);

                //默认不限
                mGenderNoRuleRIView.setSelected(true);
                mTypeNoRuleRIView.setSelected(true);

                mIsFirstExtendGender = true;
                mIsFirstExtendType = true;

                mRetGender = "";
                mRetType = "";
            }
        });

        mTeacherGenderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (View.GONE == mGenderView.getVisibility()) {
                    mGenderView.setVisibility(View.VISIBLE);
                    mTeacherGenderText.setTextColor(mContext.getResources().getColor(R.color.blue));
                    mTeacherGenderArrorImage.setImageResource(R.mipmap.ic_up_blue);

                } else {
                    mGenderView.setVisibility(View.GONE);
                    mTeacherGenderText.setTextColor(mContext.getResources().getColor(R.color.text_color_gey));
                    mTeacherGenderArrorImage.setImageResource(R.mipmap.ic_down);
                }

                mTypeView.setVisibility(View.GONE);
                mTypeText.setTextColor(mContext.getResources().getColor(R.color.text_color_gey));
                mTypeArrorImage.setImageResource(R.mipmap.ic_down);
            }
        });

        mCourseTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (View.GONE == mTypeView.getVisibility()) {
                    mTypeView.setVisibility(View.VISIBLE);

                    mTypeText.setTextColor(mContext.getResources().getColor(R.color.blue));
                    mTypeArrorImage.setImageResource(R.mipmap.ic_up_blue);

                } else {
                    mTypeView.setVisibility(View.GONE);
                    mTypeText.setTextColor(mContext.getResources().getColor(R.color.text_color_gey));
                    mTypeArrorImage.setImageResource(R.mipmap.ic_down);
                }
                mGenderView.setVisibility(View.GONE);
                mTeacherGenderText.setTextColor(mContext.getResources().getColor(R.color.text_color_gey));
                mTeacherGenderArrorImage.setImageResource(R.mipmap.ic_down);
            }
        });
    }

    public String getRetGender(){
        return mRetGender;
    }

    public String getRetClassType(){
        return mRetType;
    }

    private void initGenderListener(){
        mGenderNoRuleRIView.setOnStatusChangedListener(new RadioItemView.OnStatusChangedListener() {
            @Override
            public void onStatusChange(boolean bool, String text) {
                clearGenderInfo(mGenderNoRuleRIView, text);
                mRetGender = "";
            }
        });
        mGenderMaleRIView.setOnStatusChangedListener(new RadioItemView.OnStatusChangedListener() {
            @Override
            public void onStatusChange(boolean bool, String text) {
                clearGenderInfo(mGenderMaleRIView, text);
                mRetGender = "1";
            }
        });
        mGenderFemaleRIView.setOnStatusChangedListener(new RadioItemView.OnStatusChangedListener() {
            @Override
            public void onStatusChange(boolean bool, String text) {
                clearGenderInfo(mGenderFemaleRIView, text);
                mRetGender = "0";
            }
        });
    }

    private void clearGenderInfo(RadioItemView radioItemView, String text){

        if(mIsFirstExtendGender){
            mIsFirstExtendGender = false;
            mGenderRecorder = mGenderNoRuleRIView;
        }

        if(radioItemView != mGenderRecorder && mGenderRecorder != null){
            mGenderRecorder.setSelected(false);
        }
        mGenderRecorder = radioItemView;
        mGenderView.setVisibility(View.GONE);
        mTeacherGenderText.setText(text);

        mTeacherGenderText.setTextColor(mContext.getResources().getColor(R.color.text_color_gey));
        mTeacherGenderArrorImage.setImageResource(R.mipmap.ic_down);
    }

    private void initTypeListener(){
        mTypeNoRuleRIView.setOnStatusChangedListener(new RadioItemView.OnStatusChangedListener() {
            @Override
            public void onStatusChange(boolean bool, String text) {
                clearTypeInfo(mTypeNoRuleRIView, text);
                mRetType = "";
            }
        });
        mTypeTeacherToHomeRIView.setOnStatusChangedListener(new RadioItemView.OnStatusChangedListener() {
            @Override
            public void onStatusChange(boolean bool, String text) {
                clearTypeInfo(mTypeTeacherToHomeRIView, text);
                mRetType = "1";     //教师到家
            }
        });
        mTypeStudentToSchoolRIView.setOnStatusChangedListener(new RadioItemView.OnStatusChangedListener() {
            @Override
            public void onStatusChange(boolean bool, String text) {
                clearTypeInfo(mTypeStudentToSchoolRIView, text);
                mRetType = "2";    //校区上课
            }
        });
    }

    private void clearTypeInfo(RadioItemView radioItemView, String text){

        if(mIsFirstExtendType){
            mIsFirstExtendType = false;
            mTypeRecorder = mTypeNoRuleRIView;
        }

        if(radioItemView != mTypeRecorder && mTypeRecorder != null){
            mTypeRecorder.setSelected(false);
        }
        mTypeRecorder = radioItemView;
        mTypeView.setVisibility(View.GONE);
        mTypeText.setText(text);

        mTypeText.setTextColor(mContext.getResources().getColor(R.color.text_color_gey));
        mTypeArrorImage.setImageResource(R.mipmap.ic_down);
    }

    @Override
    public void refreshView(List<String> data) {
        clearTypeInfo(mTypeNoRuleRIView, "no rule");
        mRetType = "";
        clearGenderInfo(mGenderNoRuleRIView, "no rule");
        mRetGender = "";
    }

    public void setOnSelectedInfoListener(OnSelectedInfoListener onSelectedInfoListener){
        this.mOnSelectedInfoListener = onSelectedInfoListener;
    }

    public interface OnSelectedInfoListener{
        void OnselectedInfo(String gender, String type);
    }
}
