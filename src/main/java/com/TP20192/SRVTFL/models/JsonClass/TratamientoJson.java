/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.JsonClass;
import com.TP20192.SRVTFL.models.entity.Tratamiento;
import com.TP20192.SRVTFL.models.entity.Respuesta;
import java.util.List;
/**
 *
 * @author hp
 */
public class TratamientoJson {
    
    public Tratamiento tratamiento;
    public List<RespuestaJson> respuestas;

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public List<RespuestaJson> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaJson> respuestas) {
        this.respuestas = respuestas;
    }

}
