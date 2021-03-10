package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.Parametro;
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

public class ParametroDAO extends GenericDAOImpl<Parametro, Long> {
    
     public ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");
     public final Integer NOELIMINADO = new Integer(codigos.getString("key_eliminado_noeliminado"));
     public final String INSTANCIA = codigos.getString("key_instancia_bd");

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<Parametro> getEntityClass() {
        return Parametro.class;
    }
    
    public Parametro getParametroById(String tableName, String propertyName, Long parametroId){
        String propertyFuncionalidad = "OPER_FUNC";
        Parametro parametro = new Parametro();
        Session s = getSessionFactory().openSession();
            List<Object[]> list = s.createSQLQuery("SELECT "+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID, "+ propertyName +"_ID, LENGUAJE_ID, NOMBRE, "
				+ " CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO"
				+ " FROM "+ tableName +" SA WHERE "+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID="+ parametroId).list();
            for(Object[] o: list){
                Integer index=0;
                parametro.setParametroId(((BigDecimal)o[index++]).longValue());
                    parametro.setFkId(((BigDecimal)o[index++]).longValue());
                    parametro.setLenguajeId(((BigDecimal)o[index++]).intValue());
                    parametro.setNombre((String)o[index++]);
                    
                    Object date = o[index++];
                    if(date!=null && date instanceof Date){
                            parametro.setCreacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            parametro.setCreacionFecha((Timestamp)date);
                    }
                    parametro.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    date = o[index++];
                    if(date!=null && date instanceof Date){
                            parametro.setModificacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            parametro.setModificacionFecha((Timestamp)date);
                    }
                    parametro.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
            break;
        }
        s.close();
        return parametro ;
    }
    
     private String getQueryPrincipal(Map<String, Object> parametros){
                StringBuilder query = new StringBuilder();
                String tableName = (String) parametros.get("tableName");
                String propertyName = (String) parametros.get("propertyName");
                Integer lenguajeId = (Integer) parametros.get("lenguajeId");
                Long fkId = (Long) parametros.get("value");
                Long value = 0L;
                if (parametros.get("value") != null)
                    value = (Long) parametros.get("value");
                
		query.append("SELECT ROWID AS rid FROM ").append(tableName).append(" SAP WHERE SAP.").append(propertyName).append("_ID=").append(value);

                StringBuilder filter = new StringBuilder();
		if(lenguajeId!=null ){
			filter.append(" AND SAP.LENGUAJE_ID=").append((Integer)parametros.get("lenguajeId"));
		}
               /* if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
			filter.append(" AND SAP.NOMBRE='").append((String)parametros.get("nombre")).append("'");
		}*/
                query.append(filter);
		query.append(" ORDER BY SAP.MODIFICACION_FECHA DESC");
		return query.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
            String tableName = (String) parametros.get("tableName");
            String propertyName = (String) parametros.get("propertyName");
            String propertyFuncionalidad = "OPER_FUNC";
            Integer indexInicio = (Integer) parametros.get("indexInicio");
            Integer indexFinal = (Integer) parametros.get("indexFinal");
            if(indexInicio==null) indexInicio=1;
            if(indexFinal==null) indexFinal=10;
            String query = "SELECT SAP."+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID, SAP."+ propertyName +"_ID, SAP.LENGUAJE_ID, "+
                        " (SELECT NOMBRE FROM LENGUAJE L WHERE L.LENGUAJE_ID=SAP.LENGUAJE_ID) AS LENGUAJE, "+
                        " SAP.NOMBRE, "+
                        " SAP.CREACION_FECHA, SAP.CREACION_USUARIO, SAP.MODIFICACION_FECHA, SAP.MODIFICACION_USUARIO "+
    			" FROM "+ tableName +" SAP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = SAP.ROWID ";
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
    public List<Parametro> getParametros(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<Parametro> list = new ArrayList<>();
    	Session s = getSessionFactory().openSession();
    	List<Object[]> l = s.createSQLQuery(query).list();
    	for(Object[] o: l){
            Parametro parametro = new Parametro();
                    Integer index=0;
                    parametro.setParametroId(((BigDecimal)o[index++]).longValue());
                    parametro.setFkId(((BigDecimal)o[index++]).longValue());
                    parametro.setLenguajeId(((BigDecimal)o[index++]).intValue());
                    parametro.setLenguaje((String)o[index++]);
                    parametro.setNombre((String)o[index++]);
                    
                    Object date = o[index++];
                    if(date!=null && date instanceof Date){
                            parametro.setCreacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            parametro.setCreacionFecha((Timestamp)date);
                    }
                    parametro.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    date = o[index++];
                    if(date!=null && date instanceof Date){
                            parametro.setModificacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            parametro.setModificacionFecha((Timestamp)date);
                    }
                    parametro.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    
                    list.add(parametro);
    	}
    	s.close();
    	return list;
    }
    
        @SuppressWarnings("unchecked")
	public List<Parametro> getParametroByIntProperty(String tableName, String propertyName, Integer value){
            List<Parametro> parametros = new ArrayList<>();
            String propertyFuncionalidad = "OPER_FUNC";
            Session s = getSessionFactory().openSession();
            List<Object[]> list = s.createSQLQuery("SELECT "+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID, "+ propertyName +"_ID, LENGUAJE_ID, NOMBRE, "
				+ " CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO"
				+ " FROM "+ tableName +" SA WHERE "+ propertyName +"_ID="+value).list();
		for(Object[] o: list){
                    Parametro parametro = new Parametro();
                    Integer index=0;
                    parametro.setParametroId(((BigDecimal)o[index++]).longValue());
                    parametro.setFkId(((BigDecimal)o[index++]).longValue());
                    parametro.setLenguajeId(((BigDecimal)o[index++]).intValue());
                    parametro.setNombre((String)o[index++]);
                    
                    Object date = o[index++];
                    if(date!=null && date instanceof Date){
                            parametro.setCreacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            parametro.setCreacionFecha((Timestamp)date);
                    }
                    parametro.setCreacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    date = o[index++];
                    if(date!=null && date instanceof Date){
                            parametro.setModificacionFecha(new Timestamp(((Date)date).getTime()));
                    }
                    if(date!=null && date instanceof Timestamp){
                            parametro.setModificacionFecha((Timestamp)date);
                    }
                    parametro.setModificacionUsuario(o[index]==null?null:((BigDecimal)o[index++]).intValue());
                    
                    parametros.add(parametro);
            }
		s.close();
    	return parametros;
    }
        
    public void saveParametro(String tableName, String propertyName, Parametro o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
        String propertyFuncionalidad = "OPER_FUNC";
    	String query = "INSERT INTO "+ tableName +" ("+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID, "+ propertyName +"_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
    			+ "VALUES 	("+ propertyName +"_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setLong(index++, o.getFkId());
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
    
    public void updateParametro(String tableName, String propertyName, Parametro o){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
        String propertyFuncionalidad = "OPER_FUNC";
    	String query = "UPDATE "+ tableName +" SET LENGUAJE_ID=?, NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
    			+ "WHERE "+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID=?";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setLong(index++, o.getLenguajeId());
    	q.setString(index++, o.getNombre());
    	q.setTimestamp(index++, o.getModificacionFecha());
    	q.setInteger(index++, o.getModificacionUsuario());
        q.setLong(index++, o.getParametroId());
    	q.executeUpdate();
    	
    	t.commit();
    	s.close();
    }
    
    public List<Object[]> isValidoNombreParametro(Map<String, Object> parametros){
        Session s = getSessionFactory().openSession();
    	String query = "SELECT ROWNUM FROM ("+ getQueryPrincipal(parametros)+")";
        List<Object[]> list = s.createSQLQuery(query).list();
        s.close();
        return list;
    }
    
    public void deleteFisico(String tableName, String propertyName, Long id){
        Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
        String propertyFuncionalidad = "OPER_FUNC";
    	String query = "DELETE FROM "+ tableName +" WHERE "+ (propertyName.equals("FUNCIONALIDAD") ? propertyFuncionalidad : propertyName) +"_PARAMETRO_ID="+id;
    	Query q = s.createSQLQuery(query);
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
}
