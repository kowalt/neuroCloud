/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.dto.transform;

import com.mycompany.nncloudrestservice.dto.LayerDTO;
import com.mycompany.nncloudrestservice.dto.NetworkDTO;
import com.mycompany.nncloudrestservice.dto.NeuronDTO;
import com.mycompany.nncloudrestservice.dto.SynapseDTO;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class NetworkToDTO {
    private NetworkDTO ndto;
    
    public NetworkDTO transform(Network network)
    {
        NetworkDTO ndto = new NetworkDTO();

        ndto.setId(network.getId());
        ndto.setName(network.getName());

        List<Layer> layers = network.getLayers();

        ndto.setLayerIn(convertLayer(layers.get(0)));
        if(layers.get(1) != null)
            ndto.setLayer2(convertLayer(layers.get(1)));
        if(layers.get(2) != null)
            ndto.setLayer3(convertLayer(layers.get(2)));
        ndto.setLayerOut(convertLayer(layers.get(3)));
        
        List<Synapse> synapsesBuffer = new ArrayList<>();

        for(Neuron n: layers.get(0).getNeurons())
            synapsesBuffer.add(n.getSynapses_in().get(0));

        List<SynapseDTO> synapsesInputDTO;
        synapsesInputDTO = convert(synapsesBuffer);

        ndto.setSynapsesInput(synapsesInputDTO);

        //inner
        synapsesBuffer = new ArrayList<>();
        for(int i=1; i< layers.size()-1; i++)
        {
            for(Neuron n: layers.get(i).getNeurons())
                synapsesBuffer.addAll(n.getSynapses_in());
            ndto.addSynapsesInner(convert(synapsesBuffer));
        }
        
        //out
        synapsesBuffer = new ArrayList<>();
        for(Neuron n: layers.get(layers.size()-1).getNeurons())    
            synapsesBuffer.addAll(n.getSynapses_out());
        ndto.setSynapsesOutput(convert(synapsesBuffer));      
        
        return ndto;
    }
    
    private LayerDTO convertLayer(Layer l)
    {
        LayerDTO ldto = new LayerDTO();
        
        ldto.setRelative_number(l.getRelative_number());
        ldto.setNeurons(convertNeurons(l.getNeurons()));
        
        return ldto;
    }
    
    private List<NeuronDTO> convertNeurons(List<Neuron> neuron_list)
    {
        List<NeuronDTO> neuDTOList = new ArrayList<>();

        for(Neuron n: neuron_list)
        {
            NeuronDTO neuDTO = new NeuronDTO();
            neuDTO.setActivation(unwindActivationFunctions(n.getActivation_functions()));
            neuDTO.setId(n.getId());
            neuDTOList.add(neuDTO);
        }

        return neuDTOList;
    }
    
    private String unwindActivationFunctions(List<ActivationFunction> afl)
    {
        String buffer = "";

        for(ActivationFunction af: afl)
        {
            buffer += af.getFunction();
            if(af.getDomain_rule() != "")
            {    
                buffer += "<=>";
                buffer += af.getDomain_rule();
            }
            buffer += ";";
        }

        return buffer;
    }
    
    private List<SynapseDTO> convert(List<Synapse> synapses)
    {
        List<SynapseDTO> sdtol = new ArrayList<>();
        for(Synapse s: synapses)
        {
            SynapseDTO sdto = new SynapseDTO();
            if(s.getNeuron_out() != null) //Not output layer    
                sdto.setTo(s.getNeuron_out().getId());
            
            if(s.getNeuron_in() != null) //Not input layer
                sdto.setFrom(s.getNeuron_in().getId());

            sdto.setId(s.getId());
            sdto.setValue(s.getValue());
            sdto.setWeight(s.getWeight());
            sdtol.add(sdto);
        }
        return sdtol;
    }
}