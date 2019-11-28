/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import net.bytebuddy.implementation.bind.annotation.Empty;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "T_Usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long usu_id;

    //@Column(name = "est_usu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "est_usu_id")
    @JsonIgnore
    private EstadoUsuario estadoUsuario;

    @Column(name = "usu_codigo")
    private String usu_codigo;

    public Long getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(Long usu_id) {
        this.usu_id = usu_id;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getUsu_codigo() {
        return usu_codigo;
    }

    public void setUsu_codigo(String usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    public String getUsu_contraseña() {
        return usu_contraseña;
    }

    public void setUsu_contraseña(String usu_contraseña) {
        this.usu_contraseña = usu_contraseña;
    }

    @NotEmpty
    @Column(name = "usu_contraseña")
    private String usu_contraseña;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_id")
    @JsonIgnore
    private List<UsuarioRol> roles;

    public List<UsuarioRol> getRoles() {
        return roles;
    }

    public void setRoles(List<UsuarioRol> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_id")
    @JsonIgnore
    private List<Notificacion> Notificaciones;

    public List<Notificacion> getNotificaciones() {
        return Notificaciones;
    }

    public void setNotificaciones(List<Notificacion> Notificaciones) {
        this.Notificaciones = Notificaciones;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_id")
    @JsonIgnore
    private DetalleUsuario detalleUsuario;

    public DetalleUsuario getDetalleUsuario() {
        return detalleUsuario;
    }

    public void setDetalleUsuario(DetalleUsuario detalleUsuario) {
        this.detalleUsuario = detalleUsuario;
    }

    @JsonIgnore
    public int getConteo() {
        int conteo = 0;
        for (int i = 0; i < this.getNotificaciones().size(); i++) {
            if (this.getNotificaciones().get(i).getEstNotId().getEstNotId() == 2) {
                conteo++;
            }
        }
        return conteo;
    }
}
