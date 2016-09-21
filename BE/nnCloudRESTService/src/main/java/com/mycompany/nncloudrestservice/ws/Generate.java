/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;

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
    public Response generate(String x)
    {
        com.mycompany.nncloudrestservice.model.Generate gc = new com.mycompany.nncloudrestservice.model.Generate();
       
        try
        {
            gc.generateNetwork(new JSONObject(x));
            System.out.println("Network generated");
        }
        catch(JSONException je)
        {
            return Response.status(400).build();
        }
        return Response.status(201).build();
    }
}
