package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Actividad;
import java.util.List;


public interface IPsicologoService {
    
    public int obtenerDiasMes(int mes, int año);
    
    public List<Actividad> encontrarActividadPsicologo(Long id);
}
