/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var validacionFiltros = function () {
    var nombrePaciente = $("#nombrePaciente").val();
    var datetime = $("#datetime").val();
    var FiltroFecha = document.getElementById('selectFiltroFecha');
    var selectFiltroFecha = FiltroFecha.options[FiltroFecha.selectedIndex].innerText;
    var FiltroPaciente = document.getElementById('selectFiltroPaciente');
    var selectFiltroPaciente = FiltroPaciente.options[FiltroPaciente.selectedIndex].innerText;

    var validacionNombrePaciente = validarNombrePaciente("nombrePacienteError", nombrePaciente, "nombrePaciente");
    var validacionFecha = validarFecha("dateTimeError", datetime, "datetime");

    if (validacionNombrePaciente === false || validacionFecha === false) {

    } else {
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
    }

};

var validarNombrePaciente = function (elementError, val, element) {
    var RegularExpression = /^([A-Za-záéíóúñ])+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un nombre');
        $("#" + element).keyup(KeyNombrePaciente);
    } else {
        if (val.match(RegularExpression)) {
            addPositiveAtributtes(element);
            addPositiveHtml(elementError, 'Correcto !');
        } else {
            addNegativeAttributtes(element);
            addNegativeHtml(elementError, 'Debe ingresar un nombre válido');
            $("#" + element).keyup(KeyNombrePaciente);
        }
    }
};

var KeyNombrePaciente = function () {
    var RegularExpression = /^([A-Za-záéíóúñ])+$/;
    var nombrePac = $("#nombrePaciente");
    if (nombrePac.val() === "") {
        addNegativeAttributtes('nombrePaciente');
        addNegativeHtml('nombrePacienteError', 'Debe ingresar un nombre');
    } else {
        if (nombrePac.val().match(RegularExpression)) {
            addPositiveAtributtes('nombrePaciente');
            addPositiveHtml('nombrePacienteError', 'Correcto !');
        } else {
            addNegativeAttributtes('nombrePaciente');
            addNegativeHtml('nombrePacienteError', 'Debe ingresar un nombre válido');
        }
    }
};

var validarFecha = function (elementError, val, element) {
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar una fecha');
        $("#" + element).change(keyFecha);
    } else {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto !');
    }
};

var keyFecha = function() {
    var dateTime = $("#datetime");
    if (dateTime.val() === "") {
        addNegativeAttributtes('datetime');
        addNegativeHtml('dateTimeError', 'Debe ingresar una fecha');
    } else {
        addPositiveAtributtes('datetime');
        addPositiveHtml('dateTimeError', 'Correcto !');
    }
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
