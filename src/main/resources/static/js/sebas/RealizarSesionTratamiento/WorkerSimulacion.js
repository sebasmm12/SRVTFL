/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function timedCount() {
    console.log("Llego al web workers");
   // sse = new EventSource('http://localhost:8080/psicologo/imagenSimulacion');
    //sse.onmessage = function (event) {

      //  postMessage(event.data);
       postMessage("1");
    //};
   
    setTimeout("timedCount()", 500);
}

timedCount();