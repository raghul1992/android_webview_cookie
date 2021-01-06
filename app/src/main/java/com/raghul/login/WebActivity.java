package com.raghul.login;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebActivity extends AppCompatActivity {
WebView myWebView;
String  AWS_ACC="",AWS_IDT="",header="";
String url ="https://www.myaccount.uat.test.athome.domgen.com/my-plans";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        RelativeLayout main = (RelativeLayout) findViewById(R.id.main);

         myWebView = new WebView(this);
        myWebView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        main.addView(myWebView);


        try{
    Intent intent = getIntent();
    AWS_ACC = intent.getExtras().getString("AWS_ACC");
    AWS_IDT = intent.getExtras().getString("AWS_IDT");
            header = intent.getExtras().getString("header");
}catch (Exception ee){
    AWS_ACC="";
    AWS_IDT="";
            header="";

}



        initSettings(myWebView);
      //  setCookies(myWebView);
        myWebView.setWebViewClient(new CommonWebClient());

set();
      //  clearCookies(this);
       // String extra_acc="; Domain=uat.test.athome.domgen.com; Path=/; HttpOnly; Secure; SameSite=Strict";
       // String extra_idt="; Domain=uat.test.athome.domgen.com; Path=/; Secure; SameSite=Strict";
      //  String c_idt=AWS_IDT;
       // String c_acc=AWS_ACC;
       /* Map<String, String> headers = new HashMap<>();
        Map<String, List<String>> headerMapList =  new HashMap<>();
        List<String> cook = new ArrayList<>();
        cook.add(AWS_ACC);
        cook.add(AWS_IDT);

        headerMapList.put("Set-Cookie",cook);
        headers.put("Set-Cookie", cook.get(0)+";"+cook.get(1));*/
     //   header.put("uat.test.athome.domgen.com", AWS_ACC);
       /* Map<String, String> headers = new HashMap<>();
        headers.put("Set-Cookie", AWS_ACC+";"+AWS_IDT);
        Log.e("============", "==============="+AWS_ACC+";"+AWS_IDT);

        myWebView.loadUrl(url, headers);*/
    }


    private void initSettings(WebView siteWebView) {
        siteWebView.getSettings().setJavaScriptEnabled(true);
        siteWebView.getSettings().setAllowContentAccess(true);
        siteWebView.getSettings().setAllowFileAccess(true);
        siteWebView.getSettings().setAllowUniversalAccessFromFileURLs(false);
        siteWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        siteWebView.getSettings().setAppCacheEnabled(false);
        siteWebView.getSettings().setBlockNetworkImage(false);
        siteWebView.getSettings().setBlockNetworkLoads(false);
        siteWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        siteWebView.getSettings().setDatabaseEnabled(false);
        siteWebView.getSettings().setGeolocationEnabled(true);
        siteWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        siteWebView.getSettings().setLoadsImagesAutomatically(true);
        siteWebView.getSettings().setNeedInitialFocus(false);
        siteWebView.getSettings().setSaveFormData(true);
        siteWebView.getSettings().setSupportMultipleWindows(false);
        siteWebView.getSettings().setSupportZoom(true);
        siteWebView.getSettings().setDomStorageEnabled(true);
        siteWebView.getSettings().setAppCacheEnabled(true);
        siteWebView.getSettings().setDisplayZoomControls(false);
        siteWebView.getSettings().setLoadWithOverviewMode(true);
        siteWebView.getSettings().setUseWideViewPort(true);
        siteWebView.getSettings().setBuiltInZoomControls(true);

        siteWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        siteWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    }


    private  void  set(){


            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.acceptCookie();
            String domain = "uat.test.athome.domgen.com";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.setCookie(domain,AWS_IDT);
                cookieManager.setCookie(domain,AWS_ACC);

                myWebView.loadUrl(url);
                cookieManager.setAcceptThirdPartyCookies(myWebView, true);
            } else {
                cookieManager.setCookie(domain,AWS_IDT);
                cookieManager.setCookie(domain,AWS_ACC);

                myWebView.loadUrl(url);
            }
        }

    private void setCookies(WebView webView) {
        CookieSyncManager.createInstance(webView.getContext());

        CookieManager cookieManager = CookieManager.getInstance();

        cookieManager.setAcceptFileSchemeCookies(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        } else {
            cookieManager.setAcceptCookie(true);
        }

        String extra_acc="; Domain=uat.test.athome.domgen.com; Path=/; HttpOnly; Secure; SameSite=Strict";
        String extra_idt="; Domain=uat.test.athome.domgen.com; Path=/; Secure; SameSite=Strict";
        cookieManager.setCookie(url,"AWS_IDT="+AWS_IDT+extra_idt);
        cookieManager.setCookie(url,"AWS_ACC="+AWS_ACC+extra_acc);

        CookieSyncManager.getInstance().sync();


    }
    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Log.d("=====", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            Log.d("=====", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }


}
