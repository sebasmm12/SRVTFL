/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var validar = function () {

    var fechaInicio = $('#fechaInicioCita');

    var validarFechaInicio = ValidacionFechaInicio('fechaInicioCita', fechaInicio.val(), 'FechaCitaError');

    var citId = $(this).attr("data-id");
    var primCit = $(this).attr("prim-cit");

    if (validarFechaInicio === false) {
        return false;
    } else {

        $.ajax({
            url: '/api/sesion/registrarFecha',
            type: 'POST',
            data: {
                fecha: fechaInicio.val(),
                citId: parseInt(citId, 10)
            },
            success: function (data) {
                if (data === "1") {
                    Swal.fire({
                        type: 'success',
                        title: 'Se registro el diagnóstico exitosamente',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.value) {
                            window.location.href = "/psicologo/RealizarPreguntas?citId=" + citId;
                        }
                    });
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
            },
            complete: function (jqXHR, textStatus) { }
        });
    }
};

$("#btnModTratamiento").on('click', function () {
    var citId = $('#btnModTratamiento').attr("data-id");
    var href = "/api/sesion/obtenerTratamiento?citId=" + citId;
    $.get(href, function (tratamiento, status) {
        $('#modificacionModal #cantidadCitas').val(tratamiento.tratCantidadCitas);
        $("#modificacionModal #tipoTrat").val(tratamiento.fobId);
        console.log(tratamiento);
    });
    $('#modificacionModal').modal();
});

$('#btnModificarTratamiento').on('click', function () {

    var canCitas = document.getElementById('cantidadCitas').value;
    var VcantidadCitas = validacionCantidadCitas('cantidadCitas', canCitas, 'cantidadCitasError');
    if (VcantidadCitas === false) {
        return false;
    } else {

        var citId = document.getElementById('citId').value;
        var tratId = document.getElementById('tratId').value;
        var tipoTrat = document.getElementById("tipoTrat");
        var tipTratDesc = tipoTrat.options[tipoTrat.selectedIndex].text;
        var fobId = tipoTrat.options[tipoTrat.selectedIndex].value;
        var tratamiento = {
            tratId: tratId,
            citId: citId,
            tratCantidadCitas: canCitas,
            tratPrimeraVez: false,
            fobId: fobId,
            tratTipo: tipTratDesc
        };


        $.ajax({
            url: '/api/sesion/modificarTratamiento',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(tratamiento),
            success: function (data) {
                if (data === "1") {
                    Swal.fire({
                        type: 'success',
                        title: 'Se modifico el Tratamiento Exitosamente',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.value) {
                            window.location.href = "RealizarSesionTratamiento";
                        }
                    });
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            },
            complete: function (jqXHR, textStatus) { }
        });
    }
});

var validacionCantidadCitas = function (element, valor, elementError) {
    var cantidadCitas = $('#cantidadCitas').val();
    if (valor === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Este campo es Obligatorio');
        $("#" + element).change(changeCantidadCita);
        return false;
    } else if (valor < 0) {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'La cantidad no puede ser negativa');
        $("#" + element).change(changeCantidadCita);
        return false;
    } else {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto !');
        return true;
    }
};

var changeCantidadCita = function () {
    var cantidadCitas = $('#cantidadCitas');
    if (cantidadCitas.val() === "") {
        addNegativeAttributtes('cantidadCitas');
        addNegativeHtml('cantidadCitasError', 'Este campo es Obligatorio');
        return false;
    } else if (cantidadCitas.val() < 0) {
        addNegativeAttributtes('cantidadCitas');
        addNegativeHtml('cantidadCitasError', 'La cantidad no puede ser negativa');
        return false;
    } else {
        addPositiveAtributtes('cantidadCitas');
        addPositiveHtml('cantidadCitasError', 'Correcto !');
        return true;
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