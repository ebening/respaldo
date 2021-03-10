var perfilAplicacionJS = {
        /**
     * Limpia el formulario.
     */
    clean: function () {
       utilForm.reset('#perfilAplicacionform');
       jQuery('#perfilAplicacionform_usuarioId').val(perfilAplicacionJS.getUsuarioId());
       jQuery('#perfilAplicacionform_organizacionId').val(perfilAplicacionJS.getOrganizacionId());
       var options = $("#perfilAplicacionform_seguridadPerfilId");
       options.empty();
       options.append($("<option />").val("-1").text("--Seleccione--"));
    },
    /**
     * Limpia el formulario y el grid de organizacionCredencial, usado cuando el
     * usuario presiona el bot&oacute;n Nuevo.
     */
    setNuevoRegistro: function(nuevo){
        perfilAplicacionJS.nuevo = nuevo;
    },
    getNuevoRegistro: function(){
        return perfilAplicacionJS.nuevo;
    },
    cacheDWR: null,
    nuevo:true,
    perfilAplicacion:null,
    
    inicializarGrid: function () {
        // Bot&oacute;n AGREGAR (formulario aplicacionParametro)
        jQuery('#btnAddPerfilAplicacion').click(function () {
            perfilAplicacionJS.prepareToAgregar();
        });
        // Botón : NUEVO PARAMETRO
        jQuery('#btnNewPerfilAplicacion').click(function () {
            perfilAplicacionJS.clean();
            perfilAplicacionJS.setNuevoRegistro(true);
        }).customButtonEffect('#btnNewParametro');
    
        jQuery('#perfilAplicacionGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#perfilaplicacion_usuarioIdText').html(),
                jQuery('#perfilaplicacion_organizacionIdText').html(),
                jQuery('#perfilaplicacion_aplicacionIdText').html(),
                jQuery('#perfilaplicacion_perfilIdText').html(),
                jQuery('#perfilaplicacion_descripcionText').html(),
                'Modificar',
                'Eliminar'],
            colModel: [{
                    name: 'usuarioId',
                    index: 'usuarioId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                },{
                    name: 'organizacionId',
                    index: 'organizacionId',
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
                    search: false
                }, {
                    name: 'seguridadPerfilId',
                    index: 'seguridadPerfilId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false

                }, {
                    name: 'descripcion',
                    index: 'descripcion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'EditPerfilAplicacion',
                    index: 'EditPerfilAplicacion',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'RemovePerfilAplicacion',
                    index: 'RemovePerfilAplicacion',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: false,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#perfilAplicacionGridPagerId'),
            caption: jQuery('#key_perfilAplicacion_title').val()
    });

        // jQuery UI Dialogs
       utilDialog.setStandardDialog('#d-removePerfilAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@perfilAplicacionJS.remove()', 'Cancelar@']);
       utilDialog.setStandardDialog('#d-registroDuplicadoPerfilAplicacion', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistroAplicacion').val(), ['Aceptar@']);


       // Inicializaciones extra para el grid
       utilGrid.setup('#perfilAplicacionGrid', '#perfilAplicacionGridPagerId', '#perfilAplicacionGridCurrentPage', '#perfilAplicacionGridOrderByColumn',
               '#perfilAplicacionGridOrderByType', 'perfilAplicacionJS');
       utilGrid.setHideElementsTable('perfilAplicacionGridPagerId',['search','export','activate','delete']); 
       jQuery('#perfilAplicacionform_usuarioId').val(null);
       jQuery('#perfilAplicacionform_organizacionId').val(null);
       utilInput.removeOptionsSelectAndAddDummy( '#perfilAplicacionform_seguridadPerfilId', '--Seleccione--' );
       perfilAplicacionJS.reloadGrid();
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
        var perfilAplicacion = utilObject.buildObject('#perfilAplicacionform', new PerfilAplicacionDWR());
        PerfilAplicacionDWR.isValidoNombrePerfilAplicacion(perfilAplicacion, perfilAplicacionJS.isValidoNombreCallback);
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
        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == false && !perfilAplicacionJS.getNuevoRegistro()){
                var anterior = perfilAplicacionJS.perfilAplicacion;
                var nuevo= utilObject.buildObject('#perfilAplicacionform', new PerfilAplicacionDWR());
                if(anterior.usuarioId == nuevo.usuarioId && anterior.organizacionId == nuevo.organizacionId
                        && anterior.seguridadPerfilId == nuevo.seguridadPerfilId){
                    perfilAplicacionJS.doCommit();
                    return;
                }else{
                    utilDialog.showDialog('#d-registroDuplicadoPerfilAplicacion');
                }  
        }
        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && perfilAplicacionJS.getNuevoRegistro()) {
            perfilAplicacionJS.doCommit();
            return;
        }
        //Editando Registro
        if (isValido == true && !perfilAplicacionJS.getNuevoRegistro()) {
            perfilAplicacionJS.doCommit();
            return;
        }
        utilDialog.showDialog('#d-registroDuplicadoPerfilAplicacion');
    },
    
    reloadGrid: function () {
        if (perfilAplicacionJS.cacheDWR != null) {
            perfilAplicacionJS.findByCriteria(perfilAplicacionJS.cacheDWR);
        } else {
            perfilAplicacionJS.findByCriteria(new PerfilAplicacionDWR());
        }
    },
    findByCriteria: function (obj) {
        perfilAplicacionJS.cacheDWR = obj;
        var page = jQuery('#perfilAplicacionGridCurrentPage').val();
        var rows = jQuery('#perfilAplicacionGridRowsByPage').val();
        var order = jQuery('#perfilAplicacionGridOrderByColumn').val();
        var orderType = jQuery('#perfilAplicacionGridOrderByType').val();
        if(perfilAplicacionJS.getUsuarioId() == null) 
            return;
        else
           PerfilAplicacionDWR.findByCriteriaPerfilAplicacion(page, rows, order, orderType,perfilAplicacionJS.getUsuarioId(),perfilAplicacionJS.getOrganizacionId(), perfilAplicacionJS.findByCriteriaCallback);
    },
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#perfilAplicacionGrid', '#perfilAplicacionGridCurrentPage', '#perfilAplicacionGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (perfilAplicacionJS.nuevo) {
            perfilAplicacionJS.savePerfilAplicacion();
        } else {
            perfilAplicacionJS.updatePerfilAplicacion();
        }
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param aplicacionId, es el id de aplicacion.
     */
    savePerfilAplicacion: function () {
        var perfilAplicacion = utilObject.buildObject('#perfilAplicacionform', new PerfilAplicacionDWR());
        var listaPerfilAplicaciones = [perfilAplicacion];
        PerfilAplicacionDWR.savePerfilAplicacion(listaPerfilAplicaciones, perfilAplicacionJS.saveOrUpdateAplicacionParametroCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    updatePerfilAplicacion: function () {
        utilEffect.showProgressBar();
        var seguridadPerfilId = perfilAplicacionJS.perfilAplicacion.seguridadPerfilId;
        var perfilAplicacion = utilObject.buildObject('#perfilAplicacionform', new PerfilAplicacionDWR());
        var listaPerfilAplicaciones= [perfilAplicacion];
        PerfilAplicacionDWR.updatePerfilAplicacion(listaPerfilAplicaciones, seguridadPerfilId, perfilAplicacionJS.saveOrUpdateAplicacionParametroCallback);
    },
    /* Callback de la funci&oacute;n save(), esta funci&oacute;n es llamada por DWR al terminar
     * de realizar la operaci&oacute;n */
    saveOrUpdateAplicacionParametroCallback: function () {
        utilEffect.hideProgressBar();
        perfilAplicacionJS.clean();
        perfilAplicacionJS.reloadGrid();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());

    },
       /** 
     * Valida los campos del formulario de perfilAplicacionform antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('perfilAplicacionform');
        if (validation.result) {
            perfilAplicacionJS.isValidoNombre();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * lenguajeParametro.
     */
    agregar: function () {
        perfilAplicacionJS.doCommit();
    },
    seguridadPerfilId: null,
    aplicacionId: null,
    usuarioId: null,
    organizacionId: null,
    /*
     * Setter de la variable eliminarId
     **/
    setPerfilId: function (seguridadPerfilId) {
        perfilAplicacionJS.seguridadPerfilId = seguridadPerfilId;
    },
    /*
     * Getter de la variable PerfilId
     **/
    getPerfilId: function () {
        return perfilAplicacionJS.seguridadPerfilId;
    },
     /*
     * Setter de la variable PerfilId
     **/
    setAplicacionId: function (aplicacionId) {
        perfilAplicacionJS.aplicacionId = aplicacionId;
    },
    /*
     * Getter de la variable AplicacionId
     **/
    getAplicacionId: function () {
        return perfilAplicacionJS.aplicacionId;
    },
     /*
     * Setter de la variable PerfilId
     **/
    setUsuarioId: function (usuarioId) {
        perfilAplicacionJS.usuarioId = usuarioId;
        jQuery('#perfilAplicacionform_usuarioId').val(usuarioId);
    },
    /*
     * Getter de la variable AplicacionId
     **/
    getUsuarioId: function () {
        return perfilAplicacionJS.usuarioId;
    },
     /*
     * Setter de la variable PerfilId
     **/
    setOrganizacionId: function (organizacionId) {
        perfilAplicacionJS.organizacionId = organizacionId;
        jQuery('#perfilAplicacionform_organizacionId').val(organizacionId);
    },
    /*
     * Getter de la variable AplicacionId
     **/
    getOrganizacionId: function () {
        return perfilAplicacionJS.organizacionId;
    },
        /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var obj = Object.assign({}, utilObject.buildObject('#perfilAplicacionform', new PerfilAplicacionDWR()));
        obj.aplicacionId = perfilAplicacionJS.getAplicacionId();
        obj.seguridadPerfilId = perfilAplicacionJS.getPerfilId();
        var listaIds = [obj];
        PerfilAplicacionDWR.removePerfilAplicacion(listaIds,perfilAplicacionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        perfilAplicacionJS.nuevo=true;
        perfilAplicacionJS.clean();
        perfilAplicacionJS.reloadGrid();
    },
    findPerfilAplicacionById: function (aplicacionId, seguridadPerfilId) {
        perfilAplicacionJS.setNuevoRegistro(false);
        var obj = utilObject.buildObject('#perfilAplicacionform', new PerfilAplicacionDWR());
        obj.aplicacionId = aplicacionId;
        obj.seguridadPerfilId = seguridadPerfilId;
        PerfilAplicacionDWR.getPerfilAplicacionByIntProperty(obj, perfilAplicacionJS.findAplicacionPerfilByIdCallback);
    },
    /*
     * Callback de la función findParametroById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findAplicacionPerfilByIdCallback: function (data) {
        perfilAplicacionJS.perfilAplicacion=data[0];
        utilForm.populate('#perfilAplicacionform', data[0]);
        perfilAplicacionJS.getPerfiles(data[0].seguridadPerfilId);
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (aplicacionId, seguridadPerfilId) {
        perfilAplicacionJS.setAplicacionId(aplicacionId);
        perfilAplicacionJS.setPerfilId(seguridadPerfilId);
        utilDialog.showDialog('#d-removePerfilAplicacion');
    },
    getPerfiles: function(perfilId){
        utilEffect.showProgressBar();
        var aplicacionId = $("#perfilAplicacionform_aplicacionId").val();
        PerfilAplicacionDWR.getPerfiles(aplicacionId, function(data){
         var options = $("#perfilAplicacionform_seguridadPerfilId");
         options.empty();
         options.append($("<option />").val("-1").text("--Seleccione--"));
         $.each(data, function() {
             options.append($("<option />").val(this.seguridadPerfilId).text(this.nombre));
         });
         if(perfilId!=null){
          $("#perfilAplicacionform_seguridadPerfilId").val(perfilId);
         }
            utilEffect.hideProgressBar();
        });
    }
    
};

// Validaciones de perfilAplicacionform
validanguage.el.perfilAplicacionform_seguridadPerfilId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.perfilAplicacionform_aplicacionId= {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.perfilAplicacionform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.perfilAplicacionform_usuarioId = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};