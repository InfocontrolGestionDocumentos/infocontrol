
package com.app.infocontrol.data.pojo.response.ResponeLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MensajeError {

    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
