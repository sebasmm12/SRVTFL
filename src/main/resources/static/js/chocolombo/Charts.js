'use strict';
var ctx = document.getElementById('myChart');
var btnSimuInicio = document.getElementById('btnsimulacionInicio');
var btnSimuFin = document.getElementById('btnsimulacionFinalizar');
var btnPausaReanudar = document.getElementById('btnsimulacionPausaReanudar');
var counter = 0;
var sse;
var pausa = false;
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
btnSimuFin.addEventListener('click', function () {
    sse.close();
    $.ajax({
        url: "/psicologo/finalizarLectura",
        contentType: 'application/json;charset=utf-8',
        type: 'GET',
        success: function (data) {
            if (data === "1") {
                console.log("Finalizaion ejecutada");
            }
        }
    });
    document.getElementById("btnsimulacionInicio").disabled = false;
    document.getElementById("btnsimulacionFinalizar").disabled = true;
    document.getElementById("btnsimulacionPausaReanudar").disabled = true;
    pausa = false;
    pausar();
});
btnSimuInicio.addEventListener('click', function () {
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
        // Whatever you want to do after the wait
        sse = new EventSource('http://localhost:8080/psicologo/simulacionPulso');
        sse.onmessage = function (evt) {
            myChart.data.datasets[0].data[counter] = parseInt(evt.data);
            myChart.data.labels[counter] = counter + 1;
            counter++;
            myChart.update();
        };
    }, millisecondsToWait);

    document.getElementById("btnsimulacionInicio").disabled = true;
    document.getElementById("btnsimulacionFinalizar").disabled = false;
    document.getElementById("btnsimulacionPausaReanudar").disabled = false;
});

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

