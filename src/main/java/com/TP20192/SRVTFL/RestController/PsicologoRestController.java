/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.JsonClass.DetalleUsuarioJson;
import com.TP20192.SRVTFL.models.JsonClass.TratamientoJson;
import com.TP20192.SRVTFL.models.entity.Respuesta;
import com.TP20192.SRVTFL.models.entity.Tratamiento;
import com.TP20192.SRVTFL.models.service.ICitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/Api/psicologo")
@SessionAttributes("usuario")
public class PsicologoRestController {
    
    @Autowired
    private ICitaService citaService;
    
     @RequestMapping(value = "/registrarTratamiento", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String registrarUsuario(@RequestBody TratamientoJson tratamientojson) {
        Tratamiento tr = tratamientojson.getTratamiento();
         List<Respuesta> respuestas = tratamientojson.getRespuestas();
         System.out.println("Tratamiento Creado");
        citaService.RegistrarTratamiento(tr);
         for (int i = 0; i < respuestas.size(); i++) {
             citaService.registrarRespuesta(respuestas.get(i));
         }
        return "1";
        
    }
    
    
}
