/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.Entidades.VrSimulacion;
import com.TP20192.SRVTFL.EntidadesConfigurables.VrVariables;
import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import com.TP20192.SRVTFL.models.service.IResultadoSimulacionService;
import com.TP20192.SRVTFL.models.service.IVrAuxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gerhard
 */
@RestController
@RequestMapping("/api/vr")
public class VRRestController {

   // @Autowired
    //private IResultadoSimulacionService resultadoSimulacionService;

    @Autowired
    private IVrAuxService VrAuxService;

    /*  @GetMapping("/inicio")
    public  ResultadoSimulacion inicio(){      
        return  resultadoSimulacionService.obtenerParametrosiniciales();
    }
      @GetMapping("/guardar/{id}/{nivelfinal}")
    public  void fin(@Param("nivelfinal") int nivelfinal,@Param("id") Long id  ){      
          resultadoSimulacionService.finalizar(nivelfinal, id);
        }
     */
    @GetMapping("/inicio")
    public VrSimulacion inicar() {
        return VrAuxService.iniciarSimulacion();
    }
    
    @GetMapping("/guardar/{nivelfinal}/{resSimId}")
    public void  guardar(@PathVariable("nivelfinal") int nivelfinal,@PathVariable("resSimId") Long resSimId) {

         VrAuxService.guardarNivel(resSimId , nivelfinal);

    }
    
       @GetMapping("/prueba/{inicial}/{simulacionId}/{resSimId}/{nombreSimulacion}")
    public void pruebas(@PathVariable("inicial") int inicial , @PathVariable("simulacionId") Long simulacionId,
            @PathVariable("resSimId") Long resSimId, @PathVariable("nombreSimulacion") String nombreSimulacion
            
            ) {
         VrVariables.setNivelInicial(inicial);
         VrVariables.setNombreSimulacio(nombreSimulacion);
         VrVariables.setResSimId(resSimId);
         VrVariables.setSimulacionId(simulacionId);
    }
    @PostMapping("/screenshoot")
    public void create(@RequestBody String cadena) { 
       VrVariables.setImagen(cadena.substring(0,cadena.length()- 1));
      // return cadena;
    }
     @GetMapping("/screenshoot2")
    public String loco( ) {       
        return  VrVariables.getImagen();
    }
    
    
    
}
