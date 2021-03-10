package com.admon.entity.admon;

import java.io.Serializable;

public class CatalogoPK implements Serializable {

    private Integer catalogoId;
    private Integer organizacionId;

    public CatalogoPK() {
    }

    public Integer getCatalogoId() {
        return catalogoId;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setCatalogoId(Integer catalogoId) {
        this.catalogoId = catalogoId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }
        if (!(arg0 instanceof CatalogoPK)) {
            return false;
        }
        CatalogoPK arg1 = (CatalogoPK) arg0;
        return (this.catalogoId.longValue() == arg1.getCatalogoId().longValue())
                && (this.organizacionId.longValue() == arg1.getOrganizacionId().longValue());
    }

    @Override
    public int hashCode() {
        int hsCode;
        hsCode = catalogoId.hashCode();
        hsCode = hsCode + organizacionId.hashCode();
        return hsCode;
    }
}
