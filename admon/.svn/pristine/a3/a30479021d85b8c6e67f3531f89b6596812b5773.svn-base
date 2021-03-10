package com.admon.bss.admon;

import com.admon.entity.admon.*;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;


public interface ParcializacionBanamexBss {
    public List<Integer> save(Integer userId, List<ParcializacionBanamex> afiliacionBanamexList);
    public List<String> update(Integer userId, List<ParcializacionBanamex> afiliacionBanamexList);
    public void delete(Integer userId, List<Integer> afiliacionBanamexList);
    public boolean isValidoAfiliacionBanamex(String afiliacion);
    public ParcializacionBanamex findByName(String nombre);
    public ParcializacionBanamex findById(Integer afiliacionBanamexId);
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, ParcializacionBanamex afiliacionBanamex, String afiliacion);
    public ParcializacionBanamex findFirst();
    public List<ParcializacionBanamex> findByCriteria(DetachedCriteria criteria);
    public List<ParcializacionBanamex> findByCriteriaIgnorePrivacy(ParcializacionBanamex afiliacionBanamex);
    public List<ParcializacionBanamex> findAll();
    public DetachedCriteria createDetachedCriteria();
    public List<ConfiguracionParametro> getParametros(String catalogKey);
    public String getReportDataTest(String order, String ordenTipo, ParcializacionBanamex afiliacionBanamex, String afiliacion);
}
