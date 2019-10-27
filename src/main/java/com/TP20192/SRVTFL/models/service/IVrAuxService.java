/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.Entidades.VrSimulacion;

/**
 *
 * @author PIERO
 */
public interface IVrAuxService {
    
  public  void iniciarTratamiento(Long idtratamiento, int nivel , Long  resSimId, String nombreSimulacion); 
   
  public  void guardarNivel(Long idtratamiento, int nivel); 
    
 public  VrSimulacion iniciarSimulacion();  
}
