package com.app.infocontrol.data.pojo.response.ResponseEmpleados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Dario Carrizo on 12/04/2022
 **/
public class DataRegistro {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("docs_faltantes")
    @Expose
    private ArrayList<String> docsFaltantes;
    @SerializedName("docs_rechazados")
    @Expose
    private ArrayList<String> docsRechazados;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getDocsFaltantes() {
        return docsFaltantes;
    }

    public void setDocsFaltantes(ArrayList<String> docsFaltantes) {
        this.docsFaltantes = docsFaltantes;
    }

    public ArrayList<String> getDocsRechazados() {
        return docsRechazados;
    }

    public void setDocsRechazados(ArrayList<String> docsRechazados) {
        this.docsRechazados = docsRechazados;
    }
}
