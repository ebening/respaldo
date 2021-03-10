package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.SeguridadRolRestriccionPar;
import org.hibernate.tool.hbm2x.StringUtils;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SeguridadRolRestriccionParDAO extends GenericDAOImpl<SeguridadRolRestriccionPar, Integer> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<SeguridadRolRestriccionPar> getEntityClass() {
        return SeguridadRolRestriccionPar.class;
    }
}
