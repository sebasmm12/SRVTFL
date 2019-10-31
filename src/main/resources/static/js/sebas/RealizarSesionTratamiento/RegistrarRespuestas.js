/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var RegistrarRespuestas = function () {

    var validacion = validar();

    if (validacion === true) {
        var Respuestas = document.querySelectorAll(".respuestas");

        var PreguntasId = document.querySelectorAll(".Id");

        var listRespuesta = [];

        var citId = document.getElementById('citId').value;

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

var KeyRespuesta = function (){
    var Respuesta =$(this);
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


$("#btnRegistrarRespuestas").click(RegistrarRespuestas);
