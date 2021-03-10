jQuery(document).ready(function () {

    // Actualizar "CatalogoPadre" al seleccionar "Organizacion" si es Dependiente en formulario catalogo
    jQuery('#catalogoform_organizacionId').change(function () {
        if (jQuery('#catalogoform_esDependiente').is(':checked')) {
            catalogoJS.filtrarCatalogoPadre();
        }
    });

    // Habilitar "CatalogoPadre" al activar "EsDependiente" en formulario catalogo
    jQuery('#catalogoform_esDependiente').change(function () {
        if (jQuery('#catalogoform_esDependiente').is(':checked')) {
            jQuery('#catalogoform_catalogoPadreId').removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
            jQuery('#catalogoform_parametroDependienteId').removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
        } else {
            jQuery('#catalogoform_catalogoPadreId').attr("disabled", "disabled").attr("readonly", "readonly");
            jQuery('#catalogoform_parametroDependienteId').attr("disabled", "disabled").attr("readonly", "readonly");
        }
    });

    // Actualizar "CatalogoPadre_Parametro" al seleccionar "CatalogoPadre" si es Dependiente en formulario catalogo
    jQuery('#catalogoform_catalogoPadreId').change(function () {
        if (jQuery('#catalogoform_esDependiente').is(':checked')) {
            catalogoJS.filtrarCatalogoPadreParametroDependiente();
        }
    });

    jQuery('#catalogoform_esDependiente').change();

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
    utilInput.fixRadios('#catalogoform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewCatalogo').click(function () {
        utilForm.reset('#catalogoform');
        jQuery('#catalogoform_catalogoPK_organizacionId').removeAttr("disabled");
        jQuery('#catalogoform_catalogoId').val(0);
        
        jQuery('#catalogoform_esDependiente').removeAttr("disabled");
        jQuery('#catalogoform_esDependiente').attr('checked', false);
        jQuery('#catalogoform_esDependiente').change();
        
        jQuery('#catalogoform_generico').removeAttr("disabled");
        jQuery('#catalogoform_catalogoPadreId').val(0);
//        jQuery('#catalogoform_catalogoPadreId').removeAttr("disabled").removeAttr("readonly");
        jQuery('#catalogoform_parametroDependienteId').val(0);
//        jQuery('#catalogoform_parametroDependienteId').removeAttr("disabled").removeAttr("readonly");
        // Limpiar el formulario y grid de catalogoParametro
        if(!catalogoParametroJS.saving && !catalogoParametroLenguajeJS.saving){
	        catalogoParametroLenguajeJS.reset();
	        catalogoParametroJS.reset();
        }
        catalogoJS.catalogo = null;
    }).customButtonEffect('#btnNewCatalogo');
    // Botón : GUARDAR
    jQuery('#btnSaveCatalogo').click(function () {
        catalogoJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveCatalogo');
    // Botón AGREGAR (formulario catalogoParametro)
    jQuery('#btnAddCatalogoParametro').click(function () {
        catalogoParametroJS.prepareToAgregar();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnAddCatalogoParametro');
    // Botón AGREGAR -Sin validación- (formulario catalogoParametro)
    jQuery('#btnAddCatalogoParametroNoValidation').click(function () {
        catalogoParametroJS.agregar();
    });
    // Botón AGREGAR (formulario catalogoParametroLenguaje)
    jQuery('#btnAddCatalogoParametroLenguaje').click(function () {
        catalogoParametroLenguajeJS.prepareToAgregar();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnAddCatalogoParametroLenguaje');
    // Botón AGREGAR -Sin validación- (formulario catalogoParametroLenguaje)
    jQuery('#btnAddCatalogoParametroLenguajeNoValidation').click(function () {
        catalogoParametroLenguajeJS.agregar();
    });
    // Botón DESPLEGAR
    jQuery('#btnDesplegar').click(function () {
        catalogoJS.getDialogOrganizacion();
    }).css({'float': 'right', 'margin-right': '4px', 'margin-top': '15px'}).customButtonEffectBlue('#btnDesplegar');
    // </editor-fold>

    if (jQuery('#gridVisibleCatalogo').val() == 'true') {
        jQuery('#catalogoGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: ['', '',
                jQuery('#catalogo_catalogoIdText').html(),
                jQuery('#catalogo_organizacionIdText').html(),
                jQuery('#catalogo_organizacionIdText').html(),
                jQuery('#catalogo_nombreText').html(),
                jQuery('#catalogo_claveText').html(),
                jQuery('#catalogo_descripcionText').html(),
                jQuery('#catalogo_estatusIdText').html(),
                jQuery('#catalogo_eliminadoIdText').html(),
                jQuery('#catalogo_creacionFechaText').html(),
                jQuery('#catalogo_creacionUsuarioText').html(),
                jQuery('#catalogo_modificacionFechaText').html(),
                jQuery('#catalogo_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'catalogoPK.catalogoId',
                    index: 'catalogoPK.catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'catalogoPK.organizacionId',
                    index: 'catalogoPK.organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'generico',
                    index: 'generico',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'organizacionId',
                    index: 'organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'organizacionNombre',
                    index: 'organizacionNombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false,
                    sortable: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'clave',
                    index: 'clave',
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
                    search: false
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
                    width: 65,
                    hidden: false,
                    search: false
                }, {
                    name: 'StateCatalogo',
                    index: 'StateCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'EditCatalogo',
                    index: 'EditCatalogo',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'RemoveCatalogo',
                    index: 'RemoveCatalogo',
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
            pager: jQuery('#catalogoGridPagerId'),
            caption: jQuery('#key_catalogo_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@catalogoJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@catalogoJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@catalogoJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-catalogo', jQuery('#msgAlertaText').val(), jQuery('#msgConfirmaEliminar').val(), ['Aceptar@catalogoJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-catalogoparametro', jQuery('#msgAlertaText').val(), jQuery('#msgConfirmaEliminar').val(), ['Aceptar@catalogoParametroJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-catalogoparametrolenguaje', jQuery('#msgAlertaText').val(), jQuery('#msgConfirmaEliminar').val(), ['Aceptar@catalogoParametroLenguajeJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedRows', jQuery('#msgAlertaText').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoCatalogo', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#catalogoColumnasExportar", '#catalogoGrid');
        utilDialog.setCustomDialogSetEstatus('#catalogoSetEstatusActivoInactivo', 'catalogoJS.prepareToSetEstatus(1)', 'catalogoJS.prepareToSetEstatus(2)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#catalogoGrid', '#catalogoGridPagerId', '#catalogoGridCurrentPage', '#catalogoGridOrderByColumn',
                '#catalogoGridOrderByType', 'catalogoJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchCatalogo');

        jQuery('.parametro_area').hide();
        jQuery('.lenguaje_area').hide();
        // Recargar el widget jqGrid
        catalogoJS.reloadGrid();
        utilGrid.setHideElementsTable('catalogoGridPagerId',['activate','export']);
        //BITÁCORA
        bitacoraJS.inicializarGrid();
    }

    // Limpiar la página
    jQuery('#btnNewCatalogo').click();
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
    if (jQuery('#gridIndividualModeCatalogo').val() == 'true') {
        jQuery('#btnNewCatalogo').hide();
        catalogoJS.findFirst();
    }
    utilObject.getFuncionalidadPantalla("","CatalogoAction");
});

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
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var catalogo = utilObject.buildObject('#catalogoform', new CatalogoDWR());
        catalogo.catalogoId = 0;
        catalogo.esDependiente=$("#catalogoform_esDependiente")[0].checked;
        catalogo.generico=$("#catalogoform_generico")[0].checked;
        if(isNaN(catalogo.catalogoPadreId)) catalogo.catalogoPadreId=0;
        if(isNaN(catalogo.parametroDependienteId)) catalogo.parametroDependienteId=0;
        var listaCatalogos = [catalogo];
        CatalogoDWR.save(listaCatalogos, catalogoJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var catalogo = utilObject.buildObject('#catalogoform', new CatalogoDWR());
        if(isNaN(catalogo.catalogoPadreId)) catalogo.catalogoPadreId=0;
        if(isNaN(catalogo.parametroDependienteId)) catalogo.parametroDependienteId=0;
        catalogo.catalogoPK.catalogoId = jQuery('#catalogoform_catalogoPK_catalogoId').val();
        catalogo.catalogoPK.organizacionId = jQuery('#catalogoform_catalogoPK_organizacionId').val();
        catalogo.esDependiente=$("#catalogoform_esDependiente")[0].checked;
        catalogo.generico=$("#catalogoform_generico")[0].checked;
        var cf = catalogo.creacionFecha;
        if(typeof cf!='undefined'){
        	if(cf instanceof Date) catalogo.creacionFecha = cf.getTime();
        	else if(typeof cf=='string'){
            	var fecha = new Date(jQuery.datepicker.parseDate(getDateFormat(cf),cf));
            	catalogo.creacionFecha = fecha.getTime();
        	}
        }
        var listaCatalogos = [catalogo];
        CatalogoDWR.update(listaCatalogos, catalogoJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedList) {
        catalogoParametroJS.save(savedList[ 0 ]);
        jQuery('#btnNewCatalogo').click();
        catalogoJS.catalogo = null;
        utilForm.reset('#catalogoform');
        catalogoJS.reloadGrid();
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = catalogoJS.getEliminarId();
        var organizacionId = catalogoJS.getEliminarOrganizacionId();
        var listaCatalogos = [{catalogoId: id, organizacionId: organizacionId}];
        CatalogoDWR.remove(listaCatalogos, catalogoJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaCatalogos = utilGrid.gridGetSelectedRows('#catalogoGrid');
        CatalogoDWR.remove(listaCatalogos, catalogoJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewCatalogo').click();
        catalogoJS.reloadGrid();
    },
    /*
     * Función que realiza una búsqueda de un objeto por su ID en la BD.
     * Usualmente es llamado cuando el usuario presiona el icono <b>Editar</b>
     * de una fila en el widget jqGrid.
     * 
     * @param id (Integer) Es el id del objeto que se quiere obtener.
     */
    findById: function (catalogoId, organizacionId) {
        utilEffect.showProgressBar();
        CatalogoDWR.findById({catalogoId: catalogoId, organizacionId: organizacionId}, catalogoJS.findByIdCallback);
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
        $("#catalogoform_esDependiente")[0].checked=data.esDependiente;
        $("#catalogoform_generico")[0].checked=data.generico;
        jQuery('#catalogoform_catalogoPK_organizacionId').attr("disabled", "disabled");
        jQuery('#catalogoform_esDependiente').attr("disabled", "disabled");
        jQuery('#catalogoform_generico').attr("disabled", "disabled");
        jQuery('#catalogoform_catalogoPadreId').attr("disabled", "disabled").attr("readonly", "readonly");
        jQuery('#catalogoform_parametroDependienteId').attr("disabled", "disabled").attr("readonly", "readonly");
        jQuery('.parametro_area').show();
        $("#catalogoform_parametroDependienteId").empty();
        catalogoJS.filtrarCatalogoPadreParametroDependiente();
        catalogoParametroJS.findCatalogoParametroBy(jQuery('#catalogoform_catalogoPK_catalogoId').val(), jQuery('#catalogoform_catalogoPK_organizacionId').val());
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
        catalogoJS.cacheDWR = obj;
        var page = jQuery('#catalogoGridCurrentPage').val();
        var rows = jQuery('#catalogoGridRowsByPage').val();
        var order = jQuery('#catalogoGridOrderByColumn').val();
        var orderType = jQuery('#catalogoGridOrderByType').val();
        utilEffect.showProgressBar();
        CatalogoDWR.findByCriteria(page, rows, order, orderType, obj, catalogoJS.findByCriteriaCallback);
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
        // Limpiar el formulario y grid de catalogo, catalogoParametro, catalogoParametroLenguaje
        jQuery('#btnNewCatalogo').click();
        if(!catalogoParametroJS.saving){
        	utilEffect.hideProgressBar();
        	jQuery('.parametro_area').hide();
        	jQuery('.lenguaje_area').hide();
        }
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.findFirst(catalogoJS.findByIdCallback);
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
        var nombre = jQuery('#catalogoform_nombre').val();
        CatalogoDWR.isValidoNombre(nombre, catalogoJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#catalogoform_catalogoPK_catalogoId').val() != 0) {
            catalogoJS.doCommit();
            return;
        }
        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#catalogoform_catalogoPK_catalogoId').val() == 0) {
            catalogoJS.doCommit();
            return;
        }
        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#catalogoform_catalogoPK_catalogoId').val() != 0) {
            catalogoJS.doCommit();
            return;
        }
        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoCatalogo');
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, organizacionId, estatusId) {
        utilEffect.showProgressBar();
        var listaCatalogos = [{catalogoId: id, organizacionId: organizacionId}];
        CatalogoDWR.setEstatus(estatusId, listaCatalogos, catalogoJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = catalogoJS.estatusId;
        var listaCatalogos = utilGrid.gridGetSelectedRows('#catalogoGrid');
        CatalogoDWR.setEstatus(estatusId, listaCatalogos, catalogoJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        catalogoJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogo');
        } else {
            catalogoJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusCatalogo' );
            catalogoJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogo');
        } else {
            utilDialog.showDialog('#d-removeSeleccionCatalogo');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id, organizacionId) {
        catalogoJS.setEliminarId(id, organizacionId);
        utilDialog.showDialog('#d-removeCatalogo');
    },
    /*
     * Variable para guardar el ID de la fila que se va a eliminar, si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    eliminarId: 0,
    eliminarOrganizacionId: 0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId, organizacionId) {
        catalogoJS.eliminarId = eliminarId;
        catalogoJS.eliminarOrganizacionId = organizacionId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return catalogoJS.eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarOrganizacionId: function () {
        return catalogoJS.eliminarOrganizacionId;
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
        var validation = validanguage.validateForm('catalogoform');
        if (validation.result) {
            catalogoJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#catalogoform_catalogoPK_catalogoId').val() == 0) {
            catalogoJS.save();
        } else {
            catalogoJS.update();
        }
    },
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
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchCatalogo', '#catalogoGrid', '#catalogoform', catalogoJS, new CatalogoDWR());
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
            text: jQuery('#msgFiltrandoInformacion').val(),
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
    },
    filtrarCatalogoPadre: function () {
        utilEffect.showProgressBar();
        CatalogoDWR.filtrarCatalogo(jQuery('#catalogoform_catalogoPK_organizacionId').val(), catalogoJS.filtrarCatalogoPadreCallback);
    },
    filtrarCatalogoPadreCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#catalogoform_catalogoPadreId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#catalogoform_catalogoPadreId', data, 'catalogoPadreId', 'nombre');
            if (catalogoJS.catalogo !== null) {
                if (catalogoJS.catalogo.catalogoPadreId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#catalogoform_catalogoPadreId', catalogoJS.catalogo.catalogoPadreId)) {
                        jQuery('#catalogoform_catalogoPadreId').val(catalogoJS.catalogo.catalogoPadreId);
                    } else {
                        jQuery('#catalogoform_catalogoPadreId').val(0);
                    }
                }
            }
            jQuery('#catalogoform_catalogoPadreId').trigger('change');
        }
        utilEffect.hideProgressBar();
    },
    filtrarCatalogoPadreParametroDependiente: function () {
    	var catalogoPadreId=jQuery('#catalogoform_catalogoPadreId').val();
    	if(catalogoPadreId!=null && catalogoPadreId!='' && catalogoPadreId!='-1'){
	        utilEffect.showProgressBar();
	        CatalogoDWR.filtrarCatalogoParametro(jQuery('#catalogoform_catalogoPadreId').val(), catalogoJS.filtrarCatalogoPadreParametroDependienteCallback);
    	}
    },
    filtrarCatalogoPadreParametroDependienteCallback: function (data) {
        if (data != null && data.length > 0) {
        	var options = $("#catalogoform_parametroDependienteId");
        	options.empty();
        	options.append($("<option />").val("").text("--Seleccione--"));
        	$.each(data, function() {
        	    options.append($("<option />").val(this.catalogoParametroPK.catalogoParametroId).text(this.clave));
        	});
            if (catalogoJS.catalogo !== null) {
                if (catalogoJS.catalogo.parametroDependienteId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#catalogoform_parametroDependienteId', catalogoJS.catalogo.parametroDependienteId)) {
                        jQuery('#catalogoform_parametroDependienteId').val(catalogoJS.catalogo.parametroDependienteId);
                    } else {
                        jQuery('#catalogoform_parametroDependienteId').val(0);
                    }
                }
            }
            jQuery('#catalogoform_parametroDependienteId').trigger('change');
        }
        utilEffect.hideProgressBar();
    },
    
    //FUNCIONES PARA DESPLEGAR
    getDialogOrganizacion: function (){
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid');
        if(seleccion.length > 0){
           SeguridadPerfilDWR.getOrganizaciones(catalogoJS.getDialogOrganizacionCallBack);
        }else{
            utilDialog.showDialog('#d-noSelectedRows');
        }
    },
    getDialogOrganizacionCallBack: function (data){
       utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'catalogoJS.desplegar','nombre');
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
       var seleccion = utilGrid.gridGetSelectedRows('#catalogoGrid');
       var idEjecucion = new Date().getTime();
       var listElementos = seleccion.join();
       utilEffect.showProgressBar();
       CatalogoDWR.desplegar(page, rows, order, orderType, data, listElementos, idEjecucion, catalogoJS.desplegarCallback);
    },
    desplegarCallback: function (data){
        var grid = data.grid;
        var id = grid[ 0 ][ "estatusId" ];
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
// Validaciones de catalogoform
validanguage.el.catalogoform_catalogoPK_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_clave = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_catalogoPadreId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                if (jQuery("#catalogoform_esDependiente").is(":checked") && Number(jQuery("#catalogoform_catalogoPadreId").val()) <= 0) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Seleccione un registro.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.catalogoform_parametroDependienteId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                if (jQuery("#catalogoform_esDependiente").is(":checked") && Number(jQuery("#catalogoform_catalogoPadreId").val()) <= 0 && Number(jQuery("#catalogoform_parametroDependienteId").val()) <= 0) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Seleccione un registro.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.catalogoform_seleccionaColor = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 200) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 200 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
// Validaciones de catalogoParametroform
validanguage.el.catalogoParametroform_catalogoParametroPK_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_catalogoId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_clave = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_valor = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_orden = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 400) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 400 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
// Validaciones de catalogoParametroLenguajeform
validanguage.el.catalogoParametroLenguajeform_catalogoParametroLenguajePK_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroLenguajeform_catalogoParametroId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.catalogoParametroLenguajeform_lenguajeId = {
	characters: {
        mode: 'allow', expression: 'alphanumericspecial', suppress: false},
    validations: [ProfileRequiredComboField,ProfileNotRepeatedLanguaje]};
validanguage.el.catalogoParametroLenguajeform_etiqueta = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, ProfileRequiredField]};

