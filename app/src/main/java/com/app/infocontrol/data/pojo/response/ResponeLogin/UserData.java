
package com.app.infocontrol.data.pojo.response.ResponeLogin;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class UserData {

    @SerializedName("nombre_usuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("tipo_usuario")
    @Expose
    private String tipoUsuario;
    @SerializedName("rol")
    @Expose
    private String rol;
    @SerializedName("marcar_ingreso")
    @Expose
    private ArrayList<String> marcarIngreso;
    @SerializedName("tipo_rol")
    @Expose
    private String tipoRol;
    @SerializedName("rol_infocontrol")
    @Expose
    private String rolInfocontrol;
    @SerializedName("id_usuarios")
    @Expose
    private String idUsuarios;
    @SerializedName("permiso_visualiza_reporte")
    @Expose
    private Boolean permisoVisualizaReporte;
    @SerializedName("permiso_registrar_ingresos")
    @Expose
    private Boolean permisoRegistrarIngresos;
    @SerializedName("codigo_error")
    @Expose
    private Integer codigoError;
    @SerializedName("mensaje_error")
    @Expose
    private MensajeError mensajeError;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public ArrayList<String> getMarcarIngreso(){
        return marcarIngreso;
    }

    public void setMarcarIngreso(ArrayList<String> marcarIngreso){
        this.marcarIngreso = marcarIngreso;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public String getRolInfocontrol() {
        return rolInfocontrol;
    }

    public void setRolInfocontrol(String rolInfocontrol) {
        this.rolInfocontrol = rolInfocontrol;
    }

    public String getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(String idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public Boolean getPermisoVisualizaReporte() {
        return permisoVisualizaReporte;
    }

    public void setPermisoVisualizaReporte(Boolean permisoVisualizaReporte) {
        this.permisoVisualizaReporte = permisoVisualizaReporte;
    }

    public Boolean getPermisoRegistrarIngresos() {
        return permisoRegistrarIngresos;
    }

    public void setPermisoRegistrarIngresos(Boolean permisoRegistrarIngresos) {
        this.permisoRegistrarIngresos = permisoRegistrarIngresos;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public MensajeError getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(MensajeError mensajeError) {
        this.mensajeError = mensajeError;
    }

}
