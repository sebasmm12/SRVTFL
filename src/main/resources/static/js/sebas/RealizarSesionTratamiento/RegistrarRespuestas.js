/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*$(document).ready(function(){
 $('#btnRegistrar').on('click',function(event){
 $('#exampleModal').modal();
 });
 });*/


var RegistrarRespuestas = function () {

    var validacion = validar();

    if (validacion === true) {
        var Respuestas = document.querySelectorAll(".respuestas");

        var PreguntasId = document.querySelectorAll(".Id");

        var listRespuesta = [];

        var citId = document.getElementById('citId').value;
        var primCit = document.getElementById('primCit').value;

        for (var i = 0; i < Respuestas.length; i++) {
            var RespuestaJson = new Object();
            RespuestaJson.resId = null;
            RespuestaJson.resRespuesta = Respuestas[i].value;
            RespuestaJson.citId = parseInt($("#citId").val(), 10);
            RespuestaJson.pregId = parseInt(PreguntasId[i].value, 10);
            listRespuesta.push(RespuestaJson);
        }
        $.ajax({
            url: '/api/sesion/registrar',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(listRespuesta),
            success: function (data) {
                Swal.fire({
                    type: 'success',
                    title: 'Se registro las respuestas exitosamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/psicologo/RealizarSesion?citId=" + citId;
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
            },
            complete: function (jqXHR, textStatus) {
            }
        });
    }
};

var validar = function () {

    var number = 0;

    $(".respuestas").each(function () {
        var res = $(this);
        var data_id = $(this).attr("data-id");
        var validar = ValidacionRespuesta('Respuesta' + data_id, res.val(), 'RespuestaError' + data_id);
        if (validar === false) {
            number++;
        }
    });

    if (number !== 0) {
        return false;
    } else {
        return true;
    }
};

var ValidacionRespuesta = function (element, val, elementError) {

    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar una respuesta');
        $('#' + element).keyup(KeyRespuesta);
        return false;
    } else {
        if (val.match(RegularExpression)) {
            addPositiveAtributtes(element);
            addPositiveHtml(elementError, 'Correcto!');
            return true;
        } else {
            addNegativeAttributtes(element);
            addNegativeHtml(elementError, 'Debe ingresar una respuesta');
            $('#' + element).keyup(KeyRespuesta);
            return false;
        }
    }

};

var KeyRespuesta = function () {
    var Respuesta = $(this);
    var id = Respuesta.attr("id");
    var Error = 'RespuestaError' + Respuesta.attr("data-id");
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    if (Respuesta.val() === "") {
        addNegativeAttributtes(id);
        addNegativeHtml(Error, 'Debe ingresar una respuesta');
        return false;
    } else {
        if (Respuesta.val().match(RegularExpression)) {
            addPositiveAtributtes(id);
            addPositiveHtml(Error, 'Correcto!');
            return true;
        } else {
            addNegativeAttributtes(id);
            addNegativeHtml(Error, 'Debe ingresar una respuesta');
            return false;
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


$("#btnRegistrarRespuestas").on('click', function () {
    var primeraCita = document.getElementById('primCit').value;
    if (primeraCita === "false") {
        RegistrarRespuestas();
    } else if(primeraCita === "true"){
        var validacion = validar();
        if (validacion === true) {
            $('#registroModal').modal();
        }
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



$('#btnRegistrarTratamiento').on('click', function () {
    var cantidadCitas = $('#cantidadCitas').val();
    var VcantidadCitas = validacionCantidadCitas('cantidadCitas', cantidadCitas, 'cantidadCitasError');
    if (VcantidadCitas === true) {
        var Respuestas = document.querySelectorAll(".respuestas");

        var PreguntasId = document.querySelectorAll(".Id");

        var listRespuesta = [];

        var citId = document.getElementById('citId').value;
        var primCit = document.getElementById('primCit').value;

        var cantcitas = document.getElementById('cantidadCitas').value;
        var tipTrat = document.getElementById("tipoTrat").value;
        /*var tipTrat = tipoTrat.options[tipoTrat.selectedIndex].text;
        var fobId = tipoTrat.options[tipoTrat.selectedIndex].value;*/
        var fobId =document.getElementById("fobId").value;
        var paciente = document.getElementById('pacId').value;

        for (var i = 0; i < Respuestas.length; i++) {
            var RespuestaJson = new Object();
            RespuestaJson.resId = null;
            RespuestaJson.resRespuesta = Respuestas[i].value;
            RespuestaJson.citId = parseInt($("#citId").val(), 10);
            RespuestaJson.pregId = parseInt(PreguntasId[i].value, 10);
            listRespuesta.push(RespuestaJson);
        }

        var tratamiento = new Object();
        tratamiento.citId = parseInt(citId);
        tratamiento.tratCantidadCitas = parseInt(cantcitas);
        tratamiento.tratTipo = tipTrat;
        tratamiento.tratPrimeraVez = true;
        tratamiento.fobId = fobId;
        tratamiento.pacienteId = paciente;

        var tratamientojson = {
            tratamiento: tratamiento,
            respuestas: listRespuesta
        };

        $.ajax({
            url: '/api/sesion/registrarTratamiento',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(tratamientojson),
            success: function (data) {
                Swal.fire({
                    type: 'success',
                    title: 'Se registro el Tratamiento Exitosamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "RealizarSesionTratamiento";
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
            },
            complete: function (jqXHR, textStatus) {
            }
        });
    }

});

document.getElementById('btnFinalizar').addEventListener('click', function () {
    Swal.fire({
        type: 'error',
        icon: 'error',
        title: 'Alerta',
        text: 'Se Cancelo un tratamiento!',
        footer: 'El paciente no tiene fobias'
    }).then((result) => {
        if (result.value) {
            window.location.href = "RealizarSesionTratamiento";
        }
    });
});


