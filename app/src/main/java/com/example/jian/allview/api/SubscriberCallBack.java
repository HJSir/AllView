package com.example.jian.allview.api;

import android.text.TextUtils;


import com.example.jian.allview.news.bean.ResultResponse;
import com.example.jian.allview.utils.UIUtils;

import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 抽取CallBack
 * @date 2017/6/18  21:37
 */
public abstract class SubscriberCallBack<T> extends Subscriber<ResultResponse<T>> {

    @Override
    public void onNext(ResultResponse response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("success"))
                || !TextUtils.isEmpty(response.success) && response.success.equals("true");
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            UIUtils.showToast(response.message);
            onFailure(response);
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        onError();
    }

    protected abstract void onSuccess(T response);
    protected abstract void onError();

    protected void onFailure(ResultResponse response) {
    }

}
