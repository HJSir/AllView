package com.example.lenovo.mainui.dropmenu;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.mainui.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 搜索菜单栏
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class SelectMenuViewScenic extends LinearLayout{

    private static final int TAB_SUBJECT = 1;
    private static final int TAB_SORT = 2;
    private static final int TAB_SELECT = 3;

    private Context mContext;

    private View mSubjectView;
    private View mSortView;
    private View mSelectView;

    private View mRootView;
    
    private View mPopupWindowView;

    private RelativeLayout mMainContentLayout;
    private View mBackView;

    /** 科目 */
    private SubjectHolder mSubjectHolder;
    /** 综合排序 */
    private SortHolder mSortHolder;
    /** 筛选 */
    private SelectHolder mSelectHolder;

    private OnMenuSelectDataChangedListener mOnMenuSelectDataChangedListener;

    private RelativeLayout mContentLayout;

    private TextView mSubjectText;
    private ImageView mSubjectArrowImage;
    private TextView mSortText;
    private ImageView mSortArrowImage;
    private TextView mSelectText;
    private ImageView mSelectArrowImage;

    private List<String> mGroupList;
    private List<String> mPrimaryList;
    private List<String> mJuniorList;
    private List<String> mHighList;
    private List<List<String>> mSubjectDataList;

    private int mTabRecorder = -1;

    public SelectMenuViewScenic(Context context) {
        super(context);
        this.mContext = context;
        this.mRootView = this;
        init();
    }

    public SelectMenuViewScenic(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mRootView = this;
        init();
    }

    private void init(){

        mGroupList = new ArrayList<String>();
        mGroupList.add("A");
        mGroupList.add("B");
        mGroupList.add("C");
        mPrimaryList = new ArrayList<String>();
        mPrimaryList.add("A1");
        mPrimaryList.add("A2");
        mPrimaryList.add("A3");
        mJuniorList = new ArrayList<String>();
        mJuniorList.add("B1");
        mJuniorList.add("B2");
        mJuniorList.add("B3");
        mJuniorList.add("B4");
        mJuniorList.add("B5");
        mJuniorList.add("B6");
        mJuniorList.add("B7");
        mJuniorList.add("B8");
        mJuniorList.add("B9");
        mHighList = new ArrayList<String>();
        mHighList.add("C1");
        mHighList.add("C2");
        mHighList.add("C3");
        mHighList.add("C4");
        mHighList.add("C5");
        mHighList.add("C6");
        mHighList.add("C7");
        mHighList.add("C8");
        mHighList.add("C9");

        mSubjectDataList = new ArrayList<List<String>>();
        mSubjectDataList.add(mGroupList);
        mSubjectDataList.add(mPrimaryList);
        mSubjectDataList.add(mJuniorList);
        mSubjectDataList.add(mHighList);


        //科目
        mSubjectHolder = new SubjectHolder(mContext);
        mSubjectHolder.refreshData(mSubjectDataList, 0, -1);
        mSubjectHolder.setOnRightListViewItemSelectedListener(new SubjectHolder.OnRightListViewItemSelectedListener() {
            @Override
            public void OnRightListViewItemSelected(int leftIndex, int rightIndex, String text) {

                if(mOnMenuSelectDataChangedListener != null){
                    int grade = leftIndex+1;
                    int subject = getSubjectId(rightIndex);
                    mOnMenuSelectDataChangedListener.onSubjectChanged(grade+"", subject+"");
                }

                dismissPopupWindow();
                //Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                mSubjectText.setText(text);
            }
        });

        //综合排序
        mSortHolder = new SortHolder(mContext);
        mSortHolder.setOnSortInfoSelectedListener(new SortHolder.OnSortInfoSelectedListener() {
            @Override
            public void onSortInfoSelected(String info) {

                if(mOnMenuSelectDataChangedListener != null){
                    mOnMenuSelectDataChangedListener.onSortChanged(info);
                }

                dismissPopupWindow();
                mSortText.setText(getSortString(info));
                //Toast.makeText(UIUtils.getContext(), info, Toast.LENGTH_SHORT).show();
            }
        });

        //筛选
        mSelectHolder = new SelectHolder(mContext);
        mSelectHolder.setOnSelectedInfoListener(new SelectHolder.OnSelectedInfoListener() {
            @Override
            public void OnselectedInfo(String gender, String type) {

                if(mOnMenuSelectDataChangedListener != null){
                    mOnMenuSelectDataChangedListener.onSelectedChanged(gender, type);
                }

                dismissPopupWindow();
                //Toast.makeText(UIUtils.getContext(), gender+" "+type, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getSubjectId(int index){
        return index;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(mContext, R.layout.layout_search_menuscenic, this);

        mSubjectText = (TextView) findViewById(R.id.subject);
        mSubjectArrowImage = (ImageView) findViewById(R.id.img_sub);

        mSortText = (TextView) findViewById(R.id.comprehensive_sorting);
        mSortArrowImage = (ImageView) findViewById(R.id.img_cs);

        mSelectText = (TextView) findViewById(R.id.tv_select);
        mSelectArrowImage = (ImageView) findViewById(R.id.img_sc);

        mContentLayout = (RelativeLayout) findViewById(R.id.rl_content);

        mPopupWindowView = View.inflate(mContext, R.layout.layout_search_menu_content, null);
        mMainContentLayout = (RelativeLayout) mPopupWindowView.findViewById(R.id.rl_main);
        //mBackView = mPopupWindowView.findViewById(R.id.ll_background);

        mSubjectView = findViewById(R.id.ll_subject);
        mSortView = findViewById(R.id.ll_sort);
        mSelectView = findViewById(R.id.ll_select);

        //点击 科目 弹出菜单
        mSubjectView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnMenuSelectDataChangedListener != null){
                    mOnMenuSelectDataChangedListener.onViewClicked(mSubjectView);
                }
                handleClickSubjectView();
            }
        });
        //点击 综合排序 弹出菜单
        mSortView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnMenuSelectDataChangedListener != null){
                    mOnMenuSelectDataChangedListener.onViewClicked(mSortView);
                }
                handleClickSortView();
            }
        });
        //点击 筛选 弹出菜单
        mSelectView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnMenuSelectDataChangedListener != null){
                    mOnMenuSelectDataChangedListener.onViewClicked(mSelectView);
                }
                handleClickSelectView();
            }
        });

        mContentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupWindow();
            }
        });
    }

    private void handleClickSubjectView(){

        mMainContentLayout.removeAllViews();
        mMainContentLayout.addView(mSubjectHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popUpWindow(TAB_SUBJECT);
    }

    private void handleClickSortView(){

        mMainContentLayout.removeAllViews();
        mMainContentLayout.addView(mSortHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popUpWindow(TAB_SORT);
    }

   private void handleClickSelectView(){

        mMainContentLayout.removeAllViews();
        mMainContentLayout.addView(mSelectHolder.getRootView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popUpWindow(TAB_SELECT);
    }

    private void popUpWindow(int tab){
        if(mTabRecorder != -1) {
            resetTabExtend(mTabRecorder);
        }
        extendsContent();
        setTabExtend(tab);
        mTabRecorder = tab;
    }

    private void extendsContent(){
        mContentLayout.removeAllViews();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentLayout.addView(mPopupWindowView, params);
    }

    private void dismissPopupWindow(){
        mContentLayout.removeAllViews();
        setTabClose();
    }

    public void setOnMenuSelectDataChangedListener(OnMenuSelectDataChangedListener onMenuSelectDataChangedListener){
        this.mOnMenuSelectDataChangedListener = onMenuSelectDataChangedListener;
    }

    public interface OnMenuSelectDataChangedListener{

        void onSubjectChanged(String grade, String subjects);
        void onSortChanged(String sortType);

        void onSelectedChanged(String gender, String classType);

        void onViewClicked(View view);

        //筛选菜单，当点击其他处菜单收回后，需要更新当前选中项
        void onSelectedDismissed(String gender, String classType);
    }

    private void setTabExtend(int tab){
        if(tab == TAB_SUBJECT){
            mSubjectText.setTextColor(getResources().getColor(R.color.blue));
            mSubjectArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }else if(tab == TAB_SORT){
            mSortText.setTextColor(getResources().getColor(R.color.blue));
            mSortArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }else if(tab == TAB_SELECT){
            mSelectText.setTextColor(getResources().getColor(R.color.blue));
            mSelectArrowImage.setImageResource(R.mipmap.ic_up_blue);
        }
    }

    private void resetTabExtend(int tab){
        if(tab == TAB_SUBJECT){
            mSubjectText.setTextColor(getResources().getColor(R.color.gray));
            mSubjectArrowImage.setImageResource(R.mipmap.ic_down);
        }else if(tab == TAB_SORT){
            mSortText.setTextColor(getResources().getColor(R.color.gray));
            mSortArrowImage.setImageResource(R.mipmap.ic_down);
        }else if(tab == TAB_SELECT){
            mSelectText.setTextColor(getResources().getColor(R.color.gray));
            mSelectArrowImage.setImageResource(R.mipmap.ic_down);
        }
    }

    private void setTabClose(){

        mSubjectText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSubjectArrowImage.setImageResource(R.mipmap.ic_down);

        mSortText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSortArrowImage.setImageResource(R.mipmap.ic_down);

        mSelectText.setTextColor(getResources().getColor(R.color.text_color_gey));
        mSelectArrowImage.setImageResource(R.mipmap.ic_down);
    }

    private String getSortString(String info){
        if(SortHolder.SORT_BY_NORULE.equals(info)){
            return "sort1";
        }else if(SortHolder.SORT_BY_EVALUATION.equals(info)){
            return "sort2";
        }else if(SortHolder.SORT_BY_PRICELOW.equals(info)){
            return "sort3";
        }else if(SortHolder.SORT_BY_PRICEHIGH.equals(info)){
            return "sort4";
        }else if(SortHolder.SORT_BY_DISTANCE.equals(info)){
            return "sort5";
        }
        return "sort1";
    }

    public void clearAllInfo(){
        //清除控件内部选项
        mSubjectHolder.refreshData(mSubjectDataList, 0, -1);
        mSortHolder.refreshView(null);
        mSelectHolder.refreshView(null);

        //清除菜单栏显示
        mSubjectText.setText("type1");
        mSortText.setText("type2");
    }
}