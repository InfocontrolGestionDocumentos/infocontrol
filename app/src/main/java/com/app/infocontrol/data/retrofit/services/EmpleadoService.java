package com.app.infocontrol.data.retrofit.services;

import com.app.infocontrol.data.pojo.request.RequestAccionARegistrarEmpleado;
import com.app.infocontrol.data.pojo.request.RequestRegistrarEmpleado;
import com.app.infocontrol.data.pojo.response.ResponseControlDeIngreso.ResponseControlDeIngreso;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.ResponseEmpleados;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.ResponseRegistroEmpleado;
import com.app.infocontrol.data.room.Models.ControlDeIngresos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmpleadoService {

    @GET("empleados/listartest")
    Call<ResponseEmpleados> getEmpleados(@Query("Bearer") String tokenBearer, @Query("id_empresas") String id_empresas,
                                         @Query("fecha_hora") String fecha_hora);

    @POST("empleados/ActualizarDatosIE")
    Call<ResponseControlDeIngreso> postInsertarControlDeIngreso(@Query("Bearer") String tokenBearer, @Body ControlDeIngresos registro);

    @GET("empleados/EliminarDatosIE")
    Call<ResponseControlDeIngreso> eliminarRegistrosEnServidor(@Query("Bearer") String tokenBearer);

    @Headers("Content-type:application/json")
    @POST("Ingresos_egresos/register_movement")
    Call<ResponseRegistroEmpleado> postRegistrarEmpleado(@Query("Bearer") String tokenBearer,
                                                         @Body RequestRegistrarEmpleado registrarEmpleado);
    @Headers("Content-type:application/json")
    @POST("ingresos_egresos/action_resource")
    Call<ResponseRegistroEmpleado> getAccionDeEmpleado(@Query("Bearer") String tokenBearer, @Body RequestAccionARegistrarEmpleado action);
}

