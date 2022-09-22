
package com.app.infocontrol.commons.Empleado;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dato {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("valor")
    @Expose
    private String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
