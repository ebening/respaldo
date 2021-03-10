var bitacoraJS = {

    inicializarGrid: function () {
             
       jQuery('#bitacoraGrid').jqGrid({
            url: '',
            datatype: '',
            colNames: [
                jQuery('#key_bitacora_title').val(),
                'Fecha y Hora',
                'Proceso',
                'Procedimiento',
                'Mensaje',
                'Cantidad',
                jQuery('#key_organizacion').val(),
                jQuery('#key_ejecucion').val(),
                'Estatus'
//                jQuery('bitacoraId'),
//                jQuery('fechaHora'),
//                jQuery('proceso'),
//                jQuery('procedimiento'),
//                jQuery('cantidad'),
//                jQuery('mensaje'),
//                jQuery('organizacionId'),
//                jQuery('ejecucionId'),
//                jQuery('ejecucionId'),
//                jQuery('estatusId')
            ],
            colModel: [{
                    name: 'bitacoraId',
                    index: 'bitacoraId',
                    align: 'center',
                    //width: 50,
                    resizable: false,
                    hidden: true,
                    search: false
                }, {
                    name: 'fechaHora',
                    index: 'fechaHora',
                    align: 'center',
                    width: 25,
                    resizable: false,
                    hidden: false,
                    search: false

                },{
                    name: 'proceso',
                    index: 'proceso',
                    align: 'center',
                    width: 60,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'procedimiento',
                    index: 'procedimiento',
                    align: 'center',
                    width: 60,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'mensaje',
                    index: 'mensaje',
                    align: 'center',
                    resizable: false,
                    hidden: false,
                    search: false
                },  {
                    name: 'cantidad',
                    index: 'cantidad',
                    align: 'center',
                    width: 20,
                    resizable: false,
                    hidden: false,
                    search: false
                },{
                    name: 'organizacion',
                    index: 'organizacion',
                    align: 'center',
                    width: 35,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'ejecucion',
                    index: 'ejecucion',
                    align: 'center',
                     width: 30,
                    resizable: false,
                    hidden: false,
                    search: false
                }, {
                    name: 'estatusId',
                    index: 'estatusId',
                    align: 'center',
                     width: 20,
                    resizable: false,
                    hidden: false,
                    search: false
                }],
            height: 180,
            toolbar: false,
            hidegrid: true,
            multiselect: false,
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            pager: jQuery('#bitacoraGridPagerId'),
            caption: jQuery('#key_bitacora_title').val()
    });

         jQuery('#bitacoraGrid').setGridWidth(
               jQuery("#gbox_" + "bitacoraGrid").closest(".grid-container").width() - 5);

//        Inicializaciones extra para el grid
       utilGrid.setup('#bitacoraGrid', '#parametroGridPagerId', '#parametroGridCurrentPage', '#parametroGridOrderByColumn',
               '#parametroGridOrderByType', 'parametroJS');
               
    //utilGrid.setHideElementsTable('bitacoraGridPagerId',['activate','search', 'export', 'delete']);

    }
};

