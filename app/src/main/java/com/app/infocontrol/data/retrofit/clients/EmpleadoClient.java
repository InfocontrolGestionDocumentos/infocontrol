package com.app.infocontrol.data.retrofit.clients;



import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.data.retrofit.services.EmpleadoService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpleadoClient {

    private static EmpleadoClient instancia = null;
    private EmpleadoService empleadoService;
    private Retrofit retrofit;

    public EmpleadoClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BASE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empleadoService = retrofit.create(EmpleadoService.class);
    }


    public static EmpleadoClient getInstance(){
        if(instancia == null){
            instancia = new EmpleadoClient();
        }
        return instancia;
    }

    public EmpleadoService getEmpleadoService(){
        return empleadoService;
    }
}
