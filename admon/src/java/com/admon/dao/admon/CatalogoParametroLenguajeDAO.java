package com.admon.dao.admon;

import org.hibernate.Session;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.CatalogoParametroLenguaje;
import com.admon.entity.admon.CatalogoParametroLenguajePK;

public class CatalogoParametroLenguajeDAO extends GenericDAOImpl<CatalogoParametroLenguaje, CatalogoParametroLenguajePK> {

    /*
     * Método para tipar el objeto genérico. Es utilizado
     * en la implementación del GenericDAO para identificar
     * el objeto genérico la cual es necesaria para realizar
     * las consultas a la BD.
     * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<CatalogoParametroLenguaje> getEntityClass() {
        return CatalogoParametroLenguaje.class;
    }
    
    public int getCatalogoParametroLenguajeNextKey(){
        Integer sq = 0;
        String query = "select CATALOGO_PARAMETRO_LENG_SEQ.nextval from dual";
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
}