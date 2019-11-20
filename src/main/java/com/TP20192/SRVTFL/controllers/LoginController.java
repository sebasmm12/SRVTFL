/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;


import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USUARIO
 */
@Controller
public class LoginController {
    
    private Logger logger =LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping({"/login","/"})
    public String login(@RequestParam(value="error", required = false) String error,
            @RequestParam(value ="logout", required = false) String logout,
            Model model, Principal principal) {
        
        if(principal != null) {
            logger.info("Ya ha iniciado sesión anteriormente");
        }
        if(error !=null) {
            logger.info("Error en el login: "+error);
        }
        if(logout !=null) {
            logger.info("Ha cerrado sesión con exito");
        }
        return "Login/index";
    }
}
