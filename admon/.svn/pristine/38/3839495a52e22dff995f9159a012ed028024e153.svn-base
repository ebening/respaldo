package com.admon.model.admon;

import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;
import com.admon.bss.admon.TerminalBanamexBss;

public class TerminalBanamexDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private TerminalBanamexBss terminalBanamexBss;

    public TerminalBanamexDWR() {
    }

    /*
 * Guarda la lista <b>perfilList</b> en la tabla <b>TerminalBanamex</b>.
 * @param perfilList Lista a guardar.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(List<TerminalBanamex> afiliacionList) {
        return terminalBanamexBss.save(obtieneSessionVar().getUsuarioId(), afiliacionList);
    }

    /*
 * Método que actualiza la información de <b>perfil</b> en la tabla de <b>TerminalBanamex</b>.
 * @param perfilList Lista de registros que se actualizarán en la tabla de <b>TerminalBanamex</b> en la BD.
     */
    public List<String> update(List<TerminalBanamex> afiliacionList) {
        return terminalBanamexBss.update(obtieneSessionVar().getUsuarioId(), afiliacionList);
    }

    /*
 * Método que elimina el registro <b>perfil</b> en la tabla de <b>TerminalBanamex</b>.
 * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
 * @param perfilList Lista de registros que se eliminarán en la tabla de <b>TerminalBanamex</b> en la BD.
     */
    public void remove(List<Integer> afiliacionIdList) {
        terminalBanamexBss.delete(obtieneSessionVar().getUsuarioId(), afiliacionIdList);
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombre(TerminalBanamex terminalBanamex) {
        return terminalBanamexBss.isValidoAfiliacionBanamex(terminalBanamex);
    }

    /*
 * Método que busca el registro <b>perfil</b> por su ID en la tabla de <b>TerminalBanamex</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>TerminalBanamex</b> con la información del registro <b>perfil</b>.
     */
    public TerminalBanamex  findById(Integer afiliacionBanamexId) {
        return terminalBanamexBss.findById(afiliacionBanamexId);
    }
/*
    public List<TerminalBanamex> findTerminalBanamexPaginaByIntProperty(String propertyName, Integer value) {
        return afiliacionBanamexBss.findTerminalBanamexByIntProperty(propertyName, value);
    }*/

    /*
 * Método que hace una consulta a la tabla TerminalBanamex y obtiene los registros que coincidan con el objeto <b>perfil</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
 * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
 * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
 * @param order Indica por que campo sera ordenada la búsqueda.
 * @param ordenTipo Tipo de ordenado acendente o decendente.
 * @param TerminalBanamex objeto que contiene los parámetros a buscar.
 * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, TerminalBanamex afiliacion, String nombreAfiliacion) {
        return terminalBanamexBss.findByCriteria(p, maxResult, order, ordenTipo, afiliacion, nombreAfiliacion);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public TerminalBanamex findFirst() {
        return terminalBanamexBss.findFirst();
    }
    // </editor-fold>

    public String getReportDataTest(String order, String ordenTipo, TerminalBanamex afiliacion, String nombreAfiliacion) {
        return terminalBanamexBss.getReportDataTest(order, ordenTipo, afiliacion, nombreAfiliacion);
    }

    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    /**
     * @param parcializacionBanamexBss the parcializacionBanamexBss to set
     */
    public void setTerminalBanamexBss(TerminalBanamexBss parcializacionBanamexBss) {
        this.terminalBanamexBss = parcializacionBanamexBss;
    }
}