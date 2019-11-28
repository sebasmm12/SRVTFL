document.getElementById('registrarCita').addEventListener('click', function () {
    Swal.fire({
        type: 'info',
        title: '<strong><u>Registro de Citas</u></strong>',
        html:
                '<span class="btn-text m-b-5">Seleccione el tipo de Cita que desea crear</span>' + '<br>' +
                '<a id="registrarPrimCita" onclick="clickRegistroCita()" class="btn btn-outline-success btn-xs m-t-10"><i class="fa fa-check" style="font-size: 20px"></i><span class="btn-text">Primera Cita</span></a> ' +
                '<a id="registrarCitaTratamiento" onclick="clickRegistroCitaTratamiento()" class="btn btn-outline-info btn-xs m-t-10"><i class="fa fa-check" style="font-size: 20px"></i><span class="btn-text">Cita de Tratamiento</span></a> ',
        showCancelButton: true,
        showConfirmButton: false,
        focusCancel: true,
        cancelButtonText:
                '<i class="fa fa-thumbs-down"></i> ' +
                '<span class="btn-text">Cancelar</span>',
        cancelButtonAriaLabel: 'Thumbs down'
    });
});

function clickRegistroCitaTratamiento() {
    window.location.href = "RegistrarCitaTratamiento";
}
;

function clickRegistroCita() {
    window.location.href = "RegistrarCita";
}

var alerta1 = function () {
    $(this).click(function () {
        var boton = $(this);
        var citaId = $(this).attr("cita-id");
        var cita = {
            citId: citaId
        };
        Swal.fire({
            type: 'info',
            title: '¿Esta seguro que desea bloquear la cita?',
            confirmButtonText: 'OK'
        }).then((result) => {
            $.ajax({
                url: "/api/recepcionista/bloquearHabilitarFobia",
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                async: false,
                data: JSON.stringify(cita),
                success: function (data) {
                    if (data === "1") {
                        window.location.href = "GestionarCitas";
                        return true;
                    }
                }
            });
        });
        return true;
    });
};

var alerta2 = function () {
    $(this).click(function () {
        var boton = $(this);
        var citaId = $(this).attr("cita-id2");
        var cita = {
            citId: citaId
        };
        Swal.fire({
            type: 'info',
            title: '¿Esta seguro que desea Habilitar la cita?',
            confirmButtonText: 'OK'
        }).then((result) => {
            $.ajax({
                url: "/api/recepcionista/bloquearHabilitarFobia",
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                async: false,
                data: JSON.stringify(cita),
                success: function (data) {
                    if (data === "1") {
                        window.location.href = "GestionarCitas";
                        return true;
                    }
                }
            });
        });
        return true;
    });
};

$('.btnEliminar').each(alerta1);
$('.btnHabilitar').each(alerta2);


