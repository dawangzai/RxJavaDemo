package com.wangzai.rxjavademo.http.callback;

/**
 * Created by wangzai on 2017/5/26.
 */

public interface OnResultCallback<T> {
    void onSuccess(T t);

    void onFailed(int code, String errorMsg);
}
