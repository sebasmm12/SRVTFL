/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Observacion;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IObservacionService {
    
    public void registrarObservacion(Observacion obs);
    
    public List<Observacion> obtenerObservaciones (Long pulId);
}
