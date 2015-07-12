/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;

import com.mycompany.nncloudrestservice.model.User;

/**
 *
 * @author Tomasz
 */
public interface UserDAO 
{
    public User getUser(String login, String pasword);
}
