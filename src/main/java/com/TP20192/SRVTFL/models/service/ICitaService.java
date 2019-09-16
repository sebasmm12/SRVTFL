/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Cita;
import java.util.List;

/**
 *
 * @author hp
 */
public interface ICitaService {

    public List<Cita> obtenerCitas();
    
    public Cita obtenerCita(int cita_id);
    
    public void registrarCita(Cita cita);
}
