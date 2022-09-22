package com.app.infocontrol.commons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultadoQRVehiculo {
    @SerializedName("entidad")
    @Expose
    private String entidad;
    @SerializedName("dominio")
    @Expose
    private String vehiculo;

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }
}
