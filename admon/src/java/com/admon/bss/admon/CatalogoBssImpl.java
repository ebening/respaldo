package com.admon.bss.admon;

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

import com.admon.bss.BaseBss;
import com.admon.dao.admon.CatalogoDAO;
import com.admon.entity.admon.Bitacora;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoPK;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Lenguaje;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.admon.Pagina;
import com.admon.entity.admon.Usuario;
import com.admon.model.admon.CatalogoAction;
import com.admon.pkg.dao.CatalogoPKGDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogoBssImpl extends BaseBss implements CatalogoBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private CatalogoDAO catalogoDAO;
    private OrganizacionBss organizacionBss;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;
    private CatalogoParametroBss catalogoParametroBss;
    private CatalogoParametroLenguajeBss catalogoParametroLenguajeBss;
    private LenguajeBss lenguajeBss;
    private CatalogoPKGDAO catalogoPKGDAO;

    public CatalogoBssImpl() {
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Catalogo</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Catalogo</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Catalogo> save(Integer userId, List<Catalogo> catalogoList) {
        List<Catalogo> savedList = new ArrayList<>();
        for (Catalogo catalogo : catalogoList) {
            CatalogoPK catalogoPK = new CatalogoPK();
            catalogoPK.setCatalogoId(catalogo.getCatalogoPK().getCatalogoId()==null?catalogoDAO.getCatalogoNextKey():null);
            catalogoPK.setOrganizacionId(catalogo.getCatalogoPK().getOrganizacionId());
            catalogo.setCatalogoPK(catalogoPK);
            catalogo.setEstatusId(ACTIVO);
            catalogo.setEliminadoId(NOELIMINADO);
            catalogo.setModificacionUsuario(userId);
            catalogo.setCreacionUsuario(userId);
            catalogo.setCreacionFecha(new Timestamp(new Date().getTime()));
            catalogo.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogo.setCatalogoPK(catalogoDAO.save(catalogo));
            savedList.add(catalogo);
        }
        return savedList;
    }

    /*
     * Método que actualiza la información de <b>catalogo</b> en la tabla de <b>Catalogo</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoList Lista de registros que se actualizarán en la tabla de <b>Catalogo</b> en la BD.
     */
    @Override
    public List<Catalogo> update(Integer userId, List<Catalogo> catalogoList) {
        List<Catalogo> savedList = new ArrayList<>();
        for (Catalogo catalogo : catalogoList) {
            CatalogoPK catalogoPK = new CatalogoPK();
            catalogoPK.setCatalogoId(catalogo.getCatalogoPK().getCatalogoId());
            catalogoPK.setOrganizacionId(catalogo.getCatalogoPK().getOrganizacionId());
            catalogo.setCatalogoPK(catalogoPK);
            catalogo.setModificacionUsuario(userId);
            catalogo.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoDAO.update(catalogo);
            savedList.add(catalogo);
        }
        return savedList;
    }

    /*
     * Método que elimina el registro <b>catalogo</b> en la tabla de <b>Catalogo</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoList Lista de registros que se eliminarán en la tabla de <b>Catalogo</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<CatalogoPK> catalogoIdList) {
        for (CatalogoPK catalogoTemporal : catalogoIdList) {
            Catalogo catalogo = findById(catalogoTemporal.getCatalogoId(), catalogoTemporal.getOrganizacionId());
            catalogo.setEliminadoId(ELIMINADO);
            catalogo.setModificacionUsuario(userId);
            catalogo.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoDAO.update(catalogo);
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
     * Método que busca el registro <b>catalogo</b> por su NOMBRE en la tabla de <b>Catalogo</b>.
     * @param nombre es el NOMBRE del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>Catalogo</b> con la información de la consulta.
     */
    @Override
    public Catalogo findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("nombre", nombre.trim()))));
    }

    /*
     * Busca el registro <b>catalogoParametro</b> por su ID en la tabla de <b>CatalogoParametro</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>CatalogoParametro</b> con la información del registro <b>catalogoParametro</b>.
     */
    @Override
    public Catalogo findById(CatalogoPK catalogoId) {
        return findById(catalogoId.getCatalogoId(), catalogoId.getOrganizacionId());
    }

    @Override
    public Catalogo findById(Integer catalogoId, Integer organizacionId) {
    	Catalogo c= new Catalogo();
    	c.setCatalogoPK(new CatalogoPK());
    	c.getCatalogoPK().setCatalogoId(catalogoId);
    	c.getCatalogoPK().setOrganizacionId(organizacionId);
        Map<String, Object> parametros  = getParametros(1, 1, null, null, c);
        List<Catalogo> listCatalogo = catalogoDAO.getCatalogos(parametros);
        if (listCatalogo == null || listCatalogo.size() < 1) {
            return null;
        } else {
            return listCatalogo.get(0);
        }
    }

    @Override
    public List<Catalogo> findByIntProperty(String propertyName, Integer value) {
        return resolveDescription(findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    /*
     * Método que hace una consulta a la tabla Catalogo y obtiene los registros que coincidan con el objeto <b>catalogo</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param Catalogo objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order, String ordenTipo, Catalogo catalogo) {
		Grid grid = new Grid();
		Map<String, Object> parametros = new HashMap<>();
		parametros = getParametros(displayedPage, maxResult, order, ordenTipo, catalogo);
		int resultadosTotales = catalogoDAO.getCount(parametros);
		List<Catalogo> list = catalogoDAO.getCatalogos(parametros);
		grid.setGrid(list);
		grid.setTotal(resultadosTotales);
		grid.setPaginas(lastPage(resultadosTotales, maxResult));
		return grid;
	}
    
    private Map<String, Object> getParametros(Integer displayedPage, Integer maxResult, String order, String ordenTipo, Catalogo object) {
    	Map<String, Object> parametros = new HashMap<>();
    	if(displayedPage!=null && maxResult!=null){
			Integer indexInicio = maxResult * (displayedPage - 1);
			Integer indexFinal = indexInicio + maxResult;
			parametros.put("indexInicio", indexInicio + 1);
			parametros.put("indexFinal", indexFinal);
    	}
    	parametros.put("order", order);
    	if(ordenTipo!=null){
			if (ordenTipo.equals("asc")) {
				parametros.put("ordenTipo", ASC);
			} else if (ordenTipo.equals("des") || ordenTipo.equals("desc")) {
				parametros.put("ordenTipo", DESC);
			}
    	}
		if (object.getNombre() != null) {
			parametros.put("nombre", object.getNombre().toString().trim());
		}
		if (object.getClave() != null) {
			parametros.put("clave", object.getClave().toString().trim());
		}
		if (object.getEstatus() != null) {
			parametros.put("estatus", object.getEstatus().toString().trim());
		}
		if(object.getCatalogoPK()!=null && object.getCatalogoPK().getCatalogoId()!=null){
			parametros.put("catalogoId", object.getCatalogoPK().getCatalogoId());
		}
		if(object.getCatalogoPK()!=null && object.getCatalogoPK().getOrganizacionId()!=null){
			parametros.put("organizacionId", object.getCatalogoPK().getOrganizacionId());
		}
		return parametros;
	}

     @Override
    public Grid findByCriteriaCatRestriccion(int displayedPage, int maxResult, String order, String ordenTipo, Catalogo catalogo) {
        Grid grid = new Grid();
        Integer indexInicio = maxResult * (displayedPage - 1);
        Integer indexFinal = indexInicio+maxResult;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("indexInicio", indexInicio+1);
        parametros.put("indexFinal", indexFinal);
        parametros.put("eliminadoId", NOELIMINADO);
        if (ordenTipo.equals("asc")) {
            parametros.put("ordenTipo", ASC);
        } else if (ordenTipo.equals("des") || ordenTipo.equals("desc")) {
            parametros.put("ordenTipo", DESC);
        }
        if (catalogo.getNombre() != null) {
            parametros.put("nombre", catalogo.getNombre());
        }
        if (order.equals("modificacionFecha")) {
            parametros.put("order", "MODIFICACION_FECHA");
        } else
            if(order.equals("nombre")){
                    parametros.put("order", "NOMBRE");
            } else {
                parametros.put("order", order);
            }
        int resultadosTotales = catalogoDAO.getCount(parametros);
        List<Catalogo> list = catalogoDAO.getCatalogos(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    
    @Override
    public Catalogo findFirst() {
        List<Catalogo> catalogoList = findByCriteria(createDetachedCriteria());
        if (catalogoList.isEmpty()) {
            return null;
        } else {
            return catalogoList.get(0);
        }
    }

    public List<Catalogo> resolveDescription(List<Catalogo> list) {
        if (!list.isEmpty()) {
            List<Usuario> usuarioList = usuarioBss.findAll();
            List<Organizacion> organizacionList = organizacionBss.findAll();
            List<Catalogo> catalogoList = findAll();
            List<CatalogoParametro> catalogoParametroList = catalogoParametroBss.findAll();
            List<ConfiguracionParametro> estatusList = getParametros("key_estatus");
            List<ConfiguracionParametro> eliminadoList = getParametros("key_eliminado");
            for (Catalogo catalogo : list) {
//                catalogo.setCatalogoId(catalogo.getCatalogoPK().getCatalogoId());

                catalogo.setOrganizacionNombre("");
                if (catalogo.getCatalogoPK().getOrganizacionId() != null) {
                    for (Organizacion o : organizacionList) {
                        if (o.getOrganizacionId().equals(catalogo.getCatalogoPK().getOrganizacionId())) {
//                            catalogo.setOrganizacionId(o.getOrganizacionId());
                            catalogo.setOrganizacionNombre(o.getNombre());
                            break;
                        }
                    }
                }

                catalogo.setCatalogoPadre("");
                if (catalogo.getCatalogoPadreId() != null) {
                    for (Catalogo o : catalogoList) {
                        if (o.getCatalogoPK().getCatalogoId().equals(catalogo.getCatalogoPadreId())) {
                            catalogo.setCatalogoPadre(o.getNombre());
                            break;
                        }
                    }
                }
                catalogo.setParametroDependiente("");
                if (catalogo.getParametroDependienteId() != null) {
                    for (CatalogoParametro o : catalogoParametroList) {
                        if (o.getCatalogoParametroPK().getCatalogoParametroId().equals(catalogo.getParametroDependienteId())) {
                            catalogo.setParametroDependiente(o.getValor());
                            break;
                        }
                    }
                }
                catalogo.setUsuarioModificacion("");
                if (catalogo.getModificacionUsuario() != null) {
                    for (Usuario o : usuarioList) {
                        if (o.getUsuarioId().equals(catalogo.getModificacionUsuario())) {
                            catalogo.setUsuarioModificacion(o.getUsuario());
                            break;
                        }
                    }
                }

                catalogo.setEstatus("");
                if (catalogo.getEstatusId() != null) {
                    for (ConfiguracionParametro o : estatusList) {
                        if (Integer.parseInt(o.getValor()) == catalogo.getEstatusId()) {
                            catalogo.setEstatus(o.getNombre());
                            break;
                        }
                    }
                }

                catalogo.setEliminado("");
                if (catalogo.getEliminadoId() != null) {
                    for (ConfiguracionParametro o : eliminadoList) {
                        if (Integer.parseInt(o.getValor()) == catalogo.getEliminadoId()) {
                            catalogo.setEliminado(o.getNombre());
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return catalogoDAO.findSizeByCriteria(generalizarCriteria(criteria));
    }

    public List<Catalogo> findByCriteriaLimit(DetachedCriteria criteria, Integer from, Integer to) {
        return catalogoDAO.findByCriteriaLimit(generalizarCriteria(criteria), from, to);
    }

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>Catalogo</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */
    @Override
    public List<Catalogo> findByCriteria(DetachedCriteria criteria) {
        return catalogoDAO.findByCriteria(generalizarCriteria(criteria));
    }

    @Override
    public List<Catalogo> findByCriteriaIgnorePrivacy(Catalogo catalogo) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(catalogo));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        return catalogoDAO.findByCriteria(criteria);
    }

    public DetachedCriteria generalizarCriteria(DetachedCriteria criteria) {
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        resolvePrivacy(criteria, CatalogoAction.class);
        return criteria;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>Catalogo</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>Catalogo</b>.
     */
    @Override
    public List<Catalogo> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("nombre")));
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>Catalogo</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>Catalogo</b>.
     */
    @Override
    public List<Catalogo> findActive() {
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
        return catalogoDAO.createDetachedCriteria();
    }

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>catalogoList</b> en la tabla de <b>Catalogo</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param catalogoList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<CatalogoPK> catalogoList) {
        for (CatalogoPK catalogoTemporal : catalogoList) {
            Catalogo catalogo = findById(catalogoTemporal.getCatalogoId(), catalogoTemporal.getOrganizacionId());
            if (estatusId == 1) {
                catalogo.setEstatusId(ACTIVO);
            } else {
                catalogo.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(catalogo));
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
    public String getReportDataTest(String order, String ordenTipo, Catalogo catalogo) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, catalogo);
        return addReportDataToSession(grid.getGrid());
    }

    @Override
    public List<Lenguaje> getLenguaje() {
        return addDummy(lenguajeBss.findAll(), new Lenguaje(), Lenguaje.class);
    }

    @Override
    public List<Organizacion> getOrganizacion() {
        return addDummy(organizacionBss.findActive(), new Organizacion(), Organizacion.class);
    }

    @Override
    public List<Catalogo> getCatalogo() {
        return addDummy(findActive(), new Catalogo(), Catalogo.class);
    }

    @Override
    public List<Catalogo> filtrarCatalogo(int organizacionId) {
        return findByIntProperty("organizacionId", organizacionId);
    }

    @Override
    public List<CatalogoParametro> filtrarCatalogoParametro(int catalogoPadreId) {
        return catalogoParametroBss.getCatalogoParametrosForCombo(catalogoPadreId);
    }

    @Override
    public String hasGrid() {
        return hasGrid(CatalogoAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(CatalogoAction.class).toString();
    }

    @Override
    public String hasGridCatalogoParametro() {
        return catalogoParametroBss.hasGrid();
    }

    @Override
    public String isIndividualCatalogoParametro() {
        return catalogoParametroBss.isIndividual();
    }

    @Override
    public String hasGridCatalogoParametroLenguaje() {
        return catalogoParametroLenguajeBss.hasGrid();
    }

    @Override
    public String isIndividualCatalogoParametroLenguaje() {
        return catalogoParametroLenguajeBss.isIndividual();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(CatalogoAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }
    
    @Override
    public List<CatalogoParametro> getCatalogosByClave(String clave){
        Catalogo catalogo = new Catalogo();
        catalogo.setClave(clave);
        Catalogo c = findByCriteriaIgnorePrivacy(catalogo).get(0);
        CatalogoParametro catalogoParametro = new CatalogoParametro();
        catalogoParametro.setCatalogoId(c.getCatalogoPK().getCatalogoId());
        return catalogoParametroBss.findByIntProperty("catalogoId",c.getCatalogoPK().getCatalogoId(),"valor");
    }
    
    public List<Catalogo> getCatalogosForCombo(){
    	return catalogoDAO.getCatalogosForCombo();
    }
    @Override
    public Grid desplegar(Integer userId,int displayedPage, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion) {
        Grid grid = new Grid();
        List<Bitacora> list = null;
        String spName = "{call PUBLICA_CATALOGOS_PKG.PRC_PROCESO_CATALOGOS(?,?,?,?,?)}";
        try {
            list = catalogoPKGDAO.callSP(spName, organizaciones, elementos, idEjecucion, userId);
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoBssImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setCatalogoDAO(CatalogoDAO catalogoDAO) {
        this.catalogoDAO = catalogoDAO;
    }

    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
        this.catalogoParametroBss = catalogoParametroBss;
    }

    public void setCatalogoParametroLenguajeBss(CatalogoParametroLenguajeBss catalogoParametroLenguajeBss) {
        this.catalogoParametroLenguajeBss = catalogoParametroLenguajeBss;
    }

    public void setLenguajeBss(LenguajeBss lenguajeBss) {
        this.lenguajeBss = lenguajeBss;
    }
    
    public void setCatalogoPKGDAO(CatalogoPKGDAO catalogoPKGDAO) {
        this.catalogoPKGDAO = catalogoPKGDAO;
    }
}