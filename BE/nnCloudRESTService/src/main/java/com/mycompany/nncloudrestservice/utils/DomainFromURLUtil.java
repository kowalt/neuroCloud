/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomasz
 */
public class DomainFromURLUtil 
{
    public static String getDomain(String url)
    {
        URI uri = null;
        try 
        {
            uri = new URI(url);
        } 
        catch (URISyntaxException ex) 
        {
            ex.printStackTrace();
        }
        return uri.getHost();
    }
}
