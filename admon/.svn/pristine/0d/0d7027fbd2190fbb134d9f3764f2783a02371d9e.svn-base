package com.admon.model.admon;

import com.admon.bss.admon.ParametroBss;
import com.admon.bss.admon.PerfilAplicacionBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class PerfilAplicacionDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private PerfilAplicacionBss perfilAplicacionBss;

    public PerfilAplicacionDWR() {
    }
    
    public List<Integer> savePerfilAplicacion(List<PerfilAplicacion> perfilAplicacionList) {
        return perfilAplicacionBss.savePerfilAplicacion(obtieneSessionVar().getUsuarioId(),perfilAplicacionList);
    }
    
    public List<Integer> updatePerfilAplicacion(List<PerfilAplicacion> perfilAplicacionList, int perfilId) {
        return perfilAplicacionBss.updatePerfilAplicacion(obtieneSessionVar().getUsuarioId(), perfilAplicacionList, perfilId);
    }
    
    public void removePerfilAplicacion(List<PerfilAplicacion> perfilAplicacionList) {
        perfilAplicacionBss.deleteFisico(perfilAplicacionList);
    }
    
    public Grid findByCriteriaPerfilAplicacion(int p, int maxResult, String order, String ordenTipo, int usuarioId,int organizacionId){
        return  perfilAplicacionBss.findByCriteriaPerfilAplicacion(p, maxResult, order, ordenTipo, usuarioId,organizacionId);
    }
     
    public boolean isValidoNombrePerfilAplicacion(PerfilAplicacion perfilAplicacion) {
        return perfilAplicacionBss.isValidoPerfilAplicacion(perfilAplicacion);
    }

    public List<PerfilAplicacion> getPerfilAplicacionByIntProperty( PerfilAplicacion perfilAplicacion ) {
        return perfilAplicacionBss.getParametroById(perfilAplicacion);
    }
    
    public List<SeguridadPerfil> getPerfiles(int aplicacionId){
         return perfilAplicacionBss.getPerfilesComboByAplicacion(aplicacionId);
    }
    /**
     * @param perfilAplicacionBss the perfilAplicacionBss to set
     */
    public void setPerfilAplicacionBss(PerfilAplicacionBss perfilAplicacionBss) {
        this.perfilAplicacionBss = perfilAplicacionBss;
    }

    
}
