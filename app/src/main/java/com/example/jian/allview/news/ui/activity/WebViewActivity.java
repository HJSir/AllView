package com.example.jian.allview.news.ui.activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jian.allview.R;
import com.example.jian.allview.base.BaseActivity;
import com.example.jian.allview.base.BasePresenter;


/**
 * @author ChayChan
 * @description: 加载网页的activity
 * @date 2017/7/4  22:01
 */

public class WebViewActivity extends BaseActivity {
    public static final String URL = "url";

//    @Bind(R.id.iv_back)
//    ImageView mIvBack;
//
//    @Bind(R.id.tv_author)
//    TextView mTvTitle;
//
//    @Bind(R.id.pb_loading)
//    ProgressBar mPbLoading;
//
//    @Bind(R.id.wv_content)
//
WebView mWvContent;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView() {
        mWvContent= (WebView) findViewById(R.id.wv_content);
//        Eyes.setStatusBarColor(this, UIUtils.getColor(R.color.status_color_grey));
    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra(URL);
        mWvContent.loadUrl(url);
    }

    @Override
    public void initListener() {
        WebSettings settings = mWvContent.getSettings();
        settings.setJavaScriptEnabled(true);


        mWvContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                mPbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                mPbLoading.setVisibility(View.GONE);
            }
        });

        mWvContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                mPbLoading.setProgress(newProgress);
            }
        });

        mWvContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWvContent.canGoBack()) {  //表示按返回键
                        mWvContent.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }

//    @OnClick(R.id.iv_back)
//    public void onViewClicked() {
//        finish();
//    }
}
