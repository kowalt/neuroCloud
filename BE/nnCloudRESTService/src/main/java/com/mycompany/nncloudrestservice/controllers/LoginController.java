/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.controllers;

import com.mycompany.nncloudrestservice.exceptions.LoginException;
import com.mycompany.nncloudrestservice.exceptions.LogoutException;
import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.model.User;
import com.mycompany.nncloudrestservice.utils.SafeHashUtil;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class LoginController 
{
    public void loginUser(JSONObject creds) throws LoginException
    {
        String login = creds.getString("login");
        String plainPasswd = creds.getString("password");
        
        UserDAO udao = new UserDAO();
        
        User user = udao.getItem(login, SafeHashUtil.getHash(plainPasswd));
        
        if(user == null)
            throw new LoginException("Incorrect credentials");
        
        if(!user.isActivated())
            throw new LoginException("The user hasn't been activated yet.");
    }
    
    public void saveSession(JSONObject creds, String uuid)
    {
        UserDAO udao = new UserDAO();
        User user = null;
        
        try
        {
            user = udao.getItem(creds.getString("login"), SafeHashUtil.getHash(creds.getString("password")));
        }
        catch(LoginException le)
        {
            System.out.println("Out of reach ;-)");
        }
        
        user.setSession_id(SafeHashUtil.getHash(uuid));
        udao.updateItem(user);
    }
    
    public boolean checkIfTokenIsCorrect(String token)
    {
        UserDAO udao = new UserDAO();
        User user = udao.getItem(SafeHashUtil.getHash(token));
        if (user == null)
            return false;

        return true;
    }
    
    public void destroySession(String token) throws LogoutException
    {
        UserDAO udao = new UserDAO();
        User user = udao.getItem(SafeHashUtil.getHash(token));
        user.setSession_id("0");
        udao.updateItem(user);
    }
}
