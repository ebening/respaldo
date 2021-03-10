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
    utilInput.fixRadios('#afiliacionSantanderform');

// <editor-fold defaultstate="collapsed" desc="ACCESOS">
    console.info("title-text = "+jQuery('.title-text:first').text());
    //utilObject.getFuncionalidadPantalla("","AfiliacionSantanderAction");

    $('#btnParcializaciones').addClass('disabled');
    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewAfiliacionSantander').click(function () {
        utilForm.reset('#afiliacionSantanderform');
        jQuery('#afiliacionSantanderform_afiliacionSantanderId').val(0);
        afiliacionSantanderJS.afiliacionSantander = null;
        utilForm.populate('#afiliacionSantanderform', afiliacionSantanderJS.afiliacionDefault);
        $('#btnParcializaciones').addClass('disabled');
    }).customButtonEffect('#btnNewAfiliacionSantander');
    // Botón : GUARDAR
    jQuery('#btnSaveAfiliacionSantander').click(function () {
        afiliacionSantanderJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveAfiliacionSantander');
    // Botón DESPLEGAR
    jQuery('#btnDesplegar').click(function () {
        afiliacionSantanderJS.getDialogOrganizacion();
    }).css({'float': 'right', 'margin-right': '4px', 'margin-top': '15px'}).customButtonEffectBlue('#btnDesplegar');
    // </editor-fold>
    
    jQuery('#parametroDiv').hide();

    if (jQuery('#gridVisibleAfiliacionSantander').val() == 'true') {
        jQuery('#afiliacionSantanderGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#afiliacionSantander_afiliacionSantanderIdText').html(),
                jQuery('#afiliacionSantander_afiliacionText').html(),
                jQuery('#afiliacion_nombreEmpresaText').html(),
                jQuery('#afiliacionSantander_urlText').html(),
                '3DS',//jQuery('#afiliacion_validacionText').html(),
                'SDOS',//jQuery('#afiliacion_validaSdosText').html(),
                'AMEX',//jQuery('#afiliacion_amexText').html(),
                'Parcialidades',//jQuery('#afiliacionSantander_parcialidadesText').html(),
                jQuery('#afiliacion_tipoMonedaText').html(),
                jQuery('#generico_estatus').html(),
                jQuery('#generico_activo').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'afiliacionSantanderId',
                    index: 'afiliacionSantanderId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'afiliacion',
                    index: 'afiliacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombreEmpresa',
                    index: 'nombreEmpresa',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'url',
                    index: 'url',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'valida3ds',
                    index: 'valida3ds',
                    align: 'center',
                    width: 45,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'validaSdos',
                    index: 'validaSdos',
                    align: 'center',
                    width: 45,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'amex',
                    index: 'amex',
                    align: 'center',
                    width: 45,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'tieneParcializacion',
                    index: 'tieneParcializacion',
                    align: 'center',
                    width: 45,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'tipoMonedaId',
                    index: 'tipoMonedaId',
                    align: 'center',
                    width: 45,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'estatusId',
                    index: 'estatusId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'State',
                    index: 'State',
                    align: 'center',
                    width: 30,
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
            pager: jQuery('#afiliacionSantanderGridPagerId'),
            caption: jQuery('#key_afiliacionSantander_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeafiliacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@afiliacionSantanderJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionafiliacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@afiliacionSantanderJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusafiliacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@afiliacionSantanderJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedAfiliacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgSeleccioneUnoOMas').val(), ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoafiliacionSantander', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistro').val(), ['Cancelar@']);
//        utilDialog.setCustomDialogSelectColumnsToExport("#afiliacionSantanderColumnasExportar", '#afiliacionSantanderGrid');
        utilDialog.setCustomDialogSetEstatus('#afiliacionSantanderSetEstatusActivoInactivo',
                'afiliacionSantanderJS.prepareToSetEstatus(1)', 'afiliacionSantanderJS.prepareToSetEstatus(0)');
        utilDialog.setStandardDialog('#d-afiliacionParcializacion',jQuery('#msgAlertaText').val(), jQuery('#msgParcializaciones').val(), ['Aceptar@']);

        // Inicializaciones extra para el grid
        utilGrid.setup('#afiliacionSantanderGrid', '#afiliacionSantanderGridPagerId', '#afiliacionSantanderGridCurrentPage', '#afiliacionSantanderGridOrderByColumn',
                '#afiliacionSantanderGridOrderByType', 'afiliacionSantanderJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchafiliacionSantander');
        utilGrid.setHideElementsTable('afiliacionSantanderGridPagerId',['export']);

        // Recargar el widget jqGrid
        afiliacionSantanderJS.reloadGrid();
        
        //DEFAULT
        afiliacionSantanderJS.inicializaValoresAfiliaciones();
        //PARCIALIZACIONES
        parcializacionSantanderJS.inicializarGrid();
        //BITÁCORA
        bitacoraJS.inicializarGrid();

    }
    // Grid configuracionParametro embebido
    //parametroJS.inicializarGrid();
    
    
    // Limpiar la página
    jQuery('#btnParcializaciones').click();
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
    if (jQuery('#gridIndividualModeafiliacionSantander').val() == 'true') {
        jQuery('#btnParcializaciones').hide();
        afiliacionSantanderJS.findFirst();
    }
    utilObject.getFuncionalidadPantalla("","AfiliacionSantanderAction");

    
});

var afiliacionSantanderJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    afiliacionSantander: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de afiliacionSantanderr, ordenar, etc.
     */
    cacheDWR: null,
    //DEFAULT
    afiliacionDefault:null,
    activarBase3ds:function(el){
         if (jQuery(el).is(':checked')) {
             jQuery('#afiliacionSantanderform_base3ds').attr('disabled','disabled');
             jQuery('#afiliacionSantanderform_semilla3ds').removeAttr('disabled');
         }else{
             jQuery('#afiliacionSantanderform_base3ds').removeAttr('disabled');
             jQuery('#afiliacionSantanderform_semilla3ds').attr('disabled','disabled');
         }
    },
    activarAfiliacionAmex:function(el){
         if (jQuery(el).is(':checked')) {
            jQuery('#afiliacionSantanderform_afiliacionAmex').removeAttr('disabled');
            jQuery('#afiliacionSantanderform_merchantAmex').removeAttr('disabled');
         }else{
            jQuery('#afiliacionSantanderform_afiliacionAmex').attr('disabled','disabled');
            jQuery('#afiliacionSantanderform_merchantAmex').attr('disabled','disabled'); 
         }
    },
    activarOrderSourceMovil:function(el){
         if (jQuery(el).is(':checked')) {
            jQuery('#afiliacionSantanderform_orderSourceMovil').removeAttr('disabled');
         }else{
            jQuery('#afiliacionSantanderform_orderSourceMovil').attr('disabled','disabled');
         }
    },
    cambiarValorCripto:function(el){
        var clave ='';
       utilEffect.showProgressBar();
        var combo=$('#afiliacionSantanderform_tipoCanalVentaId').find(":selected").text();
        if(combo=='PRESENCIAL'){
            clave='CRYPTO_0';
            $('#afiliacion_dataiText').addClass("required_star col-xs-10_star");
            $('#afiliacion_datakText').addClass("required_star col-xs-10_star");
            
        }
        else{
            clave='CRYPTO_3';
            $('#afiliacion_dataiText').removeClass("required_star col-xs-10_star");
            $('#afiliacion_datakText').removeClass("required_star col-xs-10_star");
        }
            
        AfiliacionSantanderDWR.getCripto(clave,afiliacionSantanderJS.inicializaValoresCriptoCallback);
    },
    inicializaValoresCriptoCallback:function(data){
        jQuery('#afiliacionSantanderform_crypto').val(data);
        utilEffect.hideProgressBar();
    },
    getDialogParcialidades: function (){
        //Inicializar valores de Parcializaciones
        jQuery('#parcializacionSantanderform_afiliacion').val(afiliacionSantanderJS.afiliacionSantander.afiliacion);
        jQuery('#parcializacionSantanderform_claveInstitucionBancaria').val(afiliacionSantanderJS.afiliacionSantander.claveInstitucionBancaria);
        // Recargar el widget jqGrid para obtener todas las parcializaciones en base a la afiliacion editada
        parcializacionSantanderJS.reloadGrid();
//        $( "#afiliacionSantanderform_btnParcializaciones" ).prop( "disabled", false );
        $( "#parcialidadesSantanderDiv" ).dialog({
          autoOpen: true,
          height: 700,
          width: 1000,
          modal: true,
          buttons: {
            Cancelar: function() {
                afiliacionSantanderJS.reloadGrid();
                $( this ).dialog( "close" );
            }
          },
          close: function(event, ui){
                utilEffect.hideProgressBar();
                $( this ).dialog( "close" );
          }
        });
    },
    activarParcialidades:function(){
        
        $('#btnParcializaciones').removeClass('disabled');
        jQuery('#btnParcializaciones').click(function () {
                afiliacionSantanderJS.getDialogParcialidades();
        });
    },
        //DEFAULT
    inicializaValoresAfiliaciones:function(){
        AfiliacionSantanderDWR.getCatalogosAfiliaciones(afiliacionSantanderJS.inicializaValoresAfiliacionesCallback);
    },
    inicializaValoresAfiliacionesCallback:function(data){
        afiliacionSantanderJS.afiliacionDefault=Object.assign({}, data);
        utilForm.populate('#afiliacionSantanderform', data);
    },
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var afiliacionSantander = utilObject.buildObject('#afiliacionSantanderform', new AfiliacionSantanderDWR());
        afiliacionSantander.afiliacionSantanderId = 0;
        var listaafiliacionSantanders = [afiliacionSantander];
        AfiliacionSantanderDWR.save(listaafiliacionSantanders, afiliacionSantanderJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var afiliacionSantander = utilObject.buildObject('#afiliacionSantanderform', new AfiliacionSantanderDWR());
        afiliacionSantander.afiliacionSantanderId = jQuery('#afiliacionSantanderform_afiliacionSantanderId').val();
        var listaAfiliacionSantanders = [afiliacionSantander];
        AfiliacionSantanderDWR.update(listaAfiliacionSantanders, afiliacionSantanderJS.saveOrUpdateCallback);
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
        utilForm.reset('#afiliacionSantanderform');
        jQuery('#afiliacionSantanderform_afiliacionSantanderId').val(0);
        afiliacionSantanderJS.afiliacionSantander = null;
        afiliacionSantanderJS.reloadGrid();
        utilForm.populate('#afiliacionSantanderform', afiliacionSantanderJS.afiliacionDefault);
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
        var id = afiliacionSantanderJS.getEliminarId();
        var listaIds = [id];
        AfiliacionSantanderDWR.remove(listaIds, afiliacionSantanderJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#afiliacionSantanderGrid');
        AfiliacionSantanderDWR.remove(listaIds, afiliacionSantanderJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
//        afiliacionSantanderJS.getAfiliacionSantanderes();
        jQuery('#btnParcializaciones').click();
        afiliacionSantanderJS.reloadGrid();
    },
    /*
     * Función que actualiza las opciones del select anidar despues de haber hecho 
     * una modificacion a la base de datos
     * 
     */
    getAfiliacionSantanderes: function () {
        utilEffect.showProgressBar();
        AfiliacionSantanderDWR.getAfiliacionSantanderes(afiliacionSantanderJS.getAfiliacionSantanderesCallback);
    },
    /*
     * Callback de la función getAfiliacionSantanderes(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    getAfiliacionSantanderesCallback: function (pageList) {
        jQuery('#afiliacionSantanderform_anidar').find('option').remove();
        for (var i = 0; i < pageList.length; i++) {
            var afiliacionSantander = pageList[i];
            var id = afiliacionSantander.afiliacionSantanderId;
            var nombre = afiliacionSantander.nombre;
            var style = '';
            if (afiliacionSantander.url == null || afiliacionSantander.url == "") {
                style = 'style="font-weight: bold; color: #33B5E5"';
            }
            jQuery('#afiliacionSantanderform_anidar').append('<option ' + style +
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
        //PARCIALIDADES
        afiliacionSantanderJS.activarParcialidades();
        AfiliacionSantanderDWR.findById(id, afiliacionSantanderJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#afiliacionSantanderform');
        utilForm.populate('#afiliacionSantanderform', data);
        afiliacionSantanderJS.afiliacionSantander = Object.assign({}, data);
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
        afiliacionSantanderJS.cacheDWR = obj;
        var page = jQuery('#afiliacionSantanderGridCurrentPage').val();
        var rows = jQuery('#afiliacionSantanderGridRowsByPage').val();
        var order = jQuery('#afiliacionSantanderGridOrderByColumn').val();
        var orderType = jQuery('#afiliacionSantanderGridOrderByType').val();
        utilEffect.showProgressBar();
        AfiliacionSantanderDWR.findByCriteria(page, rows, order, orderType, obj, afiliacionSantanderJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#afiliacionSantanderGrid', '#afiliacionSantanderGridCurrentPage', '#afiliacionSantanderGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la afiliacionSantander es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        AfiliacionSantanderDWR.findFirst(afiliacionSantanderJS.findByIdCallback);
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
        var nombre = jQuery('#afiliacionSantanderform_afiliacion').val();
        AfiliacionSantanderDWR.isValidoNombre(nombre, afiliacionSantanderJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#afiliacionSantanderform_afiliacionSantanderId').val() != 0) {
            afiliacionSantanderJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#afiliacionSantanderform_afiliacionSantanderId').val() == 0) {
            afiliacionSantanderJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#afiliacionSantanderform_afiliacionSantanderId').val() != 0) {
            afiliacionSantanderJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoafiliacionSantander');
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
        AfiliacionSantanderDWR.setEstatus(estatusId, listaObjetos, afiliacionSantanderJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = afiliacionSantanderJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#afiliacionSantanderGrid');
        AfiliacionSantanderDWR.setEstatus(estatusId, listaObjetos, afiliacionSantanderJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        
        utilEffect.hideProgressBar();
        afiliacionSantanderJS.reloadGrid();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#afiliacionSantanderGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedafiliacionSantander');
        } else {
            afiliacionSantanderJS.estatusId = estatusId;
             utilDialog.showDialog( '#d-confirmSetEstatusafiliacionSantander' );
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#afiliacionSantanderGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedafiliacionSantander');
        } else {
            utilDialog.showDialog('#d-removeSeleccionafiliacionSantander');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        afiliacionSantanderJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeafiliacionSantander');
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
        afiliacionSantanderJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return afiliacionSantanderJS.eliminarId;
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
        var validation = validanguage.validateForm('afiliacionSantanderform');
        if (validation.result) {
            afiliacionSantanderJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#afiliacionSantanderform_afiliacionSantanderId').val() == 0) {
            afiliacionSantanderJS.save();
        } else {
            afiliacionSantanderJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (afiliacionSantanderJS.cacheDWR != null) {
            afiliacionSantanderJS.findByCriteria(afiliacionSantanderJS.cacheDWR);
        } else {
            afiliacionSantanderJS.findByCriteria(new AfiliacionSantanderDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchafiliacionSantander', '#afiliacionSantanderGrid', '#afiliacionSantanderform', afiliacionSantanderJS, new AfiliacionSantanderDWR());
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
        afiliacionSantanderJS.entity = entity;
        afiliacionSantanderJS.headers = headers;
        afiliacionSantanderJS.format = format;
        afiliacionSantanderJS.reportName = jQuery('#key_afiliacionSantander_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new AfiliacionSantanderDWR();
        if (afiliacionSantanderJS.cacheDWR != null) {
            criteriaExample = afiliacionSantanderJS.cacheDWR;
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
        AfiliacionSantanderDWR.getReportDataTest(sortBy, sortType, criteriaExample, afiliacionSantanderJS.exportarDatosCallback);
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
                afiliacionSantanderJS.entity + '&headers=' + afiliacionSantanderJS.headers +
                '&reportName=' + afiliacionSantanderJS.reportName + '&format=' + afiliacionSantanderJS.format +
                '&reportKey=' + reportKey;
    },
    
    //FUNCIONES PARA DESPLEGAR
    getDialogOrganizacion: function (){
        var seleccion = utilGrid.gridGetSelectedRows('#afiliacionSantanderGrid');
        if(seleccion.length>0){
           SeguridadPerfilDWR.getOrganizaciones(afiliacionSantanderJS.getDialogOrganizacionCallBack);
        }else{
            utilDialog.showDialog('#d-noSelectedAfiliacionSantander');
        }
       
    },
    getDialogOrganizacionCallBack: function (data){
       utilDialog.setCustomDialogOrganizaciones('#dialogOrganizacionesCatalogo',data,'afiliacionSantanderJS.desplegar','nombre');
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
       var seleccion = utilGrid.gridGetSelectedRows('#afiliacionSantanderGrid');
       var idEjecucion = new Date().getTime();
       var listElementos = seleccion.join();
       utilEffect.showProgressBar();
       AfiliacionSantanderDWR.desplegar(page, rows, order, orderType, data, listElementos, idEjecucion, afiliacionSantanderJS.desplegarCallback);
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
// Validaciones de afiliacionSantanderform
validanguage.el.afiliacionSantanderform_tipoCanalVentaId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionSantanderform_canalVentaId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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

validanguage.el.afiliacionSantanderform_afiliacion = {
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
validanguage.el.afiliacionSantanderform_url = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField,ProfileUrl ,ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionSantanderform_semilla = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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
validanguage.el.afiliacionSantanderform_semillaConsulta = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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

validanguage.el.afiliacionSantanderform_usuario = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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

validanguage.el.afiliacionSantanderform_baseCobro = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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

validanguage.el.afiliacionSantanderform_contrasena = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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
validanguage.el.afiliacionSantanderform_usuarioAdmin = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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
validanguage.el.afiliacionSantanderform_contrasenaAdmin = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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

validanguage.el.afiliacionSantanderform_idCompany = {
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

validanguage.el.afiliacionSantanderform_merchant = {
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
validanguage.el.afiliacionSantanderform_operacion = {
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
validanguage.el.afiliacionSantanderform_country = {
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
validanguage.el.afiliacionSantanderform_tipoMonedaId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.afiliacionSantanderform_idBranch = {
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
validanguage.el.afiliacionSantanderform_afiliacionAmex = {
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
        }]};
validanguage.el.afiliacionSantanderform_merchantAmex = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
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
validanguage.el.afiliacionSantanderform_nombreEmpresa = {
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
validanguage.el.afiliacionSantanderform_direccionEmpresa = {
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
validanguage.el.afiliacionSantanderform_claveEstatus = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter, {
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
