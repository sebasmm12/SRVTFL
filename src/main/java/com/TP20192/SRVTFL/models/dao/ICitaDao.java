/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hp
 */
public interface ICitaDao extends CrudRepository<Cita, Long> {
    
    @Query("select c from Cita c")
    public List<Cita> listarCitas();

    @Query("select c from Cita c where c.id = :cita_id")
    public Cita listarCita(@Param("cita_id") int cita_id);
    
    @Query("select c from Cita c join fetch c.paciente where c.citId = ?1")
    public Paciente  fetchByIdWithPacientes(Long id);
    
}
