/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.controllers;

import com.mycompany.nncloudrestservice.daos.DAO;
import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.model.Network;
import com.mycompany.nncloudrestservice.utils.SessionContainer;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class GetListOfNetworksController 
{
    public JSONArray getListOfNetworks()
    {
        List<Network> list = getFromDatabase();
        JSONArray rarrNetworks = new JSONArray();
        for(Network n: list)
        {
            JSONObject singleNetwork = new JSONObject();
            singleNetwork.put("id", n.getId());
            singleNetwork.put("name", n.getName());
            singleNetwork.put("creation-date", n.getCreation());
            rarrNetworks.put(singleNetwork);
        }
        return rarrNetworks;
    }
    
    private List<Network> getFromDatabase()
    {
        NetworkDAO ndao = new NetworkDAO();
        return ndao.getNetworksForCurrentUser();
    }
}

