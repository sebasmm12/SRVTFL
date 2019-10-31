/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.TP20192.SRVTFL.models.entity.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author hp
 */
public interface IPacienteDao extends PagingAndSortingRepository<Paciente, Long>   {
    
    @Query("select c from Paciente c where c.pacId = :paciente_id")
    public Paciente buscarPaciente (@Param("paciente_id") Long paciente_id);
    
    @Query("select p from Paciente p where p.pacNombre like %?1%")
    public List<Paciente> findPacienteByNombre(String term);
    
    @Query("select c from Paciente c where c.pacNombre = :paciente_nombre and c.pacApellido = :paciente_apellido")
    public Paciente buscarPacienteNombre (@Param("paciente_nombre") String paciente_nombre,
                                          @Param("paciente_apellido") String paciente_apellido);
}

