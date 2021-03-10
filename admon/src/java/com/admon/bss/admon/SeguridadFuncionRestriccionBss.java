package com.admon.bss.admon;

import com.admon.entity.admon.*;
import java.util.List;

public interface SeguridadFuncionRestriccionBss {

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Perfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<SeguridadFuncionRestriccion> seguridadFuncionRestriccionList);
    public List<SeguridadFuncionRestriccion> getRolFunciones(SeguridadRol seguridadRol);
    public void delete(List<SeguridadFuncionRestriccion> seguridadFuncionRestriccionList);
    public List<String> getRolFuncionesArbol(SeguridadRol seguridadRol);
   
}
