/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping(value = {"/","/psicologo"})
public class PsicologoController {
     
    @GetMapping(value = {"/index","/"})
    public String index(Model model) {
        return "Psicologo/index";
    }
}
