<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix='s' uri='/struts-tags'%>
<head>
    <script src='<s:url value='../dwr/interface/PerfilPaginaDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/PerfilPaginaPantallaDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../dwr/interface/PaginaDWR.js'/>' type='text/javascript'></script>
    <script src='<s:url value='../js/admon/perfilPagina.js'/>' type='text/javascript'></script>

    <!-- Accesos -->
    <script src='<s:url value='../js/jquerycomponentes/jquery.jstree.js'/>' type='text/javascript'></script>

    <title>
        <s:property escape='false' value='nombreActionMenu'/>
    </title>
</head>
<body>
    <div class='form-wrap panel-body'>
        <div class='titulo ui-corner-top'>
            <div class='title-text hidden'><s:property escape='false' value='nombreActionMenu'/></div>
            <div class='button-container'>
                <div class='button-custom mb-xs mt-xs mr-xs btn btn-primary' id='btnSavePerfilPagina'><s:text name='key_perfilpagina_bsave'/></div>
            </div>
            <div style='clear: both'></div>
        </div>
        <!-- Hidden -->
        <input type='hidden' id='key_perfil_title' value='<s:text name='key_perfil_title'/>'/>
        <input type='hidden' id='key_perfil_modificacionUsuario' value='<s:text name='key_perfil_modificacionUsuario'/>'/>
        <input type='hidden' id='key_perfil_modificacionFecha' value='<s:text name='key_perfil_modificacionFecha'/>'/>
        <input type='hidden' id='perfilGridOrderByColumn' value='modificacionFecha'/>
        <input type='hidden' id='perfilGridOrderByType' value='des'/>
        <input type='hidden' id='perfilGridCurrentPage' value='1'/>
        <input type='hidden' id='perfilGridRowsByPage' value='10'/>
        <input type='hidden' id='idFromIframe' value=''/>
        <s:hidden id='gridVisiblePerfilPagina' name='gridVisiblePerfilPagina'/>
        <s:hidden id='gridIndividualModePerfilPagina' name='gridIndividualModePerfilPagina'/>

        <!-- Textos de error -->
        <input type='hidden' id='key_usuariopagina_noselecciontitulo' value='<s:text name='key_usuariopagina_noselecciontitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_noseleccionmensaje' value='<s:text name='key_usuariopagina_noseleccionmensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_guardadocorrectotitulo' value='<s:text name='key_usuariopagina_guardadocorrectotitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_guardadocorrectomensaje' value='<s:text name='key_usuariopagina_guardadocorrectomensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_nohayaccesostitulo' value='<s:text name='key_usuariopagina_nohayaccesostitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_nohayaccesosmensaje' value='<s:text name='key_usuariopagina_nohayaccesosmensaje'/>'/>
        <input type='hidden' id='key_usuariopagina_seleccionusuariotitulo' value='<s:text name='key_usuariopagina_seleccionusuariotitulo'/>'/>
        <input type='hidden' id='key_usuariopagina_seleccionusuariomensaje' value='<s:text name='key_usuariopagina_seleccionusuariomensaje'/>'/>
        <div class='col-sm-8'>
            <s:form id='perfilform' name='perfilform' cssClass='pair-wrap'>
                <div class='pair-hidden '>
                    <div class='etiqueta' id='perfil_perfilIdText'>
                        <s:text name='key_perfil_perfilid'/>
                    </div>
                    <s:textfield name='perfilId' value='0' cssClass='isNumericInteger' />
                </div>
                <div class='pair-hidden'>
                    <div class='etiqueta' id='perfil_nombreText'>
                        <s:text name='key_perfil_nombre'/>
                    </div>
                    <s:textfield name='nombre' maxLength='187' cssClass='textbox form-control'/>
                </div>

                <div class='col-sm-2'>
                    <div class='grid-container'>
                        <table id='perfilGrid'></table>
                        <div id='perfilGridPagerId'></div>
                    </div>
                </div>
               
            </s:form>
        </div>
        <div class='col-sm-4'>
            <div class='alert alert-info'>
                <div>Accesos por Perfil<br> 
                    <div style="font-size: 10px;">Para modificar los accesos utilize la pantalla de Perfil</div></div>
            </div>
            <div class='tree-container panel-body-nopadding border'>
                <div class='accesos-container'>
                    <div class='accesos'></div>
                </div>
                <div class='paginas'>
                </div>
            </div>
        </div>

        <div class="clear-both"></div>
        <br>
    </div>
</div>
</body>

