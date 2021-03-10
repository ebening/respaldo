package com.admon.model.admon;

import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import com.admon.bss.admon.AfiliacionSantanderBss;
import com.admon.bss.admon.CatalogoBss;
import com.admon.entity.admon.CatalogoParametro;

public class AfiliacionSantanderAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private AfiliacionSantanderBss afiliacionSantanderBss;
    private CatalogoBss catalogoBss;

    // Elementos de struts2 para el JSP
    private List estatus = new ArrayList();
    private List eliminado = new ArrayList();

    String gridVisibleAfiliacionSantander = new String();
    String gridIndividualModeAfiliacionSantander = new String();
    String nombreActionMenu = new String();
    private List tipoCanalList = new ArrayList();
    private List canalVentaList = new ArrayList();
    private List tipoMonedaList = new ArrayList();

    public AfiliacionSantanderAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página aplicacion.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(afiliacionSantanderBss.getNombreActionMenu());
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener los parámetros del catálogo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatus(afiliacionSantanderBss.getParametros("key_estatus"));
        // Obtener los parámetros del catálogo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminado(afiliacionSantanderBss.getParametros("key_eliminado"));
        
        setCanalVentaList(catalogoBss.getCatalogosByClave("T_CANAL_VENTA"));//canal
        setTipoCanalList(catalogoBss.getCatalogosByClave("CANAL_VENTA"));//tipos
        setTipoMonedaList(catalogoBss.getCatalogosByClave("TIPO_MONEDA_AFILIACIONES"));

        setGridVisibleAfiliacionSantander(afiliacionSantanderBss.hasGrid());
        setGridIndividualModeAfiliacionSantander(afiliacionSantanderBss.isIndividual());

        return SUCCESS;
    }

    /* Cada elemento que será llenado por Struts al cargar la página
 * deben tener métodos Getter y Setter. */
 /* Getter y Setter select */
    public List getEstatus() {
        return estatus;
    }

    public void setEstatus(List estatus) {
        this.estatus = estatus;
    }

    /* Getter y Setter select */
    public List getEliminado() {
        return eliminado;
    }

    public void setEliminado(List eliminado) {
        this.eliminado = eliminado;
    }

    public String getGridVisibleAfiliacionSantander() {
        return gridVisibleAfiliacionSantander;
    }

    public void setGridVisibleAfiliacionSantander(String gridVisibleAfiliacionSantander) {
        this.gridVisibleAfiliacionSantander = gridVisibleAfiliacionSantander;
    }

    public String getGridIndividualModeAfiliacionSantander() {
        return gridIndividualModeAfiliacionSantander;
    }

    public void setGridIndividualModeAfiliacionSantander(String gridIndividualModeAfiliacionSantander) {
        this.gridIndividualModeAfiliacionSantander = gridIndividualModeAfiliacionSantander;
    }

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
    public void setAfiliacionSantanderBss(AfiliacionSantanderBss afiliacionSantanderBss) {
        this.afiliacionSantanderBss = afiliacionSantanderBss;
    }
    
     public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }
    

}
