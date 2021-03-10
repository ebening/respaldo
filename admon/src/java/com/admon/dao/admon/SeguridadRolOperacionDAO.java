package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.SeguridadRolOperacion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2x.StringUtils;

public class SeguridadRolOperacionDAO extends GenericDAOImpl<SeguridadRolOperacion, Integer> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<SeguridadRolOperacion> getEntityClass() {
        return SeguridadRolOperacion.class;
    }
    private String getQueryParametros(Map<String, Object> parametros){
        StringBuilder query = new StringBuilder();
        query.append(" WHERE ROL_OPERACION_ID IS NOT NULL ");
        if(StringUtils.isNotEmpty(parametros.get("aplicacionId").toString())){
                query.append(" AND APLICACION_ID = ").append((Integer)parametros.get("aplicacionId"));
        }
        if(StringUtils.isNotEmpty(parametros.get("rolId").toString())){
                query.append(" AND ROL_ID = ").append((Integer)parametros.get("rolId"));
        }
        if(StringUtils.isNotEmpty(parametros.get("operacionId").toString())){
                query.append(" AND OPERACION_ID = ").append((Integer)parametros.get("operacionId"));
        }
        return query.toString();
    }
    
    private StringBuilder getQuery(Map<String, Object> parametros){
        StringBuilder query = new StringBuilder();
        query.append("SELECT ROL_OPERACION_ID,ROL_ID,OPERACION_ID,APLICACION_ID ");
        query.append("FROM SEGURIDAD_ROL_OPERACION");
        query.append(getQueryParametros(parametros));
    return query;
    }
    private StringBuilder getQueryArbol(Map<String, Object> parametros){
        StringBuilder query = new StringBuilder();
        query.append("SELECT OPERACION_ID||'-O'");
        query.append("FROM SEGURIDAD_ROL_OPERACION");
        query.append(getQueryParametros(parametros));
    return query;
    }
    
    @SuppressWarnings("unchecked")
    public List<SeguridadRolOperacion> getRolOperaciones(Map<String, Object> parametros) {
    	StringBuilder query = getQuery(parametros);
    	List<SeguridadRolOperacion> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query.toString()).list();
    	for(Object[] ob: l){
            Integer index = 0;
            SeguridadRolOperacion rolOperacion = new SeguridadRolOperacion();
            rolOperacion.setRolOperacionId(((BigDecimal)ob[index++]).intValue());
            rolOperacion.setRolId(((BigDecimal)ob[index++]).intValue());
            rolOperacion.setOperacionId(((BigDecimal)ob[index++]).intValue());
            rolOperacion.setAplicacionId(((BigDecimal)ob[index++]).intValue());
            list.add(rolOperacion);
    	}
    	s.close();
    	return list;
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getRolOperacionesArbol(Map<String, Object> parametros) {
    	StringBuilder query = getQueryArbol(parametros);
    	List<String> list = new ArrayList<String>();
    	Session s = getSessionFactory().openSession();
    	List<String> l = s.createSQLQuery(query.toString()).list();
    	for(String ob: l){
            list.add(ob);
    	}
    	s.close();
    	return list;
    }
    public void saveRolOperacion(SeguridadRolOperacion op){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT SEGURIDAD_ROL_OPERACION_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_ROL_OPERACION (ROL_OPERACION_ID, APLICACION_ID, ROL_ID,OPERACION_ID,CREACION_FECHA,CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES (?,?,?,?,?,?,?,?)";
    	
    	q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setLong(index++, id);
    	q.setLong(index++, op.getAplicacionId());
        q.setLong(index++, op.getRolId());
        q.setLong(index++, op.getOperacionId());
    	q.setTimestamp(index++, op.getCreacionFecha());
    	q.setInteger(index++, op.getCreacionUsuario());
    	q.setTimestamp(index++, op.getModificacionFecha());
    	q.setInteger(index++, op.getModificacionUsuario());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    public void deleteRolOperacion(SeguridadRolOperacion op){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "DELETE FROM SEGURIDAD_ROL_OPERACION "
    			+ "WHERE OPERACION_ID=? AND ROL_ID=?";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setInteger(index++, op.getOperacionId());
    	q.setInteger(index++, op.getRolId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
}
