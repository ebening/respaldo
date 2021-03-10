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
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoPK;
import com.admon.entity.admon.Funcionalidad;
import java.sql.Date;
import java.util.ResourceBundle;
import org.hibernate.tool.hbm2x.StringUtils;

public class FuncionalidadDAO extends GenericDAOImpl<Funcionalidad, Long> {
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
    protected Class<Funcionalidad> getEntityClass() {
        return Funcionalidad.class;
    }
    
    @SuppressWarnings("unchecked")
	public Funcionalidad getFuncionalidad(Long id, Integer lenguajeId){
    	Funcionalidad funcionalidad = new Funcionalidad();
    	Session s = getSessionFactory().openSession();
		List<Object[]> list = s.createSQLQuery("SELECT SF.FUNCIONALIDAD_ID, FP.NOMBRE AS FUNCIONALIDAD, SF.OPERACION_ID, SF.MODULO_ID, SF.APLICACION_ID, SF.DESCRIPCION, SF.METODO, SF.HTML_ID, SF.TIPO_FUNCIONALIDAD_ID,"
				+ " SF.CREACION_FECHA, SF.CREACION_USUARIO, SF.MODIFICACION_FECHA, SF.MODIFICACION_USUARIO, SF.ESTATUS_ID, SF.ELIMINADO_ID, FP.LENGUAJE_ID "
				+ " FROM SEGURIDAD_OPERA_FUNCIONALIDAD SF, SEGURIDAD_OPER_FUNC_PARAMETROS FP "
                                + " WHERE SF.FUNCIONALIDAD_ID   = " + id
                                + " AND SF.FUNCIONALIDAD_ID     = FP.FUNCIONALIDAD_ID"
                                + " AND FP.LENGUAJE_ID          =" + lenguajeId).list();
		for(Object[] o: list){
			Integer index=0;
			funcionalidad.setFuncionalidadId(((BigDecimal)o[index++]).longValue());
                        funcionalidad.setNombre((String)o[index++]);
                        funcionalidad.setOperacionId(((BigDecimal)o[index++]).longValue());
			funcionalidad.setModuloId(((BigDecimal)o[index++]).longValue());
			funcionalidad.setAplicacionId(((BigDecimal)o[index++]).longValue());
                        Object descipcion = o[index++];
                                if(descipcion!=null){
                                    funcionalidad.setDescripcion((String)descipcion);
                                }
                        Object metodo = o[index++];
                                if(metodo!=null){
                                    funcionalidad.setMetodo((String)metodo);
                                }
                        Object htmliId = o[index++];
                                if(htmliId!=null){
                                    funcionalidad.setHtmlId((String)htmliId);
                                }
                        funcionalidad.setCatalogoId(((BigDecimal)o[index++]).longValue());
			Object date = o[index++];
			if(date!=null && date instanceof Date){
				funcionalidad.setCreacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				funcionalidad.setCreacionFecha((Timestamp)date);
			}
			funcionalidad.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			date = o[index++];
			if(date!=null && date instanceof Date){
				funcionalidad.setModificacionFecha(new Timestamp(((Date)date).getTime()));
			}
			if(date!=null && date instanceof Timestamp){
				funcionalidad.setModificacionFecha((Timestamp)date);
			}
                        Object modificacionUsuario = o[index++];
                        if(modificacionUsuario!=null){
                            funcionalidad.setModificacionUsuario(((BigDecimal)modificacionUsuario).intValue());
                        }
			funcionalidad.setEstatusId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
			funcionalidad.setEliminadoId(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                        Object lenguaje = o[index++];
                        if(lenguaje!=null){
                            funcionalidad.setLenguajeId(((BigDecimal)lenguaje).intValue());
                        }
    		break;
    	}
		s.close();
    	return funcionalidad;
    }

    @SuppressWarnings("unchecked")
    public List<Catalogo> getTipos(String clave){
    	List<Catalogo> tipos = new ArrayList<>();
    	try{
    		Session s = getSessionFactory().openSession();
			List<Object[]> list = s.createSQLQuery("SELECT CP.CATALOGO_PARAMETRO_ID, CP.CLAVE "+
					"FROM CATALOGO C, CATALOGO_PARAMETRO CP " +
					"WHERE C.CATALOGO_ID    = CP.CATALOGO_ID" +
					" AND CP.ELIMINADO_ID    = " + NOELIMINADO + 
                                        " AND C.CLAVE = '"+clave+"'"  +
                                        " ORDER BY CP.CLAVE ASC").list();
			for(Object[] o: list){
				Catalogo c = new Catalogo();
                                CatalogoPK catalogoPK = new CatalogoPK();
                                catalogoPK.setCatalogoId(((BigDecimal)o[0]).intValue());
	    		c.setCatalogoPK(catalogoPK);
	    		c.setClave((String)o[1]);
	    		tipos.add(c);
	    	}
			s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return tipos;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
    	StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_OPERA_FUNCIONALIDAD SF WHERE SF.ELIMINADO_ID=").append(NOELIMINADO);
                query.append(getFilter(parametros));
		return query.toString();
    }
    
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if((Long)parametros.get("aplicacionId") != null){
            filter.append(" AND SF.APLICACION_ID = ").append((Long)parametros.get("aplicacionId"));
        }
        if((Long)parametros.get("moduloId") != null){
            filter.append(" AND SF.MODULO_ID = ").append((Long)parametros.get("moduloId"));
        }
        if((Long)parametros.get("operacionId") != null){
            filter.append(" AND SF.OPERACION_ID = ").append((Long)parametros.get("operacionId"));
        }
        if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
            filter.append(" AND SF.FUNCIONALIDAD_ID IN (SELECT SFP.FUNCIONALIDAD_ID FROM SEGURIDAD_OPER_FUNC_PARAMETROS SFP WHERE UPPER(SFP.NOMBRE) LIKE '%").append(((String)parametros.get("nombre")).toUpperCase()).append("%')");
        }
        if((Long)parametros.get("catalogoId") != null){
            filter.append(" AND SF.TIPO_FUNCIONALIDAD_ID = ").append((Long)parametros.get("catalogoId"));
        }
        if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
            filter.append(" AND UPPER(SF.DESCRIPCION) LIKE '%").append(((String)parametros.get("descripcion")).toUpperCase()).append("%'");
        }
        return filter.toString();
    }
        
    private String getQuery(Map<String, Object> parametros){
        Integer lenguajeId = (Integer) parametros.get("lenguajeId");
    	Integer indexInicio = (Integer) parametros.get("indexInicio");
        Integer indexFinal = (Integer) parametros.get("indexFinal");
        if(indexInicio==null) indexInicio=1;
        if(indexFinal==null) indexFinal=10;
		
    	String query = "SELECT A.APLICACION_ID, AP.NOMBRE AS APLICACION, M.MODULO_ID, MP.NOMBRE AS MODULO, SO.OPERACION_ID, OP.NOMBRE AS OPERACION, SF.FUNCIONALIDAD_ID, FP.NOMBRE AS FUNCIONALIDAD, SF.TIPO_FUNCIONALIDAD_ID,"+
    			"(SELECT CLAVE FROM CATALOGO_PARAMETRO CP WHERE SF.TIPO_FUNCIONALIDAD_ID=CP.CATALOGO_PARAMETRO_ID) AS TIPO, "+
                        "SF.DESCRIPCION, SF.METODO, SF.MODIFICACION_FECHA, SF.MODIFICACION_USUARIO,  "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=SF.MODIFICACION_USUARIO) AS USUARIO, "+ 
    			"SF.ELIMINADO_ID, SF.ESTATUS_ID "+ 
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_APLICACION_PARAMETRO AP, SEGURIDAD_MODULO M, SEGURIDAD_MODULO_PARAMETROS MP, SEGURIDAD_OPERACION SO, SEGURIDAD_OPERACION_PARAMETROS OP, SEGURIDAD_OPERA_FUNCIONALIDAD SF, SEGURIDAD_OPER_FUNC_PARAMETROS FP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SF.ROWID "+
                                " AND A.APLICACION_ID    = AP.APLICACION_ID" +
                                " AND A.APLICACION_ID    = M.APLICACION_ID" +
                                " AND A.ELIMINADO_ID     = " + NOELIMINADO +
                                " AND AP.LENGUAJE_ID     = " + lenguajeId +
				" AND M.MODULO_ID        = MP.MODULO_ID" +
                                " AND M.MODULO_ID        = SO.MODULO_ID" +
                                " AND M.ELIMINADO_ID     = " + NOELIMINADO +
                                " AND MP.LENGUAJE_ID     = " + lenguajeId +
                                " AND SO.OPERACION_ID    = OP.OPERACION_ID" +
                                " AND OP.LENGUAJE_ID     = " + lenguajeId + 
                                " AND SO.ELIMINADO_ID    = " + NOELIMINADO +
                                " AND SF.FUNCIONALIDAD_ID= FP.FUNCIONALIDAD_ID" +
                                " AND SF.MODULO_ID       = M.MODULO_ID" +
                                " AND SF.OPERACION_ID    = SO.OPERACION_ID" +
                                " AND FP.LENGUAJE_ID     = " + lenguajeId +
                                getOrder(parametros) + "";

    	return query;
    }
    
    private String getOrder(Map<String, Object> parametros){
        StringBuilder orderString = new StringBuilder();
    	orderString.append(" ORDER BY ");
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            if(parametros.get("order").equals("nombre")){
            	orderString.append(" FP.");
            } else if(parametros.get("order").equals("AP.NOMBRE")){
            	orderString.append(" ");
            } else if(parametros.get("order").equals("MP.NOMBRE")){
            	orderString.append(" ");
            }  else if(parametros.get("order").equals("OP.NOMBRE")){
                orderString.append(" ");
            } else {
            	orderString.append(" SF.");
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
	public List<Funcionalidad> getFuncionalidades(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Funcionalidad> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] ob: l){
    		Integer index = 0;
    		Funcionalidad o = new Funcionalidad();
    		o.setAplicacionId(((BigDecimal)ob[index++]).longValue());
    		o.setAplicacion((String)ob[index++]);
    		o.setModuloId(((BigDecimal)ob[index++]).longValue());
    		o.setModulo((String)ob[index++]);
                o.setOperacionId(((BigDecimal)ob[index++]).longValue());
    		o.setOperacion((String)ob[index++]);
                o.setFuncionalidadId(Long.valueOf(ob[index++].toString()));
    		o.setNombre((String)ob[index++]);
                o.setTipoId(((BigDecimal)ob[index++]).longValue());
    		o.setTipo((String)ob[index++]);
                Object descipcion = ob[index++];
                if(descipcion!=null){
                    o.setDescripcion((String)descipcion);
                }
                Object metodo = ob[index++];
                        if(metodo!=null){
                            o.setMetodo((String)metodo);
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
    
    public void saveFuncionalidad(Funcionalidad o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	
    	String query = "SELECT FUNCIONALIDAD_SEQ.NEXTVAL FROM DUAL";
    	Query q = s.createSQLQuery(query);
    	Long id = Long.valueOf(q.uniqueResult().toString());
    	
    	
    	query = "INSERT INTO SEGURIDAD_OPERA_FUNCIONALIDAD (FUNCIONALIDAD_ID, OPERACION_ID, MODULO_ID, APLICACION_ID, DESCRIPCION, METODO, HTML_ID, TIPO_FUNCIONALIDAD_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO, ESTATUS_ID, ELIMINADO_ID) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	Integer index = 0;
    	q = s.createSQLQuery(query);
    	q.setLong(index++, id);
        q.setLong(index++, o.getOperacionId());
    	q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getDescripcion());
        q.setString(index++, o.getMetodo());
    	q.setString(index++, o.getHtmlId());
        q.setLong(index++, o.getCatalogoId());
    	q.setTimestamp(index++, o.getCreacionFecha());
    	q.setInteger(index++, o.getCreacionUsuario());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setInteger(index++, o.getEstatusId());
    	q.setInteger(index++, o.getEliminadoId());
    	q.executeUpdate();
    	
    	query = "INSERT INTO SEGURIDAD_OPER_FUNC_PARAMETROS (OPER_FUNC_PARAMETRO_ID, FUNCIONALIDAD_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	(FUNCIONALIDAD_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
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
    
    public void update(Funcionalidad o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String statusQuery="";
    	if(o.getEstatusId()!=null){
    		statusQuery = ", ESTATUS_ID=? ";
    	}
    	String query = "UPDATE SEGURIDAD_OPERA_FUNCIONALIDAD SET OPERACION_ID = ?, MODULO_ID=?, APLICACION_ID=?, DESCRIPCION=?, METODO=?, HTML_ID=?, TIPO_FUNCIONALIDAD_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "+statusQuery
    			+ "WHERE FUNCIONALIDAD_ID=?";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setLong(index++, o.getOperacionId());
        q.setLong(index++, o.getModuloId());
    	q.setLong(index++, o.getAplicacionId());
    	q.setString(index++, o.getDescripcion());
        q.setString(index++, o.getMetodo());
    	q.setString(index++, o.getHtmlId());
        q.setLong(index++, o.getCatalogoId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	if(o.getEstatusId()!=null) q.setInteger(index++, o.getEstatusId());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	
    	query = "UPDATE SEGURIDAD_OPER_FUNC_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ " WHERE FUNCIONALIDAD_ID=? AND LENGUAJE_ID=" + o.getLenguajeId();
    	q = s.createSQLQuery(query);
    	index = 0;
    	q.setString(index++, o.getNombre());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public void logicDelete(Funcionalidad o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "UPDATE SEGURIDAD_OPERA_FUNCIONALIDAD SET ELIMINADO_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE FUNCIONALIDAD_ID=? ";
    	Integer index = 0;
    	Query q = s.createSQLQuery(query);
    	q.setInteger(index++, o.getEliminadoId());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
    	q.setLong(index++, o.getFuncionalidadId());
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    public boolean existFuncionalidad(Funcionalidad funcionalidad) {
    	StringBuilder query = new StringBuilder();
        query.append(" SELECT COUNT(1) FROM SEGURIDAD_OPERA_FUNCIONALIDAD SOF ");
        query.append(" INNER JOIN SEGURIDAD_OPER_FUNC_PARAMETROS SOFP  ON (SOF.FUNCIONALIDAD_ID = SOFP.FUNCIONALIDAD_ID)");
        /*query.append(" WHERE SOF.APLICACION_ID = ").append(funcionalidad.getAplicacionId());
        query.append(" AND SOF.MODULO_ID = ").append(funcionalidad.getModuloId());
        query.append(" AND SOF.OPERACION_ID = ").append(funcionalidad.getOperacionId());
        query.append(" AND SOF.TIPO_FUNCIONALIDAD_ID = ").append(funcionalidad.getCatalogoId());
        query.append(" AND SOF.FUNCIONALIDAD_ID = ").append(funcionalidad.getFuncionalidadId());*/
        query.append(" WHERE SOFP.NOMBRE = '").append(funcionalidad.getNombre()).append("'");
        query.append(" AND SOF.ELIMINADO_ID = '").append(NOELIMINADO).append("'");
        /*if(funcionalidad.getDescripcion()!=null){
            query.append(" AND SOF.DESCRIPCION = '").append(funcionalidad.getDescripcion()).append("'");
        }
        if(funcionalidad.getMetodo()!=null){
            query.append(" AND SOF.METODO = '").append(funcionalidad.getMetodo()).append("'");
        }
        if(funcionalidad.getHtmlId()!=null){
            query.append(" AND SOF.HTML_ID = '").append(funcionalidad.getHtmlId()).append("'");
        }*/
        
        Session s = null;
        Integer count = -1;
        try{
            s = getSessionFactory().openSession();
            count = Integer.valueOf(s.createSQLQuery(query.toString()).uniqueResult().toString());
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
        if(count>0)
            return true;//si existe
        else
            return false;//no existe
    }
}
