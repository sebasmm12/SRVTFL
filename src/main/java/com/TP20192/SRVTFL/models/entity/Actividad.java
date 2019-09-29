/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    private Date act_descripcion;
    
    @Column(name = "act_inicio")
    private Date act_fin;

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

    public Long getAgenda_id() {
        return agenda_id;
    }

    public void setAgenda_id(Long agenda_id) {
        this.agenda_id = agenda_id;
    }

   

    public Date getAct_descripcion() {
        return act_descripcion;
    }

    public void setAct_descripcion(Date act_descripcion) {
        this.act_descripcion = act_descripcion;
    }

    public Date getAct_fin() {
        return act_fin;
    }

    public void setAct_fin(Date act_fin) {
        this.act_fin = act_fin;
    }
    
    
    
}