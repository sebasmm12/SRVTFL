/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T_Actividad")
public class Actividad {
    
    @Id
    @Column(name = "act_id")
    private Long act_id;
    
    @Column(name = "act_nombre")
    private String act_nombre;
    
    @Column(name = "agenda_id")
    private Long agenda_id;
    
    @Column(name = "act_descripcion")
    private String act_descripcion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/Lima")
    @Column(name = "act_inicio")
    private Date act_inicio;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/Lima")
    @Column(name = "act_fin")
    private Date act_fin;
    
   /* @Column(name="est_actividad")
    private Long est_act_id;
*/
    
    @JoinColumn(name="est_actividad")
    @ManyToOne(fetch = FetchType.EAGER)   
   // @JsonIgnore
    private EstadoActividad estadoActividad;

    public EstadoActividad getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(EstadoActividad estadoActividad) {
        this.estadoActividad = estadoActividad;
    }
    
    /*
    public Long getEst_act_id() {
        return est_act_id;
    }

    public void setEst_act_id(Long est_act_id) {
        this.est_act_id = est_act_id;
    }*/
    
    public Long getAct_id() {
        return act_id;
    }

    public void setAct_id(Long act_id) {
        this.act_id = act_id;
    }

    public String getAct_nombre() {
        return act_nombre;
    }

    public void setAct_nombre(String act_nombre) {
        this.act_nombre = act_nombre;
    }

    public String getAct_descripcion() {
        return act_descripcion;
    }

    public void setAct_descripcion(String act_descripcion) {
        this.act_descripcion = act_descripcion;
    }

    public Date getAct_inicio() {
        return act_inicio;
    }

    public void setAct_inicio(Date act_inicio) {
        this.act_inicio = act_inicio;
    }

    public Long getAgenda_id() {
        return agenda_id;
    }

    public void setAgenda_id(Long agenda_id) {
        this.agenda_id = agenda_id;
    }

    public Date getAct_fin() {
        return act_fin;
    }

    public void setAct_fin(Date act_fin) {
        this.act_fin = act_fin;
    }
    
    
    
}
