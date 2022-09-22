package com.app.infocontrol.data.retrofit.services;




import com.app.infocontrol.data.pojo.response.ResponeLogin.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    @POST("service/login")
    Call<ResponseLogin> postLogin(@Query("username") String username, @Query("password") String password);
}
