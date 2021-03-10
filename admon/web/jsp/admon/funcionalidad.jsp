<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/ParametroDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../dwr/interface/SeguridadPerfilDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <link type="text/css" href="<s:url value="../css/validacion/validator.css"/>" rel="stylesheet"/>
    <link type="text/css" href="<s:url value="../css/customcss/custom-css-jqgrid.css"/>" rel="stylesheet"/>
    <script src='<s:url value='../dwr/interface/FuncionalidadDWR.js'/>' type='text/javascript' charset="UTF-8"></script>
    <script src='<s:url value='../js/admon/funcionalidad.js'/>' type='text/javascript' charset="UTF-8"></script>
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
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewFuncionalidad'><s:text name='funcionalidad.new'/></div>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSaveFuncionalidad'><s:text name='funcionalidad.save'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='funcionalidadId'/>
        <input type='hidden' id='funcionalidad_funcionalidadId' value='<s:text name='funcionalidad.funcionalidadId'/>'/>
        <input type='hidden' id='funcionalidad_aplicacionId' value='<s:text name='funcionalidad.aplicacionId'/>'/>
        <input type='hidden' id='funcionalidad_aplicacion' value='<s:text name='funcionalidad.aplicacion'/>'/>
        <input type='hidden' id='funcionalidad_moduloId' value='<s:text name='funcionalidad.moduloId'/>'/>
        <input type='hidden' id='funcionalidad_modulo' value='<s:text name='funcionalidad.modulo'/>'/>
        <input type='hidden' id='funcionalidad_operacionId' value='<s:text name='funcionalidad.operacionId'/>'/>
        <input type='hidden' id='funcionalidad_operacion' value='<s:text name='funcionalidad.operacion'/>'/>
        <input type='hidden' id='funcionalidad_tipoId' value='<s:text name='funcionalidad.tipoId'/>'/>
        <input type='hidden' id='funcionalidad_tipo' value='<s:text name='funcionalidad.tipo'/>'/>
        <input type='hidden' id='funcionalidad_nombreES' value='<s:text name='funcionalidad.nombreES'/>'/>
        <input type='hidden' id='funcionalidad_nombreEN' value='<s:text name='funcionalidad.nombreEN'/>'/>
        <input type='hidden' id='funcionalidad_descripcion' value='<s:text name='funcionalidad.descripcion'/>'/>
        <input type='hidden' id='funcionalidad_metodo' value='<s:text name='funcionalidad.metodo'/>'/>
        <input type='hidden' id='funcionalidad_html' value='<s:text name='funcionalidad.html'/>'/>
        <input type='hidden' id='funcionalidad_modificar' value='<s:text name='funcionalidad.modificar'/>'/>
        <input type='hidden' id='funcionalidad_eliminar' value='<s:text name='funcionalidad.eliminar'/>'/>
        <input type='hidden' id='funcionalidad_activo' value='<s:text name='funcionalidad.activo'/>'/>
        <input type='hidden' id='funcionalidad_fechaModificacion' value='<s:text name='funcionalidad.fechaModificacion'/>'/>
        <input type='hidden' id='funcionalidad_usuarioModificacion' value='<s:text name='funcionalidad.usuarioModificacion'/>'/>
        <s:hidden id='gridVisibleFuncionalidad' name='gridVisibleFuncionalidad'/>
        <input type='hidden' id='funcionalidadGridOrderByColumn' value='<s:text name='modificacionFecha'/>'/>
        <input type='hidden' id='funcionalidadGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
        <input type='hidden' id='funcionalidadGridCurrentPage' value='<s:text name='key_current_page'/>'/>
        <input type='hidden' id='funcionalidadGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
        <input type='hidden' id='key_tableName' value='<s:text name='key_seguridad_oper_func_parametro'/>'/>
        <input type='hidden' id='key_propertyName' value='<s:text name='key_properti_funcionalidad'/>'/>
        <input type='hidden' id='key_propertyNameOperFunc' value='<s:text name='key_properti_oper_func'/>'/>
        <input type='hidden' id='objectNameJS' value='<s:text name='funcionalidadJS'/>'/>
        
        <s:form id='funcionalidadform' name='funcionalidadform' cssClass='pair-wrap'>
            <div class='pair-hidden '>
                <div class='etiqueta'>
                    <s:text name='funcionalidad.funcionalidadId'/>
                </div>
                <s:textfield name='funcionalidadId' value='0' cssClass='isNumericInteger form-control' />
           </div>
           <div class='pair-hidden col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='key_aplicacion_lenguaje'/>
                </div>
               <s:select list='lenguajes' listKey='lenguajeId' listValue='nombre' disabled="true"
                	name='lenguajeId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='funcionalidad.aplicacion'/>
                </div>
                <s:select list='aplicaciones' listKey='aplicacionId' listValue='nombre' 
                	name='aplicacionId' cssClass='select form-control' onchange="funcionalidadJS.getModulos();" />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='funcionalidad.modulo'/>
                </div>
                <s:select list='modulos' listKey='moduloId' listValue='nombre' name='moduloId' cssClass='select form-control'  onchange="funcionalidadJS.getOperaciones();"/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='funcionalidad.operacion'/>
                </div>
                <s:select list='operaciones' listKey='operacionId' listValue='nombre' name='operacionId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='funcionalidad.tipo'/>
                </div>
                <s:select list='tipos' listKey='catalogoPK.catalogoId' listValue='clave' name='catalogoId' cssClass='select form-control' />
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta required_star col-xs-10_star'>
                    <s:text name='funcionalidad.nombreES'/>
                </div>
                <s:textfield name='nombre' maxLength='200' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='funcionalidad.descripcion'/>
                </div>
                <s:textfield name='descripcion' maxLength='60' cssClass='textbox form-control'/>
            </div>
             <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='funcionalidad.metodo'/>
                </div>
                <s:textfield name='metodo'  maxLength='200' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                <div class='etiqueta'>
                    <s:text name='funcionalidad.html'/>
                </div>
                <s:textfield name='htmlId'  maxLength='30' cssClass='textbox form-control'/>
            </div>
            <div class='pair col-sm-4'>
                 <div class='button-custom mb-xs mr-xs btn btn-primary' id='btnDesplegar'><s:text name='key_generic_desplegar'/></div>
            </div>
            <div style='clear: both'></div>
        </s:form>

        <div class='pair-wrap divisor'></div>
        <div class='grid-container'>
            <table id='funcionalidadGrid'></table>
            <div id='funcionalidadGridPagerId'></div>
        </div>
        <jsp:include page="/jsp/admon/parametro.jsp" />
        <br>
        <jsp:include page="/jsp/admon/bitacora.jsp" />
    </div>
</body>

