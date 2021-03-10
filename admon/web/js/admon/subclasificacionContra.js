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
    utilInput.fixRadios('#subclasificacionContraform');

    utilObject.getFuncionalidadPantalla("","SubclasificacionContraAction");
    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewSubclasificacionContra').click(function () {
        utilForm.reset('#subclasificacionContraform');
        jQuery('#subclasificacionContraform_subclasificacionContraId').val(0);
        subclasificacionContraJS.subclasificacionContra = null;
    }).customButtonEffect('#btnNewSubclasificacionContra');
    // Botón : GUARDAR
    jQuery('#btnSaveSubclasificacionContra').click(function () {
        subclasificacionContraJS.prepareToSave();
    }).customButtonEffect('#btnSaveSubclasificacionContra');
    // </editor-fold>

    if (jQuery('#gridVisibleSubclasificacionContra').val() == 'true') {
        jQuery('#subclasificacionContraGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#subclasificacionContra_subclasificacionContraIdText').html(),
                jQuery('#subclasificacionContra_clasificacionContraIdText').html(),
                jQuery('#subclasificacionContra_nombreText').html(),
                jQuery('#subclasificacionContra_ordenText').html(),
                jQuery('#subclasificacionContra_estatusIdText').html(),
                jQuery('#subclasificacionContra_eliminadoIdText').html(),
                jQuery('#subclasificacionContra_creacionFechaText').html(),
                jQuery('#subclasificacionContra_creacionUsuarioText').html(),
                jQuery('#subclasificacionContra_modificacionFechaText').html(),
                jQuery('#subclasificacionContra_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'subclasificacionContraId',
                    index: 'subclasificacionContraId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'clasificacionContraId',
                    index: 'clasificacionContraId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'orden',
                    index: 'orden',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'estatusId',
                    index: 'estatusId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                }, {
                    name: 'eliminadoId',
                    index: 'eliminadoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionFecha',
                    index: 'creacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'creacionUsuario',
                    index: 'creacionUsuario',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: false,
                    search: false
                }, {
                    name: 'State',
                    index: 'State',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: true,
                    hidden: true,
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
            pager: jQuery('#subclasificacionContraGridPagerId'),
            caption: jQuery('#key_subclasificacioncontra_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeSubclasificacionContra',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarText').val(), ['Aceptar@subclasificacionContraJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionSubclasificacionContra', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@subclasificacionContraJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusSubclasificacionContra', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@subclasificacionContraJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedSubclasificacionContra',jQuery('#msgError'), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoSubclasificacionContra', 'Advertencia',  jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#subclasificacionContraColumnasExportar", '#subclasificacionContraGrid');
        utilDialog.setCustomDialogSetEstatus('#subclasificacionContraSetEstatusActivoInactivo',
                'subclasificacionContraJS.prepareToSetEstatus(1)', 'subclasificacionContraJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#subclasificacionContraGrid', '#subclasificacionContraGridPagerId', '#subclasificacionContraGridCurrentPage', '#subclasificacionContraGridOrderByColumn',
                '#subclasificacionContraGridOrderByType', 'subclasificacionContraJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchSubclasificacionContra');
        utilGrid.setHideElementsTable('subclasificacionContraGridPagerId',['export']);
        // Recargar el widget jqGrid
        subclasificacionContraJS.reloadGrid();

    }
    // Limpiar la página
    jQuery('#btnNewSubclasificacionContra').click();
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
    if (jQuery('#gridIndividualModeSubclasificacionContra').val() == 'true') {
        jQuery('#btnNewSubclasificacionContra').hide();
        subclasificacionContraJS.findFirst();
    }
    jQuery('#grid-action-activar-inactivar').hide().next().hide();
});

var subclasificacionContraJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    subclasificacionContra: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var subclasificacionContra = utilObject.buildObject('#subclasificacionContraform', new SubclasificacionContraDWR());
        subclasificacionContra.subclasificacionContraId = 0;
        var listaSubclasificacionContras = [subclasificacionContra];
        SubclasificacionContraDWR.save(listaSubclasificacionContras, subclasificacionContraJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var subclasificacionContra = utilObject.buildObject('#subclasificacionContraform', new SubclasificacionContraDWR());
        subclasificacionContra.subclasificacionContraId = jQuery('#subclasificacionContraform_subclasificacionContraId').val();
        var listaSubclasificacionContras = [subclasificacionContra];
        SubclasificacionContraDWR.update(listaSubclasificacionContras, subclasificacionContraJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModeSubclasificacionContra').val() == 'false') {
            utilForm.reset('#subclasificacionContraform');
            jQuery('#subclasificacionContraform_subclasificacionContraId').val(0);
            subclasificacionContraJS.subclasificacionContra = null;
            subclasificacionContraJS.reloadGrid();
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = subclasificacionContraJS.getEliminarId();
        var listaIds = [id];
        SubclasificacionContraDWR.remove(listaIds, subclasificacionContraJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#subclasificacionContraGrid');
        SubclasificacionContraDWR.remove(listaIds, subclasificacionContraJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewSubclasificacionContra').click();
        subclasificacionContraJS.reloadGrid();
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
        SubclasificacionContraDWR.findById(id, subclasificacionContraJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#subclasificacionContraform');
        utilForm.populate('#subclasificacionContraform', data);
        subclasificacionContraJS.subclasificacionContra = data;
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
        subclasificacionContraJS.cacheDWR = obj;
        var page = jQuery('#subclasificacionContraGridCurrentPage').val();
        var rows = jQuery('#subclasificacionContraGridRowsByPage').val();
        var order = jQuery('#subclasificacionContraGridOrderByColumn').val();
        var orderType = jQuery('#subclasificacionContraGridOrderByType').val();
        utilEffect.showProgressBar();
        SubclasificacionContraDWR.findByCriteria(page, rows, order, orderType, obj, subclasificacionContraJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#subclasificacionContraGrid', '#subclasificacionContraGridCurrentPage', '#subclasificacionContraGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        SubclasificacionContraDWR.findFirst(subclasificacionContraJS.findByIdCallback);
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
        var clasificacion = jQuery('#subclasificacionContraform_clasificacionContraId').val();
        var nombre = jQuery('#subclasificacionContraform_nombre').val();
        SubclasificacionContraDWR.isValidoNombre(clasificacion, nombre, subclasificacionContraJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#subclasificacionContraform_subclasificacionContraId').val() != 0) {
            subclasificacionContraJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#subclasificacionContraform_subclasificacionContraId').val() == 0) {
            subclasificacionContraJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#subclasificacionContraform_subclasificacionContraId').val() != 0) {
            subclasificacionContraJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoSubclasificacionContra');
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
        SubclasificacionContraDWR.setEstatus(estatusId, listaObjetos, subclasificacionContraJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = subclasificacionContraJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#subclasificacionContraGrid');
        SubclasificacionContraDWR.setEstatus(estatusId, listaObjetos, subclasificacionContraJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        subclasificacionContraJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#subclasificacionContraGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedSubclasificacionContra');
        } else {
            subclasificacionContraJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusSubclasificacionContra' );
            subclasificacionContraJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#subclasificacionContraGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedSubclasificacionContra');
        } else {
            utilDialog.showDialog('#d-removeSeleccionSubclasificacionContra');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        subclasificacionContraJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeSubclasificacionContra');
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
        subclasificacionContraJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return subclasificacionContraJS.eliminarId;
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
        var validation = validanguage.validateForm('subclasificacionContraform');
        if (validation.result) {
            subclasificacionContraJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#subclasificacionContraform_subclasificacionContraId').val() == 0) {
            subclasificacionContraJS.save();
        } else {
            subclasificacionContraJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (subclasificacionContraJS.cacheDWR != null) {
            subclasificacionContraJS.findByCriteria(subclasificacionContraJS.cacheDWR);
        } else {
            subclasificacionContraJS.findByCriteria(new SubclasificacionContraDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select subclasificacionContraform_clasificacionContraId para recargarlo mediante AJAX.
     */
    getClasificacionContra: function () {
        utilEffect.showProgressBar();
        SubclasificacionContraDWR.getClasificacionContra(subclasificacionContraJS.getClasificacionContraCallback);
    },
    /*
     * Callback de la función getClasificacionContra() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getClasificacionContraCallback: function (list) {
        var idCmb = jQuery('#subclasificacionContraform_clasificacionContraId').val();
        jQuery('#subclasificacionContraform_clasificacionContraId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.clasificacionContraId;
            var nombre = object.nombre;
            jQuery('#subclasificacionContraform_clasificacionContraId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#subclasificacionContraform_clasificacionContraId option[value='" + idCmb + "']").length > 0) {
                jQuery('#subclasificacionContraform_clasificacionContraId').val(idCmb);
            } else {
                jQuery('#subclasificacionContraform_clasificacionContraId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#subclasificacionContraform_clasificacionContraId option[value='" + sel + "']").length > 0) {
                jQuery('#subclasificacionContraform_clasificacionContraId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchSubclasificacionContra', '#subclasificacionContraGrid', '#subclasificacionContraform', subclasificacionContraJS, new SubclasificacionContraDWR());
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
        subclasificacionContraJS.entity = entity;
        subclasificacionContraJS.headers = headers;
        subclasificacionContraJS.format = format;
        subclasificacionContraJS.reportName = jQuery('#key_subclasificacioncontra_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new SubclasificacionContraDWR();
        if (subclasificacionContraJS.cacheDWR != null) {
            criteriaExample = subclasificacionContraJS.cacheDWR;
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
        SubclasificacionContraDWR.getReportDataTest(sortBy, sortType, criteriaExample, subclasificacionContraJS.exportarDatosCallback);
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
                subclasificacionContraJS.entity + '&headers=' + subclasificacionContraJS.headers +
                '&reportName=' + subclasificacionContraJS.reportName + '&format=' + subclasificacionContraJS.format +
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
// Validaciones de subclasificacionContraform
validanguage.el.subclasificacionContraform_clasificacionContraId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.subclasificacionContraform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.subclasificacionContraform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

