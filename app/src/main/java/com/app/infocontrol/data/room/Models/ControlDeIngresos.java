package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.app.infocontrol.commons.Constantes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = Constantes.TABLA_CONTROL_INGRESOS_BARRICK)
public class ControlDeIngresos {

    @SerializedName("id_control")
    @Expose
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int idControl;
    @SerializedName("id_registro")
    @Expose
    private String idRegistro; // Id del Empleado, vehiculo o socio
    @SerializedName("entidad")
    @Expose
    private String tipoEntidad; //Tipo de entidad (empleado, vehiculo o socio)
    @SerializedName("id_usuarios_ingreso")
    @Expose
    private String idUsuarioIngresado; //Guarda el id del usuario logueado
    @SerializedName("fecha_hora_entrada")
    @Expose
    private String fechaHoraEntrada; //
    @SerializedName("estado_ingreso")
    @Expose
    private String estadoIngreso; //Estado de ingreso de la entidad (habilitado o activo, deshabilitado)
    @SerializedName("id_usuario_egreso")
    @Expose
    private String idUsuarioEgreso;
    @SerializedName("fecha_hora_salida")
    @Expose
    private String fechaHoraSalida;
    @SerializedName("estado_egreso")
    @Expose
    private String estadoEgreso;
    @SerializedName("id_empresa")
    @Expose
    private String idEmpresa;


    public ControlDeIngresos(String idRegistro, String tipoEntidad, String idUsuarioIngresado, String fechaHoraEntrada, String estadoIngreso, String idUsuarioEgreso, String fechaHoraSalida, String estadoEgreso, String idEmpresa) {
        this.idRegistro = idRegistro;
        this.tipoEntidad = tipoEntidad;
        this.idUsuarioIngresado = idUsuarioIngresado;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.estadoIngreso = estadoIngreso;
        this.idUsuarioEgreso = idUsuarioEgreso;
        this.fechaHoraSalida = fechaHoraSalida;
        this.estadoEgreso = estadoEgreso;
        this.idEmpresa = idEmpresa;
    }


    public int getIdControl() {
        return idControl;
    }

    public void setIdControl(int idControl) {
        this.idControl = idControl;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public String getIdUsuarioIngresado() {
        return idUsuarioIngresado;
    }

    public void setIdUsuarioIngresado(String idUsuarioIngresado) {
        this.idUsuarioIngresado = idUsuarioIngresado;
    }

    public String getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(String fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public String getEstadoIngreso() {
        return estadoIngreso;
    }

    public void setEstadoIngreso(String estadoIngreso) {
        this.estadoIngreso = estadoIngreso;
    }

    public String getIdUsuarioEgreso() {
        return idUsuarioEgreso;
    }

    public void setIdUsuarioEgreso(String idUsuarioEgreso) {
        this.idUsuarioEgreso = idUsuarioEgreso;
    }

    public String getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(String fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getEstadoEgreso() {
        return estadoEgreso;
    }

    public void setEstadoEgreso(String estadoEgreso) {
        this.estadoEgreso = estadoEgreso;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
