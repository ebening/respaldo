jQuery(document).ready(function () {


    // Crear el mensaje 'Espere...'
    utilEffect.createProgressBar('Espere...');
    jQuery('#rolform_aplicacionId').change(function () {
        rolJS.obtieneModulos();
    });
    // Elementos button
    jQuery('.button').button();
     // Configuraciones adicionales
    jQuery.jstree._themes = '../css/jstreethemes/';
    jQuery('.paginas').hide();
    

    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewRol').click(function () {
        utilForm.reset('#rolform');
        jQuery('#rolform_rolId').val(0);
        jQuery('#rolform_organizacionId').val(0);
        rolJS.rol = null;
        parametroJS.reset();
        jQuery('#parametroDiv').hide();
        jQuery('#arbolDiv').hide();
    }).customButtonEffect('#btnNewRol');
    // Botón : GUARDAR
    jQuery('#btnSaveRol').click(function () {
        rolJS.prepareToSave();
    }).customButtonEffectBlue('#btnSaveRol');
    
    //Funcion para mostrar/ocultar permisos por Pantalla
    utilObject.getFuncionalidadPantalla("es","SeguridadRolAction");
    
    //Grid para mostrar lenguajes del Rol
    jQuery('#parametroDiv').hide();
    
    if (jQuery('#gridVisibleRol').val() == 'true') {
        jQuery('#rolGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Id',
                jQuery('#rol_aplicacionIdText').html(),
                jQuery('#rol_nombreText').html(),
                jQuery('#rol_descripcionText').html(),
                jQuery('#rol_modificacionFechaText').html(),
                jQuery('#rol_modificacionUsuarioText').html(),
                jQuery('#generico_activo').val(),
                jQuery('#generico_modificar').val(),
                jQuery('#generico_eliminar').val()],
            colModel: [{
                    name: 'rolId',
                    index: 'rolId',
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
            pager: jQuery('#rolGridPagerId'),
            caption: jQuery('#key_rol_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-removeRol',jQuery('#msgAlertaText').val(), jQuery('#msgDeseaEliminarText').val(), ['Aceptar@rolJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionRol',jQuery('#msgAlertaText').val(),jQuery('#msgDeseaEliminarSeleccionText').val(), ['Aceptar@rolJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetEstatusRol',jQuery('#msgAlertaText').val(), jQuery('#msgCambiarEstatusText').val(), ['Aceptar@rolJS.setEstatusSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedRol', jQuery('#msgError').val(),jQuery('#msgSeleccioneUnoOMas').val() , ['Aceptar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoRol', 'Advertencia', jQuery('#msgYaExisteRegistro').val(), ['Aceptar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#rolColumnasExportar", '#rolGrid');
        utilDialog.setCustomDialogSetEstatus('#rolSetEstatusActivoInactivo',
                'rolJS.prepareToSetEstatus(1)', 'rolJS.prepareToSetEstatus(0)');

        // Inicializaciones extra para el grid
        utilGrid.setup('#rolGrid', '#rolGridPagerId', '#rolGridCurrentPage', '#rolGridOrderByColumn',
                '#rolGridOrderByType', 'rolJS');

        // Crear el modal de búsqueda
        utilSearch.buildSearch('#d-searchRol');
        utilGrid.setHideElementsTable('rolGridPagerId',['activate','export']);
        // Recargar el widget jqGrid
        rolJS.reloadGrid();

    }
    // Grid configuracionParametro embebido
    parametroJS.inicializarGrid();
    // Limpiar la página
    jQuery('#btnNewRol').click();
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
    if (jQuery('#gridIndividualModeRol').val() == 'true') {
        jQuery('#btnNewRol').hide();
        rolJS.findFirst();
    }
    jQuery( '#rolGrid' ).setGridWidth(
    jQuery("#gbox_" + "rolGrid").closest(".grid-container").width() * .95 );
    
    parametroJS.setTableName(jQuery('#key_tableName').val());
    parametroJS.setPropertyName(jQuery('#key_propertyName').val());
    parametroJS.LENGUAJE_DEFAULT = $('#rolform_lenguajeId').val();
});

var rolJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    rol: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var rol = utilObject.buildObject('#rolform', new SeguridadRolDWR());
        rol.rolId = 0;
        var listaRoles = [rol];
        SeguridadRolDWR.save(listaRoles, rolJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var rol = utilObject.buildObject('#rolform', new SeguridadRolDWR());
        rol.rolId = jQuery('#rolform_rolId').val();
        var listaRoles = [rol];
        var listaFuncOper = rolJS.getChekedTree();
        SeguridadRolDWR.update(listaRoles,listaFuncOper, rolJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function () {
        // Grid Multiple
        if (jQuery('#gridIndividualModeRol').val() == 'false') {
            utilForm.reset('#rolform');
            jQuery('#rolform_rolId').val(0);
            rolJS.rol = null;
            rolJS.reloadGrid();
        }
        parametroJS.reset();
        jQuery('#parametroDiv').hide();
        jQuery('#arbolDiv').hide();
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
        var id =rolJS.getEliminarId();
        var listaIds = [id];
        SeguridadRolDWR.remove(listaIds, rolJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#rolGrid');
        SeguridadRolDWR.remove(listaIds, rolJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewRol').click();
        rolJS.reloadGrid();
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
        SeguridadRolDWR.findById(id, rolJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#rolform');
        utilForm.populate('#rolform', data);
        rolJS.rol = data;
        jQuery('#parametroDiv').show();
        jQuery('#arbolDiv').show();
        utilForm.reset('#parametroform');
        jQuery('#parametroform_fkId').val(data.rolId);
        jQuery('#parametroform_lenguajeId').val(jQuery('#rolform_lenguajeId').val());
        parametroJS.setFkId(data.rolId);
        utilGrid.autoWidthGrid();
        rolJS.obtieneModulos();
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
        rolJS.cacheDWR = obj;
        var page = jQuery('#rolGridCurrentPage').val();
        var rows = jQuery('#rolGridRowsByPage').val();
        var order = jQuery('#rolGridOrderByColumn').val();
        var orderType = jQuery('#rolGridOrderByType').val();
        var lenguajeId = jQuery('#rolform_lenguajeId').val();
        utilEffect.showProgressBar();
        SeguridadRolDWR.findByCriteria(page, rows, order, orderType, obj, lenguajeId,rolJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#rolGrid', '#rolGridCurrentPage', '#rolGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        SeguridadRolDWR.findFirst(rolJS.findByIdCallback);
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
        var nombre = jQuery('#rolform_nombre').val();
        SeguridadRolDWR.isValidoNombre(nombre, rolJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#rolform_rolId').val() != 0) {
            var anterior = rolJS.rol;
            var nuevo= utilObject.buildObject('#rolform', new SeguridadRolDWR());
            if(anterior.nombre === nuevo.nombre){ 
                rolJS.doCommit();
                return;
            }else{
                utilDialog.showDialog('#d-registroDuplicadoRol');
            }
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#rolform_rolId').val() == 0) {
            rolJS.doCommit();
            return;
        }
        //Editando Registro
        if (isValido == true && jQuery('#rolform_rolId').val() != 0) {
            rolJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoRol');
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
        SeguridadRolDWR.setEstatus(estatusId, listaObjetos, rolJS.setEstatusCallback);
    },
    /*
     * Función que cambia el Estatus de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setEstatusSeleccion: function () {
        utilEffect.showProgressBar();
        var estatusId = rolJS.estatusId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#rolGrid');
        SeguridadRolDWR.setEstatus(estatusId, listaObjetos, rolJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        rolJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        var seleccion = utilGrid.gridGetSelectedRows('#rolGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedRol');
        } else {
            rolJS.estatusId = estatusId;
            rolJS.setEstatusSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#rolGrid');
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedRol');
        } else {
            utilDialog.showDialog('#d-removeSeleccionRol');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        rolJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeRol');
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
        rolJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return rolJS.eliminarId;
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
        var validation = validanguage.validateForm('rolform');
        if (validation.result){
            rolJS.exists();
        }
    },
       /*
     * Función que valida si el registro ya existe entre los elementos eliminados
     * ya que si existe solo se le cambiará el estatus a no eliminado
     */
    exists: function () {
        var rol = utilObject.buildObject('#rolform', new SeguridadRolDWR());
        SeguridadRolDWR.exists(rol,rolJS.existsCallback);
    },
    
    existsCallback: function (data) {
        if(data==false){
            rolJS.isValidoNombre();
        }else{
            utilForm.reset('#rolform');
            jQuery('#rolform_rolId').val(0);
            rolJS.rol = null;
            rolJS.reloadGrid();
            utilEffect.hideProgressBar();
            utilEffect.showToast('success', jQuery('#msgGuardadoOk').val()); 
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#rolform_rolId').val() == 0) {
            rolJS.save();
        } else {
            rolJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (rolJS.cacheDWR != null) {
            rolJS.findByCriteria(rolJS.cacheDWR);
        } else {
            rolJS.findByCriteria(new SeguridadRolDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchRol', '#rolGrid', '#rolform', rolJS, new SeguridadRolDWR());
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
        rolJS.entity = entity;
        rolJS.headers = headers;
        rolJS.format = format;
        rolJS.reportName = jQuery('#key_rol_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new SeguridadRolDWR();
        if (rolJS.cacheDWR != null) {
            criteriaExample = rolJS.cacheDWR;
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
        SeguridadRolDWR.getReportDataTest(sortBy, sortType, criteriaExample, rolJS.exportarDatosCallback);
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
                rolJS.entity + '&headers=' + rolJS.headers +
                '&reportName=' + rolJS.reportName + '&format=' + rolJS.format +
                '&reportKey=' + reportKey;
    },
    /*
     * MÉTODOS PARA ÁRBOL
     */
    /*
     * Función que Obtiene una lista de todos los registros válidos (activos y 
     * no eliminados) de la tabla MODULOS,OPERACIONES Y FUNCIONALIDADES, en base
     * a un Id de Aplicación.
     */
    obtieneModulos: function () {
        var aplicacionId = jQuery('#rolform_aplicacionId').val();
        SeguridadRolDWR.getOpFuncByAplicacion(aplicacionId,rolJS.setTree);
    },
    nodeExists: function (node, nodeList){
		for(var i=0; i<nodeList.length; i++){
			if (nodeList[i].id === node.anidar){
				return true;
			}
		}
		return false;
    },
    buildTree: function (data,element,level,count,arreglo){
            if(count===level){
                return;
            }else{
                var array=[];
                for(var i=0; i<data.length; i++){
                        if( !rolJS.nodeExists( data[i], arreglo) ) {
                            array.push(data[i]);
                            $(element).append('<li id=' + data[i].id + '>\n\
                                               <a href="#' + data[i].id + '">' + rolJS.formatCadena(data[i].nombre)+ '</a>\n\
                                               <ul id="sub'+count+'_' + data[i].id + '"></ul></li>');
                        }else{
                            array.push(data[i]);
                            $(element + data[i].anidar).append('<li id=' + data[i].id + '>\n\
                                                                <a href="#' + data[i].id + '">' + rolJS.formatCadena(data[i].nombre) + '</a>\n\
                                                                <ul id="sub'+count+'_' + data[i].id + '"></ul></li>'); 
                        }
                }
                var aux=count;
                count++;
                rolJS.buildTree(data,'#sub'+aux+'_',level,count,array);
            }
    },
    formatCadena: function (cadena){
    return cadena
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
    },
    //Opción mejorada comentar el anterior
   /* setTree: function (paginas) {
        console.log(paginas);
        if (paginas != null) {
            var i, pagina, id;
            // Fase 1
            for (i = 0; i < paginas.length; i++) {
                pagina = paginas[i];
                var nombre = pagina.nombre;
                id = pagina.id;
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
                id = pagina.id;
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
                rolJS.setChekedTree();
            });
        }
    },*/
    setTree: function(data){
        jQuery('.accesos').empty();
        jQuery('.paginas').empty();
        if(data.length>0){
            rolJS.buildTree(data,'.paginas',3,0,data);
            jQuery('.accesos').jstree({
                'html_data': {
                    'data': $('.paginas').html()
                },
                "themes": {
                    "theme": "default",
                    "icons": false
                },
                'plugins': ['themes', 'html_data', 'checkbox', 'sort', 'ui']
            }).on('loaded.jstree', function () {
                jQuery('.accesos').jstree('open_all');
                rolJS.setChekedTree();
            });
        }else{
          utilEffect.hideProgressBar();
          parametroJS.findByCriteria(rolJS.rol);
        }  
    },
     /*
     * Función que Obtiene una lista de todos los registros checked
     */
    setChekedTree: function(){
         var rol = utilObject.buildObject('#rolform', new SeguridadRolDWR());
         SeguridadRolDWR.getOpFuncChecked(rol,rolJS.setChekedTreeCallback);
    },
     /*
     * Función que Obtiene una lista de todos los registros checked
     */
    setChekedTreeCallback: function(data){
        for (var i = 0; i < data.length; i++) {
            //En caso de que sea una operación
            var children = jQuery.jstree._reference('.accesos')._get_children('#' +data[i]);
            if (children.length == 0) {
                jQuery.jstree._reference('.accesos').check_node('#' +data[i]);
            }
            //Si es una funcionalidad
            var last =  data[i].charAt(data[i].length-1);
            if(last=='F')
              jQuery.jstree._reference('.accesos li li ').check_node('#'+data[i]);
        }
        parametroJS.findByCriteria(rolJS.rol);
    },
    
    getChekedTree: function(){
        var arbol = [];
        jQuery('.accesos li li').each(function () {
            if (jQuery(this).hasClass('jstree-undetermined') || jQuery(this).hasClass('jstree-checked')) {
                var id = jQuery(this).attr('id');
                arbol.push(id);
            }
        });
        if(arbol.length>0)
           return arbol;
        else
          return [""];
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
validanguage.el.rolform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.rolform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 999) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 999 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};

validanguage.el.rolform_aplicacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
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


