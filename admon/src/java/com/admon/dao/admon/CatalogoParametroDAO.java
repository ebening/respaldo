package com.admon.dao.admon;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.tool.hbm2x.StringUtils;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.CatalogoParametro;
import com.admon.entity.admon.CatalogoParametroPK;

public class CatalogoParametroDAO extends GenericDAOImpl<CatalogoParametro, CatalogoParametroPK> {

	public ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");
	public final Integer NOELIMINADO = new Integer(codigos.getString("key_eliminado_noeliminado"));

    /*
     * Método para tipar el objeto genérico. Es utilizado
     * en la implementación del GenericDAO para identificar
     * el objeto genérico la cual es necesaria para realizar
     * las consultas a la BD.
     * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<CatalogoParametro> getEntityClass() {
        return CatalogoParametro.class;
    }
    
    private String getQueryPrincipal(Map<String, Object> parametros){
    	StringBuilder query = new StringBuilder();
        query.append("SELECT ROWID AS rid FROM CATALOGO_PARAMETRO CP WHERE 1=1 ").append(getFilter(parametros));
        return query.toString();
    }
    
    private String getFilter(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if(parametros.get("eliminadoId")!=null){
            filter.append(" AND CP.ELIMINADO_ID=").append(parametros.get("eliminadoId"));
        }
        if(parametros.get("organizacionId")!=null){
            filter.append(" AND CP.ORGANIZACION_ID=").append(parametros.get("organizacionId"));
        }
        if(parametros.get("catalogoId")!=null){
            filter.append(" AND CP.CATALOGO_ID=").append(parametros.get("catalogoId"));
        }
        if(parametros.get("catalogoParametroId")!=null){
            filter.append(" AND CP.CATALOGO_PARAMETRO_ID=").append(parametros.get("catalogoParametroId"));
        }
        if(parametros.get("validate")!=null){
            filter.append(" AND CP.").append(parametros.get("validate")).append(" IS NOT NULL");
        }
        return filter.toString();
    }
    
    private String getQuery(Map<String, Object> parametros){
    	Integer indexInicio = (Integer) parametros.get("indexInicio");
        Integer indexFinal = (Integer) parametros.get("indexFinal");
        if(indexInicio==null) indexInicio=1;
        if(indexFinal==null) indexFinal=10;
		
    	String query = "SELECT CATALOGO_PARAMETRO_ID,CATALOGO_ID,ORGANIZACION_ID,CLAVE,VALOR,ORDEN,VISIBLE,DESCRIPCION,ESTATUS_ID,ELIMINADO_ID,CREACION_FECHA,CREACION_USUARIO,MODIFICACION_FECHA,MODIFICACION_USUARIO, "+
    			"(SELECT USUARIO FROM USUARIO U WHERE U.USUARIO_ID=CP.MODIFICACION_USUARIO) AS MOD_USUARIO, IMAGEN "+
    			"FROM CATALOGO_PARAMETRO CP, (SELECT i.* "+
				"          FROM (SELECT i.*, ROWNUM AS rn  "+
				"                  FROM ( "+getQueryPrincipal(parametros)+" ) i "+
				"                 WHERE ROWNUM <= "+indexFinal+
				"               ) i "+
				"         WHERE rn >= "+indexInicio+" "+
				"       ) i "+
				"WHERE i.rid = CP.ROWID "+getOrder(parametros);
                              
    	return query;
    }
    
    public int getCount(Map<String, Object> parametros){
        Integer count = 0;
        String query = "select count(1) from ("+ getQueryPrincipal(parametros)+")";
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            count = Integer.valueOf(s.createSQLQuery(query).uniqueResult().toString());
        }catch ( Exception e ){
            e.printStackTrace();
        }finally{
        	if(s!=null)
                s.close();
        }
        return count;
    }
    
    private String getOrder(Map<String, Object> parametros){
        StringBuilder filter = new StringBuilder();
        if(StringUtils.isNotEmpty((String)parametros.get("order"))){
            filter.append(parametros.get("order"));
        } 
        filter.append(" ");
        if(StringUtils.isNotEmpty((String)parametros.get("ordenTipo"))){
            filter.append(parametros.get("ordenTipo"));
        }
        if(filter.length()>1){
        	return " ORDER BY " + filter.toString();
        }
        return "";
    }
    
    @SuppressWarnings("unchecked")
	public List<CatalogoParametro> getCatalogoParametros(Map<String, Object> parametros) {
        String query = getQuery(parametros);
        List<CatalogoParametro> list = new ArrayList<>();
        Session s = null;
        try{
            s = getSessionFactory().openSession();
            List<Object[]> l = s.createSQLQuery(query).list();
            for(Object[] ob: l){
                Integer index = 0;
                CatalogoParametro c = new CatalogoParametro();
                CatalogoParametroPK catalogoPK = new CatalogoParametroPK();
                catalogoPK.setCatalogoParametroId(((BigDecimal)ob[index++]).intValue());
                c.setCatalogoId(((BigDecimal)ob[index++]).intValue());
                catalogoPK.setOrganizacionId(((BigDecimal)ob[index++]).intValue());
                c.setCatalogoParametroPK(catalogoPK);
                c.setClave((String)ob[index++]);
                c.setValor((String)ob[index++]);
                c.setOrden(((BigDecimal)ob[index++]).intValue());
                int visible = ((BigDecimal)ob[index++]).intValue();
                c.setVisible(visible==1?true:false);
                c.setDescripcion((String)ob[index++]);
                c.setEstatusId(((BigDecimal)ob[index++]).intValue());
                c.setEliminadoId(((BigDecimal)ob[index++]).intValue());
                Object date = ob[index++];
    			if(date!=null && date instanceof Date){
    				c.setCreacionFecha(new Timestamp(((Date)date).getTime()));
    			}
    			if(date!=null && date instanceof Timestamp){
    				c.setCreacionFecha((Timestamp)date);
    			}
    			c.setCreacionUsuario(((BigDecimal)ob[index++]).intValue());
                date = ob[index++];
    			if(date!=null && date instanceof Date){
    				c.setModificacionFecha(new Timestamp(((Date)date).getTime()));
    			}
    			if(date!=null && date instanceof Timestamp){
    				c.setModificacionFecha((Timestamp)date);
    			}
    			Object modificacionUsuario = ob[index++];
    			if(modificacionUsuario!=null){
    				c.setModificacionUsuario(((BigDecimal)modificacionUsuario).intValue());
    			}
    			c.setUsuarioModificacion((String)ob[index++]);
    			c.setImagen((String)ob[index++]);
                list.add(c);
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }finally{
        	if(s!=null)
                s.close();
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<CatalogoParametro> getCatalogoParametrosForCombo(Integer catalogoId){
    	List<CatalogoParametro> list = new ArrayList<>();
    	StringBuilder query = new StringBuilder();
        query.append("SELECT CATALOGO_PARAMETRO_ID, CLAVE FROM CATALOGO_PARAMETRO WHERE CATALOGO_ID="+catalogoId+" AND ELIMINADO_ID="+NOELIMINADO);
        Session s = null;
        try{
	        s=getSessionFactory().openSession();
	        List<Object[]> l = s.createSQLQuery(query.toString()).list();
	        for(Object[] ob: l){
	            Integer index = 0;
	            CatalogoParametro c = new CatalogoParametro();
	            c.setCatalogoParametroPK(new CatalogoParametroPK());
	            c.getCatalogoParametroPK().setCatalogoParametroId(((BigDecimal)ob[index++]).intValue());
	            c.setClave((String)ob[index++]);
	            list.add(c);
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	if(s!=null)
        		s.close();
        }
        
        return list;
    }
    
    public int getCatalogoParametroNextKey(){
        Integer sq = 0;
        String query = "select CATALOGO_PARAMETRO_SEQ.nextval from dual";
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
    
    public Timestamp getFechaCreacion(Integer catalogoParametroId){
        String query = "select CREACION_FECHA from CATALOGO_PARAMETRO where CATALOGO_PARAMETRO_ID="+catalogoParametroId;
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
        }finally{
        	if(s!=null)
                s.close();
        }
        return null;
    }
}

