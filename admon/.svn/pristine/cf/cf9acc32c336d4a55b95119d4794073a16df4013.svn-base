package com.admon.bss.admon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.CatalogoParametroDAO;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.CatalogoParametroPK;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Pagina;
import com.admon.model.admon.CatalogoParametroAction;
import com.admon.model.util.ImagenUtils;

public class CatalogoParametroBssImpl extends BaseBss implements CatalogoParametroBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase. */
    private CatalogoParametroDAO catalogoParametroDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private CatalogoBss catalogoBss;

    public CatalogoParametroBssImpl() {
    }

    /*
     * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>CatalogoParametro</b> en la BD.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<CatalogoParametro> save(Integer userId, List<CatalogoParametro> catalogoParametroList) {
        List<CatalogoParametro> savedList = new ArrayList<>();
        for (CatalogoParametro catalogoParametro : catalogoParametroList) {
            catalogoParametro.setEstatusId(ACTIVO);
            catalogoParametro.setEliminadoId(NOELIMINADO);
            catalogoParametro.setModificacionUsuario(userId);
            catalogoParametro.setCreacionUsuario(userId);
            catalogoParametro.setCreacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            CatalogoParametroPK catalogoParametroPK = new CatalogoParametroPK();
            if(catalogoParametro.getCatalogoParametroPK().getCatalogoParametroId()==null
            		|| catalogoParametro.getCatalogoParametroPK().getCatalogoParametroId()==0){
            	catalogoParametro.getCatalogoParametroPK().setCatalogoParametroId(catalogoParametroDAO.getCatalogoParametroNextKey());
            }
            ImagenUtils.procesaImagenCatalogoParametro(codigos, configuracionParametroBss, catalogoParametro);
            catalogoParametroPK = catalogoParametroDAO.save(catalogoParametro);
            catalogoParametro.setCatalogoParametroPK(catalogoParametroPK);
            savedList.add(catalogoParametro);
        }
        return savedList;
    }

    @Override
    public List<CatalogoParametro> saveCatalogoParametro(int userId, int catalogoId, int organizacionId, List<CatalogoParametro> detalleNuevoList) {
        List<CatalogoParametro> detalleActualList = findListCatalogoParametroBy(catalogoId, organizacionId);
        List<CatalogoParametro> savedOrUpdatedList = new ArrayList<>();
        // Delete
        for (CatalogoParametro detalle : detalleActualList) {
            boolean isDeletable = true;
            for (CatalogoParametro detalleNuevo : detalleNuevoList) {
                if (detalle.getCatalogoParametroPK().getCatalogoParametroId().equals(detalleNuevo.getCatalogoParametroPK().getCatalogoParametroId())
                        && detalle.getCatalogoParametroPK().getOrganizacionId().equals(detalleNuevo.getCatalogoParametroPK().getOrganizacionId())) {
                    isDeletable = false;
                }
            }
            if (isDeletable) {
                delete(userId, Arrays.asList(detalle.getCatalogoParametroPK()));
            }
        }
        // Save y update
        for (CatalogoParametro detalle : detalleNuevoList) {
            if (detalle.getCatalogoParametroPK()==null || detalle.getCatalogoParametroPK().getCatalogoParametroId()==null || detalle.getCatalogoParametroPK().getCatalogoParametroId() == 0) {
                savedOrUpdatedList.addAll(save(userId, Arrays.asList(detalle)));
            } else {
                savedOrUpdatedList.addAll(update(userId, Arrays.asList(detalle)));
            }
        }
        return savedOrUpdatedList;
    }

    /*
     * Método que actualiza la información de <b>catalogoParametro</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoParametroList Lista de registros que se actualizarán en la tabla de <b>CatalogoParametro</b> en la BD.
     */
    @Override
    public List<CatalogoParametro> update(Integer userId, List<CatalogoParametro> catalogoParametroList) {
        List<CatalogoParametro> idList = new ArrayList<>();
        for (CatalogoParametro catalogoParametro : catalogoParametroList) {
            catalogoParametro.setModificacionUsuario(userId);
            catalogoParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            ImagenUtils.procesaImagenCatalogoParametro(codigos, configuracionParametroBss, catalogoParametro);
            catalogoParametroDAO.update(catalogoParametro);
            idList.add(catalogoParametro);
        }
        return idList;
    }

    /*
     * Método que elimina el registro <b>catalogoParametro</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId Es el id del usuario que realizó la operación. * @param catalogoParametroList Lista de registros que se eliminarán en la tabla de <b>CatalogoParametro</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<CatalogoParametroPK> catalogoParametroIdList) {
        for (CatalogoParametroPK catalogoParametroId : catalogoParametroIdList) {
            CatalogoParametro catalogoParametro = findById(catalogoParametroId);
            catalogoParametro.setEliminadoId(ELIMINADO);
            catalogoParametro.setModificacionUsuario(userId);
            catalogoParametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            catalogoParametroDAO.update(catalogoParametro);
        }
    }

    /*
     * Busca el registro <b>catalogoParametro</b> por su ID en la tabla de <b>CatalogoParametro</b>.
     * @param id es el ID del registro a obtener.
     * @return Regresa un objeto <b>CatalogoParametro</b> con la información del registro <b>catalogoParametro</b>.
     */
    @Override
    public CatalogoParametro findById(CatalogoParametroPK catalogoParametroId) {
    	CatalogoParametro cp = new CatalogoParametro();
    	cp.setCatalogoParametroPK(catalogoParametroId);
    	Map<String, Object> parametros = getParametros(1, 1, null, null, cp);
        List<CatalogoParametro> catalogoParametros = catalogoParametroDAO.getCatalogoParametros(parametros);
        if (catalogoParametros.size()>0) {
            return catalogoParametros.get(0);
        }
        return null;
    }

    @Override
    public CatalogoParametro findById(Integer catalogoParametroId, Integer organizacionId) {
    	CatalogoParametroPK pk = new CatalogoParametroPK();
    	pk.setCatalogoParametroId(catalogoParametroId);
    	pk.setOrganizacionId(organizacionId);
        return findById(pk);
    }

    @Override
    public List<CatalogoParametro> findListCatalogoParametroBy(Integer catalogoId, Integer organizacionId) {
    	CatalogoParametro cp = new CatalogoParametro();
    	cp.setCatalogoParametroPK(new CatalogoParametroPK());
    	cp.getCatalogoParametroPK().setOrganizacionId(organizacionId);
    	cp.setCatalogoId(catalogoId);
    	Map<String, Object> parametros = getParametros(null, null, null, null, cp);
    	generalizarCriteria(parametros);
        return catalogoParametroDAO.getCatalogoParametros(parametros);
    }

    @Override
    public List<CatalogoParametro> findByIntProperty(String propertyName, Integer value) {
    	Map<String, Object> parametros = new HashMap<>();
    	parametros.put(propertyName, value);
        return catalogoParametroDAO.getCatalogoParametros(parametros);
    }
    
    @Override
    public List<CatalogoParametro> findByIntProperty(String propertyName, Integer value, String validate) {
    	Map<String, Object> parametros = new HashMap<>();
    	parametros.put(propertyName, value);
    	parametros.put("eliminadoId", NOELIMINADO);
    	parametros.put("validate", validate);
        return catalogoParametroDAO.getCatalogoParametros(parametros);
    }
    
    private Map<String, Object> getParametros(Integer displayedPage, Integer maxResult, String order, String ordenTipo, CatalogoParametro object) {
    	Map<String, Object> parametros = new HashMap<>();
    	if(displayedPage!=null && maxResult!=null){
			Integer indexInicio = maxResult * (displayedPage - 1);
			Integer indexFinal = indexInicio + maxResult;
			parametros.put("indexInicio", indexInicio + 1);
			parametros.put("indexFinal", indexFinal);
    	}
    	if(ordenTipo!=null){
			if (ordenTipo.equals("asc")) {
				parametros.put("ordenTipo", ASC);
			} else if (ordenTipo.equals("des") || ordenTipo.equals("desc")) {
				parametros.put("ordenTipo", DESC);
			}
    	}
		parametros.put("order", order);
		if (object.getClave() != null) {
			parametros.put("clave", object.getClave().toString().trim());
		}
		if(object.getCatalogoParametroPK()!=null && object.getCatalogoParametroPK().getCatalogoParametroId()!=null){
			parametros.put("catalogoParametroId", object.getCatalogoParametroPK().getCatalogoParametroId());
		}
		if(object.getCatalogoParametroPK()!=null && object.getCatalogoParametroPK().getOrganizacionId()!=null){
			parametros.put("organizacionId", object.getCatalogoParametroPK().getOrganizacionId());
		}
		if(object.getCatalogoId()!=null){
			parametros.put("catalogoId", object.getCatalogoId());
		}
		if (object.getCreacionFechaInicial() != null) {
			parametros.put("creacionFecha", getStartOfDay(object.getCreacionFechaInicial()));
        }
        if (object.getCreacionFechaFinal() != null) {
        	parametros.put("creacionFecha", getEndOfDay(object.getCreacionFechaFinal()));
        }
        // Auxiliar para busqueda por rango de fechas campo: ModificacionFecha
        if (object.getModificacionFechaInicial() != null) {
        	parametros.put("modificacionFecha", getStartOfDay(object.getModificacionFechaInicial()));
        }
        if (object.getModificacionFechaFinal() != null) {
        	parametros.put("modificacionFecha", getEndOfDay(object.getModificacionFechaFinal()));
        }
		return parametros;
    }
    private Map<String, Object> getParametrosCatalogos(Integer displayedPage, Integer maxResult, String order, String ordenTipo, CatalogoParametro object) {
    	Map<String, Object> parametros = new HashMap<>();
    	if(displayedPage!=null && maxResult!=null){
			Integer indexInicio = maxResult * (displayedPage - 1);
			Integer indexFinal = indexInicio + maxResult;
			parametros.put("indexInicio", indexInicio + 1);
			parametros.put("indexFinal", indexFinal);
    	}
    	if(ordenTipo!=null){
			if (ordenTipo.equals("asc")) {
				parametros.put("ordenTipo", ASC);
			} else if (ordenTipo.equals("des") || ordenTipo.equals("desc")) {
				parametros.put("ordenTipo", DESC);
			}
    	}
        if (object.getClave() != null) {
            parametros.put("clave", object.getClave().toString().trim());
        }
        if(object.getCatalogoParametroPK()!=null && object.getCatalogoParametroPK().getCatalogoParametroId()!=null){
            parametros.put("catalogoParametroId", object.getCatalogoParametroPK().getCatalogoParametroId());
        }
        if(object.getCatalogoParametroPK()!=null && object.getCatalogoParametroPK().getOrganizacionId()!=null){
            parametros.put("organizacionId", object.getCatalogoParametroPK().getOrganizacionId());
        }
	parametros.put("order",getFormatCampo(order));
        
	return parametros;
    }
    public String getFormatCampo(String campo) {
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

    /*
     * Método que hace una consulta a la tabla CatalogoParametro y obtiene los registros que coincidan con el objeto <b>catalogoParametro</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param displayedPage Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param CatalogoParametro objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    @Override
    public Grid findByCriteria(int displayedPage, int maxResult, String order, String ordenTipo, CatalogoParametro catalogoParametro) {
    	Map<String, Object> parametros = getParametros(displayedPage, maxResult, order, ordenTipo, catalogoParametro);
    	generalizarCriteria(parametros);
        int resultadosTotales = findSizeByCriteria(parametros);
        List<CatalogoParametro> catalogoParametroList = catalogoParametroDAO.getCatalogoParametros(parametros);
        Grid grid = new Grid();
        grid.setGrid(catalogoParametroList);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    
    @Override
    public Grid findByCriteriaRestricciones(int displayedPage, int maxResult, String order, String ordenTipo, CatalogoParametro catalogoParametro, int catId, int orgId) {
    	catalogoParametro.setCatalogoId(catId);
        catalogoParametro.getCatalogoParametroPK().setOrganizacionId(orgId);
        Map<String, Object> parametros = getParametros(displayedPage, maxResult, order, ordenTipo, catalogoParametro);
        parametros.put("order", getFormatCampo(order));
    	generalizarCriteria(parametros);
        int resultadosTotales = findSizeByCriteria(parametros);
        List<CatalogoParametro> catalogoParametroList = catalogoParametroDAO.getCatalogoParametros(parametros);
        Grid grid = new Grid();
        grid.setGrid(catalogoParametroList);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }

    @Override
    public CatalogoParametro findFirst() {
    	CatalogoParametro cp = new CatalogoParametro();
    	Map<String, Object> parametros = getParametros(1, 1, null, null, cp);
        List<CatalogoParametro> catalogoParametros = catalogoParametroDAO.getCatalogoParametros(parametros);
        if (catalogoParametros.size()>0) {
            return catalogoParametros.get(0);
        }
        return null;
    }
    
    public Integer findSizeByCriteria(Map<String, Object> parametros) {
        return catalogoParametroDAO.getCount(generalizarCriteria(parametros));
    }

    /*
     * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
     * definidos en el objeto <b>criteria</b>.
     *
     * @param criteria Es un objeto que contiene los parámetros de búsqueda.
     * @return Regresa una lista de objetos <b>CatalogoParametro</b> que coinciden con los parámetros definidos en
     * <b>criteria</b>.
     */

    public List<CatalogoParametro> findByCriteria(Map<String, Object> parametros) {
        return catalogoParametroDAO.getCatalogoParametros(generalizarCriteria(parametros));
    }

    @Override
    public List<CatalogoParametro> findByCriteriaIgnorePrivacy(CatalogoParametro catalogoParametro) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("eliminadoId", NOELIMINADO);
        return catalogoParametroDAO.getCatalogoParametros(parametros);
    }

    public Map<String, Object> generalizarCriteria(Map<String, Object> parametros) {
    	parametros.put("eliminadoId", NOELIMINADO);
        return parametros;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>CatalogoParametro</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>CatalogoParametro</b>.
     */
    @Override
    public List<CatalogoParametro> findAll() {
    	CatalogoParametro cp = new CatalogoParametro();
    	Map<String, Object> parametros = getParametros(null, null, "valor", "asc", cp);
        List<CatalogoParametro> catalogoParametros = catalogoParametroDAO.getCatalogoParametros(parametros);
        return catalogoParametros;
    }

    /*
     * Método que obtiene todos los registros de la tabla de <b>CatalogoParametro</b>.
     * Este método es generalmente utilizado por los action para obtener las opciones
     * de los select que se llenan mediante struts.
     *
     * @return Regresa todos los registros de la tabla <b>CatalogoParametro</b>.
     */
    @Override
    public List<CatalogoParametro> findActive() {
    	CatalogoParametro cp = new CatalogoParametro();
    	cp.setEstatusId(ACTIVO);
    	Map<String, Object> parametros = getParametros(null, null, "valor", "asc", cp);
        List<CatalogoParametro> catalogoParametros = catalogoParametroDAO.getCatalogoParametros(parametros);
        return catalogoParametros;
    }

    /*
     * Método que actualiza el Estatus de los ID' contenidos en la lista
     * <b>CatalogoParametroList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param CatalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    @Override
    public void setEstatus(Integer userId, Integer estatusId, List<CatalogoParametroPK> catalogoParametroList) {
        for (CatalogoParametroPK catalogoParametroTemporal : catalogoParametroList) {
            CatalogoParametro catalogoParametro = findById(catalogoParametroTemporal);
            if (estatusId == 1) {
                catalogoParametro.setEstatusId(ACTIVO);
            } else {
                catalogoParametro.setEstatusId(INACTIVO);
            }
            update(userId, Arrays.asList(catalogoParametro));
        }
    }

    /*
     * Método que actualiza el Visible de los ID' contenidos en la lista
     * <b>CatalogoParametroList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param visibleId Es el ID del visible al cual se quiere cambiar.
     * @param CatalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Visible.
     */
    @Override
    public void setVisible(Integer userId, Integer visible, List<CatalogoParametroPK> catalogoParametroList) {
        for (CatalogoParametroPK catalogoParametroTemporal : catalogoParametroList) {
            CatalogoParametro catalogoParametro = findById(catalogoParametroTemporal);
            if (visible == 1) {
                catalogoParametro.setVisible(true);
            } else {
                catalogoParametro.setVisible(false);
            }
            update(userId, Arrays.asList(catalogoParametro));
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
    public String getReportDataTest(String order, String ordenTipo, CatalogoParametro catalogoParametro) {
        Grid grid = findByCriteria(1, 10000, order, ordenTipo, catalogoParametro);
        return addReportDataToSession(grid.getGrid());
    }

    @Override
    public List<Catalogo> getCatalogo() {
        return addDummy(catalogoBss.findActive(), new Catalogo(), Catalogo.class);
    }

    @Override
    public String hasGrid() {
        return hasGrid(CatalogoParametroAction.class).toString();
    }

    @Override
    public String isIndividual() {
        return isIndividual(CatalogoParametroAction.class).toString();
    }

    @Override
    public String getNombreActionMenu() {
        Pagina pagina = findPageByActionClass(CatalogoParametroAction.class);
        if (pagina == null) {
            return "";
        } else {
            return pagina.getNombre();
        }
    }
    
    public List<CatalogoParametro> getCatalogoParametrosForCombo(Integer catalogoId){
    	return catalogoParametroDAO.getCatalogoParametrosForCombo(catalogoId);
    }
    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */

    public void setCatalogoParametroDAO(CatalogoParametroDAO catalogoParametroDAO) {
        this.catalogoParametroDAO = catalogoParametroDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

}