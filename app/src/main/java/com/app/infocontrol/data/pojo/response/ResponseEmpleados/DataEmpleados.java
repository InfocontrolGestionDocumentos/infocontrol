
package com.app.infocontrol.data.pojo.response.ResponseEmpleados;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEmpleados {

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
    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("cuil")
    @Expose
    private String cuil;
    @SerializedName("tipo_chofer")
    @Expose
    private String tipoChofer;
    @SerializedName("mensaje_general")
    @Expose
    private String mensajeGeneral;
    @SerializedName("anulado")
    @Expose
    private String anulado;
    @SerializedName("baja_afip")
    @Expose
    private String bajaAfip;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("convenio")
    @Expose
    private String convenio;
    @SerializedName("datos")
    @Expose
    private String datos;
    @SerializedName("fecha_ingreso")
    @Expose
    private String fechaIngreso;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("estado_doc")
    @Expose
    private String estadoDoc;
    @SerializedName("estado_admin")
    @Expose
    private Integer estadoAdmin;
    @SerializedName("estado_generales")
    @Expose
    private Integer estadoGenerales;
    @SerializedName("estado_impositivas")
    @Expose
    private Integer estadoImpositivas;
    @SerializedName("estado_especificas")
    @Expose
    private Integer estadoEspecificas;
    @SerializedName("estado_laborales")
    @Expose
    private Integer estadoLaborales;
    @SerializedName("estado_generales_doc")
    @Expose
    private Integer estadoGeneralesDoc;
    @SerializedName("estado_impositivas_doc")
    @Expose
    private Integer estadoImpositivasDoc;
    @SerializedName("estado_especificas_doc")
    @Expose
    private Integer estadoEspecificasDoc;
    @SerializedName("estado_laborales_doc")
    @Expose
    private Integer estadoLaboralesDoc;
    @SerializedName("estado_extra")
    @Expose
    private Integer estadoExtra;
    @SerializedName("estado_hys")
    @Expose
    private Integer estadoHys;
    @SerializedName("estado_hys_doc")
    @Expose
    private Integer estadoHysDoc;
    @SerializedName("tiene_tct_bt")
    @Expose
    private String tieneTctBt;
    @SerializedName("tiene_tct_mt")
    @Expose
    private String tieneTctMt;
    @SerializedName("tiene_tct_at")
    @Expose
    private String tieneTctAt;
    @SerializedName("realiza_tareas_poda")
    @Expose
    private String realizaTareasPoda;
    @SerializedName("casco_verde")
    @Expose
    private String cascoVerde;
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
    @SerializedName("eliminado")
    @Expose
    private String eliminado;
    @SerializedName("codigo_nfc")
    @Expose
    private String codigoNfc;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getTipoChofer() {
        return tipoChofer;
    }

    public void setTipoChofer(String tipoChofer) {
        this.tipoChofer = tipoChofer;
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

    public String getBajaAfip() {
        return bajaAfip;
    }

    public void setBajaAfip(String bajaAfip) {
        this.bajaAfip = bajaAfip;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    public Integer getEstadoAdmin() {
        return estadoAdmin;
    }

    public void setEstadoAdmin(Integer estadoAdmin) {
        this.estadoAdmin = estadoAdmin;
    }

    public Integer getEstadoGenerales() {
        return estadoGenerales;
    }

    public void setEstadoGenerales(Integer estadoGenerales) {
        this.estadoGenerales = estadoGenerales;
    }

    public Integer getEstadoImpositivas() {
        return estadoImpositivas;
    }

    public void setEstadoImpositivas(Integer estadoImpositivas) {
        this.estadoImpositivas = estadoImpositivas;
    }

    public Integer getEstadoEspecificas() {
        return estadoEspecificas;
    }

    public void setEstadoEspecificas(Integer estadoEspecificas) {
        this.estadoEspecificas = estadoEspecificas;
    }

    public Integer getEstadoLaborales() {
        return estadoLaborales;
    }

    public void setEstadoLaborales(Integer estadoLaborales) {
        this.estadoLaborales = estadoLaborales;
    }

    public Integer getEstadoGeneralesDoc() {
        return estadoGeneralesDoc;
    }

    public void setEstadoGeneralesDoc(Integer estadoGeneralesDoc) {
        this.estadoGeneralesDoc = estadoGeneralesDoc;
    }

    public Integer getEstadoImpositivasDoc() {
        return estadoImpositivasDoc;
    }

    public void setEstadoImpositivasDoc(Integer estadoImpositivasDoc) {
        this.estadoImpositivasDoc = estadoImpositivasDoc;
    }

    public Integer getEstadoEspecificasDoc() {
        return estadoEspecificasDoc;
    }

    public void setEstadoEspecificasDoc(Integer estadoEspecificasDoc) {
        this.estadoEspecificasDoc = estadoEspecificasDoc;
    }

    public Integer getEstadoLaboralesDoc() {
        return estadoLaboralesDoc;
    }

    public void setEstadoLaboralesDoc(Integer estadoLaboralesDoc) {
        this.estadoLaboralesDoc = estadoLaboralesDoc;
    }

    public Integer getEstadoExtra() {
        return estadoExtra;
    }

    public void setEstadoExtra(Integer estadoExtra) {
        this.estadoExtra = estadoExtra;
    }

    public Integer getEstadoHys() {
        return estadoHys;
    }

    public void setEstadoHys(Integer estadoHys) {
        this.estadoHys = estadoHys;
    }

    public Integer getEstadoHysDoc() {
        return estadoHysDoc;
    }

    public void setEstadoHysDoc(Integer estadoHysDoc) {
        this.estadoHysDoc = estadoHysDoc;
    }

    public String getTieneTctBt() {
        return tieneTctBt;
    }

    public void setTieneTctBt(String tieneTctBt) {
        this.tieneTctBt = tieneTctBt;
    }

    public String getTieneTctMt() {
        return tieneTctMt;
    }

    public void setTieneTctMt(String tieneTctMt) {
        this.tieneTctMt = tieneTctMt;
    }

    public String getTieneTctAt() {
        return tieneTctAt;
    }

    public void setTieneTctAt(String tieneTctAt) {
        this.tieneTctAt = tieneTctAt;
    }

    public String getRealizaTareasPoda() {
        return realizaTareasPoda;
    }

    public void setRealizaTareasPoda(String realizaTareasPoda) {
        this.realizaTareasPoda = realizaTareasPoda;
    }

    public String getCascoVerde() {
        return cascoVerde;
    }

    public void setCascoVerde(String cascoVerde) {
        this.cascoVerde = cascoVerde;
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

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getCodigoNfc() {
        return codigoNfc;
    }

    public void setCodigoNfc(String codigoNfc) {
        this.codigoNfc = codigoNfc;
    }
}
