/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.SeguridadPerfil;
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



public class SeguridadPerfilDAO extends GenericDAOImpl <SeguridadPerfil,Integer>{
       /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<SeguridadPerfil> getEntityClass() {
        return SeguridadPerfil.class;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
                Integer eliminadoId = (Integer) parametros.get("eliminadoId");
                StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_PERFIL P WHERE P.ELIMINADO_ID="+eliminadoId);
                query.append(getFilter(parametros));
		return query.toString();
    }
    
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if(StringUtils.isNotEmpty((String)parametros.get("aplicacionId"))){
              filter.append(" AND P.APLICACION_ID LIKE '%").append(Integer.parseInt(parametros.get("aplicacionId").toString())).append("%'");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
            filter.append(" AND P.PERFIL_ID IN (SELECT SPP.PERFIL_ID FROM SEGURIDAD_PERFIL_PARAMETROS SPP WHERE UPPER(SPP.NOMBRE) LIKE '%").append(((String)parametros.get("nombre")).toUpperCase()).append("%')");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
            filter.append(" AND UPPER(P.DESCRIPCION) LIKE '%").append(((String)parametros.get("descripcion")).toUpperCase()).append("%'");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("modificacionUsuario"))){
                    filter.append(" AND P.MODIFICACION_USUARIO LIKE '%").append(Integer.parseInt(parametros.get("modificacionUsuario").toString())).append("%'");
            }
        return filter.toString();
    }

    
    private String getQuery(Map<String, Object> parametros){
            Integer lenguajeId = (Integer) parametros.get("lenguajeId");
            Integer indexInicio = (Integer) parametros.get("indexInicio");
            Integer indexFinal = (Integer) parametros.get("indexFinal");
            Integer eliminadoId = (Integer) parametros.get("eliminadoId");
            if(indexInicio==null) indexInicio=1;
            if(indexFinal==null) indexFinal=10;
		
            String query = "SELECT P.PERFIL_ID,A.APLICACION_ID, "+
                        "(SELECT NOMBRE FROM SEGURIDAD_APLICACION_PARAMETRO AP WHERE A.APLICACION_ID=AP.APLICACION_ID AND AP.LENGUAJE_ID = "+lenguajeId+") AS APLICACION, "+ 
                        "PP.NOMBRE AS NOMBRE,"+
    			"P.DESCRIPCION, P.MODIFICACION_FECHA, P.CREACION_FECHA,P.CREACION_USUARIO, "+
                        "(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=P.MODIFICACION_USUARIO) AS MODIFICACION_USUARIO, "+
    			"P.ELIMINADO_ID, P.ESTATUS_ID " +
    			"FROM SEGURIDAD_APLICACION A ,SEGURIDAD_PERFIL P,SEGURIDAD_PERFIL_PARAMETROS PP,(SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = P.ROWID "+
                        " AND A.APLICACION_ID = P.APLICACION_ID AND A.ELIMINADO_ID= " + eliminadoId +
                        " AND P.PERFIL_ID = PP.PERFIL_ID AND PP.LENGUAJE_ID= " + lenguajeId +
                        getOrder(parametros) + "";
    	return query;
    }
    
    private String getOrder(Map<String, Object> parametros){
    	StringBuilder orderString = new StringBuilder();
    	orderString.append(" ORDER BY ");
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            if(parametros.get("order").equals("nombre")){
            	orderString.append(" PP.");
            } else {
            	orderString.append(" P.");
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
         StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(1) FROM SEGURIDAD_PERFIL SP ");
        query.append("JOIN SEGURIDAD_PERFIL_PARAMETROS SPP ON (SP.PERFIL_ID = SPP.PERFIL_ID) ");
        query.append("JOIN SEGURIDAD_APLICACION SA ON (SP.APLICACION_ID = SA.APLICACION_ID) ");
        query.append("WHERE SA.ELIMINADO_ID =").append(Integer.parseInt(parametros.get("eliminadoId").toString()));
        query.append(" AND SP.ELIMINADO_ID=").append(Integer.parseInt(parametros.get("eliminadoId").toString()));
        query.append(" AND SPP.LENGUAJE_ID=").append(Integer.parseInt(parametros.get("lenguajeId").toString()));
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            count = Integer.valueOf(s.createSQLQuery(query.toString()).uniqueResult().toString());
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
        return count;
    }
    
    @SuppressWarnings("unchecked")
    public List<SeguridadPerfil> getPerfiles(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<SeguridadPerfil> list = new ArrayList<>();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(query).list();
            for(Object[] ob: l){
                Integer index = 0;
                SeguridadPerfil p = new SeguridadPerfil();
                p.setSeguridadPerfilId(((BigDecimal)ob[index++]).intValue());
                p.setAplicacionId(((BigDecimal)ob[index++]).intValue());
                p.setAplicacion(((String)ob[index++]));
                p.setNombre(((String)ob[index++]));
                p.setDescripcion(((String)ob[index++]));
                Object date = ob[index++];
                if(date!=null && date instanceof Date){
                        p.setModificacionFecha(new Timestamp(((Date)date).getTime()));
                }
                date = ob[index++];
                if(date!=null && date instanceof Date){
                        p.setCreacionFecha(new Timestamp(((Date)date).getTime()));
                }
                p.setCreacionUsuario(((BigDecimal)ob[index++]).intValue());
               /// o.SE(((String)ob[index++]));
                p.setUsuarioModificacion(((String)ob[index++]));
                p.setEliminadoId(((BigDecimal)ob[index++]).intValue());
                p.setEstatusId(((BigDecimal)ob[index++]).intValue());
                list.add(p);
            }
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    	return list;
    }
    public void saveParametros(SeguridadPerfil p,Integer lenguajeId){
        Session s = null;
    	try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            String query = "INSERT INTO SEGURIDAD_PERFIL_PARAMETROS (PERFIL_PARAMETRO_ID,PERFIL_ID,LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
                            + "VALUES 	(PERFIL_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
            Query q = s.createSQLQuery(query);
            Integer index = 0;
            q.setLong(index++, p.getSeguridadPerfilId());
            q.setLong(index++, new Long(lenguajeId));
            q.setString(index++, p.getNombre());
            q.setTimestamp(index++, p.getCreacionFecha());
            q.setInteger(index++, p.getCreacionUsuario());
            q.setTimestamp(index++, p.getModificacionFecha());
            q.setInteger(index++, p.getModificacionUsuario());
            q.executeUpdate();
            t.commit();
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    }
        
   @SuppressWarnings("unchecked")
    public List<SeguridadPerfil> getPerfilesCombo(Map<String, Object> parametros){
        Integer eliminadoId = (Integer) parametros.get("eliminadoId");
        Integer lenguajeId = (Integer) parametros.get("lenguajeId");
        Integer aplicacionId = (Integer) parametros.get("aplicacionId");
    	List<SeguridadPerfil> perfiles = new ArrayList<>();
        Session s = null;
    	try{
    		s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append("SELECT P.PERFIL_ID, PP.NOMBRE ");
                query.append("FROM SEGURIDAD_PERFIL P ");
                query.append("INNER JOIN SEGURIDAD_PERFIL_PARAMETROS PP ON (P.PERFIL_ID=PP.PERFIL_ID) ");
                query.append("WHERE P.ELIMINADO_ID=").append(eliminadoId).append(" AND PP.LENGUAJE_ID=");
                query.append(lenguajeId).append(" AND P.APLICACION_ID= ").append(aplicacionId);
			List<Object[]> list = s.createSQLQuery(query.toString()).list();
			for(Object[] o: list){
	    		SeguridadPerfil p = new SeguridadPerfil();
	    		p.setSeguridadPerfilId(((BigDecimal)o[0]).intValue());
	    		p.setNombre((String)o[1]);
	    		perfiles.add(p);
	    	}
			s.close();
    	}catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    	return perfiles;
    }
    
    public void updatePerfilParametro(SeguridadPerfil r,Integer lenguajeId){
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            String query = "UPDATE SEGURIDAD_PERFIL_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
                            + " WHERE PERFIL_ID=? AND LENGUAJE_ID=" + lenguajeId;
            Query q = s.createSQLQuery(query);
            Integer index = 0;
            q.setString(index++, r.getNombre());
            q.setTimestamp(index++, r.getModificacionFecha());
            q.setInteger(index++, r.getModificacionUsuario());
            q.setLong(index++, r.getSeguridadPerfilId());
            q.executeUpdate();

            t.commit();
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    }

}
