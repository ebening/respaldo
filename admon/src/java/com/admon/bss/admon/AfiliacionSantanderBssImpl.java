package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.AfiliacionSantanderDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.AfiliacionSantanderAction;
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

public class AfiliacionSantanderBssImpl extends BaseBss implements AfiliacionSantanderBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private AfiliacionSantanderDAO afiliacionSantanderDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private CatalogoBss catalogoBss;
    private CatalogoParametroBss catalogoParametroBss;
    private AfiliacionPKGDAO afiliacionPKGDAO;


    public AfiliacionSantanderBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>AfiliacionSantander</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>AfiliacionSantander</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Long> save(Integer userId, List<AfiliacionSantander> objList) {
        List<Long> savedList = new ArrayList<Long>();
        for (AfiliacionSantander obj : objList) {
            obj.setEliminadoId(NOELIMINADO);
            obj.setTieneParcializacion(NO);
            obj.setCreacionUsuario(userId);
            obj.setCreacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(afiliacionSantanderDAO.save(obj));
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
 * Método que actualiza la información de <b>AfiliacionSantander</b> en la tabla de <b>AfiliacionSantander</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objList Lista de registros que se actualizarán en la tabla de <b>AfiliacionSantander</b> en la BD.
     */
    @Override
    public List<Long> update(Integer userId, List<AfiliacionSantander> objList) {
        List<Long> idList = new ArrayList();
        for (AfiliacionSantander obj : objList) {
            obj.setModificacionUsuario(userId);
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            afiliacionSantanderDAO.update(obj);
            idList.add(obj.getAfiliacionSantanderId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>AfiliacionSantander</b> en la tabla de <b>AfiliacionSantander</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param idList Lista de registros que se eliminarán en la tabla de <b>AfiliacionSantander</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Long> idList) {
        for (Long id : idList) {
            AfiliacionSantander obj = findById(id);
            afiliacionSantanderDAO.delete(obj);
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
 * Método que busca el registro <b>AfiliacionSantander</b> por su NOMBRE en la tabla de <b>AfiliacionSantander</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>AfiliacionSantander</b> con la información de la consulta.
     */
    @Override
    public AfiliacionSantander findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("afiliacion", nombre.trim()))));
    }

    /*
 * Busca el registro <b>AfiliacionSantander</b> por su ID en la tabla de <b>AfiliacionSantander</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>AfiliacionSantander</b> con la información del registro <b>id</b>.
     */
    @Override
    public AfiliacionSantander findById(Long id) {
        AfiliacionSantander obj = afiliacionSantanderDAO.findById(id);
        if (obj == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(obj)).get(0);
        }
    }

    @Override
    public List<AfiliacionSantander> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    /*
 * Método que hace una consulta a la tabla AfiliacionSantander y obtiene los registros que coincidan con el objeto <b>AfiliacionSantander</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param AfiliacionSantander objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
            String ordenTipo, AfiliacionSantander obj) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();

        criteria.add(Example.create(obj));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        List<AfiliacionSantander> objList = findAll();
        int resultadosTotales = objList.size();//findSizeByCriteria(copy(criteria));
//        List<AfiliacionSantander1> objList = findByCriteriaLimit(criteria,maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(objList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
//        int resultadosTotales = findSizeByCriteria(copy(criteria));
//        List<AfiliacionSantander> ciudadList = findByCriteriaLimit(criteria,
//                maxResult * (displayedPage - 1), maxResult);
//        grid.setGrid(resolveDescription(ciudadList));
//        grid.setTotal(resultadosTotales);
//        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public AfiliacionSantander findFirst() {
        List<AfiliacionSantander> objList = findByCriteria(createDetachedCriteria());
        if (objList.isEmpty()) {
            return null;
        } else {
            return objList.get(0);
        }
    }
    
        /*
 * Método que obtiene todos los registros de la tabla de <b>Aplicacion</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>Aplicacion</b>.
     */
    @Override
    public List<AfiliacionSantander> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("afiliacion")));
    }

    public List<AfiliacionSantander> resolveDescription(List<AfiliacionSantander> list) {
        if (!list.isEmpty()) {

            for (AfiliacionSantander obj : list) {

               obj.setTipoMoneda("");
                if (obj.getTipoMonedaId() != null) {
                    CatalogoParametro cp = catalogoParametroBss.findById(obj.getTipoMonedaId(),1);
                    obj.setTipoMoneda(cp.getValor());
                }
            }
        }
        return list;
    }

//    public Integer findSizeByCriteria(DetachedCriteria criteria) {
//        return afiliacionSantanderDAO.findSizeByCriteria(generalizarCriteria(criteria));
//    }
    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return afiliacionSantanderDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<AfiliacionSantander> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return afiliacionSantanderDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>AfiliacionSantander</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<AfiliacionSantander> findByCriteria(DetachedCriteria criteria) {
        return afiliacionSantanderDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<AfiliacionSantander> findByCriteriaIgnorePrivacy(AfiliacionSantander obj) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(obj));
        return afiliacionSantanderDAO.findByCriteria(criteria);
    }

//    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
//        resolvePrivacy(criteria, AfiliacionSantanderAction.class);
//        return criteria;
//    }
        public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, AfiliacionSantanderAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>AfiliacionSantander</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>AfiliacionSantander</b>.
     */
    @Override
    public List<AfiliacionSantander> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("afiliacion")));
    }



    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return afiliacionSantanderDAO.createDetachedCriteria();
    }

    /*
    * Método que actualiza el Estatus de los ID' contenidos en la lista
    * <b>aplicacionList</b> en la tabla de <b>Aplicacion</b>.
    * @param userId userId Es el ID del usuario que realizó la operación.
    * @param estatusId Es el ID del estatus al cual se quiere cambiar.
    * @param aplicacionList Es una lista que contiene los registros los cuales se
    * quiere actualizar su Estatus.
        */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Long> objList) {
        for (Long id : objList) {
            AfiliacionSantander obj = findById(id);
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
    public String getReportDataTest(String order, String ordenTipo, AfiliacionSantander obj) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, obj);
        return addReportDataToSession(grid.getGrid());
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    /* @Override
    public List<AfiliacionSantander> getConfiguracionParametroAplicacion() {
        return configuracionParametroBss.getConfiguracion();
    }*/
    // </editor-fold>
    @Override
    public String hasGrid() {
        return hasGrid(AfiliacionSantanderAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(AfiliacionSantanderAction.class).toString();
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
    
    @Override
    public AfiliacionSantander getCatalogosAfiliaciones(String catalogKey) {
        AfiliacionSantander afiliacion = new AfiliacionSantander();
        for (CatalogoParametro catalogo : catalogoBss.getCatalogosByClave(catalogKey)) {
            if(catalogo.getClave().equals("URL")){
                afiliacion.setUrl(catalogo.getValor());
            }
            if(catalogo.getClave().equals("TYPEC")){
                afiliacion.setTypec(catalogo.getValor());
            }
//            if(catalogo.getClave().equals("CRYPTO")){
//                afiliacion.setCrypto(catalogo.getValor());
//            }
            if(catalogo.getClave().equals("BASE_COBRO")){
                afiliacion.setBaseCobro(catalogo.getValor());
            }
            if(catalogo.getClave().equals("BASE_CANCELACION")){
                afiliacion.setBaseCancelacion(catalogo.getValor());
            }
            if(catalogo.getClave().equals("BASE_3DS")){
                afiliacion.setBase3ds(catalogo.getValor());
            }
            if(catalogo.getClave().equals("CLAVE_INSTITUCION_BANCARIA")){
                afiliacion.setClaveInstitucionBancaria(catalogo.getValor());
            }
            if(catalogo.getClave().equals("EMPRESA_AFILIACION_ID")){
                afiliacion.setEmpresaAfiliacionId(Long.valueOf(catalogo.getValor().trim()));
            }
            //Valores Temporales
//            setvaloresTemporales(afiliacion);

            afiliacion.setEliminadoId(NOELIMINADO);
            afiliacion.setEstatusId(ACTIVO);
        }
        return afiliacion;
    }
    
    private void setvaloresTemporales(AfiliacionSantander afiliacion){
    //TEmporal
    
            afiliacion.setTipoCanalVentaId(61);
            afiliacion.setCanalVentaId(7023);
            afiliacion.setAfiliacion("afiliacion");
            afiliacion.setSemilla("Semilla");
            afiliacion.setSemillaConsulta("Semilla consulta");
            afiliacion.setSemilla3ds("Semilla 3ds");
            afiliacion.setUsuario("Usuario");
            afiliacion.setContrasena("contraseña");
            afiliacion.setUsuarioAdmin("U admin");
            afiliacion.setContrasenaAdmin("C admin");
            afiliacion.setIdCompany("Id company");
            afiliacion.setMerchant("merchant");
            afiliacion.setOperacion("Operacion");
            afiliacion.setCrypto("Cripto");
            afiliacion.setCountry("Country");
            afiliacion.setTipoMonedaId(7025);
            afiliacion.setIdBranch("Id branch");
            afiliacion.setNombreEmpresa("Nom empresa");
            afiliacion.setDireccionEmpresa(" Direcion");
            afiliacion.setClaveEstatus("clave estatus");
            afiliacion.setDescripcion("desc");
            afiliacion.setAfiliacionAmex("afiliacion");
            afiliacion.setMerchantAmex("m");
            afiliacion.setOrderSourceSdos("order sdos");
            afiliacion.setOrderSourceMovil("o");
            afiliacion.setDataI("data i");
            afiliacion.setDataK("data k");
    
    }
    @Override
    public Grid desplegar(Integer userId,int displayedPage, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion) {
        Grid grid = new Grid();
        List<Bitacora> list = null;
        String spName = "{call PUBLICA_AFILIACIONES_PKG.PRC_PROCESO_SANTANDER(?,?,?,?,?)}";
        try {
            list = afiliacionPKGDAO.callSP(spName, organizaciones, elementos, idEjecucion, userId);
        } catch (SQLException ex) {
            Logger.getLogger(AfiliacionSantanderBssImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setAfiliacionSantanderDAO(AfiliacionSantanderDAO afiliacionSantanderDAO) {
        this.afiliacionSantanderDAO = afiliacionSantanderDAO;
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
    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
        this.catalogoParametroBss = catalogoParametroBss;
    }

    public void setAfiliacionPKGDAO(AfiliacionPKGDAO afiliacionPKGDAO) {
        this.afiliacionPKGDAO = afiliacionPKGDAO;
    }
    
    
}
