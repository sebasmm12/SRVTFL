/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hp
 */
public interface ICitaDao extends PagingAndSortingRepository<Cita, Long> {
    
    @Query("select c from Cita c")
    public List<Cita> listarCitas();

    @Query("select c from Cita c where c.id = :cita_id")
    public Cita listarCita(@Param("cita_id") Long cita_id);
    
    @Query("select c from Cita c join fetch c.paciente where c.citId = ?1")
    public Paciente  fetchByIdWithPacientes(Long id);
    
    @Query("select p from Paciente p where p.pacNombre like %?1%")
    public List<Paciente> findPacienteByNombre(String term);
    
    @Query("select ec from EstadoCita ec where ec.estCitId = :estCitId")
    public EstadoCita findEstadoCitaById(int estCitId);

    //filtros
    //solo nombre
    @Query("select c from Cita c where c.paciente.pacNombre like %?1%")
    public Page<Cita> filtroCitaPacienteAproximado(String term, Pageable pageable);
    
    @Query("select c from Cita c where c.paciente.pacNombre = :nombrePac")
    public Page<Cita> filtroCitaPacienteEspecifico(@Param("nombrePac")String term, Pageable pageable);
    //solo fecha
    @Query("select c from Cita c where c.citFechaHoraInicio = :fechaCita")
    public Page<Cita> filtroCitaFechaEspecifico(@Param("fechaCita") Date term, Pageable pageable);
    
    @Query("select c from Cita c where c.citFechaHoraInicio <= :fechaCita")
    public Page<Cita> filtroCitaFechaAproximado(@Param("fechaCita") Date term, Pageable pageable);
    
    //Conbinado
 
    @Query("select c from Cita c where c.paciente.pacId = :pacId")
    public Page<Cita> listarCitasporPaciente(@Param("pacId") Long usuId, Pageable page);
    
    @Query("select c from Cita c where c.paciente.pacNombre = :nombrePac and c.citFechaHoraInicio = :fechacita")
    public Page<Cita> filtroCombinadoEspecificoUnoaUno(@Param("fechacita") Date fecha,@Param("nombrePac") String nombrePac, Pageable pageable);
    
}
