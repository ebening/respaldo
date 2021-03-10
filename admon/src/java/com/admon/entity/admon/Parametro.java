package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class Parametro implements Serializable {

    private Long parametroId;
    private Long fkId;
    private String nombre;
    private Integer lenguajeId;
    
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private Timestamp modificacionFecha;
    private Integer modificacionUsuario;
    private Integer estatusId;
    private Integer eliminadoId;
       
    private String estatus;
    private String eliminado;
    private String usuarioModificacion;
    private String lenguaje;
    
    

    /*
    *Constructor de la clase Aplicacion.java
     */
    public Parametro() {
    }
    public Long getParametroId() {
        return parametroId;
    }

    public void setParametroId(Long parametroId) {
        this.parametroId = parametroId;
    }

    public Long getFkId() {
        return fkId;
    }

    public void setFkId(Long fkId) {
        this.fkId = fkId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public Integer getEliminadoId() {
        return eliminadoId;
    }

    public void setEliminadoId(Integer eliminadoId) {
        this.eliminadoId = eliminadoId;
    }

    public Timestamp getCreacionFecha() {
        return creacionFecha;
    }

    public void setCreacionFecha(Timestamp creacionFecha) {
        this.creacionFecha = creacionFecha;
    }

    public Integer getCreacionUsuario() {
        return creacionUsuario;
    }

    public void setCreacionUsuario(Integer creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }

    public Timestamp getModificacionFecha() {
        return modificacionFecha;
    }

    public void setModificacionFecha(Timestamp modificacionFecha) {
        this.modificacionFecha = modificacionFecha;
    }

    public Integer getModificacionUsuario() {
        return modificacionUsuario;
    }

    public void setModificacionUsuario(Integer modificacionUsuario) {
        this.modificacionUsuario = modificacionUsuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Integer getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(Integer lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

}
