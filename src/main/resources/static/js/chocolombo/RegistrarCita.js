var fobiaId;
var pacienteId;
var psicologoId;
var actualizarCita = function () {
    
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var durCita = document.getElementById('duracionCita').value;
    //var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFinCita = obtenerHoraFinCita(horaIniCita, durCita);
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00');
    var citId = document.getElementById('citId').value;
    
    var cita = {
        citId: parseInt(citId),
        citFechaHoraInicio: horafechaIni,
        citFechaHoraFin: horaFechaFin,
        citMotivo: motivoCita
    };

    var citaJson = {
        cita: cita,
        documentoPaciente: documento,
        fobia: fobiaCita,
        documentoPsicologo: documentoP
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
    var durCita = document.getElementById('duracionCita').value;
    //var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFinCita = obtenerHoraFinCita(horaIniCita, durCita);
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00');

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
        documentoPaciente: documento,
        fobia: fobiaCita,
        documentoPsicologo: documentoP
    };
    $.ajax({
        url: "/api/recepcionista/ingresarCita",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(citaJson),
        success: function (data) {
            if (data === "1") {
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

function alertas() {
    alerta();
}
validacionCitaRegistroTratamiento = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var durCita = document.getElementById('duracionCita').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var horaFinCita = obtenerHoraFinCita(horaIniCita, durCita);
    var term = documento;

    var VFechaCita = validarFechaCita('citFechaError', fechaCita, 'citFecha');
    var VhoraIniCita = validarHoraIniCita('fechaHoraInicioError', horaIniCita, 'fechaHoraInicio');
    var VmotivoCita = validarMotivoCita('citMotivoError', motivoCita, 'citMotivo');
    var VduracionCita = validacionDuracionCita('duracionCitaError', durCita, 'duracionCita');
    var VDocPaciente = validarPaciente('buscar_pacienteError', documento, 'buscar_paciente');
    var VDocPsicologo = validarPsicologo('buscar_psicologoError', documentoP, 'buscar_psicologoError');
    var VpacienteCita = validarPacienteCita('buscar_pacienteError', pacienteCita, 'buscar_paciente');
    var VpsicologoCita = validarPsicologoCita('buscar_psicologoError', psicologoCita, 'buscar_psicologo');

    if (VFechaCita === false || VmotivoCita === false || VhoraIniCita === false ||
            VDocPaciente === false || VduracionCita === false || VDocPsicologo === false) {
        alert("ALGO ESTA MAL!");
    } else {
        if (validarFactibilidadHorarrios(documentoP) === false) {

        } else {
            //alert("todo bien");
            $.ajax({
                url: "/api/recepcionista/verificarTratamientoPaciente",
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                async: false,
                data: term,
                success: function (data) {
                    if (data === "0") {
                        document.getElementById('btnRegistrarCitaTratSelect').addEventListener('click', function () {
                            registrarCitaTratamientoSelector();
                        });
                        $('#pac').val($('#buscar_paciente').val());
                        $('#psico').val($('#buscar_psicologo').val());
                        $('#inicio').val(fechaCita + ' ' + horaIniCita);
                        $('#fin').val(fechaCita + ' ' + horaFinCita);
                        $('#tratamientoModal').modal();
                        return true;
                    } else if (data === "2") {
                        Swal.fire({
                            type: 'error',
                            title: 'La cita no es Factible!!',
                            text: 'tiene un unico tratamiento pero ya posee citas pendientes',
                            footer: '<a href>espere a que la cita programada finalize</a>'
                        });
                        return false;
                    } else if (data === "1") {
                        registrarCitaConTratamiento();
                        return true;
                    } else if (data === "3") {
                        registrarCitaConTratamiento();
                        return true;
                    } else {
                        Swal.fire({
                            type: 'error',
                            title: 'La cita no es factible!!!',
                            text: 'Todos sus tratamientos poseen citas pendientes',
                            footer: '<a href>Espera a que alguna de las citas progamadas finalice</a>'
                        });
                    }
                }
            });
        }
        /*$.ajax({
         url: "/api/recepcionista/verificarCitaPacienteTratamiento",
         type: 'POST',
         contentType: 'application/json;charset=utf-8',
         async: false,
         data: term,
         success: function (data) {
         if (data === "0") {
         Swal.fire({
         type: 'error',
         title: 'La cita no es factible!',
         text: 'el paciente ya tiene una cita pendiente para el tratamiento',
         footer: '<a href>Intente con otro paciente o\n\
         espere haste que la cita pendiente finalize</a>'
         });
         return true;
         } else {
         //aqui se registra
         }
         }
         });*/
        //registrarCitaConTratamiento();
    }
};



var registrarCitaTratamientoSelector = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var durCita = document.getElementById('duracionCita').value;
    var motivoCita = document.getElementById('citMotivo').value;
    //var psicologo = document.getElementById('buscar_psicologo').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFinCita = obtenerHoraFinCita(horaIniCita, durCita);
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00');
    var fobia = document.getElementById('tipoTrat');
    var fobId = fobia.options[fobia.selectedIndex].value;

    var cita = {
        citId: null,
        citMotivo: motivoCita,
        citFechaHoraInicio: horafechaIni,
        citFechaHoraFin: horaFechaFin,
        simId: fobId
    };

    var numeroDocumento = documento;
    var citaJsonTratamiento = {
        cita: cita,
        numeroDocumento: numeroDocumento,
        docPsicologo: documentoP
    };

    $.ajax({
        url: "/api/recepcionista/ingresarCitaTratamientoSelector",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,
        data: JSON.stringify(citaJsonTratamiento),
        success: function (data) {

        }
    });

};



var validarSessionesTratamiento = function (term) {
    var v;

    $.ajax({
        url: "/api/recepcionista/verificarExisteSessionPendienteTratamiento",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,
        data: term,
        success: function (data) {
            if (data === "0") {
                Swal.fire({
                    type: 'error',
                    title: 'La cita no es factible!',
                    text: 'Ya existe una sesion para el tratamiento',
                    footer: '<a href>Intener con otro paciente o hasta que finalice la sesion actual</a>'
                });
                v = false;
            } else {
                v = true;
            }
        }
    });
    return v;
};

var validarFactibilidadHorarrios = function (docPsicologo) {
    var v;
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var durCita = document.getElementById('duracionCita').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFinCita = obtenerHoraFinCita(horaIniCita, durCita);
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00');

    var cita = {
        citFechaHoraInicio: horafechaIni,
        citFechaHoraFin: horaFechaFin
    };

    var docPsicologo = docPsicologo;
    var numeroDocumento = "NO ES NECESARIO";

    var citaJsonTratamiento = {
        cita: cita,
        docPsicologo: docPsicologo,
        numeroDocumento: numeroDocumento
    };

    $.ajax({
        url: "/api/recepcionista/verificarFactibilidadFechaCita",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,
        data: JSON.stringify(citaJsonTratamiento),
        success: function (data) {
            if (data === "0") {
                Swal.fire({
                    type: 'error',
                    title: 'La cita no es factible!',
                    text: 'El psicologo esta ocupado entre las ' + horaIniCita + ' y las ' + horaFinCita,
                    footer: '<a href>Intente con otro Horario</a>'
                });
                v = false;
            } else {
                v = true;
            }
        }
    });
    return v;
};


var registrarCitaConTratamiento = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var durCita = document.getElementById('duracionCita').value;
    var motivoCita = document.getElementById('citMotivo').value;
    //var psicologo = document.getElementById('buscar_psicologo').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var horafechaIni = new Date(fechaCita + ' ' + horaIniCita + ':00');
    var horaFinCita = obtenerHoraFinCita(horaIniCita, durCita);
    var horaFechaFin = new Date(fechaCita + ' ' + horaFinCita + ':00');

    var cita = {
        citId: null,
        citMotivo: motivoCita,
        citFechaHoraInicio: horafechaIni,
        citFechaHoraFin: horaFechaFin
    };

    var numeroDocumento = documento;
    var citaJsonTratamiento = {
        cita: cita,
        numeroDocumento: numeroDocumento,
        docPsicologo: documentoP
    };

    $.ajax({
        url: "/api/recepcionista/ingresarCitaTratamiento",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(citaJsonTratamiento),
        success: function (data) {
            if (data === "1") {
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

var obtenerHoraFinCita = function (horaIniCita, durCita) {
    var array = horaIniCita.split(":");
    var horaIni = parseInt(array[0]);
    var minutoIni = parseInt(array[1]);
    var horaAdd = parseInt(durCita.split(":")[0]);
    var minAdd = parseInt(durCita.split(":")[1]);

    var minTot = minutoIni + minAdd;
    var horaR = horaIni + Math.floor(minTot / 60) + horaAdd;
    var minR = minTot % 60;
    console.log(horaR + ":" + minR);
    return horaR + ":" + minR;
};




var validacionCitaRegistro = function () {

    var durCita = document.getElementById('duracionCita').value;
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var term = documento;

    var VFechaCita = validarFechaCita('citFechaError', fechaCita, 'citFecha');
    var VhoraIniCita = validarHoraIniCita('fechaHoraInicioError', horaIniCita, 'fechaHoraInicio');
    var VmotivoCita = validarMotivoCita('citMotivoError', motivoCita, 'citMotivo');
    var VpacienteCita = validarPacienteCita('buscar_pacienteError', pacienteCita, 'buscar_paciente');
    var VfobiaCita = validarFobiaCita('buscar_fobiaError', fobiaCita, 'buscar_fobia');
    var VpsicologoCita = validarPsicologoCita('buscar_psicologoError', psicologoCita, 'buscar_psicologo');
    var VDocPaciente = validarPaciente('buscar_pacienteError', documento, 'buscar_paciente');
    var VDocPsicologo = validarPsicologo('buscar_psicologoError', documentoP, 'buscar_psicologoError');
    var VduracionCita = validacionDuracionCita('duracionCitaError', durCita, 'duracionCita');

    if (VFechaCita === false || VmotivoCita === false || VhoraIniCita === false
            || VDocPaciente === false || VfobiaCita === false || VDocPsicologo === false || VduracionCita === false) {

    } else {
        /*var vcita = ValidacionFactibilidadPrimeraCita(documento,fobiaCita);
         var vhora = validarFactibilidadHorarrios(documentoP);*/
        if (ValidacionFactibilidadPrimeraCita(documento, fobiaCita) === false) {

        } else if (validarFactibilidadHorarrios(documentoP) === false) {

        } else {
            registrarCita();
        }

    }
};

var ValidacionFactibilidadPrimeraCita = function (documento, fobiaCita) {
    var v;
    var array = [documento, fobiaCita];
    $.ajax({
        url: "/api/recepcionista/verificarFctibilidadPrimeraCita",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,
        data: JSON.stringify(array),
        success: function (data) {
            if (data === "1") {
                v = true;
            } else if (data === "2") {
                Swal.fire({
                    type: 'error',
                    title: 'La cita no es factible!',
                    text: 'El paciente ya posee un tratamiento para la fobia ' + fobiaCita + ' no es posible registrar la Cita',
                    footer: '<a href>Intente con otro fobia</a>'
                });
                v = false;
            } else if (data === "3") {
                Swal.fire({
                    type: 'error',
                    title: 'La cita no es factible!',
                    text: 'El paciente ya posee un cita exploratoria pendiente para la fobia ' + fobiaCita + ' no es posible registrar la Cita',
                    footer: '<a href>Intente con otro fobia</a>'
                });
                v = false;
            }
        }
    });
    return v;
};

var validacionCitaActualizaion = function () {
    
    var durCita = document.getElementById('duracionCita').value;
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;
    var documento = document.getElementById('documentoPaciente').value;
    var documentoP = document.getElementById('documentoPsicologo').value;
    var term = documento;

    var VFechaCita = validarFechaCita('citFechaError', fechaCita, 'citFecha');
    var VhoraIniCita = validarHoraIniCita('fechaHoraInicioError', horaIniCita, 'fechaHoraInicio');
    var VmotivoCita = validarMotivoCita('citMotivoError', motivoCita, 'citMotivo');
    var VpacienteCita = validarPacienteCita('buscar_pacienteError', pacienteCita, 'buscar_paciente');
    var VfobiaCita = validarFobiaCita('buscar_fobiaError', fobiaCita, 'buscar_fobia');
    var VpsicologoCita = validarPsicologoCita('buscar_psicologoError', psicologoCita, 'buscar_psicologo');
    var VDocPaciente = validarPaciente('buscar_pacienteError', documento, 'buscar_paciente');
    var VDocPsicologo = validarPsicologo('buscar_psicologoError', documentoP, 'buscar_psicologoError');
    var VduracionCita = validacionDuracionCita('duracionCitaError', durCita, 'duracionCita');
    
    if (VFechaCita === false || VmotivoCita === false || VhoraIniCita === false ||
            VduracionCita === false ||  VDocPaciente === false || VfobiaCita === false || VDocPsicologo === false
            || validarLogicaHorarios === false) {
        alert("ALGO ESTA MAL!");
    } else {
        alert("TODO BIEN");
        actualizarCita();
    }
};





var validarLogicaHorarios = function (valIni, valFin) {
    var horaIni = $('#fechaHoraInicio').val();
    var horaFin = $('#fechaHoraFin').val();
    var paramIni = horaIni.split(':');
    var paramFin = horaFin.split(':');
    var fechaIni = new Date();
    var fechaFin = new Date();
    fechaIni.setFullYear(paramIni[0], paramIni[1], 0);
    fechaFin.setFullYear(paramFin[0], paramFin[1], 0);
    if (valIni === true && valFin === true) {
        if (fechaIni >= fechaFin) {
            addNegativeAttributtes('fechaHoraInicio');
            addNegativeHtml('fechaHoraInicioError', 'La fecha de Inicio no puede ser mayor a la de finalizacion');
            addNegativeAttributtes('fechaHoraFin');
            addNegativeHtml('fechaHoraFinError', 'La fecha de Finalizacion no puede ser menor a la de Inicio');
            return false;
        } else {
            addPositiveAtributtes('fechaHoraInicio');
            addPositiveHtml('fechaHoraInicioError', 'Correcto');
            addPositiveAtributtes('fechaHoraFin');
            addPositiveHtml('fechaHoraFinError', 'Correcto');
            return true;
        }
    }
};
var validarHoraIniCita = function (elementoError, valor, elemento) {
    var horaIni = new Date();
    var minimalHour = new Date();
    var maximalHour = new Date();
    var param = valor.split(':');
    horaIni.setHours(param[0], param[1], 0);
    minimalHour.setHours(9, 0, 0);
    maximalHour.setHours(21, 0, 0)
    var regexIsValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Hora de inicio obligatoriamente");
        $('#' + elemento).keyup(keyhorainicita);
        return false;
    } else if (!valor.match(regexIsValid)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Hora de Inicio Valida");
        $('#' + elemento).keyup(keyhorafincita);
        return false;
    } else if (horaIni <= minimalHour) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La hora de Inicio debe ser mayor a las 9 :00 A.M. [Hora de apertura]");
        $('#' + elemento).keyup(keyhorainicita);
        return false;
    } else if (horaIni >= maximalHour) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La hora de Inicio debe ser Menor a las 9 :00 P.M.");
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
    var HoraMax = new Date();
    var minimalHour = new Date();
    var param = $valor.val().split(':');
    var regexIsValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/;
    horaIni.setHours(param[0], param[1], 0);
    HoraMax.setHours(21, 0, 0);
    minimalHour.setHours(9, 0, 0);
    if ($valor.val() === "") {
        addNegativeAttributtes('fechaHoraInicio');
        addNegativeHtml('fechaHoraInicioError', "Debe ingresar un Hora de inicio obligatoriamente");
        return false;
    } else if (!$valor.val().match(regexIsValid)) {
        addNegativeAttributtes('fechaHoraInicio');
        addNegativeHtml('fechaHoraInicioError', "Debe ingresar un Hora de Inicio Valida");
        return false;
    } else if (horaIni <= minimalHour) {
        addNegativeAttributtes('fechaHoraInicio');
        addNegativeHtml('fechaHoraInicioError', "La hora de Inicio debe ser mayor a las 9 :00 A.M. [Hora de apertura]");
        return false;
    } else if (horaIni >= HoraMax) {
        addNegativeAttributtes('fechaHoraInicio');
        addNegativeHtml('fechaHoraInicioError', "La hora de Inicio debe ser menor a las 9 :00 P.M.");
        return false;
    } else {
        addPositiveAtributtes('fechaHoraInicio');
        addPositiveHtml('fechaHoraInicioError', 'Correcto');
        return true;
    }
};


var validacionDuracionCita = function (elementoError, valor, elemento) {
    var regexIsValid = /^([0]?[01]):([0-5][0-9])(:[0-5][0-9])?$/;
    var horas = parseInt(valor.split(":")[0]);
    var minutos = parseInt(valor.split(":")[1]);
    if (valor === "" || valor.trim() === "" || valor === "0") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe Ingresar una fecha valida');
        $('#' + elemento).change(changeCantidad);
        return false;
    } else if (!valor.match(regexIsValid)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'La duracion debe ser de hasta 01:59 hr como maximo');
        $('#' + elemento).change(changeCantidad);
        return false;
    } else if (horas === 0 && minutos < 25) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'La duracion debe ser de 25 min como minimo');
        $('#' + elemento).change(changeCantidad);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).change(changeCantidad);
        return true;
    }
};

var changeCantidad = function () {
    var $valor = $('#duracionCita').val();
    var regexIsValid = /^([0]?[01]):([0-5][0-9])(:[0-5][0-9])?$/;
    var horas = parseInt($valor.split(":")[0]);
    var minutos = parseInt($valor.split(":")[1]);
    if ($valor === "" || $valor.trim() === "" || $valor === "0") {
        addNegativeAttributtes('duracionCita');
        addNegativeHtml('duracionCitaError', 'Debe Ingresar una fecha valida');
        return false;
    } else if (!$valor.match(regexIsValid)) {
        addNegativeAttributtes('duracionCita');
        addNegativeHtml('duracionCitaError', 'La duracion debe ser de hasta 01:59 hr como maximo');
        return false;
    } else if (horas === 0 && minutos < 25) {
        addNegativeAttributtes('duracionCita');
        addNegativeHtml('duracionCitaError', 'La duracion debe ser de hasta 25 min como minimo');
        return false;
    } else {
        addPositiveAtributtes('duracionCita');
        addPositiveHtml('duracionCitaError', 'Correcto');
        return true;
    }
};

var validarHoraFinCita = function (elementoError, valor, elemento) {
    var horafin = new Date();
    var maximalHour = new Date();
    var param = valor.split(':');
    var regexIsValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/;
    horafin.setHours(param[0], param[1], 0);
    maximalHour.setHours(22, 0, 0);
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Hora de Finalizacion obligatoriamente");
        $('#' + elemento).keyup(keyhorafincita);
        return false;
    } else if (!valor.match(regexIsValid)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Hora de Finalizacion Valida");
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
    var $valor = $('#fechaHoraFin');
    var horafin = new Date();
    var maximalHour = new Date();
    var param = $valor.val().split(':');
    var regexIsValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/;
    horafin.setHours(param[0], param[1], 0);
    maximalHour.setHours(22, 0, 0);
    if ($valor.val() === "") {
        addNegativeAttributtes('fechaHoraFin');
        addNegativeHtml('fechaHoraFinError', "Debe ingresar un Hora de Finalizacion obligatoriamente");
        return false;
    } else if (!$valor.val().match(regexIsValid)) {
        addNegativeAttributtes('fechaHoraFin');
        addNegativeHtml('fechaHoraFinError', "Debe ingresar un Hora de Finalizacion Valida");
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
    var ret;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Psicologo obligatoriamente");
        $('#' + elemento).keyup(keynombrepsicologo);
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
                    $('#' + elemento).keyup(keynombrepsicologo);
                    psicologoId = data;
                    ret = true;
                } else {
                    addNegativeAttributtes(elemento);
                    addNegativeHtml(elementoError, "El Piscologo Ingresado no Existe");
                    $('#' + elemento).keyup(keynombrepsicologo);
                    ret = false;
                }
            }
        });
    }
    return ret;
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
    var ret;
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
                    ret = true;
                } else {
                    addNegativeAttributtes(elemento);
                    addNegativeHtml(elementoError, "El Paciente Ingresado no Existe");
                    $('#' + elemento).keyup(keynombrepaciente);
                    ret = false;
                }
            }
        });
    }
    return ret;
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

var validarPaciente = function (elementoError, valor, elemento) {
    if (valor === "" || valor.trim() === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "debeIngresar un Paciente Obligatoriamente");
        $('#documentoPaciente').change(valpaci);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#documentoPaciente').change(valpaci);
        return true;
    }
};



var valpaci = function () {
    var $doc = $('#documentoPaciente').val();
    if ($doc === "" || $doc.trim() === "") {
        addNegativeAttributtes('buscar_paciente');
        addNegativeHtml('buscar_pacienteError', "debeIngresar un Paciente Obligatoriamente");
        return false;
    } else {
        addPositiveAtributtes()('buscar_paciente');
        addPositiveHtml('buscar_pacienteError', "Correcto");
        return true;
    }
};



var validarPsicologo = function (elementoError, valor, elemento) {
    if (valor === "" || valor.trim() === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "debe Ingresar un Psicologo Obligatoriamente");
        $('#documentoPsicologo').change(valpsico);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#documentoPsicologo').change(valpsico);
        return true;
    }
};



var valpsico = function () {
    var $doc = $('#valpaci').val();
    if ($doc === "" || $doc.trim() === "") {
        addNegativeAttributtes('buscar_psicologo');
        addNegativeHtml('buscar_psicologoError', "debe Ingresar un Psicologo Obligatoriamente");
        return false;
    } else {
        addPositiveAtributtes()('buscar_psicologo');
        addPositiveHtml('buscar_psicologoError', "Correcto");
        return true;
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
        addNegativeHtml(elementoError, 'El motivo no puede poseer espacias a los extremos, minimo 3 caraceters');
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
        addNegativeHtml('citMotivoError', 'El motivo no puede poseer espacias a los extremos, minimo 3 caraceters');
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
$("#registrarCitaTratamiento").click(validacionCitaRegistroTratamiento);

