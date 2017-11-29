package com.example.lenovo.mainui.dropmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.mainui.R;


/**\
 * 搜索 筛选 中类似radiobutton的控件
 * Created by vonchenchen on 2016/4/5 0005.
 */
public class RadioItemView extends RelativeLayout implements View.OnClickListener{

    private Context mContext;

    private TextView mTitleText;
    private ImageView mSelectedImage;

    private boolean mIsSelected = false;

    private OnStatusChangedListener mOnStatusChangedListener = null;

    public RadioItemView(Context context) {
        super(context);
        mContext = context;
    }

    public RadioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(mContext, R.layout.layout_radio_item, this);
        mTitleText = (TextView) findViewById(R.id.tv_title);
        mSelectedImage = (ImageView) findViewById(R.id.img_bx1);
        this.setOnClickListener(this);
    }

    public void setTitleText(String text){
        mTitleText.setText(text);
    }

    public boolean getSelected(){
        return this.mIsSelected;
    }

    public void setSelected(boolean bool){

        this.mIsSelected = bool;

        if(bool){
            mSelectedImage.setBackgroundResource(R.mipmap.ic_check);
        }else{
           mSelectedImage.setBackgroundResource(R.mipmap.ic_no_cheak);
        }

        if(mOnStatusChangedListener != null){
            mOnStatusChangedListener.onStatusChange(mIsSelected, mTitleText.getText().toString());
        }
    }

    @Override
    public void onClick(View v) {
        //原值非真 则改变
        if(mIsSelected == false) {
            mIsSelected = !mIsSelected;
        }
        setSelected(mIsSelected);
    }

    public void setOnStatusChangedListener(OnStatusChangedListener onStatusChangedListener){
        this.mOnStatusChangedListener = onStatusChangedListener;
    }

    public interface OnStatusChangedListener{
        void onStatusChange(boolean bool, String text);
    }
}
