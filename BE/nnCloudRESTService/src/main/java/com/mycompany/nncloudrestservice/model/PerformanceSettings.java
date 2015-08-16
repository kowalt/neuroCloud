/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

/**
 *
 * @author Tomasz
 */
public class PerformanceSettings 
{
    private boolean visualisation;
    private User user;

    public boolean isVisualisation() 
    {
        return visualisation;
    }

    public void setVisualisation(boolean visualisation) 
    {
        this.visualisation = visualisation;
    }

    public User getUser() 
    {
        return user;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }
}
