/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 *
 * @author USUARIO
 */
public interface IUsuarioService {
    public Usuario encontrarUsuario(String usuCodigo);
    
    public Page<Usuario> encontrarUsuarios(Pageable page);
    
    public Page<DetalleUsuario> encontrarDetalleUsuario(Pageable page);
    
}

