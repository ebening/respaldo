package com.admon.bss.admon;

import com.admon.entity.admon.*;
import org.hibernate.criterion.DetachedCriteria;
import java.util.List;

public interface PayPalBss {

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>PayPal</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>PayPal</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    public List<Integer> save(Integer userId, List<PayPal> objList);
    

    /*
 * Método que elimina el registro <b>PayPal</b> en la tabla de <b>PayPal</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de registros que se actualizarán en la tabla de <b>PayPal</b> en la BD.
     */
    public List<Integer> update(Integer userId, List<PayPal> objList);

    /*
 * Método que elimina el registro <b>PayPal</b> en la tabla de <b>PayPal</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de registros que se eliminarán en la tabla de <b>PayPal</b> en la BD.
     */
    public void delete(Integer userId, List<Integer> objIdList);

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    public boolean isValidoNombre(String nombre);
    

    /*
 * Método que busca el registro <b>parcializacionSantander</b> por su NOMBRE en la tabla de <b>PayPal</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>PayPal</b> con la información de la consulta.
     */
    public PayPal findByName(String nombre);

    /*
 * Método que busca el registro <b>PayPal</b> por su ID en la tabla de <b>PayPal</b>.
 * @param id es el ID del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>PayPal</b> con la información del registro <b>PayPal</b>.
     */
    public List<PayPal> findById(Integer id);

    public List<PayPal> findByIntProperty(String propertyName, Integer value);

    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value);

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>PayPal</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    public List<PayPal> findByCriteria(DetachedCriteria criteria);

    public List<PayPal> findByCriteriaIgnorePrivacy(PayPal obj);

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public PayPal findFirst();
    
    /*
 * Método que obtiene todos los registros de la tabla de <b>PayPal</b>.
 * @return Regresa todos los registros de la tabla <b>PayPal</b>.
     */
    public List<PayPal> findAll();

    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    public DetachedCriteria createDetachedCriteria();


    /*
 * Método que obtiene los parámetros que le corresponden
 * a un determinado catálogo. Éste método es llamado por los Action
 * para obtener los parámetros del catálogo
 * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
 * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
 * en el archivo <b>CodesBss.properties</b>.
 * @return Regresa una lista con los parámetros del catálogo.
     */
    public List<ConfiguracionParametro> getParametros(String catalogKey);


    /**
     * Método auxiliar utilizado para la funcionalidad "detalle"
 (CONFIGURACION_PARAMETRO) campo PayPal.
     * @return regresa una lista de objetos.
     */
    

}
