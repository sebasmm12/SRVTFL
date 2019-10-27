/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.Entidades;

/**
 *
 * @author PIERO
 */
public class VrSimulacion {
    
    private long sim_id ;
    
    private String nombre;

    private int nivel;
    
    private Long ressimid;

    public long getSim_id() {
        return sim_id;
    }

    public  void setSim_id(long sim_id) {
        this.sim_id = sim_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Long getRessimid() {
        return ressimid;
    }

    public void setRessimid(Long ressimid) {
        this.ressimid = ressimid;
    }

    public VrSimulacion(long sim_id, String nombre, int nivel, Long ressimid) {
        this.sim_id = sim_id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.ressimid = ressimid;
    }

    public VrSimulacion() {
    }
    
    
    
    
}
