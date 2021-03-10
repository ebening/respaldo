package com.admon.bss.reportes;

import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.reportes.OrganizacionAdmon;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface OrganizacionBss {


    /*
     * Método que busca el registro <b>organizacion</b> por su ID en la tabla de <b>OrganizacionAdmon</b>.
     * @param id es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>OrganizacionAdmon</b> con la información del registro <b>organizacion</b>.
     */
    public OrganizacionAdmon findById(Integer organizacionId);
    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>OrganizacionAdmon</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */

    public List<OrganizacionAdmon> findByCriteria(DetachedCriteria criteria);

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public OrganizacionAdmon findFirst();

    /*
     * Método que obtiene todos los registros de la tabla de <b>OrganizacionAdmon</b>.
     * @return Regresa todos los registros de la tabla <b>OrganizacionAdmon</b>.
     */
    public List<OrganizacionAdmon> findActive();

    /*
     * Método que obtiene todos los registros de la tabla de <b>OrganizacionAdmon</b>.
     * @return Regresa todos los registros de la tabla <b>OrganizacionAdmon</b>.
     */
    public List<OrganizacionAdmon> findAll();

    /*
     * Método que obtiene un objeto DetachedCriteria.
     *
     * @return Regresa un objeto DetachedCriteria.
     */
    public DetachedCriteria createDetachedCriteria();

    /*
     * Método que obtiene los parámetros que le corresponden
     * a un determinado catálogo. Éste método es llamado por los Action
     * para obtener los parámetros del catálogo
     * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
     * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
     * en el archivo <b>CodesBss.properties</b>.
     * @return Regresa una lista con los parámetros del catálogo.
     */
    public List<ConfiguracionParametro> getParametros(String catalogKey);

    public String hasGrid();

    public String isIndividual();

    public String getNombreActionMenu();
}
