package com.admon.dao.admon;

import org.hibernate.Session;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.ConfiguracionParametro;

public class ConfiguracionParametroDAO extends GenericDAOImpl<ConfiguracionParametro, Integer> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<ConfiguracionParametro> getEntityClass() {
        return ConfiguracionParametro.class;
    }
    
    public String getValor(Integer configuracionId, String nombre){
        String query = "SELECT VALOR FROM CONFIGURACION_PARAMETRO WHERE CONFIGURACION_ID="+configuracionId+" AND NOMBRE = '"+nombre+"'";
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Object o = s.createSQLQuery(query).uniqueResult();
            if(o!=null){
            	return o.toString();
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }finally{
        	if(s!=null)
                s.close();
        }
        return null;
    }
}