/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.JsonClass.CitaJson;
import java.util.Map;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Usuario;
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
@RequestMapping("/api/paciente")
@SessionAttributes("usuario")
public class PacienteRestController {
    
     @Autowired
    private IPacienteService pacienteService; 
    
    
     @RequestMapping(value = "/ingresarPaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
      public String ingresarPaciente(@RequestBody Paciente paciente, Map<String, Object> model) {
        Usuario usu = (Usuario)model.get("usuario");
        Paciente pac = new Paciente();
        pac =paciente;
        pac.setUsu_id(usu.getUsu_id());
        //pac.setPacId(null);
        pacienteService.registrarPaciente(pac);
        return "1";
    }
      
      
      @RequestMapping(value = "/modificarPaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
      public String modificarPaciente(@RequestBody Paciente paciente, Map<String, Object> model) {
        Usuario usu = (Usuario)model.get("usuario");
        Paciente pac = new Paciente();
        pac = paciente;
        pac.setUsu_id(usu.getUsu_id());
        //pac.setPacId(null);
        pacienteService.registrarPaciente(pac);
        return "1";
    }
      
      @RequestMapping(value = "/verificarMultiplicidadNdoc", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
      public String verificarMultiplicidadNdoc(@RequestBody String term, Map<String, Object> model) {
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(term);
        if(pac == null){
            return "1";
        }else{
            return "0";
        }
    }
    
    
    /*
    @PostMapping(value="/GuardarPaciente")
    public String guardarPaciente(@Valid Paciente paciente,BindingResult result, Map<String, Object>  model, SessionStatus se ){   
        Usuario usu = (Usuario)model.get("usuario");
        if(result.hasErrors()){
            model.put("titulo", "Registro de Paciente");
            return "Paciente/RegistrarPaciente";
        }
        paciente.setUsu_id(usu.getUsu_id());
        paciente.setPacId(null);
        System.out.println(paciente.toString());
                  pacienteService.registrarPaciente(paciente);
        return "redirect:/Paciente/GestionarPacientes";
    }
    */
}
