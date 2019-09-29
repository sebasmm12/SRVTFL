/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Usuario;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/usuario")
@SessionAttributes("usuario")
public class UsuarioRestController {
    
    @GetMapping(value="/registrar")
    public String RegistrarDetalleUsuario(DetalleUsuario detalleUsuarios,String xd,int nombres) {
        DetalleUsuario DetUsuModificado=detalleUsuarios;
      //  Long Id=((Usuario)model.get("usuario")).getUsu_id();
        //DetUsuModificado.setUsu_id(Id);
        Usuario usuario=DetUsuModificado.getUsuario();
        //usuario.setUsu_id(Id);
        DetUsuModificado.setUsuario(usuario);
        
        return "1";
    }
}
