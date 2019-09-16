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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pacId;
    
    @NotEmpty
    @Column(name="usu_id")
    private int usu_id;
    
    @NotEmpty
    @Column(name="pac_edad")
    private int pac_edad;
    
    @NotEmpty
    @Column(name="pac_nombre")
    private String pacNombre;
    
    @NotEmpty
    @Column(name="pac_apellido")
    private String pacApellido;
    
    @NotEmpty
    @Column(name="pac_imagen")
    private String pacImagen;
    
    @NotEmpty
    @Column(name="pac_telefono")
    private String pacTelefono;
    
    
    @NotEmpty
    @Column(name="pac_número_documento")
    private String pacNúmeroDocumento;
    
    @NotEmpty
    @Email
    @Column(name="pac_email")
    private String pacEmail;
    
    @NotEmpty
    @Column(name="pac_direccion")
    private String pacDireccion;
    
    @NotEmpty
    @Column(name="tip_doc_id")
    private int tipDocId;
    
    @NotEmpty
    @Column(name="pac_sexo_biologico")
    private boolean pacSexoBiologico;
    
    public int getPacId() {
        return pacId;
    }

    public void setPacId(int pacId) {
        this.pacId = pacId;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public int getPac_edad() {
        return pac_edad;
    }

    public void setPac_edad(int pac_edad) {
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

    public String getPacNúmeroDocumento() {
        return pacNúmeroDocumento;
    }

    public void setPacNúmeroDocumento(String pacNúmeroDocumento) {
        this.pacNúmeroDocumento = pacNúmeroDocumento;
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
    
    
    
}
