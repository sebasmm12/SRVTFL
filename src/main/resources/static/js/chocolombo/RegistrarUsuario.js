$(document).ready(function(){
    $('#btnModal').on('click',function(event){
        $('#exampleModal').modal();
    });
});
var validacionBusqueda = function () {
    var nombreUsuario = $("#nombreUsu").val();
    var filtroUsuario = document.getElementById("tipoFiltro");
    var filtroSelected = filtroUsuario.options[filtroUsuario.selectedIndex].value;

    var Vnombreusuario = validarNombreUsuario("nombreUsuError", nombreUsuario, 'nombreUsu');
    if (Vnombreusuario === false) {
        alert("Error en el nombre de usuario");
        return false;
    } else {
        $.ajax({
            url: "/api/administrador/GestionarUsuarios",
            type: "GET",
            data: {
                page: 0,
                nombreUsu: nombreUsuario,
                filtro: filtroSelected
            },
            success: function (data) {
                var newView = $(data);
                $("#tablaUsuarios").replaceWith(newView);
            },
            error: function (jqXHR, textStatus, errorThrown) {},
            complete: function (jqXHR, textStatus) {}
        });
    }
};


var validarNombreUsuario = function (elementoError, valor, elemento) {
    var regex = /^([A-Za-záéíóúñ])+$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un nombre Obligatoriamente");
        $("#" + elemento).keyup(keynombreusuario);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un nombre de usuario valido");
        $("#" + elemento).keyup(keynombreusuario);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $("#" + elemento).keyup(keynombreusuario);
        return true;
    }
};

var keynombreusuario = function () {
    var regex = /^([A-Za-záéíóúñ])+$/;
    var $nombre = $('#nombreUsu');
    if ($nombre.val() === "") {
        addNegativeAttributtes('nombreUsu');
        addNegativeHtml('nombreUsuError', 'Debe ingresar un nombre Obligatoriamente');
        return false;
    } else if (!$nombre.val().match(regex)) {
        addNegativeAttributtes('nombreUsu');
        addNegativeHtml('nombreUsuError', "Debe ingresar un nombre de usuario valido");
        return false;
    } else {
        addPositiveAtributtes('nombreUsu');
        addPositiveHtml('nombreUsuError', 'Correcto');
        return true;
    }
};

var actualizar = function () {
    var detUsuNombre = $("#detUsuNombre").val();
    var detUsuCorreo = $("#detUsuCorreo").val();
    var detUsuDireccion = $("#detUsuDireccion").val();
    var detUsuTelefono = $("#detUsuTelefono").val();
    var detUsuTipoDocNumero = $("#detUsuTipoDocNumero").val();
    var estTipDoc = document.getElementById('detUsuTipoDocumento');
    var detUsuTipoDocumentoId = estTipDoc.options[estTipDoc.selectedIndex].value;
    var detUsuTipoDocumentoNombre = estTipDoc.options[estTipDoc.selectedIndex].innerText;
    var detUsuFechaNacimiento = $("#detUsuFechaNacimiento").val().toString();
    var detUsuLugarNacimiento = $("#detUsuLugarNacimiento").val();
    var estCivil = document.getElementById('detUsuEstadoCivil');
    var detUsuEstadoCivil = estCivil.options[estCivil.selectedIndex].innerText;
    var estSexo = document.getElementById('detUsuSexo');
    var detUsuSexo = estSexo.options[estSexo.selectedIndex].value;
    var detUsuCodigoColegio = $("#detUsuCodigoColegio").val();
    var detUsuEspecialidad = $("#detUsuEspecialidad").val();
    var detUsuOcupacion = $("#detUsuOcupacion").val();
    var detUsuReligion = $("#detUsuReligion").val();
    var usuCodigo = $("#usuCodigo").val();
    var usuId = $('#usuId').val();

    var fechaActual = new Date();
    var fechaNacIngresada = new Date($("#detUsuFechaNacimiento").val());
    fechaNacIngresada.setDate(fechaNacIngresada.getDate() + 1);
    fechaActual.setHours(0, 0, 0, 0);
    fechaNacIngresada.setHours(0, 0, 0, 0);
    var edad = fechaActual.getFullYear() - fechaNacIngresada.getFullYear();

    var tipDocId = {
        tipDocId: detUsuTipoDocumentoId,
        tipDocNombre: detUsuTipoDocumentoNombre
    };

    var usuario = {
        usu_id: usuId
    };
    var detUsu = {
        usu_id: usuId,
        usuario: usuario,
        detUsuNombre: detUsuNombre,
        detUsuCorreo: detUsuCorreo,
        detUsuDireccion: detUsuDireccion,
        detUsuTelefono: detUsuTelefono,
        detUsuTipoDocNumero: detUsuTipoDocNumero,
        detUsuFechaNacimiento: detUsuFechaNacimiento,
        detUsuLugarNacimiento: detUsuLugarNacimiento,
        detUsuEstadoCivil: detUsuEstadoCivil,
        detUsuSexo: detUsuSexo,
        tipDocId: tipDocId,
        detUsuCodigoColegio: detUsuCodigoColegio,
        detUsuEspecialidad: detUsuEspecialidad,
        detUsuOcupacion: detUsuOcupacion,
        detUsuReligion: detUsuReligion,
        detUsuEdad: edad
    };

    $.ajax({
        url: "/api/administrador/actualizarUsuario",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(detUsu),
        success: function (data) {
            if (data === "1") {
                Swal.fire({
                    type: 'success',
                    title: 'Usuario Actualizado Correctamente',
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

var registrar = function () {
    var detUsuNombre = $("#detUsuNombre").val();
    var detUsuCorreo = $("#detUsuCorreo").val();
    var detUsuDireccion = $("#detUsuDireccion").val();
    var detUsuTelefono = $("#detUsuTelefono").val();
    var detUsuTipoDocNumero = $("#detUsuTipoDocNumero").val();
    var estTipDoc = document.getElementById('detUsuTipoDocumento');
    var detUsuTipoDocumentoId = estTipDoc.options[estTipDoc.selectedIndex].value;
    var detUsuTipoDocumentoNombre = estTipDoc.options[estTipDoc.selectedIndex].innerText;
    var detUsuFechaNacimiento = $("#detUsuFechaNacimiento").val().toString();
    var detUsuLugarNacimiento = $("#detUsuLugarNacimiento").val();
    var estCivil = document.getElementById('detUsuEstadoCivil');
    var detUsuEstadoCivil = estCivil.options[estCivil.selectedIndex].innerText;
    var estSexo = document.getElementById('detUsuSexo');
    var detUsuSexo = estSexo.options[estSexo.selectedIndex].value;
    var detUsuCodigoColegio = $("#detUsuCodigoColegio").val();
    var detUsuEspecialidad = $("#detUsuEspecialidad").val();
    var detUsuOcupacion = $("#detUsuOcupacion").val();
    var detUsuReligion = $("#detUsuReligion").val();
    var usuCodigo = $("#usuCodigo").val();

    var fechaActual = new Date();
    var fechaNacIngresada = new Date($("#detUsuFechaNacimiento").val());
    fechaNacIngresada.setDate(fechaNacIngresada.getDate() + 1);
    fechaActual.setHours(0, 0, 0, 0);
    fechaNacIngresada.setHours(0, 0, 0, 0);

    var edad = fechaActual.getFullYear() - fechaNacIngresada.getFullYear();

    var roles = [];
    $('input[name="item_id[]"]').each(function () {
        if ($(this).val() !== "{ID}") {
            roles.push($(this).val());
        }
    });
    var tipDocId = {
        tipDocId: detUsuTipoDocumentoId,
        tipDocNombre: detUsuTipoDocumentoNombre
    };

    var usuario = {
        usu_id: 0,
        usu_codigo: usuCodigo,
        usu_contraseña: ''
    };
    var detUsu = {
        usu_id: 0,
        detUsuNombre: detUsuNombre,
        detUsuCorreo: detUsuCorreo,
        detUsuDireccion: detUsuDireccion,
        detUsuTelefono: detUsuTelefono,
        detUsuTipoDocNumero: detUsuTipoDocNumero,
        detUsuFechaNacimiento: detUsuFechaNacimiento,
        detUsuLugarNacimiento: detUsuLugarNacimiento,
        detUsuEstadoCivil: detUsuEstadoCivil,
        detUsuSexo: detUsuSexo,
        detUsuCodigoColegio: detUsuCodigoColegio,
        detUsuEspecialidad: detUsuEspecialidad,
        detUsuOcupacion: detUsuOcupacion,
        detUsuReligion: detUsuReligion,
        detUsuEdad: edad
    };
    var detUsuJ = {
        detalleUsuario: detUsu,
        usuario: usuario,
        tipDoc: tipDocId
    };

    $.ajax({
        url: "/api/administrador/registrarUsuario",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(detUsuJ),
        success: function (data) {
            if (data !== "") {
                GuardarRoles(data);
            }
        }
    });

};

var GuardarRoles = function (usu_id) {
    var id = parseInt(usu_id);
    var roles = [];
    $('input[name="item_id[]"]').each(function () {
        if ($(this).val() !== "{ID}") {
            roles.push($(this).val());
        }
    });
    roles.push(id);

    $.ajax({
        url: "/api/administrador/registrarRoles",
        type: "POST",
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(roles),
        success: function (data) {
            if (data === "1") {
                Swal.fire({
                    type: 'success',
                    title: 'Usuario creado Correctamente',
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

var validarActualizacion = function () {
    var detUsuNombre = $("#detUsuNombre").val();
    var detUsuCorreo = $("#detUsuCorreo").val();
    var detUsuDireccion = $("#detUsuDireccion").val();
    var detUsuTelefono = $("#detUsuTelefono").val();
    var detUsuTipoDocNumero = $("#detUsuTipoDocNumero").val();
    var estTipDoc = document.getElementById('detUsuTipoDocumento');
    var detUsuTipoDocumento = estTipDoc.options[estTipDoc.selectedIndex].value;
    var detUsuFechaNacimiento = $("#detUsuFechaNacimiento").val();
    var detUsuLugarNacimiento = $("#detUsuLugarNacimiento").val();
    var estCivil = document.getElementById('detUsuEstadoCivil');
    var detUsuEstadoCivil = estCivil.options[estCivil.selectedIndex].innerText;
    var estSexo = document.getElementById('detUsuSexo');
    var detUsuSexo = estSexo.options[estSexo.selectedIndex].value;
    var detUsuCodigoColegio = $("#detUsuCodigoColegio").val();
    var detUsuEspecialidad = $("#detUsuEspecialidad").val();
    var detUsuOcupacion = $("#detUsuOcupacion").val();
    var detUsuReligion = $("#detUsuReligion").val();
    var usuCodigo = $("#usuCodigo").val();

    var VdetUsuNombre = validacionNombre('detUsuNombreError', detUsuNombre, 'detUsuNombre');
    var VdetUsuCorreo = validacionCorreo('detUsuCorreoError', detUsuCorreo, 'detUsuCorreo');
    var VdetUsuDireccion = validacionDireccion('detUsuDireccionError', detUsuDireccion, 'detUsuDireccion');
    var VdetUsuTelefono = validacionTelefono('detUsuTelefonoError', detUsuTelefono, 'detUsuTelefono');
    var VdetUsuTipoDocNumero = validacionNumeroDoc('detUsuTipoDocNumeroError', detUsuTipoDocNumero, 'detUsuTipoDocNumero');
    var VdetUsuFechaNacimiento = validacionFechaNac('detUsuFechaNacimientoError', detUsuFechaNacimiento, 'detUsuFechaNacimiento');
    var VdetUsuLugarNacimiento = validacionLugNac('detUsuLugarNacimientoError', detUsuLugarNacimiento, 'detUsuLugarNacimiento');

    var VcodigoColegio = validacionCodigoColegio('detUsuCodigoColegioError', detUsuCodigoColegio, 'detUsuCodigoColegio');
    var Vespecialidad = validacionEspecialidad('detUsuEspecialidadError', detUsuEspecialidad, 'detUsuEspecialidad')
    var Vocupacion = validarOcupacion('detUsuOcupacionError', detUsuOcupacion, 'detUsuOcupacion');
    var Vreligion = validarReligion('detUsuReligionError', detUsuReligion, 'detUsuReligion');

    if (VdetUsuNombre === false || VdetUsuCorreo === false || VdetUsuDireccion === false
            || VdetUsuTelefono === false || VdetUsuTipoDocNumero === false || VdetUsuFechaNacimiento === false
            || VdetUsuLugarNacimiento === false || VcodigoColegio === false || Vespecialidad === false
            || Vocupacion === false || Vreligion === false) {
        alert("Hay un error");
        return false;
    } else {
        actualizar();
        alert("todo bien");

    }

};



var validarRegistro = function () {

    var detUsuNombre = $("#detUsuNombre").val();
    var detUsuCorreo = $("#detUsuCorreo").val();
    var detUsuDireccion = $("#detUsuDireccion").val();
    var detUsuTelefono = $("#detUsuTelefono").val();
    var detUsuTipoDocNumero = $("#detUsuTipoDocNumero").val();
    var estTipDoc = document.getElementById('detUsuTipoDocumento');
    var detUsuTipoDocumento = estTipDoc.options[estTipDoc.selectedIndex].value;
    var detUsuFechaNacimiento = $("#detUsuFechaNacimiento").val();
    var detUsuLugarNacimiento = $("#detUsuLugarNacimiento").val();
    var estCivil = document.getElementById('detUsuEstadoCivil');
    var detUsuEstadoCivil = estCivil.options[estCivil.selectedIndex].innerText;
    var estSexo = document.getElementById('detUsuSexo');
    var detUsuSexo = estSexo.options[estSexo.selectedIndex].value;
    var detUsuCodigoColegio = $("#detUsuCodigoColegio").val();
    var detUsuEspecialidad = $("#detUsuEspecialidad").val();
    var detUsuOcupacion = $("#detUsuOcupacion").val();
    var detUsuReligion = $("#detUsuReligion").val();
    var usuCodigo = $("#usuCodigo").val();
    //var table = document.getElementById('#cargarRolesUsuario');
    //var items = table.getElementsByTagName("tr");


    var roleRow = $('input[name="item_id[]"]');
    var its = [];
    $('input[name="item_id[]"]').each(function () {
        if ($(this).val() !== "{ID}") {
            its.push($(this).val());
        }
    });
    var r = its;


    var VdetUsuNombre = validacionNombre('detUsuNombreError', detUsuNombre, 'detUsuNombre');
    var VdetUsuCorreo = validacionCorreo('detUsuCorreoError', detUsuCorreo, 'detUsuCorreo');
    var VdetUsuDireccion = validacionDireccion('detUsuDireccionError', detUsuDireccion, 'detUsuDireccion');
    var VdetUsuTelefono = validacionTelefono('detUsuTelefonoError', detUsuTelefono, 'detUsuTelefono');
    var VdetUsuTipoDocNumero = validacionNumeroDoc('detUsuTipoDocNumeroError', detUsuTipoDocNumero, 'detUsuTipoDocNumero');
    var VdetUsuFechaNacimiento = validacionFechaNac('detUsuFechaNacimientoError', detUsuFechaNacimiento, 'detUsuFechaNacimiento');
    var VdetUsuLugarNacimiento = validacionLugNac('detUsuLugarNacimientoError', detUsuLugarNacimiento, 'detUsuLugarNacimiento');
    var VusuCodigo = validacionNombreUsuario('usuCodigoError', usuCodigo, 'usuCodigo');

    var VcodigoColegio = validacionCodigoColegio('detUsuCodigoColegioError', detUsuCodigoColegio, 'detUsuCodigoColegio');
    var Vespecialidad = validacionEspecialidad('detUsuEspecialidadError', detUsuEspecialidad, 'detUsuEspecialidad')
    var Vocupacion = validarOcupacion('detUsuOcupacionError', detUsuOcupacion, 'detUsuOcupacion');
    var Vreligion = validarReligion('detUsuReligionError', detUsuReligion, 'detUsuReligion');

    var Vroles = validarRoles('buscar_rolError', 'buscar_rol');



    if (VdetUsuNombre === false || VdetUsuCorreo === false || VdetUsuDireccion === false
            || VdetUsuTelefono === false || VdetUsuTipoDocNumero === false || VdetUsuFechaNacimiento === false
            || VdetUsuLugarNacimiento === false || VusuCodigo === false || VcodigoColegio === false || Vespecialidad === false
            || Vocupacion === false || Vreligion === false || Vroles === false) {
        alert("Hay un error");
        return false;
    } else {
        registrar();
        alert("todo bien");

    }
};

var validacionNombre = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s])[^\d]*([^\d\s])$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un nombre Obligatoriamente");
        $("#" + elemento).keyup(keynombre);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "El nombre no puede tener espacios al inicio o fin, ni llevar caracteres numericos");
        $("#" + elemento).keyup(keynombre);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $("#" + elemento).keyup(keynombre);
        return true;
    }
};

var keynombre = function () {
    var regex = /^([^\d\s])[^\d]*([^\d\s])$/;
    var $nombre = $('#detUsuNombre');
    if ($nombre.val() === "") {
        addNegativeAttributtes('detUsuNombre');
        addNegativeHtml('detUsuNombreError', 'Debe ingresar un nombre Obligatoriamente');
        return false;
    } else if (!$nombre.val().match(regex)) {
        addNegativeAttributtes('detUsuNombre');
        addNegativeHtml('detUsuNombreError', "El nombre no puede tener espacios al inicio o fin, ni llevar caracteres numericos");
        $("#" + 'detUsuNombre').keyup(keynombre);
        return false;
    } else {
        addPositiveAtributtes('detUsuNombre');
        addPositiveHtml('detUsuNombreError', 'Correcto');
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
        addPositiveAtributtes('detUsuCorreo');
        addPositiveHtml('detUsuCorreoError', 'Correcto');
        $("#" + elemento).keyup(keycorreo);
        return true;
    } else {
        addNegativeAttributtes("detUsuCorreo");
        addNegativeHtml('detUsuCorreoError', 'E-mail incorrecto, debe tener la forma: sample@something.somethingelse');
        $("#" + elemento).keyup(keycorreo);
        return false;
    }
};
var keycorreo = function () {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var $correo = $('#detUsuCorreo');
    if ($correo.val() === "") {
        addNegativeAttributtes('detUsuCorreo');
        addNegativeHtml('detUsuCorreoError', 'Debe ingresar un E-mail Obligatoriamente');
        return false;
    } else if ($correo.val().match(regex)) {
        addPositiveAtributtes('detUsuCorreo');
        addPositiveHtml('detUsuCorreoError', 'Correcto');
        return true;
    } else {
        addNegativeAttributtes("detUsuCorreo");
        addNegativeHtml('detUsuCorreoError', 'E-mail incorrecto, debe tener la forma: sample@something.somethingelse');
        return false;
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
    var $direccion = $('#detUsuDireccion');
    var regex = /^([^\d\s]+).*([^\s]+)$/;
    if ($direccion.val() === "") {
        addNegativeAttributtes('detUsuDireccion');
        addNegativeHtml('detUsuDireccionError', 'Debe una Direccion Obligatoriamente');
        return false;
    } else if (!$direccion.val().match(regex)) {
        addNegativeAttributtes('detUsuDireccion');
        addNegativeHtml('detUsuDireccionError', 'La direccion no debe poseer digitos al inicio ni tener espacios a los extremos, minimo 2 caracteres');
        return false;
    } else {
        addPositiveAtributtes('detUsuDireccion');
        addPositiveHtml('detUsuDireccionError', 'Correcto');
        return true;
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
    var $telefono = $('#detUsuTelefono');
    var regex = /^(\d{1})\d{7}(\d{1})$/;
    if ($telefono.val() === "") {
        addNegativeAttributtes('detUsuTelefono');
        addNegativeHtml('detUsuTelefonoError', 'Debe una Direccion Obligatoriamente');
        return false;
    } else if ($telefono.val().match(regex)) {
        addPositiveAtributtes('detUsuTelefono');
        addPositiveHtml('detUsuTelefonoError', 'Correcto');
        return true;
    } else {
        addNegativeAttributtes('detUsuTelefono');
        addNegativeHtml('detUsuTelefonoError', 'Numero de telefono incorrecto, debe poseer solo 9 digitos');
        return false;
    }
};

var validacionNumeroDoc = function (elementoError, valor, elemento) {
    var regexDni = /^([0-9])[0-9]{6}([0-9])$/;
    var regexPas = /^([0-9])[0-9]{9}([0-9])$/;
    var tipDoc = document.getElementById('detUsuTipoDocumento').selectedIndex;
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
    var tipDoc = document.getElementById('detUsuTipoDocumento').selectedIndex;
    var $numerodoc = $('#detUsuTipoDocNumero');
    if ($numerodoc.val() === "") {
        addNegativeAttributtes('detUsuTipoDocNumero');
        addNegativeHtml('detUsuTipoDocNumeroError', 'Debe ingresar un Numero de Documento  Obligatoriamente');
        return false;
    } else if (tipDoc === 0) {
        if ($numerodoc.val().match(regexDni)) {
            addPositiveAtributtes('detUsuTipoDocNumero');
            addPositiveHtml('detUsuTipoDocNumeroError', 'Correcto');
            return true;
        } else {
            addNegativeAttributtes('detUsuTipoDocNumero');
            addNegativeHtml('detUsuTipoDocNumeroError', 'Numero de Documento  debe poseer 8 digitos numericos');
            return false;
        }
    } else {
        if ($numerodoc.val().match(regexPas)) {
            addPositiveAtributtes('detUsuTipoDocNumero');
            addPositiveHtml('detUsuTipoDocNumeroError', 'Correcto');
            return true;
        } else {
            addNegativeAttributtes('detUsuTipoDocNumero');
            addNegativeHtml('detUsuTipoDocNumeroError', 'Numero de Documento  debe poseer 11 digitos numericos');
            return false;
        }
    }
};

var validacionFechaNac = function (elementoError, valor, elemento) {
    var fechaActual = new Date();
    var fechaNacIngresada = new Date(valor);
    fechaNacIngresada.setDate(fechaNacIngresada.getDate() + 1);
    fechaActual.setHours(0, 0, 0, 0);
    fechaNacIngresada.setHours(0, 0, 0, 0);
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar una fecha Obligatoriamente");
        $('#' + elemento).keyup(keyfechanac);
        return false;
    } else if (fechaNacIngresada >= fechaActual) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La fecha de nacimento no puede ser mayor a la actual");
        $('#' + elemento).keyup(keyfechanac);
        return false;
    } else if (fechaActual.getFullYear() - fechaNacIngresada.getFullYear() < 18) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "El usuario no es mayor de edad");
        $('#' + elemento).keyup(keyfechanac);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#' + elemento).keyup(keyfechanac);
        return true;
    }
};

var keyfechanac = function () {
    var $fechanac = $('#detUsuFechaNacimiento');
    var fechaActual = new Date();
    var fechaNacIngresada = new Date($('#detUsuFechaNacimiento').val());
    fechaNacIngresada.setDate(fechaNacIngresada.getDate() + 1);
    fechaActual.setHours(0, 0, 0, 0);
    fechaNacIngresada.setHours(0, 0, 0, 0);

    if ($fechanac.val() === "") {
        addNegativeAttributtes('detUsuFechaNacimiento');
        addNegativeHtml('detUsuFechaNacimientoError', "Debe ingresar una fecha Obligatoriamente");
        return false;
    } else if (fechaNacIngresada >= fechaActual) {
        addNegativeAttributtes('detUsuFechaNacimiento');
        addNegativeHtml('detUsuFechaNacimientoError', "La fecha de nacimento no puede ser mayor a la actual");
        return false;
    } else if (fechaActual.getFullYear() - fechaNacIngresada.getFullYear() < 18) {
        addNegativeAttributtes('detUsuFechaNacimiento');
        addNegativeHtml('detUsuFechaNacimientoError', "El usuario no es mayor de edad");
        return false;
    } else {
        addPositiveAtributtes('detUsuFechaNacimiento');
        addPositiveHtml('detUsuFechaNacimientoError', "Correcto");
        return true;
    }
};

var validacionLugNac = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s]+).*([^\s]+)$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un lugar de nacimiento obligatoriamente");
        $('#' + elemento).keyup(keylugnaci);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "El lugar de nacimieno no debe poseer digitos al inicio ni  espacios a los extremos, minimo 2 caracteres");
        $('#' + elemento).keyup(keylugnaci);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keylugnaci);
        return true;
    }
};

var keylugnaci = function () {
    var $lugnac = $('#detUsuLugarNacimiento');
    var regex = /^([^\d\s]+).*([^\s]+)$/;
    if ($lugnac.val() === "") {
        addNegativeAttributtes('detUsuLugarNacimiento');
        addNegativeHtml('detUsuLugarNacimientoError', "Debe ingresar un lugar de nacimiento obligatoriamente");
        return false;
    } else if (!$lugnac.val().match(regex)) {
        addNegativeAttributtes('detUsuLugarNacimiento');
        addNegativeHtml('detUsuLugarNacimientoError', "El lugar de nacimieno no debe poseer digitos al inicio ni  espacios a los extremos, minimo 2 caracteres");
        return false;
    } else {
        addPositiveAtributtes('detUsuLugarNacimiento');
        addPositiveHtml('detUsuLugarNacimientoError', 'Correcto');
        return true;
    }
};

var validacionNombreUsuario = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s])([\S])*([^\s])$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Nombre de Usuario Obligatoriamente");
        $('#' + elemento).keyup(keynombreusu);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "El nombre de usuario debe poseer 2 caraceteres como minimo e iniciar con caracteres alfabeticos");
        $('#' + elemento).keyup(keynombreusu);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keynombreusu);
        return true;
    }
};
var keynombreusu = function () {
    var regex = /^([^\d\s])([\S])*([^\s])$/;
    var usunombre = $('#usuCodigo');
    if (usunombre.val() === "") {
        addNegativeAttributtes('usuCodigo');
        addNegativeHtml('usuCodigoError', "Debe ingresar un Nombre de Usuario Obligatoriamente");
        return false;
    } else if (!usunombre.val().match(regex)) {
        addNegativeAttributtes('usuCodigo');
        addNegativeHtml('usuCodigoError', "El usuario debe poseer 2 caraceteres como minimo e iniciar con letras");
        return false;
    } else {
        addPositiveAtributtes('usuCodigo');
        addPositiveHtml('usuCodigoError', 'Correcto');
        return true;
    }
};

var validacionCodigoColegio = function (elementoError, valor, elemento) {
    var regex = /^([\d]+)([\d]+)$/;
    if (valor === "") {
        addNegativeAttributtes('detUsuCodigoColegio');
        addNegativeHtml('detUsuCodigoColegioError', "Debe Ingresar un Codigo de colegio");
        $('#' + elemento).keyup(keycodigoColegio);
        return false;
    }
    if (valor.match(regex)) {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#' + elemento).keyup(keycodigoColegio);
        return true;
    } else {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "El codigo de Colegio requiere solo valores numericos, minimo 2 digitos");
        $('#' + elemento).keyup(keycodigoColegio);
        return false;
    }
};

var keycodigoColegio = function () {
    var regex = /^([\d]+)([\d]+)$/;
    var $codigoColegio = $('#detUsuCodigoColegio');
    if ($codigoColegio.val() === "") {
        addNegativeAttributtes('detUsuCodigoColegio');
        addNegativeHtml('detUsuCodigoColegioError', "Debe Ingresar un Codigo de colegio Obligatoriamente");
        return false;
    } else if ($codigoColegio.val().match(regex)) {
        addPositiveAtributtes('detUsuCodigoColegio');
        addPositiveHtml('detUsuCodigoColegioError', "Correcto");
        return true;
    } else {
        addNegativeAttributtes('detUsuCodigoColegio');
        addNegativeHtml('detUsuCodigoColegioError', "El codigo de Colegio requiere solo valores numericos, minimo 2 digitos");
        return false;
    }
};

var validacionEspecialidad = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s])([^\d])*([^\d\s])$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar una Especialidad Obligatoriamente");
        $('#' + elemento).keyup(keyespecialidad);
        return false;
    } else if (valor.match(regex)) {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#' + elemento).keyup(keyespecialidad);
        return true;
    } else {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La especialidad no puede tener espacios a los extremos ni valores numericos, minimo 2 caracteres");
        $('#' + elemento).keyup(keyespecialidad);
        return false;
    }
};

var keyespecialidad = function () {
    var regex = /^([^\d\s])([^\d])*([^\d\s])$/;
    var $especialidad = $('#detUsuEspecialidad');
    if ($especialidad.val() === "") {
        addNegativeAttributtes('detUsuEspecialidad');
        addNegativeHtml('detUsuEspecialidadError', "Debe ingresar una Especialidad Obligatoriamente");
        return false;
    } else if ($especialidad.val().match(regex)) {
        addPositiveAtributtes('detUsuEspecialidad');
        addPositiveHtml('detUsuEspecialidadError', "Correcto");
        return true;
    } else {
        addNegativeAttributtes('detUsuEspecialidad');
        addNegativeHtml('detUsuEspecialidadError', "La especialidad no puede tener espacios a los extremos ni valores numericos, minimo 2 caracteres");
        return false;
    }
};

var validarOcupacion = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s])([^\d])*([^\d\s])$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar una Ocupacion Obligatoriamente");
        $('#' + elemento).keyup(keyocupacion);
        return false;
    } else if (!valor.match(regex)) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La especialidad no puede tener espacios a los extremos ni numeros, minimo 2 caracteres");
        $('#' + elemento).keyup(keyocupacion);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#' + elemento).keyup(keyocupacion);
        return true;
    }
};

var keyocupacion = function () {
    var regex = /^([^\d\s])([^\d])*([^\d\s])$/;
    var $ocupacion = $('#detUsuOcupacion');
    if ($ocupacion.val() === "") {
        addNegativeAttributtes('detUsuOcupacion');
        addNegativeHtml('detUsuOcupacionError', "Debe ingresar una Ocupacion Obligatoriamente");
        return false;
    } else if (!$ocupacion.val().match(regex)) {
        addNegativeAttributtes('detUsuOcupacion');
        addNegativeHtml('detUsuOcupacionError', "La especialidad no puede tener espacios a los extremos ni numeros, minimo 2 caracteres");
        return false;
    } else {
        addPositiveAtributtes('detUsuOcupacion');
        addPositiveHtml('detUsuOcupacionError', "Correcto");
        return true;
    }
};

var validarReligion = function (elementoError, valor, elemento) {
    var regex = /^([^\d\s])([^\d])*([^\d\s])$/;
    if (valor === "") {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Este Campo es obligatorio");
        $('#' + elemento).keyup(keyreligion);
        return false;
    } else if (valor.match(regex)) {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, "Correcto");
        $('#' + elemento).keyup(keyreligion);
        return true;
    } else {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "La Religion no puede tener espacios a los extremos ni numeros, minimo 2 caracteres");
        $('#' + elemento).keyup(keyreligion);
        return false;
    }
};

var keyreligion = function () {
    var regex = /^([^\d\s])([^\d])*([^\d\s])$/;
    var $religion = $('#detUsuReligion');
    if ($religion.val() === "") {
        addNegativeAttributtes('detUsuReligion');
        addNegativeHtml('detUsuReligionError', "Este Campo es obligatorio");
        return false;
    } else if ($religion.val().match(regex)) {
        addPositiveAtributtes('detUsuReligion');
        addPositiveHtml('detUsuReligionError', "Correcto");
        return true;
    } else {
        addNegativeAttributtes('detUsuReligion');
        addNegativeHtml('detUsuReligionError', "La Religion no puede tener espacios a los extremos ni numeros, minimo 2 caracteres");
        return false;
    }
};

var validarRoles = function (elementoError, elemento) {
    //var count =  $('input[name="item_id[]"]').length;
    var roles = [];
    $('input[name="item_id[]"]').each(function () {
        if ($(this).val() !== "{ID}") {
            roles.push($(this).val());
        }
    });
    if (roles.length === 0) {
        addNegativeAttributtes(elemento);
        addNegativeHtml(elementoError, "Debe ingresar un Rol de usuario Obligatoriamemte, es posible agregar varios");
        $('#' + elemento).keyup(keyroles);
        return false;
    } else {
        addPositiveAtributtes(elemento);
        addPositiveHtml(elementoError, 'Correcto');
        $('#' + elemento).keyup(keyroles);
        return true;
    }
};

var keyroles = function () {
    //var count = $('input[name="item_id[]"]').length;
    var roles = [];
    $('input[name="item_id[]"]').each(function () {
        if ($(this).val() !== "{ID}") {
            roles.push($(this).val());
        }
    });
    if (roles.length === 0) {
        addNegativeAttributtes('buscar_rol');
        addNegativeHtml('buscar_rolError', "Debe ingresar un Rol de usuario Obligatoriamemte, es posible agregar varios");
        return false;
    } else {
        addPositiveAtributtes('buscar_rol');
        addPositiveHtml('buscar_rolError', 'Correcto');
        return true;
    }
};

var getPage = function () {
    var nombreUsuario = $("#nombreUsu").val();
    var filtroUsuario = document.getElementById("tipoFiltro");
    var filtroSelected = filtroUsuario.options[filtroUsuario.selectedIndex].value;
    var $a = $(this);
    $.ajax({
        url: $a.attr("href"),
        type: 'GET',
        data: {  
            nombreUsu: nombreUsuario,
            filtro: parseInt(filtroSelected,10)
        },
        success: function (data) {
            var $newhtml = $(data);
            $("#tablaUsuarios").replaceWith($newhtml);
        },
        error: function (jqXHR, textStatus, errorThrown) {},
        complete: function (jqXHR, textStatus) {}
    });
    return false;
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

$('#btnRegistrar').click(validarRegistro);
$('#btnActualizar').click(validarActualizacion);
$('#btnBuscarUsuario').click(validacionBusqueda);
$(".pcoded-content").on("click", ".pagedList a", getPage);

