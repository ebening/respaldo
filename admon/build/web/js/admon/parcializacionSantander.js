
var parcializacionSantanderJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    parcializacionSantander: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de parcializacionSantanderr, ordenar, etc.
     */
    cacheDWR: null,
    inicializarGrid: function () {
        // <editor-fold defaultstate="collapsed" desc="Botones">
        // Botón : NUEVO
        jQuery('#btnNewParcializacionSantander').click(function () {
            parcializacionSantanderJS.inicializarValores();
        }).customButtonEffect('#btnNewParcializacionSantander');
        // Botón : GUARDAR
        jQuery('#btnAddParcializacionSantander').click(function () {
            parcializacionSantanderJS.prepareToSave();
        }).customButtonEffectBlue('#btnAddParcializacionSantander');
        // </editor-fold>

//  if (jQuery('#gridVisibleAfiliacionSantander').val() == 'true') {
        jQuery('#parcializacionSantanderGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#parcializacionSantander_parcializacionSantanderIdText').html(),
                jQuery('#parcializacionSantander_parcializacionText').html(),
                jQuery('#parcializacionSantander_merchantText').html(),
                jQuery('#parcializacionSantander_semilla3dsText').html(),

                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'parcializacionSantanderId',
                    index: 'parcializacionSantanderId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'parcializacion',
                    index: 'parcializacion',
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
                    search: true
                }, {
                    name: 'semilla3ds',
                    index: 'semilla3ds',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 40,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 40,
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
            pager: jQuery('#parcializacionSantanderGridPagerId'),
            caption: jQuery('#key_parcializacionSantander_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeParcializacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@parcializacionSantanderJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionParcializacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@parcializacionSantanderJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusParcializacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@parcializacionSantanderJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedParcializacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoParcializacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), [ 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#parcializacionSantanderColumnasExportar", '#parcializacionSantanderGrid');
        utilDialog.setCustomDialogSetEstatus('#parcializacionSantanderSetEstatusActivoInactivo',
                'parcializacionSantanderJS.prepareToSetEstatus(1)', 'parcializacionSantanderJS.prepareToSetEstatus(0)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#parcializacionSantanderGrid', '#parcializacionSantanderGridPagerId', '#parcializacionSantanderGridCurrentPage', '#parcializacionSantanderGridOrderByColumn',
                '#parcializacionSantanderGridOrderByType', 'parcializacionSantanderJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchParcializacionSantander');
        utilGrid.setHideElementsTable('parcializacionSantanderGridPagerId',['activate','export','search']);
        // Recargar el widget jqGrid
        parcializacionSantanderJS.reloadGrid();

//    }
    // Grid configuracionParametro embebido
    //parametroJS.inicializarGrid();
    
    
    // Limpiar la página
    jQuery('#btnNewParcializacionSantander').click();
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
    if (jQuery('#gridIndividualModeParcializacionSantander').val() == 'true') {
        jQuery('#btnNewParcializacionSantander').hide();
        parcializacionSantanderJS.findFirst();
    }
       
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    inicializarValores: function () {
        utilForm.reset('#parcializacionSantanderform');
        jQuery('#parcializacionSantanderform_afiliacion').val(jQuery('#afiliacionSantanderform_afiliacion').val());
        jQuery('#parcializacionSantanderform_claveInstitucionBancaria').val(jQuery('#afiliacionSantanderform_claveInstitucionBancaria').val());
        var parcializacion = utilObject.buildObject('#parcializacionSantanderform', new ParcializacionesSantanderDWR());
        parcializacionSantanderJS.parcializacionSantander = parcializacion;
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var parcializacionSantander = utilObject.buildObject('#parcializacionSantanderform', new ParcializacionesSantanderDWR());
        parcializacionSantander.parcializacionSantanderId = 0;
        var listaParcializacionSantanders = [parcializacionSantander];
        ParcializacionesSantanderDWR.save(listaParcializacionSantanders, parcializacionSantanderJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var parcializacionSantander = utilObject.buildObject('#parcializacionSantanderform', new ParcializacionesSantanderDWR());
        parcializacionSantander.parcializacionSantanderId = jQuery('#parcializacionSantanderform_parcializacionSantanderId').val();
        
        var listaParcializacionSantanders = [parcializacionSantander];
        ParcializacionesSantanderDWR.update(listaParcializacionSantanders, parcializacionSantanderJS.saveOrUpdateCallback);
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
        utilForm.reset('#parcializacionSantanderform');
        jQuery('#parcializacionSantanderform_parcializacionSantanderId').val(0);
        parcializacionSantanderJS.parcializacionSantander = null;
//        parcializacionSantanderJS.reloadGrid();
        parcializacionSantanderJS.inicializarValores();

        utilEffect.hideProgressBar();


        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
        parcializacionSantanderJS.reloadGrid();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = parcializacionSantanderJS.getEliminarId();
        var listaIds = [id];
        ParcializacionesSantanderDWR.remove(listaIds, parcializacionSantanderJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#parcializacionSantanderGrid');
        ParcializacionesSantanderDWR.remove(listaIds, parcializacionSantanderJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
//        parcializacionSantanderJS.getParcializacionSantanderes();
        jQuery('#btnNewParcializacionSantander').click();
        parcializacionSantanderJS.reloadGrid();
    },
    /*
     * Función que actualiza las opciones del select anidar despues de haber hecho 
     * una modificacion a la base de datos
     * 
     */
    getParcializacionSantanderes: function () {
        utilEffect.showProgressBar();
        ParcializacionesSantanderDWR.getParcializacionSantanderes(parcializacionSantanderJS.getParcializacionSantanderesCallback);
    },
    /*
     * Callback de la función getParcializacionSantanderes(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getParcializacionSantanderesCallback: function (pageList) {
        jQuery('#parcializacionSantanderform_anidar').find('option').remove();
        for (var i = 0; i < pageList.length; i++) {
            var parcializacionSantander = pageList[i];
            var id = parcializacionSantander.parcializacionSantanderId;
            var nombre = parcializacionSantander.nombre;
            var style = '';
            if (parcializacionSantander.url == null || parcializacionSantander.url == "") {
                style = 'style="font-weight: bold; color: #33B5E5"';
            }
            jQuery('#parcializacionSantanderform_anidar').append('<option ' + style +
                    ' value="' + id + '">' + nombre + '</option>');
        }
        utilEffect.hideProgressBar();
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
        ParcializacionesSantanderDWR.findById(id, parcializacionSantanderJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#parcializacionSantanderform');
        utilForm.populate('#parcializacionSantanderform', data);
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
        console.debug(obj);
         var parcializacion = utilObject.buildObject('#parcializacionSantanderform', new ParcializacionesSantanderDWR());
         console.debug(parcializacion);
        if( parcializacion != null ){
            obj.afiliacion = parcializacion.afiliacion;
            parcializacionSantanderJS.cacheDWR = obj;
            var page = jQuery('#parcializacionSantanderGridCurrentPage').val();
            var rows = jQuery('#parcializacionSantanderGridRowsByPage').val();
            var order = jQuery('#parcializacionSantanderGridOrderByColumn').val();
            var orderType = jQuery('#parcializacionSantanderGridOrderByType').val();
            obj.lenguajeId = $('#parcializacionSantanderform_lenguajeId').val();
            utilEffect.showProgressBar();
            ParcializacionesSantanderDWR.findByCriteria(page, rows, order, orderType, obj, parcializacionSantanderJS.findByCriteriaCallback);
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
        console.info(data);
        utilGrid.gridUpdate('#parcializacionSantanderGrid', '#parcializacionSantanderGridCurrentPage', '#parcializacionSantanderGridRowsByPage', data);
        utilGrid.autoWidthGrid();
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la parcializacionSantander es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ParcializacionesSantanderDWR.findFirst(parcializacionSantanderJS.findByIdCallback);
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
        var nombre = jQuery('#parcializacionSantanderform_parcializacion').val();
        ParcializacionesSantanderDWR.isValidoNombre(nombre, parcializacionSantanderJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#parcializacionSantanderform_parcializacionSantanderId').val() != 0) {
            parcializacionSantanderJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#parcializacionSantanderform_parcializacionSantanderId').val() == 0) {
            parcializacionSantanderJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#parcializacionSantanderform_parcializacionSantanderId').val() != 0) {
            parcializacionSantanderJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoParcializacionSantander');
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
        ParcializacionesSantanderDWR.setEstatus(estatusId, listaObjetos, parcializacionSantanderJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = parcializacionSantanderJS.estatusId;
//        console.info(estatusId);
        var listaObjetos = utilGrid.gridGetSelectedRows('#parcializacionSantanderGrid');
        ParcializacionesSantanderDWR.setEstatus(estatusId, listaObjetos, parcializacionSantanderJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        parcializacionSantanderJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
//        console.info(estatusId);
        var seleccion = utilGrid.gridGetSelectedRows('#parcializacionSantanderGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedParcializacionSantander');
        } else {
            parcializacionSantanderJS.estatusId = estatusId;
             utilDialog.showDialog( '#d-confirmSetEstatusParcializacionSantander' );
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#parcializacionSantanderGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedParcializacionSantander');
        } else {
            utilDialog.showDialog('#d-removeSeleccionParcializacionSantander');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        parcializacionSantanderJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeParcializacionSantander');
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
        parcializacionSantanderJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return parcializacionSantanderJS.eliminarId;
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
        var validation = validanguage.validateForm('parcializacionSantanderform');
        if (validation.result) {
            parcializacionSantanderJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#parcializacionSantanderform_parcializacionSantanderId').val() == 0) {
            parcializacionSantanderJS.save();
        } else {
            parcializacionSantanderJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (parcializacionSantanderJS.cacheDWR != null) {
            parcializacionSantanderJS.findByCriteria(parcializacionSantanderJS.cacheDWR);
        } else {
            parcializacionSantanderJS.findByCriteria(new ParcializacionesSantanderDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchParcializacionSantander', '#parcializacionSantanderGrid', '#parcializacionSantanderform', parcializacionSantanderJS, new ParcializacionesSantanderDWR());
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
        parcializacionSantanderJS.entity = entity;
        parcializacionSantanderJS.headers = headers;
        parcializacionSantanderJS.format = format;
        parcializacionSantanderJS.reportName = jQuery('#key_parcializacionSantander_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new ParcializacionesSantanderDWR();
        if (parcializacionSantanderJS.cacheDWR != null) {
            criteriaExample = parcializacionSantanderJS.cacheDWR;
        }
        // utilEffect.showProgressBar();
        jQuery().toastmessage('showToast', {
            text: jQuery('#msgFiltrandoInformacion').val(),
            sticky: false,
            position: 'bottom-center',
            type: 'notice',
            closeText: '',
            close: function () {
            }
        });
        ParcializacionesSantanderDWR.getReportDataTest(sortBy, sortType, criteriaExample, parcializacionSantanderJS.exportarDatosCallback);
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
                parcializacionSantanderJS.entity + '&headers=' + parcializacionSantanderJS.headers +
                '&reportName=' + parcializacionSantanderJS.reportName + '&format=' + parcializacionSantanderJS.format +
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
// Validaciones de parcializacionSantanderform
validanguage.el.parcializacionSantanderform_parcializacion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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
validanguage.el.parcializacionSantanderform_merchant = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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

