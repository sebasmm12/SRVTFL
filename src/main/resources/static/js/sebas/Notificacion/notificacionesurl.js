/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(".notificaciones").each(function () {
    $(this).click(function () {

        var notId = $(this).attr('data-id');
        var url =$(this).attr('data-url');
        $.ajax({
            url: '/api/usuario/notificacionVista',
            method: 'POST',
            data:{
              notId: parseInt(notId,10)  
            },
            success: function (data) {
                window.location.href = ""+url;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                
            }
        });
    });
});