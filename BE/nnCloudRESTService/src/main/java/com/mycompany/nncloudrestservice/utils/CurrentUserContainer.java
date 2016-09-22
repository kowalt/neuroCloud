/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.pojo.User;

/**
 *
 * @author Tomasz
 */
public class CurrentUserContainer 
{
    private static User instance = null;
    
    private CurrentUserContainer()
    {
    
    }
    
    public static User getInstance()
    {        
        return instance;
    }
    
    public static void loadInstance(String encToken)
    {
        String raw = SafeHashUtil.getHash(encToken);
        UserDAO udao = new UserDAO(true);
        instance = udao.getItem(raw);
    }
}
