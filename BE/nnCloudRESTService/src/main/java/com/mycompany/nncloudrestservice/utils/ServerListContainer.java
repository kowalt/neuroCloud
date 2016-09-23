/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import com.mycompany.nncloudrestservice.logic.RMIServer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class ServerListContainer {
    private static List<RMIServer> instance = new ArrayList<>();

    private ServerListContainer()
    {
    
    }
    
    public static List<RMIServer> getInstance()
    {        
        return instance;
    }
    
    public static void addServer(RMIServer server)
    {
    	instance.add(server.getId(), server);
    }
    
    public static RMIServer getAt(int id)
    {
    	return instance.get(id);
    }
    
    public static void deleteAt(int id)
    {
    	instance.remove(id);
    }
    
}
