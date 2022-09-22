
package com.app.infocontrol.data.pojo.response.ResponseControlDeIngreso;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseControlDeIngreso {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("resultado")
    @Expose
    private String resultado;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
