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
    utilInput.fixRadios('#perfilform');
    // Configuraciones adicionales
    jQuery.jstree._themes = '../css/jstreethemes/';
    jQuery('.paginas').hide();

    // Iniciar la construccion del jstree
    perfilJS.obtienePaginas();

    // Limpiar al cambiar de perfil
    jQuery('#perfilform_perfilId').change(function () {
        jQuery('.accesos').jstree('uncheck_all');
        perfilJS.obtieneAccesos();
    });

    utilObject.getFuncionalidadPantalla("es","PerfilPaginaAction");

    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewPerfilPagina').click(function () {
        utilForm.reset('#perfilform');
        jQuery('#perfilform_perfilPaginaId').val(0);
        perfilJS.perfilPagina = null;
    }).customButtonEffect('#btnNewPerfilPagina');
    // Botón : GUARDAR
    jQuery('#btnSavePerfilPagina').click(function () {
        perfilJS.prepareToSave();
    }).customButtonEffectBlue('#btnSavePerfilPagina');
    // </editor-fold>

//    if (jQuery('#gridVisiblePerfil').val() == 'true') {
        jQuery('#perfilGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#perfil_perfilIdText').html(),
                jQuery('#perfil_nombreText').html(),
                'Ver Accesos'],
            colModel: [{
                    name: 'perfilId',
                    index: 'perfilId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                     width: 300,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 200,
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
            pager: jQuery('#perfilGridPagerId'),
            caption: jQuery('#key_perfil_title').val()
        });
        // jQuery UI Dialogs

        // Inicializaciones extra para el grid
        utilGrid.setup('#perfilGrid', '#perfilGridPagerId', '#perfilGridCurrentPage', '#perfilGridOrderByColumn',
                '#perfilGridOrderByType', 'perfilJS');

        utilDialog.setStandardDialog('#d-noHayAccesos',
            jQuery('#key_usuariopagina_nohayaccesostitulo').val(),
            jQuery('#key_usuariopagina_nohayaccesosmensaje').val(), ['Aceptar@']);

        utilGrid.setHideElementsTable('perfilGridPagerId',['activate', 'export', 'delete']);
        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchPerfil');

        // Recargar el widget jqGrid
        perfilJS.reloadGrid();

    $('#perfilGrid').jqGrid('setGridWidth', '600');

//    }
    // Limpiar la página
    jQuery('#btnNewPerfilPagina').click();
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
    if (jQuery('#gridIndividualModePerfilPagina').val() == 'true') {
        jQuery('#btnNewPerfilPagina').hide();
        perfilJS.findFirst();
    }
});

var perfilJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    perfilPagina: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var perfilPagina = utilObject.buildObject('#perfilform', new PerfilPaginaDWR());
        var perfilId =  perfilJS.getPerfilId();
        if (perfilId === 0) {
            utilDialog.showDialog('#d-seleccionPerfil');
            return;
        }
        var paginas = [];
        var aux = [];
        var idNumber;
        jQuery('.accesos li').each(function () {
            if (jQuery(this).hasClass('jstree-undetermined') || jQuery(this).hasClass('jstree-checked')) {
                var id = jQuery(this).attr('id');
                if(id.indexOf('_') == -1){//paginas
                    aux = [];
                    idNumber = parseInt(id.substring(3, id.length));
                    var pag = Object.assign({}, new PaginaDWR());
                    pag.paginaId = idNumber;
                    pag.funcionalidades = aux;
                    paginas.push(pag);
                }else{//funcionalidades
                    var arreglo = id.split('_');
                    aux.push(arreglo[2]);
                }
            }
            
        });

        if (paginas.length == 0) {
            utilDialog.showDialog('#d-noSeleccion');
            return;
        } else {
            utilEffect.showProgressBar();
            PerfilPaginaDWR.save(perfilId, paginas,perfilJS.saveOrUpdateCallback);
        }
    },
       /*
     * Función que Obtiene una lista de todos los registros checked
     */
    setChekedTree: function(){
         PerfilPaginaDWR.obtienePaginasFuncionalidades(perfilJS.getPerfilId(),perfilJS.setChekedTreeCallback);
    },
     /*
     * Función que Obtiene una lista de todos los registros checked
     */
    setChekedTreeCallback: function(data){
        for (var i = 0; i < data.length; i++) {
            jQuery.jstree._reference('.accesos li li').check_node('#'+data[i]);
        }
    },

    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var perfilPagina = utilObject.buildObject('#perfilform', new PerfilPaginaDWR());
        perfilPagina.perfilPaginaId = jQuery('#perfilform_perfilPaginaId').val();
        var listaPerfilPaginas = [perfilPagina];
        PerfilPaginaDWR.update(listaPerfilPaginas, perfilJS.saveOrUpdateCallback);
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
        if (jQuery('#gridIndividualModePerfilPagina').val() == 'false') {
            utilForm.reset('#perfilform');
            jQuery('#perfilform_perfilPaginaId').val(0);
            perfilJS.perfilPagina = null;
            perfilJS.reloadGrid();
        }
        utilEffect.hideProgressBar();
        jQuery('.accesos').jstree('uncheck_all');
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val())
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = perfilJS.getEliminarId();
        var listaIds = [id];
        PerfilPaginaDWR.remove(listaIds, perfilJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#perfilPaginaGrid');
        PerfilPaginaDWR.remove(listaIds, perfilJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewPerfilPagina').click();
        perfilJS.reloadGrid();
    },
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (id) {
        perfilJS.setPerfilId(id);
        utilEffect.showProgressBar();
        PerfilPaginaDWR.findById(id, perfilJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#perfilform');
        utilForm.populate('#perfilform', data);
        jQuery('#perfilform_perfilId').val(perfilJS.getPerfilId());
        perfilJS.perfilPagina = data;
        jQuery('.accesos').jstree('uncheck_all');
        perfilJS.obtieneAccesos(perfilJS.getPerfilId());
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
        perfilJS.cacheDWR = obj;
        var page = jQuery('#perfilGridCurrentPage').val();
        var rows = jQuery('#perfilGridRowsByPage').val();
        var order = jQuery('#perfilGridOrderByColumn').val();
        var orderType = jQuery('#perfilGridOrderByType').val();
        utilEffect.showProgressBar();
        PerfilPaginaDWR.findByCriteria(page, rows, order, orderType, obj, perfilJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#perfilGrid', '#perfilGridCurrentPage', '#perfilGridRowsByPage', data);
//        utilGrid.gridUpdate('#perfilPaginaGrid', '#perfilPaginaGridCurrentPage', '#perfilPaginaGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        PerfilPaginaDWR.findFirst(perfilJS.findByIdCallback);
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
        PerfilPaginaDWR.setEstatus(estatusId, listaObjetos, perfilJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = perfilJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#perfilPaginaGrid');
        PerfilPaginaDWR.setEstatus(estatusId, listaObjetos, perfilJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        perfilJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#perfilPaginaGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPerfilPagina');
        } else {
            perfilJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPerfilPagina' );
            perfilJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#perfilPaginaGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedPerfilPagina');
        } else {
            utilDialog.showDialog('#d-removeSeleccionPerfilPagina');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        perfilJS.setEliminarId(id);
        utilDialog.showDialog('#d-removePerfilPagina');
    },

    /*
     * Variable para guardar el ID de la fila que se va a eliminar, si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    eliminarId: 0,
    perfilId:0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId) {
        perfilJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return perfilJS.eliminarId;
    },
    
        /*
     * Setter de la variable eliminarId
     **/
    setPerfilId: function (perfilId) {
        perfilJS.perfilId = perfilId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getPerfilId: function () {
        return perfilJS.perfilId;
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
        var validation = validanguage.validateForm('perfilform');
        if (validation.result) {
            perfilJS.doCommit();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR 
     * Se eliminan los datos ya existentes y los almacena nuevamente
     */
    doCommit: function () {
            perfilJS.save();
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (perfilJS.cacheDWR != null) {
            perfilJS.findByCriteria(perfilJS.cacheDWR);
        } else {
            perfilJS.findByCriteria(new PerfilPaginaDWR());
        }
    },
    /*
     * Función que obtene las opciones de un elemento select perfilform_perfilId para recargarlo mediante AJAX.
     */
    getPerfil: function () {
        utilEffect.showProgressBar();
        PerfilPaginaDWR.getPerfil(perfilJS.getPerfilCallback);
    },
    /*
     * Callback de la función getPerfil() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getPerfilCallback: function (list) {
        var idCmb = jQuery('#perfilform_perfilId').val();
        jQuery('#perfilform_perfilId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.perfilId;
            var nombre = object.nombre;
            jQuery('#perfilform_perfilId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#perfilform_perfilId option[value='" + idCmb + "']").length > 0) {
                jQuery('#perfilform_perfilId').val(idCmb);
            } else {
                jQuery('#perfilform_perfilId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#perfilform_perfilId option[value='" + sel + "']").length > 0) {
                jQuery('#perfilform_perfilId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que obtene las opciones de un elemento select perfilform_paginaId para recargarlo mediante AJAX.
     */
    getPagina: function () {
        utilEffect.showProgressBar();
        PerfilPaginaDWR.getPagina(perfilJS.getPaginaCallback);
    },
    /*
     * Callback de la función getPagina() esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getPaginaCallback: function (list) {
        var idCmb = jQuery('#perfilform_paginaId').val();
        jQuery('#perfilform_paginaId').find('option').remove();
        for (var i = 0; i < list.length; i++) {
            var object = list[i];
            var id = object.paginaId;
            var nombre = object.nombre;
            jQuery('#perfilform_paginaId').append('<option value="' + id + '">' + nombre + '</option>');
        }
        // Si el usuario selecciono una opcion en el popup entonces ponerlo como seleccionado
        var sel = parseInt(jQuery('#idFromIframe').val());
        if (sel == 0) {
            // La opcion del popup seleccionada esta inactiva, validar la opcion seleccionada antes de abrir el popup
            if (jQuery("#perfilform_paginaId option[value='" + idCmb + "']").length > 0) {
                jQuery('#perfilform_paginaId').val(idCmb);
            } else {
                jQuery('#perfilform_paginaId').val(0);
            }
        } else {
            // Checar si la seleccion esta activa
            if (jQuery("#perfilform_paginaId option[value='" + sel + "']").length > 0) {
                jQuery('#perfilform_paginaId').val(sel);
            }
        }
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
         utilSearch.openSearch('#d-searchPerfil', '#perfilGrid', '#perfilform', perfilJS, new PerfilPaginaDWR());
//        utilSearch.openSearch('#d-searchPerfilPagina', '#perfilPaginaGrid', '#perfilform', perfilJS, new PerfilPaginaDWR());
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
        perfilJS.entity = entity;
        perfilJS.headers = headers;
        perfilJS.format = format;
        perfilJS.reportName = jQuery('#key_perfilpagina_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilPaginaDWR();
        if (perfilJS.cacheDWR != null) {
            criteriaExample = perfilJS.cacheDWR;
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
        PerfilPaginaDWR.getReportDataTest(sortBy, sortType, criteriaExample, perfilJS.exportarDatosCallback);
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
                perfilJS.entity + '&headers=' + perfilJS.headers +
                '&reportName=' + perfilJS.reportName + '&format=' + perfilJS.format +
                '&reportKey=' + reportKey;
    },
    /*
     * Obtiene una lista de todas las paginas a las que un determinado perfil tiene acceso.
     *
     */
    obtieneAccesos: function (id) {
//        var perfil = utilObject.buildObject('#perfilform', new PerfilDWR());
//        var perfilId = perfil.perfilId;
        var perfilId = id;
        if (perfilId == 0) {
            return;
        }
        utilEffect.showProgressBar();
        PerfilPaginaDWR.obtieneAccesos(perfilId, perfilJS.obtieneAccesosCallback);
    },
    /*
     * Callback de la función obtieneAccesos(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param paginas (List<Pagina>) es una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla PAGINA.
     */
    obtieneAccesosCallback: function (paginas) {
        utilEffect.hideProgressBar();
        if (paginas.length == 0 || paginas == null) {
             jQuery('.accesos').jstree('uncheck_all');
            utilDialog.showDialog('#d-noHayAccesos');
            return;
        } else {
            jQuery('.accesos').jstree('uncheck_all');
            for (var i = 0; i < paginas.length; i++) {
                if (jQuery('#pag' + paginas[i]).hasClass('esMenu')) {
                    // No llamar check_node en menus, ya que al hacerlo se seleccionan
                    // todos los child nodes
                    // Si no tiene hijos pero si tiene acceso al menu entonces seleccionar el menu
                    // ya que no tiene hijos y es seguro ponerlo en checked
                    var children = jQuery.jstree._reference('.accesos')._get_children('#pag' + paginas[i]);
                    if (children.length == 0) {
                        jQuery.jstree._reference('.accesos').check_node('#pag' + paginas[i]);
                    }
                }
            }
            perfilJS.setChekedTree();
        }
    },
    /*
     * Función que Obtiene una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla PAGINA.
     */
    obtienePaginas: function () {
        PerfilPaginaDWR.obtienePaginas(perfilJS.obtienePaginasCallback)
    },
    /*
     * Callback de la función obtienePaginas(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param paginas (List<Pagina>) es una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla PAGINA.
     */
    obtienePaginasCallback: function (paginas) {
        if (paginas != null) {
            var i, pagina, id;
            // Fase 1
            for (i = 0; i < paginas.length; i++) {
                pagina = paginas[i];
                var nombre = pagina.nombre;
                id = pagina.paginaCompuestaId;
                var url = pagina.anidar;
                var esMenu = '';
                if (url == '') {
                    esMenu = " class='esMenu '";
                }
                var html = '';
                html += "<li id='pag" + id + "' " + esMenu + ">";
                html += "<a href='#'>";
                html += nombre + "</a>";
                html += "</li>";
                jQuery('.paginas').append(html);
            }

          // Fase 2
            for (i = 0; i < paginas.length; i++) {
                pagina = paginas[i];
                var parentId = pagina.anidar;
                id = pagina.paginaCompuestaId;
                if (utilError.checkById('#pag' + parentId)) {
                    jQuery('#pag' + id).appendTo('#pag' + parentId);
                }
            }
            // Fase 3
            jQuery('.paginas li li').each(function () {
                var parentTag = jQuery(this).parent().is('li');
                if (parentTag) {
                    jQuery(this).parent().find('li').wrap('<ul/>');
                }
            });
             
            // Fase 4
            jQuery('.accesos').jstree({
                'html_data': {
                    'data': jQuery('.paginas').html()
                },
                "themes": {
                    "theme": "default",
                    "icons": false
                },
                'plugins': ['themes', 'html_data', 'checkbox', 'sort', 'ui']
            }).on('loaded.jstree', function () {
                jQuery('.accesos').jstree('open_all');
            });
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
// Validaciones de perfilform
validanguage.el.perfilform_perfilId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

