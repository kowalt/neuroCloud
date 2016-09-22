/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import com.mycompany.nncloudrestservice.logic.RMIServer;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class ServerListContainer {
    private static List<RMIServer> instance = null;
    
    private ServerListContainer()
    {
    
    }
    
    public static List<RMIServer> getInstance()
    {        
        return instance;
    }
    
    public static List<RMIServer> addServer(RMIServer server)
    {
    
    }
    
}
