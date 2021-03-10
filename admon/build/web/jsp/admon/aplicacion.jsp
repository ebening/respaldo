<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/ParametroDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <link type="text/css" href="<s:url value="../css/customcss/custom-css-jqgrid.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/AplicacionDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/aplicacion.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/parametro.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewAplicacion'><s:text name='key_aplicacion_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveAplicacion'><s:text name='key_aplicacion_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
            
        <input type='hidden' id='key_aplicacion_title' value='<s:text name='key_aplicacion_title'/>'/>
        <input type='hidden' id='key_aplicacion_modificacionUsuario' value='<s:text name='key_aplicacion_modificacionUsuario'/>'/>
        <input type='hidden' id='key_aplicacion_modificacionFecha' value='<s:text name='key_aplicacion_modificacionFecha'/>'/>
        <input type='hidden' id='aplicacionGridOrderByColumn' value='<s:text name='key_orderby_column_modificacion_fecha'/>'/>
        <input type='hidden' id='aplicacionGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
        <input type='hidden' id='aplicacionGridCurrentPage' value='<s:text name='key_current_page'/>'/>
        <input type='hidden' id='aplicacionGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
        <input type='hidden' id='key_tableName' value='<s:text name='key_seguridad_aplicacion_parametro'/>'/>
        <input type='hidden' id='key_propertyName' value='<s:text name='key_properti_aplicacion'/>'/>
        <input type='hidden' id='objectNameJS' value='<s:text name='aplicacionJS'/>'/>
            
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleAplicacion' name='gridVisibleAplicacion'/>
        <s:hidden id='gridIndividualModeAplicacion' name='gridIndividualModeAplicacion'/>

        <s:form id='aplicacionform' name='aplicacionform' cssClass='pair-wrap'>
            <div class='pair-hidden'>
                <div class='etiqueta' id='aplicacion_lenguajeText'>
                    <s:text name='key_aplicacion_lenguaje'/>
                </div>
                    <s:select list='lenguajes' listKey='lenguajeId' listValue='nombre' disabled="true"
                	name='lenguajeId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_aplicacionIdText'>
                    <s:text name='key_aplicacion_aplicacionid'/>
                </div>
                <s:textfield name='aplicacionId' value='0' cssClass='isNumericInteger form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='aplicacion_nombreText'>
                    <s:text name='key_aplicacion_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='50' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='aplicacion_descripcionText'>
                    <s:text name='key_aplicacion_descripcion'/>
                </div>
                <s:textfield name='descripcion' maxLength='100' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_estatusIdText'>
                    <s:text name='key_aplicacion_estatusid'/>
                </div>
                <s:select list='estatusAplicacion' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>

            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_eliminadoIdText'>
                    <s:text name='key_aplicacion_eliminadoid'/>
                </div>
                <s:select list='eliminadoAplicacion' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_creacionFechaText'>
                    <s:text name='key_aplicacion_creacionfecha'/>
                </div>
                <s:textfield name='creacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_creacionUsuarioText'>
                    <s:text name='key_aplicacion_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_modificacionFechaText'>
                    <s:text name='key_aplicacion_modificacionFecha'/>
                </div>
                <s:textfield name='modificacionFecha' cssClass='textbox isDate form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='aplicacion_modificacionUsuarioText'>
                    <s:text name='key_aplicacion_modificacionUsuario'/>
                </div>
                <s:textfield name='modificacionUsuario' maxLength='20' cssClass='textbox isNumericInteger form-control'/>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='aplicacionGrid'></table>
            <div id='aplicacionGridPagerId'></div>
        </div>
        <jsp:include page="/jsp/admon/parametro.jsp" />
        <br>
    </div>
</body>

