var parcializacionBanamexJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    parcializacionBanamex: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    inicializarGrid: function () {
        // <editor-fold defaultstate="collapsed" desc="Botones">
        // Botón : NUEVO
        jQuery('#btnNewParcializacionBanamex').click(function () {
            parcializacionBanamexJS.inicializarValores();
        }).customButtonEffect('#btnNewParcializacionBanamex');
        // Botón : GUARDAR
        jQuery('#btnAddParcializacionBanamex').click(function () {
            parcializacionBanamexJS.prepareToSave();
        }).customButtonEffectBlue('#btnAddParcializacionBanamex');
        // </editor-fold>

       // if (jQuery('#gridVisibleAfiliacionBanamex').val() == 'true') {
            jQuery('#parcializacionBanamexGrid').jqGrid({
                url: '',
                datatype: '',
                colNames: [
                    jQuery('#afiliacion_afiliacionText').html(),
                     jQuery('#afiliacion_afiliacionText').html(),
                    jQuery('#afiliacion_claveinstitucionText').html(),
                    jQuery('#parcializacionbanamex_planText').html(),
                    jQuery('#parcializacionbanamex_claveEstatusText').html(),
                    jQuery('#parcializacionbanamex_plazoText').html(),
                    jQuery('#generico_modificar').val(),
                    jQuery('#generico_eliminar').val()],
                colModel: [{
                        name: 'parcializacionBanamexId',
                        index: 'parcializacionBanamexId',
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
                        name: 'claveInstitucionBancaria',
                        index: 'claveInstitucionBancaria',
                        align: 'center',
                        resizable: false,
                        hidden: true,
                        search: true
                    },{
                        name: 'plan',
                        index: 'plan',
                        align: 'center',
                        resizable: false,
                        hidden: false,
                        search: false
                    },{
                        name: 'claveEstatus',
                        index: 'claveEstatus',
                        align: 'center',
                        resizable: false,
                        hidden: false,
                        search: true
                    }, {
                        name: 'plazo',
                        index: 'plazo',
                        align: 'center',
                        resizable: false,
                        hidden: false,
                        search: false
                    },  {
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
                height: 230,
                toolbar: false,
                hidegrid: true,
                multiselect: true,
                viewrecords: true,
                rowList: [10, 20, 30, 50, 100],
                pager: jQuery('#parcializacionBanamexGridPagerId'),
                caption: jQuery('#key_parcializacionbanamex_title').val()
            });
            // jQuery UI Dialogs
            utilDialog.setStandardDialog('#d-removeParcializacionBanamex',jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@parcializacionBanamexJS.remove()', 'Cancelar@']);
            utilDialog.setStandardDialog('#d-removeSeleccionParcializacionBanamex',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@parcializacionBanamexJS.removeSelected()', 'Cancelar@']);
            utilDialog.setStandardDialog('#d-confirmSetEstatusParcializacionBanamex',jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@parcializacionBanamexJS.setEstatusSeleccion()', 'Cancelar@']);
            utilDialog.setStandardDialog('#d-noSelectedParcializacionBanamex', jQuery('#msgError').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
            utilDialog.setStandardDialog('#d-registroDuplicadoParcializacionBanamex', 'Advertencia', jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
            utilDialog.setCustomDialogSelectColumnsToExport("#parcializacionBanamexColumnasExportar", '#parcializacionBanamexGrid');

            // Inicializaciones extra para el grid
            utilGrid.setup('#parcializacionBanamexGrid', '#parcializacionBanamexGridPagerId', '#parcializacionBanamexGridCurrentPage', '#parcializacionBanamexGridOrderByColumn',
                    '#parcializacionBanamexGridOrderByType', 'parcializacionBanamexJS');
            
            // Crear el modal de búsqueda
            utilSearch.buildSearch('#d-searchAfiliacionBanamexPar');
            utilGrid.setHideElementsTable('parcializacionBanamexGridPagerId',['activate','export','search']);
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
       
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    inicializarValores: function () {
        utilForm.reset('#parcializacionBanamexform');
        var afiliacion =jQuery('#afiliacionbanamexform_afiliacionBanamex').val();
        jQuery('#parcializacionBanamexform_afiliacionBanamex').val(afiliacion);
        var clave =jQuery('#afiliacionbanamexform_claveInstitucionBancaria').val();
        jQuery('#parcializacionBanamexform_claveInstitucionBancaria').val(clave);
        var parcializacionBanamex = utilObject.buildObject('#parcializacionBanamexform', new ParcializacionBanamexDWR());
        parcializacionBanamexJS.parcializacionBanamex = parcializacionBanamex;
    },
    
    save: function () {
        utilEffect.showProgressBar();
        var parcializacionBanamex = utilObject.buildObject('#parcializacionBanamexform', new ParcializacionBanamexDWR());
        parcializacionBanamex.parcializacionBanamexId = 0;
        if(parcializacionBanamex.plan != null || parcializacionBanamex.claveEstatus != null || parcializacionBanamex.plazo != null){
            var listaAfiliaciones = [parcializacionBanamex];
            ParcializacionBanamexDWR.save(listaAfiliaciones, parcializacionBanamexJS.saveOrUpdateCallback);
        }
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var parcializacionBanamex = utilObject.buildObject('#parcializacionBanamexform', new ParcializacionBanamexDWR());
        parcializacionBanamex.parcializacionBanamexId = jQuery('#parcializacionBanamexform_parcializacionBanamexId').val();
        var listaAfiliaciones = [parcializacionBanamex];
        ParcializacionBanamexDWR.update(listaAfiliaciones, parcializacionBanamexJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        jQuery('#btnNewParcializacionBanamex').click();
        parcializacionBanamexJS.inicializarValores();
        parcializacionBanamexJS.reloadGrid();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());  
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = parcializacionBanamexJS.getEliminarId();
        var listaIds = [id];
        ParcializacionBanamexDWR.remove(listaIds, parcializacionBanamexJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#parcializacionBanamexGrid');
        ParcializacionBanamexDWR.remove(listaIds, parcializacionBanamexJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewParcializacionBanamex').click();
        parcializacionBanamexJS.reloadGrid();
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
        ParcializacionBanamexDWR.findById(id, parcializacionBanamexJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#parcializacionBanamexform');
        utilForm.populate('#parcializacionBanamexform', data);
        parcializacionBanamexJS.parcializacionBanamex = data;
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
         var parcializacionBanamex = utilObject.buildObject('#parcializacionBanamexform', new ParcializacionBanamexDWR());
        if( parcializacionBanamex != null ){
            var afiliacion = parcializacionBanamex.afiliacionBanamex;
            parcializacionBanamexJS.cacheDWR = obj;
            var page = jQuery('#parcializacionBanamexGridCurrentPage').val();
            var rows = jQuery('#parcializacionBanamexGridRowsByPage').val();
            var order = jQuery('#parcializacionBanamexGridOrderByColumn').val();
            var orderType = jQuery('#parcializacionBanamexGridOrderByType').val();
            utilEffect.showProgressBar();
            ParcializacionBanamexDWR.findByCriteria(page, rows, order, orderType, obj, afiliacion, parcializacionBanamexJS.findByCriteriaCallback);
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
        utilGrid.gridUpdate('#parcializacionBanamexGrid', '#parcializacionBanamexGridCurrentPage', '#parcializacionBanamexGridRowsByPage', data);
        utilGrid.autoWidthGrid();
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        ParcializacionBanamexDWR.findFirst(parcializacionBanamexJS.findByIdCallback);
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
        var nombre = jQuery('#parcializacionBanamexform_nombre').val();
        ParcializacionBanamexDWR.isValidoNombre(nombre, parcializacionBanamexJS.isValidoNombreCallback);
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
      //  if (isValido == false && jQuery('#parcializacionBanamexform_afiliacionBanamexId').val() != 0) {
            parcializacionBanamexJS.doCommit();
        /*    return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#parcializacionBanamexform_afiliacionBanamexId').val() == 0) {
            parcializacionBanamexJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#parcializacionBanamexform_afiliacionBanamexId').val() != 0) {
            parcializacionBanamexJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoParcializacionBanamex');*/
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#parcializacionBanamexGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedParcializacionBanamex');
        } else {
            utilDialog.showDialog('#d-removeSeleccionParcializacionBanamex');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        parcializacionBanamexJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeParcializacionBanamex');
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
        parcializacionBanamexJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return parcializacionBanamexJS.eliminarId;
    },
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('parcializacionBanamexform');
        if (validation.result) {
            parcializacionBanamexJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#parcializacionBanamexform_parcializacionBanamexId').val() == 0) {
            parcializacionBanamexJS.save();
        } else {
            parcializacionBanamexJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (parcializacionBanamexJS.cacheDWR != null) {
            parcializacionBanamexJS.findByCriteria(parcializacionBanamexJS.cacheDWR);
        } else {
            parcializacionBanamexJS.findByCriteria(new ParcializacionBanamexDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchAfiliacionBanamex', '#parcializacionBanamexGrid', '#parcializacionBanamexform', parcializacionBanamexJS, new ParcializacionBanamexDWR());
    },
    
    reset: function () {
        jQuery('#parcializacionBanamexGrid').clearGridData();
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
        parcializacionBanamexJS.entity = entity;
        parcializacionBanamexJS.headers = headers;
        parcializacionBanamexJS.format = format;
        parcializacionBanamexJS.reportName = jQuery('#key_afiliacionBanamex_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilDWR();
        if (parcializacionBanamexJS.cacheDWR != null) {
            criteriaExample = parcializacionBanamexJS.cacheDWR;
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
        ParcializacionBanamexDWR.getReportDataTest(sortBy, sortType, criteriaExample, parcializacionBanamexJS.exportarDatosCallback);
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
                parcializacionBanamexJS.entity + '&headers=' + parcializacionBanamexJS.headers +
                '&reportName=' + parcializacionBanamexJS.reportName + '&format=' + parcializacionBanamexJS.format +
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
// Validaciones de parcializacionBanamexform
validanguage.el.parcializacionBanamexform_afiliacionBanamex = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.parcializacionBanamexform_plazo = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.parcializacionBanamexform_claveEstatus = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.parcializacionBanamexform_plan = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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