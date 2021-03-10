package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ConfiguracionFormaPagoDAO;
import com.admon.entity.admon.*;
import com.admon.pkg.dao.AfiliacionPKGDAO;
import com.admon.model.admon.ConfiguracionFormaPagoAction;
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

public class ConfiguracionFormaPagoBssImpl extends BaseBss implements ConfiguracionFormaPagoBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private ConfiguracionFormaPagoDAO configuracionFormaPagoDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private PayNetBss payNetBss;
    private PayPalBss payPalBss;
    private CatalogoBss catalogoBss;
    private AfiliacionPKGDAO afiliacionPKGDAO;

    public ConfiguracionFormaPagoBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>ConfiguracionFormaPago</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>ConfiguracionFormaPago</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<ConfiguracionFormaPago> objList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (ConfiguracionFormaPago obj : objList) {
            obj.setEliminadoId(NOELIMINADO);
            obj.setCreacionUsuario(userId);
            obj.setCreacionFecha(new Timestamp(new Date().getTime()));
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(configuracionFormaPagoDAO.save(obj));
            if(obj.getClaveFormaPago().equals(PAYNET)){
                PayNet p = new PayNet();
                p.setAfiliacion(obj.getAfiliacion());
                p.setUserName(obj.getUsername());
                p.setPassword(obj.getPassword());
                p.setSignature(obj.getSignature());
                p.setPayNetId(savedList.get(0));
                p.setCreacionFecha(new Timestamp(new Date().getTime()));
                p.setCreacionUsuario(userId);
                payNetBss.save(userId, Arrays.asList(p));
            }else{
                if(obj.getClaveFormaPago().equals(PAYPAL)){
                    PayPal p = new PayPal();
                    p.setPayPalId(savedList.get(0));
                    p.setCreacionFecha(new Timestamp(new Date().getTime()));
                    p.setCreacionUsuario(userId);
                    payPalBss.save(userId, Arrays.asList(p));
                }
            }
        }
        return savedList;
    }

    /*
 * Método que actualiza la información de <b>ConfiguracionFormaPago</b> en la tabla de <b>ConfiguracionFormaPago</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objList Lista de registros que se actualizarán en la tabla de <b>ConfiguracionFormaPago</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<ConfiguracionFormaPago> objList) {
        List<Integer> idList = new ArrayList();
        for (ConfiguracionFormaPago obj : objList) {
            obj.setModificacionUsuario(userId);
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            configuracionFormaPagoDAO.update(obj);
            if(obj.getClaveFormaPago().equals(PAYNET)){
                PayNet p = (payNetBss.findByIntProperty("payNetId", obj.getConfiguracionFormaPagoId())).get(0);
                p.setAfiliacion(obj.getAfiliacion());
                p.setUserName(obj.getUsername());
                p.setPassword(obj.getPassword());
                p.setSignature(obj.getSignature());
                payNetBss.update(userId, Arrays.asList(p));
            }
            idList.add(obj.getConfiguracionFormaPagoId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>ConfiguracionFormaPago</b> en la tabla de <b>ConfiguracionFormaPago</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param idList Lista de registros que se eliminarán en la tabla de <b>ConfiguracionFormaPago</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> idList) {
        for (Integer id : idList) {
            ConfiguracionFormaPago obj = findById(id);
            obj.setEliminadoId(ELIMINADO);
            obj.setModificacionUsuario(userId);
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            configuracionFormaPagoDAO.update(obj);
            if(obj.getClaveFormaPago().equals(PAYNET)){
                payNetBss.delete(userId, Arrays.asList(obj.getConfiguracionFormaPagoId()));
            }else{
                if(obj.getClaveFormaPago().equals(PAYPAL)){
                    payPalBss.delete(userId, Arrays.asList(obj.getConfiguracionFormaPagoId()));
                }
            }
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
      /*  return !(findByCriteria(createDetachedCriteria().add(
                Expression.eq("afiliacion", nombre.trim()))).size() >= 1);*/
      return true;
    }

    /*
 * Método que busca el registro <b>ConfiguracionFormaPago</b> por su NOMBRE en la tabla de <b>ConfiguracionFormaPago</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>ConfiguracionFormaPago</b> con la información de la consulta.
     */
    @Override
    public ConfiguracionFormaPago findByName(String nombre,String field) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq(field, nombre.trim()))));
    }

    /*
 * Busca el registro <b>ConfiguracionFormaPago</b> por su ID en la tabla de <b>ConfiguracionFormaPago</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>ConfiguracionFormaPago</b> con la información del registro <b>id</b>.
     */
    @Override
    public ConfiguracionFormaPago findById(Integer id) {
        ConfiguracionFormaPago obj = configuracionFormaPagoDAO.findById(id);
         if(obj.getClaveFormaPago().equals(PAYNET)){
                PayNet p = (payNetBss.findByIntProperty("payNetId", obj.getConfiguracionFormaPagoId())).get(0);
                obj.setAfiliacion(p.getAfiliacion());
                obj.setPassword(p.getPassword());
            }
        return obj;
    }

    @Override
    public List<ConfiguracionFormaPago> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla ConfiguracionFormaPago y obtiene los registros que coincidan con el objeto <b>ConfiguracionFormaPago</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param ConfiguracionFormaPago objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, ConfiguracionFormaPago obj) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (obj.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(obj.getCreacionFechaInicial())));
        }
        if (obj.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(obj.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (obj.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(obj.getModificacionFechaInicial())));
        }
        if (obj.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(obj.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(obj));
        if (ordenTipo.equals("asc")) {  
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<ConfiguracionFormaPago> configuracion = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(configuracion));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public ConfiguracionFormaPago findFirst() {
        List<ConfiguracionFormaPago> objList = findByCriteria(createDetachedCriteria());
        if (objList.isEmpty()) {
            return null;
        } else {
            return objList.get(0);
        }
    }
    
        /*
 * Método que obtiene todos los registros de la tabla de <b>ConfiguracionFormaPago</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>ConfiguracionFormaPago</b>.
     */
    @Override
    public List<ConfiguracionFormaPago> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("afiliacion")));
    }

    public List<ConfiguracionFormaPago> resolveDescription(List<ConfiguracionFormaPago> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
           
            for (ConfiguracionFormaPago obj : list) {
                obj.setUsuarioModificacion("");
                if (obj.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == obj.getModificacionUsuario().intValue()) {
                            obj.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }
                obj.setEliminado("");
                if (obj.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == obj.getEliminadoId()) {
                            obj.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                
                obj.setEstatus("");
                if (obj.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == obj.getEstatusId()) {
                            obj.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }
                obj.setAfiliacion("");
                List<PayNet> payNetList = payNetBss.findByIntProperty("payNetId",obj.getConfiguracionFormaPagoId());
                if(payNetList.size()>0){
                    for (PayNet pay : payNetList) {
                         obj.setAfiliacion(pay.getAfiliacion());
                    }
                }
                
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return configuracionFormaPagoDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<ConfiguracionFormaPago> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return configuracionFormaPagoDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>ConfiguracionFormaPago</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<ConfiguracionFormaPago> findByCriteria(DetachedCriteria criteria) {
        return configuracionFormaPagoDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<ConfiguracionFormaPago> findByCriteriaIgnorePrivacy(ConfiguracionFormaPago obj) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(obj));
        return configuracionFormaPagoDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, ConfiguracionFormaPagoAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>ConfiguracionFormaPago</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>ConfiguracionFormaPago</b>.
     */
    @Override
    public List<ConfiguracionFormaPago> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("afiliacion")));
    }



    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return configuracionFormaPagoDAO.createDetachedCriteria();
    }

    /*
    * Método que actualiza el Estatus de los ID' contenidos en la lista
    * <b>configuracionFormaPagoList</b> en la tabla de <b>ConfiguracionFormaPago</b>.
    * @param userId userId Es el ID del usuario que realizó la operación.
    * @param estatusId Es el ID del estatus al cual se quiere cambiar.
    * @param configuracionFormaPagoList Es una lista que contiene los registros los cuales se
    * quiere actualizar su Estatus.
        */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> objList) {
        for (Integer id : objList) {
            ConfiguracionFormaPago obj = findById(id);
            if (estatusId == ACTIVO.intValue()) {
                obj.setEstatusId(ACTIVO);
            } else {
                obj.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(obj));
        }
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
    public String getReportDataTest(String order, String ordenTipo, ConfiguracionFormaPago obj) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, obj);
        return addReportDataToSession(grid.getGrid());
    }

    @Override
    public ConfiguracionFormaPago getCatalogosAfiliaciones(String catalogKey) {
        ConfiguracionFormaPago afiliacion = new ConfiguracionFormaPago();
        List<CatalogoParametro> b = catalogoBss.getCatalogosByClave(catalogKey);
        for (CatalogoParametro catalogo : b) {
            
            if(catalogo.getClave().equals("URL_SITE")){
                afiliacion.setUrlSite(catalogo.getValor());
            }
            if(catalogo.getClave().equals("URL_RETURN")){
                afiliacion.setUrlReturn(catalogo.getValor());
            }
            if(catalogo.getClave().equals("URL_CANCEL")){
                afiliacion.setUrlCancel(catalogo.getValor());
            }
            if(catalogo.getClave().equals("CONNECTION_MODE")){
                afiliacion.setConectionMode(catalogo.getValor());
            }
            if(catalogo.getClave().equals("CURRENCY")){
                afiliacion.setCurrency(catalogo.getValor());
            }
            

            afiliacion.setEliminadoId(NOELIMINADO);
            afiliacion.setEstatusId(ACTIVO);
        }
        return afiliacion;
    }
    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    /* @Override
    public List<ConfiguracionFormaPago> getConfiguracionParametroConfiguracionFormaPago() {
        return configuracionParametroBss.getConfiguracion();
    }*/
    // </editor-fold>
    @Override
    public String hasGrid() {
        return hasGrid(ConfiguracionFormaPagoAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(ConfiguracionFormaPagoAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(ConfiguracionFormaPagoAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }
    
    @Override
    public Grid desplegar(Integer userId,int displayedPage, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion) {
        Grid grid = new Grid();
        List<Bitacora> list = null;
        String spName = "{call PUBLICA_AFILIACIONES_PKG.PRC_PROCESO_OTROS(?,?,?,?,?)}";
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
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

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
     * @param configuracionFormaPagoDAO the configuracionFormaPagoDAO to set
     */
    public void setConfiguracionFormaPagoDAO(ConfiguracionFormaPagoDAO configuracionFormaPagoDAO) {
        this.configuracionFormaPagoDAO = configuracionFormaPagoDAO;
    }

    /**
     * @param payNetBss the payNetBss to set
     */
    public void setPayNetBss(PayNetBss payNetBss) {
        this.payNetBss = payNetBss;
    }

    /**
     * @param payPalBss the payPalBss to set
     */
    public void setPayPalBss(PayPalBss payPalBss) {
        this.payPalBss = payPalBss;
    }
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
