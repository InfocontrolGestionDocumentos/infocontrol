package com.app.infocontrol.data.retrofit.clients;



import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.data.retrofit.services.VehiculoService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehiculoClient {

    private static VehiculoClient instancia = null;
    private VehiculoService vehiculoService;
    private Retrofit retrofit;

    public VehiculoClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        vehiculoService = retrofit.create(VehiculoService.class);
    }


    public static VehiculoClient getInstance(){
        if(instancia == null){
            instancia = new VehiculoClient();
        }
        return instancia;
    }

    public VehiculoService getVehiculoService(){
        return vehiculoService;
    }
}
