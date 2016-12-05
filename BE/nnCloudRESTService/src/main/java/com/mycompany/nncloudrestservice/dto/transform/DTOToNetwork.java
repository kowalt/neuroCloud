/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.dto.transform;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.dto.LayerDTO;
import com.mycompany.nncloudrestservice.dto.NetworkDTO;
import com.mycompany.nncloudrestservice.dto.NeuronDTO;
import com.mycompany.nncloudrestservice.dto.SynapseDTO;
import com.mycompany.nncloudrestservice.logic.Generate;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomasz
 */
public class DTOToNetwork {
  
    private NetworkDTO ndto;
    private Network n;
    private HashMap<Integer, Neuron> neuronsMap;
    
    public DTOToNetwork(NetworkDTO ndto)
    {
        this.ndto = ndto;
    }
	
    public Network transform()
    {       
        n.setId(ndto.getId());

        //obtain creation from database if network already exists
        NetworkDAO ndao = new NetworkDAO();
        Network nPrototype = null;
             try {
                 nPrototype = ndao.getItem(String.valueOf(ndto.getId()));
             } catch (Exception ex) {
                 Logger.getLogger(DTOToNetwork.class.getName()).log(Level.SEVERE, null, ex);
             }
        Date creationDate;
        if(nPrototype == null)
            creationDate = Calendar.getInstance().getTime();  
        else
            creationDate = nPrototype.getCreation();

        n.setCreation(creationDate);
        if(ndto.getId() != null)
            n.setId(ndto.getId());
        n.setName(ndto.getName());
        n.setUser(CurrentUserContainer.getInstance());
        n.setLayers(transformLayers(ndto));

        transformSynapses();

        return n;
    }
    
    private List<Layer> transformLayers(NetworkDTO ndto)
    {
    	List<Layer> layerList = new ArrayList<>(4);

    	LayerDTO l1d = ndto.getLayerIn();
    	layerList.add(transformLayer(l1d));
    	LayerDTO l2d = ndto.getLayer2();
    	layerList.add(transformLayer(l2d));
    	LayerDTO l3d = ndto.getLayer3();
    	layerList.add(transformLayer(l3d));
    	LayerDTO l4d = ndto.getLayerOut();
    	layerList.add(transformLayer(l4d));

    	return layerList;
    }
    
    private Layer transformLayer(LayerDTO ld)
    {
    	Layer layer = new Layer();
    	layer.setRelative_number(ld.getRelative_number());
        if(ld.getId() != null)
            layer.setId(ld.getId());
    	layer.setNetwork(n);
    	layer.setNeurons(transformNeurons(layer,ld.getNeurons()));
    	return layer;
    }
    
    private List<Neuron> transformNeurons(Layer layer, List<NeuronDTO> neuDTOl)
    {
    	List<Neuron> neuList = new ArrayList<>();
    	Neuron neuron = new Neuron();
    	
    	for(int i=0; i< neuDTOl.size(); i++)
    	{
            NeuronDTO ndt = neuDTOl.get(i);
            if( ndt.getId() != null )
                neuron.setId(ndt.getId());
            neuron.setId(ndt.getId());
            neuron.setLayer(layer);
            neuron.setActivation_functions(transformActivation_functions(ndt.getActivation()));

            neuList.add(neuron);
            if(ndt.getId() != null)
                neuronsMap.put(neuron.getId(), neuron);
    	}
    	return neuList;
    }
    
    private List<ActivationFunction> transformActivation_functions(String activation)
    {
    	String[] functionsWithDomainRules = activation.split(";");
    	List<ActivationFunction> afl = new ArrayList<>();

    	for(String functionWithDomainRules: functionsWithDomainRules)
    	{	
    		ActivationFunction af = new ActivationFunction();
    		String function = functionWithDomainRules.split("<=>")[0];
    		String domain_rules = function.split("<=>")[1];
    		af.setFunction(function);
    		af.setDomain_rule(domain_rules);
    		afl.add(af);
    	}
    	return afl;
    }
    
    private void transformSynapses()
    {
    	transformSingleSynapsesList(ndto.getSynapsesInput());
    	transformSingleSynapsesList(ndto.getSynapsesBetween1and2Layer());
    	transformSingleSynapsesList(ndto.getSynapsesBetween2and3Layer());
    	transformSingleSynapsesList(ndto.getSynapsesBetween3and4Layer());
    	transformSingleSynapsesList(ndto.getSynapsesOutput());
    }
    
    private void transformSingleSynapsesList(List<SynapseDTO> synDTOList)
    {
    	for(SynapseDTO sdt: synDTOList)
    	{
            Synapse s = new Synapse();
            s.setId(sdt.getId());
            s.setValue(sdt.getValue());
            s.setWeight(sdt.getWeight());
            
            Neuron neuIn = null, neuOut = null;
            
            if(sdt.getFrom() != 0) // if not input layer
            {
            	neuIn = neuronsMap.get(sdt.getFrom());
                neuIn.getSynapses_out().add(s); //Adding new synapse
                s.setNeuron_in(neuIn);
            }
            if(sdt.getTo() != 0) // if not output layer
            {
            	neuOut = neuronsMap.get(sdt.getTo());
                neuOut.getSynapses_in().add(s); //Adding new synapse
                s.setNeuron_out(neuOut);
            }
    	}
    }
}
