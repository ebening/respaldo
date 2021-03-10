package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.AfiliacionBanamexDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.AfiliacionBanamexAction;
import com.admon.pkg.dao.AfiliacionPKGDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class AfiliacionBanamexBssImpl extends BaseBss implements AfiliacionBanamexBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private AfiliacionBanamexDAO afiliacionBanamexDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private CatalogoBss catalogoBss;
    private AfiliacionPKGDAO afiliacionPKGDAO;

    public AfiliacionBanamexBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>AfiliacionBanamex</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>AfiliacionBanamex</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<AfiliacionBanamex> afiliacionBanamexList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (AfiliacionBanamex afiliacionBanamex : afiliacionBanamexList) {
            afiliacionBanamex.setEliminadoId(NOELIMINADO);
            afiliacionBanamex.setModificacionUsuario(userId);
            afiliacionBanamex.setCreacionUsuario(userId);
            afiliacionBanamex.setCreacionFecha(new Timestamp(new Date().getTime()));
            afiliacionBanamex.setModificacionFecha(new Timestamp(new Date().getTime()));
            afiliacionBanamex.setTieneParcializacion(NO);
            afiliacionBanamex.setTieneTerminales(NO);
            savedList.add(afiliacionBanamexDAO.save(afiliacionBanamex));
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>afiliacionBanamex</b> en la tabla de <b>AfiliacionBanamex</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param afiliacionBanamexList Lista de registros que se actualizarán en la tabla de <b>AfiliacionBanamex</b> en la BD.
     */
    @Override
    public List<String> update(Integer userId, List<AfiliacionBanamex> afiliacionBanamexList) {
        List<String> idList = new ArrayList();
        for (AfiliacionBanamex afiliacionBanamex : afiliacionBanamexList) {
            afiliacionBanamex.setModificacionUsuario(userId);
            afiliacionBanamex.setModificacionFecha(new Timestamp(new Date().getTime()));
            afiliacionBanamexDAO.update(afiliacionBanamex);
            idList.add(afiliacionBanamex.getAfiliacionBanamex());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>afiliacionBanamex</b> en la tabla de <b>AfiliacionBanamex</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param afiliacionBanamexList Lista de registros que se eliminarán en la tabla de <b>AfiliacionBanamex</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> afiliacionBanamexList) {
        for (Integer afiliacion : afiliacionBanamexList) {
            AfiliacionBanamex afiliacionBanamex = findById(afiliacion);
            afiliacionBanamex.setEliminadoId(ELIMINADO);
            afiliacionBanamex.setModificacionUsuario(userId);
            afiliacionBanamex.setModificacionFecha(new Timestamp(new Date().getTime()));
            afiliacionBanamexDAO.update(afiliacionBanamex);
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
    public boolean isValidoByAfiliacion(String afiliacion) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("afiliacionBanamex", afiliacion));
        return !(findByCriteria(criteria).size() >= 1);
    }
    @Override
    public boolean isValidoByAfiliacionCanal(String afiliacion,Integer canalVenta) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("afiliacionBanamex", afiliacion));
        criteria.add(Expression.eq("canalVentaId", canalVenta));
        return !(findByCriteria(criteria).size() >= 1);
    }
    
    @Override
    public boolean existValorDefault(String afiliacion,Integer canalVentaId) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("afiliacionBanamex", afiliacion));
        criteria.add(Expression.eq("canalVentaId",canalVentaId));
        boolean b = findByCriteria(criteria).size() >= 1;
        return (findByCriteria(criteria).size() >= 1);
    }

    /*
 * Método que busca el registro <b>afiliacionBanamex</b> por su NOMBRE en la tabla de <b>AfiliacionBanamex</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>AfiliacionBanamex</b> con la información de la consulta.
     */
    @Override
    public AfiliacionBanamex findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
        .add(Expression.eq("afiliacionBanamex", nombre.trim()))));
    }
    
    @Override
    public AfiliacionBanamex findById(Integer afiliacionBanamexId) {
        AfiliacionBanamex afiliacionBanamex = afiliacionBanamexDAO.findById(afiliacionBanamexId);
        if (afiliacionBanamex == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(afiliacionBanamex)).get(0);
        }
    }

    /*
 * Método que hace una consulta a la tabla AfiliacionBanamex y obtiene los registros que coincidan con el objeto <b>afiliacionBanamex</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param AfiliacionBanamex objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
        String ordenTipo, AfiliacionBanamex afiliacionBanamex) {
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
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<AfiliacionBanamex> afiliacionBanamexList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(afiliacionBanamexList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public AfiliacionBanamex findFirst() {
        List<AfiliacionBanamex> afiliacionBanamexList = findByCriteria(createDetachedCriteria());
        if (afiliacionBanamexList.isEmpty()) {
            return null;
        } else {
            return afiliacionBanamexList.get(0);
        }
    }

    public List<AfiliacionBanamex> resolveDescription(List<AfiliacionBanamex> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<CatalogoParametro> canalVentaList = getCatalogos("T_CANAL_VENTA");
            List<CatalogoParametro> tipoCanalVentaList = getCatalogos("CANAL_VENTA");
            List<CatalogoParametro> versionList = getCatalogos("VERSION_AFILIACIONES");
            for (AfiliacionBanamex afiliacionBanamex : list) {
                
                afiliacionBanamex.setCanalVenta("");
                if (afiliacionBanamex.getCanalVentaId() != null) {
                    for (CatalogoParametro c : canalVentaList) {
                        if (c.getCatalogoParametroPK().getCatalogoParametroId().intValue() == afiliacionBanamex.getCanalVentaId()) {
                            afiliacionBanamex.setCanalVenta(c.getValor());
                            break;
                        }
                    }
                }
                afiliacionBanamex.setTipoCanalVenta("");
                if (afiliacionBanamex.getTipoCanalVentaId()!= null) {
                    for (CatalogoParametro c : tipoCanalVentaList) {
                        if (c.getCatalogoParametroPK().getCatalogoParametroId().intValue() == afiliacionBanamex.getTipoCanalVentaId()) {
                            afiliacionBanamex.setTipoCanalVenta(c.getValor());
                            break;
                        }
                    }
                }
                afiliacionBanamex.setVersion("");
                if (afiliacionBanamex.getVersionId()!= null) {
                    for (CatalogoParametro c : versionList) {
                        if (c.getCatalogoParametroPK().getCatalogoParametroId().intValue() == Integer.parseInt(afiliacionBanamex.getVersionId())) {
                            afiliacionBanamex.setVersion(c.getValor());
                            break;
                        }
                    }
                }
                afiliacionBanamex.setUsuarioModificacion("");
                if (afiliacionBanamex.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == afiliacionBanamex.getModificacionUsuario().intValue()) {
                            afiliacionBanamex.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }
                afiliacionBanamex.setEliminado("");
                if (afiliacionBanamex.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == afiliacionBanamex.getEliminadoId()) {
                            afiliacionBanamex.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                
                afiliacionBanamex.setEstatus("");
                if (afiliacionBanamex.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == afiliacionBanamex.getEstatusId()) {
                            afiliacionBanamex.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }
                
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return afiliacionBanamexDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<AfiliacionBanamex> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return afiliacionBanamexDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>AfiliacionBanamex</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<AfiliacionBanamex> findByCriteria(DetachedCriteria criteria) {
        return afiliacionBanamexDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<AfiliacionBanamex> findByCriteriaIgnorePrivacy(AfiliacionBanamex afiliacionBanamex) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(afiliacionBanamex));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return afiliacionBanamexDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, AfiliacionBanamexAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>AfiliacionBanamex</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>AfiliacionBanamex</b>.
     */
    @Override
    public List<AfiliacionBanamex> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }
    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>rolList</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param rolList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> afiliacionList) {
        for (Integer afiliacionBanamexId : afiliacionList) {
            AfiliacionBanamex afiliacionBanamex = findById(afiliacionBanamexId);
            if (estatusId == ACTIVO.intValue()) {
                afiliacionBanamex.setEstatusId(ACTIVO);
            } else {
                afiliacionBanamex.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(afiliacionBanamex));
        }
    }
    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return afiliacionBanamexDAO.createDetachedCriteria();
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
    public AfiliacionBanamex getCatalogosAfiliaciones(String catalogKey) {
        //List<AfiliacionBanamex> afiliacionList = new ArrayList();
        AfiliacionBanamex afiliacion = new AfiliacionBanamex();
        for (CatalogoParametro catalogo : catalogoBss.getCatalogosByClave(catalogKey)) {
            if(catalogo.getClave().equals("URL")){
                afiliacion.setUrl(catalogo.getValor());
            }
            if(catalogo.getClave().equals("COMMAND")){
                afiliacion.setCommand(catalogo.getValor());
            }
            if(catalogo.getClave().equals("RETURN_URL")){
                afiliacion.setReturnUrl(catalogo.getValor());
            }
            if(catalogo.getClave().equals("ACTION_URL")){
                afiliacion.setActionUrl(catalogo.getValor());
            }
            if(catalogo.getClave().equals("CLAVE_INSTITUCION_BANCARIA")){
                afiliacion.setClaveInstitucionBancaria(catalogo.getValor());
            }
            if(catalogo.getClave().equals("VALIDA_BANAMEX")){
                afiliacion.setValidaBanamex(Integer.parseInt(catalogo.getValor()));
            }
            if(catalogo.getClave().equals("URL_CANCELACION")){
                afiliacion.setUrlCancelacion(catalogo.getValor());
            }
            if(catalogo.getClave().equals("URL_DEVOLUCION")){
                afiliacion.setUrlDevolucion(catalogo.getValor());
            }
            if(catalogo.getClave().equals("TRN_CUR_ID1")){
                afiliacion.setTrnCurId(catalogo.getValor());
            }
            afiliacion.setEliminadoId(NOELIMINADO);
            afiliacion.setEstatusId(ACTIVO);
        }
        return afiliacion;
    }
    
    @Override
    public List getCatalogos(String catalogKey) {
        return catalogoBss.getCatalogosByClave(catalogKey);
    }
    @Override
    public Grid desplegar(Integer userId,int displayedPage, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion) {
        Grid grid = new Grid();
        List<Bitacora> list = null;
        String spName = "{call PUBLICA_AFILIACIONES_PKG.PRC_PROCESO_BANAMEX(?,?,?,?,?)}";
        try {
            list = afiliacionPKGDAO.callSP(spName, organizaciones, elementos, idEjecucion, userId);
        } catch (SQLException ex) {
            Logger.getLogger(AfiliacionBanamexBssImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int resultadosTotales = list.size();
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
        
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
    public String getReportDataTest(String order, String ordenTipo, AfiliacionBanamex afiliacionBanamex) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, afiliacionBanamex);
        return addReportDataToSession(grid.getGrid());
    }

    @Override
    public String hasGrid() {
        return hasGrid(AfiliacionBanamexAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(AfiliacionBanamexAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(AfiliacionBanamexAction.class);
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
    
    public void setAfiliacionBanamexDAO(AfiliacionBanamexDAO afiliacionBanamexDAO) {
        this.afiliacionBanamexDAO = afiliacionBanamexDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    /**
     * @param catalogoBss the catalogoBss to set
     */
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

    /**
     * @param afiliacionPKGDAO the afiliacionPKGDAO to set
     */
    public void setAfiliacionPKGDAO(AfiliacionPKGDAO afiliacionPKGDAO) {
        this.afiliacionPKGDAO = afiliacionPKGDAO;
    }
    
}
