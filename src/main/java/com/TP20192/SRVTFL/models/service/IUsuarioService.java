/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Usuario;

/**
 *
 * @author USUARIO
 */
public interface IUsuarioService {
    public Usuario encontrarUsuario(String usuCodigo);
}
