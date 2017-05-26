package com.wangzai.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wangzai.rxjavademo.bean.PhotoBean;
import com.wangzai.rxjavademo.http.HttpClient;
import com.wangzai.rxjavademo.http.callback.OnResultCallback;
import com.wangzai.rxjavademo.http.observer.HttpObserver;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPhotoList();
    }

    private void getPhotoList() {
        HttpObserver<List<PhotoBean>> observer = new HttpObserver<>(new OnResultCallback<List<PhotoBean>>() {
            @Override
            public void onSuccess(List<PhotoBean> photoBeen) {
                List<PhotoBean> photoBeen1 = photoBeen;
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                Log.i("RxJava2Log", "------code=" + code + "------msg=" + errorMsg);
            }
        });
        HttpClient.getInstance().getNewestPhotoList(observer, 1, 5);
    }
}
