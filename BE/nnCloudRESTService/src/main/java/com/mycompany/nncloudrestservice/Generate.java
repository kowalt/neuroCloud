/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.controllers.GenerateController;
import com.mycompany.nncloudrestservice.model.Network;
import java.util.Calendar;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
@Path("generate")
public class Generate 
{
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public void generate(String x)
    {
        GenerateController gc = new GenerateController();
        
        gc.generateNetwork(new JSONObject(x));
        
        
    
    }
}
