/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.exceptions.NetworkAccessException;
import com.mycompany.nncloudrestservice.pojo.Network;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Tomasz
 */
public class Load 
{
    public String loadNetworkAsXML(int id) throws NetworkAccessException
    {
        String r = null;
        StringWriter sw = new StringWriter();
        NetworkDAO ndao = new NetworkDAO();
        //check if user has access for this network
        List<Network> nList = ndao.getNetworksForCurrentUser();
        
        if(nList.isEmpty())
            throw new NetworkAccessException("Unable to load network");
        
        boolean foundFlag = false;
        for(Network n: nList)
        {
            if(n.getId() == id)
            {    
                foundFlag = true;
                break;
            }
        }

        if(!foundFlag)
            throw new NetworkAccessException("Unable to load network");
        
        ndao.setLazyLoadMode(false);
        
        try
        {
            Network network = (Network)ndao.getItem(String.valueOf(id));
            JAXBContext jaxbContext = JAXBContext.newInstance(Network.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(network, sw);
            r = sw.toString();
        }
        catch(JAXBException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        return r;
    }
}
