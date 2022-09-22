
package com.app.infocontrol.commons.Empleado;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultadoQREmpleado {

    @SerializedName("entidad")
    @Expose
    private String entidad;
    @SerializedName("dni")
    @Expose
    private String dni;

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
