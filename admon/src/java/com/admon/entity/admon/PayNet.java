package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class PayNet implements Serializable {
    private Integer payNetId;
    private Integer eventoId;
    private String afiliacion;
    private String userName;
    private String password;
    private String signature;
    private Timestamp creacionFecha;
    private Timestamp modificacionFecha;
    private Integer creacionUsuario;
    private Integer modificacionUsuario;
    private String usuarioModificacion;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp creacionFechaInicial;
    private Timestamp creacionFechaFinal;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp modificacionFechaInicial;
    private Timestamp modificacionFechaFinal;
    /*
*Constructor de la clase Comision.java
     */
    public PayNet() {
    }

    /**
     * @return the payNetId
     */
    public Integer getPayNetId() {
        return payNetId;
    }

    /**
     * @param payNetId the payNetId to set
     */
    public void setPayNetId(Integer payNetId) {
        this.payNetId = payNetId;
    }

    /**
     * @return the eventoId
     */
    public Integer getEventoId() {
        return eventoId;
    }

    /**
     * @param eventoId the eventoId to set
     */
    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    /**
     * @return the afiliacion
     */
    public String getAfiliacion() {
        return afiliacion;
    }

    /**
     * @param afiliacion the afiliacion to set
     */
    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
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
     * @return the afiliacion
     */
  
}
