/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var validar = function () {

    var fechaInicio = $('#fechaInicioCita');

    var validarFechaInicio = ValidacionFechaInicio('fechaInicioCita', fechaInicio.val(), 'FechaCitaError');    

    var citId = $(this).attr("data-id");

    if (validarFechaInicio === false) {
        return false;
    } else {
        
        $.ajax({
           url:'/api/sesion/registrarFecha',
            type: 'POST',
            data:{
                fecha: fechaInicio.val(),
                citId: parseInt(citId,10)
            },
            success: function (data) {
                 if (data === "1") {
                 Swal.fire({
                    type: 'success',
                    title: 'Se registro el diagnóstico exitosamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/psicologo/RealizarPreguntas?citId="+citId;
                    }
                });
            }
                
            },
            error: function (jqXHR, textStatus, errorThrown) { 
            },
            complete: function (jqXHR, textStatus) {    }
        });
    }
};

var ValidacionFechaInicio = function (element, val, elementError) {

    var fechaIngresada = new Date($("#" + element).val());
    var fechaActual = new Date();
    fechaActual.setHours(0, 0, 0, 0);
    fechaIngresada.setHours(0, 0, 0, 0);
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar una fecha');
        $("#" + element).change(ChangeFechaInicio);
        return false;
    } else {
        if (fechaIngresada < fechaActual || fechaIngresada > fechaActual) {
            addNegativeAttributtes(element);
            addNegativeHtml(elementError, 'Debe ingresar una fecha válida');
            $("#" + element).change(ChangeFechaInicio);
            return false;
        } else {
            addPositiveAtributtes(element);
            addPositiveHtml(elementError, 'Correcto !');
            return true;
        }
    }
};

var ChangeFechaInicio = function () {
    var fechaIngresada = new Date($("#fechaInicioCita").val());
    var fechaActual = new Date();
    fechaActual.setHours(0, 0, 0, 0);
    fechaIngresada.setHours(0, 0, 0, 0);
    if ($("#fechaInicioCita").val() === "") {
        addNegativeAttributtes('fechaInicioCita');
        addNegativeHtml('FechaCitaError', 'Debe ingresar una fecha');
        return false;
    } else {
        if (fechaIngresada < fechaActual || fechaIngresada > fechaActual) {
            addNegativeAttributtes('fechaInicioCita');
            addNegativeHtml('FechaCitaError', 'Debe ingresar una fecha válida');
            return false;
        } else {
            addPositiveAtributtes('fechaInicioCita');
            addPositiveHtml('FechaCitaError', 'Correcto !');
            return true;
        }
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

$('#btnIniciarCita').click(validar);