package com.app.infocontrol.data.retrofit.clients;



import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.data.retrofit.services.EmpresasService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpresasClient {

    private static EmpresasClient instancia = null;
    private EmpresasService empresasService;
    private Retrofit retrofit;

    public EmpresasClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empresasService = retrofit.create(EmpresasService.class);
    }


    public static EmpresasClient getInstance(){
        if(instancia == null){
            instancia = new EmpresasClient();
        }
        return instancia;
    }

    public EmpresasService getEmpresasService(){
        return empresasService;
    }
}
