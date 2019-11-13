package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPsicologoService {
    
    public int obtenerDiasMes(int mes, int año);
    
    public List<Actividad> encontrarActividadPsicologo(Long id);
    
    
}
