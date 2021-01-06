package com.raghul.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class WebActivity3 extends AppCompatActivity {
WebView myWebView;
String  AWS_ACC="",AWS_IDT="",header="";
String url ="https://api.myaccount.uat.test.athome.domgentest.cloud/v1/plans";
    String target_url ="https://www.myaccount.uat.test.athome.domgen.com/my-plans";

    String pageContent = "<html><head></head><body><form id=\"loginForm\" action=\"https://www.myaccount.uat.test.athome.domgen.com/my-plans\" method=\"post\">" +
            "</form> <script type=\"text/javascript\">document.getElementById(\"loginForm\").submit();</script></body></html>";


   /* String pageContent = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"> <title>Logging in</title></head>" +
            "<body><form id=\"loginForm\" method=\"POST\" action=\"https://api.myaccount.uat.test.athome.domgen.com/v1/identity/signin\">" +
            "<p>"+header+"</p>" +
            "<input type=\"hidden\" name=\"email\" value=" + "===email" + ">" +
            "<input type=\"hidden\" name=\"password\" value=" + "===pw" + ">" +
            "</form> </body></html>";*/
//<script type="text/javascript">document.getElementById("loginForm").submit();</script>
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
        myWebView.setWebViewClient(new CommonWebClient());

      set();
    }


    private void initSettings(WebView siteWebView) {

        //siteWebView.getSettings().supportZoom()=true;
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
                cookieManager.setCookie(target_url,AWS_IDT);
                cookieManager.setCookie(target_url,AWS_ACC);
                 myWebView.loadUrl(target_url);
                //  myWebView.loadData(pageContent, "text/html; charset=utf-8", "UTF-8");
               // myWebView.loadUrl("file:///android_asset/my_acc3.html");

                cookieManager.setAcceptThirdPartyCookies(myWebView, true);
            } else {
                cookieManager.setCookie(target_url,AWS_IDT);
                cookieManager.setCookie(target_url,AWS_ACC);
                 myWebView.loadUrl(target_url);
                //myWebView.loadData(pageContent, "text/html; charset=utf-8", "UTF-8");
              //  myWebView.loadUrl("file:///android_asset/my_acc3.html");

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
