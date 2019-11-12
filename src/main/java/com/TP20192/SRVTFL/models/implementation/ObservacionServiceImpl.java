/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IObservacionDao;
import com.TP20192.SRVTFL.models.entity.Observacion;
import com.TP20192.SRVTFL.models.service.IObservacionService;
import java.util.List;
import java.util.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service("IObservacionService")
public class ObservacionServiceImpl implements IObservacionService{

    @Autowired
    private IObservacionDao observacionService;
    
    @Override
    public void registrarObservacion(Observacion obs) {
        observacionService.save(obs);
    }

    @Override
    public List<Observacion> obtenerObservaciones(Long pulId) {
        return observacionService.listarObservacionesSesion(pulId);
    }
    
}
