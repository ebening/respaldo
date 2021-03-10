/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 *
 * @author edgar
 */
public class Bitacora implements Serializable {

    private Long bitacoraId;
    private String fechaHora;
    private String proceso;
    private String procedimiento;
    private Integer cantidad;
    private String mensaje;
    private Integer organizacion;
    private Long ejecucion;
    private Integer estatusId;

    public Bitacora(Long bitacoraId, String fechaHora, String proceso, String procedimiento, Integer cantidad, String mensaje, Integer organizacion, Long ejecucion, Integer estatusId) {
        this.bitacoraId = bitacoraId;
        this.fechaHora = fechaHora;
        this.proceso = proceso;
        this.procedimiento = procedimiento;
        this.cantidad = cantidad;
        this.mensaje = mensaje;
        this.organizacion = organizacion;
        this.ejecucion = ejecucion;
        this.estatusId = estatusId;
    }
    
    /**
     * @return the bitacoraId
     */
    public Long getBitacoraId() {
        return bitacoraId;
    }

    /**
     * @param bitacoraId the bitacoraId to set
     */
    public void setBitacoraId(Long bitacoraId) {
        this.bitacoraId = bitacoraId;
    }

    /**
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * @return the proceso
     */
    public String getProceso() {
        return proceso;
    }

    /**
     * @param proceso the proceso to set
     */
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    /**
     * @return the procedimiento
     */
    public String getProcedimiento() {
        return procedimiento;
    }

    /**
     * @param procedimiento the procedimiento to set
     */
    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the organizacionId
     */
    public Integer getOrganizacion() {
        return organizacion;
    }

    /**
     * @param organizacion the organizacionId to set
     */
    public void setOrganizacion(Integer organizacion) {
        this.organizacion = organizacion;
    }

    /**
     * @return the ejecucionId
     */
    public Long getEjecucion() {
        return ejecucion;
    }

    /**
     * @param ejecucion the ejecucionId to set
     */
    public void setEjecucion(Long ejecucion) {
        this.ejecucion = ejecucion;
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
    
    
}
