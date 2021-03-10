package com.admon.entity.admon;

import java.io.Serializable;

public class CatalogoParametroLenguajePK implements Serializable {

    private Integer catalogoParametroLenguajeId;
    private Integer organizacionId;

    public CatalogoParametroLenguajePK() {
    }

    public Integer getCatalogoParametroLenguajeId() {
        return catalogoParametroLenguajeId;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setCatalogoParametroLenguajeId(Integer catalogoParametroLenguajeId) {
        this.catalogoParametroLenguajeId = catalogoParametroLenguajeId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }
        if (!(arg0 instanceof CatalogoParametroLenguajePK)) {
            return false;
        }
        CatalogoParametroLenguajePK arg1 = (CatalogoParametroLenguajePK) arg0;
        return (this.catalogoParametroLenguajeId.longValue() == arg1.getCatalogoParametroLenguajeId().longValue())
                && (this.organizacionId.longValue() == arg1.getOrganizacionId().longValue());
    }

    @Override
    public int hashCode() {
        int hsCode;
        hsCode = catalogoParametroLenguajeId.hashCode();
        hsCode = hsCode + organizacionId.hashCode();
        return hsCode;
    }
}