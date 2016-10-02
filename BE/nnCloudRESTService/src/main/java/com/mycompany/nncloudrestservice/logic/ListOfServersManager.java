/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.exceptions.NNCloudRestServiceException;
import com.mycompany.nncloudrestservice.pojo.RMIServer;
import com.mycompany.nncloudrestservice.utils.ServerListContainer;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class ListOfServersManager {
    public JSONArray getListOfServers()
    {
        JSONArray r = new JSONArray();

        for(RMIServer s: ServerListContainer.getInstance())
        {
            JSONObject obj = new JSONObject();
            obj.put("label", s.getLabel());
            obj.put("name", s.getName());
            r.put(obj);
        }
        return r;
    }
    
    public void setActiveServer(int id) throws NNCloudRestServiceException
    {
        List<RMIServer> list = ServerListContainer.getInstance();
        
        if(!list.stream().filter( o -> o.getId().equals(id)).findFirst().isPresent())
            throw new NNCloudRestServiceException("Wrong id");
                
        ServerListContainer.activeId = id;
    }
}
