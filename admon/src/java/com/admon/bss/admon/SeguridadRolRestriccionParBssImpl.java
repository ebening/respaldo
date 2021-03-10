package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadRolRestriccionParDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadRolRestriccionParAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadRolRestriccionParBssImpl extends BaseBss implements SeguridadRolRestriccionParBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadRolRestriccionParDAO seguridadRestriccionesRolDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private SeguridadRolBss seguridadRolBss;
    private CatalogoBss catalogoBss;
    private CatalogoParametroBss catalogoParametroBss;
    private AplicacionBss aplicacionBss;
    private OrganizacionBss organizacionBss;

    public SeguridadRolRestriccionParBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadRolRestriccionPar seguridadRestriccionesRol : seguridadRestriccionesRolList) {
            seguridadRestriccionesRol.setCreacionUsuario(userId);
            seguridadRestriccionesRol.setCreacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(seguridadRestriccionesRolDAO.save(seguridadRestriccionesRol));
        }
        return savedList;
    }
    @Override
     public void save(int userId, int rolId,int aplicacionId,
        List<CatalogoParametro> catalogoParametro) {
        List<SeguridadRolRestriccionPar> detalleActualList= getRestriccionesByIds(rolId,aplicacionId);
        List<SeguridadRolRestriccionPar> detalleNuevoList= new ArrayList<SeguridadRolRestriccionPar>();
        List<SeguridadRolRestriccionPar> detalleActualizarList= new ArrayList<SeguridadRolRestriccionPar>();
        boolean existe=false;
        //Se crean los objetos de Restricción en base al catalogoParametro
        for (CatalogoParametro catPar : catalogoParametro) {
            existe=false;
            for (SeguridadRolRestriccionPar restricciones : detalleActualList) {
                if(catPar.getCatalogoParametroPK().getCatalogoParametroId().intValue()== restricciones.getCatalogoParametroId().intValue()
                        && catPar.getCatalogoParametroPK().getOrganizacionId().intValue()== restricciones.getOrganizacionId().intValue()){
                    existe=true;
                    break;
                }
            }
            if(!existe){
                detalleNuevoList.add(crearRestriccion(rolId,aplicacionId,catPar));
            }
        }
        // Delete
        //Se revisa que elemento ya no existen
        for (SeguridadRolRestriccionPar detalleActual : detalleActualList) {
            boolean isDeletable = true;
            for (CatalogoParametro catPar : catalogoParametro) {
                if (detalleActual.getCatalogoParametroId().intValue()
                        == catPar.getCatalogoParametroPK().getCatalogoParametroId().intValue()
                        && detalleActual.getOrganizacionId().intValue()== catPar.getCatalogoParametroPK().getOrganizacionId().intValue()) {
                    isDeletable = false;
                    break;
                }
            }
            if (isDeletable) {
                delete(userId,Arrays.asList(detalleActual));
            }
        }
        update(userId,detalleActualizarList);
        save(userId,detalleNuevoList);     
    }

    /*
 * Método que actualiza la información de <b>seguridadRestriccionesRol</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadRestriccionesRolList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadRolRestriccionPar seguridadRestriccionesRol : seguridadRestriccionesRolList) {
            seguridadRestriccionesRolDAO.update(seguridadRestriccionesRol);
            idList.add(seguridadRestriccionesRol.getCatalogoParametroId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>seguridadRestriccionesRol</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadRestriccionesRolList Lista de registros que se eliminarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<SeguridadRolRestriccionPar> seguridadRestriccionesRolIdList) {
        for (SeguridadRolRestriccionPar seguridadRestricciones : seguridadRestriccionesRolIdList) {
            DetachedCriteria criteria = createDetachedCriteria();
            criteria.add(Example.create(seguridadRestricciones));
            criteria.add(Expression.eq("rolId", seguridadRestricciones.getRolId()));
            criteria.add(Expression.eq("catalogoParametroId", seguridadRestricciones.getCatalogoParametroId()));
            criteria.add(Expression.eq("aplicacionId", seguridadRestricciones.getAplicacionId()));
            criteria.add(Expression.eq("organizacionId", seguridadRestricciones.getOrganizacionId()));
            SeguridadRolRestriccionPar seguridadRestriccionesRol = seguridadRestriccionesRolDAO.findByCriteria(criteria).get(0);
            seguridadRestriccionesRolDAO.delete(seguridadRestriccionesRol);
        }
    }
    /*
 * Método que busca el registro <b>seguridadRestriccionesRol</b> por su NOMBRE en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Seguridad_Rol_Restriccion_Par</b> con la información de la consulta.
     */
    @Override
    public SeguridadRolRestriccionPar findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>seguridadRestriccionesRol</b> por su ID en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>Seguridad_Rol_Restriccion_Par</b> con la información del registro <b>seguridadRestriccionesRol</b>.
     */
    @Override
    public SeguridadRolRestriccionPar findById(Integer seguridadRestriccionesRolId) {
        SeguridadRolRestriccionPar seguridadRestriccionesRol = seguridadRestriccionesRolDAO.findById(seguridadRestriccionesRolId);
        if (seguridadRestriccionesRol == null) {
            return null;
        } else {
            return Arrays.asList(seguridadRestriccionesRol).get(0);
        }
    }

    @Override
    public List<SeguridadRolRestriccionPar> findByIntProperty(String propertyName, Integer value) {
        return findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value)));
    }

    @Override
    public SeguridadRolRestriccionPar findFirst() {
        List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList = findByCriteria(createDetachedCriteria());
        if (seguridadRestriccionesRolList.isEmpty()) {
            return null;
        } else {
            return seguridadRestriccionesRolList.get(0);
        }
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return seguridadRestriccionesRolDAO.findSizeByCriteria((criteria));
    }

    public List<SeguridadRolRestriccionPar> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadRestriccionesRolDAO.findByCriteriaLimit((criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>Seguridad_Rol_Restriccion_Par</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadRolRestriccionPar> findByCriteria(DetachedCriteria criteria) {
        return seguridadRestriccionesRolDAO.findByCriteria(criteria);
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Seguridad_Rol_Restriccion_Par</b>.
     */
    @Override
    public List<SeguridadRolRestriccionPar> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return seguridadRestriccionesRolDAO.createDetachedCriteria();
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

     /*
 * Métodos que obtiene  datos para jsp
     */
    @Override
    public List<SeguridadRolRestriccionPar> getRestriccionesByCatOrg(int rolId,int aplicacionId,int organizacionId){
         DetachedCriteria criteria = createDetachedCriteria();
            criteria.add(Example.create(new SeguridadRolRestriccionPar()));
            criteria.add(Expression.eq("rolId", rolId));
            criteria.add(Expression.eq("aplicacionId", aplicacionId));
            criteria.add(Expression.eq("organizacionId", organizacionId));
        return seguridadRestriccionesRolDAO.findByCriteria(criteria);
    }
    @Override
    public List<SeguridadRolRestriccionPar> getRestriccionesByIds(int rolId,int aplicacionId){
         DetachedCriteria criteria = createDetachedCriteria();
            criteria.add(Example.create(new SeguridadRolRestriccionPar()));
            criteria.add(Expression.eq("rolId", rolId));
            criteria.add(Expression.eq("aplicacionId", aplicacionId));
        return seguridadRestriccionesRolDAO.findByCriteria(criteria);
    }
     
    public SeguridadRolRestriccionPar crearRestriccion(int rolId,int aplicacionId,CatalogoParametro catPar){
        SeguridadRolRestriccionPar res = new SeguridadRolRestriccionPar();
                   res.setRolId(rolId);
                   res.setCatalogoParametroId(catPar.getCatalogoParametroPK().getCatalogoParametroId());
                   res.setOrganizacionId(catPar.getCatalogoParametroPK().getOrganizacionId());
                   res.setAplicacionId(aplicacionId);
        return res;
    }
    
    @Override
    public List<Aplicacion> getAplicaciones(){
         return aplicacionBss.getAplicacionesCombo();
    }
    
    @Override
    public List<Catalogo> getOrganizacionesByCatalogo(int catalogoId) {
        return catalogoBss.findByIntProperty("catalogoId", catalogoId);
    }
    
    @Override
    public List<Organizacion> getOrganizaciones() {
        return organizacionBss.findByCriteriaIgnorePrivacy(new Organizacion());
    }
    
    @Override
    public List<SeguridadRol> getComboRolesByAplicacion(int aplicacionId) {
        return seguridadRolBss.getComboRolesByAplicacion(aplicacionId);
    }
    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */

    @Override
    public String hasGrid() {
        return hasGrid(SeguridadRolRestriccionParAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadRolRestriccionParAction.class).toString();
    }
    
    @Override
    public String hasGridCatalogo() {
        return catalogoBss.hasGrid().toString();
    }
    @Override
    public String hasGridCatalogoParametro() {
        return catalogoParametroBss.hasGrid().toString();
    }
    
    @Override
    public String isIndividualCatalogoParametro() {
        return catalogoParametroBss.isIndividual();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadRolRestriccionParAction.class);
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
    public void setSeguridadRestriccionesRolDAO(SeguridadRolRestriccionParDAO seguridadRestriccionesRolDAO) {
        this.seguridadRestriccionesRolDAO = seguridadRestriccionesRolDAO;
    }
    
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
    
    public void setSeguridadRolBss(SeguridadRolBss seguridadRolBss) {
        this.seguridadRolBss = seguridadRolBss;
    }
    
    
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
        this.catalogoParametroBss = catalogoParametroBss;
    }

    public void setAplicacionBss(AplicacionBss aplicacionBss) {
        this.aplicacionBss = aplicacionBss;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }
    
}
