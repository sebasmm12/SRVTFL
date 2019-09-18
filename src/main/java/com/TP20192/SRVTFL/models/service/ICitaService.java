/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
/**
 *
 * @author hp
 */
public interface ICitaService {

    public List<Cita> obtenerCitas();
    
    public Cita obtenerCita(Long cita_id);
    
    public void registrarCita(Cita cita);
    
    public Page<Cita> obtenerCitas(Pageable page);
    
    public List<Paciente> findPacienteByNombre(String term);
    
    public EstadoCita findEstadoCitaById(int estCitId);
    
    public void deleteCitaById(Long id);
    
    public Paciente findPacienteById(Long Id);
    
}
