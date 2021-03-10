<%@taglib  prefix='s' uri='/struts-tags'%>
<!-- Bitacora -->
<div id="bitacoraDiv" class='pair-hidden'>
            <div class='pair-wrap divisor'></div>
            <div class='pair-wrap' style="background-color: #F6F6F6;">
                <div class='title-text alert alert-info'><s:text name='key_bitacora_title'/>
                    
                </div> 
                <div style='clear: both'></div>
            <input type='hidden' id='key_bitacora_title' value='<s:text name='key_bitacora_title'/>'/>
            <input type='hidden' id='bitacoraGridOrderByColumn' value='<s:text name='key_orderby_column_modificacion_fecha'/>'/>
            <input type='hidden' id='bitacoraGridOrderByType' value='<s:text name='key_orderby_type_des'/>'/>
            <input type='hidden' id='bitacoraGridCurrentPage' value='<s:text name='key_current_page'/>'/>
            <input type='hidden' id='bitacoraGridRowsByPage' value='<s:text name='key_rowsby_page'/>'/>
            <input type='hidden' id='key_organizacion' value='<s:text name='key_organizacion_organizacionid'/>'/>
            <input type='hidden' id='key_ejecucion' value='<s:text name='key_ejecucion'/>'/>
    
        <div class='pair-wrap divisor'></div>
            <div class='grid-container'>
                <table id='bitacoraGrid'></table>
                <div id='bitacoraGridPagerId'></div>
            </div>
        </div>
</div>

