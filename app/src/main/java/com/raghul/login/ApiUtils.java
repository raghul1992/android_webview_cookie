package com.raghul.login;

public class ApiUtils {

    public static final String BASE_URL = "https://api.myaccount.uat.test.athome.domgen.com/v1/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}