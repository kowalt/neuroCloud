/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.dto.transform.DTOToNetwork;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import com.mycompany.nncloudrestservice.ws.DownloadOutputVector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

/**
 *
 * @author Tomasz
 */
public class GetOutput {
	private static final Logger logger = LogManager.getLogger(GetOutput.class);
    private Network network;
    
    public GetOutput(int id)
    {
        try {
            NetworkDAO ndao = new NetworkDAO();
            network = ndao.getItem(String.valueOf(id));
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
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
            logger.error(ex.getStackTrace());
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
