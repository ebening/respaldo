package com.admon.model.admon;

import com.admon.bss.admon.AfiliacionBanamexBss;
import com.admon.bss.admon.CatalogoBss;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class AfiliacionBanamexAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private AfiliacionBanamexBss afiliacionBanamexBss;
    private CatalogoBss catalogoBss;
    private List tipoCanalList = new ArrayList();
    private List canalVentaList = new ArrayList();
    private List versionList = new ArrayList();
    private List tipoMonedaList = new ArrayList();
    // Elementos de struts2 para el JSP
    private List eliminadoAfiliacionBanamex = new ArrayList();
    private List estatusList = new ArrayList();
    // Elementos de struts2 para el JSP (Embebido)
    private String gridVisibleAfiliacionBanamex = new String();
    private String gridIndividualModeAfiliacionBanamex = new String();
    String nombreActionMenu = new String();

    public AfiliacionBanamexAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página perfil.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(afiliacionBanamexBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Eliminado
        setEstatusList(afiliacionBanamexBss.getParametros("key_estatus"));
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setCanalVentaList(catalogoBss.getCatalogosByClave("T_CANAL_VENTA"));
        setTipoCanalList(catalogoBss.getCatalogosByClave("CANAL_VENTA"));
        setTipoMonedaList(catalogoBss.getCatalogosByClave("TIPO_MONEDA_AFILIACIONES"));
        setVersionList(catalogoBss.getCatalogosByClave("VERSION_AFILIACIONES"));
        setEliminadoAfiliacionBanamex(afiliacionBanamexBss.getParametros("key_eliminado"));
        // </editor-fold>
        setGridVisibleAfiliacionBanamex(afiliacionBanamexBss.hasGrid());
        setGridIndividualModeAfiliacionBanamex(afiliacionBanamexBss.isIndividual());
        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    

    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }
    
      /**
     * @return the tipoCanalList
     */
    public List getTipoCanalList() {
        return tipoCanalList;
    }

    /**
     * @param tipoCanalList the tipoCanalList to set
     */
    public void setTipoCanalList(List tipoCanalList) {
        CatalogoParametro a = new CatalogoParametro();
        a.setValor("--Seleccione--");
        tipoCanalList.add(0, a);
        this.tipoCanalList = tipoCanalList;
    }

    /**
     * @return the canalVentaList
     */
    public List getCanalVentaList() {
        return canalVentaList;
    }

    /**
     * @param canalVentaList the canalVentaList to set
     */
    public void setCanalVentaList(List canalVentaList) {
        CatalogoParametro a = new CatalogoParametro();
        a.setValor("--Seleccione--");
        canalVentaList.add(0, a);
        this.canalVentaList = canalVentaList;
    }

    /**
     * @return the versionList
     */
    public List getVersionList() {
        return versionList;
    }

    /**
     * @param versionList the versionList to set
     */
    public void setVersionList(List versionList) {
        CatalogoParametro a = new CatalogoParametro();
        a.setValor("--Seleccione--");
        versionList.add(0, a);
        this.versionList = versionList;
    }
        /**
     * @return the estatusList
     */
    public List getEstatusList() {
        return estatusList;
    }

    /**
     * @param estatusList the estatusList to set
     */
    public void setEstatusList(List estatusList) {
        this.estatusList = estatusList;
    }
    
      /**
     * @return the tipoMonedaList
     */
    public List getTipoMonedaList() {
        return tipoMonedaList;
    }

    /**
     * @param tipoMonedaList the tipoMonedaList to set
     */
    public void setTipoMonedaList(List tipoMonedaList) {
        CatalogoParametro a = new CatalogoParametro();
        a.setValor("--Seleccione--");
        tipoMonedaList.add(0, a);
        this.tipoMonedaList = tipoMonedaList;
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    /**
     * @param afiliacionBanamexBss the afiliacionBanamexBss to set
     */
    public void setAfiliacionBanamexBss(AfiliacionBanamexBss afiliacionBanamexBss) {
        this.afiliacionBanamexBss = afiliacionBanamexBss;
    }

    /**
     * @return the eliminadoAfiliacion
     */
    public List getEliminadoAfiliacionBanamex() {
        return eliminadoAfiliacionBanamex;
    }

    /**
     * @param eliminadoAfiliacion the eliminadoAfiliacion to set
     */
    public void setEliminadoAfiliacionBanamex(List eliminadoAfiliacion) {
        this.eliminadoAfiliacionBanamex = eliminadoAfiliacion;
    }

    /**
     * @return the gridVisibleAfiliacion
     */
    public String getGridVisibleAfiliacionBanamex() {
        return gridVisibleAfiliacionBanamex;
    }

    /**
     * @param gridVisibleAfiliacion the gridVisibleAfiliacion to set
     */
    public void setGridVisibleAfiliacionBanamex(String gridVisibleAfiliacion) {
        this.gridVisibleAfiliacionBanamex = gridVisibleAfiliacion;
    }

    /**
     * @return the gridIndividualModeAfiliacion
     */
    public String getGridIndividualModeAfiliacionBanamex() {
        return gridIndividualModeAfiliacionBanamex;
    }

    /**
     * @param gridIndividualModeAfiliacion the gridIndividualModeAfiliacion to set
     */
    public void setGridIndividualModeAfiliacionBanamex(String gridIndividualModeAfiliacion) {
        this.gridIndividualModeAfiliacionBanamex = gridIndividualModeAfiliacion;
    }

    /**
     * @param catalogoBss the catalogoBss to set
     */
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }
    
    
    
}
