package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.SeguridadFuncionRestriccionDAO;
import com.admon.entity.admon.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeguridadFuncionRestriccionBssImpl extends BaseBss implements SeguridadFuncionRestriccionBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private SeguridadFuncionRestriccionDAO seguridadFuncionRestriccionDAO;

    public SeguridadFuncionRestriccionBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>Perfil</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(List<SeguridadFuncionRestriccion> seguridadFuncionRestriccionList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (SeguridadFuncionRestriccion seguridad : seguridadFuncionRestriccionList) {
            seguridad.setEstatusId(ACTIVO);
            seguridadFuncionRestriccionDAO.saveRolFuncionRestriccion(seguridad);
        }
        return savedList;
    }
    @Override
    public void delete(List<SeguridadFuncionRestriccion> seguridadFuncionRestriccionList) {
         for (SeguridadFuncionRestriccion funcion : seguridadFuncionRestriccionList) {
            seguridadFuncionRestriccionDAO.deleteRolFuncionRestriccion(funcion);
        }
    }
    @Override
    public List<SeguridadFuncionRestriccion> getRolFunciones(SeguridadRol seguridadRol) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("aplicacionId", "");
        parametros.put("rolId", seguridadRol.getRolId());
        parametros.put("funcionalidadId", "");
        return seguridadFuncionRestriccionDAO.getFuncionesRestricciones(parametros); 
    }
    @Override
    public List<String> getRolFuncionesArbol(SeguridadRol seguridadRol) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("aplicacionId", seguridadRol.getAplicacionId());
        parametros.put("rolId", seguridadRol.getRolId());
        parametros.put("funcionalidadId", "");
        return seguridadFuncionRestriccionDAO.getFuncionesArbol(parametros); 
    }
    /*
 * Método que actualiza la información de <b>perfil</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param perfilList Lista de registros que se actualizarán en la tabla de <b>Perfil</b> en la BD.
     */
 /*   @Override
    public List<Integer> update(List<SeguridadFuncionRestriccion> seguridadFuncionRestriccionList) {
        List<Integer> idList = new ArrayList();
        for (SeguridadFuncionRestriccion seguridad : seguridadFuncionRestriccionList) {
            seguridad.setEstatusId(ACTIVO);
            idList.add(seguridadFuncionRestriccionDAO.save(seguridad));
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>perfil</b> en la tabla de <b>Perfil</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param perfilList Lista de registros que se eliminarán en la tabla de <b>Perfil</b> en la BD.
     */
  /*  @Override
    public void delete(Integer userId, List<Integer> perfilIdList) {
        for (Integer perfilId : perfilIdList) {
            DetachedCriteria criteria = createDetachedCriteria();
            criteria.add(Example.create(comision));
            criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
            Perfil perfil = findById(perfilId);
            perfil.setEliminadoId(ELIMINADO);
            perfil.setModificacionUsuario(userId);
            perfil.setModificacionFecha(new Timestamp(new Date().getTime()));
            seguridadFuncionRestriccionDAO.update(perfil);
        }
    }


    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadFuncionRestriccionDAO(SeguridadFuncionRestriccionDAO seguridadFuncionRestriccionDAO) {
        this.seguridadFuncionRestriccionDAO = seguridadFuncionRestriccionDAO;
    }

    
}
