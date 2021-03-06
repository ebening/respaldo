package com.admon.model.admon;

import java.util.ArrayList;
import java.util.List;

import com.admon.bss.admon.CatalogoBss;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.CatalogoParametroPK;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Organizacion;
//import com.admon.bss.admon.CatalogoParametroBss;
//import com.admon.bss.admon.CatalogoParametroLenguajeBss;
import com.admon.model.BaseModel;

public class CatalogoAction extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Inyeccion de dependencias con Spring. Estas dependencias se configuran en 
     * el applicationContext.xml, y ademas necesitan un metodo setter por cada 
     * variable el cual es anexado al final de la clase. */
    private CatalogoBss catalogoBss;
//    private CatalogoParametroBss catalogoParametroBss;
//    private CatalogoParametroLenguajeBss catalogoParametroLenguajeBss;

    // Elementos de struts2 para el JSP
    private List organizacionCatalogo = new ArrayList();
    private String organizacionCatalogoLink = new String();
    private List catalogoPadreCatalogo = new ArrayList();
    private String catalogoPadreCatalogoLink = new String();
    private List parametroDependienteCatalogo = new ArrayList();
    private String parametroDependienteCatalogoLink = new String();
    private List estatusCatalogo = new ArrayList();
    private List eliminadoCatalogo = new ArrayList();

    // Elementos de struts2 para el JSP (Embebido)
    private List catalogoCatalogoParametro = new ArrayList();
    private String catalogoCatalogoParametroLink = new String();
    private List organizacionCatalogoParametro = new ArrayList();
    private String organizacionCatalogoParametroLink = new String();
    private List estatusCatalogoParametro = new ArrayList();
    private List eliminadoCatalogoParametro = new ArrayList();

    // Elementos de struts2 para el JSP (Embebido)
    private List catalogoParametroCatalogoParametroLenguaje = new ArrayList();
    private String catalogoParametroCatalogoParametroLenguajeLink = new String();
    private List lenguajeCatalogoParametroLenguaje = new ArrayList();
    private String lenguajeCatalogoParametroLenguajeLink = new String();
    private List organizacionCatalogoParametroLenguaje = new ArrayList();
    private String organizacionCatalogoParametroLenguajeLink = new String();
    private List estatusCatalogoParametroLenguaje = new ArrayList();
    private List eliminadoCatalogoParametroLenguaje = new ArrayList();

    private String gridVisibleCatalogo = new String();
    private String gridIndividualModeCatalogo = new String();

    private String gridVisibleCatalogoParametro = new String();
    private String gridIndividualModeCatalogoParametro = new String();

    private String gridVisibleCatalogoParametroLenguaje = new String();
    private String gridIndividualModeCatalogoParametroLenguaje = new String();

    private String nombreActionMenu = new String();

    /*
     * M??todo que llena los elementos del formulario del JSP mediante Struts al cargar la p??gina catalogo.jsp 
     * @return Regresa un c??digo de tipo String, el m??todo execute() es ejecutado y la respuesta es evaluada, finalmente se manda llamar al JSP correspondiente seg??n la respuesta.
     */
    @Override
    public String execute() {
        setNombreActionMenu(catalogoBss.getNombreActionMenu());
        List<Organizacion> organzaciones = catalogoBss.getOrganizacion();
        List<ConfiguracionParametro> paramEstatus = catalogoBss.getParametros("key_estatus");
        List<ConfiguracionParametro> paramEliminado = catalogoBss.getParametros("key_eliminado");
        List<Catalogo> catalogos = catalogoBss.getCatalogosForCombo();
        
        // <editor-fold defaultstate="collapsed" desc="Getters">
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionCatalogo(organzaciones);
        setOrganizacionCatalogoLink(getLink("Catalogo", "organizacion"));
        setCatalogoPadreCatalogo(catalogos);
        // Obtener todos los registros de la tabla Catalogo.
        setCatalogoPadreCatalogoLink(getLink("Catalogo", "catalogo"));
        // Obtener todos los registros de la tabla CatalogoParametro.
        CatalogoParametro cp = new CatalogoParametro();
        cp.setCatalogoParametroPK(new CatalogoParametroPK());
        cp.getCatalogoParametroPK().setCatalogoParametroId(0);
        cp.setValor("");
        List<CatalogoParametro> catParams = new ArrayList<>();
        catParams.add(cp);
        setParametroDependienteCatalogo(catParams);
        setParametroDependienteCatalogoLink(getLink("Catalogo", "catalogoParametro"));
        // Obtener los par??metros del cat??logo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCatalogo(paramEstatus);
        // Obtener los par??metros del cat??logo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCatalogo(paramEliminado);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Catalogo.
        setCatalogoCatalogoParametroLink(getLink("Catalogo", "catalogo"));
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionCatalogoParametro(organzaciones);
        setOrganizacionCatalogoParametroLink(getLink("Catalogo", "organizacion"));
        // Obtener los par??metros del cat??logo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCatalogoParametro(paramEstatus);
        // Obtener los par??metros del cat??logo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCatalogoParametro(paramEliminado);
        // </editor-fold>

        setLenguajeCatalogoParametroLenguaje(catalogoBss.getLenguaje());
        // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
        // Obtener todos los registros de la tabla Catalogo.
        setCatalogoParametroCatalogoParametroLenguajeLink(getLink("Catalogo", "catalogoParametro"));
        // Obtener todos los registros de la tabla Lenguaje.
        setLenguajeCatalogoParametroLenguajeLink(getLink("Catalogo", "lenguaje"));
        // Obtener todos los registros de la tabla Organizacion.
        setOrganizacionCatalogoParametroLenguaje(organzaciones);
        setOrganizacionCatalogoParametroLenguajeLink(getLink("Catalogo", "organizacion"));
        // Obtener los par??metros del cat??logo Estatus
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEstatusCatalogoParametroLenguaje(paramEstatus);
        // Obtener los par??metros del cat??logo Eliminado
        // por su key en com.admon.bss.resourcesCodesBss.properties
        setEliminadoCatalogoParametroLenguaje(paramEliminado);
        // </editor-fold>

        setGridVisibleCatalogo(catalogoBss.hasGrid());
        setGridIndividualModeCatalogo(catalogoBss.isIndividual());
        setGridVisibleCatalogoParametro(catalogoBss.hasGridCatalogoParametro());
        setGridIndividualModeCatalogoParametro(catalogoBss.isIndividualCatalogoParametro());
        setGridVisibleCatalogoParametroLenguaje(catalogoBss.hasGridCatalogoParametroLenguaje());
        setGridIndividualModeCatalogoParametroLenguaje(catalogoBss.isIndividualCatalogoParametroLenguaje());

        return SUCCESS;
    }

    /* Cada elemento que ser?? llenado por Struts al cargar la p??gina
     * deben tener m??todos Getter y Setter. */
    /* Getter y Setter select */
    public List getOrganizacionCatalogo() {
        return organizacionCatalogo;
    }

    public void setOrganizacionCatalogo(List organizacionCatalogo) {
        this.organizacionCatalogo = organizacionCatalogo;
    }

    public String getOrganizacionCatalogoLink() {
        return organizacionCatalogoLink;
    }

    public void setOrganizacionCatalogoLink(String organizacionCatalogoLink) {
        this.organizacionCatalogoLink = organizacionCatalogoLink;
    }

    public List getCatalogoPadreCatalogo() {
        return catalogoPadreCatalogo;
    }

    public void setCatalogoPadreCatalogo(List catalogoPadreCatalogo) {
        this.catalogoPadreCatalogo = catalogoPadreCatalogo;
    }

    public String getCatalogoPadreCatalogoLink() {
        return catalogoPadreCatalogoLink;
    }

    public void setCatalogoPadreCatalogoLink(String catalogoPadreCatalogoLink) {
        this.catalogoPadreCatalogoLink = catalogoPadreCatalogoLink;
    }

    public List getParametroDependienteCatalogo() {
        return parametroDependienteCatalogo;
    }

    public void setParametroDependienteCatalogo(List parametroDependienteCatalogo) {
        this.parametroDependienteCatalogo = parametroDependienteCatalogo;
    }

    public String getParametroDependienteCatalogoLink() {
        return parametroDependienteCatalogoLink;
    }

    public void setParametroDependienteCatalogoLink(String parametroDependienteCatalogoLink) {
        this.parametroDependienteCatalogoLink = parametroDependienteCatalogoLink;
    }

    public List getEstatusCatalogo() {
        return estatusCatalogo;
    }

    public void setEstatusCatalogo(List estatusCatalogo) {
        this.estatusCatalogo = estatusCatalogo;
    }

    public List getEliminadoCatalogo() {
        return eliminadoCatalogo;
    }

    public void setEliminadoCatalogo(List eliminadoCatalogo) {
        this.eliminadoCatalogo = eliminadoCatalogo;
    }

    public List getCatalogoCatalogoParametro() {
        return catalogoCatalogoParametro;
    }

    public void setCatalogoCatalogoParametro(List catalogoCatalogoParametro) {
        this.catalogoCatalogoParametro = catalogoCatalogoParametro;
    }

    public String getCatalogoCatalogoParametroLink() {
        return catalogoCatalogoParametroLink;
    }

    public void setCatalogoCatalogoParametroLink(String catalogoCatalogoParametroLink) {
        this.catalogoCatalogoParametroLink = catalogoCatalogoParametroLink;
    }

    public List getOrganizacionCatalogoParametro() {
        return organizacionCatalogoParametro;
    }

    public void setOrganizacionCatalogoParametro(List organizacionCatalogoParametro) {
        this.organizacionCatalogoParametro = organizacionCatalogoParametro;
    }

    public String getOrganizacionCatalogoParametroLink() {
        return organizacionCatalogoParametroLink;
    }

    public void setOrganizacionCatalogoParametroLink(String organizacionCatalogoParametroLink) {
        this.organizacionCatalogoParametroLink = organizacionCatalogoParametroLink;
    }

    public List getEstatusCatalogoParametro() {
        return estatusCatalogoParametro;
    }

    public void setEstatusCatalogoParametro(List estatusCatalogoParametro) {
        this.estatusCatalogoParametro = estatusCatalogoParametro;
    }

    public List getEliminadoCatalogoParametro() {
        return eliminadoCatalogoParametro;
    }

    public void setEliminadoCatalogoParametro(List eliminadoCatalogoParametro) {
        this.eliminadoCatalogoParametro = eliminadoCatalogoParametro;
    }

    public List getCatalogoParametroCatalogoParametroLenguaje() {
        return catalogoParametroCatalogoParametroLenguaje;
    }

    public void setCatalogoParametroCatalogoParametroLenguaje(List catalogoParametroCatalogoParametroLenguaje) {
        this.catalogoParametroCatalogoParametroLenguaje = catalogoParametroCatalogoParametroLenguaje;
    }

    public String getCatalogoParametroCatalogoParametroLenguajeLink() {
        return catalogoParametroCatalogoParametroLenguajeLink;
    }

    public void setCatalogoParametroCatalogoParametroLenguajeLink(String catalogoParametroCatalogoParametroLenguajeLink) {
        this.catalogoParametroCatalogoParametroLenguajeLink = catalogoParametroCatalogoParametroLenguajeLink;
    }

    public List getLenguajeCatalogoParametroLenguaje() {
        return lenguajeCatalogoParametroLenguaje;
    }

    public void setLenguajeCatalogoParametroLenguaje(List lenguajeCatalogoParametroLenguaje) {
        this.lenguajeCatalogoParametroLenguaje = lenguajeCatalogoParametroLenguaje;
    }

    public String getLenguajeCatalogoParametroLenguajeLink() {
        return lenguajeCatalogoParametroLenguajeLink;
    }

    public void setLenguajeCatalogoParametroLenguajeLink(String lenguajeCatalogoParametroLenguajeLink) {
        this.lenguajeCatalogoParametroLenguajeLink = lenguajeCatalogoParametroLenguajeLink;
    }

    public List getOrganizacionCatalogoParametroLenguaje() {
        return organizacionCatalogoParametroLenguaje;
    }

    public void setOrganizacionCatalogoParametroLenguaje(List organizacionCatalogoParametroLenguaje) {
        this.organizacionCatalogoParametroLenguaje = organizacionCatalogoParametroLenguaje;
    }

    public String getOrganizacionCatalogoParametroLenguajeLink() {
        return organizacionCatalogoParametroLenguajeLink;
    }

    public void setOrganizacionCatalogoParametroLenguajeLink(String organizacionCatalogoParametroLenguajeLink) {
        this.organizacionCatalogoParametroLenguajeLink = organizacionCatalogoParametroLenguajeLink;
    }

    public List getEstatusCatalogoParametroLenguaje() {
        return estatusCatalogoParametroLenguaje;
    }

    public void setEstatusCatalogoParametroLenguaje(List estatusCatalogoParametroLenguaje) {
        this.estatusCatalogoParametroLenguaje = estatusCatalogoParametroLenguaje;
    }

    public List getEliminadoCatalogoParametroLenguaje() {
        return eliminadoCatalogoParametroLenguaje;
    }

    public void setEliminadoCatalogoParametroLenguaje(List eliminadoCatalogoParametroLenguaje) {
        this.eliminadoCatalogoParametroLenguaje = eliminadoCatalogoParametroLenguaje;
    }

    public String getGridVisibleCatalogo() {
        return gridVisibleCatalogo;
    }

    public void setGridVisibleCatalogo(String gridVisibleCatalogo) {
        this.gridVisibleCatalogo = gridVisibleCatalogo;
    }

    public String getGridIndividualModeCatalogo() {
        return gridIndividualModeCatalogo;
    }

    public void setGridIndividualModeCatalogo(String gridIndividualModeCatalogo) {
        this.gridIndividualModeCatalogo = gridIndividualModeCatalogo;
    }

    public String getGridVisibleCatalogoParametro() {
        return gridVisibleCatalogoParametro;
    }

    public void setGridVisibleCatalogoParametro(String gridVisibleCatalogoParametro) {
        this.gridVisibleCatalogoParametro = gridVisibleCatalogoParametro;
    }

    public String getGridIndividualModeCatalogoParametro() {
        return gridIndividualModeCatalogoParametro;
    }

    public void setGridIndividualModeCatalogoParametro(String gridIndividualModeCatalogoParametro) {
        this.gridIndividualModeCatalogoParametro = gridIndividualModeCatalogoParametro;
    }

    public String getGridVisibleCatalogoParametroLenguaje() {
        return gridVisibleCatalogoParametroLenguaje;
    }

    public void setGridVisibleCatalogoParametroLenguaje(String gridVisibleCatalogoParametroLenguaje) {
        this.gridVisibleCatalogoParametroLenguaje = gridVisibleCatalogoParametroLenguaje;
    }

    public String getGridIndividualModeCatalogoParametroLenguaje() {
        return gridIndividualModeCatalogoParametroLenguaje;
    }

    public void setGridIndividualModeCatalogoParametroLenguaje(String gridIndividualModeCatalogoParametroLenguaje) {
        this.gridIndividualModeCatalogoParametroLenguaje = gridIndividualModeCatalogoParametroLenguaje;
    }

    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }

    /*
     * Inyecci??n de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un m??todo
     * Setter.
     */
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

//    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
//        this.catalogoParametroBss = catalogoParametroBss;
//    }
//
//    public void setCatalogoParametroLenguajeBss(CatalogoParametroLenguajeBss catalogoParametroLenguajeBss) {
//        this.catalogoParametroLenguajeBss = catalogoParametroLenguajeBss;
//    }

}