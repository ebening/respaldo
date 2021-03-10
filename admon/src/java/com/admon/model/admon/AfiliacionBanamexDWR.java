package com.admon.model.admon;

import com.admon.bss.admon.AfiliacionBanamexBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class AfiliacionBanamexDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private AfiliacionBanamexBss afiliacionBanamexBss;

    public AfiliacionBanamexDWR() {
    }

    /*
 * Guarda la lista <b>perfilList</b> en la tabla <b>AfiliacionBanamex</b>.
 * @param perfilList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<AfiliacionBanamex> perfilList) {
        return afiliacionBanamexBss.save(obtieneSessionVar().getUsuarioId(), perfilList);
    }

    /*
 * Método que actualiza la información de <b>perfil</b> en la tabla de <b>AfiliacionBanamex</b>.
 * @param perfilList Lista de registros que se actualizarán en la tabla de <b>AfiliacionBanamex</b> en la BD.
     */
    public List<String> update(List<AfiliacionBanamex> afiliacionesList) {
        return afiliacionBanamexBss.update(obtieneSessionVar().getUsuarioId(), afiliacionesList);
    }

    /*
 * Método que elimina el registro <b>perfil</b> en la tabla de <b>AfiliacionBanamex</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param perfilList Lista de registros que se eliminarán en la tabla de <b>AfiliacionBanamex</b> en la BD.
     */
    public void remove(List<Integer> afiliacionIdList) {
        afiliacionBanamexBss.delete(obtieneSessionVar().getUsuarioId(), afiliacionIdList);
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombreByAfiliacion(String nombre) {
       return (afiliacionBanamexBss.findByName(nombre)!=null)?false:true;
    }
    public boolean isValidoNombreByAfiliacionCanal(String nombre,Integer canalVenta) {
       return afiliacionBanamexBss.isValidoByAfiliacionCanal(nombre,canalVenta);
    }
    
    public boolean existValorDefault(String afiliacion,Integer canalVentaId) {
       return afiliacionBanamexBss.existValorDefault(afiliacion, canalVentaId);
    }

    /*
 * Método que busca el registro <b>perfil</b> por su ID en la tabla de <b>AfiliacionBanamex</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>AfiliacionBanamex</b> con la información del registro <b>perfil</b>.
     */
    public AfiliacionBanamex  findById(Integer afiliacionBanamexId) {
        return afiliacionBanamexBss.findById(afiliacionBanamexId);
    }
/*
    public List<AfiliacionBanamex> findAfiliacionBanamexPaginaByIntProperty(String propertyName, Integer value) {
        return afiliacionBanamexBss.findAfiliacionBanamexByIntProperty(propertyName, value);
    }*/

    /*
 * Método que hace una consulta a la tabla AfiliacionBanamex y obtiene los registros que coincidan con el objeto <b>perfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param AfiliacionBanamex objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, AfiliacionBanamex afiliacion) {
        return afiliacionBanamexBss.findByCriteria(p, maxResult, order, ordenTipo, afiliacion);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public AfiliacionBanamex findFirst() {
        return afiliacionBanamexBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>perfilList</b> en la tabla de <b>AfiliacionBanamex</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param perfilList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Integer> perfilList) {
        afiliacionBanamexBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, perfilList);
    }
    // </editor-fold>

    public String getReportDataTest(String order, String ordenTipo, AfiliacionBanamex perfil) {
        return afiliacionBanamexBss.getReportDataTest(order, ordenTipo, perfil);
    }
    
    public AfiliacionBanamex getCatalogosAfiliaciones(){
         return afiliacionBanamexBss.getCatalogosAfiliaciones("AFILIACION_BANAMEX");
    }
    
    public List getCatalogos(String clave){
         return afiliacionBanamexBss.getCatalogos(clave);
    }
    
    public Grid desplegar(int p, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion){
        Grid grid = new Grid();
        System.out.println(organizaciones);
        System.out.println(elementos);
        System.out.println(idEjecucion);
        grid = afiliacionBanamexBss.desplegar(obtieneSessionVar().getUsuarioId(), p, maxResult, order, ordenTipo, organizaciones, elementos, idEjecucion);
        return grid;
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setAfiliacionBanamexBss(AfiliacionBanamexBss afiliacionBanamexBss) {
        this.afiliacionBanamexBss = afiliacionBanamexBss;
    }

}