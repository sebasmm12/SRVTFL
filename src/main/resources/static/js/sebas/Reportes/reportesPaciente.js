/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var BuscarReportes = function () {

    var nombrePaciente = $("#pacienteNombre").val();

    var validacionNombrePaciente = ValidarNombrePaciente("pacienteNombreError", nombrePaciente, "pacienteNombre");

    if (validacionNombrePaciente === false) {

    } else {
        
        $.ajax({
           url: '/api/reportes/buscar',
            type: 'GET',
            data:{
                nombrePaciente: nombrePaciente,
                page: 0
            },
            success: function (data) {
                var $newhtml =$(data);
                $("#tableReportes").replaceWith($newhtml);
            },
            error: function (jqXHR, textStatus, errorThrown) {},
            complete: function (jqXHR, textStatus) {}
        });
    }

};

var ValidarNombrePaciente = function (elementError, val, element) {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú]))+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un nombre');
        $("#" + element).keyup(KeyNombrePaciente);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;
    } else {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un nombre válido');
        $("#" + element).keyup(KeyNombrePaciente);
        return false;
    }
};

var KeyNombrePaciente = function() {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    var $nombrePaciente = $("#pacienteNombre");
    if ($nombrePaciente.val() === "") {
        addNegativeAttributtes('pacienteNombre');
        addNegativeHtml('pacienteNombreError', 'Debe ingresar un nombre');
        return false;
    } else if ($nombrePaciente.val().match(RegularExpression)) {
        addPositiveAtributtes('pacienteNombre');
        addPositiveHtml('pacienteNombreError', 'Correcto!');
        return true;
    } else {
        addNegativeAttributtes('pacienteNombre');
        addNegativeHtml('pacienteNombreError', 'Debe ingresar un nombre válido');
        return false;
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


$("#btnReporte").click(BuscarReportes);