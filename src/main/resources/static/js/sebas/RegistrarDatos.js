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
    var sexo =$('#selectedSexo').val();
    var correo = $('#detUsuCorreo').val();
    var codigoColegio = $('#detUsuCodigoColegio').val();
    var numeroDocumento = $('#detUsuTipoDocNumero').val();
    var especialidad = $('#detUsuEspecialidad').val();
    var estado =document.getElementById('civilSelect');
    var estadoCivil = estado.options[estado.selectedIndex].innerText;
    alert(estadoCivil);
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
    $.ajax({
        url: "/api/usuario/registrar",
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(detalleUsuario),
        success: function (data) {
            if(data==="1") {
                ActualizarImagen();   
            }
        }
    });
};

var ActualizarImagen = function(){
    
       var uploadfile = new FormData($("#form")[0]);    
       $.ajax({
        url: "/api/usuario/actualizarImagen",
        type: 'POST',
        data: uploadfile ,
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
                        window.location.href = "/";
                    }
                });
            }
        }
    });
    
};

$("#RegistraDatosId").click(Guardar);
            