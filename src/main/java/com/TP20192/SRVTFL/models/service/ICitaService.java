/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Diagnostico;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Observacion;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Pregunta;
import com.TP20192.SRVTFL.models.entity.Respuesta;
import com.TP20192.SRVTFL.models.entity.Tratamiento;
import java.util.Date;
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
    
    public Cita registrarCita(Cita cita);
    
    public Page<Cita> obtenerCitas(Pageable page);
    
    public List<Paciente> findPacienteByNombre(String term);
    
    public EstadoCita findEstadoCitaById(int estCitId);
    
    public void deleteCitaById(Long id);
    
    public Paciente findPacienteById(Long Id);
    //de los filtros
    public Page<Cita> filtroCitaPacienteAproximado(String term, Pageable pageable);
    
    public Page<Cita> filtroCitaPacienteEspecifico(String term, Pageable pageable);
    
    public Page<Cita> filtroCitaFechaEspecifico(Date fecha,Pageable pageable);
    
    public Page<Cita> filtroCitaFechaAproximado(Date fecha,Pageable pageable);
    public Page<Cita> obtenerCitasporPaciente(long pac_id,Pageable page);
    public List<DetalleUsuario> findDetalleUsuarioByNombre(String term);
    
    public void insertaActividad(Actividad act);
   
    public Actividad ObtenerActividadPorId(Long Id);
    
    public void eliminarActividadPorId(Long Id);
    
    public Page<Cita> filtroCombinadoEspecificoUnoaUno(Date fechaCita, String nombrePac,int id, Pageable pageable);
    
    //Para sesión de tratamiento
   public Page<Cita> encontrarCitasenEstadoenCita(int id ,Pageable pageable);
    
    public Page<Cita> filtroCitaPacienteAproximadoCitado(String term, int id, Pageable pageable);
    

    public Page<Cita> filtroCitaPacienteEspecificoCitado(String term, int id, Pageable pageable);

 
    public Page<Cita> filtroCitaFechaEspecificoCitado(Date term,int id, Pageable pageable);
    

    public Page<Cita> filtroCitaFechaAproximadoCitado(Date term, int id, Pageable pageable);  
   
   public Cita encontrarCitaconPacinenteconEstado(Long id);
   
   //
   public Page<Pregunta> EncontrarPreguntasCita(Boolean tratId,Long fobId, Pageable pageable);
   
   public Pregunta encontrarPregunta(Long Id);
   
   public Page<Pregunta> encontrarPreguntaPrimeraCita(Boolean primera,Pageable page);
   
   public void save(Respuesta r);
   
   public void registrarDiagnostico(Diagnostico d);
   public DetalleUsuario encontrarDetalleUsuarioByNombre(String term);
   
   public Tratamiento RegistrarTratamiento(Tratamiento t);
   
   public void registrarRespuesta(Respuesta res);
   public Page<Cita> obtenerTodasLasCitas(Pageable pageable);
   
   public Observacion registrarObservacion(Observacion obs);
}
