/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Tomasz
 */
public class SafeHashUtil
{
    public static String getHash(String raw)
    {
        if(raw.equals("0"))
            return "0";
        
        String salt = raw.substring(0,2);
    
        return DigestUtils.sha256Hex(salt+raw);
    }
}
