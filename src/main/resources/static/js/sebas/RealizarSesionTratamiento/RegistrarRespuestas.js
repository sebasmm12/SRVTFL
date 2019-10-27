/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var RegistrarRespuestas = function (){
    
    var Respuestas = document.querySelectorAll(".respuestas");
    
    var PreguntasId = document.querySelectorAll(".Id");
    
    var listRespuesta =[];
    
    for (var i = 0; i < Respuestas.length; i++) {
           var RespuestaJson = new Object();
           RespuestaJson.resId = null;
           RespuestaJson.resRespuesta = Respuestas[i].value;
           RespuestaJson.citaId = $("#citId").val();
           RespuestaJson.pregId = parseInt(PreguntasId[i].value,10);
           listRespuesta.push(RespuestaJson);
    }
    $.ajax({
        url:'/api/sesion/registrar',
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
                        window.location.href = "/psicologo/RealizarSesion";
                    }
                });
        },
        error: function (jqXHR, textStatus, errorThrown) {
        },
        complete: function (jqXHR, textStatus) {
        }
    });
    
};

$("#btnRegistrarRespuestas").click(RegistrarRespuestas);
