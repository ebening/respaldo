jQuery(document).ready(function () {
    // Se filtran los roles de acuerdo a las aplicaciones
    jQuery('#seguridadPerfilform_aplicacionId').change(function () {
        jQuery('#seguridadPerfilRolGrid').clearGridData();
        seguridadPerfilJS.filtrarRoles();
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
    utilInput.fixRadios('#seguridadPerfilform');
    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewPerfil').click(function () {
        seguridadPerfilJS.clean();
    }).customButtonEffect('#btnNewPerfil');
    // Botón : GUARDAR
    jQuery('#btnSavePerfil').click(function () {
        seguridadPerfilJS.prepareToSave();
    }).customButtonEffectBlue('#btnSavePerfil');
     // Botón NUEVO PERFIL  
    jQuery('#btnNewRol').click(function () {
        seguridadPerfilRolJS.clean();
    }).customButtonEffectBlue('#btnNewRol');
    // Botón AGREGAR 
    jQuery('#btnAddRol').click(function () {
        seguridadPerfilRolJS.prepareToAgregar();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnAddRol');
     // Botón DESPLEGAR
    jQuery('#btnDesplegar').click(function () {
        seguridadPerfilJS.getDialogOrganizacion();
    }).css({'float': 'right', 'margin-right': '4px'}).customButtonEffectBlue('#btnDesplegar');
        
    // </editor-fold>
    // 
    //Funcion para mostrar/ocultar permisos por Pantalla
    utilObject.getFuncionalidadPantalla("es","SeguridadPerfilAction");
    
    if (jQuery('#gridVisibleSeguridadPerfil').val() == 'true') {
        jQuery('#seguridadPerfilGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#seguridadPerfil_perfilIdText').val(),
                jQuery('#seguridadPerfil_aplicacionText').html(),
                jQuery('#seguridadPerfil_nombreText').html(),
                jQuery('#seguridadPerfil_descripcionText').html(),
                jQuery('#seguridadperfil_modificacionFechaText').val(),
                jQuery('#seguridadperfil_modificacionUsuarioText').val(),
                jQuery('#generico_activo').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'seguridadPerfilId',
                    index: 'seguridadPerfilId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'aplicacionId',
                    index: 'aplicacionId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                },{
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                },{
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
                    width: 110,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    width: 120,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'State',
                    index: 'State',
                    align: 'center',
                    width: 40,
                    sortable: false,
                    search: false,
                    hidden: false,
                    resizable: false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 60,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 50,
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
            pager: jQuery('#seguridadPerfilGridPagerId'),
            caption: jQuery('#key_seguridadperfil_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeSeguridadPerfil',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarText').val(), ['Aceptar@seguridadPerfilJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionSeguridadPerfil',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@seguridadPerfilJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusSeguridadPerfil', jQuery('#msgAlertaText').val(),jQuery('#msgCambiarEstatusText').val(), ['Aceptar@seguridadPerfilJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedSeguridadPerfil', jQuery('#msgError').val(),jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-noSelectedRows', jQuery('#msgAlertaText').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoSeguridadPerfil', 'Advertencia',jQuery('#msgYaExisteRegistro').val(), ['Aceptar@', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#seguridadPerfilColumnasExportar", '#seguridadPerfilGrid');
        utilDialog.setCustomDialogSetEstatus('#seguridadPerfilSetEstatusActivoInactivo',
                'seguridadPerfilJS.prepareToSetEstatus(1)', 'seguridadPerfilJS.prepareToSetEstatus(0)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#seguridadPerfilGrid', '#seguridadPerfilGridPagerId', '#seguridadPerfilGridCurrentPage', '#seguridadPerfilGridOrderByColumn',
                '#seguridadPerfilGridOrderByType', 'seguridadPerfilJS');

        utilGrid.setHideElementsTable('seguridadPerfilGridPagerId',['activate','export']);        
        // Recargar el widget jqGrid
        seguridadPerfilJS.reloadGrid();

    }
    
    if (jQuery('#gridVisibleSeguridadPerfilRol').val() == 'true') {
        jQuery('#seguridadPerfilRolGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#seguridadPerfil_rolText').html(),
                'seguridadPerfilRolId',
                jQuery('#seguridadPerfil_rolText').html(),
                jQuery('#seguridadPerfil_creacionfechaText').val(),
                jQuery('#seguridadperfil_modificacionFechaText').val(),
                jQuery('#seguridadperfil_modificacionUsuarioText').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()
            ],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                },{
                    name: 'seguridadPerfilRolId',
                    index: 'seguridadPerfilRolId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'rolId',
                    index: 'rolId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                },{
                    name: 'creacionFecha',
                    index: 'creacionFecha',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'modificacionFecha',
                    index: 'modificacionFecha',
                    align: 'center',
                    width: 110,
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    width: 120,
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'creacionUsuario',
                    index: 'creacionUsuario',
                    align: 'center',
                    width: 120,
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 60,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'Remove',
                    index: 'Remove',
                    align: 'center',
                    width: 50,
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
            pager: jQuery('#seguridadPerfilRolGridPagerId'),
            caption: jQuery('#key_rol_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeSeguridadPerfilRol',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarText').val(), ['Aceptar@seguridadPerfilRolJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionSeguridadPerfilRol',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@seguridadPerfilRolJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusSeguridadPerfilRol',jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@seguridadPerfilRolJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedSeguridadPerfilRol',jQuery('#msgError').val(),jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoSeguridadPerfilRol', 'Advertencia',jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#seguridadPerfilRolColumnasExportar", '#seguridadPerfilRolGrid');
        utilDialog.setCustomDialogSetEstatus('#seguridadPerfilRolSetEstatusActivoInactivo',
                'seguridadPerfilRolJS.prepareToSetEstatus(1)', 'seguridadPerfilRolJS.prepareToSetEstatus(0)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#seguridadPerfilRolGrid', '#seguridadPerfilRolGridPagerId', '#seguridadPerfilRolGridCurrentPage', '#seguridadPerfilRolGridOrderByColumn',
                '#seguridadPerfilRolGridOrderByType', 'seguridadPerfilRolJS');

       utilGrid.setHideElementsTable('seguridadPerfilRolGridPagerId',['activate','export','search']);

    }
    // Grid configuracionParametro embebido
    parametroJS.inicializarGrid();
    bitacoraJS.inicializarGrid();
    // Limpiar la página
    jQuery('#btnNewPerfil').click();
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
    if (jQuery('#gridIndividualModeSeguridadPerfil').val() == 'true') {
        jQuery('#btnNewPerfil').hide();
        seguridadPerfilJS.findFirst();
    }
    
    seguridadPerfilRolJS.getUsuario();
    parametroJS.setTableName(jQuery('#key_tableName').val());
    parametroJS.setPropertyName(jQuery('#key_propertyName').val());
    parametroJS.LENGUAJE_DEFAULT = $('#seguridadPerfilform_lenguajeId').val();

});
// Objeto auxiliar para la funcionalidad de desglose.
var desgloseSeguridadPerfilRolJS = {
    desglose: function (selectIdList) {
        desgloseSeguridadPerfilRolJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#seguridadPerfilRolform");
    }
};

var seguridadPerfilJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    seguridadPerfil: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    
    clean: function(){
        utilForm.reset('#seguridadPerfilform');
        jQuery('#seguridadPerfilform_seguridadPerfilId').val(0);
        seguridadPerfilRolJS.reset();
        seguridadPerfilJS.seguridadPerfil = null; 
        jQuery('#parametroDiv').hide();
        jQuery('#perfilRolDiv').hide();
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var seguridadPerfil = utilObject.buildObject('#seguridadPerfilform', new SeguridadPerfilDWR());
        seguridadPerfil.seguridadPerfilId = 0;
        var listaPerfiles = [seguridadPerfil];
        SeguridadPerfilDWR.save(listaPerfiles, seguridadPerfilJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var seguridadPerfil = utilObject.buildObject('#seguridadPerfilform', new SeguridadPerfilDWR());
        listaPerfiles = [seguridadPerfil];
        SeguridadPerfilDWR.update(listaPerfiles, seguridadPerfilJS.saveOrUpdateCallback);
        
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
        if (jQuery('#gridIndividualModeSeguridadPerfil').val() == 'false') {
            seguridadPerfilRolJS.save(savedIds[ 0 ]);
        }else{
          jQuery('#parametroDiv').hide();
          jQuery('#perfilRolDiv').hide();
          utilEffect.hideProgressBar();
          utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());  
        }
        
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id =seguridadPerfilJS.getEliminarId();
        var listaIds = [id];
        SeguridadPerfilDWR.remove(listaIds, seguridadPerfilJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#seguridadPerfilGrid');
        SeguridadPerfilDWR.remove(listaIds, seguridadPerfilJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewPerfil').click();
        seguridadPerfilJS.reloadGrid();
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
        SeguridadPerfilDWR.findById(id, seguridadPerfilJS.findByIdCallback);
        utilEffect.hideProgressBar();
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#seguridadPerfilform');
        utilForm.populate('#seguridadPerfilform', data);
        jQuery('#seguridadPerfilRolGrid').clearGridData();
        seguridadPerfilJS.seguridadPerfil = data;
        seguridadPerfilJS.getParametros(data);
    },
    getParametros: function (data) {
        jQuery('#parametroDiv').show();
        utilForm.reset('#parametroform');
        jQuery('#parametroform_fkId').val(data.seguridadPerfilId);
        jQuery('#parametroform_lenguajeId').val(jQuery('#seguridadPerfilform_lenguajeId').val());
        parametroJS.setFkId(data.seguridadPerfilId);
        parametroJS.findByCriteria(data, seguridadPerfilJS.getPerfilRol());
    },
    getPerfilRol: function () {
        jQuery('#perfilRolDiv').show();
        seguridadPerfilRolJS.reloadGrid();
        //seguridadPerfilRolJS.findSeguridadPerfilRolByIntProperty(
        //        jQuery('#seguridadPerfilform_seguridadPerfilId').val(),seguridadPerfilJS.filtrarRoles());
        utilGrid.autoWidthGrid();
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
        seguridadPerfilJS.cacheDWR = obj;
        var page = jQuery('#seguridadPerfilGridCurrentPage').val();
        var rows = jQuery('#seguridadPerfilGridRowsByPage').val();
        var order = jQuery('#seguridadPerfilGridOrderByColumn').val();
        var orderType = jQuery('#seguridadPerfilGridOrderByType').val();
        utilEffect.showProgressBar();
        SeguridadPerfilDWR.findByCriteria(page, rows, order, orderType, obj, seguridadPerfilJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#seguridadPerfilGrid', '#seguridadPerfilGridCurrentPage', '#seguridadPerfilGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        SeguridadPerfilDWR.findFirst(seguridadPerfilJS.findByIdCallback);
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
        var nombre = jQuery('#seguridadPerfilform_nombre').val();
        SeguridadPerfilDWR.isValidoNombre(nombre, seguridadPerfilJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#seguridadPerfilform_seguridadPerfilId').val() != 0) {
            var anterior = seguridadPerfilJS.seguridadPerfil;
            var nuevo= utilObject.buildObject('#seguridadPerfilform', new SeguridadPerfilDWR());
            if(anterior.nombre === nuevo.nombre){ 
                seguridadPerfilJS.doCommit();
                return;
            }else{
                utilDialog.showDialog('#d-registroDuplicadoSeguridadPerfil');
            }
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#seguridadPerfilform_seguridadPerfilId').val() == 0) {
            seguridadPerfilJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#seguridadPerfilform_seguridadPerfilId').val() != 0) {
            seguridadPerfilJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoSeguridadPerfil');
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
        SeguridadPerfilDWR.setEstatus(estatusId, listaObjetos, seguridadPerfilJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = seguridadPerfilJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#seguridadPerfilGrid');
        SeguridadPerfilDWR.setEstatus(estatusId, listaObjetos, seguridadPerfilJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        seguridadPerfilJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#seguridadPerfilGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedSeguridadPerfil');
        } else {
            seguridadPerfilJS.estatusId = estatusId;
            seguridadPerfilJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#seguridadPerfilGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedSeguridadPerfil');
        } else {
            utilDialog.showDialog('#d-removeSeleccionSeguridadPerfil');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        seguridadPerfilJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeSeguridadPerfil');
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
        seguridadPerfilJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return seguridadPerfilJS.eliminarId;
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
        var validation = validanguage.validateForm('seguridadPerfilform');
        if (validation.result){
            seguridadPerfilJS.exists();
        }
    },
       /*
     * Función que valida si el registro ya existe entre los elementos eliminados
     * ya que si existe solo se le cambiará el estatus a no eliminado
     */
    exists: function () {
        var perfil = utilObject.buildObject('#seguridadPerfilform', new SeguridadPerfilDWR());
        SeguridadPerfilDWR.exists(perfil,seguridadPerfilJS.existsCallback);
    },
    
    existsCallback: function (data) {
        if(data==false){
            seguridadPerfilJS.isValidoNombre();
        }else{
            utilForm.reset('#seguridadPerfilform');
            jQuery('#seguridadPerfilform_seguridadPerfilId').val(0);
            seguridadPerfilJS.seguridadPerfil = null;
            seguridadPerfilJS.reloadGrid();
            utilEffect.hideProgressBar();
            utilEffect.showToast('success', jQuery('#msgGuardadoOk').val()); 
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#seguridadPerfilform_seguridadPerfilId').val() == 0) {
            seguridadPerfilJS.save();
        } else {
            seguridadPerfilJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (seguridadPerfilJS.cacheDWR != null) {
            seguridadPerfilJS.findByCriteria(seguridadPerfilJS.cacheDWR);
        } else {
            seguridadPerfilJS.findByCriteria(new SeguridadPerfilDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.buildSearch('#d-searchSeguridadPerfil');
        utilSearch.openSearch('#d-searchSeguridadPerfil', '#seguridadPerfilGrid', '#seguridadPerfilform', seguridadPerfilJS, new SeguridadPerfilDWR());
    },
    
    filtrarRoles: function () {
        SeguridadPerfilDWR.getComboRolesByAplicacion(
                jQuery('#seguridadPerfilform_aplicacionId').val(),
                seguridadPerfilJS.filtrarRolesCallback);
    },
    /** 
     * Callback de la función filtrarSubclasificacionContra(), esta
     * función es llamada por DWR al terminar de realizar la consulta.
     */
    filtrarRolesCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#seguridadPerfilRolform_rolId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#seguridadPerfilRolform_rolId', data, 'rolId', 'nombre');
            if (seguridadPerfilRolJS.seguridadPerfilRol !== null) {
                if (seguridadPerfilRolJS.seguridadPerfilRol.rolId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#seguridadPerfilRolform_rolId', seguridadPerfilRolJS.seguridadPerfilRol.rolId)) {
                        jQuery('#seguridadPerfilRolform_rolId').val(seguridadPerfilRolJS.seguridadPerfilRol.rolId);
                    } else {
                        jQuery('#seguridadPerfilRolform_rolId').val(0);
                    }
                }
            }
            jQuery('#seguridadPerfilRolform_rolId').trigger('change');
        }
        utilEffect.hideProgressBar();
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
        seguridadPerfilJS.entity = entity;
        seguridadPerfilJS.headers = headers;
        seguridadPerfilJS.format = format;
        seguridadPerfilJS.reportName = jQuery('#key_seguridadPerfil_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new SeguridadPerfilDWR();
        if (seguridadPerfilJS.cacheDWR != null) {
            criteriaExample = seguridadPerfilJS.cacheDWR;
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
        SeguridadPerfilDWR.getReportDataTest(sortBy, sortType, criteriaExample, seguridadPerfilJS.exportarDatosCallback);
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
                seguridadPerfilJS.entity + '&headers=' + seguridadPerfilJS.headers +
                '&reportName=' + seguridadPerfilJS.reportName + '&format=' + seguridadPerfilJS.format +
                '&reportKey=' + reportKey;
    },
    getDialogOrganizacion: function (){
        var seleccion = utilGrid.gridGetSelectedRows('#seguridadPerfilGrid');
        if(seleccion.length > 0){
            SeguridadPerfilDWR.getOrganizaciones(seguridadPerfilJS.getDialogOrganizacionCallBack);
        }else{
            utilDialog.showDialog('#d-noSelectedRows');
        }
    },
    getDialogOrganizacionCallBack: function (data){
       utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'seguridadPerfilJS.desplegar','nombre');
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
       var seleccion = utilGrid.gridGetSelectedRows('#seguridadPerfilGrid');
       var idEjecucion = new Date().getTime();
       var listElementos = seleccion.join();
       utilEffect.showProgressBar();
       //SeguridadPerfilDWR.desplegarSeguridadPerfil(seguridadPerfilJS.desplegarCallback); 
       SeguridadPerfilDWR.desplegar(page, rows, order, orderType, data, listElementos, idEjecucion, seguridadPerfilJS.desplegarCallback);
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


var seguridadPerfilRolJS = {
    isDesglose: false,
    filaId: null,
    seguridadPerfilRol: null,
    usuario:null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#seguridadPerfilRolform');
        jQuery('#seguridadPerfilRolform_filaId').val(0);
        jQuery('#seguridadPerfilRolform_seguridadPerfilRolId').val(0);
        seguridadPerfilRolJS.seguridadPerfilRol = null;
        seguridadPerfilRolJS.isDesglose = false;
        jQuery('form[name="seguridadPerfilRolform"]').addClass("base-desglose");
    },
    /**
     * Limpia el formulario y el grid de seguridadPerfilRol, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        seguridadPerfilRolJS.clean();
        jQuery('#seguridadPerfilRolGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de seguridadPerfilRol antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('seguridadPerfilform')
        var validationRol = validanguage.validateForm('seguridadPerfilRolform');
        if (validation.result && validationRol.result){
            seguridadPerfilRolJS.isExistPerfilRol();
        }else{
           utilEffect.showToast('error','Selecciona el Perfil o Rol a Modificar'); 
           seguridadPerfilRolJS.clean();
        }
         
    },
     /**
     * Obtiene el usuario que esta modificando el Registro.
     */ 
    getUsuario:  function () {
        SeguridadPerfilDWR.getUsuario(seguridadPerfilRolJS.getUsarioCallBack); 
    },
    getUsarioCallBack:function (data){
        seguridadPerfilRolJS.usuario=data;
    },
     /**
     * Valida si existe el el Rol en el Perfil de acuerdo al grid
     */ 
    isExistPerfilRol: function(){
        var rolId = jQuery("#seguridadPerfilRolform_rolId ").val();
        var perfilId = jQuery("#seguridadPerfilform_seguridadPerfilId ").val();
        SeguridadPerfilRolDWR.isValidoSeguridadPerfilRol(perfilId,rolId,seguridadPerfilRolJS.validandoPerfilRol);
    
    },
    validandoPerfilRol: function(data){
        if(!data){
             var seguridadPerfilRol = utilObject.buildObject(
                '#seguridadPerfilRolform', new SeguridadPerfilRolDWR());
            seguridadPerfilRolJS.agregar(seguridadPerfilRol);
        }else{
            seguridadPerfilRolJS.clean();
            utilDialog.showDialog('#d-registroDuplicadoSeguridadPerfilRol');
        }
    },

    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * seguridadPerfilRol.
     */
    agregar: function (seguridadPerfilRol) {
        // Formar el objeto con los datos del formulario
        var seguridadPerfilRol = seguridadPerfilRol;
        // Validar si se esta agregando uno nuevo o uno editado
        if (jQuery('#seguridadPerfilRolform_filaId').val() == 0) {
            seguridadPerfilRol.eliminadoId=0;
            seguridadPerfilRolJS.agregarNuevo(seguridadPerfilRol);
        } else {
            seguridadPerfilRolJS.agregarEditado(seguridadPerfilRol);
        }
    },
    /* Agrega un registro editado al grid de seguridadPerfilRol */
    agregarEditado: function (object) {
        object.filaId = jQuery('#seguridadPerfilRolform_filaId').val();
        jQuery('#seguridadPerfilRolGrid').delRowData(object.filaId);
        seguridadPerfilRolJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de seguridadPerfilRol, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto SeguridadPerfilRol con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        var filaId = seguridadPerfilRolJS.findFilaIdMax() + 1;
        object=utilGrid.setFormatEditFila(filaId,object,'seguridadPerfilRol');
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            object[ propiedad ] = utilGrid.setFormatFechaFila(object[ propiedad ],propiedad);
            if ( propiedad  == 'seguridadPerfilRolId' ){
                 object.rolId=jQuery("#seguridadPerfilRolform_rolId option:selected").html();
                 object.modificacionFecha=jQuery.datepicker.formatDate(
                                 'dd/mm/yy', new Date()
                                 );
                 object.modificacionUsuario = seguridadPerfilRolJS.usuario;
             }
        }
        // Enviar los datos al grid
        jQuery('#seguridadPerfilRolGrid').addRowData(filaId, object);
        seguridadPerfilRolJS.clean();
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#seguridadPerfilRolGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * seguridadPerfilRol. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de seguridadPerfilRol*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#seguridadPerfilRolGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            /*.filter(function () { return $(this).html() == "B"; }).val();*/
            if (filas[ i ].filaId == filaId) {
                seguridadPerfilRolJS.seguridadPerfilRol = filas[i];
                utilForm.populate('#seguridadPerfilRolform', filas[ i ]);
                seguridadPerfilRolJS.isDesglose = true;
                var valor=jQuery("#seguridadPerfilRolform_rolId option").filter(function () { return $(this).html() == filas[ i ].rolId; }).val();
                jQuery('form[name="seguridadPerfilRolform"]').removeClass("base-desglose");
                jQuery("#seguridadPerfilRolform_rolId").val(valor);
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * seguridadPerfilRol.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        seguridadPerfilRolJS.filaId = filaId;
        utilDialog.showDialog('#d-removeSeguridadPerfilRol');
    },
    /** 
     * Función que elimina una fila del grid catalogoparametrolenguaje, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * catalogoparametrolenguajeJS.filaId.
     */
    remove: function () {
        jQuery('#seguridadPerfilRolGrid').delRowData(seguridadPerfilRolJS.filaId);
        seguridadPerfilRolJS.clean();
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param catalogoId, es el id de catalogo.
     */
    save: function (seguridadPerfilId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (seguridadPerfilRolJS.isDesglose) {
            jQuery('form[name="seguridadPerfilRolform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new SeguridadPerfilRolDWR());
                    obj.perfilId = seguridadPerfilId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId(
                    '#seguridadPerfilRolGrid', new SeguridadPerfilRolDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].perfilId = seguridadPerfilId;
                dwrObjectList[ i ].modificacionUsuario = undefined;
                var rolId = jQuery("#seguridadPerfilRolform_rolId option").filter(function () { return $(this).html() == dwrObjectList[ i ].rolId; }).val();
                dwrObjectList[ i ].rolId = parseInt(rolId);
                dwrObjectList[ i ].aplicacionId = jQuery('#seguridadPerfilform_aplicacionId').val();
                var fechaMod = dwrObjectList[ i ].modificacionFecha;
                if (fechaMod !== "") {
                   dwrObjectList[ i ].modificacionFecha =  new Date(funciones.parseDate(fechaMod));
                }
                var fechaCrea = dwrObjectList[ i ].creacionFecha;
                if (fechaCrea !== "" && typeof fechaCrea!== "undefined") {
                    dwrObjectList[ i ].creacionFecha =  new Date(funciones.parseDate(fechaCrea));
                }
            }
        }
        
       SeguridadPerfilDWR.saveSeguridadPerfilRol(seguridadPerfilId,dwrObjectList,seguridadPerfilRolJS.saveSeguridadPerfilRolCallBack);
       utilEffect.hideProgressBar();
    },
    saveSeguridadPerfilRolCallBack: function(){
        utilForm.reset('#seguridadPerfilform');
        jQuery('#seguridadPerfilform_seguridadPerfilId').val(0);
        seguridadPerfilJS.seguridadPerfil = null;
        seguridadPerfilJS.reloadGrid();
        jQuery('#seguridadPerfilRolGrid').clearGridData();
        jQuery('#btnNewConfiguracion').click();
        jQuery('#parametroDiv').hide();
        jQuery('#perfilRolDiv').hide();
        utilEffect.hideProgressBar();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
    },
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#seguridadPerfilRolGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedSeguridadPerfilRol');
        } else {
            utilDialog.showDialog('#d-removeSeleccionSeguridadPerfilRol');
        }
    },
    removeSelected: function () {
        utilEffect.showProgressBar();
        var selectedRows = jQuery( '#seguridadPerfilRolGrid' ).jqGrid( 'getGridParam', 'selarrrow' );
        for (var i = 0 ; i < selectedRows.length ; i++ ) {
            var registro = jQuery( '#seguridadPerfilRolGrid' ).getRowData( selectedRows[ i ] );
            jQuery('#seguridadPerfilRolGrid').delRowData(parseInt(registro.filaId));
        }
        seguridadPerfilRolJS.clean();
        utilEffect.hideProgressBar();
        seguridadPerfilRolJS.reloadGrid();
    },
    /* Función que obtiene seguridadPerfilRol sin paginado */
    findSeguridadPerfilRolByIntProperty: function (perfilId) {
        SeguridadPerfilDWR.findSeguridadPerfilRolByIntProperty("perfilId", perfilId,
                seguridadPerfilRolJS.findSeguridadPerfilRolByIntPropertyCallback);
    },
    findSeguridadPerfilRolByIntPropertyCallback: function (data) {
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                 seguridadPerfilRolJS.agregarNuevo(data[ i ]);
            }
        }
        seguridadPerfilJS.filtrarRoles();
        utilEffect.hideProgressBar();
    },
    
    openSearch: function () {
        utilSearch.buildSearch('#d-searchSeguridadPerfilRol');
        utilSearch.openSearch('#d-searchSeguridadPerfilRol', '#seguridadPerfilRolGrid', '#seguridadPerfilRolform', seguridadPerfilRolJS, new SeguridadPerfilRolDWR());
    },
    
    findByCriteria: function (obj) {
        seguridadPerfilRolJS.cacheDWR = obj;
        var page = jQuery('#seguridadPerfilRolGridCurrentPage').val();
        var rows = jQuery('#seguridadPerfilRolGridRowsByPage').val();
        var order = jQuery('#seguridadPerfilRolGridOrderByColumn').val();
        var orderType = jQuery('#seguridadPerfilRolGridOrderByType').val();
        var perfilId=parseInt(jQuery('#seguridadPerfilform_seguridadPerfilId').val());
        utilEffect.showProgressBar();
        SeguridadPerfilRolDWR.findByCriteria(page, rows, order, orderType, perfilId, obj, seguridadPerfilRolJS.findByCriteriaCallback);
    },
    findByCriteriaCallback: function (data) {
        
        utilGrid.gridUpdateFila('#seguridadPerfilRolGrid', '#seguridadPerfilRolGridCurrentPage', '#seguridadPerfilRolGridRowsByPage', data,'rolId');
        //utilForm.reset('#seguridadPerfilRolform');
        seguridadPerfilJS.filtrarRoles();
        utilEffect.hideProgressBar();
    },
    
    reloadGrid: function () {
        if (seguridadPerfilRolJS.cacheDWR != null) {
            seguridadPerfilRolJS.findByCriteria(seguridadPerfilRolJS.cacheDWR);
        } else {
            seguridadPerfilRolJS.findByCriteria(new SeguridadPerfilRolDWR());
        }
    },
    exportarDatos: function (entity, headers, sortBy, sortType, format) {
        seguridadPerfilRolJS.entity = entity;
        seguridadPerfilRolJS.headers = headers;
        seguridadPerfilRolJS.format = format;
        seguridadPerfilRolJS.reportName = jQuery('#key_seguridadPerfil_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new SeguridadPerfilRolDWR();
        if (seguridadPerfilRolJS.cacheDWR != null) {
            criteriaExample = seguridadPerfilRolJS.cacheDWR;
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
        SeguridadPerfilRolDWR.getReportDataTest(sortBy, sortType, criteriaExample, seguridadPerfilRolJS.exportarDatosCallback);
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
                seguridadPerfilRolJS.entity + '&headers=' + seguridadPerfilRolJS.headers +
                '&reportName=' + seguridadPerfilRolJS.reportName + '&format=' + seguridadPerfilRolJS.format +
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
validanguage.el.seguridadPerfilform_seguridadPerfilId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.seguridadPerfilform_nombre = {
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
validanguage.el.seguridadPerfilRolform_rolId = {
    characters: {
        mode: 'allow', expression: 'numeric', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.seguridadPerfilform_aplicacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};