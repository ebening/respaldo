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

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Modulo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import org.hibernate.tool.hbm2x.StringUtils;

public class ModuloDAO extends GenericDAOImpl<Modulo, Long> {

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
    protected Class<Modulo> getEntityClass() {
        return Modulo.class;
    }
    
    @SuppressWarnings("unchecked")
	public Modulo getModulo(Long id, Integer lenguajeId){
    	Modulo modulo = new Modulo();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT SM.MODULO_ID, SM.APLICACION_ID,SM.URL,SM.ORDEN,SM.DESCRIPCION,SM.NOMBRE_ACCION,SM.NOMBRE_OBJETO,SM.HTML_ID,"
				+ "  SM.CREACION_FECHA,SM.CREACION_USUARIO,SM.MODIFICACION_FECHA,SM.MODIFICACION_USUARIO,SM.ESTATUS_ID,SM.ELIMINADO_ID, SMP.LENGUAJE_ID, SMP.NOMBRE"
				+ " FROM SEGURIDAD_MODULO SM, SEGURIDAD_MODULO_PARAMETROS SMP WHERE SM.MODULO_ID=SMP.MODULO_ID AND SM.MODULO_ID="+id+" AND SMP.LENGUAJE_ID="+ lenguajeId).list();
		for(Object[] o: list){
                        Integer index=0;
			modulo.setModuloId(((BigDecimal)o[index++]).longValue());
			modulo.setAplicacionId(((BigDecimal)o[index++]).longValue());
			modulo.setUrl((String)o[index++]);
			modulo.setOrden(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                        Object descipcion = o[index++];
                                if(descipcion!=null){
                                    modulo.setDescripcion((String)descipcion);
                                }
                        Object nombreAccion = o[index++];
                                if(nombreAccion!=null){
                                    modulo.setNombreAccion((String)nombreAccion);
                                }
                        Object nombreObjeto = o[index++];
                                if(nombreObjeto!=null){
                                    modulo.setNombreObjeto((String)nombreObjeto);
                                }
                        Object htmliId = o[index++];
                                if(htmliId!=null){
                                    modulo.setHtmlId((String)htmliId);
                                }
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				modulo.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				modulo.setCreacionFecha((Timestamp)date);
			}
			modulo.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				modulo.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				modulo.setModificacionFecha((Timestamp)date);
			}
                        Object modificacionUsuario = o[index++];
                        if(modificacionUsuario!=null){
                            modulo.setModificacionUsuario(((BigDecimal)modificacionUsuario).intValue());
                        }
			
			modulo.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			modulo.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                        Object lenguaje = o[index++];
                        if(lenguaje!=null){
                            modulo.setLenguajeId(((BigDecimal)lenguaje).intValue());
                        }
                        
			modulo.setNombre((String)o[index++]);
    		break;
    	}
		s.close();
    	return modulo;
    }

    @SuppressWarnings("unchecked")
    public List<Modulo> getModulos(Long aplicacionId){
    	List<Modulo> modulos = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT MP.MODULO_ID, MP.NOMBRE "+
					"FROM SEGURIDAD_MODULO M, SEGURIDAD_MODULO_PARAMETROS MP "+
					"WHERE M.MODULO_ID = MP.MODULO_ID AND LENGUAJE_ID="+ LENGUAJE+
					"AND ELIMINADO_ID="+NOELIMINADO+" AND M.APLICACION_ID="+aplicacionId).list();
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
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_MODULO SM WHERE SM.ELIMINADO_ID = ").append(NOELIMINADO);
		query.append(getFilter(parametros));
		return query.toString();
    }
    
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if((Long)parametros.get("aplicacionId") != null){
            filter.append(" AND SM.APLICACION_ID = ").append((Long)parametros.get("aplicacionId"));
        }
        if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
            filter.append(" AND SM.MODULO_ID IN (SELECT SMP.MODULO_ID FROM SEGURIDAD_MODULO_PARAMETROS SMP WHERE UPPER(SMP.NOMBRE) LIKE '%").append(((String)parametros.get("nombre")).toUpperCase()).append("%')");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
            filter.append(" AND UPPER(SM.DESCRIPCION) LIKE '%").append(((String)parametros.get("descripcion")).toUpperCase()).append("%'");
        }
        return filter.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
        Integer lenguajeId = (Integer) parametros.get("lenguajeId");
    	Integer indexInicio = (Integer) parametros.get("indexInicio");
        Integer indexFinal = (Integer) parametros.get("indexFinal");
        if(indexInicio==null) indexInicio=1;
        if(indexFinal==null) indexFinal=10;

        String query = "SELECT A.APLICACION_ID, AP.NOMBRE AS APLICACION, M.MODULO_ID, MP.NOMBRE, "+
    			"M.DESCRIPCION, M.MODIFICACION_FECHA, M.MODIFICACION_USUARIO,  "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=M.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"M.ELIMINADO_ID, M.ESTATUS_ID " +
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_APLICACION_PARAMETRO AP, SEGURIDAD_MODULO M, SEGURIDAD_MODULO_PARAMETROS MP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = M.ROWID "+
				" AND A.APLICACION_ID = AP.APLICACION_ID AND A.APLICACION_ID=M.APLICACION_ID AND AP.LENGUAJE_ID=" + lenguajeId + " " +
                                " AND A.ELIMINADO_ID = "+ NOELIMINADO + "" +
				" AND M.MODULO_ID = MP.MODULO_ID " +
                                " AND MP.LENGUAJE_ID="+ lenguajeId + 
                                getOrder(parametros) + "";
    	return query;
    }
    
    private String getOrder(Map<String, Object> parametros){
    	StringBuilder orderString = new StringBuilder();
    	orderString.append(" ORDER BY ");
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            if(parametros.get("order").equals("nombre")){
            	orderString.append(" MP.");
            } else if(parametros.get("order").equals("NOMBRE")){
            	orderString.append(" A.");
            } else if(parametros.get("order").equals("MP.NOMBRE")){
            	orderString.append(" ");
            } else {
            	orderString.append(" M.");
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
	public List<Modulo> getModulos(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Modulo> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
            Integer index = 0;
            Modulo o = new Modulo();
    		o.setAplicacionId(((BigDecimal)ob[index++]).longValue());
    		o.setAplicacion((String)ob[index++]);
    		o.setModuloId(((BigDecimal)ob[index++]).longValue());
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
    
    public void saveModulo(Modulo o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
//    	try {
            String query = "SELECT MODULO_SEQ.NEXTVAL FROM DUAL";
            Query q = s.createSQLQuery(query);
            Long id = Long.valueOf(q.uniqueResult().toString());


            query = "INSERT INTO SEGURIDAD_MODULO (MODULO_ID, APLICACION_ID, URL, ORDEN, DESCRIPCION, NOMBRE_ACCION, NOMBRE_OBJETO, HTML_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            q = s.createSQLQuery(query);
            Integer index = 0;
            q.setLong(index++, id);
            q.setLong(index++, o.getAplicacionId());
            q.setString(index++, o.getUrl());
            q.setInteger(index++, o.getOrden());
            q.setString(index++, o.getDescripcion());
            q.setString(index++, o.getNombreAccion());
            q.setString(index++, o.getNombreObjeto());
            q.setString(index++, o.getHtmlId());
            q.setTimestamp(index++, o.getCreacionFecha());
            q.setInteger(index++, o.getCreacionUsuario());
            q.setTimestamp(index++, o.getModificacionFecha());
            q.setInteger(index++, o.getModificacionUsuario());
            q.setInteger(index++, o.getEstatusId());
            q.setInteger(index++, o.getEliminadoId());
            q.executeUpdate();

            query = "INSERT INTO SEGURIDAD_MODULO_PARAMETROS (MODULO_PARAMETRO_ID, MODULO_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
                            + "VALUES 	(MODULO_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
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
//        } catch (Exception e) {
//                System.out.println(e.getMessage());
//            } finally {
//                s.close();
//        }
    }
    
    public void updateModulo(Modulo o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
        String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE SEGURIDAD_MODULO SET APLICACION_ID=?, URL=?, ORDEN=?, DESCRIPCION=?, NOMBRE_ACCION=?, NOMBRE_OBJETO=?, HTML_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=?"+statusQuery
    			+ "WHERE MODULO_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getUrl());
    	q.setInteger(index++, o.getOrden());
    	q.setString(index++, o.getDescripcion());
    	q.setString(index++, o.getNombreAccion());
        q.setString(index++, o.getNombreObjeto());
    	q.setString(index++, o.getHtmlId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
        if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
        q.setLong(index++, o.getModuloId());
        
    	q.executeUpdate();
    	
    	query = "UPDATE SEGURIDAD_MODULO_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE MODULO_ID=? AND LENGUAJE_ID=" + o.getLenguajeId();
    	q = s.createSQLQuery(query);
        index = 0;
    	q.setString(index++, o.getNombre());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getModuloId());
    	q.executeUpdate();

    	t.commit();
    	s.close();
    }
    
    public void logicDelete(Modulo m){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE SEGURIDAD_MODULO SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE MODULO_ID=? ";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setInteger(index++, m.getEliminadoId());
    	q.setTimestamp(index++, m.getModificacionFecha());
    	q.setInteger(index++, m.getModificacionUsuario());
    	q.setLong(index++, m.getModuloId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Modulo> getModulosCombo(Long aplicacionId){
    	List<Modulo> modulos = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append("SELECT MP.MODULO_ID, MP.NOMBRE ");
                query.append("FROM SEGURIDAD_MODULO M ");
                query.append("INNER JOIN SEGURIDAD_MODULO_PARAMETROS MP ON (M.MODULO_ID=MP.MODULO_ID) ");
                query.append("WHERE M.ELIMINADO_ID=").append(NOELIMINADO).append(" AND MP.LENGUAJE_ID=").append(LENGUAJE);
                query.append(" AND M.APLICACION_ID=").append(aplicacionId);
                query.append(" ORDER BY MP.NOMBRE ASC");
                List<Object[]> list = s.createSQLQuery(query.toString()).list();
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

}

