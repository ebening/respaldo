package com.admon.pkg.bss;

import com.admon.pkg.dao.SeguridadPerfilPKGDAO;
import com.admon.entity.admon.SPParametro;
import com.admon.pkg.entity.OrganizacionGenerarRS;
import java.util.ArrayList;
import java.util.List;

public class SeguridadPerfilPKGBssImpl implements SeguridadPerfilPKGBss {

    // Spring IoC
    private SeguridadPerfilPKGDAO seguridadPerfilPKGDAO;

    public SeguridadPerfilPKGBssImpl() {
    }
    // Spring setter
    public void setOrganizacionPKGDAO(SeguridadPerfilPKGDAO seguridadPerfilPKGDAO) {
        this.seguridadPerfilPKGDAO = seguridadPerfilPKGDAO;
    }
    @Override
    public boolean generarSeguridadPerfil() {
        /*String spName = "generarSeguridadPerfil";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        try {
            list = seguridadPerfilPKGDAO.callStoredProcedure(spName, spList);
        } catch (Exception e) {
            System.out.println("error> " + e);
        }*/
        return true;
    }

}
