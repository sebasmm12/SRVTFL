/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author hp
 */

@Table(name="T_Paciente")
@Entity
public class Paciente implements Serializable{
    
    public Paciente(){
        
    }
    
    public String nombreCompleto(){
        return this.pacNombre+" "+this.pacApellido;
    }
    
    @Id
    @Column(name="pac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacId;
    
    //@NotEmpty
    @Column(name="usu_id")
    private long usu_id;
    
    //@NotNull
    @Column(name="pac_edad")
    private Integer pac_edad;
    
    //@NotEmpty
    @Column(name="pac_nombre")
    private String pacNombre;
    
    //@NotEmpty
    @Column(name="pac_apellido")
    private String pacApellido;
    
    //@NotEmpty
    @Column(name="pac_imagen")
    private String pacImagen;
    
    //@NotEmpty
    //@Pattern(regexp = "\\d{9}")
    @Column(name="pac_telefono")
    private String pacTelefono;
    
    
    //@NotEmpty
    //@Pattern(regexp = "\\d{8}")
    @Column(name="pac_número_documento")
    private String pacNumeroDocumento;
    
    //@NotEmpty
    //@Email
    @Column(name="pac_email")
    private String pacEmail;
    
    //@NotEmpty
    @Column(name="pac_direccion")
    private String pacDireccion;
    
    //@NotEmpty
    @Column(name="tip_doc_id")
    private int tipDocId;
    
    //@NotEmpty
    @Column(name="pac_sexo_biologico")
    private boolean pacSexoBiologico;

    
    
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="pac_id")
    @JsonIgnore
    private List<Cita> citas;

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
    
    public Long getPacId() {
        return pacId;
    }

    public void setPacId(Long pacId) {
        this.pacId = pacId;
    }
    
    

    public long getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(long usu_id) {
        this.usu_id = usu_id;
    }

    public Integer getPac_edad() {
        return pac_edad;
    }

    public void setPac_edad(Integer pac_edad) {
        this.pac_edad = pac_edad;
    }
    public String getPacNombre() {
        return pacNombre;
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

    public String getPacImagen() {
        return pacImagen;
    }

    public void setPacImagen(String pacImagen) {
        this.pacImagen = pacImagen;
    }

    public String getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public String getPacNumeroDocumento() {
        return pacNumeroDocumento;
    }

    public void setPacNumeroDocumento(String pacNumeroDocumento) {
        this.pacNumeroDocumento = pacNumeroDocumento;
    }

   

    public String getPacEmail() {
        return pacEmail;
    }

    public void setPacEmail(String pacEmail) {
        this.pacEmail = pacEmail;
    }

    public String getPacDireccion() {
        return pacDireccion;
    }

    public void setPacDireccion(String pacDireccion) {
        this.pacDireccion = pacDireccion;
    }

    public int getTipDocId() {
        return tipDocId;
    }

    public void setTipDocId(int tipDocId) {
        this.tipDocId = tipDocId;
    }

    public boolean isPacSexoBiologico() {
        return pacSexoBiologico;
    }

    public void setPacSexoBiologico(boolean pacSexoBiologico) {
        this.pacSexoBiologico = pacSexoBiologico;
    }

    @Override
    public String toString() {
        return "Paciente{" + "pacId=" + pacId + ", usu_id=" + usu_id + ", pac_edad=" + pac_edad + ", pacNombre=" + pacNombre + ", pacApellido=" + pacApellido + ", pacImagen=" + pacImagen + ", pacTelefono=" + pacTelefono + ", pacNumeroDocumento=" + pacNumeroDocumento + ", pacEmail=" + pacEmail + ", pacDireccion=" + pacDireccion + ", tipDocId=" + tipDocId + ", pacSexoBiologico=" + pacSexoBiologico + '}';
    }
    

  
        
    
}
