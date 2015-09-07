/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.model.User;
import com.mycompany.nncloudrestservice.utils.SafeHashUtil;

/**
 *
 * @author Tomasz
 */
public class CurrentUserContainer 
{
    private static User instance = null;
    
    public static User getInstance()
    {        
        return instance;
    }
    
    public static void loadInstance(String encToken)
    {
        String raw = SafeHashUtil.getHash(encToken);
        UserDAO udao = new UserDAO();
        instance = udao.getItem(raw);
    }
}
