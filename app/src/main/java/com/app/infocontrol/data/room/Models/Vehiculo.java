package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.app.infocontrol.commons.Constantes;


@Entity( tableName = Constantes.TABLA_VEHICULO)
public class Vehiculo {

    @PrimaryKey
    @NonNull
    private String idVehiculo;
    private String CUIT;
    private String valor;
    private String mensajeGeneral;
    private String estado;
    private String marca;
    private String datos;
    private int eliminado;
    private String colorMensajeGeneral;
    private String colorTextoMensajeGeneral;
    private String urlImagen;
    private String razonSocial;
    private String imagenBase64;

    public Vehiculo(@NonNull String idVehiculo, String CUIT, String valor, String mensajeGeneral, String estado, String marca, String datos, int eliminado, String colorMensajeGeneral, String colorTextoMensajeGeneral, String urlImagen, String razonSocial) {
        this.idVehiculo = idVehiculo;
        this.CUIT = CUIT;
        this.valor = valor;
        this.mensajeGeneral = mensajeGeneral;
        this.estado = estado;
        this.marca = marca;
        this.datos = datos;
        this.eliminado = eliminado;
        this.colorMensajeGeneral = colorMensajeGeneral;
        this.colorTextoMensajeGeneral = colorTextoMensajeGeneral;
        this.urlImagen = urlImagen;
        this.razonSocial = razonSocial;
    }

    @NonNull
    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(@NonNull String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
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

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
}
