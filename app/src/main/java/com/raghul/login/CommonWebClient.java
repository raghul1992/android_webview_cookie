package com.raghul.login;


import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class CommonWebClient extends WebViewClient {
    private static final String TAG = "============";
    public static final String SUCCESS_TAG = "===========";


    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        Log.e(TAG, "==============="+url);

        return super.shouldInterceptRequest(view, url);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Log.e(TAG, "==============="+request.getUrl());

        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e(TAG, "shouldOverrideUrlLoading: " + url);
        if (url.contains(SUCCESS_TAG)) {
            Log.e(TAG, "shouldOverrideUrlLoading: success");
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.e(TAG, "onPageStarted: " + url);
        String cookies = CookieManager.getInstance().getCookie(url);
        Log.e(TAG, "Eat a cookie: =========" + cookies);

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.e(TAG, "onPageFinished: " + url);
        String cookies = CookieManager.getInstance().getCookie(url);
        Log.e(TAG, "Eat a cookie: =========" + cookies);

    }
}