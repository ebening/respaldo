package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.ParametroDAO;
import com.admon.entity.admon.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametroBss extends BaseBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    
    private ParametroDAO parametroDAO;
    

    public ParametroBss() {
    }
    
    public List<Long> saveParametro(Integer userId, List<Parametro> aplicacionList, Map<String, Object> parametros) {
        List<Long> savedList = new ArrayList<Long>();
        for (Parametro parametro : aplicacionList) {
            parametro.setModificacionUsuario(userId);
            parametro.setCreacionUsuario(userId);
            parametro.setCreacionFecha(new Timestamp(new Date().getTime()));
            parametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            String tableName = (String)parametros.get("tableName");
            String propertyName = (String)parametros.get("propertyName");
            parametroDAO.saveParametro(tableName, propertyName,parametro);
        }
        return savedList;
    }
    public List<Long> updateParametro(Integer userId, List<Parametro> aplicacionList, Map<String, Object> parametros) {
        List<Long> savedList = new ArrayList<Long>();
        for (Parametro parametro : aplicacionList) {
            parametro.setModificacionUsuario(userId);
            parametro.setModificacionFecha(new Timestamp(new Date().getTime()));
            String tableName = (String)parametros.get("tableName");
            String propertyName = (String)parametros.get("propertyName");
            parametroDAO.updateParametro(tableName, propertyName, parametro);
        }
        return savedList;
    }
    
    public void deleteFisico(List<Long> idList, Map<String, Object> parametros) {
        String tableName = (String)parametros.get("tableName");
        String propertyName = (String)parametros.get("propertyName");
        for (Long id : idList) {
           parametroDAO.deleteFisico(tableName, propertyName, id);
        }
    }
    
    public Grid findByCriteriaParametro(int p, int maxResult, String order, String ordenTipo, Long idPadre, Map<String, Object> parametros) {
        String tableName = (String)parametros.get("tableName");
        String propertyName = (String)parametros.get("propertyName");
        Long value = idPadre;
        return findByCriteria(p, maxResult, order, ordenTipo, tableName, propertyName, value);
    }
    
    public boolean isValidoNombreParametro(Parametro parametro,String tableName,String propertyName ) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        parametros.put("value", parametro.getFkId());
        parametros.put("lenguajeId", parametro.getLenguajeId());
        parametros.put("nombre", parametro.getNombre().trim());
        return !(parametroDAO.isValidoNombreParametro(parametros).size() >= 1);
    }
    
    public List<Parametro> getParametroByIntProperty(String propertyName, Integer value, Map<String, Object> parametros) {
        String tableName = (String)parametros.get("tableName");
        return parametroDAO.getParametroByIntProperty(tableName, propertyName, value);
    }
    
    public Parametro getParametroById(Long parametroId, Map<String, Object> parametros) {
        String tableName = (String)parametros.get("tableName");
        String propertyName = (String)parametros.get("propertyName");
        return parametroDAO.getParametroById(tableName, propertyName, parametroId);
    }
    
   

 

    public Grid findByCriteria(int displayedPage, int maxResult, String order, String ordenTipo, String tableName, String propertyName, Long value) {
        Grid grid = new Grid();
        Integer indexInicio = maxResult * (displayedPage - 1);
        Integer indexFinal = indexInicio+maxResult;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        parametros.put("value", value);
        parametros.put("indexInicio", indexInicio+1);
        parametros.put("indexFinal", indexFinal);
        
        int resultadosTotales = parametroDAO.getCount(parametros);
        List<Parametro> list = parametroDAO.getParametros(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    
    
    
    public Parametro getParametroById(String tableName, String propertyName, Long parametroId){
        return parametroDAO.getParametroById(tableName, propertyName, parametroId);
    }
    
    public void saveParametro(String tableName, String propertyName, Parametro o){
        parametroDAO.saveParametro(tableName, propertyName, o);
    }
    
    public void updateParametro(String tableName, String propertyName, Parametro o){
        parametroDAO.updateParametro(tableName, propertyName, o);
    }
  
    public List<Parametro> getParametroByIntProperty(String tableName, String propertyName, Integer value) {
        return parametroDAO.getParametroByIntProperty(tableName, propertyName, value);
    }
    
    public List<Object[]> isValidoNombreParametro(Map<String, Object> parametros){
        return parametroDAO.isValidoNombreParametro(parametros);
    }
    
    public void deleteFisico(String tableName, String propertyName, Long id){
        parametroDAO.deleteFisico(tableName, propertyName, id);
    }
    
    
    
    
/*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */


    public void setParametroDAO(ParametroDAO parametroDAO) {
        this.parametroDAO = parametroDAO;
    }
    

}
