package com.admon.model.admon;

import com.admon.bss.admon.CatalogoBss;
import com.admon.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import com.admon.bss.admon.ConfiguracionFormaPagoBss;
import com.admon.entity.admon.CatalogoParametro;

public class ConfiguracionFormaPagoAction extends BaseModel {

    /* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
 * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
 * variable el cual es anexado al final de la clase. */
    private ConfiguracionFormaPagoBss configuracionFormaPagoBss;
    private CatalogoBss catalogoBss;

    // Elementos de struts2 para el JSP
    private List estatus = new ArrayList();
    private List eliminado = new ArrayList();
    private List formaPago = new ArrayList();
    private List tipoMonedaList = new ArrayList();

    private String gridVisibleConfiguracionFormaPago = new String();
    private String gridIndividualModeConfiguracionFormaPago = new String();
    private String nombreActionMenu = new String();

    public ConfiguracionFormaPagoAction() {
    }

    /*
 * Método que llena los elementos del formulario del JSP mediante Struts al cargar la página aplicacion.jsp 
 * @return Regresa un código de tipo String, el método execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente según la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(configuracionFormaPagoBss.getNombreActionMenu());
        setEstatus(configuracionFormaPagoBss.getParametros("key_estatus"));
        setEliminado(configuracionFormaPagoBss.getParametros("key_eliminado"));
        setFormaPago(catalogoBss.getCatalogosByClave("AFILIACIONES_FORMA_PAGO"));
        setTipoMonedaList(catalogoBss.getCatalogosByClave("TIPO_MONEDA_AFILIACIONES"));
        return SUCCESS;
    }
     /**
     * @return the estatus
     */
    public List getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(List estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the eliminado
     */
    public List getEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(List eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * @return the formaPago
     */
    public List getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(List formaPago) {
        CatalogoParametro a = new CatalogoParametro();
        a.setValor("--Seleccione--");
        formaPago.add(0, a);
        this.formaPago = formaPago;
    }

    /**
     * @return the gridVisibleConfiguracionFormaPago
     */
    public String getGridVisibleConfiguracionFormaPago() {
        return gridVisibleConfiguracionFormaPago;
    }

    /**
     * @param gridVisibleConfiguracionFormaPago the gridVisibleConfiguracionFormaPago to set
     */
    public void setGridVisibleConfiguracionFormaPago(String gridVisibleConfiguracionFormaPago) {
        this.gridVisibleConfiguracionFormaPago = gridVisibleConfiguracionFormaPago;
    }

    /**
     * @return the gridIndividualModeConfiguracionFormaPago
     */
    public String getGridIndividualModeConfiguracionFormaPago() {
        return gridIndividualModeConfiguracionFormaPago;
    }

    /**
     * @param gridIndividualModeConfiguracionFormaPago the gridIndividualModeConfiguracionFormaPago to set
     */
    public void setGridIndividualModeConfiguracionFormaPago(String gridIndividualModeConfiguracionFormaPago) {
        this.gridIndividualModeConfiguracionFormaPago = gridIndividualModeConfiguracionFormaPago;
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
     * @param configuracionFormaPagoBss the configuracionFormaPagoBss to set
     */
    public void setConfiguracionFormaPagoBss(ConfiguracionFormaPagoBss configuracionFormaPagoBss) {
        this.configuracionFormaPagoBss = configuracionFormaPagoBss;
    }

    /**
     * @param catalogoBss the catalogoBss to set
     */
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

   

}
