jQuery(document).ready(function () {
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
    //Funcion para mostrar/ocultar permisos por Pantalla
    utilObject.getFuncionalidadPantalla("","ConfiguracionFormaPagoAction");

    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewAfiliacion').click(function () {
        utilForm.reset('#configuracionFormaPagoform');
        configuracionFormaPagoJS.configuracionFormaPago = null;
        configuracionFormaPagoJS.inicializaValoresAfiliaciones();
        jQuery('#configuracionFormaPagoform_claveFormaPago').removeAttr('disabled');
      }).customButtonEffect('#btnNewAfiliacion');
    // Botón : GUARDAR
    jQuery('#btnSaveAfiliacion').click(function () {
        configuracionFormaPagoJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveAfiliacion');
     // Botón DESPLEGAR
    jQuery('#btnDesplegar').click(function () {
        configuracionFormaPagoJS.getDialogOrganizacion();
    }).css({'float': 'right', 'margin-right': '4px', 'margin-top': '15px'}).customButtonEffectBlue('#btnDesplegar');
    
        jQuery('#configuracionFormaPagoGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#otros_IdText').html(),
                jQuery('#otros_formapagoIdText').html(),
                jQuery('#otros_siteurlText').html(),
                jQuery('#otros_afiliacionText').html(),
//                jQuery('#otros_modoconexionText').html(),
                jQuery('#otros_tipomonedaText').html(),
//                jQuery('#otros_returnurlText').html(), 
                jQuery('#generico_activo').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'configuracionFormaPagoId',
                    index: 'configuracionFormaPagoId',
                    align: 'center',
//                    width: 40,
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'claveFormaPago',
                    index: 'claveFormaPago',
                    align: 'center',
                    width: 50,
                    resizable: false,
                    hidden: false,
                    search: true
                },{
                    name: 'urlSite',
                    index: 'urlSite',
                    align: 'center',
//                    width: 50,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'afiliacion',
                    index: 'afiliacion',
                    align: 'center',
                    width: 50,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'currency',
                    index: 'currency',
                    align: 'center',
                    width: 40,
                    resizable: false,
                    hidden: false,
                    search: false
                },
//                {
//                    name: 'urlReturn',
//                    index: 'urlReturn',
//                    align: 'center',
//                    width: 30,
//                    resizable: false,
//                    hidden: false,
//                    search: false
//                },
                {
                    name: 'State',
                    index: 'State',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#configuracionFormaPagoGridPagerId'),
            caption: jQuery('#key_afiliacionbanamex_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeAfiliacion',jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@configuracionFormaPagoJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionAfiliacion',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@configuracionFormaPagoJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusAfiliacion',jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@configuracionFormaPagoJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedAfiliacion', jQuery('#msgAlertaText').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoAfiliacion', 'Advertencia', jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#configuracionFormaPagoColumnasExportar", '#configuracionFormaPagoGrid');
        utilDialog.setCustomDialogSetEstatus('#configuracionFormaPagoSetEstatusActivoInactivo',
                'configuracionFormaPagoJS.prepareToSetEstatus(1)', 'configuracionFormaPagoJS.prepareToSetEstatus(0)');
        utilDialog.setStandardDialog('#d-afiliacionParcializacion',jQuery('#msgAlertaText').val(), jQuery('#msgParcializaciones').val(), ['Aceptar@']);
        // Inicializaciones extra para el grid
        utilGrid.setup('#configuracionFormaPagoGrid', '#configuracionFormaPagoGridPagerId', '#configuracionFormaPagoGridCurrentPage', '#configuracionFormaPagoGridOrderByColumn',
                '#configuracionFormaPagoGridOrderByType', 'configuracionFormaPagoJS');

        // Crear el modal de búsqueda
        utilSearch.buildSearch('#d-searchAfiliacion');
        utilGrid.setHideElementsTable('configuracionFormaPagoGridPagerId',['activate','export']);

        // Recargar el widget jqGrid
        configuracionFormaPagoJS.reloadGrid();
        //DEFAULT
        configuracionFormaPagoJS.inicializaValoresAfiliaciones();
        //BITÁCORA
        bitacoraJS.inicializarGrid();
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
    
    $("#configuracionFormaPagoform_currency").val('MXN');
    
});

var configuracionFormaPagoJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    configuracionFormaPago: null,
    //DEFAULT
    afiliacionDefault:null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,

    activarPassword:function(el){
        var combo=$('#configuracionFormaPagoform_claveFormaPago').find(":selected").text();
        if(combo=='PAYPAL'){
            jQuery('#configuracionFormaPagoform_password').removeAttr('disabled');
            jQuery('#configuracionFormaPagoform_contractId').attr('disabled','disabled');
            jQuery('#configuracionFormaPagoform_afiliacion').attr('disabled','disabled');
            ConfiguracionFormaPagoDWR.getCatalogosAfiliacionesPayPal(configuracionFormaPagoJS.cargaValoresAfiliacionesPaypalCallback);
         }else{
            jQuery('#configuracionFormaPagoform_password').attr('disabled','disabled');
            jQuery('#configuracionFormaPagoform_contractId').removeAttr('disabled');
            jQuery('#configuracionFormaPagoform_afiliacion').removeAttr('disabled');
            ConfiguracionFormaPagoDWR.getCatalogosAfiliacionesPayNet(configuracionFormaPagoJS.cargaValoresAfiliacionesPaynetCallback);
            
         }
    },
    //DEFAULT
    inicializaValoresAfiliaciones:function(){
        ConfiguracionFormaPagoDWR.getCatalogosAfiliaciones(configuracionFormaPagoJS.inicializaValoresAfiliacionesCallback);
    },
    cargaValoresAfiliacionesPaypalCallback:function(data){
        configuracionFormaPagoJS.afiliacionDefault = Object.assign({}, data);
        data.claveFormaPago = 'PAYPAL';
        data.configuracionFormaPagoId = 1;
        utilForm.populate('#configuracionFormaPagoform', data);
    },
    cargaValoresAfiliacionesPaynetCallback:function(data){
        configuracionFormaPagoJS.afiliacionDefault = Object.assign({}, data);
        data.claveFormaPago = 'PAYNET';
        data.configuracionFormaPagoId = 2;
        utilForm.populate('#configuracionFormaPagoform', data);
    },
    inicializaValoresAfiliacionesCallback:function(data){
        configuracionFormaPagoJS.afiliacionDefault = Object.assign({}, data);
        data.url = "";//Se inicializan como vacio ya que este valor únicamente se llena autómatico cuando se trata de WEB
        data.command = "";//Se inicializan como vacio ya que este valor únicamente se llena autómatico cuando se trata de WEB
        data.username = "";
        data.password = "";
        data.configuracionFormaPagoId = 0;
        utilForm.populate('#configuracionFormaPagoform', data);
    },
    getValoresByTipoCanal:function(){
        var valor = jQuery("#configuracionFormaPagoform_tipoCanalVentaId option:selected").html();
        if(valor === "WEB"){
            jQuery('#configuracionFormaPagoform_url').val(configuracionFormaPagoJS.afiliacionDefault.url);
            jQuery('#configuracionFormaPagoform_command').val(configuracionFormaPagoJS.afiliacionDefault.command);
        }
    },
     /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var configuracionFormaPago = utilObject.buildObject('#configuracionFormaPagoform', new ConfiguracionFormaPagoDWR());
        configuracionFormaPago.configuracionFormaPagoId = 0;
        configuracionFormaPago.claveFormaPago = jQuery('#configuracionFormaPagoform_claveFormaPago').val();
        configuracionFormaPago.currency = jQuery('#configuracionFormaPagoform_currency').val();
        var listaAfiliaciones = [configuracionFormaPago];
        ConfiguracionFormaPagoDWR.save(listaAfiliaciones, configuracionFormaPagoJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var configuracionFormaPago = utilObject.buildObject('#configuracionFormaPagoform', new ConfiguracionFormaPagoDWR());
        configuracionFormaPago.configuracionFormaPagoId = jQuery('#configuracionFormaPagoform_configuracionFormaPagoId').val();
        configuracionFormaPago.claveFormaPago = jQuery('#configuracionFormaPagoform_claveFormaPago').val();
        configuracionFormaPago.currency = jQuery('#configuracionFormaPagoform_currency').val();
        var listaAfiliaciones = [configuracionFormaPago];
        ConfiguracionFormaPagoDWR.update(listaAfiliaciones, configuracionFormaPagoJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        jQuery('#btnNewAfiliacion').click();
        configuracionFormaPagoJS.configuracionFormaPago = null;
        configuracionFormaPagoJS.reloadGrid();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());  
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = configuracionFormaPagoJS.getEliminarId();
        var listaIds = [id];
        ConfiguracionFormaPagoDWR.remove(listaIds, configuracionFormaPagoJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#configuracionFormaPagoGrid');
        ConfiguracionFormaPagoDWR.remove(listaIds, configuracionFormaPagoJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewAfiliacion').click();
        configuracionFormaPagoJS.reloadGrid();
    },
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id) {
        utilEffect.showProgressBar();
        ConfiguracionFormaPagoDWR.findById(id, configuracionFormaPagoJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#configuracionFormaPagoform');
        utilForm.populate('#configuracionFormaPagoform', data);
        configuracionFormaPagoJS.configuracionFormaPago = data;
        console.log(data);
        jQuery('#configuracionFormaPagoform_claveFormaPago').attr('disabled','disabled');
        if(data.claveFormaPago=='PAYPAL'){
            jQuery('#configuracionFormaPagoform_password').removeAttr('disabled');
            jQuery('#configuracionFormaPagoform_contractId').attr('disabled','disabled');
            jQuery('#configuracionFormaPagoform_afiliacion').attr('disabled','disabled');
        }else{
            jQuery('#configuracionFormaPagoform_password').attr('disabled','disabled');
            jQuery('#configuracionFormaPagoform_contractId').removeAttr('disabled');
            jQuery('#configuracionFormaPagoform_afiliacion').removeAttr('disabled');
        }
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
        configuracionFormaPagoJS.cacheDWR = obj;
        var page = jQuery('#configuracionFormaPagoGridCurrentPage').val();
        var rows = jQuery('#configuracionFormaPagoGridRowsByPage').val();
        var order = jQuery('#configuracionFormaPagoGridOrderByColumn').val();
        var orderType = jQuery('#configuracionFormaPagoGridOrderByType').val();
        ConfiguracionFormaPagoDWR.findByCriteria(page, rows, order, orderType, obj, configuracionFormaPagoJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#configuracionFormaPagoGrid', '#configuracionFormaPagoGridCurrentPage', '#configuracionFormaPagoGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ConfiguracionFormaPagoDWR.findFirst(configuracionFormaPagoJS.findByIdCallback);
    },
    /*
     * Función que válida el atributo <b>NOMBRE</b> de un registro en la BD. 
     * Se valida si ya existe un registro con el mismo nombre, esto con el 
     * propósito de advertir al usuario sobre registros duplicados.
     * Por default el <b>CoreGenerator</b> está configurado solo para mostrar
     * advertencias al usuario y permite más de un registro con el mismo
     * nombre en la BD, está metodología puede cambiarse manualmente modificando
     * el <b>callback</b> de esta función.
     * NOTICE: Ésta función es generada por el <b>CoreGenerator</b>
     * únicamente cuando la tabla tiene el campo 'NOMBRE', si la tabla no
     * contiene ese campo la función estará ausente. 
     * 
     * Ésta función siempre es llamada antes de realizar algún commit a la BD 
     * (guardar ó actualizar/modificar).
     */
    isValidoNombre: function () {
        //var nombre = jQuery('#configuracionFormaPagoform_configuracionFormaPago').val();
        //ConfiguracionFormaPagoDWR.isValidoNombre(nombre, configuracionFormaPagoJS.isValidoNombreCallback);
        configuracionFormaPagoJS.isValidoNombreCallback(true);
    },
    /*
     * Callback de la función isValidoNombre(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param isValido (boolean) es true cuando no existe ningún registro con
     * el nombre a validar, false cuando hay al menos un registro con el mismo
     * nombre.
     */
    isValidoNombreCallback: function (isValido) {
        // Cuando se esta actualizando un registro pero no se cambia el nombre, 
        // la función isValidoNombre() siempre regresa false al momento de 
        // intentar guardar por que el registro que se esta editando tiene el mismo 
        // nombre que en base de datos, en este caso se permite el commit.
        if (isValido == false && jQuery('#configuracionFormaPagoform_configuracionFormaPagoId').val() != 0) {
            configuracionFormaPagoJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#configuracionFormaPagoform_configuracionFormaPagoId').val() == 0) {
            configuracionFormaPagoJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#configuracionFormaPagoform_configuracionFormaPagoId').val() != 0) {
            configuracionFormaPagoJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoAfiliacion');
    },

    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, estatusId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        ConfiguracionFormaPagoDWR.setEstatus(estatusId, listaObjetos, configuracionFormaPagoJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = configuracionFormaPagoJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#configuracionFormaPagoGrid');
        ConfiguracionFormaPagoDWR.setEstatus(estatusId, listaObjetos, configuracionFormaPagoJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        configuracionFormaPagoJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionFormaPagoGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedAfiliacion');
        } else {
            configuracionFormaPagoJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPerfil' );
            configuracionFormaPagoJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionFormaPagoGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedAfiliacion');
        } else {
            utilDialog.showDialog('#d-removeSeleccionAfiliacion');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        configuracionFormaPagoJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeAfiliacion');
    },

    /*
     * Variable para guardar el ID de la fila que se va a eliminar, si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    eliminarId: 0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId) {
        configuracionFormaPagoJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return configuracionFormaPagoJS.eliminarId;
    },

    /*
     * Variable para guardar el estatus según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    estatusId: 0,
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('configuracionFormaPagoform');
        if (validation.result) {
            configuracionFormaPagoJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#configuracionFormaPagoform_configuracionFormaPagoId').val() == 0) {
            configuracionFormaPagoJS.save();
        } else {
            configuracionFormaPagoJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (configuracionFormaPagoJS.cacheDWR != null) {
            configuracionFormaPagoJS.findByCriteria(configuracionFormaPagoJS.cacheDWR);
        } else {
            configuracionFormaPagoJS.findByCriteria(new ConfiguracionFormaPagoDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchAfiliacion', '#configuracionFormaPagoGrid', '#configuracionFormaPagoform', configuracionFormaPagoJS, new ConfiguracionFormaPagoDWR());
    },
    
    reset: function () {
        jQuery('#configuracionFormaPagoGrid').clearGridData();
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
        configuracionFormaPagoJS.entity = entity;
        configuracionFormaPagoJS.headers = headers;
        configuracionFormaPagoJS.format = format;
        configuracionFormaPagoJS.reportName = jQuery('#key_configuracionFormaPago_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilDWR();
        if (configuracionFormaPagoJS.cacheDWR != null) {
            criteriaExample = configuracionFormaPagoJS.cacheDWR;
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
        ConfiguracionFormaPagoDWR.getReportDataTest(sortBy, sortType, criteriaExample, configuracionFormaPagoJS.exportarDatosCallback);
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
                configuracionFormaPagoJS.entity + '&headers=' + configuracionFormaPagoJS.headers +
                '&reportName=' + configuracionFormaPagoJS.reportName + '&format=' + configuracionFormaPagoJS.format +
                '&reportKey=' + reportKey;
    },
    
    //FUNCIONES PARA DESPLEGAR
    getDialogOrganizacion: function (){
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionFormaPagoGrid');
        if(seleccion.length>0){
            SeguridadPerfilDWR.getOrganizaciones(configuracionFormaPagoJS.getDialogOrganizacionCallBack);
        }else{
            utilDialog.showDialog('#d-noSelectedAfiliacion');
        }
    },
    getDialogOrganizacionCallBack: function (data){
       utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'configuracionFormaPagoJS.desplegar','nombre');
       utilDialog.showDialog('#dialogOrganizacionesCatalogo');
       $( "#dialogOrganizacionesCatalogo" ).dialog({
            width: 700,
            modal: true
        });
    },
    desplegar: function (data) {
        var page = jQuery('#bitacoraGridCurrentPage').val();
        var rows = jQuery('#bitacoraGridRowsByPage').val();
        var order = jQuery('#bitacoraGridOrderByColumn').val();
        var orderType = jQuery('#bitacoraGridOrderByType').val();
        var seleccion = utilGrid.gridGetSelectedRows('#configuracionFormaPagoGrid');
       if(seleccion!=null){
            var idEjecucion = new Date().getTime();//Math.round(+new Date()/1000);
            var listElementos = seleccion.join();
            utilEffect.showProgressBar();
            ConfiguracionFormaPagoDWR.desplegar(page, rows, order, orderType, data, listElementos, idEjecucion, configuracionFormaPagoJS.desplegarCallback);
        }else{
             utilEffect.showProgressBar();
        }
    },
    desplegarCallback: function (data){
        var grid = data.grid;
        if(grid.length > 0 ){
            var id = grid[ 0 ][ "estatusId" ];
            if(id==1)
                utilEffect.showToast('success','Se desplego Correctamente'); 
            else
                utilEffect.showToast('error','Intente nuevamente'); 
            utilEffect.hideProgressBar();
            jQuery('#bitacoraDiv').show();
            utilGrid.gridUpdate('#bitacoraGrid', '#bitacoraGridCurrentPage', '#bitacoraGridRowsByPage', data);
            utilGrid.autoWidthGrid();
        }else{
            utilEffect.hideProgressBar();
            utilEffect.showToast('error','Intente nuevamente'); 
        }
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
// Validaciones de configuracionFormaPagoform
validanguage.el.configuracionFormaPagoform_configuracionFormaPagoId = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.configuracionFormaPagoform_claveFormaPago = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.configuracionFormaPagoform_conectionMode = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 7) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 7 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_currency = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 3) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 3 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_afiliacion = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 10) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 10 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_urlReturn = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 100) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 100 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_urlCancel = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 100) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 100 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_username = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 100) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 100 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_contractId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 38) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 38 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_password = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 100) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 100 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.configuracionFormaPagoform_signature = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 100) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 100 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};