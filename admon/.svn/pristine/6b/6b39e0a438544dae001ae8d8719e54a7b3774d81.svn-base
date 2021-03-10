package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ParcializacionSantanderDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.AfiliacionSantanderAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class ParcializacionSantanderBssImpl extends BaseBss implements ParcializacionSantanderBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ParcializacionSantanderDAO parcializacionSantanderDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    //private CatalogoBss catalogoBss;

    public ParcializacionSantanderBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>ParcializacionSantander</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>ParcializacionSantander</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Long> save(Integer userId, List<ParcializacionSantander> objList) {
        List<Long> savedList = new ArrayList<Long>();
        for (ParcializacionSantander obj : objList) {
            obj.setModificacionUsuario(userId);
            obj.setCreacionUsuario(userId);
            obj.setCreacionFecha(new Timestamp(new Date().getTime()));
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(parcializacionSantanderDAO.save(obj));
//            parcializacionSantanderDAO.saveAplicacion(obj);
        }
        return savedList;
    }

    @Override
    public void saveConfiguracionParametro(int userId, int parcializacionSantanderId,
            List<ConfiguracionParametro> detalleNuevoList) {
        List<ConfiguracionParametro> detalleActualList
                = configuracionParametroBss.findByIntProperty("parcializacionSantanderId",
                        parcializacionSantanderId);
        // Delete
        for (ConfiguracionParametro detalle : detalleActualList) {
            boolean isDeletable = true;
            for (ConfiguracionParametro detalleNuevo : detalleNuevoList) {
                if (detalle.getConfiguracionParametroId()
                        == detalleNuevo.getConfiguracionParametroId()) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                configuracionParametroBss.delete(userId,
                        Arrays.asList(detalle.getConfiguracionParametroId()));
            }
        }
        // Save y update
        for (ConfiguracionParametro detalle : detalleNuevoList) {
            if (detalle.getConfiguracionParametroId() == 0) {
                configuracionParametroBss.save(userId, Arrays.asList(detalle));
            } else {
                configuracionParametroBss.update(userId, Arrays.asList(detalle));
            }
        }
    }

    /*
 * Método que actualiza la información de <b>ParcializacionSantander</b> en la tabla de <b>ParcializacionSantander</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objList Lista de registros que se actualizarán en la tabla de <b>ParcializacionSantander</b> en la BD.
     */
    @Override
    public List<Long> update(Integer userId, List<ParcializacionSantander> objList) {
        List<Long> idList = new ArrayList();
        for (ParcializacionSantander obj : objList) {
            obj.setModificacionUsuario(userId);
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            parcializacionSantanderDAO.update(obj);
//            parcializacionSantanderDAO.updateAplicacion(aplicacion);
            idList.add(obj.getParcializacionSantanderId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>ParcializacionSantander</b> en la tabla de <b>ParcializacionSantander</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param idList Lista de registros que se eliminarán en la tabla de <b>ParcializacionSantander</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Long> idList) {
        for (Long id : idList) {
            ParcializacionSantander obj = findById(id);
            parcializacionSantanderDAO.delete(obj);
        }
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    @Override
    public boolean isValidoNombre(String nombre) {
        return !(findByCriteria(createDetachedCriteria().add(
                Expression.eq("afiliacion", nombre.trim()))).size() >= 1);
    }

    /*
 * Método que busca el registro <b>ParcializacionSantander</b> por su NOMBRE en la tabla de <b>ParcializacionSantander</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>ParcializacionSantander</b> con la información de la consulta.
     */
    @Override
    public ParcializacionSantander findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("afiliacion", nombre.trim()))));
    }

    /*
 * Busca el registro <b>ParcializacionSantander</b> por su ID en la tabla de <b>ParcializacionSantander</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>ParcializacionSantander</b> con la información del registro <b>id</b>.
     */
    @Override
    public ParcializacionSantander findById(Long id) {
        ParcializacionSantander obj = parcializacionSantanderDAO.findById(id);
        if (obj == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(obj)).get(0);
        }
    }

    @Override
    public List<ParcializacionSantander> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla ParcializacionSantander y obtiene los registros que coincidan con el objeto <b>ParcializacionSantander</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param ParcializacionSantander objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, ParcializacionSantander obj) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
      /*  if (aplicacion.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(aplicacion.getCreacionFechaInicial())));
        }
        if (aplicacion.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(aplicacion.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (aplicacion.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(aplicacion.getModificacionFechaInicial())));
        }
        if (aplicacion.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(aplicacion.getModificacionFechaFinal())));
        }*/
        criteria.add(Example.create(obj));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<ParcializacionSantander> objList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(objList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public ParcializacionSantander findFirst() {
        List<ParcializacionSantander> objList = findByCriteria(createDetachedCriteria());
        if (objList.isEmpty()) {
            return null;
        } else {
            return objList.get(0);
        }
    }

    public List<ParcializacionSantander> resolveDescription(List<ParcializacionSantander> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (ParcializacionSantander obj : list) {

//                obj.setUsuarioModificacion("");
//                if (obj.getModificacionUsuario() != null) {
//                    for (Usuario o : usuarioList) {
//                        if (o.getUsuarioId().intValue() == obj.getModificacionUsuario().intValue()) {
//                            obj.setUsuarioModificacion(o.getUsuario());
//                            break;
//                        }
//                    }
//                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return parcializacionSantanderDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<ParcializacionSantander> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return parcializacionSantanderDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>ParcializacionSantander</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<ParcializacionSantander> findByCriteria(DetachedCriteria criteria) {
        return parcializacionSantanderDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<ParcializacionSantander> findByCriteriaIgnorePrivacy(ParcializacionSantander obj) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(obj));
        return parcializacionSantanderDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        resolvePrivacy(criteria, ParcializacionSantander.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>ParcializacionSantander</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>ParcializacionSantander</b>.
     */
    @Override
    public List<ParcializacionSantander> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("afiliacion")));
    }



    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return parcializacionSantanderDAO.createDetachedCriteria();
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
 * Método que hace una consulta a la tabla Usuario y obtiene todos sus
 * registros correctamente filtrados con un objeto DetachedCriteria para ser
 * utilizados en los reportes de los grid (PDF y Excel).
 *
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @return Regresa una lista de objetos <b>Usuario</b> con los datos a
 * mostrar en el reporte.
     */
    @Override
    public String getReportDataTest(String order, String ordenTipo, ParcializacionSantander obj) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, obj);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    /* @Override
    public List<ParcializacionSantander> getConfiguracionParametroAplicacion() {
        return configuracionParametroBss.getConfiguracion();
    }*/
    // </editor-fold>
    @Override
    public String hasGrid() {
        return hasGrid(ParcializacionSantander.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(ParcializacionSantander.class).toString();
    }

    @Override
    public String hasGridAplicacionParametro() {
        return configuracionParametroBss.hasGrid().toString();
    }

    @Override
    public String isIndividualAplicacionParametro() {
        return configuracionParametroBss.isIndividual().toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(AfiliacionSantanderAction.class);
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
    public void setParcializacionSantanderDAO(ParcializacionSantanderDAO parcializacionSantanderDAO) {
        this.parcializacionSantanderDAO = parcializacionSantanderDAO;
    }

   
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

 
}
