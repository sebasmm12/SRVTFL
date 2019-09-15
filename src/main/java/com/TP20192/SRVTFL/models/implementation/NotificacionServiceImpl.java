/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.INotificacionDao;
import com.TP20192.SRVTFL.models.entity.Notificacion;
import com.TP20192.SRVTFL.models.service.INotificacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service("notificacionService")
public class NotificacionServiceImpl implements INotificacionService {

    @Autowired
    private INotificacionDao notificacionDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Notificacion> findAll() {
       return  (List<Notificacion>) notificacionDao.findAll();
    }

    @Override
    @Transactional
    public void save(Notificacion notificacion) {
       notificacionDao.save(notificacion);
    }

    @Override
    @Transactional
    public void delete(Notificacion notificacion) {
       notificacionDao.delete(notificacion);
    }
    
}
