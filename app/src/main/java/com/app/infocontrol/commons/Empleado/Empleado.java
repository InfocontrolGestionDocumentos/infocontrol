
package com.app.infocontrol.commons.Empleado;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Empleado {

    @SerializedName("datos")
    @Expose
    private List<Dato> datos = null;

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }

}
