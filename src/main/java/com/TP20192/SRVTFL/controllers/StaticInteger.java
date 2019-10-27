/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

/**
 *
 * @author hp
 */
public  class StaticInteger {
    
    private static Integer integer;

	public StaticInteger() {
		
	}
	
	public static Integer getInteger() {
		return integer;
	}

	public static void setInteger(Integer integer) {
		StaticInteger.integer = integer;
	}
}
