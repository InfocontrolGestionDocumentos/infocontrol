package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.app.infocontrol.commons.Constantes;


@Entity(tableName = Constantes.TABLA_EMPLEADO)
public class Empelado {

    @PrimaryKey
    @NonNull
    private String idEmpleado;
    private String CUIL;
    private String DNI;
    private String mensajeGeneral;
    private String datosEmpleado;
    private int anulado;
    private int bajaAfip;
    private String estado;
    private int eliminado;
    private String colorMensajeGeneral;
    private String colorTextoMensajeGeneral;
    private String urlImagen;
    private String razonSocial;
    private String codigoNFC;
    private String imagenBase64;

    public Empelado(@NonNull String idEmpleado, String CUIL, String DNI, String mensajeGeneral, String datosEmpleado, int anulado, int bajaAfip, String estado, int eliminado, String colorMensajeGeneral, String colorTextoMensajeGeneral, String urlImagen, String razonSocial, String codigoNFC) {
        this.idEmpleado = idEmpleado;
        this.CUIL = CUIL;
        this.DNI = DNI;
        this.mensajeGeneral = mensajeGeneral;
        this.datosEmpleado = datosEmpleado;
        this.anulado = anulado;
        this.bajaAfip = bajaAfip;
        this.estado = estado;
        this.eliminado = eliminado;
        this.colorMensajeGeneral = colorMensajeGeneral;
        this.colorTextoMensajeGeneral = colorTextoMensajeGeneral;
        this.urlImagen = urlImagen;
        this.razonSocial = razonSocial;
        this.codigoNFC = codigoNFC;
    }

    @NonNull
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(@NonNull String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCUIL() {
        return CUIL;
    }

    public void setCUIL(String CUIL) {
        this.CUIL = CUIL;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public String getDatosEmpleado() {
        return datosEmpleado;
    }

    public void setDatosEmpleado(String datosEmpleado) {
        this.datosEmpleado = datosEmpleado;
    }

    public int getAnulado() {
        return anulado;
    }

    public void setAnulado(int anulado) {
        this.anulado = anulado;
    }

    public int getBajaAfip() {
        return bajaAfip;
    }

    public void setBajaAfip(int bajaAfip) {
        this.bajaAfip = bajaAfip;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    public String getColorMensajeGeneral() {
        return colorMensajeGeneral;
    }

    public void setColorMensajeGeneral(String colorMensajeGeneral) {
        this.colorMensajeGeneral = colorMensajeGeneral;
    }

    public String getColorTextoMensajeGeneral() {
        return colorTextoMensajeGeneral;
    }

    public void setColorTextoMensajeGeneral(String colorTextoMensajeGeneral) {
        this.colorTextoMensajeGeneral = colorTextoMensajeGeneral;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCodigoNFC() {
        return codigoNFC;
    }

    public void setCodigoNFC(String codigoNFC) {
        this.codigoNFC = codigoNFC;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
}
