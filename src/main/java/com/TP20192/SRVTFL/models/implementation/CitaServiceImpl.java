/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IActividadDao;
import com.TP20192.SRVTFL.models.dao.ICitaDao;
import com.TP20192.SRVTFL.models.dao.IDetalleUsuarioDao;
import com.TP20192.SRVTFL.models.dao.IDiagnosticoDao;
import com.TP20192.SRVTFL.models.dao.IObservacionDao;
import com.TP20192.SRVTFL.models.dao.IPacienteDao;
import com.TP20192.SRVTFL.models.dao.IPreguntasDao;
import com.TP20192.SRVTFL.models.dao.IRangoPulsoDao;
import com.TP20192.SRVTFL.models.dao.IRespuestaDao;
import com.TP20192.SRVTFL.models.dao.ITratamientoDao;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Diagnostico;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Observacion;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Pregunta;
import com.TP20192.SRVTFL.models.entity.RangoPulso;
import com.TP20192.SRVTFL.models.entity.Respuesta;
import com.TP20192.SRVTFL.models.entity.Tratamiento;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IObservacionService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp
 */
@Service("PacienteService")
public class CitaServiceImpl implements ICitaService {

    @Autowired
    public ICitaDao citaService;

    @Autowired
    public IPacienteDao pacienteService;
    
    @Autowired
    public IDetalleUsuarioDao detUsuDao;
    
    @Autowired
    public IActividadDao actividadDao;

    @Autowired
    public IPreguntasDao preguntaDao;
    
    @Autowired
    public IRespuestaDao respuestaDao;
    
    @Autowired
    public IDiagnosticoDao diagnosticoDao;
    
    @Autowired
    public ITratamientoDao tratamientoService;
    
    @Autowired
    public IObservacionDao observacionService;
    
    @Autowired
    public IRangoPulsoDao rangoPulsoService;
    
    @Transactional(readOnly = true)
    @Override
    public List<Cita> obtenerCitas() {
        return (List<Cita>) citaService.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cita obtenerCita(Long cita_id) {
        return citaService.listarCita(cita_id);
    }

    @Transactional
    @Override
    public Cita registrarCita(Cita cita) {
        return citaService.save(cita);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> obtenerCitas(Pageable page) {
        return citaService.findAll(page);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> findPacienteByNombre(String term) {
        return citaService.findPacienteByNombre(term);
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findPacienteById(Long id) {
        return pacienteService.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoCita findEstadoCitaById(int estCitId) {
        return citaService.findEstadoCitaById(estCitId);
    }

    @Transactional
    @Override
    public void deleteCitaById(Long id) {
        citaService.deleteById(id);
    }
//de los filtros

    @Override
    public Page<Cita> filtroCitaPacienteAproximado(String term, Pageable pageable) {
        return citaService.filtroCitaPacienteAproximado(term, pageable);
    }

    @Override
    public Page<Cita> filtroCitaPacienteEspecifico(String term, Pageable pageable) {
        return citaService.filtroCitaPacienteEspecifico(term, pageable);
    }

    @Override
    public Page<Cita> filtroCitaFechaEspecifico(Date fecha, Pageable pageable) {
        return citaService.filtroCitaFechaEspecifico(fecha, pageable);
    }

    @Override
    public Page<Cita> filtroCitaFechaAproximado(Date fecha, Pageable pageable) {
        return citaService.filtroCitaFechaAproximado(fecha, pageable);

    }

    @Override
    public Page<Cita> obtenerCitasporPaciente(long pac_id, Pageable page) {

        return citaService.listarCitasporPaciente(pac_id, page);
    }

    @Override
    public List<DetalleUsuario> findDetalleUsuarioByNombre(String term) {
        return detUsuDao.findDetalleUsuarioByNombre(term);
    
    }
    
    
    
    @Transactional
    @Override
    public void insertaActividad(Actividad act) {
         actividadDao.save(act);
    }

    @Override
    public Actividad ObtenerActividadPorId(Long Id) {
        return actividadDao.findById(Id).orElse(null);
    }

    @Override
    public void eliminarActividadPorId(Long Id) {
        actividadDao.deleteById(Id);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<Cita> encontrarCitasenEstadoenCita(int id, Pageable pageable) {
       return citaService.encontrarCitasenEstadoenCita(id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> filtroCombinadoEspecificoUnoaUno(Date fechaCita, String nombrePac, int id, Pageable pageable) {
       return citaService.filtroCombinadoEspecificoUnoaUno(fechaCita, nombrePac,id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> filtroCitaPacienteAproximadoCitado(String term, int id, Pageable pageable) {
        return citaService.filtroCitaPacienteAproximadoCitado(term, id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> filtroCitaPacienteEspecificoCitado(String term, int id, Pageable pageable) {
        return citaService.filtroCitaPacienteEspecificoCitado(term, id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> filtroCitaFechaEspecificoCitado(Date term, int id, Pageable pageable) {
        return citaService.filtroCitaFechaEspecificoCitado(term, id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cita> filtroCitaFechaAproximadoCitado(Date term, int id, Pageable pageable) {
        return citaService.filtroCitaFechaAproximadoCitado(term, id, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Cita encontrarCitaconPacinenteconEstado(Long id) {
        return citaService.encontrarCitaconPacinenteconEstado(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Pregunta> EncontrarPreguntasCita(Boolean preP, Long fobId, Pageable pageable) {
       return preguntaDao.EncontrarPreguntasCita(preP,fobId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Pregunta encontrarPregunta(Long Id) {
        return preguntaDao.findById(Id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Respuesta r) {
        respuestaDao.save(r);
    }

    @Transactional
    @Override
    public void registrarDiagnostico(Diagnostico d) {
        diagnosticoDao.save(d);
    }
    
    @Override
    public DetalleUsuario encontrarDetalleUsuarioByNombre(String term) {
        return detUsuDao.encontrarUsuarioByNombre(term);
    }

    @Override
    public Page<Pregunta> encontrarPreguntaPrimeraCita(Boolean primera,Pageable page) {
        return preguntaDao.encontrarPreguntaPrimeraCita(true,page);
    }

    @Override
    public Tratamiento RegistrarTratamiento(Tratamiento t) {
        return tratamientoService.save(t);
    }

    @Override
    public void registrarRespuesta(Respuesta res) {
        respuestaDao.save(res);
    }
    
    public Page<Cita> obtenerTodasLasCitas(Pageable pageable) {
        return citaService.listarCitasTratamiento(pageable);
    }

    @Override
    public Observacion registrarObservacion(Observacion obs) {
        return observacionService.save(obs);
    }

    @Override
    public RangoPulso obtenrRangoPulsoPorId(Long id) {
        return rangoPulsoService.findById(id).orElse(null);
    }

    @Override
    public List<RangoPulso> obtenerRangoPulsoPorSexo(boolean sexoBiologico) {
        return rangoPulsoService.obtenerRangoPulsoPorSexo(sexoBiologico);
    }
    
}
