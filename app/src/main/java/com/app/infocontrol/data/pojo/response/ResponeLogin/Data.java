
package com.app.infocontrol.data.pojo.response.ResponeLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Bearer")
    @Expose
    private String bearer;
    @SerializedName("BearerFormat")
    @Expose
    private String bearerFormat;
    @SerializedName("BearerCreation")
    @Expose
    private String bearerCreation;
    @SerializedName("BearerExpiresIn")
    @Expose
    private Integer bearerExpiresIn;
    @SerializedName("userData")
    @Expose
    private UserData userData;

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getBearerFormat() {
        return bearerFormat;
    }

    public void setBearerFormat(String bearerFormat) {
        this.bearerFormat = bearerFormat;
    }

    public String getBearerCreation() {
        return bearerCreation;
    }

    public void setBearerCreation(String bearerCreation) {
        this.bearerCreation = bearerCreation;
    }

    public Integer getBearerExpiresIn() {
        return bearerExpiresIn;
    }

    public void setBearerExpiresIn(Integer bearerExpiresIn) {
        this.bearerExpiresIn = bearerExpiresIn;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
