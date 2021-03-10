package com.admon.entity.admon;

import java.io.Serializable;

public class CatalogoParametroPK implements Serializable {

    private Integer catalogoParametroId;
    private Integer organizacionId;

    public CatalogoParametroPK() {
    }

    public Integer getCatalogoParametroId() {
        return catalogoParametroId;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setCatalogoParametroId(Integer catalogoParametroId) {
        this.catalogoParametroId = catalogoParametroId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }
        if (!(arg0 instanceof CatalogoParametroPK)) {
            return false;
        }
        CatalogoParametroPK arg1 = (CatalogoParametroPK) arg0;
        return (this.catalogoParametroId.longValue() == arg1.getCatalogoParametroId().longValue())
                && (this.organizacionId.longValue() == arg1.getOrganizacionId().longValue());
    }

    @Override
    public int hashCode() {
        int hsCode;
        hsCode = catalogoParametroId.hashCode();
        hsCode = hsCode + organizacionId.hashCode();
        return hsCode;
    }
}