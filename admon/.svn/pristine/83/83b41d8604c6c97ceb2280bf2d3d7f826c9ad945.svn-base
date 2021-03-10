package com.admon.pkg.bss;

import com.admon.pkg.dao.ReportesPKGDAO;
import com.admon.entity.admon.SPParametro;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaEncabezadoRS;
import com.admon.pkg.entity.EstadoCuentaRS;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReportesPKGBssImpl implements ReportesPKGBss {

    // Spring IoC
    private ReportesPKGDAO reportesPKGDAO;
    
    public ReportesPKGBssImpl() {
    }

    // Spring setter
    public void setReportesPKGDAO(ReportesPKGDAO reportesPKGDAO) {
        this.reportesPKGDAO = reportesPKGDAO;
    }

    /**
     * Obtiene Estado de Cuenta por un rango de fechas
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param organizacionId id de la organización
     * @param eventoId id del evento
     * @param presentacionId
     * @return estado de cuenta
     */
    @Override
    public List<EstadoCuentaRS> estadoCuentaPRC(Date fechaInicio, Date fechaFin, Integer organizacionId, Integer eventoId, Integer presentacionId) {
        String spName = "estadoCuentaPRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        StringBuilder sbResult = new StringBuilder();
        try {
            sbResult.append("fechaInicio: " + fechaInicio);
            sbResult.append("fechaFin: " + fechaFin);
            sbResult.append("organizacionId: " + organizacionId);
            sbResult.append("eventoId: " + eventoId);
            spList.add(new SPParametro("fechaInicio", fechaInicio));
            spList.add(new SPParametro("fechaFin", fechaFin));
            spList.add(new SPParametro("organizacionId", organizacionId));
            spList.add(new SPParametro("eventoId", eventoId));
            spList.add(new SPParametro("presentacionId", presentacionId));
            list = reportesPKGDAO.callStoredProcedure(spName, spList);
            sbResult.append("\n Generado");
        } catch (Exception e) {
            System.out.println("error> " + e);
            list =  new ArrayList<EstadoCuentaRS>();
            sbResult.append("\n String: " + e.toString());
            sbResult.append("\n Message: " + e.getMessage());
            sbResult.append("\n LocalizedMessage: " + e.getLocalizedMessage());
            sbResult.append(e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            sbResult.append(sw.toString());
//            EstadoCuentaRS estadoCuentaRS = new EstadoCuentaRS();
//            estadoCuentaRS.setNombreOrganizacion(sbResult.toString());
//            list.add(estadoCuentaRS);
        }
        return list;
    }

     /**
     * Obtiene Estado de Cuenta por un rango de fechas
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param organizacionId id de la organización
     * @param eventoId id del evento
     * @param presentacionId
     * @return estado de cuenta
     */
    @Override
    public List<EstadoCuentaEncabezadoRS> estadoCuentaEncabezadoPRC(Date fechaInicio, Date fechaFin, Integer organizacionId, Integer eventoId, Integer presentacionId) {
        String spName = "estadoCuentaEncabezadoPRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        StringBuilder sbResult = new StringBuilder();
        try {
            sbResult.append("fechaInicio: " + fechaInicio);
            sbResult.append("fechaFin: " + fechaFin);
            sbResult.append("organizacionId: " + organizacionId);
            sbResult.append("eventoId: " + eventoId);
            spList.add(new SPParametro("fechaInicio", fechaInicio));
            spList.add(new SPParametro("fechaFin", fechaFin));
            spList.add(new SPParametro("organizacionId", organizacionId));
            spList.add(new SPParametro("eventoId", eventoId));
            spList.add(new SPParametro("presentacionId", presentacionId));
            list = reportesPKGDAO.callStoredProcedure(spName, spList);
            sbResult.append("\n Generado");
        } catch (Exception e) {
            System.out.println("error> " + e);
            list =  new ArrayList<EstadoCuentaEncabezadoRS>();
            sbResult.append("\n String: " + e.toString());
            sbResult.append("\n Message: " + e.getMessage());
            sbResult.append("\n LocalizedMessage: " + e.getLocalizedMessage());
            sbResult.append(e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            sbResult.append(sw.toString());
//            EstadoCuentaRS estadoCuentaRS = new EstadoCuentaRS();
//            estadoCuentaRS.setNombreOrganizacion(sbResult.toString());
//            list.add(estadoCuentaRS);
        }
        return list;
    }
    
    /**
     * Obtiene Estado de Cuenta Detalle por un rango de fechas
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @param organizacionId id de la organización
     * @param eventoId id del evento
     * @return estado de cuenta
     */
    @Override
    public List<EstadoCuentaDetalleRS> estadoCuentaDetallePRC(Date fechaInicio, Date fechaFin, Integer organizacionId, Integer eventoId, Integer presentacionId) {
        String spName = "estadoCuentaDetallePRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        try {
            spList.add(new SPParametro("fechaInicio", fechaInicio));
            spList.add(new SPParametro("fechaFin", fechaFin));
            spList.add(new SPParametro("organizacionId", organizacionId));
            spList.add(new SPParametro("eventoId", eventoId));
            spList.add(new SPParametro("presentacionId", presentacionId));
            list = reportesPKGDAO.callStoredProcedure(spName, spList);
        } catch (Exception e) {
            System.out.println("error> " + e);
        }
        return list;
    }
}