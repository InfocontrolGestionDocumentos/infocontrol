package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.app.infocontrol.commons.Constantes;


@Entity( tableName = Constantes.TABLA_USUARIO)
public class Usuario {

    /*
    SharedPreferenceManager.setSomeStringValue(Constantes.ID_USUARIO, userData.getIdUsuarios());
                    SharedPreferenceManager.setSomeStringValue(Constantes.USERNAME, usuario);
                    SharedPreferenceManager.setSomeStringValue(Constantes.PASS, pass);
                    SharedPreferenceManager.setSomeStringValue(Constantes.TOKEN, data.getBearer());
                    SharedPreferenceManager.setSomeBooleanValue(Constantes.RECORDAR_USUARIO,checkRemember.isChecked());
     */

    @PrimaryKey
    @NonNull
    private String idUsuario;
    private String nombreUsuario;
    private String usuarioIdentificacion;
    private String usuarioPassword;
    private String tokenUsuario;
    private boolean recordarUsuario;
    private String marcarIngreso;


    public Usuario(@NonNull String idUsuario, String nombreUsuario, String usuarioIdentificacion, String usuarioPassword, String tokenUsuario,
                   boolean recordarUsuario, String marcarIngreso) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.usuarioIdentificacion = usuarioIdentificacion;
        this.usuarioPassword = usuarioPassword;
        this.tokenUsuario = tokenUsuario;
        this.recordarUsuario = recordarUsuario;
        this.marcarIngreso = marcarIngreso;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuarioIdentificacion() {
        return usuarioIdentificacion;
    }

    public void setUsuarioIdentificacion(String usuarioIdentificacion) {
        this.usuarioIdentificacion = usuarioIdentificacion;
    }

    public String getUsuarioPassword() {
        return usuarioPassword;
    }

    public void setUsuarioPassword(String usuarioPassword) {
        this.usuarioPassword = usuarioPassword;
    }

    public String getTokenUsuario() {
        return tokenUsuario;
    }

    public void setTokenUsuario(String tokenUsuario) {
        this.tokenUsuario = tokenUsuario;
    }

    public boolean isRecordarUsuario() {
        return recordarUsuario;
    }

    public void setRecordarUsuario(boolean recordarUsuario) {
        this.recordarUsuario = recordarUsuario;
    }

    public String getMarcarIngreso(){
        return marcarIngreso;
    }

    public void setMarcarIngreso(String marcarIngreso){
        this.marcarIngreso = marcarIngreso;
    }
}
