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
        if (stompClient) {
            
            var nombrePaciente = $('#buscar_paciente').val();
            var psicologoId =$('#psicologo_id').val();
            var pacienteId =$('#usu_id').val();
            
            localStorage.setItem('pacienteId',pacienteId);
            localStorage.setItem('psicologoId',psicologoId);
            var usuario = {
                usu_id: psicologoId,
                usu_codigo: '',
                usu_contraseña: '',
                est_usu_id: 1
            };

            var estadoNotificacion = {
                estNotId: 2,
                estNotNombre: 'No Visto'
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
                notDescripcion: 'Se va a realizar una cita con el paciente '+nombrePaciente,
                usuEnvio: pacienteId,
                notFecha: null,
                notUrl: '/GestionarAgenda',
                tipNotId: tipoNotificacion,
                estNotId: estadoNotificacion
            };

            stompClient.send("/app/chat.send", {}, JSON.stringify(notificacion));
            
        }
    }

    function onMessageReceived(payload) {
        var message = JSON.parse(payload.body);

        var primerElemento = $('<li></li>');

        var segundoElemento = $('<div class="media"></div>');
        
        var medioElemento =$('<img class="img-radius" src="/images/avatar-3.jpg" alt="Generic placeholder image"/>');

        var elementoBody = $('<div class="media-body"></div>'); 
         
        var tercerElemento = $('<h5 class="notification-user"></h5>').text(message.usuId.usu_codigo);

        var cuartoElemento = $('<p class="notification-msg"></p>').text(message.notDescripcion);

        var quintoElemento = $('<span class="notification-time"></span>').text(message.notFecha);

        elementoBody.append(tercerElemento, cuartoElemento, quintoElemento);
        
        segundoElemento.append(medioElemento,elementoBody);

        primerElemento.append(segundoElemento);

        $("#notificaciones"+message.usuId.usu_id).append(primerElemento);


    }
    connect();
    $("#btnRegistrarCita").click(send);
    
    var fobiaId;
var pacienteId;
var psicologoId;
var actualizarCita = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00');
    var citId = document.getElementById('citId').value;

    var cita = {
        citId: citId,
        citFechaHoraInicio: horafechaIni,
        citFechaHoraFin: horaFechaFin,
        citMotivo: motivoCita
    };

    var pacId = pacienteId;
    var fobId = fobiaId;
    var psicoId = psicologoId;

    var citaJson = {
        cita: cita,
        pacienteId: pacId,
        fobiaId: fobId,
        psicologoId: psicoId
    };

    $.ajax({
        url: "/api/recepcionista/actualizarCita",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(citaJson),
        success: function (data) {
            if (data === "1") {
                Swal.fire({
                    type: 'success',
                    title: 'Cita Actualizada Correctamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/Recepcionista/GestionarCitas";
                    }
                });
            }
        }
    });
};

var registrarCita = function () {



    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00')

    var cita = {
        citId: 0,
        citFechaHoraInicio: horafechaIni,
        citFechaHoraFin: horaFechaFin,
        citMotivo: motivoCita
    };

    var pacId = pacienteId;
    var fobId = fobiaId;
    var psicoId = psicologoId;

    var citaJson = {
        cita: cita,
        pacienteId: pacId,
        fobiaId: fobId,
        psicologoId: psicoId
    };

       


        $.ajax({
            url: "/api/recepcionista/ingresarCita",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(citaJson),
            success: function (data) {
                if (data === "1") {
                     send();
                    
                    Swal.fire({
                        type: 'success',
                        title: 'Cita Registrada Correctamente',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.value) {
                            
                            window.location.href = "/Recepcionista/GestionarCitas";
                        }
                    });
                }
            }
        });

    };

var validacionCitaRegistro = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var VFechaCita = validarFechaCita('citFechaError', fechaCita, 'citFecha');
    var VhoraIniCita = validarHoraIniCita('fechaHoraInicioError', horaIniCita, 'fechaHoraInicio');
    var VhoraFinCita = validarHoraFinCita('fechaHoraFinError', horaFinCita, 'fechaHoraFin');
    var VmotivoCita = validarMotivoCita('citMotivoError', motivoCita, 'citMotivo');
    var VpacienteCita = validarPacienteCita('buscar_pacienteError', pacienteCita, 'buscar_paciente');
    var VfobiaCita = validarFobiaCita('buscar_fobiaError', fobiaCita, 'buscar_fobia');
    var VpsicologoCita = validarPsicologoCita('buscar_psicologoError', psicologoCita, 'buscar_psicologo');

    if (VFechaCita === false || VmotivoCita === false || VhoraIniCita === false ||
            VhoraFinCita === false || VpacienteCita === false || VfobiaCita === false || VpsicologoCita === false) {
    } else {
        registrarCita();
    }
};


var validacionCitaActualizaion = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var VFechaCita = validarFechaCita('citFechaError', fechaCita, 'citFecha');
    var VhoraIniCita = validarHoraIniCita('fechaHoraInicioError', horaIniCita, 'fechaHoraInicio');
    var VhoraFinCita = validarHoraFinCita('fechaHoraFinError', horaFinCita, 'fechaHoraFin');
    var VmotivoCita = validarMotivoCita('citMotivoError', motivoCita, 'citMotivo');
    var VpacienteCita = validarPacienteCita('buscar_pacienteError', pacienteCita, 'buscar_paciente');
    var VfobiaCita = validarFobiaCita('buscar_fobiaError', fobiaCita, 'buscar_fobia');
    var VpsicologoCita = validarPsicologoCita('buscar_psicologoError', psicologoCita, 'buscar_psicologo');

    if (VFechaCita === false || VmotivoCita === false || VhoraIniCita === false ||
            VhoraFinCita === false || VpacienteCita === false || VfobiaCita === false || VpsicologoCita === false) {
    } else {
        //actualizarCita();
        actualizarCita();
    }
};



var validarHoraIniCita = function (elementoError, valor, elemento) {
    var horaIni = new Date();
    var minimalHour = new Date();
    var param = valor.split(':');
    horaIni.setHours(param[0], param[1], 0);
    minimalHour.setHours(9, 0, 0);
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Hora de inicio obligatoriamente");
        $('#' + elemento).keyup(keyhorainicita);
        return false;
    } else if (horaIni <= minimalHour) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La hora de Inicio debe ser mayor a las 9 :00 A.M. [Hora de apertura]");
        $('#' + elemento).keyup(keyhorainicita);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keyhorainicita);
        return true;
    }
};

var keyhorainicita = function () {
    $valor = $('#fechaHoraInicio');
    var horaIni = new Date();
    var minimalHour = new Date();
    var param = $valor.val().split(':');
    horaIni.setHours(param[0], param[1], 0);
    minimalHour.setHours(9, 0, 0);
    if ($valor.val() === "") {
        addNegativeAttributtes('fechaHoraInicio');
        addNegativeHtml('fechaHoraInicioError', "Debe ingresar un Hora de inicio obligatoriamente");
        return false;
    } else if (horaIni <= minimalHour) {
        addNegativeAttributtes('fechaHoraInicio');
        addNegativeHtml('fechaHoraInicioError', "La hora de Inicio debe ser mayor a las 9 :00 A.M. [Hora de apertura]");
        return false;
    } else {
        addPositiveAtributtes('fechaHoraInicio');
        addPositiveHtml('fechaHoraInicioError', 'Correcto');
        return true;
    }
};

var validarHoraFinCita = function (elementoError, valor, elemento) {
    var horafin = new Date();
    var maximalHour = new Date();
    var param = valor.split(':');
    horafin.setHours(param[0], param[1], 0);
    maximalHour.setHours(22, 0, 0);
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Hora de Finalizacion obligatoriamente");
        $('#' + elemento).keyup(keyhorafincita);
        return false;
    } else if (horafin > maximalHour) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La hora de finalizacion debe ser menor a las 10:00 P.M. [Hora de cierre]");
        $('#' + elemento).keyup(keyhorafincita);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keyhorafincita);
        return true;
    }
};

var keyhorafincita = function () {
    $valor = $('#fechaHoraFin');
    var horafin = new Date();
    var maximalHour = new Date();
    var param = $valor.val().split(':');
    horafin.setHours(param[0], param[1], 0);
    maximalHour.setHours(22, 0, 0);
    if ($valor.val() === "") {
        addNegativeAttributtes('fechaHoraFin');
        addNegativeHtml('fechaHoraFinError', "Debe ingresar un Hora de Finalizacion obligatoriamente");
        return false;
    } else if (horafin > maximalHour) {
        addNegativeAttributtes('fechaHoraFin');
        addNegativeHtml('fechaHoraFinError', "La hora de finalizacion debe ser menor a las 10:00 P.M. [Hora de cierre]");
        return false;
    } else {
        addPositiveAtributtes('fechaHoraFin');
        addPositiveHtml('fechaHoraFinError', 'Correcto');
        return true;
    }
};

var validarFobiaCita = function (elementoError, valor, elemento) {
    var term = valor;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Fobia Obligatoriamente");
        $('#' + elemento).keyup(keyfobiacita);
        return false;
    } else {
        $.ajax({
            url: "/api/recepcionista/verificarFobia",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data !== "0") {
                    addPositiveAtributtes(elemento);
                    addPositiveHtml(elementoError, "Correcto");
                    $('#' + elemento).keyup(keyfobiacita);
                    fobiaId = data;
                    return true;
                } else {
                    addNegativeAttributtes(elemento);
                    addNegativeHtml(elementoError, "La Fobia Ingresada no Existe");
                    $('#' + elemento).keyup(keyfobiacita);
                    return false;
                }
            }
        });
    }
};

var keyfobiacita = function () {
    var $fobiaCita = $('#buscar_fobia');
    var term = $fobiaCita.val();
    if ($fobiaCita.val() === "") {
        addNegativeAttributtes('buscar_fobia');
        addNegativeHtml('buscar_fobiaError', "Debe ingresar un Fobia Obligatoriamente");
        return false;
    } else {
        $.ajax({
            url: "/api/recepcionista/verificarFobia",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data !== "0") {
                    addPositiveAtributtes('buscar_fobia');
                    addPositiveHtml('buscar_fobiaError', "Correcto");
                    fobiaId = data;
                    return true;
                } else {
                    addNegativeAttributtes('buscar_fobia');
                    addNegativeHtml('buscar_fobiaError', "La Fobia Ingresada no Existe");
                    return false;
                }
            }
        });
    }
};

var validarPsicologoCita = function (elementoError, valor, elemento) {
    var term = valor;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Psicologo obligatoriamente");
        $('#' + elemento).keyup(keynombrepaciente);
        return false;
    } else {
        $.ajax({
            url: "/api/recepcionista/verificarPsicologo",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data !== "0") {
                    addPositiveAtributtes(elemento);
                    addPositiveHtml(elementoError, "Correcto");
                    $('#' + elemento).keyup(keynombrepaciente);
                    psicologoId = data;
                    return true;
                } else {
                    addNegativeAttributtes(elemento);
                    addNegativeHtml(elementoError, "El Piscologo Ingresado no Existe");
                    $('#' + elemento).keyup(keynombrepaciente);
                    return false;
                }
            }
        });
    }
};

var keynombrepsicologo = function () {
    var $psicologo = $('#buscar_psicologo');
    var term = $psicologo.val();
    if ($psicologo.val() === "") {
        addNegativeAttributtes('buscar_psicologo');
        addNegativeHtml('buscar_psicologoError', "Debe ingresar un Psicologo obligatoriamente");
        return false;
    } else {
        $.ajax({
            url: "/api/recepcionista/verificarPsicologo",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data !== "0") {
                    addPositiveAtributtes('buscar_psicologo');
                    addPositiveHtml('buscar_psicologoError', "Correcto");
                    psicologoId = data;
                    return true;
                } else {
                    addNegativeAttributtes('buscar_psicologo');
                    addNegativeHtml('buscar_psicologoError', "El Psicologo Ingresado no Existe");
                    return false;
                }
            }
        });
    }
};

var validarPacienteCita = function (elementoError, valor, elemento) {
    //verificarNombrePaciente(valor);
    var term = valor;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un paciente Obligatoriamente");
        $('#' + elemento).keyup(keynombrepaciente);
        return false;
    } else {
        $.ajax({
            url: "/api/recepcionista/verificarNombre",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data !== "0") {
                    addPositiveAtributtes(elemento);
                    addPositiveHtml(elementoError, "Correcto");
                    $('#' + elemento).keyup(keynombrepaciente);
                    pacienteId = data;
                    return true;
                } else {
                    addNegativeAttributtes(elemento);
                    addNegativeHtml(elementoError, "El Paciente Ingresado no Existe");
                    $('#' + elemento).keyup(keynombrepaciente);
                    return false;
                }
            }
        });
    }

    /*else if (verificarNombrePaciente(valor) === 0) {
     addNegativeAttributtes(elemento);
     addNegativeHtml(elementoError, "Paciente Actualmente Inexistente");
     $('#' + elemento).keyup(keynombrepaciente);
     return false;
     } else {
     addPositiveAtributtes(elemento);
     addPositiveHtml(elementoError, "Correcto");
     $('#' + elemento).keyup(keynombrepaciente);
     return false;
     }*/
};
var keynombrepaciente = function () {
    var nombrepaciente = $('#buscar_paciente');
    var term = nombrepaciente.val();
    if (nombrepaciente.val() === "") {
        addNegativeAttributtes('buscar_paciente');
        addNegativeHtml('buscar_pacienteError', "Debe ingresar un paciente Obligatoriamente");
        return false;
    } else {
        $.ajax({
            url: "/api/recepcionista/verificarNombre",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data !== "0") {
                    addPositiveAtributtes('buscar_paciente');
                    addPositiveHtml('buscar_pacienteError', "Correcto");
                    pacienteId = data;
                    return true;
                } else {
                    addNegativeAttributtes('buscar_paciente');
                    addNegativeHtml('buscar_pacienteError', "El Paciente Ingresado no Existe");
                    return false;
                }
            }
        });
    }
};

var obtenerData = function (valNombreRes) {
    validacionNombre = valNombreRes;
    return validacionNombre;
};
var validarMotivoCita = function (elementoError, valor, elemento) {
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    if (elemento === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar un Motivo Obligatoriamente');
        $('#' + elemento).keyup(keymotivocita);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'El motivo no puede poseer espacias a los extremos, minimo 2 caraceters');
        $('#' + elemento).keyup(keymotivocita);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keymotivocita);
        return true;
    }
};
var keymotivocita = function () {
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    var $motivoCita = $('#citMotivo');
    if ($motivoCita.val() === "") {
        addNegativeAttributtes('citMotivo');
        addNegativeHtml('citMotivoError', 'Debe ingresar un Motivo Obligatoriamente');
        return false;
    } else if (!$motivoCita.val().match(regex)) {
        addNegativeAttributtes('citMotivo');
        addNegativeHtml('citMotivoError', 'El motivo no puede poseer espacias a los extremos, minimo 2 caraceters');
        return false;
    } else {
        addPositiveAtributtes('citMotivo');
        addPositiveHtml('citMotivoError', 'Correcto');
        return true;
    }
};
var validarFechaCita = function (elementoError, valor, elemento) {
    var fechaActual = new Date();
    var fechaCitIngresada = new Date(valor);
    fechaCitIngresada.setDate(fechaCitIngresada.getDate() + 1);
    fechaActual.setHours(0, 0, 0, 0);
    fechaCitIngresada.setHours(0, 0, 0, 0);
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar una fecha  Obligatoriamente");
        $('#' + elemento).keyup(keyfechacita);
        return false;
    } else if (fechaCitIngresada < fechaActual) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La fecha de la cita no puede ser anterior a la Fecha Actual");
        $('#' + elemento).keyup(keyfechacita);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#' + elemento).keyup(keyfechacita);
        return true;
    }
};
var keyfechacita = function () {
    var $fechacita = $('#citFecha');
    var fechaActual = new Date();
    var fechaCitIngresada = new Date($('#citFecha').val());
    fechaCitIngresada.setDate(fechaCitIngresada.getDate() + 1);
    fechaActual.setHours(0, 0, 0, 0);
    fechaCitIngresada.setHours(0, 0, 0, 0);
    if ($fechacita.val() === "") {
        addNegativeAttributtes('citFecha');
        addNegativeHtml('citFechaError', "Debe ingresar una fecha Obligatoriamente");
        return false;
    } else if (fechaCitIngresada < fechaActual) {
        addNegativeAttributtes('citFecha');
        addNegativeHtml('citFechaError', "La fecha de la cita no puede ser anterior a la Fecha Actual");
        return false;
    } else {
        addPositiveAtributtes('citFecha');
        addPositiveHtml('citFechaError', "Correcto");
        return true;
    }
};
var addPositiveAtributtes = function (id) {
    $("#" + id).removeClass('is-invalid');
    $("#" + id).addClass('is-valid');
};
var addPositiveAtributtesJS = function (id) {
    document.getElementById(id).classList.remove('is-invalid');
    document.getElementById(id).classList.add('is-valid');
};
var addNegativeAttributtes = function (id) {
    $("#" + id).removeClass('is-valid');
    $("#" + id).addClass('is-invalid');
};
var addNegativeAttributtesJS = function (id) {
    document.getElementById(id).classList.remove('is-valid');
    document.getElementById(id).classList.add('is-invalid');
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
$('#registrarCitas').click(validacionCitaRegistro);
$('#actualizarCitas').click(validacionCitaActualizaion);


    
    
    
    
});

