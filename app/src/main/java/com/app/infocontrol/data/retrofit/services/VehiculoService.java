package com.app.infocontrol.data.retrofit.services;



import com.app.infocontrol.data.pojo.response.ResponseVehiculos.ResponseVehiculo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface VehiculoService {

    @GET("vehiculos/listartest")
    Call<ResponseVehiculo> getVehiculos(@Query("Bearer") String tokenBearer, @Query("id_empresas") String id_empresas,
                                        @Query("fecha_hora") String fecha_hora);
}
