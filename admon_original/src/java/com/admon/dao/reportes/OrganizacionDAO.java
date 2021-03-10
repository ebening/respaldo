package com.admon.dao.reportes;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.reportes.OrganizacionAdmon;

public class OrganizacionDAO extends GenericDAOImpl<OrganizacionAdmon, Integer> {

    @Override
    protected Class<OrganizacionAdmon> getEntityClass() {
        return OrganizacionAdmon.class;
    }
}
