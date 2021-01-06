package com.raghul.login;

import android.provider.DocumentsContract;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Webbing {

    public static void Open()  {

        try{
        Connection.Response loginForm = Jsoup.connect("http://your website")
                .method(Connection.Method.POST)
                .execute();

        Document document = Jsoup.connect("http://your website")
                .data("username", "XXX")
                .data("password", "YYY")
                .data("agreement", "on")
                .timeout(5000)
                .cookies(loginForm.cookies())
                .post();

        String url = "http://a page you want to load after login";

        Document fpl = Jsoup.connect(url)
                .timeout(5000)
                .cookies(loginForm.cookies())
                .get();
        String body = fpl.body().toString();
      //  ExtFile.write(body);

        }catch (Exception ee){


        }
}
}