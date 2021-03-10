package com.admon.model.admon;

import com.admon.bss.admon.SeguridadPerfilBss;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class SeguridadPerfilAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private SeguridadPerfilBss seguridadPerfilBss;

    private List aplicacion =new ArrayList();
    private List estatusSeguridadPerfil = new ArrayList();
    private List eliminadoSeguridadPerfil = new ArrayList();
    private List lenguajes = new ArrayList();
    private List rol;
    // Elementos de struts2 para el JSP (Embebido)
    private String gridVisibleSeguridadPerfil = new String();
    private String gridIndividualModeSeguridadPerfil = new String();
    
    private String gridVisibleSeguridadPerfilRol = new String();
    private String gridIndividualModeSeguridadPerfilRol = new String();
    private String nombreActionMenu = new String();

    public SeguridadPerfilAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página seguridadPerfil.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(seguridadPerfilBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        setRol(new ArrayList());
        // Obtener los parámetros del catálogo Aplicación
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setAplicacion(seguridadPerfilBss.getAplicaciones());
        // Obtener los parámetros del catálogo Estatus
        setEstatusSeguridadPerfil(seguridadPerfilBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoSeguridadPerfil(seguridadPerfilBss.getParametros("key_eliminado"));
        setLenguajes(seguridadPerfilBss.getLenguajes());
        // </editor-fold>
        setGridVisibleSeguridadPerfil(seguridadPerfilBss.hasGrid());
        setGridIndividualModeSeguridadPerfil(seguridadPerfilBss.isIndividual());
        setGridVisibleSeguridadPerfilRol(seguridadPerfilBss.hasGridPerfilRol());
        setGridIndividualModeSeguridadPerfilRol(seguridadPerfilBss.isIndividualPerfilRol());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
      /**
     * @return the aplicacion
     */
    public List getAplicacion() {
        return aplicacion;
    }

    /**
     * @param aplicacion the aplicacion to set
     */
    public void setAplicacion(List aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * @return the estatusSeguridadPerfil
     */
    public List getEstatusSeguridadPerfil() {
        return estatusSeguridadPerfil;
    }

    /**
     * @param estatusSeguridadPerfil the estatusSeguridadPerfil to set
     */
    public void setEstatusSeguridadPerfil(List estatusSeguridadPerfil) {
        this.estatusSeguridadPerfil = estatusSeguridadPerfil;
    }

    /**
     * @return the eliminadoSeguridadPerfil
     */
    public List getEliminadoSeguridadPerfil() {
        return eliminadoSeguridadPerfil;
    }

    /**
     * @param eliminadoSeguridadPerfil the eliminadoSeguridadPerfil to set
     */
    public void setEliminadoSeguridadPerfil(List eliminadoSeguridadPerfil) {
        this.eliminadoSeguridadPerfil = eliminadoSeguridadPerfil;
    }

    /**
     * @return the roles
     */
    public List getRol() {
        return rol;
    }

    /**
     * @param roles the roles to set
     */
    public void setRol(List rol) {
        this.rol = rol;
    }

    /**
     * @return the gridVisibleSeguridadPerfil
     */
    public String getGridVisibleSeguridadPerfil() {
        return gridVisibleSeguridadPerfil;
    }

    /**
     * @param gridVisibleSeguridadPerfil the gridVisibleSeguridadPerfil to set
     */
    public void setGridVisibleSeguridadPerfil(String gridVisibleSeguridadPerfil) {
        this.gridVisibleSeguridadPerfil = gridVisibleSeguridadPerfil;
    }

    /**
     * @return the gridIndividualModeSeguridadPerfil
     */
    public String getGridIndividualModeSeguridadPerfil() {
        return gridIndividualModeSeguridadPerfil;
    }

    /**
     * @param gridIndividualModeSeguridadPerfil the gridIndividualModeSeguridadPerfil to set
     */
    public void setGridIndividualModeSeguridadPerfil(String gridIndividualModeSeguridadPerfil) {
        this.gridIndividualModeSeguridadPerfil = gridIndividualModeSeguridadPerfil;
    }


    /**
     * @return the gridVisibleSeguridadPerfilRol
     */
    public String getGridVisibleSeguridadPerfilRol() {
        return gridVisibleSeguridadPerfilRol;
    }

    /**
     * @param gridVisibleSeguridadPerfilRol the gridVisibleSeguridadPerfilRol to set
     */
    public void setGridVisibleSeguridadPerfilRol(String gridVisibleSeguridadPerfilRol) {
        this.gridVisibleSeguridadPerfilRol = gridVisibleSeguridadPerfilRol;
    }

    /**
     * @return the gridIndividualModeSeguridadPerfilRol
     */
    public String getGridIndividualModeSeguridadPerfilRol() {
        return gridIndividualModeSeguridadPerfilRol;
    }

    /**
     * @param gridIndividualModeSeguridadPerfilRol the gridIndividualModeSeguridadPerfilRol to set
     */
    public void setGridIndividualModeSeguridadPerfilRol(String gridIndividualModeSeguridadPerfilRol) {
        this.gridIndividualModeSeguridadPerfilRol = gridIndividualModeSeguridadPerfilRol;
    }


    /**
     * @return the nombreActionMenu
     */
    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    /**
     * @param nombreActionMenu the nombreActionMenu to set
     */
    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }
    
    public List getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List lenguajes) {
        this.lenguajes = lenguajes;
    }

       /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadPerfilBss(SeguridadPerfilBss seguridadRolBss) {
        this.seguridadPerfilBss = seguridadRolBss;
    }

}
