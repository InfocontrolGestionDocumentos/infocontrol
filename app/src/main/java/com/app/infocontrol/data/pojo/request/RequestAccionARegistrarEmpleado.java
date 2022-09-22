package com.app.infocontrol.data.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dario Carrizo on 26/04/2022
 **/
public class RequestAccionARegistrarEmpleado {
    @SerializedName("id_entidad")
    @Expose
    private String idEntidad;

    public RequestAccionARegistrarEmpleado() {
    }

    public RequestAccionARegistrarEmpleado(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }
}
