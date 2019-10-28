/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IPulsoSimulacionDao;
import com.TP20192.SRVTFL.models.entity.PulsoSimulacion;
import com.TP20192.SRVTFL.models.service.IPulsoSimulacionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hp
 */
public class PulsoSimulacionServiceImpl implements IPulsoSimulacionService{
    
    @Autowired
    private IPulsoSimulacionDao pulsoSimulacionDao;

    @Override
    public void insertarPulsoSimulacion(PulsoSimulacion ps) {
        pulsoSimulacionDao.save(ps);
    }

    @Override
    public void eliminarPulsoSimulacion(Long Id) {
        pulsoSimulacionDao.deleteById(Id);
    }
    
}
