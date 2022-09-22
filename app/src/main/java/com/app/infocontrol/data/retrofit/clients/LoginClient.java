package com.app.infocontrol.data.retrofit.clients;

import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.data.retrofit.services.LoginService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginClient {

    private static LoginClient instancia = null;
    private LoginService loginService;
    private Retrofit retrofit;

    public LoginClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);
    }


    public static LoginClient getInstance(){
        if(instancia == null){
            instancia = new LoginClient();
        }
        return instancia;
    }

    public LoginService getLoginService(){
        return loginService;
    }
}
