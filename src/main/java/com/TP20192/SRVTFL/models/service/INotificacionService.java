/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Notificacion;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface INotificacionService {

    public List<Notificacion> findAll();

    public void save(Notificacion notificacion);

    public void delete(Notificacion notificacion);

}
