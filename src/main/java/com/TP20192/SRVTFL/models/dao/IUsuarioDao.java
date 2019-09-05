/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author USUARIO
 */
public interface IUsuarioDao extends CrudRepository<Usuario,Long> {
    
    @Query("select u from Usuario u where u.usu_codigo = :usu_codigo")
    public Usuario encontrarUsuario(@Param("usu_codigo")String usu_codigo);
}
