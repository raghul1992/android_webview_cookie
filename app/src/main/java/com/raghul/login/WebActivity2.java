package com.raghul.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class WebActivity2 extends AppCompatActivity {
WebView myWebView;
String  email="",pw="";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2);
try{
    Intent intent = getIntent();
    email = intent.getExtras().getString("email");
    pw = intent.getExtras().getString("pw");
}catch (Exception ee){
    email="";
    pw="";

}

        myWebView = (WebView)findViewById(R.id.webview);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String mimeType = "text/html";
        String encoding = "utf-8";
       // initSettings(myWebView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
      /*  String pageContent = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"> <title>Logging in</title></head>" +
                "<body><form id=\"loginForm\" method=\"POST\" action=\"https://api.myaccount.uat.test.athome.domgen.com/v1/identity/signin\">" +
                "<input  name=\"email\" value=" + email + ">" +
                "<input  name=\"password\" value=" + pw + ">" +
                "</form> <script type=\"text/javascript\">document.getElementById(\"loginForm\").submit();</script></body></html>";

*/

        String login_url = "https://api.myaccount.uat.test.athome.domgen.com/v1/identity/signin";
      //  String url ="https://api.myaccount.uat.test.athome.domgentest.cloud/v1/plans";
        String target_url ="https://www.myaccount.uat.test.athome.domgen.com/my-plans";

        String pageContent = "<html><head></head><body><form id=\"loginForm\" action=\""+login_url+"\" method=\"post\">" +
                "<input type=\"hidden\" name=\"email\" value=\"" + email  +"\">" +
                "<input type=\"hidden\" name=\"password\" value=\"" + pw + "\">" +
                "</form> <script type=\"text/javascript\">document.getElementById(\"loginForm\").submit();</script></body></html>";

 myWebView.loadDataWithBaseURL(target_url,
                pageContent,mimeType,"utf-8",null);
//https://www.myaccount.uat.test.athome.domgen.com/my-plans
        //https://api.myaccount.uat.test.athome.domgen.com/v1/identity/signin
//type="hidden"




   /*     myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        myWebView.loadUrl("file:///android_asset/my_acc.html");
*/

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
        siteWebView.setWebChromeClient(new WebChromeClient());
        //siteWebView.getSettings().setSavePassword(true);
    }


    }


