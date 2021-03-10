package com.admon.model.reportes;

import com.admon.bss.reportes.OrganizacionBss;
import com.admon.model.BaseModel;

public class OrganizacionAction extends BaseModel {

    private OrganizacionBss organizacionBss;

    private String gridVisibleOrganizacion = new String();
    private String gridIndividualModeOrganizacion = new String();
    private String nombreActionMenu = new String();

    public OrganizacionAction() {
    }

    @Override
    public String execute() {
        setNombreActionMenu(organizacionBss.getNombreActionMenu());
        setGridVisibleOrganizacion(organizacionBss.hasGrid());
        setGridIndividualModeOrganizacion(organizacionBss.isIndividual());
        return SUCCESS;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

    public String getGridVisibleOrganizacion() {
        return gridVisibleOrganizacion;
    }

    public void setGridVisibleOrganizacion(String gridVisibleOrganizacion) {
        this.gridVisibleOrganizacion = gridVisibleOrganizacion;
    }

    public String getGridIndividualModeOrganizacion() {
        return gridIndividualModeOrganizacion;
    }

    public void setGridIndividualModeOrganizacion(String gridIndividualModeOrganizacion) {
        this.gridIndividualModeOrganizacion = gridIndividualModeOrganizacion;
    }

    public String getNombreActionMenu() {
        return nombreActionMenu;
    }

    public void setNombreActionMenu(String nombreActionMenu) {
        this.nombreActionMenu = nombreActionMenu;
    }

}
