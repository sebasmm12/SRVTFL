/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
public class RecepcionistaRestController {
    
    @Autowired
    public ICitaService citaService;
    
    @GetMapping("/Api/GestionarCitas/{page}")
    public Page<Cita> listarCita(@RequestParam(name="page", defaultValue="0") int page){  
        Pageable pageRequest = PageRequest.of(page, 5);    
        Page<Cita> citas = citaService.obtenerCitas(pageRequest);
        return citas;       
    }
    
     @GetMapping("/Api/ejemplo")
    public List<String> listarCitas(){  
       List<String> abc= new ArrayList();
       abc.add("oo");
       abc.add("bb");
       return abc;       
    }
    
    
    
    
}
