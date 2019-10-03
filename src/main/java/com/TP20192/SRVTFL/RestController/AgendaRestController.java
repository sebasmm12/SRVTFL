/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.service.IPsicologoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.TP20192.SRVTFL.models.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
/**
 *
 * @author MAURICIO
 */
@RestController
@RequestMapping("/Api/GestionarAgenda")
@SessionAttributes("usuario")
public class AgendaRestController {
    
    @Autowired
    private IPsicologoService psicologoService;
    
    @GetMapping(value="/listarAgenda")
    public  List <Actividad> gestionarAgenda(Map<String, Object> model){
        Usuario usu=(Usuario)model.get("usuario");
        Long usu_codigo = usu.getUsu_id();
        List<Actividad> actividad = new ArrayList();
        actividad = psicologoService.encontrarActividadPsicologo(usu_codigo); 
        return actividad;
    }
    
}
