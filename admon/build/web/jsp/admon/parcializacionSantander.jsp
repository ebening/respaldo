<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<div id="parcialidadesSantanderDiv" title="Parcialidades" class='pair-hidden '>
    <div class='form-wrap panel-body'>
                
        <div class='pair-wrap' style="background-color: #F6F6F6;">
            <div class='title-text alert alert-info'><s:text name='key_parcializacionSantander_title'/>
                <!-- BotÃ³n Agregar -->
                <div class='button-container-detalle'>
                    <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewParcializacionSantander'><s:text name='key_afiliacion_bnew'/></div>
                    <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddParcializacionSantander'><s:text name='key_afiliacion_badd'/></div>
                    <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddParcializacionsantanderNoValidation'><s:text name='key_afiliacion_badd'/></div>
                    <div style='clear: both'></div>
                </div>
            </div> 

            <div style='clear: both'></div>

        <!-- Hidden -->
            
        <input type='hidden' id='key_parcializacionSantander_title' value='<s:text name='key_parcializacionSantander_title'/>'/>
        <input type='hidden' id='parcializacionSantanderGridOrderByColumn' value='<s:text name='key_orderby_column_modificacion_fecha'/>'/>
        <input type='hidden' id='parcializacionSantanderGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
        <input type='hidden' id='parcializacionSantanderGridCurrentPage' value='<s:text name='key_current_page'/>'/>
        <input type='hidden' id='parcializacionSantanderGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
        
            
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleParcializacionSantander' name='gridVisibleParcializacionSantander'/>
        <s:hidden id='gridIndividualModeParcializacionSantander' name='gridIndividualModeParcializacionSantander'/>

        <s:form id='parcializacionSantanderform' name='parcializacionSantanderform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='parcializacionSantander_parcializacionSantanderIdText'>
                    <s:text name='key_parcializacionSantander_parcializacionSantanderid'/>
                </div>
                <s:textfield name='parcializacionSantanderId' value='0' cssClass='isNumericInteger form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='parcializacionSantander_afiliacionText'>
                    <s:text name='key_afiliacion_afiliacion'/>
                </div>
                <s:textfield name='afiliacion' maxLength='10' cssClass='textbox form-control'  disabled="true"/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='afiliacion_claveInstitucionBancariaText'>
                    <s:text name='key_afiliacion_claveinstitucion'/>
                </div>
                <s:textfield name='claveInstitucionBancaria' cssClass='textbox form-control' disabled="true" maxLength='30'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='parcializacionSantander_parcializacionText'>
                    <s:text name='key_parcializacion'/>
                </div>
                <s:textfield name='parcializacion' maxLength='38' cssClass='textbox form-control'/>
            </div>
                        <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='parcializacionSantander_merchantText'>
                    <s:text name='key_parcializacion_merchant'/>
                </div>
                <s:textfield name='merchant' maxLength='10' cssClass='textbox form-control'/>
            </div>
                        <div class='pair col-sm-4'>
                <div class='etiqueta' id='parcializacionSantander_semilla3dsText'>
                    <s:text name='key_parcializacion_semila_3ds'/>
                </div>
                <s:textfield name='semilla3ds' maxLength='50' cssClass='textbox form-control'/>
            </div>
            
            <div class='pair-hidden '>
                <div class='etiqueta' id='parcializacionSantander_creacionFechaText'>
                    <s:text name='key_parcializacionSantander_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='generico_creacionUsuario'>
                    <s:text name='key_generico_creacionUsuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='generico_modificacionFecha'>
                    <s:text name='key_generico_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='generico_modificacionUsuario'>
                    <s:text name='key_generico_modificacionUsuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='parcializacionSantanderGrid'></table>
            <div id='parcializacionSantanderGridPagerId'></div>
        </div
        <br>
    </div>
</div>

