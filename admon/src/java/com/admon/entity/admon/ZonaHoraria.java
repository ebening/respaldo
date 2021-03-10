/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.entity.admon;

import java.io.Serializable;

/**
 *
 * @author edgar
 */
public class ZonaHoraria implements Serializable {
    
    private Integer zonaParametroId;
    private Integer zonaId;
    private String nombre;
    private Integer lenguajeId;

    public Integer getZonaParametroId() {
        return zonaParametroId;
    }

    public void setZonaParametroId(Integer zonaParametroId) {
        this.zonaParametroId = zonaParametroId;
    }

    public Integer getZonaId() {
        return zonaId;
    }

    public void setZonaId(Integer zonaId) {
        this.zonaId = zonaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public Integer getLenguajeId() {
        return lenguajeId;
    }
    
    void setLenguajeId(Integer lenguajeId) {
        this.lenguajeId = lenguajeId;
    }
    
    
}
