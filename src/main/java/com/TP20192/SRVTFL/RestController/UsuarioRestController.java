/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.JsonClass.DetalleUsuarioJson;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoNotificacion;
import com.TP20192.SRVTFL.models.entity.Notificacion;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.IUploadFileService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/usuario")
@SessionAttributes("usuario")
public class UsuarioRestController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUploadFileService uploadService;
    
    @RequestMapping(value = "/registrar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String RegistrarDetalleUsuario(@RequestBody DetalleUsuarioJson DetalleUsuarioJson, Map<String, Object> model) {
        DetalleUsuario DetUsuModificado = DetalleUsuarioJson.getDetalleUsuario();
        Long Id = ((Usuario) model.get("usuario")).getUsu_id();
        DetUsuModificado.setUsu_id(Id);
        Usuario usuario = ((Usuario) model.get("usuario"));
        DetalleUsuario DetUsuOriginal = usuarioService.obtenerDetalleUsuario(Id); 
        DetUsuModificado.setUsuario(usuario);
        DetUsuModificado.getUsuario().setUsu_id(Id);
        DetUsuModificado.getUsuario().setEstadoUsuario(usuario.getEstadoUsuario());
        DetUsuModificado.getUsuario().setUsu_codigo(DetalleUsuarioJson.getUsuario().getUsu_codigo());
        DetUsuModificado.getUsuario().setUsu_contraseña(passwordEncoder.encode(DetalleUsuarioJson.getUsuario().getUsu_contraseña()));
        DetUsuModificado.getUsuario().setEstadoUsuario(usuario.getEstadoUsuario());
        DetUsuModificado.getUsuario().setNotificaciones(usuario.getNotificaciones());
        DetUsuModificado.getUsuario().setRoles(usuario.getRoles());
        DetUsuModificado.setTipDetUsuId(DetUsuOriginal.getTipDetUsuId());
        DetUsuModificado.setTipDocId(DetUsuOriginal.getTipDocId());
        usuarioService.guardarDetalleUsuario(DetalleUsuarioJson.getDetalleUsuario());
        usuarioService.guardarUsuario(DetUsuModificado.getUsuario());
        return "1";
    }

    @RequestMapping(value = "/actualizarImagen", method = RequestMethod.POST)
    public String actualizarImagen(@RequestParam("uploadfile") MultipartFile uploadfile, Map<String, Object> model) {
        Long Id = ((Usuario) model.get("usuario")).getUsu_id();
        DetalleUsuario detUsu = usuarioService.obtenerDetalleUsuario(Id);
        String uniqueFileName = null;
        try {
            uniqueFileName = uploadService.copy(uploadfile);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        detUsu.setDetUsuImagen(uniqueFileName);
        usuarioService.guardarDetalleUsuario(detUsu);
        return "1";
    }
    @RequestMapping(value="/notificacionVista",method = RequestMethod.POST)
    public String RegistrarNotificacionVisto(Long notId) {
        Notificacion notificacion = usuarioService.findNotificacion(notId);
        EstadoNotificacion estado = new EstadoNotificacion();
        Integer id = 1;
        estado.setEstNotId(id.longValue());
        estado.setEstNotNombre("Visto");
        notificacion.setEstNotId(estado);
        
        usuarioService.saveNotificacion(notificacion);
        
        return "1";
    }
}
