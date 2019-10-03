/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import com.TP20192.SRVTFL.models.service.IResultadoSimulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gerhard
 */
@RestController
@RequestMapping("/api/vr")
public class VRRestController {
    
     @Autowired
    private IResultadoSimulacionService resultadoSimulacionService;
    
    @GetMapping("/inicio")
    public  ResultadoSimulacion inicio(){      
        return  resultadoSimulacionService.obtenerParametrosiniciales();
    }
      @GetMapping("/guardar/{id}/{nivelfinal}")
    public  void fin(@Param("nivelfinal") int nivelfinal,@Param("id") Long id  ){      
          resultadoSimulacionService.finalizar(nivelfinal, id);
                    
          
          
    }
    
    
}
