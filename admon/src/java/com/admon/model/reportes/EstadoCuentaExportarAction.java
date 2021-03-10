package com.admon.model.reportes;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.util.StringUtils;

import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.model.BaseModel;
import com.admon.pkg.entity.EstadoCuentaDetalleRS;
import com.admon.pkg.entity.EstadoCuentaEncabezadoRS;

public class EstadoCuentaExportarAction extends BaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HttpServletRequest servletRequest;
    public HttpServletResponse servletResponse;
    public String filename;
    public String reportName;
    public String format;
    public String reportKey;
    private ConfiguracionParametroBss configuracionParametroBss;
    private Map<String, CellStyle> styles;

    @SuppressWarnings("unchecked")
	@Override
    public String execute() {
    	styles = new HashMap<>();
        setFilename(getServletRequest().getParameter("reportName"));
    	Workbook wb = new HSSFWorkbook();
    	Integer cellIndex = 0;
    	Integer rowIndex = 0;
    	Integer rowResumen = 0;
    	Integer rowDetalle = 0;
    	Float height = 1.5f;
        try {
        	
        	setReportKey(getServletRequest().getParameter("reportKey"));
            List<String> listKeysECRS = new ArrayList<>();
            listKeysECRS = Arrays.asList(getReportKey().split("_"));
            List<EstadoCuentaEncabezadoRS> listEstadoCuentaEncabezadoRS = getReportData(listKeysECRS.get(0));
            List<EstadoCuentaDetalleRS> listEstadoCuentaDetalleRS = getReportData(listKeysECRS.get(1));
            
            CellStyle titleStyle = getTitleStyle(wb);
            CellStyle globalHeaderStyle = getGlobalHeaderStyle(wb);
            CellStyle globalHeaderBoldStyle = getGlobalHeaderBoldStyle(wb);
            CellStyle headerStyle = getHeaderStyle(wb);
            
            
        	Sheet sheet = wb.createSheet("Recibo de cliente");
        	Row row = null;
        	Cell cell = null;
        	
        	InputStream my_banner_image = getServletRequest().getServletContext().getResourceAsStream("/images/taasgo.png");
            byte[] bytes = IOUtils.toByteArray(my_banner_image);
            int my_picture_id = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            
        	ClientAnchor my_anchor = new HSSFClientAnchor();
            my_anchor.setCol1(1);
            my_anchor.setRow1(2);
            HSSFPatriarch drawing1 = (HSSFPatriarch)sheet.createDrawingPatriarch();
            HSSFPicture  my_picture = drawing1.createPicture(my_anchor, my_picture_id);
            my_banner_image.close();
        	
        	
            /**
             * HEADER GLOBAL
             */
            Integer finalHeaderRow = -1;
            Integer initHeaderRow = -1;
            Integer columnFixed = 4;
            Cell cellTotal = null;
            if(listEstadoCuentaEncabezadoRS!=null && listEstadoCuentaEncabezadoRS.size()>0){
            	EstadoCuentaEncabezadoRS orgData = listEstadoCuentaEncabezadoRS.get(0);
            	
	            rowIndex = 2;
	            initHeaderRow = rowIndex++;
	            row = sheet.createRow(initHeaderRow);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue("Nombre o Raz\u00F3n Social");
	            cell.setCellStyle(globalHeaderBoldStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue(orgData.getNombreOrganizacion());
	            cell.setCellStyle(globalHeaderStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue("Nombre Comercial");
	            cell.setCellStyle(globalHeaderBoldStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue(orgData.getNombreComercial());
	            cell.setCellStyle(globalHeaderStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue("RFC");
	            cell.setCellStyle(globalHeaderBoldStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue(orgData.getRFC());
	            cell.setCellStyle(globalHeaderStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue("Domicilio");
	            cell.setCellStyle(globalHeaderBoldStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue(orgData.getDomicilio());
	            cell.setCellStyle(globalHeaderStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue("Monto Total");
	            cell.setCellStyle(globalHeaderBoldStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            Row montoRow = sheet.createRow(rowIndex++);
	        	cellTotal = montoRow.createCell(columnFixed);
	        	CellStyle montoStyle = wb.createCellStyle();
	        	montoStyle.cloneStyleFrom(globalHeaderStyle);
	        	montoStyle.setAlignment(CellStyle.ALIGN_LEFT);
	        	addMoneyFormatLeft(wb, montoStyle);
	        	cellTotal.setCellStyle(montoStyle);
	            montoRow.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            
	            Row fechaLimiteRow = sheet.createRow(rowIndex++);
	        	cell = fechaLimiteRow.createCell(columnFixed);
	        	cell.setCellValue("Fecha L\u00EDmite de Pago");
	            cell.setCellStyle(globalHeaderBoldStyle);
	            fechaLimiteRow.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            row = sheet.createRow(rowIndex++);
	        	cell = row.createCell(columnFixed);
	        	cell.setCellValue("");
	            cell.setCellStyle(globalHeaderStyle);
	            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
	            
	            finalHeaderRow = rowIndex-1;
	            
	        	cell = montoRow.createCell(1);
	        	cell.setCellValue("Recibo de Consumo");
	            cell.setCellStyle(titleStyle);
	            cell = fechaLimiteRow.createCell(1);
	            Calendar today = Calendar.getInstance();
	            Locale locale = new Locale("es");
	            String monthName = new SimpleDateFormat("MMMM", locale).format(today.getTime());
	            if(monthName!=null)
	            	monthName = StringUtils.capitalize(monthName);
	        	cell.setCellValue(monthName+" "+today.get(Calendar.YEAR));
	            cell.setCellStyle(getTitleReciboStyle(wb));
            
            }
        	/**
        	 * RESUMEN TITULO
        	 */
            rowIndex = 16;
        	rowResumen = rowIndex++;
        	cellIndex = 0;
        	row = sheet.createRow(rowResumen);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("RESUMEN");
            cell.setCellStyle(titleStyle);
            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
        	
            /**
        	 * RESUMEN HEADER
        	 */
        	cellIndex = 0;
        	row = sheet.createRow(rowIndex++);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("NOMBRE EVENTO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("NOMBRE PRESENTACI\u00D3N");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("TIPO BOLETO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("# DE BOLETOS");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("TOTAL");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("MONTO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("DESCUENTO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("CARGO X SERVICIO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("COMISI\u00D3N TAASGO");
        	cell.setCellStyle(headerStyle);

        	rowIndex = processHeader(listEstadoCuentaEncabezadoRS, wb, sheet, cellTotal, rowIndex);
         	
         	/**
        	 * DETALLE TITULO
        	 */
         	rowIndex++;
         	rowDetalle = rowIndex++;
        	cellIndex = 0;
        	row = sheet.createRow(rowDetalle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("DETALLE");
            cell.setCellStyle(titleStyle);
            row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
         	
         	/**
         	 * DETALLE HEADER
         	 */
         	rowIndex++;
         	cellIndex = 0;
        	row = sheet.createRow(rowIndex++);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("NOMBRE EVENTO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("NOMBRE PRESENTACI\u00D3N");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("CANAL VENTA");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("MES FACTURA");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("TIPO BOLETO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("# DE BOLETOS");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("TOTAL");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("MONTO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("DESCUENTO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("CARGO X SERVICIO");
        	cell.setCellStyle(headerStyle);
        	cell = row.createCell(cellIndex++);
        	cell.setCellValue("COMISION TAASGO");
        	cell.setCellStyle(headerStyle);
        	
        	rowIndex = processDetalle(listEstadoCuentaDetalleRS, wb, sheet, rowIndex);
        	
        	rowIndex = addFirma(wb, sheet, rowIndex+8);
         	
         	sheet.addMergedRegion(new CellRangeAddress(rowResumen,rowResumen,0,8));
         	sheet.addMergedRegion(new CellRangeAddress(rowDetalle,rowDetalle,0,10));
         	
         	if(initHeaderRow>-1){
	         	for(int i=initHeaderRow;i<=finalHeaderRow;i++){
	         		sheet.addMergedRegion(new CellRangeAddress(i,i,columnFixed,columnFixed+4));
	         	}
         	}
         	
         	for(int i=0;i<cellIndex;i++){
         		sheet.autoSizeColumn(i);
         	}
         	
         	sheet.setColumnWidth(1, 16000);
         	
         	my_picture.resize();
         	
	        ServletOutputStream outputStream = getServletResponse().getOutputStream();
            getServletResponse().setContentType("application/vnd.ms-excel");
	        getServletResponse().setHeader("Content-Disposition", "attachment;filename=\"" + getFilename().replaceAll(" ", "") + ".xls\"");
	        wb.write(outputStream);
	        outputStream.flush();
	        outputStream.close();
	        
		} catch (Throwable e) {
			e.printStackTrace();
		}
        
        return NONE;
    }
    
    private Integer processHeader(List<EstadoCuentaEncabezadoRS> data, Workbook wb, Sheet sheet, Cell cellTotal, Integer rowIndex){
    	CellStyle summaryStyle = getSummaryStyle(wb);
        CellStyle summaryMoneyStyle = wb.createCellStyle();
        summaryMoneyStyle.cloneStyleFrom(summaryStyle);
        addMoneyFormat(wb, summaryMoneyStyle);

    	/**
    	 * RESUMEN DATOS
    	 * NOMBRE_EVENTO	NOMBRE PRESENTACION 	TIPO BOLETO	# BOLETOS	TOTAL	MONTO	DESCUENTO	CARGO X SERVICIO	COMISION TAASGO
    	 */
        Double total = 0d;
        Double iva = 0d;
        if(data!=null){
	        for(EstadoCuentaEncabezadoRS o: data){
	        	if(iva.doubleValue()==0){
	        		iva = o.getIva();
	        	}
                        if(o.getComisionTaasgo()==null){
                            o.setComisionTaasgo(new Double(0));
                        }
	        	if("TOTAL".equals(o.getTipoBoleto())){
	         		total=Double.valueOf(o.getComisionTaasgo());
	        		continue;
	         	}
	        	Integer isSubtotal = 0;
	        	if("SUB-TOTAL EVENTOS".equals(o.getTipoBoleto()))
	        		isSubtotal = 1;
	        	else if("SUB-TOTAL PRESENTACIONES".equals(o.getTipoBoleto()))
	        		isSubtotal = 2;
	        	
	        	Integer cellIndex=0;
	         	Row row = sheet.createRow(rowIndex++);
	         	Cell cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getNombreEvento());
	         	cell.setCellStyle(getDetailLeftStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getNombrePresentacion());
	         	cell.setCellStyle(getDetailLeftStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getTipoBoleto());
	         	cell.setCellStyle(getDetailCenterStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getNoBoletos());
	         	cell.setCellStyle(getDetailCenterStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getTotal());
	         	cell.setCellStyle(getDetailRightStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getMonto());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getDescuento());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getCargoXServicio());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getComisionTaasgo());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	        }
        }
        
     	/**
     	 * RESUMEN TOTALES
     	 */
        rowIndex++;
     	Integer cellIndex=7;
     	Row row = sheet.createRow(rowIndex++);
     	Cell cell = row.createCell(cellIndex++);
     	cell.setCellValue("TOTAL");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(total);
     	cell.setCellStyle(summaryMoneyStyle);
     	cellIndex=7;
     	row = sheet.createRow(rowIndex++);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue("IVA");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(total*iva);
     	cell.setCellStyle(summaryMoneyStyle);
     	
     	Double granTotal = total+(total*iva);
     	cellIndex=7;
     	row = sheet.createRow(rowIndex++);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue("GRAN TOTAL");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(granTotal);
     	cell.setCellStyle(summaryMoneyStyle);
     	
     	if(cellTotal!=null){
     		cellTotal.setCellValue(granTotal);
     	}
     	return rowIndex;
    }
    
    private Integer processDetalle(List<EstadoCuentaDetalleRS> data, Workbook wb, Sheet sheet, Integer rowIndex){
    	CellStyle summaryStyle = getSummaryStyle(wb);
        CellStyle summaryMoneyStyle = wb.createCellStyle();
        summaryMoneyStyle.cloneStyleFrom(summaryStyle);
        addMoneyFormat(wb, summaryMoneyStyle);
        CellStyle summaryLeftStyle = getSummaryLeftStyle(wb);
    	/**
    	 * DETALLE DATOS
    	 * NOMBRE EVENTO	NOMBRE PRESENTACION 	CANAL VENTA	MES FACTURA	TIPO BOLETO	# BOLETOS	TOTAL	MONTO	DESCUENTO	CARGO X SERVICIO	COMISION TAASGO
    	 */
        Long sNoBoletos = 0l;
        Double sTotal=0d, sMonto=0d, sDescuento=0d, sCargo=0d, sComision=0d;
        Set<String> keys = new HashSet<String>();
        if(data!=null){
	        for(EstadoCuentaDetalleRS o: data){
	        	Boolean repeated = false;
	        	String key = o.getNombreEvento()+o.getNombrePresentacion()+o.getCanalVenta()+o.getMesFactura();
	        	if(keys.contains(key)){
	        		repeated = true;
	        	}else{
	        		keys.add(key);
	        	}
                        if(o.getComisionTaasgo()==null){
                            o.setComisionTaasgo(new Double(0));
                        }
	        	Integer isSubtotal = 0;
	        	Integer cellIndex=0;
	         	Row row = sheet.createRow(rowIndex++);
	         	Cell cell = row.createCell(cellIndex++);
	         	cell.setCellValue(repeated?"":o.getNombreEvento());
	         	cell.setCellStyle(getDetailLeftStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(repeated?"":o.getNombrePresentacion());
	         	cell.setCellStyle(getDetailLeftStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(repeated?"":o.getCanalVenta());
	         	cell.setCellStyle(getDetailCenterStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(repeated?"":o.getMesFactura());
	         	cell.setCellStyle(getDetailCenterStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getTipoBoleto());
	         	cell.setCellStyle(getDetailCenterStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getNoBoletos());
	         	cell.setCellStyle(getDetailCenterStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getTotal());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getMonto());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getDescuento());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getCargoXServicio());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	cell = row.createCell(cellIndex++);
	         	cell.setCellValue(o.getComisionTaasgo());
	         	cell.setCellStyle(getDetailRightMoneyStyle(wb,isSubtotal));
	         	sNoBoletos += o.getNoBoletos();
	            sTotal+=o.getTotal();
	            sMonto+=o.getMonto();
	            sDescuento+=o.getDescuento();
	            sCargo+=o.getCargoXServicio();
	            sComision+=o.getComisionTaasgo();
	        }
        }
        
     	/**
    	 * DETALLE SUMMARY
    	 */
    	Integer cellIndex=0;
     	Row row = sheet.createRow(rowIndex++);
     	Cell cell = row.createCell(cellIndex++);
     	cell.setCellValue("TOTAL");
     	cell.setCellStyle(summaryLeftStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue("");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue("");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue("");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue("");
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(sNoBoletos);
     	cell.setCellStyle(summaryStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(sTotal);
     	cell.setCellStyle(summaryMoneyStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(sMonto);
     	cell.setCellStyle(summaryMoneyStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(sDescuento);
     	cell.setCellStyle(summaryMoneyStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(sCargo);
     	cell.setCellStyle(summaryMoneyStyle);
     	cell = row.createCell(cellIndex++);
     	cell.setCellValue(sComision);
     	cell.setCellStyle(summaryMoneyStyle);
     	
     	return rowIndex;
    }
    
    private Integer addFirma(Workbook wb, Sheet sheet, Integer rowIndex){
    	Integer cellIndex=1;
    	Float height = 1.3f;
     	Row row = sheet.createRow(rowIndex++);
     	Cell cell = row.createCell(cellIndex);
     	CellStyle style = wb.createCellStyle();
    	style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
    	cell.setCellStyle(style);
    	cell = row.createCell(cellIndex+2);
    	cell.setCellStyle(style);
    	cell = row.createCell(cellIndex+3);
    	cell.setCellStyle(style);
    	cell = row.createCell(cellIndex+4);
    	cell.setCellStyle(style);
    	cell = row.createCell(cellIndex+6);
    	cell.setCellStyle(style);
    	cell = row.createCell(cellIndex+7);
    	cell.setCellStyle(style);
    	
    	rowIndex++;
    	row = sheet.createRow(rowIndex++);
        row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
     	cell = row.createCell(cellIndex);
     	cell.setCellStyle(getTitleReciboStyle(wb));
     	cell.setCellValue("Nombre");
     	cell = row.createCell(cellIndex+2);
    	cell.setCellStyle(getTitleReciboStyle(wb));
    	cell.setCellValue("Nombre");
    	cell = row.createCell(cellIndex+6);
    	cell.setCellStyle(getTitleReciboStyle(wb));
    	cell.setCellValue("Nombre");
     	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),cellIndex+2,cellIndex+4));
     	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),cellIndex+6,cellIndex+7));

    	
    	row = sheet.createRow(rowIndex++);
        row.setHeightInPoints((height * sheet.getDefaultRowHeightInPoints()));
     	cell = row.createCell(cellIndex);
     	cell.setCellStyle(getTitleReciboStyle(wb));
     	cell.setCellValue("Puesto");
     	cell = row.createCell(cellIndex+2);
    	cell.setCellStyle(getTitleReciboStyle(wb));
    	cell.setCellValue("Puesto");
    	cell = row.createCell(cellIndex+6);
    	cell.setCellStyle(getTitleReciboStyle(wb));
    	cell.setCellValue("Puesto");
    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),cellIndex+2,cellIndex+4));
     	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),cellIndex+6,cellIndex+7));

    	
     	return rowIndex;
    }
    
    private CellStyle getTitleStyle(Workbook wb){
    	CellStyle s = styles.get("getTitleStyle");
    	if(s==null){
	    	Font titleFont = getTitleFont(wb);
	        CellStyle titleStyle = wb.createCellStyle();
	        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        titleStyle.setFont(titleFont);
	        styles.put("getTitleStyle", titleStyle);
	        return titleStyle;
    	}else{
    		return s;
    	}
    }
    
    private CellStyle getTitleReciboStyle(Workbook wb){
    	CellStyle s = styles.get("getTitleReciboStyle");
    	if(s==null){
    		Font font = wb.createFont();
        	font.setFontHeightInPoints((short)14);
            CellStyle titleStyle = wb.createCellStyle();
            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
            titleStyle.setFont(font);
            styles.put("getTitleReciboStyle", titleStyle);
            return titleStyle;
    	}else{
    		return s;
    	}
    }
    
    
    private CellStyle getGlobalHeaderBoldStyle(Workbook wb){
    	CellStyle s = styles.get("getGlobalHeaderBoldStyle");
    	if(s==null){
    		Font titleFont = getGlobalHeaderFont(wb);
        	titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            CellStyle style = wb.createCellStyle();
            style.setFont(titleFont);
            addGrayBackground(wb, style);
            styles.put("getGlobalHeaderBoldStyle", style);
            return style;
    	}else{
    		return s;
    	}
    	
    }
    
    private CellStyle getGlobalHeaderStyle(Workbook wb){
    	CellStyle s = styles.get("getGlobalHeaderStyle");
    	if(s==null){
        	Font titleFont = getGlobalHeaderValueFont(wb);
            CellStyle style = wb.createCellStyle();
            style.setFont(titleFont);
            addGrayBackground(wb, style);
            styles.put("getGlobalHeaderStyle", style);
            return style;
    	}else{
    		return s;
    	}
    }
    
    private CellStyle getHeaderStyle(Workbook wb){
    	CellStyle s = styles.get("getHeaderStyle");
    	if(s==null){
    		Font headerFont = getNormalBoldFont(wb);
            CellStyle headerStyle = wb.createCellStyle();
        	addFullBorderStyle(headerStyle);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setFont(headerFont);
            styles.put("getHeaderStyle", headerStyle);
            return headerStyle;
    	}else{
    		return s;
    	}
    	
    }
    
    private CellStyle getDetailLeftStyle(Workbook wb, int background){
    	CellStyle s = styles.get("getDetailLeftStyle"+background);
    	if(s==null){
        	Font headerFont = getNormalFont(wb);
            CellStyle style = wb.createCellStyle();
            if(background==1) addSubtotalBackground(wb, style);
            else if(background==2) addGrayBackground(wb, style);
        	addFullBorderStyle(style);
        	style.setAlignment(CellStyle.ALIGN_LEFT);
        	style.setFont(headerFont);
        	styles.put("getDetailLeftStyle"+background, style);
            return style;
    	}else{
    		return s;
    	}
    }
    
    private CellStyle getDetailRightStyle(Workbook wb, int background){
    	CellStyle s = styles.get("getDetailRightStyle"+background);
    	if(s==null){
    		CellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(getDetailLeftStyle(wb, background));
        	style.setAlignment(CellStyle.ALIGN_RIGHT);
        	styles.put("getDetailRightStyle"+background, style);
            return style;
    	}else{
    		return s;
    	}
    }
    
    private CellStyle getDetailCenterStyle(Workbook wb, int background){
    	CellStyle s = styles.get("getDetailCenterStyle"+background);
    	if(s==null){
    		CellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(getDetailLeftStyle(wb, background));
        	style.setAlignment(CellStyle.ALIGN_CENTER);
        	styles.put("getDetailCenterStyle"+background, style);
            return style;
    	}else{
    		return s;
    	}
    }
    
    private CellStyle getDetailRightMoneyStyle(Workbook wb, int background){
    	CellStyle s = styles.get("getDetailRightMoneyStyle"+background);
    	if(s==null){
    		CellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(getDetailRightStyle(wb, background));
            addMoneyFormat(wb, style);
            styles.put("getDetailRightMoneyStyle"+background, style);
            return style;
    	}else{
    		return s;
    	}
        
    }
    
    private CellStyle getSummaryStyle(Workbook wb){
    	CellStyle s = styles.get("getSummaryStyle");
    	if(s==null){
    		Font font = getSummaryFont(wb);
        	CellStyle style = wb.createCellStyle();
        	addFullBorderStyle(style);
            style.setAlignment(CellStyle.ALIGN_RIGHT);
            style.setFont(font);
        	addGrayBackground(wb, style);
        	styles.put("getSummaryStyle", style);
            return style;
    	}else{
    		return s;
    	}
    }
    
    private CellStyle getSummaryLeftStyle(Workbook wb){
    	CellStyle s = styles.get("getSummaryLeftStyle");
    	if(s==null){
    		CellStyle style = wb.createCellStyle();
        	style.cloneStyleFrom(getSummaryStyle(wb));
        	style.setAlignment(CellStyle.ALIGN_LEFT);
        	styles.put("getSummaryLeftStyle", style);
        	return style;
    	}else{
    		return s;
    	}
    }
    
    private void addFullBorderStyle(CellStyle style){
    	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    }
    
    private void addGrayBackground(Workbook wb, CellStyle style){
    	HSSFColor lightGray = setColor((HSSFWorkbook)wb,(byte) 0xF2, (byte)0xF2,(byte) 0xF2);
    	style.setFillForegroundColor(lightGray.getIndex());
    	style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }
    
    private void addSubtotalBackground(Workbook wb, CellStyle style){
    	HSSFColor lightGray = setColor((HSSFWorkbook)wb,(byte) 0xC0, (byte)0xC0,(byte) 0xC0);
    	style.setFillForegroundColor(lightGray.getIndex());
    	style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }
    
    private Font getNormalFont(Workbook wb){
    	Font font = wb.createFont();
    	font.setFontHeightInPoints((short)10);
        return font;
    }
    
    private Font getNormalBoldFont(Workbook wb){
    	Font font = wb.createFont();
    	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    	font.setFontHeightInPoints((short)10);
        return font;
    }
    
    private Font getSummaryFont(Workbook wb){
    	Font font = wb.createFont();
    	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    	font.setFontHeightInPoints((short)11);
        return font;
    }
    
    private Font getTitleFont(Workbook wb){
    	Font font = wb.createFont();
    	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    	font.setFontHeightInPoints((short)16);
        return font;
    }
    
    private Font getGlobalHeaderFont(Workbook wb){
    	Font font = wb.createFont();
    	font.setFontHeightInPoints((short)14);
        return font;
    }
    
    private Font getGlobalHeaderValueFont(Workbook wb){
    	Font font = wb.createFont();
    	font.setFontHeightInPoints((short)13);
        return font;
    }

    private HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            hssfColor= palette.findColor(r, g, b); 
            if (hssfColor == null ){
                palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
                hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hssfColor;
    }
    
    private void addMoneyFormat(Workbook wb, CellStyle style){
    	HSSFDataFormat cf = ((HSSFWorkbook)wb).createDataFormat();
    	String format = "_($* #,##0.00_);_($* (#,##0.00);_($* \"-\"??_);_(@_)";
    	style.setDataFormat(cf.getFormat(format));
    }
    
    private void addMoneyFormatLeft(Workbook wb, CellStyle style){
    	HSSFDataFormat cf = ((HSSFWorkbook)wb).createDataFormat();
    	String format = "_($ #,##0.00_);_($ (#,##0.00);_($ \"-\"??_);_(@_)";
    	style.setDataFormat(cf.getFormat(format));
    }
    
    @Override
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    public ConfiguracionParametroBss getConfiguracionParametroBss() {
        return configuracionParametroBss;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }


}