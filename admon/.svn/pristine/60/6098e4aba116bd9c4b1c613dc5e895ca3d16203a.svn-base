<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/SeguridadRolRestriccionParDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/CatalogoDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/CatalogoParametroDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/CatalogoParametroPKDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/restriccion.js'/>' type='text/javascript' charset="UTF-8"></script>
    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewRestriccion'><s:text name='key_restriccion_bnew'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveRestriccion'><s:text name='key_restriccion_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_restriccion_title' value='<s:text name='key_restriccion_title'/>'/>
        <input type='hidden' id='key_restriccion_modificacionUsuario' value='<s:text name='key_restriccion_modificacionUsuario'/>'/>
        <input type='hidden' id='key_restriccion_modificacionFecha' value='<s:text name='key_restriccion_modificacionFecha'/>'/>
        <input type='hidden' id='key_restriccion_estatusText' value='<s:text name='key_restriccion_estatusid'/>'/>
        <input type='hidden' id='key_restriccion_eliminadoText' value='<s:text name='key_restriccion_eliminadoid'/>'/>
        <input type='hidden' id='key_restriccion_restriccionText' value='<s:text name='key_restriccion_restriccion'/>'/>
        <input type='hidden' id='key_restriccion_parametroText' value='<s:text name='key_restriccion_parametro'/>'/>
        <input type='hidden' id='key_restriccion_catalogoText' value='<s:text name='key_restriccion_parametro'/>'/>
        <input type='hidden' id='key_restriccion_genericoText' value='<s:text name='key_restriccion_generico'/>'/>
        <input type='hidden' id='catalogoGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='catalogoGridOrderByType' value='des'/>
        <input type='hidden' id='catalogoGridCurrentPage' value='1'/>
        <input type='hidden' id='catalogoGridRowsByPage' value='10'/>
        <input type='hidden' id='catalogoParametroGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='catalogoParametroGridOrderByType' value='des'/>
        <input type='hidden' id='catalogoParametroGridCurrentPage' value='1'/>
        <input type='hidden' id='catalogoParametroGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <input type='hidden' id='msgNotRemoveCat' value=''/>
        <s:hidden id='gridVisibleCatalogo' name='gridVisibleCatalogo'/>
        <s:hidden id='gridIndividualModeCatalogo' name='gridIndividualModeCatalogo'/>
        <s:hidden id='gridVisibleCatalogoParametro' name='gridVisibleCatalogoParametro'/>
        <s:hidden id='gridIndividualModeCatalogoParametro' name='gridIndividualModeCatalogoParametro'/>

        <s:form id='restriccionform' name='restriccionform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_restriccionIdText'>
                    <s:text name='key_restriccion_restriccionid'/>
                </div>
                <s:textfield name='rolRestriccionId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_organizacionIdText'>
                    <s:text name='key_restriccion_organizacion'/>
                </div>
                <s:textfield name='organizacionId' value='0' cssClass='isNumericInteger' />
            </div>
            <div class='pair-hidden '>
                <s:textfield name='catalogoId' cssClass='isNumericInteger' />
            </div>
            <div class='pair-hidden '>
                <s:textfield name='genericoId' />
            </div>
            <div class='pair col-sm-6 '>
                <span class='etiqueta-small required_star col-xs-10_star' id='rol_aplicacionIdText'>
                    <s:text name='key_restriccion_aplicacion'/>
                </span>
                <s:select list='aplicacion' listKey='aplicacionId' listValue='nombre' name='aplicacionId' cssClass='select form-control '/>
            </div>
            <div class='pair col-sm-6'>
                <span class='etiqueta-small required_star col-xs-10_star' id='restriccion_rolIdText'>
                    <s:text name='key_restriccion_listaRol'/>
                </span>
                <s:select list='roles' listKey='rolId' listValue='nombre' name='rolesRolId' cssClass='select form-control ' />
            </div>
            <div class='pair-hidden'>
                <s:textfield name='nombre' cssClass='textbox form-control'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_creacionFechaText'>
                    <s:text name='key_restriccion_creacionfecha'/>
                </div>
                <s:textfield name='creacionFechaRestriccion' cssClass='textbox form-control isDate'/>
            </div>
            <div class='pair-hidden '>
                <div class='etiqueta' id='restriccion_creacionUsuarioText'>
                    <s:text name='key_restriccion_creacionusuario'/>
                </div>
                <s:textfield name='creacionUsuarioRestriccion' maxLength='9' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div style='clear: both'></div>
      
        </s:form>
        <div class="row" cssClass='pair-wrap'>
            <div class="col-sm-6 col-lg-7">
                <div class='pair-wrap divisor'></div>
                <div class='grid-container'>
                    <table id='catalogoGrid'></table>
                    <div id='catalogoGridPagerId'></div>
                </div>
                <div class='pair-wrap divisor'></div>
                <br>
            </div>
            <div class="col-sm-6 col-lg-5">
                <div class='pair-wrap divisor'></div>
                <div class='grid-container'>
                    <table id='catalogoParametroGrid'></table>
                    <div id='catalogoParametroGridPagerId'></div>
                </div>
                <div class='pair-wrap divisor'></div>
                <br>
            </div>
        </div> 
    </div>
</body>

