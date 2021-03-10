<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>    
<!-- Parcialidades Banamex-->
    <div id="parcialidadesBanamexDiv" title="Parcialización Banamex" class='pair-hidden'>
        <div class='pair-wrap divisor'></div>
        <div class='pair-wrap' style="background-color: #F6F6F6;">
            <div class='title-text alert alert-info'><s:text name='key_parcializacionbanamex_title'/>
                <!-- BotÃ³n Agregar -->
                <div class='button-container-detalle'>
                    <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewParcializacionBanamex'><s:text name='key_afiliacion_bnew'/></div>
                    <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddParcializacionBanamex'><s:text name='key_afiliacion_badd'/></div>
                    <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddParcializacionbanamexNoValidation'><s:text name='key_afiliacion_badd'/></div>
                    <div style='clear: both'></div>
                </div>
            </div> 
        <div style='clear: both'></div>
         <!--<input type='hidden' id='key_parcializacionbanamex_title' value='<s:text name='key_parcializacionbanamex_title'/> -->
        <input type='hidden' id='parcializacionBanamexGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='parcializacionBanamexGridOrderByType' value='des'/>
        <input type='hidden' id='parcializacionBanamexGridCurrentPage' value='1'/>
        <input type='hidden' id='parcializacionBanamexGridRowsByPage' value='10'/>
            <s:form id='parcializacionBanamexform' name='parcializacionBanamexform' cssClass='pair-wrap'>
                 <div class='pair-hidden '>
                    <s:textfield name='parcializacionBanamexId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair col-sm-5'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_afiliacionText'>
                        <s:text name='key_afiliacion_afiliacion'/>
                    </div>
                    <s:textfield name='afiliacionBanamex' cssClass='textbox form-control' disabled="true"/>
                </div>
                <div class='paid col-sm-4'>
                    <div class='etiqueta' id='afiliacion_claveinstitucionText'>
                        <s:text name='key_afiliacion_claveinstitucion'/>
                    </div>
                    <s:textfield name='claveInstitucionBancaria' cssClass='textbox form-control' disabled="true" maxLength='999'/>
                </div>
                <div class='paid col-sm-4'>
                    <div class='etiqueta' id='parcializacionbanamex_planText'>
                        <s:text name='key_parcializacionbanamex_plan'/>
                    </div>
                    <s:textfield name='plan' cssClass='textbox form-control' maxLength='10'/>
                </div>
                <div class='paid col-sm-4'>
                    <div class='etiqueta' id='parcializacionbanamex_claveEstatusText'>
                        <s:text name='key_parcializacionbanamex_clave'/>
                    </div>
                    <s:textfield name='claveEstatus' cssClass='textbox form-control' maxLength='10'/>
                </div>
                <div class='paid col-sm-4'>
                    <div class='etiqueta' id='parcializacionbanamex_plazoText'>
                        <s:text name='key_parcializacionbanamex_plazo'/>
                    </div>
                    <s:textfield name='plazo' cssClass='textbox form-control' maxLength='38'/>
                </div>
                <div class='pair-hidden'>
                    <s:textfield name='tieneParcializacion' cssClass='textbox form-control' maxLength='999'/>
                </div>
                 <div class='pair-hidden'>
                    <s:textfield name='tieneTerminales' cssClass='textbox form-control' maxLength='999'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='modificacionFecha' cssClass='textbox isDate'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='modificacionUsuario'  cssClass='textbox isNumericInteger'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='creacionFecha' cssClass='textbox form-control isDate'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
                </div>
                <div style='clear: both'></div>
            </s:form>
            <div class='pair-wrap divisor'></div>
            <div class='grid-container'>
                <table id='parcializacionBanamexGrid'></table>
                <div id='parcializacionBanamexGridPagerId'></div>
            </div>
        </div>
    </div>

