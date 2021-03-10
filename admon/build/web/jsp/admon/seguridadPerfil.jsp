<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilRolDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/ParametroDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/seguridadPerfil.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/parametro.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/bitacora.js'/>' type='text/javascript'></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewPerfil'><s:text name='key_seguridadperfil_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePerfil'><s:text name='key_seguridadperfil_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        
        <input type='hidden' id='seguridadPerfilId'/>
        <input type='hidden' id='key_seguridadperfil_title' value='<s:text name='key_seguridadperfil_title'/>'/>
        <input type='hidden' id='key_rol_title' value='<s:text name='key_rol_title'/>'/>
        <input type='hidden' id='seguridadPerfil_perfilIdText' value='<s:text name='key_seguridadperfil_perfilid'/>'/>
        <input type='hidden' id='seguridadPerfil_creacionfechaText' value='<s:text name='key_seguridadperfil_creacionfecha'/>'/>
        <input type='hidden' id='seguridadPerfil_creacionusuarioText' value='<s:text name='key_seguridadperfil_creacionusuario'/>'/>
        <input type='hidden' id='seguridadperfil_modificacionUsuarioText' value='<s:text name='key_seguridadperfil_modificacionUsuario'/>'/>
        <input type='hidden' id='seguridadperfil_modificacionFechaText' value='<s:text name='key_seguridadperfil_modificacionFecha'/>'/>
        <input type='hidden' id='seguridadPerfilGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='seguridadPerfilGridOrderByType' value='des'/>
        <input type='hidden' id='seguridadPerfilGridCurrentPage' value='1'/>
        <input type='hidden' id='seguridadPerfilGridRowsByPage' value='10'/>
        <input type='hidden' id='seguridadPerfilRolGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='seguridadPerfilRolGridOrderByType' value='des'/>
        <input type='hidden' id='seguridadPerfilRolGridCurrentPage' value='1'/>
        <input type='hidden' id='seguridadPerfilRolGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <input type='hidden' id='key_tableName' value='<s:text name='key_seguridad_perfil_parametro'/>'/>
        <input type='hidden' id='key_propertyName' value='<s:text name='key_properti_perfil'/>'/>
        <input type='hidden' id='objectNameJS' value='<s:text name='seguridadPerfilRolJS'/>'/>
        <s:hidden id='gridVisibleSeguridadPerfil' name='gridVisibleSeguridadPerfil'/>
        <s:hidden id='gridIndividualModeSeguridadPerfil' name='gridIndividualModeSeguridadPerfil'/>
        <s:hidden id='gridVisibleSeguridadPerfilRol' name='gridVisibleSeguridadPerfilRol'/>
        <s:hidden id='gridIndividualModeSeguridadPerfilRol' name='gridIndividualModeSeguridadPerfilRol'/>
        

        <s:form id='seguridadPerfilform' name='seguridadPerfilform' cssClass='pair-wrap'>
            <div class='pair-hidden'>
                <s:textfield name='seguridadPerfilId' value='0' cssClass='isNumericInteger' />
            </div>
            <div>
                <div class='pair col-sm-4 '>
                <span class='etiqueta-small required_star col-xs-10_star' id='seguridadPerfil_aplicacionText'>
                    <s:text name='key_seguridadperfil_aplicacion'/>
                </span>
                    <s:select list='aplicacion' listKey='aplicacionId' listValue='nombre' name='aplicacionId' cssClass='select form-control ' />
                </div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnDesplegar'><s:text name='key_seguridadperfil_bdesplegar'/></div>
                <div style='clear: both'></div>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta' id='rolParametro_lenguajeText'>
                    <s:text name='key_parametro_lenguaje'/>
                </div>
                    <s:select list='lenguajes' listKey='lenguajeId' listValue='nombre' disabled="true"
                        name='lenguajeId'  cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='seguridadPerfil_nombreText'>
                    <s:text name='key_seguridadperfil_nombre'/>
                </div>
                <s:textfield name='nombre' maxLength='200' cssClass='textbox form-control '/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='seguridadPerfil_descripcionText'>
                    <s:text name='key_seguridadperfil_descripcion'/>
                </div>
                <s:textfield name='descripcion' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden'>
                <s:select list='estatusSeguridadPerfil' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <s:select list='eliminadoSeguridadPerfil' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden '>
                <s:textfield name='creacionFecha' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <s:textfield name='modificacionFecha' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
        </s:form>
   

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='seguridadPerfilGrid'></table>
            <div id='seguridadPerfilGridPagerId'></div>
        </div>
        <jsp:include page="/jsp/admon/parametro.jsp" />
        <br>
         <jsp:include page="/jsp/admon/bitacora.jsp" />
        <div class='pair-wrap pair-hidden' style="background-color: #F6F6F6;" id="perfilRolDiv">
            <!-- Titulo -->
            <input type='hidden' id='key_catalogoparametro_title' value='<s:text name='key_catalogoparametro_title'/>'/>
            <div class='title-text alert alert-info'><s:text name='key_rol_title'/>
                <!-- Boton Agregar -->
                <div class='button-container-detalle'>
                    <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddRol'><s:text name='key_catalogoparametro_badd'/></div>
                    <div style='clear: both'></div>
                </div>
                 <!-- Botón Nuevo -->
                <div class='button-container-detalle'>
                    <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewRol'><s:text name='key_seguridadperfil_bnew'/></div>
                    <div style='clear: both'></div>
                </div>
            </div>
            <div style='clear: both'></div>
            <s:form id='seguridadPerfilRolform' name='seguridadPerfilRolform' cssClass='pair-wrap'>
               <div class='pair-hidden '>
                    <div class='etiqueta' id='configuracionParametro_filaIdText'>
                        filaId
                    </div>
                    <s:textfield name='filaId' value='0' cssClass='isNumericInteger form-control' />
                </div>
                <div class='pair-hidden col-sm-4'>
                    <s:textfield name='nombre' maxLength='200' cssClass='textbox form-control '/>
                </div>
                <div class='pair col-sm-3 '>
                    <span class='etiqueta-small  required_star col-xs-10_star' id='seguridadPerfil_rolText'>
                        <s:text name='key_rol_title'/>
                    </span>
                    <s:select list='rol' listKey='rolId' listValue='nombre' name='rolId' cssClass='select form-control' />
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='seguridadPerfilRolId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair col-sm-4 pair-hidden'>
                    <s:textfield name='perfilId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='creacionFecha' cssClass='textbox form-control isDate'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='creacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='modificacionFecha' cssClass='textbox form-control isDate'/>
                </div>
                <div class='pair-hidden '>
                    <s:textfield name='modificacionUsuario' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
                </div>
                <div style='clear: both'></div>
            </s:form>
            <div class='pair-wrap divisor'></div>
            <div class='grid-container'>
                <table id='seguridadPerfilRolGrid'></table>
                <div id='seguridadPerfilRolGridPagerId'></div>
            </div>
            <div class='pair-wrap divisor'></div>
            <div style='clear: both'></div>
           
        </div>
    </div>
</body>

