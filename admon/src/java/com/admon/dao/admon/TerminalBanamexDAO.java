package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.TerminalBanamex;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TerminalBanamexDAO extends GenericDAOImpl<TerminalBanamex, Integer> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<TerminalBanamex> getEntityClass() {
        return TerminalBanamex.class;
    }
    
    public Long getSecuencia(){
        Session s = null;
        Long id=null;
        try{
    	 s = getSessionFactory().openSession();
    	Transaction t = s.beginTransaction();
            String query = "SELECT AFILIACION_BMEX_TERMINAL_SEQ.NEXTVAL FROM DUAL";
            Query q = s.createSQLQuery(query);
            id = Long.valueOf(q.uniqueResult().toString());
            t.commit();
            s.close();
         }catch ( Exception e ){
            if(s!=null)
                s.close();
            e.printStackTrace();
        }
        return id;
    }
}
