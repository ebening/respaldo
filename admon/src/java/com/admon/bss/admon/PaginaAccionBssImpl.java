package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PaginaAccionDAO;
import com.admon.entity.admon.*;
import java.util.Arrays;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class PaginaAccionBssImpl extends BaseBss implements PaginaAccionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PaginaAccionDAO paginaAccionDAO;

    public PaginaAccionBssImpl() {
    }


    /*
 * Busca el registro <b>pais</b> por su ID en la tabla de <b>ZonaHoraria</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>ZonaHoraria</b> con la información del registro <b>pais</b>.
     */
    @Override
    public PaginaAccion findById(Integer funcionalidadId) {
        PaginaAccion paginaAccion = paginaAccionDAO.findById(funcionalidadId);
        if (paginaAccion == null) {
            return null;
        } else {
            return (Arrays.asList(paginaAccion)).get(0);
        }
    }

    @Override
    public List<PaginaAccion> findByIntProperty(String propertyName, Integer value) {
        return (findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public PaginaAccion findFirst() {
        List<PaginaAccion> paisList = findByCriteria(createDetachedCriteria());
        if (paisList.isEmpty()) {
            return null;
        } else {
            return paisList.get(0);
        }
    }


    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>ZonaHoraria</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<PaginaAccion> findByCriteria(DetachedCriteria criteria) {
        return paginaAccionDAO.findByCriteria(criteria);
    }

    @Override
    public List<PaginaAccion> findByCriteriaIgnorePrivacy(PaginaAccion paginaAccion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(paginaAccion));
        return paginaAccionDAO.findByCriteria(criteria);
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>ZonaHoraria</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>ZonaHoraria</b>.
     */
    @Override
    public List<PaginaAccion> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }


    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return paginaAccionDAO.createDetachedCriteria();
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    /**
     * @param paginaAccionDAO the zonaHorariaDAO to set
     */
    public void setPaginaAccionDAO(PaginaAccionDAO paginaAccionDAO) {
        this.paginaAccionDAO = paginaAccionDAO;
    }
  
}
