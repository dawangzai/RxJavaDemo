package com.wangzai.rxjavademo.http.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Headers;
import retrofit2.Response;

/**
 * Created by wangzai on 2017/5/26.
 */

public class ApiResponse<T> {
    public final T body;

    public ApiResponse(T body) {
        this.body = body;
    }
//    private static final Pattern LINK_PATTERN = Pattern
//            .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"");
//    private static final Pattern PAGE_PATTERN = Pattern.compile("page=(\\d)+");
//    private static final String NEXT_LINK = "next";
    //    public final int code;
//    @Nullable
//    public final T body;
//    @Nullable
//    public final String errorMessage;
//
//    @NonNull
//    public final Map<String, String> links;

//    public okhttp3.Response response;

//    public ApiResponse(@NonNull okhttp3.Response response, @Nullable T body) {
//        this.response = response;
//        this.body = body;
//        code = response.code();
//        if (response.isSuccessful()) {
//            body = response.body();
//            errorMessage = null;
//        } else {
//            String message = null;
//            if (response.errorBody() != null) {
//                try {
//                    message = response.errorBody().string();
//                } catch (IOException ignored) {
//                }
//            }
//            if (message == null || message.trim().length() == 0) {
//                message = response.message();
//            }
//            errorMessage = message;
//            body = null;
//        }
//
//        String linkHeader = response.headers().get("link");
//        if (linkHeader == null) {
//            links = Collections.emptyMap();
//        } else {
//            links = new ArrayMap<>();
//            Matcher matcher = LINK_PATTERN.matcher(linkHeader);
//
//            while (matcher.find()) {
//                int count = matcher.groupCount();
//                if (count == 2) {
//                    links.put(matcher.group(2), matcher.group(1));
//                }
//            }
//        }
//    }
}
