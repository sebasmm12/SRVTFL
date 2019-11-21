/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

onmessage = function (e) {
    console.log('entro');
    var x = e.data;
    if (x === 1) {
        console.log('xd');
            var thisp = this;
            sse = new EventSource('http://localhost:8080/psicologo/imagenSimulacion');
            sse.onmessage = function (evt) {
                var x = x+1;
                console.log(x);
                thisp.postMessage(evt.data);
            };
    }

};


