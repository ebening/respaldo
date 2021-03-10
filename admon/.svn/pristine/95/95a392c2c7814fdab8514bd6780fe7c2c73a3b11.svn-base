<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../js/admon/configuracionFormaPago.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/ConfiguracionFormaPagoDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
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
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewAfiliacion'><s:text name='key_comision_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveAfiliacion'><s:text name='key_comision_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_comision_title' value='<s:text name='key_comision_title'/>'/>
        <input type='hidden' id='key_comision_modificacionUsuario' value='<s:text name='key_comision_modificacionUsuario'/>'/>
        <input type='hidden' id='key_comision_modificacionFecha' value='<s:text name='key_comision_modificacionFecha'/>'/>
        <input type='hidden' id='configuracionFormaPagoGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='configuracionFormaPagoGridOrderByType' value='des'/>
        <input type='hidden' id='configuracionFormaPagoGridCurrentPage' value='1'/>
        <input type='hidden' id='configuracionFormaPagoGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleAfiliacionBanamex' name='gridVisibleAfiliacionBanamex'/>
        <s:hidden id='gridIndividualModeAfiliacionBanamex' name='gridIndividualModeAfiliacionBanamex'/>

        <s:form id='configuracionFormaPagoform' name='configuracionFormaPagoform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='otros_IdText'>
                    <s:text name='key_afiliacionotros_formapagoId'/>
                </div>
                <s:textfield name='configuracionFormaPagoId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-10 title-text alert alert-info center'>
                <div class='etiqueta' id=''>
                    <s:text name='key_afiliacionotros_msj'/>
                </div>               
            </div>
            <div class='pair col-sm-2'>
                     <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnDesplegar'><s:text name='key_generic_desplegar'/></div>
            </div>
            <div style='clear: both'></div>
            
            <div class='pair col-sm-2'>
                <div class='etiqueta required_star col-xs-10_star' id='otros_formapagoIdText'>
                    <s:text name='key_afiliacionotros_formapago'/>
                </div>
               <s:select list='formaPago' listKey='clave' listValue='valor' name='claveFormaPago' onchange="configuracionFormaPagoJS.activarPassword(this);"cssClass='select form-control' />
            </div>
            <div class='pair col-sm-5'>
                <div class='etiqueta ' id='otros_siteurlText'>
                    <s:text name='key_afiliacionotros_siteurl'/>
                </div>
                <s:textfield name='urlSite' cssClass='textbox form-control' disabled="true" maxLength='100'/>
            </div>
            <div class='pair col-sm-3'>
                <div class='etiqueta ' id='otros_modoconexionText'>
                    <s:text name='key_afiliacionotros_modoconexion'/>
                </div>
                <s:textfield name='conectionMode' cssClass='textbox form-control' disabled="true" maxLength='7'/>
            </div>
            <div class='paid col-sm-2'>
                <div class='etiqueta' id='otros_tipomonedaText'>
                    <s:text name='key_afiliacionotros_tipomoneda'/>
                </div>
                <s:select list='tipoMonedaList' listKey='valor' listValue='clave' name='currency' disabled="true" cssClass='select form-control' />
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta' id='otros_afiliacionText'>
                    <s:text name='key_afiliacionotros_afiliacion'/>
                </div>
                <s:textfield name='afiliacion' cssClass='textbox form-control' disabled="true" maxLength='10'/>
            </div>
            <div class='pair col-sm-5'>
                <div class='etiqueta' id='otros_returnurlText'>
                    <s:text name='key_afiliacionotros_returnurl'/>
                </div>
                <s:textfield name='urlReturn' cssClass='textbox form-control'disabled="true" maxLength='100'/>
            </div>
            <div class='pair col-sm-5'>
                <div class='etiqueta' id='otros_cancelurlText'>
                    <s:text name='key_afiliacionotros_cancelurl'/>
                </div>
                <s:textfield name='urlCancel' cssClass='textbox form-control' disabled="true" maxLength='100'/>
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta ' id='otros_usuarioText'>
                    <s:text name='key_afiliacionotros_usuario'/>
                </div>
                <s:textfield name='username' cssClass='textbox form-control' maxLength='100'/>
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta ' id='otros_contractIdText'>
                    <s:text name='key_afiliacionotros_contractId'/>
                </div>
                <s:textfield name='contractId' cssClass='textbox form-control' disabled="true" maxLength='38'/>
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta' id='otros_contraseniaText'>
                    <s:text name='key_afiliacionotros_contrasenia'/>
                </div>
                <s:password name='password' cssClass='textbox form-control' disabled="true" maxLength='100'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta ' id='otros_signatureText'>
                    <s:text name='key_afiliacionotros_signature'/>
                </div>
                <s:textfield name='signature' cssClass='textbox form-control' maxLength='100'/>
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta' id='afiliacion_afiliacionDefaultText' style="margin-top: 25px">
                    <s:checkbox name='afiliacionDefault' cssClass='checkbox' > &nbsp; </s:checkbox>  
                    <s:text name='key_afiliacionsantander_afiliacion_por_default'/>
                </div> 
            </div>
            
            <div class='pair-hidden '>
                <div class='etiqueta' id='generico_estatus'>
                    <s:text name='key_generico_estatus'/>
                </div>
                <s:select list='estatus' listKey='valor' listValue='nombre' name='estatusId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='generico_eliminado'>
                    <s:text name='key_generico_eliminado'/>
                </div>
                <s:select list='eliminado' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select form-control' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='generico_creacionFechaText'>
                    <s:text name='key_generico_creacionfecha'/>
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
            <table id='configuracionFormaPagoGrid'></table>
            <div id='configuracionFormaPagoGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
    <br>
     <jsp:include page="/jsp/admon/bitacora.jsp" />
</body>

