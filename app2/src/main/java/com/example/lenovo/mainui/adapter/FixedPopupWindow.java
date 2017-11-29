package com.example.lenovo.mainui.adapter;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by chitty on 2017/7/26.
 * 适配 Android 7.0  7.1
 */

public class FixedPopupWindow extends PopupWindow {

    public FixedPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height, false);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
