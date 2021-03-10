$(function(){
	// Grid catalogoParametro embebido
    if (jQuery('#gridVisibleCatalogoParametro').val() == 'true') {
        jQuery('#catalogoParametroGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: ['', '', '','',
                jQuery('#catalogoParametro_organizacionIdText').html(),
                jQuery('#catalogoParametro_catalogoIdText').html(),
                jQuery('#catalogoParametro_claveText').html(),
                jQuery('#catalogoParametro_valorText').html(),
                jQuery('#catalogoParametro_ordenText').html(),
                jQuery('#catalogoParametro_visibleText').html(),
                jQuery('#catalogoParametro_estatusIdText').html(),
                jQuery('#catalogoParametro_eliminadoIdText').html(),
                jQuery('#catalogoParametro_creacionFechaText').html(),
                jQuery('#catalogoParametro_creacionUsuarioText').html(),
                jQuery('#catalogoParametro_modificacionFechaText').html(),
                jQuery('#catalogoParametro_modificacionUsuarioText').html(),
                jQuery('#catalogoParametro_imagen').html(), '', '',''],
            colModel: [{
                    name: 'catalogoParametroPK.catalogoParametroId',
                    index: 'catalogoParametroPK.catalogoParametroId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'catalogoParametroPK.organizacionId',
                    index: 'catalogoParametroPK.organizacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'descripcion',
                    index: 'descripcion',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'imagen',
                    index: 'imagen',
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
                    name: 'catalogoId',
                    index: 'catalogoId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false

                }, {
                    name: 'clave',
                    index: 'clave',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'valor',
                    index: 'valor',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'orden',
                    index: 'orden',
                    align: 'center',
                    width: 30,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'visible',
                    index: 'visible',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    search: false,
                    resizable: false
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
                    width: 65,
                    hidden: false,
                    search: false
                }, {
                    name: 'linkimagen',
                    index: 'linkimagen',
                    align: 'center',
                    resizable: false,
                    width: 40,
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
            pager: jQuery('#catalogoParametroGridPagerId'),
            caption: jQuery('#key_catalogoparametro_title').val()
        });
        jQuery('#catalogoParametroGridPagerId').hide();
        jQuery('#catalogoParametroGrid').setGridWidth(jQuery("#gbox_" + "catalogoParametroGrid").closest(".grid-container").width() - 5);
    }
    
	var mb = 1024 * 1024;
	var maxSize = 2; //2 MB

	$('#fileupload').fileupload({
        dataType: 'json',
        maxFileSize: maxSize*mb,
        done: function (e, data) {
            setImageValue(data.result)
        }
    }).bind('fileuploadadd', function (e, data) {
    	var msg = "";
    	$(data.files).each(function () {
    		var ext = this.name.split('.').pop().toLowerCase();
    		if(ext!='gif' && ext!='jpg' && ext!='jpeg' && ext!='png'){
    			msg = "La imagen seleccionada no es v\u00E1lida";
    		}
    		if(this.size>(maxSize*mb)){
    			msg = "El tama\u00F1o m\u00E1ximo permitido es de "+maxSize+" MB";
    		}
    	});
    	if(msg!=''){
    		alert(msg);
    		return false;
    	}
    });
});

function setImageValue(data){
	if(data.error){
		$('#catalogoParametroform_imagen').val("");
    	alert(data.error);
	}else{
		$('#catalogoParametroform_imagen').val(data.fileResult);
		$('#fileupload').hide();
		fillImageDescription(data.fileName);
	}
}

function fillImageDescription(fileName){
	var html="Nombre: <b>"+fileName+"</b><br><a href='javascript:removeImageCP()'>Eliminar</a>";
	$("#filedesc").html(html);
}

function removeImageCP(reset){
	$('#fileupload').show();
	$("#filedesc").html("");
	var imagenAnterior = $('#catalogoParametroform_imagen').val();
	if(imagenAnterior.indexOf('O-')>-1){
		$('#catalogoParametroform_imagen').val(imagenAnterior+".del");
	}else{
		$('#catalogoParametroform_imagen').val("");
	}
	if(reset){
		$('#catalogoParametroform_imagen').val("");
	}
}
function descargar(fileName){
	document.location.href=$("#app_context").val()+"admon/download.action?file_path="+fileName;
}

var catalogoParametroJS = {
    isDesglose: false,
    filaId: null,
    catalogoParametro: null,
    catalogoParametroDesglozado: null,
    previousSearch: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#catalogoParametroform');
        jQuery('#catalogoParametroform_catalogoParametroPK_catalogoParametroId').val(0);
        catalogoParametroJS.catalogoParametro = null;
        catalogoParametroJS.isDesglose = false;
        jQuery('form[name="catalogoParametroform"]').addClass("base-desglose");
        removeImageCP(true);
    },
    reloadGrid: function () {
        if (catalogoParametroJS.previousSearch != null) {
        	catalogoParametroJS.findCatalogoParametroBy(catalogoParametroJS.previousSearch.catalogoId, catalogoParametroJS.previousSearch.organizacionId);
        }
    },
    /**
     * Limpia el formulario y el grid de catalogoParametro, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        catalogoParametroJS.clean();
        jQuery('#catalogoParametroGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de catalogoParametro antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('catalogoParametroform');
        if (validation.result) {
            catalogoParametroJS.agregar();
            catalogoParametroJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * catalogoParametro.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var catalogoParametro = utilObject.buildObject('#catalogoParametroform', new CatalogoParametroDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#catalogoParametroform_catalogoParametroPK_catalogoParametroId').val() == "0") {
            catalogoParametroJS.agregarNuevo(catalogoParametro);
        } else {
            catalogoParametroJS.agregarEditado(catalogoParametro);
        }
    },
    /* Agrega un registro editado al grid de catalogoParametro */
    agregarEditado: function (object) {
        jQuery('#catalogoParametroGrid').delRowData(object.catalogoParametroPK.catalogoParametroId);
        catalogoParametroJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de catalogoParametro, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto CatalogoParametro con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        // Activo
        var activo_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var activo_title = 'Activo';
        var activoCatalogoParametro_onclick = 'catalogoParametroJS.setEstatus( ' + object.catalogoParametroPK.catalogoParametroId + ',' + object.catalogoId + ',' + object.catalogoParametroPK.organizacionId + ', 2 )';
        // Inactivo
        var inactivo_css = 'ui-icon ui-icon-radio-off ui-icon-center';
        var inactivo_title = 'Inactivo';
        var inactivoCatalogoParametro_onclick = 'catalogoParametroJS.setEstatus( ' + object.catalogoParametroPK.catalogoParametroId + ',' + object.catalogoId + ',' + object.catalogoParametroPK.organizacionId + ', 1 )';
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'catalogoParametroJS.findById(' + object.catalogoParametroPK.catalogoParametroId + ',' + object.catalogoId + ',' + object.catalogoParametroPK.organizacionId+')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'catalogoParametroJS.prepareToRemove(' + object.catalogoParametroPK.catalogoParametroId + ')';
        // Visible
        var visible_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var visible_title = 'Visible';
        var visibleCatalogoParametro_onclick = 'catalogoParametroJS.setVisible( ' + object.catalogoParametroPK.catalogoParametroId + ',' + object.catalogoId + ',' + object.catalogoParametroPK.organizacionId + ', 0 )';
        // No Visible
        var novisible_css = 'ui-icon ui-icon-radio-off ui-icon-center';
        var novisible_title = 'No Visible';
        var novisibleCatalogoParametro_onclick = 'catalogoParametroJS.setVisible( ' + object.catalogoParametroPK.catalogoParametroId + ',' + object.catalogoId + ',' + object.catalogoParametroPK.organizacionId + ', 1 )';

        object.EditCatalogo = "<span class = '" + editar_css + "' title = '" + editar_title + "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.RemoveCatalogo = "<span class = '" + eliminar_css + "' title = '" + eliminar_title + "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
        if (object.estatusId == 'Activo' || object.estatusId == 1) {
            object.StateCatalogo = "<span class = '" + activo_css + "' title = '" + activo_title + "' style = 'cursor:pointer' onclick = '" + activoCatalogoParametro_onclick + "'></span>";
        } else {
            object.StateCatalogo = "<span class = '" + inactivo_css + "' title = '" + inactivo_title + "' style = 'cursor:pointer' onclick = '" + inactivoCatalogoParametro_onclick + "'></span>";
        }
        if (object.visible == true || object.visible == 1) {
            object.visible = "<span class = '" + visible_css + "' title = '" + visible_title + "' style = 'cursor:pointer' onclick = '" + visibleCatalogoParametro_onclick + "'></span>";
        } else {
            object.visible = "<span class = '" + novisible_css + "' title = '" + novisible_title + "' style = 'cursor:pointer' onclick = '" + novisibleCatalogoParametro_onclick + "'></span>";
        }
        if(typeof object.imagen!='undefined' && object.imagen!=null && object.imagen!='' && object.imagen.indexOf('.del')==-1){
        	object.linkimagen="<span title='Descargar' style='cursor:pointer' onclick='descargar(\""+object.imagen+"\")' class=\"ui-icon ui-icon-search ui-icon-center\"></span>";
        }
        // Limpiar campos si es nuevo
        if (object.catalogoParametroPK.catalogoParametroId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#catalogoParametroform', object);
            object.EditCatalogo = "";
            object.visible = "";
            object.StateCatalogo = "";
            object.linkimagen="";
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
        jQuery('#catalogoParametroGrid').addRowData(object.catalogoParametroPK.catalogoParametroId, object);
        catalogoParametroLenguajeJS.reset();
    	jQuery('.lenguaje_area').hide();
    },
    
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * catalogoParametro. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     */
    /* Función llamada al presionar el botón Editar en el registro de catalogoParametro*/
    findById: function (id, catalogoId, organizacionId) {
        utilEffect.showProgressBar();
        CatalogoParametroDWR.findById({catalogoParametroId: id, organizacionId: organizacionId}, catalogoParametroJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
    	jQuery('.lenguaje_area').show();
        catalogoParametroJS.clean();
        catalogoParametroJS.catalogoParametro = data;
        utilForm.populate('#catalogoParametroform', data);
        jQuery('#catalogoParametroform_visible')[0].checked = data.visible;
        jQuery('#catalogoParametroform_visible').val(data.visible?"1":"0");
        catalogoParametroJS.isDesglose = true;
        if(typeof data.imagen!='undefined' && data.imagen!=null){
    		$('#fileupload').hide();
        	fillImageDescription(data.imagen);
        }else{
        	removeImageCP();
        }
        jQuery('form[name="catalogoParametroform"]').removeClass("base-desglose");
        catalogoParametroLenguajeJS.findCatalogoParametroBy(jQuery('#catalogoParametroform_catalogoParametroPK_catalogoParametroId').val(), jQuery('#catalogoParametroform_catalogoParametroPK_organizacionId').val());
        utilGrid.autoWidthGrid();
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * catalogoParametro.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        catalogoParametroJS.filaId = filaId;
        if(filaId=="0"){
        	catalogoParametroJS.remove();
        }else{
            utilDialog.showDialog('#d-confirma-eliminar-catalogoparametro');
        }
    },
    /** 
     * Función que elimina una fila del grid catalogoparametro, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * catalogoparametroJS.filaId.
     */
    remove: function () {
        jQuery('#catalogoParametroGrid').delRowData(catalogoParametroJS.filaId);
        catalogoParametroJS.clean();
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param catalogoId, es el id de catalogo.
     */
    save: function (catalogo) {
    	var objDesglozado;
    	catalogoParametroJS.saving=true;
        // Funcionalidad desglose
        if (catalogoParametroJS.isDesglose) {
            jQuery('form[name="catalogoParametroform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') === false) {
                    // Construir el objeto
                    objDesglozado = utilObject.buildObject('#' + jQuery(this).attr('id'), new CatalogoParametroDWR());
                    objDesglozado.catalogoId = catalogo.catalogoPK.catalogoId;
                    objDesglozado.catalogoParametroPK.organizacionId = catalogo.catalogoPK.organizacionId;
                    if(objDesglozado.visible==1){
                    	objDesglozado.visible=true;
                    }else{
                    	objDesglozado.visible=false;
                    }
                    catalogoParametroJS.catalogoParametroDesglozado=objDesglozado;
                }
            });
        } 
        var dwrObjectList = utilObject.buildDWRObjectListByGridId('#catalogoParametroGrid', new CatalogoParametroDWR());
        for (var i = 0; i < dwrObjectList.length; i++) {
        	if(objDesglozado!=null && objDesglozado.catalogoParametroPK.catalogoParametroId==dwrObjectList[i].catalogoParametroPK.catalogoParametroId){
        		dwrObjectList[i]=objDesglozado;
        	}
            dwrObjectList[i].catalogoId = catalogo.catalogoPK.catalogoId;
            dwrObjectList[i].catalogoParametroPK.organizacionId = catalogo.catalogoPK.organizacionId;
            var cf = dwrObjectList[i].creacionFecha;
            dwrObjectList[i].modificacionFecha = null;
            if(typeof cf!='undefined'){
            	if(cf instanceof Date) dwrObjectList[i].creacionFecha = cf.getTime();
            	else if(typeof cf=='string'){
                	var fecha = new Date(jQuery.datepicker.parseDate(getDateFormat(cf),cf));
                	dwrObjectList[i].creacionFecha = fecha.getTime();
            	}
            }
            if(typeof dwrObjectList[i].visible=='string' && dwrObjectList[i].visible.indexOf("No Visible")==-1){
            	dwrObjectList[i].visible=true;
            }
        }
        
        if(catalogo.catalogoPK!=null && catalogo.catalogoPK.catalogoId!=null && catalogo.catalogoPK.organizacionId!=null){
            CatalogoParametroDWR.saveCatalogoParametro(catalogo.catalogoPK.catalogoId, catalogo.catalogoPK.organizacionId, dwrObjectList,catalogoParametroJS.saveCallback);
        }else{
        	catalogoParametroJS.saving=false;
        }
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function (savedList) {
    	catalogoParametroJS.saving=false;
    	jQuery('.parametro_area').hide();
        // Funcionalidad desglose
        if (catalogoParametroJS.catalogoParametroDesglozado!=null) {
            catalogoParametroLenguajeJS.save(catalogoParametroJS.catalogoParametroDesglozado);
            catalogoParametroJS.catalogoParametroDesglozado=null;
        } else {
            utilEffect.hideProgressBar();
            utilEffect.showToast('success', jQuery('#msgGuardadoOk').val());
        }
        catalogoParametroJS.reset();
    },
    /* Función que obtiene catalogoParametro */
    findCatalogoParametroBy: function (catalogoId, organizacionId) {
        catalogoParametroJS.previousSearch = {catalogoId: catalogoId, organizacionId: organizacionId};
        CatalogoParametroDWR.findCatalogoParametroBy(catalogoId, organizacionId, catalogoParametroJS.findCatalogoParametroByCallback);
    },
    /**
     * Callback de la función findCatalogoParametroByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos CatalogoParametro que serán
     * insertados en el grid
     */
    findCatalogoParametroByCallback: function (data) {
        catalogoParametroJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                catalogoParametroJS.agregarNuevo(data[i]);
            }
        }
        catalogoParametroLenguajeJS.reset();
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatus: function (id, catalogoId, organizacionId, estatusId) {
        utilEffect.showProgressBar();
        var listaCatalogoParametros = [{catalogoParametroId: id, organizacionId: organizacionId}];
        CatalogoParametroDWR.setEstatus(estatusId, listaCatalogoParametros, catalogoParametroJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = catalogoParametroJS.estatusId;
        var listaCatalogoParametros = utilGrid.gridGetSelectedRows('#catalogoParametroGrid');
        CatalogoParametroDWR.setEstatus(estatusId, listaCatalogoParametros, catalogoParametroJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        catalogoParametroJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoParametroGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogoParametro');
        } else {
            catalogoParametroJS.estatusId = estatusId;
            // utilDialog.showDialog( '#d-confirmSetEstatusCatalogoParametro' );
            catalogoParametroJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setVisible</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el visible
     * @param visible (int) Es el nuevo visible
     */
    setVisible: function (id, catalogoId, organizacionId, visible) {
        utilEffect.showProgressBar();
        var listaCatalogoParametros = [{catalogoParametroId: id, organizacionId: organizacionId}];
        CatalogoParametroDWR.setVisible(visible, listaCatalogoParametros, catalogoParametroJS.setVisibleCallback);
    },
    /*
     * Función que cambia el Visible de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setVisibleSeleccion: function () {
        utilEffect.showProgressBar();
        var visible = catalogoParametroJS.visible;
        var listaCatalogoParametros = utilGrid.gridGetSelectedRows('#catalogoParametroGrid');
        CatalogoParametroDWR.setVisible(visible, listaCatalogoParametros, catalogoParametroJS.setVisibleCallback);
    },
    /*
     * Callback de la función setVisible(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setVisibleCallback: function () {
        catalogoParametroJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetVisible: function (visible) {
        var seleccion = utilGrid.gridGetSelectedRows('#catalogoParametroGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedCatalogoParametro');
        } else {
            catalogoParametroJS.visible = visible;
            // utilDialog.showDialog( '#d-confirmSetVisibleCatalogoParametro' );
            catalogoParametroJS.setVisibleSeleccion();
        }
    }
};

