/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.entity.ChatMessage;
import com.TP20192.SRVTFL.models.entity.Notificacion;
import com.TP20192.SRVTFL.models.service.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/notificacion")
public class NotificationController {
    
    @Autowired
    public INotificacionService notificacionService;
    
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage notificacion, SimpMessageHeaderAccessor
    headerAccesor) {
        headerAccesor.getSessionAttributes().put("username",notificacion.getSender());
        return notificacion;
    }
    
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Notificacion sendMessage(@Payload Notificacion notificacion) {
        notificacionService.save(notificacion);
        return notificacion;
    }
    
    @RequestMapping("/index")
    public String index() {
        return "Notificacion/index";
    }
    
    @RequestMapping("/enviarNotificacion")
    public String enviar(){
        return "Ejemplo/enviarNotificacion";
    }
}
