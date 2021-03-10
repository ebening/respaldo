package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadRolOperacionDAO;
import com.admon.entity.admon.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeguridadRolOperacionBssImpl extends BaseBss implements SeguridadRolOperacionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadRolOperacionDAO seguridadRolOperacionDAO;
    private UsuarioBss usuarioBss;

    public SeguridadRolOperacionBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Perfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<SeguridadRolOperacion> seguridadRolOperacionList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadRolOperacion seguridad : seguridadRolOperacionList) {
            seguridad.setModificacionUsuario(userId);
            seguridad.setCreacionUsuario(userId);
            seguridad.setCreacionFecha(new Timestamp(new Date().getTime()));
            seguridad.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadRolOperacionDAO.saveRolOperacion(seguridad);
        }
        return savedList;
    }
    @Override
    public void delete( List<SeguridadRolOperacion> seguridadRolOperacionList) {
         for (SeguridadRolOperacion seguridad : seguridadRolOperacionList) {
            seguridadRolOperacionDAO.deleteRolOperacion(seguridad);
        }
    }
    
    @Override
    public List<SeguridadRolOperacion> getRolOperaciones(SeguridadRol seguridadRol) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("aplicacionId", "");
        parametros.put("rolId", seguridadRol.getRolId());
        parametros.put("operacionId", "");
        return seguridadRolOperacionDAO.getRolOperaciones(parametros);
         
    }
    
    @Override
    public List<String> getRolOperacionesArbol(SeguridadRol seguridadRol) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("aplicacionId", seguridadRol.getAplicacionId());
        parametros.put("rolId", seguridadRol.getRolId());
        parametros.put("operacionId", "");
        return seguridadRolOperacionDAO.getRolOperacionesArbol(parametros);
         
    }


    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    /**
     * @param seguridadRolOperacionDAO the seguridadRolOperacionDAO to set
     */
    public void setSeguridadRolOperacionDAO(SeguridadRolOperacionDAO seguridadRolOperacionDAO) {
        this.seguridadRolOperacionDAO = seguridadRolOperacionDAO;
    }
    

}
