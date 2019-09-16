/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author hp
 */
@Entity
@Table(name="T_Diagnostico")
public class Diagnostico implements Serializable{
    
    public Diagnostico(){
        
    }
    
    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dia_id")
    private int diaId;
    
    @NotEmpty
    @Column(name="dia_pruebas_aplicadas")
    private String diaPruebasAplicadas;
    
    @NotEmpty
    @Column(name="dia_observaciones")
    private String diaObservaciones;
    
    
    @NotEmpty
    @Column(name="dia_antecedentes_caso")
    private String diaAntecedentesCaso;
    
    @NotEmpty
    @Column(name="dia_antecedentes_personales")
    private String diaAntecedentesPersonales;
    
    @NotEmpty
    @Column(name="dia_recomendacion")
    private String diaRecomendacion;

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
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

    public String getDiaAntecedentesCaso() {
        return diaAntecedentesCaso;
    }

    public void setDiaAntecedentesCaso(String diaAntecedentesCaso) {
        this.diaAntecedentesCaso = diaAntecedentesCaso;
    }

    public String getDiaAntecedentesPersonales() {
        return diaAntecedentesPersonales;
    }

    public void setDiaAntecedentesPersonales(String diaAntecedentesPersonales) {
        this.diaAntecedentesPersonales = diaAntecedentesPersonales;
    }

    public String getDiaRecomendacion() {
        return diaRecomendacion;
    }

    public void setDiaRecomendacion(String diaRecomendacion) {
        this.diaRecomendacion = diaRecomendacion;
    }
    
    
}
