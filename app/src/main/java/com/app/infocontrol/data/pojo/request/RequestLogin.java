package com.app.infocontrol.data.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogin {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestLogin() {
    }

    /**
     *
     * @param usuario
     * @param contrasena
     */
    public RequestLogin(String usuario, String contrasena) {
        super();
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
