<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<!-- Parametro -->
<div id="perfilAplicacionDiv" class='pair-hidden'>
            <div class='pair-wrap divisor'></div>
            <div class='pair-wrap' style="background-color: #F6F6F6;">
                <div class='title-text alert alert-info'><s:text name='key_perfilaplicacion_title'/>
                    <!-- BotÃ³n Agregar -->
                    <div class='button-container-detalle'>
                        <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPerfilAplicacion'><s:text name='key_parametro_bnew'/></div>
                        <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddPerfilAplicacion'><s:text name='key_parametro_badd'/></div>
                          <div style='clear: both'></div>
                    </div>
                </div> 
                <div style='clear: both'></div>
            <input type='hidden' id='key_perfilAplicacion_title' value='<s:text name='key_perfilaplicacion_title'/>'/>
            <input type='hidden' id='perfilAplicacionGridOrderByColumn' value='<s:text name='key_orderby_column_modificacion_fecha'/>'/>
            <input type='hidden' id='perfilAplicacionGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
            <input type='hidden' id='perfilAplicacionGridCurrentPage' value='<s:text name='key_current_page'/>'/>
            <input type='hidden' id='perfilAplicacionGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
                <s:form id='perfilAplicacionform' name='perfilAplicacionform' cssClass='pair-wrap'>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='perfilaplicacion_usuarioIdText'>
                            <s:text name='key_perfilaplicacion_usuario'/>
                        </div>
                        <s:textfield name='usuarioId' value='0' cssClass='isNumericInteger form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='perfilaplicacion_organizacionIdText'>
                            <s:text name='key_parametro_parametroid'/>
                        </div>
                        <s:textfield name='organizacionId' value='0' cssClass='isNumericInteger form-control' />
                    </div>
                   
                    <div class='pair-hidden'>
                        <div class='etiqueta' id='perfilaplicacion_descripcionText'>
                            <s:text name='key_perfilaplicacion_descripcion'/>
                        </div>
                        <s:textfield name='descripcion' cssClass='textbox form-control'/>
                    </div>
                    
                    
                    <div class='pair col-sm-4'>
                        <div class='etiqueta required_star col-xs-10_star' id='perfilaplicacion_aplicacionIdText'>
                            <s:text name='key_organizacionusuario_aplicacion'/>
                        </div>
                            <s:select list='organizacionUsuarioAplicaciones' listKey='aplicacionId' listValue='nombre' name='aplicacionId' cssClass='select form-control ' onchange="perfilAplicacionJS.getPerfiles();"/>
                    </div>
                    <div class='pair col-sm-4 '>
                        <div class='etiqueta required_star col-xs-10_star' id='perfilaplicacion_perfilIdText'>
                            <s:text name='key_perfilaplicacion_perfil'/>
                        </div>
                        <s:select list='organizacionUsuarioPerfiles' listKey='seguridadPerfilId' listValue='nombre' name='seguridadPerfilId' cssClass='select form-control ' />
                     </div>
                    <div style='clear: both'></div>
                </s:form>
                 <div class='pair-wrap divisor'></div>
                <div class='grid-container'>
                    <table id='perfilAplicacionGrid'></table>
                    <div id='perfilAplicacionGridPagerId'></div>
                </div>
            </div>
</div>

