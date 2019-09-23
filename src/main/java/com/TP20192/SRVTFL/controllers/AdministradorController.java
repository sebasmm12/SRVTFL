/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.service.IUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hp
 */
@Controller
@RequestMapping("/Administrador")
@SessionAttributes("usuario")
public class AdministradorController {

    @Qualifier("UsuarioDatos")
    @Autowired
    public IUsuarioService usuarioService;

    @RequestMapping("/GestionarUsuarios")
    public String gestionarUsuarios(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);
        Page<DetalleUsuario> detUsus = usuarioService.encontrarDetalleUsuario(pageRequest);
        PageRender<Usuario> pageRender = new PageRender("Administrador/GestionarUsuarios", detUsus);
        model.addAttribute("detUsus", detUsus);
        model.addAttribute("titulo","Gestion de Usurios y Cuentas");
        model.addAttribute("page",pageRender);
        return "Administrador/ListarUsuario";
    }
}
