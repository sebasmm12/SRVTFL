/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Tratamiento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hp
 */
public interface ITratamientoDao extends PagingAndSortingRepository<Tratamiento, Long>{
    
    @Query("select t from Tratamiento t where t.pacienteId = :idtratamiento")
    public Tratamiento obtenerTratamientoPorPaciente(@Param("idtratamiento") Long idtratamiento);
    
    @Query("select t from Tratamiento t where t.pacienteId = :idPaciente and t.estadoTratamiento = :estadoTrat")
    public List<Tratamiento> obtenerTratamientosPorPaciente(@Param("idPaciente") Long idPaciente, @Param("estadoTrat") Integer estadoTrat);
    
    @Query("select t from Tratamiento t where t.pacienteId = :idPaciente and t.estadoTratamiento = :estadoTrat and t.fobId = :idFobia")
    public Tratamiento obtenerTratamientoPorPacienteFobia(@Param("idPaciente") Long idPaciente, @Param("idFobia") Long idFobia, @Param("estadoTrat") Integer estadoTrat);
}
