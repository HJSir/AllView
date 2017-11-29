package com.example.jian.allview.find.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;

public class FindDetailActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_find_detail;
    }

    @Override
    public void initView() {
        super.initView();

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("http://www.jietusoft.com/cases/vrtour/vrdemo/index.html");
        webview.getSettings().setSupportZoom(false);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
