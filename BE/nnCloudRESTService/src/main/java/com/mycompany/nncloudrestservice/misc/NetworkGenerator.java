/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.misc;

import com.mycompany.nncloudrestservice.model.ActivationFunction;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class NetworkGenerator 
{
    private int[] neuronsPerLayer;
    private String name;
    private ActivationFunction[] afunctions;
    
    public NetworkGenerator(JSONObject options)
    {
        options.get("name");
        neuronsPerLayer[0]=Integer.parseInt(options.get("1stlayer").toString());
        neuronsPerLayer[1]=Integer.parseInt(options.get("2ndlayer").toString());
        neuronsPerLayer[2]=Integer.parseInt(options.get("3rdlayer").toString());
        neuronsPerLayer[3]=Integer.parseInt(options.get("4thlayer").toString());
        unpackActivationFunctions(options.get("activationFunction").toString());
    }
    
    private void unpackActivationFunctions(String af)
    {
        
    
    }
}
