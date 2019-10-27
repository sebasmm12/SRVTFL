/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var Guardar = function () {
    var nombresApellidos = $('#detUsuNombre').val();
    var direccion = $('#detUsuDireccion').val();
    var telefono = $('#detUsuTelefono').val();
    var edad = $('#detUsuEdad').val();
    var fechaNacimiento = $('#detUsuFechaNacimiento').val();
    var sexo = $('#selectedSexo').val();
    var correo = $('#detUsuCorreo').val();
    var codigoColegio = $('#detUsuCodigoColegio').val();
    var numeroDocumento = $('#detUsuTipoDocNumero').val();
    var especialidad = $('#detUsuEspecialidad').val();
    var estado = document.getElementById('civilSelect');
    var estadoCivil = estado.options[estado.selectedIndex].innerText;
    var lugarNacimiento = $('#detUsuLugarNacimiento').val();
    var Ocupacion = $('#detUsuOcupacion').val();
    var Religion = $('#detUsuReligion').val();
    var UsuarioCodigo = document.getElementById('usuario.usu_codigo').value;
    var Imagen = document.getElementById('imagenId');
    var UsuarioContrasena = $('#passwordId').val();
    var usuario = {
        usu_id: 0,
        usu_codigo: UsuarioCodigo,
        usu_contraseña: UsuarioContrasena
    };
    
    var detalleUsuario = {
        usu_id: 0,
        usuario: usuario,
        detUsuNombre: nombresApellidos,
        detUsuCorreo: correo,
        detUsuDireccion: direccion,
        detUsuTelefono: telefono,
        detUsuSexo: sexo,
        detUsuTipoDocNumero: numeroDocumento,
        detUsuImagen: Imagen.src,
        detUsuCodigoColegio: codigoColegio,
        detUsuEspecialidad: especialidad,
        detUsuEdad: edad,
        detUsuFechaNacimiento: fechaNacimiento,
        detUsuLugarNacimiento: lugarNacimiento,
        detUsuOcupacion: Ocupacion,
        detUsuReligion: Religion,
        detUsuEstadoCivil: estadoCivil
    };
    
     var DetalleUsuarioJson = {
        detalleUsuario: detalleUsuario,
        usuario: usuario
    };
    $.ajax({
        url: "/api/usuario/registrar",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(DetalleUsuarioJson),
        success: function (data) {
            if (data === "1") {
                ActualizarImagen();
            }
        }
    });
};

var ActualizarImagen = function () {

    var uploadfile = new FormData($("#form")[0]);
    $.ajax({
        url: "/api/usuario/actualizarImagen",
        type: 'POST',
        data: uploadfile,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if (data === "1") {
                Swal.fire({
                    type: 'success',
                    title: 'Se modifico los datos personales exitosamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/Administrador/GestionarUsuarios";
                    }
                });
            }
        }
    });

};

var validacion = function () {
    var $telefono = $('#detUsuTelefono');
    var $correo = $('#detUsuCorreo');
    var $religion = $('#detUsuReligion');
    var $usuCodigo = document.getElementById('usuario.usu_codigo');
    var $usuContrasena = $('#passwordId');
    var $lugarNacimiento = $('#detUsuLugarNacimiento');
    var validarTelefono = validacionTelefono('detUsuTelefonoError', $telefono.val(), 'detUsuTelefono');
    var validarCorreo = validacionCorreo('detUsuCorreoError', $correo.val(), 'detUsuCorreo');
    var validarReligion = validacionReligion('detUsuReligionError', $religion.val(), 'detUsuReligion');
    var validarUsuCodigo = validacionUsuCodigo('usuCodigoError', $usuCodigo.value, 'usuario.usu_codigo');
    var validarUsuContrasena = validacionUsuContrasena('usuContrasenaError', $usuContrasena.val(), 'passwordId');
    var validarLugarNacimiento = validacionLugarNacimiento('detUsuLugarNacimientoError', $lugarNacimiento.val(), 'detUsuLugarNacimiento');
    if (validarTelefono === false || validarCorreo === false || validarReligion === false || validarUsuCodigo === false || validarUsuContrasena === false ||
            validarLugarNacimiento === false) {

    } else {
        Guardar();
    }
};


var validacionTelefono = function (elementError, val, element) {
    var RegularExpression = /^[0-9]{9}$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un número de teléfono');
        $("#" + element).keyup(keyTelefono);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;
    } else {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un teléfono válido');
        $("#" + element).keyup(keyTelefono);
        return false;
    }
};

var keyTelefono = function () {
    var RegularExpression = /^[0-9]{9}$/;
    var $telefono = $('#detUsuTelefono');
    if ($telefono.val() === "") {
        addNegativeAttributtes('detUsuTelefono');
        addNegativeHtml('detUsuTelefonoError', 'Debe ingresar un número de teléfono');
        return false;
    } else if ($telefono.val().match(RegularExpression)) {
        addPositiveAtributtes('detUsuTelefono');
        addPositiveHtml('detUsuTelefonoError', 'Correcto!');
        return true;
    } else {
        addNegativeAttributtes('detUsuTelefono');
        addNegativeHtml('detUsuTelefonoError', 'Debe ingresar un teléfono válido');
        return false;
    }
};

var validacionCorreo = function (elementError, val, element) {
    var RegularExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un correo electrónico');
        $("#" + element).keyup(keyCorreo);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;
    } else {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un correo electrónico válido');
        $("#" + element).keyup(keyCorreo);
        return false;
    }
};

var keyCorreo = function () {
    var RegularExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var $correo = $('#detUsuCorreo');
    if ($correo.val() === "") {
        addNegativeAttributtes('detUsuCorreo');
        addNegativeHtml('detUsuCorreoError', 'Debe ingresar un correo electrónico');
        return false;
    } else if ($correo.val().match(RegularExpression)) {
        addPositiveAtributtes('detUsuCorreo');
        addPositiveHtml('detUsuCorreoError', 'Correcto!');
        return true;
    } else {
        addNegativeAttributtes('detUsuCorreo');
        addNegativeHtml('detUsuCorreoError', 'Debe ingresar un correo electrónico válido');
        return false;
    }
};

var validacionReligion = function (elementError, val, element) {
    var RegularExpression = /^([A-Za-záéíóúñ])+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresa una religión');
        $("#" + element).keyup(keyReligion);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;

    } else {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresa una religión válida');
        $("#" + element).keyup(keyReligion);
        return false;
    }
};

var keyReligion = function () {
    var RegularExpression = /^([A-Za-záéíóúñ])+$/;
    var $religion = $('#detUsuReligion');
    if ($religion.val() === "") {
        addNegativeAttributtes('detUsuReligion');
        addNegativeHtml('detUsuReligionError', 'Debe ingresa una religión');
        return false;
    } else if ($religion.val().match(RegularExpression)) {
        addPositiveAtributtes('detUsuReligion');
        addPositiveHtml('detUsuReligionError', 'Correcto!');
        return true;

    } else {
        addNegativeAttributtes('detUsuReligion');
        addNegativeHtml('detUsuReligionError', 'Debe ingresa una religión válida');
        return false;
    }
};

var validacionUsuCodigo = function (elementError, val, element) {
    var RegularExpression = /^([\wñíóáéú])+$/;
    if (val === "") {
        addNegativeAttributtesJS(element);
        addNegativeHtml(elementError, 'Debe ingresa un nombre de usuario');
        document.getElementById(element).addEventListener('keyup', keyUsuCodigo);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtesJS(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;

    } else {
        addNegativeAttributtesJS(element);
        addNegativeHtml(elementError, 'Debe ingresa un nombre de usuario válido');
        document.getElementById(element).addEventListener('keyup', keyUsuCodigo);
        return false;
    }
};

var keyUsuCodigo = function () {
    var RegularExpression = /^([\wñíóáéú])+$/;
    var usuCodigo = document.getElementById('usuario.usu_codigo');
    if (usuCodigo.value === "") {
        addNegativeAttributtesJS('usuario.usu_codigo');
        addNegativeHtml('usuCodigoError', 'Debe ingresa un nombre de usuario');
        return false;
    } else if (usuCodigo.value.match(RegularExpression)) {
        addPositiveAtributtesJS('usuario.usu_codigo');
        addPositiveHtml('usuCodigoError', 'Correcto!');
        return true;

    } else {
        addNegativeAttributtesJS('usuario.usu_codigo');
        addNegativeHtml('usuCodigoError', 'Debe ingresa un nombre de usuario válido');
        return false;
    }
};

var validacionUsuContrasena = function (elementError, val, element) {
    var RegularExpression = /^([\wñíóáéú])+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresa una contraseña');
        $('#' + element).keyup(keyUsuContrasena);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;

    } else {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresa una contraseña válida');
        $('#' + element).keyup(keyUsuContrasena);
        return false;
    }
};

var keyUsuContrasena = function () {
    var RegularExpression = /^([\wñíóáéú])+$/;
    var usuContrasena = $('#passwordId');
    if (usuContrasena.val() === "") {
        addNegativeAttributtes('passwordId');
        addNegativeHtml('usuContrasenaError', 'Debe ingresa una contraseña');
        return false;
    } else if (usuContrasena.val().match(RegularExpression)) {
        addPositiveAtributtes('passwordId');
        addPositiveHtml('usuContrasenaError', 'Correcto!');
        return true;

    } else {
        addNegativeAttributtes('passwordId');
        addNegativeHtml('usuContrasenaError', 'Debe ingresa una contraseña válida');
        return false;
    }
};

var validacionLugarNacimiento = function (elementError, val, element) {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú]))+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresa un lugar de Nacimiento');
        $('#' + element).keyup(keyLugarNacimiento);
        return false;
    } else if (val.match(RegularExpression)) {
        addPositiveAtributtes(element);
        addPositiveHtml(elementError, 'Correcto!');
        return true;

    } else {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresa un lugar de Nacimiento');
        $('#' + element).keyup(keyLugarNacimiento);
        return false;
    }
};

var keyLugarNacimiento = function() {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú]))+$/;
    var $lugarNacimiento =$('#detUsuLugarNacimiento');
    if ($lugarNacimiento.val() === "") {
        addNegativeAttributtes('detUsuLugarNacimiento');
        addNegativeHtml('detUsuLugarNacimientoError', 'Debe ingresa un lugar de Nacimiento');
        return false;
    } else if ($lugarNacimiento.val().match(RegularExpression)) {
        addPositiveAtributtes('detUsuLugarNacimiento');
        addPositiveHtml('detUsuLugarNacimientoError', 'Correcto!');
        return true;

    } else {
        addNegativeAttributtes('detUsuLugarNacimiento');
        addNegativeHtml('detUsuLugarNacimientoError', 'Debe ingresa un lugar de Nacimiento');
        return false;
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


$("#RegistraDatosId").click(validacion);
            
