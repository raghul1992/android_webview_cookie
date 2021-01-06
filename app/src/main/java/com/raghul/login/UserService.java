package com.raghul.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("identity/signin")
    Call<ResObj> login(@Body ReqObj user);




}
