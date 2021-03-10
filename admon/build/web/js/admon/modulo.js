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
    // Elementos radio
    utilInput.fixRadios('#moduloform');

// <editor-fold defaultstate="collapsed" desc="ACCESOS">
    utilObject.getFuncionalidadPantalla("s","ModuloAction");
    
    // Botón : NUEVO
    jQuery('#btnNewModulo').click(function () {
        utilForm.reset('#moduloform');
        jQuery('#modulo_moduloId').val(0);
        moduloJS.modulo = null;
        //PARAMETRO
        parametroJS.reset();
        jQuery('#parametroDiv').hide();
        
    }).customButtonEffect('#btnNewModulo');
    // Botón : GUARDAR
    jQuery('#btnSaveModulo').click(function () {
        var validation = validanguage.validateForm('moduloform');
        if (validation.result) {
        	moduloJS.doCommit();
        }
    }).customButtonEffectBlue('#btnSaveModulo');
    // Botón DESPLEGAR
    jQuery('#btnDesplegar').click(function () {
        moduloJS.getDialogOrganizacion();
    }).css({'float': 'right', 'margin-right': '4px', 'margin-top': '15px'}).customButtonEffectBlue('#btnDesplegar');
   
    if (jQuery('#gridVisibleModulo').val() == 'true') {
        jQuery('#moduloGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
            	jQuery('#modulo_moduloId').val(),
            	jQuery('#modulo_aplicacionId').val(),
                jQuery('#modulo_aplicacion').val(),
                jQuery('#modulo_nombreES').val(),
                jQuery('#modulo_descripcion').val(),
                jQuery('#modulo_fechaModificacion').val(),
                jQuery('#modulo_usuarioModificacion').val(),
                jQuery('#modulo_activo').val(),
                jQuery('#modulo_modificar').val(),jQuery('#modulo_eliminar').val()
            ],
            colModel: [{
                    name: 'moduloId',
                    index: 'moduloId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'aplicacionId',
                    index: 'aplicacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                }, {
                    name: 'aplicacion',
                    index: 'aplicacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'descripcion',
                    index: 'descripcion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    resizable: false,
                    width: 40,
                    hidden: false,
                    search: false
                }, {
                    name: 'modificacionUsuarioStr',
                    index: 'modificacionUsuarioStr',
                    align: 'center',
                    resizable: false,
                    width: 40,
                    hidden: false,
                    search: false
                }, {
                    name      : 'State',
                    index     : 'State',
                    align     : 'center',
                    width     : 25,
                    sortable  : false,
                    search    : false,
                    resizable : false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 30,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 30,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 250,
            toolbar: false,
            hidegrid: true,
            multiselect: true,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#moduloGridPagerId'),
            caption: jQuery('#modulo.title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeModulo', 'Confirme', '&iquest;Desea eliminar el registro?', ['Aceptar@moduloJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionModulo', 'Confirme', '&iquest;Desea eliminar los registros seleccionados?', ['Aceptar@moduloJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusModulo', 'Confirme', '&iquest;Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@moduloJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedModulo', 'Error', 'Para poder realizar esta operaci&oacute;n seleccione uno &oacute; m&aacute;s registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-noSelectedRows', jQuery('#msgAlertaText').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoModulo', 'Advertencia', jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#moduloColumnasExportar", '#moduloGrid');
        utilDialog.setCustomDialogSetEstatus('#moduloSetEstatusActivoInactivo',
                'moduloJS.prepareToSetEstatus(1)', 'moduloJS.prepareToSetEstatus(0)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#moduloGrid', '#moduloGridPagerId', '#moduloGridCurrentPage', '#moduloGridOrderByColumn', '#moduloGridOrderByType', 'moduloJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchModulo');

        // Recargar el widget jqGrid
        moduloJS.reloadGrid();
        
        utilGrid.setHideElementsTable('moduloGridPagerId',['activate','export']);

    }
    // Grid configuracionParametro embebido
    parametroJS.inicializarGrid();
    bitacoraJS.inicializarGrid();
    //jQuery('#bitacoraDiv').show();
    
    // Limpiar la página
    jQuery('#btnNewModulo').click();
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

    // Si es individual precargar el formulario
    if (jQuery('#gridIndividualModeModulo').val() == 'true') {
        jQuery('#btnNewModulo').hide();
        moduloJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
    
    parametroJS.setTableName(jQuery('#key_tableName').val());
    parametroJS.setPropertyName(jQuery('#key_propertyName').val());
    
    parametroJS.LENGUAJE_DEFAULT = $('#moduloform_lenguajeId').val();
});

var moduloJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    modulo: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var modulo = utilObject.buildObject('#moduloform', new ModuloDWR());
        modulo.moduloId = 0;
        
        modulo.aplicacionId = $('#moduloform_aplicacionId').val();
        var listaModulos = [modulo];
        ModuloDWR.save(listaModulos, moduloJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var modulo = utilObject.buildObject('#moduloform', new ModuloDWR());
        modulo.moduloId = jQuery('#modulo_moduloId').val();
//        modulo.aplicacionId = $('#moduloform_aplicacionId').val();
        var listaModulos = [modulo];
        ModuloDWR.update(listaModulos, moduloJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        // Grid Multiple
        utilForm.reset('#moduloform');
        moduloJS.modulo = null;
        moduloJS.reloadGrid();
        utilEffect.hideProgressBar();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = moduloJS.getEliminarId();
        var listaIds = [id];
        ModuloDWR.remove(listaIds, moduloJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#moduloGrid');
        ModuloDWR.remove(listaIds, moduloJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewModulo').click();
        moduloJS.reloadGrid();
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
        var lenguajeId = $('#moduloform_lenguajeId').val();
        ModuloDWR.findById(id,lenguajeId, moduloJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#moduloform');
        utilForm.populate('#moduloform', data);
        $('#modulo_moduloId').val(data.moduloId);
//        $('#aplicacionId').val(data.aplicacionId);
        utilEffect.hideProgressBar();
        moduloJS.modulo = data;
        //TAG-PARAMETRO
        jQuery('#parametroDiv').show();
        //buscar parametros de la aplicacion
        utilForm.reset('#parametroform');
        jQuery('#parametroform_fkId').val(data.moduloId);
        jQuery('#parametroform_lenguajeId').val(data.lenguajeId);
        data.fkId = data.moduloId;
        parametroJS.setFkId(data.moduloId);
        parametroJS.findByCriteria(data);
        utilGrid.autoWidthGrid();
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
        moduloJS.cacheDWR = obj;
        var page = jQuery('#moduloGridCurrentPage').val();
        var rows = jQuery('#moduloGridRowsByPage').val();
        var order = jQuery('#moduloGridOrderByColumn').val();
        var orderType = jQuery('#moduloGridOrderByType').val();
        obj.lenguajeId = $('#moduloform_lenguajeId').val();
        utilEffect.showProgressBar();
        ModuloDWR.findByCriteria(page, rows, order, orderType, obj, function(data){
        	utilGrid.gridUpdate('#moduloGrid', '#moduloGridCurrentPage', '#moduloGridRowsByPage', data);
            utilEffect.hideProgressBar();
        });
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ModuloDWR.findFirst(moduloJS.findByIdCallback);
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
        var nombre = jQuery('#moduloform_nombre').val();
        ModuloDWR.isValidoNombre(nombre, moduloJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#modulo_moduloId').val() != 0) {
            moduloJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#modulo_moduloId').val() == 0) {
            moduloJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#modulo_moduloId').val() != 0) {
            moduloJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoModulo');
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
        var lenguajeId = $('#moduloform_lenguajeId').val();
        ModuloDWR.setEstatus(estatusId, lenguajeId, listaObjetos, moduloJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = moduloJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#moduloGrid');
        ModuloDWR.setEstatus(estatusId, listaObjetos, moduloJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        moduloJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#moduloGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedModulo');
        } else {
            moduloJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusModulo' );
            moduloJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#moduloGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedModulo');
        } else {
            utilDialog.showDialog('#d-removeSeleccionModulo');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        moduloJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeModulo');
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
        moduloJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return moduloJS.eliminarId;
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
        var validation = validanguage.validateForm('moduloform');
        if (validation.result) {
            moduloJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#modulo_moduloId').val() == 0) {
            moduloJS.save();
        } else {
            moduloJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (moduloJS.cacheDWR != null) {
            moduloJS.findByCriteria(moduloJS.cacheDWR);
        } else {
            moduloJS.findByCriteria(new ModuloDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchModulo', '#moduloGrid', '#moduloform', moduloJS, new ModuloDWR());
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
        moduloJS.entity = entity;
        moduloJS.headers = headers;
        moduloJS.format = format;
        moduloJS.reportName = jQuery('#modulo.title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new ModuloDWR();
        if (moduloJS.cacheDWR != null) {
            criteriaExample = moduloJS.cacheDWR;
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
        ModuloDWR.getReportDataTest(sortBy, sortType, criteriaExample, moduloJS.exportarDatosCallback);
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
                moduloJS.entity + '&headers=' + moduloJS.headers +
                '&reportName=' + moduloJS.reportName + '&format=' + moduloJS.format +
                '&reportKey=' + reportKey;
    },
    
//    getAplicaciones: function(aplicacionId){
//        utilEffect.showProgressBar();
//        ModuloDWR.getAplicaciones( function(data){
//        	var options = $("#aplicacionId");
//        	options.empty();
//        	options.append($("<option />").val("-1").text("Seleccione"));
//        	$.each(data, function() {
//        	    options.append($("<option />").val(this.aplicacionId).text(this.nombre));
//        	});
//        	if(aplicacionId!=null){
//        		$("#aplicacionId").val(aplicacionId);
//        		
//        	}
//            utilEffect.hideProgressBar();
//        });
//    },
    
    getModulos: function(moduloId){
        utilEffect.showProgressBar();
        
        var aplicacionId = $("#aplicacionId").val();
        ModuloDWR.getModulos(aplicacionId, function(data){
        	var options = $("#modulo_moduloId");
        	options.empty();
        	options.append($("<option />").val("-1").text("Seleccione"));
        	$.each(data, function() {
        	    options.append($("<option />").val(this.moduloId).text(this.nombre));
        	});

            utilEffect.hideProgressBar();
        });
    },
    
    getDialogOrganizacion: function (){
        var seleccion = utilGrid.gridGetSelectedRows('#moduloGrid');
        if(seleccion.length > 0){
           SeguridadPerfilDWR.getOrganizaciones(moduloJS.getDialogOrganizacionCallBack);
        }else{
            utilDialog.showDialog('#d-noSelectedRows');
        }
    },
    getDialogOrganizacionCallBack: function (data){
       utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'moduloJS.desplegar','nombre');
       utilDialog.showDialog('#dialogOrganizacionesCatalogo');
       $( "#dialogOrganizacionesCatalogo" ).dialog({
            width: 700,
            modal: true
        });
    },
    getDataID: function(data){
        var dwrObjectList = new Array();
        var dwrObjectListOrg = new Array();
        var seleccion = utilGrid.gridGetSelectedRows('#moduloGrid');
        listElementos.push(valor);
        console.info(seleccion.join());
        var listElementos = seleccion.join();
    },
    desplegar: function (data) {
        var page = jQuery('#bitacoraGridCurrentPage').val();
        var rows = jQuery('#bitacoraGridRowsByPage').val();
        var order = jQuery('#bitacoraGridOrderByColumn').val();
        var orderType = jQuery('#bitacoraGridOrderByType').val();
        
       console.log("DESPLEGANDO.....");
       console.log(data);
       var seleccion = utilGrid.gridGetSelectedRows('#moduloGrid');
       var idEjecucion = new Date().getTime();//Math.round(+new Date()/1000);
       console.info(seleccion.join());
       var listElementos = seleccion.join();
       console.info(idEjecucion);
       utilEffect.showProgressBar();
       console.log(data);
       ModuloDWR.desplegar(page, rows, order, orderType, data, listElementos, idEjecucion, moduloJS.desplegarCallback);
    },
    desplegarCallback: function (data){
        var grid = data.grid;
//        console.info(grid);
        var id = grid[ 0 ][ "estatusId" ];
//        console.info(id);
        if(id==1)
            utilEffect.showToast('success','Se desplego Correctamente'); 
        else
            utilEffect.showToast('error','Intente nuevamente'); 
        utilEffect.hideProgressBar();
        jQuery('#bitacoraDiv').show();
        utilGrid.gridUpdate('#bitacoraGrid', '#bitacoraGridCurrentPage', '#bitacoraGridRowsByPage', data);
        utilGrid.autoWidthGrid();
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
// Validaciones de moduloform
/*
validanguage.el.moduloform_code = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.moduloform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
*/

// Validaciones de moduloform
validanguage.el.moduloform_aplicacionId = {
    characters: {
        mode: 'allow', expression: 'numeric', suppress: false},
    validations: [ ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.moduloform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 200) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 200 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.moduloform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 60) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 60 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.moduloform_orden = {
	    characters: {
	        mode: 'allow', expression: 'numeric', suppress: false},
	    validations: [
	        ProfileStripWhitespace,ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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
validanguage.el.moduloform_nombreAction = {
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
        }
    ]};
validanguage.el.moduloform_nombreObjeto = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 20) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 20 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
validanguage.el.moduloform_htmlId = {
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
        }
    ]};
validanguage.el.moduloform_url = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 300) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 300 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};