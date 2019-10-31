/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name="T_Notificacion")
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="not_id")
    private Long notId;
    
    @JoinColumn(name="usu_id")
    @ManyToOne
    private Usuario usuId;

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }
    
    @JoinColumn(name="est_not_id")
    @ManyToOne
    private EstadoNotificacion estNotId;
    
    @Column(name="not_nombre")
    private String notNombre;
    
    @Column(name="not_descripcion")
    private String notDescripcion;
    
    @Column(name="usu_envio")
    private int usuEnvio;
    
    @Column(name="not_fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date notFecha;
    
    @PrePersist
    public void prePersist() {
        notFecha = new Date();
    }
    
    
    @Column(name="not_url")
    private String notUrl;
    
    @JoinColumn(name="tip_not_id")
    @ManyToOne
    private TipoNotificacion tipNotId;

    public Long getNotId() {
        return notId;
    }

    public void setNotId(Long notId) {
        this.notId = notId;
    }

    public String getNotNombre() {
        return notNombre;
    }

    public void setNotNombre(String notNombre) {
        this.notNombre = notNombre;
    }

    public String getNotDescripcion() {
        return notDescripcion;
    }

    public void setNotDescripcion(String notDescripcion) {
        this.notDescripcion = notDescripcion;
    }

    public int getUsuEnvio() {
        return usuEnvio;
    }

    public void setUsuEnvio(int usuEnvio) {
        this.usuEnvio = usuEnvio;
    }

    public Date getNotFecha() {
        return notFecha;
    }

    public void setNotFecha(Date notFecha) {
        this.notFecha = notFecha;
    }

    public String getNotUrl() {
        return notUrl;
    }

    public void setNotUrl(String notUrl) {
        this.notUrl = notUrl;
    }

    public EstadoNotificacion getEstNotId() {
        return estNotId;
    }

    public void setEstNotId(EstadoNotificacion estNotId) {
        this.estNotId = estNotId;
    }

    public TipoNotificacion getTipNotId() {
        return tipNotId;
    }

    public void setTipNotId(TipoNotificacion tipNotId) {
        this.tipNotId = tipNotId;
    }
    
}
