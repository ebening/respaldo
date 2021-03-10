//reference util.js

jQuery(document).ready(function () {
    $("#organizacionCredencialform_aplicacionId option[value='1']").remove();

    // Actualizar "Estado" al seleccionar "Pais" en formulario organizacion
    jQuery('#organizacionform_paisId').change(function () {
        jQuery('#organizacionform_estadoId').val(0);
        jQuery('#organizacionform_ciudadId').val(0);
        organizacionJS.filtrarEstado();
        
    });

    // Actualizar "Ciudad" al seleccionar "Estado" en formulario organizacion
    jQuery('#organizacionform_estadoId').change(function () {
        organizacionJS.filtrarCiudad();
    });

    // Actualizar Campos al seleccionar Aplicacion en formulario organizacion credencial
    jQuery('#organizacionCredencialform_aplicacionId').change(function () {
        organizacionCredencialJS.filtrarCamposPorAplicacion();
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
    utilInput.fixRadios('#organizacionform');


    // <editor-fold defaultstate="collapsed" desc="Botones">
    // Botón : NUEVO
    jQuery('#btnNewOrganizacion').click(function () {
        utilForm.reset('#organizacionform');
        jQuery('#organizacionform_organizacionId').val(0);
        // Limpiar el formulario y grid de organizacionCredencial
        organizacionCredencialJS.reset();
        // Limpiar el formulario y grid de organizacionUsuario
        organizacionUsuarioJS.reset();
        organizacionJS.organizacion = null;
        modsJS.iniTab();
    }).customButtonEffect('#btnNewOrganizacion');
    // Botón : GUARDAR
    jQuery('#btnSaveOrganizacion').click(function () {
        organizacionJS.prepareToSave();
    }).customButtonEffect('#btnSaveOrganizacion');
    // Botón AGREGAR (formulario organizacionCredencial)
    jQuery('#btnAddOrganizacionCredencial').click(function () {
        organizacionCredencialJS.prepareToAgregar();
    });
    // Botón AGREGAR -Sin validación- (formulario organizacionCredencial)
    jQuery('#btnAddOrganizacionCredencialNoValidation').click(function () {
        organizacionCredencialJS.agregar();
    });
    // Botón AGREGAR (formulario organizacionUsuario)
    jQuery('#btnAddOrganizacionUsuario').click(function () {
        organizacionUsuarioJS.prepareToAgregar();
    });
    // Botón AGREGAR -Sin validación- (formulario organizacionUsuario)
    jQuery('#btnAddOrganizacionUsuarioNoValidation').click(function () {
        organizacionUsuarioJS.agregar();
    });
    // Botón GENERAR (formulario organizacionGenerar)
    jQuery('#btnCreaOrganizacionGenerar').click(function () {
        organizacionGenerarJS.prepareToCrear();
    });
    // Botón AGREGAR -Sin validación- (formulario organizacionGenerar)
    jQuery('#btnCreaOrganizacionGenerarNoValidation').click(function () {
        organizacionGenerarJS.crear();
    }).customButtonEffect('#btnCreaOrganizacionGenerarNoValidation');
    // </editor-fold>

    if (jQuery('#gridVisibleOrganizacion').val() == 'true') {
        jQuery('#organizacionGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Id',
                jQuery('#organizacion_nombreText').html(),
                jQuery('#organizacion_nombreCortoText').html(),
                jQuery('#organizacion_rfcText').html(),
                jQuery('#organizacion_nombreComercialText').html(),
                jQuery('#organizacion_descripcionText').html(),
                jQuery('#organizacion_nombreContactoText').html(),
                jQuery('#organizacion_correoContactoText').html(),
                jQuery('#organizacion_telefonoContactoText').html(),
                jQuery('#organizacion_celularContactoText').html(),
                jQuery('#organizacion_direccionText').html(),
                jQuery('#organizacion_direccionAlternativaText').html(),
                jQuery('#organizacion_paisIdText').html(),
                jQuery('#organizacion_estadoIdText').html(),
                jQuery('#organizacion_ciudadIdText').html(),
//                jQuery('#organizacion_creaEventosIdText').html(),
//                jQuery('#organizacion_servicioCompletoIdText').html(),
                jQuery('#organizacion_eliminadoIdText').html(),
                jQuery('#organizacion_creacionFechaText').html(),
                jQuery('#organizacion_creacionUsuarioText').html(),
                jQuery('#organizacion_modificacionFechaText').html(),
                jQuery('#organizacion_modificacionUsuarioText').html(),
                jQuery('#organizacion_creaEventosIdText').html(),
                jQuery('#organizacion_servicioCompletoIdText').html(),
                '', ''],
            colModel: [{
                    name: 'organizacionId',
                    index: 'organizacionId',
                    align: 'center',
                    width: 50,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    width: 140,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombreCorto',
                    index: 'nombreCorto',
                    align: 'center',
                    width: 100,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'rfc',
                    index: 'rfc',
                    width: 100,
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'nombreComercial',
                    index: 'nombreComercial',
                    width: 140,
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'descripcion',
                    index: 'descripcion',
                    width: 150,
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombreContacto',
                    index: 'nombreContacto',
                    align: 'center',
                    width: 140,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'correoContacto',
                    index: 'correoContacto',
                    align: 'center',
                    width: 100,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'telefonoContacto',
                    index: 'telefonoContacto',
                    align: 'center',
                    width: 100,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'celularContacto',
                    index: 'celularContacto',
                    align: 'center',
                    width: 100,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'direccion',
                    index: 'direccion',
                    align: 'center',
                    width: 150,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'direccionAlternativa',
                    index: 'direccionAlternativa',
                    align: 'center',
                    width: 150,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'paisId',
                    index: 'paisId',
                    align: 'center',
                    width: 90,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'estadoId',
                    index: 'estadoId',
                    align: 'center',
                    width: 90,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'ciudadId',
                    index: 'ciudadId',
                    align: 'center',
                    width: 90,
                    resizable: false,
                    hidden: false,
                    search: false
//                }, {
//                    name: 'creaEventosId',
//                    index: 'creaEventosId',
//                    align: 'center',
//                    width: 80,
//                    resizable: false,
//                    hidden: false,
//                    search: true
//                }, {
//                    name: 'servicioCompletoId',
//                    index: 'servicioCompletoId',
//                    align: 'center',
//                    width: 110,
//                    resizable: false,
//                    hidden: false,
//                    search: true
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
                    width: 115,
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 115,
                    hidden: false,
                    search: false
                }, {
                    name: 'StateEventos',
                    index: 'StateEventos',
                    align: 'center',
                    width: 100,
                    sortable: false,
                    search: false,
                    resizable: false
                }, {
                    name: 'StateSC',
                    index: 'StateSC',
                    align: 'center',
                    width: 100,
                    sortable: false,
                    search: false,
                    hidden: false,
                    resizable: false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 20,
                    sortable: false,
                    hidden: false,
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
            shrinkToFit: false,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#organizacionGridPagerId'),
            caption: jQuery('#key_organizacion_title').val()
        });
        // jQuery UI Dialogs
        utilDialog.setStandardDialog('#d-crearOrg', 'Error', 'Seleccionar una aplicaci&oacute;n para su creaci&oacute;n.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-removeOrganizacion', 'Confirme', '&#191;Desea eliminar el registro?', ['Aceptar@organizacionJS.remove()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-removeSeleccionOrganizacion', 'Confirme', '&#191;Desea eliminar los registros seleccionados?', ['Aceptar@organizacionJS.removeSelected()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetCreaEventosOrganizacion', 'Confirme', '&#191;Desea cambiar el estatus de los registros seleccionados?', ['Aceptar@organizacionJS.setCreaEventosSeleccion()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirmSetServicioCompletoOrganizacion', 'Confirme', '&#191;Desea cambiar activar/desactivar el Servicio Completo de los registros seleccionados?', ['Aceptar@organizacionJS.setCreaEventosSeleccion()()', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-noSelectedOrganizacion', 'Error', 'Para poder realizar esta operaci&oacute;n seleccione uno &oacute; m&aacute;s registros.', ['Aceptar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-organizacioncredencial', 'Aviso', '&#191;Confirma eliminar la informaci&oacute;n del registro?', ['Aceptar@organizacionCredencialJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-confirma-eliminar-organizacionusuario', 'Aviso', '&#191;Confirma eliminar la informaci&oacute;n del registro?', ['Aceptar@organizacionUsuarioJS.remove();', 'Cancelar@']);
        utilDialog.setStandardDialog('#d-registroDuplicadoOrganizacion', 'Advertencia', 'Ya existe un registro con este nombre en la base de datos, ¿Desea crear un nuevo registro con este nombre?', ['Aceptar@organizacionJS.doCommit()', 'Cancelar@']);
        utilDialog.setCustomDialogSelectColumnsToExport("#organizacionColumnasExportar", '#organizacionGrid');
        utilDialog.setCustomDialogSetCreaEventosServicios('#organizacionSetCreaEventosServicios');

        // Inicializaciones extra para el grid
        utilGrid.setup('#organizacionGrid', '#organizacionGridPagerId', '#organizacionGridCurrentPage', '#organizacionGridOrderByColumn',
                '#organizacionGridOrderByType', 'organizacionJS');

        // Crear el modal de busqueda
        utilSearch.buildSearch('#d-searchOrganizacion');

        // Recargar el widget jqGrid
        organizacionJS.reloadGrid();
        //jQuery('#organizacionGrid').setGridWidth(
        //jQuery("#gbox_" + "organizacionGrid").closest(".grid-container").width() - 1150);
    }
    // Grid organizacionCredencial embebido
    if (jQuery('#gridVisibleOrganizacionCredencial').val() == 'true') {
        jQuery('#organizacionCredencialGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Fila Id',
                jQuery('#organizacionCredencial_organizacionCredencialIdText').html(),
                jQuery('#organizacionCredencial_organizacionIdText').html(),
                jQuery('#organizacionCredencial_nombreText').html(),
                jQuery('#organizacionCredencial_domainText').html(),
                jQuery('#organizacionCredencial_aplicacionIdText').html(),
                jQuery('#organizacionCredencial_aplicacionText').html(),
                jQuery('#organizacionCredencial_idanalyticsText').html(),
                jQuery('#organizacionCredencial_idappText').html(),
                jQuery('#organizacionCredencial_idrecapchaText').html(),
                jQuery('#organizacionCredencial_estatusIdText').html(),
                jQuery('#organizacionCredencial_eliminadoIdText').html(),
                jQuery('#organizacionCredencial_creacionFechaText').html(),
                jQuery('#organizacionCredencial_creacionUsuarioText').html(),
                jQuery('#organizacionCredencial_modificacionFechaText').html(),
                jQuery('#organizacionCredencial_modificacionUsuarioText').html(),
                jQuery('#organizacionCredencial_activo').val(),
                jQuery('#organizacionCredencial_modificar').val(),jQuery('#organizacionCredencial_eliminar').val()],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                }, {
                    name: 'organizacionCredencialId',
                    index: 'organizacionCredencialId',
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
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'domain',
                    index: 'domain',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'aplicacionId',
                    index: 'aplicacionId',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: true
                }, {
                    name: 'aplicacion',
                    index: 'aplicacion',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'idanalytics',
                    index: 'idanalytics',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'idapp',
                    index: 'idapp',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'idrecapcha',
                    index: 'idrecapcha',
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
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: true,
                    search: false
                }, {
                    name: 'Estatus',
                    index: 'Estatus',
                    align: 'center',
                    width: 45,
                    sortable: false,
                    search: false,
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
                    width: 60,
                    sortable: false,
                    search: false,
                    resizable: false
                }],
            height: 230,
            toolbar: false,
            hidegrid: false,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#organizacionCredencialGridPagerId'),
            caption: jQuery('#key_organizacioncredencial_title').val()
        });
        jQuery('#organizacionCredencialGridPagerId').hide();
        //utilGrid.setup('#organizacionCredencialGrid', '#organizacionCredencialGridPagerId', '#organizacionGridCurrentPage', '#organizacionGridOrderByColumn','#organizacionGridOrderByType', 'organizacionCredencialJS');
        jQuery('#organizacionCredencialGrid').setGridWidth(
                jQuery("#gbox_" + "organizacionCredencialGrid").closest(".grid-container").width() - 982);
    }

    // Grid organizacionUsuario embebido
    if (jQuery('#gridVisibleOrganizacionUsuario').val() == 'true') {
        jQuery('#organizacionUsuarioGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                'Fila Id',
                jQuery('#organizacionUsuario_organizacionUsuarioIdText').html(),
                jQuery('#organizacionUsuario_organizacionIdText').html(),
                jQuery('#organizacionUsuario_usuarioAdmonText').html(),
                jQuery('#organizacionUsuario_contrasenaText').html(),
                jQuery('#organizacionUsuario_nombreText').html(),
                jQuery('#organizacionUsuario_apellidoPaternoText').html(),
                jQuery('#organizacionUsuario_apellidoMaternoText').html(),
                jQuery('#organizacionUsuario_correoText').html(),
                jQuery('#organizacionUsuario_estatusIdText').html(),
                jQuery('#organizacionUsuario_eliminadoIdText').html(),
                jQuery('#organizacionUsuario_creacionFechaText').html(),
                jQuery('#organizacionUsuario_creacionUsuarioText').html(),
                jQuery('#organizacionUsuario_modificacionFechaText').html(),
                jQuery('#organizacionUsuario_modificacionUsuarioText').html(),
                '', ''],
            colModel: [{
                    name: 'filaId',
                    index: 'filaId',
                    hidden: true
                },
                {
                    name: 'organizacionUsuarioId',
                    index: 'organizacionUsuarioId',
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
                    name: 'usuarioAdmon',
                    index: 'usuarioAdmon',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'contrasena',
                    index: 'contrasena',
                    align: 'center',
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'nombre',
                    index: 'nombre',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'apellidoPaterno',
                    index: 'apellidoPaterno',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'apellidoMaterno',
                    index: 'apellidoMaterno',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: true
                }, {
                    name: 'correo',
                    index: 'correo',
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
                    hidden: true,
                    search: false
                }, {
                    name: 'modificacionUsuario',
                    index: 'modificacionUsuario',
                    align: 'center',
                    resizable: false,
                    width: 55,
                    hidden: true,
                    search: false
                }, {
                    name: 'Edit',
                    index: 'Edit',
                    align: 'center',
                    width: 20,
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
            hidegrid: false,
            multiselect: false,
            viewrecords: false,
            pager: jQuery('#organizacionUsuarioGridPagerId'),
            caption: jQuery('#key_organizacionusuario_title').val()
        });
        jQuery('#organizacionUsuarioGridPagerId').hide();
        jQuery('#organizacionUsuarioGrid').setGridWidth(
                jQuery("#gbox_" + "organizacionUsuarioGrid").closest(".grid-container").width() - 32);
    }
    // Limpiar la página
    jQuery('#btnNewOrganizacion').click();
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
    if (jQuery('#gridIndividualModeOrganizacion').val() == 'true') {
        jQuery('#btnNewOrganizacion').hide();
        organizacionJS.findFirst();
    }

//inicia funcionalidad de los tabs
    modsJS.iniTab();
    utilGrid.autoWidthGrid();

});

var modsJS = {
    iniTab: function () {
        modsJS.ocultarPanel();
        modsJS.ocultarTabs();
        jQuery('#panelOrganizacion').show();
        jQuery('.panelOrganizacion').addClass("active");
        jQuery('.panelOrganizacionCredencial').removeClass("active");
        jQuery('.panelOrganizacionUsuario').removeClass("active");
        jQuery('.panelOrganizacionGenerar').removeClass("active");
        jQuery('#gbox_organizacionGrid').show();
        modsJS.eventoTab();
    },
    ocultarPanel: function () {
        jQuery('#panelOrganizacion').hide();
        jQuery('#panelOrganizacionCredencial').hide();
        jQuery('#panelOrganizacionUsuario').hide();
        jQuery('#panelOrganizacionGenerar').hide();

        jQuery('#gbox_organizacionGrid').hide();
    },
    eventoTab: function () {
        jQuery('.panelOrganizacion').click(function () {
            modsJS.ocultarPanel();
            jQuery('#panelOrganizacion').show();
            jQuery('#btnNewOrganizacion').show();
            jQuery('#btnSaveOrganizacion').show();
            jQuery('#gbox_organizacionGrid').show();
        });
        jQuery('.panelOrganizacionCredencial').click(function () {
            modsJS.ocultarPanel();
            jQuery('#panelOrganizacionCredencial').show();
            jQuery('#btnNewOrganizacion').hide();
            jQuery('#btnSaveOrganizacion').hide();
            modsJS.setGridWidthModf();
        });
        jQuery('.panelOrganizacionUsuario').click(function () {
            modsJS.ocultarPanel();
            jQuery('#panelOrganizacionUsuario').show();
            jQuery('#btnNewOrganizacion').hide();
            jQuery('#btnSaveOrganizacion').hide();
            modsJS.setGridWidthModf();
        });
        jQuery('.panelOrganizacionGenerar').click(function () {
            modsJS.ocultarPanel();
            jQuery('#panelOrganizacionGenerar').show();
            jQuery('#btnNewOrganizacion').hide();
            jQuery('#btnSaveOrganizacion').hide();
            organizacionGenerarJS.findOrganizacionGenerarByIntProperty(
                    jQuery('#organizacionform_organizacionId').val());
            modsJS.setGridWidthModf();
            jQuery('#getMensajesList').html("");
        });
    },
    setGridWidthModf: function () {
        jQuery('#organizacionCredencialGrid').setGridWidth(
                jQuery("#gbox_" + "organizacionCredencialGrid").closest(".grid-container").width() - 1);
        jQuery('#organizacionUsuarioGrid').setGridWidth(
                jQuery("#gbox_" + "organizacionUsuarioGrid").closest(".grid-container").width() - 1);
    },
    ocultarTabs: function () {
        jQuery('.panelOrganizacionCredencial').hide();
        jQuery('.panelOrganizacionUsuario').hide();
        jQuery('.panelOrganizacionGenerar').hide();
    },
    mostrarTabs: function () {
        jQuery('.panelOrganizacionCredencial').show();
        jQuery('.panelOrganizacionUsuario').show();
        jQuery('.panelOrganizacionGenerar').show();
    }
};


// Objeto auxiliar para la funcionalidad de desglose.
var desgloseOrganizacionCredencialJS = {
    desglose: function (selectIdList) {
        desgloseOrganizacionCredencialJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#organizacionCredencialform");
    }
};

// Objeto auxiliar para la funcionalidad de desglose.
var desgloseOrganizacionUsuarioJS = {
    desglose: function (selectIdList) {
        desgloseOrganizacionUsuarioJS.cleanDesglose();
        utilForm.desglose(selectIdList);
    },
    cleanDesglose: function () {
        utilForm.cleanDesglose("#organizacionUsuarioform");
    }
};

var organizacionCredencialJS = {
    isDesglose: false,
    filaId: null,
    organizacionCredencial: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#organizacionCredencialform');
        jQuery('#organizacionCredencialform_filaId').val(0);
        jQuery('#organizacionCredencialform_organizacionCredencialId').val(0);
        organizacionCredencialJS.organizacionCredencial = null;
        organizacionCredencialJS.organizacionCredencialList = null;
    },
    /**
     * Limpia el formulario y el grid de organizacionCredencial, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        organizacionCredencialJS.clean();
        jQuery('#organizacionCredencialGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de organizacionCredencial antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('organizacionCredencialform');
        if (validation.result) {
            organizacionCredencialJS.agregar();
            organizacionCredencialJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * organizacionCredencial.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var organizacionCredencial = utilObject.buildObject(
                '#organizacionCredencialform', new OrganizacionCredencialDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#organizacionCredencialform_filaId') == 0) {
            organizacionCredencialJS.agregarNuevo(organizacionCredencial);
        } else {
            organizacionCredencialJS.agregarEditado(organizacionCredencial);
        }
        organizacionCredencialJS.save(jQuery('#organizacionform_organizacionId').val());
    },
    /* Agrega un registro editado al grid de organizacionCredencial */
    agregarEditado: function (object) {
        object.filaId = jQuery('#organizacionCredencialform_filaId').val();
        jQuery('#organizacionCredencialGrid').delRowData(object.filaId);
        organizacionCredencialJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de organizacionCredencial, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto OrganizacionCredencial con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        var filaId = organizacionCredencialJS.findFilaIdMax() + 1;


        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'organizacionCredencialJS.findByFilaId(' + filaId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'organizacionCredencialJS.prepareToRemove(' + filaId + ')';
        object.Edit = "<span class = '" + editar_css +
                "' title = '" + editar_title +
                "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.Remove = "<span class = '" + eliminar_css +
                "' title = '" + eliminar_title +
                "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
        // Estatus
        // Activo
        var estatus_activo_css = 'ui-icon ui-icon-bullet ui-icon-center';

        var estatus_activo_onclick = 'organizacionCredencialJS.setEstatusCredencial( ' + object.organizacionCredencialId + ', 2 )';
        // Inactivo
        var inactivo_css = 'ui-icon ui-icon-radio-off ui-icon-center';

        var inactivo_onclick = 'organizacionCredencialJS.setEstatusCredencial( ' + object.organizacionCredencialId + ', 1 )';

        var estatus_css = 'ui-icon ui-icon-bullet ui-icon-center';
        var estatus_title = 'Activo';
        var estatus_onclick = '';
        
        if (object.estatusId === -1) {
            object.estatusId  = 1
        }if (object.estatusId === 1) {
            estatus_title = 'Activo';
            estatus_css = estatus_activo_css;
            estatus_onclick = estatus_activo_onclick;
        } else {
            estatus_title = 'Inactivo';
            estatus_css = inactivo_css;
            estatus_onclick = inactivo_onclick;
        }
        object.Estatus = "<span class = '" + estatus_css +
                "' title = '" + estatus_title +
                "' style = 'cursor:pointer' onclick = '" + estatus_onclick + "'></span>";

        // Numero de Fila
        object.filaId = filaId;
        // Limpiar campos si es nuevo
        if (object.organizacionCredencialId == 0) {
            object.creacionUsuario = null;
            object.modificacionUsuario = null;
            object.creacionFecha = null;
            object.modificacionFecha = null; 
        }
        utilObject.resolveDescription('#organizacionCredencialform', object);
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (propiedad.indexOf('fecha') >= 0) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#organizacionCredencialGrid').addRowData(filaId, object);
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#organizacionCredencialGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * organizacionCredencial. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de organizacionCredencial*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#organizacionCredencialGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            if (filas[ i ].filaId == filaId) {
                organizacionCredencialJS.clean();
                organizacionCredencialJS.organizacionCredencial = filas[i];
                jQuery('#organizacionCredencialform_aplicacionId').val(filas[ i ].aplicacionId);
                organizacionCredencialJS.filtrarCamposPorAplicacion();
                utilForm.populate('#organizacionCredencialform', filas[ i ]);
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * organizacionCredencial.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        organizacionCredencialJS.filaId = filaId;
        utilDialog.showDialog('#d-confirma-eliminar-organizacioncredencial');
    },
    /** 
     * Función que elimina una fila del grid organizacioncredencial, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * organizacioncredencialJS.filaId.
     */
    remove: function () {
        jQuery('#organizacionCredencialGrid').delRowData(organizacionCredencialJS.filaId);
        organizacionCredencialJS.clean();
        organizacionCredencialJS.save(jQuery('#organizacionform_organizacionId').val());
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param organizacionId, es el id de organizacion.
     */
    save: function (organizacionId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (organizacionCredencialJS.isDesglose) {
            jQuery('form[name="organizacionCredencialform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new OrganizacionCredencialDWR());
                    obj.organizacionId = organizacionId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId(
                    '#organizacionCredencialGrid', new OrganizacionCredencialDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].organizacionId = organizacionId;
                var fechaMod = dwrObjectList[ i ].modificacionFecha;
                if (fechaMod !== "") {
                    dwrObjectList[ i ].modificacionFecha = Date.parse(fechaMod);
                }
                var fechaCrea = dwrObjectList[ i ].creacionFecha;
                if (fechaCrea !== "") {
                    dwrObjectList[ i ].creacionFecha = Date.parse(fechaCrea);
                }
            }
        }
        OrganizacionDWR.saveOrganizacionCredencial(organizacionId,
                dwrObjectList, organizacionCredencialJS.saveCallback);
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function (data) {
        jQuery().toastmessage('showToast', {
            text: jQuery("#msgGuardadoOk").val(),
            sticky: false,
            position: 'bottom-center',
            type: 'success',
            closeText: ''
        });
    },
    /* Función que obtiene organizacionCredencial */
    findOrganizacionCredencialByIntProperty: function (organizacionId) {
        OrganizacionDWR.findOrganizacionCredencialByIntProperty("organizacionId", organizacionId,
                organizacionCredencialJS.findOrganizacionCredencialByIntPropertyCallback);
    },
    /**
     * Callback de la función findOrganizacionCredencialByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos OrganizacionCredencial que serán
     * insertados en el grid
     */
    findOrganizacionCredencialByIntPropertyCallback: function (data) {
        organizacionCredencialJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                organizacionCredencialJS.agregarNuevo(data[ i ]);
            }

        }
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
    findByCriteriaOrganizacionCredencial: function (obj) {
        organizacionCredencialJS.cacheDWR = obj;
        var page = 1;//jQuery('#organizacionCredencialGridCurrentPage').val();
        var rows = 10;//jQuery('#organizacionCredencialGridRowsByPage').val();
        var order = 'modificacionFecha';//jQuery('#organizacionCredencialGridOrderByColumn').val();
        var orderType = 'des';//jQuery('#organizacionCredencialGridOrderByType').val();
        utilEffect.showProgressBar();
        OrganizacionDWR.findByCriteriaOrganizacionCredencial(page, rows, order, orderType, obj, organizacionCredencialJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {

        utilGrid.gridUpdate('#organizacionCredencialGrid', '#organizacionCredencialGridCurrentPage', '#organizacionCredencialGridRowsByPage', data);
        utilEffect.hideProgressBar();
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGridOrganizacionCredencial: function () {
        if (organizacionCredencialJS.cacheDWR != null) {
            organizacionCredencialJS.findByCriteriaOrganizacionCredencial(organizacionCredencialJS.cacheDWR);
        } else {
            organizacionCredencialJS.findByCriteriaOrganizacionCredencial(new OrganizacionCredencialDWR());
        }
    },
    /*
     * Función que cambia el estatus de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setEstatus</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el estatusId
     * @param estatusId (int) Es el nuevo estatusId
     */
    setEstatusCredencial: function (id, estatusId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        OrganizacionDWR.setEstatusCredencial(estatusId, listaObjetos, organizacionCredencialJS.setEstatusCallback);
    },
    /*
     * Callback de la función setEstatus(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setEstatusCallback: function () {
        //organizacionCredencialJS.reloadGridOrganizacionCredencial();
        organizacionCredencialJS.findOrganizacionCredencialByIntProperty(jQuery('#organizacionform_organizacionId').val());
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetEstatus: function (estatusId) {
        console.info(estatusId);
//        var seleccion = utilGrid.gridGetSelectedRows('#organizacionCredencialGrid');
//        if (seleccion.length == 0) {
//            utilDialog.showDialog('#d-noSelectedAplicacion');
//        } else {
//            organizacionCredencialJS.estatusId = estatusId;
//             utilDialog.showDialog( '#d-confirmSetEstatusAplicacion' );
//            organizacionCredencialJS.setEstatusSeleccion();
//        }
    },
    /*
     * Variable para guardar el estatus según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    estatusId: 0,
    deleteMe: function () {
        // do nothing
    },
    filtrarCamposPorAplicacion: function () {
        if (Number(jQuery('#organizacionCredencialform_aplicacionId').val()) === Number(2)) {//Web
            jQuery("#organizacionCredencialform_idanalytics").removeAttr("disabled");
            jQuery("#organizacionCredencialform_idapp").removeAttr("disabled");
            jQuery("#organizacionCredencialform_idrecapcha").removeAttr("disabled");
        } else if (Number(jQuery('#organizacionCredencialform_aplicacionId').val()) === Number(3)) {//Taquilla
            jQuery("#organizacionCredencialform_idanalytics").attr("disabled", "disabled");
            jQuery("#organizacionCredencialform_idapp").attr("disabled", "disabled");
            jQuery("#organizacionCredencialform_idrecapcha").attr("disabled", "disabled");
        }
        if (Number(jQuery("#organizacionCredencialform_organizacionCredencialId").val()) <= 0
                || Number(jQuery('#organizacionCredencialform_aplicacionId').val()) === Number(3)) {
            jQuery("#organizacionCredencialform_idanalytics").val("");
            jQuery("#organizacionCredencialform_idapp").val("");
            jQuery("#organizacionCredencialform_idrecapcha").val("");
        }
    },
}

var organizacionUsuarioJS = {
    isDesglose: false,
    filaId: null,
    organizacionUsuario: null,
    /**
     * Limpia el formulario.
     */
    clean: function () {
        utilForm.reset('#organizacionUsuarioform');
        jQuery('#organizacionUsuarioform_filaId').val(0);
        jQuery('#organizacionUsuarioform_organizacionUsuarioId').val(0);
        organizacionUsuarioJS.organizacionUsuario = null;
        organizacionUsuarioJS.organizacionUsuarioList = null;
    },
    /**
     * Limpia el formulario y el grid de organizacionUsuario, usado cuando el
     * usuario presiona el botón Nuevo.
     */
    reset: function () {
        organizacionUsuarioJS.clean();
        jQuery('#organizacionUsuarioGrid').clearGridData();
    },
    /** 
     * Valida los campos del formulario de organizacionUsuario antes de
     * agregarlos al grid.
     */
    prepareToAgregar: function () {
        var validation = validanguage.validateForm('organizacionUsuarioform');
        if (validation.result) {
            organizacionUsuarioJS.agregar();
            organizacionUsuarioJS.clean();
        }
    },
    /** 
     * Función llamada cuando se presiona el botón Agregar en el formulario de 
     * organizacionUsuario.
     */
    agregar: function () {
        // Formar el objeto con los datos del formulario
        var organizacionUsuario = utilObject.buildObject(
                '#organizacionUsuarioform', new OrganizacionUsuarioDWR());
        // Valdar si se esta agregando uno nuevo o uno editado
        if (jQuery('#organizacionUsuarioform_filaId') == 0) {
            organizacionUsuarioJS.agregarNuevo(organizacionUsuario);
        } else {
            organizacionUsuarioJS.agregarEditado(organizacionUsuario);
        }
    },
    /* Agrega un registro editado al grid de organizacionUsuario */
    agregarEditado: function (object) {
        object.filaId = jQuery('#organizacionUsuarioform_filaId').val();
        jQuery('#organizacionUsuarioGrid').delRowData(object.filaId);
        organizacionUsuarioJS.agregarNuevo(object);
    },
    /**
     * Agrega un nuevo registro al grid de organizacionUsuario, de manera 
     * predeterminada la fila se agrega al final de las existentes.
     * 
     * @param object, es un objeto OrganizacionUsuario con la información a
     * agregar.
     */
    agregarNuevo: function (object) {
        var filaId = organizacionUsuarioJS.findFilaIdMax() + 1;
        // Editar
        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
        var editar_title = 'Editar';
        var editar_onclick = 'organizacionUsuarioJS.findByFilaId(' + filaId + ')';
        // Eliminar
        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
        var eliminar_title = 'Eliminar';
        var eliminar_onclick = 'organizacionUsuarioJS.prepareToRemove(' + filaId + ')';
        object.Edit = "<span class = '" + editar_css +
                "' title = '" + editar_title +
                "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
        object.Remove = "<span class = '" + eliminar_css +
                "' title = '" + eliminar_title +
                "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";

        // Numero de Fila
        object.filaId = filaId;
        // Limpiar campos si es nuevo
        if (object.organizacionUsuarioId == 0) {
            utilObject.cleanCamposControl(object);
            utilObject.resolveDescription('#organizacionUsuarioform', object);
        }
        // Parsear fechas
        var propiedades = utilObject.getProperties(object);
        for (var i = 0; i < propiedades.length; i++) {
            var propiedad = propiedades[ i ] + '';
            if (propiedad.indexOf('fecha') >= 0) {
                if (object[ propiedad ] != null) {
                    object[ propiedad ] = jQuery.datepicker.formatDate(
                            'dd/mm/yy', new Date(object[ propiedad ])
                            );
                }
            }
        }
        // Enviar los datos al grid
        jQuery('#organizacionUsuarioGrid').addRowData(filaId, object);
    },
    /**
     * Obtiene el numero de fila mas alto en el grid.
     * 
     * @return regresa un entero igual al numero de la fila mas alto.
     */
    findFilaIdMax: function () {
        var filaIdMax = 0;
        var filaList = jQuery('#organizacionUsuarioGrid').getRowData();
        for (var i = 0; i < filaList.length; i++) {
            if (parseInt(filaList[i].filaId) > filaIdMax) {
                filaIdMax = parseInt(filaList[i].filaId);
            }
        }
        return parseInt(filaIdMax);
    },
    /** 
     * Función llamada al presionar el botón Editar en el registro de 
     * organizacionUsuario. Obtiene un objeto desde el grid mediante el numero de la
     * fila, el objeto es parseado para llenar los campos en el formulario.
     * 
     * @param filaId, es el numero de la fila.
     */
    /* Función llamada al presionar el botón Editar en el registro de organizacionUsuario*/
    findByFilaId: function (filaId) {
        var filas = jQuery('#organizacionUsuarioGrid').getRowData();
        for (var i = 0; i < filas.length; i++) {
            if (filas[ i ].filaId == filaId) {
                organizacionUsuarioJS.clean();
                organizacionUsuarioJS.organizacionUsuario = filas[i];
                utilForm.populate('#organizacionUsuarioform', filas[ i ]);
                break;
            }
        }
    },
    /** 
     * Función llamada al presionar el botón Eliminar en el registro de
     * organizacionUsuario.
     * 
     * @param filaId, es el numero de la fila.
     */
    prepareToRemove: function (filaId) {
        organizacionUsuarioJS.filaId = filaId;
        utilDialog.showDialog('#d-confirma-eliminar-organizacionusuario');
    },
    /** 
     * Función que elimina una fila del grid organizacionusuario, el numero de 
     * fila a eliminar es el que se encuentra almacenado en la variale
     * organizacionusuarioJS.filaId.
     */
    remove: function () {
        jQuery('#organizacionUsuarioGrid').delRowData(organizacionUsuarioJS.filaId);
        organizacionUsuarioJS.clean();
    },
    /** 
     * Guarda los registros del detalle.
     * 
     * @param organizacionId, es el id de organizacion.
     */
    save: function (organizacionId) {
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        if (organizacionUsuarioJS.isDesglose) {
            jQuery('form[name="organizacionUsuarioform"]').each(function () {
                if (jQuery(this).hasClass('base-desglose') == false) {
                    // Construir el objeto
                    var obj = utilObject.buildObject('#' +
                            jQuery(this).attr('id'), new OrganizacionUsuarioDWR());
                    obj.organizacionId = organizacionId;
                    dwrObjectList.push(obj);
                }
            });
        } else {
            dwrObjectList = utilObject.buildDWRObjectListByGridId(
                    '#organizacionUsuarioGrid', new OrganizacionUsuarioDWR());
            for (var i = 0; i < dwrObjectList.length; i++) {
                dwrObjectList[ i ].organizacionId = organizacionId;
            }
        }
        OrganizacionDWR.saveOrganizacionUsuario(organizacionId,
                dwrObjectList, organizacionUsuarioJS.saveCallback);
    },
    /* Callback de la función save(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    saveCallback: function () {
    },
    /* Función que obtiene organizacionUsuario */
    findOrganizacionUsuarioByIntProperty: function (organizacionId) {
        OrganizacionDWR.findOrganizacionUsuarioByIntProperty("organizacionId", organizacionId,
                organizacionUsuarioJS.findOrganizacionUsuarioByIntPropertyCallback);
    },
    /**
     * Callback de la función findOrganizacionUsuarioByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos OrganizacionUsuario que serán
     * insertados en el grid
     */
    findOrganizacionUsuarioByIntPropertyCallback: function (data) {
        organizacionUsuarioJS.reset();
        if (data != null) {
            for (var i = 0; i < data.length; i++) {
                organizacionUsuarioJS.agregarNuevo(data[ i ]);
            }

        }
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    }
}

var organizacionGenerarJS = {
    isDesglose: false,
    filaId: null,
    organizacionGenerar: null,
    /**
     * Limpia el formulario.
     */
//    clean: function () {
//        utilForm.reset('#organizacionGenerarform');
//        jQuery('#organizacionGenerarform_filaId').val(0);
//        jQuery('#organizacionGenerarform_organizacionGenerarId').val(0);
//        organizacionGenerarJS.organizacionGenerar = null;
//        organizacionGenerarJS.organizacionGenerarList = null;
//    },
//    /**
//     * Limpia el formulario y el grid de organizacionGenerar, usado cuando el
//     * usuario presiona el botón Nuevo.
//     */
//    reset: function () {
//        organizacionGenerarJS.clean();
//        jQuery('#organizacionGenerarGrid').clearGridData();
//    },
//    /** 
//     * Valida los campos del formulario de organizacionGenerar antes de
//     * agregarlos al grid.
//     */
//    prepareToAgregar: function () {
//        var validation = validanguage.validateForm('organizacionGenerarform');
//        if (validation.result) {
//            organizacionGenerarJS.agregar();
//            //organizacionGenerarJS.clean();
//        }
//    },
//    /** 
//     * Función llamada cuando se presiona el botón Agregar en el formulario de 
//     * organizacionGenerar.
//     */
//    agregar: function () {
//        // Formar el objeto con los datos del formulario
//        var organizacionGenerar = utilObject.buildObject(
//                '#organizacionGenerarform', new OrganizacionGenerarDWR());
//        // Valdar si se esta agregando uno nuevo o uno editado
//        if (jQuery('#organizacionGenerarform_filaId') == 0) {
//            organizacionGenerarJS.agregarNuevo(organizacionGenerar);
//        } else {
//            organizacionGenerarJS.agregarEditado(organizacionGenerar);
//        }
//    },
//    /* Agrega un registro editado al grid de organizacionGenerar */
//    agregarEditado: function (object) {
//        object.filaId = jQuery('#organizacionGenerarform_filaId').val();
//        jQuery('#organizacionGenerarGrid').delRowData(object.filaId);
//        organizacionGenerarJS.agregarNuevo(object);
//    },
//    /**
//     * Agrega un nuevo registro al grid de organizacionGenerar, de manera 
//     * predeterminada la fila se agrega al final de las existentes.
//     * 
//     * @param object, es un objeto OrganizacionGenerar con la información a
//     * agregar.
//     */
//    agregarNuevo: function (object) {
//        var filaId = organizacionGenerarJS.findFilaIdMax() + 1;
//        // Editar
//        var editar_css = 'ui-icon ui-icon-pencil ui-icon-center';
//        var editar_title = 'Editar';
//        var editar_onclick = 'organizacionGenerarJS.findByFilaId(' + filaId + ')';
//        // Eliminar
//        var eliminar_css = 'ui-icon ui-icon-trash ui-icon-center';
//        var eliminar_title = 'Eliminar';
//        var eliminar_onclick = 'organizacionGenerarJS.prepareToRemove(' + filaId + ')';
//        object.Edit = "<span class = '" + editar_css +
//                "' title = '" + editar_title +
//                "' style = 'cursor:pointer' onclick = '" + editar_onclick + "'></span>";
//        object.Remove = "<span class = '" + eliminar_css +
//                "' title = '" + eliminar_title +
//                "' style = 'cursor:pointer' onclick = '" + eliminar_onclick + "'></span>";
//
//        // Numero de Fila
//        object.filaId = filaId;
//        // Limpiar campos si es nuevo
//        if (object.organizacionGenerarId == 0) {
//            utilObject.cleanCamposControl(object);
//            utilObject.resolveDescription('#organizacionGenerarform', object);
//        }
//        // Parsear fechas
//        var propiedades = utilObject.getProperties(object);
//        for (var i = 0; i < propiedades.length; i++) {
//            var propiedad = propiedades[ i ] + '';
//            if (propiedad.indexOf('fecha') >= 0) {
//                if (object[ propiedad ] != null) {
//                    object[ propiedad ] = jQuery.datepicker.formatDate(
//                            'dd/mm/yy', new Date(object[ propiedad ])
//                            );
//                }
//            }
//        }
//        // Enviar los datos al grid
//        jQuery('#organizacionGenerarGrid').addRowData(filaId, object);
//    },
//    /**
//     * Obtiene el numero de fila mas alto en el grid.
//     * 
//     * @return regresa un entero igual al numero de la fila mas alto.
//     */
//    findFilaIdMax: function () {
//        var filaIdMax = 0;
//        var filaList = jQuery('#organizacionGenerarGrid').getRowData();
//        for (var i = 0; i < filaList.length; i++) {
//            if (parseInt(filaList[i].filaId) > filaIdMax) {
//                filaIdMax = parseInt(filaList[i].filaId);
//            }
//        }
//        return parseInt(filaIdMax);
//    },
//    /** 
//     * Función llamada al presionar el botón Editar en el registro de 
//     * organizacionGenerar. Obtiene un objeto desde el grid mediante el numero de la
//     * fila, el objeto es parseado para llenar los campos en el formulario.
//     * 
//     * @param filaId, es el numero de la fila.
//     */
//    /* Función llamada al presionar el botón Editar en el registro de organizacionGenerar*/
//    findByFilaId: function (filaId) {
//        var filas = jQuery('#organizacionGenerarGrid').getRowData();
//        for (var i = 0; i < filas.length; i++) {
//            if (filas[ i ].filaId == filaId) {
//                organizacionGenerarJS.clean();
//                organizacionGenerarJS.organizacionGenerar = filas[i];
//                utilForm.populate('#organizacionGenerarform', filas[ i ]);
//                break;
//            }
//        }
//    },
//    /** 
//     * Función llamada al presionar el botón Eliminar en el registro de
//     * organizacionGenerar.
//     * 
//     * @param filaId, es el numero de la fila.
//     */
//    prepareToRemove: function (filaId) {
//        organizacionGenerarJS.filaId = filaId;
//        utilDialog.showDialog('#d-confirma-eliminar-organizaciongenerar');
//    },
//    /** 
//     * Función que elimina una fila del grid organizaciongenerar, el numero de 
//     * fila a eliminar es el que se encuentra almacenado en la variale
//     * organizaciongenerarJS.filaId.
//     */
//    remove: function () {
//        jQuery('#organizacionGenerarGrid').delRowData(organizacionGenerarJS.filaId);
//        organizacionGenerarJS.clean();
//    },

    /** 
     * Crea la nueva organización.
     * 
     * @param organizacionId, es el id de organizacion.
     */
    crear: function (organizacionId) {
        var admin = $("#admin").prop("checked") == true ? 1 : 0;
        var taquilla = $("#taquilla").prop("checked") == true ? 1 : 0;
        var web = $("#web").prop("checked") == true ? 1 : 0;
        var dwrObjectList = new Array();
        // Funcionalidad desglose
        jQuery('form[name="organizacionGenerarform"]').each(function () {
            if (jQuery(this).hasClass('base-desglose') == false) {
                // Construir el objeto
                var obj = utilObject.buildObject('#' +
                        jQuery(this).attr('id'), new OrganizacionGenerarDWR());
                obj.organizacionId = organizacionId;
                obj.admin = admin;
                obj.taquilla = taquilla;
                obj.web = web;
                dwrObjectList.push(obj);
            }
        });
        organizacionId = jQuery('#organizacionform_organizacionId').val();
        if (admin > 0 || taquilla > 0 || web > 0) {
            utilEffect.showProgressBar();
            OrganizacionDWR.crearOrganizacionGenerar(organizacionId,
                    dwrObjectList, organizacionGenerarJS.crearCallback);
        } else {
            utilDialog.showDialog('#d-crearOrg');
        }

    },
    /* Callback de la función crear(), esta función es llamada por DWR al terminar
     * de realizar la operación */
    crearCallback: function (data) {
        var html = "";
        if (typeof data !== "undefined" && data !== null) {
//            for (var i = 0; i < data.length; i++) {
//                html += "<div class=\"box-content\">";
//                html += "                            <div class=\"todo\">";
//                html += "                                    <ul class=\"todo-list\">";
//                html += "                                            <li>";
//                html += "                                                    <span class=\"todo-actions\">";
////         Icono estatus 
//                if (data[i].estatusId === 0) {
//                    html += "                                                            <a><i class=\"fa fa-exclamation-triangle\"></i></a>";
//                } else {
//                    html += "                                                            <a><i class=\"fa fa-check green\"></i></a>";
//                }
//                html += "                                                    </span>";
//                html += "                                                    <span class=\"desc\">" + data[i].nombre + "</span>     ";
//                html += "                                                    <span class=\"desc\">Texto Mensaje</span>     ";
//                html += "                                            </li>";
//                html += "                                    </ul>";
//                html += "                            </div> ";
//                html += "                    </div>";
//            }
        }
        jQuery('#getMensajesList').html("");
        jQuery('#getMensajesList').html(html);
        utilEffect.hideProgressBar();
    },
    /* Función que obtiene organizacionGenerar */
    findOrganizacionGenerarByIntProperty: function (organizacionId) {
        utilEffect.showProgressBar();
        OrganizacionDWR.findOrganizacionGenerarByIntProperty("organizacionId", organizacionId,
                organizacionGenerarJS.findOrganizacionGenerarByIntPropertyCallback);
    },
    /**
     * Callback de la función findOrganizacionGenerarByIntProperty(), esta función es llamada por DWR al terminar
     * de realizar la operación 
     * 
     * @param data, es una lista de objetos OrganizacionGenerar que serán
     * insertados en el grid
     */
    findOrganizacionGenerarByIntPropertyCallback: function (data) {
        var html = "";
        for (var i = 0; i < data.length; i++) {
            html += "<div class=\"box-content\">";
            html += "                            <div class=\"todo\">";
            html += "                                    <ul class=\"todo-list\">";
            html += "                                            <li>";
            html += "                                                    <span class=\"todo-actions\">";
            // Icono estatus 
            if (data[i].estatusId === 0) {
                html += "                                                            <a><i class=\"fa fa-exclamation-triangle\"></i></a>";
            } else {
                html += "                                                            <a><i class=\"fa fa-check green\"></i></a>";
            }
            html += "                                                    </span>";
            html += "                                                    <span class=\"desc\">" + data[i].nombre + "</span>     ";
            html += "                                            </li>";
            html += "                                    </ul>";
            html += "                            </div> ";
            html += "                    </div>";
        }
        jQuery('#getRequerimientosList').html(html);
        utilEffect.hideProgressBar();
    },
    deleteMe: function () {
        // do nothing
    }
};

var organizacionJS = {
    /* Variable auxiliar para persistir un objeto (resultado de una consulta) 
     * obtenido mediante el callback de la función "findById(...)". Este objeto
     * es necesario para la funcionalidad de selects encadenados pero puede ser
     * utilizado para otros propósitos. El objeto existe solamente mientras se
     * esta editando la información en el formulario. Al presionar el botón
     * Nuevo o Guardar cualquier referencia a éste regresará "null". El 
     * objeto debe utilizarse como modo de "Solo Lectura" (la modificación de 
     * este objeto no se verá reflejada en la base de datos) */
    organizacion: null,
    /*
     * Variable auxiliar para mantener las busquedas del grid al momento de paginar, ordenar, etc.
     */
    cacheDWR: null,
    /*
     * Función que guarda un nuevo registro en la BD.
     */
    save: function () {
        utilEffect.showProgressBar();
        var organizacion = utilObject.buildObject('#organizacionform', new OrganizacionDWR());
        organizacion.organizacionId = 0;
        var listaOrganizacions = [organizacion];
        OrganizacionDWR.save(listaOrganizacions, organizacionJS.saveOrUpdateCallback);
    },
    /*
     * Función que actualiza un registro existente en la BD.
     */
    update: function () {
        utilEffect.showProgressBar();
        var organizacion = utilObject.buildObject('#organizacionform', new OrganizacionDWR());
        organizacion.organizacionId = jQuery('#organizacionform_organizacionId').val();
        var listaOrganizacions = [organizacion];
        OrganizacionDWR.update(listaOrganizacions, organizacionJS.saveOrUpdateCallback);
    },
    /*
     * Callback de la funcion save() y update(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param savedIds (array), lista de id's de objetos que
     * se guardaron en base de datos.
     */
    saveOrUpdateCallback: function (savedIds) {
        organizacionCredencialJS.save(savedIds[ 0 ]);
        //organizacionUsuarioJS.save(savedIds[ 0 ]);
        jQuery('#btnNewOrganizacion').click();
        //organizacionJS.organizacion = null;
        organizacionJS.reloadGrid();
//        organizacionGenerarJS.reloadGrid();

    },
    /*
     * Función que elimina un registro en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    remove: function () {
        utilEffect.showProgressBar();
        var id = organizacionJS.getEliminarId();
        var listaIds = [id];
        OrganizacionDWR.remove(listaIds, organizacionJS.removeCallback);
    },
    /*
     * Función que elimina los registros seleccionados en el grid al presionar el botón eliminar.
     * Manda llamar al método <b>remove</b> de DWR, éste método 
     * espera siempre una lista de id's como parámetro.
     */
    removeSelected: function () {
        utilEffect.showProgressBar();
        var listaIds = utilGrid.gridGetSelectedRows('#organizacionGrid');
        OrganizacionDWR.remove(listaIds, organizacionJS.removeCallback);
    },
    /*
     * Callback de la función remove(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     */
    removeCallback: function () {
        jQuery('#btnNewOrganizacion').click();
        organizacionJS.reloadGrid();
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
        OrganizacionDWR.findById(id, organizacionJS.findByIdCallback);
    },
    /*
     * Callback de la función findById(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto con la información de la consulta.
     */
    findByIdCallback: function (data) {
        utilForm.reset('#organizacionform');
        utilForm.populate('#organizacionform', data);
        organizacionJS.organizacion = data;
        organizacionCredencialJS.findOrganizacionCredencialByIntProperty(
                jQuery('#organizacionform_organizacionId').val());
//        organizacionGenerarJS.findOrganizacionGenerarByIntProperty(
//                jQuery('#organizacionform_organizacionId').val());
        organizacionUsuarioJS.findOrganizacionUsuarioByIntProperty(
                jQuery('#organizacionform_organizacionId').val());
        modsJS.mostrarTabs();
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
        organizacionJS.cacheDWR = obj;
        var page = jQuery('#organizacionGridCurrentPage').val();
        var rows = jQuery('#organizacionGridRowsByPage').val();
        var order = jQuery('#organizacionGridOrderByColumn').val();
        var orderType = jQuery('#organizacionGridOrderByType').val();
        utilEffect.showProgressBar();
        OrganizacionDWR.findByCriteria(page, rows, order, orderType, obj, organizacionJS.findByCriteriaCallback);
    },
    /*
     * Callback de la función findByCriteria(...), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     * @param data (Object) Es un objeto (Grid) con la información de la 
     * consulta.
     */
    findByCriteriaCallback: function (data) {
        utilGrid.gridUpdate('#organizacionGrid', '#organizacionGridCurrentPage', '#organizacionGridRowsByPage', data);
        utilEffect.hideProgressBar();
        if (data === null || typeof data === 'undefined' || (data !== null && data.total <= 0)) {
            jQuery().toastmessage('showToast', {
                text: jQuery("#msgSinResultados").val(),
                sticky: false,
                position: 'bottom-center',
                type: 'success',
                closeText: '',
                close: function () {
                }
            });
        }
    },
    /**
     * Funcion utilizado cuando la pagina es individual
     */
    findFirst: function () {
        utilEffect.showProgressBar();
        OrganizacionDWR.findFirst(organizacionJS.findByIdCallback);
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
        var nombre = jQuery('#organizacionform_nombre').val();
        OrganizacionDWR.isValidoNombre(nombre, organizacionJS.isValidoNombreCallback);
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
        if (isValido == false && jQuery('#organizacionform_organizacionId').val() != 0) {
            organizacionJS.doCommit();
            return;
        }

        // Nuevo registro, en este caso se permite el commit
        if (isValido == true && jQuery('#organizacionform_organizacionId').val() == 0) {
            organizacionJS.doCommit();
            return;
        }

        // Registro existente, se le cambio el nombre, en este caso se permite el commit
        if (isValido == true && jQuery('#organizacionform_organizacionId').val() != 0) {
            organizacionJS.doCommit();
            return;
        }

        // Mostrar mensaje de registro duplicado 
        utilDialog.showDialog('#d-registroDuplicadoOrganizacion');
    },
    /*
     * Función que cambia el creaEventos de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setCreaEventos</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el CreaEventosId
     * @param CreaEventosId (int) Es el nuevo creaEventosId
     */
    setCreaEventos: function (id, creaEventosId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        OrganizacionDWR.setCreaEventos(creaEventosId, listaObjetos, organizacionJS.setCreaEventosCallback);
    },
    /*
     * Función que cambia el CreaEventos de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setCreaEventosSeleccion: function () {
        utilEffect.showProgressBar();
        var creaEventosId = organizacionJS.creaEventosId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#organizacionGrid');
        OrganizacionDWR.setCreaEventos(creaEventosId, listaObjetos, organizacionJS.setServicioCompletoSeleccion);
    },
    /*
     * Callback de la función setCreaEventos(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setCreaEventosCallback: function () {
        organizacionJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    setCreaEventosSeleccionCallback: function () {
        organizacionJS.setServicioCompletoSeleccion();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetCreaEventosServicios: function (creaEventosId, creaServiciosId) {
        var seleccion = utilGrid.gridGetSelectedRows('#organizacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOrganizacion');
        } else {
            organizacionJS.creaEventosId = creaEventosId;
            organizacionJS.servicioCompletoId = creaServiciosId;
            organizacionJS.setCreaEventosSeleccion();
        }
    },
    prepareToSetEstatus: function (estatusEvento, estatusServicio) {
        organizacionJS.prepareToSetCreaEventosServicios(estatusEvento, estatusServicio);
    },
    /*
     * Función que cambia el servicioCompleto de un registro en el grid al presionar el botón Activar/Inactivar.
     * Manda llamar al método <b>setServicioCompleto</b> de DWR, éste método.
     *
     * @param id (int) Es el id del registro al cual se le va a cambiar el ServicioCompletoId
     * @param ServicioCompletoId (int) Es el nuevo servicioCompletoId
     */
    setServicioCompleto: function (id, servicioCompletoId) {
        utilEffect.showProgressBar();
        var listaObjetos = [id];
        OrganizacionDWR.setServicioCompleto(servicioCompletoId, listaObjetos, organizacionJS.setServicioCompletoCallback);
    },
    /*
     * Función que cambia el ServicioCompleto de los elementos seleccionados en el widget
     * de jqGrid.
     * 
     */
    setServicioCompletoSeleccion: function () {
        var servicioCompletoId = organizacionJS.servicioCompletoId;
        var listaObjetos = utilGrid.gridGetSelectedRows('#organizacionGrid');
        OrganizacionDWR.setServicioCompleto(servicioCompletoId, listaObjetos, organizacionJS.setServicioCompletoCallback);
    },
    /*
     * Callback de la función setServicioCompleto(), esta función es llamada por DWR al 
     * terminar de realizar la operación.
     * 
     */
    setServicioCompletoCallback: function () {
        organizacionJS.reloadGrid();
        utilEffect.hideProgressBar();
    },
    /*
     * Función que muestra un mensaje de confirmación para Activar o Inactivar los registros
     * seleccionados del widget jQGrid.
     */
    prepareToSetServicioCompleto: function (servicioCompletoId) {
        var seleccion = utilGrid.gridGetSelectedRows('#organizacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOrganizacion');
        } else {
            organizacionJS.servicioCompletoId = servicioCompletoId;
//            utilDialog.showDialog( '#d-confirmSetServicioCompletoOrganizacion' );
//            organizacionJS.setServicioCompletoSeleccion();
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar los registros seleccionados
     * del widget jQGrid.
     */
    prepareToRemoveSelected: function () {
        var seleccion = utilGrid.gridGetSelectedRows('#organizacionGrid')
        if (seleccion.length == 0) {
            utilDialog.showDialog('#d-noSelectedOrganizacion');
        } else {
            utilDialog.showDialog('#d-removeSeleccionOrganizacion');
        }
    },
    /*
     * Función que muestra un mensaje de confirmación para eliminar un regristro
     * del widget jQGrid.
     */
    prepareToRemove: function (id) {
        organizacionJS.setEliminarId(id);
        utilDialog.showDialog('#d-removeOrganizacion');
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
        organizacionJS.eliminarId = eliminarId;
    },
    /*
     * Getter de la variable eliminarId
     **/
    getEliminarId: function () {
        return organizacionJS.eliminarId;
    },
    /*
     * Variable para guardar el creaEventos según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    creaEventosId: 0,
    /*
     * Variable para guardar el servicioCompleto según el botón que se presionó: Activar o Inactivar si 
     * el usuario confirma la decisión al presionar el botón Aceptar del cuadro de diálogo. 
     **/
    servicioCompletoId: 0,
    /*
     * Función que se ejecuta al presionar el botón : GUARDAR.
     */
    prepareToSave: function () {
        var validation = validanguage.validateForm('organizacionform');
        if (validation.result) {
            organizacionJS.isValidoNombre();
        }
    },
    /*
     * Función que inicia el proceso para GUARDAR y ACTUALIZAR/MODIFICAR
     * Una vez que se pasaron las validaciones se realiza el commit.
     */
    doCommit: function () {
        if (jQuery('#organizacionform_organizacionId').val() == 0) {
            organizacionJS.save();
        } else {
            organizacionJS.update();
        }
    },
    /*
     * Función que es llamada para actualizar la información del grid.
     */
    reloadGrid: function () {
        if (organizacionJS.cacheDWR != null) {
            organizacionJS.findByCriteria(organizacionJS.cacheDWR);
        } else {
            organizacionJS.findByCriteria(new OrganizacionDWR());
        }
    },
    /*
     * Función que es activa el modal de busqueda.
     */
    openSearch: function () {
        utilSearch.openSearch('#d-searchOrganizacion', '#organizacionGrid', '#organizacionform', organizacionJS, new OrganizacionDWR());
    },
    filtrarEstado: function () {
        utilEffect.showProgressBar();
        OrganizacionDWR.filtrarEstado(jQuery('#organizacionform_paisId').val(), organizacionJS.filtrarEstadoCallback);
    },
    filtrarEstadoCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#organizacionform_estadoId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#organizacionform_estadoId', data, 'estadoId', 'nombre');
            if (organizacionJS.organizacion !== null) {
                if (organizacionJS.organizacion.estadoId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#organizacionform_estadoId', organizacionJS.organizacion.estadoId)) {
                        jQuery('#organizacionform_estadoId').val(organizacionJS.organizacion.estadoId);
                    } else {
                        jQuery('#organizacionform_estadoId').val(0);
                    }
                }
            }
            jQuery('#organizacionform_estadoId').trigger('change');
        }
        utilEffect.hideProgressBar();
    }, filtrarCiudad: function () {
        utilEffect.showProgressBar();
        OrganizacionDWR.filtrarCiudad(jQuery('#organizacionform_estadoId').val(), organizacionJS.filtrarCiudadCallback);
    },
    filtrarCiudadCallback: function (data) {
        if (data == null || data.length == 0) {
            utilInput.removeOptionsSelectAndAddDummy('#organizacionform_ciudadId', '--Seleccione--');
        } else {
            utilInput.populateSelect('#organizacionform_ciudadId', data, 'ciudadId', 'nombre');
            if (organizacionJS.organizacion !== null) {
                if (organizacionJS.organizacion.ciudadId !== null) {
                    if (utilInput.existsOptionByValueInSelect('#organizacionform_ciudadId', organizacionJS.organizacion.ciudadId)) {
                        jQuery('#organizacionform_ciudadId').val(organizacionJS.organizacion.ciudadId);
                    } else {
                        jQuery('#organizacionform_ciudadId').val(0);
                    }
                }
            }
            jQuery('#organizacionform_ciudadId').trigger('change');
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
        organizacionJS.entity = entity;
        organizacionJS.headers = headers;
        organizacionJS.format = format;
        organizacionJS.reportName = jQuery('#key_organizacion_title').val() +
                '_' + utilMisc.getTodayDate('-');
        var criteriaExample = new OrganizacionDWR();
        if (organizacionJS.cacheDWR != null) {
            criteriaExample = organizacionJS.cacheDWR;
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
        OrganizacionDWR.getReportDataTest(sortBy, sortType, criteriaExample, organizacionJS.exportarDatosCallback);
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
                organizacionJS.entity + '&headers=' + organizacionJS.headers +
                '&reportName=' + organizacionJS.reportName + '&format=' + organizacionJS.format +
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
// Validaciones de organizacionform
validanguage.el.organizacionform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 150) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 150 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionform_nombreComercial = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 150) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 150 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionform_nombreCorto = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionform_rfc = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
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
validanguage.el.organizacionform_descripcion = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (text) {
                if (text.length > 300) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 3750 carácteres como máximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }
    ]};
validanguage.el.organizacionform_direccion = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace,ProfileRequiredField, ProfileCheckForInvalidCharacter,{
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
validanguage.el.organizacionform_direccionAlternativa = {
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
validanguage.el.organizacionform_nombreContacto = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace,ProfileRequiredField, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 150) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 150 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionform_correoContacto = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace,ProfileRequiredField,ProfileMail, ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 150) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 150 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionform_telefonoContacto = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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
validanguage.el.organizacionform_celularContacto = {
    characters: {
        mode: 'allow', expression: 'numeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter,{
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


validanguage.el.organizacionform_paisId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionform_estadoId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionform_ciudadId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionform_correoRemitente = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace,ProfileRequiredField,ProfileMail,ProfileCheckForInvalidCharacter,{
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

// Validaciones de organizacionCredencialform
validanguage.el.organizacionCredencialform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
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
validanguage.el.organizacionCredencialform_domain = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField,ProfileUrl ,ProfileCheckForInvalidCharacter,{
            name: function (text) {
                if (text.length > 150) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Solo se permiten 150 car&aacute;cteres como m&aacute;ximo.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionCredencialform_path = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionCredencialform_aplicacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                if (jQuery('#organizacionCredencialform_aplicacionId').val() <= 0) {
                    return false;
                } else {
                    return true;
                }
            },
            errorMsg: 'Seleccione un registro.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionCredencialform_idanalytics = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
            if(jQuery('#organizacionCredencialform_idanalytics').val().length < 200){
                if (jQuery('#organizacionCredencialform_aplicacionId').val() === Number(2)) {//Web
                    return jQuery('#organizacionCredencialform_idanalytics').val() !== "";
                } else if (jQuery('#organizacionCredencialform_aplicacionId').val() === Number(3)) {//Taquilla
                    
                    return true;
                } else {
                    return true;
                }
            }else{
                return false;
            }
            },
            errorMsg: 'El campo no es correcto',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionCredencialform_idapp = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                if(jQuery('#organizacionCredencialform_idapp').val().length < 200){
                    if (jQuery('#organizacionCredencialform_aplicacionId').val() === Number(2)) {//Web
                        return jQuery('#organizacionCredencialform_idapp').val() !== "";
                    } else if (jQuery('#organizacionCredencialform_aplicacionId').val() === Number(3)) {//Taquilla
                        return true;
                    } else {
                        return true;
                    }
                }else{
                    return false;
                }
            },
            errorMsg: 'El campo no es correcto.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};
validanguage.el.organizacionCredencialform_idrecapcha = {
    characters: {
        mode: 'allow', expression: 'alphanumeric ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {
            name: function (_evt) {
                if(jQuery('#organizacionCredencialform_idrecapcha').val().length < 200){
                    if (jQuery('#organizacionCredencialform_aplicacionId').val() === Number(2)) {//Web
                        return jQuery('#organizacionCredencialform_idrecapcha').val() !== "";
                    } else if (jQuery('#organizacionCredencialform_aplicacionId').val() === Number(3)) {//Taquilla
                        return true;
                    } else {
                        return true;
                    }
                }else{
                    return false;
                }
            },
            errorMsg: 'El campo no es correcto.',
            onerror: utilEffect.showValidationTooltip,
            onsuccess: utilEffect.hideValidationTooltip
        }]};

// Validaciones de organizacionUsuarioform
validanguage.el.organizacionUsuarioform_organizacionId = {
    characters: {
        mode: 'allow', expression: 'numeric-', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionUsuarioform_usuarioAdmon = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionUsuarioform_contrasena = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionUsuarioform_confirmarContrasena = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileCheckForInvalidCharacter, {name: function () {
                return !!(this.value == jQuery('#organizacionUsuarioform_contrasena').val());
            }, errorMsg: 'La contraseña es diferente', onerror: utilEffect.showValidationTooltip, onsuccess: utilEffect.hideValidationTooltip}]};
validanguage.el.organizacionUsuarioform_nombre = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionUsuarioform_apellidoPaterno = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionUsuarioform_apellidoMaterno = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};
validanguage.el.organizacionUsuarioform_correo = {
    characters: {
        mode: 'allow', expression: 'alphanumericspecial ', suppress: false},
    validations: [
        ProfileStripWhitespace, ProfileRequiredField, ProfileCheckForInvalidCharacter]};

