package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class ParcializacionBanamex implements Serializable {
    private Integer parcializacionBanamexId;
    private String afiliacionBanamex;
    private String claveInstitucionBancaria;
    private String plan;
    private String claveEstatus;
    private Integer plazo;
    private Integer creacionUsuario;
    private Integer modificacionUsuario;
    private String usuarioModificacion;
    private Timestamp creacionFecha;
    private Timestamp modificacionFecha;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp creacionFechaInicial;
    private Timestamp creacionFechaFinal;
    // Campos auxiliares para busquedas por rango de fechas
    private Timestamp modificacionFechaInicial;
    private Timestamp modificacionFechaFinal;
    
   

    /*
*Constructor de la clase Comision.java
     */
    public ParcializacionBanamex() {
    }

     /**
     * @return the parcializacionBanamexId
     */
    public Integer getParcializacionBanamexId() {
        return parcializacionBanamexId;
    }

    /**
     * @param parcializacionBanamexId the parcializacionBanamexId to set
     */
    public void setParcializacionBanamexId(Integer parcializacionBanamexId) {
        this.parcializacionBanamexId = parcializacionBanamexId;
    }
    

    /**
     * @return the afiliacionBanamex
     */
    public String getAfiliacionBanamex() {
        return afiliacionBanamex;
    }

    /**
     * @param afiliacionBanamex the afiliacionBanamex to set
     */
    public void setAfiliacionBanamex(String afiliacionBanamex) {
        this.afiliacionBanamex = afiliacionBanamex;
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
     * @return the plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * @param plan the planId to set
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * @return the claveEstatus
     */
    public String getClaveEstatus() {
        return claveEstatus;
    }

    /**
     * @param claveEstatus the claveEstatus to set
     */
    public void setClaveEstatus(String claveEstatus) {
        this.claveEstatus = claveEstatus;
    }

    /**
     * @return the plazo
     */
    public Integer getPlazo() {
        return plazo;
    }

    /**
     * @param plazo the plazo to set
     */
    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
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
    
}
