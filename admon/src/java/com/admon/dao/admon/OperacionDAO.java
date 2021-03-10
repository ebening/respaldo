package com.admon.dao.admon;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2x.StringUtils;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Aplicacion;
import com.admon.entity.admon.Modulo;
import com.admon.entity.admon.Operacion;
import java.util.ResourceBundle;

public class OperacionDAO extends GenericDAOImpl<Operacion, Long> {

    public ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");
    public final Integer NOELIMINADO = new Integer(codigos.getString("key_eliminado_noeliminado"));
    public final Integer LENGUAJE = new Integer(codigos.getString("key_tipo_lenguaje"));
    
    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<Operacion> getEntityClass() {
        return Operacion.class;
    }
    
    @SuppressWarnings("unchecked")
	public Operacion getOperacion(Long id, Integer lenguajeId){
    	Operacion operacion = new Operacion();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT SO.OPERACION_ID, OP.NOMBRE AS OPERACION, SO.MODULO_ID, SO.APLICACION_ID, SO.URL, SO.ORDEN, SO.DESCRIPCION, SO.NOMBRE_ACCION, SO.HTML_ID, "
				+ " SO.CREACION_FECHA, SO.CREACION_USUARIO, SO.MODIFICACION_FECHA, SO.MODIFICACION_USUARIO, SO.ESTATUS_ID, SO.ELIMINADO_ID, OP.LENGUAJE_ID "
				+ " FROM SEGURIDAD_OPERACION SO, SEGURIDAD_OPERACION_PARAMETROS OP"
                                + " WHERE SO.OPERACION_ID   =" + id
                                + " AND SO.OPERACION_ID     = OP.OPERACION_ID"
                                + " AND OP.LENGUAJE_ID      =" + lenguajeId).list();
		for(Object[] o: list){
			Integer index=0;
			operacion.setOperacionId(((BigDecimal)o[index++]).longValue());
                        operacion.setNombre((String)o[index++]);
			operacion.setModuloId(((BigDecimal)o[index++]).longValue());
			operacion.setAplicacionId(((BigDecimal)o[index++]).longValue());
			operacion.setUrl((String)o[index++]);
			operacion.setOrden(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			Object descipcion = o[index++];
                                if(descipcion!=null){
                                    operacion.setDescripcion((String)descipcion);
                                }
                        Object nombreAccion = o[index++];
                                if(nombreAccion!=null){
                                    operacion.setNombreAccion((String)nombreAccion);
                                }
                        Object htmliId = o[index++];
                                if(htmliId!=null){
                                    operacion.setHtmlId((String)htmliId);
                                }
                                
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				operacion.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				operacion.setCreacionFecha((Timestamp)date);
			}
			operacion.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				operacion.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				operacion.setModificacionFecha((Timestamp)date);
			}
                        Object modificacionUsuario = o[index++];
                                if(modificacionUsuario!=null){
                                    operacion.setModificacionUsuario(((BigDecimal)modificacionUsuario).intValue());
                                }
			operacion.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			operacion.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                        Object lenguaje = o[index++];
                                if(lenguaje!=null){
                                    operacion.setLenguajeId(((BigDecimal)lenguaje).intValue());
                                }

    		break;
    	}
		s.close();
    	return operacion;
    }

    @SuppressWarnings("unchecked")
    public List<Aplicacion> getAplicaciones(){
    	List<Aplicacion> aplicaciones = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT APLICACION_ID, NOMBRE FROM SEGURIDAD_APLICACION WHERE ELIMINADO_ID=0").list();
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

    @SuppressWarnings("unchecked")
    public List<Modulo> getModulos(Long aplicacionId){
    	List<Modulo> modulos = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT MP.MODULO_ID, MP.NOMBRE "+
					"FROM SEGURIDAD_MODULO M, SEGURIDAD_MODULO_PARAMETROS MP "+
					"WHERE M.MODULO_ID = MP.MODULO_ID AND LENGUAJE_ID= "+LENGUAJE+" "+
					"AND ELIMINADO_ID=0 AND M.APLICACION_ID="+aplicacionId).list();
			for(Object[] o: list){
				Modulo a = new Modulo();
	    		a.setModuloId(((BigDecimal)o[0]).longValue());
	    		a.setNombre((String)o[1]);
	    		modulos.add(a);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return modulos;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
    	StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_OPERACION SO WHERE SO.ELIMINADO_ID=").append(NOELIMINADO);
		query.append(getFilter(parametros));
		return query.toString();
    }
    
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if((Long)parametros.get("aplicacionId") != null){
            filter.append(" AND SO.APLICACION_ID = ").append((Long)parametros.get("aplicacionId"));
        }
        if((Long)parametros.get("moduloId") != null){
            filter.append(" AND SO.MODULO_ID = ").append((Long)parametros.get("moduloId"));
        }
        if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
            filter.append(" AND SO.OPERACION_ID IN (SELECT SOP.OPERACION_ID FROM SEGURIDAD_OPERACION_PARAMETROS SOP WHERE UPPER(SOP.NOMBRE) LIKE '%").append(((String)parametros.get("nombre")).toUpperCase()).append("%')");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
            filter.append(" AND UPPER(SO.DESCRIPCION) LIKE '%").append(((String)parametros.get("descripcion")).toUpperCase()).append("%'");
        }
        return filter.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
        Integer lenguajeId = (Integer) parametros.get("lenguajeId");
    	Integer indexInicio = (Integer) parametros.get("indexInicio");
        Integer indexFinal = (Integer) parametros.get("indexFinal");
        if(indexInicio==null) indexInicio=1;
        if(indexFinal==null) indexFinal=10;
		
    	String query = "SELECT A.APLICACION_ID, AP.NOMBRE AS APLICACION, M.MODULO_ID, MP.NOMBRE AS MODULO, SO.OPERACION_ID, OP.NOMBRE AS OPERACION, "+
                        "SO.DESCRIPCION, SO.MODIFICACION_FECHA, SO.MODIFICACION_USUARIO,  "+	
                        "(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=SO.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"SO.ELIMINADO_ID, SO.ESTATUS_ID "+ 
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_APLICACION_PARAMETRO AP, SEGURIDAD_MODULO M, SEGURIDAD_MODULO_PARAMETROS MP, SEGURIDAD_OPERACION SO, SEGURIDAD_OPERACION_PARAMETROS OP,(SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SO.ROWID" +
                                " AND A.APLICACION_ID    = AP.APLICACION_ID" +
                                " AND A.APLICACION_ID    = M.APLICACION_ID" +
                                " AND A.ELIMINADO_ID     =" + NOELIMINADO +
                                " AND AP.LENGUAJE_ID     = " + lenguajeId +
				" AND M.MODULO_ID        = MP.MODULO_ID" +
                                " AND M.MODULO_ID        = SO.MODULO_ID" +
                                " AND M.ELIMINADO_ID     =" + NOELIMINADO +
                                " AND MP.LENGUAJE_ID     = " + lenguajeId +
                                " AND SO.OPERACION_ID    = OP.OPERACION_ID" +
                                " AND OP.LENGUAJE_ID     = " + lenguajeId + 
                                getOrder(parametros) + "";
    	return query;
    }
    
    private String getOrder(Map<String, Object> parametros){
    	StringBuilder orderString = new StringBuilder();
    	orderString.append(" ORDER BY ");
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            if(parametros.get("order").equals("nombre")){
            	orderString.append(" OP.");
            } else if(parametros.get("order").equals("AP.NOMBRE")){
            	orderString.append(" ");
            } else if(parametros.get("order").equals("MP.NOMBRE")){
            	orderString.append(" ");
            } else {
            	orderString.append(" SO.");
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
		String query = "select count(1) from ("+ getQueryPrincipal(parametros)+")";
		Session s = getSessionFactory().openSession();
		count = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
		s.close();
		return count;
	}
    
    @SuppressWarnings("unchecked")
	public List<Operacion> getOperaciones(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Operacion> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
    		Integer index = 0;
    		Operacion o = new Operacion();
    		o.setAplicacionId(((BigDecimal)ob[index++]).longValue());
    		o.setAplicacion((String)ob[index++]);
    		o.setModuloId(((BigDecimal)ob[index++]).longValue());
    		o.setModulo((String)ob[index++]);
                o.setOperacionId(Long.valueOf(ob[index++].toString()));
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
    		o.setModificacionUsuarioStr((String)ob[index++]);
    		o.setEliminadoId(Integer.valueOf(ob[index++].toString()));
    		o.setEstatusId(Integer.valueOf(ob[index++].toString()));
    		list.add(o);
    	}
    	s.close();
    	return list;
    }
    
    public void saveOperacion(Operacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT OPERACION_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_OPERACION (OPERACION_ID, MODULO_ID, APLICACION_ID, URL, ORDEN, DESCRIPCION, NOMBRE_ACCION, HTML_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	Integer index = 0;
    	q = s.createSQLQuery(query);
    	q.setLong(index++, id);
    	q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setInteger(index++, o.getEstatusId());
    	q.setInteger(index++, o.getEliminadoId());
    	q.executeUpdate();
    	
    	query = "INSERT INTO SEGURIDAD_OPERACION_PARAMETROS (OPERACION_PARAMETRO_ID, OPERACION_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	(OPERACION_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
    	index = 0;
    	q = s.createSQLQuery(query);
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
    
    public void update(Operacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE SEGURIDAD_OPERACION SET MODULO_ID=?, APLICACION_ID=?, URL=?, ORDEN=?, DESCRIPCION=?, NOMBRE_ACCION=?, HTML_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "+statusQuery
    			+ "WHERE OPERACION_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	
    	query = "UPDATE SEGURIDAD_OPERACION_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE OPERACION_ID=? AND LENGUAJE_ID=" + o.getLenguajeId();
    	q = s.createSQLQuery(query);
    	index = 0;
    	q.setString(index++, o.getNombre());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public void logicDelete(Operacion o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE SEGURIDAD_OPERACION SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE OPERACION_ID=? ";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setInteger(index++, o.getEliminadoId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getOperacionId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    
    
     @SuppressWarnings("unchecked")
    public List<Operacion> getOperacionesCombo(Long moduloId){
    	List<Operacion> operaciones = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append("SELECT SO.OPERACION_ID, SOP.NOMBRE ");
                query.append("FROM SEGURIDAD_OPERACION SO ");
                query.append("INNER JOIN SEGURIDAD_OPERACION_PARAMETROS SOP ON (SO.OPERACION_ID = SOP.OPERACION_ID) ");
                query.append("WHERE SO.ELIMINADO_ID=").append(NOELIMINADO).append(" AND SOP.LENGUAJE_ID=").append(LENGUAJE);
                query.append(" AND SO.MODULO_ID=").append(moduloId);
                query.append(" ORDER BY SOP.NOMBRE ASC");
                List<Object[]> list = s.createSQLQuery(query.toString()).list();
			for(Object[] o: list){
			Operacion op = new Operacion();
	    		op.setOperacionId(((BigDecimal)o[0]).longValue());
	    		op.setNombre((String)o[1]);
	    		operaciones.add(op);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return operaciones;
    }
    
}
