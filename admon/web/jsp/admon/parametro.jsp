<%@taglib  prefix='s' uri='/struts-tags'%>
<!-- Parametro -->
<div id="parametroDiv" class='pair-hidden'>
            <div class='pair-wrap divisor'></div>
            <div class='pair-wrap' style="background-color: #F6F6F6;">
                <div class='title-text alert alert-info'><s:text name='key_lenguajeparametro_title'/>
                    <!-- Botón Agregar -->
                    <div class='button-container-detalle'>
                        <div class='button-custom mb-xs mt-xs mr-xs btn btn-default' id='btnNewParametro'><s:text name='key_parametro_bnew'/></div>
                        <div class='button-custom button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddParametro'><s:text name='key_parametro_badd'/></div>
                        <div class='hidden button button-detalle mb-xs mt-xs mr-xs btn btn-default' id='btnAddAplicacionParametroNoValidation'><s:text name='key_parametro_badd'/></div>
                        <div style='clear: both'></div>
                    </div>
                </div> 
                <div style='clear: both'></div>
            <input type='hidden' id='key_parametro_title' value='<s:text name='key_parametro_title'/>'/>
            <input type='hidden' id='parametroGridOrderByColumn' value='<s:text name='key_orderby_column_modificacion_fecha'/>'/>
            <input type='hidden' id='parametroGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
            <input type='hidden' id='parametroGridCurrentPage' value='<s:text name='key_current_page'/>'/>
            <input type='hidden' id='parametroGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
                <s:form id='parametroform' name='parametroform' cssClass='pair-wrap'>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='parametro_parametroIdText'>
                            <s:text name='key_parametro_parametroid'/>
                        </div>
                        <s:textfield name='parametroId' value='0' cssClass='isNumericInteger form-control' />
                    </div>
                    <div class='pair-hidden '>
                        <div class='etiqueta' id='parametro_fkIdText'>
                            <s:text name='key_parametro_fkId'/>
                        </div>
                        <s:textfield name='fkId' value='0' cssClass='isNumericInteger form-control' />
                    </div>
                    <div class='pair col-sm-4'>
                        <div class='etiqueta' id='parametro_lenguajeText'>
                            <s:text name='key_parametro_lenguaje'/>
                        </div>
                            <s:select list='lenguajes' listKey='lenguajeId' listValue='nombre' disabled="false"
                                name='lenguajeId'  cssClass='select form-control' />
                    </div>
                    <div class='pair col-sm-4'>
                        <div class='etiqueta required_star col-xs-10_star' id='parametro_nombreText'>
                            <s:text name='key_parametro_nombre'/>
                        </div>
                        <s:textfield name='nombre' maxLength='200' cssClass='textbox form-control'/>
                    </div>
                    <div style='clear: both'></div>
                </s:form>
                 <div class='pair-wrap divisor'></div>
                <div class='grid-container'>
                    <table id='parametroGrid'></table>
                    <div id='parametroGridPagerId'></div>
                </div>
            </div>
</div>

