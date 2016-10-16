/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.dto.NetworkDTO;
import com.mycompany.nncloudrestservice.dto.transform.NetworkToDTO;
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
            NetworkToDTO transform = new NetworkToDTO();
            NetworkDTO ndto = transform.transform(network);
            
            JAXBContext jaxbContext = JAXBContext.newInstance(NetworkDTO.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            marshaller.marshal(ndto, sw);
            r = sw.toString().replace("&lt;", "<").replace("&gt;", ">");
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
