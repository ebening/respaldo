var parametroJS = {
    LENGUAJE_DEFAULT: 1,
        /**
     * Limpia el formulario.
     */
    clean: function () {
        jQuery('#parametroform_parametroId').val(0);
        jQuery('#parametroform_lenguajeId').val(parametroJS.LENGUAJE_DEFAULT);
        jQuery('#parametroform_nombre').val(null);
    },
    /**
     * Limpia el formulario y el grid de organizacionCredencial, usado cuando el
     * usuario presiona el bot&oacute;n Nuevo.
     */
    reset: function () {
        parametroJS.clean();
        jQuery('#parametroGrid').clearGridData();
    },
    setTableName: function(tableName){
        parametroJS.tableName = tableName;
    },
    getTableName: function(){
        return parametroJS.tableName;
    },
    setPropertyName: function(propertyName){
        parametroJS.propertyName = propertyName;
    },
    getPropertyName: function(){
        return parametroJS.propertyName;
    },
    cacheDWR: null,
    tableName:null,
    propertyName:null,
    parametro:null,
    
    inicializarGrid: function () {
        // Bot&oacute;n AGREGAR (formulario aplicacionParametro)
        jQuery('#btnAddParametro').click(function () {
            parametroJS.prepareToAgregar();
        });
        // Botón : NUEVO PARAMETRO
        jQuery('#btnNewParametro').click(function () {
            parametroJS.clean();
        }).customButtonEffect('#btnNewParametro');
    
        jQuery('#parametroGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#parametro_parametroIdText').html(),
                jQuery('#parametro_fkIdText').html(),
                jQuery('#parametro_lenguajeText').html(),
                jQuery('#parametro_nombreText').html(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'parametroId',
                    index: 'parametroId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'fkId',
                    index: 'fkId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                },{
                    name: 'lenguajeId',
                    index: 'lenguajeId',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'EditParametro',
                    index: 'EditParametro',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'RemoveParametro',
                    index: 'RemoveParametro',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 180,
            toolbar: false,
            hidegrid: true,
            multiselect: false,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#parametroGridPagerId'),
            caption: jQuery('#key_parametro_title').val()
    });

        // jQuery UI Dialogs
       utilDialog.setStandardDialog('#d-removeParametro', jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@parametroJS.remove()', 'Cancelar@']);
       utilDialog.setStandardDialog('#d-registroDuplicadoParametro', jQuery('#msgAlertaText').val(), jQuery('#msgYaExisteRegistroParametro').val(), ['Aceptar@']);
       utilDialog.setStandardDialog('#d-NoremoveParametroLenguaje', jQuery('#msgAlertaText').val(), jQuery('#msgNoremoveParametroLenguaje').val(), ['Aceptar@parametroJS.remove()']);
        jQuery('#parametroGrid').setGridWidth(
               jQuery("#gbox_" + "parametroGrid").closest(".grid-container").width() - 5);

       // Inicializaciones extra para el grid
       utilGrid.setup('#parametroGrid', '#parametroGridPagerId', '#parametroGridCurrentPage', '#parametroGridOrderByColumn',
               '#parametroGridOrderByType', 'parametroJS');
               
    utilGrid.setHideElementsTable('parametroGridPagerId',['activate','search', 'export', 'delete']);
               
       parametroJS.reloadGrid();
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
        var parametro = utilObject.buildObject('#parametroform', new ParametroDWR());
        ParametroDWR.isValidoNombreParametroLenguaje(parametro,parametroJS.tableName,parametroJS.propertyName, parametroJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#parametroform_parametroId').val() != 0) {
            var anterior = parametroJS.parametro;
            var nuevo= utilObject.buildObject('#parametroform', new ParametroDWR());
            if(anterior.lenguajeId === nuevo.lenguajeId){ 
                parametroJS.doCommit();
                return;
            }else{
                utilDialog.showDialog('#d-registroDuplicadoParametro');
                return;
            }
        }
        
        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#parametroform_parametroId').val() == 0) {
            parametroJS.doCommit();
            return;
        }
        //Editando Registro
        if (isValido == true && jQuery('#parametroform_parametroId').val() != 0) {
            parametroJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoParametro');
    },
    reloadGrid: function () {
        if (parametroJS.cacheDWR != null) {
            parametroJS.findByCriteria(parametroJS.cacheDWR);
        } else {
            parametroJS.findByCriteria(new ParametroDWR());
        }
    },
    findByCriteria: function (obj) {
        parametroJS.cacheDWR = obj;
        var page = jQuery('#parametroGridCurrentPage').val();
        var rows = jQuery('#parametroGridRowsByPage').val();
        var order = jQuery('#parametroGridOrderByColumn').val();
        var orderType = jQuery('#parametroGridOrderByType').val();
        var id = parametroJS.getFkId();
        
        if(id==0) 
            return;
        else
           ParametroDWR.findByCriteriaParametroLenguaje(page, rows, order, orderType,id,parametroJS.tableName,parametroJS.propertyName,parametroJS.findByCriteriaParametroCallback);
    },
    findByCriteriaParametroCallback: function (data) {
        utilGrid.gridUpdate('#parametroGrid', '#parametroGridCurrentPage', '#parametroGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#parametroform_parametroId').val() == 0) {
            parametroJS.saveParametro();
        } else {
            parametroJS.updateParametro();
        }
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param aplicacionId, es el id de aplicacion.
     */
    saveParametro: function () {
        var parametro = utilObject.buildObject('#parametroform', new ParametroDWR());
        var listaParametros = [parametro];
         ParametroDWR.saveParametroLenguaje(listaParametros,parametroJS.tableName,parametroJS.propertyName, parametroJS.saveOrUpdateAplicacionParametroCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    updateParametro: function () {
        utilEffect.showProgressBar();
        var parametro = utilObject.buildObject('#parametroform', new ParametroDWR());

        //parametro.fkId = jQuery('#parametroform_fkId').val();
        //parametro.lenguajeId = $('#parametroform_lenguajeId').val();

        var listaParametros = [parametro];
        ParametroDWR.updateParametroLenguaje(listaParametros,parametroJS.tableName,parametroJS.propertyName, parametroJS.saveOrUpdateAplicacionParametroCallback);
    },
    /* Callback de la funci&oacute;n save(), esta funci&oacute;n es llamada por DWR al terminar
     * de realizar la operaci&oacute;n */
    saveOrUpdateAplicacionParametroCallback: function () {
        utilEffect.hideProgressBar();
        parametroJS.reloadGrid();
        eval(jQuery('#objectNameJS').val()+ '.reloadGrid()');
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());

    },
       /** 
     * Valida los campos del formulario de parametroform antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('parametroform');
        if (validation.result) {
            parametroJS.isValidoNombre();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * lenguajeParametro.
     */
    agregar: function () {
        parametroJS.doCommit();
    },
    eliminarId: 0,
    eliminarFkId:0,
    lenguajeId:0,
    fkId:0,
    /*
     * Setter de la variable eliminarId
     **/
    setEliminarId: function (eliminarId) {
        parametroJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return parametroJS.eliminarId;
    },
    setEliminarFkId: function (eliminarFkId) {
        parametroJS.eliminarFkId = eliminarFkId;
    },
    getEliminarFkId: function () {
        return parametroJS.eliminarFkId;
    },
    setLenguajeId: function (lenguajeId) {
        parametroJS.lenguajeId = lenguajeId;
    },
    getLenguajeId: function () {
        return parametroJS.lenguajeId;
    },
    setFkId: function (fkId) {
        parametroJS.fkId = fkId;
    },
    getFkId: function () {
        return parametroJS.fkId;
    },
        /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = parametroJS.getEliminarId();
        if(parametroJS.LENGUAJE_DEFAULT == parametroJS.getLenguajeId()) {
            utilEffect.hideProgressBar();
            utilDialog.showDialog('#d-NoremoveParametroLenguaje');
            return;
        }
        var listaIds = [id];
        ParametroDWR.removeParametroLenguaje(listaIds,parametroJS.tableName,parametroJS.propertyName,parametroJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        parametroJS.clean();
        parametroJS.reloadGrid();
        eval(jQuery('#objectNameJS').val()+ '.reloadGrid()');
    },
    findParametroById: function (parametroId, fkId) {
        utilEffect.showProgressBar();
        ParametroDWR.getParametroLenguajeById(parametroId,parametroJS.tableName,parametroJS.propertyName, parametroJS.findParametroByIdCallback);
    },
    /*
     * Callback de la función findParametroById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findParametroByIdCallback: function (data) {
        parametroJS.parametro=data;
        utilForm.populate('#parametroform', data);
        $('#parametroform_lenguajeId').val(data.lenguajeId);
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (parametroId, lenguajeId) {
        parametroJS.setEliminarId(parametroId);
        parametroJS.setLenguajeId(lenguajeId);
        utilDialog.showDialog('#d-removeParametro');
    }
    
};

// Validaciones de parametroform
validanguage.el.parametroform_nombre = {
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