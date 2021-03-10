package com.admon.pkg.dao;

import com.admon.dao.BaseDAO;
import com.admon.entity.admon.Bitacora;
import com.admon.entity.admon.SPParametro;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.orm.hibernate3.HibernateCallback;

public abstract class GenericPKGDAOImpl extends BaseDAO  implements
        GenericPKGDAO {

    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@samptixware.ddns.net:1521:MSDEV";
    private static final String DB_USER = "ADMON";
    private static final String DB_PASSWORD = "Desarrollo1";
    
    @Override
    public List<Object> callStoredProcedure(
            final String spName,
            final List<SPParametro> listaParametros) {
        return (List<Object>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(final Session session) throws
                    HibernateException,
                    SQLException {
                Query query = session.getNamedQuery(spName);
                for (SPParametro param : listaParametros) {
                    query.setParameter(
                            param.getParametroNombre(),
                            param.getParametroValor());
                }
                return query.list();
            }
        });
    }
    
    public List<Bitacora> callSP(String spName, String organizaciones, String elementos, Long idEjecucion, Integer userId) throws SQLException{
        Connection dbConnection = null;
	CallableStatement callableStatement = null;
	ResultSet rs = null;
        List<Bitacora> list  = new ArrayList();
	String getSP = spName;
            try {
                SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) getHibernateTemplate().getSessionFactory();
                ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
                
                dbConnection = connectionProvider.getConnection();
                callableStatement = dbConnection.prepareCall(getSP);

                
                callableStatement.setString(1, organizaciones);
                callableStatement.setString(2, elementos);
                callableStatement.setLong(3, idEjecucion);
                callableStatement.setInt(4, userId);
                callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
                // execute store procedure
                callableStatement.executeUpdate();

                rs = (ResultSet) callableStatement.getObject(5);

                while (rs.next()) {
                    Long bitacoraId = rs.getLong("BITACORA_ID");
                    String fecha = rs.getString("FECHA_HORA");
                    String proceso = rs.getString("PROCESO");
                    String procedimiento = rs.getString("PROCEDIMIENTO");
                    Integer cantidad = rs.getInt("CANTIDAD");
                    String mensaje = rs.getString("MENSAJE");
                    Integer organizacionId = rs.getInt("ORGANIZACION_ID");
                    Long ejecucionId = rs.getLong("EJECUCION_ID");
                    Integer estatusId = rs.getInt("ESTATUS_ID");
                     Bitacora b = new Bitacora(bitacoraId, fecha, proceso, procedimiento, cantidad, mensaje, organizacionId, ejecucionId, estatusId);
                    list.add(b);
                    System.out.println("bitacoraId : " + bitacoraId);
                    System.out.println("fecha : " + fecha);
                    System.out.println("proceso : " + proceso);
                    System.out.println("procedimiento : " + procedimiento);
                    System.out.println("organizacionId : " + organizacionId);
                    System.out.println("ejecucionId : " + ejecucionId);
                    System.out.println("estatusId : " + estatusId);
}
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(GenericPKGDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (callableStatement != null) {
                        callableStatement.close();
                }

                if (dbConnection != null) {
                        dbConnection.close();
                }

            }
            return list;
    }
    
    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

                Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

                System.out.println(e.getMessage());

        }

        try {

                dbConnection = DriverManager.getConnection(
                        DB_CONNECTION, DB_USER,DB_PASSWORD);
                return dbConnection;

        } catch (SQLException e) {

                System.out.println(e.getMessage());

        }

        return dbConnection;

    }
}