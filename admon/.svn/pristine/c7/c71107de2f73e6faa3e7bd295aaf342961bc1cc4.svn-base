package com.admon.bss.reportes;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.dao.reportes.EstadoCuentaEncabezadoDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Pagina;
import com.admon.entity.reportes.OrganizacionAdmon;
import com.admon.entity.reportes.Evento;
import com.admon.entity.reportes.Presentacion;
import com.admon.model.reportes.EstadoCuentaAction;
import java.util.List;

public class EstadoCuentaEncabezadoBssImpl extends BaseBss implements EstadoCuentaEncabezadoBss {

    private EstadoCuentaEncabezadoDAO estadoCuentaEncabezadoDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;
    private EventoBss eventoBss;
    private PresentacionBss presentacionBss;

    public EstadoCuentaEncabezadoBssImpl() {
    }

    @Override
    public String addDataToSession(List list) {
        return addReportDataToSession(list);
    }

    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

    @Override
    public List<OrganizacionAdmon> getOrganizacion() {
        return addDummy(organizacionBss.findActive(), new OrganizacionAdmon(), OrganizacionAdmon.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(EstadoCuentaAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(EstadoCuentaAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(EstadoCuentaAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    @Override
    public List<Evento> filtrarEvento(int organizacionId) {
        return eventoBss.filtrarEvento(organizacionId);
    }

    public List<Presentacion> filtrarPresentacionEvento(int eventoId) {
        return presentacionBss.filtrarPresentacion(eventoId);
    }
    
    public void setEstadoCuentaEncabezadoDAO(EstadoCuentaEncabezadoDAO estadoCuentaEncabezadoDAO) {
        this.estadoCuentaEncabezadoDAO = estadoCuentaEncabezadoDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

    public void setEventoBss(EventoBss eventoBss) {
        this.eventoBss = eventoBss;
    }
    
    public void setPresentacionBss(PresentacionBss presentacionBss) {
        this.presentacionBss = presentacionBss;
    }
    
    
}