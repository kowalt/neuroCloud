/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tomasz
 */
public class User 
{
    private int id;
    private String login;
    private String email;
    private String password;
    private boolean activated;
    private String info_to_admin;
    private String session_id;
    private Date registered;
    private Set<Network> networks = new HashSet<>();
    private PerformanceSettings performance_settings;

    public PerformanceSettings getPerformance_settings() {
        return performance_settings;
    }

    public void setPerformance_settings(PerformanceSettings performance_settings) {
        this.performance_settings = performance_settings;
    }
    
    public Set<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(Set<Network> networks) {
        this.networks = networks;
    }
    
    public int getId() 
    {
        return id;
    }
    
    public void setId(int id) 
    {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getInfo_to_admin() {
        return info_to_admin;
    }

    public void setInfo_to_admin(String info_to_admin) {
        this.info_to_admin = info_to_admin;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }    
}
