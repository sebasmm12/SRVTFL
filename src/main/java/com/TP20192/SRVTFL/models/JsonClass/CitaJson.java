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
    private String documentoPaciente;
    private String fobia;
    private String documentoPsicologo;

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String getDocumentoPaciente() {
        return documentoPaciente;
    }

    public void setDocumentoPaciente(String documentoPaciente) {
        this.documentoPaciente = documentoPaciente;
    }

    public String getFobia() {
        return fobia;
    }

    public void setFobia(String fobia) {
        this.fobia = fobia;
    }

    public String getDocumentoPsicologo() {
        return documentoPsicologo;
    }

    public void setDocumentoPsicologo(String documentoPsicologo) {
        this.documentoPsicologo = documentoPsicologo;
    }
}
