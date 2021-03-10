package com.admon.model.admon;

import com.admon.bss.admon.AfiliacionSantanderBss;
import com.admon.bss.admon.CatalogoParametroBss;
import com.admon.bss.admon.ParcializacionesSantanderBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class ParcializacionesSantanderDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private ParcializacionesSantanderBss parcializacionesSantanderBss;

    public ParcializacionesSantanderDWR() {
    }
   /*
 * Guarda la lista <b>objList</b> en la tabla <b>ParcializacionSantander</b>.
 * @param objList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Long> save(List<ParcializacionSantander> objList) {
        return parcializacionesSantanderBss.save(obtieneSessionVar().getUsuarioId(), objList);
    }

    /*
     * Guarda la lista <b>objList</b> en la tabla <b>ParcializacionSantander</b>.
     * @param objList Lista a guardar.
     * @return Regresa una lista con los id's de los registros guardados.
     */
//    public List<Long> saveConfiguracionParametro(List<ConfiguracionParametro> objList) {
//        return parcializacionesSantanderBss.saveConfiguracionParametro(obtieneSessionVar().getUsuarioId(), objList);
//    }
    
//    public List<Long> updateParcializacionSantanderParametro(List<Parametro> objList) {
//        return parcializacionesSantanderBss.updateParcializacionSantanderParametro(obtieneSessionVar().getUsuarioId(), objList);
//    }

    /*
 * Método que actualiza la información de <b>pagina</b> en la tabla de <b>ParcializacionSantander</b>.
 * @param objList Lista de registros que se actualizarán en la tabla de <b>ParcializacionSantander</b> en la BD.
     */
    public List<Long> update(List<ParcializacionSantander> objList) {
        return parcializacionesSantanderBss.update(obtieneSessionVar().getUsuarioId(), objList);
    }

    /*
 * Método que elimina el registro <b>pagina</b> en la tabla de <b>ParcializacionSantander</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param objList Lista de registros que se eliminarán en la tabla de <b>ParcializacionSantander</b> en la BD.
     */
    public void remove(List<Long> paginaIdList) {
        parcializacionesSantanderBss.delete(obtieneSessionVar().getUsuarioId(), paginaIdList);
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
        return parcializacionesSantanderBss.isValidoNombre(nombre);
    }

    /*
 * Método que busca el registro <b>pagina</b> por su ID en la tabla de <b>ParcializacionSantander</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>ParcializacionSantander</b> con la información del registro <b>pagina</b>.
     */
    public ParcializacionSantander findById(Long id) {
        return parcializacionesSantanderBss.findById(id);
    }

    /*
 * Método que hace una consulta a la tabla ParcializacionSantander y obtiene los registros que coincidan con el objeto <b>pagina</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param ParcializacionSantander objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, ParcializacionSantander pagina) {
        return parcializacionesSantanderBss.findByCriteria(p, maxResult, order, ordenTipo, pagina);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public ParcializacionSantander findFirst() {
        return parcializacionesSantanderBss.findFirst();
    }


    /*
 * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
 * una edición de datos en un popup externo.
     */
    public String getReportDataTest(String order, String ordenTipo, ParcializacionSantander pagina) {
        return parcializacionesSantanderBss.getReportDataTest(order, ordenTipo, pagina);
    }
   
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setParcializacionesSantanderBss(ParcializacionesSantanderBss parcializacionesSantanderBss) {
        this.parcializacionesSantanderBss = parcializacionesSantanderBss;
    } 


}
