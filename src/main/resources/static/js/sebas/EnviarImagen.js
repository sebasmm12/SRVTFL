/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict'
var btnImagen = document.getElementById('btnImagen');
var sse;

btnImagen.addEventListener('click', function () {
    alert('con fe');
    var millisecondsToWait = 10;
    setTimeout(function () {
        sse = new EventSource('http://localhost:8080/psicologo/imagenSimulacion');
        sse.onmessage = function (evt) {
            
            $("#box").html(
                    $('<img>').attr('id', 'desktop').attr('src', 'data:image/jpeg;base64,'+evt.data));
        };
    }, millisecondsToWait);
});

