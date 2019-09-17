/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.IPsicologoService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping(value = {"/","/psicologo"})
@SessionAttributes("usuario")
public class PsicologoController {
   
    @Autowired
    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;
    
    @Autowired
    private IPsicologoService psicologoService;
    
    @GetMapping(value = {"/index","/"})
    public String index(Model model,Authentication authentication) {
        Usuario usuario = usuarioService.encontrarUsuario(authentication.getName());
        model.addAttribute("usuario", usuario);
        return "Psicologo/index";
    }
    
    @GetMapping(value = "/agenda")
    public String gestionarAgenda(Map<String, Object> model){
        Usuario usu=(Usuario)model.get("usuario");
        Long usu_codigo = usu.getUsu_id();
        List<Actividad> actividad = new ArrayList<Actividad>();
        actividad = psicologoService.encontrarActividadPsicologo(usu_codigo);
        int mes = psicologoService.obtenerDiasMes(15, 9);
        model.put("cantidad", mes);
        model.put("actividades", actividad);
        return "Agenda/agenda";
    }
    
    
    
    
    
}
