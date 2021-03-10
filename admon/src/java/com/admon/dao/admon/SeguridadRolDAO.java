/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.SeguridadRol;
import com.admon.entity.admon.SeguridadRolOperFunc;
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



public class SeguridadRolDAO extends GenericDAOImpl <SeguridadRol,Integer>{
       /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<SeguridadRol> getEntityClass() {
        return SeguridadRol.class;
    }
      /*
    * Método que  obtiene el registro de un rol en específico,que no esten eliminado,
    * y que el lenguaje sea en español
    *
    * @param rolId Es un Id de Rol para realizar el filtro
    * @return Regresa un objeto <b>SeguridadRol</b> que coincida con el rolId
     */
    public SeguridadRol getRol(Integer rolId){
        StringBuilder query = new StringBuilder();
        query.append("SELECT R.ROL_ID,R.APLICACION_ID,RP.NOMBRE,R.DESCRIPCION,R.MODIFICACION_FECHA,R.CREACION_FECHA,");
        query.append("R.CREACION_USUARIO,R.ELIMINADO_ID,R.ESTATUS_ID, RP.LENGUAJE_ID,R.MODIFICACION_USUARIO ");
        query.append("FROM SEGURIDAD_ROL R INNER JOIN SEGURIDAD_ROL_PARAMETROS RP ON (R.ROL_ID = RP.ROL_ID) ");
        query.append("WHERE R.ELIMINADO_ID=0 AND RP.LENGUAJE_ID=1 AND R.ROL_ID = ").append(rolId);
        SeguridadRol o = new SeguridadRol();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(query.toString()).list();
            for(Object[] ob: l){
                o = setParametros(ob);
                o.setLenguajeId(((BigDecimal)ob[9]).intValue());
                o.setModificacionUsuario(((BigDecimal)ob[10]).intValue());
            }
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
        return o;
    }
    private String getQueryPrincipal(Map<String, Object> parametros){
                Integer eliminadoId = (Integer) parametros.get("eliminadoId");
                StringBuilder query = new StringBuilder();
		query.append("SELECT ROWID AS rid FROM SEGURIDAD_ROL R WHERE R.ELIMINADO_ID=").append(eliminadoId);
                query.append(getFilter(parametros));
		return query.toString();
    }
    
     /*
    * Método que permite estructurar el query para realizar filtros de búsqueda
    * y que el lenguaje sea en español
    *
    * @param parametros Se encuentran definidos los filtros que deseamos
    * @return Regresa StringBuilder con la estructura del query
     */
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if(StringUtils.isNotEmpty((String)parametros.get("aplicacionId"))){
              filter.append(" AND R.APLICACION_ID LIKE '%").append(Integer.parseInt(parametros.get("aplicacionId").toString())).append("%'");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("nombre"))){
            filter.append(" AND R.ROL_ID IN (SELECT SRP.ROL_ID FROM SEGURIDAD_ROL_PARAMETROS SRP WHERE UPPER(SRP.NOMBRE) LIKE '%").append(((String)parametros.get("nombre")).toUpperCase()).append("%')");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("descripcion"))){
            filter.append(" AND UPPER(R.DESCRIPCION) LIKE '%").append(((String)parametros.get("descripcion")).toUpperCase()).append("%'");
        }
        if(StringUtils.isNotEmpty((String)parametros.get("modificacionUsuario"))){
                    filter.append(" AND R.MODIFICACION_USUARIO LIKE '%").append(Integer.parseInt(parametros.get("modificacionUsuario").toString())).append("%'");
            }
        return filter.toString();
    }

     /*
    * Método que permite estructurar el query para obtener los Roles paginados
    *
    * @param parametros Se encuentran definidos los filtros que deseamos
    * @return Regresa String con la estructura del query
     */
    private String getQuery(Map<String, Object> parametros){
            Integer lenguajeId = (Integer) parametros.get("lenguajeId");
            Integer indexInicio = (Integer) parametros.get("indexInicio");
            Integer indexFinal = (Integer) parametros.get("indexFinal");
            Integer eliminadoId = (Integer) parametros.get("eliminadoId");
            if(indexInicio==null) indexInicio=1;
            if(indexFinal==null) indexFinal=10;
		
            String query = "SELECT R.ROL_ID,A.APLICACION_ID, "+
                        "RP.NOMBRE AS NOMBRE, "+
    			"R.DESCRIPCION, R.MODIFICACION_FECHA, R.CREACION_FECHA,R.CREACION_USUARIO, "+
    			"R.ELIMINADO_ID, R.ESTATUS_ID, " +
                        "(SELECT NOMBRE FROM SEGURIDAD_APLICACION_PARAMETRO AP WHERE A.APLICACION_ID=AP.APLICACION_ID AND AP.LENGUAJE_ID = "+lenguajeId+") AS APLICACION, "+
                        "(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=R.MODIFICACION_USUARIO) AS MODIFICACION_USUARIO "+
    			"FROM SEGURIDAD_APLICACION A ,SEGURIDAD_ROL R,SEGURIDAD_ROL_PARAMETROS RP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = R.ROWID "+
                        " AND A.APLICACION_ID = R.APLICACION_ID AND A.ELIMINADO_ID="+eliminadoId+
                        " AND R.ROL_ID = RP.ROL_ID AND RP.LENGUAJE_ID = "+lenguajeId+
                        getOrder(parametros) + "";
    	return query;
    }
    
    private String getOrder(Map<String, Object> parametros){    
    	StringBuilder orderString = new StringBuilder();
    	orderString.append(" ORDER BY ");
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            if(parametros.get("order").equals("nombre")){
            	orderString.append(" RP.");
            }  else {
            	orderString.append(" R.");
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
        Session s=null;
        try{
            s = getSessionFactory().openSession();
            count = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
            s.close();
        }catch ( Exception e ){
        if(s!=null)
            s.close();
        e.printStackTrace();
        }
        return count;
    }
    
    @SuppressWarnings("unchecked")
    public List<SeguridadRol> getRoles(Map<String, Object> parametros) {
    	String query = getQuery(parametros);
    	List<SeguridadRol> list = new ArrayList<>();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(query).list();
            for(Object[] ob: l){
                SeguridadRol o = setParametros(ob);
                o.setAplicacion(((String)ob[9]));
                o.setUsuarioModificacion(((String)ob[10]));
                list.add(o);
            }
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    	return list;
    }
    public SeguridadRol setParametros(Object[] ob){
            Integer index = 0;
            SeguridadRol o = new SeguridadRol();
            o.setRolId(((BigDecimal)ob[index++]).intValue());
            o.setAplicacionId(((BigDecimal)ob[index++]).intValue());
            o.setNombre(((String)ob[index++]));
            o.setDescripcion(((String)ob[index++]));
            Object date = ob[index++];
            if(date!=null && date instanceof Date){
                    o.setModificacionFecha(new Timestamp(((Date)date).getTime()));
            }
            date = ob[index++];
            if(date!=null && date instanceof Date){
                    o.setCreacionFecha(new Timestamp(((Date)date).getTime()));
            }
            o.setCreacionUsuario(((BigDecimal)ob[index++]).intValue());
            o.setEliminadoId(((BigDecimal)ob[index++]).intValue());
            o.setEstatusId(((BigDecimal)ob[index++]).intValue());
            return o;
    }
    public void saveRol(SeguridadRol r,int lenguajeId){
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            String query = "SELECT SEGURIDAD_ROL_SEQ.NEXTVAL FROM DUAL";
            Query q = s.createSQLQuery(query);
            Long id = Long.valueOf(q.uniqueResult().toString());


            query = "INSERT INTO SEGURIDAD_ROL (ROL_ID, APLICACION_ID, NOMBRE, DESCRIPCION, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA,MODIFICACION_USUARIO,ESTATUS_ID,ELIMINADO_ID) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?)";

            q = s.createSQLQuery(query);
            Integer index = 0;
            q.setLong(index++, id);
            q.setLong(index++, r.getAplicacionId());
            q.setString(index++, r.getNombre());
            q.setString(index++, r.getDescripcion());
            q.setTimestamp(index++, r.getCreacionFecha());
            q.setInteger(index++, r.getCreacionUsuario());
            q.setTimestamp(index++, r.getModificacionFecha());
            q.setInteger(index++, r.getModificacionUsuario());
            q.setInteger(index++, r.getEstatusId());
            q.setInteger(index++, r.getEliminadoId());
            q.executeUpdate();

            query = "INSERT INTO SEGURIDAD_ROL_PARAMETROS (ROL_PARAMETRO_ID, ROL_ID, LENGUAJE_ID, NOMBRE, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
                            + "VALUES 	(ROL_PARAMETRO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
            q = s.createSQLQuery(query);
            index = 0;
            q.setLong(index++, id);
            q.setLong(index++, new Long(lenguajeId));
            q.setString(index++, r.getNombre());
            q.setTimestamp(index++, r.getCreacionFecha());
            q.setInteger(index++, r.getCreacionUsuario());
            q.setTimestamp(index++, r.getModificacionFecha());
            q.setInteger(index++, r.getModificacionUsuario());
            q.executeUpdate();

            t.commit();
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    }
     public void updateRolParametro(SeguridadRol r,Integer lenguajeId){
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            String query = "UPDATE SEGURIDAD_ROL_PARAMETROS SET NOMBRE=?, MODIFICACION_FECHA=?, MODIFICACION_USUARIO=? "
                            + " WHERE ROL_ID=? AND LENGUAJE_ID=" + lenguajeId;
            Query q = s.createSQLQuery(query);
            Integer index = 0;
            q.setString(index++, r.getNombre());
            q.setTimestamp(index++, r.getModificacionFecha());
            q.setInteger(index++, r.getModificacionUsuario());
            q.setLong(index++, r.getRolId());
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
    public boolean isValidoNombre(Map<String, Object> parametros) {
        String table =parametros.get("table").toString();
        String[] wordList = table.split("_");
        String id = wordList[wordList.length-1];
    	StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(1) FROM ").append(table).append(" A ");
        query.append("INNER JOIN  ").append(parametros.get("tablePar")).append(" AP ");
        query.append("ON A.").append(id).append("_ID = AP.").append(id).append("_ID ");
        query.append("WHERE A.ELIMINADO_ID =").append((Integer) parametros.get("eliminadoId"));
        query.append(" AND AP.LENGUAJE_ID =").append((Integer) parametros.get("lenguajeId"));
        query.append(" AND AP.NOMBRE ='").append(parametros.get("nombre")).append("'");
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
            return false;//no es válido
        else
            return true;//si es válido
    }
      /*
    * Método que devuelve los registros correspondientes a
    * modulos, funciones y operaciones
    *
    * @param parametros Filtros para realizar la consulta entre ellos
    * lenguajeId por default es español(1), eliminadoId(0) no eliminados,
    * aplicacionId el que seleccione el usuario en pantalla
    * @return Regresa una lista de objetos <b>SeguridadRolOperFunc</b> para 
    * poder pintarlos en el árbol
     */
    @SuppressWarnings("unchecked")
    public List<SeguridadRolOperFunc> getOpFuncByAplicacion(Map<String, Object> parametros){
        List<SeguridadRolOperFunc> seguridadOperaciones = new ArrayList<>();
        Session s = null;
    	try{
                int lenguajeId=(Integer)parametros.get("lenguajeId");
                int eliminadoId=(Integer)parametros.get("eliminadoId");
                int aplicacionId=(Integer)parametros.get("aplicacionId");
                int estatusId=(Integer)parametros.get("estatusId");
    		s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append("  SELECT IDENTIFICADOR, NOMBRE, PADRE FROM (\n" +
                            "   SELECT MOD.MODULO_ID || '-M' MOD_ID,MOD.NOMBRE NOMBRE_MOD,MOD.APLICACION_ID|| '-A' PADRE_AP,\n" +
                            "   OP.OPERACION_ID || '-O' OP_ID,OP.NOMBRE NOMBRE_OP,OP.MODULO_ID|| '-M' PADRE_MOD,\n" +
                            "   FUNC.FUNCIONALIDAD_ID || '-F' FUNC_ID,FUNC.NOMBRE NOMBRE_FUNC,FUNC.OPERACION_ID || '-O' PADRE_OP \n" +
                            "   FROM SEGURIDAD_APLICACION SA ");
                query.append("  LEFT JOIN ( SELECT  SM.MODULO_ID, SMP.NOMBRE,SM.APLICACION_ID  \n" +
                             "              FROM    SEGURIDAD_MODULO SM\n" +
                             "              INNER JOIN SEGURIDAD_MODULO_PARAMETROS SMP ON (SM.MODULO_ID = SMP.MODULO_ID)\n" +
                             "              WHERE   SM.ELIMINADO_ID = ").append(eliminadoId).append(" AND SMP.LENGUAJE_ID =").append(lenguajeId).append(" AND SM.ESTATUS_ID = ").append(estatusId) ;
                query.append("              AND SMP.NOMBRE IS NOT NULL) MOD\n" +
                             "              ON ( SA.APLICACION_ID = MOD.APLICACION_ID) ");
                query.append("  LEFT JOIN ( SELECT  SO.OPERACION_ID, SOP.NOMBRE, SO.MODULO_ID \n" +
                             "              FROM    SEGURIDAD_OPERACION SO \n" +
                             "              INNER JOIN SEGURIDAD_OPERACION_PARAMETROS SOP ON (SO.OPERACION_ID = SOP.OPERACION_ID)\n" +
                             "              WHERE   SO.ELIMINADO_ID = ").append(eliminadoId).append(" AND SOP.LENGUAJE_ID = ").append(lenguajeId).append(" AND SO.ESTATUS_ID = ").append(estatusId) ;
                query.append("              AND SOP.NOMBRE IS NOT NULL) OP\n" +
                             "              ON ( MOD.MODULO_ID = OP.MODULO_ID) ");
                query.append("  LEFT JOIN ( SELECT  SF.FUNCIONALIDAD_ID, SFPAR.NOMBRE, SF.OPERACION_ID \n" +
                             "              FROM    SEGURIDAD_OPERA_FUNCIONALIDAD SF \n" +
                             "              INNER JOIN SEGURIDAD_OPER_FUNC_PARAMETROS SFPAR ON (SF.FUNCIONALIDAD_ID=SFPAR.FUNCIONALIDAD_ID)\n" +
                             "              WHERE   SF.ELIMINADO_ID = ").append(eliminadoId).append(" AND SFPAR.LENGUAJE_ID = ").append(lenguajeId).append(" AND SF.ESTATUS_ID = ").append(estatusId) ;
                query.append("              AND SFPAR.NOMBRE IS NOT NULL) FUNC\n" +
"                                           ON ( OP.OPERACION_ID = FUNC.OPERACION_ID) "
                             + "WHERE  SA.APLICACION_ID = ") .append(aplicacionId).append(")");
                query.append(" UNPIVOT ((IDENTIFICADOR,NOMBRE,PADRE) for IDENT IN(");
                query.append("(MOD_ID,NOMBRE_MOD,PADRE_AP),(OP_ID,NOMBRE_OP,PADRE_MOD),");
                query.append("(FUNC_ID,NOMBRE_FUNC,PADRE_OP))) ");
                query.append(" WHERE NOMBRE IS NOT NULL ");
                query.append(" GROUP BY IDENTIFICADOR,NOMBRE,PADRE ");

                List<Object[]> list = s.createSQLQuery(query.toString()).list();
		for(Object[] o: list){
                    SeguridadRolOperFunc seguridad = new SeguridadRolOperFunc();
                    if((String)o[1]!=null){
                        seguridad.setId((String)o[0]);
                        seguridad.setNombre((String)o[1]);
                        seguridad.setAnidar((String)o[2]);
                        seguridadOperaciones.add(seguridad);
                    }
	    	}
		s.close();
    	}catch(Exception e){
            if(s!=null)
               s.close();
            e.printStackTrace();
    	}
    	return seguridadOperaciones;
    }
    @SuppressWarnings("unchecked")
    public List<SeguridadRol> getComboRolesByAplicacion(Map<String, Object> parametros){
        Integer eliminadoId = (Integer) parametros.get("eliminadoId");
        Integer lenguajeId = (Integer) parametros.get("lenguajeId");
        Integer aplicacionId = (Integer) parametros.get("aplicacionId");
    	List<SeguridadRol> aplicaciones = new ArrayList<>();
        Session s = null;
    	try{
    		s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append("SELECT R.ROL_ID, RP.NOMBRE ");
                query.append("FROM SEGURIDAD_ROL R  ");
                query.append("INNER JOIN SEGURIDAD_ROL_PARAMETROS RP ON (R.ROL_ID = RP.ROL_ID) ");
                query.append("WHERE R.ELIMINADO_ID=").append(eliminadoId).append(" AND RP.LENGUAJE_ID=");
                query.append(lenguajeId).append(" AND R.APLICACION_ID=").append(aplicacionId);
                query.append("ORDER BY RP.NOMBRE  ASC");
			List<Object[]> list = s.createSQLQuery(query.toString()).list();
			for(Object[] o: list){
	    		SeguridadRol rol = new SeguridadRol();
	    		rol.setRolId(((BigDecimal)o[0]).intValue());
	    		rol.setNombre((String)o[1]);
	    		aplicaciones.add(rol);
	    	}
		s.close();
    	}catch(Exception e){
            if(s!=null)
               s.close();
            e.printStackTrace();
    	}
    	return aplicaciones;
    }
    
    
}
