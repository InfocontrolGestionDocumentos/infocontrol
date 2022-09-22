package com.app.infocontrol.data.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dario Carrizo on 12/04/2022
 **/
public class RequestRegistrarEmpleado {
    @SerializedName("id_empresas")
    @Expose
    private String idEmpresas;
    @SerializedName("id_entidad")
    @Expose
    private String idEntidad;
    @SerializedName("id_usuarios")
    @Expose
    private String idUsuarios;

    public RequestRegistrarEmpleado() {
    }

    public RequestRegistrarEmpleado(String idEmpresas, String idEntidad, String idUsuarios) {
        this.idEmpresas = idEmpresas;
        this.idEntidad = idEntidad;
        this.idUsuarios = idUsuarios;
    }

    public String getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(String idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(String idUsuarios) {
        this.idUsuarios = idUsuarios;
    }
}
