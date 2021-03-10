package com.admon.entity.admon;

import java.io.Serializable;
import java.sql.Timestamp;

public class PerfilAplicacion implements Serializable {

    private Integer usuarioId;
    private String usuario;
    private Integer seguridadPerfilId;
    private String seguridadPerfil;
    private Integer aplicacionId;
    private String aplicacion;
    private Integer organizacionId;
    private Timestamp creacionFecha;
    private Integer creacionUsuario;
    private Timestamp modificacionFecha;
    private Integer modificacionUsuario;
    private String usuarioModificacion;
    private String descripcion;
    /*
    *Constructor de la clase PerfilAplicacion.java
     */
    public PerfilAplicacion() {
    }

    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }


    /**
     * @return the aplicacionId
     */
    public Integer getAplicacionId() {
        return aplicacionId;
    }

    /**
     * @param aplicacionId the aplicacionId to set
     */
    public void setAplicacionId(Integer aplicacionId) {
        this.aplicacionId = aplicacionId;
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
     * @return the organizacionId
     */
    public Integer getOrganizacionId() {
        return organizacionId;
    }

    /**
     * @param organizacionId the organizacionId to set
     */
    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    /**
     * @return the aplicacion
     */
    public String getAplicacion() {
        return aplicacion;
    }
    

    /**
     * @param aplicacion the aplicacion to set
     */
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the seguridadPerfilId
     */
    public Integer getSeguridadPerfilId() {
        return seguridadPerfilId;
    }

    /**
     * @param seguridadPerfilId the seguridadPerfilId to set
     */
    public void setSeguridadPerfilId(Integer seguridadPerfilId) {
        this.seguridadPerfilId = seguridadPerfilId;
    }

    /**
     * @return the seguridadPerfil
     */
    public String getSeguridadPerfil() {
        return seguridadPerfil;
    }

    /**
     * @param seguridadPerfil the seguridadPerfil to set
     */
    public void setSeguridadPerfil(String seguridadPerfil) {
        this.seguridadPerfil = seguridadPerfil;
    }
    
    
}
