<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../js/admon/afiliacionBanamex.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/AfiliacionBanamexDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/parcializacionBanamex.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/ParcializacionBanamexDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/terminalBanamex.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/TerminalBanamexDWR.js'/>' type='text/javascript'></script>
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
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewAfiliacionBanamex'><s:text name='key_comision_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveAfiliacionBanamex'><s:text name='key_comision_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_comision_title' value='<s:text name='key_comision_title'/>'/>
        <input type='hidden' id='key_comision_modificacionUsuario' value='<s:text name='key_comision_modificacionUsuario'/>'/>
        <input type='hidden' id='key_comision_modificacionFecha' value='<s:text name='key_comision_modificacionFecha'/>'/>
        <input type='hidden' id='afiliacionBanamexGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='afiliacionBanamexGridOrderByType' value='des'/>
        <input type='hidden' id='afiliacionBanamexGridCurrentPage' value='1'/>
        <input type='hidden' id='afiliacionBanamexGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <input type='hidden' id='title_Parcializacion' value='<s:text name='key_afiliacion_parcialidades'/>'/>
        <s:hidden id='gridVisibleAfiliacionBanamex' name='gridVisibleAfiliacionBanamex'/>
        <s:hidden id='gridIndividualModeAfiliacionBanamex' name='gridIndividualModeAfiliacionBanamex'/>

        <s:form id='afiliacionbanamexform' name='afiliacionbanamexform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='afiliacion_afiliacionBanamexIdText'>
                    <s:text name='key_rol_rolid'/>
                </div>
                <s:textfield name='afiliacionBanamexId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='afiliacion_tipoCanalText'>
                    <s:text name='key_afiliacion_tipoCanal'/>
                </div>
               <s:select list='tipoCanalList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='tipoCanalVentaId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star' id='afiliacion_canalVentaText'>
                    <s:text name='key_afiliacion_canalVenta'/>
                </div>
                <s:select list='canalVentaList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='canalVentaId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4' >
<!--            <button type="button" class='pull-right btn btn-primary button-custom mb-xs mt-xs mr-xs' id="btnParcializaciones"><s:text name='key_afiliacion_parcialidades'/></button>
            </div>
            <div class='pair col-sm-2'>
                 <div class='pull-left btn btn-primary button-custom mb-xs mt-xs mr-xs' id='btnDesplegar'><s:text name='key_generic_desplegar'/></div>-->
                <div class='pull-right btn btn-primary button-custom mb-xs mt-xs mr-xs' id='btnDesplegar'><s:text name='key_generic_desplegar'/></div>
                <button type="button" class='pull-right btn btn-primary button-custom mb-xs mt-xs mr-xs' id="btnParcializaciones"><s:text name='key_afiliacion_parcialidades'/></button>
            </div>
            <div class='pair col-sm-6'>
                <div class='etiqueta required_star col-xs-10_star' id='afiliacion_afiliacionText'>
                    <s:text name='key_afiliacion_afiliacion'/>
                </div>
                <s:textfield name='afiliacionBanamex' cssClass='textbox form-control' maxLength='15'/>
            </div>
            <div class='paid col-sm-6'>
                <div class='etiqueta' id='afiliacion_urlText'>
                    <s:text name='key_afiliacion_url'/>
                </div>
                <s:textfield name='url' cssClass='textbox form-control' maxLength='100'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_claveinstitucionText'>
                    <s:text name='key_afiliacionbanamex_nombreEmpresa'/>
                </div>
                <s:textfield name='nombreEmpresa' cssClass='textbox form-control'  maxLength='600'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_claveinstitucionText'>
                    <s:text name='key_afiliacion_claveinstitucion'/>
                </div>
                <s:textfield name='claveInstitucionBancaria' cssClass='textbox form-control' disabled="true" maxLength='30'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_commandText'>
                    <s:text name='key_afiliacionbanamex_command'/>
                </div>
                <s:textfield name='command' cssClass='textbox form-control' maxLength='20'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_versionText'>
                    <s:text name='key_afiliacionbanamex_version'/>
                </div>
                <s:select list='versionList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='versionId' cssClass='select form-control' />
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_codigoText'>
                    <s:text name='key_afiliacionbanamex_codigo'/>
                </div>
                <s:textfield name='accessCode' cssClass='textbox form-control' maxLength='50'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_merchantText'>
                    <s:text name='key_afiliacionbanamex_merchant'/>
                </div>
                <s:textfield name='merchant' cssClass='textbox form-control' maxLength='15'/>
            </div>
              <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_tipomonedaText'>
                    <s:text name='key_afiliacionotros_tipomoneda'/>
                </div>
                <s:select list='tipoMonedaList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='currency' cssClass='select form-control' />
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_secureText'>
                    <s:text name='key_afiliacionbanamex_secure'/>
                </div>
                <s:textfield name='secureScret' cssClass='textbox form-control' maxLength='100'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_actionurlText'>
                    <s:text name='key_afiliacionbanamex_actionurl'/>
                </div>
                <s:textfield name='actionUrl' cssClass='textbox form-control' disabled="true" maxLength='100'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_returnurlText'>
                    <s:text name='key_afiliacionbanamex_returnurl'/>
                </div>
                <s:textfield name='returnUrl' cssClass='textbox form-control' disabled="true" maxLength='100'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_cashbackText'>
                    <s:text name='key_afiliacionbanamex_cashback'/>
                </div>
                <s:textfield name='trnCashbackAmount' cssClass='textbox form-control isNumericInteger' maxLength='999'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_tipamountText'>
                    <s:text name='key_afiliacionbanamex_tipamount'/>
                </div>
                <s:textfield name='trnTipAmount' cssClass='textbox form-control isNumericInteger' maxLength='999'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_cruidText'>
                    <s:text name='key_afiliacionbanamex_cruid'/>
                </div>
                <s:textfield name='trnCurId' cssClass='textbox form-control isNumericInteger' disabled="true" maxLength='5'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_cancelacionurlText'>
                    <s:text name='key_afiliacionbanamex_cancelacionurl'/>
                </div>
                <s:textfield name='urlCancelacion' cssClass='textbox form-control' disabled="true" maxLength='200'/>
            </div>
            <div class='paid col-sm-4'>
                <div class='etiqueta' id='afiliacion_devolucionurlText'>
                    <s:text name='key_afiliacionbanamex_devolucionurl'/>
                </div>
                <s:textfield name='urlDevolucion' cssClass='textbox form-control' disabled="true" maxLength='200'/>
            </div>
            <div class='pair col-sm-2'>
                <div class='etiqueta' id='afiliacion_afiliacionDefaultText' style="margin-top: 25px">
                    <s:checkbox name='afiliacionDefault' cssClass='checkbox' > &nbsp; </s:checkbox>  
                    <s:text name='key_afiliacionsantander_afiliacion_por_default'/>
                </div> 
            </div>
            <div class='paid col-sm-4'>
                <div class='button-container'>
                    <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id="btnTerminales">
                        <s:text name='key_afiliacionbanamex_terminales'/>
                    </div>
                </div>
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
            
            <div class='pair-hidden '>
                <s:textfield name='validaBanamex'  cssClass='isNumericInteger'/>
            </div>
            <div class='pair-hidden '>
                <s:textfield name='tieneParcializacion' cssClass='textbox'/>
            </div>
            <div class='pair-hidden '>
                <s:textfield name='tieneTerminales' cssClass='textbox'/>
            </div>
            <div class='pair-hidden'>
                <s:select list='eliminadoAfiliacionBanamex' listKey='valor' listValue='nombre' name='eliminadoId' cssClass='select' />
            </div>
            <div class='pair-hidden'>
                <s:select list='estatusList' listKey='valor' listValue='nombre' name='estatusId' cssClass='select' />
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='afiliacionBanamexGrid'></table>
            <div id='afiliacionBanamexGridPagerId'></div>
        </div>
        <div class='pair-wrap divisor'></div>
        <br>
    </div>
    <jsp:include page="/jsp/admon/parcializacionBanamex.jsp" />
    <jsp:include page="/jsp/admon/terminalBanamex.jsp" />
    <br>
     <jsp:include page="/jsp/admon/bitacora.jsp" />
</body>

