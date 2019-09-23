/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IDetalleUsuarioDao;
import com.TP20192.SRVTFL.models.dao.IUsuarioDao;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service("UsuarioDatos")
public class UsuarioDatosServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Autowired
    private IDetalleUsuarioDao detUsuDao;
    
    @Override
    @Transactional(readOnly = true)
    public Usuario encontrarUsuario(String usuCodigo) {
        return usuarioDao.encontrarUsuario(usuCodigo);
    }

    @Override
    public Page<Usuario> encontrarUsuarios(Pageable page) {
     return  usuarioDao.findAll(page);  
    }

    @Override
    public Page<DetalleUsuario> encontrarDetalleUsuario(Pageable page) {
        return detUsuDao.findAll(page);
    }

}
