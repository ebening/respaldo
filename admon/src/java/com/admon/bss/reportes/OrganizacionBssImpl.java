package com.admon.bss.reportes;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.dao.reportes.OrganizacionDAO;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Pagina;
import com.admon.entity.reportes.OrganizacionAdmon;
import com.admon.model.reportes.OrganizacionAction;
import java.util.Arrays;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class OrganizacionBssImpl extends BaseBss implements OrganizacionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private OrganizacionDAO organizacionDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;

    public OrganizacionBssImpl() {
    }


    /*
     * Busca el registro <b>organizacion</b> por su ID en la tabla de <b>OrganizacionAdmon</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>OrganizacionAdmon</b> con la información del registro <b>organizacion</b>.
     */
    @Override
    public OrganizacionAdmon findById(Integer organizacionId) {
        OrganizacionAdmon organizacion = organizacionDAO.findById(organizacionId);
        if (organizacion == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(organizacion)).get(0);
        }
    }

    @Override
    public OrganizacionAdmon findFirst() {
        List<OrganizacionAdmon> organizacionList = findByCriteria(createDetachedCriteria());
        if (organizacionList.isEmpty()) {
            return null;
        } else {
            return organizacionList.get(0);
        }
    }

    public List<OrganizacionAdmon> resolveDescription(List<OrganizacionAdmon> list) {
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return organizacionDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<OrganizacionAdmon> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return organizacionDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>OrganizacionAdmon</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    @Override
    public List<OrganizacionAdmon> findByCriteria(DetachedCriteria criteria) {
        return organizacionDAO.findByCriteria(generalizarCriteria(criteria));
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, OrganizacionAction.class);
        return criteria;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>OrganizacionAdmon</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>OrganizacionAdmon</b>.
     */
    @Override
    public List<OrganizacionAdmon> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>OrganizacionAdmon</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>OrganizacionAdmon</b>.
     */
    @Override
    public List<OrganizacionAdmon> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("habilitado", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return organizacionDAO.createDetachedCriteria();
    }

    /*
     * Método que obtiene los parámetros que le corresponden
     * a un determinado catálogo. Éste método es llamado por los Action
     * para obtener los parámetros del catálogo
     * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
     * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
     * en el archivo <b>CodesBss.properties</b>.
     * @return Regresa una lista con los parámetros del catálogo.
     */
    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(OrganizacionAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(OrganizacionAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(OrganizacionAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setOrganizacionDAO(OrganizacionDAO organizacionDAO) {
        this.organizacionDAO = organizacionDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

}