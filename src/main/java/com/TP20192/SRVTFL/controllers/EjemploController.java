/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/ejemplo")
@SessionAttributes("usuario")
public class EjemploController {
    
    @RequestMapping("/index")
    public String index() {
        return "Ejemplo/index";
    }
}
