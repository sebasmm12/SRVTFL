package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Estado_Actividad")
public class EstadoActividad implements Serializable{

    @Id
    @Column(name="est_act_id")
    private int estActId;
    
    @Column(name = "est_act_nombre")
    private String estActNombre;
    
    @Column(name = "est_act_color")
    private String estActColor;

    public int getEstActId() {
        return estActId;
    }

    public void setEstActId(int estActId) {
        this.estActId = estActId;
    }

    public String getEstActNombre() {
        return estActNombre;
    }

    public void setEstActNombre(String estActNombre) {
        this.estActNombre = estActNombre;
    }

    public String getEstActColor() {
        return estActColor;
    }

    public void setEstActColor(String estActColor) {
        this.estActColor = estActColor;
    }

    
    
    
    
    
}
