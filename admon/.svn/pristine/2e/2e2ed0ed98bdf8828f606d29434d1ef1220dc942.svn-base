package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.PerfilPagina;
import com.admon.entity.admon.PerfilPaginaPantalla;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PerfilPaginaPantallaDAO extends GenericDAOImpl<PerfilPaginaPantalla, Integer> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<PerfilPaginaPantalla> getEntityClass() {
        return PerfilPaginaPantalla.class;
    }
    private String getQuery(Map<String, Object> parametros){
        Integer eliminadoId = (Integer) parametros.get("eliminadoId");
        Integer perfilPaginaId = (Integer) parametros.get("perfilPaginaId");
        Integer perfilId = (Integer) parametros.get("perfilId");
        StringBuilder query = new StringBuilder();
        query.append(" SELECT PAG.PERFIL_PAGINA_PANTALLA_ID, PAG.PERFIL_PAGINA_ID,PAG.FUNCIONALIDAD_ID,");
        query.append(" PAG.CREACION_FECHA,PAG.CREACION_USUARIO,'pag_'||P.PAGINA_ID||'_'||FUNCIONALIDAD_ID PAGINA_ID");
        query.append(" FROM PERFIL_PAGINA PP ");
        query.append(" INNER JOIN PAGINA P ON (PP.PAGINA_ID = P.PAGINA_ID)  ");
        query.append(" INNER JOIN PERFIL_PAGINA_PANTALLA PAG ON (PP.PERFIL_PAGINA_ID = PAG.PERFIL_PAGINA_ID) ");
        query.append(" WHERE  PP.ELIMINADO_ID = ").append(eliminadoId);
        if(perfilPaginaId!=null)
            query.append(" AND PERFIL_PAGINA_ID =").append(perfilPaginaId);
        if(perfilId!=null)
            query.append(" AND PP.PERFIL_ID =").append(perfilId);
        return query.toString();
    }
    
    
    public List<PerfilPaginaPantalla> getperfilPaginaPantalla(String pantalla, Integer perfilPaginaId,String actionName,Integer perfilId){
        List<PerfilPaginaPantalla> funcionalidades = new ArrayList<>();
        try{
    		Session s = getSessionFactory().openSession();
                StringBuilder query = new StringBuilder();
                query.append(" SELECT PPP.PERFIL_PAGINA_PANTALLA_ID, PPP.PERFIL_PAGINA_ID, PPP.FUNCIONALIDAD_ID  FROM PERFIL_PAGINA_PANTALLA PPP\n" +
                               " INNER JOIN PERFIL_PAGINA PP ON (PP.PERFIL_PAGINA_ID = PPP.PERFIL_PAGINA_ID)");
                query.append(" WHERE PPP.PERFIL_PAGINA_ID = ").append(perfilPaginaId).append(" AND  PP.PERFIL_ID = ").append(perfilId);
                List<Object[]> list = s.createSQLQuery(query.toString()).list();
			for(Object[] ob: list){
                            Integer index = 0;
                            PerfilPaginaPantalla p = new PerfilPaginaPantalla();  
                            p.setPerfilPaginaPantallaId(((BigDecimal)ob[index++]).longValue());
                            p.setPaginaId(((BigDecimal)ob[index++]).intValue());
                            p.setFuncionalidadId(((BigDecimal)ob[index++]).longValue());
                            p.setPantalla(pantalla);
                            p.setActionName(actionName);
                            funcionalidades.add(p);
                        }
                        s.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    	return funcionalidades;

    }
    
    public List<PerfilPaginaPantalla> getperfilPaginaPantalla(Map<String, Object> parametros){
    	List<PerfilPaginaPantalla> list = new ArrayList<>();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(getQuery(parametros)).list();
            for(Object[] ob: l){
                Integer index = 0;
                PerfilPaginaPantalla p = new PerfilPaginaPantalla();
                p.setPerfilPaginaPantallaId(((BigDecimal)ob[index++]).longValue());
                p.setPaginaId(((BigDecimal)ob[index++]).intValue());
                p.setFuncionalidadId(((BigDecimal)ob[index++]).longValue());
                Object date = ob[index++];
                if(date!=null && date instanceof Date){
                        p.setCreacionFecha(new Timestamp(((Date)date).getTime()));
                }
                p.setCreacionUsuario(((BigDecimal)ob[index++]).intValue());
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
    public List<String> getPaginaFuncionalidades(Map<String, Object> parametros){
    	List<String> list = new ArrayList<>();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(getQuery(parametros)).list();
            for(Object[] ob: l){
                list.add(((String)ob[5]));
            }
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
        return list;
    }
    public void savePerfilPaginaPantalla(PerfilPaginaPantalla p){
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            String query = "SELECT PERFIL_PAGINA_PANTALLA_SEQ.NEXTVAL FROM DUAL";
            Query q = s.createSQLQuery(query);
            Long id = Long.valueOf(q.uniqueResult().toString());

            query = "INSERT INTO PERFIL_PAGINA_PANTALLA (PERFIL_PAGINA_PANTALLA_ID, PERFIL_PAGINA_ID, FUNCIONALIDAD_ID, CREACION_FECHA, CREACION_USUARIO, MODIFICACION_FECHA, MODIFICACION_USUARIO) "
                            + "VALUES (?,?,?,?,?,?,?)";

            q = s.createSQLQuery(query);
            Integer index = 0;
            q.setLong(index++, id);
            q.setLong(index++,  new Long(p.getPerfilPaginaId()));
            q.setLong(index++,  p.getFuncionalidadId());
            q.setTimestamp(index++,  p.getCreacionFecha());
            q.setLong(index++,  new Long(p.getCreacionUsuario()));
            q.setTimestamp(index++,  p.getModificacionFecha());
            q.setLong(index++,  new Long(p.getModificacionUsuario()));
            q.executeUpdate();
            t.commit();
            s.close();
        }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
    }
    
    public void deleteRolOperacion(Integer perfilPaginaId){
    	Session s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
    	String query = "DELETE FROM PERFIL_PAGINA_PANTALLA WHERE PERFIL_PAGINA_ID = ?";
    	Query q = s.createSQLQuery(query);
        Integer index = 0;
    	q.setInteger(index++,perfilPaginaId);
    	q.executeUpdate();
    	t.commit();
    	s.close();
    }
    
    public List<PerfilPagina> getPerfilPagina( Integer perfilId, List<Integer> paginasId){
        StringBuilder query = new StringBuilder();
        query.append(" SELECT PAGINA_ID,PERFIL_PAGINA_ID FROM PERFIL_PAGINA WHERE PERFIL_ID =").append(perfilId);
        query.append(" AND PAGINA_ID IN (").append(paginasId.toString()).append(")");
    	List<PerfilPagina> list = new ArrayList<>();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(query.toString()).list();
             for(Object[] ob: l){
                Integer index = 0;
                PerfilPagina p = new PerfilPagina();
                p.setPaginaId(((BigDecimal)ob[index++]).intValue());
                p.setPerfilPaginaId(((BigDecimal)ob[index++]).intValue());
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
}
