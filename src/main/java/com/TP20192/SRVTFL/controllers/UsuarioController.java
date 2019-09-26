/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/Usuario")
@SessionAttributes("usuario")
public class UsuarioController {
    
    
    @Autowired
    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;
    
    @GetMapping("/GestionarDatosPersonales")
    public String GestionarDatosPersonales(Map<String,Object> model) throws UnsupportedEncodingException {
        Long Id=((Usuario)model.get("usuario")).getUsu_id();
        DetalleUsuario detalleUsuario=usuarioService.obtenerDetalleUsuario(Id);
        model.put("DetalleUsuario",detalleUsuario);
        model.put("titulo","Datos personales");
        return "Usuario/GestionarDatosPersonales";
    }
}
