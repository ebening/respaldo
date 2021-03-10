package com.admon.bss.admon;

import com.admon.entity.admon.*;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;

public interface SeguridadRolOperacionBss {

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Perfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(Integer userId, List<SeguridadRolOperacion> seguridadRolOperacionList);
    public List<SeguridadRolOperacion> getRolOperaciones(SeguridadRol seguridadRol);
    public void delete( List<SeguridadRolOperacion> seguridadRolOperacionList);
    public List<String> getRolOperacionesArbol(SeguridadRol seguridadRol);
}
