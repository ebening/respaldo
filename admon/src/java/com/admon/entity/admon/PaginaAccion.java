package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class PaginaAccion implements Serializable {

    private Integer funcionalidadId;
    private String nombre;
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private Timestamp modificacionFecha;
    private Integer modificacionUsuario;
    private Integer estatusId;
    private Integer eliminadoId;

    /*
*Constructor de la clase Usuario.java
     */
    public PaginaAccion() {
    }

    /**
     * @return the funcionalidadId
     */
    public Integer getFuncionalidadId() {
        return funcionalidadId;
    }

    /**
     * @param funcionalidadId the funcionalidadId to set
     */
    public void setFuncionalidadId(Integer funcionalidadId) {
        this.funcionalidadId = funcionalidadId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
}
