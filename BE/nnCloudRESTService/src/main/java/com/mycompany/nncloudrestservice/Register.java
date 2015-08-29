/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.controllers.RegisterController;
import com.mycompany.nncloudrestservice.exceptions.UserExistsException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
@Path("register")
public class Register 
{
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(String x)
    {
        JSONObject request = new JSONObject(x);
        RegisterController rc = new RegisterController();
        
        try
        {
            rc.registerNewUser(request);
        }
        catch(UserExistsException ex)
        {
            JSONObject error_response = new JSONObject();
            error_response.put("error", ex.getMessage());
            return Response.status(400).entity(error_response.toString()).build();
        }
        
        return Response.status(201).build();
    }
}
