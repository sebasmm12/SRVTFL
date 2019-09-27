/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var Guardar = function () {
    alert('xd');
    var nombresApellidos = $('#detUsuNombre').val();
    var direccion = $('#detUsuDireccion').val();
    var telefono = $('#detUsuTelefono').val();
    var edad = $('#detUsuEdad').val();
    var fechaNacimiento = $('#detUsuFechaNacimiento').val();
    var sexo = $('#detUsuSexo').val();
    var sexoReal = null;
    if (sexo === 'Masculino') {
        sexoReal = 'M';
    } else {
        sexoReal = 'F';
    }
    var correo = $('#detUsuCorreo').val();
    var codigoColegio = $('#detUsuCodigoColegio').val();
    var numeroDocumento = $('#detUsuTipoDocNumero').val();
    var especialidad = $('#detUsuEspecialidad').val();
    var estadoCivil = $('#detUsuEstadoCivil').val();
    var lugarNacimiento = $('#detUsuLugarNacimiento').val();
    var Ocupacion = $('#detUsuOcupacion').val();
    var Religion = $('#detUsuReligion').val();
    var UsuarioCodigo = $('#usuario.usu_codigo').val();
    var UsuarioContrasena = $('#passwordId').val();

    var Usuario = {
        usu_id: 17,
        usu_codigo: UsuarioCodigo,
        usu_contraseña: UsuarioContrasena
    };

    var detalleUsuarios = {
        usu_id: 17,
        usuario: null,
        detUsuNombre: nombresApellidos,
        detUsuCorreo: correo,
        detUsuDireccion: direccion,
        detUsuTelefono: null,
        detUsuSexo: sexoReal,
        detUsuTipoDocNumero: numeroDocumento,
        detUsuImagen: 'Hola',
        detUsuCodigoColegio: codigoColegio,
        detUsuEspecialidad: especialidad,
        detUsuEdad: edad,
        detUsuFechaNacimiento: fechaNacimiento,
        detUsuLugarNacimiento: lugarNacimiento,
        detUsuOcupacion: Ocupacion,
        detUsuReligion: Religion,
        detUsuEstadoCivil: estadoCivil,
        tipDetUsuId: null,
        tipDocId: null
    };
    $.ajax({
        url: "/api/usuario/registrar",
        data: {
            detalleUsuarios: detalleUsuarios,
            xd: 'xd',
            nombres: numeroDocumento
        },
        type: 'GET',
        success: function (data) {
            alert(data);
        }
    });
};

$("#RegistraDatosId").click(Guardar);
            