/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IUsuarioDao;
import com.TP20192.SRVTFL.models.entity.RolPermiso;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service("UsuarioServiceImpl")
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    public IUsuarioDao usuarioDao;
    
    private Logger logger =LoggerFactory.getLogger(UsuarioServiceImpl.class);
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuarioDao.encontrarUsuario(username);
       
       if(usuario == null) {
           logger.error("Error login no existe el usuario '"+ username + "'");
           throw new UsernameNotFoundException("Username " + username + "no existe en el sistema");
       }
       
       if(usuario.getEstadoUsuario().getEstUsuId()==2){
           logger.error("Error login el usuario '"+ username + "' ha sido bloqueado por el administrador");
           throw new UsernameNotFoundException("Username " + username + " bloqueado del sistema");
       }
       
       List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
       
       for(UsuarioRol rol: usuario.getRoles()) {
           logger.info("Role: ".concat(rol.getRol().getNombreRol()));
           roles.add(new SimpleGrantedAuthority(rol.getRol().getNombreRol())); 
       }
       
       logger.info("Bienvenido al sistema"+usuario.getUsu_codigo());
       return new User(usuario.getUsu_codigo(), usuario.getUsu_contraseña(), true,true, true, true, roles);
    }
      
}
