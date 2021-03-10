package com.admon.model.admon;

import com.admon.bss.admon.AfiliacionSantanderBss;
import com.admon.bss.admon.CatalogoParametroBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class AfiliacionSantanderDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private AfiliacionSantanderBss afiliacionSantanderBss;
    private CatalogoParametroBss catalogoParametroBss;

    public AfiliacionSantanderDWR() {
    }

   /*
 * Guarda la lista <b>objList</b> en la tabla <b>AfiliacionSantander</b>.
 * @param objList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(List<AfiliacionSantander> objList) {
        return afiliacionSantanderBss.save(obtieneSessionVar().getUsuarioId(), objList);
    }

    /*
     * Guarda la lista <b>objList</b> en la tabla <b>AfiliacionSantander</b>.
     * @param objList Lista a guardar.
     * @return Regresa una lista con los id's de los registros guardados.
     */
//    public List<Long> saveConfiguracionParametro(List<ConfiguracionParametro> objList) {
//        return afiliacionSantanderBss.saveConfiguracionParametro(obtieneSessionVar().getUsuarioId(), objList);
//    }
    
//    public List<Long> updateAfiliacionSantanderParametro(List<Parametro> objList) {
//        return afiliacionSantanderBss.updateAfiliacionSantanderParametro(obtieneSessionVar().getUsuarioId(), objList);
//    }

    /*
 * Método que actualiza la información de <b>pagina</b> en la tabla de <b>AfiliacionSantander</b>.
 * @param objList Lista de registros que se actualizarán en la tabla de <b>AfiliacionSantander</b> en la BD.
     */
    public List<Long> update(List<AfiliacionSantander> objList) {
        return afiliacionSantanderBss.update(obtieneSessionVar().getUsuarioId(), objList);
    }

    /*
 * Método que elimina el registro <b>pagina</b> en la tabla de <b>AfiliacionSantander</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param objList Lista de registros que se eliminarán en la tabla de <b>AfiliacionSantander</b> en la BD.
     */
    public void remove(List<Long> paginaIdList) {
        afiliacionSantanderBss.delete(obtieneSessionVar().getUsuarioId(), paginaIdList);
    }

    /*
 * Método para obtener las páginas que conforman el menu.
 *
 * @return regresa una lista de objetos AfiliacionSantander.
     */
    public List<AfiliacionSantander> getAfiliacionSantanders() {
        // Obtener todos los registros de la tabla AfiliacionSantander y ordenarlo alfabeticamente.
            return afiliacionSantanderBss.findActive();
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombre(String nombre) {
        return afiliacionSantanderBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>pagina</b> por su ID en la tabla de <b>AfiliacionSantander</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>AfiliacionSantander</b> con la información del registro <b>pagina</b>.
     */
    public AfiliacionSantander findById(Long id) {
        return afiliacionSantanderBss.findById(id);
    }

    /*
 * Método que hace una consulta a la tabla AfiliacionSantander y obtiene los registros que coincidan con el objeto <b>pagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param AfiliacionSantander objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, AfiliacionSantander pagina) {
        return afiliacionSantanderBss.findByCriteria(p, maxResult, order, ordenTipo, pagina);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public AfiliacionSantander findFirst() {
        return afiliacionSantanderBss.findFirst();
    }

    /*
 * Método que actualiza el Estatus de los objetos en la lista
 * <b>objList</b> en la tabla de <b>AfiliacionSantander</b>.
 * @param userId userId Es el ID del usuario que realizó la operación.
 * @param estatusId Es el ID del estatus al cual se quiere cambiar.
 * @param objList Es una lista que contiene los registros los cuales se
 * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<Long> objList) {
        afiliacionSantanderBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, objList);
    }

    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, AfiliacionSantander pagina) {
        return afiliacionSantanderBss.getReportDataTest(order, ordenTipo, pagina);
    }
    public AfiliacionSantander getCatalogosAfiliaciones(){
         return afiliacionSantanderBss.getCatalogosAfiliaciones("AFILIACION_SANTANDER");
    }
    
    public String getCripto(String clave){
        CatalogoParametro parametro = new CatalogoParametro();
        parametro.setClave(clave);
        List<CatalogoParametro> parametros = catalogoParametroBss.findByCriteriaIgnorePrivacy(parametro);
        String valor = "";
        if(!parametros.isEmpty()){
            @SuppressWarnings("UnusedAssignment")
            CatalogoParametro cp = new CatalogoParametro();
            cp = parametros.get(0);
            valor = cp.getValor();
        }
        return valor;
    }
    
    public Grid desplegar(int p, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion){
        Grid grid = new Grid();
        grid = afiliacionSantanderBss.desplegar(obtieneSessionVar().getUsuarioId(), p, maxResult, order, ordenTipo, organizaciones, elementos, idEjecucion);
        return grid;
    }
        
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setAfiliacionSantanderBss(AfiliacionSantanderBss afiliacionSantanderBss) {
        this.afiliacionSantanderBss = afiliacionSantanderBss;
    }
    
     public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
        this.catalogoParametroBss = catalogoParametroBss;
    }
    


}
