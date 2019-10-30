'use strict';
var ctx = document.getElementById('myChart');
var btnSimuInicio = document.getElementById('btnsimulacionInicio');
var btnSimuFin = document.getElementById('btnsimulacionFinalizar');
var btnPausaReanudar = document.getElementById('btnsimulacionPausaReanudar');
var btnFinalizarAbruptamente = document.getElementById('btnFinalizarAbruptamente');
var counter = 0;
var sse;
var pausa = false;

var restSimInicio;
var restSimFin;
var restSimSalidaEmergencia = false;
var restSimPulsoPromedio = 0;
var resSimI;
var citaId = document.getElementById('citId').value;
var inicio;
var fin;


document.getElementById('btnsimulacionFinalizar').disabled = true;
document.getElementById('btnsimulacionPausaReanudar').disabled = true;
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
                backgroundColor: "rgba(63,116,191,0.2)",
                borderColor: "rgba(63,116,191,1)",
                label: "Pulso: ",
                pointBackgroundColor: [],
                pointBorderColor: [],
                pointRadius: [],
                hoverBackgroundColor: "rgba(63,116,191,0.4)",
                hoverBorderColor: "rgba(63,116,191,0.4)",
                data: []
            }]
    },
    options: {
        responsive: false,
        maintainAspectRatio: false,
        title: {
            display: true,
            text: 'Grafico de Pulso del paciente',
            fontSize: 24
        }
    }
});

function parseoFecha(today) {
    var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    var time = today.getHours() + ":" + today.getMinutes();
    return date + ' ' + time;
}
btnSimuFin.addEventListener('click', function () {
    restSimFin = new Date();
    inicio = parseoFecha(restSimInicio);
    fin = parseoFecha(restSimFin);
    var observacion = document.getElementById('observaciones').value;
    var cita = {
        citId: citaId
    };
    var resSim = {
        resSimId: resSimI,
        resSimNivelInicial: 1,
        resSimNivelFinal: 2,
        restSimInicio: Date.parse(inicio),
        restSimFinal: Date.parse(fin),
        restSimSalidaEmergencia: restSimSalidaEmergencia,
        cita: cita,
        restSimPulsoPromedio: 0
    };
    sse.close();
    $.ajax({
        url: "/psicologo/finalizarLectura?observacion="+observacion,
        contentType: 'application/json;charset=utf-8',
        type: 'POST',
        data: JSON.stringify(resSim),
        success: function (data) {
            Swal.fire({
                    type: 'success',
                    title: 'Se registro las observaciones exitosamente',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "/psicologo/RegistarDiagnostico?citId="+data;
                    }
                });
        }
    });
    document.getElementById("btnsimulacionInicio").disabled = false;
    document.getElementById("btnsimulacionFinalizar").disabled = true;
    document.getElementById("btnsimulacionPausaReanudar").disabled = true;
    pausa = false;
    pausar();
});
btnSimuInicio.addEventListener('click', function () {
    restSimInicio = new Date();
    $.ajax({
        url: "/psicologo/registrarResultadoSimu?citId="+citaId,
        contentType: 'application/json;charset=utf-8',
        type: 'GET',
        success: function (data) {
            if (data !== "") {
                resSimI = parseInt(data);
                console.log("Inicializacion correcta");
            }
        }
    });
    $.ajax({
        url: "/psicologo/iniciarLectura",
        contentType: 'application/json;charset=utf-8',
        type: 'GET',
        success: function (data) {
            if (data === "1") {
                console.log("Inicializacion correcta");
            }
        }
    });
    var millisecondsToWait = 500;
    setTimeout(function () {
        sse = new EventSource('http://localhost:8080/psicologo/simulacionPulso');
        sse.onmessage = function (evt) {
            myChart.data.datasets[0].data[counter] = parseInt(evt.data);
            myChart.data.labels[counter] = counter + 1;
            restSimPulsoPromedio += parseInt(evt.data);
            counter++;
            registrarPulso(parseInt(evt.data));
            myChart.update();
        };
    }, millisecondsToWait);

    document.getElementById("btnsimulacionInicio").disabled = true;
    document.getElementById("btnsimulacionFinalizar").disabled = false;
    document.getElementById("btnsimulacionPausaReanudar").disabled = false;
});


btnFinalizarAbruptamente.addEventListener('click',function(){
    //registrarPulso();
});

function registrarPulso(pulso){
    var pulSim ={
        pulsSimId: 0,
        pulSimHora: parseoHora(new Date()),
        pulSimPulso: pulso,
        resSimId: resSimI,
        pulSimNormal: true     
    };
    
    $.ajax({
        url: "/psicologo/registrarPulso",
        contentType: 'application/json;charset=utf-8',
        type: 'POST',
        data: JSON.stringify(pulSim),
        success: function (data) {
            if (data === "1") {
                console.log("Pulso " + pulso + "Registrado Correctamente");
            }
        }
    });
}

function parseoHora(today) {
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    return time;
}

btnPausaReanudar.addEventListener('click', function () {
    if (pausa) {
        $.ajax({
            url: "/psicologo/iniciarLectura",
            contentType: 'application/json;charset=utf-8',
            type: 'GET',
            success: function (data) {
                if (data === "1") {
                    console.log("Inicializacion correcta");
                }
            }
        });

        sse = new EventSource('http://localhost:8080/psicologo/simulacionPulso');
        sse.onmessage = function (evt) {
            myChart.data.datasets[0].data[counter] = parseInt(evt.data);
            myChart.data.labels[counter] = counter + 1;
            counter++;
            myChart.update();
        };
        pausa = false;
        pausar();
    } else {
        sse.close();
        $.ajax({
            url: "/psicologo/pausarLectura",
            contentType: 'application/json;charset=utf-8',
            type: 'GET',
            success: function (data) {
                if (data === "1") {
                    console.log("Finalizaion ejecutada");
                }
            }
        });
        reanudar();
        pausa = true;
    }
});

function reanudar() {
    var hijos = btnPausaReanudar.children;
    hijos[0].classList.remove("fa", "fa-pause");
    hijos[0].classList.add("fa", "fa-play");
    hijos[1].innerText = 'Reanudar Simulacion';
    btnPausaReanudar.classList.remove('btn-outline-danger');
    btnPausaReanudar.classList.add('btn-outline-success');
}

function pausar() {
    var hijos = btnPausaReanudar.children;
    hijos[0].classList.remove("fa", "fa-play");
    hijos[0].classList.add("fa", "fa-pause");
    hijos[1].innerText = 'Pausar Simulacion';
    btnPausaReanudar.classList.remove('btn-outline-success');
    btnPausaReanudar.classList.add('btn-outline-danger');
}
