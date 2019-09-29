/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Paciente;
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
 
    @Query("select c from Cita c where c.paciente.pacId = :pacId")
    public Page<Cita> listarCitasporPaciente(@Param("pacId") Long usuId, Pageable page);
    
}