/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Simulacion;

/**
 *
 * @author hp
 */
public interface ISimulacionService {
    
    public Simulacion findSimuById(Long Id);
}
