package com.admon.bss.admon;

import com.admon.entity.admon.*;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;


public interface TerminalBanamexBss {
    public List<Integer> save(Integer userId, List<TerminalBanamex> afiliacionBanamexList);
    public List<String> update(Integer userId, List<TerminalBanamex> afiliacionBanamexList);
    public void delete(Integer userId, List<Integer> afiliacionBanamexList);
    public boolean isValidoAfiliacionBanamex(TerminalBanamex terminalBanamex);
    public TerminalBanamex findByName(String nombre);
    public TerminalBanamex findById(Integer afiliacionBanamexId);
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, TerminalBanamex afiliacionBanamex, String afiliacion);
    public TerminalBanamex findFirst();
    public List<TerminalBanamex> findByCriteria(DetachedCriteria criteria);
    public List<TerminalBanamex> findByCriteriaIgnorePrivacy(TerminalBanamex afiliacionBanamex);
    public List<TerminalBanamex> findAll();
    public DetachedCriteria createDetachedCriteria();
    public List<ConfiguracionParametro> getParametros(String catalogKey);
    public String getReportDataTest(String order, String ordenTipo, TerminalBanamex afiliacionBanamex, String afiliacion);
}
