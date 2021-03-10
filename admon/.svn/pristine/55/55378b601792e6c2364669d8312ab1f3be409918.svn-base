package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PerfilAplicacionDAO;
import com.admon.entity.admon.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfilAplicacionBss extends BaseBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    
    private PerfilAplicacionDAO perfilAplicacionDAO;
    private SeguridadPerfilBss seguridadPerfilBss;
    

    public PerfilAplicacionBss() {
    }
    
    public List<Integer> savePerfilAplicacion(Integer userId, List<PerfilAplicacion> perfilAplicacionList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (PerfilAplicacion perfilAplicacion : perfilAplicacionList) {
            perfilAplicacion.setModificacionUsuario(userId);
            perfilAplicacion.setCreacionUsuario(userId);
            perfilAplicacion.setCreacionFecha(new Timestamp(new Date().getTime()));
            perfilAplicacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilAplicacionDAO.savePerfilAplicacion(perfilAplicacion);
            savedList.add(perfilAplicacion.getUsuarioId());
        }
        return savedList;
    }
    public List<Integer> updatePerfilAplicacion(Integer userId, List<PerfilAplicacion> perfilAplicacionList, int perfilId) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (PerfilAplicacion perfilAplicacion : perfilAplicacionList) {
            perfilAplicacion.setModificacionUsuario(userId);
            perfilAplicacion.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilAplicacionDAO.updatePerfilAplicacion(perfilAplicacion,perfilId);
            savedList.add(perfilAplicacion.getUsuarioId());
        }
        return savedList;
    }
    
    public void deleteFisico( List<PerfilAplicacion> perfilAplicacionList) {
        for (PerfilAplicacion  perfilAplicacion : perfilAplicacionList) {
           perfilAplicacionDAO.deleteFisico(perfilAplicacion);
        }
    }
    
    public Grid findByCriteriaPerfilAplicacion(int p, int maxResult, String order, String ordenTipo, int usuarioId, int organizacionId) {
        return findByCriteria(p, maxResult, order, ordenTipo, usuarioId,organizacionId);
    }
    
    public boolean isValidoPerfilAplicacion(PerfilAplicacion perfilAplicacion) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", perfilAplicacion.getUsuarioId());
        parametros.put("perfilId", perfilAplicacion.getSeguridadPerfilId());
        parametros.put("organizacionId", perfilAplicacion.getOrganizacionId());
        return !(perfilAplicacionDAO.isValidoPerfilAplicacion(parametros).size() >= 1);
    }
    
    
    public List<PerfilAplicacion>  getParametroById(PerfilAplicacion perfilAplicacion) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", perfilAplicacion.getUsuarioId());
        parametros.put("aplicacionId", perfilAplicacion.getAplicacionId());
        parametros.put("perfilId", perfilAplicacion.getSeguridadPerfilId());
        parametros.put("organizacionId", perfilAplicacion.getOrganizacionId());
        return perfilAplicacionDAO.getPerfilAplicacionByIntProperty(parametros);
    }
    
    
    public Grid findByCriteria(int displayedPage, int maxResult, String order, String ordenTipo,int usuarioId,int organizacionId) {
        Grid grid = new Grid();
        Integer indexInicio = maxResult * (displayedPage - 1);
        Integer indexFinal = indexInicio+maxResult;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", usuarioId);
        parametros.put("organizacionId", organizacionId);
        parametros.put("indexInicio", indexInicio+1);
        parametros.put("indexFinal", indexFinal);
        int resultadosTotales = perfilAplicacionDAO.getCount(parametros);
        List<PerfilAplicacion> list = perfilAplicacionDAO.getPerfilAplicaciones(parametros);
        grid.setGrid(list);
        grid.setTotal(resultadosTotales);
        grid.setPaginas(lastPage(resultadosTotales, maxResult));
        return grid;
    }
    public  List<SeguridadPerfil> getPerfilesComboByAplicacion(int aplicacionId){
        return seguridadPerfilBss.getPerfilesComboByAplicacion(aplicacionId);
    }

    
/*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPerfilAplicacionDAO(PerfilAplicacionDAO perfilAplicacionDAO) {
        this.perfilAplicacionDAO = perfilAplicacionDAO;
    }

    /**
     * @param seguridadPerfilBss the seguridadPerfilBss to set
     */
    public void setSeguridadPerfilBss(SeguridadPerfilBss seguridadPerfilBss) {
        this.seguridadPerfilBss = seguridadPerfilBss;
    }
    
    

}
