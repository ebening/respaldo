package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class AfiliacionBanamex implements Serializable {
    private Integer afiliacionBanamexId;
    private String afiliacionBanamex;
    private String url;
    private String versionId;
    private String version;
    private String command;
    private String accessCode;
    private String merchant;
    private String returnUrl;
    private Integer currency;
    private String secureScret;
    private String actionUrl;
    private String claveInstitucionBancaria;
    private Integer validaBanamex;
    private Integer trnCashbackAmount;
    private Integer trnTipAmount;
    private Timestamp creacionFecha;
    private Timestamp modificacionFecha;
    private Integer creacionUsuario;
    private Integer modificacionUsuario;
    private String usuarioModificacion;
    private String urlCancelacion;
    private String urlDevolucion;
    private String trnCurId;
    private String nombreEmpresa;
    private Integer afiliacionDefault;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp creacionFechaInicial;
    private Timestamp creacionFechaFinal;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp modificacionFechaInicial;
    private Timestamp modificacionFechaFinal;
    private Integer eliminadoId;
    private String eliminado;
    private Integer estatusId;
    private String estatus;
    private Integer canalVentaId;
    private Integer tipoCanalVentaId;
    private String canalVenta;
    private String tipoCanalVenta;
    private String tieneParcializacion;
    private String tieneTerminales;
    
   

    /*
*Constructor de la clase Comision.java
     */
    public AfiliacionBanamex() {
    }

    /**
     * @return the afiliacion
     */
    public String getAfiliacionBanamex() {
        return afiliacionBanamex;
    }

    /**
     * @param afiliacion the afiliacion to set
     */
    public void setAfiliacionBanamex(String afiliacion) {
        this.afiliacionBanamex = afiliacion;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the accessCode
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * @param accessCode the accessCode to set
     */
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    /**
     * @return the merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the returnUrl
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * @param returnUrl the returnUrl to set
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    /**
     * @return the secureScret
     */
    public String getSecureScret() {
        return secureScret;
    }

    /**
     * @param secureScret the secureScret to set
     */
    public void setSecureScret(String secureScret) {
        this.secureScret = secureScret;
    }

    /**
     * @return the actionUrl
     */
    public String getActionUrl() {
        return actionUrl;
    }

    /**
     * @param actionUrl the actionUrl to set
     */
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    /**
     * @return the claveInstitucionBancaria
     */
    public String getClaveInstitucionBancaria() {
        return claveInstitucionBancaria;
    }

    /**
     * @param claveInstitucionBancaria the claveInstitucionBancaria to set
     */
    public void setClaveInstitucionBancaria(String claveInstitucionBancaria) {
        this.claveInstitucionBancaria = claveInstitucionBancaria;
    }

    /**
     * @return the validaBanamex
     */
    public Integer getValidaBanamex() {
        return validaBanamex;
    }

    /**
     * @param validaBanamex the validaBanamex to set
     */
    public void setValidaBanamex(Integer validaBanamex) {
        this.validaBanamex = validaBanamex;
    }

    /**
     * @return the trnCashbackAmount
     */
    public Integer getTrnCashbackAmount() {
        return trnCashbackAmount;
    }

    /**
     * @param trnCashbackAmount the trnCashbackAmount to set
     */
    public void setTrnCashbackAmount(Integer trnCashbackAmount) {
        this.trnCashbackAmount = trnCashbackAmount;
    }

    /**
     * @return the trnTipAmount
     */
    public Integer getTrnTipAmount() {
        return trnTipAmount;
    }

    /**
     * @param trnTipAmount the trnTipAmount to set
     */
    public void setTrnTipAmount(Integer trnTipAmount) {
        this.trnTipAmount = trnTipAmount;
    }

    /**
     * @return the creacionFecha
     */
    public Timestamp getCreacionFecha() {
        return creacionFecha;
    }

    /**
     * @param creacionFecha the creacionFecha to set
     */
    public void setCreacionFecha(Timestamp creacionFecha) {
        this.creacionFecha = creacionFecha;
    }

    /**
     * @return the modificacionFecha
     */
    public Timestamp getModificacionFecha() {
        return modificacionFecha;
    }

    /**
     * @param modificacionFecha the modificacionFecha to set
     */
    public void setModificacionFecha(Timestamp modificacionFecha) {
        this.modificacionFecha = modificacionFecha;
    }

    /**
     * @return the creacionUsuario
     */
    public Integer getCreacionUsuario() {
        return creacionUsuario;
    }

    /**
     * @param creacionUsuario the creacionUsuario to set
     */
    public void setCreacionUsuario(Integer creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }

    /**
     * @return the modificacionUsuario
     */
    public Integer getModificacionUsuario() {
        return modificacionUsuario;
    }

    /**
     * @param modificacionUsuario the modificacionUsuario to set
     */
    public void setModificacionUsuario(Integer modificacionUsuario) {
        this.modificacionUsuario = modificacionUsuario;
    }

    /**
     * @return the usuarioModificacion
     */
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * @param usuarioModificacion the usuarioModificacion to set
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * @return the urlCancelacion
     */
    public String getUrlCancelacion() {
        return urlCancelacion;
    }

    /**
     * @param urlCancelacion the urlCancelacion to set
     */
    public void setUrlCancelacion(String urlCancelacion) {
        this.urlCancelacion = urlCancelacion;
    }

    /**
     * @return the urlDevolucion
     */
    public String getUrlDevolucion() {
        return urlDevolucion;
    }

    /**
     * @param urlDevolucion the urlDevolucion to set
     */
    public void setUrlDevolucion(String urlDevolucion) {
        this.urlDevolucion = urlDevolucion;
    }

    /**
     * @return the trnCurId
     */
    public String getTrnCurId() {
        return trnCurId;
    }

    /**
     * @param trnCurId the trnCurId to set
     */
    public void setTrnCurId(String trnCurId) {
        this.trnCurId = trnCurId;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the creacionFechaInicial
     */
    public Timestamp getCreacionFechaInicial() {
        return creacionFechaInicial;
    }

    /**
     * @param creacionFechaInicial the creacionFechaInicial to set
     */
    public void setCreacionFechaInicial(Timestamp creacionFechaInicial) {
        this.creacionFechaInicial = creacionFechaInicial;
    }

    /**
     * @return the creacionFechaFinal
     */
    public Timestamp getCreacionFechaFinal() {
        return creacionFechaFinal;
    }

    /**
     * @param creacionFechaFinal the creacionFechaFinal to set
     */
    public void setCreacionFechaFinal(Timestamp creacionFechaFinal) {
        this.creacionFechaFinal = creacionFechaFinal;
    }

    /**
     * @return the modificacionFechaInicial
     */
    public Timestamp getModificacionFechaInicial() {
        return modificacionFechaInicial;
    }

    /**
     * @param modificacionFechaInicial the modificacionFechaInicial to set
     */
    public void setModificacionFechaInicial(Timestamp modificacionFechaInicial) {
        this.modificacionFechaInicial = modificacionFechaInicial;
    }

    /**
     * @return the modificacionFechaFinal
     */
    public Timestamp getModificacionFechaFinal() {
        return modificacionFechaFinal;
    }

    /**
     * @param modificacionFechaFinal the modificacionFechaFinal to set
     */
    public void setModificacionFechaFinal(Timestamp modificacionFechaFinal) {
        this.modificacionFechaFinal = modificacionFechaFinal;
    }

    /**
     * @return the eliminadoId
     */
    public Integer getEliminadoId() {
        return eliminadoId;
    }

    /**
     * @param eliminadoId the eliminadoId to set
     */
    public void setEliminadoId(Integer eliminadoId) {
        this.eliminadoId = eliminadoId;
    }

    /**
     * @return the eliminado
     */
    public String getEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }
    /**
     * @return the afiliacionBanamexId
     */
    public Integer getAfiliacionBanamexId() {
        return afiliacionBanamexId;
    }

    /**
     * @param afiliacionBanamexId the afiliacionBanamexId to set
     */
    public void setAfiliacionBanamexId(Integer afiliacionBanamexId) {
        this.afiliacionBanamexId = afiliacionBanamexId;
    }

    /**
     * @return the estatusId
     */
    public Integer getEstatusId() {
        return estatusId;
    }

    /**
     * @param estatusId the estatusId to set
     */
    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the canalVentaId
     */
    public Integer getCanalVentaId() {
        return canalVentaId;
    }

    /**
     * @param canalVentaId the canalVentaId to set
     */
    public void setCanalVentaId(Integer canalVentaId) {
        this.canalVentaId = canalVentaId;
    }

    /**
     * @return the tipoCanalVentaId
     */
    public Integer getTipoCanalVentaId() {
        return tipoCanalVentaId;
    }

    /**
     * @param tipoCanalVentaId the tipoCanalVentaId to set
     */
    public void setTipoCanalVentaId(Integer tipoCanalVentaId) {
        this.tipoCanalVentaId = tipoCanalVentaId;
    }

    /**
     * @return the canalVenta
     */
    public String getCanalVenta() {
        return canalVenta;
    }

    /**
     * @param canalVenta the canalVenta to set
     */
    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    /**
     * @return the tipoCanalVenta
     */
    public String getTipoCanalVenta() {
        return tipoCanalVenta;
    }

    /**
     * @param tipoCanalVenta the tipoCanalVenta to set
     */
    public void setTipoCanalVenta(String tipoCanalVenta) {
        this.tipoCanalVenta = tipoCanalVenta;
    } 

    /**
     * @return the tieneParcializacion
     */
    public String getTieneParcializacion() {
        return tieneParcializacion;
    }

    /**
     * @param tieneParcializacion the tieneParcializacion to set
     */
    public void setTieneParcializacion(String tieneParcializacion) {
        this.tieneParcializacion = tieneParcializacion;
    }

    /**
     * @return the tieneTerminales
     */
    public String getTieneTerminales() {
        return tieneTerminales;
    }

    /**
     * @param tieneTerminales the tieneTerminales to set
     */
    public void setTieneTerminales(String tieneTerminales) {
        this.tieneTerminales = tieneTerminales;
    }

    /**
     * @return the versionId
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * @param versionId the versionId to set
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    /**
     * @return the currency
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * @return the afiliacionDefault
     */
    public Integer getAfiliacionDefault() {
        return afiliacionDefault;
    }

    /**
     * @param afiliacionDefault the afiliacionDefault to set
     */
    public void setAfiliacionDefault(Integer afiliacionDefault) {
        this.afiliacionDefault = afiliacionDefault;
    }
}
