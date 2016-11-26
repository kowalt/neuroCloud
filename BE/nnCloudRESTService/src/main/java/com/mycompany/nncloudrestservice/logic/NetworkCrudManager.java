package com.mycompany.nncloudrestservice.logic;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.dto.NetworkDTO;
import com.mycompany.nncloudrestservice.dto.transform.DTOToNetwork;
import com.mycompany.nncloudrestservice.dto.transform.NetworkToDTO;
import com.mycompany.nncloudrestservice.exceptions.NetworkAccessException;
import com.mycompany.nncloudrestservice.pojo.Network;

public class NetworkCrudManager {
    public JSONArray getListOfNetworks()
    {
    	NetworkDAO ndao = new NetworkDAO();
        List<Network> list = ndao.getNetworksForCurrentUser();
        JSONArray rarrNetworks = new JSONArray();
        for(Network n: list)
        {
            JSONObject singleNetwork = new JSONObject();
            singleNetwork.put("id", n.getId());
            singleNetwork.put("name", n.getName());
            singleNetwork.put("creationdate", n.getCreation());
            rarrNetworks.put(singleNetwork);
        }
        return rarrNetworks;
    }

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
            //r = sw.toString().replace("&lt;", "<").replace("&gt;", ">");
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
    
	public void removeNetwork(int id)
	{
		NetworkDAO ndao = new NetworkDAO();
		ndao.setLazyLoadMode(true);
        Network network = ndao.getItem(String.valueOf(id));
		ndao.removeItem(network);
	}

	public void updateNetwork(String networkRaw)
	{
            /*
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NetworkDTO.class);
			Unmarshaller unm = jaxbContext.createUnmarshaller();
			StringBuffer xmlStr = new StringBuffer(networkRaw);
			NetworkDTO ndto = (NetworkDTO) unm.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
			DTOToNetwork dtotn = new DTOToNetwork(ndto);
			Network network = dtotn.transform();

			NetworkDAO ndao = new NetworkDAO();
			ndao.updateItem(network);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
               */
	}
}
