var registrarRespuesta = document.getElementById('btnRegistrarRespuestas');

registrarRespuesta.addEventListener('click', function () {

    var validacion = validar();
    if (validacion===true){
        var respuestas = document.querySelectorAll(".respuestas");
        var preguntas = document.querySelectorAll(".Id");


        var respArray = [];

        var citId = document.getElementById('citId').value;

        for (var i = 0; i < respuestas.length; i++) {
            var respuestaJson = new Object();
            respuestaJson.resId = null;
            respuestaJson.resRespuesta = respuestas[i].value;
            respuestaJson.citId = parseInt($("#citId").val(), 10);
            respuestaJson.pregId = parseInt(preguntas[i].value, 10);
            respArray.push(respuestaJson);
        }
        var tratamiento = new Object();
        tratamiento.tratId = 0;
        tratamiento.tratPrimeraVez = true;
        tratamiento.citId = $("#citId").val();

        var tratamientojson = {
            tratamiento: tratamiento,
            respuestas: respArray
        };

        $.ajax({
            url: '/Api/psicologo/registrarTratamiento',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(tratamientojson),
            success: function (data) {
                if (data === '1') {
                    Swal.fire({
                        type: 'success',
                        title: 'Resultados Registrados Correctamente',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.value) {
                            window.location.href = "/Psicologo/RealizarSesionTratamiento/ListarSesionesCitas";
                        }
                    });
                }
            }
        });
    }
});

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


$("#btnRegistrarRespuestas").click(RegistrarRespuestas);
