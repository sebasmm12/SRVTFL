/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.ISimulacionDao;
import com.TP20192.SRVTFL.models.entity.Simulacion;
import com.TP20192.SRVTFL.models.service.ISimulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service("ISimulacionService")
public class SimulacionServiceImpl implements ISimulacionService{

    @Autowired
    public ISimulacionDao simulacionService;
    
    @Override
    public Simulacion findSimuById(Long Id) {
        return simulacionService.findById(Id).orElse(null);
    }
    
}
