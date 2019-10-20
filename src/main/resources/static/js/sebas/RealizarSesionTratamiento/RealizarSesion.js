/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var BuscarFiltros = function () {
    var nombrePaciente = $("#nombrePaciente").val();
    var datetime = $("#datetime").val();
    var FiltroFecha = document.getElementById('selectFiltroFecha');
    var selectFiltroFecha = FiltroFecha.options[FiltroFecha.selectedIndex].innerText;
    var FiltroPaciente = document.getElementById('selectFiltroPaciente');
    var selectFiltroPaciente = FiltroPaciente.options[FiltroPaciente.selectedIndex].innerText;

    $.ajax({
        url: "/api/sesion/buscar",
        type: 'GET',
        data: {
            nombrePaciente: nombrePaciente,
            datetime: datetime,
            page: 1,
            selectFiltroFecha: selectFiltroFecha,
            selectFiltroPaciente: selectFiltroPaciente
        },
        success: function (data) {
            var $newhtml = $(data);
            $("#tableCitas").replaceWith($newhtml);

        }
    });
};


$("#btnFiltrar").click(BuscarFiltros);
