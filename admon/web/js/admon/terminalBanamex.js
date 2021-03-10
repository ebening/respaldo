var terminalBanamexJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    terminalBanamex: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    
    inicializarGrid: function (){
    // <editor-fold defaultstate="collapsed" desc="Botones">
        // Botón : NUEVO
        jQuery('#btnNewTerminalBanamex').click(function () {
            terminalBanamexJS.inicializarValores();
        }).customButtonEffect('#btnNewTerminalBanamex');
        // Botón : GUARDAR
        jQuery('#btnAddTerminalBanamex').click(function () {
            terminalBanamexJS.prepareToSave();
        }).customButtonEffectBlue('#btnAddTerminalBanamex');
    // </editor-fold>

        // if (jQuery('#gridVisibleAfiliacionBanamex').val() == 'true') {
        jQuery('#terminalBanamexGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#afiliacion_afiliacionText').html(),
                jQuery('#afiliacion_afiliacionText').html(),
                jQuery('#terminalesbanamex_accessCodeText').html(),
                jQuery('#terminalesbanamex_merchantText').html(),
                jQuery('#terminalesbanamex_puntoventaText').html(),
                jQuery('#terminalesbanamex_cancelacionText').html(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'terminalBanamexId',
                    index: 'terminalBanamexId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'afiliacionBanamex',
                    index: 'afiliacionBanamex',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                },{
                    name: 'accessCode',
                    index: 'accessCode',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'merchant',
                    index: 'merchant',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'puntoVentaTerminal',
                    index: 'puntoVentaTerminal',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'cancelacion',
                    index: 'cancelacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                },{
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
            pager: jQuery('#terminalBanamexGridPagerId'),
            caption: jQuery('#key_terminalesbanamex_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeTerminalBanamex',jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@terminalBanamexJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionTerminalBanamex',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@terminalBanamexJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusTerminalBanamex',jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@terminalBanamexJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedTerminalBanamex', jQuery('#msgError').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoTerminalBanamex', 'Advertencia', jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#terminalBanamexColumnasExportar", '#terminalBanamexGrid');
        
        // Inicializaciones extra para el grid
        utilGrid.setup('#terminalBanamexGrid', '#terminalBanamexGridPagerId', '#terminalBanamexGridCurrentPage', '#terminalBanamexGridOrderByColumn',
                '#terminalBanamexGridOrderByType', 'terminalBanamexJS');

        // Crear el modal de búsqueda
        utilSearch.buildSearch('#d-searchAfiliacionBanamexPar');
        utilGrid.setHideElementsTable('terminalBanamexGridPagerId',['activate','export','search']);

        // }
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
         utilGrid.autoWidthGrid();
    },
    inicializarValores: function () {
        utilForm.reset('#terminalesBanamexform');
        var afiliacion =jQuery('#afiliacionbanamexform_afiliacionBanamex').val();
        jQuery('#terminalesBanamexform_afiliacionBanamex').val(afiliacion);
        var terminalBanamex = utilObject.buildObject('#terminalesBanamexform', new ParcializacionBanamexDWR());
        terminalBanamexJS.terminalBanamex = terminalBanamex;
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var terminalBanamex = utilObject.buildObject('#terminalesBanamexform', new TerminalBanamexDWR());
        terminalBanamex.terminalBanamexId = 0;
        if(terminalBanamex.accessCode != null || terminalBanamex.merchant != null || terminalBanamex.puntoVentaTerminal != null
                || terminalBanamex.cancelacion != null){
             var listaAfiliaciones = [terminalBanamex];
            TerminalBanamexDWR.save(listaAfiliaciones, terminalBanamexJS.saveOrUpdateCallback);
        }
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var terminalBanamex = utilObject.buildObject('#terminalesBanamexform', new TerminalBanamexDWR());
        terminalBanamex.terminalBanamexId = jQuery('#terminalesBanamexform_terminalBanamexId').val();
        var listaAfiliaciones = [terminalBanamex];
        TerminalBanamexDWR.update(listaAfiliaciones, terminalBanamexJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        jQuery('#btnNewTerminalBanamex').click();
        terminalBanamexJS.inicializarValores();
        terminalBanamexJS.reloadGrid();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());  
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = terminalBanamexJS.getEliminarId();
        var listaIds = [id];
        TerminalBanamexDWR.remove(listaIds, terminalBanamexJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#terminalBanamexGrid');
        TerminalBanamexDWR.remove(listaIds, terminalBanamexJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewTerminalBanamex').click();
        terminalBanamexJS.reloadGrid();
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
        TerminalBanamexDWR.findById(id, terminalBanamexJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#terminalesBanamexform');
        utilForm.populate('#terminalesBanamexform', data);
        terminalBanamexJS.terminalBanamex = data;
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
        var terminalBanamex = utilObject.buildObject('#terminalesBanamexform', new TerminalBanamexDWR());
        if( terminalBanamex != null ){
            var afiliacion = terminalBanamex.afiliacionBanamex;
            terminalBanamexJS.cacheDWR = obj;
            var page = jQuery('#terminalBanamexGridCurrentPage').val();
            var rows = jQuery('#terminalBanamexGridRowsByPage').val();
            var order = jQuery('#terminalBanamexGridOrderByColumn').val();
            var orderType = jQuery('#terminalBanamexGridOrderByType').val();
            utilEffect.showProgressBar();
            TerminalBanamexDWR.findByCriteria(page, rows, order, orderType, obj,afiliacion, terminalBanamexJS.findByCriteriaCallback);
        }
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#terminalBanamexGrid', '#terminalBanamexGridCurrentPage', '#terminalBanamexGridRowsByPage', data);
        utilGrid.autoWidthGrid();
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        TerminalBanamexDWR.findFirst(terminalBanamexJS.findByIdCallback);
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
         var terminalBanamex = utilObject.buildObject('#terminalesBanamexform', new TerminalBanamexDWR());
        TerminalBanamexDWR.isValidoNombre(terminalBanamex, terminalBanamexJS.isValidoNombreCallback);
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
      //  if (isValido == false && jQuery('#terminalesBanamexform_afiliacionBanamexId').val() != 0) {
            terminalBanamexJS.doCommit();
        /*    return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#terminalesBanamexform_afiliacionBanamexId').val() == 0) {
            terminalBanamexJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#terminalesBanamexform_afiliacionBanamexId').val() != 0) {
            terminalBanamexJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoAfiliacionBanamex');*/
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#terminalBanamexGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedTerminalBanamex');
        } else {
            utilDialog.showDialog('#d-removeSeleccionTerminalBanamex');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        terminalBanamexJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeTerminalBanamex');
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
        terminalBanamexJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return terminalBanamexJS.eliminarId;
    },
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('terminalesBanamexform');
        if (validation.result) {
            terminalBanamexJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#terminalesBanamexform_terminalBanamexId').val() == 0) {
            terminalBanamexJS.save();
        } else {
            terminalBanamexJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (terminalBanamexJS.cacheDWR != null) {
            terminalBanamexJS.findByCriteria(terminalBanamexJS.cacheDWR);
        } else {
            terminalBanamexJS.findByCriteria(new TerminalBanamexDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchTerminalBanamex', '#terminalBanamexGrid', '#terminalesBanamexform', terminalBanamexJS, new TerminalBanamexDWR());
    },
    
    reset: function () {
        jQuery('#terminalBanamexGrid').clearGridData();
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
        terminalBanamexJS.entity = entity;
        terminalBanamexJS.headers = headers;
        terminalBanamexJS.format = format;
        terminalBanamexJS.reportName = jQuery('#key_afiliacionBanamex_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilDWR();
        if (terminalBanamexJS.cacheDWR != null) {
            criteriaExample = terminalBanamexJS.cacheDWR;
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
        TerminalBanamexDWR.getReportDataTest(sortBy, sortType, criteriaExample, terminalBanamexJS.exportarDatosCallback);
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
                terminalBanamexJS.entity + '&headers=' + terminalBanamexJS.headers +
                '&reportName=' + terminalBanamexJS.reportName + '&format=' + terminalBanamexJS.format +
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
// Validaciones de terminalesBanamexform
validanguage.el.terminalesBanamexform_afiliacionBanamex = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.terminalesBanamexform_accessCode = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
        }]};
validanguage.el.terminalesBanamexform_merchant = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 30) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 30 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.terminalesBanamexform_puntoVentaTerminal = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.terminalesBanamexform_cancelacion = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
