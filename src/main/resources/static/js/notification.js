'use strict';
$(function () {
    var stompClient = null;
    var username = null;

    function connect(event) {
        var socket = new SockJS('/javatechie');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);

    }

    function onConnected() {
        stompClient.subscribe('/topic/public', onMessageReceived);

    }

    function onError(error) {
        return true;
    }

    function send(event) {
        var messageContent = $("#notificacionMensaje").val();

        if (messageContent && stompClient) {
            var usuario = {
                usu_id: 17,
                usu_codigo: 'GerhadEgg',
                usu_contraseña: '',
                est_usu_id: 1
            };

            var estadoNotificacion = {
                estNotId: 1,
                estNotNombre: 'Visto'
            };

            var fecha = new Date();

            var tipoNotificacion = {
                tipNotId: 1,
                tipNotNombre: 'Cita',
                tipNotDescripcion: 'Se notifica cuando se le realizo una cita'
            };

            var notificacion = {
                usuId: usuario,
                notNombre: 'Realización de una cita',
                notDescripcion: 'Se va a realizar una cita con el paciente Colomobo',
                usuEnvio: 19,
                notFecha: null,
                notUrl: 'Psicologo/Index',
                tipNotId: tipoNotificacion,
                estNotId: estadoNotificacion
            };

            stompClient.send("/app/chat.send", {}, JSON.stringify(notificacion));
        }
        event.preventDefault();
    }

    function onMessageReceived(payload) {
        var message = JSON.parse(payload.body);

        var primerElemento = $('<li></li>');

        var segundoElemento = $('<div class="media"></div>');
        
        var medioElemento =$('<img class="img-radius" src="/images/avatar-3.jpg" alt="Generic placeholder image"/>');

        var tercerElemento = $('<h5 class="notification-user"></h5>').text(message.usuId.usu_codigo);

        var cuartoElemento = $('<p class="notification-msg"></p>').text(message.notDescripcion);

        var quintoElemento = $('<span class="notification-time"></span>').text(message.notFecha);

        segundoElemento.append(medioElemento, tercerElemento, cuartoElemento, quintoElemento);

        primerElemento.append(segundoElemento);

        $("#notificaciones"+"18").append(primerElemento);


    }
   $("#btnRegistrar").click(connect);
   $("#btnEnviar").click(send);
});
