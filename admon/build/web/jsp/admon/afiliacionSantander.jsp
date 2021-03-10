<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <link type="text/css" href="<s:url value="../css/customcss/custom-css-jqgrid.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/AfiliacionSantanderDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/afiliacionSantander.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/parcializacionSantander.js'/>' type='text/javascript'></script>
        <script src='<s:url value='../dwr/interface/SeguridadPerfilDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/ParcializacionesSantanderDWR.js'/>' type='text/javascript'></script>
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
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewAfiliacionSantander'><s:text name='key_generico_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveAfiliacionSantander'><s:text name='key_generico_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
            
        <input type='hidden' id='key_afiliacionSantander_title' value='<s:text name='key_afiliacionSantander_title'/>'/>
        <input type='hidden' id='afiliacionSantanderGridOrderByColumn' value='<s:text name='key_orderby_column_modificacion_fecha'/>'/>
        <input type='hidden' id='afiliacionSantanderGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
        <input type='hidden' id='afiliacionSantanderGridCurrentPage' value='<s:text name='key_current_page'/>'/>
        <input type='hidden' id='afiliacionSantanderGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
        
         
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisibleAfiliacionSantander' name='gridVisibleAfiliacionSantander'/>
        <s:hidden id='gridIndividualModeAfiliacionSantander' name='gridIndividualModeAfiliacionSantander'/>

        <s:form id='afiliacionSantanderform' name='afiliacionSantanderform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='afiliacionSantander_afiliacionSantanderIdText'>
                    <s:text name='key_afiliacionSantander_afiliacionSantanderid'/>
                </div>
                <s:textfield name='afiliacionSantanderId' value='0' cssClass='isNumericInteger form-control' />
            </div>
            <div class="row">
                <div class='pair col-sm-4'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_tipoCanalText'>
                        <s:text name='key_afiliacion_tipoCanal'/>
                    </div>
                        <s:select list='tipoCanalList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='tipoCanalVentaId'  onchange="afiliacionSantanderJS.cambiarValorCripto(this);" cssClass='select form-control' />
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_canalVentaText'>
                        <s:text name='key_afiliacion_canalVenta'/>
                    </div>
                    <s:select list='canalVentaList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='canalVentaId' cssClass='select form-control' />
                </div>
                <div class='pair col-sm-4'>
                    <button type="button"  class='pull-right button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnDesplegar'><s:text name='key_generic_desplegar'/></button>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='afiliacionSantander_parcialidadesText'>
                            <s:text name='key_afiliacion_parcialidades'/>
                        </div>
                        <s:textfield name='tieneParcializacion' cssClass='form-control' />
                    </div>
                    <button type="button" class="pull-right button-custom mb-xs mt-xs mr-xs btn btn-primary" id="btnParcializaciones"><s:text name='key_afiliacion_parcialidades'/></button>

    <!--                <div class='pair col-sm-4'>-->
    <!--                    <div class='pair-hidden '>
                            <div class='etiqueta' id='afiliacionSantander_parcialidadesText'>
                                <%--<s:text name='key_afiliacion_parcialidades'/>--%>
                            </div>
                            <%--<s:textfield name='tieneParcializacion' cssClass='form-control' />--%>
                        </div>
                        <button type="button" class="btn btn-primary" id="btnParcializaciones"><s:text name='key_afiliacion_parcialidades'/></button>-->
                         <!--<div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnParcializaciones'  ><s:text name='key_afiliacion_parcialidades'/></div> -->
    <!--                </div>

                    <div class='pair col-sm-4'>-->
                         <!--<div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnDesplegar'><s:text name='key_generic_desplegar'/></div>-->
    <!--                </div>-->

                </div>
            </div>

            <div style='clear: both'></div>
            <div class="row">
                <div class='pair col-sm-4'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_idCompañiaText'>
                        <s:text name='key_afiliacionsantander_idcompania'/>
                    </div>
                   <s:textfield name='idCompany'  cssClass='textbox form-control isNumericInteger'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacionSantander_afiliacionText'>
                        <s:text name='key_afiliacion_afiliacion'/>
                    </div>
                    <s:textfield name='afiliacion' maxLength='10' cssClass='textbox form-control'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta' id='afiliacionSantander_urlText'>
                        <s:text name='key_afiliacion_url'/>
                    </div>
                    <s:textfield name='url' maxLength='50' disabled="true" cssClass='textbox form-control'/>
                </div>
            </div>
            <div class="row">
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_claveinstitucionText'>
                        <s:text name='key_afiliacion_claveinstitucion'/>
                    </div>
                    <s:textfield name='claveInstitucionBancaria' disabled="true" cssClass='textbox form-control' maxLength='30'/>
                </div>
                <div class='pair col-sm-1'>
                    <div class='etiqueta' id='afiliacion_dataiText'>
                        <s:text name='key_afiliacionsantander_datai'/>
                    </div>
                    <s:textfield name='dataI' cssClass='textbox form-control' maxLength='200'/>
                </div>
                <div class='pair col-sm-1'>
                    <div class='etiqueta ' id='afiliacion_datakText'>
                        <s:text name='key_afiliacionsantander_datak'/>
                    </div>
                    <s:textfield name='dataK' cssClass='textbox form-control' maxLength='200'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_claveoperacionText'>
                        <s:text name='key_afiliacionsantander_claveoperacion'/>
                    </div>
                    <s:textfield name='typec' cssClass='textbox form-control' disabled="true" maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_operacionText'>
                        <s:text name='key_afiliacionsantander_operacion'/>
                    </div>
                    <s:textfield name='operacion' cssClass='textbox form-control' maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_claveEstatusText'>
                        <s:text name='key_afiliacionsantander_clave_estatus'/>
                    </div>
                    <s:textfield name='claveEstatus' cssClass='textbox form-control' maxLength='30'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_merchantText'>
                        <s:text name='key_afiliacion_merchant'/>
                    </div>
                    <s:textfield name='merchant' cssClass='textbox form-control' maxLength='10'/>
                </div>
            </div>
            <div class="row">
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_cryptoText'>
                        <s:text name='key_afiliacionsantander_crypto'/>
                    </div>
                    <s:textfield name='crypto' cssClass='textbox form-control' disabled="true" maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_idBranchText'>
                        <s:text name='key_afiliacionsantander_idbranch'/>
                    </div>
                    <s:textfield name='idBranch' cssClass='textbox form-control' maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_validacionText' style="margin-top: 25px">
                        <s:text name='key_afiliacionsantander_validacion'/>:       
                    </div>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_valida3dsText' style="margin-top: 25px">
                        <s:checkbox name='valida3ds' cssClass='checkbox'  onclick="afiliacionSantanderJS.activarBase3ds(this);" >&nbsp;
                            <s:text name='key_afiliacionsantander_3ds' /> </s:checkbox>       
                    </div>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_validaSdosText' style="margin-top: 25px">
                        <s:checkbox name='validaSdos' cssClass='checkbox' onclick="afiliacionSantanderJS.activarOrderSourceMovil(this);" > &nbsp;
                        <s:text name='key_afiliacionsantander_sdos'/></s:checkbox>
                    </div>
                </div>

                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_empresaAfiliacionIdText'>
                        <s:text name='key_afiliacionsantander_empresa_afiliacionid'/>
                    </div>
                    <s:textfield name='empresaAfiliacionId' cssClass='textbox form-control' maxLength='38'/>
                </div>
            </div>
            <div class="row">
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_nombreEmpresaText'>
                        <s:text name='key_afiliacionsantander_nombreempresa'/>
                    </div>
                    <s:textfield name='nombreEmpresa' cssClass='textbox form-control' maxLength='200'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_descripcionText'>
                        <s:text name='key_afiliacionsantander_descripcion'/>
                    </div>
                    <s:textfield name='descripcion' cssClass='textbox form-control' maxLength='200'/>
                </div>
                <div class='pair col-sm-4'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_direccionEmpresaText'>
                        <s:text name='key_afiliacionsantander_direccion'/>
                    </div>
                    <s:textfield name='direccionEmpresa' cssClass='textbox form-control' maxLength='200'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_countryText'>
                        <s:text name='key_afiliacionsantander_ciudad'/>
                    </div>
                    <s:textfield name='country' cssClass='textbox form-control' maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_tipoMonedaText'>
                        <s:text name='key_afiliacion_tipomoneda'/>
                    </div>
                    <s:select list='tipoMonedaList' listKey='catalogoParametroPK.catalogoParametroId' listValue='valor' name='tipoMonedaId' cssClass='select form-control' />
                    <s:hidden name='tipoMoneda' value='' />
                </div>
            </div>
            <div class="row">
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_baseCobroText'>
                        <s:text name='key_afiliacionsantander_basecobro'/>
                    </div>
                        <s:textfield name='baseCobro' cssClass='textbox form-control' disabled="true" maxLength='30'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_baseCancelacionText'>
                        <s:text name='key_afiliacionsantander_basecancelacion'/>
                    </div>
                        <s:textfield name='baseCancelacion' cssClass='textbox form-control' disabled="true" maxLength='30'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_base3dsText'>
                        <s:text name='key_afiliacionsantander_base3ds'/>
                    </div>
                        <s:textfield name='base3ds' cssClass='textbox form-control'  maxLength='50'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_semillaText'>
                        <s:text name='key_afiliacionsantander_semila'/>
                    </div>
                        <s:textfield name='semilla' cssClass='textbox form-control' maxLength='30'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_semillaConsultaText'>
                        <s:text name='key_afiliacionsantander_semila_consulta'/>
                    </div>
                        <s:textfield name='semillaConsulta' cssClass='textbox form-control'maxLength='30'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_semilla3dsText'>
                        <s:text name='key_afiliacionsantander_semila_3ds'/>
                    </div>
                        <s:textfield name='semilla3ds' cssClass='textbox form-control' disabled="true" maxLength='30'/>
                </div>
            </div>
            <div class="row">
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_orderSourceSdosText'>
                        <s:text name='key_afiliacionsantander_order_source_sdos'/>
                    </div>
                        <s:textfield name='orderSourceSdos' cssClass='textbox form-control' maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_orderSourceMovilText'>
                        <s:text name='key_afiliacionsantander_order_source_movile'/>
                    </div>
                        <s:textfield name='orderSourceMovil' cssClass='textbox form-control' disabled="true" maxLength='5'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_AmexText' style="margin-top: 25px">
                        <s:checkbox name='amex' cssClass='checkbox'  onclick="afiliacionSantanderJS.activarAfiliacionAmex(this);" > &nbsp; </s:checkbox>  
                        <s:text name='key_afiliacionsantander_american_express'/>
                    </div> 
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_afiliacionAmexText'>
                        <s:text name='key_afiliacionsantander_afiliacion_amex'/>
                    </div>
                        <s:textfield name='afiliacionAmex' cssClass='textbox form-control' disabled="true"  maxLength='20'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_merchantAmexText'>
                        <s:text name='key_afiliacionsantander_merchant_amex'/>
                    </div>
                        <s:textfield name='merchantAmex' cssClass='textbox form-control' disabled="true" maxLength='10'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_usuarioText'>
                        <s:text name='key_afiliacionsantander_usuario'/>
                    </div>
                        <s:textfield name='usuario' cssClass='textbox form-control' maxLength='999'/>
                </div>
            </div>
            <div class="row">
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_contrasenaText'>
                        <s:text name='key_afiliacionsantander_contrasena'/>
                    </div>
                        <s:password name='contrasena' cssClass='textbox form-control' maxLength='999'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_usuarioAdminText'>
                        <s:text name='key_afiliacionsantander_usuario_admon'/>
                    </div>
                        <s:textfield name='usuarioAdmin' cssClass='textbox form-control'  maxLength='999'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta required_star col-xs-10_star' id='afiliacion_contrasenaAdminText'>
                        <s:text name='key_afiliacionsantander_contrasena_admin'/>
                    </div>
                        <s:password name='contrasenaAdmin' cssClass='textbox form-control'  maxLength='999'/>
                </div>
                <div class='pair col-sm-2'>
                    <div class='etiqueta' id='afiliacion_afiliacionDefaultText' style="margin-top: 25px">
                        <s:checkbox name='afiliacionDefault' cssClass='checkbox'> &nbsp; </s:checkbox>  
                        <s:text name='key_afiliacionsantander_afiliacion_por_default'/>
                    </div> 
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
                <div class='etiqueta' id='afiliacionSantander_creacionFechaText'>
                    <s:text name='key_afiliacionSantander_creacionfecha'/>
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
            <table id='afiliacionSantanderGrid'></table>
            <div id='afiliacionSantanderGridPagerId'></div>
        </div
        <br>
    </div>
    <jsp:include page="/jsp/admon/parcializacionSantander.jsp" />
    <br>
     <jsp:include page="/jsp/admon/bitacora.jsp" />
</body>

