package com.admon.bss.admon;

import com.admon.entity.admon.*;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;

public interface PaginaAccionBss {
    public PaginaAccion findById(Integer funcionalidadId);
    public List<PaginaAccion> findByIntProperty(String propertyName, Integer value);
    public PaginaAccion findFirst();
    public List<PaginaAccion> findByCriteria(DetachedCriteria criteria);
    public List<PaginaAccion> findByCriteriaIgnorePrivacy(PaginaAccion paginaAccion);
    public List<PaginaAccion> findAll();
    public DetachedCriteria createDetachedCriteria();

}
