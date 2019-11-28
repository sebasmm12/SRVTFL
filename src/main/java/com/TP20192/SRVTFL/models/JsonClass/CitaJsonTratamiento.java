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
public class CitaJsonTratamiento {
    private Cita cita;
    private String numeroDocumento;
    private String docPsicologo;

    public Cita getCita() {
        return cita;
    }

    public String getDocPsicologo() {
        return docPsicologo;
    }

    public void setDocPsicologo(String docPsicologo) {
        this.docPsicologo = docPsicologo;
    }
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    
}
