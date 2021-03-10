package com.admon.model.admon;

import java.util.List;

import com.admon.bss.admon.CatalogoBss;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoPK;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.Grid;
import com.admon.model.BaseModel;

public class CatalogoDWR extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Inyección de dependencias con Spring. Estas dependencias se configuran en
     * el applicationContext.xml, y además necesitan un método setter por cada
     * variable que se anexa al final de la clase.
     */
    private CatalogoBss catalogoBss;

    public CatalogoDWR() {
    }

    /**
     * Guarda la lista <b>catalogoList</b> en la tabla <b>Catalogo</b>.
     *
     * @param catalogoList Lista a guardar.
     * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Catalogo> save(List<Catalogo> catalogoList) {
        return catalogoBss.save(obtieneSessionVar().getUsuarioId(), catalogoList);
    }

    /**
     * Método que actualiza la información de <b>catalogo</b> en la tabla de
     * <b>Catalogo</b>.
     *
     * @param catalogoList Lista de registros que se actualizarán en la tabla de
     * <b>Catalogo</b> en la BD.
     */
    public List<Catalogo> update(List<Catalogo> catalogoList) {
        return catalogoBss.update(obtieneSessionVar().getUsuarioId(), catalogoList);
    }

    /**
     * Método que elimina el registro <b>catalogo</b> en la tabla de
     * <b>Catalogo</b>. Este método se llama <b>remove</b> debido a que en
     * javascript (que se encarga de llamar a este método) <b>delete</b> es una
     * palabra reservada.
     *
     * @param catalogoList Lista de registros que se eliminarán en la tabla de
     * <b>Catalogo</b> en la BD.
     */
    public void remove(List<CatalogoPK> catalogoList) {
        catalogoBss.delete(obtieneSessionVar().getUsuarioId(), catalogoList);
    }

    /**
     * Método que evalúa si existe al menos un registro con un determinado
     * nombre en la tabla.
     *
     * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
     * @return Regresa <b>false</b> si existe al menos un registro con el nombre
     * específicado como parámetro en el método, si no existe ningún registro
     * con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombre(String nombre) {
        return catalogoBss.isValidoNombre(nombre);
    }

    /**
     * Método que busca el registro <b>catalogo</b> por su ID en la tabla de
     * <b>Catalogo</b>.
     *
     * @param catalogoId es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>Catalogo</b> con la información del
     * registro <b>catalogo</b>.
     */
    public Catalogo findById(CatalogoPK catalogoId) {
        return catalogoBss.findById(catalogoId);
    }

    /**
     * Método que hace una consulta a la tabla Catalogo y obtiene los registros
     * que coincidan con el objeto <b>catalogo</b> que es mandado como parámetro
     * en el método. Los resultados de la consulta son páginados y encapsulados
     * en un objeto <b>Grid</b>para que puedan ser mostrados en el widget JQGrid
     * del JSP.
     *
     * @param p Número de página que se desea mostrar al usuario en el widget
     * JQGrid.
     * @param maxResult Cantidad de registros por página que el widget JQGrid
     * puede mostrar.
     * @param order Indica por que campo sera ordenada la búsqueda.
     * @param ordenTipo Tipo de ordenado acendente o decendente.
     * @param catalogo objeto que contiene los parámetros a buscar.
     * @return Regrega un objeto <b>Grid</b> con los datos a mostrar en la
     * pantalla.
     */
    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, Catalogo catalogo) {
        return catalogoBss.findByCriteria(p, maxResult, order, ordenTipo, catalogo);
    }

    public Grid findByCriteriaCatalogoById(int p, int maxResult, String order, String ordenTipo, Catalogo catalogo ){
        return catalogoBss.findByCriteria(p, maxResult, order, ordenTipo, catalogo);
    }
    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public Catalogo findFirst() {
        return catalogoBss.findFirst();
    }
    
    public List<CatalogoParametro> filtrarCatalogoParametro(int catalogoPadreId) {
        return catalogoBss.filtrarCatalogoParametro(catalogoPadreId);
    }


    /**
     * Método que actualiza el Estatus de los objetos en la lista
     * <b>catalogoList</b> en la tabla de <b>Catalogo</b>.
     *
     * @param userId userId Es el ID del usuario que realizó la operación.
     * @param estatusId Es el ID del estatus al cual se quiere cambiar.
     * @param catalogoList Es una lista que contiene los registros los cuales se
     * quiere actualizar su Estatus.
     */
    public void setEstatus(Integer estatusId, List<CatalogoPK> catalogoList) {
        catalogoBss.setEstatus(obtieneSessionVar().getUsuarioId(), estatusId, catalogoList);
    }
    
    
    public Grid desplegar(int p, int maxResult, String order, String ordenTipo, String organizaciones, String elementos, Long idEjecucion){
        Grid grid = new Grid();
        System.out.println(organizaciones);
        System.out.println(elementos);
        System.out.println(idEjecucion);
        grid = catalogoBss.desplegar(obtieneSessionVar().getUsuarioId(), p, maxResult, order, ordenTipo, organizaciones, elementos, idEjecucion);
        return grid;
    }

    /**
     * Inyección de dependencias con Spring, cada referencia definida al inicio
     * de la clase requiere un método Setter.
     */
    public void setCatalogoBss(CatalogoBss catalogoBss) {
        this.catalogoBss = catalogoBss;
    }

}