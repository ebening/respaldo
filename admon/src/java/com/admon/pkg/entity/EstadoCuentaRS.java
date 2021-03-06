package com.admon.pkg.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class EstadoCuentaRS implements Serializable {

    // Campos auxiliares para busquedas
    private Date fechaPagoInicio;
    private Date fechaPagoFin;
    private Integer organizacionId;
    private Integer eventoId;
    private Integer presentacionId;

    private Integer identificador;
    private String nombreOrganizacion;
    private String nombreComercial;
    private String RFC;
    private String domicilio;
    private String nombreEvento;
    private String nombrePresentacion;
    private Integer noBoletos;
    private Integer noCortesias;
    private Integer noCancelaciones;
    private Integer noBoletoDuro;
    private Double total;
    private Double monto;
    private Double descuento;
    private Double cargoXServicio;
    private Double comisionTaasgo;
    private String tipoBoleto;
    private Double subTotal;
    private Double granTotal;
    private Integer configuracionId;
    private String ivaKey;
    
    /*
     *Constructor de la clase EstadoCuentaRS.java
     */
    private List<EstadoCuentaDetalleRS> listEstadoCuentaRS;

    public EstadoCuentaRS() {
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Integer getNoBoletos() {
        return noBoletos;
    }

    public void setNoBoletos(Integer noBoletos) {
        this.noBoletos = noBoletos;
    }

    public Integer getNoCortesias() {
        return noCortesias;
    }

    public void setNoCortesias(Integer noCortesias) {
        this.noCortesias = noCortesias;
    }

    public Integer getNoCancelaciones() {
        return noCancelaciones;
    }

    public void setNoCancelaciones(Integer noCancelaciones) {
        this.noCancelaciones = noCancelaciones;
    }

    public Integer getNoBoletoDuro() {
        return noBoletoDuro;
    }

    public void setNoBoletoDuro(Integer noBoletoDuro) {
        this.noBoletoDuro = noBoletoDuro;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getCargoXServicio() {
        return cargoXServicio;
    }

    public void setCargoXServicio(Double cargoXServicio) {
        this.cargoXServicio = cargoXServicio;
    }

    public Double getComisionTaasgo() {
        return comisionTaasgo;
    }

    public void setComisionTaasgo(Double comisionTaasgo) {
        this.comisionTaasgo = comisionTaasgo;
    }

    public Date getFechaPagoInicio() {
        return fechaPagoInicio;
    }

    public void setFechaPagoInicio(Date fechaPagoInicio) {
        this.fechaPagoInicio = fechaPagoInicio;
    }

    public Date getFechaPagoFin() {
        return fechaPagoFin;
    }

    public void setFechaPagoFin(Date fechaPagoFin) {
        this.fechaPagoFin = fechaPagoFin;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public List<EstadoCuentaDetalleRS> getListEstadoCuentaRS() {
        return listEstadoCuentaRS;
    }

    public void setListEstadoCuentaRS(List<EstadoCuentaDetalleRS> listEstadoCuentaRS) {
        this.listEstadoCuentaRS = listEstadoCuentaRS;
    }

    public String getTipoBoleto() {
        return tipoBoleto;
    }

    public void setTipoBoleto(String tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getGranTotal() {
        return granTotal;
    }

    public void setGranTotal(Double granTotal) {
        this.granTotal = granTotal;
    }
    
    public String getNombrePresentacion() {
        return nombrePresentacion;
    }

    public void setNombrePresentacion(String nombrePresentacion) {
        this.nombrePresentacion = nombrePresentacion;
    }

    public Integer getPresentacionId() {
        return presentacionId;
    }

    public void setPresentacionId(Integer presentacionId) {
        this.presentacionId = presentacionId;
    }

    public Integer getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(Integer configuracionId) {
        this.configuracionId = configuracionId;
    }

    public String getIvaKey() {
        return ivaKey;
    }

    public void setIvaKey(String ivaKey) {
        this.ivaKey = ivaKey;
    }
    
    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

}