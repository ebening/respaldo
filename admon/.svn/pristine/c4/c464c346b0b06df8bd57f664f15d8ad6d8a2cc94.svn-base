jQuery(document).ready(function () {
    
    jQuery('#afiliacionbanamexform_tipoCanalVentaId').change(function () {
        afiliacionBanamexJS.getValoresByTipoCanal();
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
    //Funcion para mostrar/ocultar permisos por Pantalla
  //  utilObject.getFuncionalidadPantalla("es","PerfilAction");
  
    $('#btnParcializaciones').addClass('disabled');
    $('#btnTerminales').addClass('disabled');
    
    //Funcion para mostrar/ocultar permisos por Pantalla
    utilObject.getFuncionalidadPantalla("","AfiliacionBanamexAction");
    
    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewAfiliacionBanamex').click(function () {
        utilForm.reset('#afiliacionbanamexform');
       // afiliacionBanamexJS.reset();
        afiliacionBanamexJS.afiliacionBanamex = null;
        utilForm.populate('#afiliacionbanamexform', afiliacionBanamexJS.afiliacionDefault);
        $('#btnParcializaciones').addClass('disabled');
      }).customButtonEffect('#btnNewAfiliacionBanamex');
    // Botón : GUARDAR
    jQuery('#btnSaveAfiliacionBanamex').click(function () {
        afiliacionBanamexJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveAfiliacionBanamex');
     // Botón DESPLEGAR
    jQuery('#btnDesplegar').click(function () {
        afiliacionBanamexJS.getDialogOrganizacion();
    }).css({'float': 'right', 'margin-right': '4px', 'margin-top': '15px'}).customButtonEffectBlue('#btnDesplegar');
 
    // Botón : TERMINALES
    //jQuery('#btnTerminales').click(function () {
    //    afiliacionBanamexJS.getDialogTerminales();
    //}).customButtonEffectBlue('#btnTerminales');
    // </editor-fold>
    
   // if (jQuery('#gridVisibleAfiliacionBanamex').val() == 'true') {
        jQuery('#afiliacionBanamexGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#afiliacion_afiliacionText').html(),
                jQuery('#afiliacion_afiliacionText').html(),
                jQuery('#afiliacion_tipoCanalText').html(),
                jQuery('#afiliacion_canalVentaText').html(),
                jQuery('#afiliacion_urlText').html(),
                jQuery('#afiliacion_versionText').html(),
                'Terminales', 'Parcialidades',  
                jQuery('#generico_activo').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'afiliacionBanamexId',
                    index: 'afiliacionBanamexId',
                    align: 'center',
                    width: 40,
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'afiliacionBanamex',
                    index: 'afiliacionBanamex',
                    align: 'center',
                    width: 50,
                    resizable: false,
                    hidden: false,
                    search: true
                },{
                    name: 'tipoCanalVentaId',
                    index: 'tipoCanalVentaId',
                    align: 'center',
                    width: 50,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'canalVentaId',
                    index: 'canalVentaId',
                    align: 'center',
                    width: 50,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'url',
                    index: 'url',
                    align: 'center',
                    width: 140,
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'versionId',
                    index: 'versionId',
                    align: 'center',
                    width: 30,
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'tieneTerminales',
                    index: 'tieneTerminales',
                    align: 'center',
                    width: 40,
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'tieneParcializacion',
                    index: 'tieneParcializacion',
                    align: 'center',
                    width: 40,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
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
                    width: 30,
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
            pager: jQuery('#afiliacionBanamexGridPagerId'),
            caption: jQuery('#key_afiliacionbanamex_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeAfiliacionBanamex',jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@afiliacionBanamexJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionAfiliacionBanamex',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@afiliacionBanamexJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusAfiliacionBanamex',jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@afiliacionBanamexJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedAfiliacionBanamex', jQuery('#msgAlertaText').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-noSelectedEditar', jQuery('#msgError').val(),jQuery('#msgEditarRegistro').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-regristroDuplicadoAfiliacion', jQuery('#msgError').val(),jQuery('#msgYaExisteAfiliacionDefault').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoAfiliacionBanamex', 'Advertencia', jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#afiliacionBanamexColumnasExportar", '#afiliacionBanamexGrid');
        utilDialog.setCustomDialogSetEstatus('#afiliacionBanamexSetEstatusActivoInactivo',
                'afiliacionBanamexJS.prepareToSetEstatus(1)', 'afiliacionBanamexJS.prepareToSetEstatus(0)');
        utilDialog.setStandardDialog('#d-afiliacionParcializacion',jQuery('#msgAlertaText').val(), jQuery('#msgParcializaciones').val(), ['Aceptar@']);
        // Inicializaciones extra para el grid
        utilGrid.setup('#afiliacionBanamexGrid', '#afiliacionBanamexGridPagerId', '#afiliacionBanamexGridCurrentPage', '#afiliacionBanamexGridOrderByColumn',
                '#afiliacionBanamexGridOrderByType', 'afiliacionBanamexJS');

        // Crear el modal de búsqueda
        utilSearch.buildSearch('#d-searchAfiliacionBanamex');
        utilGrid.setHideElementsTable('afiliacionBanamexGridPagerId',['activate','export']);
        // Recargar el widget jqGrid
        afiliacionBanamexJS.reloadGrid();
        //DEFAULT
        afiliacionBanamexJS.inicializaValoresAfiliaciones();
        //PARCIALIZACIONES
        parcializacionBanamexJS.inicializarGrid();
        //TERMINALES
        terminalBanamexJS.inicializarGrid();
        //BITÁCORA
        bitacoraJS.inicializarGrid();
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
});

var afiliacionBanamexJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    afiliacionBanamex: null,
    //DEFAULT
    afiliacionDefault:null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
   
    getDialogParcialidades: function (){
        //Inicializar valores de Parcializaciones
        jQuery('#parcializacionBanamexform_afiliacionBanamex').val(afiliacionBanamexJS.afiliacionBanamex.afiliacionBanamex);
        jQuery('#parcializacionBanamexform_claveInstitucionBancaria').val(afiliacionBanamexJS.afiliacionBanamex.claveInstitucionBancaria);
        jQuery('#parcialidadesBanamexDiv').show();
        // Recargar el widget jqGrid para obtener todas las parcializaciones en base a la afiliacion editada
        parcializacionBanamexJS.reloadGrid();
        //$( "#afiliacionbanamexform_btnParcializaciones" ).prop( "disabled", false );
        $( "#parcialidadesBanamexDiv" ).dialog({
          autoOpen: true,
          height: 700,
          width: 1000,
          modal: true,
          buttons: {
            Cancelar: function() {
                afiliacionBanamexJS.reloadGrid();
                $( this ).dialog( "close" );
            }
          },
          close: function(event, ui){
            utilEffect.hideProgressBar();
             $( this ).dialog( "close" );
          }
        });
    },
    getDialogTerminales: function (){
        jQuery('#terminalesBanamexform_afiliacionBanamex').val(afiliacionBanamexJS.afiliacionBanamex.afiliacionBanamex);
        jQuery('#terminalesBanamexDiv').show();
        // Recargar el widget jqGrid
        terminalBanamexJS.reloadGrid();
        //$( "#afiliacionbanamexform_btnTerminales" ).prop( "disabled", false );
        $( "#terminalesBanamexDiv" ).dialog({
            autoOpen: true,
            height: 700,
            width: 1000,
            modal: true,
            buttons: {
              Cancelar: function() {
                  $( this ).dialog( "close" );
                  afiliacionBanamexJS.reloadGrid();
              }
              
            },
            close: function(event, ui)
            {
                utilEffect.hideProgressBar();
                $( this ).dialog( "close" );
            }
        });
    },
    
    activarParcialidades:function(){
        $('#btnParcializaciones').removeClass('disabled');
        jQuery('#btnParcializaciones').click(function () {
                afiliacionBanamexJS.getDialogParcialidades();
        }).customButtonEffectBlue('#btnParcializaciones');
    },
    activarTerminales:function(){
        $('#btnTerminales').removeClass('disabled');
        jQuery('#btnTerminales').click(function () {
                afiliacionBanamexJS.getDialogTerminales();
        }).customButtonEffectBlue('#btnTerminales');
    },
    //DEFAULT
    inicializaValoresAfiliaciones:function(){
        AfiliacionBanamexDWR.getCatalogosAfiliaciones(afiliacionBanamexJS.inicializaValoresAfiliacionesCallback);
    },
    inicializaValoresAfiliacionesCallback:function(data){
        afiliacionBanamexJS.afiliacionDefault = Object.assign({}, data);
        data.url = "";//Se inicializan como vacio ya que este valor únicamente se llena autómatico cuando se trata de WEB
        data.command = "";//Se inicializan como vacio ya que este valor únicamente se llena autómatico cuando se trata de WEB
        utilForm.populate('#afiliacionbanamexform', data);
    },
    getValoresByTipoCanal:function(){
        var valor = jQuery("#afiliacionbanamexform_tipoCanalVentaId option:selected").html();
        if(valor === "WEB"){
            jQuery('#afiliacionbanamexform_url').val(afiliacionBanamexJS.afiliacionDefault.url);
            jQuery('#afiliacionbanamexform_command').val(afiliacionBanamexJS.afiliacionDefault.command);
        }
    },
     /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var afiliacionBanamex = utilObject.buildObject('#afiliacionbanamexform', new AfiliacionBanamexDWR());
        afiliacionBanamex.afiliacionBanamexId = 0;
        afiliacionBanamex.afiliaciones = "";
        afiliacionBanamex.parcialidades ="";
        var listaAfiliaciones = [afiliacionBanamex];
        AfiliacionBanamexDWR.save(listaAfiliaciones, afiliacionBanamexJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var afiliacionBanamex = utilObject.buildObject('#afiliacionbanamexform', new AfiliacionBanamexDWR());
        afiliacionBanamex.afiliacionBanamexId = jQuery('#afiliacionbanamexform_afiliacionBanamexId').val();
        var listaAfiliaciones = [afiliacionBanamex];
        AfiliacionBanamexDWR.update(listaAfiliaciones, afiliacionBanamexJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        jQuery('#btnNewAfiliacionBanamex').click();
        afiliacionBanamexJS.afiliacionBanamex = null;
        afiliacionBanamexJS.reloadGrid();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());  
    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = afiliacionBanamexJS.getEliminarId();
        var listaIds = [id];
        AfiliacionBanamexDWR.remove(listaIds, afiliacionBanamexJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#afiliacionBanamexGrid');
        AfiliacionBanamexDWR.remove(listaIds, afiliacionBanamexJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewAfiliacionBanamex').click();
        afiliacionBanamexJS.reloadGrid();
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
        //PARCIALIDADES Y TERMINALES
        afiliacionBanamexJS.activarParcialidades();
        afiliacionBanamexJS.activarTerminales();
        
        AfiliacionBanamexDWR.findById(id, afiliacionBanamexJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#afiliacionbanamexform');
        utilForm.populate('#afiliacionbanamexform', data);
        afiliacionBanamexJS.afiliacionBanamex = data;
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
        afiliacionBanamexJS.cacheDWR = obj;
        var page = jQuery('#afiliacionBanamexGridCurrentPage').val();
        var rows = jQuery('#afiliacionBanamexGridRowsByPage').val();
        var order = jQuery('#afiliacionBanamexGridOrderByColumn').val();
        var orderType = jQuery('#afiliacionBanamexGridOrderByType').val();
        AfiliacionBanamexDWR.findByCriteria(page, rows, order, orderType, obj, afiliacionBanamexJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#afiliacionBanamexGrid', '#afiliacionBanamexGridCurrentPage', '#afiliacionBanamexGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        AfiliacionBanamexDWR.findFirst(afiliacionBanamexJS.findByIdCallback);
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
        var nombre = jQuery('#afiliacionbanamexform_afiliacionBanamex').val();
        var canalVenta =  jQuery("#afiliacionbanamexform_canalVentaId").val();
        AfiliacionBanamexDWR.isValidoNombreByAfiliacionCanal(nombre,canalVenta, afiliacionBanamexJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#afiliacionbanamexform_afiliacionBanamexId').val() != 0) {
            afiliacionBanamexJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#afiliacionbanamexform_afiliacionBanamexId').val() == 0) {
            afiliacionBanamexJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#afiliacionbanamexform_afiliacionBanamexId').val() != 0) {
            afiliacionBanamexJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoAfiliacionBanamex');
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
        AfiliacionBanamexDWR.setEstatus(estatusId, listaObjetos, afiliacionBanamexJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = afiliacionBanamexJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#afiliacionBanamexGrid');
        AfiliacionBanamexDWR.setEstatus(estatusId, listaObjetos, afiliacionBanamexJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        afiliacionBanamexJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#afiliacionBanamexGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedAfiliacionBanamex');
        } else {
            afiliacionBanamexJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusPerfil' );
            afiliacionBanamexJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#afiliacionBanamexGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedAfiliacionBanamex');
        } else {
            utilDialog.showDialog('#d-removeSeleccionAfiliacionBanamex');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        afiliacionBanamexJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeAfiliacionBanamex');
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
        afiliacionBanamexJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return afiliacionBanamexJS.eliminarId;
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
        var validation = validanguage.validateForm('afiliacionbanamexform');
        if (validation.result) {
            afiliacionBanamexJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#afiliacionbanamexform_afiliacionBanamexId').val() == 0) {
            afiliacionBanamexJS.save();
        } else {
            afiliacionBanamexJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (afiliacionBanamexJS.cacheDWR != null) {
            afiliacionBanamexJS.findByCriteria(afiliacionBanamexJS.cacheDWR);
        } else {
            afiliacionBanamexJS.findByCriteria(new AfiliacionBanamexDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchAfiliacionBanamex', '#afiliacionBanamexGrid', '#afiliacionbanamexform', afiliacionBanamexJS, new AfiliacionBanamexDWR());
    },
    
    reset: function () {
        jQuery('#afiliacionBanamexGrid').clearGridData();
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
        afiliacionBanamexJS.entity = entity;
        afiliacionBanamexJS.headers = headers;
        afiliacionBanamexJS.format = format;
        afiliacionBanamexJS.reportName = jQuery('#key_afiliacionBanamex_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new PerfilDWR();
        if (afiliacionBanamexJS.cacheDWR != null) {
            criteriaExample = afiliacionBanamexJS.cacheDWR;
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
        AfiliacionBanamexDWR.getReportDataTest(sortBy, sortType, criteriaExample, afiliacionBanamexJS.exportarDatosCallback);
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
                afiliacionBanamexJS.entity + '&headers=' + afiliacionBanamexJS.headers +
                '&reportName=' + afiliacionBanamexJS.reportName + '&format=' + afiliacionBanamexJS.format +
                '&reportKey=' + reportKey;
    },
    
    //FUNCIONES PARA DESPLEGAR
    getDialogOrganizacion: function (){
        var seleccion = utilGrid.gridGetSelectedRows('#afiliacionBanamexGrid');
        if(seleccion.length>0){
           SeguridadPerfilDWR.getOrganizaciones(afiliacionBanamexJS.getDialogOrganizacionCallBack);
        }else{
            utilDialog.showDialog('#d-noSelectedAfiliacionBanamex');
        }
      
    },
    getDialogOrganizacionCallBack: function (data){
       utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'afiliacionBanamexJS.desplegar','nombre');
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
       var seleccion = utilGrid.gridGetSelectedRows('#afiliacionBanamexGrid');
       var idEjecucion = new Date().getTime();//Math.round(+new Date()/1000);
       var listElementos = seleccion.join();
       utilEffect.showProgressBar();
       AfiliacionBanamexDWR.desplegar(page, rows, order, orderType, data, listElementos, idEjecucion, afiliacionBanamexJS.desplegarCallback);
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
// Validaciones de afiliacionbanamexform
validanguage.el.afiliacionbanamexform_afiliacionBanamex = {
    characters: {
        mode: 'allow', expression: 'numeric', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.afiliacionbanamexform_tipoCanalVentaId = {
    characters: {
        mode: 'allow', expression: 'numeric', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.afiliacionbanamexform_canalVentaId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 15) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 15 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.afiliacionbanamexform_url = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_command = {
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
validanguage.el.afiliacionbanamexform_accessCode = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 50) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 20 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.afiliacionbanamexform_merchant = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 50) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 50 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.afiliacionbanamexform_returnUrl = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_secureScret = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_actionUrl = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_claveInstitucionBancaria = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_urlCancelacion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_urlDevolucion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionbanamexform_trnCurId = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 5) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 5 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.afiliacionbanamexform_nombreEmpresa = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 600) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 600 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.afiliacionbanamexform_trnTipAmount = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.afiliacionbanamexform_trnCashbackAmount = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};

/*validanguage.el.afiliacionbanamexform_afiliacionBanamexId = {
    characters: {
        mode: 'allow', expression: 'numeric', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};*/