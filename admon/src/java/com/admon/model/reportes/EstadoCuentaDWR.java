package com.admon.model.reportes;

import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.OrganizacionBss;
import com.admon.bss.reportes.EstadoCuentaBss;
import com.admon.entity.admon.Grid;
import com.admon.entity.admon.Organizacion;
import com.admon.entity.reportes.Evento;
import com.admon.entity.reportes.Presentacion;
import com.admon.model.BaseModel;
import com.admon.pkg.bss.ReportesPKGBss;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaEncabezadoRS;
import com.admon.pkg.entity.EstadoCuentaRS;
import java.util.ArrayList;
import java.util.List;

public class EstadoCuentaDWR extends BaseModel {

    private EstadoCuentaBss estadoCuentaBss;
    private ReportesPKGBss reportesPKGBss;
    private OrganizacionBss organizacionBss;
    private ConfiguracionParametroBss configuracionParametroBss;

    public EstadoCuentaDWR() {
    }

    public Grid findByCriteria(int p, int maxResult, String order, String ordenTipo, EstadoCuentaRS estadoCuentaRS) {
        Grid grid = new Grid();
        List<EstadoCuentaRS> listEstadoCuentaRS = new ArrayList<>();
        if(estadoCuentaRS!=null){
        listEstadoCuentaRS = reportesPKGBss.estadoCuentaPRC(
                estadoCuentaRS.getFechaPagoInicio(), estadoCuentaRS.getFechaPagoFin(),
                estadoCuentaRS.getOrganizacionId(), estadoCuentaRS.getEventoId(), estadoCuentaRS.getPresentacionId());
            int resultadosTotales = listEstadoCuentaRS.size();
            grid.setGrid(listEstadoCuentaRS);
            grid.setTotal(resultadosTotales);
            grid.setPaginas(1);
        }else{
            grid.setGrid(listEstadoCuentaRS);
            grid.setTotal(0);
            grid.setPaginas(0);
        }
        return grid;
    }

    public String reporteEstadoCuentaXLS(EstadoCuentaRS estadoCuentaRS) {
        try {
            
            StringBuilder sbKey = new StringBuilder();
            List<EstadoCuentaEncabezadoRS> listEstadoCuentaRS = new ArrayList<>();
            listEstadoCuentaRS = reportesPKGBss.estadoCuentaEncabezadoPRC(
                    estadoCuentaRS.getFechaPagoInicio(), estadoCuentaRS.getFechaPagoFin(),
                    estadoCuentaRS.getOrganizacionId(), estadoCuentaRS.getEventoId(), estadoCuentaRS.getPresentacionId());
            if(!listEstadoCuentaRS.isEmpty()){
               EstadoCuentaEncabezadoRS encabezado = listEstadoCuentaRS.get(0);
               Organizacion o = new Organizacion();
               o.setOrganizacionId(estadoCuentaRS.getOrganizacionId());
               Organizacion organizacion = organizacionBss.findByIdIgnore(o.getOrganizacionId());
               
               if(organizacion != null){
                    encabezado.setOrganizacionId(organizacion.getOrganizacionId());
                    encabezado.setNombreOrganizacion(organizacion.getNombre());
                    encabezado.setNombreComercial(organizacion.getNombreComercial());
                    encabezado.setRFC(organizacion.getRfc());
                    encabezado.setDomicilio(organizacion.getDireccion());
                    estadoCuentaRS.setNombreOrganizacion(encabezado.getNombreOrganizacion());
               }

               //Integer configuracionId = 58; //TODO: obtener de properties
//               estadoCuentaRS.setConfiguracionId(97);
//               estadoCuentaRS.setIvaKey("IVA");
               String iva = configuracionParametroBss.getValor(estadoCuentaRS.getConfiguracionId(), estadoCuentaRS.getIvaKey());
               if(iva==null) iva="0.16"; //DEFAULT
               encabezado.setIva(Double.valueOf(iva));
            }
            String keyECRS = estadoCuentaBss.addDataToSession(listEstadoCuentaRS);
            sbKey.append(keyECRS);
            List<EstadoCuentaDetalleRS> listEstadoCuentaDetalleRS = new ArrayList<>();
            listEstadoCuentaDetalleRS = reportesPKGBss.estadoCuentaDetallePRC(
                    estadoCuentaRS.getFechaPagoInicio(), estadoCuentaRS.getFechaPagoFin(),
                    estadoCuentaRS.getOrganizacionId(), estadoCuentaRS.getEventoId(), estadoCuentaRS.getPresentacionId());
            String keyECDetalleRS = estadoCuentaBss.addDataToSession(listEstadoCuentaDetalleRS);
            sbKey.append("_");
            sbKey.append(keyECDetalleRS);
            return sbKey.toString();
        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e.toString());
            return "error::" + e.toString() +" ConfiguracionId:: "+ estadoCuentaRS.getConfiguracionId() + " IvaKey:: "+estadoCuentaRS.getIvaKey() +"Encabezado:: "+estadoCuentaRS.getNombreOrganizacion();
        }
    }

    public List<Organizacion> getOrganizacion() {
        return estadoCuentaBss.getOrganizacion();
    }

    public List<Evento> filtrarEvento(int organizacionId) {
        List<Evento> listEvento = null;
        listEvento = estadoCuentaBss.filtrarEvento(organizacionId);
        Evento evento = new Evento();
        evento.setEventoId(-1);
        evento.setNombre("Todas");
        listEvento.add(1,evento);
        return listEvento;
    }
    
    public List<Presentacion> filtrarPresentacionEvento(int eventoId) {
        List<Presentacion> listPresntaciones = estadoCuentaBss.filtrarPresentacionEvento(eventoId);
        Presentacion presentacion = new Presentacion();
        presentacion.setPresentacionId(-1);
        presentacion.setNombre("Todas");
        listPresntaciones.add(1,presentacion);
        return listPresntaciones;
    }

    public void setEstadoCuentaBss(EstadoCuentaBss estadoCuentaBss) {
        this.estadoCuentaBss = estadoCuentaBss;
    }

    public void setReportesPKGBss(ReportesPKGBss reportesPKGBss) {
        this.reportesPKGBss = reportesPKGBss;
    }
    
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }
    
    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
    
}