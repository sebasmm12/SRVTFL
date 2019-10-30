var registrarCita = function () {
    var fechaCita = document.getElementById('citFecha').value;
    var horaIniCita = document.getElementById('fechaHoraInicio').value;
    var horaFinCita = document.getElementById('fechaHoraFin').value;
    var motivoCita = document.getElementById('citMotivo').value;
    var fobiaCita = document.getElementById('buscar_fobia').value;
    var pacienteCita = document.getElementById('buscar_paciente').value;
    var psicologoCita = document.getElementById('buscar_psicologo').value;

    var VFechaCita = validarFechaCita('citFechaError', fechaCita, 'citFecha');
    //var VhoraIniCita = validarHoraIniCita('fechaHoraInicioError', horaIniCita, 'fechaHoraInicio');
    //var VhoraFinCita = validarHoraFinCita('fechaHoraFinError', horaFinCita, 'fechaHoraFin');
    var VmotivoCita = validarMotivoCita('citMotivoError', motivoCita, 'citMotivo');
    var VpacienteCita = validarPacienteCita('buscar_pacienteError', pacienteCita,'buscar_paciente');
    //var VfobiaCita = validarFobiaCita('buscar_fobiaError', fobiaCita, 'buscar_fobia');
    //var VpsicologoCita = validarPsicologoCita('buscar_psicologoError', psicologoCita, 'buscar_psicologo');

    if(VFechaCita===false || VmotivoCita===false || VpacienteCita===false){
        alert("ALGO ESTA MAL!");
    }else{
        alert("TODO BIEN!");
    }

   
};


var validarPacienteCita = function(elementoError,valor,elemento){
  
    if(valor===""){
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError,"Debe ingresar un paciente Obligatoriamente");
        $('#'+elemento).keyup(keynombrepaciente);
        return false;
    }else if(verificarNombrePaciente(valor) !== 0){
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError,"Debe ingresar un paciente Obligatoriamente");
        $('#'+elemento).keyup(keynombrepaciente);
        return false;
    }else{
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError,"Correcto");
        $('#'+elemento).keyup(keynombrepaciente);
        return false;
    }
    
};

var keynombrepaciente = function(){
    var nombrepaciente = $('#buscar_paciente');
    if(nombrepaciente.val()===""){
        addNegativeAttributtes('buscar_paciente');
        addNegativeHtml('buscar_pacienteError',"Debe ingresar un paciente Obligatoriamente");
        return false;
    }else if(verificarNombrePaciente(nombrepaciente.val()) !== 0){
        addNegativeAttributtes('buscar_paciente');
        addNegativeHtml('buscar_pacienteError',"Debe ingresar un paciente Obligatoriamente");
        return false;
    }else{
        addPositiveAtributtes('buscar_paciente');
        addPositiveHtml('buscar_pacienteError',"Correcto");
        return false;
    }
};

var verificarNombrePaciente = function(termino){
    var term = termino;
    var resultado;
    $.ajax({
        url: '/api/recepcionista/verificarNombre',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(term),
        success: function(data){
            if(data !== "0"){
                resultado=data;
            }else{
                resultado =0;
            }
        }
    });
    return resultado;
};


var validarMotivoCita = function(elementoError,valor,elemento){
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    if(elemento === ""){
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError,'Debe ingresar un Motivo Obligatoriamente');
        $('#'+elemento).keyup(keymotivocita);
        return false;
    }else if(!valor.match(regex)){
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError,'El motivo no puede poseer espacias a los extremos, minimo 2 caraceters');
        $('#'+elemento).keyup(keymotivocita);
        return false;
    }else{
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError,'Correcto');
        $('#'+elemento).keyup(keymotivocita);
        return true;
    }
};

var keymotivocita = function(){
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    var $motivoCita = $('#citMotivo');
    if($motivoCita.val() === ""){
        addNegativeAttributtes('citMotivo');
        addNegativeHtml('citMotivoError','Debe ingresar un Motivo Obligatoriamente');
        return false;
    }else if(!$motivoCita.val().match(regex)){
        addNegativeAttributtes('citMotivo');
        addNegativeHtml('citMotivoError','El motivo no puede poseer espacias a los extremos, minimo 2 caraceters');
        return false;
    }else{
        addPositiveAtributtes('citMotivo');
        addPositiveHtml('citMotivoError','Correcto');
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
            addNegativeHtml('citFechaError', "La fecha de nacimento no puede ser mayor a la actual");
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
    
    
$('#btnRegistrarCita').click(registrarCita);


