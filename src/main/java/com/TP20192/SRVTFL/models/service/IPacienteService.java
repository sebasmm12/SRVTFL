/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author alumno
 */
public interface IPacienteService {
    public List<Paciente> obtenerCitas();
    
    public Paciente obtenerPaciente(Long cita_id);
    
    public int registrarPaciente(Paciente cita);
    
    public Page<Paciente> obtenerPacientes(Pageable page);
    
    public List<Paciente> findPacienteByNombre(String term);    
    
    public TipoDocumento findDocumentoById(Long id);
    
    public Paciente findOnePaciente(String nombre, String apellido);
    
    public Paciente findOnePaciente2(String nombre, String apellido);
    
    public Paciente verificarDniPaciente(String dni);
    
    public Page<Paciente> buscarPacienteNombrePageable (String paciente_nombre, Pageable page);
    
     public Page<Paciente> findPacienteByNombrePageable(String term, Pageable page);
}
