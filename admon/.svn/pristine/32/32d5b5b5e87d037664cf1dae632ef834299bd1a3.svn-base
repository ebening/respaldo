package com.admon.bss.admon;

import com.admon.entity.admon.*;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;

public interface ZonaHorariaBss {
    public ZonaHoraria findById(Integer zonaHorariaId);
    public List<ZonaHoraria> findByIntProperty(String propertyName, Integer value);
    public ZonaHoraria findFirst();
    public List<ZonaHoraria> findByCriteria(DetachedCriteria criteria);
    public List<ZonaHoraria> findByCriteriaIgnorePrivacy(ZonaHoraria zonaHoraria);
    public List<ZonaHoraria> findAll();
    public DetachedCriteria createDetachedCriteria();

}
