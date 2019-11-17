/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;


/**
 *
 * @author hp
 */
@Entity
@Table(name = "T_Estado_Cita")
public class EstadoCita implements Serializable{
    
    public EstadoCita(){
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="est_cit_id")
    private int estCitId;
    
    //@NotEmpty
    @Column(name="est_cit_nombre_estado")
    private String estCitNombreEstado;

    public int getEstCitId() {
        return estCitId;
    }

    public void setEstCitId(int estCitId) {
        this.estCitId = estCitId;
    }

    public String getEstCitNombreEstado() {
        return estCitNombreEstado;
    }

    public void setEstCitNombreEstado(String estCitNombreEstado) {
        this.estCitNombreEstado = estCitNombreEstado;
    }
    
}
