'use strict';
$(function(){
    var stompClientP = null;
    var username = null;
    let count = 0;
    
    var ctx = document.getElementById('myChart');
            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"],
                    datasets: [
                        {
                            label: 'Serial',
                            backgroundColor: "rgba(255,99,132,0.2)",
                            borderColor: "rgba(255,99,132,1)",
                            borderWidth: 2,
                            hoverBackgroundColor: "rgba(255,99,132,0.4)",
                            hoverBorderColor: "rgba(255,99,132,1)",
                            data: [10, 8, 3, 5, 7, 6, 7, 3, 4, 5]
                        }
                    ]
                }
            });
    
    
    function connectP(event){
        var socketP = new SockJS('/javatechie');
        stompClientP = Stomp.over(socketP);
        stompClientP.connect({}, onConnectedP,onErrorP);
    }
    function onConnectedP(){
        stompClientP.subscribe('/topic/chart', onMessageReceivedP);
    }
    
    function onErrorP(error) {
        return true;
    }
    function sendP(event){
        var pulso = $('#coordenate').val();
        stompClientP.send("/app/pulso.send",{},JSON.stringify(pulso));
    }
    function onMessageReceivedP(payload){
        var message = JSON.parse(payload.body);
        myChart.data.labels.push(count);
        myChart.data.datasets.forEach(dataSet =>{
            dataSet.data.push(message);
        });
        count++;
        myChart.update;
    }
    connectP();
    $('btnAdd').click(sendP);
});