package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IPsicologoDao;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.service.IPsicologoService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PsicologoDatosImpl implements IPsicologoService {

    @Autowired
    private IPsicologoDao psicologoDao;
    
    @Override
    public int obtenerDiasMes(int mes, int año) {
        int  numDias = 0;
        Calendar fecha = Calendar.getInstance();
        fecha.set(año, mes, 0);
        numDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
        return numDias;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Actividad> encontrarActividadPsicologo(Long id) {
        return (List<Actividad>) psicologoDao.encontrarActividadPsicologo(id);
    }

    

}
