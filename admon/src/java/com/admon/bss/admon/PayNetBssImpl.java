package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PayNetDAO;
import com.admon.entity.admon.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

public class PayNetBssImpl extends BaseBss implements PayNetBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PayNetDAO payNetDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;


    public PayNetBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>PayNet</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>PayNet</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<PayNet> objList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (PayNet obj : objList) {
            obj.setCreacionUsuario(userId);
            obj.setCreacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(payNetDAO.save(obj));
        }
        return savedList;
    }
 
    /*
 * Método que actualiza la información de <b>PayNet</b> en la tabla de <b>PayNet</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objList Lista de registros que se actualizarán en la tabla de <b>PayNet</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<PayNet> objList) {
        List<Integer> idList = new ArrayList();
        for (PayNet obj : objList) {
            obj.setModificacionUsuario(userId);
            obj.setModificacionFecha(new Timestamp(new Date().getTime()));
            payNetDAO.update(obj);
            idList.add(obj.getPayNetId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>PayNet</b> en la tabla de <b>PayNet</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param idList Lista de registros que se eliminarán en la tabla de <b>PayNet</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> idList) {
        for (Integer id : idList) {
            PayNet obj = findById(id);
            payNetDAO.delete(obj);
        }
    }

    /*
 * Método que evalúa si existe al menos un registro con un determinado
 * nombre en la tabla.
 * @param nombre Es el nombre que se desea buscar en la tabla de la BD.
 * @return Regresa <b>false</b> si existe al menos un registro con el
 * nombre específicado como parámetro en el método, si no existe ningún
 * registro con ese nombre regresa un <b>true</b>.
     */
    @Override
    public boolean isValidoNombre(String nombre) {
        return !(findByCriteria(createDetachedCriteria().add(
                Expression.eq("afiliacion", nombre.trim()))).size() >= 1);
    }

    /*
 * Método que busca el registro <b>PayNet</b> por su NOMBRE en la tabla de <b>PayNet</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>PayNet</b> con la información de la consulta.
     */
    @Override
    public PayNet findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("afiliacion", nombre.trim()))));
    }

    /*
 * Busca el registro <b>PayNet</b> por su ID en la tabla de <b>PayNet</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>PayNet</b> con la información del registro <b>id</b>.
     */
    @Override
    public PayNet findById(Integer id) {
        PayNet obj = payNetDAO.findById(id);
        if (obj == null) {
            return null;
        } else {
            return (Arrays.asList(obj)).get(0);
        }
    }

    @Override
    public List<PayNet> findByIntProperty(String propertyName, Integer value) {
        return (findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    @Override
    public PayNet findFirst() {
        List<PayNet> objList = findByCriteria(createDetachedCriteria());
        if (objList.isEmpty()) {
            return null;
        } else {
            return objList.get(0);
        }
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return payNetDAO.findSizeByCriteria(criteria);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>PayNet</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<PayNet> findByCriteria(DetachedCriteria criteria) {
        return payNetDAO.findByCriteria(criteria);
    }

    @Override
    public List<PayNet> findByCriteriaIgnorePrivacy(PayNet obj) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(obj));
        return payNetDAO.findByCriteria(criteria);
    }


    /*
 * Método que obtiene todos los registros de la tabla de <b>PayNet</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>PayNet</b>.
     */
    @Override
    public List<PayNet> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("afiliacion")));
    }



    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return payNetDAO.createDetachedCriteria();
    }



    /*
 * Método que obtiene los parámetros que le corresponden
 * a un determinado catálogo. Éste método es llamado por los Action
 * para obtener los parámetros del catálogo
 * @param catalogKey Es el código del catálogo del cual se quieren obtener los parámetros.
 * Los códigos se encuentran en el paquete <b>com.admon.bss.resources</b> 
 * en el archivo <b>CodesBss.properties</b>.
 * @return Regresa una lista con los parámetros del catálogo.
     */
    @Override
    public List<ConfiguracionParametro> getParametros(String catalogKey) {
        return addDummy(configuracionParametroBss.getParametros(catalogKey), new ConfiguracionParametro(), ConfiguracionParametro.class);
    }

   
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */

    /**
     * @param payNetDAO the payNetDAO to set
     */
    public void setPayNetDAO(PayNetDAO payNetDAO) {
        this.payNetDAO = payNetDAO;
    }

    /**
     * @param usuarioBss the usuarioBss to set
     */
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    /**
     * @param configuracionParametroBss the configuracionParametroBss to set
     */
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
}
