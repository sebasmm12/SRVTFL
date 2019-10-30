/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.JsonClass;

/**
 *
 * @author USUARIO
 */
public class DiagnosticoJson {
    
    private Long diaId;
    
    private String diaPruebasAplicadas;
    
    private String diaObservaciones;
    
    private String diaDiagnostico;
    
    private String diaRecomendacion;
    
    private Long citId;

    public Long getDiaId() {
        return diaId;
    }

    public void setDiaId(Long diaId) {
        this.diaId = diaId;
    }

    public String getDiaPruebasAplicadas() {
        return diaPruebasAplicadas;
    }

    public void setDiaPruebasAplicadas(String diaPruebasAplicadas) {
        this.diaPruebasAplicadas = diaPruebasAplicadas;
    }

    public String getDiaObservaciones() {
        return diaObservaciones;
    }

    public void setDiaObservaciones(String diaObservaciones) {
        this.diaObservaciones = diaObservaciones;
    }

    public String getDiaDiagnostico() {
        return diaDiagnostico;
    }

    public void setDiaDiagnostico(String diaDiagnostico) {
        this.diaDiagnostico = diaDiagnostico;
    }

    public String getDiaRecomendacion() {
        return diaRecomendacion;
    }

    public void setDiaRecomendacion(String diaRecomendacion) {
        this.diaRecomendacion = diaRecomendacion;
    }

    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }
}
