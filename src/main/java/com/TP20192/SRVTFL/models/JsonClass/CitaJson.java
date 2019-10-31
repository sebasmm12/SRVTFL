/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.JsonClass;
import com.TP20192.SRVTFL.models.entity.Cita;

/**
 *
 * @author hp
 */
public class CitaJson {
    
    private Cita cita;
    private Long pacienteId;
    private Long fobiaId;
    private Long psicologoId;

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getFobiaId() {
        return fobiaId;
    }

    public void setFobiaId(Long fobiaId) {
        this.fobiaId = fobiaId;
    }

    public Long getPsicologoId() {
        return psicologoId;
    }

    public void setPsicologoId(Long psicologoId) {
        this.psicologoId = psicologoId;
    }
    
    
}
