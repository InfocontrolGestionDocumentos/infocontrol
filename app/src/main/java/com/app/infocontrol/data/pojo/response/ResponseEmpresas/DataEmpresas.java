
package com.app.infocontrol.data.pojo.response.ResponseEmpresas;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEmpresas {

    @SerializedName("id_empresa_asociada")
    @Expose
    private String idEmpresaAsociada;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("activa")
    @Expose
    private String activa;
    @SerializedName("tipo_cliente")
    @Expose
    private String tipoCliente;
    @SerializedName("cantidad_pendientes")
    @Expose
    private String cantidadPendientes;
    @SerializedName("baja_afip_dias_gracia")
    @Expose
    private String bajaAfipDiasGracia;
    @SerializedName("logo")
    @Expose
    private String logo;

    private String fechaHoraActualizacion;


    public DataEmpresas(@NonNull String idEmpresaAsociada, String nombre, String activa, String tipoCliente, String cantidadPendientes, String bajaAfipDiasGracia, String logo) {
        super();
        this.idEmpresaAsociada = idEmpresaAsociada;
        this.nombre = nombre;
        this.activa = activa;
        this.tipoCliente = tipoCliente;
        this.cantidadPendientes = cantidadPendientes;
        this.bajaAfipDiasGracia = bajaAfipDiasGracia;
        this.logo = logo;
    }

    public String getIdEmpresaAsociada() {
        return idEmpresaAsociada;
    }

    public void setIdEmpresaAsociada(String idEmpresaAsociada) {
        this.idEmpresaAsociada = idEmpresaAsociada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCantidadPendientes() {
        return cantidadPendientes;
    }

    public void setCantidadPendientes(String cantidadPendientes) {
        this.cantidadPendientes = cantidadPendientes;
    }

    public String getBajaAfipDiasGracia() {
        return bajaAfipDiasGracia;
    }

    public void setBajaAfipDiasGracia(String bajaAfipDiasGracia) {
        this.bajaAfipDiasGracia = bajaAfipDiasGracia;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFechaHoraActualizacion() {
        return fechaHoraActualizacion;
    }

    public void setFechaHoraActualizacion(String fechaHoraActualizacion) {
        this.fechaHoraActualizacion = fechaHoraActualizacion;
    }
}
