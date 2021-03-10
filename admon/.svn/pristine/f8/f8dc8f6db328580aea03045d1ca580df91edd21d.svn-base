package com.admon.bss.reportes;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.dao.reportes.PresentacionDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.reportes.OrganizacionAdmon;
import com.admon.entity.reportes.Presentacion;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

public class PresentacionBssImpl extends BaseBss implements PresentacionBss {

    private PresentacionDAO presentacionDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private OrganizacionBss organizacionBss;

    public List<Presentacion> resolveDescription(List<Presentacion> list) {
        return list;
    }

    @Override
    public List<Presentacion> findByCriteria(DetachedCriteria criteria) {
        return presentacionDAO.findByCriteria(criteria);
    }

    @Override
    public DetachedCriteria createDetachedCriteria() {
        return presentacionDAO.createDetachedCriteria();
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
    public List<Presentacion> filtrarPresentacion(int eventoId) {
        return addDummy(findByCriteria(createDetachedCriteria().add(Expression.eq("eventoId", eventoId))
        ), new Presentacion(), Presentacion.class);
    }

    public void setPresentacionDAO(PresentacionDAO presentacionDAO) {
        this.presentacionDAO = presentacionDAO;
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
}