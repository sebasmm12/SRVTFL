package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IPsicologoDao extends CrudRepository<Actividad,Long> {
       
    @Query("select a from  Actividad a where a.agenda_id = :usu_id")
    public List<Actividad> encontrarActividadPsicologo(@Param("usu_id")Long usu_id );
    

  
    
}
