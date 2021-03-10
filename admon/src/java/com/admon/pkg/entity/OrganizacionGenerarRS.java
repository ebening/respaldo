package com.admon.pkg.entity;

import java.io.Serializable;

public class OrganizacionGenerarRS implements Serializable {

    private Integer organizacionGenerarId;
    private Integer organizacionId;
    private String descripcion;

    /*
     *Constructor de la clase EstadoCuentaRS.java
     */
    public OrganizacionGenerarRS() {
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public Integer getOrganizacionGenerarId() {
        return organizacionGenerarId;
    }

    public void setOrganizacionGenerarId(Integer organizacionGenerarId) {
        this.organizacionGenerarId = organizacionGenerarId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}