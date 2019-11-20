/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.domain;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author USUARIO
 */
public class PacientePsicologo {
    
    private Long pacId;
    
    private String pacNombre;
    
    private String pacApellido;
    
    private Long sesion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date ultimaSesion; 

    public Long getPacId() {
        return pacId;
    }

    public Long getSesion() {
        return sesion;
    }

    public void setSesion(Long sesion) {
        this.sesion = sesion;
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public void setPacId(Long pacId) {
        this.pacId = pacId;
    }

    public String getPacNombre() {
        return pacNombre;
    }

    public PacientePsicologo(Long pacId, String pacNombre, String pacApellido, Long sesion, Date ultimaSesion) {
        this.pacId = pacId;
        this.pacNombre = pacNombre;
        this.pacApellido = pacApellido;
        this.sesion = sesion;
        this.ultimaSesion = ultimaSesion;
    }

   

    public void setPacNombre(String pacNombre) {
        this.pacNombre = pacNombre;
    }

    public String getPacApellido() {
        return pacApellido;
    }

    public void setPacApellido(String pacApellido) {
        this.pacApellido = pacApellido;
    }
    
    
    
}
