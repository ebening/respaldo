package com.admon.bss.admon;

import com.admon.entity.admon.*;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;


public interface AfiliacionBanamexBss {
    public List<Integer> save(Integer userId, List<AfiliacionBanamex> afiliacionBanamexList);
    public List<String> update(Integer userId, List<AfiliacionBanamex> afiliacionBanamexList);
    public void delete(Integer userId, List<Integer> afiliacionBanamexList);
    public boolean isValidoByAfiliacion(String afiliacion);
    public boolean isValidoByAfiliacionCanal(String afiliacion,Integer canalVenta);
    public boolean existValorDefault(String afiliacion,Integer canalVentaId);
    public AfiliacionBanamex findByName(String nombre);
    public AfiliacionBanamex findById(Integer afiliacionBanamexId);
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, AfiliacionBanamex afiliacionBanamex);
    public AfiliacionBanamex findFirst();
    public List<AfiliacionBanamex> findByCriteria(DetachedCriteria criteria);
    public List<AfiliacionBanamex> findByCriteriaIgnorePrivacy(AfiliacionBanamex afiliacionBanamex);
    public List<AfiliacionBanamex> findAll();
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> afiliacionList);
    public DetachedCriteria createDetachedCriteria();
    public List<ConfiguracionParametro> getParametros(String catalogKey);
    public String getReportDataTest(String order, String ordenTipo, AfiliacionBanamex afiliacionBanamex);
    public String hasGrid();
    public String isIndividual();
    public String getNombreActionMenu();
    public AfiliacionBanamex getCatalogosAfiliaciones(String catalogKey);
    public List getCatalogos(String catalogKey);
    public Grid desplegar(Integer userId,int displayedPage, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion);
}
