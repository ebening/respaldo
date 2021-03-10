package com.admon.bss.admon;
import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadRolDAO;
import com.admon.entity.admon.*;
import com.admon.model.admon.SeguridadRolAction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class SeguridadRolBssImpl extends BaseBss implements SeguridadRolBss{
   /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadRolDAO seguridadRolDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private PaginaBss paginaBss;
    private AplicacionBss aplicacionBss;
    private LenguajeBss lenguajeBss;
    private SeguridadRolOperacionBss seguridadRolOperacionBss;
    private SeguridadFuncionRestriccionBss seguridadFuncionRestriccionBss;

    public SeguridadRolBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Seguridad_Rol</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadRol> seguridadRolList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadRol rol : seguridadRolList) {
            rol.setEstatusId(ACTIVO);
            rol.setEliminadoId(NOELIMINADO);
            rol.setModificacionUsuario(userId);
            rol.setCreacionUsuario(userId);
            rol.setCreacionFecha(new Timestamp(new Date().getTime()));
            rol.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadRolDAO.saveRol(rol, LENGUAJE);
        }
        return savedList;
    }
    @Override
    @SuppressWarnings("unchecked")
    public void updateSeguridadRol(Integer userId, List<SeguridadRol> seguridadRolList,
            List<String> funcionalidadesList) {
            update(userId, seguridadRolList);
            if(!funcionalidadesList.contains("")){
                List<List<Object>> listaOperFunc = getOpereracionesFunciones(userId,seguridadRolList, funcionalidadesList);
                List<SeguridadRolOperacion> op = (List<SeguridadRolOperacion>)(Object)listaOperFunc.get(0);
                List<SeguridadFuncionRestriccion> func = (List<SeguridadFuncionRestriccion>)(Object)listaOperFunc.get(1);
                saveFuncOperRol(seguridadRolList.get(0),userId,op,func);
            }
    }
    /*
 * Método que actualiza la información de <b>rol</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param rolList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Rol</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<SeguridadRol> seguridadRolList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadRol rol : seguridadRolList) {
            rol.setModificacionUsuario(userId);
            rol.setModificacionFecha(new Timestamp(new Date().getTime()));
            if(rol.getCreacionFecha()==null)
                rol.setCreacionFecha(new Timestamp(new Date().getTime()));
            seguridadRolDAO.update(rol);
            seguridadRolDAO.updateRolParametro(rol, LENGUAJE);
            idList.add(rol.getRolId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>rol</b> en la tabla de <b>Seguridad_Rol</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param rolList Lista de registros que se eliminarán en la tabla de <b>Seguridad_Rol</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> seguridadRolList) {
        for (Integer rolId : seguridadRolList) {
            SeguridadRol rol = findById(rolId);
            rol.setEliminadoId(ELIMINADO);
            rol.setModificacionUsuario(rolId);
            if (rol.getCreacionFecha()==null) rol.setCreacionFecha(new Timestamp(new Date().getTime()));
            rol.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadRolDAO.update(rol);
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
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("table", "SEGURIDAD_ROL");
        parametros.put("tablePar", "SEGURIDAD_ROL_PARAMETROS");
        parametros.put("lenguajeId", LENGUAJE);
        parametros.put("eliminadoId", NOELIMINADO);
        parametros.put("nombre", nombre);
        return seguridadRolDAO.isValidoNombre(parametros);
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
    public boolean exists(Integer userId, SeguridadRol rol) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("aplicacionId", rol.getAplicacionId()));
        criteria.add(Expression.eq("nombre", rol.getNombre()));
        criteria.add(Expression.eq("descripcion", rol.getDescripcion()));
        criteria.add(Expression.eq("eliminadoId", ELIMINADO));
        //validar si existe el registro
        List<SeguridadRol> list = seguridadRolDAO.findByCriteria(criteria);
        //si es diferente de null existe el registro por tanto solo se le cambiara el estatus de eliminado
        if(list.size() >0){
            rol = list.get(0);
            rol.setEliminadoId(NOELIMINADO);
            //se actualiza el estatus
            List<Integer> ids = update(userId, Arrays.asList(rol));
            // si se actualizo 
            if(ids.size()>0)
                return true;
            return false;
        }else{
            return false;
        }
    }

    /*
 * Método que busca el registro <b>rol</b> por su NOMBRE en la tabla de <b>Seguridad_Rol</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>SeguridadRol</b> con la información de la consulta.
     */
    @Override
    public SeguridadRol findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
 * Busca el registro <b>rol</b> por su ID en la tabla de <b>SeguridadRol</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>SeguridadRol</b> con la información del registro <b>rol</b>.
     */
    @Override
    public SeguridadRol findById(Integer rolId) {
        SeguridadRol rol = seguridadRolDAO.getRol(rolId);
        if (rol == null) {
            return null;
        } else {
            return rol;
        }
    }

    @Override
    public List<SeguridadRol> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
 * Método que hace una consulta a la tabla Seguridad_Rol y obtiene los registros que coincidan con el objeto <b>rol</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param SeguridadRol objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
        String ordenTipo, SeguridadRol rol) {
        Grid grid = new Grid();
        DetachedCriteria criteria = createDetachedCriteria();
        // Auxiliar para busqueda por rango de fechas campo: CreacionFecha
        if (rol.getCreacionFechaInicial() != null) {
            criteria.add(Expression.ge("creacionFecha", getStartOfDay(rol.getCreacionFechaInicial())));
        }
        if (rol.getCreacionFechaFinal() != null) {
            criteria.add(Expression.le("creacionFecha", getEndOfDay(rol.getCreacionFechaFinal())));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (rol.getModificacionFechaInicial() != null) {
            criteria.add(Expression.ge("modificacionFecha", getStartOfDay(rol.getModificacionFechaInicial())));
        }
        if (rol.getModificacionFechaFinal() != null) {
            criteria.add(Expression.le("modificacionFecha", getEndOfDay(rol.getModificacionFechaFinal())));
        }
        criteria.add(Example.create(rol));
        if (ordenTipo.equals("asc")) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        int resultadosTotales = findSizeByCriteria(copy(criteria));
        List<SeguridadRol> rolList = findByCriteriaLimit(criteria,
                maxResult * (displayedPage - 1), maxResult);
        grid.setGrid(resolveDescription(rolList));
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order,
        String ordenTipo, SeguridadRol rol,int lenguajeId){
        Grid grid = new Grid();
        Map<String, Object> parametros = new HashMap<>();
        parametros = getParametros(displayedPage, maxResult, order, ordenTipo, rol);
        int resultadosTotales = seguridadRolDAO.getCount(parametros);
        List<SeguridadRol> list = seguridadRolDAO.getRoles(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    private Map<String, Object> getParametros(int displayedPage, int maxResult, 
            String order, String ordenTipo, SeguridadRol rol) {
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
        if (rol.getNombre() != null) {
            parametros.put("nombre", rol.getNombre());
        }
        if (rol.getDescripcion() != null) {
            parametros.put("descripcion", rol.getDescripcion());
        }
        if (rol.getAplicacionId()!= null) {
            parametros.put("aplicacionId", rol.getAplicacionId().toString());
        }
        if (rol.getModificacionUsuario()!= null) {
            parametros.put("modificacionUsuario", rol.getModificacionUsuario().toString());
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
    public SeguridadRol findFirst() {
        List<SeguridadRol> rolList = findByCriteria(createDetachedCriteria());
        if (rolList.isEmpty()) {
            return null;
        } else {
            return rolList.get(0);
        }
    }

    public List<SeguridadRol> resolveDescription(List<SeguridadRol> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            List<ConfiguracionParametro> aplicacionList = getParametros("key_aplicacion");
            for (SeguridadRol rol : list) {

                rol.setUsuarioModificacion("");
                if (rol.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().intValue() == rol.getModificacionUsuario().intValue()) {
                            rol.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                rol.setEstatus("");
                if (rol.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == rol.getEstatusId()) {
                            rol.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                rol.setEliminado("");
                if (rol.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == rol.getEliminadoId()) {
                            rol.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
                
                rol.setAplicacion("");
                if (rol.getAplicacionId() != null) {
                    for (ConfiguracionParametro o : aplicacionList) {
                        if (Integer.parseInt(o.getValor()) == rol.getAplicacionId()) {
                            rol.setAplicacion(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return seguridadRolDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<SeguridadRol> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return seguridadRolDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>SeguridadRol</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<SeguridadRol> findByCriteria(DetachedCriteria criteria) {
        return seguridadRolDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<SeguridadRol> findByCriteriaIgnorePrivacy(SeguridadRol rol) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(rol));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return seguridadRolDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, SeguridadRolAction.class);
        return criteria;
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadRol</b>.
     */
    @Override
    public List<SeguridadRol> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
 * Método que obtiene todos los registros de la tabla de <b>Seguridad_Rol</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>SeguridadRol</b>.
     */
    @Override
    public List<SeguridadRol> findActive() {
        return findByCriteria(generalizarCriteria(createDetachedCriteria().add(
                Expression.eq("estatusId", ACTIVO)).addOrder(Order.asc("nombre"))));
    }

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return seguridadRolDAO.createDetachedCriteria();
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
    public void setEstatus(Integer userId, Integer estatusId, List<Integer> rolList) {
        for (Integer rolId : rolList) {
            SeguridadRol rol = findById(rolId);
            if (estatusId == ACTIVO.intValue()) {
                rol.setEstatusId(ACTIVO);
            } else {
                rol.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(rol));
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
    public String getReportDataTest(String order, String ordenTipo, SeguridadRol rol) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, rol);
        return addReportDataToSession(grid.getGrid());
    }
    
    // Selects encadenados metodos
    @Override
    public List<SeguridadRol> getComboRolesByAplicacion(int aplicacionId) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("eliminadoId", NOELIMINADO);
        parametros.put("lenguajeId", LENGUAJE);
        parametros.put("aplicacionId", aplicacionId);
        SeguridadRol s = new SeguridadRol();
    	s.setNombre("--Seleccione--");
    	s.setRolId(-1);
        List<SeguridadRol> roles = seguridadRolDAO.getComboRolesByAplicacion(parametros);
    	roles.add(0,s);
        return roles;
    }
    
    
    @Override
    public List<Lenguaje> getLenguajes() {
        return lenguajeBss.findAll();
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    // <editor-fold defaultstate="collapsed" desc="Getters Detalle(s)">
    
    @Override
    public List<Aplicacion> getAplicaciones(){
         return aplicacionBss.getAplicacionesCombo();
    }
    
    @Override
    public List<SeguridadRolOperFunc> getOpFuncByAplicacion(int aplicacionId){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("lenguajeId", LENGUAJE);
        parametros.put("eliminadoId", NOELIMINADO);
        parametros.put("estatusId", ACTIVO);
        parametros.put("aplicacionId", aplicacionId);
        return seguridadRolDAO.getOpFuncByAplicacion(parametros);
    }
    
    @Override
    public String hasGrid() {
        return hasGrid(SeguridadRolAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(SeguridadRolAction.class).toString();
    }
    
    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(SeguridadRolAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }
     /*
    * Funciones extras para eliminar y guardar regristro de Funciones y Operaciones
    * en base a los datos seleccionados en el árbol
     */
    public  void saveFuncOperRol(SeguridadRol rol,Integer userId, List<SeguridadRolOperacion> operaciones,
            List<SeguridadFuncionRestriccion> funciones){
        List<SeguridadRolOperacion> detalleNuevoOpList = new ArrayList<SeguridadRolOperacion>();
        List<SeguridadFuncionRestriccion> detalleNuevoFuncList = new ArrayList<SeguridadFuncionRestriccion>();

        List<SeguridadRolOperacion> detalleActualOpList =seguridadRolOperacionBss.getRolOperaciones(rol);
        List<SeguridadFuncionRestriccion> detalleActualFuncList = seguridadFuncionRestriccionBss.getRolFunciones(rol);
        
        boolean existe=false;
        for (SeguridadRolOperacion op : operaciones) {
            existe=false;
            for (SeguridadRolOperacion actualOp : detalleActualOpList) {
                if(op.getOperacionId().intValue() == actualOp.getOperacionId().intValue() 
                       && op.getAplicacionId().intValue() == actualOp.getAplicacionId().intValue()){
                    existe=true;
                    break;
                }
            }
            if(!existe){
                detalleNuevoOpList.add(op);
            }
        }
        for (SeguridadFuncionRestriccion func : funciones) {
            existe=false;
            for (SeguridadFuncionRestriccion actualFunc : detalleActualFuncList) {
                if(func.getFuncionalidadId().intValue() == actualFunc.getFuncionalidadId().intValue()
                         && func.getAplicacionId().intValue() == actualFunc.getAplicacionId().intValue()){
                    existe=true;
                    break;
                }
            }
            if(!existe){
                detalleNuevoFuncList.add(func);
            }
        }
        seguridadRolOperacionBss.save(userId, detalleNuevoOpList);
        seguridadFuncionRestriccionBss.save(detalleNuevoFuncList);
         // Delete
        //Se revisa que elemento ya no existen
        for (SeguridadRolOperacion detalleActualOp : detalleActualOpList) {
            boolean isDeletable = true;
            for (SeguridadRolOperacion op : operaciones) {
                if (detalleActualOp.getOperacionId().intValue()
                        == op.getOperacionId().intValue()
                         && detalleActualOp.getAplicacionId().intValue() == op.getAplicacionId().intValue()) {
                    isDeletable = false;
                    break;
                }
            }
            if (isDeletable) {
                seguridadRolOperacionBss.delete(Arrays.asList(detalleActualOp));
            }
        }

        for (SeguridadFuncionRestriccion detalleActualFunc : detalleActualFuncList) {
            boolean isDeletable = true;
            for (SeguridadFuncionRestriccion func : funciones) {
                if (func.getFuncionalidadId().intValue() 
                        == detalleActualFunc.getFuncionalidadId().intValue()
                        && func.getAplicacionId().intValue() == detalleActualFunc.getAplicacionId().intValue()) {
                    isDeletable = false;
                    break;
                }
            }
            if (isDeletable) {
                seguridadFuncionRestriccionBss.delete(Arrays.asList(detalleActualFunc));
            }
        }
    }
    public  List<List<Object>> getOpereracionesFunciones(Integer userId, List<SeguridadRol> seguridadRolList,
            List<String> funcionalidadesList){
        List<List<Object>> listOperFunc = new ArrayList<List<Object>>();
        List<SeguridadRolOperacion> operaciones = new ArrayList<SeguridadRolOperacion>();
        List<SeguridadFuncionRestriccion> funcionalidades = new ArrayList<SeguridadFuncionRestriccion>();
        if(funcionalidadesList.size()>0){
            for(int i=0; i<funcionalidadesList.size();i++){
                String funcoper = funcionalidadesList.get(i);
                 String[] wordList = funcoper.split("-");
                 String id = wordList[wordList.length-1];
                 if(id.equals("O")){
                     SeguridadRolOperacion o = new SeguridadRolOperacion();
                     o.setOperacionId(Integer.parseInt(wordList[0]));
                     o.setRolId(seguridadRolList.get(0).getRolId());
                     o.setAplicacionId(seguridadRolList.get(0).getAplicacionId());
                     operaciones.add(o);
                 }else{
                     if(id.equals("F")){
                         SeguridadFuncionRestriccion f = new SeguridadFuncionRestriccion();
                         f.setFuncionalidadId(Integer.parseInt(wordList[0]));
                         f.setRolId(seguridadRolList.get(0).getRolId());
                         f.setAplicacionId(seguridadRolList.get(0).getAplicacionId());
                         funcionalidades.add(f);
                     }
                 }
            }
            listOperFunc.add((List<Object>)(Object)operaciones);
            listOperFunc.add((List<Object>)(Object)funcionalidades);
        }
        return listOperFunc;
    }
    @Override
    public List<String> getOperFuncArbol(SeguridadRol seguridadRol){
        List<String> operFuncArbolList = new ArrayList<String>();
        operFuncArbolList.addAll(seguridadRolOperacionBss.getRolOperacionesArbol(seguridadRol));
        operFuncArbolList.addAll(seguridadFuncionRestriccionBss.getRolFuncionesArbol(seguridadRol));
        return operFuncArbolList;
    }
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadRolDAO(SeguridadRolDAO rolDAO) {
        this.seguridadRolDAO = rolDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setPaginaBss(PaginaBss paginaBss) {
        this.paginaBss = paginaBss;
    }
    
    public void setAplicacionBss(AplicacionBss aplicacionBss) {
        this.aplicacionBss = aplicacionBss;
    }

    public void setSeguridadRolOperacionBss(SeguridadRolOperacionBss seguridadRolOperacionBss) {
        this.seguridadRolOperacionBss = seguridadRolOperacionBss;
    }

    public void setSeguridadFuncionRestriccionBss(SeguridadFuncionRestriccionBss seguridadFuncionRestriccionBss) {
        this.seguridadFuncionRestriccionBss = seguridadFuncionRestriccionBss;
    }
    
    public void setLenguajeBss(LenguajeBss lenguajeBss) {
        this.lenguajeBss = lenguajeBss;
    }

}
