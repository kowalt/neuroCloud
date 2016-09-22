/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import com.mycompany.nncloudrestservice.ws.DownloadOutputVector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Tomasz
 */
public class GetOutput {
    private Network network;
    
    public GetOutput(int id)
    {
        try {
            NetworkDAO ndao = new NetworkDAO();
            network = ndao.getItem(String.valueOf(id));
        } catch (Exception ex) {
            Logger.getLogger(GetOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String validate(int id)
    {
        if(network.getUser().getId() != CurrentUserContainer.getInstance().getId())
            return "Network doesn't exist or isn't associated with this user.";

        return "OK";
    }
    
    public JSONArray getOutput(int id)
    {
        NetworkDAO ndao = new NetworkDAO();
    	
    	Network network = null;
        
        try {
            network = ndao.getItem(String.valueOf(id));
        } catch (Exception ex) {
            Logger.getLogger(DownloadOutputVector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JSONArray r = new JSONArray();
        
        for(Neuron n : network.getLayers().get(2).getNeurons())
        {
            for(Synapse s: n.getSynapses_in())
            {
                r.put(s.getValue());
            }    
        }

        return r;
    }
}
