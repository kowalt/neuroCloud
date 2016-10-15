/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.dto.transform;

import com.mycompany.nncloudrestservice.dto.LayerDTO;
import com.mycompany.nncloudrestservice.dto.NetworkDTO;
import com.mycompany.nncloudrestservice.dto.NeuronDTO;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
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

        ndto.setLayer1(convertLayer(layers.get(0)));
        ndto.setLayer2(convertLayer(layers.get(1)));
        ndto.setLayer3(convertLayer(layers.get(2)));
        ndto.setLayer4(convertLayer(layers.get(3)));
        
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
}
