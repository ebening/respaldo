$(function(){
    // Grid catalogoParametroLenguaje embebido
    if (jQuery('#gridVisibleCatalogoParametroLenguaje').val() == 'true') {
        jQuery('#catalogoParametroLenguajeGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
            	jQuery('#catalogoParametroLenguaje_catalogoParametroLenguajeIdText').html(),
                jQuery('#catalogoParametroLenguaje_organizacionIdText').html(),
                jQuery('#catalogoparametrolenguaje_catalogoparametroid').html(),
                jQuery('#catalogoParametroLenguaje_lenguajeIdText').html(),
                jQuery('#catalogoParametroLenguaje_lenguajeIdText').html(),
                jQuery('#catalogoParametroLenguaje_etiquetaText').html(),
                jQuery('#catalogoParametroLenguaje_estatusIdText').html(),
                jQuery('#catalogoParametroLenguaje_eliminadoIdText').html(),
                jQuery('#catalogoParametroLenguaje_creacionFechaText').html(),
                jQuery('#catalogoParametroLenguaje_creacionUsuarioText').html(),
                jQuery('#catalogoParametroLenguaje_modificacionFechaText').html(),
                jQuery('#catalogoParametroLenguaje_modificacionUsuarioText').html(),
                '', '', ''],
            colModel: [{
                    name: 'catalogoParametroLenguajePK.catalogoParametroLenguajeId',
                    index: 'catalogoParametroLenguajePK.catalogoParametroLenguajeId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'catalogoParametroLenguajePK.organizacionId',
                    index: 'catalogoParametroLenguajePK.organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'catalogoParametroId',
                    index: 'catalogoParametroId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'lenguajeId',
                    index: 'lenguajeId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'lenguajeNombre',
                    index: 'lenguajeNombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false

                }, {
                    name: 'etiqueta',
                    index: 'etiqueta',
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
                    search: false
                }, {
                    name: 'usuarioModificacion',
                    index: 'usuarioModificacion',
                    align: 'center',
                    resizable: false,
                    width: 55,
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
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#catalogoParametroLenguajeGridPagerId'),
            caption: jQuery('#key_catalogoparametrolenguaje_title').val()
        });
        jQuery('#catalogoParametroLenguajeGridPagerId').hide();
        jQuery('#catalogoParametroLenguajeGrid').setGridWidth(
                jQuery("#gbox_" + "catalogoParametroLenguajeGrid").closest(".grid-container").width() - 5);
    };
	
});

var catalogoParametroLenguajeJS = {
    isDesglose: false,
    catalogoParametroLenguaje: null,
    filaId: null,
    saving: false,
    cacheDWR: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#catalogoParametroLenguajeform');
        jQuery('#catalogoParametroLenguajeform_catalogoParametroLenguajePK_catalogoParametroLenguajeId').val(0);
        catalogoParametroLenguajeJS.catalogoParametroLenguaje = null;
        catalogoParametroLenguajeJS.isDesglose = false;
        jQuery('form[name="catalogoParametroLenguajeform"]').addClass("base-desglose");
    },
    /**
     * Limpia el formulario y el grid de catalogoParametroLenguaje, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        catalogoParametroLenguajeJS.clean();
        jQuery('#catalogoParametroLenguajeGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de catalogoParametroLenguaje antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        
        var validation = validanguage.validateForm('catalogoParametroLenguajeform');
        if (validation.result) {
            catalogoParametroLenguajeJS.agregar();
            catalogoParametroLenguajeJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * catalogoParametroLenguaje.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var catalogoParametroLenguaje = utilObject.buildObject('#catalogoParametroLenguajeform', new CatalogoParametroLenguajeDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#catalogoParametroLenguajeform_catalogoParametroLenguajePK_catalogoParametroLenguajeId').val() == 0) {
            catalogoParametroLenguajeJS.agregarNuevo(catalogoParametroLenguaje);
        } else {
            catalogoParametroLenguajeJS.agregarEditado(catalogoParametroLenguaje);
        }
    },
    /* Agrega un registro editado al grid de catalogoParametroLenguaje */
    agregarEditado: function (object) {
        jQuery('#catalogoParametroLenguajeGrid').delRowData(object.catalogoParametroLenguajePK.catalogoParametroLenguajeId);
        catalogoParametroLenguajeJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de catalogoParametroLenguaje, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto CatalogoParametroLenguaje con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        //
        // Activo
        var activo_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var activo_title = 'Activo';
        var activoCatalogoParametroLenguaje_onclick = 'catalogoParametroLenguajeJS.setEstatus( ' + object.catalogoParametroLenguajePK.catalogoParametroLenguajeId + ',' + object.catalogoParametroId + ',' + object.catalogoParametroLenguajePK.organizacionId + ', 2 )';
        // Inactivo
        var inactivo_css = 'ui-icon ui-icon-radio-off ui-icon-center';
        var inactivo_title = 'Inactivo';
        var inactivoCatalogoParametroLenguaje_onclick = 'catalogoParametroLenguajeJS.setEstatus( ' + object.catalogoParametroLenguajePK.catalogoParametroLenguajeId + ',' + object.catalogoParametroId + ',' + object.catalogoParametroLenguajePK.organizacionId + ', 1 )';
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'catalogoParametroLenguajeJS.findById(' + object.catalogoParametroLenguajePK.catalogoParametroLenguajeId + ',' + object.catalogoParametroId + ',' + object.catalogoParametroLenguajePK.organizacionId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'catalogoParametroLenguajeJS.prepareToRemove(' + object.catalogoParametroLenguajePK.catalogoParametroLenguajeId + ')';

        object.EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
        if (object.estatusId == 'Activo' || object.estatusId == 1) {
            object.StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogoParametroLenguaje_onclick + "'></span>";
        } else {
            object.StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogoParametroLenguaje_onclick + "'></span>";
        }
        object.lenguajeNombre = $('#catalogoParametroLenguajeform_lenguajeId option[value="'+object.lenguajeId+'"]').text();
        // Limpiar campos si es nuevo
        if (object.catalogoParametroLenguajePK.catalogoParametroLenguajeId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#catalogoParametroLenguajeform', object);
            object.EditCatalogo = "";
            object.StateCatalogo = "";
        }
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (utilString.containsIgnoreCase(propiedad, 'fecha') ||
                    utilString.containsIgnoreCase(propiedad, 'date')) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#catalogoParametroLenguajeGrid').addRowData(object.catalogoParametroLenguajePK.catalogoParametroLenguajeId, object);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * catalogoParametroLenguaje. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     */
    /* Función llamada al presionar el botón Editar en el registro de catalogoParametroLenguaje*/
    findById: function (id, catalogoParametroId, organizacionId) {
        utilEffect.showProgressBar();
        CatalogoParametroLenguajeDWR.findById({catalogoParametroLenguajeId: id, organizacionId: organizacionId}, catalogoParametroLenguajeJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        catalogoParametroLenguajeJS.clean();
        catalogoParametroLenguajeJS.catalogoParametroLenguaje = data;
        utilForm.populate('#catalogoParametroLenguajeform', data);
        catalogoParametroLenguajeJS.isDesglose = true;
        jQuery('form[name="catalogoParametroLenguajeform"]').removeClass("base-desglose");
        utilEffect.hideProgressBar();
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * catalogoParametroLenguaje.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        catalogoParametroLenguajeJS.filaId = filaId;
        if(filaId=="0"){
        	catalogoParametroLenguajeJS.remove();
        }else{
        	utilDialog.showDialog('#d-confirma-eliminar-catalogoparametrolenguaje');
        }
    },
    /** 
     * Función que elimina una fila del grid catalogoparametrolenguaje, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * catalogoparametrolenguajeJS.filaId.
     */
    remove: function () {
        jQuery('#catalogoParametroLenguajeGrid').delRowData(catalogoParametroLenguajeJS.filaId);
        catalogoParametroLenguajeJS.clean();
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param catalogoId, es el id de catalogo.
     */

    save: function (catalogoParametro) {
    	catalogoParametroLenguajeJS.saving = true;
        var objectoDesglozado = null;
        /*
        if (catalogoParametroLenguajeJS.isDesglose) {
            jQuery('form[name="catalogoParametroLenguajeform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                	objectoDesglozado = utilObject.buildObject('#' +jQuery(this).attr('id'), new CatalogoParametroLenguajeDWR());
                }
            });
        }
        */
        var dwrObjectList = utilObject.buildDWRObjectListByGridId('#catalogoParametroLenguajeGrid', new CatalogoParametroLenguajeDWR());
        for (var i = 0; i < dwrObjectList.length; i++) {
        	if(objectoDesglozado!=null && typeof dwrObjectList[i].catalogoParametroLenguajePK!='undefined'
        			&& objectoDesglozado.catalogoParametroLenguajePK.catalogoParametroLenguajeId==dwrObjectList[i].catalogoParametroLenguajePK.catalogoParametroLenguajeId){
        		dwrObjectList[i] = objectoDesglozado;
        	}
            dwrObjectList[i].catalogoParametroId = catalogoParametro.catalogoParametroPK.catalogoParametroId;
            if(typeof dwrObjectList[i].catalogoParametroLenguajePK=='undefined'){
            	dwrObjectList[i].catalogoParametroLenguajePK = {};
            }
            dwrObjectList[i].catalogoParametroLenguajePK.organizacionId = catalogoParametro.catalogoParametroPK.organizacionId;
            var cf = dwrObjectList[i].creacionFecha;
            dwrObjectList[i].modificacionFecha = null;
            if(typeof cf!='undefined'){
            	if(cf instanceof Date) dwrObjectList[i].creacionFecha = cf.getTime();
            	else if(typeof cf=='string'){
                	var fecha = new Date(jQuery.datepicker.parseDate(getDateFormat(cf),cf));
                	dwrObjectList[i].creacionFecha = fecha.getTime();
            	}
            }
        }
        if(dwrObjectList.length>0){
        	CatalogoParametroLenguajeDWR.saveCatalogoParametroLenguaje(catalogoParametro.catalogoParametroPK.catalogoParametroId, catalogoParametro.catalogoParametroPK.organizacionId, dwrObjectList, catalogoParametroLenguajeJS.saveCallback);
        }else{
        	catalogoParametroLenguajeJS.reset();
        	catalogoParametroLenguajeJS.saving = false;
        	utilEffect.hideProgressBar();
            utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
            jQuery('.lenguaje_area').hide();
        }
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function () {
    	catalogoParametroLenguajeJS.reset();
    	catalogoParametroLenguajeJS.saving = false;
    	utilEffect.hideProgressBar();
        utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
        jQuery('.lenguaje_area').hide();
    },
    /* Función que obtiene catalogoParametroLenguaje */
    findCatalogoParametroBy: function (catalogoParametroId, organizacionId) {
    	catalogoParametroLenguajeJS.cacheDWR = {catalogoParametroId:catalogoParametroId, organizacionId:organizacionId};
        CatalogoParametroLenguajeDWR.findCatalogoParametroBy(catalogoParametroId, organizacionId, catalogoParametroLenguajeJS.findCatalogoParametroByCallback);
    },
    /**
     * Callback de la función findCatalogoParametroLenguajeByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos CatalogoParametroLenguaje que serán
     * insertados en el grid
     */
    findCatalogoParametroByCallback: function (data) {
        catalogoParametroLenguajeJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                catalogoParametroLenguajeJS.agregarNuevo(data[ i ]);
            }
        }
        utilEffect.hideProgressBar();
        utilGrid.autoWidthGrid();
    },
    estatusId: 0,
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, catalogoParametroId, organizacionId, estatusId) {
        utilEffect.showProgressBar();
        var listaCatalogoParametrosLenguaje = [{catalogoParametroLenguajeId: id, organizacionId: organizacionId}];
        CatalogoParametroLenguajeDWR.setEstatus(estatusId, listaCatalogoParametrosLenguaje, catalogoParametroLenguajeJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = catalogoParametroLenguajeJS.estatusId;
        var listaCatalogoParametroLenguaje = utilGrid.gridGetSelectedRows('#catalogoParametroLenguajeGrid');
        CatalogoParametroLenguajeDWR.setEstatus(estatusId, listaCatalogoParametroLenguaje, catalogoParametroLenguajeJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        utilEffect.hideProgressBar();
        catalogoParametroLenguajeJS.reloadGrid();
    },
    reloadGrid: function () {
        if (catalogoParametroLenguajeJS.cacheDWR != null) {
        	catalogoParametroLenguajeJS.findCatalogoParametroBy(catalogoParametroLenguajeJS.cacheDWR.catalogoParametroId, catalogoParametroLenguajeJS.cacheDWR.organizacionId);
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoParametroLenguajeGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogoParametroLenguaje');
        } else {
            catalogoParametroLenguajeJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusCatalogoParametro' );
            catalogoParametroLenguajeJS.setEstatusSeleccion();
        }
    },
    deleteMe: function () {
        // do nothing
    }
};

ProfileNotRepeatedLanguaje = {
	name: function(){
		var data = jQuery('#catalogoParametroLenguajeGrid').getRowData();
		for(var i=0;i<data.length;i++){
            if(data[i].lenguajeId==this.value){
            	return false;
            }
        };
		return true;
	},
    errorMsg: "Este valor ya existe",
    onerror: utilEffect.showValidationTooltip,
    onsuccess: utilEffect.hideValidationTooltip
}