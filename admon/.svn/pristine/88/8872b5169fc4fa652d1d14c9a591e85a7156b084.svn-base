package com.admon.model.admon;
import com.admon.bss.admon.SeguridadRolRestriccionParBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class SeguridadRolRestriccionParDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private SeguridadRolRestriccionParBss seguridadRestriccionesRolBss;

    public SeguridadRolRestriccionParDWR() {
    }

    /*
 * Guarda la lista <b>seguridadRestriccionesRolList</b> en la tabla <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param seguridadRestriccionesRolList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList) {
        return seguridadRestriccionesRolBss.save(obtieneSessionVar().getUsuarioId(), seguridadRestriccionesRolList);
    }
    
    public void prepareToSave( int rolId,int aplicacionId, List<CatalogoParametro> catalogoParametro) {
       seguridadRestriccionesRolBss.save(obtieneSessionVar().getUsuarioId(),rolId,aplicacionId,catalogoParametro);
    }

    /*
 * Método que actualiza la información de <b>seguridadRestriccionesRol</b> en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param seguridadRestriccionesRolList Lista de registros que se actualizarán en la tabla de <b>Seguridad_Rol_Restriccion_Par</b> en la BD.
     */
    public List<Integer> update(List<SeguridadRolRestriccionPar> seguridadRestriccionesRolList) {
        return seguridadRestriccionesRolBss.update(obtieneSessionVar().getUsuarioId(), seguridadRestriccionesRolList);
    }

    /*
 * Método que busca el registro <b>seguridadRestriccionesRol</b> por su ID en la tabla de <b>Seguridad_Rol_Restriccion_Par</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>Seguridad_Rol_Restriccion_Par</b> con la información del registro <b>seguridadRestriccionesRol</b>.
     */
    public SeguridadRolRestriccionPar findById(Integer seguridadRestriccionesRolId) {
        return seguridadRestriccionesRolBss.findById(seguridadRestriccionesRolId);
    }
    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public SeguridadRolRestriccionPar findFirst() {
        return seguridadRestriccionesRolBss.findFirst();
    }
    /**
     * Método que obtiene roles de acuerdo al idAplicación
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public List<SeguridadRol> getComboRolesByAplicacion(int aplicacionId) {
        return seguridadRestriccionesRolBss.getComboRolesByAplicacion(aplicacionId);
    }
    
    public List<SeguridadRolRestriccionPar> getRestriccionesByCatOrg(int rolId,int aplicacionId,int organizacionId){
        return seguridadRestriccionesRolBss.getRestriccionesByCatOrg(rolId,aplicacionId,organizacionId);
    }
    
    public List<Catalogo> getOrganizacionesByCatalogo(int idCatalogo){
        return seguridadRestriccionesRolBss.getOrganizacionesByCatalogo(idCatalogo);
    }
    
    public List<Organizacion> getOrganizaciones() {
        return seguridadRestriccionesRolBss.getOrganizaciones();
    }
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setSeguridadRestriccionesRolBss(SeguridadRolRestriccionParBss seguridadRestriccionesRolBss) {
        this.seguridadRestriccionesRolBss = seguridadRestriccionesRolBss;
    }

}
