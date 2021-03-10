package com.admon.dao.admon;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2x.StringUtils;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Catalogo;
import com.admon.entity.admon.CatalogoPK;

public class CatalogoDAO extends GenericDAOImpl<Catalogo, CatalogoPK> {

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
	protected Class<Catalogo> getEntityClass() {
		return Catalogo.class;
	}
	
	public void delete(Catalogo c){
		String query = "UPDATE CATALOGO SET ELIMINADO_ID = ?, MODIFICACION_FECHA = ?, MODIFICACION_USUARIO = ? WHERE CATALOGO_ID = ? AND ORGANIZACION_ID = ?";
		Session s = null;
		try{
			s = getSessionFactory().openSession();
	    	Transaction t = s.beginTransaction();
	    	SQLQuery q = s.createSQLQuery(query);
	    	Integer index = 0;
	    	q.setInteger(index++, c.getEliminadoId());
	    	q.setTimestamp(index++, c.getModificacionFecha());
	    	q.setInteger(index++, c.getModificacionUsuario());
	    	q.setInteger(index++, c.getCatalogoPK().getCatalogoId());
	    	q.setInteger(index++, c.getCatalogoPK().getOrganizacionId());
	    	q.executeUpdate();
	    	t.commit();
		}finally{
			if(s!=null)
				s.close();
		}
	}

	private String getQueryPrincipal(Map<String, Object> parametros){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM CATALOGO C WHERE C.ELIMINADO_ID=").append(NOELIMINADO);
		query.append(getFilter(parametros));
		return query.toString();
	}

	private String getFilter(Map<String, Object> parametros){
		StringBuilder filter = new StringBuilder();
		if(parametros.get("nombre") != null){
			filter.append(" AND LOWER(C.NOMBRE) LIKE '%").append(parametros.get("nombre").toString().toLowerCase()).append("%'");
		}
		if(parametros.get("clave") != null){
			filter.append(" AND LOWER(C.CLAVE) LIKE '%").append(parametros.get("clave").toString().toLowerCase()).append("%'");
		}
		if(parametros.get("estatusId") != null){
			filter.append(" AND C.ESTATUS_ID = ").append(parametros.get("estatusId"));
		}
		if(parametros.get("modificacionFechaInicial") != null){
			filter.append(" AND C.MODIFICACION_FECHA >= TO_DATE('").append(parametros.get("modificacionFechaInicial")).append("', 'DD/MM/YYYY')");
		}
		if(parametros.get("modificacionFechaFinal") != null){
			filter.append(" AND C.MODIFICACION_FECHA <= TO_DATE('").append(parametros.get("modificacionFechaFinal")).append("', 'DD/MM/YYYY')");
		}
		if(parametros.get("catalogoId") != null){
			filter.append(" AND C.CATALOGO_ID = ").append(parametros.get("catalogoId"));
		}
		if(parametros.get("organizacionId") != null){
			filter.append(" AND C.ORGANIZACION_ID = ").append(parametros.get("organizacionId"));
		}
		return filter.toString();
	}

	private String getQuery(Map<String, Object> parametros){
		Integer indexInicio = (Integer) parametros.get("indexInicio");
		Integer indexFinal = (Integer) parametros.get("indexFinal");
		if(indexInicio==null) indexInicio=1;
		if(indexFinal==null) indexFinal=10;

		String query = "SELECT C.CATALOGO_ID, C.ORGANIZACION_ID, C.NOMBRE, C.CLAVE, C.SELECCIONA_COLOR, C.DESCRIPCION, C.CATALOGO_PADRE_ID, C.ES_DEPENDIENTE, C.PARAMETRO_DEPENDIENTE_ID, C.GENERICO, C.ESTATUS_ID, "+
				"C.ELIMINADO_ID, C.CREACION_FECHA, C.CREACION_USUARIO, C.MODIFICACION_FECHA, C.MODIFICACION_USUARIO, U.USUARIO, O.NOMBRE AS ORGANIZACION "+ 
				"FROM CATALOGO C, ORGANIZACION O, USUARIO U, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = C.ROWID" +
				" AND O.ORGANIZACION_ID = C.ORGANIZACION_ID "
				+ "AND U.USUARIO_ID(+)=C.MODIFICACION_USUARIO"
				+getOrder(parametros) + ""
				;
		return query;
	}

	private String getOrder(Map<String, Object> parametros){
		StringBuilder orderString = new StringBuilder();
		orderString.append(" ORDER BY ");
		if(StringUtils.isNotEmpty((String)parametros.get("order"))){
			if(parametros.get("order").equals("modificacionFecha")){
				orderString.append("c.MODIFICACION_FECHA");
			}else if(parametros.get("order").equals("modificacionUsuario")){
				orderString.append("U.USUARIO");
			}else{
				orderString.append(parametros.get("order"));
			}
		}else{
			return "";
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
		Session s = null;
		try{
			s = getSessionFactory().openSession();
			count = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
		}finally{
			if(s!=null)
				s.close();
		}
		
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Catalogo> getCatalogos(Map<String, Object> parametros) {
		String query = getQuery(parametros);
		List<Catalogo> list = new ArrayList<>();
		Session s = null;
		try{
			s = getSessionFactory().openSession();
			List<Object[]> l = s.createSQLQuery(query).list();
			for(Object[] ob: l){
				Integer index = 0;
				Catalogo o = new Catalogo();
				CatalogoPK pk = new CatalogoPK();
				pk.setCatalogoId(((BigDecimal)ob[index++]).intValue());
				pk.setOrganizacionId(((BigDecimal)ob[index++]).intValue());
				o.setCatalogoPK(pk);
				o.setNombre((String)ob[index++]);
				o.setClave((String)ob[index++]);
                                BigDecimal color = (BigDecimal)ob[index++];
				o.setSeleccionaColor(color!=null?color.intValue():null);
				o.setDescripcion((String)ob[index++]);
				
				BigDecimal catalogoPadreId = (BigDecimal)ob[index++];
				o.setCatalogoPadreId(catalogoPadreId!=null?catalogoPadreId.intValue():null);
				BigDecimal esDependiente = (BigDecimal)ob[index++];
				o.setEsDependiente(esDependiente!=null?(esDependiente.intValue()==1?true:false):null);
				BigDecimal parametroDependienteId = (BigDecimal)ob[index++];
				o.setParametroDependienteId(parametroDependienteId!=null?parametroDependienteId.intValue():null);
				BigDecimal esGenerico = (BigDecimal)ob[index++];
				o.setGenerico(esGenerico!=null?(esGenerico.intValue()==1?true:false):null);
				o.setEstatusId(((BigDecimal)ob[index++]).intValue());
				o.setEliminadoId(((BigDecimal)ob[index++]).intValue());
	
				Object date = ob[index++];
				if(date!=null && date instanceof Date){
					o.setCreacionFecha(new Timestamp(((Date)date).getTime()));
				}
				if(date!=null && date instanceof Timestamp){
					o.setCreacionFecha((Timestamp)date);
				}
				Object usuarioId = ob[index++];
				if(usuarioId!=null){
					o.setCreacionUsuario(((BigDecimal)usuarioId).intValue());
				}
				date = ob[index++];
				if(date!=null && date instanceof Date){
					o.setModificacionFecha(new Timestamp(((Date)date).getTime()));
				}
				if(date!=null && date instanceof Timestamp){
					o.setModificacionFecha((Timestamp)date);
				}
				usuarioId = ob[index++];
				if(usuarioId!=null){
					o.setModificacionUsuario(((BigDecimal)usuarioId).intValue());
				}
				
				o.setUsuarioModificacion((String)ob[index++]);
				o.setOrganizacionNombre((String)ob[index++]);
				list.add(o);
			}
		}finally{
			if(s!=null)
				s.close();
		}
		return list;
	}
	
	public int getCatalogoNextKey(){
        Integer sq = 0;
        String query = "select CATALOGO_SEQ.nextval from dual";
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            sq = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
        }catch ( Exception e ){
            e.printStackTrace();
        }finally{
        	if(s!=null)
        		s.close();
        }
        return sq;
    }
	
	@SuppressWarnings("unchecked")
	public List<Catalogo> getCatalogosForCombo(){
		List<Catalogo> list = new ArrayList<>();
		String query = "SELECT CATALOGO_ID, NOMBRE FROM CATALOGO WHERE ELIMINADO_ID="+NOELIMINADO+" ORDER BY NOMBRE";
		Session s = null;
		try{
			s = getSessionFactory().openSession();
			List<Object[]> l = s.createSQLQuery(query).list();
			for(Object[] ob: l){
				Integer index = 0;
				Catalogo o = new Catalogo();
				o.setCatalogoPK(new CatalogoPK());
				o.getCatalogoPK().setCatalogoId(((BigDecimal)ob[index++]).intValue());
				o.setNombre((String)ob[index++]);
				list.add(o);
			}
		}finally{
			if(s!=null)
				s.close();
		}
		return list;
	}
	
	public Timestamp getFechaCreacion(Integer catalogoId){
        String query = "select CREACION_FECHA from CATALOGO where CATALOGO_ID="+catalogoId;
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Object o = s.createSQLQuery(query).uniqueResult();
            if(o!=null){
            	if(o instanceof Timestamp){
            		return (Timestamp)o;
            	}else if(o instanceof Date){
            		return new Timestamp(((Date)o).getTime());
            	}
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }finally {
        	if(s!=null)
                s.close();
		}
        return null;
    }
	
	public Integer getCreacionUsuario(Integer catalogoId){
        String query = "select CREACION_USUARIO from CATALOGO where CATALOGO_ID="+catalogoId;
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Object o = s.createSQLQuery(query).uniqueResult();
            if(o!=null){
            	return Integer.valueOf(o.toString());
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