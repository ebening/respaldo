package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Modulo;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2x.StringUtils;

public class AplicacionDAO extends GenericDAOImpl<Aplicacion, Long> {
    
     public ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");
     public final Integer NOELIMINADO = new Integer(codigos.getString("key_eliminado_noeliminado"));
     public final Integer LENGUAJE = new Integer(codigos.getString("key_tipo_lenguaje"));
     public final String INSTANCIA = codigos.getString("key_instancia_bd");

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<Aplicacion> getEntityClass() {
        return Aplicacion.class;
    }
    
    @SuppressWarnings("unchecked")
	public Aplicacion getAplicacion(Long id, Integer lenguajeId){
    	Aplicacion aplicacion = new Aplicacion();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT SA.APLICACION_ID, SA.DESCRIPCION, SAP.LENGUAJE_ID, "
				+ " SA.CREACION_FECHA, SA.CREACION_USUARIO, SA.MODIFICACION_FECHA, SA.MODIFICACION_USUARIO, SA.ESTATUS_ID, SA.ELIMINADO_ID, SAP.NOMBRE AS APLICACION "
				+ " FROM SEGURIDAD_APLICACION SA, SEGURIDAD_APLICACION_PARAMETRO SAP WHERE SA.APLICACION_ID=SAP.APLICACION_ID AND SA.APLICACION_ID=" + id + " AND SAP.LENGUAJE_ID=" + lenguajeId).list();
		for(Object[] o: list){
                        Integer index=0;
			aplicacion.setAplicacionId(((BigDecimal)o[index++]).longValue());
                        Object descipcion = o[index++];
                                if(descipcion!=null){
                                    aplicacion.setDescripcion((String)descipcion);
                                }
                        Object lenguaje = o[index++];
                        if(lenguaje!=null){
                            aplicacion.setLenguajeId(((BigDecimal)lenguaje).intValue());
                        }
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				aplicacion.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				aplicacion.setCreacionFecha((Timestamp)date);
			}
			aplicacion.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				aplicacion.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				aplicacion.setModificacionFecha((Timestamp)date);
			}
                        Object modificacionUsuario = o[index++];
                                if(modificacionUsuario!=null){
                                    aplicacion.setModificacionUsuario(((BigDecimal)modificacionUsuario).intValue());
                                }
			aplicacion.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			aplicacion.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			aplicacion.setNombre((String)o[index++]);
    		break;
    	}
		s.close();
    	return aplicacion;
    }
        
            
    private String getQueryPrincipal(Map<String, Object> parametros){
                StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_APLICACION SA WHERE SA.ELIMINADO_ID=").append(NOELIMINADO);
		query.append(getFilter(parametros));
		return query.toString();
    }
    
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
            filter.append(" AND SA.APLICACION_ID IN (SELECT SAP.APLICACION_ID FROM SEGURIDAD_APLICACION_PARAMETRO SAP WHERE UPPER(SAP.NOMBRE) LIKE '%").append(((String)parametros.get("nombre")).toUpperCase()).append("%')");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
            filter.append(" AND UPPER(SA.DESCRIPCION) LIKE '%").append(((String)parametros.get("descripcion")).toUpperCase()).append("%'");
        }
        return filter.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
            Integer lenguajeId = (Integer) parametros.get("lenguajeId");
            Integer indexInicio = (Integer) parametros.get("indexInicio");
            Integer indexFinal = (Integer) parametros.get("indexFinal");
            if(indexInicio==null) indexInicio=1;
            if(indexFinal==null) indexFinal=10;
            
            String query = "SELECT SA.APLICACION_ID, AP.NOMBRE AS APLICACION, "+
    			"SA.DESCRIPCION, SA.MODIFICACION_FECHA, SA.MODIFICACION_USUARIO,  "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=SA.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"SA.ELIMINADO_ID, SA.ESTATUS_ID " +
    			"FROM SEGURIDAD_APLICACION SA, SEGURIDAD_APLICACION_PARAMETRO AP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SA.ROWID "+
				" AND SA.APLICACION_ID = AP.APLICACION_ID AND AP.LENGUAJE_ID =" + lenguajeId + 
                                getOrder(parametros) + "";
    	return query;
    }
    
        private String getOrder(Map<String, Object> parametros){
    	StringBuilder orderString = new StringBuilder();
    	orderString.append(" ORDER BY ");
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            if(parametros.get("order").equals("nombre")){
            	orderString.append("  SA.");
            } else if(parametros.get("order").equals("AP.NOMBRE")){
            	orderString.append(" ");
            }
            orderString.append(parametros.get("order"));
        } 
        orderString.append(" ");
        if(StringUtils.isNotEmpty((String)parametros.get("ordenTipo"))){
        	orderString.append(parametros.get("ordenTipo"));
        }
        return orderString.toString();
    }
        
    public int getCount(Map<String, Object> parametros){
    Integer count = 0;
            String query = "SELECT COUNT(1) FROM ("+ getQueryPrincipal(parametros)+")";
            Session s = getSessionFactory().openSession();
            count = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
            s.close();
            return count;
    }
    
    @SuppressWarnings("unchecked")
    public List<Aplicacion> getAplicaciones(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Aplicacion> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
            Integer index = 0;
            Aplicacion o = new Aplicacion();
    		o.setAplicacionId(((BigDecimal)ob[index++]).longValue());
    		o.setNombre((String)ob[index++]);
                Object descipcion = ob[index++];
                        if(descipcion!=null){
                            o.setDescripcion((String)descipcion);
                        }
    		Object date = ob[index++];
			if(date!=null && date instanceof Date){
				o.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				o.setModificacionFecha((Timestamp)date);
			}
                Object modificacionUsuario = ob[index++];
                        if(modificacionUsuario!=null){
                            o.setModificacionUsuario(((BigDecimal)modificacionUsuario).intValue());
                        }
		Object usuarioModificacion = ob[index++];
                        if(usuarioModificacion!=null){
                            o.setUsuarioModificacion(((String)usuarioModificacion));
                        }
    		o.setEliminadoId(Integer.valueOf(ob[index++].toString()));
    		o.setEstatusId(Integer.valueOf(ob[index++].toString()));
    		list.add(o);
    	}
    	s.close();
    	return list;
    }
    
     public void saveAplicacion(Aplicacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT APLICACION_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_APLICACION (APLICACION_ID, NOMBRE, DESCRIPCION, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?)";
    	
    	q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setLong(index++, id);
        q.setString(index++, o.getNombre());
    	q.setString(index++, o.getDescripcion());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setInteger(index++, o.getEstatusId());
    	q.setInteger(index++, o.getEliminadoId());
    	q.executeUpdate();
    	
    	query = "INSERT INTO SEGURIDAD_APLICACION_PARAMETRO (APLICACION_PARAMETRO_ID, APLICACION_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	(APLICACION_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
    	q = s.createSQLQuery(query);
        index = 0;
    	q.setLong(index++, id);
    	q.setLong(index++, o.getLenguajeId());
    	q.setString(index++, o.getNombre());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
     
     public void updateAplicacion(Aplicacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
        String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE SEGURIDAD_APLICACION SET NOMBRE=?, DESCRIPCION=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=?"+statusQuery
    			+ "WHERE APLICACION_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
        q.setString(index++, o.getNombre());
    	q.setString(index++, o.getDescripcion());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
        if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
        q.setLong(index++, o.getAplicacionId());
        
    	q.executeUpdate();
    	
    	query = "UPDATE SEGURIDAD_APLICACION_PARAMETRO SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE APLICACION_ID=? AND LENGUAJE_ID=" + o.getLenguajeId();
    	q = s.createSQLQuery(query);
        index = 0;
    	q.setString(index++, o.getNombre());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getAplicacionId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
     
    public void logicDelete(Modulo m){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE SEGURIDAD_APLICACION SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE APLICACION_ID=? ";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setInteger(index++, m.getEliminadoId());
    	q.setTimestamp(index++, m.getModificacionFecha());
    	q.setInteger(index++, m.getModificacionUsuario());
    	q.setLong(index++, m.getAplicacionId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    
    @SuppressWarnings("unchecked")
    public List<Aplicacion> getAplicacionesCombo(){
    	List<Aplicacion> aplicaciones = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append("SELECT A.APLICACION_ID, AP.NOMBRE ");
                query.append("FROM SEGURIDAD_APLICACION A ");
                query.append("INNER JOIN SEGURIDAD_APLICACION_PARAMETRO AP ON (A.APLICACION_ID=AP.APLICACION_ID) ");
                query.append("WHERE A.ELIMINADO_ID=").append(NOELIMINADO).append(" AND AP.LENGUAJE_ID=");
                query.append(LENGUAJE);
                query.append(" ORDER BY AP.NOMBRE ASC");
			List<Object[]> list = s.createSQLQuery(query.toString()).list();
			for(Object[] o: list){
	    		Aplicacion a = new Aplicacion();
	    		a.setAplicacionId(((BigDecimal)o[0]).longValue());
	    		a.setNombre((String)o[1]);
	    		aplicaciones.add(a);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return aplicaciones;
    }

}
