/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Pregunta;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author USUARIO
 */
public interface IPreguntasDao extends PagingAndSortingRepository<Pregunta, Long> {
    
    @Query(value= "select p from Pregunta p join fetch p.fobId f"
            + " where p.pregPrimeraVez= :preP and f.fobId = :fobId",
            countQuery = "select count(p) from Pregunta p join p.fobId f"
                    + " where p.pregPrimeraVez = :preP and f.fobId = :fobId")
    public Page<Pregunta> EncontrarPreguntasCita(@Param("preP") Boolean preP,@Param("fobId") int fobId ,Pageable pageable);
}
