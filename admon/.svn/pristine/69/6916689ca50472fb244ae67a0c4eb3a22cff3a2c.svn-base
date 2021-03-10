package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PayPalDAO;
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

public class PayPalBssImpl extends BaseBss implements PayPalBss {

    /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PayPalDAO payPalDAO;
    private UsuarioBss usuarioBss;
    private ConfiguracionParametroBss configuracionParametroBss;


    public PayPalBssImpl() {
    }

    /*
 * Método que guarda los objetos contenidos en la lista <b>objectList</b> en la tabla de <b>PayPal</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objectList Lista de objetos que se guardarán en la tabla de <b>PayPal</b> en la BD.
 * @return Regresa una lista con los id's de los registros guardados.
     */
    @Override
    public List<Integer> save(Integer userId, List<PayPal> objList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (PayPal obj : objList) {
            obj.setCreacionUsuario(userId);
            obj.setCreacionFecha(new Timestamp(new Date().getTime()));
            savedList.add(payPalDAO.save(obj));
        }
        return savedList;
    }
 
    /*
 * Método que actualiza la información de <b>PayPal</b> en la tabla de <b>PayPal</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param objList Lista de registros que se actualizarán en la tabla de <b>PayPal</b> en la BD.
     */
    @Override
    public List<Integer> update(Integer userId, List<PayPal> objList) {
        List<Integer> idList = new ArrayList();
        for (PayPal obj : objList) {
            payPalDAO.update(obj);
            idList.add(obj.getPayPalId());
        }
        return idList;
    }

    /*
 * Método que elimina el registro <b>PayPal</b> en la tabla de <b>PayPal</b>.
 * @param userId Es el id del usuario que realizó la operación. * @param idList Lista de registros que se eliminarán en la tabla de <b>PayPal</b> en la BD.
     */
    @Override
    public void delete(Integer userId, List<Integer> idList) {
        for (Integer id : idList) {
            List<PayPal> objList = findById(id);
            for (PayPal obj : objList) {
                payPalDAO.delete(obj);
            }
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
 * Método que busca el registro <b>PayPal</b> por su NOMBRE en la tabla de <b>PayPal</b>.
 * @param nombre es el NOMBRE del registro que se quiere obtener.
 * @return Regresa un objeto tipo <b>PayPal</b> con la información de la consulta.
     */
    @Override
    public PayPal findByName(String nombre) {
        return getFirst(findByCriteria(createDetachedCriteria()
                .add(Expression.eq("afiliacion", nombre.trim()))));
    }

    /*
 * Busca el registro <b>PayPal</b> por su ID en la tabla de <b>PayPal</b>.
 * @param id es el ID del registro a obtener.
 * @return Regresa un objeto <b>PayPal</b> con la información del registro <b>id</b>.
     */
    @Override
    public List<PayPal> findById(Integer id) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Expression.eq("payPalId", id));
        return payPalDAO.findByCriteria(criteria);
    }

    @Override
    public List<PayPal> findByIntProperty(String propertyName, Integer value) {
        return (findByCriteria(
                createDetachedCriteria().add(Expression.eq(propertyName, value))));
    }

    @Override
    public List<ConfiguracionParametro> findConfiguracionParametroByIntProperty(String propertyName, Integer value) {
        return configuracionParametroBss.findByIntProperty(propertyName, value);
    }

    @Override
    public PayPal findFirst() {
        List<PayPal> objList = findByCriteria(createDetachedCriteria());
        if (objList.isEmpty()) {
            return null;
        } else {
            return objList.get(0);
        }
    }

    public Integer findSizeByCriteria(DetachedCriteria criteria) {
        return payPalDAO.findSizeByCriteria(criteria);
    }

    /*
 * Método que realiza un búsqueda en la BD obteniendo todos los registros que coincidan con los parámetros
 * definidos en el objeto <b>criteria</b>.
 *
 * @param criteria Es un objeto que contiene los parámetros de búsqueda.
 * @return Regresa una lista de objetos <b>PayPal</b> que coinciden con los parámetros definidos en
 * <b>criteria</b>.
     */
    @Override
    public List<PayPal> findByCriteria(DetachedCriteria criteria) {
        return payPalDAO.findByCriteria(criteria);
    }

    @Override
    public List<PayPal> findByCriteriaIgnorePrivacy(PayPal obj) {
        DetachedCriteria criteria = createDetachedCriteria();
        criteria.add(Example.create(obj));
        return payPalDAO.findByCriteria(criteria);
    }


    /*
 * Método que obtiene todos los registros de la tabla de <b>PayPal</b>.
 * Este método es generalmente utilizado por los action para obtener las opciones
 * de los select que se llenan mediante struts.
 *
 * @return Regresa todos los registros de la tabla <b>PayPal</b>.
     */
    @Override
    public List<PayPal> findAll() {
        return findByCriteria(createDetachedCriteria().addOrder(Order.asc("afiliacion")));
    }



    /*
 * Método que obtiene un objeto DetachedCriteria.
 *
 * @return Regresa un objeto DetachedCriteria.
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return payPalDAO.createDetachedCriteria();
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
     * @param payPalDAO the payPalDAO to set
     */
    public void setPayPalDAO(PayPalDAO payPalDAO) {
        this.payPalDAO = payPalDAO;
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
