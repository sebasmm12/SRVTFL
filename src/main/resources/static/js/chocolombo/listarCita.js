var alertaLoca = function(){
    $(this).click(function(){
        var boton = $(this);
        var id_plan = $(this).attr("cita-id");
        Swal.fire({
                    type: 'info',
                    title: '¿Esta seguro que desea bloquear la cita N°'+id_plan+'?',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    console.log('hola');
                });
                return true;
    });
};
$('.btnEliminar').each(alertaLoca);



