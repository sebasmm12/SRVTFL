/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */

@Entity
@Table(name ="T_Rol_Permiso")
public class RolPermiso implements Serializable {
    
    @EmbeddedId
    private RolPermisoId Id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="per_id")
    @MapsId("perId")
    private Permiso permiso;

    public RolPermisoId getId() {
        return Id;
    }

    public void setId(RolPermisoId Id) {
        this.Id = Id;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
    
}
