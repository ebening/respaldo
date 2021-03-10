package com.admon.model.admon;

import com.admon.bss.admon.ParametroBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametroDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private ParametroBss parametroBss;

    public ParametroDWR() {
    }
    
    public List<Long> saveParametroLenguaje(List<Parametro> aplicacionList, String tableName, String propertyName) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        return parametroBss.saveParametro(obtieneSessionVar().getUsuarioId(),aplicacionList, parametros);
    }
    
    public List<Long> updateParametroLenguaje(List<Parametro> aplicacionList, String tableName, String propertyName) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        return parametroBss.updateParametro(obtieneSessionVar().getUsuarioId(), aplicacionList,parametros);
    }
    
    public void removeParametroLenguaje(List<Long> idList, String tableName, String propertyName) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        parametroBss.deleteFisico(idList,parametros);
    }
    
    public Grid findByCriteriaParametroLenguaje(int p, int maxResult, String order, String ordenTipo, Long idPadre, String tableName, String propertyName ){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        return  parametroBss.findByCriteriaParametro(p, maxResult, order, ordenTipo, idPadre , parametros);
    }
     
    public boolean isValidoNombreParametroLenguaje(Parametro parametro,String tableName, String propertyName) {
        return parametroBss.isValidoNombreParametro(parametro,tableName, propertyName);
    }

    public List<Parametro> getParametroLenguajeByIntProperty(String propertyName, Integer value, String tableName) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        return parametroBss.getParametroByIntProperty(propertyName, value, parametros);
    }

    public Parametro getParametroLenguajeById(Long parametroId,String tableName, String propertyName) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tableName", tableName);
        parametros.put("propertyName", propertyName);
        return parametroBss.getParametroById(parametroId,parametros);
    }

    /**
     * @param parametroBss the parametroBss to set
     */
    public void setParametroBss(ParametroBss parametroBss) {
        this.parametroBss = parametroBss;
    }

}
