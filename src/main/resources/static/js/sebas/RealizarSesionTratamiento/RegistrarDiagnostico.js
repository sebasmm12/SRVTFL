/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var RegistrarDiagnostico = function () {

    var Observaciones = $("#diaObservaciones");
    var Diagnostico = $("#diaDiagnostico");
    var Recomendacion = $("#diaRecomendacion");

    var validarObservaciones = ValidacionObservaciones('diaObservaciones', Observaciones.val(), 'ObservacionesError');
    var validarDiagnostico = ValidacionDiagnostico('diaDiagnostico', Diagnostico.val(), 'DiagnosticoError');
    var validarRecomendacion = ValidacionRecomendacion('diaRecomendacion', Recomendacion.val(), 'RecomendacionError');

    if (validarObservaciones === false || validarDiagnostico === false || validarRecomendacion === false) {

    } else {
        Guardar();

    }

};

var Guardar = function (){
    
    let params = new URLSearchParams(location.search);
    
    var citId = params.get('citId');
    
    $.ajax({
       url : '',
       type: 'POST',
       
    });
};

var ValidacionObservaciones = function (element, val, elementError) {

    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar una observación');
        $('#' + element).keyup(keyObservacion);
        return false;
    } else {
        if (val.match(RegularExpression)) {
            addPositiveAtributtes(element);
            addPositiveHtml(elementError, 'Correcto!');
            return true;
        } else {
            addNegativeAttributtes(element);
            addNegativeHtml(elementError, 'Debe ingresar una observación');
            $('#' + element).keyup();
        }
    }
};

var keyObservacion = function () {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    var Observaciones = $("#diaObservaciones");
    if (Observaciones.val() === "") {
        addNegativeAttributtes('diaObservaciones');
        addNegativeHtml('ObservacionesError', 'Debe ingresar una observación');
        return false;
    } else {
        if (Observaciones.val().match(RegularExpression)) {
            addPositiveAtributtes('diaObservaciones');
            addPositiveHtml('ObservacionesError', 'Correcto!');
            return true;

        } else {
            addNegativeAttributtes('diaObservaciones');
            addNegativeHtml('ObservacionesError', 'Debe ingresar una observación');
            return false;
        }
    }
};

var ValidacionDiagnostico = function (element, val, elementError) {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar un diagnóstico');
        $('#' + element).keyup(KeyDiagnostico);
        return false;
    } else {
        if (val.match(RegularExpression)) {
            addPositiveAtributtes(element);
            addPositiveHtml(elementError, 'Correcto!');
            return true;
        } else {
            addNegativeAttributtes(element);
            addNegativeHtml(elementError, 'Debe ingresar un diagnóstico');
            $('#' + element).keyup(KeyDiagnostico);
        }
    }
};

var KeyDiagnostico = function (){
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    var Diagnostico = $("#diaDiagnostico");
    if (Diagnostico.val() === "") {
        addNegativeAttributtes('diaDiagnostico');
        addNegativeHtml('ObservacionesError', 'Debe ingresar un diagnóstico');
        return false;
    } else {
        if (Diagnostico.val().match(RegularExpression)) {
            addPositiveAtributtes('diaDiagnostico');
            addPositiveHtml('DiagnosticoError', 'Correcto!');
            return true;

        } else {
            addNegativeAttributtes('diaDiagnostico');
            addNegativeHtml('DiagnosticoError', 'Debe ingresar un diagnóstico');
            return false;
        }
    }
};


var ValidacionRecomendacion = function (element, val, elementError) {
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    if (val === "") {
        addNegativeAttributtes(element);
        addNegativeHtml(elementError, 'Debe ingresar una recomendación');
        $('#' + element).keyup(KeyRecomendacion);
        return false;
    } else {
        if (val.match(RegularExpression)) {
            addPositiveAtributtes(element);
            addPositiveHtml(elementError, 'Correcto!');
            return true;
        } else {
            addNegativeAttributtes(element);
            addNegativeHtml(elementError, 'Debe ingresar una recomendación');
            $('#' + element).keyup(KeyRecomendacion);
            return false;
        }
    }
};


var KeyRecomendacion = function (){
    var RegularExpression = /^(([A-Za-záéíóú])*(\s){0,1}([A-Za-záéíóú\d]))+$/;
    var Recomendacion = $("#diaRecomendacion");
    if (Recomendacion.val() === "") {
        addNegativeAttributtes('diaRecomendacion');
        addNegativeHtml('RecomendacionError', 'Debe ingresar un diagnóstico');
        return false;
    } else {
        if (Recomendacion.val().match(RegularExpression)) {
            addPositiveAtributtes('diaRecomendacion');
            addPositiveHtml('RecomendacionError', 'Correcto!');
            return true;

        } else {
            addNegativeAttributtes('diaRecomendacion');
            addNegativeHtml('RecomendacionError', 'Debe ingresar un diagnóstico');
            return false;
        }
    }
};


var addPositiveAtributtes = function (id) {
    $("#" + id).removeClass('is-invalid');
    $("#" + id).addClass('is-valid');
};

var addNegativeAttributtes = function (id) {
    $("#" + id).removeClass('is-valid');
    $("#" + id).addClass('is-invalid');
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



$("#btnRegistrarDiagnostico").click(RegistrarDiagnostico);
