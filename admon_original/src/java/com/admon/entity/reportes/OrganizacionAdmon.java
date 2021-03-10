package com.admon.entity.reportes;

import java.io.Serializable;

public class OrganizacionAdmon implements Serializable {

    private Integer organizacionAdmonId;
    private String nombre;
    private Integer eliminadoId;
    private Integer habilitado;

    public OrganizacionAdmon() {
    }

    public Integer getOrganizacionAdmonId() {
        return organizacionAdmonId;
    }

    public void setOrganizacionAdmonId(Integer organizacionAdmonId) {
        this.organizacionAdmonId = organizacionAdmonId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEliminadoId() {
        return eliminadoId;
    }

    public void setEliminadoId(Integer eliminadoId) {
        this.eliminadoId = eliminadoId;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

}
