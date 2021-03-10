package com.admon.bss.admon;
import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadPerfilDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadPerfilAction;
import com.admon.pkg.dao.OrganizacionPKGDAO;
import com.admon.pkg.dao.SeguridadPerfilPKGDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadPerfilBssImpl extends BaseBss implements SeguridadPerfilBss{
   /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadPerfilDAO seguridadPerfilDAO;
     private OrganizacionPKGDAO organizacionPKGDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private SeguridadRolBss seguridadRolBss;
    private AplicacionBss aplicacionBss;
    private LenguajeBss lenguajeBss;
    private SeguridadPerfilRolBss seguridadPerfilRolBss;
    private SeguridadPerfilPKGDAO seguridadPerfilPKGDAO;
    private OrganizacionBss organizacionBss;
    public SeguridadPerfilBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Seguridad_Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Seguridad_Perfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadPerfil> seguridadPerfilList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadPerfil seguridadPerfil : seguridadPerfilList) {
            seguridadPerfil.setEstatusId(ACTIVO);
            seguridadPerfil.setEliminadoId(NOELIMINADO);
            seguridadPerfil.setModificacionUsuario(userId);
            seguridadPerfil.setCreacionUsuario(userId);
            seguridadPerfil.setCreacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(seguridadPerfilDAO.save(seguridadPerfil));
            seguridadPerfilDAO.saveParametros(seguridadPerfil, LENGUAJE);
        }
        return savedList;
    }
    
    @Override
    public void saveSeguridadPerfilRol(int userId, int seguridadPerfilId,
        List<SeguridadPerfilRol> detalleNuevoList) {
        List<SeguridadPerfilRol> detalleActualList
                = seguridadPerfilRolBss.findByIntProperty("perfilId",
                        seguridadPerfilId);
        // Delete
        for (SeguridadPerfilRol detalle : detalleActualList) {
            boolean isDeletable = true;
            for (SeguridadPerfilRol detalleNuevo : detalleNuevoList) {
                if (detalle.getSeguridadPerfilRolId().intValue()
                        == detalleNuevo.getSeguridadPerfilRolId().intValue()) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                seguridadPerfilRolBss.delete(userId,
                        Arrays.asList(detalle.getSeguridadPerfilRolId()));
            }
        }
        // Save y update
        for (SeguridadPerfilRol detalle : detalleNuevoList) {
            if (detalle.getSeguridadPerfilRolId() == 0) {
                seguridadPerfilRolBss.save(userId, Arrays.asList(detalle));
            } else {
                seguridadPerfilRolBss.update(userId, Arrays.asList(detalle));
            }
        }
    }


    /*
 * Método que actualiza la información de <b>seguridadPerfil</b> en la tabla de <b>Seguridad_Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadPerfilList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Perfil</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadPerfil> seguridadPerfilList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadPerfil seguridadPerfil : seguridadPerfilList) {
            seguridadPerfil.setModificacionUsuario(userId);
            seguridadPerfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilDAO.update(seguridadPerfil);
            seguridadPerfilDAO.updatePerfilParametro(seguridadPerfil, LENGUAJE);
            idList.add(seguridadPerfil.getSeguridadPerfilId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>seguridadPerfil</b> en la tabla de <b>Seguridad_Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param seguridadPerfilList Lista de registros que se eliminarán en la tabla de <b>Seguridad_Perfil</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> seguridadPerfilList) {
        for (Integer seguridadPerfilId : seguridadPerfilList) {
            SeguridadPerfil seguridadPerfil = findById(seguridadPerfilId);
            seguridadPerfil.setEliminadoId(ELIMINADO);
            seguridadPerfil.setModificacionUsuario(seguridadPerfilId);
            seguridadPerfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadPerfilDAO.update(seguridadPerfil);
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
                Expression.eq("nombre", nombre.trim()))).size() >= 1);
    }
      /*
    * Método que evalúa si existe un registro con los mismos datos
    * ya que existe la posibilidad de tener el registro en estatus eliminado y desear
    * agregarlo por tanto solo se cambiara el estatus
    * @param userId usuario que modifico el registro
    * @param SeguridadRol Es el rol que se desea buscar en la tabla de la BD.
    * @return Regresa <b>false</b> no existe <b>true</b> si existio y ya
    * se actualizo el estatus
        */
    @Override
    public boolean exists(Integer userId, SeguridadPerfil perfil) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("aplicacionId", perfil.getAplicacionId()));
        criteria.add(Expression.eq("nombre", perfil.getNombre()));
        criteria.add(Expression.eq("descripcion", perfil.getDescripcion()));
        criteria.add(Expression.eq("eliminadoId", ELIMINADO));
        //validar si existe el registro
        List<SeguridadPerfil> list = seguridadPerfilDAO.findByCriteria(criteria);
        //si es diferente de null existe el registro por tanto solo se le cambiara el estatus de eliminado
        if(list.size() >0){
            perfil = list.get(0);
            perfil.setEliminadoId(NOELIMINADO);
            //se actualiza el estatus
            List<Integer> ids = update(userId, Arrays.asList(perfil));
            // si se actualizo 
            if(ids.size()>0)
                return true;
            return false;
        }else{
            return false;
        }
    }
  
    @Override
    public boolean isValidoPerfilRol(int perfilId,int rolId) {
        return seguridadPerfilRolBss.isValidoSeguridadPerfilRol(perfilId,rolId);
    }

    /*
 * Método que busca el registro <b>seguridadPerfil</b> por su NOMBRE en la tabla de <b>Seguridad_Perfil</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>seguridadPerfil</b> con la información de la consulta.
     */
    @Override
    public SeguridadPerfil findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>seguridadPerfil</b> por su ID en la tabla de <b>seguridadPerfil</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>seguridadPerfil</b> con la información del registro <b>seguridadPerfil</b>.
     */
    @Override
    public SeguridadPerfil findById(Integer seguridadPerfilId) {
        SeguridadPerfil seguridadPerfil = seguridadPerfilDAO.findById(seguridadPerfilId);
        if (seguridadPerfil == null) {
            return null;
        } else {
            return resolveDescription(Arrays.asList(seguridadPerfil)).get(0);
        }
    }

    @Override
    public List<SeguridadPerfil> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Seguridad_Perfil y obtiene los registros que coincidan con el objeto <b>seguridadPerfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param seguridadPerfil objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
        String ordenTipo, SeguridadPerfil seguridadPerfil) {
        Grid grid = new Grid();
        Map<String, Object> parametros = new HashMap<>();
        parametros = getParametros(displayedPage, maxResult, order, ordenTipo, seguridadPerfil);
        int resultadosTotales = seguridadPerfilDAO.getCount(parametros);
        List<SeguridadPerfil> list = seguridadPerfilDAO.getPerfiles(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
     private Map<String, Object> getParametros(int displayedPage, int maxResult, 
            String order, String ordenTipo, SeguridadPerfil seguridadPerfil) {
        Integer indexInicio = maxResult * (displayedPage - 1);
        Integer indexFinal = indexInicio + maxResult;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("indexInicio", indexInicio + 1);
        parametros.put("indexFinal", indexFinal);
        parametros.put("lenguajeId", LENGUAJE);
        parametros.put("eliminadoId", NOELIMINADO);
        
        if (ordenTipo.equals("asc")) {
            parametros.put("ordenTipo", ASC);
        } else if (ordenTipo.equals("desc") || ordenTipo.equals("des")) {
            parametros.put("ordenTipo", DESC);
        }
        if (seguridadPerfil.getNombre() != null) {
            parametros.put("nombre", seguridadPerfil.getNombre());
        }
        if (seguridadPerfil.getDescripcion() != null) {
            parametros.put("descripcion", seguridadPerfil.getDescripcion());
        }
        if (seguridadPerfil.getAplicacionId()!= null) {
            parametros.put("aplicacionId", seguridadPerfil.getAplicacionId().toString());
        }
        if (seguridadPerfil.getModificacionUsuario()!= null) {
            parametros.put("modificacionUsuario", seguridadPerfil.getModificacionUsuario().toString());
        }
        parametros.put("order",getFormatCampo(order));
        return parametros;
    }
    
    public String getFormatCampo(String campo){
       char[] caracteres = campo.toCharArray();
       char[] nuevoCampo = new char[campo.length()+1];
       int j=0;
       for(int i = 0; i<caracteres.length; i++) {
           if(Character.isUpperCase(caracteres[i])){
                nuevoCampo[j]='_';
                j++;
                nuevoCampo[j]=Character.toUpperCase(caracteres[i]);
           }else{
                nuevoCampo[j]= Character.toUpperCase(caracteres[i]);
           }
           j++;
       }
       return String.valueOf(nuevoCampo);
    }

    @Override
    public SeguridadPerfil findFirst() {
        List<SeguridadPerfil> seguridadPerfilList = findByCriteria(createDetachedCriteria());
        if (seguridadPerfilList.isEmpty()) {
            return null;
        } else {
            return seguridadPerfilList.get(0);
        }
    }

    public List<SeguridadPerfil> resolveDescription(List<SeguridadPerfil> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> aplicacionList = getParametros("key_aplicacion");
            
            for (SeguridadPerfil seguridadPerfil : list) {

                seguridadPerfil.setUsuarioModificacion("");
                if (seguridadPerfil.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == seguridadPerfil.getModificacionUsuario().intValue()) {
                            seguridadPerfil.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                seguridadPerfil.setEstatus("");
                if (seguridadPerfil.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == seguridadPerfil.getEstatusId()) {
                            seguridadPerfil.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                seguridadPerfil.setEliminado("");
                if (seguridadPerfil.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == seguridadPerfil.getEliminadoId()) {
                            seguridadPerfil.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                
                seguridadPerfil.setAplicacion("");
                if (seguridadPerfil.getAplicacionId() != null) {
                    for (ConfiguracionParametro o : aplicacionList) {
                        if (Integer.parseInt(o.getValor()) == seguridadPerfil.getAplicacionId()) {
                            seguridadPerfil.setAplicacion(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return seguridadPerfilDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<SeguridadPerfil> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadPerfilDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>seguridadPerfil</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadPerfil> findByCriteria(DetachedCriteria criteria) {
        return seguridadPerfilDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SeguridadPerfil> findByCriteriaIgnorePrivacy(SeguridadPerfil seguridadPerfil) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(seguridadPerfil));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return seguridadPerfilDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SeguridadPerfilAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Perfil</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>seguridadPerfil</b>.
     */
    @Override
    public List<SeguridadPerfil> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Perfil</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>seguridadPerfil</b>.
     */
    @Override
    public List<SeguridadPerfil> findActive() {
        return findByCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return seguridadPerfilDAO.createDetachedCriteria();
    }

    /*
 * Método que actualiza el Estatus de los ID' contenidos en la lista
 * <b>seguridadPerfilList</b> en la tabla de <b>Seguridad_Perfil</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param seguridadPerfilList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> seguridadPerfilList) {
        for (Integer seguridadPerfilId : seguridadPerfilList) {
            SeguridadPerfil seguridadPerfil = findById(seguridadPerfilId);
            if (estatusId == ACTIVO.intValue()) {
                seguridadPerfil.setEstatusId(ACTIVO);
            } else {
                seguridadPerfil.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(seguridadPerfil));
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
    public String getReportDataTest(String order, String ordenTipo, SeguridadPerfil seguridadPerfil) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, seguridadPerfil);
        return addReportDataToSession(grid.getGrid());
    }
    
    // Selects encadenados
    @Override
    public List<SeguridadRol> getComboRolesByAplicacion(int aplicacionId) {
        return seguridadRolBss.getComboRolesByAplicacion(aplicacionId);
    }
    
    @Override
    public List<Lenguaje> getLenguajes() {
        return lenguajeBss.findAll();
    }
    
    @Override
    public List<SeguridadPerfil> getPerfilesComboByAplicacion(int aplicacionId) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("eliminadoId", NOELIMINADO);
        parametros.put("lenguajeId", LENGUAJE);
        parametros.put("aplicacionId", aplicacionId);
        List<SeguridadPerfil> seguridadPerfiles = seguridadPerfilDAO.getPerfilesCombo(parametros);
        return seguridadPerfiles;
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    
    @Override
    public String hasGrid() {
        return hasGrid(SeguridadPerfilAction.class).toString();
    }
    
    @Override
    public String hasGridPerfilRol() {
        return seguridadPerfilRolBss.hasGrid().toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadPerfilAction.class).toString();
    }
    
    @Override
    public String isIndividualPerfilRol() {
        return seguridadPerfilRolBss.isIndividual().toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadPerfilAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }
    @Override
    public List<SeguridadRol> getRoles(){
        return seguridadRolBss.findActive();
    }
    
    @Override
    public List<SeguridadPerfilRol> findSeguridadPerfilRolByIntProperty(String propertyName, Integer value) {
        return seguridadPerfilRolBss.findByIntProperty(propertyName, value);
    }
    
    @Override
    public List<Aplicacion> getAplicaciones(){
         return aplicacionBss.getAplicacionesCombo();
    }
    /*
    * Se despliega la parte de Seguridad.
    */   
    @Override
    public boolean desplegarSeguridadPerfil(){
        String spName = "generarSeguridadPerfil";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        try {
            list = seguridadPerfilPKGDAO.callStoredProcedure(spName, spList);
        } catch (Exception e) {
            System.out.println("error> " + e);
        }
           System.out.println("Llamando a store Procedure");
        return true;
    }
    @Override
    public List<Organizacion> getOrganizaciones() {
        return organizacionBss.findByCriteriaIgnorePrivacy(new Organizacion());
    }
    
    public Grid desplegar(Integer userId,int displayedPage, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion) {
        Grid grid = new Grid();
        List<Bitacora> list = null;
        String spName = "{call PUBLICA_SEGURIDAD_PKG.PRC_PROCESO_PERFILES(?,?,?,?,?)}";
        try {
            list = organizacionPKGDAO.callSP(spName, organizaciones, elementos, idEjecucion, userId);
        } catch (SQLException ex) {
            Logger.getLogger(ModuloBss.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setSeguridadPerfilDAO(SeguridadPerfilDAO seguridadPerfilDAO) {
        this.seguridadPerfilDAO = seguridadPerfilDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setSeguridadRolBss(SeguridadRolBss seguridadRolBss) {
        this.seguridadRolBss = seguridadRolBss;
    }

    public void setSeguridadPerfilRolBss(SeguridadPerfilRolBss seguridadPerfilRolBss) {
        this.seguridadPerfilRolBss = seguridadPerfilRolBss;
    }

    public void setSeguridadPerfilPKGDAO(SeguridadPerfilPKGDAO seguridadPerfilPKGDAO) {
        this.seguridadPerfilPKGDAO = seguridadPerfilPKGDAO;
    }

    public void setAplicacionBss(AplicacionBss aplicacionBss) {
        this.aplicacionBss = aplicacionBss;
    }

    public void setLenguajeBss(LenguajeBss lenguajeBss) {
        this.lenguajeBss = lenguajeBss;
    }
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }
    public void setOrganizacionPKGDAO(OrganizacionPKGDAO organizacionPKGDAO) {
        this.organizacionPKGDAO = organizacionPKGDAO;
    }
}
