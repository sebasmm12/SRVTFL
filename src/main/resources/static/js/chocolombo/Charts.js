'use strict';
//var ctx = document.getElementById('myChart');
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
var suma = 0;
var totPulsos = 0;
var promedio = 0;
var rangoPulsoMinimo = document.getElementById('pulsoMinimo').value;
var rangoPulsoMaximo = document.getElementById('pulsoMaximo').value;
var rangoPulsoProm = document.getElementById('pulsoPromedio').value;
var w;
document.getElementById('btnsimulacionFinalizar').disabled = true;
document.getElementById('btnsimulacionPausaReanudar').disabled = true;

//recien añadido
var canvas = document.getElementById("myChart");
var ctx = canvas.getContext("2d");
var horizonalLinePlugin = {
    afterDraw: function (chartInstance) {
        var yScale = chartInstance.scales["y-axis-0"];
        var canvas = chartInstance.chart;
        var ctx = canvas.ctx;
        var index;
        var line;
        var style;
        var yValue;

        if (chartInstance.options.horizontalLine) {
            for (index = 0; index < chartInstance.options.horizontalLine.length; index++) {
                line = chartInstance.options.horizontalLine[index];

                if (!line.style) {
                    style = "rgba(169,169,169, .6)";
                } else {
                    style = line.style;
                }

                if (line.y) {
                    yValue = yScale.getPixelForValue(line.y);
                } else {
                    yValue = 0;
                }

                ctx.lineWidth = 3;

                if (yValue) {
                    ctx.beginPath();
                    ctx.moveTo(0, yValue);
                    ctx.lineTo(canvas.width, yValue);
                    ctx.strokeStyle = style;
                    ctx.stroke();
                }

                if (line.text) {
                    ctx.fillStyle = style;
                    ctx.fillText(line.text, 0, yValue + ctx.lineWidth);
                }
            }
            return;
        }
        ;
    }
};
Chart.pluginService.register(horizonalLinePlugin);

var data = {
    labels: [],
    datasets: [{
            label: "My First dataset",
            fill: false,
            lineTension: 0.1,
            backgroundColor: "rgba(75,192,192,0.4)",
            borderColor: "rgba(75,192,192,1)",
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: "rgba(75,192,192,1)",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "rgba(75,192,192,1)",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: [],
        }]
};

var myChart = new Chart(ctx, {
    type: 'line',
    data: data,
    options: {
        responsive: false,
        maintainAspectRatio: false,
        title: {
            display: true,
            text: 'Grafico de Pulso del paciente',
            fontSize: 24
        },
        horizontalLine: [{
                y: rangoPulsoMaximo,
                style: "rgba(255, 0, 0, .98)",
            }, {
                y: rangoPulsoProm,
                style: "rgba(28, 154, 8, .98)",
            }, {
                y: rangoPulsoMinimo,
                style: "rgba(0, 0, 255, .98)",
            }],
        scales: {
            yAxes: [{
                ticks: {
                    suggestedMin: 0,
                    suggestedMax: 150
                }
            }]
        }
    }
});

function parseoFecha(today) {
    var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    var time = today.getHours() + ":" + today.getMinutes();
    return date + ' ' + time;
}
btnSimuFin.addEventListener('click', function () {
    promedio = suma / totPulsos;
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
        restSimPulsoPromedio: promedio
    };

    sse.close();
    $.ajax({
        url: "/psicologo/finalizarLectura?observacion=" + observacion,
        contentType: 'application/json;charset=utf-8',
        type: 'POST',
        data: JSON.stringify(resSim),
        success: function (data) {
            Swal.fire({
                type: 'success',
                title: 'Se registro las observaciones exitosamente, Promedio:' + promedio,
                confirmButtonText: 'OK'
            }).then((result) => {
                if (result.value) {
                    window.location.href = "/psicologo/RegistarDiagnostico?citId=" + data + "&simId=" + resSim.resSimId;
                }
            });
        }
    });
    document.getElementById("btnsimulacionInicio").disabled = false;
    document.getElementById("btnsimulacionFinalizar").disabled = true;
    document.getElementById("btnsimulacionPausaReanudar").disabled = true;
    pausa = false;
    w.terminate();
    w = undefined;
    pausar();
});
btnSimuInicio.addEventListener('click', function () {
    restSimInicio = new Date();
    $.ajax({
        url: "/psicologo/registrarResultadoSimu?citId=" + citaId,
        contentType: 'application/json;charset=utf-8',
        type: 'GET',
        success: function (data) {
            if (data !== "") {
                resSimI = parseInt(data);
                console.log("Inicializacion correcta" + data);
                console.log("Resultado de Simulacion " + resSimI);
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
            /*myChart.data.datasets[0].data[counter] = parseInt(evt.data);
             myChart.data.labels[counter] = counter + 1;
             restSimPulsoPromedio += parseInt(evt.data);
             counter++;
             registrarPulso(parseInt(evt.data));
             myChart.update();*/
            totPulsos++;
            suma = suma + parseInt(evt.data);
            if (myChart.data.datasets[0].data.length <= 10) {
                myChart.data.datasets[0].data[counter] = parseInt(evt.data);
                //myChart.data.datasets[0].pointBackgroundColor[counter] = "rgba(63,116,191,0.4)";
                //myChart.data.datasets[0].pointBorderColor[counter] = "rgba(63,116,191,1)";
                myChart.data.labels[counter] = counter + 1;
                // myChart.data.datasets[0].pointRadius[counter] =
                // 4;
            } else {
                myChart.data.datasets[0].data[11] = parseInt(evt.data);
                //myChart.data.datasets[0].pointBackgroundColor[11] = "rgba(63,116,191,0.4)";
                //myChart.data.datasets[0].pointBorderColor[11] = "rgba(63,116,191,1)";
                myChart.data.labels[11] = counter + 1;
                myChart.data.labels.splice(0, 1);
                myChart.data.datasets[0].data.splice(0, 1);
            }
            counter++;
            registrarPulso(parseInt(evt.data));
            myChart.update();
        };
    }, millisecondsToWait);
    setTimeout(function () {
     if (typeof (w) === "undefined") {
     w = new Worker("../js/sebas/RealizarSesionTratamiento/WorkerSimulacion.js");
     }
     console.log('hi');
     w.postMessage(1);
     w.onmessage = function (evt) {
     
     $('#divcambio').html(  
                 $('<img class="h-100" style="border: dashed blue;width: 90% !important">').attr('id', 'desktop').attr('src', 'data:image/jpeg;base64,' + evt.data));
     
     };
     }, millisecondsToWait);
    /*setTimeout(function () {
        sse = new EventSource('http://localhost:8080/psicologo/imagenSimulacion');
        sse.onmessage = function (evt) {
            $('#divcambio').html(  
                 $('<img class="h-100" style="border: dashed blue;width: 90% !important">').attr('id', 'desktop').attr('src', 'data:image/jpeg;base64,' + evt.data));
        };
    }, millisecondsToWait);*/
    document.getElementById("btnsimulacionInicio").disabled = true;
    document.getElementById("btnsimulacionFinalizar").disabled = false;
    document.getElementById("btnsimulacionPausaReanudar").disabled = false;
});


btnFinalizarAbruptamente.addEventListener('click', function () {
    //registrarPulso();
});

function registrarPulso(pulso) {
    var pulSim = {
        pulsSimId: 0,
        pulSimHora: parseoHora(new Date()),
        pulSimPulso: pulso,
        resSimId: resSimI,
        pulSimNormal: true
    };
    console.log(resSimI);
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

function registrarObservacion() {

    var obs = document.getElementById('observaciones').value;

    if (obs !== "" && obs.toString().trim() !== "" && resSimI > 0) {
        var Length = myChart.data.labels.length;
        var observacion = {
            obsId: 0,
            resSimId: resSimI,
            obsComentario: obs,
            obsTiempo: myChart.data.labels[Length - 1]
        };
        $('#observaciones').val('');
        $.ajax({
            url: "/psicologo/registrarObservaciones",
            contentType: 'application/json;charset=utf-8',
            type: 'POST',
            data: JSON.stringify(observacion),
            success: function (data) {
                if (data === "1") {
                    console.log(observacion);
                }
            }
        });
        //document.getElementById('observaciones').text = "";
    }
}

document.getElementById('btnRegistrarObservacion').addEventListener('click',
        function () {
            registrarObservacion();
        });
/*var myChart = new Chart(ctx, {
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
 }, options: {
 "horizontalLine": [{
 "y": 0.2,
 "style": "rgba(255, 0, 0, .4)",
 "text": "max"
 }, {
 "y": 60,
 "style": "#00ffff",
 }, {
 "y": 44,
 "text": "min"
 }]
 }
 }
 });*/
