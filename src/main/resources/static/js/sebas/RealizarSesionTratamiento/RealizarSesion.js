/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var BuscarFiltros = function () {
    var nombrePaciente = $("#nombrePaciente").val();
    var datetime = $("#datetime").val();
    var FiltroFecha = document.getElementById('selectFiltroFecha');
    var selectFiltroFecha = FiltroFecha.options[FiltroFecha.selectedIndex].innerText;
    var FiltroPaciente = document.getElementById('selectFiltroPaciente');
    var selectFiltroPaciente = FiltroPaciente.options[FiltroPaciente.selectedIndex].innerText;

    $.ajax({
        url: "/api/sesion/buscar",
        type: 'GET',
        data: {
            nombrePaciente: nombrePaciente,
            datetime: datetime,
            page: 0,
            selectFiltroFecha: selectFiltroFecha,
            selectFiltroPaciente: selectFiltroPaciente
        },
        success: function (data) {
            var $newhtml = $(data);
            $("#tableCitas").replaceWith($newhtml);

        }, error: function (jqXHR, textStatus, errorThrown) {},
        complete: function (jqXHR, textStatus) {}
    });
};

var validacionFiltros = function () {
    var nombrePaciente = $("#nombrePaciente").val();
    var datetime = $("#datetime").val();
    
};



var addPositiveAtributtes = function (id) {
    $("#" + id).removeClass('is-invalid');
    $("#" + id).addClass('is-valid');
};

var addNegativeAttributtes = function (id) {
    $("#" + id).removeClass('is-valid');
    $("#" + id).addClass('is-invalid');
};

var addPositiveHtml = function (id, message) {
    $("#" + id).removeClass('negativeSpan');
    $("#" + id).addClass('positiveSpan');
    $("#" + id).html('');
    $("#" + id).html('<i class="fa fa-check"></i><label class="pt-2 pl-1">' + message + '</label>');
};

var addNegativeHtml = function (id, message) {
    $("#" + id).removeClass('positiveSpan');
    $("#" + id).addClass('negativeSpan');
    $("#" + id).html('');
    $("#" + id).html('<i class="fa fa-times"></i><label class="pt-2 pl-1">' + message + '</label>');
};


$("#btnFiltrar").click(validacionFiltros);
