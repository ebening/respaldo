<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/ParametroDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <link type="text/css" href="<s:url value="../css/customcss/custom-css-jqgrid.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/OperacionDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/operacion.js'/>' type='text/javascript' charset="UTF-8"></script>
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
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewOperacion'><s:text name='operacion.new'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveOperacion'><s:text name='operacion.save'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='operacion_operacionId' value='<s:text name='operacion.operacionId'/>'/>
        <input type='hidden' id='operacion_aplicacionId' value='<s:text name='operacion.aplicacionId'/>'/>
        <input type='hidden' id='operacion_aplicacion' value='<s:text name='operacion.aplicacion'/>'/>
        <input type='hidden' id='operacion_moduloId' value='<s:text name='operacion.moduloId'/>'/>
        <input type='hidden' id='operacion_modulo' value='<s:text name='operacion.modulo'/>'/>
        <input type='hidden' id='operacion_orden' value='<s:text name='operacion.orden'/>'/>
        <input type='hidden' id='operacion_nombreES' value='<s:text name='operacion.nombreES'/>'/>
        <input type='hidden' id='operacion_nombreEN' value='<s:text name='operacion.nombreEN'/>'/>
        <input type='hidden' id='operacion_descripcion' value='<s:text name='operacion.descripcion'/>'/>
        <input type='hidden' id='operacion_nombreAction' value='<s:text name='operacion.nombreAction'/>'/>
        <input type='hidden' id='operacion_html' value='<s:text name='operacion.html'/>'/>
        <input type='hidden' id='operacion_url' value='<s:text name='operacion.url'/>'/>
        <input type='hidden' id='operacion_modificar' value='<s:text name='operacion.modificar'/>'/>
        <input type='hidden' id='operacion_eliminar' value='<s:text name='operacion.eliminar'/>'/>
        <input type='hidden' id='operacion_activo' value='<s:text name='operacion.activo'/>'/>
        <input type='hidden' id='operacion_fechaModificacion' value='<s:text name='operacion.fechaModificacion'/>'/>
        <input type='hidden' id='operacion_usuarioModificacion' value='<s:text name='operacion.usuarioModificacion'/>'/>
        <s:hidden id='gridVisibleOperacion' name='gridVisibleOperacion'/>
        <input type='hidden' id='operacionGridOrderByColumn' value='<s:text name='modificacionFecha'/>'/>
        <input type='hidden' id='operacionGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
        <input type='hidden' id='operacionGridCurrentPage' value='<s:text name='key_current_page'/>'/>
        <input type='hidden' id='operacionGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
        <input type='hidden' id='key_tableName' value='<s:text name='key_seguridad_operacion_parametro'/>'/>
        <input type='hidden' id='key_propertyName' value='<s:text name='key_properti_operacion'/>'/>
        <input type='hidden' id='objectNameJS' value='<s:text name='operacionJS'/>'/>
        
        <s:form id='operacionform' name='operacionform' cssClass='pair-wrap'>
           <div class='pair-hidden '>
                <div class='etiqueta'>
                    <s:text name='operacion.operacionId'/>
                </div>
                <s:textfield name='operacionId' value='0' cssClass='isNumericInteger form-control' />
           </div>
           <div class='pair-hidden'>
                <div class='etiqueta '>
                    <s:text name='key_aplicacion_lenguaje'/>
                </div>
               <s:select list='lenguajes' listKey='lenguajeId' listValue='nombre' disabled="true"
                	name='lenguajeId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='operacion.aplicacion'/>
                </div>
                <s:select list='aplicaciones' listKey='aplicacionId' listValue='nombre' 
                	name='aplicacionId' cssClass='select form-control' onchange="operacionJS.getModulos();" />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='operacion.modulo'/>
                </div>
                <s:select list='modulos' listKey='moduloId' listValue='nombre' name='moduloId' cssClass='select form-control' />
            </div>

            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='operacion.orden'/>
                </div>
                <s:textfield name='orden' maxLength='10' cssClass='textbox form-control isNumericInteger'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='operacion.nombreES'/>
                </div>
                <s:textfield name='nombre' maxLength='200' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.descripcion'/>
                </div>
                <s:textfield name='descripcion' maxLength='60'  cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.nombreAction'/>
                </div>
                <s:textfield name='nombreAccion' maxLength='100' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.html'/>
                </div>
                <s:textfield name='htmlId' maxLength='100' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='operacion.url'/>
                </div>
                <s:textfield name='url' maxLength='300' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                 <div class='button-custom mb-xs mr-xs btn btn-primary' id='btnDesplegar'><s:text name='key_generic_desplegar'/></div>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='operacionGrid'></table>
            <div id='operacionGridPagerId'></div>
        </div>
        <jsp:include page="/jsp/admon/parametro.jsp" />
        <br>
        <jsp:include page="/jsp/admon/bitacora.jsp" />
    </div>
</body>

