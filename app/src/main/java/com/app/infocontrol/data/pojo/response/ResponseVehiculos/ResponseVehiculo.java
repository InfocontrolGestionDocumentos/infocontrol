
package com.app.infocontrol.data.pojo.response.ResponseVehiculos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseVehiculo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("elapsed")
    @Expose
    private String elapsed;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataVehiculos> dataVehiculos = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataVehiculos> getDataVehiculos() {
        return dataVehiculos;
    }

    public void setDataVehiculos(List<DataVehiculos> dataVehiculos) {
        this.dataVehiculos = dataVehiculos;
    }

}
