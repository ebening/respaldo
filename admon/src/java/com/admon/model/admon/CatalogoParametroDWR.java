package com.admon.model.admon;

import java.util.List;

import com.admon.bss.admon.CatalogoParametroBss;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.CatalogoParametroPK;
import com.admon.entity.admon.Grid;
import com.admon.model.BaseModel;

public class CatalogoParametroDWR extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
     * Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase.
     */
    private CatalogoParametroBss catalogoParametroBss;

    public CatalogoParametroDWR() {
    }
//
//    /*
//     * Guarda la lista <b>catalogoParametroList</b> en la tabla <b>CatalogoParametro</b>.
//     * @param catalogoParametroList Lista a guardar.
//     * @return Regresa una lista con los id's de los registros guardados.
//     */
//    public List<Integer> save(List<CatalogoParametro> catalogoParametroList) {
//        return catalogoParametroBss.save(obtieneSessionVar().getUsuarioId(), catalogoParametroList);
//    }

    public List<CatalogoParametro> saveCatalogoParametro(Integer catalogoId, Integer organizacionId, List<CatalogoParametro> catalogoParametroList) {
        return catalogoParametroBss.saveCatalogoParametro(obtieneSessionVar().getUsuarioId(),
                catalogoId, organizacionId, catalogoParametroList);
    }

    /*
     * Método que actualiza la información de <b>catalogoParametro</b> en la tabla de <b>CatalogoParametro</b>.
     * @param catalogoParametroList Lista de registros que se actualizarán en la tabla de <b>CatalogoParametro</b> en la BD.
     */
    public List<CatalogoParametro> update(List<CatalogoParametro> catalogoParametroList) {
        return catalogoParametroBss.update(obtieneSessionVar().getUsuarioId(), catalogoParametroList);
    }

    /*
     * Método que elimina el registro <b>catalogoParametro</b> en la tabla de <b>CatalogoParametro</b>.
     * Este método se llama <b>remove</b> debido a que en javascript (que se encarga de llamar a este método) <b>delete</b> es una palabra reservada.
     * @param catalogoParametroList Lista de registros que se eliminarán en la tabla de <b>CatalogoParametro</b> en la BD.
     */
    public void remove(List<CatalogoParametroPK> catalogoParametroIdList) {
        catalogoParametroBss.delete(obtieneSessionVar().getUsuarioId(), catalogoParametroIdList);
    }

    /*
     * Método que busca el registro <b>catalogoParametro</b> por su ID en la tabla de <b>CatalogoParametro</b>.
     * @param id es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>CatalogoParametro</b> con la información del registro <b>catalogoParametro</b>.
     */
    public CatalogoParametro findById(CatalogoParametroPK catalogoParametroId) {
        return catalogoParametroBss.findById(catalogoParametroId);
    }

    public List<CatalogoParametro> findCatalogoParametroBy(Integer catalogoId, Integer organizacionId) {
        return catalogoParametroBss.findListCatalogoParametroBy(catalogoId, organizacionId);
    }
    /*
     * Método que hace una consulta a la tabla CatalogoParametro y obtiene los registros que coincidan con el objeto <b>catalogoParametro</b> que es mandado como parámetro en el método. Los resultados de la consulta son páginados y encapsulados en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid del JSP.
     * @param p Número de página que se desea mostrar al usuario en el widget JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param CatalogoParametro objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la pantalla.
     */

    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, CatalogoParametro catalogoParametro) {
        return catalogoParametroBss.findByCriteria(p, maxResult, order, ordenTipo, catalogoParametro);
    }
    public Grid findByCriteriaCatalogoByPar(int p, int maxResult, String order, String ordenTipo, CatalogoParametro catalogoParametro,int catId,int orgId) {
        return catalogoParametroBss.findByCriteriaRestricciones(p, maxResult, order, ordenTipo, catalogoParametro, catId, orgId);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public CatalogoParametro findFirst() {
        return catalogoParametroBss.findFirst();
    }

    /*
     * Método que actualiza el Estatus de los objetos en la lista
     * <b>catalogoParametroList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param catalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<CatalogoParametroPK> catalogoParametroList) {
        catalogoParametroBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, catalogoParametroList);
    }

    /*
     * Método que actualiza el Visible de los objetos en la lista
     * <b>catalogoParametroList</b> en la tabla de <b>CatalogoParametro</b>.
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param visibleId Es el ID del visible al cual se quiere cambiar.
     * @param catalogoParametroList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Visible.
     */
    public void setVisible(Integer visible, List<CatalogoParametroPK> catalogoParametroList) {
        catalogoParametroBss.setVisible(obtieneSessionVar().getUsuarioId(), visible, catalogoParametroList);
    }
    /*
     * Metodos que actualizan los campos <select> en el jsp despues de haber hecho
     * una edición de datos en un popup externo.
     */

    public List<Catalogo> getCatalogo() {
        return catalogoParametroBss.getCatalogo();
    }

    public String getReportDataTest(String order, String ordenTipo, CatalogoParametro catalogoParametro) {
        return catalogoParametroBss.getReportDataTest(order, ordenTipo, catalogoParametro);
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setCatalogoParametroBss(CatalogoParametroBss catalogoParametroBss) {
        this.catalogoParametroBss = catalogoParametroBss;
    }

}