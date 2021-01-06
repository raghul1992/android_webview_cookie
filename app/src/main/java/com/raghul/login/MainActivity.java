package com.raghul.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    EditText et_email, et_pw;
    Button btn_login;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pw = (EditText) findViewById(R.id.et_pw);
        btn_login = (Button) findViewById(R.id.btn_login);
        userService = ApiUtils.getUserService();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_email.getText().toString();
                String password = et_pw.getText().toString();
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    Log.e("====================", "login" );

                   // doLogin(username, password);
                    Intent intent = new Intent(MainActivity.this, WebActivity2.class);

                    intent.putExtra("email", username);
                    intent.putExtra("pw", password);


                    Log.e("=====", username );
                    Log.e("=====", password );
                    startActivity(intent);
                }
            }
        });

    }
    private void doLogin(final String username,final String password){
      ReqObj temp=  new ReqObj(username,password);
        Call call = userService.login( temp);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    ResObj resObj = (ResObj) response.body();
                    Log.e("====================", resObj.toString() );


                    String Cookielist=   response.headers().get("Set-Cookie");
                    String header=   response.headers().toString();
                   // response.raw().headers("Set-Cookie");
                    Headers headers = response.headers();


                    Headers headerResponse = response.headers();
                    //convert header to Map
                    Map<String, List<String>> headerMapList = headerResponse.toMultimap();
                    //Get List of "Set-Cookie" from Map
                    List<String> allCookies = headerMapList.get("set-cookie");
                    String cookieval = "";
                    for (int i = 1; i < allCookies.size(); i++) {
                        allCookies.get(i);
                        //concat all cookies in cookieval.
                        cookieval = cookieval + allCookies.get(i);
                    }

                    List<String> COOK = response.headers().values("Set-Cookie");
                    String AWS_IDT = (COOK .get(0).split(";"))[0];
                    String AWS_ACC = (COOK .get(1).split(";"))[0];


                    if(resObj.getExpires_in().equals(("1800"))){
                        //login start main activity
                        Toast.makeText(getBaseContext(), "Yaaaaayy", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, WebActivity3.class);

                        intent.putExtra("AWS_IDT", AWS_IDT);
                        intent.putExtra("AWS_ACC", AWS_ACC);
                        intent.putExtra("header", header);


                        Log.e("=====", AWS_IDT );
                        Log.e("=====", AWS_ACC );
                        Log.e("=====", header );
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("====================", t.toString() );

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validateLogin( String username, String  password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private  class  DGCookieJar implements CookieJar {

        List<Cookie> list_of_cookies= new ArrayList<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            list_of_cookies.clear();
            list_of_cookies.addAll(cookies);

        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return null;
        }
    }
}