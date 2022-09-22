
package com.app.infocontrol.data.pojo.response.ResponseVehiculos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVehiculos {
    @SerializedName("cuit")
    @Expose
    private String cuit;
    @SerializedName("id_empresas")
    @Expose
    private String idEmpresas;
    @SerializedName("id_proveedores")
    @Expose
    private String idProveedores;
    @SerializedName("nombre_razon_social")
    @Expose
    private String nombreRazonSocial;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("valor")
    @Expose
    private String valor;
    @SerializedName("id_entidad")
    @Expose
    private String idEntidad;
    @SerializedName("modelo_vehiculos")
    @Expose
    private String modeloVehiculos;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("mensaje_general")
    @Expose
    private String mensajeGeneral;
    @SerializedName("anulado")
    @Expose
    private String anulado;
    @SerializedName("numero_serie")
    @Expose
    private String numeroSerie;
    @SerializedName("numero_chasis")
    @Expose
    private String numeroChasis;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("estado_doc")
    @Expose
    private String estadoDoc;
    @SerializedName("estado_admin")
    @Expose
    private String estadoAdmin;
    @SerializedName("estado_generales")
    @Expose
    private String estadoGenerales;
    @SerializedName("estado_impositivas")
    @Expose
    private String estadoImpositivas;
    @SerializedName("estado_especificas")
    @Expose
    private String estadoEspecificas;
    @SerializedName("estado_laborales")
    @Expose
    private String estadoLaborales;
    @SerializedName("estado_generales_doc")
    @Expose
    private String estadoGeneralesDoc;
    @SerializedName("estado_impositivas_doc")
    @Expose
    private String estadoImpositivasDoc;
    @SerializedName("estado_especificas_doc")
    @Expose
    private String estadoEspecificasDoc;
    @SerializedName("estado_laborales_doc")
    @Expose
    private String estadoLaboralesDoc;
    @SerializedName("estado_extra")
    @Expose
    private String estadoExtra;
    @SerializedName("estado_hys")
    @Expose
    private String estadoHys;
    @SerializedName("estado_hys_doc")
    @Expose
    private String estadoHysDoc;
    @SerializedName("motivo_estado")
    @Expose
    private String motivoEstado;
    @SerializedName("imagen_array")
    @Expose
    private String imagenArray;
    @SerializedName("imagen_path")
    @Expose
    private String imagenPath;
    @SerializedName("mensaje_general_color")
    @Expose
    private String mensajeGeneralColor;
    @SerializedName("mensaje_general_color_text")
    @Expose
    private String mensajeGeneralColorText;
    @SerializedName("datos")
    @Expose
    private String datos;
    @SerializedName("eliminado")
    @Expose
    private String eliminado;

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(String idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public String getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(String idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getModeloVehiculos() {
        return modeloVehiculos;
    }

    public void setModeloVehiculos(String modeloVehiculos) {
        this.modeloVehiculos = modeloVehiculos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoDoc() {
        return estadoDoc;
    }

    public void setEstadoDoc(String estadoDoc) {
        this.estadoDoc = estadoDoc;
    }

    public String getEstadoAdmin() {
        return estadoAdmin;
    }

    public void setEstadoAdmin(String estadoAdmin) {
        this.estadoAdmin = estadoAdmin;
    }

    public String getEstadoGenerales() {
        return estadoGenerales;
    }

    public void setEstadoGenerales(String estadoGenerales) {
        this.estadoGenerales = estadoGenerales;
    }

    public String getEstadoImpositivas() {
        return estadoImpositivas;
    }

    public void setEstadoImpositivas(String estadoImpositivas) {
        this.estadoImpositivas = estadoImpositivas;
    }

    public String getEstadoEspecificas() {
        return estadoEspecificas;
    }

    public void setEstadoEspecificas(String estadoEspecificas) {
        this.estadoEspecificas = estadoEspecificas;
    }

    public String getEstadoLaborales() {
        return estadoLaborales;
    }

    public void setEstadoLaborales(String estadoLaborales) {
        this.estadoLaborales = estadoLaborales;
    }

    public String getEstadoGeneralesDoc() {
        return estadoGeneralesDoc;
    }

    public void setEstadoGeneralesDoc(String estadoGeneralesDoc) {
        this.estadoGeneralesDoc = estadoGeneralesDoc;
    }

    public String getEstadoImpositivasDoc() {
        return estadoImpositivasDoc;
    }

    public void setEstadoImpositivasDoc(String estadoImpositivasDoc) {
        this.estadoImpositivasDoc = estadoImpositivasDoc;
    }

    public String getEstadoEspecificasDoc() {
        return estadoEspecificasDoc;
    }

    public void setEstadoEspecificasDoc(String estadoEspecificasDoc) {
        this.estadoEspecificasDoc = estadoEspecificasDoc;
    }

    public String getEstadoLaboralesDoc() {
        return estadoLaboralesDoc;
    }

    public void setEstadoLaboralesDoc(String estadoLaboralesDoc) {
        this.estadoLaboralesDoc = estadoLaboralesDoc;
    }

    public String getEstadoExtra() {
        return estadoExtra;
    }

    public void setEstadoExtra(String estadoExtra) {
        this.estadoExtra = estadoExtra;
    }

    public String getEstadoHys() {
        return estadoHys;
    }

    public void setEstadoHys(String estadoHys) {
        this.estadoHys = estadoHys;
    }

    public String getEstadoHysDoc() {
        return estadoHysDoc;
    }

    public void setEstadoHysDoc(String estadoHysDoc) {
        this.estadoHysDoc = estadoHysDoc;
    }

    public String getMotivoEstado() {
        return motivoEstado;
    }

    public void setMotivoEstado(String motivoEstado) {
        this.motivoEstado = motivoEstado;
    }

    public String getImagenArray() {
        return imagenArray;
    }

    public void setImagenArray(String imagenArray) {
        this.imagenArray = imagenArray;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getMensajeGeneralColor() {
        return mensajeGeneralColor;
    }

    public void setMensajeGeneralColor(String mensajeGeneralColor) {
        this.mensajeGeneralColor = mensajeGeneralColor;
    }

    public String getMensajeGeneralColorText() {
        return mensajeGeneralColorText;
    }

    public void setMensajeGeneralColorText(String mensajeGeneralColorText) {
        this.mensajeGeneralColorText = mensajeGeneralColorText;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }
}