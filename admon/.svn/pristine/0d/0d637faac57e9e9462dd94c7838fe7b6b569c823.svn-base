<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>  
<!-- Parcialidades Banamex-->
<div id="terminalesBanamexDiv" title="Terminales Banamex"  class='pair-hidden'>
    <div class='pair-wrap divisor'></div>
    <div class='pair-wrap' style="background-color: #F6F6F6;">
        <div class='title-text alert alert-info'><s:text name='key_terminalesbanamex_title'/>
            <!-- Botón Agregar -->
            <div class='button-container-detalle'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewTerminalBanamex'><s:text name='key_afiliacion_bnew'/></div>
                <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddTerminalBanamex'><s:text name='key_afiliacion_badd'/></div>
                <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddParcializacionbanamexNoValidation'><s:text name='key_afiliacion_badd'/></div>
                <div style='clear: both'></div>
            </div>
        </div> 
    <div style='clear: both'></div>
    <input type='hidden' id='key_terminalesbanamex_title' value='<s:text name='key_terminalesbanamex_title'/>'/>
    <input type='hidden' id='terminalBanamexGridOrderByColumn' value='modificacionFecha'/>
    <input type='hidden' id='terminalBanamexGridOrderByType' value='des'/>
    <input type='hidden' id='terminalBanamexGridCurrentPage' value='1'/>
    <input type='hidden' id='terminalBanamexGridRowsByPage' value='10'/>
        <s:form id='terminalesBanamexform' name='terminalesBanamexform' cssClass='pair-wrap'>
             <div class='pair-hidden '>
                <s:textfield name='terminalBanamexId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-5'>
                <div class='etiqueta required_star col-xs-10_star' id='afiliacion_afiliacionText'>
                    <s:text name='key_afiliacion_afiliacion'/>
                </div>
                <s:textfield name='afiliacionBanamex' cssClass='textbox form-control' disabled="true"/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='terminalesbanamex_accessCodeText'>
                    <s:text name='key_terminalesbanamex_accesscode'/>
                </div>
                <s:textfield name='accessCode' cssClass='textbox form-control' maxLength='20' />
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='terminalesbanamex_merchantText'>
                    <s:text name='key_terminalesbanamex_merchant'/>
                </div>
                <s:textfield name='merchant' cssClass='textbox form-control' maxLength='30'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='terminalesbanamex_puntoventaText'>
                    <s:text name='key_terminalesbanamex_puntoventa'/>
                </div>
                <s:textfield name='puntoVentaTerminal' cssClass='textbox form-control'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='terminalesbanamex_cancelacionText'>
                    <s:text name='key_terminalesbanamex_cancelacion'/>
                </div>
                <s:textfield name='cancelacion' cssClass='textbox form-control'/>
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
            <table id='terminalBanamexGrid'></table>
            <div id='terminalBanamexGridPagerId'></div>
        </div>
    </div>
</div>

