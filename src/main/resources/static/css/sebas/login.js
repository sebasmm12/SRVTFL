/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    var Aceptar = function () {
        var contraseña = $('#password').val();
        alert(contraseña);
        localStorage.clear();
        localStorage.setItem('contrasena', contraseña);
    };
    
    $("#RegistrarId").click(Aceptar);
});

