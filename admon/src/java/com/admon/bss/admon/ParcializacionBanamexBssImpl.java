package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ParcializacionBanamexDAO;
import com.admon.entity.admon.*;
//import com.admon.model.admon.AfiliacionBanamexAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class ParcializacionBanamexBssImpl extends BaseBss implements ParcializacionBanamexBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ParcializacionBanamexDAO parcializacionBanamexDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private AfiliacionBanamexBss afiliacionBanamexBss;

    public ParcializacionBanamexBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>AfiliacionBanamex</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>AfiliacionBanamex</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<ParcializacionBanamex> afiliacionBanamexList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (ParcializacionBanamex afiliacionBanamex : afiliacionBanamexList) {
            afiliacionBanamex.setModificacionUsuario(userId);
            afiliacionBanamex.setCreacionUsuario(userId);
            afiliacionBanamex.setCreacionFecha(new Timestamp(new Date().getTime()));
            afiliacionBanamex.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(parcializacionBanamexDAO.save(afiliacionBanamex));
            AfiliacionBanamex afiliacion = afiliacionBanamexBss.findByName(afiliacionBanamex.getAfiliacionBanamex());
            afiliacion.setTieneParcializacion("SI");
            afiliacionBanamexBss.update(userId, Arrays.asList(afiliacion));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>afiliacionBanamex</b> en la tabla de <b>AFILIACION_BANAMEX_PLAN</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param afiliacionBanamexList Lista de registros que se actualizarán en la tabla de <b>AFILIACION_BANAMEX_PLAN</b> en la BD.
     */
    @Override
    public List<String> update(Integer userId, List<ParcializacionBanamex> afiliacionBanamexList) {
        List<String> idList = new ArrayList();
        for (ParcializacionBanamex afiliacionBanamex : afiliacionBanamexList) {
            afiliacionBanamex.setModificacionUsuario(userId);
            afiliacionBanamex.setModificacionFecha(new Timestamp(new Date().getTime()));
            parcializacionBanamexDAO.update(afiliacionBanamex);
            idList.add(afiliacionBanamex.getAfiliacionBanamex());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>afiliacionBanamex</b> en la tabla de <b>AFILIACION_BANAMEX_PLAN</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param afiliacionBanamexList Lista de registros que se eliminarán en la tabla de <b>AFILIACION_BANAMEX_PLAN</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> afiliacionBanamexList) {
        ParcializacionBanamex parcializacion=null;
        for (Integer afiliacion : afiliacionBanamexList) {
            parcializacion = findById(afiliacion);
            parcializacion.setModificacionUsuario(userId);
            parcializacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            parcializacionBanamexDAO.delete(parcializacion);
        }
        if(parcializacion !=null){
            DetachedCriteria criteria = createDetachedCriteria();
            criteria.add(Expression.eq("afiliacionBanamex", parcializacion.getAfiliacionBanamex()));
            int valor = findSizeByCriteria(copy(criteria));
            if(valor <= 0){
                String a = parcializacion.getAfiliacionBanamex();
                AfiliacionBanamex afiliacion = afiliacionBanamexBss.findByName(parcializacion.getAfiliacionBanamex());
                afiliacion.setTieneParcializacion("NO");
                afiliacionBanamexBss.update(userId, Arrays.asList(afiliacion));
                System.out.println("estamos entrando");
            }
        }
    }

    /*
 * Método que evalúa si existe al menos un registro con una determinada
 * comisión por organización en la tabla.
 * @param organizacionId Es el nombre que se desea buscar en la tabla de la BD.
 * @param afiliacionBanamexId Es la comisión se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    @Override
    public boolean isValidoAfiliacionBanamex(String afiliacion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("afiliacion", afiliacion));
        return (findByCriteria(criteria).size() >= 1);
    }

    /*
 * Método que busca el registro <b>afiliacionBanamex</b> por su NOMBRE en la tabla de <b>AFILIACION_BANAMEX_PLAN</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>AFILIACION_BANAMEX_PLAN</b> con la información de la consulta.
     */
    @Override
    public ParcializacionBanamex findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
        .add(Expression.eq("afiliacion", nombre.trim()))));
    }
    
    @Override
    public ParcializacionBanamex findById(Integer afiliacionBanamexId) {
        ParcializacionBanamex afiliacionBanamex = parcializacionBanamexDAO.findById(afiliacionBanamexId);
        if (afiliacionBanamex == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(afiliacionBanamex)).get(0);
        }
    }

    /*
 * Método que hace una consulta a la tabla AFILIACION_BANAMEX_PLAN y obtiene los registros que coincidan con el objeto <b>AFILIACION_BANAMEX_PLAN</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param AfiliacionBanamex objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, ParcializacionBanamex afiliacionBanamex, String afiliacion) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (afiliacionBanamex.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(afiliacionBanamex.getCreacionFechaInicial())));
        }
        if (afiliacionBanamex.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(afiliacionBanamex.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (afiliacionBanamex.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(afiliacionBanamex.getModificacionFechaInicial())));
        }
        if (afiliacionBanamex.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(afiliacionBanamex.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(afiliacionBanamex));
        if (ordenTipo.equals("asc")) {  
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        criteria.add(Expression.eq("afiliacionBanamex", afiliacion));
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<ParcializacionBanamex> afiliacionBanamexList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(afiliacionBanamexList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public ParcializacionBanamex findFirst() {
        List<ParcializacionBanamex> afiliacionBanamexList = findByCriteria(createDetachedCriteria());
        if (afiliacionBanamexList.isEmpty()) {
            return null;
        } else {
            return afiliacionBanamexList.get(0);
        }
    }

    public List<ParcializacionBanamex> resolveDescription(List<ParcializacionBanamex> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            for (ParcializacionBanamex afiliacionBanamex : list) {

                afiliacionBanamex.setUsuarioModificacion("");
                if (afiliacionBanamex.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == afiliacionBanamex.getModificacionUsuario().intValue()) {
                            afiliacionBanamex.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return parcializacionBanamexDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<ParcializacionBanamex> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return parcializacionBanamexDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>AFILIACION_BANAMEX_PLAN</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<ParcializacionBanamex> findByCriteria(DetachedCriteria criteria) {
        return parcializacionBanamexDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<ParcializacionBanamex> findByCriteriaIgnorePrivacy(ParcializacionBanamex afiliacionBanamex) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(afiliacionBanamex));
        return parcializacionBanamexDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        resolvePrivacy(criteria, ParcializacionBanamexBss.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>AFILIACION_BANAMEX_PLAN</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>AFILIACION_BANAMEX_PLAN</b>.
     */
    @Override
    public List<ParcializacionBanamex> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }
    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return parcializacionBanamexDAO.createDetachedCriteria();
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
    public String getReportDataTest(String order, String ordenTipo, ParcializacionBanamex afiliacionBanamex, String afiliacion) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, afiliacionBanamex, afiliacion);
        return addReportDataToSession(grid.getGrid());
    }
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    /**
     * @param parcializacionBanamexDAO the parcializacionBanamexDAO to set
     */
    public void setParcializacionBanamexDAO(ParcializacionBanamexDAO parcializacionBanamexDAO) {
        this.parcializacionBanamexDAO = parcializacionBanamexDAO;
    }
    
    /**
     * @param usuarioBss the usuarioBss to set
     */
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    /**
     * @param configuracionParametroBss the configuracionParametroBss to set
     */
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    /**
     * @param afiliacionBanamexBss the afiliacionBanamexBss to set
     */
    public void setAfiliacionBanamexBss(AfiliacionBanamexBss afiliacionBanamexBss) {
        this.afiliacionBanamexBss = afiliacionBanamexBss;
    }
}
