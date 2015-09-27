/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.controllers;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.model.Network;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Tomasz
 */
public class LoadController 
{
    public String loadNetworkAsXML(int id)
    {
        String r = null;
        StringWriter sw = new StringWriter();
        NetworkDAO ndao = new NetworkDAO();
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
