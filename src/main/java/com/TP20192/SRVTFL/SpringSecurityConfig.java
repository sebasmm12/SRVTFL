/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL;

import com.TP20192.SRVTFL.aut.handler.LoginSuccessHandler;
import com.TP20192.SRVTFL.models.implementation.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author USUARIO
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private LoginSuccessHandler successHandler;
    
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.authorizeRequests().
               antMatchers("/css/**","/js/**","/images/**","/","/login","/psicologo/index","/index","/webfonts/**","api/**","/fonts/**","/vendor/**","/Recepcionista/index","/Recepcionista/gestionarCitas").permitAll()
        .anyRequest().permitAll()//si es autenticado .authenticated()
        .and()
          .formLogin()
          .loginPage("/login")
        .and()
           .logout().permitAll();
        
    }
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        
        builder.userDetailsService(usuarioService)
               .passwordEncoder(passwordEncoder);
    }
    
}
