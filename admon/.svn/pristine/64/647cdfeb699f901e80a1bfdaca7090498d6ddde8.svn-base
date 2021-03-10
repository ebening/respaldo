jQuery(document).ready(function () {
    
    // Actualizar "Aplicación" dependiendo del rol ***Falta
    // Actualizar "Módulos" al seleccionar "Aplicación" en formulario restriccion
    jQuery('#restriccionform_aplicacionId').change(function () {
        restriccionJS.filtrarRoles();
    });
     jQuery('#restriccionform_rolesRolId').change(function () {
        jQuery('#catalogoParametroGrid').clearGridData();
    });
    // Crear el mensaje 'Espere...'
    utilEffect.createProgressBar('Espere...');

    // Elementos button
    jQuery('.button').button();
    // Elementos datepicker
    utilInput.setAsDatepicker('.isDate');
    // Elementos datetimepicker
    utilInput.setAsDateTimePicker('.isDateTime');
    // Elementos colorpicker
    utilInput.setAsColorpicker('.color-selector');
    // Elementos radio
    utilInput.fixRadios('#paisform');
    
    //Funcion para mostrar/ocultar permisos por Pantalla
    utilObject.getFuncionalidadPantalla("es","SeguridadRolRestriccionParAction");

    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewRestriccion').click(function () {
         utilForm.reset('#restriccionform');
         jQuery('#catalogoParametroGrid').clearGridData();
        // Limpiar Grid de catálogos
        restriccionJS.restriccion = null;
    }).customButtonEffect('#btnNewRestriccion');
    // Botón : GUARDAR
    jQuery('#btnSaveRestriccion').click(function () {
        restriccionJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveRestriccion');
    // </editor-fold>
    if (jQuery('#gridVisibleCatalogo').val() == 'true') {
        jQuery('#catalogoGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                '',
                jQuery('#key_restriccion_catalogoText').val(),
                jQuery('#key_restriccion_genericoText').val(),
                jQuery('#key_restriccion_restriccionText').val()
            ],
            colModel: [{
                    name: 'catalogoId',
                    index: 'catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    width: 250,
                    resizable: false,
                    hidden: false,
                    search: true
                },{
                    name: 'generico',
                    index: 'generico',
                    align: 'center',
                    width: 250,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'Restriccion',
                    index: 'Restriccion',
                    align: 'center',
                    width: 100,
                    sortable: false,
                    search: false,
                    resizable: false
                }
            ],
            height: 230,
            toolbar: false,
            hidegrid: true,
            viewrecords: true,
            shrinkToFit : false,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#catalogoGridPagerId'),
            caption: jQuery('#key_restriccion_catalogoText').val()
        });
        // jQuery UI Dialogs
        utilDialog.setCustomDialogSelectColumnsToExport("#catalogoColumnasExportar", '#catalogoGrid');
        utilDialog.setStandardDialog('#d-removeMessage', jQuery('#msgAlertaText').val(),jQuery('#msgNotRemoveCat').val()+""+jQuery('#msgNotRemove').val(), ['Aceptar']);
        utilDialog.setStandardDialog('#d-notExistElement', jQuery('#msgAlertaText').val(),jQuery('#msgNoExistenElementos').val(), ['Aceptar']);
        // Inicializaciones extra para el grid
        utilGrid.setup('#catalogoGrid', '#catalogoGridPagerId', '#catalogoGridCurrentPage', '#catalogoGridOrderByColumn',
                '#catalogoGridOrderByType', 'catalogoJS');
        
        jQuery('#catalogoGrid').setGridWidth(
                jQuery("#gbox_" + "catalogoGrid").closest(".grid-container").width() - .10);
        
        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchCatalogo');
        utilGrid.setHideElementsTable('catalogoGridPagerId',['export','activate','delete']);
    }
    if (jQuery('#gridVisibleCatalogoParametro').val() == 'true') {
        jQuery('#catalogoParametroGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                '','',
                jQuery('#key_restriccion_parametroText').val()],
            colModel: [{
                    name: 'catalogoParametroPK.catalogoParametroId',
                    index: 'catalogoParametroPK.catalogoParametroId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'catalogoParametroPK.organizacionId',
                    index: 'catalogoParametroPK.organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'clave',
                    index: 'clave',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false,
                    width: 250
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            shrinkToFit : false,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#catalogoParametroGridPagerId'),
            caption: jQuery('#key_restriccion_parametroText').val()
        });
        catalogoJS.reloadGrid();
         // jQuery UI Dialogs
        utilDialog.setCustomDialogSelectColumnsToExport("#catalogoColumnasExportar", '#catalogoParametroGrid');
        
        // Inicializaciones extra para el grid
        utilGrid.setup('#catalogoParametroGrid', '#catalogoParametroGridPagerId', '#catalogoParametroGridCurrentPage', '#catalogoParametroGridOrderByColumn',
                '#catalogoParametroGridOrderByType', 'catalogoParametroJS');
                
        jQuery('#catalogoParametroGrid').setGridWidth(
                jQuery("#gbox_" + "catalogoParametroGrid").closest(".grid-container").width() - .10);
        
        utilGrid.setHideElementsTable('catalogoParametroGridPagerId',['search','export','activate','delete']);
    }

   
    // Limpiar la página
    jQuery('#btnNewRestriccion').click();
    // Edición externa
    jQuery('.new-window').tooltip();
    jQuery('.new-window').contents().hide();
    jQuery('.new-window').click(function () {
        utilForm.hideTooltips();
        jQuery('#idFromIframe').val(0);
        var url = jQuery(this).find('.url').text();
        var callback = jQuery(this).find('.callback').text();
        utilWindow.openNewWindow(url, callback, 1100);
    });
    
    utilInput.addOptionToSelect('#restriccionform_rolesRolId',-1,'--Seleccione--');
});

var restriccionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    restriccion: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    clean: function () {
        utilForm.reset('#restriccionform');
        restriccionJS.restriccion = null;
        jQuery('#catalogoGrid').clearGridData();
        jQuery('#catalogoParametroGrid').clearGridData();
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    getDialogOrganizacion: function (){
         if(catalogoJS.getGenericoId() == 'true'){
            restriccionJS.save();
        }else{
            SeguridadRolRestriccionParDWR.getOrganizaciones(restriccionJS.getDialogOrganizacionCallBack);
        }
    },
    getDialogOrganizacionCallBack: function (data){
            utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'restriccionJS.save','nombre');
            utilDialog.showDialog('#dialogOrganizacionesCatalogo');
            $( "#dialogOrganizacionesCatalogo" ).dialog({
                 width: 700,
                 modal: true
             });
    },
    save: function (datos) {
        utilEffect.showProgressBar();
        var rolId = parseInt(jQuery('#restriccionform_rolesRolId').val());
        var aplicacionId = parseInt(jQuery('#restriccionform_aplicacionId').val());
        var organizacionId = catalogoJS.getOrganizacionId();
        var catalogoId = catalogoJS.getCatalogoId();
        var dwrObjectList = new Array();
        var dwrObjectListOrg = new Array();
        var a =  new Object();
         dwrObjectList = utilObject.buildDWRObjectListByGridCheck(
                  '#catalogoParametroGrid', new CatalogoParametroDWR());
        if(typeof datos !== "undefined")
            var datos = datos.split(",");
        else
             var datos =[organizacionId];
        for (var i = 0; i < dwrObjectList.length; i++) {
            for(var j=0 ; j< datos.length; j++){
                dwrObjectList[ i ].catalogoId =  catalogoId;
               
                var fechaMod = dwrObjectList[ i ].modificacionFecha;
                if (fechaMod !== ""  && typeof fechaMod!== "undefined") {
                   dwrObjectList[ i ].modificacionFecha =  new Date(funciones.parseDate(fechaMod));
                }
                var fechaCrea = dwrObjectList[ i ].creacionFecha;
                if (fechaCrea !== "" && typeof fechaCrea!== "undefined") {
                    dwrObjectList[ i ].creacionFecha =  new Date(funciones.parseDate(fechaCrea));
                }
                if(catalogoJS.getGenericoId() == 'true'){
                    dwrObjectList[ i ].organizacionId = organizacionId;
                }else{
                    a = Object.assign({}, dwrObjectList[i]);
                    a.catalogoParametroPK={organizacionId:datos[j],catalogoParametroId:a.catalogoParametroPK.catalogoParametroId};
                    dwrObjectListOrg.push(a);
                }
            }
        }
        var generico = catalogoJS.getGenericoId();
        if(generico == 'false'){
            dwrObjectList=[];
            dwrObjectList=dwrObjectListOrg;
        }
        SeguridadRolRestriccionParDWR.prepareToSave(rolId,aplicacionId,dwrObjectList,restriccionJS.saveOrUpdateCallback);
     },
    
    
    
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function () {
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val()); 
        utilEffect.hideProgressBar();
    },
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () { 
        var validation = validanguage.validateForm('restriccionform');
        if (validation.result) {
            restriccionJS.getDialogOrganizacion();
        }
    },
     /*
     * filtrarRoles, esta función es llamada por DWR al 
     * para filtrar los Roles de acuerdo a la aplicación seleccionada.
     */
    filtrarRoles: function () {
        SeguridadRolRestriccionParDWR.getComboRolesByAplicacion(
                jQuery('#restriccionform_aplicacionId').val(),
                restriccionJS.filtrarRolesCallback);
    },
    /** 
     * Callback de la función filtrarRoles(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    filtrarRolesCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#restriccionform_rolesRolId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#restriccionform_rolesRolId', data, 'rolId', 'nombre');
            if (restriccionJS.restriccion !== null) {
                if (restriccionJS.restriccion.rolesRolId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#seguridadPerfilRolform_rolesRolId', restriccionJS.restriccion.rolesRolId)) {
                        jQuery('#restriccionform_rolesRolId').val(restriccionJS.restriccion.rolesRolId);
                    } else {
                        jQuery('#restriccionform_rolesRolId').val(0);
                    }
                }
            }
            jQuery('#restriccionform_rolesRolId').trigger('change');
        }
        utilEffect.hideProgressBar();
    },
     /** 
     * getRolRestriccion  esta función permite obtener
     *  las restricciones que no están eliminados y pintarlas de acuerdo al Rol 
     *  y al catálogo seleccionado
     * .
     */
    getRolRestriccion:function(){
        var rolId = parseInt(jQuery('#restriccionform_rolesRolId').val());
        var aplicacionId = parseInt(jQuery('#restriccionform_aplicacionId').val());
        var organizacionId = catalogoJS.getOrganizacionId();
        SeguridadRolRestriccionParDWR.getRestriccionesByCatOrg(rolId,aplicacionId,organizacionId,restriccionJS.getRolRestriccionCallback);
    },
     /** 
     * getRolRestriccionCallback de la función getRolRestriccion(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    getRolRestriccionCallback:function(data){
        var lista = jQuery("#catalogoParametroGrid").getDataIDs();
        for(var i=0;i<lista.length;i++){
            var rowdata =$("#catalogoParametroGrid").getRowData(lista[i]);
            for(var j=0;j<data.length;j++){
                if(rowdata.catalogoParametroId == data[j].catalogoParametroId){
                        jQuery("#catalogoParametroGrid").find('#jqg_catalogoParametroGrid_'+lista[i]).prop('checked',true); 
                        jQuery( 'tr.jqgrow:odd' ).css( 'background', '#EEEEEE' );
                        jQuery("#catalogoParametroGrid").jqGrid('setSelection', lista[i], false);
                }
            }
        }
        utilEffect.hideProgressBar();
    }
};
var catalogoJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    catalogo: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id) {
        utilEffect.showProgressBar();
        CatalogoDWR.findById(id, catalogoJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#catalogoform');
        utilForm.populate('#catalogoform', data);
        catalogoJS.catalogo = data;
        utilEffect.hideProgressBar();
    },
       /*
     * Función que realiza una búsqueda en la BD. Este objeto contiene 
     * todos los criterios para realizar la consulta.
     * 
     * @param obj (Object) Es el objeto con el cual se va a realizar la 
     * consulta. El resultado será una lista de objetos que concuerden con las
     * propiedades del objeto que se envió como parámetro.
     */
    findByCriteria: function (obj) {
        catalogoJS.cacheDWR = obj;
        var page = jQuery('#catalogoGridCurrentPage').val();
        var rows = jQuery('#catalogoGridRowsByPage').val();
        var order = jQuery('#catalogoGridOrderByColumn').val();
        var orderType = jQuery('#catalogoGridOrderByType').val();
        utilEffect.showProgressBar();
        CatalogoDWR.findByCriteriaCatalogoById(page, rows, order, orderType, obj, catalogoJS.findByCriteriaCallback);
    },
    
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#catalogoGrid', '#catalogoGridCurrentPage', '#catalogoGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.findFirst(catalogoJS.findByIdCallback);
    },
    /*
     * Getter y Setter de las variables catalogoId y organizacionId
     * ya que estos serán utilizados para almacenar una restricción 
     * por Rol
     **/
    setCatalogoId: function (catalogoId) {
       jQuery("#restriccionform_catalogoId").val(catalogoId);
    },
    getCatalogoId: function () {
        return parseInt(jQuery('#restriccionform_catalogoId').val());
    },
    setOrganizacionId: function (organizacionId) {
       jQuery("#restriccionform_organizacionId").val(organizacionId);
    },
    getOrganizacionId: function () {
        return parseInt(jQuery('#restriccionform_organizacionId').val());
    },
    setGenericoId: function (genericoId) {
       jQuery("#restriccionform_genericoId").val(genericoId);
    },
    getGenericoId: function () {
        return (jQuery('#restriccionform_genericoId').val());
    },

    /*
     * Variable para guardar el estatus según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    estatusId: 0,
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (catalogoJS.cacheDWR != null) {
            catalogoJS.findByCriteria(catalogoJS.cacheDWR);
        } else {
            catalogoJS.findByCriteria(new CatalogoDWR());
        }
    },
     /*
     * Función que es llamada para actualizar la información del grid
     * de las Restricciones en base al catálogo y organización seleccionados.
     */
    showRestricciones: function (catalogoId,organizacionId,genericoId) {
        catalogoJS.setOrganizacionId(organizacionId);
        catalogoJS.setCatalogoId(catalogoId);
        catalogoJS.setGenericoId(genericoId);
        catalogoParametroJS.reloadGrid();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchCatalogo', '#catalogoGrid', '#restriccionform', catalogoJS, new CatalogoDWR());
    },
    
    /*
     * Funcion que inicia la exportacion del grid a PDF mediante struts invocando una url y 
     * madandole parametros, esta funcion es llamada por utilMisc.prepareToExportPDF en el arcvivo 
     * util.js.
     * 
     * @param entity es el nombre del entity en Java que representa este grid
     * @param headers son los encabezados de la tabla, son los mismo que los nombres de las columnas del grid
     * @param sortBy es el nombre del campo por el cual seran ordenados los resultados
     * @param sortType es el tipo de orden: ascendente o descendente
     * @param format es el formato a exportar: pdf o excel 
     * 
     */
    entity: null,
    headers: null,
    format: null,
    reportName: null,
    exportarDatos: function (entity, headers, sortBy, sortType, format) {
        catalogoJS.entity = entity;
        catalogoJS.headers = headers;
        catalogoJS.format = format;
        catalogoJS.reportName = jQuery('#key_catalogo_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new CatalogoDWR();
        if (catalogoJS.cacheDWR != null) {
            criteriaExample = catalogoJS.cacheDWR;
        }
        // utilEffect.showProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Filtrando información...',
            sticky: false,
            position: 'bottom-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        CatalogoDWR.getReportDataTest(sortBy, sortType, criteriaExample, catalogoJS.exportarDatosCallback);
    },
    exportarDatosCallback: function (reportKey) {
        // utilEffect.hideProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Generando reporte...',
            sticky: false,
            position: 'middle-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        window.location.href = '/admon/admon/exportarPDF.action?entity=' +
                catalogoJS.entity + '&headers=' + catalogoJS.headers +
                '&reportName=' + catalogoJS.reportName + '&format=' + catalogoJS.format +
                '&reportKey=' + reportKey;
    }
};
var catalogoParametroJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    catalogoParametro: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id) {
        utilEffect.showProgressBar();
        CatalogoParametroDWR.findById(id, catalogoParametroJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#catalogoParametroform');
        utilForm.populate('#catalogoParametroform', data);
        catalogoParametroJS.catalogoParametro = data;
        catalogoParametroPaginaJS.findcatalogoParametroPaginaByIntProperty(
                jQuery('#catalogoParametroform_catalogoParametroId').val());
    },
    /*
     * Función que realiza una búsqueda en la BD. Este objeto contiene 
     * todos los criterios para realizar la consulta.
     * 
     * @param obj (Object) Es el objeto con el cual se va a realizar la 
     * consulta. El resultado será una lista de objetos que concuerden con las
     * propiedades del objeto que se envió como parámetro.
     */
    findByCriteria: function (obj) {
        catalogoParametroJS.cacheDWR = obj;
        var page = jQuery('#catalogoParametroGridCurrentPage').val();
        var rows = jQuery('#catalogoParametroGridRowsByPage').val();
        var order = jQuery('#catalogoParametroGridOrderByColumn').val();
        var orderType = jQuery('#catalogoParametroGridOrderByType').val();
        var organizacionId = catalogoJS.getOrganizacionId();
        var catalogoId = catalogoJS.getCatalogoId();
        utilEffect.showProgressBar();
        obj.catalogoParametroPK = new CatalogoParametroPKDWR();
        CatalogoParametroDWR.findByCriteriaCatalogoByPar(page, rows, order, orderType, obj,catalogoId,organizacionId, catalogoParametroJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#catalogoParametroGrid', '#catalogoParametroGridCurrentPage', '#catalogoParametroGridRowsByPage', data);
        $('#cb_catalogoParametroGrid').trigger('click').attr('checked', true);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        CatalogoParametroDWR.findFirst(catalogoParametroJS.findByIdCallback);
    },
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('catalogoParametroform');
        if (validation.result) {
            catalogoParametroJS.isValidoNombre();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (catalogoParametroJS.cacheDWR != null) {
            catalogoParametroJS.findByCriteria(catalogoParametroJS.cacheDWR);
        } else {
            catalogoParametroJS.findByCriteria(new CatalogoParametroDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchCatalogoRestriccion', '#catalogoParametroGrid', '#catalogoRestriccionParametroform', catalogoParametroJS, new CatalogoParametroDWR());
    },
    
    setIdCatalogoOrganizacion :function(data){
        var organizacionId = catalogoJS.getOrganizacionId();
        var catalogoId = catalogoJS.getCatalogoId();
        for (var i = 0; i < data.length; i++) {
                data[ i ].catalogoId =  catalogoId;
                data[ i ].organizacionId = organizacionId;
        }
        return data; 
    },
    /*
     * Funcion que inicia la exportacion del grid a PDF mediante struts invocando una url y 
     * madandole parametros, esta funcion es llamada por utilMisc.prepareToExportPDF en el arcvivo 
     * util.js.
     * 
     * @param entity es el nombre del entity en Java que representa este grid
     * @param headers son los encabezados de la tabla, son los mismo que los nombres de las columnas del grid
     * @param sortBy es el nombre del campo por el cual seran ordenados los resultados
     * @param sortType es el tipo de orden: ascendente o descendente
     * @param format es el formato a exportar: pdf o excel 
     * 
     */
    entity: null,
    headers: null,
    format: null,
    reportName: null,
    exportarDatos: function (entity, headers, sortBy, sortType, format) {
        catalogoParametroJS.entity = entity;
        catalogoParametroJS.headers = headers;
        catalogoParametroJS.format = format;
        catalogoParametroJS.reportName = jQuery('#key_catalogoParametro_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new CatalogoParametroDWR();
        if (catalogoParametroJS.cacheDWR != null) {
            criteriaExample = catalogoParametroJS.cacheDWR;
        }
        // utilEffect.showProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Filtrando información...',
            sticky: false,
            position: 'bottom-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        CatalogoParametroDWR.getReportDataTest(sortBy, sortType, criteriaExample, catalogoParametroJS.exportarDatosCallback);
    },
    exportarDatosCallback: function (reportKey) {
        // utilEffect.hideProgressBar();
        jQuery().toastmessage('showToast', {
            text: 'Generando reporte...',
            sticky: false,
            position: 'middle-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        window.location.href = '/admon/admon/exportarPDF.action?entity=' +
                catalogoParametroJS.entity + '&headers=' + catalogoParametroJS.headers +
                '&reportName=' + catalogoParametroJS.reportName + '&format=' + catalogoParametroJS.format +
                '&reportKey=' + reportKey;
    }
};
/*
 * Funcion que setea el id del elemento seleccionado en la fila del jqgrid en un hidden cuando el grid
 * esta en un iframe de edicion externa. El id es utilizado para que al momento de cerrar el dialogo
 * se actualize su respectivo select y setear el valor en ese mismo select con el id que el usuario selecciono.
 */
function setIdFromIframe(id) {
    jQuery('#idFromIframe').val(id);
}
// Validaciones de paisform
validanguage.el.restriccionform_aplicacionId = {
    characters: {
        mode: 'allow', expression: 'numeric- ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.restriccionform_catalogoId = {
    characters: {
        mode: 'allow', expression: 'numeric- ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.restriccionform_rolesRolId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};


