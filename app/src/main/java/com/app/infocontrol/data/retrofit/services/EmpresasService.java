package com.app.infocontrol.data.retrofit.services;



import com.app.infocontrol.data.pojo.request.RequestRegistrarEmpleado;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.ResponseRegistroEmpleado;
import com.app.infocontrol.data.pojo.response.ResponseEmpresas.ResponseEmpresas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmpresasService {

    @GET("empresas/listar")
    Call<ResponseEmpresas> getEmpresas(@Query("Bearer") String tokenBearer);



}
