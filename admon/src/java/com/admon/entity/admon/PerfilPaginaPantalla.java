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
public class PerfilPaginaPantalla implements Serializable {
    
    private Long perfilPaginaPantallaId;
    private Integer perfilPaginaId;
    private Long funcionalidadId;
    private Integer paginaId;
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private Timestamp modificacionFecha;
    private Integer modificacionUsuario;
    private String pantalla;
    private String patern;
    private String actionName;

    /**
     * @return the perfilPaginaPantallaId
     */
    public Long getPerfilPaginaPantallaId() {
        return perfilPaginaPantallaId;
    }

    /**
     * @param perfilPaginaPantallaId the perfilPaginaPantallaId to set
     */
    public void setPerfilPaginaPantallaId(Long perfilPaginaPantallaId) {
        this.perfilPaginaPantallaId = perfilPaginaPantallaId;
    }

    /**
     * @return the perfilPaginaId
     */
    public Integer getPerfilPaginaId() {
        return perfilPaginaId;
    }

    /**
     * @param perfilPaginaId the perfilPaginaId to set
     */
    public void setPerfilPaginaId(Integer perfilPaginaId) {
        this.perfilPaginaId = perfilPaginaId;
    }

    /**
     * @return the funcionalidadId
     */
    public Long getFuncionalidadId() {
        return funcionalidadId;
    }

    /**
     * @param funcionalidadId the funcionalidadId to set
     */
    public void setFuncionalidadId(Long funcionalidadId) {
        this.funcionalidadId = funcionalidadId;
    }

    /**
     * @return the paginaId
     */
    public Integer getPaginaId() {
        return paginaId;
    }

    /**
     * @param paginaId the paginaId to set
     */
    public void setPaginaId(Integer paginaId) {
        this.paginaId = paginaId;
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
     * @return the pantalla
     */
    public String getPantalla() {
        return pantalla;
    }

    /**
     * @param pantalla the pantalla to set
     */
    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    /**
     * @return the patern
     */
    public String getPatern() {
        return patern;
    }

    /**
     * @param patern the patern to set
     */
    public void setPatern(String patern) {
        this.patern = patern;
    }

    /**
     * @return the actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    
    
}
