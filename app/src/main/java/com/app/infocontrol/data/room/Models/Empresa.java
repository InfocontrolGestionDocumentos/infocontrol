package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.app.infocontrol.commons.Constantes;


@Entity(tableName = Constantes.TABLA_EMPRESA)
public class Empresa {
    @PrimaryKey
    @NonNull
    private String idEmpresa;
    private String nombre;
    private int activa;
    private String tipoCliente;
    private int cantidadPendientes;
    private int bajaAfipDiasDeGracia;
    private String logo;
    private String fechaHoraActualizacion;
    private String imagenBase64;

    public Empresa(@NonNull String idEmpresa, String nombre, int activa, String tipoCliente, int cantidadPendientes, int bajaAfipDiasDeGracia, String logo, String fechaHoraActualizacion) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.activa = activa;
        this.tipoCliente = tipoCliente;
        this.cantidadPendientes = cantidadPendientes;
        this.bajaAfipDiasDeGracia = bajaAfipDiasDeGracia;
        this.logo = logo;
        this.fechaHoraActualizacion = fechaHoraActualizacion;
    }

    @NonNull
    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(@NonNull String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getCantidadPendientes() {
        return cantidadPendientes;
    }

    public void setCantidadPendientes(int cantidadPendientes) {
        this.cantidadPendientes = cantidadPendientes;
    }

    public int getBajaAfipDiasDeGracia() {
        return bajaAfipDiasDeGracia;
    }

    public void setBajaAfipDiasDeGracia(int bajaAfipDiasDeGracia) {
        this.bajaAfipDiasDeGracia = bajaAfipDiasDeGracia;
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

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
}
