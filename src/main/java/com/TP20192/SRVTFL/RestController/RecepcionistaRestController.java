/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.service.IPacienteService;
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
@RequestMapping("/api/recepcionista")
//@SessionAttributes("usuario")
public class RecepcionistaRestController {
    
    @Autowired
    private IPacienteService pacienteService;
    
    @RequestMapping(value="/verificarNombre",method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarNombrePaciente(@RequestBody String term){
        Paciente pac = pacienteService.findOnePaciente(term);
        if(pac == null){
            return "0";
        }else{
            return pac.getPacId()+"";
        }
    }   
}
