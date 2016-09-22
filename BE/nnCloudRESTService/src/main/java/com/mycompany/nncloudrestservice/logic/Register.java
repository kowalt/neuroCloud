/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.UserDAO;
import com.mycompany.nncloudrestservice.exceptions.UserExistsException;
import com.mycompany.nncloudrestservice.pojo.User;
import com.mycompany.nncloudrestservice.utils.SafeHashUtil;
import java.util.Calendar;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class Register 
{
    public void registerNewUser(JSONObject regData) throws UserExistsException
    {
        User user = new User();
        user.setActivated(false);
        user.setEmail(regData.getString("email"));
        user.setLogin(regData.getString("login"));
        user.setInfo_to_admin(regData.getString("info_to_admin"));
        //set password
        String givenPassword = regData.getString("password");

        String givenEncryptedPassword = SafeHashUtil.getHash(givenPassword);
        user.setPassword(givenEncryptedPassword);
        user.setRegistered(Calendar.getInstance().getTime());
        user.setNetworks(null);
        
        UserDAO udao = new UserDAO();
        udao.addItem(user);
    }
}
