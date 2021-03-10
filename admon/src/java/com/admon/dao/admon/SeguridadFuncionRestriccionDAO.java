package com.admon.dao.admon;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.SeguridadFuncionRestriccion;
import java.sql.Date;
import org.hibernate.tool.hbm2x.StringUtils;

public class SeguridadFuncionRestriccionDAO extends GenericDAOImpl<SeguridadFuncionRestriccion, Integer> {

   /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<SeguridadFuncionRestriccion> getEntityClass() {
        return SeguridadFuncionRestriccion.class;
    }
    
    private String getQueryParametros(Map<String, Object> parametros){
        StringBuilder query = new StringBuilder();
        query.append(" WHERE ACTIVO = 1");
        if(StringUtils.isNotEmpty(parametros.get("aplicacionId").toString())){
                query.append(" AND APLICACION_ID = ").append((Integer)parametros.get("aplicacionId"));
        }
        if(StringUtils.isNotEmpty(parametros.get("rolId").toString())){
                query.append(" AND ROL_ID = ").append((Integer)parametros.get("rolId"));
        }
        if(StringUtils.isNotEmpty(parametros.get("funcionalidadId").toString())){
                query.append(" AND FUNCIONALIDAD_ID = ").append((Integer)parametros.get("funcionalidadId"));
        }
        return query.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
        String query = "SELECT APLICACION_ID,FUNCIONALIDAD_ID,ROL_ID,ACTIVO "+
                    "FROM SEGURIDAD_FUNCION_RESTRICCION "+getQueryParametros(parametros);
    return query;
    }
    private String getQueryArbol(Map<String, Object> parametros){
        String query = "SELECT FUNCIONALIDAD_ID||'-F'"+
                    "FROM SEGURIDAD_FUNCION_RESTRICCION "+getQueryParametros(parametros);
    return query;
    }
    @SuppressWarnings("unchecked")
    public List<SeguridadFuncionRestriccion> getFuncionesRestricciones(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<SeguridadFuncionRestriccion> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
            Integer index = 0;
            SeguridadFuncionRestriccion funcionRestriccion = new SeguridadFuncionRestriccion();
            funcionRestriccion.setAplicacionId(((BigDecimal)ob[index++]).intValue());
            funcionRestriccion.setFuncionalidadId(((BigDecimal)ob[index++]).intValue());
            funcionRestriccion.setRolId(((BigDecimal)ob[index++]).intValue());
            funcionRestriccion.setEstatusId(((BigDecimal)ob[index++]).intValue());
            list.add(funcionRestriccion);
    	}
    	s.close();
    	return list;
    }
    @SuppressWarnings("unchecked")
    public List<String> getFuncionesArbol(Map<String, Object> parametros) {
    	String query = getQueryArbol(parametros);
    	List<String> list = new ArrayList<String>();
    	Session s = getSessionFactory().openSession();
    	List<String> l = s.createSQLQuery(query).list();
    	for(String ob: l){
            list.add(ob);
    	}
    	s.close();
    	return list;
    }
    public void saveRolFuncionRestriccion(SeguridadFuncionRestriccion func){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "INSERT INTO SEGURIDAD_FUNCION_RESTRICCION (APLICACION_ID,FUNCIONALIDAD_ID,ROL_ID,ACTIVO) "
    			+ "VALUES (?,?,?,?)";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setLong(index++, func.getAplicacionId());
    	q.setLong(index++, func.getFuncionalidadId());
    	q.setInteger(index++, func.getRolId());
    	q.setInteger(index++, func.getEstatusId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    public void deleteRolFuncionRestriccion(SeguridadFuncionRestriccion f){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "DELETE FROM SEGURIDAD_FUNCION_RESTRICCION "
    			+ "WHERE FUNCIONALIDAD_ID=? AND ROL_ID=?";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setInteger(index++, f.getFuncionalidadId());
    	q.setInteger(index++, f.getRolId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
}
