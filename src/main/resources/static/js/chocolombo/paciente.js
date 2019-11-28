/* var registrarpacientes =  document.getElementById('registrarpacientes').addEventListener('click', function () {
    validarPacienteRegistro();
});*/
$('#registrarpacientes').click(function(){
    validarPacienteRegistro();
});
 $("#actualizarpacientes").click(function(){
     validarPacienteActualizacion();
 });

//actualizarpacientes




var validarPacienteActualizacion = function () {
    var edad = document.getElementById('pacienteEdad').value;
    var nombre = document.getElementById('nombrePaciente').value;
    var apellido = document.getElementById('apellidoPaciente').value;
    var telefono = document.getElementById('telefonoPaciente').value;
    var email = document.getElementById('pacienteCorreo').value;
    var direccion = document.getElementById('pacienteDireccion').value;
    var numeroDocumento = document.getElementById('pacienteDocumento').value;
    var tipDoc = document.getElementById('pacienteTipoDocumento');
    var tipodocumento = tipDoc.options[tipDoc.selectedIndex].value;
    var sexoBio = document.getElementById('pacienteSexoBiologico');
    var sexoBiologico = sexoBio.options[sexoBio.selectedIndex].value;


    var ValidacionEdad = validarEdad('pacienteEdadError', edad, 'pacienteEdad');
    var ValidacionNombre = validarNombrePaciente('nombrePacienteError', nombre, 'nombrePaciente');
    var ValidacionApellido = validarApellidoPaciente('apellidoPacienteError', apellido, 'apellidoPaciente');
    var ValidacionTelefono = validacionTelefono('telefonoPacienteError', telefono, 'telefonoPaciente');
    var ValidacionCorreo = validacionCorreo('pacienteCorreoError', email, 'pacienteCorreo');
    var ValidacionDireccion = validacionDireccion('pacienteDireccionError', direccion, 'pacienteDireccion');
    var ValidacionNumDoc = validacionNumeroDoc('pacienteDocumentoError', numeroDocumento, 'pacienteDocumento');


    if (ValidacionEdad === false || ValidacionNombre === false || ValidacionApellido === false || ValidacionTelefono === false ||
            ValidacionCorreo === false || ValidacionDireccion === false || ValidacionNumDoc === false) {
        return;
    } else if (validardocumentoA() === false) {
        Swal.fire({
            type: 'error',
            title: 'No es posible actualizar',
            text: 'ya existe un paciente con el DNI ingresado',
            footer: '<a href>verifique el numero de Documento o intente con el numero de pasaporte</a>'
        });
    } else {
        //alert('Todo Bien');
        actualizarPaciente();
    }
};

var validardocumentoR = function () {
    var v;
    var numeroDocumento = document.getElementById('pacienteDocumento').value;
    //var numeroDocActual = document.getElementById('pacNumDoc').value;
        var term = numeroDocumento;
        $.ajax({
            url: "/api/paciente/verificarMultiplicidadNdoc",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data === "1") {
                    v = true;
                } else {
                    v = false;
                }
            }
        });
    
    return v;
};


var validardocumentoA = function () {
    var v;
    var numeroDocumento = document.getElementById('pacienteDocumento').value;
    var numeroDocActual = document.getElementById('pacNumDoc').value;
    if (numeroDocumento === numeroDocActual) {
        v = true;
    } else {
        var term = numeroDocumento;
        $.ajax({
            url: "/api/paciente/verificarMultiplicidadNdoc",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            async: false,
            data: term,
            success: function (data) {
                if (data === "1") {
                    v = true;
                } else {
                    v = false;
                }
            }
        });
    }
    return v;
};

var actualizarPaciente = function () {

    var edad = document.getElementById('pacienteEdad').value;
    var nombre = document.getElementById('nombrePaciente').value;
    var apellido = document.getElementById('apellidoPaciente').value;
    var telefono = document.getElementById('telefonoPaciente').value;
    var email = document.getElementById('pacienteCorreo').value;
    var direccion = document.getElementById('pacienteDireccion').value;
    var numeroDocumento = document.getElementById('pacienteDocumento').value;
    var tipDoc = document.getElementById('pacienteTipoDocumento');
    var tipodocumento = tipDoc.options[tipDoc.selectedIndex].value;
    var sexoBio = document.getElementById('pacienteSexoBiologico');
    var sexoBiologico = sexoBio.options[sexoBio.selectedIndex].value;
    var pacId = document.getElementById('pacId').value;

    var sb;
    if (sexoBiologico === '0') {
        sb = false;
    } else {
        sb = true;
    }

    var paciente = {
        pacId: pacId,
        pac_edad: edad,
        pacNombre: nombre,
        pacApellido: apellido,
        pacTelefono: telefono,
        pacEmail: email,
        pacDireccion: direccion,
        tipDocId: tipodocumento,
        pacSexoBiologico: sb,
        pacNumeroDocumento: numeroDocumento
    };

    $.ajax({
        url: "/api/paciente/modificarPaciente",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,
        data: JSON.stringify(paciente),
        success: function (data) {
            if (data === "1") {
                Swal.fire({
                    type: 'success',
                    title: 'Paciente Registrado Correctamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/Paciente/GestionarPacientes";
                    }
                });
            }
        }
    });

};



var validarPacienteRegistro = function () {

    var edad = document.getElementById('pacienteEdad').value;
    var nombre = document.getElementById('nombrePaciente').value;
    var apellido = document.getElementById('apellidoPaciente').value;
    var telefono = document.getElementById('telefonoPaciente').value;
    var email = document.getElementById('pacienteCorreo').value;
    var direccion = document.getElementById('pacienteDireccion').value;
    var numeroDocumento = document.getElementById('pacienteDocumento').value;
    var tipDoc = document.getElementById('pacienteTipoDocumento');
    var tipodocumento = tipDoc.options[tipDoc.selectedIndex].value;
    var sexoBio = document.getElementById('pacienteSexoBiologico');
    var sexoBiologico = sexoBio.options[sexoBio.selectedIndex].value;


    var ValidacionEdad = validarEdad('pacienteEdadError', edad, 'pacienteEdad');
    var ValidacionNombre = validarNombrePaciente('nombrePacienteError', nombre, 'nombrePaciente');
    var ValidacionApellido = validarApellidoPaciente('apellidoPacienteError', apellido, 'apellidoPaciente');
    var ValidacionTelefono = validacionTelefono('telefonoPacienteError', telefono, 'telefonoPaciente');
    var ValidacionCorreo = validacionCorreo('pacienteCorreoError', email, 'pacienteCorreo');
    var ValidacionDireccion = validacionDireccion('pacienteDireccionError', direccion, 'pacienteDireccion');
    var ValidacionNumDoc = validacionNumeroDoc('pacienteDocumentoError', numeroDocumento, 'pacienteDocumento');


    if (ValidacionEdad === false || ValidacionNombre === false || ValidacionApellido === false || ValidacionTelefono === false ||
            ValidacionCorreo === false || ValidacionDireccion === false) {
        alert('Algo Mal');
    }else if(validardocumentoR() === false){
        Swal.fire({
            type: 'error',
            title: 'No es posible registrar',
            text: 'ya existe un paciente con el DNI ingresado',
            footer: '<a href>verifique el numero de Documento o intente con el numero de pasaporte</a>'
        });
    }else {
        registrarPaciente();
    }
    //var ValidacionEdad= validarEdad('pacienteEdadError',edad,'pacienteEdad');

};

var registrarPaciente = function () {

    var edad = document.getElementById('pacienteEdad').value;
    var nombre = document.getElementById('nombrePaciente').value;
    var apellido = document.getElementById('apellidoPaciente').value;
    var telefono = document.getElementById('telefonoPaciente').value;
    var email = document.getElementById('pacienteCorreo').value;
    var direccion = document.getElementById('pacienteDireccion').value;
    var numeroDocumento = document.getElementById('pacienteDocumento').value;
    var tipDoc = document.getElementById('pacienteTipoDocumento');
    var tipodocumento = tipDoc.options[tipDoc.selectedIndex].value;
    var sexoBio = document.getElementById('pacienteSexoBiologico');
    var sexoBiologico = sexoBio.options[sexoBio.selectedIndex].value;

    var sb;
    if (sexoBiologico === '0') {
        sb = false;
    } else {
        sb = true;
    }

    var paciente = {
        pacId: null,
        pac_edad: edad,
        pacNombre: nombre,
        pacApellido: apellido,
        pacTelefono: telefono,
        pacEmail: email,
        pacDireccion: direccion,
        tipDocId: tipodocumento,
        pacSexoBiologico: sb,
        pacNumeroDocumento: numeroDocumento
    };

    $.ajax({
        url: "/api/paciente/ingresarPaciente",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,
        data: JSON.stringify(paciente),
        success: function (data) {
            if (data === "1") {
                Swal.fire({
                    type: 'success',
                    title: 'Paciente Registrado Correctamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/Paciente/GestionarPacientes";
                    }
                });
            }
        }
    });

};

var validarEdad = function (elementoError, valor, elemento) {
    var regex = /^[0-9]{1,2}$/;
    if (valor === 0 || valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar una edad Obligatoriamente');
        $('#' + elemento).change(keyEdad);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar un formato numerico');
        $('#' + elemento).change(keyEdad);
        return false;
    } else if (valor < 9 || valor > 80) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'El paciente de tener una edad entre 9 y 80 años');
        $('#' + elemento).change(keyEdad);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).change(keyEdad);
        return true;
    }
};

var keyEdad = function () {
    var $edad = $('#pacienteEdad').val();
    var regex = /^[0-9]{1,2}$/;
    if ($edad === 0 || $edad === "") {
        addNegativeAttributtes('pacienteEdad');
        addNegativeHtml('pacienteEdadError', 'Debe ingresar una edad Obligatoriamente');
        return false;
    } else if (!$edad.match(regex)) {
        addNegativeAttributtes('pacienteEdad');
        addNegativeHtml('pacienteEdadError', 'Debe ingresar un formato numerico');
        return false;
    } else if ($edad < 9 || $edad > 80) {
        addNegativeAttributtes('pacienteEdad');
        addNegativeHtml('pacienteEdadError', 'El paciente de tener una edad entre 9 y 80 años');
        return false;
    } else {
        addPositiveAtributtes('pacienteEdad');
        addPositiveHtml('pacienteEdadError', 'Correcto');
        return true;
    }
};


var validacionNumeroDoc = function (elementoError, valor, elemento) {
    var regexDni = /^([0-9])[0-9]{6}([0-9])$/;
    var regexPas = /^([0-9])[0-9]{9}([0-9])$/;
    var tipDoc = document.getElementById('pacienteTipoDocumento').selectedIndex;
    if (valor === null) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar un Numero de Documento  Obligatoriamente');
        $('#' + elemento).keyup(keynumerodoc);
        return false;
    } else if (tipDoc === 0) {
        if (valor.match(regexDni)) {
            addPositiveAtributtes(elemento);
            addPositiveHtml(elementoError, 'Correcto');
            $('#' + elemento).keyup(keynumerodoc);
            return true;
        } else {
            addNegativeAttributtes(elemento);
            addNegativeHtml(elementoError, 'Numero de Documento  debe poseer 8 digitos numericos');
            $('#' + elemento).keyup(keynumerodoc);
            return false;
        }
    } else {
        if (valor.match(regexPas)) {
            addPositiveAtributtes(elemento);
            addPositiveHtml(elementoError, 'Correcto');
            $('#' + elemento).keyup(keynumerodoc);
            return true;
        } else {
            addNegativeAttributtes(elemento);
            addNegativeHtml(elementoError, 'Numero de Documento  debe poseer 11 digitos numericos');
            $('#' + elemento).keyup(keynumerodoc);
            return false;
        }
    }
};
var keynumerodoc = function () {
    var regexDni = /^([0-9])[0-9]{6}([0-9])$/;
    var regexPas = /^([0-9])[0-9]{9}([0-9])$/;
    var tipDoc = document.getElementById('pacienteTipoDocumento').selectedIndex;
    var $numerodoc = $('#pacienteDocumento');
    if ($numerodoc.val() === "") {
        addNegativeAttributtes('pacienteDocumento');
        addNegativeHtml('pacienteDocumentoError', 'Debe ingresar un Numero de Documento  Obligatoriamente');
        return false;
    } else if (tipDoc === 0) {
        if ($numerodoc.val().match(regexDni)) {
            addPositiveAtributtes('pacienteDocumento');
            addPositiveHtml('pacienteDocumentoError', 'Correcto');
            return true;
        } else {
            addNegativeAttributtes('pacienteDocumento');
            addNegativeHtml('pacienteDocumentoError', 'Numero de Documento  debe poseer 8 digitos numericos');
            return false;
        }
    } else {
        if ($numerodoc.val().match(regexPas)) {
            addPositiveAtributtes('pacienteDocumento');
            addPositiveHtml('pacienteDocumentoError', 'Correcto');
            return true;
        } else {
            addNegativeAttributtes('pacienteDocumento');
            addNegativeHtml('pacienteDocumentoError', 'Numero de Documento  debe poseer 11 digitos numericos');
            return false;
        }
    }
};



var validacionDireccion = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s]+).*([^\s]+)$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar una Direccion Obligatoriamente');
        $('#' + elemento).keyup(keydireccion);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'La direccion no debe poseer digitos al inicio ni tener espacios a los extremos, minimo 2 caracteres');
        $('#' + elemento).keyup(keydireccion);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keydireccion);
        return true;
    }
};

var keydireccion = function () {
    var $direccion = $('#pacienteDireccion');
    var regex = /^([^\d\s]+).*([^\s]+)$/;
    if ($direccion.val() === "") {
        addNegativeAttributtes('pacienteDireccion');
        addNegativeHtml('pacienteDireccionError', 'Debe una Direccion Obligatoriamente');
        return false;
    } else if (!$direccion.val().match(regex)) {
        addNegativeAttributtes('pacienteDireccion');
        addNegativeHtml('pacienteDireccionError', 'La direccion no debe poseer digitos al inicio ni tener espacios a los extremos, minimo 2 caracteres');
        return false;
    } else {
        addPositiveAtributtes('pacienteDireccion');
        addPositiveHtml('pacienteDireccionError', 'Correcto');
        return true;
    }
};



var validacionCorreo = function (elementoError, valor, elemento) {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un E-mail Obligatoriamente");
        $("#" + elemento).keyup(keycorreo);
        return false;
    } else if (valor.match(regex)) {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $("#" + elemento).keyup(keycorreo);
        return true;
    } else {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'E-mail incorrecto, debe tener la forma: sample@something.somethingelse');
        $("#" + elemento).keyup(keycorreo);
        return false;
    }
};
var keycorreo = function () {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var $correo = $('#pacienteCorreo');
    if ($correo.val() === "") {
        addNegativeAttributtes('pacienteCorreo');
        addNegativeHtml('pacienteCorreoError', 'Debe ingresar un E-mail Obligatoriamente');
        return false;
    } else if ($correo.val().match(regex)) {
        addPositiveAtributtes('pacienteCorreo');
        addPositiveHtml('pacienteCorreoError', 'Correcto');
        return true;
    } else {
        addNegativeAttributtes("pacienteCorreo");
        addNegativeHtml('pacienteCorreoError', 'E-mail incorrecto, debe tener la forma: sample@something.somethingelse');
        return false;
    }
};

var validacionTelefono = function (elementoError, valor, elemento) {
    var regex = /^(\d{1})\d{7}(\d{1})$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar una Direccion Obligatoriamente');
        $('#' + elemento).keyup(keytelefono);
        return false;
    } else if (valor.match(regex)) {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keytelefono);
        return true;
    } else {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Numero de telefono incorrecto, debe poseer solo 9 digitos');
        $('#' + elemento).keyup(keytelefono);
        return false;
    }
};

var keytelefono = function () {
    var $telefono = $('#telefonoPaciente');
    var regex = /^(\d{1})\d{7}(\d{1})$/;
    if ($telefono.val() === "") {
        addNegativeAttributtes('telefonoPaciente');
        addNegativeHtml('telefonoPacienteError', 'Debe una Direccion Obligatoriamente');
        return false;
    } else if ($telefono.val().match(regex)) {
        addPositiveAtributtes('telefonoPaciente');
        addPositiveHtml('telefonoPacienteError', 'Correcto');
        return true;
    } else {
        addNegativeAttributtes('telefonoPaciente');
        addNegativeHtml('telefonoPacienteError', 'Numero de telefono incorrecto, debe poseer solo 9 digitos');
        return false;
    }
};



var validarApellidoPaciente = function (elementoError, valor, elemento) {
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    if (elemento === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar un nombre Obligatoriamente');
        $('#' + elemento).keyup(keyapellidopaciente);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'El nombre no puede poseer espacios a los extremos, minimo 3 caraceters');
        $('#' + elemento).keyup(keyapellidopaciente);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keyapellidopaciente);
        return true;
    }
};
var keyapellidopaciente = function () {
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    var $motivoCita = $('#apellidoPaciente');
    if ($motivoCita.val() === "") {
        addNegativeAttributtes('apellidoPaciente');
        addNegativeHtml('apellidoPacienteError', 'Debe ingresar un nombre Obligatoriamente');
        return false;
    } else if (!$motivoCita.val().match(regex)) {
        addNegativeAttributtes('apellidoPaciente');
        addNegativeHtml('apellidoPacienteError', 'El nombre no puede poseer espacios a los extremos, minimo 3 caraceters');
        return false;
    } else {
        addPositiveAtributtes('apellidoPaciente');
        addPositiveHtml('apellidoPacienteError', 'Correcto');
        return true;
    }
};



var validarNombrePaciente = function (elementoError, valor, elemento) {
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    if (elemento === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'Debe ingresar un nombre Obligatoriamente');
        $('#' + elemento).keyup(keynombrepaciente);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, 'El nombre no puede poseer espacios a los extremos, minimo 3 caraceters');
        $('#' + elemento).keyup(keynombrepaciente);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keynombrepaciente);
        return true;
    }
};
var keynombrepaciente = function () {
    var regex = /^[^\s]([\w]+([\s]{1,4})?)+[^\s]$/g;
    var $motivoCita = $('#nombrePaciente');
    if ($motivoCita.val() === "") {
        addNegativeAttributtes('nombrePaciente');
        addNegativeHtml('nombrePacienteError', 'Debe ingresar un nombre Obligatoriamente');
        return false;
    } else if (!$motivoCita.val().match(regex)) {
        addNegativeAttributtes('nombrePaciente');
        addNegativeHtml('nombrePacienteError', 'El nombre no puede poseer espacios a los extremos, minimo 3 caraceters');
        return false;
    } else {
        addPositiveAtributtes('nombrePaciente');
        addPositiveHtml('nombrePacienteError', 'Correcto');
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
