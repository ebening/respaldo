package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.PerfilAplicacion;
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

public class PerfilAplicacionDAO extends GenericDAOImpl<PerfilAplicacion, Long> {
    
     public ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");
     public final Integer NOELIMINADO = new Integer(codigos.getString("key_eliminado_noeliminado"));
     public final String INSTANCIA = codigos.getString("key_instancia_bd");
     public final Integer LENGUAJE = new Integer(codigos.getString("key_tipo_lenguaje"));

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<PerfilAplicacion> getEntityClass() {
        return PerfilAplicacion.class;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
                StringBuilder query = new StringBuilder();
                
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_USUARIO_PERFIL UP WHERE UP.USUARIO_ID = ");
                query.append((Integer)parametros.get("usuarioId"));
                query.append("AND UP.ORGANIZACION_ID = ");
                query.append((Integer)parametros.get("organizacionId"));

                StringBuilder filter = new StringBuilder();
		if((Integer)parametros.get("aplicacionId")!=null){
			filter.append(" AND UP.APLICACION_ID=").append((Integer)parametros.get("aplicacionId"));
		}
               if((Integer)parametros.get("perfilId")!=null){
			filter.append(" AND UP.PERFIL_ID='").append((Integer)parametros.get("perfilId")).append("'");
		}
                query.append(filter);
		query.append(" ORDER BY UP.MODIFICACION_FECHA DESC");
		return query.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
            Integer indexInicio = (Integer) parametros.get("indexInicio");
            Integer indexFinal = (Integer) parametros.get("indexFinal");
            if(indexInicio==null) indexInicio=1;
            if(indexFinal==null) indexFinal=10;
            String query = "SELECT UP.USUARIO_ID, "+
    			//"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=UP.USUARIO_ID) AS USUARIO, "+ 
                        "A.APLICACION_ID, AP.NOMBRE AS APLICACION,P.PERFIL_ID, SPP.NOMBRE AS PERFIL, P.DESCRIPCION,"+
    			"UP.MODIFICACION_FECHA, UP.MODIFICACION_USUARIO "+ 
                        //"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=UP.MODIFICACION_USUARIO) AS USUARIO_MOD "+ 
    			"FROM SEGURIDAD_APLICACION A, SEGURIDAD_APLICACION_PARAMETRO AP, SEGURIDAD_PERFIL P, SEGURIDAD_PERFIL_PARAMETROS SPP, SEGURIDAD_USUARIO_PERFIL UP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = UP.ROWID "+
                                " AND A.APLICACION_ID    = AP.APLICACION_ID" +
                                " AND AP.LENGUAJE_ID     = " + LENGUAJE +
				                " AND P.PERFIL_ID        = SPP.PERFIL_ID" +
                                " AND SPP.LENGUAJE_ID    = " + LENGUAJE +
                                " AND UP.PERFIL_ID = P.PERFIL_ID"+
                                " AND UP.APLICACION_ID = A.APLICACION_ID";

            return query;
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
    public List<PerfilAplicacion> getPerfilAplicaciones(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<PerfilAplicacion> list = new ArrayList<>();
    	  Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(query).list();
            for(Object[] o: l){
                        PerfilAplicacion perfilAplicacion = new PerfilAplicacion();
                        Integer index=0;
                        perfilAplicacion.setUsuarioId(((BigDecimal)o[index++]).intValue());
                        //perfilAplicacion.setUsuario((String)o[index++]);
                        perfilAplicacion.setAplicacionId(((BigDecimal)o[index++]).intValue());
                        perfilAplicacion.setAplicacion((String)o[index++]);
                        perfilAplicacion.setSeguridadPerfilId(((BigDecimal)o[index++]).intValue());
                        perfilAplicacion.setSeguridadPerfil((String)o[index++]);
                        perfilAplicacion.setDescripcion((String)o[index++]);
                        Object date = o[index++];
                        if(date!=null && date instanceof Date){
                                perfilAplicacion.setModificacionFecha(new Timestamp(((Date)date).getTime()));
                        }
                        if(date!=null && date instanceof Timestamp){
                                perfilAplicacion.setModificacionFecha((Timestamp)date);
                        }
                        perfilAplicacion.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                        //perfilAplicacion.setUsuarioModificacion((String)o[index++]);
                        list.add(perfilAplicacion);
            }
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    	return list;
    }
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if((Integer)parametros.get("organizacionId")!=null){
                filter.append(" AND UP.ORGANIZACION_ID=").append((Integer)parametros.get("organizacionId"));
        }
        if((Integer)parametros.get("aplicacionId")!=null){
                filter.append(" AND UP.APLICACION_ID=").append((Integer)parametros.get("aplicacionId"));
        }
        if((Integer)parametros.get("perfilId")!=null){
                filter.append(" AND UP.PERFIL_ID=").append((Integer)parametros.get("perfilId"));
        }
        filter.append(" ORDER BY UP.MODIFICACION_FECHA DESC");
        return filter.toString();
    }
    
    
    @SuppressWarnings("unchecked")
    public List<PerfilAplicacion> getPerfilAplicacionByIntProperty(Map<String, Object> parametros){
            List<PerfilAplicacion> perfilAplicacion = new ArrayList<>();
            Session s = getSessionFactory().openSession();
            String query ="SELECT UP.USUARIO_ID, UP.PERFIL_ID, UP.ORGANIZACION_ID, UP.APLICACION_ID, "
				+ " UP.CREACION_FECHA, UP.CREACION_USUARIO, UP.MODIFICACION_FECHA, UP.MODIFICACION_USUARIO"
				+ " FROM SEGURIDAD_USUARIO_PERFIL UP WHERE UP.USUARIO_ID = "+(Integer)parametros.get("usuarioId")
                                + getFilter(parametros);
            List<Object[]> list = s.createSQLQuery(query).list();
		for(Object[] o: list){
                    PerfilAplicacion perfil = new PerfilAplicacion();
                    Integer index=0;
                    perfil.setUsuarioId(((BigDecimal)o[index++]).intValue());
                    perfil.setSeguridadPerfilId(((BigDecimal)o[index++]).intValue());
                    perfil.setOrganizacionId(((BigDecimal)o[index++]).intValue());
                    perfil.setAplicacionId(((BigDecimal)o[index++]).intValue());
                    
                    Object date = o[index++];
                    if(date!=null && date instanceof Date){
                            perfil.setCreacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            perfil.setCreacionFecha((Timestamp)date);
                    }
                    perfil.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    date = o[index++];
                    if(date!=null && date instanceof Date){
                            perfil.setModificacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            perfil.setModificacionFecha((Timestamp)date);
                    }
                    perfil.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    
                    perfilAplicacion.add(perfil);
            }
		s.close();
    	return perfilAplicacion;
    }
        
    public void savePerfilAplicacion(PerfilAplicacion p){
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            String query = "INSERT INTO SEGURIDAD_USUARIO_PERFIL (USUARIO_ID, PERFIL_ID, ORGANIZACION_ID, APLICACION_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
                            + "VALUES 	(?,?,?,?,?,?,?,?)";
            Query q = s.createSQLQuery(query);
            Integer index = 0;
            q.setInteger(index++, p.getUsuarioId());
            q.setInteger(index++, p.getSeguridadPerfilId());
            q.setInteger(index++, p.getOrganizacionId());
            q.setInteger(index++, p.getAplicacionId());
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
           // e.printStackTrace();
        }
    }
    
    public void updatePerfilAplicacion( PerfilAplicacion p, int perfilId){
    	Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            String query = "UPDATE SEGURIDAD_USUARIO_PERFIL SET PERFIL_ID=?, APLICACION_ID=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
                            + "WHERE USUARIO_ID = "+ p.getUsuarioId() +
                            " AND ORGANIZACION_ID = "+ p.getOrganizacionId() +
                            " AND PERFIL_ID = "+ perfilId;
            Query q = s.createSQLQuery(query);
            Integer index = 0;
            q.setInteger(index++, p.getSeguridadPerfilId());
            q.setInteger(index++,p.getAplicacionId());
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
    
    public List<Object[]> isValidoPerfilAplicacion(Map<String, Object> parametros){
        List<Object[]> list=null;
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            String query = "SELECT ROWNUM FROM ("+ getQueryPrincipal(parametros)+")";
            list = s.createSQLQuery(query).list();
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
        return list;
    }
    
    public void deleteFisico(PerfilAplicacion p){
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            String query = "DELETE FROM SEGURIDAD_USUARIO_PERFIL WHERE USUARIO_ID ="+ p.getUsuarioId() +" AND PERFIL_ID ="+ 
                    p.getSeguridadPerfilId()+" AND APLICACION_ID ="+p.getAplicacionId(); 
            Query q = s.createSQLQuery(query);
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
